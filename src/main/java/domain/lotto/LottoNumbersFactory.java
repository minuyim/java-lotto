package domain.lotto;

import domain.generator.NumberGenerator;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoNumbersFactory {
    private LottoNumbersFactory() {
        throw new AssertionError();
    }

    public static LottoNumbers create(NumberGenerator numberGenerator) {
        return create(numberGenerator.create());
    }

    public static LottoNumbers create(List<Integer> ints) {
        return ints.stream()
                .map(LottoNumberFactory::getInstance)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(TreeSet::new),
                        LottoNumbers::new));
    }
}
