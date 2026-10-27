package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.HashMap;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class oa {

    @NotNull
    public final i9 a;

    @NotNull
    public final HashMap<String, MutableStateFlow<SoftwareStateData>> b;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.ExecutorSoftwareStateMgr", f = "ExecutorSoftwareStateMgr.kt", i = {0, 1}, l = {110, 111}, m = "isContainNetworkActiveSoftware", n = {"this", "downLoadingApiDataBody"}, s = {"L$0", "L$0"})
    public static final class a extends ContinuationImpl {
        public Object a;
        public /* synthetic */ Object b;
        public int d;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return oa.this.e(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.ExecutorSoftwareStateMgr", f = "ExecutorSoftwareStateMgr.kt", i = {0, 0, 1, 1, 1, 1}, l = {121, 155}, m = "resetSoftwareStateToPoolAndDb", n = {"this", "packageName", "packageName", "softwareLocalWrap", "stateData", "poolState"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "I$0"})
    public static final class b extends ContinuationImpl {
        public Object a;
        public Object b;
        public SoftwareStateData c;
        public int d;
        public /* synthetic */ Object e;
        public int g;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.e = obj;
            this.g |= Integer.MIN_VALUE;
            return oa.this.f(null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.ExecutorSoftwareStateMgr", f = "ExecutorSoftwareStateMgr.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, l = {171, 182, 195}, m = "updateSoftwareStateToPoolAndDbWhenReplace", n = {"this", "packageName", "context", "this", "packageName", "softwareLocalWrap", "state", "percentCount", "this", "packageName", "state", "percentCount"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"})
    public static final class c extends ContinuationImpl {
        public oa a;
        public String b;
        public Object c;
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
            return oa.this.g(null, null, this);
        }
    }

    public oa(@NotNull i9 executorDbMgr) {
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        this.a = executorDbMgr;
        this.b = new HashMap<>();
    }

    public final void a(@NotNull String packageName, @NotNull MutableStateFlow<SoftwareStateData> softwareDataFlow) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(softwareDataFlow, "softwareDataFlow");
        this.b.put(packageName, softwareDataFlow);
    }

    public final int b(@NotNull String pkn) {
        SoftwareStateData value;
        Intrinsics.checkNotNullParameter(pkn, "pkn");
        MutableStateFlow<SoftwareStateData> mutableStateFlow = this.b.get(pkn);
        if (mutableStateFlow == null || (value = mutableStateFlow.getValue()) == null) {
            return 0;
        }
        return value.getState();
    }

    @NotNull
    public final SoftwareStateData c(@NotNull String packageName) {
        SoftwareStateData value;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        MutableStateFlow<SoftwareStateData> mutableStateFlow = this.b.get(packageName);
        return (mutableStateFlow == null || (value = mutableStateFlow.getValue()) == null) ? new SoftwareStateData(0, 0) : value;
    }

    @Nullable
    public final MutableStateFlow<SoftwareStateData> d(@NotNull String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.b.get(packageName);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof spi.oa.a
            if (r0 == 0) goto L13
            r0 = r8
            spi.oa$a r0 = (spi.oa.a) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.oa$a r0 = new spi.oa$a
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r0 = r0.a
            com.byd.overseaappstore.module.ability.data.ApiDataBody r0 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L66
        L30:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L38:
            java.lang.Object r2 = r0.a
            spi.oa r2 = (spi.oa) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L52
        L40:
            kotlin.ResultKt.throwOnFailure(r8)
            spi.i9 r8 = r7.a
            r2 = 3
            r0.a = r7
            r0.d = r4
            java.lang.Object r8 = r8.d(r2, r0)
            if (r8 != r1) goto L51
            return r1
        L51:
            r2 = r7
        L52:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r8 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r8
            spi.i9 r2 = r2.a
            r5 = 10
            r0.a = r8
            r0.d = r3
            java.lang.Object r0 = r2.d(r5, r0)
            if (r0 != r1) goto L63
            return r1
        L63:
            r6 = r0
            r0 = r8
            r8 = r6
        L66:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r8 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r8
            int r0 = r0.getCode()
            r1 = -1
            if (r0 != r1) goto L77
            int r8 = r8.getCode()
            if (r8 == r1) goto L76
            goto L77
        L76:
            r4 = 0
        L77:
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.oa.e(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(@org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.oa.f(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(@org.jetbrains.annotations.NotNull java.lang.String r13, @org.jetbrains.annotations.NotNull android.content.Context r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.oa.g(java.lang.String, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void h(@NotNull String packageName, @NotNull SoftwareStateData softwareStateData) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(softwareStateData, "softwareStateData");
        MutableStateFlow<SoftwareStateData> mutableStateFlow = this.b.get(packageName);
        if (mutableStateFlow == null) {
            return;
        }
        mutableStateFlow.setValue(softwareStateData);
    }
}
