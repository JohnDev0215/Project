package com.amazon.avod.media.drm;

import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.media.ResourceBusyException;
import android.media.UnsupportedSchemeException;
import com.amazon.avod.drm.widevine.WidevineHeader;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.pipeline.ConditionalLoggingTimer;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
@TargetApi(19)
public class MediaDrmSession implements DrmSession {
    private int mActiveDrmSessionCount;
    private DrmScheme mCurrentDrmScheme;
    private DrmSecurityLevel mDrmSecurityLevel;
    private final boolean mForceWidevine;
    private final boolean mIsMediaDrmChallengeReplayEnabled;
    private final boolean mIsMultiSessionEnabled;
    private final EnumMap<SessionType, String> mKeySetIdMap;
    private final MediaDefaultConfiguration mMediaConfig;
    private MediaDrm mMediaDrm;
    private final MediaDrmConfig mMediaDrmConfig;
    private final boolean mMediaDrmReturnNullRightsForIAE;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private final Object mMutex;
    private UUID mSchemeUUID;
    private final EnumMap<SessionType, byte[]> mSessionIdMap;
    private final List<DrmScheme> mSupportedDrmSchemes;
    private final ConditionalLoggingTimer mTimer;

    private enum SessionType {
        IMMEDIATE,
        CACHE
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework, com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
    }

    public MediaDrmSession(boolean z) {
        this(MediaDefaultConfiguration.getInstance(), MediaDrmConfig.getInstance(), MediaSystemSharedDependencies.getInstance(), z);
    }

    MediaDrmSession(@Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaDrmConfig mediaDrmConfig, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies, boolean z) {
        this.mMutex = new Object();
        this.mTimer = ConditionalLoggingTimer.create();
        this.mSessionIdMap = new EnumMap<>(SessionType.class);
        this.mKeySetIdMap = new EnumMap<>(SessionType.class);
        this.mDrmSecurityLevel = DrmSecurityLevel.UNKNOWN;
        this.mActiveDrmSessionCount = 0;
        this.mMediaConfig = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaConfig");
        this.mSupportedDrmSchemes = populateSupportedDrmSchemes();
        MediaDrmConfig mediaDrmConfig2 = (MediaDrmConfig) Preconditions.checkNotNull(mediaDrmConfig, "mediaDrmConfig");
        this.mMediaDrmConfig = mediaDrmConfig2;
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
        this.mIsMultiSessionEnabled = mediaDrmConfig2.isMediaDrmMultiSessionEnabled();
        this.mIsMediaDrmChallengeReplayEnabled = mediaDrmConfig2.isMediaDrmChallengeReplayEnabled();
        this.mMediaDrmReturnNullRightsForIAE = mediaDrmConfig2.getMediaDrmReturnNullRightsForIAE();
        this.mForceWidevine = z;
    }

