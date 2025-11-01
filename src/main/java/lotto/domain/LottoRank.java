package lotto.domain;

public enum LottoRank {

    FIRST(6, false, 2_000_000_000),     // 1등 : 6개 일치
    SECOND(5, true, 30_000_000),        // 2등 : 5개, 보너스 일치
    THIRD(5, false, 1_500_000),         // 3등 : 5개
    FOURTH(4, false, 50_000),           // 4등 : 4개
    FIFTH(3, false, 5_000),             // 5등 : 3개
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
