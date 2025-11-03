package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

	@DisplayName("1: 6개 일치하면 1등")
	@Test
	void test1() {
		Rank rank = Rank.valueOf(6, false);

		assertThat(rank).isEqualTo(Rank.FIRST);
		assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
	}

	@DisplayName("2: 5개 일치하고 보너스볼 일치하면 2등")
	@Test
	void test2() {
		Rank rank = Rank.valueOf(5, true);

		assertThat(rank).isEqualTo(Rank.SECOND);
		assertThat(rank.getPrize()).isEqualTo(30_000_000);
	}

	@DisplayName("3: 5개 일치하고 보너스볼 불일치하면 3등")
	@Test
	void test3() {
		Rank rank = Rank.valueOf(5, false);

		assertThat(rank).isEqualTo(Rank.THIRD);
		assertThat(rank.getPrize()).isEqualTo(1_500_000);
	}

	@DisplayName("4: 4개 일치하면 4등")
	@Test
	void test4() {
		Rank rank = Rank.valueOf(4, false);

		assertThat(rank).isEqualTo(Rank.FOURTH);
		assertThat(rank.getPrize()).isEqualTo(50_000);
	}

	@DisplayName("5: 3개 일치하면 5등")
	@Test
	void test5() {
		Rank rank = Rank.valueOf(3, false);

		assertThat(rank).isEqualTo(Rank.FIFTH);
		assertThat(rank.getPrize()).isEqualTo(5_000);
	}

	@DisplayName("6: 2개 이하 일치하면 NONE")
	@ParameterizedTest
	@CsvSource({"0, false", "1, false", "2, false"})
	void test6(int matchCount, boolean matchBonus) {
		Rank rank = Rank.valueOf(matchCount, matchBonus);

		assertThat(rank).isEqualTo(Rank.NONE);
		assertThat(rank.getPrize()).isEqualTo(0);
	}

	@DisplayName("7: 당첨 여부 확인")
	@Test
	void test7() {
		assertThat(Rank.FIRST.isWinning()).isTrue();
		assertThat(Rank.SECOND.isWinning()).isTrue();
		assertThat(Rank.THIRD.isWinning()).isTrue();
		assertThat(Rank.FOURTH.isWinning()).isTrue();
		assertThat(Rank.FIFTH.isWinning()).isTrue();
		assertThat(Rank.NONE.isWinning()).isFalse();
	}
}