// var pants = 20;
// var socks = 100;
// `바지${pants} 양말${socks}`;

// function 해체분석기(문자들,변수들){
//   console.log(문자들[1]+변수들[0])
// }


var 변수 = '손흥민';

function 해체분석기(문자들, 변수들){
  console.log(문자들[1] + 변수들);
}

해체분석기`안녕하세요 ${변수} 입니다`;
console.log(문자들);