package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Computer {

    public List<Integer> computerPickNumber() {
        int cardCount = 0;
        int[] checkUsedUniqueNumber = new int[10];
        Arrays.fill(checkUsedUniqueNumber, 0);
        List<Integer> computerRandomNumber = new ArrayList<>(Constant.CARD_COUNT);

        while (true) {
            if (cardCount == Constant.CARD_COUNT){
                break;
            }
            int number = Randoms.pickNumberInRange(1, 9);
            if (checkUsedUniqueNumber[number] == Constant.USED_NUMBER) {
                continue;
            }
            checkUsedUniqueNumber[number] = Constant.USED_NUMBER;
            computerRandomNumber.add(number);
            cardCount++;
        }

        return computerRandomNumber;
    }

    public int[] initRandomNumberIndex(List<Integer> randomNumber) {
        int[] randomNumberIndex = new int[10];
        Arrays.fill(randomNumberIndex, Constant.NO_USED_CARD);

        //컴퓨터의 숫자와 index 번호를 저장한다
        for (int i = 0; i < Constant.CARD_COUNT; i++) {
            randomNumberIndex[randomNumber.get(i)] = i;
        }
        return randomNumberIndex;
    }
}
