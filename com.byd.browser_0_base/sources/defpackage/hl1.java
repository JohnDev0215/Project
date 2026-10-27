package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.SelectableTabGridView;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hl1 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ hl1(Object obj, int i, int i2, Object obj2) {
        this.e = i2;
        this.g = obj;
        this.f = i;
        this.h = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        int i2 = this.f;
        Object obj = this.h;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                il1 il1Var = (il1) obj2;
                il1Var.getClass();
                int i3 = ((NavigationEntry) obj).a;
                gl1 gl1Var = (gl1) ((pq2) il1Var.a).f;
                j63 j63Var = (j63) gl1Var.n;
                Tab tab = j63Var.a;
                if (i3 == -1) {
                    j63Var.b.accept(tab);
                } else {
                    tab.c().m().w(i3);
                }
                gl1Var.c();
                if (gl1Var.q) {
                    n3.X0("BackMenu_" + (i3 == -1 ? "ShowFullHistory" : "HistoryClick" + (i2 + 1)));
                }
                break;
            default:
                ((j0.l) ((PropertyModel) obj2).j(u0.o)).a(i2);
                ((SelectableTabGridView) ((ViewLookupCachingFrameLayout) obj)).m();
                break;
        }
    }
}
