package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultCalculatingServiceTest {

    private final LottoResultCalculatingService service = new LottoResultCalculatingService();

    @DisplayName("여러 장의 로또를 입력하면 등수별 개수가 모두 계산된다.")
    @Test
    void 여러장의_로또를_입력하면_등수별_개수가_모두_계산된다() {
        // given
        Lotto first = new Lotto(List.of(1, 2, 3, 4, 5, 6)); // 1등
        Lotto second = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 2등
        Lotto third = new Lotto(List.of(1, 2, 3, 4, 5, 8)); // 3등
        Lotto miss = new Lotto(List.of(10, 20, 30, 40, 41, 42)); // 당첨 안됨
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        Map<LottoRank, Integer> result = service.calculateWinCounts(List.of(first, second, third, miss), winning);

        // then
        assertThat(result.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.get(LottoRank.THIRD)).isEqualTo(1);
        assertThat(result.get(LottoRank.MISS)).isEqualTo(1);
    }

    @DisplayName("등수별 당첨 개수로 총 상금이 올바르게 계산된다.")
    @Test
    void 등수별_당첨_개수로_총상금이_올바르게_계산된다() {
        // given
        Map<LottoRank, Integer> counts = Map.of(
                LottoRank.FIRST, 1,
                LottoRank.SECOND, 1,
                LottoRank.THIRD, 1,
                LottoRank.FOURTH, 1,
                LottoRank.FIFTH, 1,
                LottoRank.MISS, 0
        );

        // when
        long totalPrize = service.calculateTotalPrize(counts);

        // then
        long expected = LottoRank.FIRST.getPrizeMoney()
                + LottoRank.SECOND.getPrizeMoney()
                + LottoRank.THIRD.getPrizeMoney()
                + LottoRank.FOURTH.getPrizeMoney()
                + LottoRank.FIFTH.getPrizeMoney();

        assertThat(totalPrize).isEqualTo(expected);
    }

    @DisplayName("총 상금과 구입금액으로 수익률이 올바르게 계산된다.")
    @Test
    void 총상금과_구입금액으로_수익률이_올바르게_계산된다() {
        // given
        long totalPrize = 5000;
        int purchaseAmount = 10000;
        // when
        double rate = service.calculateProfitRate(totalPrize, purchaseAmount);
        // then
        assertThat(rate).isEqualTo(50.0);
    }

    @DisplayName("전체 로또결과를 계산하면 LottoResult객체가 반환된다.")
    @Test
    void 전체_로또결과를_계산하면_LottoResult객체가_반환된다() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7))
        );
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        int purchaseAmount = 2000;

        // when
        LottoResult result = service.calculateLottoResult(lottos, winning, purchaseAmount);

        // then
        assertThat(result.getWinCounts().get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.getWinCounts().get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.getTotalPrize()).isPositive();
        assertThat(result.getProfitRate()).isGreaterThan(0);
    }
}
