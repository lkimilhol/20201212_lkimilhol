INSERT INTO STUDENT(`NAME`, UPDATE_TIME, INSERT_TIME)
VALUES
( '학생1', CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
( '학생2', CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
;

INSERT INTO SUBJECT(`NAME`)
VALUES
( '국어'),
( '수학'),
( '영어'),
( '과학'),
( '사회')
;

INSERT INTO SCORE(STUDENT_ID, SUBJECT_ID, SCORE, INSERT_TIME, UPDATE_TIME)
VALUES
    (1, 1, 100 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (1, 2, 100 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (1, 3, 100 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (1, 4, 100 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (1, 5, 100 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (2, 1, 50 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (2, 2, 50 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (2, 3, 50 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP ),
    (2, 4, 50 , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
  ;