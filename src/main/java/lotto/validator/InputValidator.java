package lotto.validator;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

public class InputValidator {

	private static final int LOTTO_PRICE = 1000;
	private static final int MIN_PURCHASE_AMOUNT = 1000;
	private static final int ZERO_AMOUNT = 0;

	public static void validatePurchaseAmount(int amount) {
		validateNegative(amount);
		validateZero(amount);
		validateMinAmount(amount);
		validateDivisibleByUnit(amount);
	}

	private static void validateNegative(int amount) {
		if (amount < ZERO_AMOUNT) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 양수여야 합니다.");
		}
	}

	private static void validateZero(int amount) {
		if (amount == ZERO_AMOUNT) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
		}
	}

	private static void validateMinAmount(int amount) {
		// 1~999 사이의 금액을 검증합니다.
		if (amount > ZERO_AMOUNT && amount < MIN_PURCHASE_AMOUNT) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 이상이어야 합니다.");
		}
	}

	private static void validateDivisibleByUnit(int amount) {
		// validateMinAmount를 통과한, 1000 이상의 금액 중 단위가 맞지 않는 경우를 검증합니다.
		if (amount >= MIN_PURCHASE_AMOUNT && amount % LOTTO_PRICE != 0) {
			throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
		}
	}

	public static void validateWinningNumbers(List<Integer> numbers) {
		// Lotto 생성자에 List<Integer>를 전달하여 null, 개수, 범위, 중복 검증을 위임
		new Lotto(numbers);
	}

	public static void validateBonusNumber(Lotto winningNumbers, int bonusNumber) {
		// WinningLotto 생성자를 호출하여 범위 및 중복 검증을 위임
		new WinningLotto(winningNumbers, bonusNumber);
	}
}