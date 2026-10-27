package defpackage;

import J.N;
import android.widget.PopupWindow;
import java.util.Iterator;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.toolbar.top.a;
import org.chromium.chrome.browser.toolbar.top.b;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class pm1 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ pm1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((NewTabPageLayout) obj).H = null;
                break;
            case 1:
                b bVar = (b) obj;
                if (bVar.r) {
                    FaviconHelper faviconHelper = bVar.q;
                    N.Mz5mgjYL(faviconHelper.a);
                    faviconHelper.a = 0L;
                }
                bVar.r = false;
                FaviconHelper.a aVar = bVar.p;
                if (aVar != null) {
                    aVar.a = null;
                    aVar.b = null;
                    aVar.c = null;
                }
                a aVar2 = bVar.m;
                if (aVar2 != null) {
                    bVar.g.getAnchorView().removeOnLayoutChangeListener(aVar2);
                }
                break;
            default:
                ListMenuButton listMenuButton = (ListMenuButton) obj;
                listMenuButton.k = null;
                Iterator it = listMenuButton.m.e.iterator();
                while (it.hasNext()) {
                    ((ListMenuButton.a) it.next()).b();
                }
                break;
        }
    }
}
