package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes4.dex */
public interface TlsClientContext {
    SecureRandom getSecureRandom();

    SecurityParameters getSecurityParameters();

    Object getUserObject();

    void setUserObject(Object obj);
}
