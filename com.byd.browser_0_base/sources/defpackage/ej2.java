package defpackage;

import defpackage.dj2;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: SessionRestoreManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class ej2 implements dj2 {
    public static final long e = TimeUnit.MINUTES.toMillis(5);
    public static final /* synthetic */ int f = 0;
    public final um3 a;
    public final b<dj2.a> b;
    public ll c;
    public final long d;

    public ej2() {
        um3 um3Var = new um3(2, 0);
        this.b = new b<>();
        this.d = e;
        this.a = um3Var;
    }

    public final void a(int i) {
        int i2;
        um3 um3Var = this.a;
        Object obj = um3Var.f;
        if (((Tab) obj) != null) {
            int id = ((Tab) obj).getId();
            ((Tab) um3Var.f).destroy();
            um3Var.f = null;
            i2 = id;
        } else {
            i2 = -1;
        }
        g9 g9Var = h9.a;
        if (g9Var.a.get(i2) instanceof d33) {
            g9Var.remove(i2);
        }
        Iterator<dj2.a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((dj2.a) aVar.next()).a();
            }
        }
    }
}
