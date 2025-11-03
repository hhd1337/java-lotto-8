# 우테코 프리코스 3주차 미션 - 로또 발권 & 당첨 확인 🎰
<br>

## 1. 구현할 기능 목록
### 1) 입력
- 구입 금액 입력 받음
- 당첨 번호 6개 입력 받음
- 보너스 번호 1개 입력 받음
### 2) 당첨 정책 작성
- 당첨 등수 별 n개 번호일치, 당첨금액 정책을 Enum으로 작성
### 3) 로또 발행 (구매)
- 발행개수 계산(구입금액/1000)
- 발행 개수만큼 로또 생성
- 각 티켓은 오름차순 정렬, 불변 상태 유지
### 4) 당첨 판정
- 각 구매 티켓과 당첨 번호의 일치 개수 계산
- 5개 일치일 경우에만 보너스 일치 여부 확인
### 5) 당첨 집계
- 등수별 당첨개수 집계
- 등수별 상금을 합산하여 총상금 계산
- 총 수익률 계산 (총 상금/투자한 구입금액)
### 6) 출력
- 구입금액 입력 메시지 출력
- n개를 구매했습니다. 출력
- 발행된 로또 번호 묶음을 한 줄에 한 묶음씩 전부 출력
- 당첨번호, 보너스 번호 입력 메시지 출력
- 당첨 통계 헤더 및 구분선 출력
- 당첨 통계 출력
- 총 수익률 출력
### 7) 입력 유효성 검증
- 잘못된 입력 시 IllegalArgumentException 발생
- 에러 메시지는 반드시 "[ERROR]"로 시작
- 에러 발생 시 해당 입력 단계부터 재입력
- 구입금액
  - 공백, 빈문자열, 비정수 예외처리
  - 1000원으로 나누어 떨어지지 않을 경우 예외처리
- 당첨번호
  - 숫자가 아닐 경우 예외처리
  - 전부 6개가 아님, 중복숫자, 1~45 밖일 경우 예외처리
- 보너스번호
  - 정수 1개 아님, 1~45범위 아님, 당첨번호와 중복일 경우 예외처리
### 8) 단위 테스트 작성
- 각 로직이 존재하는 클래스별 핵심 기능과 예외 케이스에 대한 단위 테스트 작성

<br>

## 2. 아키텍처 개요 (클래스별 책임) 
### controller
- `LottoController` : 전체 실행 과정을 관리하는 오케스트레이터 (로또 구매, 당첨번호 입력, 당첨 통계 출력)
### service
- `LottoResultCalculatingService` : 당첨 결과 계산로직을 담은 서비스 레이어
### view
- `InputView / OutputView` : 콘솔 입출력 담당
### domain
- `Lotto` : 로또 티켓 하나 (숫자 6개 한 묶음)
- `WinningLotto` : 당첨 번호 6개 + 보너스 번호 1개 묶음
- `LottoFactory` : 로또 번호 6개 랜덤 생성하여 오름차순 정렬된 티켓 발행
- `LottoRank` : 등수별 정책 ENUM (일치 개수, 보너스 필요 여부, 상금 금액)
- `LottoConstants` : 상수 정의 (상금 금액, 로또 가격, 번호 범위, 등수별 상금)
### parse
- `StringToIntegerParser` : 문자열을 정수로 변환
- `WinningNumberParser` : 쉼표로 구분된 입력을 정수 리스트로 파싱
### validate
- `PurchaseAmountValidator` : 구입금액 입력의 유효성 검증
- `LottoNumbersValidator` : 로또번호 6개의 유효성 검증
- `BonusNumberValidator` : 보너스 번호의 유효성 검증

<br>

## 3. 패키지 구조
```
src
└── main
    └── java
        └── lotto
            ├── controller
            │   └── LottoController.java
            ├── domain
            │   ├── Lotto.java
            │   ├── LottoFactory.java
            │   ├── LottoRank.java
            │   ├── WinningLotto.java
            │   └── LottoConstants.java
            ├── dto
            │   └── LottoResult.java
            ├── service
            │   └── LottoResultCalculatingService.java
            ├── support
            │   ├── parser
            │   │   ├── StringToIntegerParser.java
            │   │   └── WinningNumberParser.java
            │   └── validator
            │       ├── BonusNumberValidator.java
            │       ├── LottoNumbersValidator.java
            │       └── PurchaseAmountValidator.java
            └── view
                ├── InputView.java
                └── OutputView.java

```
