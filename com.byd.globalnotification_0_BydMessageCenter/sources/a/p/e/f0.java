package a.p.e;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a.e.h<RecyclerView.b0, a> f1166a = new a.e.h<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.e.e<RecyclerView.b0> f1167b = new a.e.e<>();

    public static class a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static a.h.j.d<a> f1168d = new a.h.j.d<>(20);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1169a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.j.c f1170b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RecyclerView.j.c f1171c;

        public static a a() {
            a aVarA = f1168d.a();
            return aVarA == null ? new a() : aVarA;
        }

        public static void b(a aVar) {
            aVar.f1169a = 0;
            aVar.f1170b = null;
            aVar.f1171c = null;
            f1168d.b(aVar);
        }
    }

    public interface b {
    }

    public void a(RecyclerView.b0 b0Var) {
        a orDefault = this.f1166a.getOrDefault(b0Var, null);
        if (orDefault == null) {
            orDefault = a.a();
            this.f1166a.put(b0Var, orDefault);
        }
        orDefault.f1169a |= 1;
    }

    public void b(RecyclerView.b0 b0Var, RecyclerView.j.c cVar) {
        a orDefault = this.f1166a.getOrDefault(b0Var, null);
        if (orDefault == null) {
            orDefault = a.a();
            this.f1166a.put(b0Var, orDefault);
        }
        orDefault.f1171c = cVar;
        orDefault.f1169a |= 8;
    }

    public void c(RecyclerView.b0 b0Var, RecyclerView.j.c cVar) {
        a orDefault = this.f1166a.getOrDefault(b0Var, null);
        if (orDefault == null) {
            orDefault = a.a();
            this.f1166a.put(b0Var, orDefault);
        }
        orDefault.f1170b = cVar;
        orDefault.f1169a |= 4;
    }

    public boolean d(RecyclerView.b0 b0Var) {
        a orDefault = this.f1166a.getOrDefault(b0Var, null);
        return (orDefault == null || (orDefault.f1169a & 1) == 0) ? false : true;
    }

    public final RecyclerView.j.c e(RecyclerView.b0 b0Var, int i) {
        a aVarK;
        RecyclerView.j.c cVar;
        int iE = this.f1166a.e(b0Var);
        if (iE >= 0 && (aVarK = this.f1166a.k(iE)) != null) {
            int i2 = aVarK.f1169a;
            if ((i2 & i) != 0) {
                aVarK.f1169a = (~i) & i2;
                if (i == 4) {
                    cVar = aVarK.f1170b;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVarK.f1171c;
                }
                if ((aVarK.f1169a & 12) == 0) {
                    this.f1166a.i(iE);
                    a.b(aVarK);
                }
                return cVar;
            }
        }
        return null;
    }

    public void f(RecyclerView.b0 b0Var) {
        a orDefault = this.f1166a.getOrDefault(b0Var, null);
        if (orDefault == null) {
            return;
        }
        orDefault.f1169a &= -2;
    }

    public void g(RecyclerView.b0 b0Var) {
        int iG = this.f1167b.g() - 1;
        while (true) {
            if (iG < 0) {
                break;
            }
            if (b0Var == this.f1167b.h(iG)) {
                a.e.e<RecyclerView.b0> eVar = this.f1167b;
                Object[] objArr = eVar.f462c;
                Object obj = objArr[iG];
                Object obj2 = a.e.e.f459e;
                if (obj != obj2) {
                    objArr[iG] = obj2;
                    eVar.f460a = true;
                }
            } else {
                iG--;
            }
        }
        a aVarRemove = this.f1166a.remove(b0Var);
        if (aVarRemove != null) {
            a.b(aVarRemove);
        }
    }
}
