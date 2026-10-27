package spi;

import android.content.Context;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class xp implements yp {

    @NotNull
    public final tr a;

    @NotNull
    public final mq b;

    @NotNull
    public final Context c;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {69}, m = "fetchDownloadedButNotInstalledList", n = {"this"}, s = {"L$0"})
    public static final class a extends ContinuationImpl {
        public xp a;
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
            return xp.this.i(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {148}, m = "fetchSoftware", n = {"this"}, s = {"L$0"})
    public static final class b extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.j(null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {}, l = {158}, m = "fetchSoftwareExtra", n = {}, s = {})
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
            return xp.this.g(null, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {35}, m = "fetchSoftwareList", n = {"this"}, s = {"L$0"})
    public static final class d extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.f(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {45}, m = "fetchSoftwareList", n = {"this"}, s = {"L$0"})
    public static final class e extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public e(Continuation<? super e> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.d(0, this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {55}, m = "fetchSoftwareListDownloadingAndUpdating", n = {"this"}, s = {"L$0"})
    public static final class f extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public f(Continuation<? super f> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.h(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {79}, m = "fetchSoftwareListInNetworking", n = {"this"}, s = {"L$0"})
    public static final class g extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public g(Continuation<? super g> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.b(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {86}, m = "fetchSoftwareListInNetworkingForDownloads", n = {"this"}, s = {"L$0"})
    public static final class h extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public h(Continuation<? super h> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.c(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {93}, m = "fetchSoftwareListInNetworkingForUpdates", n = {"this"}, s = {"L$0"})
    public static final class i extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public i(Continuation<? super i> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.m(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {128}, m = "fetchSoftwareListRangeFromDownloadWaitingToDownloaded", n = {"this"}, s = {"L$0"})
    public static final class j extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public j(Continuation<? super j> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.e(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {118}, m = "fetchSoftwareListRangeFromInstallToUpdateFinish", n = {"this"}, s = {"L$0"})
    public static final class k extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public k(Continuation<? super k> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.a(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {108}, m = "fetchSoftwareListRangeFromToUpdateToUpdateFinish", n = {"this"}, s = {"L$0"})
    public static final class l extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public l(Continuation<? super l> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.n(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {138}, m = "fetchSoftwareListToUpdateAndUpdateWaiting", n = {"this"}, s = {"L$0"})
    public static final class m extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public m(Continuation<? super m> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.k(this);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.source.SoftwareDataSourceDb", f = "SoftwareDataSourceDb.kt", i = {0}, l = {100}, m = "fetchSoftwareListWaiting", n = {"this"}, s = {"L$0"})
    public static final class n extends ContinuationImpl {
        public xp a;
        public /* synthetic */ Object b;
        public int d;

        public n(Continuation<? super n> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.b = obj;
            this.d |= Integer.MIN_VALUE;
            return xp.this.l(this);
        }
    }

    public xp(@NotNull tr softwareWrapDao, @NotNull mq softwareExtraDao, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(softwareWrapDao, "softwareWrapDao");
        Intrinsics.checkNotNullParameter(softwareExtraDao, "softwareExtraDao");
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = softwareWrapDao;
        this.b = softwareExtraDao;
        this.c = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof spi.xp.k
            if (r0 == 0) goto L13
            r0 = r15
            spi.xp$k r0 = (spi.xp.k) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$k r0 = new spi.xp$k
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r15)
            goto L44
        L2b:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            spi.tr r15 = r14.a
            r0.a = r14
            r0.d = r3
            java.lang.Object r15 = r15.a(r0)
            if (r15 != r1) goto L43
            return r1
        L43:
            r0 = r14
        L44:
            r7 = r15
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L52
            boolean r15 = r7.isEmpty()
            if (r15 == 0) goto L50
            goto L52
        L50:
            r15 = 0
            goto L53
        L52:
            r15 = r3
        L53:
            if (r15 != r3) goto L61
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L8b
        L61:
            r5 = 0
            r6 = 0
            java.util.Iterator r15 = r7.iterator()
        L67:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L83
            java.lang.Object r1 = r15.next()
            spi.qq r1 = (spi.qq) r1
            spi.pq r1 = r1.a
            spi.h8 r2 = spi.h8.a
            java.lang.String r3 = r1.f
            android.content.Context r4 = r0.c
            java.lang.String r2 = r2.a(r3, r4)
            r1.a(r2)
            goto L67
        L83:
            r8 = 3
            r9 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
        L8b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof spi.xp.g
            if (r0 == 0) goto L13
            r0 = r9
            spi.xp$g r0 = (spi.xp.g) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$g r0 = new spi.xp$g
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r9)
            goto L44
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            spi.tr r9 = r8.a
            r0.a = r8
            r0.d = r3
            java.lang.Object r9 = r9.b(r0)
            if (r9 != r1) goto L43
            return r1
        L43:
            r0 = r8
        L44:
            java.util.List r9 = (java.util.List) r9
            r2 = 0
            r3 = 0
            if (r9 == 0) goto L6a
            java.util.Iterator r1 = r9.iterator()
        L4e:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L6b
            java.lang.Object r4 = r1.next()
            spi.qq r4 = (spi.qq) r4
            spi.pq r4 = r4.a
            spi.h8 r5 = spi.h8.a
            java.lang.String r6 = r4.f
            android.content.Context r7 = r0.c
            java.lang.String r5 = r5.a(r6, r7)
            r4.a(r5)
            goto L4e
        L6a:
            r9 = 0
        L6b:
            r4 = r9
            r5 = 3
            r6 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r9 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof spi.xp.h
            if (r0 == 0) goto L13
            r0 = r9
            spi.xp$h r0 = (spi.xp.h) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$h r0 = new spi.xp$h
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r9)
            goto L44
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            spi.tr r9 = r8.a
            r0.a = r8
            r0.d = r3
            java.lang.Object r9 = r9.c(r0)
            if (r9 != r1) goto L43
            return r1
        L43:
            r0 = r8
        L44:
            java.util.List r9 = (java.util.List) r9
            r2 = 0
            r3 = 0
            if (r9 == 0) goto L6a
            java.util.Iterator r1 = r9.iterator()
        L4e:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L6b
            java.lang.Object r4 = r1.next()
            spi.qq r4 = (spi.qq) r4
            spi.pq r4 = r4.a
            spi.h8 r5 = spi.h8.a
            java.lang.String r6 = r4.f
            android.content.Context r7 = r0.c
            java.lang.String r5 = r5.a(r6, r7)
            r4.a(r5)
            goto L4e
        L6a:
            r9 = 0
        L6b:
            r4 = r9
            r5 = 3
            r6 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r9 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.c(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(int r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof spi.xp.e
            if (r2 == 0) goto L17
            r2 = r1
            spi.xp$e r2 = (spi.xp.e) r2
            int r3 = r2.d
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.d = r3
            goto L1c
        L17:
            spi.xp$e r2 = new spi.xp$e
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.b
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.d
            r5 = 1
            if (r4 == 0) goto L37
            if (r4 != r5) goto L2f
            spi.xp r2 = r2.a
            kotlin.ResultKt.throwOnFailure(r1)
            goto L4a
        L2f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L37:
            kotlin.ResultKt.throwOnFailure(r1)
            spi.tr r1 = r0.a
            r2.a = r0
            r2.d = r5
            r4 = r17
            java.lang.Object r1 = r1.d(r4, r2)
            if (r1 != r3) goto L49
            return r3
        L49:
            r2 = r0
        L4a:
            r9 = r1
            java.util.List r9 = (java.util.List) r9
            if (r9 == 0) goto L58
            boolean r1 = r9.isEmpty()
            if (r1 == 0) goto L56
            goto L58
        L56:
            r1 = 0
            goto L59
        L58:
            r1 = r5
        L59:
            if (r1 != r5) goto L67
            com.byd.overseaappstore.module.ability.data.ApiDataBody r1 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r11 = -1
            r12 = 0
            r13 = 0
            r14 = 6
            r15 = 0
            r10 = r1
            r10.<init>(r11, r12, r13, r14, r15)
            goto L91
        L67:
            r7 = 0
            r8 = 0
            java.util.Iterator r1 = r9.iterator()
        L6d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L89
            java.lang.Object r3 = r1.next()
            spi.qq r3 = (spi.qq) r3
            spi.pq r3 = r3.a
            spi.h8 r4 = spi.h8.a
            java.lang.String r5 = r3.f
            android.content.Context r6 = r2.c
            java.lang.String r4 = r4.a(r5, r6)
            r3.a(r4)
            goto L6d
        L89:
            r10 = 3
            r11 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r1 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11)
        L91:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.d(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof spi.xp.j
            if (r0 == 0) goto L13
            r0 = r15
            spi.xp$j r0 = (spi.xp.j) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$j r0 = new spi.xp$j
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r15)
            goto L44
        L2b:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            spi.tr r15 = r14.a
            r0.a = r14
            r0.d = r3
            java.lang.Object r15 = r15.e(r0)
            if (r15 != r1) goto L43
            return r1
        L43:
            r0 = r14
        L44:
            r7 = r15
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L52
            boolean r15 = r7.isEmpty()
            if (r15 == 0) goto L50
            goto L52
        L50:
            r15 = 0
            goto L53
        L52:
            r15 = r3
        L53:
            if (r15 != r3) goto L61
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L8b
        L61:
            r5 = 0
            r6 = 0
            java.util.Iterator r15 = r7.iterator()
        L67:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L83
            java.lang.Object r1 = r15.next()
            spi.qq r1 = (spi.qq) r1
            spi.pq r1 = r1.a
            spi.h8 r2 = spi.h8.a
            java.lang.String r3 = r1.f
            android.content.Context r4 = r0.c
            java.lang.String r2 = r2.a(r3, r4)
            r1.a(r2)
            goto L67
        L83:
            r8 = 3
            r9 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
        L8b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.e(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof spi.xp.d
            if (r0 == 0) goto L13
            r0 = r15
            spi.xp$d r0 = (spi.xp.d) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$d r0 = new spi.xp$d
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r15)
            goto L44
        L2b:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            spi.tr r15 = r14.a
            r0.a = r14
            r0.d = r3
            java.lang.Object r15 = r15.f(r0)
            if (r15 != r1) goto L43
            return r1
        L43:
            r0 = r14
        L44:
            r7 = r15
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L52
            boolean r15 = r7.isEmpty()
            if (r15 == 0) goto L50
            goto L52
        L50:
            r15 = 0
            goto L53
        L52:
            r15 = r3
        L53:
            if (r15 != r3) goto L61
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L8b
        L61:
            r5 = 0
            r6 = 0
            java.util.Iterator r15 = r7.iterator()
        L67:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L83
            java.lang.Object r1 = r15.next()
            spi.qq r1 = (spi.qq) r1
            spi.pq r1 = r1.a
            spi.h8 r2 = spi.h8.a
            java.lang.String r3 = r1.f
            android.content.Context r4 = r0.c
            java.lang.String r2 = r2.a(r3, r4)
            r1.a(r2)
            goto L67
        L83:
            r8 = 3
            r9 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
        L8b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.f(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(@org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<spi.oq>> r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof spi.xp.c
            if (r2 == 0) goto L17
            r2 = r1
            spi.xp$c r2 = (spi.xp.c) r2
            int r3 = r2.c
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.c = r3
            goto L1c
        L17:
            spi.xp$c r2 = new spi.xp$c
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.a
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.c
            r5 = 1
            if (r4 == 0) goto L35
            if (r4 != r5) goto L2d
            kotlin.ResultKt.throwOnFailure(r1)
            goto L45
        L2d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L35:
            kotlin.ResultKt.throwOnFailure(r1)
            spi.mq r1 = r0.b
            r2.c = r5
            r4 = r17
            java.lang.Object r1 = r1.g(r4, r2)
            if (r1 != r3) goto L45
            return r3
        L45:
            r9 = r1
            spi.oq r9 = (spi.oq) r9
            if (r9 != 0) goto L4c
            r1 = r5
            goto L4d
        L4c:
            r1 = 0
        L4d:
            if (r1 != r5) goto L5b
            com.byd.overseaappstore.module.ability.data.ApiDataBody r1 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r11 = -1
            r12 = 0
            r13 = 0
            r14 = 6
            r15 = 0
            r10 = r1
            r10.<init>(r11, r12, r13, r14, r15)
            goto L65
        L5b:
            com.byd.overseaappstore.module.ability.data.ApiDataBody r1 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r7 = 0
            r8 = 0
            r10 = 3
            r11 = 0
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11)
        L65:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.g(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object h(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof spi.xp.f
            if (r0 == 0) goto L13
            r0 = r15
            spi.xp$f r0 = (spi.xp.f) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$f r0 = new spi.xp$f
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r15)
            goto L44
        L2b:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            spi.tr r15 = r14.a
            r0.a = r14
            r0.d = r3
            java.lang.Object r15 = r15.h(r0)
            if (r15 != r1) goto L43
            return r1
        L43:
            r0 = r14
        L44:
            r7 = r15
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L52
            boolean r15 = r7.isEmpty()
            if (r15 == 0) goto L50
            goto L52
        L50:
            r15 = 0
            goto L53
        L52:
            r15 = r3
        L53:
            if (r15 != r3) goto L61
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L8b
        L61:
            r5 = 0
            r6 = 0
            java.util.Iterator r15 = r7.iterator()
        L67:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L83
            java.lang.Object r1 = r15.next()
            spi.qq r1 = (spi.qq) r1
            spi.pq r1 = r1.a
            spi.h8 r2 = spi.h8.a
            java.lang.String r3 = r1.f
            android.content.Context r4 = r0.c
            java.lang.String r2 = r2.a(r3, r4)
            r1.a(r2)
            goto L67
        L83:
            r8 = 3
            r9 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
        L8b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.h(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof spi.xp.a
            if (r0 == 0) goto L13
            r0 = r15
            spi.xp$a r0 = (spi.xp.a) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$a r0 = new spi.xp$a
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r15)
            goto L44
        L2b:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            spi.tr r15 = r14.a
            r0.a = r14
            r0.d = r3
            java.lang.Object r15 = r15.i(r0)
            if (r15 != r1) goto L43
            return r1
        L43:
            r0 = r14
        L44:
            r7 = r15
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L52
            boolean r15 = r7.isEmpty()
            if (r15 == 0) goto L50
            goto L52
        L50:
            r15 = 0
            goto L53
        L52:
            r15 = r3
        L53:
            if (r15 != r3) goto L61
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L8b
        L61:
            r5 = 0
            r6 = 0
            java.util.Iterator r15 = r7.iterator()
        L67:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L83
            java.lang.Object r1 = r15.next()
            spi.qq r1 = (spi.qq) r1
            spi.pq r1 = r1.a
            spi.h8 r2 = spi.h8.a
            java.lang.String r3 = r1.f
            android.content.Context r4 = r0.c
            java.lang.String r2 = r2.a(r3, r4)
            r1.a(r2)
            goto L67
        L83:
            r8 = 3
            r9 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
        L8b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.i(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object j(@org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<spi.qq>> r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof spi.xp.b
            if (r2 == 0) goto L17
            r2 = r1
            spi.xp$b r2 = (spi.xp.b) r2
            int r3 = r2.d
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.d = r3
            goto L1c
        L17:
            spi.xp$b r2 = new spi.xp$b
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.b
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.d
            r5 = 1
            if (r4 == 0) goto L37
            if (r4 != r5) goto L2f
            spi.xp r2 = r2.a
            kotlin.ResultKt.throwOnFailure(r1)
            goto L4a
        L2f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L37:
            kotlin.ResultKt.throwOnFailure(r1)
            spi.tr r1 = r0.a
            r2.a = r0
            r2.d = r5
            r4 = r17
            java.lang.Object r1 = r1.j(r4, r2)
            if (r1 != r3) goto L49
            return r3
        L49:
            r2 = r0
        L4a:
            r9 = r1
            spi.qq r9 = (spi.qq) r9
            if (r9 != 0) goto L51
            r1 = r5
            goto L52
        L51:
            r1 = 0
        L52:
            if (r1 != r5) goto L60
            com.byd.overseaappstore.module.ability.data.ApiDataBody r1 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r11 = -1
            r12 = 0
            r13 = 0
            r14 = 6
            r15 = 0
            r10 = r1
            r10.<init>(r11, r12, r13, r14, r15)
            goto L7b
        L60:
            r7 = 0
            r8 = 0
            spi.pq r1 = r9.a
            spi.h8 r3 = spi.h8.a
            java.lang.String r4 = r1.f
            android.content.Context r2 = r2.c
            java.lang.String r2 = r3.a(r4, r2)
            r1.a(r2)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            r10 = 3
            r11 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r1 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11)
        L7b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.j(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object k(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof spi.xp.m
            if (r0 == 0) goto L13
            r0 = r15
            spi.xp$m r0 = (spi.xp.m) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$m r0 = new spi.xp$m
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r15)
            goto L44
        L2b:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            spi.tr r15 = r14.a
            r0.a = r14
            r0.d = r3
            java.lang.Object r15 = r15.k(r0)
            if (r15 != r1) goto L43
            return r1
        L43:
            r0 = r14
        L44:
            r7 = r15
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L52
            boolean r15 = r7.isEmpty()
            if (r15 == 0) goto L50
            goto L52
        L50:
            r15 = 0
            goto L53
        L52:
            r15 = r3
        L53:
            if (r15 != r3) goto L61
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L8b
        L61:
            r5 = 0
            r6 = 0
            java.util.Iterator r15 = r7.iterator()
        L67:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L83
            java.lang.Object r1 = r15.next()
            spi.qq r1 = (spi.qq) r1
            spi.pq r1 = r1.a
            spi.h8 r2 = spi.h8.a
            java.lang.String r3 = r1.f
            android.content.Context r4 = r0.c
            java.lang.String r2 = r2.a(r3, r4)
            r1.a(r2)
            goto L67
        L83:
            r8 = 3
            r9 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
        L8b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.k(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object l(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof spi.xp.n
            if (r0 == 0) goto L13
            r0 = r9
            spi.xp$n r0 = (spi.xp.n) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$n r0 = new spi.xp$n
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r9)
            goto L44
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            spi.tr r9 = r8.a
            r0.a = r8
            r0.d = r3
            java.lang.Object r9 = r9.l(r0)
            if (r9 != r1) goto L43
            return r1
        L43:
            r0 = r8
        L44:
            java.util.List r9 = (java.util.List) r9
            r2 = 0
            r3 = 0
            if (r9 == 0) goto L6a
            java.util.Iterator r1 = r9.iterator()
        L4e:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L6b
            java.lang.Object r4 = r1.next()
            spi.qq r4 = (spi.qq) r4
            spi.pq r4 = r4.a
            spi.h8 r5 = spi.h8.a
            java.lang.String r6 = r4.f
            android.content.Context r7 = r0.c
            java.lang.String r5 = r5.a(r6, r7)
            r4.a(r5)
            goto L4e
        L6a:
            r9 = 0
        L6b:
            r4 = r9
            r5 = 3
            r6 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r9 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.l(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof spi.xp.i
            if (r0 == 0) goto L13
            r0 = r9
            spi.xp$i r0 = (spi.xp.i) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$i r0 = new spi.xp$i
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r9)
            goto L44
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            spi.tr r9 = r8.a
            r0.a = r8
            r0.d = r3
            java.lang.Object r9 = r9.m(r0)
            if (r9 != r1) goto L43
            return r1
        L43:
            r0 = r8
        L44:
            java.util.List r9 = (java.util.List) r9
            r2 = 0
            r3 = 0
            if (r9 == 0) goto L6a
            java.util.Iterator r1 = r9.iterator()
        L4e:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L6b
            java.lang.Object r4 = r1.next()
            spi.qq r4 = (spi.qq) r4
            spi.pq r4 = r4.a
            spi.h8 r5 = spi.h8.a
            java.lang.String r6 = r4.f
            android.content.Context r7 = r0.c
            java.lang.String r5 = r5.a(r6, r7)
            r4.a(r5)
            goto L4e
        L6a:
            r9 = 0
        L6b:
            r4 = r9
            r5 = 3
            r6 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r9 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.m(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // spi.yp
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object n(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.byd.overseaappstore.module.ability.data.ApiDataBody<java.util.List<spi.qq>>> r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof spi.xp.l
            if (r0 == 0) goto L13
            r0 = r15
            spi.xp$l r0 = (spi.xp.l) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            spi.xp$l r0 = new spi.xp$l
            r0.<init>(r15)
        L18:
            java.lang.Object r15 = r0.b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.d
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            spi.xp r0 = r0.a
            kotlin.ResultKt.throwOnFailure(r15)
            goto L44
        L2b:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            spi.tr r15 = r14.a
            r0.a = r14
            r0.d = r3
            java.lang.Object r15 = r15.n(r0)
            if (r15 != r1) goto L43
            return r1
        L43:
            r0 = r14
        L44:
            r7 = r15
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L52
            boolean r15 = r7.isEmpty()
            if (r15 == 0) goto L50
            goto L52
        L50:
            r15 = 0
            goto L53
        L52:
            r15 = r3
        L53:
            if (r15 != r3) goto L61
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 6
            r13 = 0
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13)
            goto L8b
        L61:
            r5 = 0
            r6 = 0
            java.util.Iterator r15 = r7.iterator()
        L67:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L83
            java.lang.Object r1 = r15.next()
            spi.qq r1 = (spi.qq) r1
            spi.pq r1 = r1.a
            spi.h8 r2 = spi.h8.a
            java.lang.String r3 = r1.f
            android.content.Context r4 = r0.c
            java.lang.String r2 = r2.a(r3, r4)
            r1.a(r2)
            goto L67
        L83:
            r8 = 3
            r9 = 0
            com.byd.overseaappstore.module.ability.data.ApiDataBody r15 = new com.byd.overseaappstore.module.ability.data.ApiDataBody
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
        L8b:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.xp.n(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // spi.yp
    @Nullable
    public final Object o(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation) {
        pqVar.a(h8.a.b(pqVar.f, this.c));
        Object objG = this.a.g(pqVar, continuation);
        return objG == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objG : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object p(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation) {
        pqVar.a(h8.a.b(pqVar.f, this.c));
        Object objP = this.a.p(pqVar, continuation);
        return objP == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objP : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object q(@NotNull oq oqVar, @NotNull Continuation<? super Unit> continuation) {
        Object objR = this.a.r(oqVar, continuation);
        return objR == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objR : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object r(@NotNull String str, int i2, int i3, @NotNull Continuation<? super Unit> continuation) {
        Object objQ = this.a.q(str, i2, i3, continuation);
        return objQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objQ : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object s(@NotNull List<oq> list, @NotNull Continuation<? super Unit> continuation) {
        Object objO = this.a.o(list, continuation);
        return objO == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objO : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object t(@NotNull String str, int i2, @NotNull Continuation<? super Unit> continuation) {
        Object objS = this.a.s(str, i2, continuation);
        return objS == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objS : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object u(@NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        Object objA = this.b.a(str, continuation);
        return objA == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objA : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object v(@NotNull String str, int i2, @NotNull String str2, @NotNull Continuation<? super Unit> continuation) {
        Object objT = this.a.t(str, i2, h8.a.b(str2, this.c), continuation);
        return objT == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objT : Unit.INSTANCE;
    }

    @Override // spi.yp
    @Nullable
    public final Object w(@NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        Object objB = this.b.b(str, continuation);
        return objB == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objB : Unit.INSTANCE;
    }
}
