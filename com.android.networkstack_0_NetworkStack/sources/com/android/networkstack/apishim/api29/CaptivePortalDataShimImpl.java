package com.android.networkstack.apishim.api29;

import com.android.networkstack.apishim.common.CaptivePortalDataShim;
import com.android.networkstack.apishim.common.UnsupportedApiLevelException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class CaptivePortalDataShimImpl implements CaptivePortalDataShim {
    public static boolean isSupported() {
        return false;
    }

    protected CaptivePortalDataShimImpl() {
    }

    public static CaptivePortalDataShim fromJson(JSONObject jSONObject) throws JSONException, UnsupportedApiLevelException {
        throw new UnsupportedApiLevelException("CaptivePortalData not supported on API 29");
    }
}
