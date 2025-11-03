package lotto.parser;

import java.util.Objects;

public class PurchaseAmountParser {

	public static int parse(String input) {
		if (input == null || input.trim().isEmpty()) {
			throw new IllegalArgumentException("[ERROR] 구입 금액을 입력해주세요.");
		}
		return parseToInteger(input);
	}

	private static int parseToInteger(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("[ERROR] 구입 금액은 숫자여야 합니다.");
		}
	}
}