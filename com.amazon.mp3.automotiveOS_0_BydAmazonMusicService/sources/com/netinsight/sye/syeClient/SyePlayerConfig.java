package com.netinsight.sye.syeClient;

import com.netinsight.sye.syeClient.audio.SyeAudioPreferences;
import com.netinsight.sye.syeClient.internal.NativeAdaptorConfig;
import com.netinsight.sye.syeClient.internal.NativeSyePlayerConfig;
import com.netinsight.sye.syeClient.video.SyeVideoPreferences;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bE\u0010\u0019R$\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0016\u0010\f\u001a\u00020\t8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\"\u0010\u0014\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R*\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0005\"\u0004\b\u0017\u0010\u0007R$\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0005\"\u0004\b\u001c\u0010\u0007R*\u0010!\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b \u0010\u0019\u001a\u0004\b\u001e\u0010\u0005\"\u0004\b\u001f\u0010\u0007R\"\u0010%\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u000f\u001a\u0004\b#\u0010\u0011\"\u0004\b$\u0010\u0013R$\u0010(\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010\u0005\"\u0004\b'\u0010\u0007R\"\u0010,\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010\u000f\u001a\u0004\b*\u0010\u0011\"\u0004\b+\u0010\u0013R\"\u00100\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010\u000f\u001a\u0004\b.\u0010\u0011\"\u0004\b/\u0010\u0013R\u0016\u00104\u001a\u0002018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R$\u00106\u001a\u0002052\u0006\u00106\u001a\u0002058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010<\u001a\u00020;2\u0006\u0010<\u001a\u00020;8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R*\u0010D\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\bC\u0010\u0019\u001a\u0004\bA\u0010\u0005\"\u0004\bB\u0010\u0007¨\u0006F"}, d2 = {"Lcom/netinsight/sye/syeClient/SyePlayerConfig;", "", "", "millis", "getStallPlayingThresholdMillis", "()I", "setStallPlayingThresholdMillis", "(I)V", "stallPlayingThresholdMillis", "", "b", "J", "nativeRef", "", "f", "Z", "getRenderLatencyCompensation", "()Z", "setRenderLatencyCompensation", "(Z)V", "renderLatencyCompensation", "<anonymous parameter 0>", "getLoadingStallHoldOffDurationMillis", "setLoadingStallHoldOffDurationMillis", "loadingStallHoldOffDurationMillis$annotations", "()V", "loadingStallHoldOffDurationMillis", "getPlayingStallThresholdMillis", "setPlayingStallThresholdMillis", "playingStallThresholdMillis", "getPlayingStallHoldOffDurationMillis", "setPlayingStallHoldOffDurationMillis", "playingStallHoldOffDurationMillis$annotations", "playingStallHoldOffDurationMillis", "e", "getSmoothFrameRendering", "setSmoothFrameRendering", "smoothFrameRendering", "getPresentationDelayMillis", "setPresentationDelayMillis", "presentationDelayMillis", "c", "getContinuePlaybackInBackground", "setContinuePlaybackInBackground", "continuePlaybackInBackground", "d", "getDynamicAudioLatencyCompensation", "setDynamicAudioLatencyCompensation", "dynamicAudioLatencyCompensation", "Lcom/netinsight/sye/syeClient/internal/NativeSyePlayerConfig;", "a", "Lcom/netinsight/sye/syeClient/internal/NativeSyePlayerConfig;", "nativeConfig", "Lcom/netinsight/sye/syeClient/audio/SyeAudioPreferences;", "audioPreferences", "getAudioPreferences", "()Lcom/netinsight/sye/syeClient/audio/SyeAudioPreferences;", "setAudioPreferences", "(Lcom/netinsight/sye/syeClient/audio/SyeAudioPreferences;)V", "Lcom/netinsight/sye/syeClient/video/SyeVideoPreferences;", "videoPreferences", "getVideoPreferences", "()Lcom/netinsight/sye/syeClient/video/SyeVideoPreferences;", "setVideoPreferences", "(Lcom/netinsight/sye/syeClient/video/SyeVideoPreferences;)V", "getPreferredInitialBitrate", "setPreferredInitialBitrate", "preferredInitialBitrate$annotations", "preferredInitialBitrate", "<init>", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public class SyePlayerConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final NativeSyePlayerConfig nativeConfig;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final long nativeRef;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public boolean continuePlaybackInBackground;

    /* JADX INFO: renamed from: d, reason: from kotlin metadata */
    public boolean dynamicAudioLatencyCompensation;

    /* JADX INFO: renamed from: e, reason: from kotlin metadata */
    public boolean smoothFrameRendering;

    /* JADX INFO: renamed from: f, reason: from kotlin metadata */
    public boolean renderLatencyCompensation;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Not used since SDK 1.16")
    public static /* synthetic */ void loadingStallHoldOffDurationMillis$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Not used since SDK 1.16")
    public static /* synthetic */ void playingStallHoldOffDurationMillis$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Not used since SDK 1.17")
    public static /* synthetic */ void preferredInitialBitrate$annotations() {
    }

    public final SyeAudioPreferences getAudioPreferences() {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        return nativeSyePlayerConfig.getAudioPreferences(nativeSyePlayerConfig.f763a);
    }

    public final boolean getContinuePlaybackInBackground() {
        return this.continuePlaybackInBackground;
    }

    public final boolean getDynamicAudioLatencyCompensation() {
        return this.dynamicAudioLatencyCompensation;
    }

    public final int getLoadingStallHoldOffDurationMillis() {
        return 0;
    }

    public final int getPlayingStallHoldOffDurationMillis() {
        return 0;
    }

    public final int getPlayingStallThresholdMillis() {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        return nativeSyePlayerConfig.getPlayingStallThresholdMillis(nativeSyePlayerConfig.f763a);
    }

    public final int getPreferredInitialBitrate() {
        return 0;
    }

    public final int getPresentationDelayMillis() {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        return nativeSyePlayerConfig.getPresentationDelayMillis(nativeSyePlayerConfig.f763a);
    }

    public final boolean getRenderLatencyCompensation() {
        return this.renderLatencyCompensation;
    }

    public final boolean getSmoothFrameRendering() {
        return this.smoothFrameRendering;
    }

    public final int getStallPlayingThresholdMillis() {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        return nativeSyePlayerConfig.getStallPlayingThresholdMillis(nativeSyePlayerConfig.f763a);
    }

    public final SyeVideoPreferences getVideoPreferences() {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        return nativeSyePlayerConfig.getVideoPreferences(nativeSyePlayerConfig.f763a);
    }

    public final void setAudioPreferences(SyeAudioPreferences syeAudioPreferences) {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        nativeSyePlayerConfig.getClass();
        nativeSyePlayerConfig.setAudioPreferences(nativeSyePlayerConfig.f763a, syeAudioPreferences);
    }

    public final void setContinuePlaybackInBackground(boolean z) {
        this.continuePlaybackInBackground = z;
    }

    public final void setDynamicAudioLatencyCompensation(boolean z) {
        this.dynamicAudioLatencyCompensation = z;
    }

    public final void setLoadingStallHoldOffDurationMillis(int i) {
    }

    public final void setPlayingStallHoldOffDurationMillis(int i) {
    }

    public final void setPlayingStallThresholdMillis(int i) {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        nativeSyePlayerConfig.setPlayingStallThresholdMillis(nativeSyePlayerConfig.f763a, i);
    }

    public final void setPreferredInitialBitrate(int i) {
    }

    public final void setPresentationDelayMillis(int i) {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        nativeSyePlayerConfig.setPresentationDelayMillis(nativeSyePlayerConfig.f763a, i);
    }

    public final void setRenderLatencyCompensation(boolean z) {
        this.renderLatencyCompensation = z;
    }

    public final void setSmoothFrameRendering(boolean z) {
        this.smoothFrameRendering = z;
    }

    public final void setStallPlayingThresholdMillis(int i) {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        nativeSyePlayerConfig.setStallPlayingThresholdMillis(nativeSyePlayerConfig.f763a, i);
    }

    public final void setVideoPreferences(SyeVideoPreferences syeVideoPreferences) {
        NativeSyePlayerConfig nativeSyePlayerConfig = this.nativeConfig;
        nativeSyePlayerConfig.getClass();
        nativeSyePlayerConfig.setVideoPreferences(nativeSyePlayerConfig.f763a, syeVideoPreferences);
    }

    public SyePlayerConfig() {
        NativeSyePlayerConfig nativeSyePlayerConfig = new NativeSyePlayerConfig();
        this.nativeConfig = nativeSyePlayerConfig;
        this.nativeRef = nativeSyePlayerConfig.a();
        this.dynamicAudioLatencyCompensation = true;
        this.smoothFrameRendering = true;
        this.renderLatencyCompensation = true;
        new NativeAdaptorConfig(nativeSyePlayerConfig.a());
        nativeSyePlayerConfig.a(true);
    }
}
