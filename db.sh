#!/usr/bin/env bash
#mysqldump --compatible=no_table_options,no_field_options,no_key_options --hex-blob --skip-opt -uroot -ppassword test > ./src/main/resources/db/mysqldump.sql
#mysqldump -uroot -ppassword  test | sed '$!N;s/^\(\s*[^C].*\),\n\s*CONSTRAINT.*FOREIGN KEY.*$/\1/;P;D' | grep -v 'KEY `' > ./src/main/resources/db/mysqldump.sql
mysqldump -uroot -ppassword  test | grep -v 'KEY `' > ./src/main/resources/db/mysqldump.sql
java -jar ~/mysql2h2/mysql2h2-converter-tool-0.1-SNAPSHOT.jar ./src/main/resources/db/mysqldump.sql > ./src/main/resources/db/h2dump.sql
mysqldump -uroot -ppassword  test > ./src/main/resources/db/mysqldump.sql

git add .
