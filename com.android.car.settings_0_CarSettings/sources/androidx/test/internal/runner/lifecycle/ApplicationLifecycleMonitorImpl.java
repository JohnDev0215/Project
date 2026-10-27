package androidx.test.internal.runner.lifecycle;

import android.app.Application;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.runner.lifecycle.ApplicationLifecycleCallback;
import androidx.test.runner.lifecycle.ApplicationLifecycleMonitor;
import androidx.test.runner.lifecycle.ApplicationStage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationLifecycleMonitorImpl implements ApplicationLifecycleMonitor {
    private static final String TAG = "ApplicationLifecycleMonitorImpl";
    private final List<WeakReference<ApplicationLifecycleCallback>> callbacks = new ArrayList();

    @Override // androidx.test.runner.lifecycle.ApplicationLifecycleMonitor
    public void addLifecycleCallback(ApplicationLifecycleCallback callback) {
        Checks.checkNotNull(callback);
        synchronized (this.callbacks) {
            boolean needsAdd = true;
            Iterator<WeakReference<ApplicationLifecycleCallback>> refIter = this.callbacks.iterator();
            while (refIter.hasNext()) {
                ApplicationLifecycleCallback storedCallback = refIter.next().get();
                if (storedCallback == null) {
                    refIter.remove();
                } else if (storedCallback == callback) {
                    needsAdd = false;
                }
            }
            if (needsAdd) {
                this.callbacks.add(new WeakReference<>(callback));
            }
        }
    }

    @Override // androidx.test.runner.lifecycle.ApplicationLifecycleMonitor
    public void removeLifecycleCallback(ApplicationLifecycleCallback callback) {
        Checks.checkNotNull(callback);
        synchronized (this.callbacks) {
            Iterator<WeakReference<ApplicationLifecycleCallback>> refIter = this.callbacks.iterator();
            while (refIter.hasNext()) {
                ApplicationLifecycleCallback storedCallback = refIter.next().get();
                if (storedCallback == null) {
                    refIter.remove();
                } else if (storedCallback == callback) {
                    refIter.remove();
                }
            }
        }
    }

    public void signalLifecycleChange(Application app, ApplicationStage stage) {
        synchronized (this.callbacks) {
            Iterator<WeakReference<ApplicationLifecycleCallback>> refIter = this.callbacks.iterator();
            while (refIter.hasNext()) {
                ApplicationLifecycleCallback callback = refIter.next().get();
                if (callback == null) {
                    refIter.remove();
                } else {
                    try {
                        String strValueOf = String.valueOf(callback);
                        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 18);
                        sb.append("running callback: ");
                        sb.append(strValueOf);
                        Log.d(TAG, sb.toString());
                        callback.onApplicationLifecycleChanged(app, stage);
                        String strValueOf2 = String.valueOf(callback);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 20);
                        sb2.append("callback completes: ");
                        sb2.append(strValueOf2);
                        Log.d(TAG, sb2.toString());
                    } catch (RuntimeException re) {
                        Log.e(TAG, String.format("Callback threw exception! (callback: %s stage: %s)", callback, stage), re);
                    }
                }
            }
        }
    }
}
