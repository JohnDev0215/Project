package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import defpackage.bz;
import defpackage.k81;
import defpackage.l81;
import defpackage.n3;
import defpackage.nn3;
import defpackage.qg;
import defpackage.ya1;
import defpackage.z92;
import defpackage.zd;
import defpackage.zp1;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: loaded from: classes2.dex */
public class HomeButton extends ListMenuButton implements MenuItem.OnMenuItemClickListener {
    public static final /* synthetic */ int u = 0;
    public Callback<Context> r;
    public zp1 s;
    public ya1 t;

    public class a implements l81 {
        public final /* synthetic */ zd e;
        public final /* synthetic */ z92 f;

        public a(zd zdVar, nn3 nn3Var) {
            this.e = zdVar;
            this.f = nn3Var;
        }

        @Override // defpackage.l81
        public final z92 a(View view) {
            return this.f;
        }

        @Override // defpackage.l81
        public final k81 b() {
            return this.e;
        }
    }

    public HomeButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i = R$drawable.btn_toolbar_home;
        Object obj = bz.a;
        setImageDrawable(context.getDrawable(i));
    }

    public static /* synthetic */ void g(HomeButton homeButton, View view) {
        homeButton.setDelegateForMenu(view);
        ((ListMenuButton) view).f();
    }

    private void setDelegateForMenu(View view) {
        nn3 nn3VarN0 = n3.n0(view);
        ya1 ya1Var = new ya1();
        ya1Var.t(zd.c(R$string.options_homepage_edit_title, 0, R$drawable.ic_edit_24dp));
        this.t = ya1Var;
        d(new a(new zd(getContext(), ya1Var, new qg(this, 1)), nn3VarN0), false);
    }

    public ya1 getMenuForTests() {
        return this.t;
    }

    public final void h() {
        if (((Boolean) this.s.get()).booleanValue() || this.r == null) {
            setLongClickable(false);
        } else {
            setOnLongClickListener(new View.OnLongClickListener() { // from class: lv0
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    HomeButton.g(this.e, view);
                    return true;
                }
            });
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent traceEventH0 = TraceEvent.H0("HomeButton.onLayout", null);
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        TraceEvent traceEventH0 = TraceEvent.H0("HomeButton.onMeasure", null);
        try {
            super.onMeasure(i, i2);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        this.r.onResult(getContext());
        return true;
    }

    public static void setSaveContextMenuForTests(boolean z) {
    }
}
