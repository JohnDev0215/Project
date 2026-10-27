package com.amazon.avod.media.playback.android;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidVideoPlayerConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0005R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/avod/media/playback/android/AndroidVideoPlayerConfig;", "Lamazon/android/config/ServerConfigBase;", "()V", "mPreventBufferTransitionBeforePrepare", "Lamazon/android/config/ConfigurationValue;", "", "kotlin.jvm.PlatformType", "shouldPreventBufferBeforePrepare", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AndroidVideoPlayerConfig extends ServerConfigBase {
    public static final AndroidVideoPlayerConfig INSTANCE;
    private static final ConfigurationValue<Boolean> mPreventBufferTransitionBeforePrepare;

    private AndroidVideoPlayerConfig() {
    }

    static {
        AndroidVideoPlayerConfig androidVideoPlayerConfig = new AndroidVideoPlayerConfig();
        INSTANCE = androidVideoPlayerConfig;
        mPreventBufferTransitionBeforePrepare = androidVideoPlayerConfig.newBooleanConfigValue("androidVideoPlayer_preventBufferBeforePrepare", true);
    }

    public final boolean shouldPreventBufferBeforePrepare() {
        Boolean value = mPreventBufferTransitionBeforePrepare.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "mPreventBufferTransitionBeforePrepare.value");
        return value.booleanValue();
    }
}
