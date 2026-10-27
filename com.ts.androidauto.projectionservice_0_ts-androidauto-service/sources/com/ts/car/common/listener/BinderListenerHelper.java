package com.ts.car.common.listener;

import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class BinderListenerHelper<T extends IInterface> {
    private final Map<T, BinderListenerHelper<T>.Callback> mCallbackMap = new HashMap();
    private Handler mHandler;

    public enum ErrorPolicy {
        ContinueAndRemoveOnError,
        BreakAndRemoveOnError,
        ContinueOnError,
        BreakOnError
    }

    private final class Callback implements IBinder.DeathRecipient {
        public T listener;

        private Callback() {
        }

        /* synthetic */ Callback(BinderListenerHelper x0, AnonymousClass1 x1) {
            this();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            BinderListenerHelper.this.removeListener(this.listener);
        }
    }

    public BinderListenerHelper(Looper looper) {
        this.mHandler = new Handler(looper);
    }

    public boolean addListener(T listener) throws RemoteException {
        if (listener == null) {
            return false;
        }
        synchronized (this.mCallbackMap) {
            if (this.mCallbackMap.containsKey(listener)) {
                return false;
            }
            BinderListenerHelper<T>.Callback callback = new Callback(this, null);
            callback.listener = listener;
            listener.asBinder().linkToDeath(callback, 0);
            this.mCallbackMap.put(listener, callback);
            return true;
        }
    }

    public boolean removeListener(T listener) {
        if (listener == null) {
            return false;
        }
        synchronized (this.mCallbackMap) {
            BinderListenerHelper<T>.Callback callback = this.mCallbackMap.get(listener);
            if (callback == null) {
                return false;
            }
            this.mCallbackMap.remove(listener);
            callback.listener.asBinder().unlinkToDeath(callback, 0);
            return true;
        }
    }

    public void removeAll() {
        synchronized (this.mCallbackMap) {
            for (BinderListenerHelper<T>.Callback callback : this.mCallbackMap.values()) {
                if (callback != null) {
                    callback.listener.asBinder().unlinkToDeath(callback, 0);
                }
            }
            this.mCallbackMap.clear();
        }
    }

    public final void notifyListener(BinderConsumer<T> consumer) {
        lambda$asyncNotifyListener$1$BinderListenerHelper(consumer, ErrorPolicy.ContinueOnError);
    }

    /* JADX INFO: renamed from: notifyListener, reason: merged with bridge method [inline-methods] */
    public void lambda$asyncNotifyListener$1$BinderListenerHelper(BinderConsumer<T> consumer, ErrorPolicy policy) {
        List<T> listeners;
        synchronized (this.mCallbackMap) {
            listeners = new ArrayList<>(this.mCallbackMap.keySet());
        }
        for (T listener : listeners) {
            try {
                consumer.doCallback(listener);
            } catch (RemoteException e) {
                int i = AnonymousClass1.$SwitchMap$com$ts$car$common$listener$BinderListenerHelper$ErrorPolicy[policy.ordinal()];
                if (i == 1) {
                    removeListener(listener);
                } else {
                    if (i == 2) {
                        return;
                    }
                    if (i == 3) {
                        removeListener(listener);
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.car.common.listener.BinderListenerHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$car$common$listener$BinderListenerHelper$ErrorPolicy;

        static {
            int[] iArr = new int[ErrorPolicy.values().length];
            $SwitchMap$com$ts$car$common$listener$BinderListenerHelper$ErrorPolicy = iArr;
            try {
                iArr[ErrorPolicy.ContinueAndRemoveOnError.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$car$common$listener$BinderListenerHelper$ErrorPolicy[ErrorPolicy.BreakOnError.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$car$common$listener$BinderListenerHelper$ErrorPolicy[ErrorPolicy.BreakAndRemoveOnError.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public final void asyncNotifyListener(final BinderConsumer<T> consumer) {
        this.mHandler.post(new Runnable() { // from class: com.ts.car.common.listener.-$$Lambda$BinderListenerHelper$Qf-dJX-DPZM3xaOx5xv1Oq8HAs0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$0$BinderListenerHelper(consumer);
            }
        });
    }

    public /* synthetic */ void lambda$asyncNotifyListener$0$BinderListenerHelper(BinderConsumer consumer) {
        lambda$asyncNotifyListener$1$BinderListenerHelper(consumer, ErrorPolicy.ContinueOnError);
    }

    public final void asyncNotifyListener(final BinderConsumer<T> consumer, final ErrorPolicy policy) {
        this.mHandler.post(new Runnable() { // from class: com.ts.car.common.listener.-$$Lambda$BinderListenerHelper$Tj8xlO32fYe9BoOpisvtrd12KF0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$1$BinderListenerHelper(consumer, policy);
            }
        });
    }
}
