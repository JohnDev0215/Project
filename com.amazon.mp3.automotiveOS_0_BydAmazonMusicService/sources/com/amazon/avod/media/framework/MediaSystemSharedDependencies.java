package com.amazon.avod.media.framework;

import com.amazon.avod.http.PlaybackAuthProvider;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MediaSystemSharedDependencies {
    private DeviceIdentity mDeviceIdentity;
    private HdcpLevelProvider mHdcpLevelProvider;
    private IdentityShim mIdentityShim;
    private final InitializationLatch mInitializationLatch;
    private PlaybackAuthProvider mPlaybackAuthProvider;
    private PlaybackEnvelopeValidator mPlaybackEnvelopeValidator;
    private String mPlayerName;
    private String mTerminatorId;

    private static class SingletonHolder {
        private static volatile MediaSystemSharedDependencies sInstance = new MediaSystemSharedDependencies();

        private SingletonHolder() {
        }
    }

    private MediaSystemSharedDependencies() {
        this.mInitializationLatch = new InitializationLatch(this);
    }

    public static MediaSystemSharedDependencies getInstance() {
        return SingletonHolder.sInstance;
    }

    public void initialize(@Nonnull DeviceIdentity deviceIdentity, @Nonnull String str, @Nonnull String str2, @Nonnull PlaybackAuthProvider playbackAuthProvider, @Nonnull IdentityShim identityShim, @Nonnull PlaybackEnvelopeValidator playbackEnvelopeValidator, @Nonnull HdcpLevelProvider hdcpLevelProvider) {
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "MediaSystemSharedDependencies:init");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mTerminatorId = (String) Preconditions.checkNotNull(str, "terminatorId");
        this.mPlayerName = (String) Preconditions.checkNotNull(str2, "playerName");
        this.mPlaybackAuthProvider = (PlaybackAuthProvider) Preconditions.checkNotNull(playbackAuthProvider, "playbackAuthProvider");
        this.mIdentityShim = (IdentityShim) Preconditions.checkNotNull(identityShim, "identityShim");
        this.mPlaybackEnvelopeValidator = (PlaybackEnvelopeValidator) Preconditions.checkNotNull(playbackEnvelopeValidator, "playbackEnvelopeValidator");
        this.mHdcpLevelProvider = (HdcpLevelProvider) Preconditions.checkNotNull(hdcpLevelProvider, "hdcpLevelProvider");
        this.mInitializationLatch.complete();
        Profiler.endTrace(traceKeyBeginTrace);
    }

    public DeviceIdentity getDeviceIdentity() {
        this.mInitializationLatch.checkInitialized();
        return this.mDeviceIdentity;
    }

    @Nonnull
    public String getTerminatorId() {
        return this.mTerminatorId;
    }

    public boolean isSDKPlayer() {
        this.mInitializationLatch.checkInitialized();
        return this.mPlayerName.equals("Android Player SDK");
    }

    @Nonnull
    public IdentityShim getIdentityShim() {
        this.mInitializationLatch.checkInitialized();
        return this.mIdentityShim;
    }

    @Nonnull
    public PlaybackAuthProvider getPlaybackAuthProvider() {
        this.mInitializationLatch.checkInitialized();
        return this.mPlaybackAuthProvider;
    }

    @Nonnull
    public PlaybackEnvelopeValidator getPlaybackEnvelopeValidator() {
        this.mInitializationLatch.checkInitialized();
        return this.mPlaybackEnvelopeValidator;
    }

    @Nonnull
    public HdcpLevelProvider getHdcpLevelProvider() {
        this.mInitializationLatch.checkInitialized();
        return this.mHdcpLevelProvider;
    }

    public void waitForInitializationUninterruptibly() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
    }
}
