package spi;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import okhttp3.Protocol;
import okhttp3.TlsVersion;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.g2.a;
import spi.ke;
import spi.l6;
import spi.zk;

/* JADX INFO: loaded from: classes2.dex */
public final class w2 implements Closeable, Flushable {
    public static final b b = new b(null);

    @NotNull
    public final l6 a;

    public static final class a extends mn {
        public final ul a;

        @NotNull
        public final l6.f b;
        public final String c;
        public final String d;

        /* JADX INFO: renamed from: spi.w2$a$a, reason: collision with other inner class name */
        public static final class C0144a extends ic {
            public final /* synthetic */ vr b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0144a(vr vrVar, vr vrVar2) {
                super(vrVar2);
                this.b = vrVar;
            }

            @Override // spi.ic, spi.vr, java.io.Closeable, java.lang.AutoCloseable
            public final void close() throws IOException {
                a.this.b.close();
                super.close();
            }
        }

        public a(@NotNull l6.f snapshot, @Nullable String str, @Nullable String str2) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            this.b = snapshot;
            this.c = str;
            this.d = str2;
            vr vrVar = snapshot.c.get(1);
            this.a = (ul) spi.b.c(new C0144a(vrVar, vrVar));
        }

        @Override // spi.mn
        public final long contentLength() {
            String toLongOrDefault = this.d;
            if (toLongOrDefault == null) {
                return -1L;
            }
            byte[] bArr = uu.a;
            Intrinsics.checkNotNullParameter(toLongOrDefault, "$this$toLongOrDefault");
            try {
                return Long.parseLong(toLongOrDefault);
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        @Override // spi.mn
        @Nullable
        public final mi contentType() {
            String str = this.c;
            if (str != null) {
                return mi.f.b(str);
            }
            return null;
        }

        @Override // spi.mn
        @NotNull
        public final l2 source() {
            return this.a;
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final String a(@NotNull hf url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return ByteString.INSTANCE.d(url.j).md5().hex();
        }

        public final int b(@NotNull l2 source) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            try {
                ul ulVar = (ul) source;
                long jB = ulVar.B();
                String strR = ulVar.r();
                if (jB >= 0 && jB <= Integer.MAX_VALUE) {
                    if (!(strR.length() > 0)) {
                        return (int) jB;
                    }
                }
                throw new IOException("expected an int but was \"" + jB + strR + Typography.quote);
            } catch (NumberFormatException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final Set<String> c(ke keVar) {
            int length = keVar.a.length / 2;
            TreeSet treeSet = null;
            for (int i = 0; i < length; i++) {
                if (StringsKt.h("Vary", keVar.b(i))) {
                    String strD = keVar.d(i);
                    if (treeSet == null) {
                        treeSet = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
                    }
                    for (String str : StringsKt__StringsKt.split$default(strD, new char[]{','}, false, 0, 6, (Object) null)) {
                        Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.CharSequence");
                        treeSet.add(StringsKt.trim((CharSequence) str).toString());
                    }
                }
            }
            return treeSet != null ? treeSet : SetsKt.emptySet();
        }
    }

    public final class d implements a3 {
        public final rp a;
        public final a b;
        public boolean c;
        public final l6.a d;
        public final /* synthetic */ w2 e;

        public static final class a extends hc {
            public a(rp rpVar) {
                super(rpVar);
            }

            @Override // spi.hc, spi.rp, java.io.Closeable, java.lang.AutoCloseable
            public final void close() throws IOException {
                synchronized (d.this.e) {
                    d dVar = d.this;
                    if (dVar.c) {
                        return;
                    }
                    dVar.c = true;
                    Objects.requireNonNull(dVar.e);
                    super.close();
                    d.this.d.b();
                }
            }
        }

        public d(@NotNull w2 w2Var, l6.a editor) {
            Intrinsics.checkNotNullParameter(editor, "editor");
            this.e = w2Var;
            this.d = editor;
            rp rpVarD = editor.d(1);
            this.a = rpVarD;
            this.b = new a(rpVarD);
        }

        @Override // spi.a3
        public final void a() {
            synchronized (this.e) {
                if (this.c) {
                    return;
                }
                this.c = true;
                Objects.requireNonNull(this.e);
                uu.c(this.a);
                try {
                    this.d.a();
                } catch (IOException unused) {
                }
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public w2(@NotNull File directory, long j) {
        this(directory, j, yb.a);
        Intrinsics.checkNotNullParameter(directory, "directory");
    }

    public w2(@NotNull File directory, long j, @NotNull yb fileSystem) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        this.a = new l6(fileSystem, directory, 201105, 2, j, ws.h);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
    }

    public final void e(@NotNull im request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        l6 l6Var = this.a;
        String key = b.a(request.b);
        synchronized (l6Var) {
            Intrinsics.checkNotNullParameter(key, "key");
            l6Var.E();
            l6Var.e();
            l6Var.N(key);
            l6.e eVar = l6Var.g.get(key);
            if (eVar != null) {
                Intrinsics.checkNotNullExpressionValue(eVar, "lruEntries[key] ?: return false");
                l6Var.L(eVar);
                if (l6Var.e <= l6Var.a) {
                    l6Var.m = false;
                }
            }
        }
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        this.a.flush();
    }

    public static final class c {
        public static final String k;
        public static final String l;
        public final String a;
        public final ke b;
        public final String c;
        public final Protocol d;
        public final int e;
        public final String f;
        public final ke g;
        public final ge h;
        public final long i;
        public final long j;

        public static final class a {
            private a() {
            }

            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            new a(null);
            zk.a aVar = zk.c;
            Objects.requireNonNull(aVar);
            Objects.requireNonNull(zk.a);
            k = "OkHttp-Sent-Millis";
            Objects.requireNonNull(aVar);
            Objects.requireNonNull(zk.a);
            l = "OkHttp-Received-Millis";
        }

        public c(@NotNull ln varyHeaders) {
            ke keVarD;
            Intrinsics.checkNotNullParameter(varyHeaders, "response");
            this.a = varyHeaders.b.b.j;
            b bVar = w2.b;
            Objects.requireNonNull(bVar);
            Intrinsics.checkNotNullParameter(varyHeaders, "$this$varyHeaders");
            ln lnVar = varyHeaders.i;
            Intrinsics.checkNotNull(lnVar);
            ke keVar = lnVar.b.d;
            Set<String> setC = bVar.c(varyHeaders.g);
            if (setC.isEmpty()) {
                keVarD = uu.b;
            } else {
                ke.a aVar = new ke.a();
                int length = keVar.a.length / 2;
                for (int i = 0; i < length; i++) {
                    String strB = keVar.b(i);
                    if (setC.contains(strB)) {
                        aVar.a(strB, keVar.d(i));
                    }
                }
                keVarD = aVar.d();
            }
            this.b = keVarD;
            this.c = varyHeaders.b.c;
            this.d = varyHeaders.c;
            this.e = varyHeaders.e;
            this.f = varyHeaders.d;
            this.g = varyHeaders.g;
            this.h = varyHeaders.f;
            this.i = varyHeaders.l;
            this.j = varyHeaders.m;
        }

        public final List<Certificate> a(l2 l2Var) throws IOException {
            int iB = w2.b.b(l2Var);
            if (iB == -1) {
                return CollectionsKt.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(iB);
                for (int i = 0; i < iB; i++) {
                    String strR = ((ul) l2Var).r();
                    g2 g2Var = new g2();
                    ByteString byteStringA = ByteString.INSTANCE.a(strR);
                    Intrinsics.checkNotNull(byteStringA);
                    g2Var.P(byteStringA);
                    arrayList.add(certificateFactory.generateCertificate(g2Var.new a()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final void b(k2 k2Var, List<? extends Certificate> list) throws IOException {
            try {
                tl tlVar = (tl) k2Var;
                tlVar.y(list.size());
                tlVar.i(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    byte[] bytes = list.get(i).getEncoded();
                    ByteString.Companion companion = ByteString.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
                    tlVar.l(companion.e(bytes, 0, bytes.length).base64());
                    tlVar.i(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final void c(@NotNull l6.a editor) throws IOException {
            Intrinsics.checkNotNullParameter(editor, "editor");
            k2 k2VarB = spi.b.b(editor.d(0));
            try {
                tl tlVar = (tl) k2VarB;
                tlVar.l(this.a);
                tlVar.i(10);
                tlVar.l(this.c);
                tlVar.i(10);
                tlVar.y(this.b.a.length / 2);
                tlVar.i(10);
                int length = this.b.a.length / 2;
                for (int i = 0; i < length; i++) {
                    tlVar.l(this.b.b(i));
                    tlVar.l(": ");
                    tlVar.l(this.b.d(i));
                    tlVar.i(10);
                }
                tlVar.l(new fs(this.d, this.e, this.f).toString());
                tlVar.i(10);
                tlVar.y((this.g.a.length / 2) + 2);
                tlVar.i(10);
                int length2 = this.g.a.length / 2;
                for (int i2 = 0; i2 < length2; i2++) {
                    tlVar.l(this.g.b(i2));
                    tlVar.l(": ");
                    tlVar.l(this.g.d(i2));
                    tlVar.i(10);
                }
                tlVar.l(k);
                tlVar.l(": ");
                tlVar.y(this.i);
                tlVar.i(10);
                tlVar.l(l);
                tlVar.l(": ");
                tlVar.y(this.j);
                tlVar.i(10);
                if (StringsKt.x(this.a, "https://")) {
                    tlVar.i(10);
                    ge geVar = this.h;
                    Intrinsics.checkNotNull(geVar);
                    tlVar.l(geVar.c.a);
                    tlVar.i(10);
                    b(k2VarB, this.h.b());
                    b(k2VarB, this.h.d);
                    tlVar.l(this.h.b.javaName());
                    tlVar.i(10);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(k2VarB, null);
            } finally {
            }
        }

        public c(@NotNull vr rawSource) throws IOException {
            Intrinsics.checkNotNullParameter(rawSource, "rawSource");
            try {
                l2 l2VarC = spi.b.c(rawSource);
                ul ulVar = (ul) l2VarC;
                this.a = ulVar.r();
                this.c = ulVar.r();
                ke.a aVar = new ke.a();
                int iB = w2.b.b(l2VarC);
                for (int i = 0; i < iB; i++) {
                    aVar.b(ulVar.r());
                }
                this.b = aVar.d();
                fs fsVarA = fs.d.a(ulVar.r());
                this.d = fsVarA.a;
                this.e = fsVarA.b;
                this.f = fsVarA.c;
                ke.a aVar2 = new ke.a();
                int iB2 = w2.b.b(l2VarC);
                for (int i2 = 0; i2 < iB2; i2++) {
                    aVar2.b(ulVar.r());
                }
                String str = k;
                String strE = aVar2.e(str);
                String str2 = l;
                String strE2 = aVar2.e(str2);
                aVar2.f(str);
                aVar2.f(str2);
                this.i = strE != null ? Long.parseLong(strE) : 0L;
                this.j = strE2 != null ? Long.parseLong(strE2) : 0L;
                this.g = aVar2.d();
                if (StringsKt.x(this.a, "https://")) {
                    String strR = ulVar.r();
                    if (strR.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + strR + Typography.quote);
                    }
                    this.h = ge.e.b(!ulVar.h() ? TlsVersion.INSTANCE.a(ulVar.r()) : TlsVersion.SSL_3_0, p3.t.b(ulVar.r()), a(l2VarC), a(l2VarC));
                } else {
                    this.h = null;
                }
            } finally {
                rawSource.close();
            }
        }
    }
}
