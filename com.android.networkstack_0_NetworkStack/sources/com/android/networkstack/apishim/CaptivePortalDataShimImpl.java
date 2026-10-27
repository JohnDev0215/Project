package com.android.networkstack.apishim;

import android.net.CaptivePortalData;
import android.net.INetworkMonitorCallbacks;
import android.net.Uri;
import android.os.RemoteException;
import com.android.networkstack.apishim.common.CaptivePortalDataShim;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.networkstack.apishim.common.UnsupportedApiLevelException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CaptivePortalDataShimImpl extends com.android.networkstack.apishim.api29.CaptivePortalDataShimImpl {
    private final CaptivePortalData mData;

    protected CaptivePortalDataShimImpl(CaptivePortalData captivePortalData) {
        this.mData = captivePortalData;
    }

    public static CaptivePortalDataShim fromJson(JSONObject jSONObject) throws JSONException, UnsupportedApiLevelException {
        if (!isSupported()) {
            com.android.networkstack.apishim.api29.CaptivePortalDataShimImpl.fromJson(jSONObject);
            throw null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long longOrDefault = getLongOrDefault(jSONObject, "seconds-remaining", -1L);
        return new CaptivePortalDataShimImpl(new CaptivePortalData.Builder().setRefreshTime(jCurrentTimeMillis).setCaptive(jSONObject.getBoolean("captive")).setUserPortalUrl(getUriOrNull(jSONObject, "user-portal-url")).setVenueInfoUrl(getUriOrNull(jSONObject, "venue-info-url")).setBytesRemaining(getLongOrDefault(jSONObject, "bytes-remaining", -1L)).setExpiryTime(longOrDefault == -1 ? -1L : Math.min(Long.MAX_VALUE - jCurrentTimeMillis, longOrDefault <= 9223372036854775L ? 1000 * longOrDefault : Long.MAX_VALUE) + jCurrentTimeMillis).build());
    }

    public static boolean isSupported() {
        return ShimUtils.isReleaseOrDevelopmentApiAbove(29);
    }

    private static long getLongOrDefault(JSONObject jSONObject, String str, long j) throws JSONException {
        return !jSONObject.has(str) ? j : jSONObject.getLong(str);
    }

    private static Uri getUriOrNull(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return Uri.parse(jSONObject.getString(str));
        }
        return null;
    }

    @Override // com.android.networkstack.apishim.common.CaptivePortalDataShim
    public boolean isCaptive() {
        return this.mData.isCaptive();
    }

    @Override // com.android.networkstack.apishim.common.CaptivePortalDataShim
    public long getByteLimit() {
        return this.mData.getByteLimit();
    }

    @Override // com.android.networkstack.apishim.common.CaptivePortalDataShim
    public long getExpiryTimeMillis() {
        return this.mData.getExpiryTimeMillis();
    }

    @Override // com.android.networkstack.apishim.common.CaptivePortalDataShim
    public Uri getUserPortalUrl() {
        return this.mData.getUserPortalUrl();
    }

    @Override // com.android.networkstack.apishim.common.CaptivePortalDataShim
    public Uri getVenueInfoUrl() {
        return this.mData.getVenueInfoUrl();
    }

    @Override // com.android.networkstack.apishim.common.CaptivePortalDataShim
    public void notifyChanged(INetworkMonitorCallbacks iNetworkMonitorCallbacks) throws RemoteException {
        iNetworkMonitorCallbacks.notifyCaptivePortalDataChanged(this.mData);
    }
}
