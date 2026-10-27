package cerence.ark.assistant.api;

import android.text.TextUtils;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class BaseResult implements JsonCompliant {
    private String mJsonString;

    public BaseResult() {
        this.mJsonString = "{}";
    }

    public BaseResult(String str) {
        this.mJsonString = "{}";
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("The json string cannot be null");
        }
        this.mJsonString = str;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        return this.mJsonString;
    }
}
