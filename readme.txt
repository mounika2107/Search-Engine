Below are the steps for running the code
1. Download the web pages using the WebCrawler.java- 
   a. Set the input URL location in Constants.INPUT_CRAWLER_URL
   b. Set the downloaded html file location in Constants.INPUT_HTML_FILE_LOCATION
   c. Run WebCrawler.java.
 2. Convert HTML files downloaded in the previous step to text files using HTMLtoText.java. Set Constants.INTERMEDIATE_HTML_TEXT_FILE_LOC for output file location
 3. Make sure the external referenced libraries jsoup-1.8.1.jar and servlet-api.jar are configured in the build path.
 4. Install Apache Tomcat v8.0.
 5. Run index.jsp file on server.