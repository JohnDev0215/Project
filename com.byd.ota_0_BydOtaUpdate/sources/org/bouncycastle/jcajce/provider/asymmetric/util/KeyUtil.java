package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.a.e;
import g.a.a.p2.p;
import g.a.a.w2.a;
import g.a.a.w2.u;

/* JADX INFO: loaded from: classes.dex */
public class KeyUtil {
    public static byte[] getEncodedPrivateKeyInfo(p pVar) {
        try {
            return pVar.f("DER");
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedPrivateKeyInfo(a aVar, e eVar) {
        try {
            return getEncodedPrivateKeyInfo(new p(aVar, eVar.c()));
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(a aVar, e eVar) {
        try {
            return getEncodedSubjectPublicKeyInfo(new u(aVar, eVar));
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(a aVar, byte[] bArr) {
        try {
            return getEncodedSubjectPublicKeyInfo(new u(aVar, bArr));
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(u uVar) {
        try {
            return uVar.f("DER");
        } catch (Exception unused) {
            return null;
        }
    }
}
