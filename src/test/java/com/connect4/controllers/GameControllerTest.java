package com.connect4.controllers;

import com.connect4.exceptions.GameException;
import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;
import com.connect4.services.GameService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static com.connect4.model.builder.GameInstanceBuilder.aGameInstanceBuilder;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

/**
 * Created by dmorenoh on 12/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {
    private static final String SESSION_IDENTIFIER = "sessionIdentifier";
    @InjectMocks
    private GameController gameController;

    @Mock
    private GameService gameService;

    @Before
    public void setup() throws Exception {
        RestAssuredMockMvc.standaloneSetup(gameController);
    }

    @Test
    public void startGame_validInitValuesProvided_createdStatus() {
        GameInstance gameInstance = aGameInstanceBuilder().withSessionIdentifier(SESSION_IDENTIFIER).build();
        org.mockito.Mockito.when(gameService.createNewGameSession())
                .thenReturn(gameInstance);
        given().contentType(ContentType.JSON)
                .when()
                .post("/connect4/startGame")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("sessionIdentifier", equalTo(SESSION_IDENTIFIER));
        org.mockito.Mockito.verify(gameService).createNewGameSession();
    }

    @Test
    public void playGame_playerNotProvided_badRequestException() {
        given().body("{  \n" +
                "   \"player\":{  \n" +
                "   },\n" +
                "   \"column\":\"6\"\n" +
                "}").contentType(ContentType.JSON)
                .when().put("/connect4/play/123")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void playGame_columnNotProvided_badRequestException() {
        given().body("{  \n" +
                "   \"player\":{  \n" +
                "      \"tokenType\":\"TOKEN_ONE\"\n" +
                "   }\n" +
                "}").contentType(ContentType.JSON)
                .when().put("/connect4/play/123")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void playGame_gameServiceThrowsException_badRequest() {
        org.mockito.Mockito.when(gameService.makeBoardMovement(anyString(), any(GameMovement.class)))
                .thenThrow(GameException.class);
        given().body("{  \n" +
                "   \"player\":{  \n" +
                "      \"tokenType\":\"TOKEN_ONE\"\n" +
                "   },\n" +
                "   \"column\":\"6\"\n" +
                "}").contentType(ContentType.JSON)
                .when().put("/connect4/play/123")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void playGame_validRequest_returnGameInstance() {
        GameInstance gameInstance = aGameInstanceBuilder().withSessionIdentifier(SESSION_IDENTIFIER).build();
        org.mockito.Mockito.when(gameService.makeBoardMovement(anyString(), any(GameMovement.class))).thenReturn(gameInstance);

        given().body("{  \n" +
                "   \"player\":{  \n" +
                "      \"tokenType\":\"TOKEN_ONE\"\n" +
                "   },\n" +
                "   \"column\":\"1\"\n" +
                "}").contentType(ContentType.JSON)
                .when()
                .put("/connect4/play/" + SESSION_IDENTIFIER)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("sessionIdentifier", equalTo(SESSION_IDENTIFIER));

    }
}