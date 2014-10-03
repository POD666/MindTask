package com.thydetefs.mindtask.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.thydetefs.mindtask.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ControlTaskAnswerString extends LinearLayout
{
    EditText TaskAnswer;
    TextView TaskMark;


    public ControlTaskAnswerString(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.control_task_anwer_string, this);

        TaskMark = (TextView)findViewById(R.id.TaskMark);
        TaskAnswer = (EditText)findViewById(R.id.TaskAnswer);
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        Button btn;
        btn = (Button)findViewById(R.id.TaskTryAnswer);
        btn.setOnClickListener(l);
        btn = (Button)findViewById(R.id.TaskUseKey);
        btn.setOnClickListener(l);

        ImageButton ibtn;
        ibtn = (ImageButton)findViewById(R.id.TaskMarkVoteUp);
        ibtn.setOnClickListener(l);
        ibtn = (ImageButton)findViewById(R.id.TaskMarkVoteDown);
        ibtn.setOnClickListener(l);

        super.setOnClickListener(l);
    }

    public void SetData(JSONObject data)
    {
        try
        {
            int likes = (int) data.getDouble("likes");
            int dislikes = (int) data.getDouble("dislikes");
            String likes_dislikes = String.valueOf(dislikes) + "      " + String.valueOf(likes);

            TaskMark.setText(likes_dislikes);
        }
        catch(JSONException e)
        {
        }
    }
}
