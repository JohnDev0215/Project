package p;

import com.google.common.base.Optional;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class g0 {
    public final String a;
    public final String b;
    public final String c;
    public final Optional d;
    public final Optional e;
    public final Optional f;
    public final Optional g;
    public final Optional h;
    public final long i;
    public final long j;
    public final long k;

    public g0(String str, String str2, String str3, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, long j, long j2, long j3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = optional;
        this.e = optional2;
        this.f = optional3;
        this.g = optional4;
        this.h = optional5;
        this.i = j;
        this.j = j2;
        this.k = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g0)) {
            return false;
        }
        g0 g0Var = (g0) obj;
        return this.a.equals(g0Var.a) && this.b.equals(g0Var.b) && this.c.equals(g0Var.c) && !f60.p(this.d, g0Var.d) && !f60.p(this.e, g0Var.e) && !f60.p(this.f, g0Var.f) && !f60.p(this.g, g0Var.g) && !f60.p(this.h, g0Var.h) && this.i == g0Var.i && this.j == g0Var.j && Math.abs(this.k - g0Var.k) < 1000;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, Long.valueOf(this.i), Long.valueOf(this.j), Long.valueOf(this.k)});
    }
}
