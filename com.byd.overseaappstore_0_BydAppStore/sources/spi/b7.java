package spi;

import com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment;
import com.byd.overseaappstore.ui.vm.DownloadViewModel;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$initListener$1$1", f = "DownloadListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class b7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object a;
    public final /* synthetic */ DownloadListFragment b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b7(DownloadListFragment downloadListFragment, Continuation<? super b7> continuation) {
        super(2, continuation);
        this.b = downloadListFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        b7 b7Var = new b7(this.b, continuation);
        b7Var.a = obj;
        return b7Var;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((b7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.a;
        DownloadViewModel downloadViewModelI = DownloadListFragment.i(this.b);
        Objects.requireNonNull(downloadViewModelI);
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new o7(downloadViewModelI, null), 3, null);
        return Unit.INSTANCE;
    }
}
