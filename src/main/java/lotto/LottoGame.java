package lotto;

import static java.util.stream.Collectors.*;

import java.util.List;

public class LottoGame {
	private List<Lotto> lottos;

	public LottoGame(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public LottoMatchResults calculateResults(WinningLotto winningLotto) {
		return lottos.stream()
			.map(winningLotto::calculateMatchResult)
			.collect(collectingAndThen(toList(), LottoMatchResults::new));
	}
}
