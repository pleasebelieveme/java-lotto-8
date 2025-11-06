// package lotto.service;
//
// import lotto.view.InputView;
// import lotto.view.OutputView;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
//
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.*;
//
// class LottoGameServiceWithMockitoTest {
//
// 	@DisplayName("정상1: 게임이 정상적으로 실행된다")
// 	@Test
// 	void test1() {
// 		// Given
// 		InputView inputView = mock(InputView.class);
// 		OutputView outputView = mock(OutputView.class);
//
// 		when(inputView.inputPurchaseAmount()).thenReturn("1000");
// 		when(inputView.inputWinningNumbers()).thenReturn("1,2,3,4,5,6");
// 		when(inputView.inputBonusNumber()).thenReturn("7");
//
// 		LottoGameService service = new LottoGameService(inputView, outputView);
//
// 		// When
// 		service.run();
//
// 		// Then
// 		verify(inputView, times(1)).inputPurchaseAmount();
// 		verify(inputView, times(1)).inputWinningNumbers();
// 		verify(inputView, times(1)).inputBonusNumber();
// 		verify(outputView, times(1)).printLottoTickets(any());
// 		verify(outputView, times(1)).printWinningStatistics(any());
// 		verify(outputView, times(1)).printProfitRate(any());
// 	}
//
// 	@DisplayName("예외1: 잘못된 구매 금액 입력 시 재입력을 요청한다")
// 	@Test
// 	void test2() {
// 		// Given
// 		InputView inputView = mock(InputView.class);
// 		OutputView outputView = mock(OutputView.class);
//
// 		when(inputView.inputPurchaseAmount())
// 			.thenReturn("1500")  // 첫 번째: 잘못된 입력
// 			.thenReturn("1000"); // 두 번째: 올바른 입력
// 		when(inputView.inputWinningNumbers()).thenReturn("1,2,3,4,5,6");
// 		when(inputView.inputBonusNumber()).thenReturn("7");
//
// 		LottoGameService service = new LottoGameService(inputView, outputView);
//
// 		// When
// 		service.run();
//
// 		// Then
// 		verify(inputView, times(2)).inputPurchaseAmount(); // 2번 호출
// 		verify(outputView, times(1)).printErrorMessage(any()); // 에러 메시지 1번 출력
// 	}
//
// 	@DisplayName("예외2: 잘못된 당첨 번호 입력 시 재입력을 요청한다")
// 	@Test
// 	void test3() {
// 		// Given
// 		InputView inputView = mock(InputView.class);
// 		OutputView outputView = mock(OutputView.class);
//
// 		when(inputView.inputPurchaseAmount()).thenReturn("1000");
// 		when(inputView.inputWinningNumbers())
// 			.thenReturn("1,2,3,4,5")    // 첫 번째: 5개 입력 (오류)
// 			.thenReturn("1,2,3,4,5,6"); // 두 번째: 올바른 입력
// 		when(inputView.inputBonusNumber()).thenReturn("7");
//
// 		LottoGameService service = new LottoGameService(inputView, outputView);
//
// 		// When
// 		service.run();
//
// 		// Then
// 		verify(inputView, times(2)).inputWinningNumbers(); // 2번 호출
// 		verify(outputView, times(1)).printErrorMessage(any()); // 에러 메시지 1번 출력
// 	}
//
// 	@DisplayName("예외3: 잘못된 보너스 번호 입력 시 재입력을 요청한다")
// 	@Test
// 	void test4() {
// 		// Given
// 		InputView inputView = mock(InputView.class);
// 		OutputView outputView = mock(OutputView.class);
//
// 		when(inputView.inputPurchaseAmount()).thenReturn("1000");
// 		when(inputView.inputWinningNumbers()).thenReturn("1,2,3,4,5,6");
// 		when(inputView.inputBonusNumber())
// 			.thenReturn("6")  // 첫 번째: 중복 (오류)
// 			.thenReturn("7"); // 두 번째: 올바른 입력
//
// 		LottoGameService service = new LottoGameService(inputView, outputView);
//
// 		// When
// 		service.run();
//
// 		// Then
// 		verify(inputView, times(2)).inputBonusNumber(); // 2번 호출
// 		verify(outputView, times(1)).printErrorMessage(any()); // 에러 메시지 1번 출력
// 	}
// }