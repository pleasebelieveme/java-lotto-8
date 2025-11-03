package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTickets {
	private static final int MIN_LOTTO_COUNT = 1;

	private final List<Lotto> lottos;

	public LottoTickets(List<Lotto> lottos) {
		if (Objects.isNull(lottos)) {
			throw new IllegalArgumentException("[ERROR] 로또 목록이 없습니다.");
		}

		if (lottos.size() < MIN_LOTTO_COUNT) {
			throw new IllegalArgumentException("[ERROR] 최소 1개 이상의 로또를 구매해야 합니다.");
		}

		this.lottos = lottos;
	}

	/**
	 * 구매한 로또의 개수를 반환합니다.
	 */
	public int getLottoCount() {
		return lottos.size();
	}

	/**
	 * 모든 로또를 반환합니다.
	 */
	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}

	// public LottoResult compareWithWinningLotto(WinningLotto winningLotto, int purchaseAmount) {
	// 	// 모든 로또의 당첨 등수를 계산합니다.
	// 	List<Rank> ranks = lottos.stream()
	// 		.map(winningLotto::match)
	// 		.collect(Collectors.toList());
	//
	// 	// LottoResult 객체를 생성하여 등수별 개수 및 수익률을 관리합니다.
	// 	return new LottoResult(ranks, purchaseAmount);
	// }
}