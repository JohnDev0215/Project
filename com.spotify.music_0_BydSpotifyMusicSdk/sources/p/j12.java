package p;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j12 implements uv2 {
    public final LinkedHashMap c = new LinkedHashMap();
    public int e;

    @Override // p.uv2
    public final synchronized void a(int i) {
        if (i >= 10 && i != 20) {
            b();
        }
    }

    public final void b() {
        WeakReference weakReference;
        this.e = 0;
        Iterator it = this.c.values().iterator();
        while (it.hasNext()) {
            ArrayList arrayList = (ArrayList) it.next();
            if (arrayList.size() <= 1) {
                i12 i12Var = (i12) ou.d0(arrayList);
                if (((i12Var == null || (weakReference = i12Var.b) == null) ? null : (Bitmap) weakReference.get()) == null) {
                    it.remove();
                }
            } else {
                int size = arrayList.size();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    int i3 = i2 - i;
                    if (((i12) arrayList.get(i3)).b.get() == null) {
                        arrayList.remove(i3);
                        i++;
                    }
                }
                if (arrayList.isEmpty()) {
                    it.remove();
                }
            }
        }
    }

    @Override // p.uv2
    public final synchronized ni1 c(mi1 mi1Var) {
        try {
            ArrayList arrayList = (ArrayList) this.c.get(mi1Var);
            ni1 ni1Var = null;
            if (arrayList == null) {
                return null;
            }
            int size = arrayList.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                i12 i12Var = (i12) arrayList.get(i);
                Bitmap bitmap = (Bitmap) i12Var.b.get();
                ni1 ni1Var2 = bitmap != null ? new ni1(bitmap, i12Var.c) : null;
                if (ni1Var2 != null) {
                    ni1Var = ni1Var2;
                    break;
                }
                i++;
            }
            int i2 = this.e;
            this.e = i2 + 1;
            if (i2 >= 10) {
                b();
            }
            return ni1Var;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // p.uv2
    public final synchronized void g(mi1 mi1Var, Bitmap bitmap, Map map, int i) {
        try {
            LinkedHashMap linkedHashMap = this.c;
            Object arrayList = linkedHashMap.get(mi1Var);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(mi1Var, arrayList);
            }
            ArrayList arrayList2 = (ArrayList) arrayList;
            int iIdentityHashCode = System.identityHashCode(bitmap);
            i12 i12Var = new i12(iIdentityHashCode, new WeakReference(bitmap), map, i);
            int size = arrayList2.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    arrayList2.add(i12Var);
                    break;
                }
                i12 i12Var2 = (i12) arrayList2.get(i2);
                if (i < i12Var2.d) {
                    i2++;
                } else if (i12Var2.a == iIdentityHashCode && i12Var2.b.get() == bitmap) {
                    arrayList2.set(i2, i12Var);
                } else {
                    arrayList2.add(i2, i12Var);
                }
            }
            int i3 = this.e;
            this.e = i3 + 1;
            if (i3 >= 10) {
                b();
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
