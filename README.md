# Gun Gang
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/4fc0b39b-d185-4019-a8af-ea35d3bc3c83" alt="온보딩" width="500" height="500">
</p>

## 소개
GunGang은 카이스트 주변에서 친구와 함께 운동하고 기록할 수 있는 앱입니다.

빡빡한 일정, 적은 운동시설, 

삭막한 몰입캠프 생활에 지친 몰캠생들의 운동을 돕는 앱

## 이런 분들께 추천드립니다!

> “헬친과 같이 운동하러 가고싶어요”
> 

> “3대 500 치는데 지금 다니는 헬스장에 원판이 부족해요”
> 

> “운동 안 하는 친구를 꼭 운동에 데려가고 싶어요"
> 

> “카이스트 근처에  운동시설이 뭐가 있는지 모르겠어요”
> 

> “ 10kg 원판 4개 올리면 40kg죠?”
>

# Outline
## 1. **"친구야"** 👬

- 운동 친구 목록
- 친구와 마지막으로 함께 운동한 날짜를 기록할 수 있습니다.
- 전화 연결 ☎️
    
    앱에서 바로 친구에게 전화할 수 있습니다.
    
    오랫동안 함께 운동하지 않은 친구에게 전화로 독촉합시다.\
  <img src="https://github.com/mummy-alive/GunGang/assets/113423544/edf4f93b-4dc5-4add-801b-3e708bff5e3f" height="160"/>

  
## 2. **"여기서"** 🏟️

- 카이스트 근처 인기 운동시설 리스트
- 운동시설을 사진으로 확인해볼 수 있습니다.
- 다양한 시설을 종목 별로 필터링해 확인할 수 있습니다.
- 이참에 새로운 종목에 도전해보아요 💪


## 3. **"운동하자"** 🏸

- 오운완 캘린더 🔥
- 나의 운동 기록을 저장할 수 있습니다.
- 기간별 운동 시간 계산, 최장 운동 지속기간 기록,동기부여가 되는 운동 명언까지!

# Team

조민주 - 이화여자대학교 컴퓨터공학전공

공진우 - 카이스트 전산학부

# Tech Stack
***Front-end - Kotlin, Jetpack Compose, View***

***IDE : Android Studio***

```
//Jetpack Compose 기존구현
Column(/*modifier*/){
	Text()
	Spacer()
	Calendar()
	Spacer()
	...
}
```


```
val dialogView = LayoutInflater.from(context)
        .inflate(R.layout
        .exercise_time_dialog, null)

val alertDialog = AlertDialog.Builder(context)
        .setView(dialogView)
        .setOnDismissListener { onDismiss() }
        .create()
```

# Workflow
***Feature Update & Bug 관리 - Github Project***
### 1. Issue 등록

### 2. Branch 및 작업

### 3. Status / Size

# Details

## Intro

<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/a6147c1c-70b5-4557-8623-4159dd17b4be" alt="앱런치" width="300" height="550">
</p>

- 가장 처음 시작 화면입니다.
- 하단의 탭바로 탭을 이동할 수 있습니다.

## Tab 1: 친구야 👬

### 연락처 목록 
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/3527cbd8-6ad8-4d79-88c4-e619ea6e02f4" alt="연락처스크롤" width="300" height="550">
</p>

- 친구들의 연락처 목록입니다.
- 친구의 이름, 전화번호, 최근 함께 운동한 날짜를 볼 수 있습니다.
- 최근 함께 운동한 날짜는 기록에 따라 다른 색으로 표시됩니다.
    - 기록 없음: 회색
    - 10일 이내: 연두색
    - 10일 이전: 빨간색

### 전화 연결 📞
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/5d99268a-b154-42c9-ad27-4eee57a8bd90" alt="연락처전화" width="300" height="550">
</p>

- 친구의 연락처를 클릭하면 휴대폰 기본 전화 앱으로 연결됩니다.
- 친구에게 함께 운동하자고 제안해봅시다.

