package lotto.parser;

import static lotto.common.ErrorMessages.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbersParser {
	private static final String SEPARATOR = ",";

	/**
	 * 사용자 입력 문자열(당첨 번호)을 정수 리스트로 파싱하여 반환합니다.
	 *
	 * @param input 사용자가 입력한 당첨 번호 문자열 (쉼표로 구분됨)
	 * @return 파싱된 당첨 번호 리스트 (정수)
	 * @throws IllegalArgumentException 입력이 null, 빈 문자열, 또는 빈 값이 포함된 경우
	 * @throws NumberFormatException 숫자가 아닌 값이 포함된 경우
	 */
	public static List<Integer> parse(String input) {
		ParserUtils.validateNotNullOrEmpty(input, WINNING_NUMBERS_EMPTY);

		List<String> numberStrings = splitBySeparator(input);
		return parseToIntegers(numberStrings);
	}

	private static List<String> splitBySeparator(String input) {
		List<String> parts = Arrays.stream(input.split(SEPARATOR, -1))
			.map(String::trim)
			.collect(Collectors.toList());

		validateNoEmptyValues(parts);
		return parts;
	}

	private static void validateNoEmptyValues(List<String> parts) {
		if (parts.stream().anyMatch(String::isEmpty)) {
			throw new IllegalArgumentException(WINNING_NUMBERS_EMPTY_VALUE);
		}
	}

	private static List<Integer> parseToIntegers(List<String> numberStrings) {
		return numberStrings.stream()
			.map(str -> ParserUtils.parseToInteger(str, WINNING_NUMBERS_INVALID_FORMAT))
			.collect(Collectors.toList());
	}
}