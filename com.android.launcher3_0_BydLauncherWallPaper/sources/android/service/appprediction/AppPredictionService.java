package android.service.appprediction;

import android.annotation.SystemApi;
import android.app.Service;
import android.app.prediction.AppPredictionContext;
import android.app.prediction.AppPredictionSessionId;
import android.app.prediction.AppTarget;
import android.app.prediction.AppTargetEvent;
import android.app.prediction.AppTargetId;
import android.app.prediction.IPredictionCallback;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.appprediction.AppPredictionService;
import android.service.appprediction.IPredictionService;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.QuintConsumer;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public abstract class AppPredictionService extends Service {
    public static final String SERVICE_INTERFACE = "android.service.appprediction.AppPredictionService";
    private static final String TAG = "AppPredictionService";
    private Handler mHandler;
    private final ArrayMap<AppPredictionSessionId, ArrayList<CallbackWrapper>> mSessionCallbacks = new ArrayMap<>();
    private final IPredictionService mInterface = new AnonymousClass1();

    public abstract void onAppTargetEvent(AppPredictionSessionId appPredictionSessionId, AppTargetEvent appTargetEvent);

    public void onCreatePredictionSession(AppPredictionContext appPredictionContext, AppPredictionSessionId appPredictionSessionId) {
    }

    public void onDestroyPredictionSession(AppPredictionSessionId appPredictionSessionId) {
    }

    public abstract void onLaunchLocationShown(AppPredictionSessionId appPredictionSessionId, String str, List<AppTargetId> list);

    public abstract void onRequestPredictionUpdate(AppPredictionSessionId appPredictionSessionId);

    public abstract void onSortAppTargets(AppPredictionSessionId appPredictionSessionId, List<AppTarget> list, CancellationSignal cancellationSignal, Consumer<List<AppTarget>> consumer);

    public void onStartPredictionUpdates() {
    }

    public void onStopPredictionUpdates() {
    }

    /* JADX INFO: renamed from: android.service.appprediction.AppPredictionService$1, reason: invalid class name */
    class AnonymousClass1 extends IPredictionService.Stub {
        AnonymousClass1() {
        }

        @Override // android.service.appprediction.IPredictionService
        public void onCreatePredictionSession(AppPredictionContext appPredictionContext, AppPredictionSessionId appPredictionSessionId) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.service.appprediction.-$$Lambda$AppPredictionService$1$KOWr8ljT_refJNe5nQdE5iQ1g4s
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((AppPredictionService) obj).doCreatePredictionSession((AppPredictionContext) obj2, (AppPredictionSessionId) obj3);
                }
            }, AppPredictionService.this, appPredictionContext, appPredictionSessionId));
        }

        @Override // android.service.appprediction.IPredictionService
        public void notifyAppTargetEvent(AppPredictionSessionId appPredictionSessionId, AppTargetEvent appTargetEvent) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.service.appprediction.-$$Lambda$BlF1RZOwFMzHeX9GIARa9ovAMME
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((AppPredictionService) obj).onAppTargetEvent((AppPredictionSessionId) obj2, (AppTargetEvent) obj3);
                }
            }, AppPredictionService.this, appPredictionSessionId, appTargetEvent));
        }

        @Override // android.service.appprediction.IPredictionService
        public void notifyLaunchLocationShown(AppPredictionSessionId appPredictionSessionId, String str, ParceledListSlice parceledListSlice) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.service.appprediction.-$$Lambda$LXLYW4YmqcO6z6CeO0Y4pissQqg
                @Override // com.android.internal.util.function.QuadConsumer
                public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                    ((AppPredictionService) obj).onLaunchLocationShown((AppPredictionSessionId) obj2, (String) obj3, (List) obj4);
                }
            }, AppPredictionService.this, appPredictionSessionId, str, parceledListSlice.getList()));
        }

        @Override // android.service.appprediction.IPredictionService
        public void sortAppTargets(AppPredictionSessionId appPredictionSessionId, ParceledListSlice parceledListSlice, IPredictionCallback iPredictionCallback) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuintConsumer() { // from class: android.service.appprediction.-$$Lambda$2P9M18gbiBUGmPqCjZFX1MM0Q7s
                @Override // com.android.internal.util.function.QuintConsumer
                public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                    ((AppPredictionService) obj).onSortAppTargets((AppPredictionSessionId) obj2, (List) obj3, (CancellationSignal) obj4, (AppPredictionService.CallbackWrapper) obj5);
                }
            }, AppPredictionService.this, appPredictionSessionId, parceledListSlice.getList(), null, new CallbackWrapper(iPredictionCallback, null)));
        }

        @Override // android.service.appprediction.IPredictionService
        public void registerPredictionUpdates(AppPredictionSessionId appPredictionSessionId, IPredictionCallback iPredictionCallback) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.service.appprediction.-$$Lambda$AppPredictionService$1$FzZPJ94IPkg_ajhEVc9tPJsCQRw
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((AppPredictionService) obj).doRegisterPredictionUpdates((AppPredictionSessionId) obj2, (IPredictionCallback) obj3);
                }
            }, AppPredictionService.this, appPredictionSessionId, iPredictionCallback));
        }

        @Override // android.service.appprediction.IPredictionService
        public void unregisterPredictionUpdates(AppPredictionSessionId appPredictionSessionId, IPredictionCallback iPredictionCallback) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.service.appprediction.-$$Lambda$AppPredictionService$1$dfFkqxodA9HHyaCvEzWnVdJO8aM
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((AppPredictionService) obj).doUnregisterPredictionUpdates((AppPredictionSessionId) obj2, (IPredictionCallback) obj3);
                }
            }, AppPredictionService.this, appPredictionSessionId, iPredictionCallback));
        }

        @Override // android.service.appprediction.IPredictionService
        public void requestPredictionUpdate(AppPredictionSessionId appPredictionSessionId) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.service.appprediction.-$$Lambda$AppPredictionService$1$-ScC4QnkAb73yirfvbdK8LglF1w
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((AppPredictionService) obj).doRequestPredictionUpdate((AppPredictionSessionId) obj2);
                }
            }, AppPredictionService.this, appPredictionSessionId));
        }

        @Override // android.service.appprediction.IPredictionService
        public void onDestroyPredictionSession(AppPredictionSessionId appPredictionSessionId) {
            AppPredictionService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.service.appprediction.-$$Lambda$AppPredictionService$1$VxOBYUui2l3Ndwzu-6D8H3idauU
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((AppPredictionService) obj).doDestroyPredictionSession((AppPredictionSessionId) obj2);
                }
            }, AppPredictionService.this, appPredictionSessionId));
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mHandler = new Handler(Looper.getMainLooper(), null, true);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return this.mInterface.asBinder();
        }
        Log.w(TAG, "Tried to bind to wrong intent (should be android.service.appprediction.AppPredictionService: " + intent);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCreatePredictionSession(AppPredictionContext appPredictionContext, AppPredictionSessionId appPredictionSessionId) {
        this.mSessionCallbacks.put(appPredictionSessionId, new ArrayList<>());
        onCreatePredictionSession(appPredictionContext, appPredictionSessionId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRegisterPredictionUpdates(AppPredictionSessionId appPredictionSessionId, IPredictionCallback iPredictionCallback) {
        final ArrayList<CallbackWrapper> arrayList = this.mSessionCallbacks.get(appPredictionSessionId);
        if (arrayList == null) {
            Slog.e(TAG, "Failed to register for updates for unknown session: " + appPredictionSessionId);
        } else if (findCallbackWrapper(arrayList, iPredictionCallback) == null) {
            arrayList.add(new CallbackWrapper(iPredictionCallback, new Consumer() { // from class: android.service.appprediction.-$$Lambda$AppPredictionService$Elfyxl6tibwYmdgo89pc4n-B4DE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$doRegisterPredictionUpdates$1$AppPredictionService(arrayList, (AppPredictionService.CallbackWrapper) obj);
                }
            }));
            if (arrayList.size() == 1) {
                onStartPredictionUpdates();
            }
        }
    }

    public /* synthetic */ void lambda$doRegisterPredictionUpdates$1$AppPredictionService(final ArrayList arrayList, final CallbackWrapper callbackWrapper) {
        this.mHandler.post(new Runnable() { // from class: android.service.appprediction.-$$Lambda$AppPredictionService$_lx-N1wvGaW68zCd51CVH9WcL2A
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$doRegisterPredictionUpdates$0$AppPredictionService(arrayList, callbackWrapper);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUnregisterPredictionUpdates(AppPredictionSessionId appPredictionSessionId, IPredictionCallback iPredictionCallback) {
        ArrayList<CallbackWrapper> arrayList = this.mSessionCallbacks.get(appPredictionSessionId);
        if (arrayList == null) {
            Slog.e(TAG, "Failed to unregister for updates for unknown session: " + appPredictionSessionId);
            return;
        }
        CallbackWrapper callbackWrapperFindCallbackWrapper = findCallbackWrapper(arrayList, iPredictionCallback);
        if (callbackWrapperFindCallbackWrapper != null) {
            lambda$doRegisterPredictionUpdates$0$AppPredictionService(arrayList, callbackWrapperFindCallbackWrapper);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeCallbackWrapper, reason: merged with bridge method [inline-methods] */
    public void lambda$doRegisterPredictionUpdates$0$AppPredictionService(ArrayList<CallbackWrapper> arrayList, CallbackWrapper callbackWrapper) {
        if (arrayList == null) {
            return;
        }
        arrayList.remove(callbackWrapper);
        if (arrayList.isEmpty()) {
            onStopPredictionUpdates();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRequestPredictionUpdate(AppPredictionSessionId appPredictionSessionId) {
        ArrayList<CallbackWrapper> arrayList = this.mSessionCallbacks.get(appPredictionSessionId);
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        onRequestPredictionUpdate(appPredictionSessionId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDestroyPredictionSession(AppPredictionSessionId appPredictionSessionId) {
        this.mSessionCallbacks.remove(appPredictionSessionId);
        onDestroyPredictionSession(appPredictionSessionId);
    }

    public final void updatePredictions(AppPredictionSessionId appPredictionSessionId, List<AppTarget> list) {
        ArrayList<CallbackWrapper> arrayList = this.mSessionCallbacks.get(appPredictionSessionId);
        if (arrayList != null) {
            Iterator<CallbackWrapper> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().accept(list);
            }
        }
    }

    private CallbackWrapper findCallbackWrapper(ArrayList<CallbackWrapper> arrayList, IPredictionCallback iPredictionCallback) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).isCallback(iPredictionCallback)) {
                return arrayList.get(size);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class CallbackWrapper implements Consumer<List<AppTarget>>, IBinder.DeathRecipient {
        private IPredictionCallback mCallback;
        private final Consumer<CallbackWrapper> mOnBinderDied;

        CallbackWrapper(IPredictionCallback iPredictionCallback, Consumer<CallbackWrapper> consumer) {
            this.mCallback = iPredictionCallback;
            this.mOnBinderDied = consumer;
            try {
                iPredictionCallback.asBinder().linkToDeath(this, 0);
            } catch (RemoteException e) {
                Slog.e(AppPredictionService.TAG, "Failed to link to death: " + e);
            }
        }

        public boolean isCallback(IPredictionCallback iPredictionCallback) {
            IPredictionCallback iPredictionCallback2 = this.mCallback;
            if (iPredictionCallback2 == null) {
                Slog.e(AppPredictionService.TAG, "Callback is null, likely the binder has died.");
                return false;
            }
            return iPredictionCallback2.equals(iPredictionCallback);
        }

        @Override // java.util.function.Consumer
        public void accept(List<AppTarget> list) {
            try {
                IPredictionCallback iPredictionCallback = this.mCallback;
                if (iPredictionCallback != null) {
                    iPredictionCallback.onResult(new ParceledListSlice(list));
                }
            } catch (RemoteException e) {
                Slog.e(AppPredictionService.TAG, "Error sending result:" + e);
            }
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mCallback = null;
            Consumer<CallbackWrapper> consumer = this.mOnBinderDied;
            if (consumer != null) {
                consumer.accept(this);
            }
        }
    }
}
