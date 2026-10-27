package a.f.a.i.l;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f597c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public o f598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<o> f599b = new ArrayList<>();

    public l(o oVar, int i) {
        this.f598a = null;
        f597c++;
        this.f598a = oVar;
    }

    public final long a(f fVar, long j) {
        o oVar = fVar.f585d;
        if (oVar instanceof j) {
            return j;
        }
        int size = fVar.k.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            d dVar = fVar.k.get(i);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f585d != oVar) {
                    jMin = Math.min(jMin, a(fVar2, ((long) fVar2.f587f) + j));
                }
            }
        }
        if (fVar != oVar.i) {
            return jMin;
        }
        long j2 = j - oVar.j();
        return Math.min(Math.min(jMin, a(oVar.h, j2)), j2 - ((long) oVar.h.f587f));
    }

    public final long b(f fVar, long j) {
        o oVar = fVar.f585d;
        if (oVar instanceof j) {
            return j;
        }
        int size = fVar.k.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            d dVar = fVar.k.get(i);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f585d != oVar) {
                    jMax = Math.max(jMax, b(fVar2, ((long) fVar2.f587f) + j));
                }
            }
        }
        if (fVar != oVar.h) {
            return jMax;
        }
        long j2 = j + oVar.j();
        return Math.max(Math.max(jMax, b(oVar.i, j2)), j2 - ((long) oVar.i.f587f));
    }
}
