package lotto.support.validator;

public class PurchaseAmountValidator {

    private static final int UNIT = 1000;

    public void validate(int amount) {
        validatePositive(amount);
        validateMultipleOfUnit(amount);
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 0보다 큰 양수여야 합니다.");
        }
    }

    private void validateMultipleOfUnit(int amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 1000원 단위여야 합니다.");
        }
    }
}
