package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMatchResultTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoMatchResult(6, true));
	}

	@Test
	@DisplayName("생성 테스트 - 음수 예외")
	void constructorWithNegativeException() {
		assertThatThrownBy(() -> new LottoMatchResult(-1, false))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("음수");
	}

	@Test
	void constructorWithExceedException() {
		assertThatThrownBy(() -> new LottoMatchResult(7, false))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}
}