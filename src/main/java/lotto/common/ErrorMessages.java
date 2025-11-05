package lotto.common;

/**
 * 로또 게임에서 사용하는 에러 메시지를 정의합니다.
 */
public class ErrorMessages {

	private ErrorMessages() {
		// 상수 클래스는 인스턴스화 방지
	}

	// 구매 금액 관련
	public static final String PURCHASE_AMOUNT_ZERO = "[ERROR] 구입 금액은 0보다 커야 합니다.";
	public static final String PURCHASE_AMOUNT_MINIMUM = "[ERROR] 구입 금액은 1,000원 이상이어야 합니다.";
	public static final String PURCHASE_AMOUNT_UNIT = "[ERROR] 구입 금액은 1,000원 단위여야 합니다.";
	public static final String PURCHASE_AMOUNT_EMPTY = "[ERROR] 구입 금액을 입력해주세요.";
	public static final String PURCHASE_AMOUNT_INVALID_FORMAT = "[ERROR] 구입 금액은 숫자여야 합니다.";

	// 로또 번호 관련
	public static final String LOTTO_NUMBERS_NULL = "[ERROR] 로또 번호를 입력해주세요.";
	public static final String LOTTO_NUMBERS_SIZE = "[ERROR] 로또 번호는 6개여야 합니다.";
	public static final String LOTTO_NUMBERS_DUPLICATE = "[ERROR] 로또 번호는 중복될 수 없습니다.";
	public static final String LOTTO_NUMBERS_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";

	// 당첨 번호 관련
	public static final String WINNING_NUMBERS_EMPTY = "[ERROR] 당첨 번호를 입력해주세요.";
	public static final String WINNING_NUMBERS_INVALID_FORMAT = "[ERROR] 당첨 번호는 숫자여야 합니다.";
	public static final String WINNING_NUMBERS_EMPTY_VALUE = "[ERROR] 당첨 번호는 빈 값일 수 없습니다.";

	// 보너스 번호 관련
	public static final String BONUS_NUMBER_EMPTY = "[ERROR] 보너스 번호를 입력해주세요.";
	public static final String BONUS_NUMBER_INVALID_FORMAT = "[ERROR] 보너스 번호는 숫자여야 합니다.";
	public static final String BONUS_NUMBER_RANGE = "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.";
	public static final String BONUS_NUMBER_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

	// 기타
	public static final String LOTTO_TICKETS_NULL = "[ERROR] 로또 목록이 없습니다.";
	public static final String LOTTO_TICKETS_EMPTY = "[ERROR] 최소 1개 이상의 로또를 구매해야 합니다.";
	public static final String LOTTO_RESULT_PURCHASE_AMOUNT = "[ERROR] 구매 금액은 0보다 커야 합니다.";
}