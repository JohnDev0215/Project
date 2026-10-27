package com.byd.overseaappstore.module.ability.repo;

import com.byd.overseaappstore.module.ability.data.ApiDataBody;
import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicGroupRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareThemRsp;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.aq;
import spi.ar;
import spi.bq;
import spi.br;
import spi.ct;
import spi.d;
import spi.h1;
import spi.i9;
import spi.kr;
import spi.lr;
import spi.qq;
import spi.qr;
import spi.rr;
import spi.uq;
import spi.vq;
import spi.wq;
import spi.xq;
import spi.yq;
import spi.zq;

/* JADX INFO: loaded from: classes.dex */
public final class SoftwareRepository extends h1 {

    @NotNull
    public final bq a;

    @NotNull
    public final i9 b;

    @NotNull
    public final aq c;

    @NotNull
    public final DataGenerator d;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$1", f = "SoftwareRepository.kt", i = {}, l = {142}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<FlowCollector<? super ApiDataBody<List<? extends SoftwareDeviceInstalledRsp>>>, Continuation<? super Unit>, Object> {
        public int a;
        public /* synthetic */ Object b;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = SoftwareRepository.this.new a(continuation);
            aVar.b = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(FlowCollector<? super ApiDataBody<List<? extends SoftwareDeviceInstalledRsp>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((a) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.b;
                ApiDataBody<List<SoftwareDeviceInstalledRsp>> apiDataBodyB = SoftwareRepository.this.c.b();
                this.a = 1;
                if (flowCollector.emit(apiDataBodyB, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$3", f = "SoftwareRepository.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function3<FlowCollector<? super SourceDataBody<List<? extends SoftwareDeviceInstalledRsp>>>, Throwable, Continuation<? super Unit>, Object> {
        public int a;
        public /* synthetic */ FlowCollector b;
        public /* synthetic */ Throwable c;

        public b(Continuation<? super b> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super SourceDataBody<List<? extends SoftwareDeviceInstalledRsp>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            b bVar = SoftwareRepository.this.new b(continuation);
            bVar.b = flowCollector;
            bVar.c = th;
            return bVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = this.b;
                Throwable th = this.c;
                ct.b(d.g("repo-vm fetchInstalledDeviceList error class:", th.getClass().getSimpleName(), " catch = ", th.getMessage()), new Object[0]);
                int iA = SoftwareRepository.this.a(th);
                String message = th.getMessage();
                if (message == null) {
                    message = "unknown error";
                }
                SourceDataBody sourceDataBody = new SourceDataBody(iA, message, null, 4, null);
                this.b = null;
                this.a = 1;
                if (flowCollector.emit(sourceDataBody, this) == coroutine_suspended) {
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

    public SoftwareRepository(@NotNull bq softwareDataSourceNet, @NotNull i9 executorDbMgr, @NotNull aq softwareDataSourceDevice, @NotNull DataGenerator dataGenerator) {
        Intrinsics.checkNotNullParameter(softwareDataSourceNet, "softwareDataSourceNet");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(softwareDataSourceDevice, "softwareDataSourceDevice");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        this.a = softwareDataSourceNet;
        this.b = executorDbMgr;
        this.c = softwareDataSourceDevice;
        this.d = dataGenerator;
    }

    public static Object d(SoftwareRepository softwareRepository) {
        Objects.requireNonNull(softwareRepository);
        final Flow flow = FlowKt.flow(new yq(softwareRepository, 0, 8, null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<NetRspDataBody<List<? extends SoftwareThemRsp>>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1$2$1
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
                        java.lang.String r10 = "repo-vm fetchFeatureThemList url:theme/softwares ret:"
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemList$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends SoftwareThemRsp>>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new zq(softwareRepository, null)), Dispatchers.getIO());
    }

    @Nullable
    public final Object b() {
        final Flow flow = FlowKt.flow(new uq(this, null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<List<? extends qq>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1$2$1
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
                        java.lang.String r9 = "repo-vm fetchDownloadedButNotInstalledList ret = "
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadedButNotInstalledList$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<List<? extends qq>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new vq(this, null)), Dispatchers.getIO());
    }

    @Nullable
    public final Object c() {
        final Flow flow = FlowKt.flow(new wq(this, null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<List<? extends qq>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1$2$1
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
                        java.lang.String r9 = "repo-vm fetchPausedList ret = "
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchDownloadingList$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<List<? extends qq>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new xq(null)), Dispatchers.getIO());
    }

    @Nullable
    public final Object e() {
        final Flow flow = FlowKt.flow(new ar(this, null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<NetRspDataBody<List<? extends FeatureTopicGroupRsp>>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1$2$1
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
                        java.lang.String r10 = "repo-vm fetchFeatureThemTopicGroup url:featured/theme/get ret:"
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchFeatureThemTopicGroup$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends FeatureTopicGroupRsp>>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new br(this, null)), Dispatchers.getIO());
    }

    @NotNull
    public final Flow<SourceDataBody<List<SoftwareDeviceInstalledRsp>>> f() {
        final Flow flow = FlowKt.flow(new a(null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<List<? extends SoftwareDeviceInstalledRsp>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1$2$1
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
                        java.lang.String r9 = "repo-vm fetchInstalledDeviceList ret = "
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchInstalledDeviceList$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<List<? extends SoftwareDeviceInstalledRsp>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new b(null)), Dispatchers.getIO());
    }

    @Nullable
    public final Object g() {
        final Flow flow = FlowKt.flow(new kr(this, null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<List<? extends qq>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1$2$1
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
                        java.lang.String r9 = "repo-vm fetchSoftwareListRangeFromToUpdateToUpdateFinish ret = "
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchSoftwareListRangeFromToUpdateToUpdateFinish$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<List<? extends qq>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new lr(null)), Dispatchers.getIO());
    }

    @Nullable
    public final Object h() {
        final Flow flow = FlowKt.flow(new qr(this, null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<List<? extends qq>>>() { // from class: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1$2", f = "SoftwareRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1$2$1
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
                        java.lang.String r9 = "repo-vm fetchUpdatingList ret = "
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.SoftwareRepository$fetchUpdatingList$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<List<? extends qq>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new rr(null)), Dispatchers.getIO());
    }
}
