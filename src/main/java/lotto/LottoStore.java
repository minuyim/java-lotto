package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
	private long price;
	private LottoGenerateStrategy lottoGenerateStrategy;

	public LottoStore(long price, LottoGenerateStrategy lottoGenerateStrategy) {
		this.price = price;
		this.lottoGenerateStrategy = lottoGenerateStrategy;
	}

	public LottoGame createGame(LottoPurchase lottoPurchase, List<Lotto> lottos) {
		if (lottoPurchase.isNotEqualToManualAmount(lottos.size())) {
			throw new IllegalArgumentException("수동 입력이 너무 많습니다.");
		}
		List<Lotto> mergelottos = new ArrayList<>(lottos);
		for (int i = 0; i < lottoPurchase.calculateAutoAmount(price); i++) {
			mergelottos.add(lottoGenerateStrategy.createLotto());
		}
		return new LottoGame(mergelottos);
	}
}
