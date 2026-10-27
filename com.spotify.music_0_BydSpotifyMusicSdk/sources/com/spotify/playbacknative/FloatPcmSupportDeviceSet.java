package com.spotify.playbacknative;

import android.content.Context;
import android.os.Build;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FloatPcmSupportDeviceSet {
    private final Context context;

    public FloatPcmSupportDeviceSet(Context context) {
        v03.j(context, "context");
        this.context = context;
    }

    private final boolean isSamsungExperienceSystemFeatureAvailable() {
        return this.context.getPackageManager().hasSystemFeature("com.samsung.feature.samsung_experience_mobile") || this.context.getPackageManager().hasSystemFeature("com.samsung.feature.SAMSUNG_EXPERIENCE");
    }

    private final boolean isSamsungFloatPcmSupported(String str, int i) {
        return ng2.b0(str, "Samsung", true) && i >= 31 && isSamsungExperienceSystemFeatureAvailable();
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isFloatPcmSupported() {
        String str = Build.MANUFACTURER;
        v03.i(str, "MANUFACTURER");
        return isFloatPcmSupported(str, Build.VERSION.SDK_INT);
    }

    public final boolean isFloatPcmSupported(String str, int i) {
        v03.j(str, "manufacturer");
        return isSamsungFloatPcmSupported(str, i);
    }
}
