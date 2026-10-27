package com.amazon.alexa.voice.core.processor.superbowl.directives;

import com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DirectiveBaseFactory<T extends SuperbowlDirective> implements DirectiveFactory.Factory<T> {
    protected final String getMessageId(JSONObject jSONObject) throws JSONException {
        return jSONObject.getJSONObject("header").getString("messageId");
    }

    protected final String getDialogRequestId(JSONObject jSONObject) throws JSONException {
        return jSONObject.getJSONObject("header").optString("dialogRequestId", null);
    }
}
