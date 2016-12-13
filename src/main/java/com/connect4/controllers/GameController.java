package com.connect4.controllers;

import com.connect4.exceptions.GameException;
import com.connect4.model.ErrorResponse;
import com.connect4.model.GameInstance;
import com.connect4.model.GameMovement;
import com.connect4.services.GameService;
import com.connect4.validators.BoardMovementValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by dmorenoh on 12/11/16.
 */
@RestController
@RequestMapping("/connect4")
public class GameController {

    @Inject
    private GameService gameService;

    @RequestMapping("/")
    public String index() {
        return "Connect4 server loaded!";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new BoardMovementValidator());
    }

    @RequestMapping(value = "/startGame", method = RequestMethod.POST)
    public ResponseEntity<GameInstance> startGame() {
        GameInstance newGameInstance = gameService.createNewGameSession();
        return new ResponseEntity<>(newGameInstance, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/play/{gameIdentifier}", method = RequestMethod.PUT)
    public ResponseEntity<GameInstance> playGame(@PathVariable final String gameIdentifier,
                                                 @Valid @RequestBody final GameMovement gameMovement) throws GameException {
        if (StringUtils.isBlank(gameIdentifier)) {
            throw new GameException("Game identifier not provided");
        }
        GameInstance gameInstance = gameService.makeBoardMovement(gameIdentifier, gameMovement);
        return new ResponseEntity<>(gameInstance, HttpStatus.OK);
    }

    @ExceptionHandler(GameException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
    }

}
