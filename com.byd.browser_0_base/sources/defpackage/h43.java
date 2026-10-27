package defpackage;

import J.N;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSwitcherActionMenuCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public class h43 {
    public static xa1 a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? new xa1(0, new PropertyModel(new v62[0])) : zd.e(R$string.menu_new_incognito_tab, R$id.new_incognito_tab_menu_id, R$drawable.incognito_simple, N.M$3vpOHw()) : zd.c(R$string.menu_new_tab, R$id.new_tab_menu_id, R$drawable.new_tab_icon) : zd.c(R$string.close_tab, R$id.close_tab, R$drawable.btn_close);
    }

    public ya1 b() {
        ya1 ya1Var = new ya1();
        ya1Var.t(a(1));
        ya1Var.t(a(0));
        ya1Var.t(a(2));
        ya1Var.t(a(3));
        return ya1Var;
    }
}
