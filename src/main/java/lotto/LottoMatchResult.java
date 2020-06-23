package lotto;

import java.util.Objects;

public class LottoMatchResult {
	private int matchCount;
	private boolean bonus;

	public LottoMatchResult(int matchCount, boolean bonus) {
		validateMatchCount(matchCount);
		this.matchCount = matchCount;
		this.bonus = bonus;
	}

	private void validateMatchCount(int matchCount) {
		if (matchCount < 0) {
			throw new IllegalArgumentException("matchCount는 음수가 불가능합니다.");
		}
		if (matchCount > Lotto.SIZE) {
			throw new IllegalArgumentException("matchCount는 로또 크기를 초과할 수 없습니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoMatchResult that = (LottoMatchResult)o;
		return matchCount == that.matchCount &&
			bonus == that.bonus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matchCount, bonus);
	}
}
