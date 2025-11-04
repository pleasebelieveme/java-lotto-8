package lotto.parser;

public class PurchaseAmountParser {
	private static final String ERROR_EMPTY_INPUT = "[ERROR] 구입 금액을 입력해주세요.";
	private static final String ERROR_INVALID_FORMAT = "[ERROR] 구입 금액은 숫자여야 합니다.";

	/**
	 * 사용자 입력 문자열(구입 금액)을 정수로 파싱하여 반환합니다.
	 *
	 * @param input 사용자가 입력한 구입 금액 문자열
	 * @return 파싱된 구입 금액 (정수)
	 * @throws IllegalArgumentException 입력이 null 또는 빈 문자열인 경우
	 * @throws NumberFormatException 숫자가 아닌 값이 포함된 경우
	 */
	public static int parse(String input) {
		ParserUtils.validateNotNullOrEmpty(input, ERROR_EMPTY_INPUT);
		String trimmedInput = input.trim();
		return ParserUtils.parseToInteger(trimmedInput, ERROR_INVALID_FORMAT);
	}
}