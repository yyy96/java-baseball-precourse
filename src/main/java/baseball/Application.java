package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static final int INPUT_LENGTH_BOUND = 3;
    public static final int GAME_CONTINUE = 1;
    public static final int GAME_FINISH = 2;
    public static final int CARD_COUNT = 3;
    public static final int USED_NUMBER = 1;
    public static final int ALL_STRIKE = 3;
    public static final int NO_USED_CARD = -1;

    public static void main(String[] args) {
        Application numberBaseballGame = new Application();
        while (true) {
            List<Integer> randomNumber = numberBaseballGame.computerPickNumber(); //스스로를 호출해서 객체생성

            if (numberBaseballGame.playGame(randomNumber) == GAME_CONTINUE) {
                continue;
            } else {
                break;
            }
        }
    }

    public List<Integer> computerPickNumber() {
        int cardCount = 0;
        int[] checkUsedUniqueNumber = new int[10];
        Arrays.fill(checkUsedUniqueNumber, 0);
        List<Integer> computerRandomNumber = new ArrayList<>(CARD_COUNT);

        while(true) {
            if (cardCount == CARD_COUNT) break;
            int number = Randoms.pickNumberInRange(1, 9);
            if (checkUsedUniqueNumber[number] == USED_NUMBER) {
                continue;
            }
            checkUsedUniqueNumber[number] = USED_NUMBER;
            computerRandomNumber.add(number);
            cardCount++;
        }

        return computerRandomNumber;
    }


    public int playGame(List<Integer> randomNumber) {
        //게임 진행
        while (true) {
            List<Integer> playerNumber = playerInputNumber();

            if (checkPlayerNumber(randomNumber, playerNumber) == true) {
                break;
            } else {
                continue;
            }
        }

        if (gameOver() == GAME_CONTINUE) {
            return GAME_CONTINUE;
        } else {
            return GAME_FINISH;
        }
    }

    public List<Integer> playerInputNumber() {
        List<Integer> playerNumber = new ArrayList<>(CARD_COUNT);
        String input;
        System.out.print("숫자를 입력해주세요 : ");
        input = Console.readLine();
        for (int i = 0; i < 3; i++) {
            int number = Character.getNumericValue(input.charAt(i));
            playerNumber.add(number);
        }

        validateNumberCheck(input);
        return playerNumber;
    }


    private void validateNumberCheck(final String input) {
        if (input.length() != INPUT_LENGTH_BOUND) {
            throw new IllegalArgumentException("numbers cannot be empty.");
        }
    }

    public int gameOver() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
        String gameOverInput = Console.readLine();
        int gameOver = Character.getNumericValue(gameOverInput.charAt(0));
        if (gameOver == GAME_CONTINUE) {
            return GAME_CONTINUE;
        } else {
            return GAME_FINISH;
        }
    }

    public Boolean checkPlayerNumber(List<Integer> randomNumber, List<Integer> playerNumber) {
        int ball = 0;
        int strike = 0;
        int[] randomNumberIndex = new int[10];
        randomNumberIndex = initRandomNumberIndex(randomNumber);

        for (int i = 0; i < CARD_COUNT; i++) {
            if (randomNumberIndex[playerNumber.get(i)] == i) {
                strike++;
            } else if (randomNumberIndex[playerNumber.get(i)] != NO_USED_CARD) {
                ball++;
            }
        }

        printResult(ball, strike);

        if (strike == ALL_STRIKE) {
            return true;
        } else {
            return false;
        }
    }

    public int[] initRandomNumberIndex(List<Integer> randomNumber) {
        int[] randomNumberIndex = new int[10]; //direct address table
        Arrays.fill(randomNumberIndex, NO_USED_CARD);

        //컴퓨터의 숫자와 index 번호를 저장한다
        for (int i = 0; i < CARD_COUNT; i++) {
            randomNumberIndex[randomNumber.get(i)] = i;
        }
        return randomNumberIndex;
    }


    public void printResult(int ball, int strike) {
        if (ball != 0 && strike != 0)
            System.out.println(ball + "볼 " + strike + "스트라이크");
        else if (ball != 0 && strike == 0)
            System.out.println(ball + "볼");
        else if (ball == 0 && strike != 0)
            System.out.println(strike + "스트라이크");
        else
            System.out.println("낫싱");
    }
}
