package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareSearchRsp;
import com.byd.overseaappstore.module.ability.data.uistate.HotSearchItemUiState;
import com.byd.overseaappstore.ui.vm.SearchActiveViewModel;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SearchActiveViewModel$search$1", f = "SearchActiveViewModel.kt", i = {}, l = {111, 111}, m = "invokeSuspend", n = {}, s = {})
public final class ko extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ SearchActiveViewModel b;
    public final /* synthetic */ String c;
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.vm.SearchActiveViewModel$search$1$1", f = "SearchActiveViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<SourceDataBody<NetRspDataBody<List<? extends SoftwareSearchRsp>>>, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object a;
        public final /* synthetic */ SearchActiveViewModel b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(SearchActiveViewModel searchActiveViewModel, Continuation<? super a> continuation) {
            super(2, continuation);
            this.b = searchActiveViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.b, continuation);
            aVar.a = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(SourceDataBody<NetRspDataBody<List<? extends SoftwareSearchRsp>>> sourceDataBody, Continuation<? super Unit> continuation) {
            return ((a) create(sourceDataBody, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            SourceDataBody<NetRspDataBody<List<SoftwareSearchRsp>>> sourceDataBody = (SourceDataBody) this.a;
            this.b.k.setValue(new HotSearchItemUiState(null, false, 1, null));
            SearchActiveViewModel searchActiveViewModel = this.b;
            searchActiveViewModel.i.setValue(searchActiveViewModel.b.generateSearchResultItemUiState(sourceDataBody));
            SearchActiveViewModel searchActiveViewModel2 = this.b;
            searchActiveViewModel2.m.setValue(searchActiveViewModel2.b.generateErrorUiState(sourceDataBody));
            SearchActiveViewModel searchActiveViewModel3 = this.b;
            searchActiveViewModel3.q.setValue(searchActiveViewModel3.b.generateToastUiState(sourceDataBody));
            SearchActiveViewModel searchActiveViewModel4 = this.b;
            searchActiveViewModel4.o.setValue(searchActiveViewModel4.b.generatePullUpUiState(sourceDataBody));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ko(SearchActiveViewModel searchActiveViewModel, String str, int i, int i2, Continuation<? super ko> continuation) {
        super(2, continuation);
        this.b = searchActiveViewModel;
        this.c = str;
        this.d = i;
        this.e = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ko(this.b, this.c, this.d, this.e, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ko) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            qo qoVar = this.b.a;
            final String str = this.c;
            int i2 = this.d;
            int i3 = this.e;
            this.a = 1;
            Objects.requireNonNull(qoVar);
            final Flow flow = FlowKt.flow(new oo(qoVar, str, i2, i3, null));
            obj = FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<NetRspDataBody<List<? extends SoftwareSearchRsp>>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ String b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1$2", f = "SearchRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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

                    public AnonymousClass2(FlowCollector flowCollector, String str) {
                        this.a = flowCollector;
                        this.b = str;
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
                            boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r2 == 0) goto L17
                            r2 = r1
                            com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                            int r3 = r2.label
                            r4 = -2147483648(0xffffffff80000000, float:-0.0)
                            r5 = r3 & r4
                            if (r5 == 0) goto L17
                            int r3 = r3 - r4
                            r2.label = r3
                            goto L1c
                        L17:
                            com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1$2$1
                            r2.<init>(r1)
                        L1c:
                            java.lang.Object r1 = r2.result
                            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r4 = r2.label
                            r5 = 1
                            if (r4 == 0) goto L35
                            if (r4 != r5) goto L2d
                            kotlin.ResultKt.throwOnFailure(r1)
                            goto L8c
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
                            java.lang.String r6 = r0.b
                            int r7 = r4.getRet()
                            java.lang.Object r8 = r4.getApiData()
                            com.byd.overseaappstore.module.ability.data.NetRspDataBody r8 = (com.byd.overseaappstore.module.ability.data.NetRspDataBody) r8
                            if (r8 == 0) goto L6a
                            java.lang.Object r8 = r8.getData()
                            java.util.List r8 = (java.util.List) r8
                            goto L6b
                        L6a:
                            r8 = 0
                        L6b:
                            java.lang.String r9 = "repo-vm search url:search/software keyword:"
                            java.lang.String r10 = " ret:"
                            java.lang.String r11 = " data:"
                            java.lang.StringBuilder r6 = spi.d.k(r9, r6, r10, r7, r11)
                            r6.append(r8)
                            java.lang.String r6 = r6.toString()
                            r7 = 0
                            java.lang.Object[] r7 = new java.lang.Object[r7]
                            spi.ct.a(r6, r7)
                            r2.label = r5
                            java.lang.Object r1 = r1.emit(r4, r2)
                            if (r1 != r3) goto L8c
                            return r3
                        L8c:
                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SearchRepository$search$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends SoftwareSearchRsp>>>> flowCollector, @NotNull Continuation continuation) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, str), continuation);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            }, new po(str, qoVar, null)), Dispatchers.getIO());
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
        a aVar = new a(this.b, null);
        this.a = 2;
        if (FlowKt.collectLatest((Flow) obj, aVar, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
