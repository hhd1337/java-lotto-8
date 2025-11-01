package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                        LOTTO_MIN_NUMBER,
                        LOTTO_MAX_NUMBER,
                        LOTTO_NUMBER_COUNT
                ).stream()
                .sorted()
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }
}
