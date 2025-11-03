package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    private final LottoFactory factory = new LottoFactory();

    @DisplayName("로또 한장을 생성하면 1~45 사이의 중복 없는 6개 숫자가 오름차순으로 담긴다.")
    @Test
    void 로또_한장을_생성하면_유효한_숫자_6개가_오름차순으로_담긴다() {
        // when
        Lotto lotto = factory.generateOne();
        List<Integer> nums = lotto.getNumbers();

        // then
        assertThat(nums).hasSize(6);
        assertThat(nums).isSorted();
        assertThat(nums).allMatch(n -> n >= 1 && n <= 45);

        Set<Integer> distinct = new HashSet<>(nums);
        assertThat(distinct).hasSameSizeAs(nums); // 중복 없음
    }

    @DisplayName("generateMany()는 요청한 개수만큼 유효한 로또를 생성한다.")
    @Test
    void generateMany메소드는_요청한_개수만큼_유효한_로또를_생성한다() {
        // given
        int count = 3;

        // when
        List<Lotto> lottos = factory.generateMany(count);

        // then
        assertThat(lottos).hasSize(count);
        for (Lotto lotto : lottos) {
            List<Integer> nums = lotto.getNumbers();
            assertThat(nums).hasSize(6);
            assertThat(nums).isSorted();
            assertThat(nums).allMatch(n -> n >= 1 && n <= 45);
            assertThat(new HashSet<>(nums)).hasSameSizeAs(nums);
        }
    }

    @DisplayName("로또 생성 개수가 0이면 예외가 발생한다.")
    @Test
    void 로또_생성_개수가_0이면_예외가_발생한다() {
        assertThatThrownBy(() -> factory.generateMany(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 생성 개수가 음수이면 예외가 발생한다.")
    @Test
    void 로또_생성_개수가_음수이면_예외가_발생한다() {
        assertThatThrownBy(() -> factory.generateMany(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
