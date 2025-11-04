package lotto.util;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.common.LottoConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

	@DisplayName("정상1: 구입 금액에 따라 정확한 개수의 로또를 생성한다")
	@ParameterizedTest(name = "구입 금액: {0}원")
	@ValueSource(ints = {1000, 5000, 10000})
	void test1(int purchaseAmount) {
		// Given & When
		List<Lotto> lottos = LottoGenerator.generateLottos(purchaseAmount);
		int expectedCount = purchaseAmount / LOTTO_PRICE;

		// Then
		assertThat(lottos).hasSize(expectedCount);
	}

	@DisplayName("정상2: 생성된 각 로또가 유효한 번호 6개를 포함하고 있는지 검증한다")
	@Test
	void test2() {
		// Given
		int purchaseAmount = 3000;

		// When
		List<Lotto> lottos = LottoGenerator.generateLottos(purchaseAmount);

		// Then
		for (Lotto lotto : lottos) {
			List<Integer> numbers = lotto.getNumbers();

			// 1. 번호 개수는 6개여야 한다.
			assertThat(numbers).hasSize(LOTTO_NUMBER_COUNT);

			// 2. 1부터 45 사이의 숫자여야 한다.
			assertThat(numbers).allMatch(number -> number >= MIN_LOTTO_NUMBER && number <= MAX_LOTTO_NUMBER);

			// 3. 중복된 숫자가 없어야 한다.
			Set<Integer> uniqueNumbers = new HashSet<>(numbers);
			assertThat(uniqueNumbers).hasSize(LOTTO_NUMBER_COUNT);

			// 4. 오름차순으로 정렬되어 있어야 한다.
			assertThat(numbers).isSorted();
		}
	}

	@DisplayName("정상3: 여러 로또 생성 시 각 로또가 서로 다른 번호 조합을 가질 가능성이 있다")
	@Test
	void test3() {
		// Given: 100개 로또 생성
		int purchaseAmount = 100_000;

		// When
		List<Lotto> lottos = LottoGenerator.generateLottos(purchaseAmount);

		// Then
		// 100개 로또가 모두 같을 확률은 거의 0이므로, 최소 2개는 다를 것으로 기대
		Set<List<Integer>> uniqueLottos = new HashSet<>();
		lottos.forEach(lotto -> uniqueLottos.add(lotto.getNumbers()));

		assertThat(uniqueLottos.size()).isGreaterThan(1);
	}
}