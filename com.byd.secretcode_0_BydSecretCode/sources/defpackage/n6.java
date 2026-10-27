package defpackage;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class n6 {
    public static int a;
    public WidgetRun d;
    public WidgetRun e;
    public int g;
    public int h;
    public int b = 0;
    public boolean c = false;
    public ArrayList<WidgetRun> f = new ArrayList<>();

    public n6(WidgetRun widgetRun, int i) {
        this.d = null;
        this.e = null;
        this.g = 0;
        int i2 = a;
        this.g = i2;
        a = i2 + 1;
        this.d = widgetRun;
        this.e = widgetRun;
        this.h = i;
    }

    public void a(WidgetRun widgetRun) {
        this.f.add(widgetRun);
        this.e = widgetRun;
    }

    public long b(w5 w5Var, int i) {
        long j;
        int i2;
        WidgetRun widgetRun = this.d;
        if (widgetRun instanceof g6) {
            if (((g6) widgetRun).f != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(widgetRun instanceof m6)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof o6)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i == 0 ? w5Var.f : w5Var.g).h;
        DependencyNode dependencyNode2 = (i == 0 ? w5Var.f : w5Var.g).i;
        boolean zContains = widgetRun.h.l.contains(dependencyNode);
        boolean zContains2 = this.d.i.l.contains(dependencyNode2);
        long j2 = this.d.j();
        if (zContains && zContains2) {
            long jD = d(this.d.h, 0L);
            long jC = c(this.d.i, 0L);
            long j3 = jD - j2;
            WidgetRun widgetRun2 = this.d;
            int i3 = widgetRun2.i.f;
            if (j3 >= (-i3)) {
                j3 += (long) i3;
            }
            int i4 = widgetRun2.h.f;
            long j4 = ((-jC) - j2) - ((long) i4);
            if (j4 >= i4) {
                j4 -= (long) i4;
            }
            float fP = widgetRun2.b.p(i);
            float f = fP > 0.0f ? (long) ((j4 / fP) + (j3 / (1.0f - fP))) : 0L;
            long j5 = ((long) ((f * fP) + 0.5f)) + j2 + ((long) ((f * (1.0f - fP)) + 0.5f));
            WidgetRun widgetRun3 = this.d;
            j = ((long) widgetRun3.h.f) + j5;
            i2 = widgetRun3.i.f;
        } else {
            if (zContains) {
                return Math.max(d(this.d.h, r12.f), ((long) this.d.h.f) + j2);
            }
            if (zContains2) {
                return Math.max(-c(this.d.i, r12.f), ((long) (-this.d.i.f)) + j2);
            }
            WidgetRun widgetRun4 = this.d;
            j = ((long) widgetRun4.h.f) + widgetRun4.j();
            i2 = this.d.i.f;
        }
        return j - ((long) i2);
    }

    public final long c(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.d;
        if (widgetRun instanceof l6) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            h6 h6Var = dependencyNode.k.get(i);
            if (h6Var instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) h6Var;
                if (dependencyNode2.d != widgetRun) {
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

    public final long d(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.d;
        if (widgetRun instanceof l6) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            h6 h6Var = dependencyNode.k.get(i);
            if (h6Var instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) h6Var;
                if (dependencyNode2.d != widgetRun) {
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
}
