package androidx.constraintlayout.solver.widgets.analyzer;

import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1016a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    WidgetRun f1019d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    WidgetRun f1020e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f1022g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f1023h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1017b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1018c = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ArrayList<WidgetRun> f1021f = new ArrayList<>();

    public j(WidgetRun widgetRun, int i) {
        this.f1019d = null;
        this.f1020e = null;
        this.f1022g = 0;
        int i2 = f1016a;
        this.f1022g = i2;
        f1016a = i2 + 1;
        this.f1019d = widgetRun;
        this.f1020e = widgetRun;
        this.f1023h = i;
    }

    private long c(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f980d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            d dVar = dependencyNode.k.get(i);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f980d != widgetRun) {
                    jMin = Math.min(jMin, c(dependencyNode2, ((long) dependencyNode2.f982f) + j));
                }
            }
        }
        if (dependencyNode != widgetRun.i) {
            return jMin;
        }
        long j2 = j - widgetRun.j();
        return Math.min(Math.min(jMin, c(widgetRun.f993h, j2)), j2 - ((long) widgetRun.f993h.f982f));
    }

    private long d(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f980d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            d dVar = dependencyNode.k.get(i);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f980d != widgetRun) {
                    jMax = Math.max(jMax, d(dependencyNode2, ((long) dependencyNode2.f982f) + j));
                }
            }
        }
        if (dependencyNode != widgetRun.f993h) {
            return jMax;
        }
        long j2 = j + widgetRun.j();
        return Math.max(Math.max(jMax, d(widgetRun.i, j2)), j2 - ((long) widgetRun.i.f982f));
    }

    public void a(WidgetRun widgetRun) {
        this.f1021f.add(widgetRun);
        this.f1020e = widgetRun;
    }

    public long b(androidx.constraintlayout.solver.widgets.d dVar, int i) {
        long j;
        int i2;
        WidgetRun widgetRun = this.f1019d;
        if (widgetRun instanceof c) {
            if (((c) widgetRun).f991f != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(widgetRun instanceof i)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof k)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i == 0 ? dVar.f971f : dVar.f972g).f993h;
        DependencyNode dependencyNode2 = (i == 0 ? dVar.f971f : dVar.f972g).i;
        boolean zContains = widgetRun.f993h.l.contains(dependencyNode);
        boolean zContains2 = this.f1019d.i.l.contains(dependencyNode2);
        long j2 = this.f1019d.j();
        if (zContains && zContains2) {
            long jD = d(this.f1019d.f993h, 0L);
            long jC = c(this.f1019d.i, 0L);
            long j3 = jD - j2;
            WidgetRun widgetRun2 = this.f1019d;
            int i3 = widgetRun2.i.f982f;
            if (j3 >= (-i3)) {
                j3 += (long) i3;
            }
            int i4 = widgetRun2.f993h.f982f;
            long j4 = ((-jC) - j2) - ((long) i4);
            if (j4 >= i4) {
                j4 -= (long) i4;
            }
            float fP = widgetRun2.f987b.p(i);
            float f2 = fP > 0.0f ? (long) ((j4 / fP) + (j3 / (1.0f - fP))) : 0L;
            long j5 = ((long) ((f2 * fP) + 0.5f)) + j2 + ((long) ((f2 * (1.0f - fP)) + 0.5f));
            WidgetRun widgetRun3 = this.f1019d;
            j = ((long) widgetRun3.f993h.f982f) + j5;
            i2 = widgetRun3.i.f982f;
        } else {
            if (zContains) {
                return Math.max(d(this.f1019d.f993h, r12.f982f), ((long) this.f1019d.f993h.f982f) + j2);
            }
            if (zContains2) {
                return Math.max(-c(this.f1019d.i, r12.f982f), ((long) (-this.f1019d.i.f982f)) + j2);
            }
            WidgetRun widgetRun4 = this.f1019d;
            j = ((long) widgetRun4.f993h.f982f) + widgetRun4.j();
            i2 = this.f1019d.i.f982f;
        }
        return j - ((long) i2);
    }
}
