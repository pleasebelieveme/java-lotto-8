package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.common.ViewMessages.*;

/**
 * 사용자 입력을 받는 View 클래스입니다.
 */
public class InputView {

	/**
	 * 구매 금액을 입력받습니다.
	 *
	 * @return 사용자가 입력한 구매 금액 문자열
	 */
	public String inputPurchaseAmount() {
		System.out.println(INPUT_PURCHASE_AMOUNT);
		return Console.readLine();
	}

	/**
	 * 당첨 번호를 입력받습니다.
	 *
	 * @return 사용자가 입력한 당첨 번호 문자열 (쉼표로 구분)
	 */
	public String inputWinningNumbers() {
		System.out.println();
		System.out.println(INPUT_WINNING_NUMBERS);
		return Console.readLine();
	}

	/**
	 * 보너스 번호를 입력받습니다.
	 *
	 * @return 사용자가 입력한 보너스 번호 문자열
	 */
	public String inputBonusNumber() {
		System.out.println();
		System.out.println(INPUT_BONUS_NUMBER);
		return Console.readLine();
	}
}