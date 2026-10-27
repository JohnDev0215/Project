package Q3;

import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class k implements l, j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6179a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f6180b = new Path();
    public final Path c = new Path();
    public final ArrayList d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final W3.g f6181e;

    public k(W3.g gVar) {
        gVar.getClass();
        this.f6181e = gVar;
    }

    public final void b(Path.Op op) {
        Matrix matrixD;
        Matrix matrixD2;
        Path path = this.f6180b;
        path.reset();
        Path path2 = this.f6179a;
        path2.reset();
        ArrayList arrayList = this.d;
        for (int size = arrayList.size() - 1; size >= 1; size--) {
            l lVar = (l) arrayList.get(size);
            if (lVar instanceof d) {
                d dVar = (d) lVar;
                ArrayList arrayList2 = (ArrayList) dVar.d();
                for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                    Path pathE = ((l) arrayList2.get(size2)).e();
                    R3.n nVar = dVar.f6144k;
                    if (nVar != null) {
                        matrixD2 = nVar.d();
                    } else {
                        matrixD2 = dVar.d;
                        matrixD2.reset();
                    }
                    pathE.transform(matrixD2);
                    path.addPath(pathE);
                }
            } else {
                path.addPath(lVar.e());
            }
        }
        int i7 = 0;
        l lVar2 = (l) arrayList.get(0);
        if (lVar2 instanceof d) {
            d dVar2 = (d) lVar2;
            List listD = dVar2.d();
            while (true) {
                ArrayList arrayList3 = (ArrayList) listD;
                if (i7 >= arrayList3.size()) {
                    break;
                }
                Path pathE2 = ((l) arrayList3.get(i7)).e();
                R3.n nVar2 = dVar2.f6144k;
                if (nVar2 != null) {
                    matrixD = nVar2.d();
                } else {
                    matrixD = dVar2.d;
                    matrixD.reset();
                }
                pathE2.transform(matrixD);
                path2.addPath(pathE2);
                i7++;
            }
        } else {
            path2.set(lVar2.e());
        }
        this.c.op(path2, path, op);
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.d;
            if (i7 >= arrayList.size()) {
                return;
            }
            ((l) arrayList.get(i7)).c(list, list2);
            i7++;
        }
    }

    @Override // Q3.j
    public final void d(ListIterator listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            c cVar = (c) listIterator.previous();
            if (cVar instanceof l) {
                this.d.add((l) cVar);
                listIterator.remove();
            }
        }
    }

    @Override // Q3.l
    public final Path e() {
        Path path = this.c;
        path.reset();
        W3.g gVar = this.f6181e;
        if (gVar.f9779b) {
            return path;
        }
        int iC = AbstractC2641j.c(gVar.f9778a);
        if (iC == 0) {
            int i7 = 0;
            while (true) {
                ArrayList arrayList = this.d;
                if (i7 >= arrayList.size()) {
                    break;
                }
                path.addPath(((l) arrayList.get(i7)).e());
                i7++;
            }
        } else if (iC == 1) {
            b(Path.Op.UNION);
        } else if (iC == 2) {
            b(Path.Op.REVERSE_DIFFERENCE);
        } else if (iC == 3) {
            b(Path.Op.INTERSECT);
        } else if (iC == 4) {
            b(Path.Op.XOR);
        }
        return path;
    }
}
