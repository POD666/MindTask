package com.thydetefs.mindtask.system;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Dictionary;

public class RequestMaster
{
    MyActivity MyParent;
    JSONObject data;

    RequestType request;
    Dictionary<RequestParamType, String> RequestParams;

    public RequestMaster(MyActivity parent)
    {
        this.MyParent = parent;
    }

    public void execute(RequestType request, Dictionary<RequestParamType, String> params)
    {
        this.request = request;
        this.RequestParams = params;

        new RequestSender().execute();
    }

    private static String getJSONfromUri(URI uri)
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri);

        StringBuilder str = new StringBuilder();

        try
        {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200)
            { // Download OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download file..");
            }
        }
        catch (ClientProtocolException e)
        {
            Log.e("Log", "Failed to download file.. ClientProtocolException");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Log.e("Log", "Failed to download file.. IOException");
            e.printStackTrace();
        }
        return str.toString();
    }

    class RequestSender extends AsyncTask<String, Void, Void>
    {
        protected void onPreExecute() {
            super.onPreExecute();
            MyParent.showDialog(0);
        }

        @Override
        protected Void doInBackground(String... params)
        {
            String JSON = "lol";
            try
            {
                Log.e("!", "=========!doInBackground!========");
                Log.e("!", request.toString());
                URI uri = RequestLinks.Create_uri(request, RequestParams);
                Log.e("!", uri.toString());
                //JSON = getJSONfromUri(uri);

                //for(int i = 0; i < 99999; i++);

                JSON = "{\"questions\":  [{\"username\": \"detonavomek\", \"votes\": 0, \"question\": \"qwes5\", \"hard\": 2.5, \"dislikes\": 0.0, \"likes\": 2.0, \"answer\": \"answ5\"} ]}";

                Log.e("!", JSON);
                data = new JSONObject(JSON);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                data = null;
            }
            Log.e("!", "=========!end!========");

            return null;
        }

        protected void onPostExecute(Void unused)
        {
            try
            {
                MyParent.DialogCallback(data);   //data
                MyParent.dismissDialog(0);
                MyParent.removeDialog(0);
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

}
