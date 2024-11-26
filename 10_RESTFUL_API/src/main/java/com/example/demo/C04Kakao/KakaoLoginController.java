package com.example.demo.C04Kakao;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping("/kakao")
public class KakaoLoginController {

    String REDIRECT_URL = "http://localhost:8090/kakao/callback";
    String CLIENT_ID = "12ea49b8bf4a25e396372e2e09ebdf6b";
    String RESPONSE_TYPE = "code";
    String LOGOUT_REDIRECT_URI = "http://localhost:8090/kakao/main";

    private KakaoResponse kakaoResponse;


//    @GetMapping("/getCode")
    @GetMapping("/login")
    public String getCode(){
        log.info("GET /kakao/getCode...");
        return "redirect:https://kauth.kakao.com/oauth/authorize?" +
                "client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URL +
                "&response_type=" + RESPONSE_TYPE;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code){
        log.info("GET /kakao/callback..." + code);

        String url = "https://kauth.kakao.com/oauth/token";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",CLIENT_ID);
        params.add("redirect_uri",REDIRECT_URL);
        params.add("code",code);

        //HTTP 엔티티 (헤더 + 파라미터)
        HttpEntity< MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);


        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, KakaoResponse.class);
        System.out.println(response);
        this.kakaoResponse = response.getBody();
        return "redirect:/kakao/main";
    }

    @GetMapping("/main")
    public void main(){
        log.info("GET /kakao/main...");
    }

    @GetMapping("/profile")
    public void profile(Model model){
        log.info("GET /kakao/profile...");

        String url = "https://kapi.kakao.com/v2/user/me";
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer " +kakaoResponse.getAccess_token());
        //HTTP 요청 파라미터

        //HTTP 엔티티 (헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProfileResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, ProfileResponse.class);
        System.out.println(response.getBody());

        model.addAttribute("profile", response.getBody());

    }


    @GetMapping("/logout")
    @ResponseBody
    public void logout() {
        log.info("GET /kakao/logout");

        String url = "https://kapi.kakao.com/v1/user/logout";
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " +kakaoResponse.getAccess_token());
        //HTTP 요청 파라미터

        //HTTP 엔티티 (헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());

    }

    @GetMapping("/unlink")
    @ResponseBody
    public void unlink() {
        log.info("GET /kakao/unlink");

        String url = "https://kapi.kakao.com/v1/user/unlink";
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " +kakaoResponse.getAccess_token());
        //HTTP 요청 파라미터

        //HTTP 엔티티 (헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());

    }

    @GetMapping("/logoutAll")
    public void logoutAll( HttpServletResponse response) throws IOException {
        log.info("GET /kakao/logoutAll...");
        response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+CLIENT_ID+"&logout_redirect_uri="+LOGOUT_REDIRECT_URI);
    }

    @GetMapping("/getCodeMsg")
    public String getCodeMsg(){
        log.info("GET /kakao/getCodeMsg...");
        return "redirect:https://kauth.kakao.com/oauth/authorize?" +
                "client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URL +
                "&response_type=" + RESPONSE_TYPE +
                "&scope=talk_message";
    }


@GetMapping("/getFriendsCode")
    public String getFriendsCode(){
        log.info("GET /kakao/getFriendsCode...");
        return "redirect:https://kauth.kakao.com/oauth/authorize?" +
                "client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URL +
                "&response_type=" + RESPONSE_TYPE +
                "&scope=friends";
    }


//    url = "https://kapi.kakao.com/v1/api/talk/friends" #친구 목록 가져오기
//            header = {"Authorization": 'Bearer ' + tokens["access_token"]}
//result = json.loads(requests.get(url, headers=header).text)
//friends_list = result.get("elements")
//print(friends_list)

@GetMapping("/friends")
@ResponseBody
public void getFriends() {
    log.info("GET /kakao/friends");

    String url = "https://kapi.kakao.com/v1/api/talk/friends";
    // HTTP 요청 헤더
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + kakaoResponse.getAccess_token());

    // HTTP 엔티티 (헤더만 포함)
    HttpEntity<Void> entity = new HttpEntity<>(headers);

    // HTTP 요청 후 응답받기
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    // JSON 형태로 응답받은 친구 목록 확인
    System.out.println(response.getBody());
}



    @GetMapping("/message/friends/{friendUuid}/{message}")
    @ResponseBody
    public void messageFriend(@PathVariable("friendUuid") String friendUuid, @PathVariable("message") String message) {
        log.info("GET /kakao/message/friends/" + friendUuid + "/" + message);

        String url = "https://kapi.kakao.com/v1/api/talk/friends/message/default/send";

        // HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer " + kakaoResponse.getAccess_token());

        // HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("receiver_uuids", "[\"" + friendUuid + "\"]"); // 친구 UUID는 JSON 배열 형태로 전달
        JSONObject templateObject = new JSONObject();
        templateObject.put("object_type", "text");
        templateObject.put("text", message);
        templateObject.put("link", new JSONObject());
        params.add("template_object", templateObject.toString());

        // HTTP 엔티티 (헤더 + 파라미터)
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        // HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 결과 출력
        System.out.println(response.getBody());
    }


    @GetMapping("/message/me/{message}")
    @ResponseBody
    public void message_me(@PathVariable("message") String message) {
        log.info("GET /kakao/message/me..." + message);

        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer " + kakaoResponse.getAccess_token());
        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        JSONObject template_object = new JSONObject();
        template_object.put("object_type","text");
        template_object.put("text",message);
        template_object.put("link",new JSONObject());
        template_object.put("button_title","버튼 제목");
        params.add("template_object",template_object.toString());



        //HTTP 엔티티 (헤더 + 파라미터)
        HttpEntity< MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);


        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response);
    }


    @Data
    private static class KakaoResponse {
        public String access_token;
        public String token_type;
        public String refresh_token;
        public int expires_in;
        public String scope;
        public int refresh_token_expires_in;
    }
    @Data
    private static class KakaoAccount{
        public boolean profile_nickname_needs_agreement;
        public boolean profile_image_needs_agreement;
        public Profile profile;
        public boolean has_email;
        public boolean email_needs_agreement;
        public boolean is_email_valid;
        public boolean is_email_verified;
        public String email;
    }
    @Data
    private static class Profile{
        public String nickname;
        public String thumbnail_image_url;
        public String profile_image_url;
        public boolean is_default_image;
        public boolean is_default_nickname;
    }
    @Data
    private static class Properties{
        public String nickname;
        public String profile_image;
        public String thumbnail_image;
    }

    @Data
    private static class ProfileResponse{
        public long id;
        public LocalDateTime connected_at;
        public Properties properties;
        public KakaoAccount kakao_account;
    }

}
