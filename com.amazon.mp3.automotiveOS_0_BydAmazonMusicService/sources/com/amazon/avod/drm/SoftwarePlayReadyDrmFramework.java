package com.amazon.avod.drm;

import android.content.ContentValues;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.drm.playready.PlayReadyException;
import com.amazon.avod.drm.playready.SoftwarePlayReadyInitialization;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.config.LicensingConfig;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SoftwarePlayReadyDrmFramework implements DrmFramework {
    private final Context mContext;
    private boolean mDrmCallsDisabled;
    private final FileSystem mFileSystem;
    private boolean mInitialized;
    private boolean mIsChallengeReplaySupported;
    private final LicensingConfig mLicensingConfig;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private long mNativeDrmSession;
    private final PlaybackNativeLibrariesLoader mNativeLibrariesLoader;

    private native void decryptJni(int i, ByteBuffer byteBuffer, int i2, int i3);

    private native byte[] generateLicenseChallengeJni(String str) throws PlayReadyException;

    private native ContentValues getRightsJni(String str) throws PlayReadyException;

    private native ByteBuffer getSampleBuffer(int i, int i2) throws PlayReadyException;

    private static native void initPlayReadyLicensingLibraryJni() throws PlayReadyException;

    private native void initializeDrm(String str, String str2) throws PlayReadyException;

    private native void processLicenseResponseJni(String str) throws PlayReadyException;

    private native void removeRightsJni(String str) throws PlayReadyException;

    private native void sessionStartJni(String str, String str2) throws PlayReadyException;

    private native void sessionStopJni() throws PlayReadyException;

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeAllRights(@Nonnull Set<String> set) throws DrmLicensingException {
    }

    public native void setPlayReadyHeader(byte[] bArr) throws PlayReadyException;

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean shutdownSession() {
        return false;
    }

    public native void waitForSetPlayReadyHeader() throws PlayReadyException;

    public SoftwarePlayReadyDrmFramework(@Nonnull Context context, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this(context, fileSystem, playbackNativeLibrariesLoader, LicensingConfig.getInstance(), MediaSystemSharedDependencies.getInstance());
    }

    @VisibleForTesting
    SoftwarePlayReadyDrmFramework(@Nonnull Context context, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull LicensingConfig licensingConfig, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies) {
        this.mInitialized = false;
        this.mDrmCallsDisabled = false;
        this.mFileSystem = (FileSystem) Preconditions.checkNotNull(fileSystem, "fileSystem");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mNativeLibrariesLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader, "nativeLibrariesLoader");
        this.mIsChallengeReplaySupported = LicensingConfig.getInstance().isSoftwarePlayReadyChallengeReplaySupported();
        this.mLicensingConfig = (LicensingConfig) Preconditions.checkNotNull(licensingConfig, "licensingConfig");
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
    }

    protected void finalize() throws Throwable {
        if (this.mInitialized) {
            sessionStopJni();
        }
    }

    private void sessionStart() throws PlayReadyException {
        if (this.mInitialized) {
            return;
        }
        if (!this.mNativeLibrariesLoader.waitForInitialization()) {
            throw new PlayReadyException("Loading the playback native libraries timed out or was interrupted", LicenseError.SOFTWARE_PLAYREADY_NATIVE_LIB_LOAD_TIMEOUT);
        }
        initPlayReadyLicensingLibraryJni();
        SoftwarePlayReadyInitialization softwarePlayReadyInitializationInitialize = SoftwarePlayReadyInitialization.initialize(this.mContext, this.mFileSystem);
        sessionStartJni(softwarePlayReadyInitializationInitialize.getSystemDirectoryPath(), softwarePlayReadyInitializationInitialize.getLicenseStorePath());
        this.mInitialized = true;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean supportsChallengeReplay() {
        return this.mIsChallengeReplaySupported;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public byte[] generateLicenseChallenge(@Nonnull String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "header");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        ensureDrmSchemeSupported(drmScheme);
        try {
            sessionStart();
            byte[] bArrGenerateLicenseChallengeJni = generateLicenseChallengeJni(str);
            DLog.logf("SoftwarePlayreadyDrmFramework generated license challenge with length: %s", bArrGenerateLicenseChallengeJni == null ? null : Integer.valueOf(bArrGenerateLicenseChallengeJni.length));
            return bArrGenerateLicenseChallengeJni == null ? new byte[0] : bArrGenerateLicenseChallengeJni;
        } catch (PlayReadyException e) {
            throw new DrmLicensingException(e);
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nullable
    public String processLicenseResponse(@Nonnull String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "response");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        ensureDrmSchemeSupported(drmScheme);
        try {
            sessionStart();
            processLicenseResponseJni(str);
            return null;
        } catch (PlayReadyException e) {
            throw new DrmLicensingException(e);
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nullable
    public Map<String, Object> getRights(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "playReadyHeader");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        ensureDrmSchemeSupported(drmScheme);
        try {
            sessionStart();
            ContentValues rightsJni = getRightsJni(str);
            if (rightsJni == null) {
                return null;
            }
            HashMap map = new HashMap();
            for (String str3 : rightsJni.keySet()) {
                map.put(str3, rightsJni.get(str3));
            }
            return map;
        } catch (PlayReadyException e) {
            throw new DrmLicensingException(e);
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeRights(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "keyIdUri");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        ensureDrmSchemeSupported(drmScheme);
        try {
            sessionStart();
            removeRightsJni(str);
        } catch (PlayReadyException e) {
            throw new DrmLicensingException(e);
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public synchronized void disableDrmClientCalls() {
        this.mDrmCallsDisabled = true;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public synchronized void enableDrmClientCalls() {
        this.mDrmCallsDisabled = false;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public synchronized boolean areDrmClientCallsEnabled() {
        return !this.mDrmCallsDisabled;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public DrmScheme getCurrentDrmScheme() {
        return DrmScheme.PLAYREADY;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public DrmSecurityLevel getDrmSecurityLevel() {
        return DrmSecurityLevel.SL_150;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public List<DrmScheme> getSupportedDrmSchemes() {
        return ImmutableList.of(DrmScheme.PLAYREADY);
    }

    public synchronized void decrypt(SampleType sampleType, ByteBuffer byteBuffer, int i, int i2) {
        int i3 = 1;
        if (sampleType == SampleType.AUDIO_SAMPLE) {
            i3 = 0;
        } else if (sampleType != SampleType.VIDEO_SAMPLE) {
            DLog.errorf("unexpected sample type, SampleType %s", sampleType);
            return;
        }
        decryptJni(i3, byteBuffer, i, i2);
    }

    public synchronized ByteBuffer getSampleBuffer(SampleType sampleType, int i) throws PlayReadyException {
        if (sampleType == SampleType.AUDIO_SAMPLE) {
            return getSampleBuffer(i, 0);
        }
        if (sampleType == SampleType.VIDEO_SAMPLE) {
            return getSampleBuffer(i, 1);
        }
        throw new PlayReadyException(String.format(Locale.US, "Unable to allocate native buffer because of unexpected sample type, sampleType %s", sampleType), LicenseError.SOFTWARE_PLAYREADY_NATIVE_BUFFER_ALLOCATION_FAILURE);
    }

    public void configureDrm() throws PlayReadyException {
        SoftwarePlayReadyInitialization softwarePlayReadyInitializationInitialize = SoftwarePlayReadyInitialization.initialize(this.mContext, this.mFileSystem);
        initializeDrm(softwarePlayReadyInitializationInitialize.getSystemDirectoryPath(), softwarePlayReadyInitializationInitialize.getLicenseStorePath());
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework, com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
        boolean z = this.mLicensingConfig.isSoftwarePlayReadyChallengeReplaySupported() && !this.mMediaSystemSharedDependencies.isSDKPlayer();
        this.mIsChallengeReplaySupported = z;
        DLog.logf("SoftwarePlayReadyDrmFramework: Challenge replay support=%s", Boolean.valueOf(z));
    }

    private void ensureDrmSchemeSupported(@Nonnull DrmScheme drmScheme) throws DrmLicensingException {
        if (drmScheme != DrmScheme.PLAYREADY) {
            throw new DrmLicensingException(LicenseError.AIV_DRM_SCHEME_NOT_SUPPORTED, String.format(Locale.US, "%s scheme not supported by SoftwarePlayReadyDrmFramework!", drmScheme));
        }
    }
}
