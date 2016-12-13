package com.connect4.integration;

import com.connect4.model.GameInstance;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
/**
 * Created by dmorenoh on 4/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Connect4IntegrationTest {
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void playConnectFour_someOneOnes_returnGameInstance(){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        GameInstance response = restTemplate.postForObject("http://localhost:8080/connect4/startGame", map, GameInstance.class);
        assertThat(response, is(not(nullValue())));
    }
}
