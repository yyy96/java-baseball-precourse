package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public List<Integer> playerInputNumber() {
        List<Integer> playerNumber = new ArrayList<>(Constant.CARD_COUNT);
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
        if (input.length() != Constant.INPUT_LENGTH_BOUND) {
            throw new IllegalArgumentException("numbers cannot be empty.");
        }
    }
}
