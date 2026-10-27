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

    /* JADX INFO: renamed from: android.hardware.bydauto.audio.tsimpl.LocalListenerHelper$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy;

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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$asyncNotifyListener$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a(Consumer consumer) {
        lambda$asyncNotifyListener$1(consumer, ErrorPolicy.ContinueOnError);
    }

    public boolean addListener(T t3) {
        if (t3 == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            if (this.mListenerList.contains(t3)) {
                return true;
            }
            this.mListenerList.add(t3);
            return true;
        }
    }

    public final void asyncNotifyListener(final Consumer<T> consumer) {
        this.mHandler.post(new Runnable() { // from class: c.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f1782b.a(consumer);
            }
        });
    }

    public boolean containsListener(T t3) {
        boolean zContains;
        if (t3 == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zContains = this.mListenerList.contains(t3);
        }
        return zContains;
    }

    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.mListenerList) {
            zIsEmpty = this.mListenerList.isEmpty();
        }
        return zIsEmpty;
    }

    public final void notifyListener(Consumer<T> consumer) {
        lambda$asyncNotifyListener$1(consumer, ErrorPolicy.ContinueOnError);
    }

    public void removeAll() {
        synchronized (this.mListenerList) {
            this.mListenerList.clear();
        }
    }

    public boolean removeListener(T t3) {
        boolean zRemove;
        if (t3 == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zRemove = this.mListenerList.remove(t3);
        }
        return zRemove;
    }

    public int size() {
        int size;
        synchronized (this.mListenerList) {
            size = this.mListenerList.size();
        }
        return size;
    }

    public final void asyncNotifyListener(final Consumer<T> consumer, final ErrorPolicy errorPolicy) {
        this.mHandler.post(new Runnable() { // from class: c.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f1784b.b(consumer, errorPolicy);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: notifyListener, reason: merged with bridge method [inline-methods] */
    public void b(Consumer<T> consumer, ErrorPolicy errorPolicy) {
        ArrayList arrayList;
        synchronized (this.mListenerList) {
            arrayList = new ArrayList(this.mListenerList);
        }
        for (Object obj : arrayList) {
            try {
                consumer.accept(obj);
            } catch (Exception unused) {
                int i3 = AnonymousClass1.$SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy[errorPolicy.ordinal()];
                if (i3 == 1) {
                    removeListener(obj);
                } else {
                    if (i3 == 2) {
                        return;
                    }
                    if (i3 == 3) {
                        removeListener(obj);
                        return;
                    }
                }
            }
        }
    }

    public LocalListenerHelper(Looper looper) {
        this.mListenerList = new ArrayList();
        this.mHandler = new Handler(looper);
    }
}
