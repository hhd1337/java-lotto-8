package lotto.view;

import java.util.List;

public class OutputView {

    public void printPurchaseAmountNotice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printWinningNumbersNotice() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberNotice() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printPurchasedLottoCount(int count) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.", count);
    }

    public void printLottoNumbers(List<Integer> numbers) {
        System.out.println(numbers);
    }

    public void printWinResultHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void printWinResultLine(String line) {
        System.out.println(line);
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}
