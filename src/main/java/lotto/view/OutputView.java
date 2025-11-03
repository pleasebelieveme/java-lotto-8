package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {
	private static final String NEW_LINE = "";
	private static final String PURCHASED_LOTTO_COUNT_FORMAT = "%d개를 구매했습니다.";
	private static final String WINNING_STATISTICS_HEADER = "당첨 통계";
	private static final String SEPARATOR = "---";
	private static final String RANK_FORMAT = "%s (%s원) - %d개";
	private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
	private static final String ERROR_PREFIX = "[ERROR] ";
	private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");

	/**
	 * 5.4 구매한 로또 출력
	 * 각 로또 번호는 오름차순으로 정렬되어 출력됨 (Lotto 객체에서 정렬 보장)
	 */
	public void printLottoTickets(List<Lotto> lottos) {
		System.out.println(NEW_LINE);
		System.out.println(String.format(PURCHASED_LOTTO_COUNT_FORMAT, lottos.size()));
		lottos.stream()
			.map(Lotto::toString) // Lotto 클래스에 toString()이 구현되어 있다고 가정
			.forEach(System.out::println);
	}

	/**
	 * 5.5 당첨 통계 출력
	 * 5등부터 1등까지 순서대로 출력
	 */
	public void printWinningStatistics(LottoResult lottoResult) {
		System.out.println(NEW_LINE);
		System.out.println(WINNING_STATISTICS_HEADER);
		System.out.println(SEPARATOR);

		// 5등, 4등, 3등, 2등, 1등 순서로 출력
		Stream.of(Rank.values())
			.filter(Rank::isWinning)
			.sorted(Comparator.comparingInt(Rank::getPrize)) // 상금 기준 오름차순 정렬 (5등 -> 1등)
			.forEach(rank -> printRank(rank, lottoResult));
	}

	private void printRank(Rank rank, LottoResult lottoResult) {
		String prize = MONEY_FORMAT.format(rank.getPrize());
		String description = rank.getDescription(); // LottoResultTest.java의 Rank Enum에 getDescription()이 있다고 가정

		System.out.println(String.format(
			RANK_FORMAT,
			description,
			prize,
			lottoResult.getPrizeCount(rank)
		));
	}

	/**
	 * 5.6 수익률 출력
	 * 소수점 둘째 자리에서 반올림 (%.1f 사용)
	 */
	public void printProfitRate(LottoResult lottoResult) {
		String profitRate = lottoResult.calculateProfitRate();
		System.out.println(String.format(PROFIT_RATE_FORMAT, profitRate));
	}

	/**
	 * 5.7 에러 메시지 출력
	 */
	public void printErrorMessage(String message) {
		System.out.println(ERROR_PREFIX + message);
	}
}
