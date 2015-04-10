package rub.masterprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//
public class MainActivity extends Activity implements TaskCompleted
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText edtxtUser = (EditText) findViewById(R.id.edtxtUser);
		final EditText edtxtPassword = (EditText) findViewById(R.id.edtxtPassword);
		final Button btnLogin = (Button) findViewById(R.id.btnLogin);
		final Button btnAccount = (Button) findViewById(R.id.btnAccount);
		
		btnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				String user = edtxtUser.getText().toString();
				String password = edtxtPassword.getText().toString();
				
				if (checkInput(user, password))
				{
				    //Neues Fenster für das User-Login erzeugen und öffnen.
					//Intent intent = new Intent(view.getContext(), LoginActivity.class);
					//view.getContext().startActivity(intent);
					String request = "nick=" + user + "&password=" + password;
					new RequestTask(MainActivity.this).execute("http://masterproj.bplaced.net/cms_login.php", request);
				}
				else
				{
					Toast.makeText(view.getContext(), "Benutzername oder Passwort ungültig.", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btnAccount.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				// Neues Fenster für das User-Login erzeugen und öffnen.
				Intent intent = new Intent(view.getContext(), AccountActivity.class);
				view.getContext().startActivity(intent);
			}
		});
	}
	
	
	//
	private boolean checkInput(String user, String password)
	{
		if (user != null && user.length() > 0)
		{
			if (password != null && password.length() > 0)
			{
				return true;
			}
		}
		return false;
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


	@Override
	public void onTaskComplete(String result)
	{
		//Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		
		// Neues Fenster für das User-Login erzeugen und öffnen.
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		intent.putExtra("RESULT", result + "\n" + Thread.currentThread().getName());
		startActivity(intent);
	}

}
