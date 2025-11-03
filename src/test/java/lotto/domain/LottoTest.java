package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

	@DisplayName("정상1: 로또 번호 6개를 저장한다")
	@Test
	void test1() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

		Lotto lotto = new Lotto(numbers);  // 전체 경로 제거
		assertThat(lotto.getNumbers()).hasSize(6);
		assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
	}

	@DisplayName("정상2: 로또 번호는 1~45 범위의 중복되지 않는 숫자이다")
	@Test
	void test2() {
		List<Integer> numbers = List.of(1, 15, 23, 32, 41, 45);

		Lotto lotto = new Lotto(numbers);
		assertThat(lotto.getNumbers()).allMatch(num -> num >= 1 && num <= 45);
	}

	@DisplayName("정상3: 로또 번호를 오름차순으로 정렬하여 반환한다")
	@Test
	void test3() {
		List<Integer> numbers = List.of(45, 1, 32, 15, 23, 8);

		Lotto lotto = new Lotto(numbers);
		assertThat(lotto.getNumbers()).containsExactly(1, 8, 15, 23, 32, 45);
	}

	@DisplayName("정상4: 주어진 번호 리스트와 일치하는 개수를 반환한다")
	@Test
	void test4() {
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
		List<Integer> winningNumbers = List.of(1, 2, 3, 7, 8, 9);

		int matchCount = lotto.countMatches(winningNumbers);
		assertThat(matchCount).isEqualTo(3);
	}

	@DisplayName("정상5: 일치하는 번호가 없을 때 0을 반환한다")
	@Test
	void test5() {
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
		List<Integer> winningNumbers = List.of(7, 8, 9, 10, 11, 12);

		int matchCount = lotto.countMatches(winningNumbers);
		assertThat(matchCount).isEqualTo(0);
	}

	@DisplayName("정상6: 모든 번호가 일치할 때 6을 반환한다")
	@Test
	void test6() {
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
		List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

		int matchCount = lotto.countMatches(winningNumbers);
		assertThat(matchCount).isEqualTo(6);
	}

	@DisplayName("정상7: 특정 번호 포함 여부를 확인한다 - 포함하는 경우")
	@Test
	void test7() {
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

		assertThat(lotto.contains(1)).isTrue();
		assertThat(lotto.contains(3)).isTrue();
		assertThat(lotto.contains(6)).isTrue();
	}

	@DisplayName("정상8: 특정 번호 포함 여부를 확인한다 - 포함하지 않는 경우")
	@Test
	void test8() {
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

		assertThat(lotto.contains(7)).isFalse();
		assertThat(lotto.contains(45)).isFalse();
	}

	@DisplayName("예외1: 로또 번호가 null이면 예외가 발생한다")
	@Test
	void test9() {
		assertThatThrownBy(() -> new Lotto(null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR]");
	}

	@DisplayName("예외2: 로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
	@Test
	void test10() {
		assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,6,7)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
	}

	@DisplayName("예외3: 로또 번호의 개수가 6개 미만이면 예외가 발생한다")
	@Test
	void test11() {
		assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
	}

	@Tag("error")
	@DisplayName("예외4: 로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
	@Test
	void test12() {
		assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
	}

	@DisplayName("예외5: 로또 번호가 1보다 작으면 예외가 발생한다")
	@Test
	void test13() {
		assertThatThrownBy(() -> new Lotto(List.of(0,1,2,3,4,5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
	}

	@DisplayName("예외6: 로또 번호가 45보다 크면 예외가 발생한다")
	@Test
	void test14() {
		assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,46)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
	}

	@DisplayName("예외7: 로또 번호가 음수이면 예외가 발생한다")
	@Test
	void test15() {
		assertThatThrownBy(() -> new Lotto(List.of(-1,1,2,3,4,5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
	}
}
