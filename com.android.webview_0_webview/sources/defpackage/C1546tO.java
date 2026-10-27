package defpackage;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: tO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1546tO {
    public final AbstractC1491sO d;
    public AbstractC1491sO e;
    public int f;
    public boolean g;
    public final SparseArray a = new SparseArray();
    public final SparseArray b = new SparseArray();
    public final Set c = new HashSet();
    public final Ys h = new Ys();
    public final Map i = new HashMap();

    public C1546tO(AbstractC1491sO abstractC1491sO, int i) {
        this.d = abstractC1491sO;
        this.a.put(i, abstractC1491sO);
        this.i.put(0, new C0995jP(new Runnable(this) { // from class: oO
            public final C1546tO s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.d(0);
            }
        }));
        this.i.put(1, new C0995jP(new Runnable(this) { // from class: pO
            public final C1546tO s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.d(1);
            }
        }));
    }

    public boolean a(int i, int i2) {
        if (!c() || i != this.f) {
            return false;
        }
        b(this.e.b, i2);
        return true;
    }

    public void b(JO jo, int i) {
        FO fo = AbstractC1711wO.a;
        if (jo == null) {
            return;
        }
        AbstractC1491sO abstractC1491sO = this.e;
        if (abstractC1491sO == null || jo != abstractC1491sO.b) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                List list = (List) this.b.valueAt(i2);
                for (int i3 = 0; i3 < list.size(); i3++) {
                    if (list.get(i3) == jo) {
                        ((InterfaceC1656vO) ((JO) list.remove(i3)).a(fo)).b(jo, i);
                        return;
                    }
                }
            }
            return;
        }
        if (!c() || this.g) {
            return;
        }
        this.g = true;
        ((InterfaceC1656vO) jo.a(fo)).b(jo, i);
        Iterator it = this.h.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                AbstractC1491sO.a(this.e, null, null);
                this.e = null;
                this.g = false;
                f();
                return;
            }
            ((HP) ((InterfaceC1436rO) ws.next())).b(false);
        }
    }

    public boolean c() {
        return this.e != null;
    }

    public final void d(int i) {
        if (!((C0995jP) this.i.get(Integer.valueOf(i))).a.isEmpty()) {
            return;
        }
        this.c.remove(Integer.valueOf(i));
        if (c()) {
            return;
        }
        f();
    }

    public void e(final JO jo, int i, boolean z) {
        if (Es.e().h("enable-screenshot-ui-mode")) {
            return;
        }
        List list = (List) this.b.get(i);
        if (list == null) {
            SparseArray sparseArray = this.b;
            ArrayList arrayList = new ArrayList();
            sparseArray.put(i, arrayList);
            list = arrayList;
        }
        if (this.c.contains(Integer.valueOf(i)) || (c() && this.f <= i)) {
            list.add(z ? 0 : list.size(), jo);
            return;
        }
        if (c()) {
            AbstractC1491sO abstractC1491sO = this.e;
            JO jo2 = abstractC1491sO.b;
            AbstractC1491sO.a(abstractC1491sO, null, null);
            this.e = null;
            ((List) this.b.get(this.f)).add(0, jo2);
        }
        this.f = i;
        AbstractC1491sO abstractC1491sO2 = (AbstractC1491sO) this.a.get(i, this.d);
        this.e = abstractC1491sO2;
        AbstractC1491sO.a(abstractC1491sO2, jo, new Callback(this, jo) { // from class: qO
            public final C1546tO a;
            public final JO b;

            {
                this.a = this;
                this.b = jo;
            }

            @Override // org.chromium.base.Callback
            public void a(Object obj) {
                C1546tO c1546tO = this.a;
                JO jo3 = this.b;
                Objects.requireNonNull(c1546tO);
                c1546tO.b(jo3, ((Integer) obj).intValue());
            }
        });
        Iterator it = this.h.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((HP) ((InterfaceC1436rO) ws.next())).b(true);
            }
        }
    }

    public final void f() {
        for (int i = 0; i < this.b.size(); i++) {
            int iKeyAt = this.b.keyAt(i);
            if (!this.c.contains(Integer.valueOf(iKeyAt))) {
                List list = (List) this.b.valueAt(i);
                if (!list.isEmpty()) {
                    e((JO) list.remove(0), iKeyAt, false);
                    return;
                }
            }
        }
    }
}
