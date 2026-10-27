package spi;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.d4;

/* JADX INFO: loaded from: classes2.dex */
public final class e4 {
    public int a;
    public boolean b;
    public boolean c;
    public final List<d4> d;

    public e4(@NotNull List<d4> connectionSpecs) {
        Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
        this.d = connectionSpecs;
    }

    @NotNull
    public final d4 a(@NotNull SSLSocket sslSocket) throws IOException, CloneNotSupportedException {
        d4 d4Var;
        boolean z;
        String[] cipherSuitesIntersection;
        String[] tlsVersionsIntersection;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        int i = this.a;
        int size = this.d.size();
        while (true) {
            if (i >= size) {
                d4Var = null;
                break;
            }
            d4Var = this.d.get(i);
            if (d4Var.b(sslSocket)) {
                this.a = i + 1;
                break;
            }
            i++;
        }
        if (d4Var == null) {
            StringBuilder sbK = e.k("Unable to find acceptable protocols. isFallback=");
            sbK.append(this.c);
            sbK.append(',');
            sbK.append(" modes=");
            sbK.append(this.d);
            sbK.append(',');
            sbK.append(" supported protocols=");
            String[] enabledProtocols = sslSocket.getEnabledProtocols();
            Intrinsics.checkNotNull(enabledProtocols);
            String string = Arrays.toString(enabledProtocols);
            Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
            sbK.append(string);
            throw new UnknownServiceException(sbK.toString());
        }
        int i2 = this.a;
        int size2 = this.d.size();
        while (true) {
            if (i2 >= size2) {
                z = false;
                break;
            }
            if (this.d.get(i2).b(sslSocket)) {
                z = true;
                break;
            }
            i2++;
        }
        this.b = z;
        boolean z2 = this.c;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        if (d4Var.c != null) {
            String[] enabledCipherSuites = sslSocket.getEnabledCipherSuites();
            Intrinsics.checkNotNullExpressionValue(enabledCipherSuites, "sslSocket.enabledCipherSuites");
            String[] strArr = d4Var.c;
            Objects.requireNonNull(p3.t);
            cipherSuitesIntersection = uu.o(enabledCipherSuites, strArr, p3.b);
        } else {
            cipherSuitesIntersection = sslSocket.getEnabledCipherSuites();
        }
        if (d4Var.d != null) {
            String[] enabledProtocols2 = sslSocket.getEnabledProtocols();
            Intrinsics.checkNotNullExpressionValue(enabledProtocols2, "sslSocket.enabledProtocols");
            tlsVersionsIntersection = uu.o(enabledProtocols2, d4Var.d, ComparisonsKt.naturalOrder());
        } else {
            tlsVersionsIntersection = sslSocket.getEnabledProtocols();
        }
        String[] indexOf = sslSocket.getSupportedCipherSuites();
        Intrinsics.checkNotNullExpressionValue(indexOf, "supportedCipherSuites");
        Objects.requireNonNull(p3.t);
        Comparator<String> comparator = p3.b;
        byte[] bArr = uu.a;
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        Intrinsics.checkNotNullParameter("TLS_FALLBACK_SCSV", "value");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int length = indexOf.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                i3 = -1;
                break;
            }
            if (comparator.compare(indexOf[i3], "TLS_FALLBACK_SCSV") == 0) {
                break;
            }
            i3++;
        }
        if (z2 && i3 != -1) {
            Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
            String value = indexOf[i3];
            Intrinsics.checkNotNullExpressionValue(value, "supportedCipherSuites[indexOfFallbackScsv]");
            Intrinsics.checkNotNullParameter(cipherSuitesIntersection, "$this$concat");
            Intrinsics.checkNotNullParameter(value, "value");
            Object[] objArrCopyOf = Arrays.copyOf(cipherSuitesIntersection, cipherSuitesIntersection.length + 1);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
            cipherSuitesIntersection = (String[]) objArrCopyOf;
            cipherSuitesIntersection[ArraysKt.getLastIndex(cipherSuitesIntersection)] = value;
        }
        d4.a aVar = new d4.a(d4Var);
        Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection");
        aVar.b((String[]) Arrays.copyOf(cipherSuitesIntersection, cipherSuitesIntersection.length));
        Intrinsics.checkNotNullExpressionValue(tlsVersionsIntersection, "tlsVersionsIntersection");
        aVar.e((String[]) Arrays.copyOf(tlsVersionsIntersection, tlsVersionsIntersection.length));
        d4 d4VarA = aVar.a();
        if (d4VarA.c() != null) {
            sslSocket.setEnabledProtocols(d4VarA.d);
        }
        if (d4VarA.a() != null) {
            sslSocket.setEnabledCipherSuites(d4VarA.c);
        }
        return d4Var;
    }
}
