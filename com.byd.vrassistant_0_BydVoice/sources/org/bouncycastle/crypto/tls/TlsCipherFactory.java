package org.bouncycastle.crypto.tls;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface TlsCipherFactory {
    TlsCipher createCipher(TlsClientContext tlsClientContext, int i, int i2) throws IOException;
}
