package org.bouncycastle.jcajce.provider.util;

import g.a.a.m;
import g.a.a.o2.b;
import g.a.a.p2.n;
import g.a.b.n0.a;
import g.a.f.i;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class DigestFactory {
    private static Set md5 = new HashSet();
    private static Set sha1 = new HashSet();
    private static Set sha224 = new HashSet();
    private static Set sha256 = new HashSet();
    private static Set sha384 = new HashSet();
    private static Set sha512 = new HashSet();
    private static Set sha512_224 = new HashSet();
    private static Set sha512_256 = new HashSet();
    private static Set sha3_224 = new HashSet();
    private static Set sha3_256 = new HashSet();
    private static Set sha3_384 = new HashSet();
    private static Set sha3_512 = new HashSet();
    private static Map oids = new HashMap();

    static {
        md5.add("MD5");
        Set set = md5;
        m mVar = n.P;
        set.add(mVar.r());
        sha1.add("SHA1");
        sha1.add("SHA-1");
        Set set2 = sha1;
        m mVar2 = b.f3948i;
        set2.add(mVar2.r());
        sha224.add("SHA224");
        sha224.add("SHA-224");
        Set set3 = sha224;
        m mVar3 = g.a.a.l2.b.f3912f;
        set3.add(mVar3.r());
        sha256.add("SHA256");
        sha256.add("SHA-256");
        Set set4 = sha256;
        m mVar4 = g.a.a.l2.b.f3909c;
        set4.add(mVar4.r());
        sha384.add("SHA384");
        sha384.add("SHA-384");
        Set set5 = sha384;
        m mVar5 = g.a.a.l2.b.f3910d;
        set5.add(mVar5.r());
        sha512.add("SHA512");
        sha512.add("SHA-512");
        Set set6 = sha512;
        m mVar6 = g.a.a.l2.b.f3911e;
        set6.add(mVar6.r());
        sha512_224.add("SHA512(224)");
        sha512_224.add("SHA-512(224)");
        Set set7 = sha512_224;
        m mVar7 = g.a.a.l2.b.f3913g;
        set7.add(mVar7.r());
        sha512_256.add("SHA512(256)");
        sha512_256.add("SHA-512(256)");
        Set set8 = sha512_256;
        m mVar8 = g.a.a.l2.b.f3914h;
        set8.add(mVar8.r());
        sha3_224.add("SHA3-224");
        Set set9 = sha3_224;
        m mVar9 = g.a.a.l2.b.f3915i;
        set9.add(mVar9.r());
        sha3_256.add("SHA3-256");
        Set set10 = sha3_256;
        m mVar10 = g.a.a.l2.b.j;
        set10.add(mVar10.r());
        sha3_384.add("SHA3-384");
        Set set11 = sha3_384;
        m mVar11 = g.a.a.l2.b.k;
        set11.add(mVar11.r());
        sha3_512.add("SHA3-512");
        Set set12 = sha3_512;
        m mVar12 = g.a.a.l2.b.l;
        set12.add(mVar12.r());
        oids.put("MD5", mVar);
        oids.put(mVar.r(), mVar);
        oids.put("SHA1", mVar2);
        oids.put("SHA-1", mVar2);
        oids.put(mVar2.r(), mVar2);
        oids.put("SHA224", mVar3);
        oids.put("SHA-224", mVar3);
        oids.put(mVar3.r(), mVar3);
        oids.put("SHA256", mVar4);
        oids.put("SHA-256", mVar4);
        oids.put(mVar4.r(), mVar4);
        oids.put("SHA384", mVar5);
        oids.put("SHA-384", mVar5);
        oids.put(mVar5.r(), mVar5);
        oids.put("SHA512", mVar6);
        oids.put("SHA-512", mVar6);
        oids.put(mVar6.r(), mVar6);
        oids.put("SHA512(224)", mVar7);
        oids.put("SHA-512(224)", mVar7);
        oids.put(mVar7.r(), mVar7);
        oids.put("SHA512(256)", mVar8);
        oids.put("SHA-512(256)", mVar8);
        oids.put(mVar8.r(), mVar8);
        oids.put("SHA3-224", mVar9);
        oids.put(mVar9.r(), mVar9);
        oids.put("SHA3-256", mVar10);
        oids.put(mVar10.r(), mVar10);
        oids.put("SHA3-384", mVar11);
        oids.put(mVar11.r(), mVar11);
        oids.put("SHA3-512", mVar12);
        oids.put(mVar12.r(), mVar12);
    }

    public static g.a.b.n getDigest(String str) {
        String strK = i.k(str);
        if (sha1.contains(strK)) {
            return a.b();
        }
        if (md5.contains(strK)) {
            return a.a();
        }
        if (sha224.contains(strK)) {
            return a.c();
        }
        if (sha256.contains(strK)) {
            return a.d();
        }
        if (sha384.contains(strK)) {
            return a.e();
        }
        if (sha512.contains(strK)) {
            return a.j();
        }
        if (sha512_224.contains(strK)) {
            return a.k();
        }
        if (sha512_256.contains(strK)) {
            return a.l();
        }
        if (sha3_224.contains(strK)) {
            return a.f();
        }
        if (sha3_256.contains(strK)) {
            return a.g();
        }
        if (sha3_384.contains(strK)) {
            return a.h();
        }
        if (sha3_512.contains(strK)) {
            return a.i();
        }
        return null;
    }

    public static m getOID(String str) {
        return (m) oids.get(str);
    }

    public static boolean isSameDigest(String str, String str2) {
        return (sha1.contains(str) && sha1.contains(str2)) || (sha224.contains(str) && sha224.contains(str2)) || ((sha256.contains(str) && sha256.contains(str2)) || ((sha384.contains(str) && sha384.contains(str2)) || ((sha512.contains(str) && sha512.contains(str2)) || ((sha512_224.contains(str) && sha512_224.contains(str2)) || ((sha512_256.contains(str) && sha512_256.contains(str2)) || ((sha3_224.contains(str) && sha3_224.contains(str2)) || ((sha3_256.contains(str) && sha3_256.contains(str2)) || ((sha3_384.contains(str) && sha3_384.contains(str2)) || ((sha3_512.contains(str) && sha3_512.contains(str2)) || (md5.contains(str) && md5.contains(str2)))))))))));
    }
}
