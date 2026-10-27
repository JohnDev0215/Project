package org.bouncycastle.jcajce;

import g.a.b.g;
import g.a.f.a;

/* JADX INFO: loaded from: classes.dex */
public class PBKDF2Key implements PBKDFKey {
    private final g converter;
    private final char[] password;

    public PBKDF2Key(char[] cArr, g gVar) {
        this.password = a.h(cArr);
        this.converter = gVar;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "PBKDF2";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.converter.convert(this.password);
    }

    @Override // java.security.Key
    public String getFormat() {
        return this.converter.getType();
    }

    public char[] getPassword() {
        return this.password;
    }
}
