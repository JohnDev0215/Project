package com.amazon.mp3.util;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: AdvertisingUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/util/AdvertisingUtil;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getAdInfo", "Lcom/amazon/mp3/util/AdvertisingUtil$AndroidAdvertisingInfo;", "context", "Landroid/content/Context;", "AndroidAdvertisingInfo", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AdvertisingUtil {
    public static final AdvertisingUtil INSTANCE = new AdvertisingUtil();
    private static final String TAG = AdvertisingUtil.class.getName();

    private AdvertisingUtil() {
    }

    @WorkerThread
    public final AndroidAdvertisingInfo getAdInfo(Context context) {
        AdvertisingIdClient.Info advertisingIdInfo;
        Intrinsics.checkNotNullParameter(context, "context");
        Log.debug(TAG, "Fetching android advertising id information from Google Play Services.");
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.warning(TAG, "Google play service is not available on this device.", e);
            advertisingIdInfo = null;
        } catch (GooglePlayServicesRepairableException e2) {
            String str = TAG;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str2 = String.format(Locale.ROOT, "Unable to connect to Google Play Services. Connection status code:%d", Arrays.copyOf(new Object[]{Integer.valueOf(e2.getConnectionStatusCode())}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(locale, format, *args)");
            Log.warning(str, str2, e2);
            advertisingIdInfo = null;
        } catch (IOException unused) {
            Log.warning(TAG, "Unable to conntect to Google Play Services");
            advertisingIdInfo = null;
        }
        if (advertisingIdInfo == null) {
            return null;
        }
        AndroidAdvertisingInfo androidAdvertisingInfo = new AndroidAdvertisingInfo();
        androidAdvertisingInfo.setAdId(advertisingIdInfo.getId());
        androidAdvertisingInfo.setLimitAdTrackingEnabled(advertisingIdInfo.isLimitAdTrackingEnabled());
        return androidAdvertisingInfo;
    }

    /* JADX INFO: compiled from: AdvertisingUtil.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/util/AdvertisingUtil$AndroidAdvertisingInfo;", "", "()V", "adId", "", "getAdId", "()Ljava/lang/String;", "setAdId", "(Ljava/lang/String;)V", "isLimitAdTrackingEnabled", "", "()Z", "setLimitAdTrackingEnabled", "(Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AndroidAdvertisingInfo {
        private String adId;
        private boolean isLimitAdTrackingEnabled;

        public final String getAdId() {
            return this.adId;
        }

        public final void setAdId(String str) {
            this.adId = str;
        }

        /* JADX INFO: renamed from: isLimitAdTrackingEnabled, reason: from getter */
        public final boolean getIsLimitAdTrackingEnabled() {
            return this.isLimitAdTrackingEnabled;
        }

        public final void setLimitAdTrackingEnabled(boolean z) {
            this.isLimitAdTrackingEnabled = z;
        }
    }
}
