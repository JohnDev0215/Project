package org.chromium.components.policy;

import android.os.Bundle;
import org.chromium.base.annotations.CalledByNative;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PolicyConverter {
    public long a;

    public PolicyConverter(long j) {
        this.a = j;
    }

    public static JSONArray a(Bundle[] bundleArr) {
        JSONArray jSONArray = new JSONArray();
        for (Bundle bundle : bundleArr) {
            jSONArray.put(b(bundle));
        }
        return jSONArray;
    }

    public static JSONObject b(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object objA = bundle.get(str);
            if (objA instanceof Bundle) {
                objA = b((Bundle) objA);
            }
            if (objA instanceof Bundle[]) {
                objA = a((Bundle[]) objA);
            }
            jSONObject.put(str, JSONObject.wrap(objA));
        }
        return jSONObject;
    }

    @CalledByNative
    public static PolicyConverter create(long j) {
        return new PolicyConverter(j);
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.a = 0L;
    }
}
