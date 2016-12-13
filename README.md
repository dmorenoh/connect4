# connect4
In order to play thius Connect4 game version, please consider that just two players are allowed and each one has to have a single token for play when is its turn.
So, connect4 game board will consider TOKEN_ONE and TOKEN_TWO as valid tokens for populate this board untils someone wins or board is not allowed to progress.

1. As based on spring-boot, please run Application.java main method in order to run the server.

2. This is a multi-session game rest service, every time is required to create a new session please use this url: http://localhost:8080/connect4/startGame considering POST as REST method.

3. After POST the request above, this will return a gameSessionIdentifier, please consider this for the following step.

4. For playing, please use this url as PUT method: http://localhost:8080/connect4/play/{gameSessionIdentifier} . Considert the following json as request body as example:
{  
   "player":{  
      "tokenType":"TOKEN_ONE"
   },
   "column":"6"
}

5. From the above, you can see the player which has the TOKEN_ONE has drop his token in the column 6. 

6. Repeat the step above for next player and so on until the game finishes.

7. Please remember that the players has to alternate their turn. If player one has played, he has to wait until next player plays.
