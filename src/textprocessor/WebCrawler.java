package textprocessor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    private HashSet<String> links;
    ArrayList<String> ar= new ArrayList<String>(10);
  

    public WebCrawler() {
        links = new HashSet<String>();
    }

    public void getPageLinks(String URL) {
        if (!links.contains(URL)) {
            try {
        
                if (links.add(URL)) {
                    System.out.println(URL);
                    ar.add(URL);
                    int i = 1;
                    i = i + ar.indexOf(URL);
                    String s = Constants.INPUT_HTML_FILE_LOCATION + Integer.toString(i);
                    String str = URL;
                    saveUrl(s,str); 
                }
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
    
    public void saveUrl(final String filename, final String urlString)
            throws MalformedURLException, IOException {
    	{ 
            try { 
      
             
                URL url = new URL(urlString); 
                BufferedReader readr =  
                  new BufferedReader(new InputStreamReader(url.openStream())); 
      
             
                String str = filename+".html";
                BufferedWriter writer =  
                  new BufferedWriter(new FileWriter(str)); 
                  
             
                String line; 
                while ((line = readr.readLine()) != null) { 
                    writer.write(line); 
                } 
      
                readr.close(); 
                writer.close(); 
                System.out.println("Successfully Downloaded."); 
            } 
      
             
            catch (MalformedURLException mue) { 
                System.out.println("Malformed URL Exception raised"); 
            } 
            catch (IOException ie) { 
                System.out.println("IOException raised"); 
            } 
        } 
    }
 
    	public static void main(String[] args) {
        
        new WebCrawler().getPageLinks(Constants.INPUT_CRAWLER_URL);
    }

}