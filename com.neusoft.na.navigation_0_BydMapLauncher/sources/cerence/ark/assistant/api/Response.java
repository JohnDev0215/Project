package cerence.ark.assistant.api;

import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class Response<T extends BaseResult> implements IResponse<T> {
    private static final String TAG = "Response";
    protected T mData;
    protected String mErrorMessage;
    protected boolean mIsSkipSnoozing = false;
    protected String mResultCode;

    public Response() {
    }

    public Response(String str) {
        this.mResultCode = str;
    }

    public Response(String str, T t) {
        this.mResultCode = str;
        this.mData = t;
    }

    public Response(String str, String str2) {
        this.mResultCode = str;
        this.mErrorMessage = str2;
    }

    public Response(String str, String str2, T t) {
        this.mResultCode = str;
        this.mErrorMessage = str2;
        this.mData = t;
    }

    @Override // cerence.ark.assistant.api.IResponse
    public void setResultCode(String str) {
        this.mResultCode = str;
    }

    @Override // cerence.ark.assistant.api.IResponse
    public void setErrorMessage(String str) {
        this.mErrorMessage = str;
    }

    @Override // cerence.ark.assistant.api.IResponse
    public void setData(T t) {
        this.mData = t;
    }

    @Override // cerence.ark.assistant.api.IResponse
    public void requestSkipSnoozing() {
        this.mIsSkipSnoozing = true;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(this.mResultCode)) {
            Logger.warn(TAG, "response to json string, the result code is empty");
        }
        jSONObject.put("result_code", this.mResultCode);
        jSONObject.put("error_msg", this.mErrorMessage);
        if (this.mIsSkipSnoozing) {
            jSONObject.put(JsonKey.RESULTS_SKIP_SNOOZING, true);
        }
        T t = this.mData;
        String jsonString = t == null ? "" : t.toJsonString();
        if (TextUtils.isEmpty(jsonString)) {
            jSONObject.put(JsonKey.RESULTS_DATA, new JSONObject());
        } else {
            jSONObject.put(JsonKey.RESULTS_DATA, new JSONObject(jsonString));
        }
        return jSONObject.toString();
    }
}
