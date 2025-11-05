package lotto.parser;

import static lotto.common.ErrorMessages.*;

public class PurchaseAmountParser {

	/**
	 * 사용자 입력 문자열(구입 금액)을 정수로 파싱하여 반환합니다.
	 *
	 * @param input 사용자가 입력한 구입 금액 문자열
	 * @return 파싱된 구입 금액 (정수)
	 * @throws IllegalArgumentException 입력이 null 또는 빈 문자열인 경우
	 * @throws NumberFormatException 숫자가 아닌 값이 포함된 경우
	 */
	public static int parse(String input) {
		ParserUtils.validateNotNullOrEmpty(input, PURCHASE_AMOUNT_EMPTY);
		String trimmedInput = input.trim();
		return ParserUtils.parseToInteger(trimmedInput, PURCHASE_AMOUNT_INVALID_FORMAT);
	}
}