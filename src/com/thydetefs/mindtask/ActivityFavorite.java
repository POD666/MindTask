package com.thydetefs.mindtask;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import com.thydetefs.mindtask.system.MyActivity;

public class ActivityFavorite extends MyActivity implements OnClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Home");
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
    }
}
