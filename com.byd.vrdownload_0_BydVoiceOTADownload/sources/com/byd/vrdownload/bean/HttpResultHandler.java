package com.byd.vrdownload.bean;

import com.byd.vrdownload.exception.NetException;
import com.byd.vrdownload.model.Result;
import java.io.InvalidObjectException;

/* JADX INFO: loaded from: classes.dex */
public class HttpResultHandler {
    public static <T> Result<T> getResult(HttpResult<T> httpResult) {
        if ("0".equals(httpResult.getCode())) {
            return Result.success(httpResult.getData());
        }
        if (httpResult.getCode() == "504") {
            return Result.fail(new InvalidObjectException("server net mask timeout"));
        }
        return Result.fail(new NetException(httpResult.getCode(), httpResult.getMsg(), getDefinedErrorMsg(httpResult.getCode())));
    }

    public static String getDefinedErrorMsg(String str) {
        str.hashCode();
        switch (str) {
            case "1":
                return "系统异常";
            case "504":
                return "连接Global服务器超时";
            case "1039":
                return "API签名丢失";
            case "1040":
                return "API签名无效";
            case "10404":
                return "apk版本号错误";
            case "10405":
                return "vin不正确";
            case "10406":
                return "xPlatform错误";
            default:
                return "未知错误";
        }
    }
}
