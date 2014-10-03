package com.thydetefs.mindtask.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.thydetefs.mindtask.R;
import org.json.JSONObject;

public class ControlTask extends LinearLayout
{
    ControlTaskHeader TaskHeader;
    ControlTaskBody TaskBody;
    View view;

    public ControlTask(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.control_task, this);

        TaskHeader = (ControlTaskHeader)findViewById(R.id.TaskHeader);
        TaskBody = (ControlTaskBody)findViewById(R.id.TaskBody);

    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        TaskHeader.setOnClickListener(l);
        TaskBody.setOnClickListener(l);

        super.setOnClickListener(l);
    }

    public void SetData(JSONObject data)
    {
        TaskHeader.SetData(data);
        TaskBody.SetData(data);
        this.setVisibility(VISIBLE);
    }


}