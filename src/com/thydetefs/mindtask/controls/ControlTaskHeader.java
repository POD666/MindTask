package com.thydetefs.mindtask.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.thydetefs.mindtask.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ControlTaskHeader extends LinearLayout
{
    TextView TaskTitle;
    RatingBar TaskRatingBar;

    public ControlTaskHeader(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.control_task_header, this);

        TaskTitle = (TextView)findViewById(R.id.TaskTitle);
        TaskRatingBar = (RatingBar)findViewById(R.id.TaskRatingBar);

        TaskRatingBar.setIsIndicator(true);
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        super.setOnClickListener(l);
    }

    public void SetData(JSONObject data)
    {
        try
        {
            TaskTitle.setText("№" + data.getString("id"));
            TaskRatingBar.setRating((float) data.getDouble("hard"));
        }
        catch(JSONException e)
        {
        }
    }
}