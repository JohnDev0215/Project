package spi;

import com.byd.car.locale.ccTLD;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import java.util.Iterator;
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
public final class e9 implements b9 {

    @NotNull
    public final oa a;

    @NotNull
    public final ExecutorSoftwareNewTaskInTrigger b;

    @NotNull
    public final i9 c;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.refresh.ExecutorConnectWorkClickMultiSpecial", f = "ExecutorConnectWorkClickMultiSpecial.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {59, 73, 93}, m = "refreshLocal", n = {"this", "pkn", "prevStateData", "targetSoftwareStateData", "this", "pkn", "prevStateData", "targetSoftwareStateData", "this", "pkn", "prevStateData", "targetSoftwareStateData"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    public static final class a extends ContinuationImpl {
        public e9 a;
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
            return e9.this.c(null, 0, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.refresh.ExecutorConnectWorkClickMultiSpecial", f = "ExecutorConnectWorkClickMultiSpecial.kt", i = {0, 0, 0, 0, 0}, l = {117}, m = "refreshLocal", n = {"this", "optParamList", "pairList", ccTLD.ITALY, "policy"}, s = {"L$0", "L$1", "L$2", "L$4", "I$0"})
    public static final class b extends ContinuationImpl {
        public e9 a;
        public List b;
        public List c;
        public Iterator d;
        public SoftwareOptParam e;
        public int f;
        public /* synthetic */ Object g;
        public int i;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.g = obj;
            this.i |= Integer.MIN_VALUE;
            return e9.this.a(null, 0, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.refresh.ExecutorConnectWorkClickMultiSpecial", f = "ExecutorConnectWorkClickMultiSpecial.kt", i = {0, 0, 0, 0}, l = {169}, m = "restoreLocal", n = {"this", "pairList", "policy", "index$iv"}, s = {"L$0", "L$1", "I$0", "I$1"})
    public static final class c extends ContinuationImpl {
        public e9 a;
        public List b;
        public Iterator c;
        public int d;
        public int e;
        public /* synthetic */ Object f;
        public int h;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f = obj;
            this.h |= Integer.MIN_VALUE;
            return e9.this.d(null, null, 0, this);
        }
    }

    public e9(@NotNull oa executorSoftwareStateMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull i9 executorDbMgr) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        this.a = executorSoftwareStateMgr;
        this.b = executorSoftwareNewTaskInTrigger;
        this.c = executorDbMgr;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0089 -> B:21:0x008c). Please report as a decompilation issue!!! */
    @Override // spi.b9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull java.util.List<com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam> r13, int r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<kotlin.Pair<com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData, com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData>>> r15) {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.e9.a(java.util.List, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.b9
    @Nullable
    public final Object b(@NotNull SoftwareOptParam softwareOptParam, @NotNull Pair<SoftwareStateData, SoftwareStateData> pair, int i, @NotNull Continuation<? super Unit> continuation) {
        int iY = this.c.y();
        String packageName = softwareOptParam.getPackageName();
        SoftwareStateData first = pair.getFirst();
        StringBuilder sbL = e.l("executor clickMultiSpecial restoreLocal updateDb curState:", pair.getSecond().getState(), " prevState:", first.getState(), " networkCount：");
        d.t(sbL, iY, " pkn:", packageName, " policy:");
        sbL.append(i);
        ct.a(sbL.toString(), new Object[0]);
        this.a.h(packageName, first);
        Object objT = this.c.t(packageName, first.getState(), continuation);
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
            Method dump skipped, instruction units count: 468
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.e9.c(com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    @Override // spi.b9
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(@org.jetbrains.annotations.NotNull java.util.List<com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam> r17, @org.jetbrains.annotations.NotNull java.util.List<kotlin.Pair<com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData, com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData>> r18, int r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.e9.d(java.util.List, java.util.List, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
