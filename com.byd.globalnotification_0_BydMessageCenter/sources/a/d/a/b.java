package a.d.a;

import android.content.res.ColorStateList;

/* JADX INFO: loaded from: classes.dex */
public class b implements d {
    public final e a(c cVar) {
        return (e) cVar.c();
    }

    public float b(c cVar) {
        throw null;
    }

    public float c(c cVar) {
        throw null;
    }

    public void d(c cVar, ColorStateList colorStateList) {
        throw null;
    }

    public void e(c cVar, float f2) {
        e eVarA = a(cVar);
        boolean zA = cVar.a();
        boolean zD = cVar.d();
        if (f2 != eVarA.f442b || eVarA.f443c != zA || eVarA.f444d != zD) {
            eVarA.f442b = f2;
            eVarA.f443c = zA;
            eVarA.f444d = zD;
            eVarA.b(null);
            throw null;
        }
        if (!cVar.a()) {
            cVar.b(0, 0, 0, 0);
            return;
        }
        float f3 = a(cVar).f442b;
        float f4 = a(cVar).f441a;
        int iCeil = (int) Math.ceil(f.a(f3, f4, cVar.d()));
        int iCeil2 = (int) Math.ceil(f.b(f3, f4, cVar.d()));
        cVar.b(iCeil, iCeil2, iCeil, iCeil2);
    }
}
