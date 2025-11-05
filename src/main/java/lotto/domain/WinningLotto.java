package lotto.domain;

import static lotto.common.ErrorMessages.*;
import static lotto.common.LottoConstants.*;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
	private final Lotto winningNumbers;
	private final int bonusNumber;

	public WinningLotto(Lotto winningNumbers, int bonusNumber) {
		validate(winningNumbers, bonusNumber);
		this.winningNumbers = winningNumbers;
		this.bonusNumber = bonusNumber;
	}

	private void validate(Lotto winningNumbers, int bonusNumber) {
		validateNotNull(winningNumbers);
		validateBonusNumberRange(bonusNumber);
		validateDuplicateWithWinningNumbers(winningNumbers, bonusNumber);
	}

	private void validateNotNull(Lotto winningNumbers) {
		if (Objects.isNull(winningNumbers)) {
			throw new IllegalArgumentException(LOTTO_NUMBERS_NULL);
		}
	}

	private void validateBonusNumberRange(int bonusNumber) {
		if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(BONUS_NUMBER_RANGE);
		}
	}

	private void validateDuplicateWithWinningNumbers(Lotto winningNumbers, int bonusNumber) {
		if (winningNumbers.contains(bonusNumber)) {
			throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE);
		}
	}

	/**
	 * 구매한 로또와 당첨 번호를 비교하여 등수를 반환합니다.
	 */
	public Rank match(Lotto userLotto) {
		// 1. 일치하는 번호 개수 계산
		List<Integer> numbers = winningNumbers.getNumbers();
		int matchCount = userLotto.countMatches(numbers);

		// 2. 보너스 번호 일치 여부 확인
		boolean matchBonus = userLotto.contains(bonusNumber);

		// 3. Rank Enum을 통해 등수 판별
		return Rank.valueOf(matchCount, matchBonus);
	}
}