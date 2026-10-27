package android.hardware.bydauto.audio.tsimpl;

import A2.m;
import T1.k;
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
    public /* synthetic */ void lambda$asyncNotifyListener$0(Consumer consumer) {
        lambda$asyncNotifyListener$1(consumer, ErrorPolicy.ContinueOnError);
    }

    public boolean addListener(T t10) {
        if (t10 == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            try {
                if (this.mListenerList.contains(t10)) {
                    return true;
                }
                this.mListenerList.add(t10);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void asyncNotifyListener(Consumer<T> consumer) {
        this.mHandler.post(new m(this, 2, consumer));
    }

    public boolean containsListener(T t10) {
        boolean zContains;
        if (t10 == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zContains = this.mListenerList.contains(t10);
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

    public boolean removeListener(T t10) {
        boolean zRemove;
        if (t10 == null) {
            return false;
        }
        synchronized (this.mListenerList) {
            zRemove = this.mListenerList.remove(t10);
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

    public final void asyncNotifyListener(Consumer<T> consumer, ErrorPolicy errorPolicy) {
        this.mHandler.post(new k(this, consumer, errorPolicy, 1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: notifyListener, reason: merged with bridge method [inline-methods] */
    public void lambda$asyncNotifyListener$1(Consumer<T> consumer, ErrorPolicy errorPolicy) {
        ArrayList arrayList;
        synchronized (this.mListenerList) {
            arrayList = new ArrayList(this.mListenerList);
        }
        for (Object obj : arrayList) {
            try {
                consumer.accept(obj);
            } catch (Exception unused) {
                int i7 = AnonymousClass1.$SwitchMap$android$hardware$bydauto$audio$tsimpl$LocalListenerHelper$ErrorPolicy[errorPolicy.ordinal()];
                if (i7 == 1) {
                    removeListener(obj);
                } else {
                    if (i7 == 2) {
                        return;
                    }
                    if (i7 == 3) {
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
