package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningMoneyTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new WinningMoney(0)).isNotNull();
	}

	@Test
	@DisplayName("생성 테스트 - 음수 예외")
	void constructorWithException() {
		assertThatThrownBy(() -> new WinningMoney(-10))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("음수");
	}

	@Test
	@DisplayName("서로 다른 두 WinningMoney를 더할 수 있는 지 확인")
	void sum() {
		assertThat(WinningMoney.sum(new WinningMoney(10), new WinningMoney(20))).isEqualTo(new WinningMoney(30));
	}

	@Test
	@DisplayName("WinningMoney에 long을 더할 수 있는 지 확인")
	void add() {
		assertThat(new WinningMoney(10L).add(10L)).isEqualTo(new WinningMoney(20L));
	}
}