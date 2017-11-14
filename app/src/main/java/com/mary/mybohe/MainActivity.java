package com.mary.mybohe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private LinearLayout bottomHealth,bottomFind,bottomStore,bottomMe,fragmentcontainer;
    private ImageView bottomHealthIv,bottomFindIv,bottomStoreIv,bottomMeIv,bottomAddIv;
    private TextView bottomHealthTv,bottomFindTv,bottomStoreTv,bottomMeTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bottomHealth = (LinearLayout) findViewById(R.id.bottom_health);
        bottomHealth.setOnClickListener(this);
        bottomFind = (LinearLayout) findViewById(R.id.bottom_find);
        bottomFind.setOnClickListener(this);
        bottomStore = (LinearLayout) findViewById(R.id.bottom_store);
        bottomStore.setOnClickListener(this);
        bottomMe = (LinearLayout) findViewById(R.id.bottom_me);
        bottomMe.setOnClickListener(this);

        bottomHealthTv = (TextView) findViewById(R.id.bottom_health_tv);
        bottomFindTv = (TextView) findViewById(R.id.bottom_find_tv);
        bottomStoreTv = (TextView) findViewById(R.id.bottom_store_tv);
        bottomMeTv = (TextView) findViewById(R.id.bottom_me_tv);

        bottomHealthIv = (ImageView) findViewById(R.id.bottom_health_iv);
        bottomFindIv = (ImageView) findViewById(R.id.bottom_find_iv);
        bottomStoreIv = (ImageView) findViewById(R.id.bottom_store_iv);
        bottomMeIv = (ImageView) findViewById(R.id.bottom_me_iv);
        bottomAddIv = (ImageView) findViewById(R.id.bottom_add);
        bottomAddIv.setOnClickListener(this);

        fragmentcontainer = (LinearLayout) findViewById(R.id.fragmentcontainer);
        showFragment(new HealthFragment());
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentcontainer,fragment)
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bottom_health:
                Log.i(TAG, "onClick: health");
                changeColor(R.id.bottom_health);
                showFragment(new HealthFragment());
                break;
            case R.id.bottom_find:
                Log.i(TAG, "onClick: find");
                changeColor(R.id.bottom_find);
                showFragment(new FindFragment());
                break;
            case R.id.bottom_store:
                Log.i(TAG, "onClick: store");
                changeColor(R.id.bottom_store);
                showFragment(new StoreFragment());
                break;
            case R.id.bottom_me:
                Log.i(TAG, "onClick: me");
                changeColor(R.id.bottom_me);
                showFragment(new MeFragment());
                break;
            case R.id.bottom_add:
                Log.i(TAG, "onClick: add");
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    private void changeColor(int id) {

        bottomHealthTv.setTextColor(getColor(R.color.colorGray));
        bottomFindTv.setTextColor(getColor(R.color.colorGray));
        bottomStoreTv.setTextColor(getColor(R.color.colorGray));
        bottomMeTv.setTextColor(getColor(R.color.colorGray));

        bottomHealthIv.setImageResource(R.drawable.bottom_health_gray);
        bottomFindIv.setImageResource(R.drawable.bottom_find_gray);
        bottomStoreIv.setImageResource(R.drawable.bottom_store_gray);
        bottomMeIv.setImageResource(R.drawable.bottom_me_gray);

        switch (id){
            case R.id.bottom_health:
                bottomHealthTv.setTextColor(getColor(R.color.colorMain));
                bottomHealthIv.setImageResource(R.drawable.bottom_health_green);
                break;
            case R.id.bottom_find:
                bottomFindTv.setTextColor(getColor(R.color.colorMain));
                bottomFindIv.setImageResource(R.drawable.bottom_find_green);
                break;
            case R.id.bottom_store:
                bottomStoreTv.setTextColor(getColor(R.color.colorMain));
                bottomStoreIv.setImageResource(R.drawable.bottom_store_green);
                break;
            case R.id.bottom_me:
                bottomMeTv.setTextColor(getColor(R.color.colorMain));
                bottomMeIv.setImageResource(R.drawable.bottom_me_green);
                break;
            default:
                break;
        }
    }
}
