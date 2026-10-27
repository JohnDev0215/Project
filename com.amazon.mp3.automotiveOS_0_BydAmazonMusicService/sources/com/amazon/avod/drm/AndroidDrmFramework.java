package com.amazon.avod.drm;

import android.content.ContentValues;
import android.content.Context;
import android.drm.DrmErrorEvent;
import android.drm.DrmEvent;
import android.drm.DrmInfo;
import android.drm.DrmInfoEvent;
import android.drm.DrmInfoRequest;
import android.drm.DrmManagerClient;
import android.os.SystemClock;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.config.LicensingConfig;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.drm.DrmSecurityLevel;
import com.amazon.avod.util.DLog;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidDrmFramework implements DrmFramework, DrmManagerClient.OnEventListener, DrmManagerClient.OnInfoListener, DrmManagerClient.OnErrorListener {
    private final Context mContext;
    private volatile boolean mDrmCallsDisabled;
    private DrmManagerClient mDrmClient;
    private final DrmInfoFactory mDrmInfoFactory;
    private final DrmInfoRequestFactory mDrmInfoRequestFactory;
    private final Object mDrmLock;
    private final DrmManagerClientFactory mDrmManagerClientFactory;
    private final ExecutorService mExecutorService;
    private boolean mIsChallengeReplaySupported;
    private boolean mIsDrmManagerClientCreated;
    private LicenseAcquisitionState mLicenseAcquisitionState;
    private final LicensingConfig mLicensingConfig;
    private final MediaProfiler mMediaProfiler;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private LicenseError mMostRecentLicenseError;
    private String mPlayReadySessionId;
    private final Object mProcessDrmInfoSyncObject;

    private interface DrmCallable<T> {
        T call() throws DrmLicensingException;
    }

    private interface DrmRunnable {
        void run() throws DrmLicensingException;
    }

    private enum LicenseAcquisitionState {
        IDLE,
        WAITING_FOR_RESPONSE,
        PROCESSED_RESPONSE,
        FAILED_TO_PROCESS_RESPONSE
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeAllRights(@Nonnull Set<String> set) throws DrmLicensingException {
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean shutdownSession() {
        return false;
    }

    public AndroidDrmFramework(@Nonnull Context context, @Nonnull MediaProfiler mediaProfiler, @Nonnull ExecutorService executorService) {
        this(context, mediaProfiler, executorService, LicensingConfig.getInstance(), new DrmManagerClientFactory(), new DrmInfoRequestFactory(), new DrmInfoFactory(), MediaSystemSharedDependencies.getInstance());
    }

    AndroidDrmFramework(@Nonnull Context context, @Nonnull MediaProfiler mediaProfiler, @Nonnull ExecutorService executorService, @Nonnull LicensingConfig licensingConfig, @Nonnull DrmManagerClientFactory drmManagerClientFactory, @Nonnull DrmInfoRequestFactory drmInfoRequestFactory, @Nonnull DrmInfoFactory drmInfoFactory, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies) {
        this.mDrmClient = null;
        this.mProcessDrmInfoSyncObject = new Object();
        this.mLicenseAcquisitionState = LicenseAcquisitionState.IDLE;
        this.mMostRecentLicenseError = null;
        this.mIsDrmManagerClientCreated = false;
        this.mDrmLock = new Object();
        this.mDrmCallsDisabled = false;
        this.mDrmManagerClientFactory = (DrmManagerClientFactory) Preconditions.checkNotNull(drmManagerClientFactory, "drmManagerClientFactory");
        this.mDrmInfoRequestFactory = (DrmInfoRequestFactory) Preconditions.checkNotNull(drmInfoRequestFactory, "drmInfoRequestFactory");
        this.mDrmInfoFactory = (DrmInfoFactory) Preconditions.checkNotNull(drmInfoFactory, "drmInfoFactory");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mLicensingConfig = (LicensingConfig) Preconditions.checkNotNull(licensingConfig, "licensingConfig");
        this.mMediaProfiler = (MediaProfiler) Preconditions.checkNotNull(mediaProfiler, "mediaProfiler");
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T callHoldingDrmLock(DrmCallable<T> drmCallable) throws DrmLicensingException {
        T tCall;
        synchronized (this.mDrmLock) {
            if (this.mDrmCallsDisabled) {
                throw new DrmLicensingException(LicenseError.AIV_DRM_FRAMEWORK_BUSY, "Playback is currently in progress, so the DRM framework plugin cannot be used.");
            }
            ensureDrmClientAvailable();
            tCall = drmCallable.call();
        }
        return tCall;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runHoldingDrmLock(DrmRunnable drmRunnable) throws DrmLicensingException {
        synchronized (this.mDrmLock) {
            if (this.mDrmCallsDisabled) {
                throw new DrmLicensingException(LicenseError.AIV_DRM_FRAMEWORK_BUSY, "Playback is currently in progress, so the DRM framework plugin cannot be used.");
            }
            ensureDrmClientAvailable();
            drmRunnable.run();
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework, com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
        boolean z = this.mLicensingConfig.isAndroidDrmFrameworkChallengeReplaySupported() && !this.mMediaSystemSharedDependencies.isSDKPlayer();
        this.mIsChallengeReplaySupported = z;
        DLog.logf("AndroidDrmFramework: Challenge replay support=%s", Boolean.valueOf(z));
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.drm.AndroidDrmFramework.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (AndroidDrmFramework.this.mDrmLock) {
                    if (AndroidDrmFramework.this.mDrmCallsDisabled) {
                        return;
                    }
                    AndroidDrmFramework.this.ensureDrmClientAvailable();
                }
            }
        });
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void disableDrmClientCalls() throws DrmLicensingException {
        synchronized (this.mDrmLock) {
            this.mLicenseAcquisitionState = LicenseAcquisitionState.IDLE;
            if (this.mDrmCallsDisabled) {
                throw new DrmLicensingException(LicenseError.AIV_DRM_FRAMEWORK_BUSY, "DRM calls were already disabled!");
            }
            this.mDrmCallsDisabled = true;
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void enableDrmClientCalls() {
        synchronized (this.mDrmLock) {
            if (this.mDrmCallsDisabled) {
                this.mLicenseAcquisitionState = LicenseAcquisitionState.IDLE;
                this.mDrmCallsDisabled = false;
            } else {
                DLog.warnf("Trying to realease lock when drm calls are already enabled");
            }
        }
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean areDrmClientCallsEnabled() {
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
        return DrmSecurityLevel.SL_2000;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public List<DrmScheme> getSupportedDrmSchemes() {
        return ImmutableList.of(DrmScheme.PLAYREADY);
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nullable
    public Map<String, Object> getRights(@Nonnull final String str, @Nullable String str2, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "uri");
        return (Map) callHoldingDrmLock(new DrmCallable<Map<String, Object>>() { // from class: com.amazon.avod.drm.AndroidDrmFramework.2
            @Override // com.amazon.avod.drm.AndroidDrmFramework.DrmCallable
            public Map<String, Object> call() throws DrmLicensingException {
                DLog.logf("AndroidDrmFramework.getRights URI = %s", str);
                ContentValues contentValues = (ContentValues) AndroidDrmFramework.this.callHoldingDrmLock(new DrmCallable<ContentValues>() { // from class: com.amazon.avod.drm.AndroidDrmFramework.2.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.amazon.avod.drm.AndroidDrmFramework.DrmCallable
                    public ContentValues call() throws DrmLicensingException {
                        DrmManagerClient drmManagerClient = AndroidDrmFramework.this.mDrmClient;
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        return drmManagerClient.getConstraints(AndroidDrmFramework.this.buildRequestXmlString(str), 0);
                    }
                });
                if (contentValues == null) {
                    return null;
                }
                HashMap map = new HashMap();
                for (String str3 : contentValues.keySet()) {
                    map.put(str3, contentValues.get(str3));
                }
                return map;
            }
        });
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public void removeRights(@Nonnull final String str, @Nullable String str2, @Nonnull DrmScheme drmScheme) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "uri");
        runHoldingDrmLock(new DrmRunnable() { // from class: com.amazon.avod.drm.AndroidDrmFramework.3
            @Override // com.amazon.avod.drm.AndroidDrmFramework.DrmRunnable
            public void run() throws DrmLicensingException {
                DLog.logf("AndroidDrmFramework.removeRights URI = %s", str);
                AndroidDrmFramework.this.runHoldingDrmLock(new DrmRunnable() { // from class: com.amazon.avod.drm.AndroidDrmFramework.3.1
                    @Override // com.amazon.avod.drm.AndroidDrmFramework.DrmRunnable
                    public void run() throws DrmLicensingException {
                        DrmManagerClient drmManagerClient = AndroidDrmFramework.this.mDrmClient;
                        AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                        int iRemoveRights = drmManagerClient.removeRights(AndroidDrmFramework.this.buildRequestXmlString(str));
                        if (iRemoveRights != 0) {
                            AnonymousClass3 anonymousClass32 = AnonymousClass3.this;
                            DLog.errorf("DrmManagerClient.removeRights(\"%s\") returned %s", str, AndroidDrmFramework.this.getErrorName(iRemoveRights));
                        }
                    }
                });
            }
        });
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    public boolean supportsChallengeReplay() {
        return this.mIsChallengeReplaySupported;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nonnull
    public byte[] generateLicenseChallenge(@Nonnull final String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        Preconditions.checkNotNull(str, "header");
        return (byte[]) callHoldingDrmLock(new DrmCallable<byte[]>() { // from class: com.amazon.avod.drm.AndroidDrmFramework.4
            @Override // com.amazon.avod.drm.AndroidDrmFramework.DrmCallable
            public byte[] call() throws DrmLicensingException {
                DLog.logf("PlayReady header = %s", str);
                return (byte[]) AndroidDrmFramework.this.callHoldingDrmLock(new DrmCallable<byte[]>() { // from class: com.amazon.avod.drm.AndroidDrmFramework.4.1
                    @Override // com.amazon.avod.drm.AndroidDrmFramework.DrmCallable
                    public byte[] call() throws DrmLicensingException {
                        DrmInfoRequest drmInfoRequestCreateDrmInfoRequest = AndroidDrmFramework.this.mDrmInfoRequestFactory.createDrmInfoRequest(3, "application/vnd.ms-playready.media.pyv");
                        AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                        drmInfoRequestCreateDrmInfoRequest.put("Header", AndroidDrmFramework.this.buildRequestXmlString(str));
                        DrmInfo drmInfoAcquireDrmInfo = AndroidDrmFramework.this.mDrmClient.acquireDrmInfo(drmInfoRequestCreateDrmInfoRequest);
                        if (drmInfoAcquireDrmInfo == null) {
                            DLog.errorf("DrmManagerClient.acquireDrmInfo() returned a null value.");
                            AndroidDrmFramework.this.fail(LicenseError.GENERATE_CHALLENGE_FAILURE);
                        } else {
                            byte[] data = drmInfoAcquireDrmInfo.getData();
                            if (data != null && data.length != 0) {
                                return AndroidDrmFramework.this.validateLicenseChallenge(data);
                            }
                            String str2 = (String) drmInfoAcquireDrmInfo.get("error");
                            Object[] objArr = new Object[1];
                            if (str2 == null) {
                                str2 = "";
                            }
                            objArr[0] = str2;
                            DLog.errorf("DrmManagerClient.acquireDrmInfo() returned a null or empty challenge. %s", objArr);
                            AndroidDrmFramework.this.fail(LicenseError.GENERATE_CHALLENGE_FAILURE);
                        }
                        return null;
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] validateLicenseChallenge(byte[] bArr) throws DrmLicensingException {
        LicenseError licenseErrorFromErrorString;
        if (bArr.length > 32) {
            return bArr;
        }
        DLog.warnf("Invalid %s-byte license challenge: 0x%s", Integer.valueOf(bArr.length), Arrays.toString(bArr));
        if (bArr.length == 4) {
            int iLittleEndianBytesToInt = littleEndianBytesToInt(bArr);
            if (iLittleEndianBytesToInt == 0) {
                DLog.logf("License already present");
                return null;
            }
            licenseErrorFromErrorString = LicenseError.fromErrorCode(iLittleEndianBytesToInt);
        } else {
            licenseErrorFromErrorString = LicenseError.fromErrorString(new String(bArr));
        }
        if (licenseErrorFromErrorString == LicenseError.UNKNOWN_PLAYREADY_ERROR) {
            DLog.warnf("Failed to interpret the invalid license challenge as an error code");
            fail(LicenseError.GENERATE_CHALLENGE_FAILURE);
        } else {
            DLog.logf("Interpreted the challenge as %s", licenseErrorFromErrorString);
            fail(licenseErrorFromErrorString);
        }
        return null;
    }

    @Override // com.amazon.avod.media.playback.support.DrmFramework
    @Nullable
    public String processLicenseResponse(@Nonnull final String str, @Nonnull DrmScheme drmScheme, boolean z) throws DrmLicensingException {
        return (String) callHoldingDrmLock(new DrmCallable<String>() { // from class: com.amazon.avod.drm.AndroidDrmFramework.5
            @Override // com.amazon.avod.drm.AndroidDrmFramework.DrmCallable
            public String call() throws DrmLicensingException {
                DLog.logf("Processing license response");
                DrmInfo drmInfoCreateDrmInfo = AndroidDrmFramework.this.mDrmInfoFactory.createDrmInfo(3, new byte[1], "application/vnd.ms-playready.media.pyv");
                drmInfoCreateDrmInfo.put("Response", AndroidDrmFramework.this.buildRequestXmlString(str));
                synchronized (AndroidDrmFramework.this.mProcessDrmInfoSyncObject) {
                    AndroidDrmFramework.this.mLicenseAcquisitionState = LicenseAcquisitionState.WAITING_FOR_RESPONSE;
                    AndroidDrmFramework.this.mMostRecentLicenseError = null;
                    int iProcessDrmInfo = AndroidDrmFramework.this.mDrmClient.processDrmInfo(drmInfoCreateDrmInfo);
                    if (iProcessDrmInfo != 0) {
                        DLog.warnf("DrmManagerClient.processDrmInfo() returned %s", AndroidDrmFramework.this.getErrorName(iProcessDrmInfo));
                        AndroidDrmFramework.this.fail(LicenseError.PROCESS_RESPONSE_FAILURE);
                    }
                    AndroidDrmFramework.this.waitForPlayReadyCallback();
                    int i = AnonymousClass6.$SwitchMap$com$amazon$avod$drm$AndroidDrmFramework$LicenseAcquisitionState[AndroidDrmFramework.this.mLicenseAcquisitionState.ordinal()];
                    if (i == 1) {
                        DLog.logf("License processed and stored");
                    } else if (i == 2) {
                        DLog.errorf("PlayReady failed to process license response");
                        if (AndroidDrmFramework.this.mMostRecentLicenseError == null) {
                            AndroidDrmFramework.this.fail(LicenseError.PROCESS_RESPONSE_FAILURE);
                        } else {
                            AndroidDrmFramework androidDrmFramework = AndroidDrmFramework.this;
                            androidDrmFramework.fail(androidDrmFramework.mMostRecentLicenseError);
                        }
                    } else if (i != 3) {
                        DLog.errorf("Impossible license acquisition state: %s", AndroidDrmFramework.this.mLicenseAcquisitionState);
                    } else {
                        DLog.errorf("PlayReady timed out processing license response");
                        AndroidDrmFramework.this.fail(LicenseError.PLAYREADY_PROCESS_RESPONSE_TIMEOUT);
                    }
                    AndroidDrmFramework.this.mLicenseAcquisitionState = LicenseAcquisitionState.IDLE;
                }
                return null;
            }
        });
    }

    /* JADX INFO: renamed from: com.amazon.avod.drm.AndroidDrmFramework$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$drm$AndroidDrmFramework$LicenseAcquisitionState;

        static {
            int[] iArr = new int[LicenseAcquisitionState.values().length];
            $SwitchMap$com$amazon$avod$drm$AndroidDrmFramework$LicenseAcquisitionState = iArr;
            try {
                iArr[LicenseAcquisitionState.PROCESSED_RESPONSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$drm$AndroidDrmFramework$LicenseAcquisitionState[LicenseAcquisitionState.FAILED_TO_PROCESS_RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$drm$AndroidDrmFramework$LicenseAcquisitionState[LicenseAcquisitionState.WAITING_FOR_RESPONSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureDrmClientAvailable() {
        Preconditions.checkState(Thread.holdsLock(this.mDrmLock), "Must call holding mDrmLock!");
        if (this.mIsDrmManagerClientCreated) {
            return;
        }
        DLog.logf("Creating DrmManagerClient...");
        this.mMediaProfiler.start("drmCreateLicenseManager");
        DrmManagerClient drmManagerClientCreateDrmManagerClientUsingContext = this.mDrmManagerClientFactory.createDrmManagerClientUsingContext(this.mContext);
        this.mDrmClient = drmManagerClientCreateDrmManagerClientUsingContext;
        drmManagerClientCreateDrmManagerClientUsingContext.setOnEventListener(this);
        this.mDrmClient.setOnInfoListener(this);
        this.mDrmClient.setOnErrorListener(this);
        this.mMediaProfiler.stop("drmCreateLicenseManager");
        DLog.logf("DrmManagerClient created successfully");
        this.mIsDrmManagerClientCreated = true;
        this.mPlayReadySessionId = null;
        preloadPlayReady();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForPlayReadyCallback() {
        LicenseAcquisitionState licenseAcquisitionState;
        LicenseAcquisitionState licenseAcquisitionState2;
        long totalMilliseconds = this.mLicensingConfig.getCallTimeout().getTotalMilliseconds();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = jElapsedRealtime + totalMilliseconds;
        DLog.devf("Waiting for %dms for PlayReady to process the license response", Long.valueOf(totalMilliseconds));
        boolean z = false;
        while (true) {
            licenseAcquisitionState = this.mLicenseAcquisitionState;
            licenseAcquisitionState2 = LicenseAcquisitionState.WAITING_FOR_RESPONSE;
            if (licenseAcquisitionState != licenseAcquisitionState2 || jElapsedRealtime >= j) {
                break;
            }
            try {
                this.mProcessDrmInfoSyncObject.wait(j - jElapsedRealtime);
            } catch (InterruptedException e) {
                DLog.devf("%s: ignoring, but preserving thread's interrupted state", e.getMessage());
                z = true;
            }
            jElapsedRealtime = SystemClock.elapsedRealtime();
        }
        if (licenseAcquisitionState == licenseAcquisitionState2) {
            DLog.logf("PlayReady did not process license response within %sms", Long.valueOf(totalMilliseconds));
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fail(LicenseError licenseError) throws DrmLicensingException {
        this.mLicenseAcquisitionState = LicenseAcquisitionState.IDLE;
        throw new DrmLicensingException(licenseError, "error from PlayReady DRM Framework");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getErrorName(int i) {
        return i == 0 ? "ERROR_NONE" : i == -2000 ? "ERROR_UNKNOWN" : String.format(Locale.US, "unexpected error code %s", Integer.valueOf(i));
    }

    private static int littleEndianBytesToInt(byte[] bArr) {
        return ((bArr[3] & 255) << 0) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    @Override // android.drm.DrmManagerClient.OnEventListener
    public void onEvent(DrmManagerClient drmManagerClient, DrmEvent drmEvent) {
        DLog.logf("Received DRM manager event: %s %s", getEventTypeString(drmEvent), drmEvent.getMessage());
        if (drmEvent.getType() == 1002) {
            synchronized (this.mProcessDrmInfoSyncObject) {
                if (this.mLicenseAcquisitionState == LicenseAcquisitionState.WAITING_FOR_RESPONSE) {
                    this.mLicenseAcquisitionState = LicenseAcquisitionState.PROCESSED_RESPONSE;
                    this.mProcessDrmInfoSyncObject.notifyAll();
                } else {
                    DLog.warnf("DRM_INFO_PROCESSED event unexpected at this time");
                }
            }
        }
    }

    @Override // android.drm.DrmManagerClient.OnInfoListener
    public void onInfo(DrmManagerClient drmManagerClient, DrmInfoEvent drmInfoEvent) {
        DLog.logf("Received DRM manager info: %s %s", getInfoTypeString(drmInfoEvent), drmInfoEvent.getMessage());
    }

    @Override // android.drm.DrmManagerClient.OnErrorListener
    public void onError(DrmManagerClient drmManagerClient, DrmErrorEvent drmErrorEvent) {
        String message = drmErrorEvent.getMessage();
        DLog.warnf("Received DRM manager error %s with message \"%s\"", getErrorTypeString(drmErrorEvent), message);
        if (drmErrorEvent.getType() == 2006) {
            synchronized (this.mProcessDrmInfoSyncObject) {
                if (this.mLicenseAcquisitionState == LicenseAcquisitionState.WAITING_FOR_RESPONSE) {
                    this.mLicenseAcquisitionState = LicenseAcquisitionState.FAILED_TO_PROCESS_RESPONSE;
                    this.mMostRecentLicenseError = LicenseError.fromErrorString(message);
                    this.mProcessDrmInfoSyncObject.notifyAll();
                } else {
                    DLog.warnf("PROCESS_DRM_INFO_FAILED error unexpected at this time");
                }
            }
        }
    }

    private void preloadPlayReady() {
        DrmInfo drmInfoPreloadPlayReadySession = preloadPlayReadySession(true);
        if (drmInfoPreloadPlayReadySession != null) {
            Object obj = drmInfoPreloadPlayReadySession.get("sessionid");
            if (obj != null) {
                String str = (String) obj;
                this.mPlayReadySessionId = str;
                DLog.logf("New session %s is created.", str);
                return;
            }
            DLog.logf("Preloaded PlayReady engine succeeded but no session information is returned. The underlying DRM sub-system doesn't support sessions.");
        }
    }

    @Nullable
    private DrmInfo preloadPlayReadySession(boolean z) {
        int iLittleEndianBytesToInt;
        DrmInfoRequest drmInfoRequest = new DrmInfoRequest(3, "application/vnd.ms-playready.media.pyv");
        drmInfoRequest.put("Header", z ? "GetPersistentLicSession" : "EnterPersistentTZMode");
        DrmInfo drmInfoAcquireDrmInfo = this.mDrmClient.acquireDrmInfo(drmInfoRequest);
        if (drmInfoAcquireDrmInfo == null) {
            DLog.logf("Failed to acquire PlayReady session; normal on TI platforms");
            return null;
        }
        String str = (String) drmInfoAcquireDrmInfo.get("error");
        if (str != null) {
            DLog.logf("Failed to acquire PlayReady session. Error returned: %s", str);
            return null;
        }
        byte[] data = drmInfoAcquireDrmInfo.getData();
        if (data != null) {
            if (data.length == 10) {
                int iErrorCodeFromString = LicenseError.errorCodeFromString(new String(data));
                if (iErrorCodeFromString <= 0) {
                    if (z) {
                        DLog.logf("PlayReady engine doesn't support session-aware initialization. Status: 0x%x. This is normal on session unaware platforms.", Integer.valueOf(iErrorCodeFromString));
                        DLog.logf("Trying session-unaware initialization");
                        return preloadPlayReadySession(false);
                    }
                    DLog.warnf("PlayReady engine failed session-unaware initialization. Status: 0x%x. This is not a critical failure.", Integer.valueOf(iErrorCodeFromString));
                    return null;
                }
            } else if (data.length == 4 && (iLittleEndianBytesToInt = littleEndianBytesToInt(data)) != 0) {
                DLog.warnf("PlayReady engine doesn't support pre-initialization. Status: 0x%x. This is not a critical failure.", Integer.valueOf(iLittleEndianBytesToInt));
                return null;
            }
        }
        DLog.logf("Preloaded PlayReady engine to optimize license operations.");
        return drmInfoAcquireDrmInfo;
    }

    private String getErrorTypeString(DrmErrorEvent drmErrorEvent) {
        switch (drmErrorEvent.getType()) {
            case CastStatusCodes.INVALID_REQUEST /* 2001 */:
                return "TYPE_RIGHTS_NOT_INSTALLED";
            case CastStatusCodes.CANCELED /* 2002 */:
                return "TYPE_RIGHTS_RENEWAL_NOT_ALLOWED";
            case CastStatusCodes.NOT_ALLOWED /* 2003 */:
                return "TYPE_NOT_SUPPORTED";
            case CastStatusCodes.APPLICATION_NOT_FOUND /* 2004 */:
                return "TYPE_OUT_OF_MEMORY";
            case CastStatusCodes.APPLICATION_NOT_RUNNING /* 2005 */:
                return "TYPE_NO_INTERNET_CONNECTION";
            case CastStatusCodes.MESSAGE_TOO_LARGE /* 2006 */:
                return "TYPE_PROCESS_DRM_INFO_FAILED";
            case CastStatusCodes.MESSAGE_SEND_BUFFER_TOO_FULL /* 2007 */:
                return "TYPE_REMOVE_ALL_RIGHTS_FAILED";
            case 2008:
                return "TYPE_ACQUIRE_DRM_INFO_FAILED";
            default:
                return Integer.toString(drmErrorEvent.getType());
        }
    }

    private String getInfoTypeString(DrmInfoEvent drmInfoEvent) {
        int type = drmInfoEvent.getType();
        return type != 1 ? type != 2 ? type != 3 ? type != 4 ? type != 5 ? Integer.toString(drmInfoEvent.getType()) : "TYPE_ACCOUNT_ALREADY_REGISTERED" : "TYPE_WAIT_FOR_RIGHTS" : "TYPE_RIGHTS_INSTALLED" : "TYPE_REMOVE_RIGHTS" : "TYPE_ALREADY_REGISTERED_BY_ANOTHER_ACCOUNT";
    }

    private String getEventTypeString(DrmEvent drmEvent) {
        int type = drmEvent.getType();
        return type != 1001 ? type != 1002 ? Integer.toString(drmEvent.getType()) : "TYPE_DRM_INFO_PROCESSED" : "TYPE_ALL_RIGHTS_REMOVED";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String buildRequestXmlString(String str) {
        Preconditions.checkNotNull(str);
        String str2 = this.mPlayReadySessionId;
        return str2 == null ? str : String.format(Locale.US, "<data>%s</data><sessionid>%s</sessionid>.playready", str, str2);
    }

    static class DrmManagerClientFactory {
        DrmManagerClientFactory() {
        }

        public DrmManagerClient createDrmManagerClientUsingContext(Context context) {
            return new DrmManagerClient(context);
        }
    }

    static class DrmInfoRequestFactory {
        DrmInfoRequestFactory() {
        }

        public DrmInfoRequest createDrmInfoRequest(int i, String str) {
            return new DrmInfoRequest(i, str);
        }
    }

    static class DrmInfoFactory {
        DrmInfoFactory() {
        }

        public DrmInfo createDrmInfo(int i, byte[] bArr, String str) {
            return new DrmInfo(i, bArr, str);
        }
    }
}
