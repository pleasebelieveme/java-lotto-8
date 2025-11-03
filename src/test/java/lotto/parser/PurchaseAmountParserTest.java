package lotto.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountParserTest {

	@DisplayName("정상1: 유효한 문자열 금액을 정수로 파싱한다")
	@Test
	void test1() {
		// Given
		String input = "8000";

		// When
		int result = PurchaseAmountParser.parse(input);

		// Then
		assertThat(result).isEqualTo(8000);
	}

	@DisplayName("예외1: 입력이 null이면 IllegalArgumentException 발생")
	@Test
	void test2() {
		String input = null;

		assertThatThrownBy(() -> PurchaseAmountParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액을 입력해주세요.");
	}

	@Tag("error")
	@DisplayName("예외2: 입력이 빈 문자열 또는 공백이면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {"", " "})
	void test3(String input) {
		assertThatThrownBy(() -> PurchaseAmountParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구입 금액을 입력해주세요.");
	}

	@Tag("error")
	@DisplayName("예외3: 숫자가 아닌 문자가 포함되면 NumberFormatException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {"1000원", "a1000", "1,000"})
	void test4(String input) {
		assertThatThrownBy(() -> PurchaseAmountParser.parse(input))
			.isInstanceOf(NumberFormatException.class)
			.hasMessage("[ERROR] 구입 금액은 숫자여야 합니다.");
	}
}