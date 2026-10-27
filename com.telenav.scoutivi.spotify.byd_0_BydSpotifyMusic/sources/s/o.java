package s;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: WidgetGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static int f9437g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f9439b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f9441d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ArrayList<r.e> f9438a = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f9440c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ArrayList<a> f9442e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f9443f = -1;

    /* JADX INFO: compiled from: WidgetGroup.java */
    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<r.e> f9444a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f9445b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f9446c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f9447d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f9448e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f9449f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f9450g;

        public a(r.e eVar, o.d dVar, int i7) {
            this.f9444a = new WeakReference<>(eVar);
            this.f9445b = dVar.x(eVar.Q);
            this.f9446c = dVar.x(eVar.R);
            this.f9447d = dVar.x(eVar.S);
            this.f9448e = dVar.x(eVar.T);
            this.f9449f = dVar.x(eVar.U);
            this.f9450g = i7;
        }
    }

    public o(int i7) {
        int i8 = f9437g;
        f9437g = i8 + 1;
        this.f9439b = i8;
        this.f9441d = i7;
    }

    private String e() {
        int i7 = this.f9441d;
        return i7 == 0 ? "Horizontal" : i7 == 1 ? "Vertical" : i7 == 2 ? "Both" : "Unknown";
    }

    private int j(o.d dVar, ArrayList<r.e> arrayList, int i7) {
        int iX;
        int iX2;
        r.f fVar = (r.f) arrayList.get(0).M();
        dVar.D();
        fVar.g(dVar, false);
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            arrayList.get(i8).g(dVar, false);
        }
        if (i7 == 0 && fVar.f9264f1 > 0) {
            r.b.b(fVar, dVar, arrayList, 0);
        }
        if (i7 == 1 && fVar.f9265g1 > 0) {
            r.b.b(fVar, dVar, arrayList, 1);
        }
        try {
            dVar.z();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        this.f9442e = new ArrayList<>();
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            this.f9442e.add(new a(arrayList.get(i9), dVar, i7));
        }
        if (i7 == 0) {
            iX = dVar.x(fVar.Q);
            iX2 = dVar.x(fVar.S);
            dVar.D();
        } else {
            iX = dVar.x(fVar.R);
            iX2 = dVar.x(fVar.T);
            dVar.D();
        }
        return iX2 - iX;
    }

    public boolean a(r.e eVar) {
        if (this.f9438a.contains(eVar)) {
            return false;
        }
        this.f9438a.add(eVar);
        return true;
    }

    public void b(ArrayList<o> arrayList) {
        int size = this.f9438a.size();
        if (this.f9443f != -1 && size > 0) {
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                o oVar = arrayList.get(i7);
                if (this.f9443f == oVar.f9439b) {
                    g(this.f9441d, oVar);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public int c() {
        return this.f9439b;
    }

    public int d() {
        return this.f9441d;
    }

    public int f(o.d dVar, int i7) {
        if (this.f9438a.size() == 0) {
            return 0;
        }
        return j(dVar, this.f9438a, i7);
    }

    public void g(int i7, o oVar) {
        for (r.e eVar : this.f9438a) {
            oVar.a(eVar);
            if (i7 == 0) {
                eVar.R0 = oVar.c();
            } else {
                eVar.S0 = oVar.c();
            }
        }
        this.f9443f = oVar.f9439b;
    }

    public void h(boolean z6) {
        this.f9440c = z6;
    }

    public void i(int i7) {
        this.f9441d = i7;
    }

    public String toString() {
        String str = e() + " [" + this.f9439b + "] <";
        Iterator<r.e> it = this.f9438a.iterator();
        while (it.hasNext()) {
            str = str + " " + it.next().v();
        }
        return str + " >";
    }
}
