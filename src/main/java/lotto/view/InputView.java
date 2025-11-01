package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readTotalPurchaseAmount() {
        return Console.readLine();
    }

    public String readWinningNumbers() {
        return Console.readLine();
    }

    public String readBonusNumber() {
        return Console.readLine();
    }

    public void close() {
        Console.close();
    }
}
