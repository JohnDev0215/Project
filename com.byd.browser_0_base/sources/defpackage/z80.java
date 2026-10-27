package defpackage;

import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.ui.fast_checkout.R$color;
import org.chromium.chrome.browser.ui.fast_checkout.R$dimen;
import org.chromium.chrome.browser.ui.fast_checkout.R$drawable;
import org.chromium.chrome.browser.ui.fast_checkout.R$id;
import org.chromium.chrome.browser.ui.fast_checkout.R$menu;
import org.chromium.chrome.browser.ui.fast_checkout.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: DetailScreenCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z80 {
    public final RecyclerView a;
    public final ji b;
    public final a90 c;
    public final a d;

    /* JADX INFO: compiled from: DetailScreenCoordinator.java */
    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            z80 z80Var = z80.this;
            if (i == 0) {
                z80Var.b.e(z80Var.d);
                return;
            }
            if (i == 3) {
                z80Var.a.suppressLayout(false);
            } else if (i == 2) {
                if (z80Var.c.f == 0) {
                    z80Var.a.suppressLayout(true);
                }
            }
        }
    }

    public z80(Context context, View view, PropertyModel propertyModel, ji jiVar) {
        a aVar = new a();
        this.d = aVar;
        this.b = jiVar;
        a90 a90Var = new a90(jiVar);
        this.c = a90Var;
        Toolbar toolbar = (Toolbar) view.findViewById(R$id.action_bar);
        toolbar.o(R$menu.fast_checkout_toolbar_menu);
        toolbar.setNavigationIcon(za3.b(toolbar.getContext(), R$drawable.ic_arrow_back_white_24dp, R$color.default_icon_color_tint_list));
        toolbar.setNavigationContentDescription(R$string.fast_checkout_back_to_home_screen_icon_description);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R$id.fast_checkout_detail_screen_recycler_view);
        this.a = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.g(new y80(context.getResources().getDimensionPixelSize(R$dimen.fast_checkout_detail_sheet_spacing_vertical)));
        recyclerView.addOnScrollListener(a90Var);
        jiVar.g(aVar);
        j.b(propertyModel, new b90(context, view, a90Var), new cg(22));
    }
}
