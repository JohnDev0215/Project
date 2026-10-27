package g.a.a.v2.e;

import g.a.a.e;
import java.util.Enumeration;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements g.a.a.v2.d {
    public static Hashtable e(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration enumerationKeys = hashtable.keys();
        while (enumerationKeys.hasMoreElements()) {
            Object objNextElement = enumerationKeys.nextElement();
            hashtable2.put(objNextElement, hashtable.get(objNextElement));
        }
        return hashtable2;
    }

    @Override // g.a.a.v2.d
    public boolean a(g.a.a.v2.c cVar, g.a.a.v2.c cVar2) {
        g.a.a.v2.b[] bVarArrJ = cVar.j();
        g.a.a.v2.b[] bVarArrJ2 = cVar2.j();
        if (bVarArrJ.length != bVarArrJ2.length) {
            return false;
        }
        boolean z = (bVarArrJ[0].g() == null || bVarArrJ2[0].g() == null) ? false : !bVarArrJ[0].g().h().equals(bVarArrJ2[0].g().h());
        for (int i2 = 0; i2 != bVarArrJ.length; i2++) {
            if (!f(z, bVarArrJ[i2], bVarArrJ2)) {
                return false;
            }
        }
        return true;
    }

    @Override // g.a.a.v2.d
    public int b(g.a.a.v2.c cVar) {
        g.a.a.v2.b[] bVarArrJ = cVar.j();
        int iHashCode = 0;
        for (int i2 = 0; i2 != bVarArrJ.length; i2++) {
            if (bVarArrJ[i2].j()) {
                g.a.a.v2.a[] aVarArrI = bVarArrJ[i2].i();
                for (int i3 = 0; i3 != aVarArrI.length; i3++) {
                    iHashCode = (iHashCode ^ aVarArrI[i3].h().hashCode()) ^ d(aVarArrI[i3].i());
                }
            } else {
                iHashCode = (iHashCode ^ bVarArrJ[i2].g().h().hashCode()) ^ d(bVarArrJ[i2].g().i());
            }
        }
        return iHashCode;
    }

    public final int d(e eVar) {
        return c.e(c.i(eVar)).hashCode();
    }

    public final boolean f(boolean z, g.a.a.v2.b bVar, g.a.a.v2.b[] bVarArr) {
        if (z) {
            for (int length = bVarArr.length - 1; length >= 0; length--) {
                if (bVarArr[length] != null && g(bVar, bVarArr[length])) {
                    bVarArr[length] = null;
                    return true;
                }
            }
        } else {
            for (int i2 = 0; i2 != bVarArr.length; i2++) {
                if (bVarArr[i2] != null && g(bVar, bVarArr[i2])) {
                    bVarArr[i2] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean g(g.a.a.v2.b bVar, g.a.a.v2.b bVar2) {
        return c.g(bVar, bVar2);
    }
}
