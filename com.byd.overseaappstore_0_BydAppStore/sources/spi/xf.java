package spi;

import com.byd.car.locale.ccTLD;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.ui.vm.InstalledViewModel;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.vm.InstalledViewModel$fetchInstallList$1", f = "InstalledViewModel.kt", i = {}, l = {62, 62}, m = "invokeSuspend", n = {}, s = {})
public final class xf extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ InstalledViewModel b;

    public static final class a<T> implements FlowCollector {
        public final /* synthetic */ InstalledViewModel a;

        public a(InstalledViewModel installedViewModel) {
            this.a = installedViewModel;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            SourceDataBody<List<SoftwareIntermediaryRsp>> sourceDataBody = (SourceDataBody) obj;
            InstalledViewModel installedViewModel = this.a;
            installedViewModel.i.setValue(installedViewModel.b.generateInstalledItemUiState(sourceDataBody));
            InstalledViewModel installedViewModel2 = this.a;
            installedViewModel2.k.setValue(installedViewModel2.c.generateErrorUiStateForUninstallManager(sourceDataBody));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public xf(InstalledViewModel installedViewModel, Continuation<? super xf> continuation) {
        super(2, continuation);
        this.b = installedViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new xf(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((xf) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final SoftwareRepository softwareRepository = this.b.a;
            this.a = 1;
            Objects.requireNonNull(softwareRepository);
            final Flow flow = FlowKt.flow(new er(softwareRepository, null));
            final Flow<List<? extends SoftwareIntermediaryRsp>> flow2 = new Flow<List<? extends SoftwareIntermediaryRsp>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$1

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$1$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;
                    public final /* synthetic */ SoftwareRepository b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {0, 0}, l = {230, 224}, m = "emit", n = {"this", ccTLD.ITALY}, s = {"L$0", "L$2"})
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        public Object L$0;
                        public Object L$1;
                        public Object L$2;
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

                    public AnonymousClass2(FlowCollector flowCollector, SoftwareRepository softwareRepository) {
                        this.a = flowCollector;
                        this.b = softwareRepository;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:75:0x0178 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object emit(java.lang.Object r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r18) {
                        /*
                            Method dump skipped, instruction units count: 380
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super List<? extends SoftwareIntermediaryRsp>> flowCollector, @NotNull Continuation continuation) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector, softwareRepository), continuation);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            obj = FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<List<? extends SoftwareIntermediaryRsp>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2$2, reason: invalid class name */
                public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                    public final /* synthetic */ FlowCollector a;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2$2", f = "SoftwareRepository.kt", i = {}, l = {224}, m = "emit", n = {}, s = {})
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
                            boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2.AnonymousClass2.AnonymousClass1
                            if (r2 == 0) goto L17
                            r2 = r1
                            com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2.AnonymousClass2.AnonymousClass1) r2
                            int r3 = r2.label
                            r4 = -2147483648(0xffffffff80000000, float:-0.0)
                            r5 = r3 & r4
                            if (r5 == 0) goto L17
                            int r3 = r3 - r4
                            r2.label = r3
                            goto L1c
                        L17:
                            com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2$2$1
                            r2.<init>(r1)
                        L1c:
                            java.lang.Object r1 = r2.result
                            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r4 = r2.label
                            r5 = 1
                            if (r4 == 0) goto L35
                            if (r4 != r5) goto L2d
                            kotlin.ResultKt.throwOnFailure(r1)
                            goto L5e
                        L2d:
                            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                            r1.<init>(r2)
                            throw r1
                        L35:
                            kotlin.ResultKt.throwOnFailure(r1)
                            kotlinx.coroutines.flow.FlowCollector r1 = r0.a
                            r9 = r17
                            java.util.List r9 = (java.util.List) r9
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
                            r2.label = r5
                            java.lang.Object r1 = r1.emit(r4, r2)
                            if (r1 != r3) goto L5e
                            return r3
                        L5e:
                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledCanBeUninstalledList$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                @Nullable
                public final Object collect(@NotNull FlowCollector<? super SourceDataBody<List<? extends SoftwareIntermediaryRsp>>> flowCollector, @NotNull Continuation continuation) {
                    Object objCollect = flow2.collect(new AnonymousClass2(flowCollector), continuation);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            }, new fr(softwareRepository, null)), Dispatchers.getIO());
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
        a aVar = new a(this.b);
        this.a = 2;
        if (((Flow) obj).collect(aVar, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
