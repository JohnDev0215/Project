package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.os.Handler;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class CameraDeviceState {
    private static final boolean DEBUG = false;
    public static final int NO_CAPTURE_ERROR = -1;
    private static final int STATE_CAPTURING = 4;
    private static final int STATE_CONFIGURING = 2;
    private static final int STATE_ERROR = 0;
    private static final int STATE_IDLE = 3;
    private static final int STATE_UNCONFIGURED = 1;
    private static final String TAG = "CameraDeviceState";
    private static final String[] sStateNames = {"ERROR", "UNCONFIGURED", "CONFIGURING", "IDLE", "CAPTURING"};
    private int mCurrentState = 1;
    private int mCurrentError = -1;
    private RequestHolder mCurrentRequest = null;
    private Handler mCurrentHandler = null;
    private CameraDeviceStateListener mCurrentListener = null;

    public interface CameraDeviceStateListener {
        void onBusy();

        void onCaptureResult(CameraMetadataNative cameraMetadataNative, RequestHolder requestHolder);

        void onCaptureStarted(RequestHolder requestHolder, long j);

        void onConfiguring();

        void onError(int i, Object obj, RequestHolder requestHolder);

        void onIdle();

        void onRepeatingRequestError(long j, int i);

        void onRequestQueueEmpty();
    }

    public synchronized void setError(int i) {
        this.mCurrentError = i;
        doStateTransition(0);
    }

    public synchronized boolean setConfiguring() {
        doStateTransition(2);
        return this.mCurrentError == -1;
    }

    public synchronized boolean setIdle() {
        doStateTransition(3);
        return this.mCurrentError == -1;
    }

    public synchronized boolean setCaptureStart(RequestHolder requestHolder, long j, int i) {
        this.mCurrentRequest = requestHolder;
        doStateTransition(4, j, i);
        return this.mCurrentError == -1;
    }

    public synchronized boolean setCaptureResult(final RequestHolder requestHolder, final CameraMetadataNative cameraMetadataNative, final int i, final Object obj) {
        if (this.mCurrentState != 4) {
            Log.e(TAG, "Cannot receive result while in state: " + this.mCurrentState);
            this.mCurrentError = 1;
            doStateTransition(0);
            return this.mCurrentError == -1;
        }
        if (this.mCurrentHandler != null && this.mCurrentListener != null) {
            if (i != -1) {
                this.mCurrentHandler.post(new Runnable() { // from class: android.hardware.camera2.legacy.CameraDeviceState.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraDeviceState.this.mCurrentListener.onError(i, obj, requestHolder);
                    }
                });
            } else {
                this.mCurrentHandler.post(new Runnable() { // from class: android.hardware.camera2.legacy.CameraDeviceState.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CameraDeviceState.this.mCurrentListener.onCaptureResult(cameraMetadataNative, requestHolder);
                    }
                });
            }
        }
        return this.mCurrentError == -1;
    }

    public synchronized boolean setCaptureResult(RequestHolder requestHolder, CameraMetadataNative cameraMetadataNative) {
        return setCaptureResult(requestHolder, cameraMetadataNative, -1, null);
    }

    public synchronized void setRepeatingRequestError(final long j, final int i) {
        this.mCurrentHandler.post(new Runnable() { // from class: android.hardware.camera2.legacy.CameraDeviceState.3
            @Override // java.lang.Runnable
            public void run() {
                CameraDeviceState.this.mCurrentListener.onRepeatingRequestError(j, i);
            }
        });
    }

    public synchronized void setRequestQueueEmpty() {
        this.mCurrentHandler.post(new Runnable() { // from class: android.hardware.camera2.legacy.CameraDeviceState.4
            @Override // java.lang.Runnable
            public void run() {
                CameraDeviceState.this.mCurrentListener.onRequestQueueEmpty();
            }
        });
    }

    public synchronized void setCameraDeviceCallbacks(Handler handler, CameraDeviceStateListener cameraDeviceStateListener) {
        this.mCurrentHandler = handler;
        this.mCurrentListener = cameraDeviceStateListener;
    }

    private void doStateTransition(int i) {
        doStateTransition(i, 0L, -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doStateTransition(int r7, final long r8, final int r10) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.legacy.CameraDeviceState.doStateTransition(int, long, int):void");
    }
}
