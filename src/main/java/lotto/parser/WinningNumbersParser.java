package lotto.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningNumbersParser {
	private static final String SEPARATOR = ",";

	/**
	 * 사용자 입력 문자열(당첨 번호)을 정수 리스트로 파싱하여 반환합니다.
	 *
	 * @param input 사용자가 입력한 당첨 번호 문자열 (쉼표로 구분됨)
	 * @return 파싱된 당첨 번호 리스트 (정수)
	 * @throws IllegalArgumentException 입력이 null, 빈 문자열, 또는 쉼표 구분 형식이 아닌 경우
	 * @throws NumberFormatException 숫자가 아닌 값이 포함된 경우
	 */
	public static List<Integer> parse(String input) {
		validateNotNullOrEmpty(input);

		List<String> numberStrings = splitBySeparator(input);
		return parseToIntegers(numberStrings);
	}

	private static void validateNotNullOrEmpty(String input) {
		if (Objects.isNull(input) || input.trim().isEmpty()) {
			throw new IllegalArgumentException("[ERROR] 당첨 번호를 입력해주세요.");
		}
	}

	private static List<String> splitBySeparator(String input) {
		List<String> parts = Arrays.stream(input.split(SEPARATOR, -1))
			.map(String::trim)
			.collect(Collectors.toList());

		if (parts.stream().anyMatch(String::isEmpty)) {
			throw new IllegalArgumentException("[ERROR] 당첨 번호는 빈 값일 수 없습니다.");
		}

		return parts;
	}

	private static List<Integer> parseToIntegers(List<String> numberStrings) {
		try {
			return numberStrings.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new NumberFormatException("[ERROR] 당첨 번호는 숫자여야 합니다.");
		}
	}
}