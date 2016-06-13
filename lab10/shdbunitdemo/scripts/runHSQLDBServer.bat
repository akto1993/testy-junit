#!/bin/bash

rem java -cp ./scripts/hsqldb-2.3.3.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb
java -cp .\hsqldb-2.3.3.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 workdb
