package com.amazon.music.views.library.views;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: ScopedViews.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u000e2\u00020\u00012\u00020\u0002:\u0001\u000eB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/views/ScopedConstraintLayout;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/views/ScopedView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "limitedScope", "Lkotlinx/coroutines/CoroutineScope;", "getLimitedScope", "()Lkotlinx/coroutines/CoroutineScope;", "setLimitedScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "clear", "", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ScopedConstraintLayout extends ConstraintLayout implements ScopedView {
    private static final CoroutineDispatcher limitedDispatcher = Dispatchers.getIO().limitedParallelism(4);
    private CoroutineScope limitedScope;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScopedConstraintLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.limitedScope = CoroutineScopeKt.CoroutineScope(limitedDispatcher.plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    }

    @Override // com.amazon.music.views.library.views.ScopedView
    public CoroutineScope getLimitedScope() {
        return this.limitedScope;
    }

    public void setLimitedScope(CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<set-?>");
        this.limitedScope = coroutineScope;
    }

    @Override // com.amazon.music.views.library.views.ScopedView
    public void clear() {
        JobKt__JobKt.cancelChildren$default(JobKt.getJob(getLimitedScope().getCoroutineContext()), (CancellationException) null, 1, (Object) null);
    }
}
