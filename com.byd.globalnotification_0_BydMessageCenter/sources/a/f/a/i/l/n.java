package a.f.a.i.l;

import a.b.k.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f600f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f602b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f603c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a.f.a.i.d> f601a = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<a> f604d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f605e = -1;

    public class a {
        public a(n nVar, a.f.a.i.d dVar, a.f.a.d dVar2, int i) {
            new WeakReference(dVar);
            dVar2.o(dVar.L);
            dVar2.o(dVar.M);
            dVar2.o(dVar.N);
            dVar2.o(dVar.O);
            dVar2.o(dVar.P);
        }
    }

    public n(int i) {
        this.f602b = -1;
        this.f603c = 0;
        int i2 = f600f;
        f600f = i2 + 1;
        this.f602b = i2;
        this.f603c = i;
    }

    public boolean a(a.f.a.i.d dVar) {
        if (this.f601a.contains(dVar)) {
            return false;
        }
        this.f601a.add(dVar);
        return true;
    }

    public void b(ArrayList<n> arrayList) {
        int size = this.f601a.size();
        if (this.f605e != -1 && size > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                n nVar = arrayList.get(i);
                if (this.f605e == nVar.f602b) {
                    d(this.f603c, nVar);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public int c(a.f.a.d dVar, int i) {
        int iO;
        a.f.a.i.c cVar;
        if (this.f601a.size() == 0) {
            return 0;
        }
        ArrayList<a.f.a.i.d> arrayList = this.f601a;
        a.f.a.i.e eVar = (a.f.a.i.e) arrayList.get(0).X;
        dVar.u();
        eVar.d(dVar, false);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.get(i2).d(dVar, false);
        }
        if (i == 0 && eVar.E0 > 0) {
            l.i.b(eVar, dVar, arrayList, 0);
        }
        if (i == 1 && eVar.F0 > 0) {
            l.i.b(eVar, dVar, arrayList, 1);
        }
        try {
            dVar.q();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f604d = new ArrayList<>();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.f604d.add(new a(this, arrayList.get(i3), dVar, i));
        }
        if (i == 0) {
            iO = dVar.o(eVar.L);
            cVar = eVar.N;
        } else {
            iO = dVar.o(eVar.M);
            cVar = eVar.O;
        }
        int iO2 = dVar.o(cVar);
        dVar.u();
        return iO2 - iO;
    }

    public void d(int i, n nVar) {
        for (a.f.a.i.d dVar : this.f601a) {
            nVar.a(dVar);
            if (i == 0) {
                dVar.t0 = nVar.f602b;
            } else {
                dVar.u0 = nVar.f602b;
            }
        }
        this.f605e = nVar.f602b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.f603c;
        sb.append(i == 0 ? "Horizontal" : i == 1 ? "Vertical" : i == 2 ? "Both" : "Unknown");
        sb.append(" [");
        sb.append(this.f602b);
        sb.append("] <");
        String string = sb.toString();
        Iterator<a.f.a.i.d> it = this.f601a.iterator();
        while (it.hasNext()) {
            string = string + " " + it.next().m0;
        }
        return b.a.a.a.a.c(string, " >");
    }
}
