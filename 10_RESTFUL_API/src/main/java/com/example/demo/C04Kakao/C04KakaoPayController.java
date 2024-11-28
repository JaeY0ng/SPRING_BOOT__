package com.example.demo.C04Kakao;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping("/kakao/pay")
public class C04KakaoPayController {

    @GetMapping("/main")
    public void main(){}

    @GetMapping("/req")
    @ResponseBody
    public void req(){
        log.info("GET /kakao/pay/req...");

        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","SECRET_KEY TEST");
        headers.add("Content-Type","application/json");

        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        JSONObject obj = new JSONObject();
        obj.put("cid","TC0ONETIME");
        obj.put("partner_order_id","partner_order_id");
        obj.put("partner_user_id","partner_user_id");
        obj.put("item_name","초코파이");
        obj.put("quantity","1");
        obj.put("total_amount","2200");
        obj.put("vat_amount","200");
        obj.put("tax_free_amount","0");
        obj.put("approval_url","http://localhost:8090/kakao/pay/success");
        obj.put("fail_url","http://localhost:8090/kakao/pay/fail");
        obj.put("cancel_url","http://localhost:8090/kakao/pay/cancel");


        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity< JSONObject > entity = new HttpEntity<>(obj, headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoPayResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, KakaoPayResponse.class);
        System.out.println(response.getBody());
    }

    @GetMapping("/success")
    public void success(){
        log.info("GET /kakao/pay/success...");
    }

    @GetMapping("/cancel")
    public void cancel() {
        log.info("GET /kakao/pay/cancel...");
    }

    @GetMapping("/fail")
    public void fail(){
        log.info("GET /kakao/pay/fail...");
    }


    @Data

    private static class KakaoPayResponse{
        public String tid;
        public boolean tms_result;
        public LocalDateTime created_at;
        public String next_redirect_pc_url;
        public String next_redirect_mobile_url;
        public String next_redirect_app_url;
        public String android_app_scheme;
        public String ios_app_scheme;
    }


}
