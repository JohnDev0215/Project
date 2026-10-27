package android.hardware.bydauto.audio.tsimpl;

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

    public boolean addListener(T t) {
        if (t == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            if (this.mListenerList.contains(t)) {
                return true;
            }
            this.mListenerList.add(t);
            return true;
        }
    }

    public boolean removeListener(T t) {
        boolean zRemove;
        if (t == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zRemove = this.mListenerList.remove(t);
        }
        return zRemove;
    }

    public boolean containsListener(T t) {
        boolean zContains;
        if (t == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zContains = this.mListenerList.contains(t);
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: notifyListener, reason: merged with bridge method [inline-methods] */
    public void lambda$asyncNotifyListener$1$LocalListenerHelper(Consumer<T> consumer, ErrorPolicy errorPolicy) {
        ArrayList arrayList;
        synchronized (this.mListenerList) {
            arrayList = new ArrayList(this.mListenerList);
        }
        for (Object obj : arrayList) {
            try {
                consumer.accept(obj);
            } catch (Exception unused) {
                int i = AnonymousClass1.$SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy[errorPolicy.ordinal()];
                if (i == 1) {
                    removeListener(obj);
                } else {
                    if (i == 2) {
                        return;
                    }
                    if (i == 3) {
                        removeListener(obj);
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: android.hardware.bydauto.audio.tsimpl.LocalListenerHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy;

        static {
            int[] iArr = new int[ErrorPolicy.values().length];
            $SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy = iArr;
            try {
                iArr[ErrorPolicy.ContinueAndRemoveOnError.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy[ErrorPolicy.BreakOnError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy[ErrorPolicy.BreakAndRemoveOnError.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public final void asyncNotifyListener(final Consumer<T> consumer) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.bydauto.audio.tsimpl.-$$Lambda$LocalListenerHelper$6GEHubyyeAiBGcTjIY6B9ZEH2C0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$0$LocalListenerHelper(consumer);
            }
        });
    }

    public /* synthetic */ void lambda$asyncNotifyListener$0$LocalListenerHelper(Consumer consumer) {
        lambda$asyncNotifyListener$1$LocalListenerHelper(consumer, ErrorPolicy.ContinueOnError);
    }

    public final void asyncNotifyListener(final Consumer<T> consumer, final ErrorPolicy errorPolicy) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.bydauto.audio.tsimpl.-$$Lambda$LocalListenerHelper$ZoaJBaYEk1JhzxptTHrwGXZC2sw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncNotifyListener$1$LocalListenerHelper(consumer, errorPolicy);
            }
        });
    }
}
