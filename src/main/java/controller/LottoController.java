package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.lotto.DefaultRankCalculator;
import domain.lotto.Lotto;
import domain.lotto.LottoGame;
import domain.lotto.LottoMatchResults;
import domain.lotto.LottoNumber;
import domain.lotto.LottoPurchase;
import domain.lotto.LottoRank;
import domain.lotto.LottoResult;
import domain.lotto.LottoStore;
import domain.lotto.Money;
import domain.lotto.RandomLottoGenerator;
import domain.lotto.WinningLotto;
import domain.lotto.WinningMoney;
import dto.LottoDto;
import dto.RankResultDto;
import view.InputView;
import view.OutputView;

public class LottoController {
	private InputView inputView;
	private OutputView outputView;

	public LottoController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		Money money = new Money(inputView.getMoney());
		LottoPurchase lottoPurchase = new LottoPurchase(money, inputView.getManualAmount());
		LottoStore lottoStore = new LottoStore(new RandomLottoGenerator());
		LottoGame game = lottoStore.createGame(lottoPurchase, getManualLotto(lottoPurchase.getManualAmount()));
		outputView.printLottoCount(lottoPurchase.getManualAmount(), lottoStore.getAutoAmount(lottoPurchase));
		outputView.printLotto(getLottoDtos(game.getLottos()));

		WinningLotto winningLotto = new WinningLotto(Lotto.valueOf(inputView.getWinningLotto()),
			LottoNumber.of(inputView.getBonusNumber()));
		LottoMatchResults lottoMatchResults = game.calculateResults(winningLotto);
		LottoResult lottoResult = lottoMatchResults.calculateResults(new DefaultRankCalculator());
		WinningMoney winningMoney = lottoResult.calculateWinning();

		outputView.printRankResults(getRankResults(lottoResult));
		outputView.printWinningRate(winningMoney.calculateWinningRate(money));
	}

	private List<RankResultDto> getRankResults(LottoResult lottoResult) {
		return Arrays.stream(LottoRank.values())
			.filter(it -> it != LottoRank.NOTHING)
			.map(lottoResult::countRank)
			.map(RankResultDto::of)
			.collect(Collectors.toList());
	}

	private List<LottoDto> getLottoDtos(List<Lotto> lottos) {
		return lottos.stream()
			.map(LottoDto::of)
			.collect(Collectors.toList());
	}

	private List<Lotto> getManualLotto(int manualAmount) {
		return inputView.getManualLottos(manualAmount)
			.stream()
			.map(Lotto::valueOf)
			.collect(Collectors.toList());
	}
}
