package com.amazon.video.sdk.player;

import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractPlaybackEventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0010"}, d2 = {"Lcom/amazon/video/sdk/player/AbstractAdEnabledVideoEventListener;", "Lcom/amazon/avod/media/ads/AdEnabledVideoEventListener;", "()V", "onAdBreakError", "", "adBreak", "Lcom/amazon/avod/media/ads/AdBreak;", "error", "Lcom/amazon/avod/media/ads/AdError;", "onAdClipError", "adClip", "Lcom/amazon/avod/media/ads/AdClip;", "onBeginAdBreak", "onBeginAdClip", "onEndAdBreak", "onEndAdClip", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AbstractAdEnabledVideoEventListener implements AdEnabledVideoEventListener {
    public static final AbstractAdEnabledVideoEventListener INSTANCE = new AbstractAdEnabledVideoEventListener();

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onAdBreakError(AdBreak adBreak, AdError error) {
        Intrinsics.checkNotNullParameter(adBreak, "adBreak");
        Intrinsics.checkNotNullParameter(error, "error");
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onAdClipError(AdClip adClip, AdError error) {
        Intrinsics.checkNotNullParameter(adClip, "adClip");
        Intrinsics.checkNotNullParameter(error, "error");
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onBeginAdBreak(AdBreak adBreak) {
        Intrinsics.checkNotNullParameter(adBreak, "adBreak");
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onBeginAdClip(AdClip adClip) {
        Intrinsics.checkNotNullParameter(adClip, "adClip");
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onEndAdBreak(AdBreak adBreak) {
        Intrinsics.checkNotNullParameter(adBreak, "adBreak");
    }

    @Override // com.amazon.avod.media.ads.AdEnabledVideoEventListener
    public void onEndAdClip(AdClip adClip) {
        Intrinsics.checkNotNullParameter(adClip, "adClip");
    }

    private AbstractAdEnabledVideoEventListener() {
    }
}
