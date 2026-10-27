package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.a.k2.c;
import g.a.a.l2.b;
import g.a.a.m;
import g.a.a.n2.a;
import g.a.a.p2.n;
import g.a.b.j0.r0;
import g.a.b.l;
import g.a.f.d;
import g.a.f.i;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.KeyAgreementSpi;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseAgreementSpi extends KeyAgreementSpi {
    private static final Map<String, m> defaultOids;
    private static final Hashtable des;
    private static final Map<String, Integer> keySizes;
    private static final Map<String, String> nameTable;
    private static final Hashtable oids;
    public final String kaAlgorithm;
    public final l kdf;
    public byte[] ukmParameters;

    static {
        HashMap map = new HashMap();
        defaultOids = map;
        HashMap map2 = new HashMap();
        keySizes = map2;
        HashMap map3 = new HashMap();
        nameTable = map3;
        oids = new Hashtable();
        des = new Hashtable();
        Integer numB = d.b(64);
        Integer numB2 = d.b(128);
        Integer numB3 = d.b(192);
        Integer numB4 = d.b(256);
        map2.put("DES", numB);
        map2.put("DESEDE", numB3);
        map2.put("BLOWFISH", numB2);
        map2.put("AES", numB4);
        map2.put(b.t.r(), numB2);
        map2.put(b.B.r(), numB3);
        map2.put(b.J.r(), numB4);
        map2.put(b.u.r(), numB2);
        map2.put(b.C.r(), numB3);
        m mVar = b.K;
        map2.put(mVar.r(), numB4);
        map2.put(b.w.r(), numB2);
        map2.put(b.E.r(), numB3);
        map2.put(b.M.r(), numB4);
        map2.put(b.v.r(), numB2);
        map2.put(b.D.r(), numB3);
        map2.put(b.L.r(), numB4);
        m mVar2 = b.x;
        map2.put(mVar2.r(), numB2);
        map2.put(b.F.r(), numB3);
        map2.put(b.N.r(), numB4);
        m mVar3 = b.z;
        map2.put(mVar3.r(), numB2);
        map2.put(b.H.r(), numB3);
        map2.put(b.P.r(), numB4);
        map2.put(b.y.r(), numB2);
        map2.put(b.G.r(), numB3);
        map2.put(b.O.r(), numB4);
        m mVar4 = a.f3934d;
        map2.put(mVar4.r(), numB2);
        m mVar5 = a.f3935e;
        map2.put(mVar5.r(), numB3);
        m mVar6 = a.f3936f;
        map2.put(mVar6.r(), numB4);
        m mVar7 = g.a.a.j2.a.f3883d;
        map2.put(mVar7.r(), numB2);
        m mVar8 = n.Q1;
        map2.put(mVar8.r(), numB3);
        m mVar9 = n.J;
        map2.put(mVar9.r(), numB3);
        m mVar10 = g.a.a.o2.b.f3944e;
        map2.put(mVar10.r(), numB);
        m mVar11 = g.a.a.d2.a.f3798f;
        map2.put(mVar11.r(), numB4);
        map2.put(g.a.a.d2.a.f3796d.r(), numB4);
        map2.put(g.a.a.d2.a.f3797e.r(), numB4);
        m mVar12 = n.Q;
        map2.put(mVar12.r(), d.b(160));
        m mVar13 = n.S;
        map2.put(mVar13.r(), numB4);
        m mVar14 = n.T;
        map2.put(mVar14.r(), d.b(384));
        m mVar15 = n.U;
        map2.put(mVar15.r(), d.b(512));
        map.put("DESEDE", mVar9);
        map.put("AES", mVar);
        m mVar16 = a.f3933c;
        map.put("CAMELLIA", mVar16);
        m mVar17 = g.a.a.j2.a.f3880a;
        map.put("SEED", mVar17);
        map.put("DES", mVar10);
        map3.put(c.u.r(), "CAST5");
        map3.put(c.v.r(), "IDEA");
        map3.put(c.y.r(), "Blowfish");
        map3.put(c.z.r(), "Blowfish");
        map3.put(c.A.r(), "Blowfish");
        map3.put(c.B.r(), "Blowfish");
        map3.put(g.a.a.o2.b.f3943d.r(), "DES");
        map3.put(mVar10.r(), "DES");
        map3.put(g.a.a.o2.b.f3946g.r(), "DES");
        map3.put(g.a.a.o2.b.f3945f.r(), "DES");
        map3.put(g.a.a.o2.b.f3947h.r(), "DESede");
        map3.put(mVar9.r(), "DESede");
        map3.put(mVar8.r(), "DESede");
        map3.put(n.R1.r(), "RC2");
        Map<String, String> map4 = nameTable;
        map4.put(mVar12.r(), "HmacSHA1");
        map4.put(n.R.r(), "HmacSHA224");
        map4.put(mVar13.r(), "HmacSHA256");
        map4.put(mVar14.r(), "HmacSHA384");
        map4.put(mVar15.r(), "HmacSHA512");
        map4.put(a.f3931a.r(), "Camellia");
        map4.put(a.f3932b.r(), "Camellia");
        map4.put(mVar16.r(), "Camellia");
        map4.put(mVar4.r(), "Camellia");
        map4.put(mVar5.r(), "Camellia");
        map4.put(mVar6.r(), "Camellia");
        map4.put(mVar7.r(), "SEED");
        map4.put(mVar17.r(), "SEED");
        map4.put(g.a.a.j2.a.f3881b.r(), "SEED");
        map4.put(mVar11.r(), "GOST28147");
        map4.put(mVar2.r(), "AES");
        map4.put(mVar3.r(), "AES");
        map4.put(mVar3.r(), "AES");
        Hashtable hashtable = oids;
        hashtable.put("DESEDE", mVar9);
        hashtable.put("AES", mVar);
        hashtable.put("DES", mVar10);
        Hashtable hashtable2 = des;
        hashtable2.put("DES", "DES");
        hashtable2.put("DESEDE", "DES");
        hashtable2.put(mVar10.r(), "DES");
        hashtable2.put(mVar9.r(), "DES");
        hashtable2.put(mVar8.r(), "DES");
    }

    public BaseAgreementSpi(String str, l lVar) {
        this.kaAlgorithm = str;
        this.kdf = lVar;
    }

    public static String getAlgorithm(String str) {
        if (str.indexOf(91) > 0) {
            return str.substring(0, str.indexOf(91));
        }
        if (str.startsWith(b.s.r())) {
            return "AES";
        }
        if (str.startsWith(g.a.a.g2.a.f3854i.r())) {
            return "Serpent";
        }
        String str2 = nameTable.get(i.k(str));
        return str2 != null ? str2 : str;
    }

    public static int getKeySize(String str) {
        if (str.indexOf(91) > 0) {
            return Integer.parseInt(str.substring(str.indexOf(91) + 1, str.indexOf(93)));
        }
        String strK = i.k(str);
        Map<String, Integer> map = keySizes;
        if (map.containsKey(strK)) {
            return map.get(strK).intValue();
        }
        return -1;
    }

    private byte[] getSharedSecretBytes(byte[] bArr, String str, int i2) throws NoSuchAlgorithmException {
        g.a.b.m r0Var;
        l lVar = this.kdf;
        if (lVar == null) {
            if (i2 <= 0) {
                return bArr;
            }
            int i3 = i2 / 8;
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, 0, bArr2, 0, i3);
            g.a.f.a.e(bArr);
            return bArr2;
        }
        if (i2 < 0) {
            throw new NoSuchAlgorithmException("unknown algorithm encountered: " + str);
        }
        int i4 = i2 / 8;
        byte[] bArr3 = new byte[i4];
        if (!(lVar instanceof g.a.b.z.i.c)) {
            r0Var = new r0(bArr, this.ukmParameters);
        } else {
            if (str == null) {
                throw new NoSuchAlgorithmException("algorithm OID is null");
            }
            try {
                r0Var = new g.a.b.z.i.b(new m(str), i2, bArr, this.ukmParameters);
            } catch (IllegalArgumentException unused) {
                throw new NoSuchAlgorithmException("no OID for algorithm: " + str);
            }
        }
        this.kdf.a(r0Var);
        this.kdf.b(bArr3, 0, i4);
        g.a.f.a.e(bArr);
        return bArr3;
    }

    public static byte[] trimZeroes(byte[] bArr) {
        if (bArr[0] != 0) {
            return bArr;
        }
        int i2 = 0;
        while (i2 < bArr.length && bArr[i2] == 0) {
            i2++;
        }
        int length = bArr.length - i2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i2, bArr2, 0, length);
        return bArr2;
    }

    public abstract byte[] calcSecret();

    @Override // javax.crypto.KeyAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i2) throws IllegalStateException, ShortBufferException {
        byte[] bArrEngineGenerateSecret = engineGenerateSecret();
        if (bArr.length - i2 >= bArrEngineGenerateSecret.length) {
            System.arraycopy(bArrEngineGenerateSecret, 0, bArr, i2, bArrEngineGenerateSecret.length);
            return bArrEngineGenerateSecret.length;
        }
        throw new ShortBufferException(this.kaAlgorithm + " key agreement: need " + bArrEngineGenerateSecret.length + " bytes");
    }

    @Override // javax.crypto.KeyAgreementSpi
    public SecretKey engineGenerateSecret(String str) throws NoSuchAlgorithmException {
        String strK = i.k(str);
        Hashtable hashtable = oids;
        String strR = hashtable.containsKey(strK) ? ((m) hashtable.get(strK)).r() : str;
        byte[] sharedSecretBytes = getSharedSecretBytes(calcSecret(), strR, getKeySize(strR));
        String algorithm = getAlgorithm(str);
        if (des.containsKey(algorithm)) {
            g.a.b.j0.c.c(sharedSecretBytes);
        }
        return new SecretKeySpec(sharedSecretBytes, algorithm);
    }

    @Override // javax.crypto.KeyAgreementSpi
    public byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.kdf == null) {
            return calcSecret();
        }
        try {
            return getSharedSecretBytes(calcSecret(), null, calcSecret().length * 8);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2.getMessage());
        }
    }
}
