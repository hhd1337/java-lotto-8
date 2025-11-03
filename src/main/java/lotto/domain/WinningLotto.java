package lotto.domain;

import java.util.List;
import lotto.support.validator.BonusNumberValidator;
import lotto.support.validator.LottoNumbersValidator;

public class WinningLotto {

    LottoNumbersValidator lottoNumbersValidator = new LottoNumbersValidator();
    BonusNumberValidator bonusNumberValidator = new BonusNumberValidator();

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        lottoNumbersValidator.validate(winningNumbers);
        bonusNumberValidator.validate(bonusNumber, winningNumbers);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
