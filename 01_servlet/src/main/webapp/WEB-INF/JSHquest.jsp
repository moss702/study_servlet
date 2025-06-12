<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>JS & jQuery 문제셋 v2</title>
  <style>
    .highlight { background-color: yellow; }
    .bold { font-weight: bold; }
    .hidden { display: none; }
  </style>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

  <h1 id="page-title">프로그래밍 테스트</h1>

  <input type="text" id="username" placeholder="이름 입력">
  <button id="btn-greet">인사하기</button>
  <div id="greeting"></div>

  <ul id="item-list">
    <li data-price="1000">상품 A</li>
    <li data-price="2000">상품 B</li>
    <li data-price="3000">상품 C</li>
  </ul>

  <button id="btn-hide">목록 숨기기</button>
  <button id="btn-show">목록 보이기</button>

  <div id="color-box" style="width: 100px; height: 100px; background: green;"></div>
  <button id="btn-color">노란색으로 변경</button>

  <a href="https://openai.com" id="external-link">OpenAI 가기</a>

  <hr>
  <h2>정답 스크립트 입력 영역</h2>
  <script>
    /******************** JS 문제 1 ********************
    #btn-greet 클릭 시, input(#username)의 값으로
    #greeting 요소에 "안녕하세요, [이름]님!" 텍스트 출력
    ****************************************************/

    /******************** JS 문제 2 ********************
    #item-list 안의 <li> 요소 중 data-price가 2000인 항목의
    텍스트 색상을 파란색으로 변경하라.
    ****************************************************/

    /******************** JS 문제 3 ********************
    #btn-hide 클릭 시 <ul id="item-list">를 display:none 처리하라.
    ****************************************************/

    /******************** JS 문제 4 ********************
    #btn-show 클릭 시 <ul id="item-list">를 다시 표시하되,
    클래스로 'bold'를 추가하라.
    ****************************************************/

    /******************** JS 문제 5 ********************
    #external-link 클릭 시 새 창으로 열리도록 하고,
    기존 이동은 막아라.
    ****************************************************/

    /******************** jQuery 문제 1 ********************
    #btn-color 클릭 시 #color-box 배경색을 yellow로 변경하라.
    ****************************************************/

    /******************** jQuery 문제 2 ********************
    input 값이 5자 이상 입력되었을 때 #greeting에 
    "이름이 너무 깁니다" 출력
    ****************************************************/

    /******************** jQuery 문제 3 ********************
    #item-list의 <li> 요소를 클릭하면 해당 항목에
    'highlight' 클래스를 toggle 하라.
    ****************************************************/

    /******************** jQuery 문제 4 ********************
    페이지 로드 후 2초 뒤 #page-title을 슬라이드 업(slideUp)
    ****************************************************/

    /******************** jQuery 문제 5 ********************
    #item-list의 모든 li 요소의 텍스트 뒤에 가격(원)을 표시하라.
    예시: 상품 A (1000원)
    ****************************************************/
  </script>

</body>
</html>