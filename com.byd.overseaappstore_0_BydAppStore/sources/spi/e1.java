package spi;

import com.byd.overseaappstore.module.ability.data.NetRspDataBody;
import com.byd.overseaappstore.module.ability.data.SourceDataBody;
import com.byd.overseaappstore.module.ability.data.rsp.BannerRsp;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class e1 extends h1 {

    @NotNull
    public final b1 a;

    public e1(@NotNull b1 bannerDataSourceNet) {
        Intrinsics.checkNotNullParameter(bannerDataSourceNet, "bannerDataSourceNet");
        this.a = bannerDataSourceNet;
    }

    @Nullable
    public final Object b() {
        final Flow flow = FlowKt.flow(new c1(this, null));
        return FlowKt.flowOn(FlowKt.m1592catch(new Flow<SourceDataBody<NetRspDataBody<List<? extends BannerRsp>>>>() { // from class: com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1

            /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector, SuspendFunction {
                public final /* synthetic */ FlowCollector a;

                /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1$2", f = "BannerRepository.kt", i = {}, l = {227}, m = "emit", n = {}, s = {})
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
                        boolean r2 = r1 instanceof com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1$2$1 r2 = (com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1$2$1 r2 = new com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1$2$1
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
                        java.lang.String r10 = "repo-vm fetchList url:featured/advertise/get ret:"
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
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.repo.BannerRepository$fetchList$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public final Object collect(@NotNull FlowCollector<? super SourceDataBody<NetRspDataBody<List<? extends BannerRsp>>>> flowCollector, @NotNull Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new d1(this, null)), Dispatchers.getIO());
    }
}
