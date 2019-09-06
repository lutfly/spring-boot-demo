package com.lutfly.sbdGlobalLocks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
public class TreasureTest extends SbdGlobalLocksApplicationTests {


    @Autowired
    RestTemplate restTemplate;

    /**
     * 参与夺宝测试
     */
    @Test
    public void concurrentTest() throws InterruptedException {

        String url = "https://uo.imlaidian.com/user-point/treasure/participateTreasure";

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.set("Authorization", "tewdBlbotexdnkhqi3nr4zprk1uf4");
        HttpEntity httpEntity = new HttpEntity(header);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("period", 9)
                .queryParam("activityId", 6);
        URI uri = builder.buildAndExpand().toUri();

//        while (true) {
//            System.out.println("start");
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
                log.error(exchange.getBody());
            }).start();
            new Thread(() -> {
                ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
                log.error(exchange.getBody());
            }).start();
        }
//        TimeUnit.SECONDS.sleep(10000);
//        }
    }
}