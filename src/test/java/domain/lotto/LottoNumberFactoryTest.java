package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberFactoryTest {
    @Test
    @DisplayName("팩토리 생성 테스트")
    void test1() {
        Assertions.assertThatCode(() -> LottoNumberFactory.getInstance(1)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    @DisplayName("캐시를 이용해 값이 같으면 동일한 객체 생성")
    void test2(int value) {
        LottoNumber lottoNumber1 = LottoNumberFactory.getInstance(value);
        LottoNumber lottoNumber2 = LottoNumberFactory.getInstance(value);
        Assertions.assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    @DisplayName("예외처리 테스트")
    void test3(int value) {
        Assertions.assertThatThrownBy(() -> LottoNumberFactory.getInstance(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1부터 45 사이의 숫자만 입력 가능합니다.");
    }

}
