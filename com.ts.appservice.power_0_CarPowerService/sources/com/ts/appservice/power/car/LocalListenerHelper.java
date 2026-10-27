package com.ts.appservice.power.car;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class LocalListenerHelper<T> {
    private Handler mHandler;
    private final List<T> mListenerList;

    public enum ErrorPolicy {
        ContinueAndRemoveOnError,
        BreakAndRemoveOnError,
        ContinueOnError,
        BreakOnError
    }

    public LocalListenerHelper() {
        this.mListenerList = new ArrayList();
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public LocalListenerHelper(Looper looper) {
        this.mListenerList = new ArrayList();
        this.mHandler = new Handler(looper);
    }

    public boolean addListener(T listener) {
        if (listener == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            if (this.mListenerList.contains(listener)) {
                return true;
            }
            this.mListenerList.add(listener);
            return true;
        }
    }

    public boolean removeListener(T listener) {
        boolean zRemove;
        if (listener == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zRemove = this.mListenerList.remove(listener);
        }
        return zRemove;
    }

    public boolean containsListener(T listener) {
        boolean zContains;
        if (listener == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zContains = this.mListenerList.contains(listener);
        }
        return zContains;
    }

    public void removeAll() {
        synchronized (this.mListenerList) {
            this.mListenerList.clear();
        }
    }

    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.mListenerList) {
            zIsEmpty = this.mListenerList.isEmpty();
        }
        return zIsEmpty;
    }

    public int size() {
        int size;
        synchronized (this.mListenerList) {
            size = this.mListenerList.size();
        }
        return size;
    }

    public final void notifyListener(Consumer<T> consumer) {
        lambda$asyncNotifyListener$1$LocalListenerHelper(consumer, ErrorPolicy.ContinueOnError);
    }

    /* JADX INFO: renamed from: notifyListener, reason: merged with bridge method [inline-methods] */
    public void lambda$asyncNotifyListener$1$LocalListenerHelper(Consumer<T> consumer, ErrorPolicy policy) {
        List<T> listeners;
        synchronized (this.mListenerList) {
            listeners = new ArrayList<>(this.mListenerList);
        }
        for (T listener : listeners) {
            try {
                consumer.accept(listener);
            } catch (Exception e) {
                int i = AnonymousClass1.$SwitchMap$com$ts$appservice$power$car$LocalListenerHelper$ErrorPolicy[policy.ordinal()];
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

    /* JADX INFO: renamed from: com.ts.appservice.power.car.LocalListenerHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$appservice$power$car$LocalListenerHelper$ErrorPolicy;

        static {
            int[] iArr = new int[ErrorPolicy.values().length];
            $SwitchMap$com$ts$appservice$power$car$LocalListenerHelper$ErrorPolicy = iArr;
            try {
                iArr[ErrorPolicy.ContinueAndRemoveOnError.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$appservice$power$car$LocalListenerHelper$ErrorPolicy[ErrorPolicy.BreakOnError.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$appservice$power$car$LocalListenerHelper$ErrorPolicy[ErrorPolicy.BreakAndRemoveOnError.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public final void asyncNotifyListener(final Consumer<T> consumer) {
        this.mHandler.post(new Runnable() { // from class: com.ts.appservice.power.car.-$$Lambda$LocalListenerHelper$U2U3or8xyvgN_2orlZpCOLJbR8g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$0$LocalListenerHelper(consumer);
            }
        });
    }

    public /* synthetic */ void lambda$asyncNotifyListener$0$LocalListenerHelper(Consumer consumer) {
        lambda$asyncNotifyListener$1$LocalListenerHelper(consumer, ErrorPolicy.ContinueOnError);
    }

    public final void asyncNotifyListener(final Consumer<T> consumer, final ErrorPolicy policy) {
        this.mHandler.post(new Runnable() { // from class: com.ts.appservice.power.car.-$$Lambda$LocalListenerHelper$Yrxwxg0i4cuWSD6XG5Ay1VrG9e8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$1$LocalListenerHelper(consumer, policy);
            }
        });
    }
}
