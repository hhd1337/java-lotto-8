package lotto.domain;

import java.util.List;
import lotto.support.validator.LottoNumbersValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoNumbersValidator lottoNumbersValidator = new LottoNumbersValidator();
        lottoNumbersValidator.validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
