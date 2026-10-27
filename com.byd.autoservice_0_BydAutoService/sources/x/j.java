package x;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5708a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WidgetRun f5711d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WidgetRun f5712e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5714g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5715h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5709b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5710c = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<WidgetRun> f5713f = new ArrayList<>();

    public j(WidgetRun widgetRun, int i3) {
        this.f5711d = null;
        this.f5712e = null;
        this.f5714g = 0;
        int i4 = f5708a;
        this.f5714g = i4;
        f5708a = i4 + 1;
        this.f5711d = widgetRun;
        this.f5712e = widgetRun;
        this.f5715h = i3;
    }

    public void a(WidgetRun widgetRun) {
        this.f5713f.add(widgetRun);
        this.f5712e = widgetRun;
    }

    public long b(w.d dVar, int i3) {
        long j3;
        int i4;
        WidgetRun widgetRun = this.f5711d;
        if (widgetRun instanceof c) {
            if (((c) widgetRun).f904f != i3) {
                return 0L;
            }
        } else if (i3 == 0) {
            if (!(widgetRun instanceof i)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof k)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i3 == 0 ? dVar.f843f : dVar.f845g).f906h;
        DependencyNode dependencyNode2 = (i3 == 0 ? dVar.f843f : dVar.f845g).f907i;
        boolean zContains = widgetRun.f906h.f898l.contains(dependencyNode);
        boolean zContains2 = this.f5711d.f907i.f898l.contains(dependencyNode2);
        long j4 = this.f5711d.j();
        if (zContains && zContains2) {
            long jD = d(this.f5711d.f906h, 0L);
            long jC = c(this.f5711d.f907i, 0L);
            long j5 = jD - j4;
            WidgetRun widgetRun2 = this.f5711d;
            int i5 = widgetRun2.f907i.f892f;
            if (j5 >= (-i5)) {
                j5 += (long) i5;
            }
            int i6 = widgetRun2.f906h.f892f;
            long j6 = ((-jC) - j4) - ((long) i6);
            if (j6 >= i6) {
                j6 -= (long) i6;
            }
            float fP = widgetRun2.f900b.p(i3);
            float f3 = fP > 0.0f ? (long) ((j6 / fP) + (j5 / (1.0f - fP))) : 0L;
            long j7 = ((long) ((f3 * fP) + 0.5f)) + j4 + ((long) ((f3 * (1.0f - fP)) + 0.5f));
            WidgetRun widgetRun3 = this.f5711d;
            j3 = ((long) widgetRun3.f906h.f892f) + j7;
            i4 = widgetRun3.f907i.f892f;
        } else {
            if (zContains) {
                return Math.max(d(this.f5711d.f906h, r12.f892f), ((long) this.f5711d.f906h.f892f) + j4);
            }
            if (zContains2) {
                return Math.max(-c(this.f5711d.f907i, r12.f892f), ((long) (-this.f5711d.f907i.f892f)) + j4);
            }
            WidgetRun widgetRun4 = this.f5711d;
            j3 = ((long) widgetRun4.f906h.f892f) + widgetRun4.j();
            i4 = this.f5711d.f907i.f892f;
        }
        return j3 - ((long) i4);
    }

    public final long c(DependencyNode dependencyNode, long j3) {
        WidgetRun widgetRun = dependencyNode.f890d;
        if (widgetRun instanceof h) {
            return j3;
        }
        int size = dependencyNode.f897k.size();
        long jMin = j3;
        for (int i3 = 0; i3 < size; i3++) {
            d dVar = dependencyNode.f897k.get(i3);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f890d != widgetRun) {
                    jMin = Math.min(jMin, c(dependencyNode2, ((long) dependencyNode2.f892f) + j3));
                }
            }
        }
        if (dependencyNode != widgetRun.f907i) {
            return jMin;
        }
        long j4 = j3 - widgetRun.j();
        return Math.min(Math.min(jMin, c(widgetRun.f906h, j4)), j4 - ((long) widgetRun.f906h.f892f));
    }

    public final long d(DependencyNode dependencyNode, long j3) {
        WidgetRun widgetRun = dependencyNode.f890d;
        if (widgetRun instanceof h) {
            return j3;
        }
        int size = dependencyNode.f897k.size();
        long jMax = j3;
        for (int i3 = 0; i3 < size; i3++) {
            d dVar = dependencyNode.f897k.get(i3);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f890d != widgetRun) {
                    jMax = Math.max(jMax, d(dependencyNode2, ((long) dependencyNode2.f892f) + j3));
                }
            }
        }
        if (dependencyNode != widgetRun.f906h) {
            return jMax;
        }
        long j4 = j3 + widgetRun.j();
        return Math.max(Math.max(jMax, d(widgetRun.f907i, j4)), j4 - ((long) widgetRun.f907i.f892f));
    }
}
