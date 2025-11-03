package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.domain.Lotto.MAX_LOTTO_NUMBER;
import static lotto.domain.Lotto.MIN_LOTTO_NUMBER;

public class LottoGenerator {
	public static final int LOTTO_PRICE = 1000;

	public static List<Lotto> generateLottos(int purchaseAmount) {
		int lottoCount = purchaseAmount / LOTTO_PRICE;
		return IntStream.range(0, lottoCount)
			.mapToObj(i -> generateOneLotto())
			.collect(Collectors.toList());
	}

	private static Lotto generateOneLotto() {
		List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
			MIN_LOTTO_NUMBER,
			MAX_LOTTO_NUMBER,
			LOTTO_NUMBER_COUNT
		);
		return new Lotto(numbers);
	}
}
