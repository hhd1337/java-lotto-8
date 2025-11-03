package lotto.support.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberValidatorTest {

    private final BonusNumberValidator validator = new BonusNumberValidator();

    @DisplayName("보너스번호가 정상 범위이고 당첨 번호와 중복되지 않으면 예외가 발생하지 않는다.")
    @Test
    void 보너스번호가_정상범위이고_당첨번호와_중복되지_않으면_예외가_발생하지_않는다() {
        // given
        int bonus = 7;
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatCode(() -> validator.validate(bonus, winningNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스번호가 1 미만이면 예외가 발생한다.")
    @Test
    void 보너스번호가_1_미만이면_예외가_발생한다() {
        // given
        int bonus = 0;
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatThrownBy(() -> validator.validate(bonus, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("보너스번호가 45 초과이면 예외가 발생한다.")
    @Test
    void 보너스번호가_45_초과이면_예외가_발생한다() {
        // given
        int bonus = 46;
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatThrownBy(() -> validator.validate(bonus, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("보너스번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스번호가_당첨번호와_중복되면_예외가_발생한다() {
        // given
        int bonus = 6;
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatThrownBy(() -> validator.validate(bonus, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
