package com.ts.car.common.listener;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class LocalMapListenerHelper<K, T> {
    private Handler mHandler;
    private final HashMap<K, ArrayList<T>> mListenerMap;

    public enum ErrorPolicy {
        ContinueAndRemoveOnError,
        BreakAndRemoveOnError,
        ContinueOnError,
        BreakOnError
    }

    public LocalMapListenerHelper() {
        this.mListenerMap = new HashMap<>();
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public LocalMapListenerHelper(Looper looper) {
        this.mListenerMap = new HashMap<>();
        this.mHandler = new Handler(looper);
    }

    public boolean addListener(K key, T listener) {
        if (listener == null || key == null) {
            return false;
        }
        synchronized (this.mListenerMap) {
            ArrayList<T> list = this.mListenerMap.get(key);
            if (list == null) {
                list = new ArrayList<>();
                this.mListenerMap.put(key, list);
            }
            if (list.contains(listener)) {
                return true;
            }
            list.add(listener);
            return true;
        }
    }

    public boolean removeListener(K key, T listener) {
        if (listener == null || key == null) {
            return false;
        }
        synchronized (this.mListenerMap) {
            ArrayList<T> list = this.mListenerMap.get(key);
            if (list == null) {
                return false;
            }
            return list.remove(listener);
        }
    }

    public void removeAll() {
        synchronized (this.mListenerMap) {
            this.mListenerMap.clear();
        }
    }

    public boolean isEmpty(K key) {
        boolean z;
        synchronized (this.mListenerMap) {
            z = this.mListenerMap.get(key) == null || this.mListenerMap.get(key).size() == 0;
        }
        return z;
    }

    public final void notifyListener(K key, Consumer<T> consumer) {
        lambda$asyncNotifyListener$1$LocalMapListenerHelper(key, consumer, ErrorPolicy.ContinueOnError);
    }

    /* JADX INFO: renamed from: notifyListener, reason: merged with bridge method [inline-methods] */
    public void lambda$asyncNotifyListener$1$LocalMapListenerHelper(K key, Consumer<T> consumer, ErrorPolicy policy) {
        synchronized (this.mListenerMap) {
            ArrayList<T> list = this.mListenerMap.get(key);
            if (list != null && list.size() != 0) {
                List<T> listeners = new ArrayList<>(list);
                for (T listener : listeners) {
                    try {
                        consumer.accept(listener);
                    } catch (Exception e) {
                        int i = AnonymousClass1.$SwitchMap$com$ts$car$common$listener$LocalMapListenerHelper$ErrorPolicy[policy.ordinal()];
                        if (i == 1) {
                            removeListener(key, listener);
                        } else {
                            if (i == 2) {
                                return;
                            }
                            if (i == 3) {
                                removeListener(key, listener);
                                return;
                            }
                        }
                    }
                }
                return;
            }
            Log.d("LocalMapListenerHelper", "notifyListener, listenerList is empty for" + key.toString());
        }
    }

    /* JADX INFO: renamed from: com.ts.car.common.listener.LocalMapListenerHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$car$common$listener$LocalMapListenerHelper$ErrorPolicy;

        static {
            int[] iArr = new int[ErrorPolicy.values().length];
            $SwitchMap$com$ts$car$common$listener$LocalMapListenerHelper$ErrorPolicy = iArr;
            try {
                iArr[ErrorPolicy.ContinueAndRemoveOnError.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$car$common$listener$LocalMapListenerHelper$ErrorPolicy[ErrorPolicy.BreakOnError.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$car$common$listener$LocalMapListenerHelper$ErrorPolicy[ErrorPolicy.BreakAndRemoveOnError.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public final void asyncNotifyListener(final K key, final Consumer<T> consumer) {
        this.mHandler.post(new Runnable() { // from class: com.ts.car.common.listener.-$$Lambda$LocalMapListenerHelper$rnuuSiNusj1r20Dl8WJoI37SZ-0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$0$LocalMapListenerHelper(key, consumer);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$asyncNotifyListener$0$LocalMapListenerHelper(Object obj, Consumer consumer) {
        lambda$asyncNotifyListener$1$LocalMapListenerHelper(obj, consumer, ErrorPolicy.ContinueOnError);
    }

    public final void asyncNotifyListener(final K key, final Consumer<T> consumer, final ErrorPolicy policy) {
        this.mHandler.post(new Runnable() { // from class: com.ts.car.common.listener.-$$Lambda$LocalMapListenerHelper$fwq3pVrwsGp6L9vJKSXMHnEqb8I
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$1$LocalMapListenerHelper(key, consumer, policy);
            }
        });
    }
}
