Please check the document folder.
1. Import the project as maven project.
2. Go to documents folder in project and execute Sponsorsdb.sql file in mysql. this will create a db with name sponsorsdb.
   Note : we have 3 triggers on candidate table
   1. To set expiration date when candidate is created.
   2. on creation of candidate record insert record into candidate_status_change_log
   3. On update of case status, set the old record to end dated in candidate_status_change_log and creaete a new record.
   
3. You can find entity relationship diagram in same document folder.
4. Import postman collections from dcoument folder.
   You need to use login api to get authentication token to access any other api.
   You can find user details from users table, Password is default "password" for all users.
5. Authorization is the header name, we need to use to access all restricted api.
