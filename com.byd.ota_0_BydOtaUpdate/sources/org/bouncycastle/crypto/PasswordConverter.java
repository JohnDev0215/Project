package org.bouncycastle.crypto;

import g.a.b.g;
import g.a.b.u;

/* JADX INFO: loaded from: classes.dex */
public enum PasswordConverter implements g {
    ASCII { // from class: org.bouncycastle.crypto.PasswordConverter.1
        @Override // org.bouncycastle.crypto.PasswordConverter, g.a.b.g
        public byte[] convert(char[] cArr) {
            return u.b(cArr);
        }

        @Override // org.bouncycastle.crypto.PasswordConverter, g.a.b.g
        public String getType() {
            return "ASCII";
        }
    },
    UTF8 { // from class: org.bouncycastle.crypto.PasswordConverter.2
        @Override // org.bouncycastle.crypto.PasswordConverter, g.a.b.g
        public byte[] convert(char[] cArr) {
            return u.c(cArr);
        }

        @Override // org.bouncycastle.crypto.PasswordConverter, g.a.b.g
        public String getType() {
            return "UTF8";
        }
    },
    PKCS12 { // from class: org.bouncycastle.crypto.PasswordConverter.3
        @Override // org.bouncycastle.crypto.PasswordConverter, g.a.b.g
        public byte[] convert(char[] cArr) {
            return u.a(cArr);
        }

        @Override // org.bouncycastle.crypto.PasswordConverter, g.a.b.g
        public String getType() {
            return "PKCS12";
        }
    };

    @Override // g.a.b.g
    public abstract /* synthetic */ byte[] convert(char[] cArr);

    @Override // g.a.b.g
    public abstract /* synthetic */ String getType();
}
