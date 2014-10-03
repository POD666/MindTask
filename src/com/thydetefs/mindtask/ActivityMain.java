package com.thydetefs.mindtask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import com.thydetefs.mindtask.system.MyActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityMain extends MyActivity implements OnClickListener, ListView.OnItemClickListener
{
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mTitles;
    Menu menu;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btn;
        btn = (Button)findViewById(R.id.home_btn_new);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.home_btn_best);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.home_btn_random);
        btn.setOnClickListener(this);
        btn = (Button)findViewById(R.id.home_btn_favorite);
        btn.setOnClickListener(this);

        mTitles = new String[]{"1", "2"};

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mTitles));
        mDrawerList.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
        {
            public void onDrawerClosed(View view)
            {
            }

            public void onDrawerOpened(View drawerView)
            {
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        Intent myIntent = new Intent(this, ActivitySolve.class);

        String key = null;

        switch(id)
        {
            case  R.id.home_btn_new:
                key = "new";
                break;
            case  R.id.home_btn_best:
                key = "best";
                break;
            case  R.id.home_btn_random:
                //key = "random";
                break;
            case  R.id.home_btn_favorite:
                myIntent = new Intent(this, ActivityFavorite.class);
                startActivity(myIntent);
                break;

        }
        if(key != null)
        {
            myIntent.putExtra("key", key);
            startActivity(myIntent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            //Ask the user if they want to quit
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.quit)
                    .setMessage(R.string.really_quit)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        //wv.loadUrl(link + position + ".html");
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu _menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, _menu);

        menu = _menu;

        if(LoginStat.AUTHORIZED)
        {
            MenuItem LoginMenuItem = (MenuItem)menu.findItem(R.id.action_login);
            LoginMenuItem.setTitle(LoginStat.USERNAME);
        }

        return super.onCreateOptionsMenu(_menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case android.R.id.home:
                boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
                if(drawerOpen)
                    mDrawerLayout.closeDrawer(mDrawerList);
                else
                    mDrawerLayout.openDrawer(mDrawerList);
            break;

            case R.id.action_login:
                if(!LoginStat.AUTHORIZED)
                {
                    DialogLogin dialog = new DialogLogin(this);
                    dialog.show(getFragmentManager(), "login");
                }
                else
                {
                    LoginStat.AUTHORIZED = false;

                    MenuItem LoginMenuItem = (MenuItem)menu.findItem(R.id.action_login);
                    LoginMenuItem.setTitle(R.string.login);

                    Toast.makeText(this, "Login activity", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return true;
    }


    @Override
    public void DialogCallback(JSONObject data) throws JSONException
    {
         if(data == null)
         {
             new AlertDialog.Builder(this)
                     .setIcon(android.R.drawable.ic_lock_idle_alarm)
                     .setTitle(R.string.error)
                     .setMessage(R.string.error_authorization)
                     .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                     {
                         @Override
                         public void onClick(DialogInterface dialog, int which)
                         {
                             finish();
                         }
                     })
                     .show();
         }
         else
        {
            LoginStat.AUTHORIZED = true;

            LoginStat.USER_ID = data.getInt("id");

            MenuItem LoginMenuItem = (MenuItem)menu.findItem(R.id.action_login);
            LoginMenuItem.setTitle(LoginStat.USERNAME);
        }
    }
}
