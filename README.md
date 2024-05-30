# Bus_Arrival_Info

공공데이터를 활용한 버스 도착 정보 조회 안드로이드 어플리케이션입니다.

## 프로젝트 구조

<img width="892" alt="use case diagram" src="https://github.com/jj0526/Bus_Arrival_Info/assets/117873805/c7b7f8d8-5313-4987-956c-12c969d46951">
<img width="1281" alt="class diagram" src="https://github.com/jj0526/Bus_Arrival_Info/assets/117873805/51be9849-630a-4403-8af1-60ee30ed7871">

## 주요 기능

- 현재 내 위치 gps로 받아와서 근처 정류장 찾기 </br>
- 근처 버스 정류장에서 실시간 버스 도착 정보 확인 </br>
- google map으로 현위치 알기쉽게 확인 </br>

## 동작 화면 예시
<-------------동작 예시------------------>









<------------------------------------------->

## 👥 팀원 소개

202135574 전시현 </br>

201935116 임석현 </br>

202035326 김태화 </br>

201934219 김준희 </br>


## **🖥️ Stack**
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white" height="22">

## ✔️ Branches
- main: implementation of the application

- test: testing of the application (JUnit)

## ✔️ Git Commit Message Convention

프로젝트의 커밋 메시지를 일관성 있고 명확하게 작성하기 위해 아래와 같은 구조로 커밋 메시지를 작성합니다. 이 Convention은 각각의 커밋이 어떤 작업을 수행하는지를 명확히 전달하여 협업과 버전 관리를 원활하게 합니다.

### 1. 구조

- `[타입]: 제목 (필수)`

- `[본문]: 변경 내용 (옵션)`

### 2. 타입 및 Emoji

| 타입             | emoji | 코드                      | 설명                                                                      |
| ---------------- | ----- | ------------------------- | ------------------------------------------------------------------------- |
| feat             | ✨    | `:sparkles:`              | 새로운 기능 추가                                                          |
| fix              | 🐛    | `:bug:`                   | 버그 수정                                                                 |
| design           | 📱    | `:iphone:`                | 사용자 UI 디자인 변경                                                     |
| style            | 🎨    | `:art:`                   | 코드 포맷 변경, 세미 콜론 누락, 코드 수정 없음                            |
| comment          | 💡    | `:bulb:`                  | 주석 추가 및 변경                                                         |
| docs             | ♻️    | `:recycle:`               | 프로덕션 코드 리팩토링                                                    |
| refactor         | 📝    | `:memo:`                  | 문서 수정                                                                 |
| test             | ✅    | `:white_check_mark:`      | 테스트 추가 또는 리팩토링                                                 |
| rename           | 🚚    | `:truck:`                 | 파일이나 폴더명 변경, 이동 작업만                                         |
| remove           | ➖    | `:heavy_minus_sign:`      | 파일 삭제 작업만                                                          |
| !BREAKING CHANGE | 👽️   | `:alien:`                 | 커다란 API 변경                                                           |
| !HOTFIX          | 🔥    | `:fire:`                  | 급하게 치명적인 버그 수정                                                 |
| chore            | 🏗️    | `:building_construction:` | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X) |

### 커밋 예시

✨ feat: 검색 기능 추가 #1(이슈 번호 작성)

- 변경 사항 1
- 변경 사항 2 // 본문은 필요에 따라 작성

### 주의사항

- 커밋 메시지는 명료하고 간결하게 작성하는 것이 좋습니다.

- 커밋 타입과 내용을 일광성 있게 작성하여 프로젝트의 커밋 로그를 쉽게 읽을 수 있도록 합니다.

- 이슈를 참조하는 경우 이슈 번호를 커밋 메시지에 포함시키는 것이 유용합니다. 이를 통해 이슈와 관련되 커밋을 추적할 수 있습니다.
