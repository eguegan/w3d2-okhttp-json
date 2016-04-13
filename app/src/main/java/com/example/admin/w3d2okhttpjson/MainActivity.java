package com.example.admin.w3d2okhttpjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mTextview = (TextView) findViewById(R.id.char_info);
        ImageView mImageView = (ImageView) findViewById(R.id.char_img);

        DuckTask duckTask = new DuckTask(mTextview, mImageView, this);
        duckTask.execute();
    }
}
