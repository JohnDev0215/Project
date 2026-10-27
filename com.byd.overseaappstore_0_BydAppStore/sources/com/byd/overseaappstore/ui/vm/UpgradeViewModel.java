package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.ErrorUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.UpgradeUiState;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.oa;
import spi.qq;
import spi.s9;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BQ\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/UpgradeViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;", "errorUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;", "executorDialogRouterMgr", "Lspi/s9;", "executorHciMgr", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;", "executorSoftwareNewTaskInTrigger", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;", "languageChangeStateMgr", "<init>", "(Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;Lspi/s9;Lspi/oa;Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class UpgradeViewModel extends ViewModel {

    @NotNull
    public final SoftwareRepository a;

    @NotNull
    public final SoftwareUiStateGenerator b;

    @NotNull
    public final ErrorUiStateGenerator c;

    @NotNull
    public final s9 d;

    @NotNull
    public final oa e;

    @NotNull
    public final ExecutorSoftwareNewTaskInTrigger f;

    @NotNull
    public final DataGenerator g;

    @NotNull
    public final LanguageChangeStateMgr h;

    @NotNull
    public final SharedFlow<IUUState> i;

    @NotNull
    public final MutableStateFlow<Boolean> j;

    @NotNull
    public final StateFlow<Boolean> k;

    @NotNull
    public final MutableStateFlow<UpgradeUiState> l;

    @NotNull
    public final StateFlow<UpgradeUiState> m;

    @NotNull
    public final MutableStateFlow<ErrorEmptyUiState> n;

    @NotNull
    public final StateFlow<ErrorEmptyUiState> o;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.UpgradeViewModel", f = "UpgradeViewModel.kt", i = {0, 0}, l = {142}, m = "deleteUpdateDownloadWork", n = {"this", "coroutineScope"}, s = {"L$0", "L$1"})
    public static final class a extends ContinuationImpl {
        public UpgradeViewModel a;
        public CoroutineScope b;
        public /* synthetic */ Object c;
        public int e;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.c = obj;
            this.e |= Integer.MIN_VALUE;
            return UpgradeViewModel.this.a(null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.UpgradeViewModel", f = "UpgradeViewModel.kt", i = {0, 0}, l = {153}, m = "deleteUpdateWaitingWork", n = {"this", "coroutineScope"}, s = {"L$0", "L$1"})
    public static final class b extends ContinuationImpl {
        public UpgradeViewModel a;
        public CoroutineScope b;
        public /* synthetic */ Object c;
        public int e;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.c = obj;
            this.e |= Integer.MIN_VALUE;
            return UpgradeViewModel.this.b(null, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.UpgradeViewModel$fetchUpgradeList$1", f = "UpgradeViewModel.kt", i = {}, l = {79, 79}, m = "invokeSuspend", n = {}, s = {})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public static final class a<T> implements FlowCollector {
            public final /* synthetic */ UpgradeViewModel a;

            public a(UpgradeViewModel upgradeViewModel) {
                this.a = upgradeViewModel;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                SourceDataBody<List<qq>> sourceDataBody = (SourceDataBody) obj;
                UpgradeViewModel upgradeViewModel = this.a;
                upgradeViewModel.l.setValue(upgradeViewModel.b.generateUpgradeSoftwareUiState(sourceDataBody));
                UpgradeViewModel upgradeViewModel2 = this.a;
                upgradeViewModel2.j.setValue(Boxing.boxBoolean(upgradeViewModel2.b.generateStartAllPauseAllUiState(sourceDataBody)));
                UpgradeViewModel upgradeViewModel3 = this.a;
                upgradeViewModel3.n.setValue(upgradeViewModel3.c.generateErrorUiStateForUpdateManager(sourceDataBody));
                return Unit.INSTANCE;
            }
        }

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return UpgradeViewModel.this.new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SoftwareRepository softwareRepository = UpgradeViewModel.this.a;
                this.a = 1;
                obj = softwareRepository.g();
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            a aVar = new a(UpgradeViewModel.this);
            this.a = 2;
            if (((Flow) obj).collect(aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public UpgradeViewModel(@NotNull SoftwareRepository softwareRepository, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull ErrorUiStateGenerator errorUiStateGenerator, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull s9 executorHciMgr, @NotNull oa executorSoftwareStateMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull DataGenerator dataGenerator, @NotNull LanguageChangeStateMgr languageChangeStateMgr) {
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(softwareUiStateGenerator, "softwareUiStateGenerator");
        Intrinsics.checkNotNullParameter(errorUiStateGenerator, "errorUiStateGenerator");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(languageChangeStateMgr, "languageChangeStateMgr");
        this.a = softwareRepository;
        this.b = softwareUiStateGenerator;
        this.c = errorUiStateGenerator;
        this.d = executorHciMgr;
        this.e = executorSoftwareStateMgr;
        this.f = executorSoftwareNewTaskInTrigger;
        this.g = dataGenerator;
        this.h = languageChangeStateMgr;
        this.i = executorDialogRouterMgr.getExecutorIuuState().getIuuStateDataFlow();
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.j = MutableStateFlow;
        this.k = MutableStateFlow;
        MutableStateFlow<UpgradeUiState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new UpgradeUiState(null, false, 3, null));
        this.l = MutableStateFlow2;
        this.m = MutableStateFlow2;
        MutableStateFlow<ErrorEmptyUiState> MutableStateFlow3 = StateFlowKt.MutableStateFlow(new ErrorEmptyUiState(false, 0, 0, 7, null));
        this.n = MutableStateFlow3;
        this.o = MutableStateFlow3;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlinx.coroutines.CoroutineScope r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.byd.overseaappstore.ui.vm.UpgradeViewModel.a
            if (r0 == 0) goto L13
            r0 = r7
            com.byd.overseaappstore.ui.vm.UpgradeViewModel$a r0 = (com.byd.overseaappstore.ui.vm.UpgradeViewModel.a) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.UpgradeViewModel$a r0 = new com.byd.overseaappstore.ui.vm.UpgradeViewModel$a
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            kotlinx.coroutines.CoroutineScope r6 = r0.b
            com.byd.overseaappstore.ui.vm.UpgradeViewModel r5 = r0.a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L48
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            spi.s9 r7 = r4.d
            r0.a = r4
            r0.b = r6
            r0.e = r3
            java.lang.Object r5 = spi.s9.a(r7, r5, r0)
            if (r5 != r1) goto L47
            return r1
        L47:
            r5 = r4
        L48:
            r5.c(r6)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.UpgradeViewModel.a(java.lang.String, kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlinx.coroutines.CoroutineScope r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.byd.overseaappstore.ui.vm.UpgradeViewModel.b
            if (r0 == 0) goto L13
            r0 = r7
            com.byd.overseaappstore.ui.vm.UpgradeViewModel$b r0 = (com.byd.overseaappstore.ui.vm.UpgradeViewModel.b) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.UpgradeViewModel$b r0 = new com.byd.overseaappstore.ui.vm.UpgradeViewModel$b
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            kotlinx.coroutines.CoroutineScope r6 = r0.b
            com.byd.overseaappstore.ui.vm.UpgradeViewModel r5 = r0.a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L48
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            spi.oa r7 = r4.e
            r0.a = r4
            r0.b = r6
            r0.e = r3
            java.lang.Object r5 = r7.f(r5, r0)
            if (r5 != r1) goto L47
            return r1
        L47:
            r5 = r4
        L48:
            r5.c(r6)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.UpgradeViewModel.b(java.lang.String, kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void c(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new c(null), 3, null);
    }
}
