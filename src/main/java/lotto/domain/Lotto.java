package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import static lotto.common.ErrorMessages.*;
import static lotto.common.LottoConstants.*;

public class Lotto {

	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		this.numbers = numbers.stream()
			.sorted()
			.collect(Collectors.toList());
	}

	private void validate(List<Integer> numbers) {
		validateNotNull(numbers);
		validateSize(numbers);
		validateDuplicate(numbers);
		validateRange(numbers);
	}

	private void validateNotNull(List<Integer> numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException(LOTTO_NUMBERS_NULL);
		}
	}

	private void validateSize(List<Integer> numbers) {
		if (numbers.size() != LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException(LOTTO_NUMBERS_SIZE);
		}
	}
	private void validateDuplicate(List<Integer> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new IllegalArgumentException(LOTTO_NUMBERS_DUPLICATE);
		}
	}

	private void validateRange(List<Integer> numbers) {
		boolean isOutOfRange = numbers.stream()
			.anyMatch(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER);

		if (isOutOfRange) {
			throw new IllegalArgumentException(LOTTO_NUMBERS_RANGE);
		}
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public int countMatches(List<Integer> winningNumbers) {
		return (int) numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	public boolean contains(int number) {
		return numbers.contains(number);
	}

	@Override
	public String toString() {
		return numbers.toString();
	}
}