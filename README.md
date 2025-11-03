# 🎰 로또 게임 (java-lotto-precourse)

## 📖 프로젝트 소개

> 우아한테크코스 프리코스 3주차 미션입니다.
>
> 로또 구매부터 당첨 확인까지 전체 프로세스를 구현한 콘솔 기반 로또 게임입니다.

### 주요 기능
- 💰 구매 금액 입력 및 로또 자동 발행
- 🎲 1~45 범위의 중복되지 않는 번호 6개 생성
- 🎯 당첨 번호 및 보너스 번호 입력
- 🏆 당첨 통계 및 수익률 계산
- ✅ 입력값 검증 및 예외 처리 (재시도 로직)

---

## 🛠️ 기술 스택

- **Language:** Java 21
- **Build Tool:** Gradle
- **Testing:** JUnit 5, AssertJ
- **Library:** camp.nextstep.edu.missionutils

---

## 🚀  입출력 예시

**입력:**
```
구입금액을 입력해 주세요.
8000

당첨 번호를 입력해 주세요.
1,2,3,4,5,6

보너스 번호를 입력해 주세요.
7
```

**출력:**
```
8개를 구매했습니다.
[8, 21, 23, 41, 42, 43] 
[3, 5, 11, 16, 32, 38] 
[7, 11, 16, 35, 36, 44] 
[1, 8, 11, 31, 41, 42] 
[13, 14, 16, 38, 42, 45] 
[7, 11, 30, 40, 42, 43] 
[2, 13, 22, 32, 38, 45] 
[1, 3, 5, 14, 22, 45]

당첨 통계
---
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
총 수익률은 62.5%입니다.
```

---

## 📦 패키지 구조
**레이어별 책임:**
- **Application**: 프로그램 시작점
- **Domain**: 핵심 비즈니스 로직
- **Service**: 전체 게임 흐름 조율 및 예외 재시도 처리
- **View**: 입출력 담당 (`Console.readLine()`, `System.out.println()`)
- **Parser**: 문자열 → 객체 변환
- **Validator**: 비즈니스 규칙 검증
- **Util**: 로또 번호 생성 등 유틸리티

```
src/main/java/lotto/
├── Application.java              # 프로그램 진입점
├── domain/                       # 핵심 비즈니스 로직
│   ├── Lotto.java               # 로또 번호 (6개)
│   ├── LottoTickets.java        # 여러 로또 관리
│   ├── WinningLotto.java        # 당첨 번호 + 보너스 번호
│   ├── Rank.java                # 당첨 등수 (Enum)
│   └── LottoResult.java         # 당첨 결과 및 수익률
├── service/                      # 게임 진행 조율
│   └── LottoGameService.java
├── view/                         # 입출력 처리
│   ├── InputView.java           # 사용자 입력 (Console.readLine())
│   └── OutputView.java          # 결과 출력
├── parser/                       # 문자열 파싱
│   ├── PurchaseAmountParser.java
│   ├── WinningNumbersParser.java
│   └── BonusNumberParser.java
├── validator/                    # 입력 검증
│   └── InputValidator.java
└── util/                         # 유틸리티
    └── LottoGenerator.java       # 로또 번호 생성
```

---

## ⚙️ 기능 구현 목록

### 1. 도메인 객체 구현

#### 1.1 Lotto 객체 구현

**정상 케이스:**
- [ ] 6개의 로또 번호를 저장한다
- [ ] 로또 번호는 1~45 범위의 중복되지 않는 숫자이다
- [ ] 로또 번호를 오름차순으로 정렬하여 반환한다
- [ ] 주어진 번호 리스트와 일치하는 개수를 반환한다
- [ ] 특정 번호 포함 여부를 확인한다

**예외 케이스:**
- [ ] 로또 번호가 null이면 `IllegalArgumentException` 발생
    - `[ERROR] 로또 번호를 입력해주세요.`
- [ ] 로또 번호가 6개가 아니면 `IllegalArgumentException` 발생
    - `[ERROR] 로또 번호는 6개여야 합니다.`
- [ ] 중복된 번호가 있으면 `IllegalArgumentException` 발생
    - `[ERROR] 로또 번호는 중복될 수 없습니다.`
