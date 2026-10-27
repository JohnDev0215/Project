package org.chromium.media;

import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.os.Handler;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
public class MediaDrmBridge {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ENABLE = "enable";
    private static final long INVALID_NATIVE_MEDIA_DRM_BRIDGE = 0;
    private static final String PRIVACY_MODE = "privacyMode";
    private static final String SECURITY_LEVEL = "securityLevel";
    private static final String SERVER_CERTIFICATE = "serviceCertificate";
    private static final String SESSION_SHARING = "sessionSharing";
    private static final String TAG = "cr_media";
    private byte[] mMediaCryptoSession;
    private MediaDrm mMediaDrm;
    private long mNativeMediaDrmBridge;
    private UUID mSchemeUUID;
    private static final char[] HEX_CHAR_LOOKUP = "0123456789ABCDEF".toCharArray();
    private static final UUID WIDEVINE_UUID = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed");
    private static final byte[] DUMMY_KEY_ID = {0};
    private HashMap<ByteBuffer, String> mSessionIds = new HashMap<>();
    private ArrayDeque<PendingCreateSessionData> mPendingCreateSessionDataQueue = new ArrayDeque<>();
    private boolean mResetDeviceCredentialsPending = false;
    private boolean mProvisioningPending = false;

    private native void nativeOnLegacySessionError(long j, byte[] bArr, String str);

    private native void nativeOnMediaCryptoReady(long j, MediaCrypto mediaCrypto);

    private native void nativeOnPromiseRejected(long j, long j2, String str);

    private native void nativeOnPromiseResolved(long j, long j2);

    private native void nativeOnPromiseResolvedWithSession(long j, long j2, byte[] bArr);

    private native void nativeOnResetDeviceCredentialsCompleted(long j, boolean z);

    private native void nativeOnSessionClosed(long j, byte[] bArr);

    private native void nativeOnSessionExpirationUpdate(long j, byte[] bArr, long j2);

    private native void nativeOnSessionKeysChange(long j, byte[] bArr, Object[] objArr, boolean z);

    private native void nativeOnSessionMessage(long j, byte[] bArr, int i, byte[] bArr2, String str);

    private native void nativeOnStartProvisioning(long j, String str, byte[] bArr);

    private static class KeyStatus {
        private final byte[] mKeyId;
        private final int mStatusCode;

        private KeyStatus(byte[] bArr, int i) {
            this.mKeyId = bArr;
            this.mStatusCode = i;
        }

        private byte[] getKeyId() {
            return this.mKeyId;
        }

        private int getStatusCode() {
            return this.mStatusCode;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<KeyStatus> getDummyKeysInfo(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyStatus(DUMMY_KEY_ID, i));
        return arrayList;
    }

    private static class PendingCreateSessionData {
        private final byte[] mInitData;
        private final String mMimeType;
        private final HashMap<String, String> mOptionalParameters;
        private final long mPromiseId;

