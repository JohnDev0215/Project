package spi;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.TlsVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ge {
    public static final a e = new a(null);

    @NotNull
    public final Lazy a;

    @NotNull
    public final TlsVersion b;

    @NotNull
    public final p3 c;

    @NotNull
    public final List<Certificate> d;

    public static final class a {

        /* JADX INFO: renamed from: spi.ge$a$a, reason: collision with other inner class name */
        public static final class C0125a extends Lambda implements Function0<List<? extends Certificate>> {
            public final /* synthetic */ List a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0125a(List list) {
                super(0);
                this.a = list;
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends Certificate> invoke() {
                return this.a;
            }
        }

        public static final class b extends Lambda implements Function0<List<? extends Certificate>> {
            public final /* synthetic */ List a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(List list) {
                super(0);
                this.a = list;
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends Certificate> invoke() {
                return this.a;
            }
        }

        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @JvmName(name = "get")
        @NotNull
        public final ge a(@NotNull SSLSession handshake) throws IOException {
            List listEmptyList;
            Intrinsics.checkNotNullParameter(handshake, "$this$handshake");
            String cipherSuite = handshake.getCipherSuite();
            if (cipherSuite == null) {
                throw new IllegalStateException("cipherSuite == null".toString());
            }
            int iHashCode = cipherSuite.hashCode();
            if (iHashCode == 1019404634 ? cipherSuite.equals("TLS_NULL_WITH_NULL_NULL") : iHashCode == 1208658923 && cipherSuite.equals("SSL_NULL_WITH_NULL_NULL")) {
                throw new IOException(d.f("cipherSuite == ", cipherSuite));
            }
            p3 p3VarB = p3.t.b(cipherSuite);
            String protocol = handshake.getProtocol();
            if (protocol == null) {
                throw new IllegalStateException("tlsVersion == null".toString());
            }
            if (Intrinsics.areEqual("NONE", protocol)) {
                throw new IOException("tlsVersion == NONE");
            }
            TlsVersion tlsVersionA = TlsVersion.INSTANCE.a(protocol);
            try {
                Certificate[] peerCertificates = handshake.getPeerCertificates();
                listEmptyList = peerCertificates != null ? uu.k((Certificate[]) Arrays.copyOf(peerCertificates, peerCertificates.length)) : CollectionsKt.emptyList();
            } catch (SSLPeerUnverifiedException unused) {
                listEmptyList = CollectionsKt.emptyList();
            }
            Certificate[] localCertificates = handshake.getLocalCertificates();
            return new ge(tlsVersionA, p3VarB, localCertificates != null ? uu.k((Certificate[]) Arrays.copyOf(localCertificates, localCertificates.length)) : CollectionsKt.emptyList(), new b(listEmptyList));
        }

        @JvmStatic
        @NotNull
        public final ge b(@NotNull TlsVersion tlsVersion, @NotNull p3 cipherSuite, @NotNull List<? extends Certificate> peerCertificates, @NotNull List<? extends Certificate> localCertificates) {
            Intrinsics.checkNotNullParameter(tlsVersion, "tlsVersion");
            Intrinsics.checkNotNullParameter(cipherSuite, "cipherSuite");
            Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
            Intrinsics.checkNotNullParameter(localCertificates, "localCertificates");
            return new ge(tlsVersion, cipherSuite, uu.x(localCertificates), new C0125a(uu.x(peerCertificates)));
        }
    }

    public static final class b extends Lambda implements Function0<List<? extends Certificate>> {
        public final /* synthetic */ Function0 a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Function0 function0) {
            super(0);
            this.a = function0;
        }

        @Override // kotlin.jvm.functions.Function0
        public final List<? extends Certificate> invoke() {
            try {
                return (List) this.a.invoke();
            } catch (SSLPeerUnverifiedException unused) {
                return CollectionsKt.emptyList();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ge(@NotNull TlsVersion tlsVersion, @NotNull p3 cipherSuite, @NotNull List<? extends Certificate> localCertificates, @NotNull Function0<? extends List<? extends Certificate>> peerCertificatesFn) {
        Intrinsics.checkNotNullParameter(tlsVersion, "tlsVersion");
        Intrinsics.checkNotNullParameter(cipherSuite, "cipherSuite");
        Intrinsics.checkNotNullParameter(localCertificates, "localCertificates");
        Intrinsics.checkNotNullParameter(peerCertificatesFn, "peerCertificatesFn");
        this.b = tlsVersion;
        this.c = cipherSuite;
        this.d = localCertificates;
        this.a = LazyKt.lazy(new b(peerCertificatesFn));
    }

    public final String a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return ((X509Certificate) certificate).getSubjectDN().toString();
        }
        String type = certificate.getType();
        Intrinsics.checkNotNullExpressionValue(type, "type");
        return type;
    }

    @JvmName(name = "peerCertificates")
    @NotNull
    public final List<Certificate> b() {
        return (List) this.a.getValue();
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof ge) {
            ge geVar = (ge) obj;
            if (geVar.b == this.b && Intrinsics.areEqual(geVar.c, this.c) && Intrinsics.areEqual(geVar.b(), b()) && Intrinsics.areEqual(geVar.d, this.d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() + ((b().hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + 527) * 31)) * 31)) * 31);
    }

    @NotNull
    public final String toString() {
        List<Certificate> listB = b();
        ArrayList arrayList = new ArrayList(CollectionsKt.b(listB));
        Iterator<T> it = listB.iterator();
        while (it.hasNext()) {
            arrayList.add(a((Certificate) it.next()));
        }
        String string = arrayList.toString();
        StringBuilder sbM = e.m("Handshake{", "tlsVersion=");
        sbM.append(this.b);
        sbM.append(' ');
        sbM.append("cipherSuite=");
        sbM.append(this.c);
        sbM.append(' ');
        sbM.append("peerCertificates=");
        sbM.append(string);
        sbM.append(' ');
        sbM.append("localCertificates=");
        List<Certificate> list = this.d;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.b(list));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(a((Certificate) it2.next()));
        }
        sbM.append(arrayList2);
        sbM.append('}');
        return sbM.toString();
    }
}
