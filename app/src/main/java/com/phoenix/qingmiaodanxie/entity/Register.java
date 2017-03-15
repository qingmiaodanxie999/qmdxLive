package com.phoenix.qingmiaodanxie.entity;

/**
 * Created by 王东 on 2017/3/15.
 */

public class Register {

    /**
     * result : true
     * error_code : 0
     */

    private boolean result;
    private int error_code;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}

