package lotto.controller;

import static lotto.domain.LottoConstants.FIFTH_PRIZE_MONEY;
import static lotto.domain.LottoConstants.FIRST_PRIZE_MONEY;
import static lotto.domain.LottoConstants.FOURTH_PRIZE_MONEY;
import static lotto.domain.LottoConstants.LOTTO_PRICE;
import static lotto.domain.LottoConstants.SECOND_PRIZE_MONEY;
import static lotto.domain.LottoConstants.THIRD_PRIZE_MONEY;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;
import lotto.service.LottoResultCalculatingService;
import lotto.support.parser.StringToIntegerParser;
import lotto.support.parser.WinningNumberParser;
import lotto.support.validator.BonusNumberValidator;
import lotto.support.validator.LottoNumbersValidator;
import lotto.support.validator.PurchaseAmountValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final WinningNumberParser winningNumberParser;
    private final PurchaseAmountValidator purchaseAmountValidator;
    private final BonusNumberValidator bonusNumberValidator;
    private final LottoNumbersValidator lottoNumbersValidator;
    private final LottoFactory lottoFactory;
    private final LottoResultCalculatingService lottoResultCalculatingService;
    private final StringToIntegerParser stringToIntegerParser;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.winningNumberParser = new WinningNumberParser();
        this.purchaseAmountValidator = new PurchaseAmountValidator();
        this.bonusNumberValidator = new BonusNumberValidator();
        this.lottoNumbersValidator = new LottoNumbersValidator();
        this.lottoFactory = new LottoFactory();
        this.lottoResultCalculatingService = new LottoResultCalculatingService();
        this.stringToIntegerParser = new StringToIntegerParser();
    }

    public void run() {
        int purchaseAmount = readPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        printPurchasedLottos(lottos);

        List<Integer> winningNumbers = readWinningNumbers();
        int bonusNumber = readBonusNumber(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResult result = lottoResultCalculatingService.calculateLottoResult(lottos, winningLotto, purchaseAmount);

        printCalculatedLottoResult(result);

        inputView.close();
    }

    private int readPurchaseAmount() {
        while (true) {
            try {
                outputView.printPurchaseAmountNotice();
                String raw = inputView.readTotalPurchaseAmount().trim();
                int amount = stringToIntegerParser.parseToInt(raw);
                purchaseAmountValidator.validate(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Integer> readWinningNumbers() {
        while (true) {
            try {
                outputView.printWinningNumbersNotice();
                String raw = inputView.readWinningNumbers();
                List<Integer> numbers = winningNumberParser.parse(raw);
                lottoNumbersValidator.validate(numbers);
                return numbers;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                outputView.printBonusNumberNotice();
                String raw = inputView.readBonusNumber().trim();
                int bonus = stringToIntegerParser.parseToInt(raw);
                bonusNumberValidator.validate(bonus, winningNumbers);
                return bonus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Lotto> purchaseLottos(int purchaseAmount) {
        int count = purchaseAmount / LOTTO_PRICE;
        List<Lotto> lottos = lottoFactory.generateMany(count);
        outputView.printPurchasedLottoCount(count);
        System.out.println();
        return lottos;
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto l : lottos) {
            outputView.printLottoNumbers(l.getNumbers());
        }
    }

    private void printCalculatedLottoResult(LottoResult result) {
        outputView.printWinResultHeader();
        Map<LottoRank, Integer> winCounts = result.getWinCounts();

        printLineForRank("3개 일치", FIFTH_PRIZE_MONEY, winCounts.get(LottoRank.FIFTH));
        printLineForRank("4개 일치", FOURTH_PRIZE_MONEY, winCounts.get(LottoRank.FOURTH));
        printLineForRank("5개 일치", THIRD_PRIZE_MONEY, winCounts.get(LottoRank.THIRD));
        printLineForRankWithBonus(winCounts.get(LottoRank.SECOND));
        printLineForRank("6개 일치", FIRST_PRIZE_MONEY, winCounts.get(LottoRank.FIRST));

        outputView.printProfitRate(result.getProfitRate());
    }

    private void printLineForRank(String label, int prize, int count) {
        String line = String.format("%s (%,d원) - %d개", label, prize, count);
        outputView.printWinResultLine(line);
    }

    private void printLineForRankWithBonus(int count) {
        String line = String.format("5개 일치, 보너스 볼 일치 (%,d원) - %d개", SECOND_PRIZE_MONEY, count);
        outputView.printWinResultLine(line);
    }
}