### 최근 함께 운동한 날짜 📅
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/b919dd3e-220f-48e5-8054-ce19ec1f8c2b" alt="연락처5일전" width="200" height="350">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/4d7bf010-2496-492a-b08c-496145eec5ae" alt="연락처31일전" width="200" height="350">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/a818cb2e-a346-428b-a280-60e75361c9de" alt="연락처오늘" width="200" height="350">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/03db6983-0bcc-4d1b-b140-77b09ac792cd" alt="연락처지우기" width="200" height="350">
</p>

- **`n일전` / `기록 없음`** 텍스트를 클릭하면 화면이 나타나 최근 함께 운동한 날짜를 수정할 수 있습니다.
- 달력에서 최근 운동한 날짜를 고르면 바로 적용됩니다.
- 오늘 이후의 날짜는 아직 고를 수 없습니다.
- **`오늘`** 을 누르면 오늘로 빠르게 설정됩니다.
- **`지우기`** 를 누르면 기록을 지울 수 있습니다.

## Tab 2: 여기서 🏟️

### 홈 화면
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/c13b4280-01a3-4f71-a00c-19dc863c6c87" alt="사진스크롤" width="300" height="550">
</p>

- 홈 화면에는 앱에 등록된 카이스트 근처 인기 있는 운동 시설의 사진이 **모두** 나타납니다.
- 각 사진 위에는 시설들의 이름, 주소가 나타납니다.

### 운동 목록
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/e078012c-6314-4cc0-976f-208d0105ae10" alt="사진운동" width="300" height="550">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/e0d90d37-be71-4bbd-9af3-4348e6038652" alt="사진확대" width="300" height="550">
</p>

- 상단에 있는 바를 통해 친구와 함께 하고 싶은 운동을 선택할 수 있습니다.
- 운동을 선택하면 각 운동을 할 수 있는 카이스트 주변 운동 시설이 나타납니다.
- 시설의 사진을 클릭하면 크게 볼 수 있습니다.

## Tab 3: 운동하자 🏋️

### 오운완🔥 달력
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/d7c9d95d-9c21-4371-9bdf-af7c3cfb2810" alt="운동날짜" width="300" height="550">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/ab3bde1f-3107-48b6-9dd0-a5ecdc7d479f" alt="운동미래" width="300" height="550">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/1776a2fe-f91f-4699-b076-d6f70eed1201" alt="운동취소" width="300" height="550">
</p>

- 히트맵 달력으로 나의 운동 기록을 한 눈에 확인할 수 있습니다. 화면 선택 시 바로 오늘 날짜를
- `운동 시간 기록하기` 를 눌러 나의 운동 기록을 저장할 수 있습니다.
- 미래 날짜 선택 시, 경고창과 함께 운동 기록 저장이 취소됩니다.
- 날짜를 잘못 저장해도 바로 새로운 데이터를 저장하여 수정할 수 있습니다.
- 저장된 운동 기록은 히트맵 달력에 바로 반영됩니다.

  
### 운동기록 인증화면 & 동기부여 명언 🍀
<p align="center">
  <img src="https://github.com/mummy-alive/GunGang/assets/121531442/2872513a-c80b-4aa8-88c1-19fd67c07f53" alt="운동스크롤" width="300" height="550">
</p>

- 저장된 운동 시간 데이터를 기반으로 이번주 누적 운동시간, 이번달 누적 운동시간, 최고 연속 운동일수를 계산하여 출력합니다.
- 새로운 데이터 저장 시 수정된 기록은 바로 화면에 반영됩니다.
- 랜덤으로 출력되는 동기부여 명언을 보며 운동 슬럼프를 극복할 수 있습니다💪

# APK File
https://drive.google.com/file/d/1OJokE3yk0g5lxiVd6s-4RQ8Nr6UMpfPY/view?usp=drive_link
