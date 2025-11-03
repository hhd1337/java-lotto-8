package lotto.service;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;

public class LottoResultCalculatingService {

    public LottoResult calculateLottoResult(List<Lotto> lottos, WinningLotto winningLotto, int purchaseAmount) {
        Map<LottoRank, Integer> winCounts = calculateWinCounts(lottos, winningLotto);
        long totalPrize = calculateTotalPrize(winCounts);
        double profitRate = calculateProfitRate(totalPrize, purchaseAmount);

        return new LottoResult(winCounts, totalPrize, profitRate);
    }

    public Map<LottoRank, Integer> calculateWinCounts(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> winCounts = initWinCounts();
        for (Lotto lotto : lottos) {
            LottoRank rank = calculateLottoRank(lotto, winningLotto);
            winCounts.put(rank, winCounts.get(rank) + 1);
        }

        return winCounts;
    }

    public long calculateTotalPrize(Map<LottoRank, Integer> winCounts) {
        long sum = 0;
        for (Entry<LottoRank, Integer> e : winCounts.entrySet()) {
            sum += (long) e.getKey().getPrizeMoney() * e.getValue();
        }
        return sum;
    }

    public double calculateProfitRate(long totalPrize, int purchaseAmount) {
        double rate = (totalPrize * 100.0) / purchaseAmount;
        return Math.round(rate * 10) / 10.0;
    }

    private LottoRank calculateLottoRank(Lotto myLotto, WinningLotto winningLotto) {
        int matchCount = countMatchingNumbers(myLotto.getNumbers(), winningLotto.getWinningNumbers());
        boolean bonusMatch = myLotto.getNumbers().contains(winningLotto.getBonusNumber());

        return LottoRank.findRankByMatchCountAndBonus(matchCount, bonusMatch);
    }

    private int countMatchingNumbers(List<Integer> myLottoNumbers, List<Integer> winningLottoNumbers) {
        Set<Integer> set = new HashSet<>(winningLottoNumbers);
        int count = 0;
        for (int n : myLottoNumbers) {
            if (set.contains(n)) {
                count++;
            }
        }
        return count;
    }

    private Map<LottoRank, Integer> initWinCounts() {
        Map<LottoRank, Integer> m = new EnumMap<>(LottoRank.class);
        for (LottoRank r : LottoRank.values()) {
            m.put(r, 0);
        }
        return m;
    }

}
