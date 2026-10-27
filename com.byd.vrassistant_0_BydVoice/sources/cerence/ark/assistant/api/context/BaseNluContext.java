package cerence.ark.assistant.api.context;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.data.AssistantContextType;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseNluContext implements IAssistantContext {
    private static final String TAG = "BaseNluContext";

    public abstract JSONObject getNluContextJsonData() throws JSONException;

    public abstract String getNluContextJsonKey();

    @Override // cerence.ark.assistant.api.context.IAssistantContext
    public final AssistantContextType getContextType() {
        return AssistantContextType.NLU_CONTEXT;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public final String toJsonString() throws JSONException {
        String nluContextJsonKey = getNluContextJsonKey();
        if (TextUtils.isEmpty(nluContextJsonKey)) {
            Logger.error(TAG, String.format("%s: nlu context key must not be empty", "toJsonString"));
            return null;
        }
        JSONObject nluContextJsonData = getNluContextJsonData();
        if (nluContextJsonData == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(nluContextJsonKey, nluContextJsonData);
        return jSONObject.toString();
    }
}
