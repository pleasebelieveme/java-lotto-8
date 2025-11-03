package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
	public static final int LOTTO_NUMBER_COUNT = 6;
	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;

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
			throw new IllegalArgumentException("[ERROR] 로또 번호를 입력해주세요.");
		}
	}

	private void validateSize(List<Integer> numbers) {
		if (numbers.size() != LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
		}
	}

	private void validateDuplicate(List<Integer> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
		}
	}

	private void validateRange(List<Integer> numbers) {
		boolean isOutOfRange = numbers.stream()
			.anyMatch(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER);

		if (isOutOfRange) {
			throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
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
}