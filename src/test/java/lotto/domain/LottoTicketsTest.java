package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsTest {

	private final Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
	private final Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
	private final List<Lotto> validLottoList = List.of(lotto1, lotto2);

	@DisplayName("정상1: 여러 개의 로또를 관리하는 객체를 생성한다")
	@Test
	void test1() {
		// When
		LottoTickets lottoTickets = new LottoTickets(validLottoList);

		// Then
		assertThat(lottoTickets).isNotNull();
		assertThat(lottoTickets.getLottoCount()).isEqualTo(2);
		assertThat(lottoTickets.getLottos()).containsExactly(lotto1, lotto2);
	}

	@DisplayName("정상2: 구매한 로또 개수를 정확히 반환한다")
	@Test
	void test2() {
		// When
		LottoTickets lottoTickets = new LottoTickets(validLottoList);

		// Then
		assertThat(lottoTickets.getLottoCount()).isEqualTo(2);
	}

	@DisplayName("정상3: 당첨 번호와 비교하여 각 로또의 등수를 판별하고 LottoResult를 반환한다")
	@Test
	void test3() {
		// Given
		// lotto1: 1, 2, 3, 4, 5, 6 (당첨 번호와 6개 일치 -> 1등)
		// lotto2: 7, 8, 9, 10, 11, 12 (당첨 번호와 0개 일치 -> 꽝)
		LottoTickets lottoTickets = new LottoTickets(validLottoList);
		WinningLotto winningLotto = new WinningLotto(lotto1, 7); // 1~6 당첨, 보너스 7
		int purchaseAmount = 2000;

		// When
		LottoResult result = lottoTickets.compareWithWinningLotto(winningLotto, purchaseAmount);

		assertThat(result.getPrizeCount(Rank.FIRST)).isEqualTo(1);
		assertThat(result.getPrizeCount(Rank.NONE)).isEqualTo(0);
	}

	@DisplayName("예외1: 로또 리스트가 null이면 예외가 발생한다")
	@Test
	void test4() {
		assertThatThrownBy(() -> new LottoTickets(null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 목록이 없습니다.");
	}

	@DisplayName("예외2: 로또 리스트가 비어있으면 예외가 발생한다")
	@Test
	void test5() {
		assertThatThrownBy(() -> new LottoTickets(Collections.emptyList()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 최소 1개 이상의 로또를 구매해야 합니다.");
	}
}