package defpackage;

import android.util.SparseArray;
import defpackage.di1;
import defpackage.yz1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.b;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ModalDialogManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bi1 {
    public final SparseArray<b> a;
    public final HashSet b;
    public final b c;
    public b d;
    public int e;
    public int f;
    public boolean g;
    public final org.chromium.base.b<a> h;
    public final HashMap i;
    public final yz1 j;

    /* JADX INFO: compiled from: ModalDialogManager.java */
    public static abstract class b {
        public Callback<Integer> e;
        public PropertyModel f;

        public static void a(b bVar, PropertyModel propertyModel, xm1 xm1Var) {
            if (propertyModel == null) {
                bVar.e(bVar.f);
                bVar.f = null;
                bVar.e = null;
            } else {
                bVar.f = propertyModel;
                bVar.e = xm1Var;
                bVar.b(propertyModel);
            }
        }

        public abstract void b(PropertyModel propertyModel);

        public final void d(int i) {
            Callback<Integer> callback = this.e;
            if (callback == null) {
                return;
            }
            this.e = null;
            callback.onResult(Integer.valueOf(i));
        }

        public abstract void e(PropertyModel propertyModel);
    }

    public bi1(AppModalPresenter appModalPresenter) {
        SparseArray<b> sparseArray = new SparseArray<>();
        this.a = sparseArray;
        this.b = new HashSet();
        this.h = new org.chromium.base.b<>();
        HashMap map = new HashMap();
        this.i = map;
        this.j = new yz1();
        this.c = appModalPresenter;
        sparseArray.put(1, appModalPresenter);
        map.put(1, new mb3(new ve3(29, this)));
        map.put(0, new mb3(new i22(12, this)));
    }

    public final void a() {
        for (int i = 0; i <= 1; i++) {
            d(i, 8);
        }
        if (f()) {
            b(8, this.d.f);
        }
        this.h.clear();
    }

    public final void b(int i, PropertyModel propertyModel) {
        if (propertyModel == null) {
            return;
        }
        b bVar = this.d;
        boolean z = true;
        org.chromium.base.b<a> bVar2 = this.h;
        if (bVar == null || propertyModel != bVar.f) {
            HashMap<Integer, List<PropertyModel>> map = this.j.a;
            Iterator<Map.Entry<Integer, List<PropertyModel>>> it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Map.Entry<Integer, List<PropertyModel>> next = it.next();
                List<PropertyModel> value = next.getValue();
                for (int i2 = 0; i2 < value.size(); i2++) {
                    if (value.get(i2) == propertyModel) {
                        value.remove(i2);
                        if (value.isEmpty()) {
                            map.remove(next.getKey());
                        }
                    }
                }
            }
            if (!z) {
                return;
            }
            ((di1.a) propertyModel.j(di1.a)).b(i);
            Iterator<a> it2 = bVar2.iterator();
            while (true) {
                b.a aVar = (b.a) it2;
                if (!aVar.hasNext()) {
                    e();
                    return;
                }
                ((a) aVar.next()).k();
            }
        } else {
            if (!f() || this.g) {
                return;
            }
            this.g = true;
            ((di1.a) propertyModel.j(di1.a)).b(i);
            b.a(this.d, null, null);
            Iterator<a> it3 = bVar2.iterator();
            while (true) {
                b.a aVar2 = (b.a) it3;
                if (!aVar2.hasNext()) {
                    this.d = null;
                    this.f = 1;
                    this.g = false;
                    e();
                    k();
                    return;
                }
                ((a) aVar2.next()).k();
            }
        }
    }

    public final void c(int i) {
        d(0, i);
        if (f() && this.e == 0) {
            b(i, this.d.f);
        }
    }

    public final void d(int i, int i2) {
        int i3 = 1;
        while (true) {
            yz1 yz1Var = this.j;
            if (i3 > 3) {
                yz1Var.getClass();
                return;
            }
            yz1Var.getClass();
            Integer numValueOf = Integer.valueOf((i * 10) + i3);
            HashMap<Integer, List<PropertyModel>> map = yz1Var.a;
            List<PropertyModel> list = map.get(numValueOf);
            if (list != null) {
                for (int i4 = 0; i4 < list.size(); i4++) {
                    ((di1.a) list.get(i4).j(di1.a)).b(i2);
                    Iterator<a> it = this.h.iterator();
                    while (true) {
                        b.a aVar = (b.a) it;
                        if (aVar.hasNext()) {
                            ((a) aVar.next()).k();
                        }
                    }
                    e();
                }
                map.remove(numValueOf);
            }
            i3++;
        }
    }

    public final void e() {
        boolean z;
        Iterator<Map.Entry<Integer, List<PropertyModel>>> it = this.j.a.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            } else if (!it.next().getValue().isEmpty()) {
                z = false;
                break;
            }
        }
        if (!z) {
            return;
        }
        Iterator<a> it2 = this.h.iterator();
        while (true) {
            b.a aVar = (b.a) it2;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).d();
            }
        }
    }

    public final boolean f() {
        return this.d != null;
    }

    public final void g(int i, int i2) {
        ((mb3) this.i.get(Integer.valueOf(i))).c(i2);
    }

    public final void h(int i) {
        if (((mb3) this.i.get(Integer.valueOf(i))).b()) {
            return;
        }
        this.b.remove(Integer.valueOf(i));
        if (f()) {
            return;
        }
        k();
    }

    public final void i(int i, int i2, PropertyModel propertyModel, boolean z) {
        if (iv.e().g("enable-screenshot-ui-mode")) {
            return;
        }
        yz1 yz1Var = this.j;
        if (i2 == 3) {
            if (f() && this.f >= i2) {
                yz1Var.a(i, i2, propertyModel, z);
                return;
            }
        } else if (this.b.contains(Integer.valueOf(i)) || (f() && this.f >= i2)) {
            yz1Var.a(i, i2, propertyModel, z);
            return;
        }
        if (f()) {
            b bVar = this.d;
            PropertyModel propertyModel2 = bVar.f;
            b.a(bVar, null, null);
            this.d = null;
            yz1Var.a(this.e, this.f, propertyModel2, true);
        }
        this.e = i;
        this.f = i2;
        b bVar2 = this.a.get(i, this.c);
        this.d = bVar2;
        b.a(bVar2, propertyModel, new xm1(this, 15, propertyModel));
        Iterator<a> it = this.h.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).e();
            }
        }
    }

    public final void j(PropertyModel propertyModel, int i, boolean z) {
        int i2 = 1;
        if (i != 0 && i == 1) {
            i2 = 2;
        }
        i(i, i2, propertyModel, z);
    }

    public final void k() {
        yz1.a aVar;
        Integer numValueOf;
        HashMap<Integer, List<PropertyModel>> map;
        List<PropertyModel> list;
        HashSet hashSet = this.b;
        yz1 yz1Var = this.j;
        yz1Var.getClass();
        int i = 3;
        while (true) {
            if (i < 1) {
                aVar = null;
                break;
            }
            for (int i2 = 1; i2 >= 0; i2--) {
                if (!hashSet.contains(Integer.valueOf(i2)) && (list = (map = yz1Var.a).get((numValueOf = Integer.valueOf((i2 * 10) + i)))) != null && !list.isEmpty()) {
                    PropertyModel propertyModel = list.get(0);
                    list.remove(0);
                    if (list.isEmpty()) {
                        map.remove(numValueOf);
                    }
                    aVar = new yz1.a(propertyModel, i2, i);
                }
            }
            i--;
        }
        if (aVar == null) {
            return;
        }
        i(aVar.b, aVar.c, aVar.a, false);
    }

    public final int l(int i) {
        this.b.add(Integer.valueOf(i));
        if (f() && i == this.e && this.f != 3) {
            b bVar = this.d;
            PropertyModel propertyModel = bVar.f;
            b.a(bVar, null, null);
            this.d = null;
            this.j.a(this.e, this.f, propertyModel, true);
            k();
        }
        return ((mb3) this.i.get(Integer.valueOf(i))).a();
    }

    /* JADX INFO: compiled from: ModalDialogManager.java */
    public interface a {
        default void d() {
        }

        default void e() {
        }

        default void k() {
        }
    }
}
