package rub.masterprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class LoginActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		TextView txtView = (TextView) findViewById(R.id.txtView);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		if (bundle != null)
		{
			String result = bundle.getString("RESULT");
			txtView.setText(result);
		}
	}
}
