package com.byd.automultipletheme.common.base;

import androidx.lifecycle.LiveDataScope;
import com.blankj.utilcode.util.AppUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/lifecycle/LiveDataScope;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.byd.automultipletheme.common.base.BaseViewModel$appVersion$1", f = "BaseViewModel.kt", i = {}, l = {11}, m = "invokeSuspend", n = {}, s = {})
final class BaseViewModel$appVersion$1 extends SuspendLambda implements Function2<LiveDataScope<String>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    BaseViewModel$appVersion$1(Continuation<? super BaseViewModel$appVersion$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BaseViewModel$appVersion$1 baseViewModel$appVersion$1 = new BaseViewModel$appVersion$1(continuation);
        baseViewModel$appVersion$1.L$0 = obj;
        return baseViewModel$appVersion$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(LiveDataScope<String> liveDataScope, Continuation<? super Unit> continuation) {
        return ((BaseViewModel$appVersion$1) create(liveDataScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LiveDataScope liveDataScope = (LiveDataScope) this.L$0;
            String appVersionName = AppUtils.getAppVersionName();
            Intrinsics.checkNotNullExpressionValue(appVersionName, "getAppVersionName()");
            this.label = 1;
            if (liveDataScope.emit(appVersionName, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
