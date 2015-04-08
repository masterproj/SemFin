package rub.masterprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private StringBuilder m = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText user = (EditText) findViewById(R.id.editText1);
		final EditText password = (EditText) findViewById(R.id.editText2);
		final Button loginButton = (Button) findViewById(R.id.button1);
		final TextView content = (TextView) findViewById(R.id.textView3);

		loginButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String request = "nick=" + user.getText().toString() + "&password=" + password.getText().toString();
				new RequestTask().execute("http://masterproj.bplaced.net/cms_login.php", request);
			}
		});
	}
	
	private class RequestTask extends AsyncTask<String, Void, Void>
	{
		private String response = "FEHLER";
		
		protected void onPreExecute()
		{
            Toast.makeText(getBaseContext(), "Verbindung zum Server wird hergestellt!", Toast.LENGTH_SHORT).show();
        }
		
		@Override
		protected Void doInBackground(String... params)
		{
			try
			{
				response = sendPost(params[0], params[1]);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(Void unused)
		{
			TextView c = (TextView) findViewById(R.id.textView3);
			Toast.makeText(getBaseContext(), "Verbindung zum Server hergestellt!", Toast.LENGTH_SHORT).show();
			c.setText(response);
		}
	}

	// HTTP POST request
	private String sendPost(String url, String urlParameters) throws Exception
	{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
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
		con.disconnect();

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
