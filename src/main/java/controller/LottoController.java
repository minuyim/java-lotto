package controller;

import domain.Money;
import domain.RepeatCount;
import domain.lotto.LottoGame;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import domain.generator.RandomNumberGenerator;
import domain.generator.UserNumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        RepeatCount repeatCount = money.createRepeatCount();
        RepeatCount userRepeatCount = repeatCount.split(InputView.inputNumber(
                OutputView::printUserRepeatCountFormat));

        LottoGame userLottoGame = LottoGame.create(new UserNumberGenerator(
                InputView.inputManualLottos(userRepeatCount.getRepeatCount())), userRepeatCount);
        LottoGame autoLottoGame = LottoGame.create(new RandomNumberGenerator(), repeatCount);
        LottoGame lottoGame = LottoGame.merge(userLottoGame, autoLottoGame);

        OutputView.printLottoNumbersCount(userRepeatCount.getRepeatCount(),
                repeatCount.getRepeatCount());
        OutputView.printLottoGame(lottoGame);

        LottoWinner lottoWinner = LottoWinner.create(InputView.inputNumbers(
                OutputView::printWinnerNumbersFormat),
                InputView.inputNumber(OutputView::printBonusNumberFormat));
        LottoResult lottoResult = lottoGame.createGameResult(lottoWinner);

        OutputView.printResultAndEarningRate(money.calculateEarningRate(lottoResult.calculateEarning()),
                lottoResult);
    }
}
