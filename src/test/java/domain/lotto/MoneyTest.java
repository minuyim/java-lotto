package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new Money(10L)).isNotNull();
	}

	@Test
	@DisplayName("생성 테스트 - 0이하일 경우 예외 처리")
	void constructorWithException() {
		assertThatThrownBy(() -> new Money(0L))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("0보다");
	}

	@Test
	void calculateRate() {
		assertThat(new Money(10L).calculateRate(100)).isEqualTo(10);
	}

	@Test
	void isDividable() {
		assertThat(new Money(1000L).isDividable(100L)).isTrue();
	}

	@Test
	void divideBy() {
		assertThat(new Money(1000L).divideBy(100L)).isEqualTo(10);
	}

	@Test
	void isEqualToOrLessThan() {
		assertThat(new Money(1000L).isEqualToOrLessThan(1000L)).isTrue();
	}
}