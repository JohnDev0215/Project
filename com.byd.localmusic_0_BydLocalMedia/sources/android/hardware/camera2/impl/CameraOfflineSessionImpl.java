package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraOfflineSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraOfflineSession;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class CameraOfflineSessionImpl extends CameraOfflineSession implements IBinder.DeathRecipient {
    private static final long NANO_PER_SECOND = 1000000000;
    private static final int REQUEST_ID_NONE = -1;
    private static final String TAG = "CameraOfflineSessionImpl";
    private final String mCameraId;
    private SparseArray<CaptureCallbackHolder> mCaptureCallbackMap;
    private final CameraCharacteristics mCharacteristics;
    private SparseArray<OutputConfiguration> mConfiguredOutputs;
    private FrameNumberTracker mFrameNumberTracker;
    private final CameraOfflineSession.CameraOfflineSessionCallback mOfflineCallback;
    private final Executor mOfflineExecutor;
    private AbstractMap.SimpleEntry<Integer, InputConfiguration> mOfflineInput;
    private SparseArray<OutputConfiguration> mOfflineOutputs;
    private ICameraOfflineSession mRemoteSession;
    private final int mTotalPartialCount;
    private final boolean DEBUG = false;
    private final AtomicBoolean mClosing = new AtomicBoolean();
    final Object mInterfaceLock = new Object();
    private final CameraDeviceCallbacks mCallbacks = new CameraDeviceCallbacks();
    private List<RequestLastFrameNumbersHolder> mOfflineRequestLastFrameNumbersList = new ArrayList();

    public CameraOfflineSessionImpl(String str, CameraCharacteristics cameraCharacteristics, Executor executor, CameraOfflineSession.CameraOfflineSessionCallback cameraOfflineSessionCallback, SparseArray<OutputConfiguration> sparseArray, AbstractMap.SimpleEntry<Integer, InputConfiguration> simpleEntry, SparseArray<OutputConfiguration> sparseArray2, FrameNumberTracker frameNumberTracker, SparseArray<CaptureCallbackHolder> sparseArray3, List<RequestLastFrameNumbersHolder> list) {
        this.mOfflineInput = new AbstractMap.SimpleEntry<>(-1, null);
        this.mOfflineOutputs = new SparseArray<>();
        this.mConfiguredOutputs = new SparseArray<>();
        this.mFrameNumberTracker = new FrameNumberTracker();
        this.mCaptureCallbackMap = new SparseArray<>();
        if (str == null || cameraCharacteristics == null) {
            throw new IllegalArgumentException("Null argument given");
        }
        this.mCameraId = str;
        this.mCharacteristics = cameraCharacteristics;
        Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
        if (num == null) {
            this.mTotalPartialCount = 1;
        } else {
            this.mTotalPartialCount = num.intValue();
        }
        this.mOfflineRequestLastFrameNumbersList.addAll(list);
        this.mFrameNumberTracker = frameNumberTracker;
        this.mCaptureCallbackMap = sparseArray3;
        this.mConfiguredOutputs = sparseArray2;
        this.mOfflineOutputs = sparseArray;
        this.mOfflineInput = simpleEntry;
        this.mOfflineExecutor = (Executor) Preconditions.checkNotNull(executor, "offline executor must not be null");
        this.mOfflineCallback = (CameraOfflineSession.CameraOfflineSessionCallback) Preconditions.checkNotNull(cameraOfflineSessionCallback, "offline callback must not be null");
    }

    public CameraDeviceCallbacks getCallbacks() {
        return this.mCallbacks;
    }

    public class CameraDeviceCallbacks extends ICameraDeviceCallbacks.Stub {
        @Override // android.hardware.camera2.ICameraDeviceCallbacks.Stub, android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public CameraDeviceCallbacks() {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceError(int i, CaptureResultExtras captureResultExtras) {
            synchronized (CameraOfflineSessionImpl.this.mInterfaceLock) {
                if (i == 3 || i == 4 || i == 5) {
                    onCaptureErrorLocked(i, captureResultExtras);
                } else {
                    Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.CameraDeviceCallbacks.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CameraOfflineSessionImpl.this.isClosed()) {
                                return;
                            }
                            CameraOfflineSessionImpl.this.mOfflineCallback.onError(CameraOfflineSessionImpl.this, 0);
                        }
                    };
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        CameraOfflineSessionImpl.this.mOfflineExecutor.execute(runnable);
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    } catch (Throwable th) {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        throw th;
                    }
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRepeatingRequestError(long j, int i) {
            Log.e(CameraOfflineSessionImpl.TAG, "Unexpected repeating request error received. Last frame number is " + j);
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceIdle() {
            synchronized (CameraOfflineSessionImpl.this.mInterfaceLock) {
                if (CameraOfflineSessionImpl.this.mRemoteSession != null) {
                    CameraOfflineSessionImpl.this.removeCompletedCallbackHolderLocked(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);
                    Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.CameraDeviceCallbacks.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CameraOfflineSessionImpl.this.isClosed()) {
                                return;
                            }
                            CameraOfflineSessionImpl.this.mOfflineCallback.onIdle(CameraOfflineSessionImpl.this);
                        }
                    };
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        CameraOfflineSessionImpl.this.mOfflineExecutor.execute(runnable);
                        return;
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                Log.v(CameraOfflineSessionImpl.TAG, "Ignoring idle state notifications during offline switches");
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r13v0, types: [long] */
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v2 */
        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onCaptureStarted(final CaptureResultExtras captureResultExtras, final long j) throws Throwable {
            int requestId = captureResultExtras.getRequestId();
            final long frameNumber = captureResultExtras.getFrameNumber();
            long lastCompletedRegularFrameNumber = captureResultExtras.getLastCompletedRegularFrameNumber();
            ?? lastCompletedReprocessFrameNumber = captureResultExtras.getLastCompletedReprocessFrameNumber();
            long lastCompletedZslFrameNumber = captureResultExtras.getLastCompletedZslFrameNumber();
            Object obj = CameraOfflineSessionImpl.this.mInterfaceLock;
            synchronized (obj) {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    CameraOfflineSessionImpl.this.removeCompletedCallbackHolderLocked(lastCompletedRegularFrameNumber, lastCompletedReprocessFrameNumber, lastCompletedZslFrameNumber);
                    final CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder) CameraOfflineSessionImpl.this.mCaptureCallbackMap.get(requestId);
                    if (captureCallbackHolder == null) {
                        return;
                    }
                    Executor executor = captureCallbackHolder.getCallback().getExecutor();
                    if (!CameraOfflineSessionImpl.this.isClosed() && executor != null) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        try {
                            executor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.CameraDeviceCallbacks.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    CameraCaptureSession.CaptureCallback sessionCallback = captureCallbackHolder.getCallback().getSessionCallback();
                                    if (CameraOfflineSessionImpl.this.isClosed() || sessionCallback == null) {
                                        return;
                                    }
                                    int subsequenceId = captureResultExtras.getSubsequenceId();
                                    CaptureRequest request = captureCallbackHolder.getRequest(subsequenceId);
                                    if (captureCallbackHolder.hasBatchedOutputs()) {
                                        Range range = (Range) request.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                                        for (int i = 0; i < captureCallbackHolder.getRequestCount(); i++) {
                                            long j2 = subsequenceId - i;
                                            sessionCallback.onCaptureStarted(CameraOfflineSessionImpl.this, captureCallbackHolder.getRequest(i), j - ((1000000000 * j2) / ((long) ((Integer) range.getUpper()).intValue())), frameNumber - j2);
                                        }
                                        return;
                                    }
                                    sessionCallback.onCaptureStarted(CameraOfflineSessionImpl.this, captureCallbackHolder.getRequest(captureResultExtras.getSubsequenceId()), j, frameNumber);
                                }
                            });
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        } catch (Throwable th3) {
                            th = th3;
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    lastCompletedReprocessFrameNumber = obj;
                    throw th;
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onResultReceived(CameraMetadataNative cameraMetadataNative, final CaptureResultExtras captureResultExtras, PhysicalCaptureResultInfo[] physicalCaptureResultInfoArr) throws Throwable {
            Object obj;
            Executor executor;
            Runnable runnable;
            CaptureResult captureResult;
            int requestId = captureResultExtras.getRequestId();
            long frameNumber = captureResultExtras.getFrameNumber();
            Object obj2 = CameraOfflineSessionImpl.this.mInterfaceLock;
            synchronized (obj2) {
                try {
                    cameraMetadataNative.set(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE, (Size) CameraOfflineSessionImpl.this.mCharacteristics.get(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE));
                    final CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder) CameraOfflineSessionImpl.this.mCaptureCallbackMap.get(requestId);
                    final CaptureRequest request = captureCallbackHolder.getRequest(captureResultExtras.getSubsequenceId());
                    boolean z = captureResultExtras.getPartialResultCount() < CameraOfflineSessionImpl.this.mTotalPartialCount;
                    int requestType = request.getRequestType();
                    if (captureCallbackHolder == null) {
                        CameraOfflineSessionImpl.this.mFrameNumberTracker.updateTracker(frameNumber, null, z, requestType);
                        return;
                    }
                    if (CameraOfflineSessionImpl.this.isClosed()) {
                        CameraOfflineSessionImpl.this.mFrameNumberTracker.updateTracker(frameNumber, null, z, requestType);
                        return;
                    }
                    final CameraMetadataNative cameraMetadataNative2 = captureCallbackHolder.hasBatchedOutputs() ? new CameraMetadataNative(cameraMetadataNative) : null;
                    Executor executor2 = captureCallbackHolder.getCallback().getExecutor();
                    if (!z) {
                        final List<CaptureResult> listPopPartialResults = CameraOfflineSessionImpl.this.mFrameNumberTracker.popPartialResults(frameNumber);
                        final long jLongValue = ((Long) cameraMetadataNative.get(CaptureResult.SENSOR_TIMESTAMP)).longValue();
                        final Range range = (Range) request.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                        final int subsequenceId = captureResultExtras.getSubsequenceId();
                        final TotalCaptureResult totalCaptureResult = new TotalCaptureResult(cameraMetadataNative, request, captureResultExtras, listPopPartialResults, captureCallbackHolder.getSessionId(), physicalCaptureResultInfoArr);
                        executor = executor2;
                        obj = obj2;
                        try {
                            runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.CameraDeviceCallbacks.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    CameraCaptureSession.CaptureCallback sessionCallback = captureCallbackHolder.getCallback().getSessionCallback();
                                    if (CameraOfflineSessionImpl.this.isClosed() || sessionCallback == null) {
                                        return;
                                    }
                                    if (captureCallbackHolder.hasBatchedOutputs()) {
                                        for (int i = 0; i < captureCallbackHolder.getRequestCount(); i++) {
                                            cameraMetadataNative2.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(jLongValue - ((((long) (subsequenceId - i)) * 1000000000) / ((long) ((Integer) range.getUpper()).intValue()))));
                                            sessionCallback.onCaptureCompleted(CameraOfflineSessionImpl.this, captureCallbackHolder.getRequest(i), new TotalCaptureResult(new CameraMetadataNative(cameraMetadataNative2), captureCallbackHolder.getRequest(i), captureResultExtras, listPopPartialResults, captureCallbackHolder.getSessionId(), new PhysicalCaptureResultInfo[0]));
                                        }
                                        return;
                                    }
                                    sessionCallback.onCaptureCompleted(CameraOfflineSessionImpl.this, request, totalCaptureResult);
                                }
                            };
                            captureResult = totalCaptureResult;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } else {
                        final CaptureResult captureResult2 = new CaptureResult(cameraMetadataNative, request, captureResultExtras);
                        runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.CameraDeviceCallbacks.4
                            @Override // java.lang.Runnable
                            public void run() {
                                CameraCaptureSession.CaptureCallback sessionCallback = captureCallbackHolder.getCallback().getSessionCallback();
                                if (CameraOfflineSessionImpl.this.isClosed() || sessionCallback == null) {
                                    return;
                                }
                                if (captureCallbackHolder.hasBatchedOutputs()) {
                                    for (int i = 0; i < captureCallbackHolder.getRequestCount(); i++) {
                                        CaptureResult captureResult3 = new CaptureResult(new CameraMetadataNative(cameraMetadataNative2), captureCallbackHolder.getRequest(i), captureResultExtras);
                                        sessionCallback.onCaptureProgressed(CameraOfflineSessionImpl.this, captureCallbackHolder.getRequest(i), captureResult3);
                                    }
                                    return;
                                }
                                sessionCallback.onCaptureProgressed(CameraOfflineSessionImpl.this, request, captureResult2);
                            }
                        };
                        captureResult = captureResult2;
                        executor = executor2;
                        obj = obj2;
                    }
                    if (executor != null) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            executor.execute(runnable);
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        } catch (Throwable th2) {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                            throw th2;
                        }
                    }
                    CameraOfflineSessionImpl.this.mFrameNumberTracker.updateTracker(frameNumber, captureResult, z, requestType);
                    if (!z) {
                        CameraOfflineSessionImpl.this.checkAndFireSequenceComplete();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obj = obj2;
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onPrepared(int i) {
            Log.e(CameraOfflineSessionImpl.TAG, "Unexpected stream " + i + " is prepared");
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRequestQueueEmpty() {
            Log.v(CameraOfflineSessionImpl.TAG, "onRequestQueueEmpty");
        }

        private void onCaptureErrorLocked(int i, CaptureResultExtras captureResultExtras) {
            long jClearCallingIdentity;
            int requestId = captureResultExtras.getRequestId();
            int subsequenceId = captureResultExtras.getSubsequenceId();
            final long frameNumber = captureResultExtras.getFrameNumber();
            String errorPhysicalCameraId = captureResultExtras.getErrorPhysicalCameraId();
            final CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder) CameraOfflineSessionImpl.this.mCaptureCallbackMap.get(requestId);
            if (captureCallbackHolder == null) {
                Log.e(CameraOfflineSessionImpl.TAG, String.format("Receive capture error on unknown request ID %d", Integer.valueOf(requestId)));
                return;
            }
            final CaptureRequest request = captureCallbackHolder.getRequest(subsequenceId);
            if (i == 5) {
                OutputConfiguration outputConfiguration = (CameraOfflineSessionImpl.this.mRemoteSession != null || CameraOfflineSessionImpl.this.isClosed()) ? (OutputConfiguration) CameraOfflineSessionImpl.this.mOfflineOutputs.get(captureResultExtras.getErrorStreamId()) : (OutputConfiguration) CameraOfflineSessionImpl.this.mConfiguredOutputs.get(captureResultExtras.getErrorStreamId());
                if (outputConfiguration == null) {
                    Log.v(CameraOfflineSessionImpl.TAG, String.format("Stream %d has been removed. Skipping buffer lost callback", Integer.valueOf(captureResultExtras.getErrorStreamId())));
                    return;
                }
                for (final Surface surface : outputConfiguration.getSurfaces()) {
                    if (request.containsTarget(surface)) {
                        Executor executor = captureCallbackHolder.getCallback().getExecutor();
                        Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.CameraDeviceCallbacks.6
                            @Override // java.lang.Runnable
                            public void run() {
                                CameraCaptureSession.CaptureCallback sessionCallback = captureCallbackHolder.getCallback().getSessionCallback();
                                if (CameraOfflineSessionImpl.this.isClosed() || sessionCallback == null) {
                                    return;
                                }
                                sessionCallback.onCaptureBufferLost(CameraOfflineSessionImpl.this, request, surface, frameNumber);
                            }
                        };
                        if (executor != null) {
                            jClearCallingIdentity = Binder.clearCallingIdentity();
                            try {
                                executor.execute(runnable);
                            } finally {
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return;
            }
            final CaptureFailure captureFailure = new CaptureFailure(request, 0, i == 4, requestId, frameNumber, errorPhysicalCameraId);
            Executor executor2 = captureCallbackHolder.getCallback().getExecutor();
            Runnable runnable2 = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.CameraDeviceCallbacks.7
                @Override // java.lang.Runnable
                public void run() {
                    CameraCaptureSession.CaptureCallback sessionCallback = captureCallbackHolder.getCallback().getSessionCallback();
                    if (CameraOfflineSessionImpl.this.isClosed() || sessionCallback == null) {
                        return;
                    }
                    sessionCallback.onCaptureFailed(CameraOfflineSessionImpl.this, request, captureFailure);
                }
            };
            CameraOfflineSessionImpl.this.mFrameNumberTracker.updateTracker(frameNumber, true, request.getRequestType());
            CameraOfflineSessionImpl.this.checkAndFireSequenceComplete();
            if (executor2 != null) {
                jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    executor2.execute(runnable2);
                } finally {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndFireSequenceComplete() {
        final CameraCaptureSession.CaptureCallback captureCallback;
        CaptureCallbackHolder captureCallbackHolderValueAt;
        Executor executor;
        long completedFrameNumber = this.mFrameNumberTracker.getCompletedFrameNumber();
        long completedReprocessFrameNumber = this.mFrameNumberTracker.getCompletedReprocessFrameNumber();
        long completedZslStillFrameNumber = this.mFrameNumberTracker.getCompletedZslStillFrameNumber();
        Iterator<RequestLastFrameNumbersHolder> it = this.mOfflineRequestLastFrameNumbersList.iterator();
        while (it.hasNext()) {
            final RequestLastFrameNumbersHolder next = it.next();
            boolean z = false;
            final int requestId = next.getRequestId();
            synchronized (this.mInterfaceLock) {
                int iIndexOfKey = this.mCaptureCallbackMap.indexOfKey(requestId);
                captureCallback = null;
                captureCallbackHolderValueAt = iIndexOfKey >= 0 ? this.mCaptureCallbackMap.valueAt(iIndexOfKey) : null;
                if (captureCallbackHolderValueAt != null) {
                    long lastRegularFrameNumber = next.getLastRegularFrameNumber();
                    long lastReprocessFrameNumber = next.getLastReprocessFrameNumber();
                    long lastZslStillFrameNumber = next.getLastZslStillFrameNumber();
                    Executor executor2 = captureCallbackHolderValueAt.getCallback().getExecutor();
                    CameraCaptureSession.CaptureCallback sessionCallback = captureCallbackHolderValueAt.getCallback().getSessionCallback();
                    if (lastRegularFrameNumber <= completedFrameNumber && lastReprocessFrameNumber <= completedReprocessFrameNumber && lastZslStillFrameNumber <= completedZslStillFrameNumber) {
                        z = true;
                        this.mCaptureCallbackMap.removeAt(iIndexOfKey);
                    }
                    executor = executor2;
                    captureCallback = sessionCallback;
                } else {
                    executor = null;
                }
            }
            if (captureCallbackHolderValueAt == null || z) {
                it.remove();
            }
            if (z && captureCallback != null && executor != null) {
                Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CameraOfflineSessionImpl.this.isClosed()) {
                            return;
                        }
                        captureCallback.onCaptureSequenceCompleted(CameraOfflineSessionImpl.this, requestId, next.getLastFrameNumber());
                    }
                };
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    executor.execute(runnable);
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    if (this.mCaptureCallbackMap.size() == 0) {
                        getCallbacks().onDeviceIdle();
                    }
                } catch (Throwable th) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCompletedCallbackHolderLocked(long j, long j2, long j3) {
        Iterator<RequestLastFrameNumbersHolder> it = this.mOfflineRequestLastFrameNumbersList.iterator();
        while (it.hasNext()) {
            RequestLastFrameNumbersHolder next = it.next();
            int requestId = next.getRequestId();
            int iIndexOfKey = this.mCaptureCallbackMap.indexOfKey(requestId);
            if ((iIndexOfKey >= 0 ? this.mCaptureCallbackMap.valueAt(iIndexOfKey) : null) != null) {
                long lastRegularFrameNumber = next.getLastRegularFrameNumber();
                long lastReprocessFrameNumber = next.getLastReprocessFrameNumber();
                long lastZslStillFrameNumber = next.getLastZslStillFrameNumber();
                if (lastRegularFrameNumber <= j && lastReprocessFrameNumber <= j2 && lastZslStillFrameNumber <= j3) {
                    if (next.isSequenceCompleted()) {
                        this.mCaptureCallbackMap.removeAt(iIndexOfKey);
                        it.remove();
                    } else {
                        Log.e(TAG, "Sequence not yet completed for request id " + requestId);
                    }
                }
            }
        }
    }

    public void notifyFailedSwitch() {
        synchronized (this.mInterfaceLock) {
            Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    CameraOfflineSessionImpl.this.mOfflineCallback.onSwitchFailed(CameraOfflineSessionImpl.this);
                }
            };
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mOfflineExecutor.execute(runnable);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void setRemoteSession(ICameraOfflineSession iCameraOfflineSession) throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            if (iCameraOfflineSession == null) {
                notifyFailedSwitch();
                return;
            }
            this.mRemoteSession = iCameraOfflineSession;
            IBinder iBinderAsBinder = iCameraOfflineSession.asBinder();
            if (iBinderAsBinder == null) {
                throw new CameraAccessException(2, "The camera offline session has encountered a serious error");
            }
            try {
                iBinderAsBinder.linkToDeath(this, 0);
                Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CameraOfflineSessionImpl.this.isClosed()) {
                            return;
                        }
                        CameraOfflineSessionImpl.this.mOfflineCallback.onReady(CameraOfflineSessionImpl.this);
                    }
                };
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mOfflineExecutor.execute(runnable);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } catch (RemoteException unused) {
                throw new CameraAccessException(2, "The camera offline session has encountered a serious error");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isClosed() {
        return this.mClosing.get();
    }

    private void disconnect() {
        synchronized (this.mInterfaceLock) {
            if (this.mClosing.getAndSet(true)) {
                return;
            }
            if (this.mRemoteSession != null) {
                this.mRemoteSession.asBinder().unlinkToDeath(this, 0);
                try {
                    this.mRemoteSession.disconnect();
                } catch (RemoteException e) {
                    Log.e(TAG, "Exception while disconnecting from offline session: ", e);
                }
                this.mRemoteSession = null;
                Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraOfflineSessionImpl.4
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraOfflineSessionImpl.this.mOfflineCallback.onClosed(CameraOfflineSessionImpl.this);
                    }
                };
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mOfflineExecutor.execute(runnable);
                    return;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
            throw new IllegalStateException("Offline session is not yet ready");
        }
    }

    protected void finalize() throws Throwable {
        try {
            disconnect();
        } finally {
            super.finalize();
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        Log.w(TAG, "CameraOfflineSession on device " + this.mCameraId + " died unexpectedly");
        disconnect();
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public CameraDevice getDevice() {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public void prepare(Surface surface) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public void prepare(int i, Surface surface) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public void tearDown(Surface surface) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public void finalizeOutputConfigurations(List<OutputConfiguration> list) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int capture(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback, Handler handler) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int captureSingleRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int captureBurst(List<CaptureRequest> list, CameraCaptureSession.CaptureCallback captureCallback, Handler handler) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int captureBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int setRepeatingRequest(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback, Handler handler) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int setSingleRepeatingRequest(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int setRepeatingBurst(List<CaptureRequest> list, CameraCaptureSession.CaptureCallback captureCallback, Handler handler) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public int setRepeatingBurstRequests(List<CaptureRequest> list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public void stopRepeating() throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public void abortCaptures() throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public void updateOutputConfiguration(OutputConfiguration outputConfiguration) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public boolean isReprocessable() {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public Surface getInputSurface() {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public CameraOfflineSession switchToOffline(Collection<Surface> collection, Executor executor, CameraOfflineSession.CameraOfflineSessionCallback cameraOfflineSessionCallback) throws CameraAccessException {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraCaptureSession
    public boolean supportsOfflineProcessing(Surface surface) {
        throw new UnsupportedOperationException("Operation not supported in offline mode");
    }

    @Override // android.hardware.camera2.CameraOfflineSession, android.hardware.camera2.CameraCaptureSession, java.lang.AutoCloseable
    public void close() {
        disconnect();
    }
}
