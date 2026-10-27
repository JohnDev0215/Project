package k3;

import android.util.SparseBooleanArray;
import e2.C1206n;
import h2.AbstractC1346c;
import java.util.HashSet;

/* JADX INFO: renamed from: k3.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1675w0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z1 f18147e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e2.S f18148f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z1 f18149a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e2.S f18150b;
    public final d5.I c;
    public final d5.I d;

    static {
        HashSet hashSet = new HashSet();
        d5.d0 d0Var = y1.d;
        for (int i7 = 0; i7 < d0Var.o; i7++) {
            hashSet.add(new y1(((Integer) d0Var.get(i7)).intValue()));
        }
        f18147e = new z1(hashSet);
        HashSet hashSet2 = new HashSet();
        d5.d0 d0Var2 = y1.f18163e;
        for (int i10 = 0; i10 < d0Var2.o; i10++) {
            hashSet2.add(new y1(((Integer) d0Var2.get(i10)).intValue()));
        }
        for (int i11 = 0; i11 < d0Var.o; i11++) {
            hashSet2.add(new y1(((Integer) d0Var.get(i11)).intValue()));
        }
        new z1(hashSet2);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        for (int i12 : e2.Q.f13997b) {
            AbstractC1346c.j(!false);
            sparseBooleanArray.append(i12, true);
        }
        AbstractC1346c.j(!false);
        f18148f = new e2.S(new C1206n(sparseBooleanArray));
    }

    public C1675w0(z1 z1Var, e2.S s6, d5.I i7, d5.I i10) {
        this.f18149a = z1Var;
        this.f18150b = s6;
        this.c = i7;
        this.d = i10;
    }
}
