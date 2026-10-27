package a0;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ChainRun.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends WidgetRun {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList<WidgetRun> f15k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f16l;

    public c(ConstraintWidget constraintWidget, int i8) {
        super(constraintWidget);
        this.f15k = new ArrayList<>();
        this.f995f = i8;
        q();
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x01cd A[PHI: r1 r22 r23 r24
  0x01cd: PHI (r1v62 int) = (r1v60 int), (r1v68 int) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]
  0x01cd: PHI (r22v3 float) = (r22v2 float), (r22v5 float) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]
  0x01cd: PHI (r23v6 boolean) = (r23v5 boolean), (r23v8 boolean) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]
  0x01cd: PHI (r24v6 int) = (r24v5 int), (r24v8 int) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00eb  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, a0.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(a0.d r26) {
        /*
            Method dump skipped, instruction units count: 1088
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a0.c.a(a0.d):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        Iterator<WidgetRun> it = this.f15k.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        int size = this.f15k.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.f15k.get(0).f991b;
        ConstraintWidget constraintWidget2 = this.f15k.get(size - 1).f991b;
        if (this.f995f == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.E;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.G;
            DependencyNode dependencyNodeI = i(constraintAnchor, 0);
            int iC = constraintAnchor.c();
            ConstraintWidget constraintWidgetR = r();
            if (constraintWidgetR != null) {
                iC = constraintWidgetR.E.c();
            }
            if (dependencyNodeI != null) {
                b(this.f997h, dependencyNodeI, iC);
            }
            DependencyNode dependencyNodeI2 = i(constraintAnchor2, 0);
            int iC2 = constraintAnchor2.c();
            ConstraintWidget constraintWidgetS = s();
            if (constraintWidgetS != null) {
                iC2 = constraintWidgetS.G.c();
            }
            if (dependencyNodeI2 != null) {
                b(this.f998i, dependencyNodeI2, -iC2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.F;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.H;
            DependencyNode dependencyNodeI3 = i(constraintAnchor3, 1);
            int iC3 = constraintAnchor3.c();
            ConstraintWidget constraintWidgetR2 = r();
            if (constraintWidgetR2 != null) {
                iC3 = constraintWidgetR2.F.c();
            }
            if (dependencyNodeI3 != null) {
                b(this.f997h, dependencyNodeI3, iC3);
            }
            DependencyNode dependencyNodeI4 = i(constraintAnchor4, 1);
            int iC4 = constraintAnchor4.c();
            ConstraintWidget constraintWidgetS2 = s();
            if (constraintWidgetS2 != null) {
                iC4 = constraintWidgetS2.H.c();
            }
            if (dependencyNodeI4 != null) {
                b(this.f998i, dependencyNodeI4, -iC4);
            }
        }
        this.f997h.f978a = this;
        this.f998i.f978a = this;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        for (int i8 = 0; i8 < this.f15k.size(); i8++) {
            this.f15k.get(i8).e();
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f992c = null;
        Iterator<WidgetRun> it = this.f15k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public long j() {
        int size = this.f15k.size();
        long j8 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            WidgetRun widgetRun = this.f15k.get(i8);
            j8 = j8 + ((long) widgetRun.f997h.f983f) + widgetRun.j() + ((long) widgetRun.f998i.f983f);
        }
        return j8;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        int size = this.f15k.size();
        for (int i8 = 0; i8 < size; i8++) {
            if (!this.f15k.get(i8).m()) {
                return false;
            }
        }
        return true;
    }

    public final void q() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.f991b;
        ConstraintWidget constraintWidgetI = constraintWidget2.I(this.f995f);
        while (true) {
            ConstraintWidget constraintWidget3 = constraintWidgetI;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                constraintWidgetI = constraintWidget2.I(this.f995f);
            }
        }
        this.f991b = constraintWidget;
        this.f15k.add(constraintWidget.K(this.f995f));
        ConstraintWidget constraintWidgetG = constraintWidget.G(this.f995f);
        while (constraintWidgetG != null) {
            this.f15k.add(constraintWidgetG.K(this.f995f));
            constraintWidgetG = constraintWidgetG.G(this.f995f);
        }
        for (WidgetRun widgetRun : this.f15k) {
            int i8 = this.f995f;
            if (i8 == 0) {
                widgetRun.f991b.f930d = this;
            } else if (i8 == 1) {
                widgetRun.f991b.f932e = this;
            }
        }
        if ((this.f995f == 0 && ((z.d) this.f991b.H()).c1()) && this.f15k.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.f15k;
            this.f991b = arrayList.get(arrayList.size() - 1).f991b;
        }
        this.f16l = this.f995f == 0 ? this.f991b.y() : this.f991b.M();
    }

    public final ConstraintWidget r() {
        for (int i8 = 0; i8 < this.f15k.size(); i8++) {
            WidgetRun widgetRun = this.f15k.get(i8);
            if (widgetRun.f991b.P() != 8) {
                return widgetRun.f991b;
            }
        }
        return null;
    }

    public final ConstraintWidget s() {
        for (int size = this.f15k.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.f15k.get(size);
            if (widgetRun.f991b.P() != 8) {
                return widgetRun.f991b;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChainRun ");
        sb.append(this.f995f == 0 ? "horizontal : " : "vertical : ");
        String string = sb.toString();
        Iterator<WidgetRun> it = this.f15k.iterator();
        while (it.hasNext()) {
            String str = string + "<";
            string = (str + it.next()) + "> ";
        }
        return string;
    }
}
