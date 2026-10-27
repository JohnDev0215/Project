package defpackage;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: CustomTabActivityTabProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class a30 {
    public Tab b;
    public String d;
    public final b<a> a = new b<>();
    public int c = 0;

    public final void a() {
        if (this.b == null) {
            return;
        }
        this.b = null;
        this.c = 0;
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }

    public final void b(int i, Tab tab) {
        this.b = tab;
        this.c = i;
        if (i != 4) {
            this.d = null;
        }
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).b(tab);
            }
        }
    }

    /* JADX INFO: compiled from: CustomTabActivityTabProvider.java */
    public static abstract class a {
        public void a() {
        }

        public void b(Tab tab) {
        }

        public void c(Tab tab) {
        }
    }
}
