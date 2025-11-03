package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	private static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
	private static final String INPUT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
	private static final String NEW_LINE = "";

	/**
	 * 5.1 구매 금액 입력
	 */
	public String inputPurchaseAmount() {
		System.out.println(INPUT_PURCHASE_AMOUNT);
		return Console.readLine();
	}

	/**
	 * 5.2 당첨 번호 입력
	 */
	public String inputWinningNumbers() {
		System.out.println(NEW_LINE);
		System.out.println(INPUT_WINNING_NUMBERS);
		return Console.readLine();
	}

	/**
	 * 5.3 보너스 번호 입력
	 */
	public String inputBonusNumber() {
		System.out.println(NEW_LINE);
		System.out.println(INPUT_BONUS_NUMBER);
		return Console.readLine();
	}
}
