package spi;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.bf;
import spi.ol;

/* JADX INFO: loaded from: classes2.dex */
public final class re implements Closeable {

    @NotNull
    public static final jp B;
    public static final c C = new c(null);
    public final Set<Integer> A;
    public final boolean a;

    @NotNull
    public final d b;

    @NotNull
    public final Map<Integer, cf> c;

    @NotNull
    public final String d;
    public int e;
    public int f;
    public boolean g;
    public final ws h;
    public final vs i;
    public final vs j;
    public final vs k;
    public final ol.a.C0137a l;
    public long m;
    public long n;
    public long o;
    public long p;
    public long q;

    @NotNull
    public final jp r;

    @NotNull
    public jp s;
    public long t;
    public long u;
    public long v;
    public long w;

    @NotNull
    public final Socket x;

    @NotNull
    public final df y;

    @NotNull
    public final e z;

    public static final class a extends ts {
        public final /* synthetic */ re e;
        public final /* synthetic */ long f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, String str2, re reVar, long j) {
            super(str2, false, 2, null);
            this.e = reVar;
            this.f = j;
        }

        @Override // spi.ts
        public final long a() {
            re reVar;
            boolean z;
            synchronized (this.e) {
                reVar = this.e;
                long j = reVar.n;
                long j2 = reVar.m;
                if (j < j2) {
                    z = true;
                } else {
                    reVar.m = j2 + 1;
                    z = false;
                }
            }
            if (z) {
                re.e(reVar, null);
                return -1L;
            }
            reVar.I(false, 1, 0);
            return this.f;
        }
    }

    public static final class b {

        @NotNull
        public Socket a;

        @NotNull
        public String b;

        @NotNull
        public l2 c;

        @NotNull
        public k2 d;

        @NotNull
        public d e;

        @NotNull
        public ol.a.C0137a f;
        public int g;
        public boolean h;

        @NotNull
        public final ws i;

        public b(boolean z, @NotNull ws taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            this.h = z;
            this.i = taskRunner;
            this.e = d.a;
            this.f = ol.a;
        }
    }

    public static final class c {
        private c() {
        }

        public /* synthetic */ c(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static abstract class d {

        @JvmField
        @NotNull
        public static final a a;

        public static final class a extends d {
            @Override // spi.re.d
            public final void b(@NotNull cf stream) throws IOException {
                Intrinsics.checkNotNullParameter(stream, "stream");
                stream.c(ErrorCode.REFUSED_STREAM, null);
            }
        }

        public static final class b {
            private b() {
            }

            public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            new b(null);
            a = new a();
        }

        public void a(@NotNull re connection, @NotNull jp settings) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(settings, "settings");
        }

        public abstract void b(@NotNull cf cfVar) throws IOException;
    }

    public final class e implements bf.c, Function0<Unit> {

        @NotNull
        public final bf a;
        public final /* synthetic */ re b;

        public static final class a extends ts {
            public final /* synthetic */ e e;
            public final /* synthetic */ int f;
            public final /* synthetic */ int g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, boolean z, String str2, boolean z2, e eVar, int i, int i2) {
                super(str2, z2);
                this.e = eVar;
                this.f = i;
                this.g = i2;
            }

            @Override // spi.ts
            public final long a() {
                this.e.b.I(true, this.f, this.g);
                return -1L;
            }
        }

        public e(@NotNull re reVar, bf reader) {
            Intrinsics.checkNotNullParameter(reader, "reader");
            this.b = reVar;
            this.a = reader;
        }

        @Override // spi.bf.c
        public final void a(int i, @NotNull List requestHeaders) {
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            re reVar = this.b;
            Objects.requireNonNull(reVar);
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            synchronized (reVar) {
                if (reVar.A.contains(Integer.valueOf(i))) {
                    reVar.J(i, ErrorCode.PROTOCOL_ERROR);
                    return;
                }
                reVar.A.add(Integer.valueOf(i));
                vs vsVar = reVar.j;
                String str = reVar.d + '[' + i + "] onRequest";
                vsVar.c(new xe(str, true, str, true, reVar, i, requestHeaders), 0L);
            }
        }

        @Override // spi.bf.c
        public final void b() {
        }

        @Override // spi.bf.c
        public final void c(int i, long j) {
            Object obj;
            if (i == 0) {
                re reVar = this.b;
                synchronized (reVar) {
                    re reVar2 = this.b;
                    reVar2.w += j;
                    if (reVar2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    reVar2.notifyAll();
                    Unit unit = Unit.INSTANCE;
                    obj = reVar;
                }
            } else {
                cf cfVarC = this.b.C(i);
                if (cfVarC == null) {
                    return;
                }
                synchronized (cfVarC) {
                    cfVarC.d += j;
                    if (j > 0) {
                        cfVarC.notifyAll();
                    }
                    Unit unit2 = Unit.INSTANCE;
                    obj = cfVarC;
                }
            }
        }

        @Override // spi.bf.c
        public final void d(@NotNull jp settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            vs vsVar = this.b.i;
            String strF = hi.f(new StringBuilder(), this.b.d, " applyAndAckSettings");
            vsVar.c(new ue(strF, true, strF, true, this, false, settings), 0L);
        }

        @Override // spi.bf.c
        public final void e(boolean z, int i, @NotNull List requestHeaders) {
            Intrinsics.checkNotNullParameter(requestHeaders, "headerBlock");
            if (this.b.D(i)) {
                re reVar = this.b;
                Objects.requireNonNull(reVar);
                Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
                vs vsVar = reVar.j;
                String str = reVar.d + '[' + i + "] onHeaders";
                vsVar.c(new we(str, true, str, true, reVar, i, requestHeaders, z), 0L);
                return;
            }
            synchronized (this.b) {
                cf cfVarC = this.b.C(i);
                if (cfVarC != null) {
                    Unit unit = Unit.INSTANCE;
                    cfVarC.j(uu.v(requestHeaders), z);
                    return;
                }
                re reVar2 = this.b;
                if (reVar2.g) {
                    return;
                }
                if (i <= reVar2.e) {
                    return;
                }
                if (i % 2 == reVar2.f % 2) {
                    return;
                }
                cf cfVar = new cf(i, this.b, false, z, uu.v(requestHeaders));
                re reVar3 = this.b;
                reVar3.e = i;
                reVar3.c.put(Integer.valueOf(i), cfVar);
                vs vsVarF = this.b.h.f();
                String str2 = this.b.d + '[' + i + "] onStream";
                vsVarF.c(new te(str2, true, str2, true, cfVar, this, cfVarC, i, requestHeaders, z), 0L);
            }
        }

        @Override // spi.bf.c
        public final void f() {
        }

        @Override // spi.bf.c
        public final void g(boolean z, int i, int i2) {
            if (!z) {
                vs vsVar = this.b.i;
                String strF = hi.f(new StringBuilder(), this.b.d, " ping");
                vsVar.c(new a(strF, true, strF, true, this, i, i2), 0L);
                return;
            }
            synchronized (this.b) {
                if (i == 1) {
                    this.b.n++;
                } else if (i != 2) {
                    if (i == 3) {
                        re reVar = this.b;
                        Objects.requireNonNull(reVar);
                        reVar.notifyAll();
                    }
                    Unit unit = Unit.INSTANCE;
                } else {
                    this.b.p++;
                }
            }
        }

        @Override // spi.bf.c
        public final void h(int i, @NotNull ErrorCode errorCode) {
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            if (!this.b.D(i)) {
                cf cfVarE = this.b.E(i);
                if (cfVarE != null) {
                    synchronized (cfVarE) {
                        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                        if (cfVarE.k == null) {
                            cfVarE.k = errorCode;
                            cfVarE.notifyAll();
                        }
                    }
                    return;
                }
                return;
            }
            re reVar = this.b;
            Objects.requireNonNull(reVar);
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            vs vsVar = reVar.j;
            String str = reVar.d + '[' + i + "] onReset";
            vsVar.c(new ye(str, true, str, true, reVar, i, errorCode), 0L);
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x00f3, code lost:
        
            throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x010a, code lost:
        
            if (r17 == false) goto L72;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x010c, code lost:
        
            r3.j(spi.uu.b, true);
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0111, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:?, code lost:
        
            return;
         */
        @Override // spi.bf.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void i(boolean r17, int r18, @org.jetbrains.annotations.NotNull spi.l2 r19, int r20) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 274
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.re.e.i(boolean, int, spi.l2, int):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [okhttp3.internal.http2.ErrorCode] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v6, types: [kotlin.Unit] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // kotlin.jvm.functions.Function0
        public final Unit invoke() throws Throwable {
            Throwable th;
            ErrorCode errorCode;
            ErrorCode errorCode2;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            IOException e = null;
            try {
                try {
                    this.a.B(this);
                    while (this.a.e(false, this)) {
                    }
                    errorCode2 = ErrorCode.NO_ERROR;
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    th = th2;
                    errorCode = errorCode3;
                    this.b.B(errorCode, errorCode3, e);
                    uu.c(this.a);
                    throw th;
                }
                try {
                    this.b.B(errorCode2, ErrorCode.CANCEL, null);
                    errorCode = errorCode2;
                } catch (IOException e3) {
                    e = e3;
                    ErrorCode errorCode4 = ErrorCode.PROTOCOL_ERROR;
                    re reVar = this.b;
                    reVar.B(errorCode4, errorCode4, e);
                    errorCode = reVar;
                }
                uu.c(this.a);
                errorCode3 = Unit.INSTANCE;
                return errorCode3;
            } catch (Throwable th3) {
                th = th3;
                this.b.B(errorCode, errorCode3, e);
                uu.c(this.a);
                throw th;
            }
        }

        /* JADX WARN: Type inference failed for: r7v2, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, spi.cf>] */
        @Override // spi.bf.c
        public final void j(int i, @NotNull ErrorCode errorCode, @NotNull ByteString debugData) {
            int i2;
            cf[] cfVarArr;
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(debugData, "debugData");
            debugData.size();
            synchronized (this.b) {
                Object[] array = this.b.c.values().toArray(new cf[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                cfVarArr = (cf[]) array;
                this.b.g = true;
                Unit unit = Unit.INSTANCE;
            }
            for (cf cfVar : cfVarArr) {
                if (cfVar.m > i && cfVar.h()) {
                    ErrorCode errorCode2 = ErrorCode.REFUSED_STREAM;
                    synchronized (cfVar) {
                        Intrinsics.checkNotNullParameter(errorCode2, "errorCode");
                        if (cfVar.k == null) {
                            cfVar.k = errorCode2;
                            cfVar.notifyAll();
                        }
                    }
                    this.b.E(cfVar.m);
                }
            }
        }
    }

    public static final class f extends ts {
        public final /* synthetic */ re e;
        public final /* synthetic */ int f;
        public final /* synthetic */ ErrorCode g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, boolean z, String str2, boolean z2, re reVar, int i, ErrorCode errorCode) {
            super(str2, z2);
            this.e = reVar;
            this.f = i;
            this.g = errorCode;
        }

        @Override // spi.ts
        public final long a() {
            try {
                re reVar = this.e;
                int i = this.f;
                ErrorCode statusCode = this.g;
                Objects.requireNonNull(reVar);
                Intrinsics.checkNotNullParameter(statusCode, "statusCode");
                reVar.y.G(i, statusCode);
                return -1L;
            } catch (IOException e) {
                re.e(this.e, e);
                return -1L;
            }
        }
    }

    public static final class g extends ts {
        public final /* synthetic */ re e;
        public final /* synthetic */ int f;
        public final /* synthetic */ long g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, boolean z, String str2, boolean z2, re reVar, int i, long j) {
            super(str2, z2);
            this.e = reVar;
            this.f = i;
            this.g = j;
        }

        @Override // spi.ts
        public final long a() {
            try {
                this.e.y.H(this.f, this.g);
                return -1L;
            } catch (IOException e) {
                re.e(this.e, e);
                return -1L;
            }
        }
    }

    static {
        jp jpVar = new jp();
        jpVar.c(7, 65535);
        jpVar.c(5, 16384);
        B = jpVar;
    }

    public re(@NotNull b builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        boolean z = builder.h;
        this.a = z;
        this.b = builder.e;
        this.c = new LinkedHashMap();
        String str = builder.b;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionName");
        }
        this.d = str;
        this.f = builder.h ? 3 : 2;
        ws wsVar = builder.i;
        this.h = wsVar;
        vs vsVarF = wsVar.f();
        this.i = vsVarF;
        this.j = wsVar.f();
        this.k = wsVar.f();
        this.l = builder.f;
        jp jpVar = new jp();
        if (builder.h) {
            jpVar.c(7, 16777216);
        }
        Unit unit = Unit.INSTANCE;
        this.r = jpVar;
        this.s = B;
        this.w = r2.a();
        Socket socket = builder.a;
        if (socket == null) {
            Intrinsics.throwUninitializedPropertyAccessException("socket");
        }
        this.x = socket;
        k2 k2Var = builder.d;
        if (k2Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sink");
        }
        this.y = new df(k2Var, z);
        l2 l2Var = builder.c;
        if (l2Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException(BYDAutoMultimediaDevice.PARAM_SOURCE);
        }
        this.z = new e(this, new bf(l2Var, z));
        this.A = new LinkedHashSet();
        int i = builder.g;
        if (i != 0) {
            long nanos = TimeUnit.MILLISECONDS.toNanos(i);
            String strF = spi.d.f(str, " ping");
            vsVarF.c(new a(strF, strF, this, nanos), nanos);
        }
    }

    public static final void e(re reVar, IOException iOException) {
        Objects.requireNonNull(reVar);
        ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        reVar.B(errorCode, errorCode, iOException);
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, spi.cf>] */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, spi.cf>] */
    public final void B(@NotNull ErrorCode connectionCode, @NotNull ErrorCode streamCode, @Nullable IOException iOException) {
        int i;
        Intrinsics.checkNotNullParameter(connectionCode, "connectionCode");
        Intrinsics.checkNotNullParameter(streamCode, "streamCode");
        byte[] bArr = uu.a;
        try {
            F(connectionCode);
        } catch (IOException unused) {
        }
        cf[] cfVarArr = null;
        synchronized (this) {
            if (!this.c.isEmpty()) {
                Object[] array = this.c.values().toArray(new cf[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                cfVarArr = (cf[]) array;
                this.c.clear();
            }
            Unit unit = Unit.INSTANCE;
        }
        if (cfVarArr != null) {
            for (cf cfVar : cfVarArr) {
                try {
                    cfVar.c(streamCode, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.y.close();
        } catch (IOException unused3) {
        }
        try {
            this.x.close();
        } catch (IOException unused4) {
        }
        this.i.f();
        this.j.f();
        this.k.f();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.LinkedHashMap, java.util.Map<java.lang.Integer, spi.cf>] */
    @Nullable
    public final synchronized cf C(int i) {
        return (cf) this.c.get(Integer.valueOf(i));
    }

    public final boolean D(int i) {
        return i != 0 && (i & 1) == 0;
    }

    @Nullable
    public final synchronized cf E(int i) {
        cf cfVarRemove;
        cfVarRemove = this.c.remove(Integer.valueOf(i));
        notifyAll();
        return cfVarRemove;
    }

    public final void F(@NotNull ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        synchronized (this.y) {
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                int i = this.e;
                Unit unit = Unit.INSTANCE;
                this.y.D(i, statusCode, uu.a);
            }
        }
    }

    public final synchronized void G(long j) {
        long j2 = this.t + j;
        this.t = j2;
        long j3 = j2 - this.u;
        if (j3 >= this.r.a() / 2) {
            K(0, j3);
            this.u += j3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.y.b);
        r6 = r2;
        r8.v += r6;
        r4 = kotlin.Unit.INSTANCE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void H(int r9, boolean r10, @org.jetbrains.annotations.Nullable spi.g2 r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            spi.df r12 = r8.y
            r12.B(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L6a
            monitor-enter(r8)
        L12:
            long r4 = r8.v     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            long r6 = r8.w     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L32
            java.util.Map<java.lang.Integer, spi.cf> r2 = r8.c     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            if (r2 == 0) goto L2a
            r8.wait()     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            goto L12
        L2a:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
            throw r9     // Catch: java.lang.Throwable -> L59 java.lang.InterruptedException -> L5b
        L32:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L59
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L59
            spi.df r4 = r8.y     // Catch: java.lang.Throwable -> L59
            int r4 = r4.b     // Catch: java.lang.Throwable -> L59
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L59
            long r4 = r8.v     // Catch: java.lang.Throwable -> L59
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L59
            long r4 = r4 + r6
            r8.v = r4     // Catch: java.lang.Throwable -> L59
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L59
            monitor-exit(r8)
            long r12 = r12 - r6
            spi.df r4 = r8.y
            if (r10 == 0) goto L54
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L54
            r5 = 1
            goto L55
        L54:
            r5 = r3
        L55:
            r4.B(r5, r9, r11, r2)
            goto Ld
        L59:
            r9 = move-exception
            goto L68
        L5b:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L59
            r9.interrupt()     // Catch: java.lang.Throwable -> L59
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L59
            r9.<init>()     // Catch: java.lang.Throwable -> L59
            throw r9     // Catch: java.lang.Throwable -> L59
        L68:
            monitor-exit(r8)
            throw r9
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.re.H(int, boolean, spi.g2, long):void");
    }

    public final void I(boolean z, int i, int i2) {
        try {
            this.y.F(z, i, i2);
        } catch (IOException e2) {
            ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
            B(errorCode, errorCode, e2);
        }
    }

    public final void J(int i, @NotNull ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        vs vsVar = this.i;
        String str = this.d + '[' + i + "] writeSynReset";
        vsVar.c(new f(str, true, str, true, this, i, errorCode), 0L);
    }

    public final void K(int i, long j) {
        vs vsVar = this.i;
        String str = this.d + '[' + i + "] windowUpdate";
        vsVar.c(new g(str, true, str, true, this, i, j), 0L);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        B(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    public final void flush() throws IOException {
        this.y.flush();
    }
}
