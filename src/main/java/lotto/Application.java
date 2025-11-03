package lotto;

import lotto.service.LottoGameService;
import lotto.util.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
		// 의존성 주입
		InputView inputView = new InputView();
		OutputView outputView = new OutputView();
		LottoGenerator lottoGenerator = new LottoGenerator();

		LottoGameService gameService = new LottoGameService(inputView, outputView, lottoGenerator);

		// 게임 실행
		gameService.run();
	}
}
