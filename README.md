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

