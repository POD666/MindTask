package com.thydetefs.mindtask.system;

import java.util.Dictionary;
import java.util.Hashtable;

public class RequestParamMaker
{
    Dictionary<RequestParamType, String> param;

    public RequestParamMaker()
    {
        param = new Hashtable<RequestParamType, String>();
    }

    public RequestParamMaker put( RequestParamType key, String value)
    {
        param.put(key, value);
        return this;
    }

    public Dictionary<RequestParamType, String> get()
    {
        return param;
    }

}
