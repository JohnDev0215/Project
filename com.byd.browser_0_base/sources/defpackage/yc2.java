package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
public final class yc2 {
    public final gt3 a;
    public final ArrayList<gt3> b = new ArrayList<>();

    public yc2(gt3 gt3Var) {
        this.a = null;
        this.a = gt3Var;
    }

    public static long a(o80 o80Var, long j) {
        gt3 gt3Var = o80Var.d;
        if (gt3Var instanceof ju0) {
            return j;
        }
        ArrayList arrayList = o80Var.k;
        int size = arrayList.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            m80 m80Var = (m80) arrayList.get(i);
            if (m80Var instanceof o80) {
                o80 o80Var2 = (o80) m80Var;
                if (o80Var2.d != gt3Var) {
                    jMin = Math.min(jMin, a(o80Var2, ((long) o80Var2.f) + j));
                }
            }
        }
        if (o80Var != gt3Var.i) {
            return jMin;
        }
        long j2 = gt3Var.j();
        o80 o80Var3 = gt3Var.h;
        long j3 = j - j2;
        return Math.min(Math.min(jMin, a(o80Var3, j3)), j3 - ((long) o80Var3.f));
    }

    public static long b(o80 o80Var, long j) {
        gt3 gt3Var = o80Var.d;
        if (gt3Var instanceof ju0) {
            return j;
        }
        ArrayList arrayList = o80Var.k;
        int size = arrayList.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            m80 m80Var = (m80) arrayList.get(i);
            if (m80Var instanceof o80) {
                o80 o80Var2 = (o80) m80Var;
                if (o80Var2.d != gt3Var) {
                    jMax = Math.max(jMax, b(o80Var2, ((long) o80Var2.f) + j));
                }
            }
        }
        if (o80Var != gt3Var.h) {
            return jMax;
        }
        long j2 = gt3Var.j();
        o80 o80Var3 = gt3Var.i;
        long j3 = j + j2;
        return Math.max(Math.max(jMax, b(o80Var3, j3)), j3 - ((long) o80Var3.f));
    }
}
