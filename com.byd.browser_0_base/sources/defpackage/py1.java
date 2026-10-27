package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.components.payments.PaymentApp;

/* JADX INFO: compiled from: PaymentAppService.java */
/* JADX INFO: loaded from: classes3.dex */
public final class py1 implements ny1 {
    public static py1 b;
    public final HashMap a = new HashMap();

    /* JADX INFO: compiled from: PaymentAppService.java */
    public final class a implements my1 {
        public final Set<ny1> a;
        public final ArrayList b = new ArrayList();
        public final my1 c;
        public boolean d;

        public a(HashSet hashSet, my1 my1Var) {
            this.a = hashSet;
            this.c = my1Var;
        }

        @Override // defpackage.my1
        public final void b(boolean z) {
            if (!z || this.d) {
                return;
            }
            this.d = true;
            this.c.b(true);
        }

        @Override // defpackage.my1
        public final void c(String str, int i) {
            this.c.c(str, i);
        }

        @Override // defpackage.my1
        public final void d() {
            this.c.d();
        }

        @Override // defpackage.my1
        public final void e(ny1 ny1Var) {
            Set<ny1> set = this.a;
            set.remove(ny1Var);
            if (set.isEmpty()) {
                boolean z = this.d;
                my1 my1Var = this.c;
                if (!z) {
                    my1Var.b(false);
                }
                ArrayList arrayList = this.b;
                HashMap map = new HashMap();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    map.put(((PaymentApp) arrayList.get(i)).g, (PaymentApp) arrayList.get(i));
                }
                for (int i2 = 0; i2 < size; i2++) {
                    map.remove(((PaymentApp) arrayList.get(i2)).h());
                }
                HashSet hashSet = new HashSet(map.values());
                Iterator it = map.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PaymentApp paymentApp = (PaymentApp) it.next();
                    if (paymentApp.s()) {
                        hashSet.clear();
                        hashSet.add(paymentApp);
                        break;
                    }
                    Set<String> setI = paymentApp.i();
                    if (setI != null) {
                        Iterator<String> it2 = setI.iterator();
                        while (it2.hasNext()) {
                            if (map.containsKey(it2.next())) {
                                hashSet.remove(paymentApp);
                            }
                        }
                    }
                }
                arrayList.clear();
                Iterator it3 = hashSet.iterator();
                while (it3.hasNext()) {
                    my1Var.i((PaymentApp) it3.next());
                }
                my1Var.e(py1.this);
            }
        }

        @Override // defpackage.my1
        public final oy1 f() {
            return this.c.f();
        }

        @Override // defpackage.my1
        public final void g() {
            this.c.g();
        }

        @Override // defpackage.my1
        public final mk h() {
            return this.c.h();
        }

        @Override // defpackage.my1
        public final void i(PaymentApp paymentApp) {
            this.b.add(paymentApp);
        }
    }

    @Override // defpackage.ny1
    public final void a(my1 my1Var) {
        HashMap map = this.a;
        a aVar = new a(new HashSet(map.values()), my1Var);
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            ((ny1) it.next()).a(aVar);
        }
    }
}
