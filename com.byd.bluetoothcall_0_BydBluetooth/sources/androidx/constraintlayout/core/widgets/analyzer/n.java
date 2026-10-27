package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: WidgetGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static int f866a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f868c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f870e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<ConstraintWidget> f867b = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f869d = false;
    ArrayList<a> f = null;
    private int g = -1;

    /* JADX INFO: compiled from: WidgetGroup.java */
    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<ConstraintWidget> f871a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f872b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f873c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f874d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f875e;
        int f;
        int g;

        public a(ConstraintWidget constraintWidget, androidx.constraintlayout.core.d dVar, int i) {
            this.f871a = new WeakReference<>(constraintWidget);
            this.f872b = dVar.x(constraintWidget.R);
            this.f873c = dVar.x(constraintWidget.S);
            this.f874d = dVar.x(constraintWidget.T);
            this.f875e = dVar.x(constraintWidget.U);
            this.f = dVar.x(constraintWidget.V);
            this.g = i;
        }
    }

    public n(int i) {
        this.f868c = -1;
        this.f870e = 0;
        int i2 = f866a;
        f866a = i2 + 1;
        this.f868c = i2;
        this.f870e = i;
    }

    private String e() {
        int i = this.f870e;
        return i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown";
    }

    private int j(androidx.constraintlayout.core.d dVar, ArrayList<ConstraintWidget> arrayList, int i) {
        int iX;
        int iX2;
        androidx.constraintlayout.core.widgets.d dVar2 = (androidx.constraintlayout.core.widgets.d) arrayList.get(0).M();
        dVar.D();
        dVar2.g(dVar, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).g(dVar, false);
        }
        if (i == 0 && dVar2.g1 > 0) {
            androidx.constraintlayout.core.widgets.b.b(dVar2, dVar, arrayList, 0);
        }
        if (i == 1 && dVar2.h1 > 0) {
            androidx.constraintlayout.core.widgets.b.b(dVar2, dVar, arrayList, 1);
        }
        try {
            dVar.z();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f = new ArrayList<>();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.f.add(new a(arrayList.get(i3), dVar, i));
        }
        if (i == 0) {
            iX = dVar.x(dVar2.R);
            iX2 = dVar.x(dVar2.T);
            dVar.D();
        } else {
            iX = dVar.x(dVar2.S);
            iX2 = dVar.x(dVar2.U);
            dVar.D();
        }
        return iX2 - iX;
    }

    public boolean a(ConstraintWidget constraintWidget) {
        if (this.f867b.contains(constraintWidget)) {
            return false;
        }
        this.f867b.add(constraintWidget);
        return true;
    }

    public void b(ArrayList<n> arrayList) {
        int size = this.f867b.size();
        if (this.g != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                n nVar = arrayList.get(i);
                if (this.g == nVar.f868c) {
                    g(this.f870e, nVar);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public int c() {
        return this.f868c;
    }

    public int d() {
        return this.f870e;
    }

    public int f(androidx.constraintlayout.core.d dVar, int i) {
        if (this.f867b.size() == 0) {
            return 0;
        }
        return j(dVar, this.f867b, i);
    }

    public void g(int i, n nVar) {
        for (ConstraintWidget constraintWidget : this.f867b) {
            nVar.a(constraintWidget);
            if (i == 0) {
                constraintWidget.S0 = nVar.c();
            } else {
                constraintWidget.T0 = nVar.c();
            }
        }
        this.g = nVar.f868c;
    }

    public void h(boolean z) {
        this.f869d = z;
    }

    public void i(int i) {
        this.f870e = i;
    }

    public String toString() {
        String str = e() + " [" + this.f868c + "] <";
        Iterator<ConstraintWidget> it = this.f867b.iterator();
        while (it.hasNext()) {
            str = str + " " + it.next().v();
        }
        return str + " >";
    }
}
