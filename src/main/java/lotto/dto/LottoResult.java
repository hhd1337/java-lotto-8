package lotto.dto;

import java.util.Map;
import lotto.domain.LottoRank;

public class LottoResult {
    private final Map<LottoRank, Integer> winCounts;
    private final long totalPrize;
    private final double profitRate;

    public LottoResult(Map<LottoRank, Integer> winCounts, long totalPrize, double profitRate) {
        this.winCounts = winCounts;
        this.totalPrize = totalPrize;
        this.profitRate = profitRate;
    }

    public Map<LottoRank, Integer> getWinCounts() {
        return winCounts;
    }

    public long getTotalPrize() {
        return totalPrize;
    }

    public double getProfitRate() {
        return profitRate;
    }
}