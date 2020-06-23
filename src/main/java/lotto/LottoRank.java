package lotto;

import java.util.Arrays;

public enum LottoRank {
	FIRST(6, DefaultMatchingStrategy.DEFAULT, 2_000_000_000L),
	SECOND(5, DefaultMatchingStrategy.WITH_BONUS, 30_000_000L),
	THIRD(5, DefaultMatchingStrategy.WITHOUT_BONUS, 1_500_000L),
	FORTH(4, DefaultMatchingStrategy.DEFAULT, 50_000L),
	FIFTH(3, DefaultMatchingStrategy.DEFAULT, 5_000L),
	NOTHING(2, DefaultMatchingStrategy.UNDER, 0L);

	private final int matchCount;
	private final MatchingStrategy matchingStrategy;
	private long winning;

	LottoRank(int matchCount, MatchingStrategy matchingStrategy, long winning) {
		this.matchCount = matchCount;
		this.matchingStrategy = matchingStrategy;
		this.winning = winning;
	}

	public static LottoRank findRank(LottoMatchResult lottoMatchResult) {
		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.isMatch(lottoMatchResult))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("조건에 해당하는 Rank가 존재하지 않습니다."));
	}

	public WinningMoney addWinning(WinningMoney subTotal) {
		return subTotal.add(winning);
	}

	private boolean isMatch(LottoMatchResult lottoMatchResult) {
		return matchingStrategy.isMatch(lottoMatchResult, matchCount);
	}

	@FunctionalInterface
	private interface MatchingStrategy {
		boolean isMatch(LottoMatchResult lottoMatchResult, int matchCount);
	}

	private enum DefaultMatchingStrategy implements MatchingStrategy {
		DEFAULT {
			@Override
			public boolean isMatch(LottoMatchResult lottoMatchResult, int matchCount) {
				return lottoMatchResult.isEqualToMatchCount(matchCount);
			}
		},
		WITH_BONUS {
			@Override
			public boolean isMatch(LottoMatchResult lottoMatchResult, int matchCount) {
				return lottoMatchResult.isEqualToMatchCount(matchCount) && lottoMatchResult.hasBonus();
			}
		},
		WITHOUT_BONUS {
			@Override
			public boolean isMatch(LottoMatchResult lottoMatchResult, int matchCount) {
				return lottoMatchResult.isEqualToMatchCount(matchCount) && !lottoMatchResult.hasBonus();
			}
		},
		UNDER {
			@Override
			public boolean isMatch(LottoMatchResult lottoMatchResult, int matchCount) {
				return lottoMatchResult.isEqualToOrGraterThanMatchCount(matchCount);
			}
		}
	}
}
