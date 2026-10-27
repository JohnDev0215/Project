package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsNotification implements INotification {
    protected static final String TAG = "AbsNotification";
    private final String mJsonData;
    private String mName;
    private String mParams;

    protected abstract void parseParam(String str) throws JSONException;

    protected AbsNotification(String str) {
        this.mJsonData = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mName = jSONObject.optString("name");
            String strOptString = jSONObject.optString("params");
            this.mParams = strOptString;
            parseParam(strOptString);
        } catch (Exception e) {
            Logger.error(TAG, "parse json exception", e);
        }
    }

    @Override // cerence.ark.assistant.api.notification.INotification
    public String getName() {
        return this.mName;
    }

    @Override // cerence.ark.assistant.api.notification.INotification
    public String getParams() {
        return this.mParams;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() {
        return this.mJsonData;
    }
}
