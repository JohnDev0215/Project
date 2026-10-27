package androidx.constraintlayout.solver.widgets.analyzer;

import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f770a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    WidgetRun f773d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    WidgetRun f774e;
    int g;
    int h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f771b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f772c = false;
    ArrayList<WidgetRun> f = new ArrayList<>();

    public j(WidgetRun widgetRun, int i) {
        this.f773d = null;
        this.f774e = null;
        this.g = 0;
        int i2 = f770a;
        this.g = i2;
        f770a = i2 + 1;
        this.f773d = widgetRun;
        this.f774e = widgetRun;
        this.h = i;
    }

    private long c(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f746d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            d dVar = dependencyNode.k.get(i);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f746d != widgetRun) {
                    jMin = Math.min(jMin, c(dependencyNode2, ((long) dependencyNode2.f) + j));
                }
            }
        }
        if (dependencyNode != widgetRun.i) {
            return jMin;
        }
        long j2 = j - widgetRun.j();
        return Math.min(Math.min(jMin, c(widgetRun.h, j2)), j2 - ((long) widgetRun.h.f));
    }

    private long d(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f746d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            d dVar = dependencyNode.k.get(i);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f746d != widgetRun) {
                    jMax = Math.max(jMax, d(dependencyNode2, ((long) dependencyNode2.f) + j));
                }
            }
        }
        if (dependencyNode != widgetRun.h) {
            return jMax;
        }
        long j2 = j + widgetRun.j();
        return Math.max(Math.max(jMax, d(widgetRun.i, j2)), j2 - ((long) widgetRun.i.f));
    }

    public void a(WidgetRun widgetRun) {
        this.f.add(widgetRun);
        this.f774e = widgetRun;
    }

    public long b(androidx.constraintlayout.solver.widgets.d dVar, int i) {
        long j;
        int i2;
        WidgetRun widgetRun = this.f773d;
        if (widgetRun instanceof c) {
            if (((c) widgetRun).f != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(widgetRun instanceof i)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof k)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i == 0 ? dVar.f : dVar.g).h;
        DependencyNode dependencyNode2 = (i == 0 ? dVar.f : dVar.g).i;
        boolean zContains = widgetRun.h.l.contains(dependencyNode);
        boolean zContains2 = this.f773d.i.l.contains(dependencyNode2);
        long j2 = this.f773d.j();
        if (zContains && zContains2) {
            long jD = d(this.f773d.h, 0L);
            long jC = c(this.f773d.i, 0L);
            long j3 = jD - j2;
            WidgetRun widgetRun2 = this.f773d;
            int i3 = widgetRun2.i.f;
            if (j3 >= (-i3)) {
                j3 += (long) i3;
            }
            int i4 = widgetRun2.h.f;
            long j4 = ((-jC) - j2) - ((long) i4);
            if (j4 >= i4) {
                j4 -= (long) i4;
            }
            float fP = widgetRun2.f750b.p(i);
            float f = fP > 0.0f ? (long) ((j4 / fP) + (j3 / (1.0f - fP))) : 0L;
            long j5 = ((long) ((f * fP) + 0.5f)) + j2 + ((long) ((f * (1.0f - fP)) + 0.5f));
            WidgetRun widgetRun3 = this.f773d;
            j = ((long) widgetRun3.h.f) + j5;
            i2 = widgetRun3.i.f;
        } else {
            if (zContains) {
                return Math.max(d(this.f773d.h, r12.f), ((long) this.f773d.h.f) + j2);
            }
            if (zContains2) {
                return Math.max(-c(this.f773d.i, r12.f), ((long) (-this.f773d.i.f)) + j2);
            }
            WidgetRun widgetRun4 = this.f773d;
            j = ((long) widgetRun4.h.f) + widgetRun4.j();
            i2 = this.f773d.i.f;
        }
        return j - ((long) i2);
    }
}
