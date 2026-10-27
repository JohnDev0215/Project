package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.os.Binder;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class CallbackProxies {

    public static class SessionStateCallbackProxy extends CameraCaptureSession.StateCallback {
        private final CameraCaptureSession.StateCallback mCallback;
        private final Executor mExecutor;

        public SessionStateCallbackProxy(Executor executor, CameraCaptureSession.StateCallback stateCallback) {
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor, "executor must not be null");
            this.mCallback = (CameraCaptureSession.StateCallback) Preconditions.checkNotNull(stateCallback, "callback must not be null");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(final CameraCaptureSession cameraCaptureSession) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.-$$Lambda$CallbackProxies$SessionStateCallbackProxy$HnPrsYGyv_dr7h4oQtOdlAVFnXc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onConfigured$0$CallbackProxies$SessionStateCallbackProxy(cameraCaptureSession);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onConfigured$0$CallbackProxies$SessionStateCallbackProxy(CameraCaptureSession cameraCaptureSession) {
            this.mCallback.onConfigured(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(final CameraCaptureSession cameraCaptureSession) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.-$$Lambda$CallbackProxies$SessionStateCallbackProxy$Gzvm0PHGtB3fJqA4va64jow8aJw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onConfigureFailed$1$CallbackProxies$SessionStateCallbackProxy(cameraCaptureSession);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onConfigureFailed$1$CallbackProxies$SessionStateCallbackProxy(CameraCaptureSession cameraCaptureSession) {
            this.mCallback.onConfigureFailed(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(final CameraCaptureSession cameraCaptureSession) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.-$$Lambda$CallbackProxies$SessionStateCallbackProxy$CL-TfkVqLW0buOwbBZdXfjTp9YE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onReady$2$CallbackProxies$SessionStateCallbackProxy(cameraCaptureSession);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onReady$2$CallbackProxies$SessionStateCallbackProxy(CameraCaptureSession cameraCaptureSession) {
            this.mCallback.onReady(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(final CameraCaptureSession cameraCaptureSession) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.-$$Lambda$CallbackProxies$SessionStateCallbackProxy$3sCbRQj2a283sJ8sQ9Wq24DwilQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onActive$3$CallbackProxies$SessionStateCallbackProxy(cameraCaptureSession);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onActive$3$CallbackProxies$SessionStateCallbackProxy(CameraCaptureSession cameraCaptureSession) {
            this.mCallback.onActive(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onCaptureQueueEmpty(final CameraCaptureSession cameraCaptureSession) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.-$$Lambda$CallbackProxies$SessionStateCallbackProxy$2Mjs4CmXVUDC_nFYwDjmvv4w9HQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onCaptureQueueEmpty$4$CallbackProxies$SessionStateCallbackProxy(cameraCaptureSession);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onCaptureQueueEmpty$4$CallbackProxies$SessionStateCallbackProxy(CameraCaptureSession cameraCaptureSession) {
            this.mCallback.onCaptureQueueEmpty(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(final CameraCaptureSession cameraCaptureSession) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.-$$Lambda$CallbackProxies$SessionStateCallbackProxy$GdB6AT4RAIK1ZLv0r6OXq0eARj0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onClosed$5$CallbackProxies$SessionStateCallbackProxy(cameraCaptureSession);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onClosed$5$CallbackProxies$SessionStateCallbackProxy(CameraCaptureSession cameraCaptureSession) {
            this.mCallback.onClosed(cameraCaptureSession);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onSurfacePrepared(final CameraCaptureSession cameraCaptureSession, final Surface surface) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.-$$Lambda$CallbackProxies$SessionStateCallbackProxy$II-MLiRDmcm0GaS7cKz5rchKc80
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSurfacePrepared$6$CallbackProxies$SessionStateCallbackProxy(cameraCaptureSession, surface);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onSurfacePrepared$6$CallbackProxies$SessionStateCallbackProxy(CameraCaptureSession cameraCaptureSession, Surface surface) {
            this.mCallback.onSurfacePrepared(cameraCaptureSession, surface);
        }
    }

    private CallbackProxies() {
        throw new AssertionError();
    }
}
