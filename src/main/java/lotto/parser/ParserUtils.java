package lotto.parser;

import java.util.Objects;

/**
 * Parser들이 공통으로 사용하는 유틸리티 메서드를 제공합니다.
 */
public class ParserUtils {

	private ParserUtils() {
		// 유틸리티 클래스는 인스턴스화 방지
	}

	/**
	 * 입력이 null이거나 빈 문자열(공백 포함)인지 검증합니다.
	 *
	 * @param input 검증할 입력 문자열
	 * @param errorMessage 예외 발생 시 표시할 에러 메시지
	 * @throws IllegalArgumentException 입력이 null이거나 빈 문자열인 경우
	 */
	public static void validateNotNullOrEmpty(String input, String errorMessage) {
		if (Objects.isNull(input) || input.trim().isEmpty()) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	/**
	 * 문자열을 정수로 파싱합니다.
	 *
	 * @param input 파싱할 문자열
	 * @param errorMessage 파싱 실패 시 표시할 에러 메시지
	 * @return 파싱된 정수
	 * @throws NumberFormatException 숫자로 변환할 수 없는 경우
	 */
	public static int parseToInteger(String input, String errorMessage) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(errorMessage);
		}
	}
}