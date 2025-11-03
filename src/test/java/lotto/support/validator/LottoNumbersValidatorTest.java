package lotto.support.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersValidatorTest {

    private final LottoNumbersValidator validator = new LottoNumbersValidator();

    @DisplayName("정상적인 당첨번호 리스트는 예외가 발생하지 않는다.")
    @Test
    void 정상적인_당첨번호_리스트는_예외가_발생하지_않는다() {
        // given
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatCode(() -> validator.validate(validNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("리스트가 null이면 예외가 발생한다.")
    @Test
    void 리스트가_null이면_예외가_발생한다() {
        // given
        List<Integer> numbers = null;

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("리스트가 비어 있으면 예외가 발생한다.")
    @Test
    void 리스트가_비어_있으면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of();

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("리스트 크기가 6이 아니면 예외가 발생한다.")
    @Test
    void 리스트_크기가_6이_아니면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("번호 중 0 이하의 정수가 있으면 예외가 발생한다.")
    @Test
    void 번호_중_0_이하의_정수가_있으면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("번호 중 46 이상의 정수가 있으면 예외가 발생한다.")
    @Test
    void 번호_중_46_이상의_정수가_있으면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("중복된 번호가 있으면 예외가 발생한다.")
    @Test
    void 중복된_번호가_있으면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
