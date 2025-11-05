package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static lotto.common.ViewMessages.*;

/**
 * 게임 결과를 출력하는 View 클래스입니다.
 */
public class OutputView {
	private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");

	/**
	 * 구매한 로또 목록을 출력합니다.
	 * 각 로또 번호는 오름차순으로 정렬되어 출력됩니다.
	 *
	 * @param lottos 구매한 로또 리스트
	 */
	public void printLottoTickets(List<Lotto> lottos) {
		System.out.println();
		System.out.printf(PURCHASED_LOTTO_COUNT_FORMAT + "%n", lottos.size());
		lottos.forEach(lotto -> System.out.println(lotto.toString()));
	}

	/**
	 * 당첨 통계를 출력합니다.
	 * 5등부터 1등까지 순서대로 출력합니다.
	 *
	 * @param lottoResult 당첨 결과
	 */
	public void printWinningStatistics(LottoResult lottoResult) {
		System.out.println();
		System.out.println(WINNING_STATISTICS_HEADER);
		System.out.println(SEPARATOR);

		Stream.of(Rank.values())
			.filter(Rank::isWinning)
			.sorted(Comparator.comparingInt(Rank::getPrize))
			.forEach(rank -> printRank(rank, lottoResult));
	}

	private void printRank(Rank rank, LottoResult lottoResult) {
		String prize = MONEY_FORMAT.format(rank.getPrize());
		String description = rank.getDescription();
		int count = lottoResult.getPrizeCount(rank);

		System.out.printf(RANK_FORMAT + "%n", description, prize, count);
	}

	/**
	 * 수익률을 출력합니다.
	 * 소수점 첫째 자리까지 표시하며 반올림됩니다.
	 *
	 * @param lottoResult 당첨 결과
	 */
	public void printProfitRate(LottoResult lottoResult) {
		double profitRate = lottoResult.calculateProfitRate();
		System.out.printf(PROFIT_RATE_FORMAT + "%n", profitRate);
	}

	/**
	 * 에러 메시지를 출력합니다.
	 *
	 * @param message 에러 메시지 (이미 [ERROR] 접두사 포함)
	 */
	public void printErrorMessage(String message) {
		System.out.println(message);
	}
}