package com.example.demo.C02OpenWeatherMap;


import com.example.demo.C01OpenDataAPI.OpenDataController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/openWeather")
public class OpenWeatherMapController {

    @GetMapping(value ="/get/{lat}/{lon}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getWeather(
            @PathVariable("lat") double lat,
            @PathVariable("lon") double lon
    )
    {
        //
        String apiKey = "b7a263e63bfe790ff0081e9b619e7c91";
        String url =
        "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+apiKey;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET,null, String.class);
        System.out.println(response);
        return response.getBody();
    }


}
