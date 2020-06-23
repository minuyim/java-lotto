package domain.lotto.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoGenerateStrategy;
import domain.lotto.ticket.LottoNumber;

public class RandomLottoGenerator implements LottoGenerateStrategy {
	private static List<Integer> lottoNumbers = new ArrayList<>(LottoNumber.CACHE.keySet());

	@Override
	public Lotto createLotto() {
		Collections.shuffle(lottoNumbers);
		return Lotto.valueOf(new ArrayList<>(lottoNumbers.subList(0, 6)));
	}
}
