package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.common.LottoConstants.*;

public class LottoGenerator {

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
