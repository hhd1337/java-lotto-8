package lotto.support.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberParserTest {

    private final WinningNumberParser parser = new WinningNumberParser();

    @DisplayName("정상적인 입력 '1,2,3,4,5,6'은 정수 리스트로 파싱된다.")
    @Test
    void 정상_입력은_정수_리스트로_파싱된다() {
        // when
        List<Integer> numbers = parser.parse("1,2,3,4,5,6");
        // then
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("입력 문자열 앞뒤 공백이 있어도 파싱된다.")
    @Test
    void 입력_문자열_앞뒤_공백이_있어도_파싱된다() {
        // when
        List<Integer> numbers = parser.parse("   1,2,3,4,5,6   ");
        // then
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("입력이 null이면 예외가 발생한다.")
    @Test
    void 입력이_null이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> parser.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력이 공백이면 예외가 발생한다.")
    @Test
    void 입력이_공백이면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> parser.parse("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("비정수가 포함되면 예외가 발생한다.")
    @Test
    void 비정수가_포함되면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> parser.parse("1,2,a,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
