package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ChainRun.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends WidgetRun {
    ArrayList<WidgetRun> k;
    private int l;

    public c(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.k = new ArrayList<>();
        this.f = i;
        q();
    }

    private void q() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.f837b;
        ConstraintWidget constraintWidgetN = constraintWidget2.N(this.f);
        while (true) {
            ConstraintWidget constraintWidget3 = constraintWidgetN;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                constraintWidgetN = constraintWidget2.N(this.f);
            }
        }
        this.f837b = constraintWidget;
        this.k.add(constraintWidget.P(this.f));
        ConstraintWidget constraintWidgetL = constraintWidget.L(this.f);
        while (constraintWidgetL != null) {
            this.k.add(constraintWidgetL.P(this.f));
            constraintWidgetL = constraintWidgetL.L(this.f);
        }
        for (WidgetRun widgetRun : this.k) {
            int i = this.f;
            if (i == 0) {
                widgetRun.f837b.f825d = this;
            } else if (i == 1) {
                widgetRun.f837b.f826e = this;
            }
        }
        if ((this.f == 0 && ((androidx.constraintlayout.core.widgets.d) this.f837b.M()).S1()) && this.k.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.k;
            this.f837b = arrayList.get(arrayList.size() - 1).f837b;
        }
        this.l = this.f == 0 ? this.f837b.B() : this.f837b.U();
    }

    private ConstraintWidget r() {
        for (int i = 0; i < this.k.size(); i++) {
            WidgetRun widgetRun = this.k.get(i);
            if (widgetRun.f837b.X() != 8) {
                return widgetRun.f837b;
            }
        }
        return null;
    }

    private ConstraintWidget s() {
        for (int size = this.k.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.k.get(size);
            if (widgetRun.f837b.X() != 8) {
                return widgetRun.f837b;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e9  */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.constraintlayout.core.widgets.analyzer.d r27) {
        /*
            Method dump skipped, instruction units count: 1064
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.c.a(androidx.constraintlayout.core.widgets.analyzer.d):void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        Iterator<WidgetRun> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        int size = this.k.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.k.get(0).f837b;
        ConstraintWidget constraintWidget2 = this.k.get(size - 1).f837b;
        if (this.f == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.R;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.T;
            DependencyNode dependencyNodeI = i(constraintAnchor, 0);
            int iF = constraintAnchor.f();
            ConstraintWidget constraintWidgetR = r();
            if (constraintWidgetR != null) {
                iF = constraintWidgetR.R.f();
            }
            if (dependencyNodeI != null) {
                b(this.h, dependencyNodeI, iF);
            }
            DependencyNode dependencyNodeI2 = i(constraintAnchor2, 0);
            int iF2 = constraintAnchor2.f();
            ConstraintWidget constraintWidgetS = s();
            if (constraintWidgetS != null) {
                iF2 = constraintWidgetS.T.f();
            }
            if (dependencyNodeI2 != null) {
                b(this.i, dependencyNodeI2, -iF2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.S;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.U;
            DependencyNode dependencyNodeI3 = i(constraintAnchor3, 1);
            int iF3 = constraintAnchor3.f();
            ConstraintWidget constraintWidgetR2 = r();
            if (constraintWidgetR2 != null) {
                iF3 = constraintWidgetR2.S.f();
            }
            if (dependencyNodeI3 != null) {
                b(this.h, dependencyNodeI3, iF3);
            }
            DependencyNode dependencyNodeI4 = i(constraintAnchor4, 1);
            int iF4 = constraintAnchor4.f();
            ConstraintWidget constraintWidgetS2 = s();
            if (constraintWidgetS2 != null) {
                iF4 = constraintWidgetS2.U.f();
            }
            if (dependencyNodeI4 != null) {
                b(this.i, dependencyNodeI4, -iF4);
            }
        }
        this.h.f830a = this;
        this.i.f830a = this;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        for (int i = 0; i < this.k.size(); i++) {
            this.k.get(i).e();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f838c = null;
        Iterator<WidgetRun> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long j() {
        int size = this.k.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.k.get(i);
            j = j + ((long) widgetRun.h.f) + widgetRun.j() + ((long) widgetRun.i.f);
        }
        return j;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (!this.k.get(i).m()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f == 0 ? "horizontal : " : "vertical : ");
        for (WidgetRun widgetRun : this.k) {
            sb.append("<");
            sb.append(widgetRun);
            sb.append("> ");
        }
        return sb.toString();
    }
}
