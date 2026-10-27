package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.ErrorUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicSoftwareRsp;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.LoadingUiState;
import com.byd.overseaappstore.module.ability.data.uistate.TopicDetailUiState;
import com.byd.overseaappstore.module.ability.data.uistate.TopicTitleUiState;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
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
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.cr;
import spi.ct;
import spi.dr;
import spi.oa;
import spi.s9;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BI\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/TopicDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;", "errorUiStateGenerator", "Lspi/s9;", "executorHciMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;", "executorSoftwareNewTaskInTrigger", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;", "languageChangeStateMgr", "<init>", "(Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;Lspi/s9;Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;Lspi/oa;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class TopicDetailViewModel extends ViewModel {

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
    public final MutableStateFlow<TopicDetailUiState> i;

    @NotNull
    public final StateFlow<TopicDetailUiState> j;

    @NotNull
    public final MutableStateFlow<TopicTitleUiState> k;

    @NotNull
    public final StateFlow<TopicTitleUiState> l;

    @NotNull
    public final MutableStateFlow<ErrorEmptyUiState> m;

    @NotNull
    public final StateFlow<ErrorEmptyUiState> n;

    @NotNull
    public final MutableStateFlow<LoadingUiState> o;

    @NotNull
    public final StateFlow<LoadingUiState> p;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.TopicDetailViewModel$fetchFeatureTopicGroupDetail$1", f = "TopicDetailViewModel.kt", i = {}, l = {83, 83}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ String c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: com.byd.overseaappstore.ui.vm.TopicDetailViewModel$a$a, reason: collision with other inner class name */
        public static final class C0116a<T> implements FlowCollector {
            public final /* synthetic */ TopicDetailViewModel a;

            public C0116a(TopicDetailViewModel topicDetailViewModel) {
                this.a = topicDetailViewModel;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                SourceDataBody<NetRspDataBody<List<FeatureTopicSoftwareRsp>>> sourceDataBody = (SourceDataBody) obj;
                this.a.o.setValue(new LoadingUiState(null, false, 1, null));
                TopicDetailViewModel topicDetailViewModel = this.a;
                topicDetailViewModel.i.setValue(topicDetailViewModel.b.generateTopicDetailUiState(sourceDataBody));
                TopicDetailViewModel topicDetailViewModel2 = this.a;
                topicDetailViewModel2.m.setValue(topicDetailViewModel2.c.generateErrorUiState(sourceDataBody));
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, int i, Continuation<? super a> continuation) {
            super(2, continuation);
            this.c = str;
            this.d = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return TopicDetailViewModel.this.new a(this.c, this.d, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TopicDetailViewModel.this.o.setValue(new LoadingUiState(null, true, 1, null));
                TopicDetailViewModel topicDetailViewModel = TopicDetailViewModel.this;
                topicDetailViewModel.k.setValue(topicDetailViewModel.b.generateTopicTitleUiState(this.c));
                SoftwareRepository softwareRepository = TopicDetailViewModel.this.a;
                int i2 = this.d;
                this.a = 1;
                Objects.requireNonNull(softwareRepository);
                final Flow flow = FlowKt.flow(new cr(softwareRepository, i2, 0, 20, null));
                obj = FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<NetRspDataBody<List<? extends FeatureTopicSoftwareRsp>>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1$2, reason: invalid class name */
                    public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                        public final /* synthetic */ FlowCollector a;

                        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1$2$1, reason: invalid class name */
                        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        public final java.lang.Object emit(java.lang.Object r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r18) {
                            /*
                                r16 = this;
                                r0 = r16
                                r1 = r18
                                boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r2 == 0) goto L17
                                r2 = r1
                                com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                                int r3 = r2.label
                                r4 = -2147483648(0xffffffff80000000, float:-0.0)
                                r5 = r3 & r4
                                if (r5 == 0) goto L17
                                int r3 = r3 - r4
                                r2.label = r3
                                goto L1c
                            L17:
                                com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1$2$1
                                r2.<init>(r1)
                            L1c:
                                java.lang.Object r1 = r2.result
                                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                int r4 = r2.label
                                r5 = 1
                                if (r4 == 0) goto L36
                                if (r4 != r5) goto L2e
                                kotlin.ResultKt.throwOnFailure(r1)
                                goto Lb4
                            L2e:
                                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                                r1.<init>(r2)
                                throw r1
                            L36:
                                kotlin.ResultKt.throwOnFailure(r1)
                                kotlinx.coroutines.flow.FlowCollector r1 = r0.a
                                r9 = r17
                                com.byd.overseaappstore.module.ability.data.NetRspDataBody r9 = (com.byd.overseaappstore.module.ability.data.NetRspDataBody) r9
                                com.byd.overseaappstore.module.ability.data.SourceDataBody r4 = new com.byd.overseaappstore.module.ability.data.SourceDataBody
                                if (r9 != 0) goto L4e
                                r11 = 0
                                r13 = 0
                                r14 = 5
                                r15 = 0
                                java.lang.String r12 = "no data"
                                r10 = r4
                                r10.<init>(r11, r12, r13, r14, r15)
                                goto L56
                            L4e:
                                r7 = 0
                                r8 = 0
                                r10 = 3
                                r11 = 0
                                r6 = r4
                                r6.<init>(r7, r8, r9, r10, r11)
                            L56:
                                int r6 = r4.getRet()
                                java.lang.Object r7 = r4.getApiData()
                                com.byd.overseaappstore.module.ability.data.NetRspDataBody r7 = (com.byd.overseaappstore.module.ability.data.NetRspDataBody) r7
                                r8 = 0
                                if (r7 == 0) goto L74
                                java.lang.Object r7 = r7.getData()
                                java.util.List r7 = (java.util.List) r7
                                if (r7 == 0) goto L74
                                int r7 = r7.size()
                                java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
                                goto L75
                            L74:
                                r7 = r8
                            L75:
                                java.lang.Object r9 = r4.getApiData()
                                com.byd.overseaappstore.module.ability.data.NetRspDataBody r9 = (com.byd.overseaappstore.module.ability.data.NetRspDataBody) r9
                                if (r9 == 0) goto L83
                                java.lang.Object r8 = r9.getData()
                                java.util.List r8 = (java.util.List) r8
                            L83:
                                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                                r9.<init>()
                                java.lang.String r10 = "repo-vm fetchFeatureTopicGroupDetail url:featured/theme/getDetail ret:"
                                r9.append(r10)
                                r9.append(r6)
                                java.lang.String r6 = " size:"
                                r9.append(r6)
                                r9.append(r7)
                                java.lang.String r6 = " data:"
                                r9.append(r6)
                                r9.append(r8)
                                java.lang.String r6 = r9.toString()
                                r7 = 0
                                java.lang.Object[] r7 = new java.lang.Object[r7]
                                spi.ct.a(r6, r7)
                                r2.label = r5
                                java.lang.Object r1 = r1.emit(r4, r2)
                                if (r1 != r3) goto Lb4
                                return r3
                            Lb4:
                                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                return r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureTopicGroupDetail$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    @Nullable
                    public final Object collect(@NotNull FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends FeatureTopicSoftwareRsp>>>> flowCollector, @NotNull Continuation continuation) {
                        Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, new dr(softwareRepository, null)), Dispatchers.getIO());
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
            C0116a c0116a = new C0116a(TopicDetailViewModel.this);
            this.a = 2;
            if (((Flow) obj).collect(c0116a, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public TopicDetailViewModel(@NotNull SoftwareRepository softwareRepository, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull ErrorUiStateGenerator errorUiStateGenerator, @NotNull s9 executorHciMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull oa executorSoftwareStateMgr, @NotNull DataGenerator dataGenerator, @NotNull LanguageChangeStateMgr languageChangeStateMgr) {
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
        MutableStateFlow<TopicDetailUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new TopicDetailUiState(null, false, 3, null));
        this.i = MutableStateFlow;
        this.j = MutableStateFlow;
        MutableStateFlow<TopicTitleUiState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new TopicTitleUiState(null, true, 1, null));
        this.k = MutableStateFlow2;
        this.l = MutableStateFlow2;
        MutableStateFlow<ErrorEmptyUiState> MutableStateFlow3 = StateFlowKt.MutableStateFlow(new ErrorEmptyUiState(false, 0, 0, 7, null));
        this.m = MutableStateFlow3;
        this.n = MutableStateFlow3;
        MutableStateFlow<LoadingUiState> MutableStateFlow4 = StateFlowKt.MutableStateFlow(new LoadingUiState(null, false, 3, null));
        this.o = MutableStateFlow4;
        this.p = MutableStateFlow4;
    }

    public static final void a(TopicDetailViewModel topicDetailViewModel, List list) {
        Objects.requireNonNull(topicDetailViewModel);
        if (list == null || list.isEmpty()) {
            return;
        }
        ct.a("operate new list is not null, ", new Object[0]);
        topicDetailViewModel.i.setValue(new TopicDetailUiState(list, true));
    }

    public final void b(int i, @NotNull String title, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new a(title, i, null), 3, null);
    }
}
