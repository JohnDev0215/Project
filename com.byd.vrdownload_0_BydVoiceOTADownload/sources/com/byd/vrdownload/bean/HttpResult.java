package com.byd.vrdownload.bean;

import com.byd.vrdownload.model.Result;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class HttpResult<T> {
    String code;
    T data;
    String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public boolean isSuccess() {
        return Objects.equals(this.code, "0");
    }

    public Result<T> getResult() {
        if (isSuccess()) {
            return Result.success(this.data);
        }
        return HttpResultHandler.getResult(this);
    }

    public String toString() {
        String string;
        T t = this.data;
        if (t != null && (t instanceof List)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ((List) this.data).size(); i++) {
                sb.append(((List) this.data).get(i));
            }
            string = sb.toString();
        } else {
            string = t == null ? "" : t.toString();
        }
        return "HttpResult{data=" + string + ", code='" + this.code + "', msg='" + this.msg + "'}";
    }
}
