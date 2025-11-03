package lotto.service;

import lotto.domain.*;
import lotto.parser.BonusNumberParser;
import lotto.parser.PurchaseAmountParser;
import lotto.parser.WinningNumbersParser;
import lotto.util.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGameService {
	private final InputView inputView;
	private final OutputView outputView;
	private final LottoGenerator lottoGenerator;

	public LottoGameService(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.lottoGenerator = lottoGenerator;
	}

	/**
	 * 6.1 LottoGameService 구현
	 */
	public void run() {
		try {
			int purchaseAmount = inputPurchaseAmount();
			LottoTickets lottoTickets = buyLottos(purchaseAmount);

			outputView.printLottoTickets(lottoTickets.getLottos());

			WinningLotto winningLotto = inputWinningLotto();

			LottoResult lottoResult = calculateResult(lottoTickets, winningLotto, purchaseAmount);

			outputView.printWinningStatistics(lottoResult);
			outputView.printProfitRate(lottoResult);
		} catch (Exception e) {
			outputView.printErrorMessage(e.getMessage());
			// 애플리케이션 종료 (여기서 예외를 잡으면 메인에서 추가 처리가 필요 없음)
		}
	}

	// region 1. 구매 금액 입력 및 로또 구매
	private int inputPurchaseAmount() {
		while (true) {
			try {
				String input = inputView.inputPurchaseAmount();
				int amount = PurchaseAmountParser.parse(input);
				if (amount % LottoGenerator.LOTTO_PRICE != 0) {
					throw new IllegalArgumentException("구입 금액은 " + LottoGenerator.LOTTO_PRICE + "원 단위여야 합니다.");
				}
				return amount;
			} catch (IllegalArgumentException e) {
				outputView.printErrorMessage(e.getMessage());
			}
		}
	}

	private LottoTickets buyLottos(int purchaseAmount) {
		int lottoCount = purchaseAmount / LottoGenerator.LOTTO_PRICE;
		List<Lotto> lottos = lottoGenerator.generateLottos(lottoCount);
		return new LottoTickets(lottos);
	}
	// endregion

	// region 2. 당첨 번호, 보너스 번호 입력
	private WinningLotto inputWinningLotto() {
		Lotto winningNumbers = inputWinningNumbers();
		while (true) {
			try {
				int bonusNumber = inputBonusNumber();
				return new WinningLotto(winningNumbers, bonusNumber);
			} catch (IllegalArgumentException e) {
				outputView.printErrorMessage(e.getMessage());
			}
		}
	}

	private Lotto inputWinningNumbers() {
		while (true) {
			try {
				String input = inputView.inputWinningNumbers();
				List<Integer> numbers = WinningNumbersParser.parse(input);
				return new Lotto(numbers);
			} catch (IllegalArgumentException e) {
				outputView.printErrorMessage(e.getMessage());
			}
		}
	}

	private int inputBonusNumber() {
		while (true) {
			try {
				String input = inputView.inputBonusNumber();
				return BonusNumberParser.parse(input);
			} catch (IllegalArgumentException e) {
				outputView.printErrorMessage(e.getMessage());
				// 이 내부 while 문은 WinningLotto 생성 중 발생하는 예외 처리용.
				// WinningLotto의 생성자에서 WinningNumber와의 중복 검증도 수행되므로,
				// InputView에서 받은 문자열을 숫자로 파싱하는 예외만 여기서 잡고,
				// WinningLotto 관련 예외는 inputWinningLotto()의 바깥 while문에서 잡음
				// (요구사항: 보너스 번호 입력 예외 → 보너스 번호 입력부터 재시도)
				// LottoGameService의 inputWinningLotto() 메서드의 while(true) 루프가
				// 이 요구사항을 만족시키도록 구현됨.
			}
		}
	}
	// endregion

	// region 3. 결과 계산 및 출력
	private LottoResult calculateResult(LottoTickets lottoTickets, WinningLotto winningLotto, int purchaseAmount) {
		List<Rank> ranks = lottoTickets.getLottos().stream()
			.map(winningLotto::match)
			.collect(Collectors.toList());
		return new LottoResult(ranks, purchaseAmount);
	}
	// endregion
}
