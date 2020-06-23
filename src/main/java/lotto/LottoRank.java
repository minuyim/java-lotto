package lotto;

import java.util.Arrays;

public enum LottoRank {
	FIRST(6, DefaultMatchingStrategy.DEFAULT),
	SECOND(5, DefaultMatchingStrategy.WITH_BONUS),
	THIRD(5, DefaultMatchingStrategy.WITHOUT_BONUS),
	FORTH(4, DefaultMatchingStrategy.DEFAULT),
	NOTHING(3, DefaultMatchingStrategy.UNDER);

	private final int matchCount;
	private final MatchingStrategy matchingStrategy;

	LottoRank(int matchCount, MatchingStrategy matchingStrategy) {
		this.matchCount = matchCount;
		this.matchingStrategy = matchingStrategy;
	}

	public static LottoRank findRank(LottoMatchResult lottoMatchResult) {
		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.isMatch(lottoMatchResult))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("조건에 해당하는 Rank가 존재하지 않습니다."));
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
