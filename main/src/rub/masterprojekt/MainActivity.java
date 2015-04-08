package rub.masterprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private final String USER_AGENT = "Mozilla/5.0";
	private StringBuilder m = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button loginButton = (Button) findViewById(R.id.button1);
		final TextView content = (TextView) findViewById(R.id.textView3);

		loginButton.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				try
				{
				    content.setText(m.append(sendPost("http://masterproj.bplaced.net/cms_login.php", "nick=seb&password=abcde")));
				}
				catch (Exception e)
				{
					content.setText(m.append("Fehler"));
				}
				//
				// BufferedReader reader = null;
				// String text = "";
				//
				// try
				// {
				// String urlParameters = "nick=seb&password=abcde";
				//
				// // Defined URL where to send data
				// URL url = new
				// URL("http://masterproj.bplaced.net/cms_login.php");
				// text += "URL erstellt";
				//
				// // Send POST data request
				// URLConnection conn = url.openConnection();
				// conn.setDoOutput(true);
				// //conn.setDoInput(true);
				// text += "Connection erstellt";
				//
				// text += "DAVOR\n";
				// text += conn.toString();
				// text += "\n";
				// OutputStreamWriter wr = new
				// OutputStreamWriter(conn.getOutputStream());
				// text += "DANACH";
				// wr.write(urlParameters);
				// wr.flush();
				// text += "Writer erstellt";
				//
				// // Get the server response
				// reader = new BufferedReader(new
				// InputStreamReader(conn.getInputStream()));
				// StringBuilder sb = new StringBuilder();
				// String line = null;
				// text += "Reader erstellt";
				//
				// // Read Server Response
				// while ((line = reader.readLine()) != null)
				// {
				// // Append server response in string
				// sb.append(line + "\n");
				// }
				//
				// text = sb.toString();
				// }
				// catch (IOException e)
				// {
				// text += "IO_";
				// }
				// catch (Exception ex)
				// {
				// text += "FEHLER1";
				// }
				// finally
				// {
				// try
				// {
				// reader.close();
				// }
				//
				// catch (Exception ex)
				// {
				// text += "FEHLER2";
				// }
				// }
				//
				// // Show response on activity
				// content.setText("Antwort:" + text);
			}
		});
	}

	// HTTP POST request
	private String sendPost(String url, String urlParameters) throws Exception
	{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// Add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput(true);
//		m.append(con.toString());
		m.append("Erstelle Stream: ");
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		m.append("DANACH");
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
			response.append("\n");
		}
		in.close();

		// Return result
		return response.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
