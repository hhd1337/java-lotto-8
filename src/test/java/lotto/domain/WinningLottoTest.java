package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("정상적인 당첨번호와 보너스번호를 입력하면 객체가 생성된다.")
    @Test
    void 정상적인_당첨번호와_보너스번호를_입력하면_객체가_생성된다() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;
        // when
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
        // then
        assertThat(winningLotto.getWinningNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @DisplayName("보너스번호가 당첨번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스번호가_당첨번호와_중복되면_예외가_발생한다() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 6;
        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
