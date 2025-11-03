package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRankTest {

    @DisplayName("6개 일치하면 1등을 반환한다.")
    @Test
    void 여섯개_일치하면_1등을_반환한다() {
        LottoRank rank = LottoRank.findRankByMatchCountAndBonus(6, false);
        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("5개 일치하고 그중 보너스도 일치하면 2등을 반환한다.")
    @Test
    void 다섯개_일치하고_그중_보너스도_일치하면_2등을_반환한다() {
        LottoRank rank = LottoRank.findRankByMatchCountAndBonus(5, true);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("5개 일치하지만 보너스가 불일치하면 3등을 반환한다.")
    @Test
    void 다섯개_일치하지만_보너스가_불일치하면_3등을_반환한다() {
        LottoRank rank = LottoRank.findRankByMatchCountAndBonus(5, false);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("4개 일치하면 4등을 반환한다.")
    @Test
    void 네개_일치하면_4등을_반환한다() {
        LottoRank rank = LottoRank.findRankByMatchCountAndBonus(4, false);
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("3개 일치하면 5등을 반환한다.")
    @Test
    void 세개_일치하면_5등을_반환한다() {
        LottoRank rank = LottoRank.findRankByMatchCountAndBonus(3, false);
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("2개 이하 일치하면 MISS를 반환한다.")
    @Test
    void 두개_이하_일치하면_MISS를_반환한다() {
        assertThat(LottoRank.findRankByMatchCountAndBonus(2, false)).isEqualTo(LottoRank.MISS);
        assertThat(LottoRank.findRankByMatchCountAndBonus(1, false)).isEqualTo(LottoRank.MISS);
        assertThat(LottoRank.findRankByMatchCountAndBonus(0, false)).isEqualTo(LottoRank.MISS);
    }
}
