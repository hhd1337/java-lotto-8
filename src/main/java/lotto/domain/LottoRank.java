package lotto.domain;

import static lotto.domain.LottoConstants.FIFTH_PRIZE_MONEY;
import static lotto.domain.LottoConstants.FIRST_PRIZE_MONEY;
import static lotto.domain.LottoConstants.FOURTH_PRIZE_MONEY;
import static lotto.domain.LottoConstants.SECOND_PRIZE_MONEY;
import static lotto.domain.LottoConstants.THIRD_PRIZE_MONEY;

public enum LottoRank {

    FIRST(6, false, FIRST_PRIZE_MONEY),         // 1등 : 6개 일치
    SECOND(5, true, SECOND_PRIZE_MONEY),        // 2등 : 5개, 보너스 일치
    THIRD(5, false, THIRD_PRIZE_MONEY),         // 3등 : 5개
    FOURTH(4, false, FOURTH_PRIZE_MONEY),       // 4등 : 4개
    FIFTH(3, false, FIFTH_PRIZE_MONEY),         // 5등 : 3개
    MISS(0, false, 0);

    private final int matchCount;
    private final boolean requiresBonusMatch;
    private final int prizeMoney;

    LottoRank(int matchCount, boolean requiresBonusMatch, int prizeMoney) {
        this.matchCount = matchCount;
        this.requiresBonusMatch = requiresBonusMatch;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank findRankByMatchCountAndBonus(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && bonusMatch) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return MISS;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean requiresBonusMatch() {
        return requiresBonusMatch;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
