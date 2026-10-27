package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WorkContinuation {
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected abstract WorkContinuation combineInternal(@NonNull List<WorkContinuation> continuations);

    @NonNull
    public abstract Operation enqueue();

    @NonNull
    public abstract ListenableFuture<List<WorkInfo>> getWorkInfos();

    @NonNull
    public abstract LiveData<List<WorkInfo>> getWorkInfosLiveData();

    @NonNull
    public abstract WorkContinuation then(@NonNull List<OneTimeWorkRequest> work);

    @NonNull
    public final WorkContinuation then(@NonNull OneTimeWorkRequest work) {
        return then(Collections.singletonList(work));
    }

    @NonNull
    public static WorkContinuation combine(@NonNull List<WorkContinuation> continuations) {
        return continuations.get(0).combineInternal(continuations);
    }
}
