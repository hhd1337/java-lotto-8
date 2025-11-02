package lotto.support;

import java.util.ArrayList;
import java.util.List;

public class WinningNumberParser {

    private static final String SEPARATOR = ",";

    public List<Integer> parse(String input) {
        validateBlankAndNull(input);
        input = input.trim();

        String[] parts = input.split(SEPARATOR);
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            numbers.add(parseToInt(part));
        }
        return numbers;
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다. : " + input);
        }
    }

    private void validateBlankAndNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다.");
        }
    }

}
