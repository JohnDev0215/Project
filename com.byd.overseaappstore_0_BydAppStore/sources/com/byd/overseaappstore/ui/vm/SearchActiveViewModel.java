package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SearchUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.rsp.HotSearchRsp;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FooterHeaderUiState;
import com.byd.overseaappstore.module.ability.data.uistate.HotSearchItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SearchResultItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ToastUiState;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
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
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ko;
import spi.mo;
import spi.no;
import spi.oa;
import spi.qo;
import spi.s9;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BI\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/SearchActiveViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lspi/qo;", "searchRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SearchUiStateGenerator;", "searchUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lspi/s9;", "executorHciMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;", "executorSoftwareNewTaskInTrigger", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;", "languageChangeStateMgr", "<init>", "(Lspi/qo;Lcom/byd/overseaappstore/module/ability/data/generator/SearchUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lspi/s9;Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;Lspi/oa;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchActiveViewModel extends ViewModel {

    @NotNull
    public final qo a;

    @NotNull
    public final SearchUiStateGenerator b;

    @NotNull
    public final SoftwareUiStateGenerator c;

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
    public final MutableStateFlow<SearchResultItemUiState> i;

    @NotNull
    public final StateFlow<SearchResultItemUiState> j;

    @NotNull
    public final MutableStateFlow<HotSearchItemUiState> k;

    @NotNull
    public final StateFlow<HotSearchItemUiState> l;

    @NotNull
    public final MutableStateFlow<ErrorEmptyUiState> m;

    @NotNull
    public final StateFlow<ErrorEmptyUiState> n;

    @NotNull
    public final MutableStateFlow<FooterHeaderUiState> o;

    @NotNull
    public final StateFlow<FooterHeaderUiState> p;

    @NotNull
    public final MutableStateFlow<ToastUiState> q;

    @NotNull
    public final StateFlow<ToastUiState> r;

    @NotNull
    public final MutableStateFlow<String> s;

    @NotNull
    public final StateFlow<String> t;

    @NotNull
    public final MutableSharedFlow<Boolean> u;

    @NotNull
    public final SharedFlow<Boolean> v;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SearchActiveViewModel$fetchHotSearchTag$1", f = "SearchActiveViewModel.kt", i = {}, l = {127, 127}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        /* JADX INFO: renamed from: com.byd.overseaappstore.ui.vm.SearchActiveViewModel$a$a, reason: collision with other inner class name */
        public static final class C0114a<T> implements FlowCollector {
            public final /* synthetic */ SearchActiveViewModel a;

            public C0114a(SearchActiveViewModel searchActiveViewModel) {
                this.a = searchActiveViewModel;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                SourceDataBody<NetRspDataBody<List<HotSearchRsp>>> sourceDataBody = (SourceDataBody) obj;
                this.a.i.setValue(new SearchResultItemUiState(false, null, 2, null));
                SearchActiveViewModel searchActiveViewModel = this.a;
                searchActiveViewModel.k.setValue(searchActiveViewModel.b.generateHotSearchUiState(sourceDataBody));
                SearchActiveViewModel searchActiveViewModel2 = this.a;
                searchActiveViewModel2.m.setValue(searchActiveViewModel2.b.generateErrorUiState(sourceDataBody));
                return Unit.INSTANCE;
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveViewModel.this.new a(continuation);
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
                qo qoVar = SearchActiveViewModel.this.a;
                this.a = 1;
                Objects.requireNonNull(qoVar);
                final Flow flow = FlowKt.flow(new mo(qoVar, null));
                obj = FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<NetRspDataBody<List<? extends HotSearchRsp>>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1$2, reason: invalid class name */
                    public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                        public final /* synthetic */ FlowCollector a;

                        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1$2$1, reason: invalid class name */
                        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1$2", f = "SearchRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                                boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r2 == 0) goto L17
                                r2 = r1
                                com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                                int r3 = r2.label
                                r4 = -2147483648(0xffffffff80000000, float:-0.0)
                                r5 = r3 & r4
                                if (r5 == 0) goto L17
                                int r3 = r3 - r4
                                r2.label = r3
                                goto L1c
                            L17:
                                com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1$2$1
                                r2.<init>(r1)
                            L1c:
                                java.lang.Object r1 = r2.result
                                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                int r4 = r2.label
                                r5 = 1
                                if (r4 == 0) goto L35
                                if (r4 != r5) goto L2d
                                kotlin.ResultKt.throwOnFailure(r1)
                                goto L6f
                            L2d:
                                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                                r1.<init>(r2)
                                throw r1
                            L35:
                                kotlin.ResultKt.throwOnFailure(r1)
                                kotlinx.coroutines.flow.FlowCollector r1 = r0.a
                                r9 = r17
                                com.byd.overseaappstore.module.ability.data.NetRspDataBody r9 = (com.byd.overseaappstore.module.ability.data.NetRspDataBody) r9
                                com.byd.overseaappstore.module.ability.data.SourceDataBody r4 = new com.byd.overseaappstore.module.ability.data.SourceDataBody
                                if (r9 != 0) goto L4d
                                r11 = 0
                                r13 = 0
                                r14 = 5
                                r15 = 0
                                java.lang.String r12 = "no data"
                                r10 = r4
                                r10.<init>(r11, r12, r13, r14, r15)
                                goto L55
                            L4d:
                                r7 = 0
                                r8 = 0
                                r10 = 3
                                r11 = 0
                                r6 = r4
                                r6.<init>(r7, r8, r9, r10, r11)
                            L55:
                                int r6 = r4.getRet()
                                java.lang.String r7 = "repo-vm fetchHotSearchTags ret = "
                                java.lang.String r6 = spi.e.c(r7, r6)
                                r7 = 0
                                java.lang.Object[] r7 = new java.lang.Object[r7]
                                spi.ct.a(r6, r7)
                                r2.label = r5
                                java.lang.Object r1 = r1.emit(r4, r2)
                                if (r1 != r3) goto L6f
                                return r3
                            L6f:
                                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                return r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SearchRepository$fetchHotSearchTags$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    @Nullable
                    public final Object collect(@NotNull FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends HotSearchRsp>>>> flowCollector, @NotNull Continuation continuation) {
                        Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                    }
                }, new no(qoVar, null)), Dispatchers.getIO());
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
            C0114a c0114a = new C0114a(SearchActiveViewModel.this);
            this.a = 2;
            if (((Flow) obj).collect(c0114a, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SearchActiveViewModel$keyboardState$1", f = "SearchActiveViewModel.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ boolean c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, Continuation<? super b> continuation) {
            super(2, continuation);
            this.c = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveViewModel.this.new b(this.c, continuation);
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
                MutableSharedFlow<Boolean> mutableSharedFlow = SearchActiveViewModel.this.u;
                Boolean boolBoxBoolean = Boxing.boxBoolean(this.c);
                this.a = 1;
                if (mutableSharedFlow.emit(boolBoxBoolean, this) == coroutine_suspended) {
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

    public SearchActiveViewModel(@NotNull qo searchRepository, @NotNull SearchUiStateGenerator searchUiStateGenerator, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull s9 executorHciMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull oa executorSoftwareStateMgr, @NotNull DataGenerator dataGenerator, @NotNull LanguageChangeStateMgr languageChangeStateMgr) {
        Intrinsics.checkNotNullParameter(searchRepository, "searchRepository");
        Intrinsics.checkNotNullParameter(searchUiStateGenerator, "searchUiStateGenerator");
        Intrinsics.checkNotNullParameter(softwareUiStateGenerator, "softwareUiStateGenerator");
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(languageChangeStateMgr, "languageChangeStateMgr");
        this.a = searchRepository;
        this.b = searchUiStateGenerator;
        this.c = softwareUiStateGenerator;
        this.d = executorHciMgr;
        this.e = executorSoftwareNewTaskInTrigger;
        this.f = executorSoftwareStateMgr;
        this.g = dataGenerator;
        this.h = languageChangeStateMgr;
        MutableStateFlow<SearchResultItemUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new SearchResultItemUiState(false, null, 2, null));
        this.i = MutableStateFlow;
        this.j = MutableStateFlow;
        MutableStateFlow<HotSearchItemUiState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new HotSearchItemUiState(null, false, 3, null));
        this.k = MutableStateFlow2;
        this.l = MutableStateFlow2;
        MutableStateFlow<ErrorEmptyUiState> MutableStateFlow3 = StateFlowKt.MutableStateFlow(new ErrorEmptyUiState(false, 0, 0, 7, null));
        this.m = MutableStateFlow3;
        this.n = MutableStateFlow3;
        MutableStateFlow<FooterHeaderUiState> MutableStateFlow4 = StateFlowKt.MutableStateFlow(new FooterHeaderUiState(false, 0, 3, null));
        this.o = MutableStateFlow4;
        this.p = MutableStateFlow4;
        MutableStateFlow<ToastUiState> MutableStateFlow5 = StateFlowKt.MutableStateFlow(new ToastUiState(false, 0, 3, null));
        this.q = MutableStateFlow5;
        this.r = MutableStateFlow5;
        MutableStateFlow<String> MutableStateFlow6 = StateFlowKt.MutableStateFlow(null);
        this.s = MutableStateFlow6;
        this.t = MutableStateFlow6;
        MutableSharedFlow<Boolean> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this.u = mutableSharedFlowMutableSharedFlow$default;
        this.v = mutableSharedFlowMutableSharedFlow$default;
    }

    public static void c(SearchActiveViewModel searchActiveViewModel, String keyword, int i) {
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(searchActiveViewModel);
        Objects.requireNonNull(searchActiveViewModel);
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ko(searchActiveViewModel, keyword, 0, i, null), 3, null);
    }

    public final void a(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new a(null), 3, null);
    }

    public final void b(@NotNull CoroutineScope coroutineScope, boolean z) {
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new b(z, null), 3, null);
    }
}
