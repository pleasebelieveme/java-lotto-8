package lotto.common;

/**
 * 로또 게임에서 사용하는 공통 상수를 정의합니다.
 */
public class LottoConstants {

	private LottoConstants() {
		// 상수 클래스는 인스턴스화 방지
	}

	// 로또 번호 범위
	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;
	public static final int LOTTO_NUMBER_COUNT = 6;

	// 로또 가격
	public static final int LOTTO_PRICE = 1000;

	public static final int MIN_PURCHASE_AMOUNT = 1000;
}