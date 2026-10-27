package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class h9 implements b9 {

    @NotNull
    public final oa a;

    @NotNull
    public final i9 b;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.refresh.ExecutorConnectWorkWaitingResume", f = "ExecutorConnectWorkWaitingResume.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {45, 53}, m = "refreshLocal", n = {"this", "pkn", "prevStateData", "targetSoftwareStateData", "this", "pkn", "prevStateData", "targetSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    public static final class a extends ContinuationImpl {
        public h9 a;
        public String b;
        public SoftwareStateData c;
        public SoftwareStateData d;
        public /* synthetic */ Object e;
        public int g;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.e = obj;
            this.g |= Integer.MIN_VALUE;
            return h9.this.c(null, 0, this);
        }
    }

    public h9(@NotNull oa executorSoftwareStateMgr, @NotNull i9 executorDbMgr) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        this.a = executorSoftwareStateMgr;
        this.b = executorDbMgr;
    }

    @Override // spi.b9
    @Nullable
    public final Object a(@NotNull List<SoftwareOptParam> list, int i, @NotNull Continuation<? super List<Pair<SoftwareStateData, SoftwareStateData>>> continuation) {
        return null;
    }

    @Override // spi.b9
    @Nullable
    public final Object b(@NotNull SoftwareOptParam softwareOptParam, @NotNull Pair<SoftwareStateData, SoftwareStateData> pair, int i, @NotNull Continuation<? super Unit> continuation) {
        int iY = this.b.y();
        String packageName = softwareOptParam.getPackageName();
        SoftwareStateData first = pair.getFirst();
        SoftwareStateData second = pair.getSecond();
        int state = first.getState();
        if (state == 1) {
            SoftwareStateData softwareStateData = new SoftwareStateData(0, 0);
            int state2 = second.getState();
            int state3 = softwareStateData.getState();
            int state4 = first.getState();
            StringBuilder sbL = e.l("executor waitingResume restoreLocal updatePool & db curState:", state2, " targetState:", state3, " prevState:");
            hi.i(sbL, state4, " networkCount：", iY, " pkn:");
            ct.b(hi.g(sbL, packageName, " policy:", i), new Object[0]);
            this.a.h(packageName, softwareStateData);
            Object objT = this.b.t(packageName, softwareStateData.getState(), continuation);
            return objT == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objT : Unit.INSTANCE;
        }
        if (state != 8) {
            return Unit.INSTANCE;
        }
        SoftwareStateData softwareStateData2 = new SoftwareStateData(0, 7);
        int state5 = second.getState();
        int state6 = softwareStateData2.getState();
        int state7 = first.getState();
        StringBuilder sbL2 = e.l("executor waitingResume restoreLocal updatePool & db curState:", state5, " targetState:", state6, " prevState:");
        hi.i(sbL2, state7, " networkCount：", iY, " pkn:");
        ct.b(hi.g(sbL2, packageName, " policy:", i), new Object[0]);
        this.a.h(packageName, softwareStateData2);
        Object objT2 = this.b.t(packageName, softwareStateData2.getState(), continuation);
        return objT2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objT2 : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.b9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r12, int r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData, com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData>> r14) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.h9.c(com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.b9
    @Nullable
    public final Object d(@NotNull List<SoftwareOptParam> list, @NotNull List<Pair<SoftwareStateData, SoftwareStateData>> list2, int i, @NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
