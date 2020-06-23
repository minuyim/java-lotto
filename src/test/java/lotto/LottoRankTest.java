package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoRankTest {
	@ParameterizedTest
	@DisplayName("MatchResult에 따른 Rank를 생성한다.")
	@MethodSource("provideMatchedRank")
	void findRank(List<Integer> numbers, LottoRank expected) {
		WinningLotto winningLotto = new WinningLotto(Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
			LottoNumber.of(45));
		assertThat(LottoRank.findRank(winningLotto.calculateMatchResult(Lotto.valueOf(numbers)))).isEqualTo(expected);
	}

	private static Stream<Arguments> provideMatchedRank() {
		return Stream.of(
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), LottoRank.FIRST),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 45), LottoRank.SECOND),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), LottoRank.THIRD),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 7, 8), LottoRank.FORTH),
			Arguments.of(Arrays.asList(1, 2, 3, 7, 8, 9), LottoRank.NOTHING),
			Arguments.of(Arrays.asList(1, 2, 7, 8, 9, 10), LottoRank.NOTHING),
			Arguments.of(Arrays.asList(1, 7, 8, 9, 10, 11), LottoRank.NOTHING),
			Arguments.of(Arrays.asList(7, 8, 9, 10, 11, 12), LottoRank.NOTHING)
		);
	}
}