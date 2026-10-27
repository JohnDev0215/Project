package com.amazon.mp3.auto.carmode;

import android.content.Context;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeVersion.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002R\u001c\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeVersion;", "", "Landroid/content/Context;", "context", "", "save", "", "checkUpdate", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeVersion {
    public static final CarModeVersion INSTANCE = new CarModeVersion();
    private static final String TAG = CarModeVersion.class.getSimpleName();

    private CarModeVersion() {
    }

    public final void save(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("Car_Mode_Version", "2.0").apply();
    }

    public final boolean checkUpdate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("Car_Mode_Version", new String());
        Log.debug(TAG, "Car Mode local version is " + ((Object) string) + ", and latest version is 2.0");
        return !Intrinsics.areEqual("2.0", string) && AmazonApplication.getCapabilities().isCarModeV2Enabled();
    }
}
