package io.branch.referral;

import android.text.TextUtils;
import com.crashlytics.android.answers.shim.AnswersOptionalLogger;
import com.crashlytics.android.answers.shim.KitEvent;
import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
class ExtendedAnswerProvider {
    ExtendedAnswerProvider() {
    }

    public void provideData(String str, JSONObject jSONObject, String str2) {
        try {
            KitEvent kitEvent = new KitEvent(str);
            if (jSONObject != null) {
                addJsonObjectToKitEvent(kitEvent, jSONObject, "");
                kitEvent.putAttribute(Defines$Jsonkey.BranchIdentity.getKey(), str2);
                AnswersOptionalLogger.get().logKitEvent(kitEvent);
            }
        } catch (Throwable unused) {
        }
    }

    private void addJsonObjectToKitEvent(KitEvent kitEvent, JSONObject jSONObject, String str) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object obj = jSONObject.get(next);
            if (!next.startsWith(MqttTopic.SINGLE_LEVEL_WILDCARD)) {
                if (obj instanceof JSONObject) {
                    addJsonObjectToKitEvent(kitEvent, (JSONObject) obj, str + next + ".");
                } else if (obj instanceof JSONArray) {
                    addJsonArrayToKitEvent(kitEvent, (JSONArray) obj, next + ".");
                } else {
                    addBranchAttributes(kitEvent, str, next, jSONObject.getString(next));
                }
            }
        }
    }

    private void addJsonArrayToKitEvent(KitEvent kitEvent, JSONArray jSONArray, String str) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            addBranchAttributes(kitEvent, str, "~" + Integer.toString(i), jSONArray.getString(i));
        }
    }

    private void addBranchAttributes(KitEvent kitEvent, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        if (str2.startsWith("~")) {
            kitEvent.putAttribute(str.replaceFirst("~", "") + str2.replaceFirst("~", ""), str3);
            return;
        }
        if (str2.equals("$" + Defines$Jsonkey.IdentityID.getKey())) {
            kitEvent.putAttribute(Defines$Jsonkey.ReferringBranchIdentity.getKey(), str3);
        }
    }
}
