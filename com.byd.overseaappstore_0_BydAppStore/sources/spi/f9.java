package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
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
public final class f9 implements b9 {

    @NotNull
    public final oa a;

    @NotNull
    public final ExecutorSoftwareNewTaskInTrigger b;

    @NotNull
    public final i9 c;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.refresh.ExecutorConnectWorkClickSingle", f = "ExecutorConnectWorkClickSingle.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, l = {46, 81, 99, 118}, m = "refreshLocal", n = {"this", "pkn", "prevStateData", "targetSoftwareStateData", "policy", "state", "networkCount", "this", "pkn", "prevStateData", "targetSoftwareStateData", "this", "pkn", "prevStateData", "targetSoftwareStateData", "this", "pkn", "prevStateData", "targetSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    public static final class a extends ContinuationImpl {
        public f9 a;
        public String b;
        public SoftwareStateData c;
        public SoftwareStateData d;
        public int e;
        public int f;
        public int g;
        public /* synthetic */ Object h;
        public int j;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.h = obj;
            this.j |= Integer.MIN_VALUE;
            return f9.this.c(null, 0, this);
        }
    }

    public f9(@NotNull oa executorSoftwareStateMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull i9 executorDbMgr) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        this.a = executorSoftwareStateMgr;
        this.b = executorSoftwareNewTaskInTrigger;
        this.c = executorDbMgr;
    }

    @Override // spi.b9
    @Nullable
    public final Object a(@NotNull List<SoftwareOptParam> list, int i, @NotNull Continuation<? super List<Pair<SoftwareStateData, SoftwareStateData>>> continuation) {
        return null;
    }

    @Override // spi.b9
    @Nullable
    public final Object b(@NotNull SoftwareOptParam softwareOptParam, @NotNull Pair<SoftwareStateData, SoftwareStateData> pair, int i, @NotNull Continuation<? super Unit> continuation) {
        int iY = this.c.y();
        String packageName = softwareOptParam.getPackageName();
        SoftwareStateData first = pair.getFirst();
        StringBuilder sbL = e.l("executor clickSingle restoreLocal updatePool updateDb curState:", pair.getSecond().getState(), " prevState:", first.getState(), " networkCount：");
        d.t(sbL, iY, " pkn:", packageName, " policy:");
        sbL.append(i);
        ct.b(sbL.toString(), new Object[0]);
        this.a.h(packageName, first);
        Object objT = this.c.t(packageName, first.getState(), continuation);
        return objT == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objT : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    @Override // spi.b9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(@org.jetbrains.annotations.NotNull com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r17, int r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData, com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData>> r19) {
        /*
            Method dump skipped, instruction units count: 752
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.f9.c(com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.b9
    @Nullable
    public final Object d(@NotNull List<SoftwareOptParam> list, @NotNull List<Pair<SoftwareStateData, SoftwareStateData>> list2, int i, @NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
