MSR17-CommitLoader
------------------

Gathers commit data for builds in a TravisTorrent database.


How to run
----------

1. Import `schema.sql` into a database containing TravisTorrent data utilizing the June 12th, 2016 format.
2. Create a personal access token on GitHub, with only the `public_repo` scope: https://github.com/settings/tokens
3. Run the application with the following arguments:
  1. The JDBC URL for the database (ex: `jdbc:mysql://hostname/dbname`)
  2. Database username
  3. Database password
  4. GitHub access token
  5. Comma-separated list of repositories (in the data) to fetch commit data for (ex: `user/repo,user/repo`)

(Note: It will likely be easiest to run the application from your IDE, which should automatically add dependencies to the classpath.)
