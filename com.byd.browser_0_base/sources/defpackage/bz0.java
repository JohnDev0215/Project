package defpackage;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: IncognitoStateProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bz0 {
    public final org.chromium.base.b<b> a = new org.chromium.base.b<>();
    public final a b = new a();
    public y13 c;

    /* JADX INFO: compiled from: IncognitoStateProvider.java */
    public class a implements e23 {
        public a() {
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            boolean zIsIncognito = tabModel.isIncognito();
            Iterator<b> it = bz0.this.a.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((b) aVar.next()).i(zIsIncognito);
                }
            }
        }
    }

    /* JADX INFO: compiled from: IncognitoStateProvider.java */
    public interface b {
        void i(boolean z);
    }

    public final void a(b bVar) {
        this.a.a(bVar);
        bVar.i(b());
    }

    public final boolean b() {
        y13 y13Var = this.c;
        if (y13Var != null) {
            return y13Var.r();
        }
        return false;
    }
}
