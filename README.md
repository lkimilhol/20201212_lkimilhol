# 20201221 김일호님

```
학생들의 중간고사 점수를 관리하는 서버를 만드는 과제입니다.
아래 기능적 요구 사항과 비기능적 요구 사항을 잘 확인하고 제출 부탁드립니다.
```

## 기능적 요구 사항

기능적 요구 사항은 아래 내용들과 추가로 필요하다고 생각되는 기능들까지 자유롭게 개발합니다.

* 학생을 추가할 수 있다.
* 학생을 삭제할 수 있다.
* 과목을 추가할 수 있다.
* 과목을 삭제할 수 있다.
* 특정 과목에 대해 학생의 점수를 추가할 수 있다.
* 특정 과목에 대해 학생의 점수를 수정할 수 있다.
* 특정 과목에 대해 학생의 점수를 삭제할 수 있다.
* 특정 학생에 대해 평균 점수를 조회할 수 있다.
* 특정 과목에 대해 전체 학생들의 평균 점수를 조회할 수 있다.

## 비기능적 요구 사항

과제는 Spring Boot 혹은 Node.js를 사용해야만 합니다.

#### 공통

* 관계형 데이터베이스를 사용해서 개발해주세요
* REST API 형식으로 개발해주세요
* 다른 사람이 읽기 좋은 코드로 개발해주세요
* 오류 사항을 최소화하고, 기능적 예외사항에 대해 사용자가 이해하기 쉽게 처리해주세요 (ex. 존재하지 않는 학생 삭제)

#### Spring Boot 사용 시

* [Java](https://www.java.com/), [Kotlin](https://kotlinlang.org/) 중 선택
* [Spring Boot 2.x](https://spring.io/projects/spring-boot) 이상 버전 사용

#### Node.js 사용 시

* [TypeScript](https://www.typescriptlang.org/) 사용하여 개발합니다.
* [Koa.js](https://koajs.com/) 혹은 [express.js](https://expressjs.com/) 사용합니다.
* Package-Manager는 [yarn](https://classic.yarnpkg.com/en/), [npm](https://www.npmjs.com/) 중 선택합니다.

### 제출 방법

* README에 실행 방법 및 코드 및 구조에 대한 개요를 포함해 주세요
* 디비 스키마에 대해서도 제출해 주세요 (ERD 혹은 DDL)

### 개발 스펙
* Spring Framework 2.4.1
* JDK 11
* Mysql 5.7


### 실행 방법
```
1. gradlew build 명령어를 이용하여 build를 한다.
2. build 폴더 안의 libs 폴더로 이동한다.
3. java -jar 20201212_lkimilhol-0.0.1-SNAPSHOT.jar 로 jar 파일을 실행한다.
4. 혹은 IDE 상에서 바로 실행한다.
```

### 상세 내용
* 8080 포트를 사용합니다.
* DB 포트는 3306으로 설정하였습니다. application.yml 파일을 참고해주세요.
  (root 패스워드가 password로 기본 설정 되어 있습니다)
  
* RDS가 연결되었다면 기본 database를 create 해주세요(script/DDL.sql 파일참고).
    ```
    CREATE SCHEMA `mathFLAT` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
    ```
  
* script/datainit.sql 파일의 쿼리를 사용하여 쉽게 데이터를 넣을 수 있습니다.

