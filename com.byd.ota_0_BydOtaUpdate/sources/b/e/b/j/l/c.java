package b.e.b.j.l;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ChainRun.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends WidgetRun {
    public ArrayList<WidgetRun> k;
    public int l;

    public c(ConstraintWidget constraintWidget, int i2) {
        super(constraintWidget);
        this.k = new ArrayList<>();
        this.f365f = i2;
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
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, b.e.b.j.l.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(b.e.b.j.l.d r26) {
        /*
            Method dump skipped, instruction units count: 1088
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.j.l.c.a(b.e.b.j.l.d):void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        Iterator<WidgetRun> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        int size = this.k.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.k.get(0).f361b;
        ConstraintWidget constraintWidget2 = this.k.get(size - 1).f361b;
        if (this.f365f == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.E;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.G;
            DependencyNode dependencyNodeI = i(constraintAnchor, 0);
            int iC = constraintAnchor.c();
            ConstraintWidget constraintWidgetR = r();
            if (constraintWidgetR != null) {
                iC = constraintWidgetR.E.c();
            }
            if (dependencyNodeI != null) {
                b(this.f367h, dependencyNodeI, iC);
            }
            DependencyNode dependencyNodeI2 = i(constraintAnchor2, 0);
            int iC2 = constraintAnchor2.c();
            ConstraintWidget constraintWidgetS = s();
            if (constraintWidgetS != null) {
                iC2 = constraintWidgetS.G.c();
            }
            if (dependencyNodeI2 != null) {
                b(this.f368i, dependencyNodeI2, -iC2);
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
                b(this.f367h, dependencyNodeI3, iC3);
            }
            DependencyNode dependencyNodeI4 = i(constraintAnchor4, 1);
            int iC4 = constraintAnchor4.c();
            ConstraintWidget constraintWidgetS2 = s();
            if (constraintWidgetS2 != null) {
                iC4 = constraintWidgetS2.H.c();
            }
            if (dependencyNodeI4 != null) {
                b(this.f368i, dependencyNodeI4, -iC4);
            }
        }
        this.f367h.f350a = this;
        this.f368i.f350a = this;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void e() {
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            this.k.get(i2).e();
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void f() {
        this.f362c = null;
        Iterator<WidgetRun> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public long j() {
        int size = this.k.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            WidgetRun widgetRun = this.k.get(i2);
            j = j + ((long) widgetRun.f367h.f355f) + widgetRun.j() + ((long) widgetRun.f368i.f355f);
        }
        return j;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean m() {
        int size = this.k.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.k.get(i2).m()) {
                return false;
            }
        }
        return true;
    }

    public final void q() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.f361b;
        ConstraintWidget constraintWidgetI = constraintWidget2.I(this.f365f);
        while (true) {
            ConstraintWidget constraintWidget3 = constraintWidgetI;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                constraintWidgetI = constraintWidget2.I(this.f365f);
            }
        }
        this.f361b = constraintWidget;
        this.k.add(constraintWidget.K(this.f365f));
        ConstraintWidget constraintWidgetG = constraintWidget.G(this.f365f);
        while (constraintWidgetG != null) {
            this.k.add(constraintWidgetG.K(this.f365f));
            constraintWidgetG = constraintWidgetG.G(this.f365f);
        }
        for (WidgetRun widgetRun : this.k) {
            int i2 = this.f365f;
            if (i2 == 0) {
                widgetRun.f361b.f341d = this;
            } else if (i2 == 1) {
                widgetRun.f361b.f342e = this;
            }
        }
        if ((this.f365f == 0 && ((b.e.b.j.d) this.f361b.H()).c1()) && this.k.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.k;
            this.f361b = arrayList.get(arrayList.size() - 1).f361b;
        }
        this.l = this.f365f == 0 ? this.f361b.y() : this.f361b.M();
    }

    public final ConstraintWidget r() {
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            WidgetRun widgetRun = this.k.get(i2);
            if (widgetRun.f361b.P() != 8) {
                return widgetRun.f361b;
            }
        }
        return null;
    }

    public final ConstraintWidget s() {
        for (int size = this.k.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.k.get(size);
            if (widgetRun.f361b.P() != 8) {
                return widgetRun.f361b;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChainRun ");
        sb.append(this.f365f == 0 ? "horizontal : " : "vertical : ");
        String string = sb.toString();
        Iterator<WidgetRun> it = this.k.iterator();
        while (it.hasNext()) {
            String str = string + "<";
            string = (str + it.next()) + "> ";
        }
        return string;
    }
}
