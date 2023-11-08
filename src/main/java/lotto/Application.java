package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Application {

    public static void alertError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static int convertInteger(String input) {
        int toValidate;
        try {
            toValidate = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return toValidate;
    }

    public static int inputBudget() {
        int input = convertInteger(Console.readLine());
        boolean isNotMultipleOf1000 = input % 1000 != 0;
        if(isNotMultipleOf1000) {
            throw new IllegalArgumentException();
        }
        return input;
    }

    public static List<Integer> getRandomLottoNumber() {
        return Randoms.pickUniqueNumbersInRange(1,45,6);
    }

    public static Lotto getRandomLotto() {
        return new Lotto(getRandomLottoNumber());
    }

    public static List<Lotto> buyLottos(int budget) {
        int toBuy = budget / 1000;
        List<Lotto> lottos = IntStream.rangeClosed(1, toBuy)
                .mapToObj(i -> getRandomLotto())
                .toList();
        System.out.println(toBuy + "개를 구매했습니다.");
        lottos.forEach(Lotto::printNumbers);
        return lottos;
    }

    public static void main(String[] args) {
        int budget;
        List<Lotto> lottos;
        try {
            budget = inputBudget();
            lottos = buyLottos(budget);
        } catch (IllegalArgumentException e) {
            alertError("로또 구입 금액은 1,000 단위의 숫자여야 합니다.");
        }
    }
}
