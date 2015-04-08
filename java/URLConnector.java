//---------------------------------------------------------
// Imports
//---------------------------------------------------------
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
 
public class URLConnector
{ 
	//---------------------------------------------------------
	// Fields
	//---------------------------------------------------------
	private final String USER_AGENT = "Mozilla/5.0";
 
	//---------------------------------------------------------
	// Methods
	//---------------------------------------------------------
	
	//-----------------------
	// Main
	//-----------------------
	public static void main(String[] args) throws Exception
	{ 
		URLConnector http = new URLConnector();
 
		// Userinfo
		System.out.println("Testing - Send Http POST request");
		// Send request
		String res = http.sendPost("http://masterproj.bplaced.net/cms_login.php", "nick=lexi&password=klapptauch");
//		String res = http.sendPost("http://masterproj.bplaced.net/cms_login.php", "nick=seb&password=abcde");
//		String res = http.sendPost("http://masterproj.bplaced.net/cms_login.php", "nick=alex&password=12345");
		// Server response
		System.out.println(res);
	}
	//-----------------------
	// HTTP POST request
	//-----------------------
	private String sendPost(String url, String urlParameters) throws Exception
	{ 
		// Variables
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// Add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		// HTTP response infos
//		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'POST' request to URL : " + urlLogin);
//		System.out.println("Post parameters : " + urlParameters);
//		System.out.println("Response Code : " + responseCode);
 
		// Read server response
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		// Create response data
		while ((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
			response.append("\n");
		}
		in.close();
 
		// Return result
		return response.toString(); 
	} 
}