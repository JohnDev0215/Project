package p;

import com.spotify.base.java.logging.LogLevel;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
public final class jc1 implements hc1 {
    public static final ic1 g = new ic1(0);
    public static final ic1 h = new ic1(1);
    public static final ic1 i = new ic1(2);
    public static final ic1 j = new ic1(3);
    public static final ic1 k = new ic1(4);
    public static final ic1 l = new ic1(5);
    public static final ic1 m = new ic1(6);
    public final rc1 a;
    public final rc1 b;
    public final rc1 c;
    public final rc1 d;
    public final rc1 e;
    public final rc1 f;

    public jc1(EnumSet enumSet) {
        enumSet.contains(LogLevel.c);
        rc1 rc1Var = rc1.h;
        this.a = enumSet.contains(LogLevel.e) ? g : rc1Var;
        this.b = enumSet.contains(LogLevel.k) ? i : rc1Var;
        this.c = enumSet.contains(LogLevel.l) ? j : rc1Var;
        this.d = enumSet.contains(LogLevel.m) ? k : rc1Var;
        this.e = enumSet.contains(LogLevel.n) ? l : rc1Var;
        this.f = enumSet.contains(LogLevel.o) ? m : rc1Var;
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
