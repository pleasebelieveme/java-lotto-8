package lotto.parser;

import java.util.Objects;

public class BonusNumberParser {

	public static int parse(String input) {
		if (Objects.isNull(input) || input.trim().isEmpty()) {
			throw new IllegalArgumentException("[ERROR] 보너스 번호를 입력해주세요.");
		}
		String trimmedInput = input.trim();
		return parseToInteger(trimmedInput);
	}

	private static int parseToInteger(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("[ERROR] 보너스 번호는 숫자여야 합니다.");
		}
	}
}