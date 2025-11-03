package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.validator.InputValidator.*;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

class InputValidatorTest {

	@DisplayName("정상1: 1000원 단위의 금액은 검증을 통과한다")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {1000, 8000, 10000})
	void test1(int amount) {
		assertThatNoException().isThrownBy(() -> validatePurchaseAmount(amount));
	}

	@DisplayName("정상2: 유효한 6개의 당첨 번호는 검증을 통과한다")
	@Test
	void test2() {
		List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);
		assertThatNoException().isThrownBy(() -> validateWinningNumbers(validNumbers));
	}

	@DisplayName("예외1: 음수를 입력하면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {-1, -1000})
	void test3(int amount) {
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 양수여야 합니다.");
	}

	@DisplayName("예외2: 0을 입력하면 IllegalArgumentException 발생")
	@Test
	void test4() {
		int amount = 0;
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 0보다 커야 합니다.");
	}

	@DisplayName("예외3: 1,000원 미만(1~999)이면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {1, 500, 999})
	void test5(int amount) {
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 1,000원 이상이어야 합니다.");
	}

	@DisplayName("예외4: 1,000원으로 나누어떨어지지 않으면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {1001, 1500, 8001})
	void test6(int amount) {
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
	}


	@DisplayName("예외5: 번호 리스트가 null이면 IllegalArgumentException 발생")
	@Test
	void test7() {
		assertThatThrownBy(() -> validateWinningNumbers(null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호를 입력해주세요.");
	}

	@DisplayName("예외6: 번호가 6개가 아니면 IllegalArgumentException 발생")
	@Test
	void test8() {
		List<Integer> invalidSizeNumbers = List.of(1, 2, 3, 4, 5);
		assertThatThrownBy(() -> validateWinningNumbers(invalidSizeNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
	}

	@DisplayName("예외7: 1~45 범위를 벗어나면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {0, 46})
	void test9(int invalidNumber) {
		List<Integer> outOfRangeNumbers = List.of(1, 2, 3, 4, 5, invalidNumber);
		assertThatThrownBy(() -> validateWinningNumbers(outOfRangeNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
	}

	@DisplayName("예외8 중복된 번호가 있으면 IllegalArgumentException 발생")
	@Test
	void test10() {
		List<Integer> duplicateNumbers = List.of(1, 2, 3, 4, 5, 5);
		assertThatThrownBy(() -> validateWinningNumbers(duplicateNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
	}
}