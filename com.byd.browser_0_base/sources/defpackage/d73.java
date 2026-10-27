package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: TaskTraits.java */
/* JADX INFO: loaded from: classes.dex */
public final class d73 {
    public static final d73 f;
    public static final d73 g;
    public static final d73 h;
    public static final d73 i;
    public static final d73 j;
    public static final d73 k;
    public static final d73 l;
    public static final d73 m;
    public int a;
    public boolean b;
    public boolean c;
    public byte d;
    public byte[] e;

    static {
        d73 d73VarA = new d73().a(0);
        f = d73VarA;
        d73 d73Var = new d73(d73VarA);
        d73Var.b = true;
        g = d73Var;
        d73 d73VarA2 = new d73().a(1);
        h = d73VarA2;
        d73 d73Var2 = new d73(d73VarA2);
        d73Var2.b = true;
        i = d73Var2;
        d73 d73VarA3 = new d73().a(2);
        j = d73VarA3;
        d73 d73Var3 = new d73(d73VarA3);
        d73Var3.b = true;
        k = d73Var3;
        d73 d73Var4 = new d73(new d73());
        d73Var4.c = true;
        d73 d73VarA4 = d73Var4.a(2);
        l = d73VarA4;
        d73VarA4.a(2);
        d73VarA4.a(1);
        m = d73VarA4.a(0);
    }

    public d73() {
        this.a = 2;
    }

    public final d73 a(int i2) {
        d73 d73Var = new d73(this);
        d73Var.a = i2;
        return d73Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d73)) {
            return false;
        }
        d73 d73Var = (d73) obj;
        return this.a == d73Var.a && this.b == d73Var.b && this.c == d73Var.c && this.d == d73Var.d && Arrays.equals(this.e, d73Var.e);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.e) + ((((((((1147 + this.a) * 37) + (!this.b ? 1 : 0)) * 37) + (!this.c ? 1 : 0)) * 37) + this.d) * 37);
    }

    public d73(d73 d73Var) {
        this.a = d73Var.a;
        this.b = d73Var.b;
        this.c = d73Var.c;
        this.d = d73Var.d;
        this.e = d73Var.e;
    }
}
