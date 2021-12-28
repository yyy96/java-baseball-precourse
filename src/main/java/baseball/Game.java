package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class Game {

    Computer computer = new Computer();
    Player player = new Player();

    public static int ball = 0;
    public static int strike = 0;

    public void startGame() {
        while (true) {
            List<Integer> randomNumber = computer.computerPickNumber();
            playGame(randomNumber);
            if (gameOver() == Constant.GAME_FINISH) {
                break;
            }
        }
    }

    public void playGame(List<Integer> randomNumber) {
        while (true) {
            List<Integer> playerNumber = player.playerInputNumber();
            if (checkPlayerNumber(randomNumber, playerNumber) == Constant.ALL_STRIKE) {
                break;
            }
        }
    }

    public int gameOver() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
        String gameOverInput = Console.readLine();
        int gameOver = Character.getNumericValue(gameOverInput.charAt(0));
        if (gameOver == Constant.GAME_CONTINUE) {
            return Constant.GAME_CONTINUE;
        } else {
            return Constant.GAME_FINISH;
        }
    }

    public int checkPlayerNumber(List<Integer> randomNumber, List<Integer> playerNumber) {
        int[] randomNumberIndex = computer.initRandomNumberIndex(randomNumber);

        initBallStrikeCount();
        checkBallStrike(randomNumberIndex, playerNumber);
        printResult(ball, strike);

        if (strike == Constant.ALL_STRIKE) {
            return Constant.ALL_STRIKE;
        } else {
            return Constant.WRONG;
        }
    }

    public void initBallStrikeCount() {
        ball = 0;
        strike = 0;
    }

    public void checkBallStrike(int[] randomNumberIndex, List<Integer> playerNumber) {
        for (int i = 0; i < Constant.CARD_COUNT; i++) {
            if (randomNumberIndex[playerNumber.get(i)] == i) {
                strike++;
            } else if (randomNumberIndex[playerNumber.get(i)] != Constant.NO_USED_CARD) {
                ball++;
            }
        }
    }

    public void printResult(int ball, int strike) {
        if (ball != 0 && strike != 0) {
            System.out.println(ball + "볼 " + strike + "스트라이크");
        } else if (ball != 0 && strike == 0) {
            System.out.println(ball + "볼");
        } else if (ball == 0 && strike != 0) {
            System.out.println(strike + "스트라이크");
        } else {
            System.out.println("낫싱");
        }
    }

}
