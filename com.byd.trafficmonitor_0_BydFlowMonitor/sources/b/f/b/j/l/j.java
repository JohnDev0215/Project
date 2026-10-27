package b.f.b.j.l;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2256a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WidgetRun f2259d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WidgetRun f2260e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2262g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2263h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2257b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2258c = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<WidgetRun> f2261f = new ArrayList<>();

    public j(WidgetRun widgetRun, int i2) {
        this.f2259d = null;
        this.f2260e = null;
        this.f2262g = 0;
        int i3 = f2256a;
        this.f2262g = i3;
        f2256a = i3 + 1;
        this.f2259d = widgetRun;
        this.f2260e = widgetRun;
        this.f2263h = i2;
    }

    public void a(WidgetRun widgetRun) {
        this.f2261f.add(widgetRun);
        this.f2260e = widgetRun;
    }

    public long b(b.f.b.j.d dVar, int i2) {
        long j;
        int i3;
        WidgetRun widgetRun = this.f2259d;
        if (widgetRun instanceof c) {
            if (((c) widgetRun).f654f != i2) {
                return 0L;
            }
        } else if (i2 == 0) {
            if (!(widgetRun instanceof i)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof k)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i2 == 0 ? dVar.f632f : dVar.f633g).f656h;
        DependencyNode dependencyNode2 = (i2 == 0 ? dVar.f632f : dVar.f633g).f657i;
        boolean zContains = widgetRun.f656h.l.contains(dependencyNode);
        boolean zContains2 = this.f2259d.f657i.l.contains(dependencyNode2);
        long j2 = this.f2259d.j();
        if (zContains && zContains2) {
            long jD = d(this.f2259d.f656h, 0L);
            long jC = c(this.f2259d.f657i, 0L);
            long j3 = jD - j2;
            WidgetRun widgetRun2 = this.f2259d;
            int i4 = widgetRun2.f657i.f644f;
            if (j3 >= (-i4)) {
                j3 += (long) i4;
            }
            int i5 = widgetRun2.f656h.f644f;
            long j4 = ((-jC) - j2) - ((long) i5);
            if (j4 >= i5) {
                j4 -= (long) i5;
            }
            float fP = widgetRun2.f650b.p(i2);
            float f2 = fP > 0.0f ? (long) ((j4 / fP) + (j3 / (1.0f - fP))) : 0L;
            long j5 = ((long) ((f2 * fP) + 0.5f)) + j2 + ((long) ((f2 * (1.0f - fP)) + 0.5f));
            WidgetRun widgetRun3 = this.f2259d;
            j = ((long) widgetRun3.f656h.f644f) + j5;
            i3 = widgetRun3.f657i.f644f;
        } else {
            if (zContains) {
                return Math.max(d(this.f2259d.f656h, r12.f644f), ((long) this.f2259d.f656h.f644f) + j2);
            }
            if (zContains2) {
                return Math.max(-c(this.f2259d.f657i, r12.f644f), ((long) (-this.f2259d.f657i.f644f)) + j2);
            }
            WidgetRun widgetRun4 = this.f2259d;
            j = ((long) widgetRun4.f656h.f644f) + widgetRun4.j();
            i3 = this.f2259d.f657i.f644f;
        }
        return j - ((long) i3);
    }

    public final long c(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f642d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMin = j;
        for (int i2 = 0; i2 < size; i2++) {
            d dVar = dependencyNode.k.get(i2);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f642d != widgetRun) {
                    jMin = Math.min(jMin, c(dependencyNode2, ((long) dependencyNode2.f644f) + j));
                }
            }
        }
        if (dependencyNode != widgetRun.f657i) {
            return jMin;
        }
        long j2 = j - widgetRun.j();
        return Math.min(Math.min(jMin, c(widgetRun.f656h, j2)), j2 - ((long) widgetRun.f656h.f644f));
    }

    public final long d(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.f642d;
        if (widgetRun instanceof h) {
            return j;
        }
        int size = dependencyNode.k.size();
        long jMax = j;
        for (int i2 = 0; i2 < size; i2++) {
            d dVar = dependencyNode.k.get(i2);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f642d != widgetRun) {
                    jMax = Math.max(jMax, d(dependencyNode2, ((long) dependencyNode2.f644f) + j));
                }
            }
        }
        if (dependencyNode != widgetRun.f656h) {
            return jMax;
        }
        long j2 = j + widgetRun.j();
        return Math.max(Math.max(jMax, d(widgetRun.f657i, j2)), j2 - ((long) widgetRun.f657i.f644f));
    }
}
