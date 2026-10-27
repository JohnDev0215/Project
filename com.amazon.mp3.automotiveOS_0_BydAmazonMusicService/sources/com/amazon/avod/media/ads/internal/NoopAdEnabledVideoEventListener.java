package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdError;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class NoopAdEnabledVideoEventListener implements AdEnabledVideoEventListener {
    public static final NoopAdEnabledVideoEventListener INSTANCE = new NoopAdEnabledVideoEventListener();

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onAdBreakError(@Nonnull AdBreak adBreak, @Nonnull AdError adError) {
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onAdClipError(@Nonnull AdClip adClip, @Nonnull AdError adError) {
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onBeginAdBreak(AdBreak adBreak) {
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onBeginAdClip(AdClip adClip) {
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onEndAdBreak(AdBreak adBreak) {
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onEndAdClip(AdClip adClip) {
    }
}
