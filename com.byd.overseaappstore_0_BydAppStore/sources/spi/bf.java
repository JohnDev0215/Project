package spi;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import spi.oe;

/* JADX INFO: loaded from: classes2.dex */
public final class bf implements Closeable {

    @NotNull
    public static final Logger e;
    public static final a f = new a(null);
    public final b a;
    public final oe.a b;
    public final l2 c;
    public final boolean d;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int a(int i, int i2, int i3) throws IOException {
            if ((i2 & 8) != 0) {
                i--;
            }
            if (i3 <= i) {
                return i - i3;
            }
            throw new IOException(d.c("PROTOCOL_ERROR padding ", i3, " > remaining length ", i));
        }
    }

    public static final class b implements vr {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public final l2 f;

        public b(@NotNull l2 source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.f = source;
        }

        @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
        }

        @Override // spi.vr
        public final long read(@NotNull g2 sink, long j) throws IOException {
            int i;
            int i2;
            Intrinsics.checkNotNullParameter(sink, "sink");
            do {
                int i3 = this.d;
                if (i3 != 0) {
                    long j2 = this.f.read(sink, Math.min(j, i3));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.d -= (int) j2;
                    return j2;
                }
                this.f.skip(this.e);
                this.e = 0;
                if ((this.b & 4) != 0) {
                    return -1L;
                }
                i = this.c;
                int iS = uu.s(this.f);
                this.d = iS;
                this.a = iS;
                int i4 = this.f.readByte() & 255;
                this.b = this.f.readByte() & 255;
                Objects.requireNonNull(bf.f);
                Logger logger = bf.e;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(qe.e.b(true, this.c, this.a, i4, this.b));
                }
                i2 = this.f.readInt() & Integer.MAX_VALUE;
                this.c = i2;
                if (i4 != 9) {
                    throw new IOException(i4 + " != TYPE_CONTINUATION");
                }
            } while (i2 == i);
            throw new IOException("TYPE_CONTINUATION streamId changed");
        }

        @Override // spi.vr
        @NotNull
        public final dt timeout() {
            return this.f.timeout();
        }
    }

    public interface c {
        void a(int i, @NotNull List list) throws IOException;

        void b();

        void c(int i, long j);

        void d(@NotNull jp jpVar);

        void e(boolean z, int i, @NotNull List list);

        void f();

        void g(boolean z, int i, int i2);

        void h(int i, @NotNull ErrorCode errorCode);

        void i(boolean z, int i, @NotNull l2 l2Var, int i2) throws IOException;

        void j(int i, @NotNull ErrorCode errorCode, @NotNull ByteString byteString);
    }

    static {
        Logger logger = Logger.getLogger(qe.class.getName());
        Intrinsics.checkNotNullExpressionValue(logger, "Logger.getLogger(Http2::class.java.name)");
        e = logger;
    }

    public bf(@NotNull l2 source, boolean z) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.c = source;
        this.d = z;
        b bVar = new b(source);
        this.a = bVar;
        this.b = new oe.a(bVar, 4096, 0, 4, null);
    }

    public final void B(@NotNull c handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (this.d) {
            if (!e(true, handler)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        l2 l2Var = this.c;
        ByteString byteString = qe.a;
        ByteString byteStringB = l2Var.b(byteString.size());
        Logger logger = e;
        if (logger.isLoggable(Level.FINE)) {
            StringBuilder sbK = e.k("<< CONNECTION ");
            sbK.append(byteStringB.hex());
            logger.fine(uu.h(sbK.toString(), new Object[0]));
        }
        if (!Intrinsics.areEqual(byteString, byteStringB)) {
            StringBuilder sbK2 = e.k("Expected a connection header but was ");
            sbK2.append(byteStringB.utf8());
            throw new IOException(sbK2.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.ArrayList, java.util.List<spi.ie>] */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.util.ArrayList, java.util.List<spi.ie>] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.util.ArrayList, java.util.List<spi.ie>] */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.util.ArrayList, java.util.List<spi.ie>] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.util.ArrayList, java.util.List<spi.ie>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<spi.ie> C(int r3, int r4, int r5, int r6) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.bf.C(int, int, int, int):java.util.List");
    }

    public final void D(c cVar, int i) throws IOException {
        this.c.readInt();
        this.c.readByte();
        byte[] bArr = uu.a;
        cVar.f();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.c.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x01bc, code lost:
    
        throw new java.io.IOException(spi.e.c("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ", r11));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e(boolean r17, @org.jetbrains.annotations.NotNull spi.bf.c r18) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.bf.e(boolean, spi.bf$c):boolean");
    }
}
