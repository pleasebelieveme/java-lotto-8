package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OutputViewTest {

	private OutputView outputView;
	private ByteArrayOutputStream outputStream;
	private PrintStream originalOut;

	@BeforeEach
	void setUp() {
		outputView = new OutputView();
		outputStream = new ByteArrayOutputStream();
		originalOut = System.out;
		System.setOut(new PrintStream(outputStream));
	}

	@AfterEach
	void tearDown() {
		System.setOut(originalOut);
	}

	@DisplayName("정상1: 구매한 로또 목록을 출력한다")
	@Test
	void test1() {
		// Given
		List<Lotto> lottos = List.of(
			new Lotto(List.of(1, 2, 3, 4, 5, 6)),
			new Lotto(List.of(7, 8, 9, 10, 11, 12))
		);

		// When
		outputView.printLottoTickets(lottos);

		// Then
		String output = outputStream.toString();
		assertThat(output).contains("2개를 구매했습니다.");
		assertThat(output).contains("[1, 2, 3, 4, 5, 6]");
		assertThat(output).contains("[7, 8, 9, 10, 11, 12]");
	}

	@DisplayName("정상2: 당첨 통계를 출력한다")
	@Test
	void test2() {
		// Given
		List<Rank> ranks = List.of(Rank.FIFTH, Rank.THIRD);
		LottoResult lottoResult = new LottoResult(ranks, 8000);

		// When
		outputView.printWinningStatistics(lottoResult);

		// Then
		String output = outputStream.toString();
		assertThat(output).contains("당첨 통계");
		assertThat(output).contains("---");
		assertThat(output).contains("3개 일치");
		assertThat(output).contains("5개 일치");
	}

	@DisplayName("정상3: 수익률을 출력한다")
	@Test
	void test3() {
		// Given
		List<Rank> ranks = List.of(Rank.FIFTH);
		LottoResult lottoResult = new LottoResult(ranks, 8000);

		// When
		outputView.printProfitRate(lottoResult);

		// Then
		String output = outputStream.toString();
		assertThat(output).contains("총 수익률은");
		assertThat(output).contains("%입니다.");
	}

	@DisplayName("예외1: 에러 메시지를 출력한다")
	@Test
	void test4() {
		// Given
		String errorMessage = "[ERROR] 테스트 에러";

		// When
		outputView.printErrorMessage(errorMessage);

		// Then
		String output = outputStream.toString();
		assertThat(output).contains("[ERROR] 테스트 에러");
	}
}