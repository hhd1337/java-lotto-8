package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    public List<Lotto> generateMany(int count) {
        validateCount(count);

        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(generateOne());
        }

        return tickets;
    }

    public Lotto generateOne() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                        LOTTO_MIN_NUMBER,
                        LOTTO_MAX_NUMBER,
                        LOTTO_NUMBER_COUNT
                ).stream()
                .sorted()
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }

    private void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("[ERROR] 로또 생성 개수는 1개 이상이어야 합니다.");
        }
    }
}
