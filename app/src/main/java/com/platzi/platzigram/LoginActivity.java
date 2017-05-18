package com.platzi.platzigram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.platzi.platzigram.view.ContainerActivity;
import com.platzi.platzigram.view.CreateAccountActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.donHaveAccount)
    TextView sample_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", "modificado@email.com");
        editor.putString("nombre", "Prueba");
        editor.putInt("edad", 23);
        editor.commit();

        String languaje = prefs.getString("email", "");
        int edad = prefs.getInt("edad", 0);
        System.out.println(languaje);

        ImageView img = (ImageView) findViewById(R.id.logo);
        Glide.with(this).load("https://web2.hirez.com/smite//wp-content/uploads/2017/04/1024ganeshajpg.jpg").into(img);

        //new MyHttpAsyncTask().execute("https://jsonplaceholder.typicode.com/users");

        ButterKnife.inject(this);

    }

    private class MyHttpAsyncTask extends AsyncTask <String,Void,String>{

        InputStream is = null;
        String json ="";

        @Override
        protected String doInBackground(String... urls) {
            String result="";
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet(urls[0]);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray
                        jArrayObject = new JSONArray(result);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
        }
    }

    @OnClick(R.id.donHaveAccount)
    public void showToastMessage(){
        Toast.makeText(LoginActivity.this, "This is a message from the activity", Toast.LENGTH_SHORT).show();
    }

    public void goCreateAccount (View v) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goHome (View v) {
        Intent intent2 = new Intent(this, ContainerActivity.class);
        startActivity(intent2);
    }
    public void goGoogle (View v) throws URISyntaxException {
        Intent intent3 = new Intent(Intent.getIntentOld("https://www.google.com.mx/?gfe_rd=cr&ei=fJgQWbLDNMi_-wX60K7wCw"));
        startActivity(intent3);
    }
}
