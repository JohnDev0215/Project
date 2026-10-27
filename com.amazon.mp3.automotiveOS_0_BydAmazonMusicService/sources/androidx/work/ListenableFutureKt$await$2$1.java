package androidx.work;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CancellableContinuation;

/* JADX INFO: compiled from: ListenableFuture.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\n"}, d2 = {"R", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
public final class ListenableFutureKt$await$2$1 implements Runnable {
    final /* synthetic */ CancellableContinuation<R> $cancellableContinuation;
    final /* synthetic */ ListenableFuture<R> $this_await;

    /* JADX WARN: Multi-variable type inference failed */
    public ListenableFutureKt$await$2$1(CancellableContinuation<? super R> cancellableContinuation, ListenableFuture<R> listenableFuture) {
        this.$cancellableContinuation = cancellableContinuation;
        this.$this_await = listenableFuture;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.$cancellableContinuation.resumeWith(Result.m2810constructorimpl(this.$this_await.get()));
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause == null) {
                cause = th;
            }
            if (th instanceof CancellationException) {
                this.$cancellableContinuation.cancel(cause);
                return;
            }
            Continuation continuation = this.$cancellableContinuation;
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m2810constructorimpl(ResultKt.createFailure(cause)));
        }
    }
}
