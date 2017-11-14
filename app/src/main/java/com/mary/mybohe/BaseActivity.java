package com.mary.mybohe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mary.mybohe.util.StatusBarUtil;

/**
 * Created on 17-11-13.
 */
public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.immersive(BaseActivity.this.getWindow(), getColor(R.color.colorMain),1.0f);
    }
}
