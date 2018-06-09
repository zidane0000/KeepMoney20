package tw.edu.niu.keepmoney20;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class Json {

	String json_data = null;
	String result = "";
	String x = "";
	InputStream is = null;

	// 建立MySQL連線 API為php的網址位置，並用post傳送帳號、密碼、資料庫和語法
	public String parseJSON(String API, String server, String account,
			String password, String database, String sql) {

		// http post
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(API);
			nameValuePairs.add(new BasicNameValuePair("server", server));
			nameValuePairs.add(new BasicNameValuePair("account", account));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			nameValuePairs.add(new BasicNameValuePair("database", database));
			nameValuePairs.add(new BasicNameValuePair("sql", sql));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		// convert response to string
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			result = sb.toString();

		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		return result;
	}

	public String[] jsonkey(String sql, String key) {
		String[] Array = null;
		ArrayList<String> mStringList = new ArrayList<String>();

		try {
			//有{}的JSON解析方法
			// http://blog.csdn.net/shazhuzhux/article/details/6301386
			// JSONObject json=new JSONObject(result);
			// json_data = json.getString(key);
			// http://blog.yam.com/KusoDir/article/40167430
			//只有[]的解析方法
			JSONArray jArray = new JSONArray(sql);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json = jArray.getJSONObject(i);
				mStringList.add(json.getString(key));
			}
			Array = new String[mStringList.size()];
			Array = mStringList.toArray(Array);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		return Array;
	}
}
