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
public final class c9 implements b9 {

    @NotNull
    public final oa a;

    @NotNull
    public final i9 b;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.refresh.ExecutorConnectWorkAutoResume", f = "ExecutorConnectWorkAutoResume.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {55, 63}, m = "refreshLocal", n = {"this", "pkn", "prevStateData", "targetSoftwareStateData", "this", "pkn", "prevStateData", "targetSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    public static final class a extends ContinuationImpl {
        public c9 a;
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
            return c9.this.c(null, 0, this);
        }
    }

    public c9(@NotNull oa executorSoftwareStateMgr, @NotNull i9 executorDbMgr) {
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
        StringBuilder sbL = e.l("executor autoResume restoreLocal updatePool updateDb curState:", pair.getSecond().getState(), " prevState:", first.getState(), " networkCount：");
        d.t(sbL, iY, " pkn:", packageName, " policy:");
        sbL.append(i);
        ct.b(sbL.toString(), new Object[0]);
        this.a.h(packageName, first);
        Object objT = this.b.t(packageName, first.getState(), continuation);
        return objT == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objT : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.b9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r13, int r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData, com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData>> r15) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.c9.c(com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.b9
    @Nullable
    public final Object d(@NotNull List<SoftwareOptParam> list, @NotNull List<Pair<SoftwareStateData, SoftwareStateData>> list2, int i, @NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
