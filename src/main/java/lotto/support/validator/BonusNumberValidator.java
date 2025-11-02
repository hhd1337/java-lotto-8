package lotto.support.validator;

import java.util.List;

public class BonusNumberValidator {

    private static final int MIN = 1;
    private static final int MAX = 45;

    public void validate(int bonus, List<Integer> winningNumbers) {
        validateRange(bonus);
        validateDuplication(bonus, winningNumbers);
    }

    private void validateRange(int bonus) {
        if (bonus < MIN || bonus > MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplication(int bonus, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
