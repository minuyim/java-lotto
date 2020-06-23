package lotto;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Lotto {
	private static final int LOTTO_SIZE = 6;

	private SortedSet<LottoNumber> lotto;

	private Lotto(SortedSet<LottoNumber> lotto) {
		validateSize(lotto);
		this.lotto = lotto;
	}

	private void validateSize(Set<LottoNumber> lotto) {
		if (lotto.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또 숫자는 " + LOTTO_SIZE + "개여야 합니다.");
		}
	}

	public static Lotto valueOf(List<Integer> lotto) {
		return lotto.stream()
			.map(LottoNumber::of)
			.collect(collectingAndThen(toCollection(TreeSet::new), Lotto::new));
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public int countMatchedNumber(Lotto compared) {
		return (int) lotto.stream()
			.filter(compared::contains)
			.count();
	}
}
