package com.example.admin.w3d2okhttpjson;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 4/13/2016.
 */
public class DuckTask extends AsyncTask<Void, Void, Void> {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    private TextView mTextView;
    private ImageView mImageView;
    private MainActivity mMainActivity;

    private String charInfo;
    private String charImg;

    public DuckTask(TextView mTv, ImageView mIv, MainActivity mMa){
        mTextView = mTv;
        mImageView = mIv;
        mMainActivity = mMa;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String getResponse;
        try {
            getResponse = doGetRequest("http://api.duckduckgo.com/?q=simpsons+characters&format=json");
            Log.d("TAG", "doInBackground: " + getResponse);
            parseJson(getResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Picasso.with(mMainActivity).load(charImg).into(mImageView);
        mTextView.setText(charInfo);
    }

    public String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void parseJson(String rawJson){
        try {
            JSONObject jsonObject = new JSONObject(rawJson);
            JSONArray jsonArray = jsonObject.getJSONArray("RelatedTopics");

            JSONObject x = jsonArray.getJSONObject(0);
            charInfo = x.getString("Text");
            JSONObject icon = x.getJSONObject("Icon");
            charImg = icon.getString("URL");



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
