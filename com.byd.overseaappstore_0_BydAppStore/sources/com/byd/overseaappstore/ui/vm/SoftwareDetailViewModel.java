package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.ErrorUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDetailRsp;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.LoadingUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareDetailUiState;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetail;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.oa;
import spi.s9;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BI\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/SoftwareDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;", "errorUiStateGenerator", "Lspi/s9;", "executorHciMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;", "executorSoftwareNewTaskInTrigger", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;", "languageChangeStateMgr", "<init>", "(Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;Lspi/s9;Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;Lspi/oa;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SoftwareDetailViewModel extends ViewModel {

    @NotNull
    public final SoftwareRepository a;

    @NotNull
    public final SoftwareUiStateGenerator b;

    @NotNull
    public final ErrorUiStateGenerator c;

    @NotNull
    public final s9 d;

    @NotNull
    public final ExecutorSoftwareNewTaskInTrigger e;

    @NotNull
    public final oa f;

    @NotNull
    public final DataGenerator g;

    @NotNull
    public final LanguageChangeStateMgr h;

    @NotNull
    public final MutableStateFlow<SoftwareDetailUiState> i;

    @NotNull
    public final StateFlow<SoftwareDetailUiState> j;

    @NotNull
    public final MutableStateFlow<LoadingUiState> k;

    @NotNull
    public final StateFlow<LoadingUiState> l;

    @NotNull
    public final MutableStateFlow<ErrorEmptyUiState> m;

    @NotNull
    public final StateFlow<ErrorEmptyUiState> n;

    @NotNull
    public final MutableStateFlow<SoftwareStateData> o;

    @NotNull
    public final StateFlow<SoftwareStateData> p;
    public short q;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel$fetchDetail$1", f = "SoftwareDetailViewModel.kt", i = {}, l = {75, 75}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ int c;
        public final /* synthetic */ CoroutineScope d;

        /* JADX INFO: renamed from: com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel$a$a, reason: collision with other inner class name */
        public static final class C0115a<T> implements FlowCollector {
            public final /* synthetic */ SoftwareDetailViewModel a;

            public C0115a(SoftwareDetailViewModel softwareDetailViewModel) {
                this.a = softwareDetailViewModel;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                SourceDataBody<NetRspDataBody<SoftwareDetailRsp>> sourceDataBody = (SourceDataBody) obj;
                this.a.k.setValue(new LoadingUiState(null, false, 1, null));
                SoftwareDetailViewModel softwareDetailViewModel = this.a;
                softwareDetailViewModel.i.setValue(softwareDetailViewModel.b.generateSoftwareDetailUiState(sourceDataBody));
                SoftwareDetailViewModel softwareDetailViewModel2 = this.a;
                softwareDetailViewModel2.m.setValue(softwareDetailViewModel2.c.generateErrorUiState(sourceDataBody));
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(int i, CoroutineScope coroutineScope, Continuation<? super a> continuation) {
            super(2, continuation);
            this.c = i;
            this.d = coroutineScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SoftwareDetailViewModel.this.new a(this.c, this.d, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0083  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 261
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public SoftwareDetailViewModel(@NotNull SoftwareRepository softwareRepository, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull ErrorUiStateGenerator errorUiStateGenerator, @NotNull s9 executorHciMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull oa executorSoftwareStateMgr, @NotNull DataGenerator dataGenerator, @NotNull LanguageChangeStateMgr languageChangeStateMgr) {
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(softwareUiStateGenerator, "softwareUiStateGenerator");
        Intrinsics.checkNotNullParameter(errorUiStateGenerator, "errorUiStateGenerator");
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(languageChangeStateMgr, "languageChangeStateMgr");
        this.a = softwareRepository;
        this.b = softwareUiStateGenerator;
        this.c = errorUiStateGenerator;
        this.d = executorHciMgr;
        this.e = executorSoftwareNewTaskInTrigger;
        this.f = executorSoftwareStateMgr;
        this.g = dataGenerator;
        this.h = languageChangeStateMgr;
        MutableStateFlow<SoftwareDetailUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new SoftwareDetailUiState(null, false, 3, null));
        this.i = MutableStateFlow;
        this.j = MutableStateFlow;
        MutableStateFlow<LoadingUiState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new LoadingUiState(null, false, 3, null));
        this.k = MutableStateFlow2;
        this.l = MutableStateFlow2;
        MutableStateFlow<ErrorEmptyUiState> MutableStateFlow3 = StateFlowKt.MutableStateFlow(new ErrorEmptyUiState(false, 0, 0, 7, null));
        this.m = MutableStateFlow3;
        this.n = MutableStateFlow3;
        MutableStateFlow<SoftwareStateData> MutableStateFlow4 = StateFlowKt.MutableStateFlow(new SoftwareStateData(0, 0));
        this.o = MutableStateFlow4;
        this.p = MutableStateFlow4;
        this.q = dataGenerator.getLanguageCode();
    }

    public final void a(int i, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.i.setValue(new SoftwareDetailUiState(null, false, 1, null));
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new a(i, coroutineScope, null), 3, null);
    }

    public final void b() {
        Software software;
        SoftwareDetail data = this.j.getValue().getData();
        if (data == null || (software = data.getSoftware()) == null) {
            return;
        }
        this.d.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), this.g.parseToSoftwareOptParam(software));
    }
}
