**KITTY'S ADVENTURE**



Java Swing을 이용해 구현한 장애물 피하기 게임

키보드 방향키로 키티(Kitty)를 조작하여 좌우에서 날아오는 구름 장애물을 피해야 하며, 충돌 시 점수가 감소

프로젝트 개요
- 플랫폼: Java (Swing GUI)
- 게임 방식:
  - 키보드 화살표 키(↑ ↓ ← →)로 키티를 조작합니다.
  - 장애물은 파란 구름(Cloud)과 먹구름(anotherCloud) 두 종류로 구성되어 있으며, 오른쪽에서 왼쪽으로 날아옵니다.
  - 장애물에 부딪히면 `Remain` 점수가 1점씩 감소합니다.
  - `Remain`이 0이 되면 게임 오버.

- 레벨 선택 기능:
  - `Level1`: 파란 구름 8개 + 먹구름 4개
  - `Level2`: 파란 구름 10개 + 먹구름 6개  
    → 레벨이 올라갈수록 장애물 수가 증가하여 난이도가 상승합니다.

기능 구성

구성 요소             설명                               
`startButton`         게임 시작 버튼                      
`stopButton`          게임 종료 버튼                      
`levelComboBox`       레벨 선택 콤보박스                  
`cloudImage`          파란 구름 이미지                    
`anotherCloudImage`   먹구름 이미지                       
`kittyImage`          플레이어 캐릭터 키티 이미지         
`GamebackgroundImage` 게임 배경 이미지                 
`Remain`              생명 수치 (장애물 충돌 시 감소)     


구현 방법

- GUI 구성: `JFrame`, `JPanel`, `JButton`, `JComboBox` 등 Swing 컴포넌트 사용
- 게임 흐름 제어:
  - `panel1`: 메인 화면 (레벨 선택, 시작/종료 버튼 포함)
  - `panel2`: 게임 화면 (구름 이동 및 충돌 감지)
  - 버튼 클릭 시 `CardLayout`으로 화면 전환
- 키보드 이벤트 처리: `KeyListener` 인터페이스 활용
- 이미지 처리: `ImageIcon`을 이용한 키티, 배경, 장애물 구성
- 충돌 판정: `Rectangle` 객체로 충돌 감지 → `Remain` 감소 및 장애물 제거
- 게임 종료 처리: `Remain`이 0이 되면 게임 오버 메시지 출력 및 타이머 정지
