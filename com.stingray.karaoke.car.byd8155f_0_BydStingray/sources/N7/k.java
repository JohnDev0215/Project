package N7;

import java.util.Set;
import n7.AbstractC1996k;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public enum k {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");

    public final C2061f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2061f f4811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f4812n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Set f4801p = AbstractC1996k.c0(new k[]{CHAR, BYTE, SHORT, INT, FLOAT, LONG, DOUBLE});

    k(String str) {
        this.l = C2061f.e(str);
        this.f4811m = C2061f.e(str.concat("Array"));
        m7.g gVar = m7.g.l;
        this.f4812n = h6.r.n(gVar, new j(this, 1));
        this.o = h6.r.n(gVar, new j(this, 0));
    }
}
