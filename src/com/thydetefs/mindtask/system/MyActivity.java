package com.thydetefs.mindtask.system;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class MyActivity extends ActionBarActivity
{
    //protected RequestMaster requestMaster;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //requestMaster = new RequestMaster(this);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading.....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        return mProgressDialog;
    }

    public void DialogCallback(JSONObject data) throws JSONException
    {

    }

    public MyActivity getTHIS()
    {
        return this;
    }


}
