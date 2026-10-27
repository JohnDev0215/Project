package com.facebook.react.fabric;

import androidx.annotation.NonNull;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public interface ReactNativeConfig {
    public static final ReactNativeConfig DEFAULT_CONFIG = new EmptyReactNativeConfig();

    @DoNotStrip
    boolean getBool(@NonNull String str);

    @DoNotStrip
    double getDouble(@NonNull String str);

    @DoNotStrip
    long getInt64(@NonNull String str);

    @DoNotStrip
    String getString(@NonNull String str);
}
