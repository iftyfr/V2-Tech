package com.example.v2tech.intefaces;

public interface OnRequestComplete {
    void onRequestSuccess(Object obj);

    void onRequestError(String errMsg);
}
