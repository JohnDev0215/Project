package defpackage;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.ui.fast_checkout.R$id;

/* JADX INFO: compiled from: DetailScreenViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b90 {
    public final Context a;
    public final Toolbar b;
    public final MenuItem c;
    public final RecyclerView d;
    public final a90 e;

    public b90(Context context, View view, a90 a90Var) {
        this.a = context;
        this.d = (RecyclerView) view.findViewById(R$id.fast_checkout_detail_screen_recycler_view);
        Toolbar toolbar = (Toolbar) view.findViewById(R$id.action_bar);
        this.b = toolbar;
        this.c = toolbar.getMenu().findItem(R$id.settings_menu_id);
        this.e = a90Var;
    }
}
