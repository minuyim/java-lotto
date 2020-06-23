package domain.lotto;

public class DefaultRankCalculator implements RankCalculator{
	@Override
	public LottoRank calculate(LottoMatchResult lottoMatchResult) {
		return LottoRank.findRank(lottoMatchResult);
	}
}
