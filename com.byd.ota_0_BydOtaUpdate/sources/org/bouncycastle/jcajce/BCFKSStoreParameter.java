package org.bouncycastle.jcajce;

import g.a.b.n0.c;
import java.io.OutputStream;
import java.security.KeyStore;

/* JADX INFO: loaded from: classes.dex */
public class BCFKSStoreParameter implements KeyStore.LoadStoreParameter {
    private OutputStream out;
    private final KeyStore.ProtectionParameter protectionParameter;
    private final c storeConfig;

    public BCFKSStoreParameter(OutputStream outputStream, c cVar, KeyStore.ProtectionParameter protectionParameter) {
        this.out = outputStream;
        this.storeConfig = cVar;
        this.protectionParameter = protectionParameter;
    }

    public BCFKSStoreParameter(OutputStream outputStream, c cVar, char[] cArr) {
        this(outputStream, cVar, new KeyStore.PasswordProtection(cArr));
    }

    public OutputStream getOutputStream() {
        return this.out;
    }

    @Override // java.security.KeyStore.LoadStoreParameter
    public KeyStore.ProtectionParameter getProtectionParameter() {
        return this.protectionParameter;
    }

    public c getStorePBKDFConfig() {
        return this.storeConfig;
    }
}
