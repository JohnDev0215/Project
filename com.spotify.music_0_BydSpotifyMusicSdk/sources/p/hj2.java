package p;

import com.spotify.base.java.logging.LogLevel;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
public final class hj2 implements hc1 {
    public final rc1 a;
    public final rc1 b;
    public final rc1 c;
    public final rc1 d;
    public final rc1 e;
    public final rc1 f;

    public hj2(qo qoVar, EnumSet enumSet) {
        rc1 gj2Var = new gj2(qoVar, 1);
        rc1 gj2Var2 = new gj2(qoVar, 3);
        rc1 gj2Var3 = new gj2(qoVar, 5);
        rc1 gj2Var4 = new gj2(qoVar, 2);
        rc1 gj2Var5 = new gj2(qoVar, 0);
        rc1 gj2Var6 = new gj2(qoVar, 6);
        enumSet.contains(LogLevel.c);
        zb0 zb0Var = rc1.h;
        this.a = enumSet.contains(LogLevel.e) ? gj2Var : zb0Var;
        this.b = enumSet.contains(LogLevel.k) ? gj2Var2 : zb0Var;
        this.c = enumSet.contains(LogLevel.l) ? gj2Var3 : zb0Var;
        this.d = enumSet.contains(LogLevel.m) ? gj2Var4 : zb0Var;
        this.e = enumSet.contains(LogLevel.n) ? gj2Var5 : zb0Var;
        this.f = enumSet.contains(LogLevel.o) ? gj2Var6 : zb0Var;
    }

    @Override // p.hc1
    public final rc1 a() {
        return this.b;
    }

    @Override // p.hc1
    public final rc1 b() {
        return this.c;
    }

    @Override // p.hc1
    public final rc1 c() {
        return this.e;
    }

    @Override // p.hc1
    public final rc1 d() {
        return this.f;
    }

    @Override // p.hc1
    public final rc1 e() {
        return this.a;
    }

    @Override // p.hc1
    public final rc1 f() {
        return this.d;
    }
}
