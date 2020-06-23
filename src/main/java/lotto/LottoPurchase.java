package lotto;

public class LottoPurchase {
	private Money money;
	private int manualAmount;

	public LottoPurchase(Money money, int manualAmount) {
		validate(manualAmount);
		this.money = money;
		this.manualAmount = manualAmount;
	}

	private void validate(int manualAmount) {
		if (manualAmount < 0) {
			throw new IllegalArgumentException("수동 구매 횟수는 음수일 수 없습니다.");
		}
	}

	public int calculateAutoAmount(long lottoPrice) {
		if (money.isEqualToOrLessThan(lottoPrice * manualAmount)) {
			throw new IllegalArgumentException("수동 구매 횟수가 너무 많습니다.");
		}
		if (lottoPrice <= 0) {
			throw new IllegalArgumentException("로또 가격은 0이하일 수 없습니다.");
		}
		if (!money.isDividable(lottoPrice)) {
			throw new IllegalArgumentException("거스름돈이 남습니다. 다시 입력해주세요.");
		}
		return money.divideBy(lottoPrice) - manualAmount;
	}

	public boolean isNotEqualToManualAmount(int size) {
		return manualAmount != size;
	}
}
