package spi;

import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCacheProcess;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class o9 {

    @NotNull
    public final i9 a;

    @NotNull
    public final DataGenerator b;

    @NotNull
    public final oa c;

    @NotNull
    public final ExecutorHciCachePreTreat d;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCacheLine", f = "ExecutorHciCacheLine.kt", i = {0, 0}, l = {54, 64}, m = "handleWaitToWork", n = {"this", "bizSrc"}, s = {"L$0", "L$1"})
    public static final class a extends ContinuationImpl {
        public o9 a;
        public a2 b;
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
            return o9.this.a(null, this);
        }
    }

    public o9(@NotNull i9 executorDbMgr, @NotNull DataGenerator dataGenerator, @NotNull oa executorSoftwareStateMgr, @NotNull ExecutorHciCachePreTreat preTreat, @NotNull ExecutorHciCacheProcess process, @NotNull n9 execute) {
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(preTreat, "preTreat");
        Intrinsics.checkNotNullParameter(process, "process");
        Intrinsics.checkNotNullParameter(execute, "execute");
        this.a = executorDbMgr;
        this.b = dataGenerator;
        this.c = executorSoftwareStateMgr;
        this.d = preTreat;
        process.a = execute;
        preTreat.a = process;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull spi.a2 r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof spi.o9.a
            if (r0 == 0) goto L13
            r0 = r8
            spi.o9$a r0 = (spi.o9.a) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            spi.o9$a r0 = new spi.o9$a
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r8)
            goto L97
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            spi.a2 r7 = r0.b
            spi.o9 r2 = r0.a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4f
        L3c:
            kotlin.ResultKt.throwOnFailure(r8)
            spi.i9 r8 = r6.a
            r0.a = r6
            r0.b = r7
            r0.e = r4
            java.lang.Object r8 = r8.D(r0)
            if (r8 != r1) goto L4e
            return r1
        L4e:
            r2 = r6
        L4f:
            spi.qq r8 = (spi.qq) r8
            r4 = 0
            if (r8 != 0) goto L62
            java.lang.String r8 = "executorHciCacheLine handleWaitToWork no wait software bizSrc:"
            java.lang.String r7 = spi.e.e(r8, r7)
            java.lang.Object[] r8 = new java.lang.Object[r4]
            spi.ct.a(r7, r8)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L62:
            java.lang.String r5 = "executorHciCacheLine handleWaitToWork bizSrc:"
            java.lang.String r5 = spi.e.e(r5, r7)
            java.lang.Object[] r4 = new java.lang.Object[r4]
            spi.ct.a(r5, r4)
            com.byd.overseaappstore.module.ability.data.generator.DataGenerator r4 = r2.b
            com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r8 = r4.parseToSoftwareOptParam(r8)
            spi.oa r4 = r2.c
            java.lang.String r5 = r8.getPackageName()
            int r4 = r4.b(r5)
            com.byd.overseaappstore.module.ability.data.generator.DataGenerator r5 = r2.b
            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier r8 = r5.parseToCacheCarrier(r8, r4)
            kotlinx.coroutines.flow.Flow r8 = kotlinx.coroutines.flow.FlowKt.flowOf(r8)
            com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat r2 = r2.d
            r4 = 0
            r0.a = r4
            r0.b = r4
            r0.e = r3
            java.lang.Object r7 = r2.a(r7, r8, r0)
            if (r7 != r1) goto L97
            return r1
        L97:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.o9.a(spi.a2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
