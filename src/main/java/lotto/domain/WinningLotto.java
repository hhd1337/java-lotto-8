package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {

    private static final int NUMBERS_COUNT = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> winningNumbers, int bonusNumber) {
        validateNotEmpty(winningNumbers);
        validateSize(winningNumbers);
        validateRange(winningNumbers);
        validateNoDuplicates(winningNumbers);
        validateBonusNumber(bonusNumber, winningNumbers);
    }

    private void validateNotEmpty(List<Integer> winningNumbers) {
        if (winningNumbers == null || winningNumbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 당첨번호가 비어 있습니다.");
        }
    }

    private void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != NUMBERS_COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨번호는 정확히 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> winningNumbers) {
        for (int number : winningNumbers) {
            if (number < MIN || number > MAX) {
                throw new IllegalArgumentException("[ERROR] 로또번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private void validateNoDuplicates(List<Integer> winningNumbers) {
        Set<Integer> distinct = new HashSet<>(winningNumbers);
        if (distinct.size() != winningNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨번호는 중복될 수 없습니다.");
        }
    }

    private void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < MIN || bonusNumber > MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스번호는 당첨번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
