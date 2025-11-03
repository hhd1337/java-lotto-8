package lotto.support.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseAmountValidatorTest {

    private final PurchaseAmountValidator validator = new PurchaseAmountValidator();

    @DisplayName("구입금액이 1000원 단위의 양수라면 예외가 발생하지 않는다.")
    @Test
    void 구입금액이_1000원_단위의_양수라면_예외가_발생하지_않는다() {
        // given
        int amount = 6000;

        // when & then
        assertThatCode(() -> validator.validate(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("구입금액이 0이면 예외가 발생한다.")
    @Test
    void 구입금액이_0이면_예외가_발생한다() {
        // given
        int amount = 0;

        // when & then
        assertThatThrownBy(() -> validator.validate(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("구입금액이 음수이면 예외가 발생한다.")
    @Test
    void 구입금액이_음수이면_예외가_발생한다() {
        // given
        int amount = -1000;

        // when & then
        assertThatThrownBy(() -> validator.validate(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("구입금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구입금액이_1000원_단위가_아니면_예외가_발생한다() {
        // given
        int amount = 1500;

        // when & then
        assertThatThrownBy(() -> validator.validate(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
