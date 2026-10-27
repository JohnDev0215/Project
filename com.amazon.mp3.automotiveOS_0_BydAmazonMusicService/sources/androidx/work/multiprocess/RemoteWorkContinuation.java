package androidx.work.multiprocess;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.OneTimeWorkRequest;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RemoteWorkContinuation {
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected abstract RemoteWorkContinuation combineInternal(@NonNull List<RemoteWorkContinuation> continuations);

    @NonNull
    public abstract ListenableFuture<Void> enqueue();

    @NonNull
    public abstract RemoteWorkContinuation then(@NonNull List<OneTimeWorkRequest> work);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected RemoteWorkContinuation() {
    }

    @NonNull
    public final RemoteWorkContinuation then(@NonNull OneTimeWorkRequest work) {
        return then(Collections.singletonList(work));
    }

    @NonNull
    public static RemoteWorkContinuation combine(@NonNull List<RemoteWorkContinuation> continuations) {
        return continuations.get(0).combineInternal(continuations);
    }
}
