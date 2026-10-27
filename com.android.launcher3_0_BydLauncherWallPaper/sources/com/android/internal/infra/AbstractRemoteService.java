package com.android.internal.infra;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Slog;
import android.util.TimeUtils;
import com.android.internal.infra.AbstractRemoteService;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractRemoteService<S extends AbstractRemoteService<S, I>, I extends IInterface> implements IBinder.DeathRecipient {
    protected static final int LAST_PRIVATE_MSG = 2;
    private static final int MSG_BIND = 1;
    private static final int MSG_UNBIND = 2;
    public static final long PERMANENT_BOUND_TIMEOUT_MS = 0;
    private boolean mBinding;
    private final int mBindingFlags;
    private boolean mCompleted;
    protected final ComponentName mComponentName;
    private final Context mContext;
    private boolean mDestroyed;
    protected final Handler mHandler;
    private final Intent mIntent;
    private long mNextUnbind;
    protected I mService;
    private boolean mServiceDied;
    private final int mUserId;
    public final boolean mVerbose;
    private final VultureCallback<S> mVultureCallback;
    protected final String mTag = getClass().getSimpleName();
    private final ServiceConnection mServiceConnection = new RemoteServiceConnection();
    private final ArrayList<BasePendingRequest<S, I>> mUnfinishedRequests = new ArrayList<>();

    public interface AsyncRequest<I extends IInterface> {
        void run(I i) throws RemoteException;
    }

    public interface VultureCallback<T> {
        void onServiceDied(T t);
    }

    protected abstract I getServiceInterface(IBinder iBinder);

    protected abstract long getTimeoutIdleBindMillis();

    abstract void handleBindFailure();

    protected void handleOnConnectedStateChanged(boolean z) {
    }

    protected abstract void handleOnDestroy();

    abstract void handlePendingRequestWhileUnBound(BasePendingRequest<S, I> basePendingRequest);

    abstract void handlePendingRequests();

    AbstractRemoteService(Context context, String str, ComponentName componentName, int i, VultureCallback<S> vultureCallback, Handler handler, int i2, boolean z) {
        this.mContext = context;
        this.mVultureCallback = vultureCallback;
        this.mVerbose = z;
        this.mComponentName = componentName;
        this.mIntent = new Intent(str).setComponent(componentName);
        this.mUserId = i;
        this.mHandler = new Handler(handler.getLooper());
        this.mBindingFlags = i2;
    }

    public final void destroy() {
        this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.infra.-$$Lambda$AbstractRemoteService$kh-lC3s_yrhnp_g8lxKU7txJluI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractRemoteService) obj).handleDestroy();
            }
        }, this));
    }

    public final boolean isDestroyed() {
        return this.mDestroyed;
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnConnectedStateChangedInternal(boolean z) {
        handleOnConnectedStateChanged(z);
        if (z) {
            handlePendingRequests();
        }
    }

    protected long getRemoteRequestMillis() {
        throw new UnsupportedOperationException("not implemented by " + getClass());
    }

    public final I getServiceInterface() {
        return this.mService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDestroy() {
        if (checkIfDestroyed()) {
            return;
        }
        handleOnDestroy();
        handleEnsureUnbound();
        this.mDestroyed = true;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.infra.-$$Lambda$AbstractRemoteService$pr2Sh36sHI2hBkHT788X8pmcokk
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractRemoteService) obj).handleBinderDied();
            }
        }, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBinderDied() {
        if (checkIfDestroyed()) {
            return;
        }
        I i = this.mService;
        if (i != null) {
            i.asBinder().unlinkToDeath(this, 0);
        }
        this.mService = null;
        this.mServiceDied = true;
        cancelScheduledUnbind();
        this.mVultureCallback.onServiceDied(this);
        handleBindFailure();
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.append((CharSequence) str).append("service:").println();
        printWriter.append((CharSequence) str).append("  ").append("userId=").append((CharSequence) String.valueOf(this.mUserId)).println();
        printWriter.append((CharSequence) str).append("  ").append("componentName=").append((CharSequence) this.mComponentName.flattenToString()).println();
        printWriter.append((CharSequence) str).append("  ").append("destroyed=").append((CharSequence) String.valueOf(this.mDestroyed)).println();
        printWriter.append((CharSequence) str).append("  ").append("numUnfinishedRequests=").append((CharSequence) String.valueOf(this.mUnfinishedRequests.size())).println();
        boolean zHandleIsBound = handleIsBound();
        printWriter.append((CharSequence) str).append("  ").append("bound=").append((CharSequence) String.valueOf(zHandleIsBound));
        long timeoutIdleBindMillis = getTimeoutIdleBindMillis();
        if (zHandleIsBound) {
            if (timeoutIdleBindMillis > 0) {
                printWriter.append(" (unbind in : ");
                TimeUtils.formatDuration(this.mNextUnbind - SystemClock.elapsedRealtime(), printWriter);
                printWriter.append(")");
            } else {
                printWriter.append(" (permanently bound)");
            }
        }
        printWriter.println();
        printWriter.append((CharSequence) str).append("mBindingFlags=").println(this.mBindingFlags);
        printWriter.append((CharSequence) str).append("idleTimeout=").append((CharSequence) Long.toString(timeoutIdleBindMillis / 1000)).append("s\n");
        printWriter.append((CharSequence) str).append("requestTimeout=");
        try {
            printWriter.append((CharSequence) Long.toString(getRemoteRequestMillis() / 1000)).append("s\n");
        } catch (UnsupportedOperationException unused) {
            printWriter.append("not supported\n");
        }
        printWriter.println();
    }

    protected void scheduleRequest(BasePendingRequest<S, I> basePendingRequest) {
        this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.internal.infra.-$$Lambda$bXbG5o8vIJOSPa_aIrb2-ihbT2U
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((AbstractRemoteService) obj).handlePendingRequest((AbstractRemoteService.BasePendingRequest) obj2);
            }
        }, this, basePendingRequest));
    }

    void finishRequest(BasePendingRequest<S, I> basePendingRequest) {
        this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.internal.infra.-$$Lambda$AbstractRemoteService$ldNkkT58p4_26OYFnZFfaAeiV3I
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((AbstractRemoteService) obj).handleFinishRequest((AbstractRemoteService.BasePendingRequest) obj2);
            }
        }, this, basePendingRequest));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFinishRequest(BasePendingRequest<S, I> basePendingRequest) {
        this.mUnfinishedRequests.remove(basePendingRequest);
        if (this.mUnfinishedRequests.isEmpty()) {
            scheduleUnbind();
        }
    }

    protected void scheduleAsyncRequest(AsyncRequest<I> asyncRequest) {
        this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.internal.infra.-$$Lambda$6pk9NyOda4-YNcu3o0LDBoldWIs
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((AbstractRemoteService) obj).handlePendingRequest((AbstractRemoteService.MyAsyncPendingRequest) obj2);
            }
        }, this, new MyAsyncPendingRequest(this, asyncRequest)));
    }

    protected void executeAsyncRequest(AsyncRequest<I> asyncRequest) {
        handlePendingRequest(new MyAsyncPendingRequest(this, asyncRequest));
    }

    private void cancelScheduledUnbind() {
        this.mHandler.removeMessages(2);
    }

    protected void scheduleBind() {
        if (this.mHandler.hasMessages(1)) {
            if (this.mVerbose) {
                Slog.v(this.mTag, "scheduleBind(): already scheduled");
                return;
            }
            return;
        }
        this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.infra.-$$Lambda$AbstractRemoteService$FfMJ90qG2_k97UFpP5-66UppL1Q
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractRemoteService) obj).handleEnsureBound();
            }
        }, this).setWhat(1));
    }

    protected void scheduleUnbind() {
        scheduleUnbind(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleUnbind(boolean z) {
        long timeoutIdleBindMillis = getTimeoutIdleBindMillis();
        if (timeoutIdleBindMillis <= 0) {
            if (this.mVerbose) {
                Slog.v(this.mTag, "not scheduling unbind when value is " + timeoutIdleBindMillis);
                return;
            }
            return;
        }
        if (!z) {
            timeoutIdleBindMillis = 0;
        }
        cancelScheduledUnbind();
        this.mNextUnbind = SystemClock.elapsedRealtime() + timeoutIdleBindMillis;
        if (this.mVerbose) {
            Slog.v(this.mTag, "unbinding in " + timeoutIdleBindMillis + "ms: " + this.mNextUnbind);
        }
        this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.infra.-$$Lambda$AbstractRemoteService$n27JOUQBEHySGHhjyYB_cPRqGtI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractRemoteService) obj).handleUnbind();
            }
        }, this).setWhat(2), timeoutIdleBindMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUnbind() {
        if (checkIfDestroyed()) {
            return;
        }
        handleEnsureUnbound();
    }

    protected final void handlePendingRequest(BasePendingRequest<S, I> basePendingRequest) {
        if (checkIfDestroyed() || this.mCompleted) {
            return;
        }
        if (!handleIsBound()) {
            if (this.mVerbose) {
                Slog.v(this.mTag, "handlePendingRequest(): queuing " + basePendingRequest);
            }
            handlePendingRequestWhileUnBound(basePendingRequest);
            handleEnsureBound();
            return;
        }
        if (this.mVerbose) {
            Slog.v(this.mTag, "handlePendingRequest(): " + basePendingRequest);
        }
        this.mUnfinishedRequests.add(basePendingRequest);
        cancelScheduledUnbind();
        basePendingRequest.run();
        if (basePendingRequest.isFinal()) {
            this.mCompleted = true;
        }
    }

    private boolean handleIsBound() {
        return this.mService != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEnsureBound() {
        if (handleIsBound() || this.mBinding) {
            return;
        }
        if (this.mVerbose) {
            Slog.v(this.mTag, "ensureBound()");
        }
        this.mBinding = true;
        int i = 67108865 | this.mBindingFlags;
        if (this.mContext.bindServiceAsUser(this.mIntent, this.mServiceConnection, i, this.mHandler, new UserHandle(this.mUserId))) {
            return;
        }
        Slog.w(this.mTag, "could not bind to " + this.mIntent + " using flags " + i);
        this.mBinding = false;
        if (this.mServiceDied) {
            return;
        }
        handleBinderDied();
    }

    private void handleEnsureUnbound() {
        if (handleIsBound() || this.mBinding) {
            if (this.mVerbose) {
                Slog.v(this.mTag, "ensureUnbound()");
            }
            this.mBinding = false;
            if (handleIsBound()) {
                handleOnConnectedStateChangedInternal(false);
                I i = this.mService;
                if (i != null) {
                    i.asBinder().unlinkToDeath(this, 0);
                    this.mService = null;
                }
            }
            this.mNextUnbind = 0L;
            this.mContext.unbindService(this.mServiceConnection);
        }
    }

    private class RemoteServiceConnection implements ServiceConnection {
        private RemoteServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (AbstractRemoteService.this.mVerbose) {
                Slog.v(AbstractRemoteService.this.mTag, "onServiceConnected()");
            }
            if (!AbstractRemoteService.this.mDestroyed && AbstractRemoteService.this.mBinding) {
                AbstractRemoteService.this.mBinding = false;
                try {
                    iBinder.linkToDeath(AbstractRemoteService.this, 0);
                    AbstractRemoteService abstractRemoteService = AbstractRemoteService.this;
                    abstractRemoteService.mService = (I) abstractRemoteService.getServiceInterface(iBinder);
                    AbstractRemoteService.this.handleOnConnectedStateChangedInternal(true);
                    AbstractRemoteService.this.mServiceDied = false;
                    return;
                } catch (RemoteException unused) {
                    AbstractRemoteService.this.handleBinderDied();
                    return;
                }
            }
            Slog.wtf(AbstractRemoteService.this.mTag, "onServiceConnected() was dispatched after unbindService.");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (AbstractRemoteService.this.mVerbose) {
                Slog.v(AbstractRemoteService.this.mTag, "onServiceDisconnected()");
            }
            AbstractRemoteService.this.mBinding = true;
            AbstractRemoteService.this.mService = null;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            if (AbstractRemoteService.this.mVerbose) {
                Slog.v(AbstractRemoteService.this.mTag, "onBindingDied()");
            }
            AbstractRemoteService.this.scheduleUnbind(false);
        }
    }

    private boolean checkIfDestroyed() {
        if (this.mDestroyed && this.mVerbose) {
            Slog.v(this.mTag, "Not handling operation as service for " + this.mComponentName + " is already destroyed");
        }
        return this.mDestroyed;
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.mComponentName + " " + System.identityHashCode(this) + (this.mService != null ? " (bound)" : " (unbound)") + (this.mDestroyed ? " (destroyed)" : "") + "]";
    }

    public static abstract class BasePendingRequest<S extends AbstractRemoteService<S, I>, I extends IInterface> implements Runnable {
        boolean mCancelled;
        boolean mCompleted;
        final WeakReference<S> mWeakService;
        protected final String mTag = getClass().getSimpleName();
        protected final Object mLock = new Object();

        protected boolean isFinal() {
            return false;
        }

        void onCancel() {
        }

        protected void onFailed() {
        }

        void onFinished() {
        }

        BasePendingRequest(S s) {
            this.mWeakService = new WeakReference<>(s);
        }

        protected final S getService() {
            return this.mWeakService.get();
        }

        protected final boolean finish() {
            synchronized (this.mLock) {
                if (!this.mCompleted && !this.mCancelled) {
                    this.mCompleted = true;
                    S s = this.mWeakService.get();
                    if (s != null) {
                        s.finishRequest(this);
                    }
                    onFinished();
                    return true;
                }
                return false;
            }
        }

        protected final boolean isCancelledLocked() {
            return this.mCancelled;
        }

        public boolean cancel() {
            synchronized (this.mLock) {
                if (!this.mCancelled && !this.mCompleted) {
                    this.mCancelled = true;
                    onCancel();
                    return true;
                }
                return false;
            }
        }

        protected boolean isRequestCompleted() {
            boolean z;
            synchronized (this.mLock) {
                z = this.mCompleted;
            }
            return z;
        }
    }

    public static abstract class PendingRequest<S extends AbstractRemoteService<S, I>, I extends IInterface> extends BasePendingRequest<S, I> {
        private final Handler mServiceHandler;
        private final Runnable mTimeoutTrigger;

        protected abstract void onTimeout(S s);

        protected PendingRequest(final S s) {
            super(s);
            Handler handler = s.mHandler;
            this.mServiceHandler = handler;
            Runnable runnable = new Runnable() { // from class: com.android.internal.infra.-$$Lambda$AbstractRemoteService$PendingRequest$SjtBgv2nVJLqdPsXCiZzZUY5rk4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0$AbstractRemoteService$PendingRequest(s);
                }
            };
            this.mTimeoutTrigger = runnable;
            handler.postAtTime(runnable, SystemClock.uptimeMillis() + s.getRemoteRequestMillis());
        }

        public /* synthetic */ void lambda$new$0$AbstractRemoteService$PendingRequest(AbstractRemoteService abstractRemoteService) {
            synchronized (this.mLock) {
                if (this.mCancelled) {
                    return;
                }
                this.mCompleted = true;
                S s = this.mWeakService.get();
                if (s != null) {
                    Slog.w(this.mTag, "timed out after " + abstractRemoteService.getRemoteRequestMillis() + " ms");
                    s.finishRequest(this);
                    onTimeout(s);
                    return;
                }
                Slog.w(this.mTag, "timed out (no service)");
            }
        }

        @Override // com.android.internal.infra.AbstractRemoteService.BasePendingRequest
        final void onFinished() {
            this.mServiceHandler.removeCallbacks(this.mTimeoutTrigger);
        }

        @Override // com.android.internal.infra.AbstractRemoteService.BasePendingRequest
        final void onCancel() {
            this.mServiceHandler.removeCallbacks(this.mTimeoutTrigger);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class MyAsyncPendingRequest<S extends AbstractRemoteService<S, I>, I extends IInterface> extends BasePendingRequest<S, I> {
        private static final String TAG = "MyAsyncPendingRequest";
        private final AsyncRequest<I> mRequest;

        protected MyAsyncPendingRequest(S s, AsyncRequest<I> asyncRequest) {
            super(s);
            this.mRequest = asyncRequest;
        }

        @Override // java.lang.Runnable
        public void run() {
            S service = getService();
            try {
                if (service == null) {
                    return;
                }
                try {
                    this.mRequest.run(service.mService);
                } catch (RemoteException e) {
                    Slog.w(TAG, "exception handling async request (" + this + "): " + e);
                }
            } finally {
                finish();
            }
        }
    }
}
