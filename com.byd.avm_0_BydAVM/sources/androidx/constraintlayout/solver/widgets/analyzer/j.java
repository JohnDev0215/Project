package androidx.constraintlayout.solver.widgets.analyzer;

import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f686a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    WidgetRun f689d;
    WidgetRun e;
    int g;
    int h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f687b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f688c = false;
    ArrayList<WidgetRun> f = new ArrayList<>();

    public j(WidgetRun widgetRun, int i) {
        this.f689d = null;
        this.e = null;
        this.g = 0;
        int i2 = f686a;
        this.g = i2;
        f686a = i2 + 1;
        this.f689d = widgetRun;
        this.e = widgetRun;
        this.h = i;
    }

    private long c(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f666d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            d dVar = dependencyNode.k.get(i);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f666d != widgetRun) {
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
        WidgetRun widgetRun = dependencyNode.f666d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            d dVar = dependencyNode.k.get(i);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f666d != widgetRun) {
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
        this.e = widgetRun;
    }

    public long b(androidx.constraintlayout.solver.widgets.d dVar, int i) {
        WidgetRun widgetRun = this.f689d;
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
        boolean zContains2 = this.f689d.i.l.contains(dependencyNode2);
        long j = this.f689d.j();
        if (!zContains || !zContains2) {
            if (zContains) {
                return Math.max(d(this.f689d.h, r12.f), ((long) this.f689d.h.f) + j);
            }
            if (!zContains2) {
                WidgetRun widgetRun2 = this.f689d;
                return (((long) widgetRun2.h.f) + widgetRun2.j()) - ((long) this.f689d.i.f);
            }
            return Math.max(-c(this.f689d.i, r12.f), ((long) (-this.f689d.i.f)) + j);
        }
        long jD = d(this.f689d.h, 0L);
        long jC = c(this.f689d.i, 0L);
        long j2 = jD - j;
        WidgetRun widgetRun3 = this.f689d;
        int i2 = widgetRun3.i.f;
        if (j2 >= (-i2)) {
            j2 += (long) i2;
        }
        int i3 = widgetRun3.h.f;
        long j3 = ((-jC) - j) - ((long) i3);
        if (j3 >= i3) {
            j3 -= (long) i3;
        }
        float fP = widgetRun3.f669b.p(i);
        float f = fP > 0.0f ? (long) ((j3 / fP) + (j2 / (1.0f - fP))) : 0L;
        long j4 = ((long) ((f * fP) + 0.5f)) + j + ((long) ((f * (1.0f - fP)) + 0.5f));
        WidgetRun widgetRun4 = this.f689d;
        return (((long) widgetRun4.h.f) + j4) - ((long) widgetRun4.i.f);
    }
}
