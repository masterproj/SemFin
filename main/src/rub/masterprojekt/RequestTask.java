package rub.masterprojekt;

import android.os.AsyncTask;


public class RequestTask extends AsyncTask<String, String, String>
{
	private String response = "";
	private static URLConnector connector;
	TaskCompleted caller;
	
	public RequestTask(TaskCompleted caller)
	{
		this.caller = caller;
		connector = new URLConnector();
	}
	
	
	@Override
	protected void onPreExecute()
	{
        
    }
	
	
	@Override
	protected String doInBackground(String... params)
	{
		try
		{
			response = connector.sendPostRequest(params[0], params[1]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return response + "\n" + Thread.currentThread().getName();
	}
	
	
	@Override
	protected void onPostExecute(String result)
	{
		// Return the result to the caller of this task.
		caller.onTaskComplete(result);
	}
}
