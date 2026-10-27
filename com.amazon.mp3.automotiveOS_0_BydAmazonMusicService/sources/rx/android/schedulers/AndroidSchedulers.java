package rx.android.schedulers;

import android.os.Looper;
import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;

/* JADX INFO: loaded from: classes6.dex */
public final class AndroidSchedulers {
    private static final AtomicReference<AndroidSchedulers> INSTANCE = new AtomicReference<>();
    private final Scheduler mainThreadScheduler;

    private static AndroidSchedulers getInstance() {
        AtomicReference<AndroidSchedulers> atomicReference;
        AndroidSchedulers androidSchedulers;
        do {
            atomicReference = INSTANCE;
            AndroidSchedulers androidSchedulers2 = atomicReference.get();
            if (androidSchedulers2 != null) {
                return androidSchedulers2;
            }
            androidSchedulers = new AndroidSchedulers();
        } while (!CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, androidSchedulers));
        return androidSchedulers;
    }

    private AndroidSchedulers() {
        Scheduler mainThreadScheduler = RxAndroidPlugins.getInstance().getSchedulersHook().getMainThreadScheduler();
        if (mainThreadScheduler != null) {
            this.mainThreadScheduler = mainThreadScheduler;
        } else {
            this.mainThreadScheduler = new LooperScheduler(Looper.getMainLooper());
        }
    }

    public static Scheduler mainThread() {
        return getInstance().mainThreadScheduler;
    }

    public static Scheduler from(Looper looper) {
        if (looper == null) {
            throw new NullPointerException("looper == null");
        }
        return new LooperScheduler(looper);
    }
}