    @Nonnull
    private List<DrmScheme> populateSupportedDrmSchemes() {
        ImmutableList.Builder builder = ImmutableList.builder();
        try {
            for (DrmScheme drmScheme : DrmScheme.values()) {
                Iterator<UUID> it = this.mMediaConfig.getCryptoSchemes(drmScheme).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (MediaDrm.isCryptoSchemeSupported(it.next())) {
                        builder.add(drmScheme);
                        DLog.logf("Adding %s to supported Drm scheme list", drmScheme);
                        break;
                    }
                }
            }
        } catch (MediaDrmResetException e) {
            DLog.warnf("Populating supported drm scheme list failed with: %s", e);
            handleMediaDrmResetException(e);
        }
        return builder.build();
    }

    public void preLoadMediaDrm() {
        if (this.mMediaDrmConfig.shouldPreLoadMediaDrmOnStartup() && this.mMediaDrm == null) {
            DrmScheme currentDrmScheme = getCurrentDrmScheme();
            try {
                instantiateMediaDrm(currentDrmScheme);
            } catch (MediaDrmResetException e) {
                DLog.warnf("Pre-loading MediaDrm with scheme %s failed with: %s", currentDrmScheme, e);
                handleMediaDrmResetException(e);
            } catch (UnsupportedSchemeException e2) {
                DLog.warnf("Pre-loading MediaDrm with scheme %s failed with: %s", currentDrmScheme, e2);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public List<DrmScheme> getSupportedDrmSchemes() {
        return this.mSupportedDrmSchemes;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean shutdownSession() {
        synchronized (this.mMutex) {
            if (this.mActiveDrmSessionCount != 0) {
                return false;
            }
            closeDrmSession(SessionType.IMMEDIATE);
            return true;
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean isMultiSessionDrmSupported() {
        return this.mIsMultiSessionEnabled;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean supportsChallengeReplay() {
        return this.mIsMediaDrmChallengeReplayEnabled && this.mCurrentDrmScheme == DrmScheme.PLAYREADY && !this.mMediaSystemSharedDependencies.isSDKPlayer();
    }

    private void logMediaDrmOperation(@Nonnull String str, @Nonnull byte[] bArr, @Nonnull SessionType sessionType) {
        DLog.logf("MediaDrm Operation: %s | SessionId: %s | SessionType: %s | Scheme: %s", str, new String(bArr), sessionType, this.mCurrentDrmScheme);
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public byte[] generateLicenseChallenge(@Nonnull String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        byte[] data;
        Preconditions.checkNotNull(str, "header");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        synchronized (this.mMutex) {
            throwIfDrmFrameworkBusy();
            SessionType sessionType = getSessionType(z);
            try {
                try {
                    try {
                        try {
                            try {
                                closeDrmSession(sessionType);
                                ensureMediaDrmLoadedWithScheme(drmScheme, sessionType);
                                byte[] bArrDecode = Base64.decode(str);
                                byte[] bArr = this.mSessionIdMap.get(sessionType);
                                data = this.mMediaDrm.getKeyRequest(bArr, bArrDecode, null, 2, null).getData();
                                if (data.length == 0) {
                                    DLog.warnf("We think the MediaServer has died because the generated challenge was empty, unloading MediaDrm.");
                                    unloadMediaDrm();
                                    throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "MediaDrm generated empty license challenge, perhaps mediaserver died", null, this.mCurrentDrmScheme);
                                }
                                logMediaDrmOperation(String.format("Generated license challenge, length: %s", Integer.valueOf(data.length)), bArr, sessionType);
                            } catch (UnsupportedSchemeException e) {
                                closeDrmSession(sessionType);
                                throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "UnsupportedSchemeException: Failed to instantiate MediaDrm object", e, this.mCurrentDrmScheme);
                            } catch (IllegalArgumentException e2) {
                                closeDrmSession(sessionType);
                                throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "IllegalArgumentException: Failed to decode keySetId from Base64", e2, this.mCurrentDrmScheme);
                            }
                        } catch (ResourceBusyException e3) {
                            closeDrmSession(sessionType);
                            throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "ResourceBusyException: Failed to open MediaDrm session", e3, this.mCurrentDrmScheme);
                        } catch (IllegalStateException e4) {
                            closeDrmSession(sessionType);
                            handleIllegalStateException(e4);
                            throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "MediaDrm threw unexpected IllegalStateException generating license challenge", e4, this.mCurrentDrmScheme);
                        }
                    } catch (IOException e5) {
                        closeDrmSession(sessionType);
                        throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "IOException: Failed to decode initialization data from Base64", e5, this.mCurrentDrmScheme);
                    }
                } catch (MediaDrmResetException e6) {
                    closeDrmSession(sessionType);
                    handleMediaDrmResetException(e6);
                    throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "MediaDrm threw unexpected MediaDrmResetException generating license challenge", e6, this.mCurrentDrmScheme);
                }
            } catch (NotProvisionedException e7) {
                closeDrmSession(sessionType);
                handleNotProvisionedException();
                throw new DrmLicensingException(LicenseError.GENERATE_CHALLENGE_FAILURE, "NotProvisionedException: Provisioning complete, try again", e7, this.mCurrentDrmScheme);
            }
        }
        return data;
    }

    private void handleIllegalStateException(IllegalStateException illegalStateException) {
        DLog.warnf("We think the MediaServer might have died because MediaDrm threw ISE %s, unloading MediaDrm.", illegalStateException);
        unloadMediaDrm();
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public String processLicenseResponse(@Nonnull String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        byte[] bytes;
        String strEncodeBytes;
        Preconditions.checkNotNull(str, "licenseResponse");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        synchronized (this.mMutex) {
            throwIfDrmFrameworkBusy();
            SessionType sessionType = getSessionType(z);
            try {
                try {
                    try {
                        try {
                            ensureMediaDrmLoadedWithScheme(drmScheme, sessionType);
                            if (drmScheme == DrmScheme.WIDEVINE) {
                                bytes = Base64.decode(str);
                            } else {
                                bytes = str.getBytes();
                            }
                            byte[] bArr = this.mSessionIdMap.get(sessionType);
                            byte[] bArrProvideKeyResponse = this.mMediaDrm.provideKeyResponse(bArr, bytes);
                            if (bArrProvideKeyResponse == null || bArrProvideKeyResponse.length == 0) {
                                closeDrmSession(sessionType);
                                throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "MediaDrm returned null or empty keySetId on processing license response!");
                            }
                            strEncodeBytes = Base64.encodeBytes(bArrProvideKeyResponse);
                            logMediaDrmOperation(String.format("Processed license response, got back keySetId: %s", strEncodeBytes), bArr, sessionType);
                            this.mKeySetIdMap.put(sessionType, strEncodeBytes);
                        } catch (DeniedByServerException e) {
                            closeDrmSession(sessionType);
                            throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "DeniedByServerException: Failed to process MediaDrm key response", e, this.mCurrentDrmScheme);
                        } catch (NotProvisionedException e2) {
                            closeDrmSession(sessionType);
                            handleNotProvisionedException();
                            throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "NotProvisionedException: Provisioning complete, try again", e2, this.mCurrentDrmScheme);
                        }
                    } catch (ResourceBusyException e3) {
                        closeDrmSession(sessionType);
                        throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "ResourceBusyException: Failed to open MediaDrm session", e3, this.mCurrentDrmScheme);
                    } catch (UnsupportedSchemeException e4) {
                        closeDrmSession(sessionType);
                        throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "UnsupportedSchemeException: Failed to instantiate MediaDrm object", e4, this.mCurrentDrmScheme);
                    }
                } catch (MediaDrmResetException e5) {
                    closeDrmSession(sessionType);
                    handleMediaDrmResetException(e5);
                    throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "MediaDrm threw unexpected MediaDrmResetException processing license response", e5, this.mCurrentDrmScheme);
                } catch (IllegalStateException e6) {
                    closeDrmSession(sessionType);
                    handleIllegalStateException(e6);
                    throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "MediaDrm threw unexpected IllegalStateException processing license response", e6, this.mCurrentDrmScheme);
                }
            } catch (IOException e7) {
                closeDrmSession(sessionType);
                throw new DrmLicensingException(LicenseError.PROCESS_RESPONSE_FAILURE, "IOException: Failed to decode license response from Base64", e7, this.mCurrentDrmScheme);
            }
        }
        return strEncodeBytes;
    }

    @Override // com.amazon.avod.media.drm.DrmSession
    @Nonnull
    public DrmCryptoSession openDrmCryptoSession(@Nonnull byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3, @Nullable String str, @Nonnull DrmScheme drmScheme) throws DrmLicensingException {
        MediaCryptoSession mediaCryptoSession;
        Preconditions.checkNotNull(bArr, "encryptionInitData");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        SessionType sessionType = SessionType.IMMEDIATE;
        if (Strings.isNullOrEmpty(str)) {
            closeDrmSession(sessionType);
            throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "Cannot instantiate MediaCrypto with a null or empty keySetId!");
        }
        synchronized (this.mMutex) {
            try {
                try {
                    try {
                        try {
                            try {
                                ensureMediaDrmLoadedWithScheme(drmScheme, sessionType);
                                if (str.equals(this.mKeySetIdMap.get(sessionType))) {
                                    logMediaDrmOperation(String.format("KeySetId: %s already restored", str), this.mSessionIdMap.get(sessionType), sessionType);
                                } else {
                                    if (this.mKeySetIdMap.get(sessionType) != null) {
                                        closeDrmSession(sessionType);
                                        openSession(sessionType);
                                    }
                                    restoreKeySetId(str, sessionType);
                                }
                                byte[] bArr4 = this.mSessionIdMap.get(sessionType);
                                this.mTimer.start();
                                MediaCrypto mediaCrypto = new MediaCrypto(this.mSchemeUUID, bArr4);
                                this.mTimer.stop(String.format("new MediaCrypto %s", this.mCurrentDrmScheme));
                                logMediaDrmOperation("Instantiated new MediaCrypto", bArr4, sessionType);
                                if (drmScheme == DrmScheme.WIDEVINE) {
                                    if (!this.mMediaConfig.isWidevineMultiKeyDecryptionEnabled() || bArr2 == null) {
                                        bArr2 = getWidevineKeyId(bArr);
                                    } else {
                                        mediaCryptoSession = new MediaCryptoSession(bArr2, bArr3, mediaCrypto, this.mMediaDrmConfig.shouldGenerateCryptoInfoForClearSamples());
                                    }
                                } else if (drmScheme == DrmScheme.PLAYREADY) {
                                    bArr2 = getPlayReadyKeyId(bArr);
                                } else {
                                    closeDrmSession(sessionType);
                                    throw new DrmLicensingException(LicenseError.AIV_DRM_SCHEME_NOT_SUPPORTED, String.format("Unsupported Drm Scheme: %s!", drmScheme));
                                }
                                bArr3 = bArr2;
                                mediaCryptoSession = new MediaCryptoSession(bArr2, bArr3, mediaCrypto, this.mMediaDrmConfig.shouldGenerateCryptoInfoForClearSamples());
                            } catch (MediaCryptoException e) {
                                closeDrmSession(sessionType);
                                throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "MediaCryptoException: Failed to open MediaDrm session", e, this.mCurrentDrmScheme);
                            } catch (ResourceBusyException e2) {
                                closeDrmSession(sessionType);
                                throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "ResourceBusyException: Failed to open MediaDrm session", e2, this.mCurrentDrmScheme);
                            }
                        } catch (MediaDrmResetException e3) {
                            closeDrmSession(sessionType);
                            handleMediaDrmResetException(e3);
                            throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "MediaDrm threw unexpected MediaDrmResetException", e3, this.mCurrentDrmScheme);
                        } catch (IOException e4) {
                            closeDrmSession(sessionType);
                            throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "IOException: Failed to decode offline keySetId or decryption KeyId", e4, this.mCurrentDrmScheme);
                        }
                    } catch (NotProvisionedException e5) {
                        closeDrmSession(sessionType);
                        handleNotProvisionedException();
                        throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "NotProvisionedException: Provisioning complete, try again", e5, this.mCurrentDrmScheme);
                    } catch (IllegalArgumentException e6) {
                        closeDrmSession(sessionType);
                        throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "IllegalArgumentException: Failed to decode keySetId from Base64", e6, this.mCurrentDrmScheme);
                    }
                } catch (UnsupportedSchemeException e7) {
                    closeDrmSession(sessionType);
                    throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "UnsupportedSchemeException: Failed to instantiate MediaDrm object", e7, this.mCurrentDrmScheme);
                }
            } catch (IllegalStateException e8) {
                closeDrmSession(sessionType);
                handleIllegalStateException(e8);
                throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "MediaDrm threw unexpected IllegalStateException", e8, this.mCurrentDrmScheme);
            }
        }
        return mediaCryptoSession;
    }

    protected void finalize() {
        synchronized (this.mMutex) {
            unloadMediaDrm();
        }
    }

    private void ensureMediaDrmLoadedWithScheme(@Nonnull DrmScheme drmScheme, @Nonnull SessionType sessionType) throws UnsupportedSchemeException, IllegalStateException, DrmLicensingException, ResourceBusyException, NotProvisionedException {
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        Preconditions.checkNotNull(sessionType, "sessionType");
        DrmScheme drmScheme2 = this.mCurrentDrmScheme;
        if (drmScheme2 != null && drmScheme2 != drmScheme) {
            unloadMediaDrm();
        }
        if (this.mMediaDrm == null) {
            instantiateMediaDrm(drmScheme);
        }
        openSession(sessionType);
    }

    private void instantiateMediaDrm(@Nonnull DrmScheme drmScheme) throws UnsupportedSchemeException {
        UUID next;
        Iterator<UUID> it = this.mMediaConfig.getCryptoSchemes(drmScheme).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (MediaDrm.isCryptoSchemeSupported(next)) {
                    break;
                }
            }
        }
        if (next == null) {
            throw new UnsupportedSchemeException("No supported scheme found for MediaDrm!");
        }
        this.mSchemeUUID = next;
        this.mTimer.start();
        this.mMediaDrm = new MediaDrm(this.mSchemeUUID);
        this.mCurrentDrmScheme = drmScheme;
        this.mTimer.stop(String.format("New MediaDrm %s", drmScheme));
        if (this.mCurrentDrmScheme == DrmScheme.WIDEVINE) {
            String propertyString = this.mMediaDrm.getPropertyString("securityLevel");
            DLog.logf("MediaDrm Widevine Security Level: %s", propertyString);
            this.mDrmSecurityLevel = DrmSecurityLevel.fromSecurityLevelString(propertyString);
        } else {
            DrmSecurityLevel drmSecurityLevel = DrmSecurityLevel.SL_2000;
            this.mDrmSecurityLevel = drmSecurityLevel;
            DLog.logf("MediaDrm assumed PlayReady Security Level: %s", drmSecurityLevel);
        }
        DLog.logf("Instantiated new MediaDrm, Scheme: %s SchemeUUID: %s", this.mCurrentDrmScheme, this.mSchemeUUID);
        closeDrmSession(SessionType.IMMEDIATE);
        closeDrmSession(SessionType.CACHE);
    }

    private void openSession(@Nonnull SessionType sessionType) throws DrmLicensingException, ResourceBusyException, NotProvisionedException {
        if (this.mSessionIdMap.get(sessionType) == null) {
            this.mTimer.start();
            this.mSessionIdMap.put(sessionType, this.mMediaDrm.openSession());
            this.mTimer.stop(String.format("MediaDrm.openSession() %s", this.mCurrentDrmScheme));
            logMediaDrmOperation("Opened session", this.mSessionIdMap.get(sessionType), sessionType);
            verifyValidSession(this.mSessionIdMap.get(sessionType));
        }
    }

    @Nonnull
    private SessionType getSessionType(boolean z) {
        return (!this.mIsMultiSessionEnabled || z) ? SessionType.IMMEDIATE : SessionType.CACHE;
    }

    private void verifyValidSession(@Nullable byte[] bArr) throws DrmLicensingException {
        if (bArr == null || bArr.length != 0) {
            return;
        }
        DLog.warnf("Newly opened MediaDrm session is invalid! MediaServer probably died, unloading MediaDrm.");
        unloadMediaDrm();
        throw new DrmLicensingException(LicenseError.OPEN_SESSION_FAILURE, "Newly opened MediaDrm session is invalid! MediaServer probably died, unloading MediaDrm.");
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.util.EnumMap, java.util.EnumMap<com.amazon.avod.media.drm.MediaDrmSession$SessionType, java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.media.MediaDrm, com.amazon.avod.playback.drm.DrmScheme, java.util.UUID] */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.media.MediaDrm, com.amazon.avod.playback.drm.DrmScheme, java.util.UUID] */
    private void unloadMediaDrm() {
        if (this.mMediaDrm != null) {
            EnumMap<SessionType, byte[]> enumMap = this.mSessionIdMap;
            SessionType sessionType = SessionType.IMMEDIATE;
            if (enumMap.get(sessionType) != null) {
                closeDrmSession(sessionType);
            }
            EnumMap<SessionType, byte[]> enumMap2 = this.mSessionIdMap;
            SessionType sessionType2 = SessionType.CACHE;
            if (enumMap2.get(sessionType2) != null) {
                closeDrmSession(sessionType2);
            }
            ?? r0 = 0;
            r0 = 0;
            try {
                try {
                    this.mTimer.start();
                    this.mMediaDrm.release();
                    this.mTimer.stop("MediaDrm.release()");
                } catch (IllegalStateException e) {
                    DLog.warnf("MediaDrm threw unexpected IllegalStateException calling release(): %s", e.getMessage());
                }
            } finally {
                this.mSchemeUUID = r0;
                this.mMediaDrm = r0;
                this.mCurrentDrmScheme = r0;
                this.mSessionIdMap.clear();
                this.mKeySetIdMap.clear();
            }
        }
    }

    @Nonnull
    private static byte[] getPlayReadyKeyId(@Nonnull byte[] bArr) throws IOException, IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i += 2) {
            sb.append((char) bArr[i]);
        }
        String string = sb.toString();
        byte[] bArrDecode = Base64.decode(string.substring(string.indexOf("<KID>") + 5, string.indexOf("</KID>")));
        swap(bArrDecode, 0, 3);
        swap(bArrDecode, 1, 2);
        swap(bArrDecode, 4, 5);
        swap(bArrDecode, 6, 7);
        return bArrDecode;
    }

    private static void swap(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    @Nonnull
    private static byte[] getWidevineKeyId(@Nonnull byte[] bArr) throws IOException {
        return WidevineHeader.WidevineCencHeader.parseFrom(bArr).getKeyId(0).toByteArray();
    }

    private void restoreKeySetId(@Nonnull String str, @Nonnull SessionType sessionType) throws IOException {
        Preconditions.checkNotNull(str, "keySetIdAsBase64");
        byte[] bArr = this.mSessionIdMap.get(sessionType);
        Preconditions.checkState(this.mKeySetIdMap.get(sessionType) == null, String.format("Attempted to restore keySetId: %s on SessionId: %s, SessionType: %s that already has keySetId: %s restored in it!", str, new String(bArr), sessionType, this.mKeySetIdMap.get(sessionType)));
        byte[] bArrDecode = Base64.decode(str);
        logMediaDrmOperation(String.format("Attempting to restore keySetId: %s", str), bArr, sessionType);
        this.mTimer.start();
        this.mMediaDrm.restoreKeys(bArr, bArrDecode);
        this.mTimer.stop(String.format("MediaDrm.restoreKeys %s", this.mCurrentDrmScheme));
        logMediaDrmOperation(String.format("Restored keySetId: %s", str), bArr, sessionType);
        this.mKeySetIdMap.put(sessionType, str);
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nullable
    public Map<String, Object> getRights(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        HashMap map;
        Preconditions.checkNotNull(str, "uri");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        if (Strings.isNullOrEmpty(str2)) {
            DLog.logf("MediaDrm no license available in the %s license store, keySetId is null, returning null rights.", this.mCurrentDrmScheme);
            return null;
        }
        synchronized (this.mMutex) {
            throwIfDrmFrameworkBusy();
            SessionType sessionType = getSessionType(z);
            if (this.mActiveDrmSessionCount > 0 && str2.equals(this.mKeySetIdMap.get(SessionType.IMMEDIATE))) {
                throw new DrmLicensingException(LicenseError.AIV_DRM_FRAMEWORK_BUSY, "Playback for this title is currently in progress, skipping cache licensing operation.");
            }
            try {
                try {
                    try {
                        try {
                            try {
                                ensureMediaDrmLoadedWithScheme(drmScheme, sessionType);
                                byte[] bArr = this.mSessionIdMap.get(sessionType);
                                if (str2.equals(this.mKeySetIdMap.get(sessionType))) {
                                    logMediaDrmOperation(String.format("KeySetId: %s already restored", str2), bArr, sessionType);
                                } else {
                                    if (this.mKeySetIdMap.get(sessionType) != null) {
                                        closeDrmSession(sessionType);
                                        openSession(sessionType);
                                    }
                                    restoreKeySetId(str2, sessionType);
                                }
                                this.mTimer.start();
                                HashMap<String, String> mapQueryKeyStatus = this.mMediaDrm.queryKeyStatus(bArr);
                                this.mTimer.stop(String.format("MediaDrm.queryKeyStatus Scheme: %s SessionId: %s SessionType: %s KeySetId: %s", this.mCurrentDrmScheme, new String(bArr), sessionType, str2));
                                if (mapQueryKeyStatus == null) {
                                    closeDrmSession(sessionType);
                                    throw new DrmLicensingException(LicenseError.QUERY_RIGHTS_FAILURE, "MediaDrm returned null on querying key status for the current session.");
                                }
                                map = new HashMap(mapQueryKeyStatus.size());
                                map.putAll(mapQueryKeyStatus);
                                if (!z) {
                                    closeDrmSession(sessionType);
                                }
                            } catch (UnsupportedSchemeException e) {
                                closeDrmSession(sessionType);
                                throw new DrmLicensingException(LicenseError.QUERY_RIGHTS_FAILURE, "UnsupportedSchemeException: Failed to instantiate MediaDrm object", e, this.mCurrentDrmScheme);
                            } catch (IOException e2) {
                                closeDrmSession(sessionType);
                                throw new DrmLicensingException(LicenseError.QUERY_RIGHTS_FAILURE, "IOException: Failed to decode MediaDrm keySetId data from Base64", e2, this.mCurrentDrmScheme);
                            }
                        } catch (IllegalArgumentException e3) {
                            closeDrmSession(sessionType);
                            if (this.mMediaDrmReturnNullRightsForIAE) {
                                DLog.warnf("IllegalArgumentException: %s when querying key status from MediaDrm, returning null", e3);
                                return null;
                            }
                            throw new DrmLicensingException(LicenseError.QUERY_RIGHTS_FAILURE, "IllegalArgumentException: Failed to decode keySetId from Base64", e3, this.mCurrentDrmScheme);
                        } catch (IllegalStateException e4) {
                            closeDrmSession(sessionType);
                            DLog.warnf("IllegalStateException: %s when querying key status from MediaDrm, returning null", e4);
                            return null;
                        }
                    } catch (MediaDrmResetException e5) {
                        closeDrmSession(sessionType);
                        handleMediaDrmResetException(e5);
                        throw new DrmLicensingException(LicenseError.QUERY_RIGHTS_FAILURE, "MediaDrm threw unexpected MediaDrmResetException querying key status", e5, this.mCurrentDrmScheme);
                    }
                } catch (ResourceBusyException e6) {
                    closeDrmSession(sessionType);
                    throw new DrmLicensingException(LicenseError.QUERY_RIGHTS_FAILURE, "ResourceBusyException: Failed to open MediaDrm session", e6, this.mCurrentDrmScheme);
                }
            } catch (NotProvisionedException e7) {
                closeDrmSession(sessionType);
                handleNotProvisionedException();
                throw new DrmLicensingException(LicenseError.QUERY_RIGHTS_FAILURE, "NotProvisionedException: Provisioning complete, try again", e7, this.mCurrentDrmScheme);
            }
        }
        return map;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeRights(@Nonnull String str, @Nullable String str2, @Nonnull DrmScheme drmScheme) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "keyIdUri");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        if (Strings.isNullOrEmpty(str2)) {
            DLog.logf("Cannot remove rights through MediaDrm without a keySetId, returning.");
            return;
        }
        if (str.equals("playready://AllLicenses.playready") || str.equals("playready://AllExpiredLicenses.playready")) {
            DLog.warnf("MediaDrm doesn't support bulk deleting all/expired licenses though wildcards, returning.");
            return;
        }
        synchronized (this.mMutex) {
            throwIfDrmFrameworkBusy();
            SessionType sessionType = SessionType.IMMEDIATE;
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    try {
                                        ensureMediaDrmLoadedWithScheme(drmScheme, sessionType);
                                        byte[] bArrDecode = Base64.decode(str2);
                                        logMediaDrmOperation(String.format("Removing stored license for keySetId: %s", str2), this.mSessionIdMap.get(sessionType), sessionType);
                                        this.mTimer.start();
                                        this.mMediaDrm.getKeyRequest(bArrDecode, null, null, 3, null);
                                        this.mTimer.stop(String.format("MediaDrm removeRights %s", this.mCurrentDrmScheme));
                                    } catch (IllegalArgumentException e) {
                                        throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "IllegalArgumentException: Failed to decode keySetId from Base64", e, this.mCurrentDrmScheme);
                                    } catch (IllegalStateException e2) {
                                        handleIllegalStateException(e2);
                                        throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "IllegalStateException: Failed to execute getKeyRequest", e2, this.mCurrentDrmScheme);
                                    }
                                } catch (MediaDrmResetException e3) {
                                    handleMediaDrmResetException(e3);
                                    throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "MediaDrm threw unexpected MediaDrmResetException", e3, this.mCurrentDrmScheme);
                                }
                            } catch (NotProvisionedException e4) {
                                handleNotProvisionedException();
                                throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "NotProvisionedException: Provisioning complete, try again", e4, this.mCurrentDrmScheme);
                            }
                        } catch (ResourceBusyException e5) {
                            throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "ResourceBusyException: Failed to open MediaDrm session", e5, this.mCurrentDrmScheme);
                        }
                    } catch (IOException e6) {
                        throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "IOException: Failed to decode the Base64 keySetId", e6, this.mCurrentDrmScheme);
                    }
                } catch (UnsupportedSchemeException e7) {
                    throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "UnsupportedSchemeException: Failed to instantiate MediaDrm object", e7, this.mCurrentDrmScheme);
                }
            } finally {
                closeDrmSession(sessionType);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeAllRights(@Nonnull Set<String> set) throws DrmLicensingException {
        Preconditions.checkNotNull(set, "offlineKeyIdSet");
        if (set.isEmpty()) {
            DLog.logf("Mediadrm removeAllRights passed an empty offline KeySetId Set, returning.");
            return;
        }
        synchronized (this.mMutex) {
            throwIfDrmFrameworkBusy();
            SessionType sessionType = SessionType.IMMEDIATE;
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    try {
                                        DrmScheme currentDrmScheme = getCurrentDrmScheme();
                                        for (String str : set) {
                                            if (!Strings.isNullOrEmpty(str)) {
                                                ensureMediaDrmLoadedWithScheme(currentDrmScheme, sessionType);
                                                byte[] bArrDecode = Base64.decode(str);
                                                logMediaDrmOperation(String.format("Removing stored license for keySetId: %s", str), this.mSessionIdMap.get(sessionType), sessionType);
                                                this.mMediaDrm.getKeyRequest(bArrDecode, null, null, 3, null);
                                                closeDrmSession(sessionType);
                                            }
                                        }
                                        DLog.logf("MediaDrm removed %d licenses from the license store.", Integer.valueOf(set.size()));
                                    } catch (MediaDrmResetException e) {
                                        handleMediaDrmResetException(e);
                                        throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "MediaDrm threw unexpected MediaDrmResetException", e, this.mCurrentDrmScheme);
                                    } catch (IllegalStateException e2) {
                                        handleIllegalStateException(e2);
                                        throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "IllegalStateException: Failed to execute getKeyRequest", e2, this.mCurrentDrmScheme);
                                    }
                                } catch (NotProvisionedException e3) {
                                    handleNotProvisionedException();
                                    throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "NotProvisionedException: Provisioning complete, try again", e3, this.mCurrentDrmScheme);
                                }
                            } catch (IOException e4) {
                                throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "IOException: Failed to decode the Base64 keySetId", e4, this.mCurrentDrmScheme);
                            }
                        } catch (IllegalArgumentException e5) {
                            throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "IllegalArgumentException: Failed to decode keySetId from Base64", e5, this.mCurrentDrmScheme);
                        }
                    } catch (UnsupportedSchemeException e6) {
                        throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "UnsupportedSchemeException: Failed to instantiate MediaDrm object", e6, this.mCurrentDrmScheme);
                    }
                } catch (ResourceBusyException e7) {
                    throw new DrmLicensingException(LicenseError.LICENSE_DELETE_FAILURE, "ResourceBusyException: Failed to open MediaDrm session", e7, this.mCurrentDrmScheme);
                }
            } finally {
                closeDrmSession(sessionType);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void disableDrmClientCalls() {
        synchronized (this.mMutex) {
            this.mActiveDrmSessionCount++;
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void enableDrmClientCalls() {
        synchronized (this.mMutex) {
            int i = this.mActiveDrmSessionCount;
            if (i > 0) {
                this.mActiveDrmSessionCount = i - 1;
            }
            if (this.mActiveDrmSessionCount == 0) {
                closeDrmSession(SessionType.IMMEDIATE);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean areDrmClientCallsEnabled() {
        boolean z = true;
        if (this.mIsMultiSessionEnabled) {
            return true;
        }
        synchronized (this.mMutex) {
            if (this.mActiveDrmSessionCount != 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public DrmScheme getCurrentDrmScheme() {
        return (this.mForceWidevine || this.mMediaConfig.isWidevineEnabled()) ? DrmScheme.WIDEVINE : DrmScheme.PLAYREADY;
    }

    private void closeDrmSession(@Nonnull SessionType sessionType) {
        byte[] bArr;
        if (this.mMediaDrm == null || (bArr = this.mSessionIdMap.get(sessionType)) == null) {
            return;
        }
        try {
            try {
                this.mTimer.start();
                this.mMediaDrm.closeSession(bArr);
                this.mTimer.stop(String.format("MediaDrm.closeSession() %s", this.mCurrentDrmScheme));
                logMediaDrmOperation("Closed session", bArr, sessionType);
            } catch (IllegalStateException e) {
                logMediaDrmOperation(String.format("Close session threw: %s", e), bArr, sessionType);
            }
        } finally {
            this.mSessionIdMap.put(sessionType, (byte[]) null);
            this.mKeySetIdMap.put(sessionType, (String) null);
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public DrmSecurityLevel getDrmSecurityLevel() {
        return this.mDrmSecurityLevel;
    }

    private void throwIfDrmFrameworkBusy() throws DrmLicensingException {
        if (!this.mIsMultiSessionEnabled && this.mActiveDrmSessionCount > 0) {
            throw new DrmLicensingException(LicenseError.AIV_DRM_FRAMEWORK_BUSY, String.format("Playback is currently in progress and multi session is disabled, so the %s DRM framework plugin cannot be used", this.mCurrentDrmScheme));
        }
    }

    private void handleNotProvisionedException() throws Throwable {
        WidevineFieldProvisioner.handleNotProvisionedException(this.mMediaDrm);
    }

    private void handleMediaDrmResetException(MediaDrmResetException mediaDrmResetException) {
        DLog.warnf("MediaServer died: %s! Unloading MediaDrm.", mediaDrmResetException);
        unloadMediaDrm();
    }
}
