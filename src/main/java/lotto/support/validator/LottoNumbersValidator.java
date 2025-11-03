package lotto.support.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbersValidator {

    private static final int NUMBERS_COUNT = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    public void validate(List<Integer> numbers) {
        validateNotEmpty(numbers);
        validateSize(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
    }

    private void validateNotEmpty(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 당첨번호가 비어 있습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != NUMBERS_COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨번호는 정확히 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN || number > MAX) {
                throw new IllegalArgumentException("[ERROR] 로또번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> distinct = new HashSet<>(numbers);
        if (distinct.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨번호는 중복될 수 없습니다.");
        }
    }
}
