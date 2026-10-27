package spi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.Deprecated;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.TlsVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class d4 {

    @JvmField
    @NotNull
    public static final d4 e;

    @JvmField
    @NotNull
    public static final d4 f;
    public final boolean a;
    public final boolean b;
    public final String[] c;
    public final String[] d;

    public static final class a {
        public boolean a;

        @Nullable
        public String[] b;

        @Nullable
        public String[] c;
        public boolean d;

        public a(@NotNull d4 connectionSpec) {
            Intrinsics.checkNotNullParameter(connectionSpec, "connectionSpec");
            this.a = connectionSpec.a;
            this.b = connectionSpec.c;
            this.c = connectionSpec.d;
            this.d = connectionSpec.b;
        }

        public a(boolean z) {
            this.a = z;
        }

        @NotNull
        public final d4 a() {
            return new d4(this.a, this.d, this.b, this.c);
        }

        @NotNull
        public final a b(@NotNull String... cipherSuites) throws CloneNotSupportedException {
            Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
            if (!this.a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            if (!(!(cipherSuites.length == 0))) {
                throw new IllegalArgumentException("At least one cipher suite is required".toString());
            }
            Object objClone = cipherSuites.clone();
            Objects.requireNonNull(objClone, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            this.b = (String[]) objClone;
            return this;
        }

        @NotNull
        public final a c(@NotNull p3... cipherSuites) throws CloneNotSupportedException {
            Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
            if (!this.a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(cipherSuites.length);
            for (p3 p3Var : cipherSuites) {
                arrayList.add(p3Var.a);
            }
            Object[] array = arrayList.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            String[] strArr = (String[]) array;
            b((String[]) Arrays.copyOf(strArr, strArr.length));
            return this;
        }

        @Deprecated(message = "since OkHttp 3.13 all TLS-connections are expected to support TLS extensions.\nIn a future release setting this to true will be unnecessary and setting it to false\nwill have no effect.")
        @NotNull
        public final a d() {
            if (!this.a) {
                throw new IllegalArgumentException("no TLS extensions for cleartext connections".toString());
            }
            this.d = true;
            return this;
        }

        @NotNull
        public final a e(@NotNull String... tlsVersions) throws CloneNotSupportedException {
            Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
            if (!this.a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            if (!(!(tlsVersions.length == 0))) {
                throw new IllegalArgumentException("At least one TLS version is required".toString());
            }
            Object objClone = tlsVersions.clone();
            Objects.requireNonNull(objClone, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            this.c = (String[]) objClone;
            return this;
        }

        @NotNull
        public final a f(@NotNull TlsVersion... tlsVersions) throws CloneNotSupportedException {
            Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
            if (!this.a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(tlsVersions.length);
            for (TlsVersion tlsVersion : tlsVersions) {
                arrayList.add(tlsVersion.javaName());
            }
            Object[] array = arrayList.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            String[] strArr = (String[]) array;
            e((String[]) Arrays.copyOf(strArr, strArr.length));
            return this;
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
        p3 p3Var = p3.q;
        p3 p3Var2 = p3.r;
        p3 p3Var3 = p3.s;
        p3 p3Var4 = p3.k;
        p3 p3Var5 = p3.m;
        p3 p3Var6 = p3.l;
        p3 p3Var7 = p3.n;
        p3 p3Var8 = p3.p;
        p3 p3Var9 = p3.o;
        p3[] p3VarArr = {p3Var, p3Var2, p3Var3, p3Var4, p3Var5, p3Var6, p3Var7, p3Var8, p3Var9};
        p3[] p3VarArr2 = {p3Var, p3Var2, p3Var3, p3Var4, p3Var5, p3Var6, p3Var7, p3Var8, p3Var9, p3.i, p3.j, p3.g, p3.h, p3.e, p3.f, p3.d};
        a aVar = new a(true);
        aVar.c((p3[]) Arrays.copyOf(p3VarArr, 9));
        TlsVersion tlsVersion = TlsVersion.TLS_1_3;
        TlsVersion tlsVersion2 = TlsVersion.TLS_1_2;
        aVar.f(tlsVersion, tlsVersion2);
        aVar.d();
        aVar.a();
        a aVar2 = new a(true);
        aVar2.c((p3[]) Arrays.copyOf(p3VarArr2, 16));
        aVar2.f(tlsVersion, tlsVersion2);
        aVar2.d();
        e = aVar2.a();
        a aVar3 = new a(true);
        aVar3.c((p3[]) Arrays.copyOf(p3VarArr2, 16));
        aVar3.f(tlsVersion, tlsVersion2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        aVar3.d();
        aVar3.a();
        f = new a(false).a();
    }

    public d4(boolean z, boolean z2, @Nullable String[] strArr, @Nullable String[] strArr2) {
        this.a = z;
        this.b = z2;
        this.c = strArr;
        this.d = strArr2;
    }

    @JvmName(name = "cipherSuites")
    @Nullable
    public final List<p3> a() {
        String[] strArr = this.c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(p3.t.b(str));
        }
        return CollectionsKt.toList(arrayList);
    }

    public final boolean b(@NotNull SSLSocket socket) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        if (!this.a) {
            return false;
        }
        String[] strArr = this.d;
        if (strArr != null && !uu.i(strArr, socket.getEnabledProtocols(), ComparisonsKt.naturalOrder())) {
            return false;
        }
        String[] strArr2 = this.c;
        if (strArr2 == null) {
            return true;
        }
        String[] enabledCipherSuites = socket.getEnabledCipherSuites();
        Objects.requireNonNull(p3.t);
        return uu.i(strArr2, enabledCipherSuites, p3.b);
    }

    @JvmName(name = "tlsVersions")
    @Nullable
    public final List<TlsVersion> c() {
        String[] strArr = this.d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(TlsVersion.INSTANCE.a(str));
        }
        return CollectionsKt.toList(arrayList);
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof d4)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        boolean z = this.a;
        d4 d4Var = (d4) obj;
        if (z != d4Var.a) {
            return false;
        }
        return !z || (Arrays.equals(this.c, d4Var.c) && Arrays.equals(this.d, d4Var.d) && this.b == d4Var.b);
    }

    public final int hashCode() {
        if (!this.a) {
            return 17;
        }
        String[] strArr = this.c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.b ? 1 : 0);
    }

    @NotNull
    public final String toString() {
        if (!this.a) {
            return "ConnectionSpec()";
        }
        StringBuilder sbM = e.m("ConnectionSpec(", "cipherSuites=");
        sbM.append(Objects.toString(a(), "[all enabled]"));
        sbM.append(", ");
        sbM.append("tlsVersions=");
        sbM.append(Objects.toString(c(), "[all enabled]"));
        sbM.append(", ");
        sbM.append("supportsTlsExtensions=");
        sbM.append(this.b);
        sbM.append(')');
        return sbM.toString();
    }
}
