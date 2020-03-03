package domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumberFactory {
    private static final int LOTTO_UNDER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final String ERROR_BOUND_MESSAGE = "1부터 45 사이의 숫자만 입력 가능합니다.";
    private static final Map<Integer, LottoNumber> cache = new HashMap<>();

    static {
        for (int i = LOTTO_UNDER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            cache.put(i, new LottoNumber(i));
        }
    }

    private LottoNumberFactory() {
        throw new AssertionError();
    }

    public static LottoNumber getInstance(int number) {
        LottoNumber lottoNumber = cache.get(number);
        if (Objects.isNull(lottoNumber)) {
            throw new IllegalArgumentException(ERROR_BOUND_MESSAGE);
        }
        return lottoNumber;
    }
}
