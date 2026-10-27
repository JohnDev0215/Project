package org.bouncycastle.crypto.tls;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AbstractTlsCipherFactory implements TlsCipherFactory {
    @Override // org.bouncycastle.crypto.tls.TlsCipherFactory
    public TlsCipher createCipher(TlsContext tlsContext, int i, int i2) throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 80);
    }
}
