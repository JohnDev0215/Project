package com.byd.card.floatcard;

import android.content.Context;
import android.os.Looper;
import com.android.systemui.util.SystemUIException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class MainThreadInitializedObject<T> {
    private final ObjectProvider<T> mProvider;
    private T mValue;

    public interface ObjectProvider<T> {
        T get(Context context);
    }

    public MainThreadInitializedObject(ObjectProvider<T> provider) {
        this.mProvider = provider;
    }

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public T lambda$get$1$MainThreadInitializedObject(final Context context) {
        if (this.mValue == null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.mValue = (T) TraceHelper.whitelistIpcs("main.thread.object", new Supplier() { // from class: com.byd.card.floatcard.-$$Lambda$MainThreadInitializedObject$GuBJLb6bmMzr4nxlcrjREu_ph10
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return this.f$0.lambda$get$0$MainThreadInitializedObject(context);
                    }
                });
            } else {
                try {
                    return Executors.MAIN_EXECUTOR.submit(new Callable() { // from class: com.byd.card.floatcard.-$$Lambda$MainThreadInitializedObject$Wieb8b_ZbQsI2WI9BVwBujkl_E4
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return this.f$0.lambda$get$1$MainThreadInitializedObject(context);
                        }
                    }).get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new SystemUIException(e);
                }
            }
        }
        return this.mValue;
    }

    public /* synthetic */ Object lambda$get$0$MainThreadInitializedObject(Context context) {
        return this.mProvider.get(context.getApplicationContext());
    }

    public T getNoCreate() {
        return this.mValue;
    }

    public void initializeForTesting(T value) {
        this.mValue = value;
    }
}
