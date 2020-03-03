package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 1, 45})
    @DisplayName("생성 테스트")
    void test1(int value) {
        Assertions.assertThatCode(() -> new LottoNumber(value)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,-1", "2,0", "3,1", "4,1"})
    @DisplayName("비교 테스트")
    void test2(int value, int expected) {
        LottoNumber lottoNumber = new LottoNumber(value);
        LottoNumber comparedNumber = new LottoNumber(2);
        Assertions.assertThat(lottoNumber.compareTo(comparedNumber)).isEqualTo(expected);
    }
}
