package com.airbnb.lottie.r.b;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Build;
import com.airbnb.lottie.t.k.h;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(19)
public class l implements m, j {
    private final String d;
    private final com.airbnb.lottie.t.k.h f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f744a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path f745b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Path f746c = new Path();
    private final List<m> e = new ArrayList();

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f747a;

        static {
            int[] iArr = new int[h.a.values().length];
            f747a = iArr;
            try {
                iArr[h.a.MERGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f747a[h.a.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f747a[h.a.SUBTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f747a[h.a.INTERSECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f747a[h.a.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public l(com.airbnb.lottie.t.k.h hVar) {
        if (Build.VERSION.SDK_INT < 19) {
            throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
        }
        this.d = hVar.c();
        this.f = hVar;
    }

    private void b() {
        for (int i = 0; i < this.e.size(); i++) {
            this.f746c.addPath(this.e.get(i).getPath());
        }
    }

    @TargetApi(19)
    private void e(Path.Op op) {
        this.f745b.reset();
        this.f744a.reset();
        for (int size = this.e.size() - 1; size >= 1; size--) {
            m mVar = this.e.get(size);
            if (mVar instanceof d) {
                d dVar = (d) mVar;
                List<m> listI = dVar.i();
                for (int size2 = listI.size() - 1; size2 >= 0; size2--) {
                    Path path = listI.get(size2).getPath();
                    path.transform(dVar.j());
                    this.f745b.addPath(path);
                }
            } else {
                this.f745b.addPath(mVar.getPath());
            }
        }
        m mVar2 = this.e.get(0);
        if (mVar2 instanceof d) {
            d dVar2 = (d) mVar2;
            List<m> listI2 = dVar2.i();
            for (int i = 0; i < listI2.size(); i++) {
                Path path2 = listI2.get(i).getPath();
                path2.transform(dVar2.j());
                this.f744a.addPath(path2);
            }
        } else {
            this.f744a.set(mVar2.getPath());
        }
        this.f746c.op(this.f744a, this.f745b, op);
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < this.e.size(); i++) {
            this.e.get(i).c(list, list2);
        }
    }

    @Override // com.airbnb.lottie.r.b.j
    public void d(ListIterator<c> listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            c cVarPrevious = listIterator.previous();
            if (cVarPrevious instanceof m) {
                this.e.add((m) cVarPrevious);
                listIterator.remove();
            }
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path getPath() {
        Path.Op op;
        this.f746c.reset();
        if (this.f.d()) {
            return this.f746c;
        }
        int i = a.f747a[this.f.b().ordinal()];
        if (i != 1) {
            if (i == 2) {
                op = Path.Op.UNION;
            } else if (i == 3) {
                op = Path.Op.REVERSE_DIFFERENCE;
            } else if (i == 4) {
                op = Path.Op.INTERSECT;
            } else if (i == 5) {
                op = Path.Op.XOR;
            }
            e(op);
        } else {
            b();
        }
        return this.f746c;
    }
}
