// (해체분석기`바지${pants} 양말${socks}`; 이렇게 코드를 실행하면 콘솔창에 `양말20 바지100`이라는 문장이 떠야합니다.)
var pants = 20;
var socks = 100;
`바지${pants} 양말${socks}`;

function 해체분석기(문자들,변수들){
  console.log(문자들[1]+변수들[0])
}

해체분석기 

