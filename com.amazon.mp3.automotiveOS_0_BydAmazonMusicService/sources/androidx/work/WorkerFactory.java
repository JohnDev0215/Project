package androidx.work;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WorkerFactory {
    private static final String TAG = Logger.tagWithPrefix("WorkerFactory");

    @Nullable
    public abstract ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters);

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final ListenableWorker createWorkerWithDefaultFallback(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        ListenableWorker listenableWorkerCreateWorker = createWorker(appContext, workerClassName, workerParameters);
        if (listenableWorkerCreateWorker == null) {
            Class clsAsSubclass = null;
            try {
                clsAsSubclass = Class.forName(workerClassName).asSubclass(ListenableWorker.class);
            } catch (Throwable th) {
                Logger.get().error(TAG, "Invalid class: " + workerClassName, th);
            }
            if (clsAsSubclass != null) {
                try {
                    listenableWorkerCreateWorker = (ListenableWorker) clsAsSubclass.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(appContext, workerParameters);
                } catch (Throwable th2) {
                    Logger.get().error(TAG, "Could not instantiate " + workerClassName, th2);
                }
            }
        }
        if (listenableWorkerCreateWorker == null || !listenableWorkerCreateWorker.isUsed()) {
            return listenableWorkerCreateWorker;
        }
        throw new IllegalStateException(String.format("WorkerFactory (%s) returned an instance of a ListenableWorker (%s) which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.", getClass().getName(), workerClassName));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static WorkerFactory getDefaultWorkerFactory() {
        return new WorkerFactory() { // from class: androidx.work.WorkerFactory.1
            @Override // androidx.work.WorkerFactory
            @Nullable
            public ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
                return null;
            }
        };
    }
}
