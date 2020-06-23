package domain.lotto;

import java.util.Arrays;

import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoGenerateStrategy;

public class TestGenerateStrategy implements LottoGenerateStrategy {
	@Override
	public Lotto createLotto() {
		return Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6));
	}
}
