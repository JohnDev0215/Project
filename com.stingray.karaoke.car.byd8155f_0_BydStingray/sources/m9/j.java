package m9;

import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes.dex */
public final class j implements l {
    @Override // m9.l
    public final boolean a(SSLSocket sSLSocket) {
        boolean z6 = l9.h.d;
        return l9.f.b() && Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // m9.l
    public final n b(SSLSocket sSLSocket) {
        return new k();
    }
}
