package spi;

import com.byd.overseaappstore.module.ability.data.ApiDataBody;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class i9 implements yp {

    @NotNull
    public final yp a;

    @NotNull
    public final DataGenerator b;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr", f = "ExecutorDbMgr.kt", i = {}, l = {285}, m = "bizFetchDownloadingAndUpdatingList", n = {}, s = {})
    public static final class a extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return i9.this.x(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr$bizFetchNetworkingCount$1", f = "ExecutorDbMgr.kt", i = {}, l = {404}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public int a;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return i9.this.new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            List list;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            boolean z = true;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                yp ypVar = i9.this.a;
                this.a = 1;
                obj = ypVar.b(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            ApiDataBody apiDataBody = (ApiDataBody) obj;
            Collection collection = (Collection) apiDataBody.getData();
            int size = 0;
            if (collection != null && !collection.isEmpty()) {
                z = false;
            }
            if (!z && (list = (List) apiDataBody.getData()) != null) {
                size = list.size();
            }
            return Boxing.boxInt(size);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr", f = "ExecutorDbMgr.kt", i = {}, l = {384}, m = "bizFetchSoftware", n = {}, s = {})
    public static final class c extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return i9.this.z(null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr", f = "ExecutorDbMgr.kt", i = {}, l = {333}, m = "bizFetchSoftwareExtra", n = {}, s = {})
    public static final class d extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return i9.this.A(null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr", f = "ExecutorDbMgr.kt", i = {}, l = {298}, m = "bizFetchSoftwareList", n = {}, s = {})
    public static final class e extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public e(Continuation<? super e> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return i9.this.C(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr", f = "ExecutorDbMgr.kt", i = {}, l = {311}, m = "bizFetchSoftwareList", n = {}, s = {})
    public static final class f extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public f(Continuation<? super f> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return i9.this.B(0, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.db.ExecutorDbMgr", f = "ExecutorDbMgr.kt", i = {}, l = {457}, m = "bizFetchSoftwareWaiting", n = {}, s = {})
    public static final class g extends ContinuationImpl {
        public /* synthetic */ Object a;
        public int c;

        public g(Continuation<? super g> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.a = obj;
            this.c |= Integer.MIN_VALUE;
            return i9.this.D(this);
        }
    }

    public i9(@NotNull yp softwareDataSourceDb, @NotNull DataGenerator dataGenerator) {
        Intrinsics.checkNotNullParameter(softwareDataSourceDb, "softwareDataSourceDb");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        this.a = softwareDataSourceDb;
        this.b = dataGenerator;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object A(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super spi.oq> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof spi.i9.d
            if (r0 == 0) goto L13
            r0 = r6
            spi.i9$d r0 = (spi.i9.d) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            spi.i9$d r0 = new spi.i9$d
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            spi.yp r6 = r4.a
            r0.c = r3
            java.lang.Object r6 = r6.g(r5, r0)
            if (r6 != r1) goto L3f
            return r1
        L3f:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r6 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r6
            int r5 = r6.getCode()
            r0 = -1
            if (r5 != r0) goto L4a
            r5 = 0
            goto L50
        L4a:
            java.lang.Object r5 = r6.getData()
            spi.oq r5 = (spi.oq) r5
        L50:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i9.A(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object B(int r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<spi.qq>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof spi.i9.f
            if (r0 == 0) goto L13
            r0 = r6
            spi.i9$f r0 = (spi.i9.f) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            spi.i9$f r0 = new spi.i9$f
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            spi.yp r6 = r4.a
            r0.c = r3
            java.lang.Object r6 = r6.d(r5, r0)
            if (r6 != r1) goto L3f
            return r1
        L3f:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r6 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r6
            int r5 = r6.getCode()
            r0 = -1
            if (r5 != r0) goto L4a
            r5 = 0
            goto L50
        L4a:
            java.lang.Object r5 = r6.getData()
            java.util.List r5 = (java.util.List) r5
        L50:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i9.B(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object C(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<spi.qq>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof spi.i9.e
            if (r0 == 0) goto L13
            r0 = r5
            spi.i9$e r0 = (spi.i9.e) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            spi.i9$e r0 = new spi.i9$e
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            spi.yp r5 = r4.a
            r0.c = r3
            java.lang.Object r5 = r5.f(r0)
            if (r5 != r1) goto L3f
            return r1
        L3f:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r5 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r5
            int r0 = r5.getCode()
            r1 = -1
            if (r0 != r1) goto L4a
            r5 = 0
            goto L50
        L4a:
            java.lang.Object r5 = r5.getData()
            java.util.List r5 = (java.util.List) r5
        L50:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i9.C(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object D(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super spi.qq> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof spi.i9.g
            if (r0 == 0) goto L13
            r0 = r5
            spi.i9$g r0 = (spi.i9.g) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            spi.i9$g r0 = new spi.i9$g
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            spi.yp r5 = r4.a
            r0.c = r3
            java.lang.Object r5 = r5.l(r0)
            if (r5 != r1) goto L3f
            return r1
        L3f:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r5 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r5
            java.lang.Object r0 = r5.getData()
            java.util.Collection r0 = (java.util.Collection) r0
            r1 = 0
            if (r0 == 0) goto L52
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L51
            goto L52
        L51:
            r3 = r1
        L52:
            r0 = 0
            if (r3 == 0) goto L56
            goto L65
        L56:
            java.lang.Object r5 = r5.getData()
            java.util.List r5 = (java.util.List) r5
            if (r5 == 0) goto L65
            java.lang.Object r5 = r5.get(r1)
            r0 = r5
            spi.qq r0 = (spi.qq) r0
        L65:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i9.D(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object E(@NotNull SoftwareOptParam softwareOptParam, @NotNull Continuation<? super Unit> continuation) {
        Object objO = this.a.o(this.b.parseToSoftwareLocal(softwareOptParam), continuation);
        return objO == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objO : Unit.INSTANCE;
    }

    @Nullable
    public final Object F(@NotNull SoftwareOptParam softwareOptParam, @NotNull Continuation<? super Unit> continuation) {
        Object objP = this.a.p(this.b.parseToSoftwareLocal(softwareOptParam), continuation);
        return objP == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objP : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object a(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.a(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object b(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.b(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object c(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.c(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object d(int i, @NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.d(i, continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object e(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.e(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object f(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.f(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object g(@NotNull String str, @NotNull Continuation<? super ApiDataBody<oq>> continuation) {
        return this.a.g(str, continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object h(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.h(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object i(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.i(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object j(@NotNull String str, @NotNull Continuation<? super ApiDataBody<qq>> continuation) {
        return this.a.j(str, continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object k(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.k(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object l(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.l(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object m(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.m(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object n(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation) {
        return this.a.n(continuation);
    }

    @Override // spi.yp
    @Nullable
    public final Object o(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation) {
        Object objO = this.a.o(pqVar, continuation);
        return objO == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objO : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object p(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation) {
        Object objP = this.a.p(pqVar, continuation);
        return objP == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objP : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object q(@NotNull oq oqVar, @NotNull Continuation<? super Unit> continuation) {
        Object objQ = this.a.q(oqVar, continuation);
        return objQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objQ : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object r(@NotNull String str, int i, int i2, @NotNull Continuation<? super Unit> continuation) {
        Object objR = this.a.r(str, i, i2, continuation);
        return objR == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objR : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object s(@NotNull List<oq> list, @NotNull Continuation<? super Unit> continuation) {
        Object objS = this.a.s(list, continuation);
        return objS == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objS : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object t(@NotNull String str, int i, @NotNull Continuation<? super Unit> continuation) {
        Object objT = this.a.t(str, i, continuation);
        return objT == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objT : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object u(@NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        Object objU = this.a.u(str, continuation);
        return objU == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objU : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object v(@NotNull String str, int i, @NotNull String str2, @NotNull Continuation<? super Unit> continuation) {
        Object objV = this.a.v(str, i, str2, continuation);
        return objV == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objV : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object w(@NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        Object objW = this.a.w(str, continuation);
        return objW == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objW : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object x(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<spi.qq>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof spi.i9.a
            if (r0 == 0) goto L13
            r0 = r5
            spi.i9$a r0 = (spi.i9.a) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            spi.i9$a r0 = new spi.i9$a
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            spi.yp r5 = r4.a
            r0.c = r3
            java.lang.Object r5 = r5.h(r0)
            if (r5 != r1) goto L3f
            return r1
        L3f:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r5 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r5
            int r0 = r5.getCode()
            r1 = -1
            if (r0 != r1) goto L4a
            r5 = 0
            goto L50
        L4a:
            java.lang.Object r5 = r5.getData()
            java.util.List r5 = (java.util.List) r5
        L50:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i9.x(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final int y() {
        return ((Number) BuildersKt__BuildersKt.runBlocking$default(null, new b(null), 1, null)).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object z(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super spi.qq> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof spi.i9.c
            if (r0 == 0) goto L13
            r0 = r6
            spi.i9$c r0 = (spi.i9.c) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            spi.i9$c r0 = new spi.i9$c
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            spi.yp r6 = r4.a
            r0.c = r3
            java.lang.Object r6 = r6.j(r5, r0)
            if (r6 != r1) goto L3f
            return r1
        L3f:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r6 = (com.byd.overseaappstore.module.ability.data.ApiDataBody) r6
            int r5 = r6.getCode()
            r0 = -1
            if (r5 != r0) goto L4a
            r5 = 0
            goto L50
        L4a:
            java.lang.Object r5 = r6.getData()
            spi.qq r5 = (spi.qq) r5
        L50:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i9.z(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
