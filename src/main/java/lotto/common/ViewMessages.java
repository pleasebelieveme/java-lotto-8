package lotto.common;

/**
 * 로또 게임에서 사용하는 출력 메시지를 정의합니다.
 */
public class ViewMessages {

	private ViewMessages() {
		// 상수 클래스는 인스턴스화 방지
	}

	// 입력 안내
	public static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
	public static final String INPUT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
	public static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

	// 결과 출력
	public static final String PURCHASED_LOTTO_COUNT_FORMAT = "%d개를 구매했습니다.";
	public static final String WINNING_STATISTICS_HEADER = "당첨 통계";
	public static final String SEPARATOR = "---";
	public static final String RANK_FORMAT = "%s (%s원) - %d개";
	public static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
}