package lotto;

import java.util.List;

public class LottoResult {
	private List<LottoRank> lottoRanks;

	public LottoResult(List<LottoRank> lottoRanks) {
		this.lottoRanks = lottoRanks;
	}

	public WinningMoney calculateWinning() {
		return lottoRanks.stream()
			.reduce(new WinningMoney(0), ((total, lottoRank) -> lottoRank.addWinning(total)), WinningMoney::sum);
	}
}
