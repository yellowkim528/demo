
// 프로미스 함수(매개값을 함수로(reslove,reject))
// 프로미스 객체 : 
// 1.상태정보(pending, fulfilled, rejected) 
// 2.처리결과정보(성공,실패)
const promise = new Promise((resolve,reject)=>{
  // 비동기로직 위치하는곳
  // 비동기로직 성공하면
  if(true){
    resolve('성공한 처리결과');
  } else {
    reject('실패결과');
  };
});
// 성공한 결과는 then()메소드로 받는다. 반환값 == '프로미스 객체'
// 실패한 결과는 catch()메소드로 받음.
console.log('시작');
promise.then(res => {console.log(res); return 1})
        .finally(() => console.log('실패유무 상관없이 실행1'))
        .then(res => {console.log(res); return new Error('오류1')})
        .finally(() => console.log('실패유무 상관없이 실행2'))
        .then(res => {console.log(res); return 3})
        .then(res => {console.log(res)})
        .catch(err => console.log(err));
console.log('끝');