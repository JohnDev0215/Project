package a.f.a.i.l;

import a.f.a.i.d;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<a.f.a.i.d> f567a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f568b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a.f.a.i.e f569c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d.a f570a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d.a f571b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f572c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f573d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f574e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f575f;
        public int g;
        public boolean h;
        public boolean i;
        public int j;
    }

    /* JADX INFO: renamed from: a.f.a.i.l.b$b, reason: collision with other inner class name */
    public interface InterfaceC0007b {
    }

    public b(a.f.a.i.e eVar) {
        this.f569c = eVar;
    }

    public final boolean a(InterfaceC0007b interfaceC0007b, a.f.a.i.d dVar, int i) {
        d.a aVar = d.a.FIXED;
        d.a aVar2 = d.a.MATCH_CONSTRAINT;
        this.f568b.f570a = dVar.m();
        this.f568b.f571b = dVar.t();
        this.f568b.f572c = dVar.u();
        this.f568b.f573d = dVar.l();
        a aVar3 = this.f568b;
        aVar3.i = false;
        aVar3.j = i;
        boolean z = aVar3.f570a == aVar2;
        boolean z2 = this.f568b.f571b == aVar2;
        boolean z3 = z && dVar.a0 > 0.0f;
        boolean z4 = z2 && dVar.a0 > 0.0f;
        if (z3 && dVar.v[0] == 4) {
            this.f568b.f570a = aVar;
        }
        if (z4 && dVar.v[1] == 4) {
            this.f568b.f571b = aVar;
        }
        ((ConstraintLayout.b) interfaceC0007b).b(dVar, this.f568b);
        dVar.S(this.f568b.f574e);
        dVar.N(this.f568b.f575f);
        a aVar4 = this.f568b;
        dVar.G = aVar4.h;
        dVar.K(aVar4.g);
        a aVar5 = this.f568b;
        aVar5.j = 0;
        return aVar5.i;
    }

    public final void b(a.f.a.i.e eVar, int i, int i2, int i3) {
        int i4 = eVar.f0;
        int i5 = eVar.g0;
        eVar.Q(0);
        eVar.P(0);
        eVar.Y = i2;
        int i6 = eVar.f0;
        if (i2 < i6) {
            eVar.Y = i6;
        }
        eVar.Z = i3;
        int i7 = eVar.g0;
        if (i3 < i7) {
            eVar.Z = i7;
        }
        eVar.Q(i4);
        eVar.P(i5);
        a.f.a.i.e eVar2 = this.f569c;
        eVar2.y0 = i;
        eVar2.V();
    }

    public void c(a.f.a.i.e eVar) {
        d.a aVar = d.a.MATCH_CONSTRAINT;
        this.f567a.clear();
        int size = eVar.v0.size();
        for (int i = 0; i < size; i++) {
            a.f.a.i.d dVar = eVar.v0.get(i);
            if (dVar.m() == aVar || dVar.t() == aVar) {
                this.f567a.add(dVar);
            }
        }
        eVar.d0();
    }
}
