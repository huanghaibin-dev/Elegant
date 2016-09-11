package com.haibin.elegantproject.model;

import java.io.Serializable;

/**
 * Created by huanghaibin_dev
 * on 2016/8/17.
 */

public class BaseModel<T> implements Serializable{
    protected int Status;
    protected T Result;
    protected String Message;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isSuccess(){
        return Status == 1;
    }
}
