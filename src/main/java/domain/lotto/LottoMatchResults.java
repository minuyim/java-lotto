package domain.lotto;

import static java.util.stream.Collectors.*;

import java.util.List;

public class LottoMatchResults {
	private List<LottoMatchResult> lottoMatchResults;

	public LottoMatchResults(List<LottoMatchResult> lottoMatchResults) {
		this.lottoMatchResults = lottoMatchResults;
	}

	public LottoResult calculateResults(RankCalculator calculator) {
		return lottoMatchResults.stream()
			.map(calculator::calculate)
			.collect(collectingAndThen(toList(), LottoResult::new));
	}
}
