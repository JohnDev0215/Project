package com.netinsight.sye.syeClient.internal;

import com.netinsight.sye.syeClient.ISyeMetrics;
import com.netinsight.sye.syeClient.ISyePlayerSynchronizationCallback;
import com.netinsight.sye.syeClient.audio.SyeAudioPreferences;
import com.netinsight.sye.syeClient.closedCaptions.SyeDTVCCSettings;
import com.netinsight.sye.syeClient.video.SyeVideoPreferences;

/* JADX INFO: loaded from: classes7.dex */
public final class NativeSyePlayer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f762a;

    public NativeSyePlayer(JNISyeSystem jNISyeSystem, ISyePlayerCallbackWrapper iSyePlayerCallbackWrapper, long j, IBazPlayerQueryWrapper iBazPlayerQueryWrapper) {
        this.f762a = create(jNISyeSystem, iSyePlayerCallbackWrapper, iBazPlayerQueryWrapper, j);
    }

    public final void a(int i, String str) {
        reportError(this.f762a, i, str);
    }

    public final native void addDecryptionKeys(long j, JNISyeDRMKeys jNISyeDRMKeys);

    public final native void cacheThumbnailsForInterval(long j, long j2, long j3);

    public final native void cacheThumbnailsFromLive(long j, int i);

    public final native long create(JNISyeSystem jNISyeSystem, ISyePlayerCallbackWrapper iSyePlayerCallbackWrapper, IBazPlayerQueryWrapper iBazPlayerQueryWrapper, long j);

    public final native SyeAudioPreferences getAudioPreferences(long j);

    public final native String getAudioStreamInfo(long j);

    public final native String getAvailableAudioTracks(long j);

    public final native String getAvailableClosedCaptionsChannels(long j);

    public final native String getAvailableClosedCaptionsServices(long j);

    public final native String getCurrentChannelId(long j);

    public final native SyeDTVCCSettings getDTVCCSettings(long j);

    public final native ISyeMetrics getMetrics(long j);

    public final native int getPlayerState(long j);

    public final native int getSelectedClosedCaptionsChannel(long j);

    public final native int getSelectedClosedCaptionsService(long j);

    public final native int getSelectedClosedCaptionsType(long j);

    public final native DatafulJsonString getThumbnail(long j, long j2);

    public final native SyeVideoPreferences getVideoPreferences(long j);

    public final native String getVideoStreamInfo(long j);

    public final native void pause(long j);

    public final native void playFromLive(long j, String str);

    public final native void playFromUtcTime(long j, String str, long j2);

    public final native void playResume(long j);

    public final native void playWithOffset(long j, String str, long j2);

    public final native long poll(long j);

    public final native void reportError(long j, int i, String str);

    public final native void reportWarning(long j, int i, String str);

    public final native void selectClosedCaptionsChannel(long j, int i);

    public final native void selectClosedCaptionsService(long j, int i);

    public final native void selectClosedCaptionsType(long j, int i);

    public final native void setAudioPreferences(long j, SyeAudioPreferences syeAudioPreferences);

    public final native void setDTVCCSettings(long j, SyeDTVCCSettings syeDTVCCSettings);

    public final native void setVideoPreferences(long j, SyeVideoPreferences syeVideoPreferences);

    public final native void stop(long j);

    public final native void synchronize(long j, ISyePlayerSynchronizationCallback iSyePlayerSynchronizationCallback);

    public final native void teardown(long j);

    public final native void updateSyeSystem(long j, JNISyeSystem jNISyeSystem);
}
