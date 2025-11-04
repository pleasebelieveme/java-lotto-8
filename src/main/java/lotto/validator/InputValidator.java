package lotto.validator;

import static lotto.common.LottoConstants.*;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

public class InputValidator {

	/**
	 * 구매 금액이 유효한지 검증합니다.
	 *
	 * @param amount 구매 금액
	 * @throws IllegalArgumentException 금액이 0 이하, 1000원 미만, 1000원 단위가 아닌 경우
	 */
	public static void validatePurchaseAmount(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
		}
		if (amount < MIN_PURCHASE_AMOUNT) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 이상이어야 합니다.");
		}
		if (amount % LOTTO_PRICE != 0) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
		}
	}

	/**
	 * 당첨 번호가 유효한지 검증합니다.
	 * Lotto 객체 생성을 통해 null, 개수, 범위, 중복 검증을 수행합니다.
	 *
	 * @param numbers 당첨 번호 리스트
	 * @throws IllegalArgumentException Lotto 생성 조건을 만족하지 않는 경우
	 */
	public static void validateWinningNumbers(List<Integer> numbers) {
		new Lotto(numbers);
	}

	/**
	 * 보너스 번호가 유효한지 검증합니다.
	 * WinningLotto 객체 생성을 통해 범위 및 중복 검증을 수행합니다.
	 *
	 * @param winningNumbers 당첨 번호 Lotto 객체
	 * @param bonusNumber 보너스 번호
	 * @throws IllegalArgumentException WinningLotto 생성 조건을 만족하지 않는 경우
	 */
	public static void validateBonusNumber(Lotto winningNumbers, int bonusNumber) {
		new WinningLotto(winningNumbers, bonusNumber);
	}
}