package lotto.parser;

public class BonusNumberParser {
	private static final String ERROR_EMPTY_INPUT = "[ERROR] 보너스 번호를 입력해주세요.";
	private static final String ERROR_INVALID_FORMAT = "[ERROR] 보너스 번호는 숫자여야 합니다.";

	/**
	 * 사용자 입력 문자열(보너스 번호)을 정수로 파싱하여 반환합니다.
	 *
	 * @param input 사용자가 입력한 보너스 번호 문자열
	 * @return 파싱된 보너스 번호 (정수)
	 * @throws IllegalArgumentException 입력이 null 또는 빈 문자열인 경우
	 * @throws NumberFormatException 숫자가 아닌 값이 포함된 경우
	 */
	public static int parse(String input) {
		ParserUtils.validateNotNullOrEmpty(input, ERROR_EMPTY_INPUT);
		String trimmedInput = input.trim();
		return ParserUtils.parseToInteger(trimmedInput, ERROR_INVALID_FORMAT);
	}
}