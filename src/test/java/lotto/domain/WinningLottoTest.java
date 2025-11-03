package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

	private final Lotto validWinningLottoNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

	@DisplayName("정상1: 당첨 번호 6개와 보너스 번호 1개를 저장한다")
	@Test
	void test1() {
		// Given
		int bonusNumber = 7;

		// When
		WinningLotto winningLotto = new WinningLotto(validWinningLottoNumbers, bonusNumber);

		// Then
		assertThat(winningLotto).isNotNull();
	}

	@DisplayName("정상2: 로또와 비교하여 등수를 판별한다 - 1등")
	@Test
	void test2() {
		// Given: 1, 2, 3, 4, 5, 6 (당첨 번호), 7 (보너스)
		WinningLotto winningLotto = new WinningLotto(validWinningLottoNumbers, 7);
		// When: 1, 2, 3, 4, 5, 6 (6개 일치)
		Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

		// Then
		assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.FIRST);
	}

	@DisplayName("정상3: 로또와 비교하여 등수를 판별한다 - 2등")
	@Test
	void test3() {
		// Given: 1, 2, 3, 4, 5, 6 (당첨 번호), 7 (보너스)
		WinningLotto winningLotto = new WinningLotto(validWinningLottoNumbers, 7);
		// When: 1, 2, 3, 4, 5, 7 (5개 일치, 보너스 일치)
		Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

		// Then
		assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.SECOND);
	}

	@DisplayName("정상4: 로또와 비교하여 등수를 판별한다 - 3등")
	@Test
	void test4() {
		// Given: 1, 2, 3, 4, 5, 6 (당첨 번호), 7 (보너스)
		WinningLotto winningLotto = new WinningLotto(validWinningLottoNumbers, 7);
		// When: 1, 2, 3, 4, 5, 8 (5개 일치, 보너스 불일치)
		Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

		// Then
		assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.THIRD);
	}

	@DisplayName("정상5: 로또와 비교하여 등수를 판별한다 - 5등")
	@Test
	void test5() {
		// Given: 1, 2, 3, 4, 5, 6 (당첨 번호), 7 (보너스)
		WinningLotto winningLotto = new WinningLotto(validWinningLottoNumbers, 7);
		// When: 1, 2, 3, 8, 9, 10 (3개 일치)
		Lotto userLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));

		// Then
		assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.FIFTH);
	}

	@DisplayName("정상6: 로또와 비교하여 등수를 판별한다 - 꽝 (NONE)")
	@ParameterizedTest(name = "{0}개 일치 시 NONE")
	@CsvSource({"0", "1", "2"})
	void test6(int matchCount) {
		// Given: 1, 2, 3, 4, 5, 6 (당첨 번호), 7 (보너스)
		WinningLotto winningLotto = new WinningLotto(validWinningLottoNumbers, 7);

		// When: matchCount에 맞게 로또 생성 (보너스 번호와도 불일치하게)
		List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).subList(0, matchCount);
		lottoNumbers = new java.util.ArrayList<>(lottoNumbers);
		// 나머지 번호를 7, 8, ...로 채워서 보너스 번호(7)와도 겹치지 않게 함
		for (int i = 0; lottoNumbers.size() < 6; i++) {
			lottoNumbers.add(8 + i);
		}
		Lotto userLotto = new Lotto(lottoNumbers);

		// Then
		assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.NONE);
	}

	// 예외 테스트
	@DisplayName("예외1: 당첨 번호가 null이면 IllegalArgumentException 발생")
	@Test
	void test7() {
		assertThatThrownBy(() -> new WinningLotto(null, 7))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR] 당첨 번호를 입력해주세요.");
	}

	@DisplayName("예외2: 보너스 번호가 당첨 번호와 중복되면 IllegalArgumentException 발생")
	@Test
	void test8() {
		// 당첨 번호: 1, 2, 3, 4, 5, 6
		int bonusNumber = 6;
		assertThatThrownBy(() -> new WinningLotto(validWinningLottoNumbers, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
	}

	@DisplayName("예외3: 보너스 번호가 1보다 작으면 IllegalArgumentException 발생")
	@Test
	void test9() {
		int bonusNumber = 0;
		assertThatThrownBy(() -> new WinningLotto(validWinningLottoNumbers, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
	}

	@DisplayName("예외4: 보너스 번호가 45보다 크면 IllegalArgumentException 발생")
	@Test
	void test10() {
		int bonusNumber = 46;
		assertThatThrownBy(() -> new WinningLotto(validWinningLottoNumbers, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
	}
}