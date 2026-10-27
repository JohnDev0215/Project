package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.ErrorUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.uistate.DownloadItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ir;
import spi.jr;
import spi.oa;
import spi.qq;
import spi.s9;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BQ\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/DownloadViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;", "errorUiStateGenerator", "Lspi/s9;", "executorHciMgr", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;", "executorSoftwareNewTaskInTrigger", "Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;", "executorDialogRouterMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;", "languageChangeStateMgr", "<init>", "(Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;Lspi/s9;Lspi/oa;Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class DownloadViewModel extends ViewModel {

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
    public final MutableStateFlow<DownloadItemUiState> l;

    @NotNull
    public final StateFlow<DownloadItemUiState> m;

    @NotNull
    public final MutableStateFlow<ErrorEmptyUiState> n;

    @NotNull
    public final StateFlow<ErrorEmptyUiState> o;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.DownloadViewModel", f = "DownloadViewModel.kt", i = {0, 0, 1, 1, 2, 2}, l = {164, 168, 172}, m = "deleteDownloadWork", n = {"this", "coroutineScope", "this", "coroutineScope", "this", "coroutineScope"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
    public static final class a extends ContinuationImpl {
        public DownloadViewModel a;
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
            return DownloadViewModel.this.a(null, 0, null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.DownloadViewModel$fetchDownloadList$1", f = "DownloadViewModel.kt", i = {}, l = {77, 77}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public static final class a<T> implements FlowCollector {
            public final /* synthetic */ DownloadViewModel a;

            public a(DownloadViewModel downloadViewModel) {
                this.a = downloadViewModel;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                SourceDataBody<List<qq>> sourceDataBody = (SourceDataBody) obj;
                DownloadViewModel downloadViewModel = this.a;
                downloadViewModel.l.setValue(downloadViewModel.b.generateDownloadItemUiState(sourceDataBody));
                DownloadViewModel downloadViewModel2 = this.a;
                downloadViewModel2.j.setValue(Boxing.boxBoolean(downloadViewModel2.b.generateStartAllPauseAllUiState(sourceDataBody)));
                DownloadViewModel downloadViewModel3 = this.a;
                downloadViewModel3.n.setValue(downloadViewModel3.c.generateErrorUiStateForDownloadManager(sourceDataBody));
                return Unit.INSTANCE;
            }
        }

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return DownloadViewModel.this.new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SoftwareRepository softwareRepository = DownloadViewModel.this.a;
                this.a = 1;
                Objects.requireNonNull(softwareRepository);
                final Flow flow = FlowKt.flow(new ir(softwareRepository, null));
                obj = FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<List<? extends qq>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1$2, reason: invalid class name */
                    public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                        public final /* synthetic */ FlowCollector a;

                        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1$2$1, reason: invalid class name */
                        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
                        public static final class AnonymousClass1 extends ContinuationImpl {
                            public Object L$0;
                            public int label;
                            public /* synthetic */ Object result;

                            public AnonymousClass1(Continuation continuation) {
                                super(continuation);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @Nullable
                            public final Object invokeSuspend(@NotNull Object obj) {
                                this.result = obj;
                                this.label |= Integer.MIN_VALUE;
                                return AnonymousClass2.this.emit(null, this);
                            }
                        }

                        public AnonymousClass2(FlowCollector flowCollector) {
                            this.a = flowCollector;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        @org.jetbrains.annotations.Nullable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final java.lang.Object emit(java.lang.Object r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r22) {
                            /*
                                r20 = this;
                                r0 = r20
                                r1 = r22
                                boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r2 == 0) goto L17
                                r2 = r1
                                com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                                int r3 = r2.label
                                r4 = -2147483648(0xffffffff80000000, float:-0.0)
                                r5 = r3 & r4
                                if (r5 == 0) goto L17
                                int r3 = r3 - r4
                                r2.label = r3
                                goto L1c
                            L17:
                                com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1$2$1
                                r2.<init>(r1)
                            L1c:
                                java.lang.Object r1 = r2.result
                                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                int r4 = r2.label
                                r5 = 1
                                if (r4 == 0) goto L36
                                if (r4 != r5) goto L2e
                                kotlin.ResultKt.throwOnFailure(r1)
                                goto La4
                            L2e:
                                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                                r1.<init>(r2)
                                throw r1
                            L36:
                                kotlin.ResultKt.throwOnFailure(r1)
                                kotlinx.coroutines.flow.FlowCollector r1 = r0.a
                                r4 = r21
                                com.byd.overseaappstore.module.ability.data.ApiDataBody r4 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r4
                                int r6 = r4.getCode()
                                r7 = -1
                                if (r6 != r7) goto L53
                                com.byd.overseaappstore.module.ability.data.SourceDataBody r4 = new com.byd.overseaappstore.module.ability.data.SourceDataBody
                                r9 = 0
                                r11 = 0
                                r12 = 5
                                r13 = 0
                                java.lang.String r10 = "no data"
                                r8 = r4
                                r8.<init>(r9, r10, r11, r12, r13)
                                goto L65
                            L53:
                                com.byd.overseaappstore.module.ability.data.SourceDataBody r6 = new com.byd.overseaappstore.module.ability.data.SourceDataBody
                                r15 = 0
                                r16 = 0
                                java.lang.Object r17 = r4.getData()
                                r18 = 3
                                r19 = 0
                                r14 = r6
                                r14.<init>(r15, r16, r17, r18, r19)
                                r4 = r6
                            L65:
                                int r6 = r4.getRet()
                                java.lang.Object r7 = r4.getApiData()
                                java.util.List r7 = (java.util.List) r7
                                if (r7 == 0) goto L7a
                                int r7 = r7.size()
                                java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
                                goto L7b
                            L7a:
                                r7 = 0
                            L7b:
                                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                                r8.<init>()
                                java.lang.String r9 = "repo-vm fetchSoftwareListRangeFromDownloadWaitingToDownloaded ret = "
                                r8.append(r9)
                                r8.append(r6)
                                java.lang.String r6 = " size = "
                                r8.append(r6)
                                r8.append(r7)
                                java.lang.String r6 = r8.toString()
                                r7 = 0
                                java.lang.Object[] r7 = new java.lang.Object[r7]
                                spi.ct.a(r6, r7)
                                r2.label = r5
                                java.lang.Object r1 = r1.emit(r4, r2)
                                if (r1 != r3) goto La4
                                return r3
                            La4:
                                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                return r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromDownloadWaitingToDownloaded$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    @Nullable
                    public final Object collect(@NotNull FlowCollector<? super SourceDataBody<List<? extends qq>>> flowCollector, @NotNull Continuation continuation) {
                        Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, new jr(softwareRepository, null)), Dispatchers.getIO());
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
            a aVar = new a(DownloadViewModel.this);
            this.a = 2;
            if (((Flow) obj).collect(aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public DownloadViewModel(@NotNull SoftwareRepository softwareRepository, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull ErrorUiStateGenerator errorUiStateGenerator, @NotNull s9 executorHciMgr, @NotNull oa executorSoftwareStateMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull DataGenerator dataGenerator, @NotNull LanguageChangeStateMgr languageChangeStateMgr) {
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(softwareUiStateGenerator, "softwareUiStateGenerator");
        Intrinsics.checkNotNullParameter(errorUiStateGenerator, "errorUiStateGenerator");
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
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
        MutableStateFlow<DownloadItemUiState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new DownloadItemUiState(null, false, 3, null));
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
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull java.lang.String r7, int r8, @org.jetbrains.annotations.NotNull kotlinx.coroutines.CoroutineScope r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.byd.overseaappstore.ui.vm.DownloadViewModel.a
            if (r0 == 0) goto L13
            r0 = r10
            com.byd.overseaappstore.ui.vm.DownloadViewModel$a r0 = (com.byd.overseaappstore.ui.vm.DownloadViewModel.a) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            com.byd.overseaappstore.ui.vm.DownloadViewModel$a r0 = new com.byd.overseaappstore.ui.vm.DownloadViewModel$a
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 2
            r4 = 3
            r5 = 1
            if (r2 == 0) goto L3c
            if (r2 == r5) goto L34
            if (r2 == r3) goto L34
            if (r2 != r4) goto L2c
            goto L34
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            kotlinx.coroutines.CoroutineScope r9 = r0.b
            com.byd.overseaappstore.ui.vm.DownloadViewModel r7 = r0.a
            kotlin.ResultKt.throwOnFailure(r10)
            goto L83
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            if (r8 == r5) goto L73
            if (r8 == r4) goto L64
            r10 = 4
            if (r8 == r10) goto L55
            java.lang.String r9 = "deleteDownloadWork error currentState:"
            java.lang.String r10 = " packageName:"
            java.lang.String r7 = spi.xi.f(r9, r8, r10, r7)
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]
            spi.ct.b(r7, r8)
            goto L86
        L55:
            spi.s9 r8 = r6.d
            r0.a = r6
            r0.b = r9
            r0.e = r3
            java.lang.Object r7 = spi.s9.a(r8, r7, r0)
            if (r7 != r1) goto L82
            return r1
        L64:
            spi.s9 r8 = r6.d
            r0.a = r6
            r0.b = r9
            r0.e = r5
            java.lang.Object r7 = spi.s9.a(r8, r7, r0)
            if (r7 != r1) goto L82
            return r1
        L73:
            spi.oa r8 = r6.e
            r0.a = r6
            r0.b = r9
            r0.e = r4
            java.lang.Object r7 = r8.f(r7, r0)
            if (r7 != r1) goto L82
            return r1
        L82:
            r7 = r6
        L83:
            r7.b(r9)
        L86:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.vm.DownloadViewModel.a(java.lang.String, int, kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void b(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new b(null), 3, null);
    }
}
