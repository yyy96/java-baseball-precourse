package baseball;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static final int INPUT_LENGTH_BOUND =3;
    public static final int GAME_CONTINUE = 1;
    public static final int GAME_FINISH = 2;

    public static void main(String[] args) {

        while(true){
            List<Integer> randomNumber = new ArrayList<>(3);
            //1. 컴퓨터가 랜덤 숫자를 정한다
            randomNumber = computerPickNumber();

            //2. 플레이어가 숫자게임을 시작한다.
            if(playGame(randomNumber)==GAME_CONTINUE){
                continue;
            }
            else{
                break;
            }
        }

    }

    public static List<Integer> computerPickNumber(){
        int cardCnt = 0;
        int[] used = new int[10];
        Arrays.fill(used,0);
        List<Integer> computerNumber = new ArrayList<>(3);

        while(true){
            int num = Randoms.pickNumberInRange(1,9);
            if(used[num]==1) continue;
            used[num] = 1;
            cardCnt++;
            computerNumber.add(num);
            if(cardCnt==3) break;
        }

        return computerNumber;
    }



    public static int playGame(List<Integer> randomNumber){
        //게임 진행
        while(true) {
            List<Integer> playerNumber = new ArrayList<>(3);
            playerNumber = playerInputNumber();

            if(checkNum(randomNumber, playerNumber)==true){
                break;
            }
            else {
                continue;
            }
        }

        if(gameOver() == GAME_CONTINUE){
            return GAME_CONTINUE;
        }
        else{
            return GAME_FINISH;
        }
    }

    public static List<Integer> playerInputNumber(){
        List<Integer> playerNumber = new ArrayList<>(3);
        String input;
        System.out.print("숫자를 입력해주세요 : ");
        input = Console.readLine();
        for (int i = 0; i < 3; i++) {
            int num = Character.getNumericValue(input.charAt(i));
            playerNumber.add(num);
        }

        validateNumberCheck(input);
        return playerNumber;
    }


    private static void validateNumberCheck(final String input) {
        if (input.length()!=INPUT_LENGTH_BOUND) {
            throw new IllegalArgumentException("numbers cannot be empty.");
        }
    }

    public static int gameOver(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
        String gameOver = Console.readLine();
        int gameOverNum = Character.getNumericValue(gameOver.charAt(0));
        if(gameOverNum==GAME_CONTINUE){
            return GAME_CONTINUE;
        }
        else{
            return GAME_FINISH;
        }
    }

    public static Boolean checkNum(List<Integer> randomNumber, List<Integer> playerNumber){
        int ball =0;
        int strike =0;
        int[] DAT = new int[10]; //direct address table
        Arrays.fill(DAT,-1);

        //컴퓨터의 숫자와 index 번호를 저장한다
        for(int i=0; i<3; i++){
            DAT[randomNumber.get(i)] = i;
        }

        for(int i=0; i<3; i++){
            if(DAT[playerNumber.get(i)]==i) strike++;
            else if(DAT[playerNumber.get(i)]!=-1) ball++;
        }

        //결과 출력
        printResult(ball, strike);

        if(strike==3) return true;
        else return false;
    }


    public static void printResult(int ball, int strike){
        if(ball!=0 && strike!=0)
            System.out.println(ball+"볼 "+strike+"스트라이크");
        else if(ball!=0 && strike==0)
            System.out.println(ball+"볼");
        else if(ball==0 && strike!=0)
            System.out.println(strike+"스트라이크");
        else
            System.out.println("낫싱");
    }
}
