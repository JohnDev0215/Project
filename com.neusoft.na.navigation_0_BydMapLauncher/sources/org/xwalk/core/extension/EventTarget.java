package org.xwalk.core.extension;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class EventTarget extends BindingObject {
    private String TAG = "EventTarget";
    private Map<String, MessageInfo> mEvents = new HashMap();

    public void startEvent(String str) {
    }

    public void stopEvent(String str) {
    }

    public EventTarget() {
        this.mHandler.register("addEventListener", this);
        this.mHandler.register("removeEventListener", this);
    }

    public boolean isEventActive(String str) {
        return this.mEvents.containsKey(str);
    }

    public void dispatchEvent(String str) {
        dispatchEvent(str, null);
    }

    public void dispatchEvent(String str, JSONObject jSONObject) {
        try {
            if (!this.mEvents.containsKey(str)) {
                Log.w(this.TAG, "Attempt to dispatch to non-existing event :" + str);
                return;
            }
            MessageInfo messageInfo = this.mEvents.get(str);
            JSONArray jSONArray = new JSONArray();
            if (jSONObject != null) {
                jSONArray.put(0, jSONObject);
            }
            messageInfo.postResult(jSONArray);
        } catch (JSONException e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public void onAddEventListener(MessageInfo messageInfo) {
        try {
            String string = messageInfo.getArgs().getString(0);
            if (this.mEvents.containsKey(string)) {
                Log.w(this.TAG, "Trying to re-add the event :" + string);
            } else {
                this.mEvents.put(string, messageInfo);
                startEvent(string);
            }
        } catch (JSONException e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public void onRemoveEventListener(MessageInfo messageInfo) {
        try {
            String string = messageInfo.getArgs().getString(0);
            if (!this.mEvents.containsKey(string)) {
                Log.w(this.TAG, "Attempt to remove non-existing event :" + string);
            } else {
                stopEvent(string);
                this.mEvents.remove(string);
            }
        } catch (JSONException e) {
            Log.e(this.TAG, e.toString());
        }
    }
}
