package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
	@Test
	@DisplayName("생성 테스트")
	void valueOf() {
		assertThat(Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6))).isNotNull();
	}

	@Test
	@DisplayName("생성 테스트 - 중복된 숫자가 있을 경우")
	void valueOfWithException() {
		assertThatThrownBy(() -> Lotto.valueOf(Arrays.asList(1, 1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 숫자는");
	}

	@Test
	@DisplayName("생성 테스트 - 로또 넘버 갯수가 다를 경우")
	void valueOfWithSizeException() {
		assertThatThrownBy(() -> Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 숫자는");
	}
}
