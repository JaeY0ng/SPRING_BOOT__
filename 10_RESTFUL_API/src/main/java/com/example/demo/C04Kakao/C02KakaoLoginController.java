package com.example.demo.C04Kakao;


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
import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/kakao")
public class C02KakaoLoginController {

    String REDIRECT_URI = "http://192.168.5.50:8090/kakao/callback";
    String CLIENT_ID = "";
    String RESPONSE_TYPE  ="code";
    String LOGOUT_REDIRECT_URI = "http://192.168.5.50:8090/kakao/main";

    private KakaoResponse kakaoResponse;
    private FriendsResponse friendsResponse;

    //@GetMapping("/getCode")
    @GetMapping("/login")
    public String getCode(){
        log.info("GET /kakao/getCode...");
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&response_type="+RESPONSE_TYPE;
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
        params.add("redirect_uri",REDIRECT_URI);
        params.add("code",code);

        //HTTP 엔티티(헤더 + 파라미터)
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
        log.info("GET /kakao/profile....");

        String url = "https://kapi.kakao.com/v2/user/me";
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer "+kakaoResponse.getAccess_token());

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProfileResponse> response = restTemplate.exchange(url,HttpMethod.POST,entity,ProfileResponse.class);
        System.out.println(response.getBody());
        //
        model.addAttribute("profile",response.getBody());

    }

    @GetMapping("/logout")
    @ResponseBody
    public void logout(){
        log.info("GET /kakao/logout");

        String url = "https://kapi.kakao.com/v1/user/logout";
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+kakaoResponse.getAccess_token());

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,entity,String.class);
        System.out.println(response.getBody());

    }
    @GetMapping("/unlink")
    @ResponseBody
    public void unlink(){
        log.info("GET /kakao/unlink");

        String url = "https://kapi.kakao.com/v1/user/unlink";
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+kakaoResponse.getAccess_token());

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,entity,String.class);
        System.out.println(response.getBody());

    }

    @GetMapping("/logoutAll")
    public void logout_all (HttpServletResponse response) throws IOException {
        log.info("GET /kakao/logoutAll...");
        response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+CLIENT_ID+"&logout_redirect_uri="+LOGOUT_REDIRECT_URI);
    }

    @GetMapping("/getCodeMsg")
    public String getCodeMsg(){
        log.info("GET /kakao/getCodeMsg...");
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&response_type="+RESPONSE_TYPE+"&scope=talk_message";
    }

    @GetMapping("/message/me/{message}")
    @ResponseBody
    public void message_me(@PathVariable("message") String message){
        log.info("GET /kakao/message/me...." + message);

        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer "+kakaoResponse.getAccess_token());
        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        JSONObject template_object = new JSONObject();
        template_object.put("object_type","text");
        template_object.put("text",message);
        template_object.put("link",new JSONObject());
        template_object.put("button_title","");
        params.add("template_object",template_object.toString());


        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity< MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response);

    }

    @GetMapping("/getCodeFriends")
    public String getCodeFriends(){
        log.info("GET /kakao/getCodeFriends...");
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&response_type="+RESPONSE_TYPE+"&scope=friends,talk_message";
    }
    @GetMapping("/friends")
    @ResponseBody
    public void getMyFriends(HttpServletRequest request){
        log.info("GET /kakao/friends...");
        String url = "https://kapi.kakao.com/v1/api/talk/friends";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + kakaoResponse.getAccess_token());

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FriendsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FriendsResponse.class);
        System.out.println(response.getBody());

//        HttpSession session = request.getSession();
//        session.setAttribute("friends",response.getBody());


        this.friendsResponse = response.getBody();

    }
    @GetMapping("/message/friend/{message}")
    @ResponseBody
    public void message_friend(@PathVariable("message") String message){
        ;
        System.out.println("uuid  :" + friendsResponse.getElements().get(0).getUuid());

        log.info("GET /kakao/message/friend...." + message);

        String url = "https://kapi.kakao.com/v1/api/talk/friends/message/send";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization","Bearer "+kakaoResponse.getAccess_token());
        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("receiver_uuids","[\""+friendsResponse.getElements().get(0).getUuid()+"\"]");
        params.add("template_id","114668");
        params.add("TITLE",message);

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity< MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response);

    }

    //---------------------------
    //친구  MESSAGE
    //---------------------------
    @Data
    private static  class Element{
        public String profile_nickname;
        public String profile_thumbnail_image;
        public boolean allowed_msg;
        public long id;
        public String uuid;
        public boolean favorite;
    }
    @Data
    private static class FriendsResponse{
        public ArrayList<Element> elements;
        public int total_count;
        public Object after_url;
        public int favorite_count;
    }




    //---------------------------
    //LOGIN KakaoResponse
    //---------------------------
    @Data
    private static class KakaoResponse{
        public String access_token;
        public String token_type;
        public String refresh_token;
        public int expires_in;
        public String scope;
        public int refresh_token_expires_in;
    }

    //---------------------------
    //PROFILE
    //---------------------------
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
