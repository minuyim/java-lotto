package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoResult(Arrays.asList(LottoRank.FIRST, LottoRank.SECOND))).isNotNull();
	}
}
