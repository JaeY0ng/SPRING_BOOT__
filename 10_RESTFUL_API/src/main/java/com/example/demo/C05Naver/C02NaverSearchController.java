package com.example.demo.C05Naver;

import com.example.demo.C04Kakao.C02KakaoLoginController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/naver/search")
public class C02NaverSearchController {

        private String CLIENT_ID = "FLGUS7TEn0fSWnTAKFZB";
        private String CLIENT_SECRET = "bkH6VUSIGV";

    @GetMapping("/book/{keyword}")
    @ResponseBody
    public String search(@PathVariable("keyword") String keyword){

        log.info("GET /naver/search/book{}...", keyword);

        String url = "https://openapi.naver.com/v1/search/book.json?query=" + keyword;

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id",CLIENT_ID);
        headers.add("X-Naver-Client-Secret",CLIENT_SECRET);
        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("query",keyword);
        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity< MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response);
        return response.getBody();
    }
}
