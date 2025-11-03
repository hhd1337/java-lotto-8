package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_MAX_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_MIN_NUMBER;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

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
