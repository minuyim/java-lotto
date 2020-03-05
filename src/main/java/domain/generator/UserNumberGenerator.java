package domain.generator;

import java.util.Iterator;
import java.util.List;

public class UserNumberGenerator implements NumberGenerator {
    private static final String ERROR_REPEAT_END_MESSAGE = "입력한 번호를 모두 반환하였습니다.";

    private Iterator<List<Integer>> userInputs;

    public UserNumberGenerator(List<List<Integer>> userInputs) {
        this.userInputs = userInputs.iterator();
    }

    @Override
    public List<Integer> create() {
        if (userInputs.hasNext()) {
            return userInputs.next();
        }
        throw new RuntimeException(ERROR_REPEAT_END_MESSAGE);
    }
}
