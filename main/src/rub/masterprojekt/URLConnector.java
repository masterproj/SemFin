package rub.masterprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnector
{
	// 
	public String sendPostRequest(String url, String urlParameters)
	{
		String line = "";
		StringBuffer response = new StringBuffer("");
		HttpURLConnection connection = null;
		DataOutputStream writer = null;
		BufferedReader reader = null;
		
		try
		{
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
		
			writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(urlParameters);
			writer.flush();
		
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null)
			{
				response.append(line + "\n");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (connection != null)
			{
				connection.disconnect();
			}
			
			if (writer != null)
			{
				try
				{
					writer.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return response.toString();
	}
}
