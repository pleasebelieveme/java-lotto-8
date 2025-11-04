package lotto.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParserUtilsTest {

	@DisplayName("정상1: 유효한 문자열은 검증을 통과한다")
	@Test
	void test1() {
		// Given
		String input = "8000";
		String errorMessage = "[ERROR] 테스트 에러";

		// When & Then
		ParserUtils.validateNotNullOrEmpty(input, errorMessage);
		// 예외가 발생하지 않으면 테스트 통과
	}

	@DisplayName("정상2: 공백이 포함된 문자열은 검증을 통과한다")
	@Test
	void test2() {
		// Given
		String input = " 8000 ";
		String errorMessage = "[ERROR] 테스트 에러";

		// When & Then
		ParserUtils.validateNotNullOrEmpty(input, errorMessage);
		// 예외가 발생하지 않으면 테스트 통과
	}

	@DisplayName("예외1: 입력이 null이면 IllegalArgumentException 발생")
	@Test
	void test3() {
		// Given
		String input = null;
		String errorMessage = "[ERROR] null 입력 에러";

		// When & Then
		assertThatThrownBy(() -> ParserUtils.validateNotNullOrEmpty(input, errorMessage))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(errorMessage);
	}

	@DisplayName("예외2: 입력이 빈 문자열이면 IllegalArgumentException 발생")
	@Test
	void test4() {
		// Given
		String input = "";
		String errorMessage = "[ERROR] 빈 문자열 에러";

		// When & Then
		assertThatThrownBy(() -> ParserUtils.validateNotNullOrEmpty(input, errorMessage))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(errorMessage);
	}

	@DisplayName("예외3: 입력이 공백만 있으면 IllegalArgumentException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {" ", "  "})
	void test5(String input) {
		// Given
		String errorMessage = "[ERROR] 공백 에러";

		// When & Then
		assertThatThrownBy(() -> ParserUtils.validateNotNullOrEmpty(input, errorMessage))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(errorMessage);
	}


	@DisplayName("정상3: 유효한 문자열을 정수로 파싱한다")
	@Test
	void test6() {
		// Given
		String input = "8000";
		String errorMessage = "[ERROR] 파싱 에러";

		// When
		int result = ParserUtils.parseToInteger(input, errorMessage);

		// Then
		assertThat(result).isEqualTo(8000);
	}

	@DisplayName("정상4: 음수 문자열을 음수 정수로 파싱한다")
	@Test
	void test7() {
		// Given
		String input = "-1000";
		String errorMessage = "[ERROR] 파싱 에러";

		// When
		int result = ParserUtils.parseToInteger(input, errorMessage);

		// Then
		assertThat(result).isEqualTo(-1000);
	}

	@DisplayName("정상5: 0을 정수로 파싱한다")
	@Test
	void test8() {
		// Given
		String input = "0";
		String errorMessage = "[ERROR] 파싱 에러";

		// When
		int result = ParserUtils.parseToInteger(input, errorMessage);

		// Then
		assertThat(result).isEqualTo(0);
	}

	@DisplayName("예외4: 숫자가 아닌 문자가 포함되면 NumberFormatException 발생")
	@ParameterizedTest(name = "입력: \"{0}\"")
	@ValueSource(strings = {"abc", "1000원", "1,000", "1.5"})
	void test9(String input) {
		// Given
		String errorMessage = "[ERROR] 숫자 형식 에러";

		// When & Then
		assertThatThrownBy(() -> ParserUtils.parseToInteger(input, errorMessage))
			.isInstanceOf(NumberFormatException.class)
			.hasMessage(errorMessage);
	}

	@DisplayName("예외5: 빈 문자열은 NumberFormatException 발생")
	@Test
	void test10() {
		// Given
		String input = "";
		String errorMessage = "[ERROR] 빈 문자열 파싱 에러";

		// When & Then
		assertThatThrownBy(() -> ParserUtils.parseToInteger(input, errorMessage))
			.isInstanceOf(NumberFormatException.class)
			.hasMessage(errorMessage);
	}

	@DisplayName("예외6: Integer 범위를 벗어나면 NumberFormatException 발생")
	@Test
	void test11() {
		// Given
		String input = "9999999999999999999"; // Integer.MAX_VALUE를 초과
		String errorMessage = "[ERROR] 범위 초과 에러";

		// When & Then
		assertThatThrownBy(() -> ParserUtils.parseToInteger(input, errorMessage))
			.isInstanceOf(NumberFormatException.class)
			.hasMessage(errorMessage);
	}
}