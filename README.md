# Medipush
<img src="https://raw.githubusercontent.com/ownit4137/medipush/master/src/main/resources/static/title.png">

## Setting up a local MEDIPUSH web server

**git, Java 11이 설치되어 있어야 합니다**

먼저 Medipush를 실행하려는 폴더로 이동하여 파일을 다운로드합니다. 

```
git clone https://github.com/ownit4137/medipush.git
```


medipush안의 폴더로 이동하고 서버를 빌드합니다.
```
cd ./medipush

./gradlew build
```

./build/libs 폴더로 이동한 후 jar 파일을 실행하여 서버를 구동합니다.

```
cd ./build/libs/

java -jar medipush-0.0.1-SNAPSHOT.jar
```

## Amazon EC2 Cloud Server

👉 [Link](http://ec2-13-124-201-28.ap-northeast-2.compute.amazonaws.com:8080/)



