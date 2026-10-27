package com.amazon.mp3.net.stratus.preferences;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.stratus.RetrievePreferencesRequest;
import com.amazon.stratus.RetrievePreferencesResponse;
import com.amazon.stratus.external.RetrievePreferencesCall;
import com.android.volley.VolleyError;
import com.google.common.collect.ImmutableList;
import java.net.URL;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RetailPreferencesRetriever.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0017J*\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/net/stratus/preferences/RetailPreferencesRetriever;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "buildRetrievePreferencesRequest", "Lcom/amazon/stratus/RetrievePreferencesRequest;", "customerId", "", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "preferenceType", "getPreference", "Lcom/amazon/stratus/RetrievePreferencesResponse;", "retrievePreferencesRequest", "retrievePreference", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class RetailPreferencesRetriever {
    private static final String TAG = RetailPreferencesRetriever.class.getSimpleName();
    private final Context context;

    public RetailPreferencesRetriever(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public String retrievePreference(String customerId, String deviceId, String deviceType, String preferenceType) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(preferenceType, "preferenceType");
        RetrievePreferencesRequest retrievePreferencesRequestBuildRetrievePreferencesRequest = buildRetrievePreferencesRequest(customerId, deviceId, deviceType, preferenceType);
        try {
            Map<String, String> map = getPreference(retrievePreferencesRequestBuildRetrievePreferencesRequest).getPreferencesByNamespace().get("retailPreferences");
            if (map == null) {
                return null;
            }
            return map.get(preferenceType);
        } catch (Exception e) {
            Log.error(TAG, Intrinsics.stringPlus("Exception occurred while calling StratusService for customerId + ", retrievePreferencesRequestBuildRetrievePreferencesRequest.getCustomerId()), e);
            return null;
        }
    }

    @VisibleForTesting
    public RetrievePreferencesResponse getPreference(RetrievePreferencesRequest retrievePreferencesRequest) throws VolleyError {
        Intrinsics.checkNotNullParameter(retrievePreferencesRequest, "retrievePreferencesRequest");
        RetrievePreferencesResponse retrievePreferencesResponseExecute = new RetrievePreferencesCall(new URL(Environment.STRATUS.get().toURL()), retrievePreferencesRequest, new RequestInterceptorFactory(this.context).provideRequestInterceptor()).execute();
        Intrinsics.checkNotNullExpressionValue(retrievePreferencesResponseExecute, "retrievePreferencesCall.execute()");
        return retrievePreferencesResponseExecute;
    }

    private final RetrievePreferencesRequest buildRetrievePreferencesRequest(String customerId, String deviceId, String deviceType, String preferenceType) {
        RetrievePreferencesRequest retrievePreferencesRequestBuild = RetrievePreferencesRequest.builder().withCustomerId(customerId).withDeviceId(deviceId).withDeviceType(deviceType).withNamespace("retailPreferences").withKeys(ImmutableList.of(preferenceType)).build();
        Intrinsics.checkNotNullExpressionValue(retrievePreferencesRequestBuild, "builder()\n            .w…pe))\n            .build()");
        return retrievePreferencesRequestBuild;
    }
}
