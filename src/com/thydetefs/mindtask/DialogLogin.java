package com.thydetefs.mindtask;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.thydetefs.mindtask.system.*;

public class DialogLogin extends DialogFragment implements OnClickListener {

    final String LOG_TAG = "myLogs";

    MyActivity parent;
    View view;

    public DialogLogin(MyActivity parent)
    {
        this.parent = parent;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().setTitle("Title!");
        view = inflater.inflate(R.layout.dialog_login, null);
        view.findViewById(R.id.btnLogin).setOnClickListener(this);
        return view;
    }

    public void onClick(View v)
    {
        LoginStat.USERNAME = ((EditText) view.findViewById(R.id.in_username)).getText().toString();
        LoginStat.PASSWORD = ((EditText) view.findViewById(R.id.in_password)).getText().toString();

        RequestMaster requestMaster = new RequestMaster(parent);
        requestMaster.execute(RequestType.authorize, new RequestParamMaker()
                .put(RequestParamType.USERNAME, LoginStat.USERNAME)
                .put(RequestParamType.PASSWORD, LoginStat.PASSWORD)
                .get());

        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());

        dismiss();
    }

    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog)
    {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}