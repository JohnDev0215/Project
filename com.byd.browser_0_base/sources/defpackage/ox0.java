package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.commerce.core.ShoppingService;

/* JADX INFO: compiled from: ImplicitPriceDropSubscriptionsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ox0 {
    public final y13 a;
    public final a b;
    public final ShoppingService c;

    /* JADX INFO: compiled from: ImplicitPriceDropSubscriptionsManager.java */
    public class a implements t13 {
        public a() {
        }

        @Override // defpackage.t13
        public final void B0(int i, int i2, Tab tab) {
            ox0.a(ox0.this, tab);
        }

        @Override // defpackage.t13
        public final void i1(Tab tab) {
            ox0.a(ox0.this, tab);
        }

        @Override // defpackage.t13
        public final void n0(Tab tab) {
            ox0.a(ox0.this, tab);
        }
    }

    public ox0(y13 y13Var, ShoppingService shoppingService) {
        this.c = shoppingService;
        this.a = y13Var;
        a aVar = new a();
        this.b = aVar;
        y13Var.g(false).j(aVar);
    }

    public static void a(ox0 ox0Var, Tab tab) {
        boolean z = false;
        TabModel tabModelG = ox0Var.a.g(false);
        int i = 0;
        while (true) {
            if (i >= tabModelG.getCount()) {
                z = true;
                break;
            }
            Tab tabAt = tabModelG.getTabAt(i);
            if (tabAt.getId() != tab.getId() && tabAt.v().j().equals(tab.v().j())) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            xl2.o(new t02(new lj1(25, ox0Var), 4), tab);
        }
    }
}
