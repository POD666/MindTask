package com.thydetefs.mindtask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.thydetefs.mindtask.controls.ControlTask;
import com.thydetefs.mindtask.system.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivitySolve extends MyActivity  implements OnClickListener
{
    ControlTask MindTask;
    RequestType TaskType;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);

        String key = getIntent().getStringExtra("key"); //if it's a string you stored.

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Home");

        if(key.equals("new"))
            TaskType = RequestType.get_question_new;
        else if(key.equals("best"))
            TaskType = RequestType.get_question_top;

        //RequestMaster requestMaster = new RequestMaster(this);
        //requestMaster.execute(TaskType, new RequestParamMaker()
        //        .put(RequestParamType.USER_ID, String.valueOf(LoginStat.USER_ID))
        //        .get());

        ControlTask MindTask = (ControlTask)findViewById(R.id.MindTask);
        MindTask.setOnClickListener(this);
        MindTask.setVisibility(View.GONE);

        /*
        RequestMaster requestMaster = new RequestMaster(getTHIS());
        requestMaster.execute(TaskType, new RequestParamMaker()
                .put(RequestParamType.USER_ID, String.valueOf(LoginStat.USER_ID))
                .get());
                        */
        String JSON = "{\"username\": \"detonavomek\", \"votes\": 0, \"question\": \"qwes5\", \"hard\": 2.5, \"dislikes\": 0.0, \"likes\": 2.0, \"answer\": \"answ5\"}";
        try
        {

            JSONObject Question = new JSONObject(JSON);
            MindTask.SetData(Question);
            ((TextView)findViewById(R.id.tmp_error_msg)).setVisibility(View.GONE);
        }catch (JSONException e){}


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_btn_menu, menu);

        if(!LoginStat.AUTHORIZED)
        {
            menu.findItem(R.id.action_add_favorite).setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_add_favorite:
                item.setIcon(R.drawable.btn_favorite_true);
                break;
            case R.id.action_skip_task:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.skip)
                        .setMessage(R.string.really_skip)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                RequestMaster requestMaster = new RequestMaster(getTHIS());
                                requestMaster.execute(TaskType, new RequestParamMaker()
                                        .put(RequestParamType.USER_ID, String.valueOf(LoginStat.USER_ID))
                                        .get());
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();

                break;
            case android.R.id.home:
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

                break;
        }

        return true;
    }

    @Override
    public void DialogCallback(JSONObject data) throws JSONException
    {
        if(data == null)
        {

        }
        else
        {
            JSONArray QuestionArray = data.getJSONArray("questions");
            JSONObject Question = QuestionArray.getJSONObject(0);
            MindTask.SetData(Question);
            ((TextView)findViewById(R.id.tmp_error_msg)).setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
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
    public void onClick(View v)
    {
        int id = v.getId();

        switch(id)
        {
            case R.id.TaskTryAnswer:
                Toast.makeText(this, "TryAnswer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.TaskUseKey:
                if(LoginStat.AUTHORIZED)
                    Toast.makeText(this, "UseKey", Toast.LENGTH_SHORT).show();
                else
                {
                    new DialogLogin(this).show(getFragmentManager(), "login");
                }


                break;
            case R.id.TaskMarkVoteUp:
                Toast.makeText(this, "VoteUp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.TaskMarkVoteDown:
                Toast.makeText(this, "VoteDown", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}