package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.MainViewModel$collectDownloadFinishFlow$1", f = "MainViewModel.kt", i = {}, l = {468}, m = "invokeSuspend", n = {}, s = {})
public final class qh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MainViewModel b;
    public final /* synthetic */ Context c;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ MainViewModel a;
        public final /* synthetic */ Context b;

        public a(MainViewModel mainViewModel, Context context) {
            this.a = mainViewModel;
            this.b = context;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            SoftwareOptParam softwareOptParam = (SoftwareOptParam) obj;
            IUUState toIUUState = this.a.g.parseToIUUState(softwareOptParam, 3);
            int iB = this.a.f.b(softwareOptParam.getPackageName());
            if (iB == 5 || iB == 12) {
                oa oaVar = this.a.f;
                String packageName = softwareOptParam.getPackageName();
                SoftwareStateData softwareStateData = new SoftwareStateData(0, 13);
                oaVar.h(packageName, softwareStateData);
                this.a.e.b(toIUUState.getSoftwareOptParam(), this.b);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qh(MainViewModel mainViewModel, Context context, Continuation<? super qh> continuation) {
        super(2, continuation);
        this.b = mainViewModel;
        this.c = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new qh(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((qh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow<SoftwareOptParam> needStartInstallFlow = this.b.m.getNeedStartInstallFlow();
            a aVar = new a(this.b, this.c);
            this.a = 1;
            if (needStartInstallFlow.collect(aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