- [ ] 1~45 범위를 벗어나면 `IllegalArgumentException` 발생
    - `[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.`

**커밋:** `feat(domain): Lotto 객체 기본 기능 구현`

#### 1.2 Rank 열거형 구현

**정상 케이스:**
- [ ] 일치 개수와 보너스 일치 여부로 등수를 판별한다
- [ ] 각 등수별 상금을 반환한다
- [ ] 5등: 3개 일치 / 5,000원
- [ ] 4등: 4개 일치 / 50,000원
- [ ] 3등: 5개 일치 / 1,500,000원
- [ ] 2등: 5개 + 보너스 일치 / 30,000,000원
- [ ] 1등: 6개 일치 / 2,000,000,000원
- [ ] 당첨되지 않으면 NONE 반환

**커밋:** `feat(domain): Rank 열거형 구현`

#### 1.3 WinningLotto 객체 구현

**정상 케이스:**
- [ ] 당첨 번호 6개와 보너스 번호 1개를 저장한다
- [ ] 로또와 비교하여 등수를 판별한다
- [ ] 일치 개수를 계산한다
- [ ] 보너스 번호 일치 여부를 확인한다

**예외 케이스:**
- [ ] 당첨 번호가 null이면 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호를 입력해주세요.`
- [ ] 보너스 번호가 당첨 번호와 중복되면 `IllegalArgumentException` 발생
    - `[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.`
- [ ] 보너스 번호가 1~45 범위를 벗어나면 `IllegalArgumentException` 발생
    - `[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.`

**커밋:** `feat(domain): WinningLotto 객체 구현`

#### 1.4 LottoTickets 객체 구현

**정상 케이스:**
- [ ] 여러 개의 로또를 관리한다
- [ ] 구매한 로또 개수를 반환한다
- [ ] 모든 로또를 반환한다
- [ ] 당첨 번호와 비교하여 각 로또의 등수를 판별한다

**예외 케이스:**
- [ ] 로또 리스트가 null이면 `IllegalArgumentException` 발생
    - `[ERROR] 로또 목록이 없습니다.`
- [ ] 로또 리스트가 비어있으면 `IllegalArgumentException` 발생
    - `[ERROR] 최소 1개 이상의 로또를 구매해야 합니다.`

**커밋:** `feat(domain): LottoTickets 객체 구현`

#### 1.5 LottoResult 객체 구현

**정상 케이스:**
- [ ] 등수별 당첨 개수를 저장한다
- [ ] 총 수익금을 계산한다
- [ ] 수익률을 계산한다 (소수점 둘째 자리 반올림)
- [ ] 등수별 당첨 개수를 반환한다

**예외 케이스:**
- [ ] 구매 금액이 0 이하면 `IllegalArgumentException` 발생
    - `[ERROR] 구매 금액은 0보다 커야 합니다.`

**커밋:** `feat(domain): LottoResult 객체 구현`

---

### 2. 입력 파싱 구현

#### 2.1 PurchaseAmountParser 구현

**정상 케이스:**
- [ ] 문자열을 정수로 변환한다
- [ ] 앞뒤 공백을 제거한다

**예외 케이스:**
- [ ] 입력이 null이면 `IllegalArgumentException` 발생
    - `[ERROR] 구입 금액을 입력해주세요.`
- [ ] 입력이 빈 문자열이면 `IllegalArgumentException` 발생
    - `[ERROR] 구입 금액을 입력해주세요.`
- [ ] 숫자가 아닌 문자가 포함되면 `NumberFormatException` 발생
    - `[ERROR] 구입 금액은 숫자여야 합니다.`

**커밋:** `feat(parser): 구매 금액 파싱 구현`

#### 2.2 WinningNumbersParser 구현

**정상 케이스:**
- [ ] 쉼표 기준으로 문자열을 분리한다
- [ ] 각 번호의 앞뒤 공백을 제거한다
- [ ] 문자열 리스트를 정수 리스트로 변환한다

**예외 케이스:**
- [ ] 입력이 null이면 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호를 입력해주세요.`
- [ ] 입력이 빈 문자열이면 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호를 입력해주세요.`
- [ ] 쉼표로 구분되지 않은 경우 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호는 쉼표(,)로 구분해야 합니다.`
- [ ] 숫자가 아닌 값이 포함되면 `NumberFormatException` 발생
    - `[ERROR] 당첨 번호는 숫자여야 합니다.`
- [ ] 공백만 있는 번호가 있으면 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호는 빈 값일 수 없습니다.`

**커밋:** `feat(parser): 당첨 번호 파싱 구현`

#### 2.3 BonusNumberParser 구현

**정상 케이스:**
- [ ] 문자열을 정수로 변환한다
- [ ] 앞뒤 공백을 제거한다

**예외 케이스:**
- [ ] 입력이 null이면 `IllegalArgumentException` 발생
    - `[ERROR] 보너스 번호를 입력해주세요.`
- [ ] 입력이 빈 문자열이면 `IllegalArgumentException` 발생
    - `[ERROR] 보너스 번호를 입력해주세요.`
- [ ] 숫자가 아닌 문자가 포함되면 `NumberFormatException` 발생
    - `[ERROR] 보너스 번호는 숫자여야 합니다.`

**커밋:** `feat(parser): 보너스 번호 파싱 구현`

---

### 3. 입력 검증 구현

#### 3.1 구매 금액 검증

**정상 케이스:**
- [ ] 1,000원 이상의 금액이면 통과
- [ ] 1,000원 단위로 나누어떨어지면 통과

**예외 케이스:**
- [ ] 음수를 입력하면 `IllegalArgumentException` 발생
    - `[ERROR] 구입 금액은 양수여야 합니다.`
- [ ] 0을 입력하면 `IllegalArgumentException` 발생
    - `[ERROR] 구입 금액은 0보다 커야 합니다.`
- [ ] 1,000원 미만이면 `IllegalArgumentException` 발생
    - `[ERROR] 구입 금액은 1,000원 이상이어야 합니다.`
- [ ] 1,000원으로 나누어떨어지지 않으면 `IllegalArgumentException` 발생
    - `[ERROR] 구입 금액은 1,000원 단위여야 합니다.`

**커밋:** `feat(validator): 구매 금액 검증 구현`

#### 3.2 당첨 번호 검증

**정상 케이스:**
- [ ] 6개의 번호면 통과
- [ ] 각 번호가 1~45 범위 내에 있으면 통과
- [ ] 중복되지 않으면 통과

**예외 케이스:**
- [ ] 번호 리스트가 null이면 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호를 입력해주세요.`
- [ ] 번호가 6개가 아니면 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호는 6개여야 합니다.`
- [ ] 1~45 범위를 벗어나면 `IllegalArgumentException` 발생
    - `[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.`
- [ ] 중복된 번호가 있으면 `IllegalArgumentException` 발생
    - `[ERROR] 당첨 번호는 중복될 수 없습니다.`

**커밋:** `feat(validator): 당첨 번호 검증 구현`

#### 3.3 보너스 번호 검증

**정상 케이스:**
- [ ] 1~45 범위의 숫자면 통과
- [ ] 당첨 번호와 중복되지 않으면 통과

**예외 케이스:**
- [ ] 1~45 범위를 벗어나면 `IllegalArgumentException` 발생
    - `[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.`
- [ ] 당첨 번호와 중복되면 `IllegalArgumentException` 발생
    - `[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.`

**커밋:** `feat(validator): 보너스 번호 검증 구현`

---

### 4. 로또 번호 생성

#### 4.1 자동 로또 번호 생성

**정상 케이스:**
- [ ] 1~45 범위에서 중복되지 않는 6개의 숫자를 생성한다
- [ ] `Randoms.pickUniqueNumbersInRange()` 사용
- [ ] 구매 금액에 맞는 개수만큼 로또를 생성한다

**예외 케이스:**
- [ ] 생성할 로또 개수가 0 이하면 `IllegalArgumentException` 발생
    - `[ERROR] 로또 개수는 1개 이상이어야 합니다.`

**커밋:** `feat(util): 로또 번호 자동 생성 구현`

---

### 5. 입출력 기능

#### 5.1 구매 금액 입력
- [ ] 입력 안내 메시지 출력: "구입금액을 입력해 주세요."
- [ ] `Console.readLine()`으로 입력 받기
- [ ] 입력받은 문자열을 그대로 반환

**커밋:** `feat(view): 구매 금액 입력 기능 구현`

#### 5.2 당첨 번호 입력
- [ ] 빈 줄 출력
- [ ] 입력 안내 메시지 출력: "당첨 번호를 입력해 주세요."
- [ ] `Console.readLine()`으로 입력 받기
- [ ] 입력받은 문자열을 그대로 반환

**커밋:** `feat(view): 당첨 번호 입력 기능 구현`

#### 5.3 보너스 번호 입력
- [ ] 빈 줄 출력
- [ ] 입력 안내 메시지 출력: "보너스 번호를 입력해 주세요."
- [ ] `Console.readLine()`으로 입력 받기
- [ ] 입력받은 문자열을 그대로 반환

**커밋:** `feat(view): 보너스 번호 입력 기능 구현`

#### 5.4 구매한 로또 출력
- [ ] 빈 줄 출력
- [ ] "n개를 구매했습니다." 출력
- [ ] 각 로또를 오름차순 정렬하여 출력
- [ ] 형식: `[번호1, 번호2, 번호3, 번호4, 번호5, 번호6]`
- [ ] 각 로또마다 줄바꿈

**커밋:** `feat(view): 구매 로또 출력 기능 구현`

#### 5.5 당첨 통계 출력
- [ ] 빈 줄 출력
- [ ] "당첨 통계" 헤더 출력
- [ ] 구분선 "---" 출력
- [ ] 5등부터 1등까지 순서대로 출력
- [ ] 형식: "n개 일치 (금액원) - m개"
- [ ] 2등: "5개 일치, 보너스 볼 일치 (금액원) - m개"
- [ ] 금액은 천 단위 쉼표 포함

**커밋:** `feat(view): 당첨 통계 출력 기능 구현`

#### 5.6 수익률 출력
- [ ] "총 수익률은 n%입니다." 형식으로 출력
- [ ] 소수점 둘째 자리에서 반올림
- [ ] 천 단위 쉼표 포함

**커밋:** `feat(view): 수익률 출력 기능 구현`

#### 5.7 에러 메시지 출력
- [ ] "[ERROR]"로 시작하는 에러 메시지 출력
- [ ] `System.out.println()` 사용

**커밋:** `feat(view): 에러 메시지 출력 기능 구현`

---

### 6. 게임 진행 서비스

#### 6.1 LottoGameService 구현
- [ ] 구매 금액 입력 및 검증 (재시도 로직)
- [ ] 로또 자동 발행
- [ ] 구매한 로또 출력
- [ ] 당첨 번호 입력 및 검증 (재시도 로직)
- [ ] 보너스 번호 입력 및 검증 (재시도 로직)
- [ ] 당첨 결과 계산
- [ ] 당첨 통계 및 수익률 출력
- [ ] 각 입력 단계에서 예외 발생 시 해당 단계부터 재시도

**재시도 흐름:**
- [ ] 구매 금액 예외 → 구매 금액 입력부터 재시도
- [ ] 당첨 번호 예외 → 당첨 번호 입력부터 재시도
- [ ] 보너스 번호 예외 → 보너스 번호 입력부터 재시도

**커밋:** `feat(service): LottoGameService 게임 진행 로직 구현`

---

### 7. 메인 실행

#### 7.1 Application 진입점
- [ ] LottoGameService 실행
- [ ] 예외는 Service 레이어에서 처리

**커밋:** `feat(app): Application 메인 실행 구현`

---

## 🎯 예외 처리 전략

### Exception 타입별 사용 기준

#### 1. `IllegalArgumentException`
**사용 시점:** 메서드에 전달된 인자가 유효하지 않을 때
- 숫자 범위 오류 (1~45 범위 벗어남)
- 개수 오류 (6개가 아님)
- 중복 오류
- 단위 오류 (1,000원 단위 아님)
- null 또는 빈 값

**예시:**
```java
if (numbers.size() != 6) {
    throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
}
```

#### 2. `NumberFormatException`
**사용 시점:** 문자열을 숫자로 변환할 수 없을 때
- 파싱 과정에서 발생
- `Integer.parseInt()` 실패 시

**예시:**
```java
try {
    return Integer.parseInt(input);
} catch (NumberFormatException e) {
    throw new NumberFormatException("[ERROR] 구입 금액은 숫자여야 합니다.");
}
```

#### 3. `IllegalStateException` (선택적 사용)
**사용 시점:** 객체의 상태가 메서드 호출에 적합하지 않을 때
- 게임이 이미 종료된 상태에서 추가 작업 시도
- 초기화되지 않은 객체 사용

**예시:**
```java
if (lottoTickets == null) {
    throw new IllegalStateException("[ERROR] 로또가 발행되지 않았습니다.");
}
```
---

## 🏗️ 아키텍처

- **레이어드 아키텍처** (Layered Architecture) 기반
- **Package by Layer** 방식
- **Inside-Out 개발** (Domain → Parser → Validator → Util → View → Service → Application)

```
┌─────────────────┐
│  Application    │ (진입점)
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│    Service      │ (조율 + 재시도 로직)
└────┬────────────┘
     │
     ├──────────┬──────────┬──────────┐
     ▼          ▼          ▼          ▼
┌─────────┐  ┌──────────┐ ┌──────────┐ ┌──────────┐
│ Domain  │  │  Parser  │ │   View   │ │   Util   │
└─────────┘  └──────────┘ └──────────┘ └──────────┘
     ▲          ▲
     │          │
┌────┴──────────┴────┐
│    Validator       │
└────────────────────┘
```

---

## ✅ 프로그래밍 요구사항 준수

- [ ] JDK 21에서 실행 가능
- [ ] indent depth 2 이하
- [ ] 3항 연산자 미사용
- [ ] else 예약어 미사용
- [ ] 함수 길이 15라인 이하
- [ ] 함수가 한 가지 일만 수행
- [ ] Java Enum 활용 (Rank)
- [ ] JUnit 5, AssertJ 테스트 작성
- [ ] `Randoms.pickUniqueNumbersInRange()` 사용
- [ ] `Console.readLine()` 사용
- [ ] 제공된 Lotto 클래스 활용 (필드 추가 불가)
- [ ] UI 로직 제외 단위 테스트 작성
- [ ] `IllegalArgumentException`, `IllegalStateException` 등 명확한 예외 타입 사용

---

## 🧪 테스트

### 테스트 구조
```
src/test/java/lotto/
├── domain/
│   ├── LottoTest.java
│   ├── RankTest.java
│   ├── WinningLottoTest.java
│   ├── LottoTicketsTest.java
│   └── LottoResultTest.java
├── parser/
│   ├── PurchaseAmountParserTest.java
│   ├── WinningNumbersParserTest.java
│   └── BonusNumberParserTest.java
├── validator/
│   └── InputValidatorTest.java
├── util/
│   └── LottoGeneratorTest.java
└── service/
    └── LottoGameServiceTest.java
```

### 테스트 작성 원칙
- [ ] 정상 케이스와 예외 케이스 모두 테스트
- [ ] 경계값 테스트 (0, 1, 45, 46 등)
- [ ] `@ParameterizedTest` 활용하여 여러 케이스 검증
- [ ] `assertThatThrownBy()` 로 예외 메시지까지 검증
- [ ] UI 로직(InputView, OutputView)은 테스트 제외

---

## 💭 회고

### 이번 주차 학습 목표
- [ ] Java Enum을 활용한 등수 관리
- [ ] 일급 컬렉션 패턴 적용 (LottoTickets, LottoResult)
- [ ] 예외 발생 시 재입력 로직 구현
- [ ] Parser 레이어 분리로 책임 명확화
- [ ] 다양한 예외 타입 활용 (IllegalArgumentException, NumberFormatException)
- [ ] 2주차 공통 피드백 반영

### 2주차 피드백 반영 사항
- [ ] 함수 분리를 통한 indent depth 관리
- [ ] Early return 패턴으로 else 제거
- [ ] 매직 넘버 상수화
- [ ] 도메인 로직과 UI 로직 분리
- [ ] 함수 길이 15라인 이하 유지

### 예상 고민 포인트
- Parser와 Validator의 경계 설정
- 재시도 로직의 중복 제거
- Enum을 활용한 등수 및 상금 관리
- 일급 컬렉션 적용 범위

---

## 📚 학습 내용

- Java Enum 활용 (상태와 행위를 함께 관리)
- 일급 컬렉션 (First-Class Collection)
- 예외 처리