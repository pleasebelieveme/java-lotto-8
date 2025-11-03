package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoResultTest {

	@DisplayName("정상1: 등수별 당첨 개수를 정확히 저장한다")
	@Test
	void test1() {
		// Given
		List<Rank> ranks = List.of(Rank.THIRD, Rank.THIRD, Rank.FIFTH, Rank.NONE);

		// When
		LottoResult result = new LottoResult(ranks, 4000);

		// Then
		assertThat(result.getPrizeCount(Rank.THIRD)).isEqualTo(2);
		assertThat(result.getPrizeCount(Rank.FIFTH)).isEqualTo(1);
		assertThat(result.getPrizeCount(Rank.FOURTH)).isEqualTo(0);
		assertThat(result.getPrizeCount(Rank.NONE)).isEqualTo(0);
	}

	@DisplayName("정상2: 총 수익금을 정확하게 계산한다")
	@Test
	void test2() {
		// 1등 1개 (20억) + 3등 1개 (150만) = 2,001,500,000
		LottoResult result = new LottoResult(List.of(Rank.FIRST, Rank.THIRD), 8000);

		long expectedProfit = 2_000_000_000L + 1_500_000L;

		assertThat(result.calculateTotalProfit()).isEqualTo(expectedProfit);
	}

	@DisplayName("정상3: 수익률 계산 (반올림 테스트 1) - 66.666..% -> 66.7%")
	@Test
	void test3() {
		// Given: 3000원 구매, 2000원 당첨
		int amount = 3000;
		int profit = 2000;

		// When
		double profitRate = (double) profit * 100 / amount;
		String formattedRate = String.format("%.1f", profitRate);

		// Then
		assertThat(formattedRate).isEqualTo("66.7");
	}

	@DisplayName("예외1: 구매 금액이 0이면 예외가 발생한다")
	@Test
	void test6() {
		assertThatThrownBy(() -> new LottoResult(List.of(Rank.FIFTH), 0))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구매 금액은 0보다 커야 합니다.");
	}

	@DisplayName("예외2: 구매 금액이 음수이면 예외가 발생한다")
	@Test
	void test7() {
		assertThatThrownBy(() -> new LottoResult(List.of(Rank.FIFTH), -1000))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 구매 금액은 0보다 커야 합니다.");
	}
}