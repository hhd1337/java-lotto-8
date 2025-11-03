package lotto.domain;

public class LottoConstants {

    private LottoConstants() {
    }

    // 기본 규칙
    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;

    // 등수별 상금 정책
    public static final int FIRST_PRIZE_MONEY = 2_000_000_000;
    public static final int SECOND_PRIZE_MONEY = 30_000_000;
    public static final int THIRD_PRIZE_MONEY = 1_500_000;
    public static final int FOURTH_PRIZE_MONEY = 50_000;
    public static final int FIFTH_PRIZE_MONEY = 5_000;
}
