package domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	public static Map<Integer, LottoNumber> CACHE = new HashMap<>();

	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private int lottoNumber;

	static {
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			CACHE.put(i, new LottoNumber(i));
		}
	}

	private LottoNumber(int number) {
		this.lottoNumber = number;
	}

	private static void validate(int number) {
		if (!CACHE.containsKey(number)) {
			throw new IllegalArgumentException("로또 번호에 해당하는 값을 입력해주세요.");
		}
	}

	public static LottoNumber of(int number) {
		validate(number);
		return CACHE.get(number);
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final LottoNumber that = (LottoNumber)o;
		return lottoNumber == that.lottoNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}

	@Override
	public int compareTo(LottoNumber lottoNumber) {
		return Integer.compare(this.lottoNumber, lottoNumber.lottoNumber);
	}
}
