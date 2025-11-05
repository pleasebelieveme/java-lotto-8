package lotto.domain;

import static lotto.common.ErrorMessages.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
	private static final int MIN_PURCHASE_AMOUNT = 1;
	private static final String PROFIT_RATE_FORMAT = "%.1f";

	private final Map<Rank, Integer> result;
	private final int purchaseAmount;

	public LottoResult(List<Rank> ranks, int purchaseAmount) {
		if (purchaseAmount < MIN_PURCHASE_AMOUNT) {
			throw new IllegalArgumentException(LOTTO_RESULT_PURCHASE_AMOUNT);
		}
		this.purchaseAmount = purchaseAmount;
		this.result = countRanks(ranks);
	}

	private Map<Rank, Integer> countRanks(List<Rank> ranks) {
		Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

		// NONE 등수를 제외한 모든 당첨 등수를 미리 0으로 초기화
		for (Rank rank : Rank.values()) {
			if (rank.isWinning()) {
				counts.put(rank, 0);
			}
		}

		// 실제 당첨 등수의 개수를 카운트
		ranks.stream()
			.filter(Rank::isWinning)
			.forEach(rank -> counts.merge(rank, 1, Integer::sum));

		return counts;
	}

	public int getPrizeCount(Rank rank) {
		return result.getOrDefault(rank, 0);
	}

	public long calculateTotalProfit() {
		return result.entrySet().stream()
			.mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
			.sum();
	}

	/**
	 * 총 수익률을 계산합니다.
	 *
	 * @return 수익률 (퍼센트, 소수점 포함)
	 */
	public double calculateProfitRate() {
		long totalProfit = calculateTotalProfit();
		return (double) totalProfit * 100 / purchaseAmount;
	}

	public Map<Rank, Integer> getResult() {
		return result;
	}

	public int getPurchaseAmount() {
		return purchaseAmount;
	}
}