package android.app.prediction;

import android.annotation.SystemApi;
import android.app.prediction.IPredictionCallback;
import android.app.prediction.IPredictionManager;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.SettingsStringUtil;
import android.util.ArrayMap;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class AppPredictor {
    private static final String TAG = "AppPredictor";
    private final IPredictionManager mPredictionManager;
    private final AppPredictionSessionId mSessionId;
    private final IBinder mToken;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final AtomicBoolean mIsClosed = new AtomicBoolean(false);
    private final ArrayMap<Callback, CallbackWrapper> mRegisteredCallbacks = new ArrayMap<>();

    public interface Callback {
        void onTargetsAvailable(List<AppTarget> list);
    }

    AppPredictor(Context context, AppPredictionContext appPredictionContext) {
        Binder binder = new Binder();
        this.mToken = binder;
        IPredictionManager iPredictionManagerAsInterface = IPredictionManager.Stub.asInterface(ServiceManager.getService(Context.APP_PREDICTION_SERVICE));
        this.mPredictionManager = iPredictionManagerAsInterface;
        AppPredictionSessionId appPredictionSessionId = new AppPredictionSessionId(context.getPackageName() + SettingsStringUtil.DELIMITER + UUID.randomUUID().toString(), context.getUserId());
        this.mSessionId = appPredictionSessionId;
        try {
            iPredictionManagerAsInterface.createPredictionSession(appPredictionContext, appPredictionSessionId, binder);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to create predictor", e);
            e.rethrowAsRuntimeException();
        }
        this.mCloseGuard.open("close");
    }

    public void notifyAppTargetEvent(AppTargetEvent appTargetEvent) {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        }
        try {
            this.mPredictionManager.notifyAppTargetEvent(this.mSessionId, appTargetEvent);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to notify app target event", e);
            e.rethrowAsRuntimeException();
        }
    }

    public void notifyLaunchLocationShown(String str, List<AppTargetId> list) {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        }
        try {
            this.mPredictionManager.notifyLaunchLocationShown(this.mSessionId, str, new ParceledListSlice(list));
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to notify location shown event", e);
            e.rethrowAsRuntimeException();
        }
    }

    public void registerPredictionUpdates(Executor executor, final Callback callback) {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        }
        if (this.mRegisteredCallbacks.containsKey(callback)) {
            return;
        }
        try {
            Objects.requireNonNull(callback);
            CallbackWrapper callbackWrapper = new CallbackWrapper(executor, new Consumer() { // from class: android.app.prediction.-$$Lambda$7HS9mYZHhKaf1pnml8YLWzxXxoE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    callback.onTargetsAvailable((List) obj);
                }
            });
            this.mPredictionManager.registerPredictionUpdates(this.mSessionId, callbackWrapper);
            this.mRegisteredCallbacks.put(callback, callbackWrapper);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to register for prediction updates", e);
            e.rethrowAsRuntimeException();
        }
    }

    public void unregisterPredictionUpdates(Callback callback) {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        }
        if (this.mRegisteredCallbacks.containsKey(callback)) {
            try {
                this.mPredictionManager.unregisterPredictionUpdates(this.mSessionId, this.mRegisteredCallbacks.remove(callback));
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to unregister for prediction updates", e);
                e.rethrowAsRuntimeException();
            }
        }
    }

    public void requestPredictionUpdate() {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        }
        try {
            this.mPredictionManager.requestPredictionUpdate(this.mSessionId);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to request prediction update", e);
            e.rethrowAsRuntimeException();
        }
    }

    public void sortTargets(List<AppTarget> list, Executor executor, Consumer<List<AppTarget>> consumer) {
        if (this.mIsClosed.get()) {
            throw new IllegalStateException("This client has already been destroyed.");
        }
        try {
            this.mPredictionManager.sortAppTargets(this.mSessionId, new ParceledListSlice(list), new CallbackWrapper(executor, consumer));
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to sort targets", e);
            e.rethrowAsRuntimeException();
        }
    }

    public void destroy() {
        if (!this.mIsClosed.getAndSet(true)) {
            this.mCloseGuard.close();
            try {
                this.mPredictionManager.onDestroyPredictionSession(this.mSessionId);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to notify app target event", e);
                e.rethrowAsRuntimeException();
            }
            this.mRegisteredCallbacks.clear();
            return;
        }
        throw new IllegalStateException("This client has already been destroyed.");
    }

    protected void finalize() throws Throwable {
        try {
            CloseGuard closeGuard = this.mCloseGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
            }
            if (!this.mIsClosed.get()) {
                destroy();
            }
        } finally {
            super.finalize();
        }
    }

    public AppPredictionSessionId getSessionId() {
        return this.mSessionId;
    }

    static class CallbackWrapper extends IPredictionCallback.Stub {
        private final Consumer<List<AppTarget>> mCallback;
        private final Executor mExecutor;

        CallbackWrapper(Executor executor, Consumer<List<AppTarget>> consumer) {
            this.mCallback = consumer;
            this.mExecutor = executor;
        }

        @Override // android.app.prediction.IPredictionCallback
        public void onResult(final ParceledListSlice parceledListSlice) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.app.prediction.-$$Lambda$AppPredictor$CallbackWrapper$dqt76cJQV2KLhqcQ0sgzoQhlxLw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onResult$0$AppPredictor$CallbackWrapper(parceledListSlice);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        public /* synthetic */ void lambda$onResult$0$AppPredictor$CallbackWrapper(ParceledListSlice parceledListSlice) {
            this.mCallback.accept(parceledListSlice.getList());
        }
    }
}
