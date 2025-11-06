package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.parser.BonusNumberParser;
import lotto.parser.PurchaseAmountParser;
import lotto.parser.WinningNumbersParser;
import lotto.util.LottoGenerator;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

/**
 * 로또 게임의 전체 흐름을 관리하는 서비스 클래스입니다.
 */
public class LottoGameService {
	private final InputView inputView;
	private final OutputView outputView;

	public LottoGameService() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public LottoGameService(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	/**
	 * 로또 게임을 실행합니다.
	 */
	public void run() {
		int purchaseAmount = inputPurchaseAmount();
		LottoTickets lottoTickets = purchaseLottos(purchaseAmount);
		outputView.printLottoTickets(lottoTickets.getLottos());

		Lotto winningNumbers = inputWinningNumbers();
		int bonusNumber = inputBonusNumber(winningNumbers);

		WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
		LottoResult result = lottoTickets.compareWithWinningLotto(winningLotto, purchaseAmount);

		outputView.printWinningStatistics(result);
		outputView.printProfitRate(result);
	}

	/**
	 * 구매 금액을 입력받고 검증합니다.
	 * 예외 발생 시 재입력을 요청합니다.
	 */
	private int inputPurchaseAmount() {
		while (true) {
			try {
				String input = inputView.inputPurchaseAmount();
				int amount = PurchaseAmountParser.parse(input);
				InputValidator.validatePurchaseAmount(amount);
				return amount;
			} catch (IllegalArgumentException e) {
				outputView.printErrorMessage(e.getMessage());
			}
		}
	}

	/**
	 * 구매 금액만큼 로또를 자동 생성합니다.
	 */
	private LottoTickets purchaseLottos(int purchaseAmount) {
		List<Lotto> lottos = LottoGenerator.generateLottos(purchaseAmount);
		return new LottoTickets(lottos);
	}

	/**
	 * 당첨 번호를 입력받고 검증합니다.
	 * 예외 발생 시 재입력을 요청합니다.
	 */
	private Lotto inputWinningNumbers() {
		while (true) {
			try {
				String input = inputView.inputWinningNumbers();
				List<Integer> numbers = WinningNumbersParser.parse(input);
				InputValidator.validateWinningNumbers(numbers);
				return new Lotto(numbers);
			} catch (IllegalArgumentException e) {
				outputView.printErrorMessage(e.getMessage());
			}
		}
	}

	/**
	 * 보너스 번호를 입력받고 검증합니다.
	 * 예외 발생 시 재입력을 요청합니다.
	 */
	private int inputBonusNumber(Lotto winningNumbers) {
		while (true) {
			try {
				String input = inputView.inputBonusNumber();
				int bonusNumber = BonusNumberParser.parse(input);
				InputValidator.validateBonusNumber(winningNumbers, bonusNumber);
				return bonusNumber;
			} catch (IllegalArgumentException e) {
				outputView.printErrorMessage(e.getMessage());
			}
		}
	}
}