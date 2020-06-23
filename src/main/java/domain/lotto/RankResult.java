package domain.lotto;

public class RankResult {
	private LottoRank lottoRank;
	private int count;

	public RankResult(LottoRank lottoRank, int count) {
		this.lottoRank = lottoRank;
		this.count = count;
	}

	public LottoRank getLottoRank() {
		return lottoRank;
	}

	public int getCount() {
		return count;
	}
}
