package com.amazon.avod.media.ads;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AdEnabledVideoEventListener {
    void onAdBreakError(@Nonnull AdBreak adBreak, @Nonnull AdError adError);

    void onAdClipError(@Nonnull AdClip adClip, @Nonnull AdError adError);

    void onBeginAdBreak(@Nonnull AdBreak adBreak);

    void onBeginAdClip(@Nonnull AdClip adClip);

    void onEndAdBreak(@Nonnull AdBreak adBreak);

    void onEndAdClip(@Nonnull AdClip adClip);
}
