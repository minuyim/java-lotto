package domain.store;

import java.util.ArrayList;
import java.util.List;

import domain.lotto.game.LottoGame;
import domain.lotto.ticket.LottoGenerateStrategy;
import domain.lotto.ticket.Lotto;

public class LottoStore {
	private static final long LOTTO_PRICE = 1_000L;
	private LottoGenerateStrategy lottoGenerateStrategy;

	public LottoStore(LottoGenerateStrategy lottoGenerateStrategy) {
		this.lottoGenerateStrategy = lottoGenerateStrategy;
	}

	public LottoGame createGame(LottoPurchase lottoPurchase, List<Lotto> lottos) {
		if (lottoPurchase.isNotEqualToManualAmount(lottos.size())) {
			throw new IllegalArgumentException("수동 입력이 너무 많습니다.");
		}
		List<Lotto> mergelottos = new ArrayList<>(lottos);
		for (int i = 0; i < getAutoAmount(lottoPurchase); i++) {
			mergelottos.add(lottoGenerateStrategy.createLotto());
		}
		return new LottoGame(mergelottos);
	}

	public int getAutoAmount(LottoPurchase lottoPurchase) {
		return lottoPurchase.calculateAutoAmount(LOTTO_PRICE);
	}
}
