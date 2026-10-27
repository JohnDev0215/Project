package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import org.chromium.base.TraceEvent;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;
import org.chromium.ui.R$dimen;

/* JADX INFO: compiled from: PlayerSwipeRefreshHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j22 implements qt1 {
    public final SwipeRefreshLayout a;
    public final Runnable b;

    public j22(Context context, g22 g22Var) {
        TraceEvent.q("PlayerSwipeRefreshHandler", null);
        this.b = g22Var;
        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(context);
        this.a = swipeRefreshLayout;
        swipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(yp.c(context, R$dimen.default_elevation_2));
        swipeRefreshLayout.setColorSchemeColors(ii2.b(context));
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.e() { // from class: h22
            @Override // org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout.e
            public final void c() {
                j22 j22Var = this.e;
                j22Var.a.postDelayed(new i22(0, j22Var), 500L);
                j22Var.b.run();
            }
        });
        TraceEvent.C("PlayerSwipeRefreshHandler");
    }
}
