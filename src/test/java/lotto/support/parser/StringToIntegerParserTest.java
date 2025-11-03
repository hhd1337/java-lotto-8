package lotto.support.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringToIntegerParserTest {

    private final StringToIntegerParser parser = new StringToIntegerParser();

    @DisplayName("숫자 문자열이면 정수로 변환된다.")
    @Test
    void 숫자_문자열이면_정수로_변환된다() {
        // when
        int value = parser.parseToInt("42");
        // then
        assertThat(value).isEqualTo(42);
    }

    @DisplayName("숫자가 아닌 문자열이면 예외가 발생한다.")
    @Test
    void 숫자가_아닌_문자열이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> parser.parseToInt("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
