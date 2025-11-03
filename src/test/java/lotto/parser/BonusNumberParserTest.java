package lotto.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberParserTest {

	@Tag("normal")
	@DisplayName("정상1: 유효한 문자열 번호를 정수로 파싱한다")
	@Test
	void test1() {
		// Given
		String input = "7";

		// When
		int result = BonusNumberParser.parse(input);

		// Then
		assertThat(result).isEqualTo(7);
	}

	@Tag("normal")
	@DisplayName("정상2: 앞뒤 공백이 포함된 문자열도 정수로 파싱한다")
	@Test
	void test2() {
		// Given
		String input = " 45 ";

		// When
		int result = BonusNumberParser.parse(input);

		// Then
		assertThat(result).isEqualTo(45);
	}

	@Tag("error")
	@DisplayName("예외1: 입력이 null이면 IllegalArgumentException 발생")
	@Test
	void test3() {
		String input = null;

		assertThatThrownBy(() -> BonusNumberParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 보너스 번호를 입력해주세요.");
	}

	@Tag("error")
	@DisplayName("예외2: 입력이 빈 문자열 또는 공백이면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {"", " ", "  "})
	void test4(String input) {
		assertThatThrownBy(() -> BonusNumberParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 보너스 번호를 입력해주세요.");
	}

	@Tag("error")
	@DisplayName("예외3: 숫자가 아닌 문자가 포함되면 NumberFormatException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {"1a", "A", "7.0"})
	void test5(String input) {
		assertThatThrownBy(() -> BonusNumberParser.parse(input))
			.isInstanceOf(NumberFormatException.class)
			.hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.");
	}
}