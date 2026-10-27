package org.chromium.url;

import J.N;
import defpackage.et1;
import defpackage.ri3;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class Origin {
    public final String a;
    public final String b;
    public final short c;
    public final boolean d;
    public final long e;
    public final long f;

    public Origin(et1 et1Var) {
        this.a = et1Var.b;
        this.b = et1Var.c;
        this.c = et1Var.d;
        ri3 ri3Var = et1Var.e;
        if (ri3Var != null) {
            this.d = true;
            this.e = ri3Var.b;
            this.f = ri3Var.c;
        } else {
            this.d = false;
            this.e = 0L;
            this.f = 0L;
        }
    }

    @CalledByNative
    public final long toNativeOrigin() {
        return N.MdLvmXEa(this.a, this.b, this.c, this.d, this.e, this.f);
    }

    @CalledByNative
    public Origin(String str, String str2, short s, boolean z, long j, long j2) {
        this.a = str;
        this.b = str2;
        this.c = s;
        this.d = z;
        this.e = j;
        this.f = j2;
    }
}
