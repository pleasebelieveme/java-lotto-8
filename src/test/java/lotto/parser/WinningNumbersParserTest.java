package lotto.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersParserTest {


	@DisplayName("정상1: 쉼표로 구분된 문자열을 정수 리스트로 파싱한다")
	@Test
	void test1() {
		// Given
		String input = "1,2,3,4,5,6";

		// When
		List<Integer> result = WinningNumbersParser.parse(input);

		// Then
		assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
	}

	@DisplayName("예외1: 입력이 null이면 IllegalArgumentException 발생")
	@Test
	void test2() {
		String input = null;

		assertThatThrownBy(() -> WinningNumbersParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 당첨 번호를 입력해주세요.");
	}

	@DisplayName("예외2: 입력이 빈 문자열 또는 공백이면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {"", " "})
	void test3(String input) {
		assertThatThrownBy(() -> WinningNumbersParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 당첨 번호를 입력해주세요.");
	}

	@DisplayName("예외3: 숫자가 아닌 값이 포함되면 NumberFormatException 발생")
	@Test
	void test4() {
		String input = "1,2,a,4,5,6";

		assertThatThrownBy(() -> WinningNumbersParser.parse(input))
			.isInstanceOf(NumberFormatException.class)
			.hasMessage("[ERROR] 당첨 번호는 숫자여야 합니다.");
	}

	@DisplayName("예외4: 쉼표로 구분된 항목이 빈 값(공백 포함)이면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {"1,,3,4,5,6", "1,2, ,4,5,6", "1,2,3,4,5,6,"})
	void test6(String input) {
		assertThatThrownBy(() -> WinningNumbersParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 당첨 번호는 빈 값일 수 없습니다.");
	}

	@DisplayName("예외5: 문자열이 쉼표로 끝나는 경우 IllegalArgumentException 발생")
	@Test
	void test5() {
		String input = "1,2,3,4,5,6,";

		assertThatThrownBy(() -> WinningNumbersParser.parse(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 당첨 번호는 빈 값일 수 없습니다.");
	}
}