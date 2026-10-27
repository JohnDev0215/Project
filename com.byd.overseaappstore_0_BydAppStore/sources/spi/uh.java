package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import java.util.List;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.MainViewModel$syncAllDataAndWork$2", f = "MainViewModel.kt", i = {}, l = {106, 112}, m = "invokeSuspend", n = {}, s = {})
public final class uh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MainViewModel b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public uh(MainViewModel mainViewModel, Continuation<? super uh> continuation) {
        super(2, continuation);
        this.b = mainViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new uh(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((uh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        MainViewModel mainViewModel;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.b.l.getInitSyncFinishDispatcher().notifyInitSyncFinishDispatch(false);
            i9 i9Var = this.b.i;
            this.a = 1;
            obj = i9Var.C(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                mainViewModel = this.b;
                mainViewModel.l.getInitSyncFinishDispatcher().notifyInitSyncFinishDispatch(true);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        List<qq> list = (List) obj;
        if (list == null || list.isEmpty()) {
            ct.a("operate resumeWork db contain data:false", new Object[0]);
            mainViewModel = this.b;
            mainViewModel.l.getInitSyncFinishDispatcher().notifyInitSyncFinishDispatch(true);
            return Unit.INSTANCE;
        }
        s9 s9Var = this.b.a;
        this.a = 2;
        a2 a2Var = new a2((byte) 0, (byte) 0, (byte) 0, 7, null);
        q9 q9Var = s9Var.a;
        List<SoftwareOptParam> toSoftwareOptParamList = q9Var.b.parseToSoftwareOptParamList(list);
        y9 y9Var = q9Var.a;
        Objects.requireNonNull(y9Var);
        ct.a("executorHciWorkLine handleInitSync size:" + toSoftwareOptParamList.size() + " bizSrc:" + a2Var, new Object[0]);
        Object objC = y9Var.a.c(a2Var, FlowKt.flowOf(y9Var.b.parseToWorkCarrier(toSoftwareOptParamList)), this);
        if (objC != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objC = Unit.INSTANCE;
        }
        if (objC != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objC = Unit.INSTANCE;
        }
        if (objC != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objC = Unit.INSTANCE;
        }
        if (objC == coroutine_suspended) {
            return coroutine_suspended;
        }
        mainViewModel = this.b;
        mainViewModel.l.getInitSyncFinishDispatcher().notifyInitSyncFinishDispatch(true);
        return Unit.INSTANCE;
    }
}
