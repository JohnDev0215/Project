package a0;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f29a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WidgetRun f32d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WidgetRun f33e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f35g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f36h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f30b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f31c = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<WidgetRun> f34f = new ArrayList<>();

    public j(WidgetRun widgetRun, int i8) {
        this.f32d = null;
        this.f33e = null;
        this.f35g = 0;
        int i9 = f29a;
        this.f35g = i9;
        f29a = i9 + 1;
        this.f32d = widgetRun;
        this.f33e = widgetRun;
        this.f36h = i8;
    }

    public void a(WidgetRun widgetRun) {
        this.f34f.add(widgetRun);
        this.f33e = widgetRun;
    }

    public long b(z.d dVar, int i8) {
        long j8;
        int i9;
        WidgetRun widgetRun = this.f32d;
        if (widgetRun instanceof c) {
            if (((c) widgetRun).f995f != i8) {
                return 0L;
            }
        } else if (i8 == 0) {
            if (!(widgetRun instanceof i)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof k)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i8 == 0 ? dVar.f934f : dVar.f936g).f997h;
        DependencyNode dependencyNode2 = (i8 == 0 ? dVar.f934f : dVar.f936g).f998i;
        boolean zContains = widgetRun.f997h.f989l.contains(dependencyNode);
        boolean zContains2 = this.f32d.f998i.f989l.contains(dependencyNode2);
        long j9 = this.f32d.j();
        if (zContains && zContains2) {
            long jD = d(this.f32d.f997h, 0L);
            long jC = c(this.f32d.f998i, 0L);
            long j10 = jD - j9;
            WidgetRun widgetRun2 = this.f32d;
            int i10 = widgetRun2.f998i.f983f;
            if (j10 >= (-i10)) {
                j10 += (long) i10;
            }
            int i11 = widgetRun2.f997h.f983f;
            long j11 = ((-jC) - j9) - ((long) i11);
            if (j11 >= i11) {
                j11 -= (long) i11;
            }
            float fP = widgetRun2.f991b.p(i8);
            float f8 = fP > 0.0f ? (long) ((j11 / fP) + (j10 / (1.0f - fP))) : 0L;
            long j12 = ((long) ((f8 * fP) + 0.5f)) + j9 + ((long) ((f8 * (1.0f - fP)) + 0.5f));
            WidgetRun widgetRun3 = this.f32d;
            j8 = ((long) widgetRun3.f997h.f983f) + j12;
            i9 = widgetRun3.f998i.f983f;
        } else {
            if (zContains) {
                return Math.max(d(this.f32d.f997h, r12.f983f), ((long) this.f32d.f997h.f983f) + j9);
            }
            if (zContains2) {
                return Math.max(-c(this.f32d.f998i, r12.f983f), ((long) (-this.f32d.f998i.f983f)) + j9);
            }
            WidgetRun widgetRun4 = this.f32d;
            j8 = ((long) widgetRun4.f997h.f983f) + widgetRun4.j();
            i9 = this.f32d.f998i.f983f;
        }
        return j8 - ((long) i9);
    }

    public final long c(DependencyNode dependencyNode, long j8) {
        WidgetRun widgetRun = dependencyNode.f981d;
        if (widgetRun instanceof h) {
            return j8;
        }
        int size = dependencyNode.f988k.size();
        long jMin = j8;
        for (int i8 = 0; i8 < size; i8++) {
            d dVar = dependencyNode.f988k.get(i8);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f981d != widgetRun) {
                    jMin = Math.min(jMin, c(dependencyNode2, ((long) dependencyNode2.f983f) + j8));
                }
            }
        }
        if (dependencyNode != widgetRun.f998i) {
            return jMin;
        }
        long j9 = j8 - widgetRun.j();
        return Math.min(Math.min(jMin, c(widgetRun.f997h, j9)), j9 - ((long) widgetRun.f997h.f983f));
    }

    public final long d(DependencyNode dependencyNode, long j8) {
        WidgetRun widgetRun = dependencyNode.f981d;
        if (widgetRun instanceof h) {
            return j8;
        }
        int size = dependencyNode.f988k.size();
        long jMax = j8;
        for (int i8 = 0; i8 < size; i8++) {
            d dVar = dependencyNode.f988k.get(i8);
            if (dVar instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dVar;
                if (dependencyNode2.f981d != widgetRun) {
                    jMax = Math.max(jMax, d(dependencyNode2, ((long) dependencyNode2.f983f) + j8));
                }
            }
        }
        if (dependencyNode != widgetRun.f997h) {
            return jMax;
        }
        long j9 = j8 + widgetRun.j();
        return Math.max(Math.max(jMax, d(widgetRun.f998i, j9)), j9 - ((long) widgetRun.f998i.f983f));
    }
}
