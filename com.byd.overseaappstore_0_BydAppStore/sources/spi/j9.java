package spi;

import com.byd.overseaappstore.module.ability.data.ApiDataBody;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr$bizFetchNetworkingForDownloadsCount$1", f = "ExecutorDbMgr.kt", i = {}, l = {422}, m = "invokeSuspend", n = {}, s = {})
public final class j9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    public int a;
    public final /* synthetic */ i9 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j9(i9 i9Var, Continuation<? super j9> continuation) {
        super(2, continuation);
        this.b = i9Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new j9(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((j9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        List list;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            yp ypVar = this.b.a;
            this.a = 1;
            obj = ypVar.c(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ApiDataBody apiDataBody = (ApiDataBody) obj;
        Collection collection = (Collection) apiDataBody.getData();
        int size = 0;
        if (collection != null && !collection.isEmpty()) {
            z = false;
        }
        if (!z && (list = (List) apiDataBody.getData()) != null) {
            size = list.size();
        }
        return Boxing.boxInt(size);
    }
}
