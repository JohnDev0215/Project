package com.spotify.automotive.packagevalidator;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.google.common.hash.c;
import com.google.common.hash.d;
import com.spotify.base.java.logging.Logger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import p.ci1;
import p.k1;
import p.kr1;
import p.ou;
import p.p11;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class b implements kr1 {
    public final Context a;
    public final LinkedHashMap b;
    public final byte[] c;

    /* JADX WARN: Removed duplicated region for block: B:18:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "context"
            p.v03.j(r7, r0)
            android.content.res.Resources r0 = r7.getResources()
            r1 = 2131951616(0x7f130000, float:1.9539652E38)
            android.content.res.XmlResourceParser r0 = r0.getXml(r1)
            java.lang.String r1 = "context.resources.getXml(allowListResId)"
            p.v03.i(r0, r1)
            r6.<init>()
            r6.a = r7
            java.lang.String r1 = "android"
            r2 = 0
            android.content.pm.PackageManager r7 = r7.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L27
            r3 = 64
            android.content.pm.PackageInfo r7 = r7.getPackageInfo(r1, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L27
            goto L28
        L27:
            r7 = r2
        L28:
            if (r7 == 0) goto L2c
            android.content.pm.Signature[] r2 = r7.signatures
        L2c:
            r1 = 0
            r3 = 1
            if (r2 == 0) goto L7b
            android.content.pm.Signature[] r7 = r7.signatures
            int r2 = r7.length
            if (r3 == r2) goto L36
            goto L7b
        L36:
            r7 = r7[r1]
            java.lang.String r2 = "systemPackage.signatures[0]"
            p.v03.i(r7, r2)
            int r2 = com.google.common.hash.d.a
            p.p11 r2 = com.google.common.hash.c.a
            byte[] r7 = r7.toByteArray()
            p.k1 r2 = (p.k1) r2
            r2.getClass()
            int r4 = r7.length
            int r5 = r7.length
            p.ci1.m(r1, r4, r5)
            if (r4 < 0) goto L67
            p.zu r2 = r2.a()
            p.zu r7 = r2.y0(r7, r4)
            p.o11 r7 = r7.l0()
            byte[] r7 = r7.a()
            java.lang.String r2 = "sha256().hashBytes(signa….toByteArray()).asBytes()"
            p.v03.i(r7, r2)
            goto L84
        L67:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "expectedInputSize must be >= 0 but was %s"
            java.lang.String r0 = p.v03.z(r1, r0)
            r7.<init>(r0)
            throw r7
        L7b:
            java.lang.String r7 = "Failed to load the system signature."
            java.lang.Object[] r2 = new java.lang.Object[r1]
            com.spotify.base.java.logging.Logger.b(r7, r2)
            byte[] r7 = new byte[r1]
        L84:
            r6.c = r7
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap
            r7.<init>()
            int r2 = r0.next()     // Catch: java.lang.Exception -> Lb1
        L8f:
            if (r2 == r3) goto Lbf
            r4 = 2
            if (r2 != r4) goto Lb3
            java.lang.String r2 = r0.getName()     // Catch: java.lang.Exception -> Lb1
            java.lang.String r4 = "signature"
            boolean r2 = p.v03.b(r2, r4)     // Catch: java.lang.Exception -> Lb1
            if (r2 == 0) goto Lb3
            p.pr1 r2 = com.spotify.automotive.packagevalidator.a.b(r0)     // Catch: java.lang.Exception -> Lb1
            java.lang.Object r4 = r2.a     // Catch: java.lang.Exception -> Lb1
            java.lang.Object r2 = r2.b     // Catch: java.lang.Exception -> Lb1
            java.lang.String r5 = "entry.second"
            p.v03.i(r2, r5)     // Catch: java.lang.Exception -> Lb1
            r7.put(r4, r2)     // Catch: java.lang.Exception -> Lb1
            goto Lb3
        Lb1:
            r0 = move-exception
            goto Lb8
        Lb3:
            int r2 = r0.next()     // Catch: java.lang.Exception -> Lb1
            goto L8f
        Lb8:
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Unexpected exception while reading allowlist from XML."
            com.spotify.base.java.logging.Logger.c(r0, r2, r1)
        Lbf:
            r6.b = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.automotive.packagevalidator.b.<init>(android.content.Context):void");
    }

    @Override // p.kr1
    public final boolean a(int i, String str) {
        v03.j(str, "packageName");
        return b(str);
    }

    @Override // p.kr1
    public final boolean b(String str) {
        PackageInfo packageInfo;
        v03.j(str, "packageName");
        try {
            packageInfo = this.a.getPackageManager().getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            Logger.g("Package \"%s\" does not exist.", str);
            return false;
        }
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || 1 != signatureArr.length) {
            Logger.g("Package \"%s\" is not signed with exactly one signature.", str);
            return false;
        }
        Signature signature = signatureArr[0];
        v03.i(signature, "packageInfo.signatures[0]");
        int i = d.a;
        p11 p11Var = c.a;
        byte[] byteArray = signature.toByteArray();
        k1 k1Var = (k1) p11Var;
        k1Var.getClass();
        int length = byteArray.length;
        ci1.m(0, length, byteArray.length);
        if (length < 0) {
            throw new IllegalArgumentException(v03.z("expectedInputSize must be >= 0 but was %s", Integer.valueOf(length)));
        }
        byte[] bArrA = k1Var.a().y0(byteArray, length).l0().a();
        v03.i(bArrA, "sha256().hashBytes(signa….toByteArray()).asBytes()");
        if (Arrays.equals(this.c, bArrA)) {
            return true;
        }
        List list = (List) this.b.get(str);
        if (list == null) {
            Logger.g("Package \"%s\" not found in allowlist. Signature is %s.", str, a.a(bArrA));
            return false;
        }
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (Arrays.equals((byte[]) it.next(), bArrA)) {
                    return true;
                }
            }
        }
        Logger.g("Package \"%s\" found in allowlist, but no matches for signature %s. Expected one of the following signatures: %s.", str, a.a(bArrA), ou.h0(list, null, null, null, new ry0() { // from class: com.spotify.automotive.packagevalidator.SignatureBasedPackageValidator$isAllowlistedSignature$2
            @Override // p.ry0
            public final Object invoke(Object obj) {
                byte[] bArr = (byte[]) obj;
                v03.j(bArr, "it");
                return a.a(bArr);
            }
        }, 31));
        return false;
    }
}
