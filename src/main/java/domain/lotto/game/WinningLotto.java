package domain.lotto.game;

import domain.lotto.result.LottoMatchResult;
import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoNumber;

public class WinningLotto {
	private Lotto winning;
	private LottoNumber bonus;

	public WinningLotto(Lotto winning, LottoNumber bonus) {
		this.winning = winning;
		this.bonus = bonus;
	}

	public LottoMatchResult calculateMatchResult(Lotto lotto) {
		return new LottoMatchResult(lotto.countMatchedNumber(winning), lotto.contains(bonus));
	}
}
