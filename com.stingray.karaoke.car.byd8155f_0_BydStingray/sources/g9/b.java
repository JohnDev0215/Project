package g9;

import c9.C1050k;
import c9.C1051l;
import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import p7.C2112b;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f15031a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15032b;
    public boolean c;
    public boolean d;

    public b(List list) {
        A7.m.f(list, "connectionSpecs");
        this.f15031a = list;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.Serializable, java.lang.String[]] */
    public final c9.n a(SSLSocket sSLSocket) throws UnknownServiceException {
        c9.n nVar;
        int i7;
        boolean z6;
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        int i10 = this.f15032b;
        List list = this.f15031a;
        int size = list.size();
        while (true) {
            if (i10 >= size) {
                nVar = null;
                break;
            }
            nVar = (c9.n) list.get(i10);
            if (nVar.b(sSLSocket)) {
                this.f15032b = i10 + 1;
                break;
            }
            i10++;
        }
        if (nVar == null) {
            StringBuilder sb = new StringBuilder("Unable to find acceptable protocols. isFallback=");
            sb.append(this.d);
            sb.append(", modes=");
            sb.append(list);
            sb.append(", supported protocols=");
            String[] enabledProtocols2 = sSLSocket.getEnabledProtocols();
            A7.m.c(enabledProtocols2);
            String string = Arrays.toString(enabledProtocols2);
            A7.m.e(string, "toString(this)");
            sb.append(string);
            throw new UnknownServiceException(sb.toString());
        }
        int i11 = this.f15032b;
        int size2 = list.size();
        while (true) {
            i7 = 0;
            if (i11 >= size2) {
                z6 = false;
                break;
            }
            if (((c9.n) list.get(i11)).b(sSLSocket)) {
                z6 = true;
                break;
            }
            i11++;
        }
        this.c = z6;
        boolean z10 = this.d;
        String[] strArr = nVar.c;
        if (strArr != null) {
            String[] enabledCipherSuites2 = sSLSocket.getEnabledCipherSuites();
            A7.m.e(enabledCipherSuites2, "sslSocket.enabledCipherSuites");
            enabledCipherSuites = d9.b.p(enabledCipherSuites2, strArr, C1051l.c);
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        ?? r32 = nVar.d;
        if (r32 != 0) {
            String[] enabledProtocols3 = sSLSocket.getEnabledProtocols();
            A7.m.e(enabledProtocols3, "sslSocket.enabledProtocols");
            enabledProtocols = d9.b.p(enabledProtocols3, r32, C2112b.f20944m);
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        A7.m.e(supportedCipherSuites, "supportedCipherSuites");
        C1050k c1050k = C1051l.c;
        byte[] bArr = d9.b.f13645a;
        int length = supportedCipherSuites.length;
        while (true) {
            if (i7 >= length) {
                i7 = -1;
                break;
            }
            if (c1050k.compare(supportedCipherSuites[i7], "TLS_FALLBACK_SCSV") == 0) {
                break;
            }
            i7++;
        }
        if (z10 && i7 != -1) {
            A7.m.e(enabledCipherSuites, "cipherSuitesIntersection");
            String str = supportedCipherSuites[i7];
            A7.m.e(str, "supportedCipherSuites[indexOfFallbackScsv]");
            Object[] objArrCopyOf = Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length + 1);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            enabledCipherSuites = (String[]) objArrCopyOf;
            enabledCipherSuites[enabledCipherSuites.length - 1] = str;
        }
        c9.m mVar = new c9.m();
        mVar.f12587a = nVar.f12591a;
        mVar.c = strArr;
        mVar.d = r32;
        mVar.f12588b = nVar.f12592b;
        A7.m.e(enabledCipherSuites, "cipherSuitesIntersection");
        mVar.c((String[]) Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length));
        A7.m.e(enabledProtocols, "tlsVersionsIntersection");
        mVar.e((String[]) Arrays.copyOf(enabledProtocols, enabledProtocols.length));
        c9.n nVarA = mVar.a();
        if (nVarA.c() != null) {
            sSLSocket.setEnabledProtocols(nVarA.d);
        }
        if (nVarA.a() != null) {
            sSLSocket.setEnabledCipherSuites(nVarA.c);
        }
        return nVar;
    }
}
