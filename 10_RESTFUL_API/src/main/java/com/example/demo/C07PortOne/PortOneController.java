package com.example.demo.C07PortOne;

import com.example.demo.C04Kakao.C02KakaoLoginController;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/portOne")
public class PortOneController {

    private TokenResponse tokenResponse;


    @GetMapping("/main")
    public void main(){
        log.info("GET /portOne/main...");
    }

    @GetMapping("/getToken")
    @ResponseBody
    public void getToken(){
        log.info("GET /portOne/getToken...");

        String url = "https://api.iamport.kr/users/getToken";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type","application/json");
        //HTTP 요청 파라미터
//        JSONObject params = new JSONObject();
//        params.put("imp_key", "7410267001604006");
//        params.put("imp_secret", "gAmYuXa3MiA4OiPlKssPGewRfxiXtAmIEkzE4LTCCbTjRQ3TUq9vOxTjAyGBb2pvN16DYbfKjTbOc2QX");


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("imp_key", "7410267001604006");
        params.add("imp_secret", "gAmYuXa3MiA4OiPlKssPGewRfxiXtAmIEkzE4LTCCbTjRQ3TUq9vOxTjAyGBb2pvN16DYbfKjTbOc2QX");

        //HTTP 엔티티(헤더 + 파라미터)
       HttpEntity <MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, TokenResponse.class);
        System.out.println(response);
        this.tokenResponse = response.getBody();

    }

    @GetMapping("/getPayments")
    @ResponseBody
    public void getPayments(){
        log.info("GET /portOne/getPayments...");

        String url = "https://api.iamport.kr/payments?imp_uid[]=imp_10487374&merchant_uid[]=";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", "Bearer " + tokenResponse.getResponse().getAccess_token());

       //HTTP 요청 파라미터
//        JSONObject params = new JSONObject();
//        params.put("imp_key", "7410267001604006");
//        params.put("imp_secret", "gAmYuXa3MiA4OiPlKssPGewRfxiXtAmIEkzE4LTCCbTjRQ3TUq9vOxTjAyGBb2pvN16DYbfKjTbOc2QX");

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity entity = new HttpEntity<>(headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());

    }

    @Data
    private static class Response {
        public String access_token;
        public int now;
        public int expired_at;
    }
    @Data
    private static class TokenResponse{
        public int code;
        public Object message;
        public Response response;
    }
}