        private PendingCreateSessionData(byte[] bArr, String str, HashMap<String, String> map, long j) {
            this.mInitData = bArr;
            this.mMimeType = str;
            this.mOptionalParameters = map;
            this.mPromiseId = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] initData() {
            return this.mInitData;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String mimeType() {
            return this.mMimeType;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HashMap<String, String> optionalParameters() {
            return this.mOptionalParameters;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long promiseId() {
            return this.mPromiseId;
        }
    }

    private static UUID getUUIDFromBytes(byte[] bArr) {
        if (bArr.length != 16) {
            return null;
        }
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < 8; i++) {
            j2 = (j2 << 8) | ((long) (bArr[i] & 255));
        }
        for (int i2 = 8; i2 < 16; i2++) {
            j = (j << 8) | ((long) (bArr[i2] & 255));
        }
        return new UUID(j2, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            char[] cArr = HEX_CHAR_LOOKUP;
            sb.append(cArr[bArr[i] >>> 4]);
            sb.append(cArr[bArr[i] & 15]);
        }
        return sb.toString();
    }

    private boolean isNativeMediaDrmBridgeValid() {
        return this.mNativeMediaDrmBridge != 0;
    }

    private boolean isWidevine() {
        return this.mSchemeUUID.equals(WIDEVINE_UUID);
    }

    private MediaDrmBridge(UUID uuid, long j) throws UnsupportedSchemeException {
        this.mSchemeUUID = uuid;
        this.mMediaDrm = new MediaDrm(uuid);
        this.mNativeMediaDrmBridge = j;
        this.mMediaDrm.setOnEventListener(new EventListener());
        this.mMediaDrm.setOnExpirationUpdateListener(new ExpirationUpdateListener(), (Handler) null);
        this.mMediaDrm.setOnKeyStatusChangeListener(new KeyStatusChangeListener(), (Handler) null);
        if (isWidevine()) {
            this.mMediaDrm.setPropertyString(PRIVACY_MODE, ENABLE);
            this.mMediaDrm.setPropertyString(SESSION_SHARING, ENABLE);
        }
    }

    private boolean createMediaCrypto() {
        try {
            byte[] bArrOpenSession = openSession();
            this.mMediaCryptoSession = bArrOpenSession;
            if (bArrOpenSession == null) {
                Log.e(TAG, "Cannot create MediaCrypto Session.", new Object[0]);
                return false;
            }
            Log.d(TAG, "MediaCrypto Session created: %s", bytesToHexString(bArrOpenSession));
            try {
            } catch (MediaCryptoException e) {
                Log.e(TAG, "Cannot create MediaCrypto", e);
            }
            if (MediaCrypto.isCryptoSchemeSupported(this.mSchemeUUID)) {
                MediaCrypto mediaCrypto = new MediaCrypto(this.mSchemeUUID, this.mMediaCryptoSession);
                Log.d(TAG, "MediaCrypto successfully created!");
                onMediaCryptoReady(mediaCrypto);
                return true;
            }
            Log.e(TAG, "Cannot create MediaCrypto for unsupported scheme.", new Object[0]);
            try {
                this.mMediaDrm.closeSession(this.mMediaCryptoSession);
            } catch (Exception e2) {
                Log.e(TAG, "closeSession failed: ", e2);
            }
            this.mMediaCryptoSession = null;
            return false;
        } catch (NotProvisionedException e3) {
            Log.d(TAG, "Device not provisioned", e3);
            startProvisioning();
            return true;
        }
    }

    private byte[] openSession() throws NotProvisionedException {
        try {
            return (byte[]) this.mMediaDrm.openSession().clone();
        } catch (NotProvisionedException e) {
            throw e;
        } catch (MediaDrmException e2) {
            Log.e(TAG, "Cannot open a new session", e2);
            release();
            return null;
        } catch (RuntimeException e3) {
            Log.e(TAG, "Cannot open a new session", e3);
            release();
            return null;
        }
    }

    private static boolean isCryptoSchemeSupported(byte[] bArr, String str) {
        UUID uUIDFromBytes = getUUIDFromBytes(bArr);
        if (str.isEmpty()) {
            return MediaDrm.isCryptoSchemeSupported(uUIDFromBytes);
        }
        return MediaDrm.isCryptoSchemeSupported(uUIDFromBytes, str);
    }

    private static MediaDrmBridge create(byte[] bArr, String str, long j) {
        UUID uUIDFromBytes = getUUIDFromBytes(bArr);
        if (uUIDFromBytes != null && MediaDrm.isCryptoSchemeSupported(uUIDFromBytes)) {
            try {
                MediaDrmBridge mediaDrmBridge = new MediaDrmBridge(uUIDFromBytes, j);
                Log.d(TAG, "MediaDrmBridge successfully created.");
                if ((str.isEmpty() || mediaDrmBridge.setSecurityLevel(str)) && mediaDrmBridge.createMediaCrypto()) {
                    return mediaDrmBridge;
                }
                return null;
            } catch (UnsupportedSchemeException e) {
                Log.e(TAG, "Unsupported DRM scheme", e);
            } catch (IllegalArgumentException e2) {
                Log.e(TAG, "Failed to create MediaDrmBridge", e2);
                return null;
            } catch (IllegalStateException e3) {
                Log.e(TAG, "Failed to create MediaDrmBridge", e3);
                return null;
            }
        }
        return null;
    }

    private boolean setSecurityLevel(String str) {
        if (!isWidevine()) {
            Log.d(TAG, "Security level is not supported.");
            return true;
        }
        String propertyString = this.mMediaDrm.getPropertyString(SECURITY_LEVEL);
        Log.e(TAG, "Security level: current %s, new %s", propertyString, str);
        if (str.equals(propertyString)) {
            return true;
        }
        try {
            this.mMediaDrm.setPropertyString(SECURITY_LEVEL, str);
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to set security level %s", str, e);
            Log.e(TAG, "Security level %s not supported!", str);
            return false;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to set security level %s", str, e2);
            Log.e(TAG, "Security level %s not supported!", str);
            return false;
        }
    }

    private boolean setServerCertificate(byte[] bArr) {
        if (!isWidevine()) {
            Log.d(TAG, "Setting server certificate is not supported.");
            return true;
        }
        try {
            this.mMediaDrm.setPropertyByteArray(SERVER_CERTIFICATE, bArr);
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to set server certificate", e);
            return false;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to set server certificate", e2);
            return false;
        }
    }

    private void resetDeviceCredentials() {
        if (this.mMediaDrm == null) {
            onResetDeviceCredentialsCompleted(false);
        } else {
            this.mResetDeviceCredentialsPending = true;
            startProvisioning();
        }
    }

    private void destroy() {
        this.mNativeMediaDrmBridge = 0L;
        if (this.mMediaDrm != null) {
            release();
        }
    }

    private void release() {
        Iterator<PendingCreateSessionData> it = this.mPendingCreateSessionDataQueue.iterator();
        while (it.hasNext()) {
            onPromiseRejected(it.next().promiseId(), "Create session aborted.");
        }
        this.mPendingCreateSessionDataQueue.clear();
        this.mPendingCreateSessionDataQueue = null;
        for (ByteBuffer byteBuffer : this.mSessionIds.keySet()) {
            try {
                this.mMediaDrm.removeKeys(byteBuffer.array());
            } catch (Exception e) {
                Log.e(TAG, "removeKeys failed: ", e);
            }
            try {
                this.mMediaDrm.closeSession(byteBuffer.array());
            } catch (Exception e2) {
                Log.e(TAG, "closeSession failed: ", e2);
            }
            onSessionClosed(byteBuffer.array());
        }
        this.mSessionIds.clear();
        this.mSessionIds = null;
        byte[] bArr = this.mMediaCryptoSession;
        if (bArr == null) {
            onMediaCryptoReady(null);
        } else {
            try {
                this.mMediaDrm.closeSession(bArr);
            } catch (Exception e3) {
                Log.e(TAG, "closeSession failed: ", e3);
            }
            this.mMediaCryptoSession = null;
        }
        if (this.mResetDeviceCredentialsPending) {
            this.mResetDeviceCredentialsPending = false;
            onResetDeviceCredentialsCompleted(false);
        }
        MediaDrm mediaDrm = this.mMediaDrm;
        if (mediaDrm != null) {
            mediaDrm.release();
            this.mMediaDrm = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaDrm.KeyRequest getKeyRequest(byte[] bArr, byte[] bArr2, String str, HashMap<String, String> map) throws NotProvisionedException {
        MediaDrm.KeyRequest keyRequest;
        if (map == null) {
            map = new HashMap<>();
        }
        try {
            keyRequest = this.mMediaDrm.getKeyRequest(bArr, bArr2, str, 1, map);
        } catch (IllegalStateException e) {
            if (e instanceof MediaDrm.MediaDrmStateException) {
                Log.e(TAG, "MediaDrmStateException fired during getKeyRequest().", e);
            }
            keyRequest = null;
        }
        Log.d(TAG, "getKeyRequest %s!", keyRequest != null ? "successed" : "failed");
        return keyRequest;
    }

    private void savePendingCreateSessionData(byte[] bArr, String str, HashMap<String, String> map, long j) {
        Log.d(TAG, "savePendingCreateSessionData()");
        this.mPendingCreateSessionDataQueue.offer(new PendingCreateSessionData(bArr, str, map, j));
    }

    private void processPendingCreateSessionData() {
        Log.d(TAG, "processPendingCreateSessionData()");
        while (this.mMediaDrm != null && !this.mProvisioningPending && !this.mPendingCreateSessionDataQueue.isEmpty()) {
            PendingCreateSessionData pendingCreateSessionDataPoll = this.mPendingCreateSessionDataQueue.poll();
            createSession(pendingCreateSessionDataPoll.initData(), pendingCreateSessionDataPoll.mimeType(), pendingCreateSessionDataPoll.optionalParameters(), pendingCreateSessionDataPoll.promiseId());
        }
    }

    private void createSessionFromNative(byte[] bArr, String str, String[] strArr, long j) {
        HashMap<String, String> map = new HashMap<>();
        if (strArr != null) {
            if (strArr.length % 2 != 0) {
                throw new IllegalArgumentException("Additional data array doesn't have equal keys/values");
            }
            for (int i = 0; i < strArr.length; i += 2) {
                map.put(strArr[i], strArr[i + 1]);
            }
        }
        createSession(bArr, str, map, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void createSession(byte[] r9, java.lang.String r10, java.util.HashMap<java.lang.String, java.lang.String> r11, long r12) {
        /*
            r8 = this;
            java.lang.String r0 = "closeSession failed"
            java.lang.String r1 = "createSession()"
            java.lang.String r2 = "cr_media"
            org.chromium.base.Log.d(r2, r1)
            android.media.MediaDrm r1 = r8.mMediaDrm
            r3 = 0
            if (r1 != 0) goto L1b
            java.lang.String r9 = "createSession() called when MediaDrm is null."
            java.lang.Object[] r10 = new java.lang.Object[r3]
            org.chromium.base.Log.e(r2, r9, r10)
            java.lang.String r9 = "MediaDrm released previously."
            r8.onPromiseRejected(r12, r9)
            return
        L1b:
            boolean r1 = r8.mProvisioningPending
            if (r1 == 0) goto L23
            r8.savePendingCreateSessionData(r9, r10, r11, r12)
            return
        L23:
            r1 = 1
            byte[] r4 = r8.openSession()     // Catch: android.media.NotProvisionedException -> L68
            if (r4 != 0) goto L32
            java.lang.String r5 = "Open session failed."
            r8.onPromiseRejected(r12, r5)     // Catch: android.media.NotProvisionedException -> L30
            return
        L30:
            r5 = move-exception
            goto L6a
        L32:
            android.media.MediaDrm$KeyRequest r5 = r8.getKeyRequest(r4, r9, r10, r11)     // Catch: android.media.NotProvisionedException -> L65
            if (r5 != 0) goto L4c
            android.media.MediaDrm r5 = r8.mMediaDrm     // Catch: java.lang.Exception -> L3e android.media.NotProvisionedException -> L65
            r5.closeSession(r4)     // Catch: java.lang.Exception -> L3e android.media.NotProvisionedException -> L65
            goto L46
        L3e:
            r5 = move-exception
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: android.media.NotProvisionedException -> L65
            r6[r3] = r5     // Catch: android.media.NotProvisionedException -> L65
            org.chromium.base.Log.e(r2, r0, r6)     // Catch: android.media.NotProvisionedException -> L65
        L46:
            java.lang.String r5 = "Generate request failed."
            r8.onPromiseRejected(r12, r5)     // Catch: android.media.NotProvisionedException -> L65
            return
        L4c:
            java.lang.String r6 = "createSession(): Session (%s) created."
            java.lang.String r7 = bytesToHexString(r4)     // Catch: android.media.NotProvisionedException -> L65
            org.chromium.base.Log.d(r2, r6, r7)     // Catch: android.media.NotProvisionedException -> L65
            r8.onPromiseResolvedWithSession(r12, r4)     // Catch: android.media.NotProvisionedException -> L65
            r8.onSessionMessage(r4, r5)     // Catch: android.media.NotProvisionedException -> L65
            java.util.HashMap<java.nio.ByteBuffer, java.lang.String> r5 = r8.mSessionIds     // Catch: android.media.NotProvisionedException -> L65
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r4)     // Catch: android.media.NotProvisionedException -> L65
            r5.put(r6, r10)     // Catch: android.media.NotProvisionedException -> L65
            goto L8a
        L65:
            r5 = move-exception
            r6 = r1
            goto L6b
        L68:
            r5 = move-exception
            r4 = 0
        L6a:
            r6 = r3
        L6b:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r3] = r5
            java.lang.String r5 = "Device not provisioned"
            org.chromium.base.Log.e(r2, r5, r7)
            if (r6 == 0) goto L84
            android.media.MediaDrm r5 = r8.mMediaDrm     // Catch: java.lang.Exception -> L7c
            r5.closeSession(r4)     // Catch: java.lang.Exception -> L7c
            goto L84
        L7c:
            r4 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r3] = r4
            org.chromium.base.Log.e(r2, r0, r1)
        L84:
            r8.savePendingCreateSessionData(r9, r10, r11, r12)
            r8.startProvisioning()
        L8a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.createSession(byte[], java.lang.String, java.util.HashMap, long):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sessionExists(byte[] bArr) {
        byte[] bArr2 = this.mMediaCryptoSession;
        if (bArr2 != null) {
            return !Arrays.equals(bArr, bArr2) && this.mSessionIds.containsKey(ByteBuffer.wrap(bArr));
        }
        Log.e(TAG, "Session doesn't exist because media crypto session is not created.", new Object[0]);
        return false;
    }

    private void closeSession(byte[] bArr, long j) {
        Log.d(TAG, "closeSession()");
        if (this.mMediaDrm == null) {
            onPromiseRejected(j, "closeSession() called when MediaDrm is null.");
            return;
        }
        if (!sessionExists(bArr)) {
            onPromiseRejected(j, "Invalid sessionId in closeSession(): " + bytesToHexString(bArr));
            return;
        }
        try {
            this.mMediaDrm.removeKeys(bArr);
        } catch (Exception e) {
            Log.e(TAG, "removeKeys failed: ", e);
        }
        try {
            this.mMediaDrm.closeSession(bArr);
        } catch (Exception e2) {
            Log.e(TAG, "closeSession failed: ", e2);
        }
        this.mSessionIds.remove(ByteBuffer.wrap(bArr));
        onPromiseResolved(j);
        onSessionClosed(bArr);
        Log.d(TAG, "Session %s closed", bytesToHexString(bArr));
    }

    private void updateSession(byte[] bArr, byte[] bArr2, long j) {
        Log.d(TAG, "updateSession()");
        if (this.mMediaDrm == null) {
            onPromiseRejected(j, "updateSession() called when MediaDrm is null.");
            return;
        }
        if (!sessionExists(bArr)) {
            onPromiseRejected(j, "Invalid session in updateSession: " + bytesToHexString(bArr));
            return;
        }
        try {
            try {
                this.mMediaDrm.provideKeyResponse(bArr, bArr2);
            } catch (IllegalStateException e) {
                Log.e(TAG, "Exception intentionally caught when calling provideKeyResponse()", e);
            }
            Log.d(TAG, "Key successfully added for session %s", bytesToHexString(bArr));
            onPromiseResolved(j);
        } catch (DeniedByServerException e2) {
            Log.e(TAG, "failed to provide key response", e2);
            onPromiseRejected(j, "Update session failed.");
            release();
        } catch (NotProvisionedException e3) {
            Log.e(TAG, "failed to provide key response", e3);
            onPromiseRejected(j, "Update session failed.");
            release();
        }
    }

    private String getSecurityLevel() {
        if (this.mMediaDrm == null || !isWidevine()) {
            Log.e(TAG, "getSecurityLevel(): MediaDrm is null or security level is not supported.", new Object[0]);
            return null;
        }
        return this.mMediaDrm.getPropertyString(SECURITY_LEVEL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startProvisioning() {
        if (this.mProvisioningPending) {
            Log.d(TAG, "startProvisioning: another provisioning is in progress, returning");
            return;
        }
        Log.d(TAG, "startProvisioning");
        this.mProvisioningPending = true;
        MediaDrm.ProvisionRequest provisionRequest = this.mMediaDrm.getProvisionRequest();
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnStartProvisioning(this.mNativeMediaDrmBridge, provisionRequest.getDefaultUrl(), provisionRequest.getData());
        }
    }

    private void processProvisionResponse(boolean z, byte[] bArr) {
        Log.d(TAG, "processProvisionResponse()");
        if (this.mMediaDrm == null) {
            return;
        }
        this.mProvisioningPending = false;
        boolean zProvideProvisionResponse = z ? provideProvisionResponse(bArr) : false;
        if (this.mResetDeviceCredentialsPending) {
            onResetDeviceCredentialsCompleted(zProvideProvisionResponse);
            this.mResetDeviceCredentialsPending = false;
        }
        if (!zProvideProvisionResponse || (this.mMediaCryptoSession == null && !createMediaCrypto())) {
            release();
        } else {
            processPendingCreateSessionData();
        }
    }

    boolean provideProvisionResponse(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            Log.e(TAG, "Invalid provision response.", new Object[0]);
            return false;
        }
        try {
            this.mMediaDrm.provideProvisionResponse(bArr);
            return true;
        } catch (DeniedByServerException e) {
            Log.e(TAG, "failed to provide provision response", e);
            return false;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "failed to provide provision response", e2);
            return false;
        }
    }

    private void onMediaCryptoReady(MediaCrypto mediaCrypto) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnMediaCryptoReady(this.mNativeMediaDrmBridge, mediaCrypto);
        }
    }

    private void onPromiseResolved(long j) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnPromiseResolved(this.mNativeMediaDrmBridge, j);
        }
    }

    private void onPromiseResolvedWithSession(long j, byte[] bArr) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnPromiseResolvedWithSession(this.mNativeMediaDrmBridge, j, bArr);
        }
    }

    private void onPromiseRejected(long j, String str) {
        Log.e(TAG, "onPromiseRejected: %s", str);
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnPromiseRejected(this.mNativeMediaDrmBridge, j, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSessionMessage(byte[] bArr, MediaDrm.KeyRequest keyRequest) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnSessionMessage(this.mNativeMediaDrmBridge, bArr, keyRequest.getRequestType(), keyRequest.getData(), keyRequest.getDefaultUrl());
        }
    }

    private void onSessionClosed(byte[] bArr) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnSessionClosed(this.mNativeMediaDrmBridge, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSessionKeysChange(byte[] bArr, Object[] objArr, boolean z) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnSessionKeysChange(this.mNativeMediaDrmBridge, bArr, objArr, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSessionExpirationUpdate(byte[] bArr, long j) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnSessionExpirationUpdate(this.mNativeMediaDrmBridge, bArr, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLegacySessionError(byte[] bArr, String str) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnLegacySessionError(this.mNativeMediaDrmBridge, bArr, str);
        }
    }

    private void onResetDeviceCredentialsCompleted(boolean z) {
        if (isNativeMediaDrmBridgeValid()) {
            nativeOnResetDeviceCredentialsCompleted(this.mNativeMediaDrmBridge, z);
        }
    }

    private class EventListener implements MediaDrm.OnEventListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private EventListener() {
        }

        @Override // android.media.MediaDrm.OnEventListener
        public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            if (bArr != null) {
                if (!MediaDrmBridge.this.sessionExists(bArr)) {
                    Log.e(MediaDrmBridge.TAG, "EventListener: Invalid session %s", MediaDrmBridge.bytesToHexString(bArr));
                    return;
                }
                if (i != 2) {
                    if (i == 3) {
                        Log.d(MediaDrmBridge.TAG, "MediaDrm.EVENT_KEY_EXPIRED");
                        MediaDrmBridge.this.onLegacySessionError(bArr, "MediaDrm EVENT_KEY_EXPIRED.");
                        return;
                    } else if (i == 4) {
                        Log.d(MediaDrmBridge.TAG, "MediaDrm.EVENT_VENDOR_DEFINED");
                        return;
                    } else {
                        Log.e(MediaDrmBridge.TAG, "Invalid DRM event " + i, new Object[0]);
                        return;
                    }
                }
                Log.d(MediaDrmBridge.TAG, "MediaDrm.EVENT_KEY_REQUIRED");
                if (MediaDrmBridge.this.mProvisioningPending) {
                    return;
                }
                try {
                    MediaDrm.KeyRequest keyRequest = MediaDrmBridge.this.getKeyRequest(bArr, bArr2, (String) MediaDrmBridge.this.mSessionIds.get(ByteBuffer.wrap(bArr)), null);
                    if (keyRequest != null) {
                        MediaDrmBridge.this.onSessionMessage(bArr, keyRequest);
                        return;
                    } else {
                        MediaDrmBridge.this.onLegacySessionError(bArr, "MediaDrm EVENT_KEY_REQUIRED: Failed to generate request.");
                        Log.e(MediaDrmBridge.TAG, "EventListener: getKeyRequest failed.", new Object[0]);
                        return;
                    }
                } catch (NotProvisionedException e) {
                    Log.e(MediaDrmBridge.TAG, "Device not provisioned", e);
                    MediaDrmBridge.this.startProvisioning();
                    return;
                }
            }
            Log.e(MediaDrmBridge.TAG, "EventListener: Null session.", new Object[0]);
        }
    }

    private class KeyStatusChangeListener implements MediaDrm.OnKeyStatusChangeListener {
        private KeyStatusChangeListener() {
        }

        private List<KeyStatus> getKeysInfo(List<MediaDrm.KeyStatus> list) {
            ArrayList arrayList = new ArrayList();
            for (MediaDrm.KeyStatus keyStatus : list) {
                arrayList.add(new KeyStatus(keyStatus.getKeyId(), keyStatus.getStatusCode()));
            }
            return arrayList;
        }

        @Override // android.media.MediaDrm.OnKeyStatusChangeListener
        public void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List<MediaDrm.KeyStatus> list, boolean z) {
            Log.d(MediaDrmBridge.TAG, "KeysStatusChange: " + MediaDrmBridge.bytesToHexString(bArr) + ", " + z);
            MediaDrmBridge.this.onSessionKeysChange(bArr, getKeysInfo(list).toArray(), z);
        }
    }

    private class ExpirationUpdateListener implements MediaDrm.OnExpirationUpdateListener {
        private ExpirationUpdateListener() {
        }

        @Override // android.media.MediaDrm.OnExpirationUpdateListener
        public void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
            Log.d(MediaDrmBridge.TAG, "ExpirationUpdate: " + MediaDrmBridge.bytesToHexString(bArr) + ", " + j);
            MediaDrmBridge.this.onSessionExpirationUpdate(bArr, j);
        }
    }
}
