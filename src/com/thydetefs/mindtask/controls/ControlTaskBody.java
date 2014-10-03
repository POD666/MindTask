package com.thydetefs.mindtask.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thydetefs.mindtask.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ControlTaskBody extends LinearLayout
{
    ControlTaskAnswerString TaskAnswerControl;
    TextView TextQuestion;

    public ControlTaskBody(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.control_task_body, this);

        TaskAnswerControl = (ControlTaskAnswerString)findViewById(R.id.TaskAnswerControl);
        TextQuestion = (TextView)findViewById(R.id.TextQuestion);
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        TaskAnswerControl.setOnClickListener(l);

        super.setOnClickListener(l);
    }

    public void SetData(JSONObject data)
    {
        try
        {
            TextQuestion.setText((String) data.getString("question"));
            TaskAnswerControl.SetData(data);
        }
        catch(JSONException e)
        {
        }
    }
}