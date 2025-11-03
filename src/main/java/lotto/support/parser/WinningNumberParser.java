package lotto.support.parser;

import java.util.ArrayList;
import java.util.List;

public class WinningNumberParser {
    private static final String SEPARATOR = ",";
    StringToIntegerParser stringToIntegerparser = new StringToIntegerParser();

    public List<Integer> parse(String input) {
        validateBlankAndNull(input);
        input = input.trim();

        String[] parts = input.split(SEPARATOR);
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            numbers.add(stringToIntegerparser.parseToInt(part));
        }
        return numbers;
    }

    private void validateBlankAndNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다.");
        }
    }

}
