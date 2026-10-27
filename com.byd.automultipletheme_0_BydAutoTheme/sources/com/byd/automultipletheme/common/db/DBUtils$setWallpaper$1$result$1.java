package com.byd.automultipletheme.common.db;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.byd.automultipletheme.common.db.DBUtils$setWallpaper$1$result$1", f = "DBUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class DBUtils$setWallpaper$1$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ String $path;
    final /* synthetic */ int $resId;
    final /* synthetic */ int $type;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DBUtils$setWallpaper$1$result$1(String str, int i, int i2, Continuation<? super DBUtils$setWallpaper$1$result$1> continuation) {
        super(2, continuation);
        this.$path = str;
        this.$type = i;
        this.$resId = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DBUtils$setWallpaper$1$result$1(this.$path, this.$type, this.$resId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((DBUtils$setWallpaper$1$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new UpdateWallpaperCallable(this.$path, this.$type, this.$resId).call();
    }
}
