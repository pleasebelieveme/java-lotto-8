package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.validator.InputValidator.validatePurchaseAmount;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

	@DisplayName("정상1: 1000원 단위의 금액은 검증을 통과한다")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {1000, 8000, 10000})
	void test1(int amount) {
		assertThatNoException().isThrownBy(() -> validatePurchaseAmount(amount));
	}

	@DisplayName("예외1: 음수를 입력하면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {-1, -1000})
	void test2(int amount) {
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 양수여야 합니다.");
	}

	@DisplayName("예외2: 0을 입력하면 IllegalArgumentException 발생")
	@Test
	void test3() {
		int amount = 0;
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 0보다 커야 합니다.");
	}

	@DisplayName("예외3: 1,000원 미만(1~999)이면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {1, 500, 999})
	void test4(int amount) {
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 1,000원 이상이어야 합니다.");
	}

	@DisplayName("예외4: 1,000원으로 나누어떨어지지 않으면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: {0}")
	@ValueSource(ints = {1001, 1500, 8001})
	void test5(int amount) {
		assertThatThrownBy(() -> validatePurchaseAmount(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
	}
}