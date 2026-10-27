package org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import defpackage.t13;
import defpackage.y13;
import java.util.Iterator;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: loaded from: classes2.dex */
public class IncognitoToggleButtonTablet extends IncognitoToggleButton {
    public static final /* synthetic */ int l = 0;
    public b k;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            y13 y13Var = IncognitoToggleButtonTablet.this.h;
            if (y13Var != null) {
                y13Var.d(!y13Var.r());
            }
        }
    }

    public class b implements t13 {
        public b() {
        }

        @Override // defpackage.t13
        public final void X0(boolean z) {
            int i = IncognitoToggleButtonTablet.l;
            IncognitoToggleButtonTablet.this.d();
        }

        @Override // defpackage.t13
        public final void f1(Tab tab, int i, int i2, boolean z) {
            int i3 = IncognitoToggleButtonTablet.l;
            IncognitoToggleButtonTablet.this.d();
        }

        @Override // defpackage.t13
        public final void n0(Tab tab) {
            int i = IncognitoToggleButtonTablet.l;
            IncognitoToggleButtonTablet.this.d();
        }

        @Override // defpackage.t13
        public final void t(Tab tab, boolean z, boolean z2) {
            int i = IncognitoToggleButtonTablet.l;
            IncognitoToggleButtonTablet.this.d();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            IncognitoToggleButtonTablet incognitoToggleButtonTablet = IncognitoToggleButtonTablet.this;
            incognitoToggleButtonTablet.setVisibility(incognitoToggleButtonTablet.h.g(true).getCount() > 0 ? 0 : 8);
        }
    }

    public IncognitoToggleButtonTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void d() {
        y13 y13Var = this.h;
        if (y13Var == null || y13Var.z() == null) {
            setVisibility(8);
        } else {
            post(new c());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        y13 y13Var = this.h;
        if (y13Var != null) {
            y13Var.m(this.i);
            Iterator it = this.h.y().iterator();
            while (it.hasNext()) {
                ((TabModel) it.next()).j(this.k);
            }
        }
        super.onAttachedToWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        y13 y13Var = this.h;
        if (y13Var != null) {
            y13Var.l(this.i);
            Iterator it = this.h.y().iterator();
            while (it.hasNext()) {
                ((TabModel) it.next()).l(this.k);
            }
        }
        super.onDetachedFromWindow();
    }

    @Override // org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButton, org.chromium.ui.widget.ChromeImageButton, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setVisibility(8);
        setOnClickListener(new a());
    }

    @Override // org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButton
    public void setImage(boolean z) {
        setImageResource(z ? R$drawable.location_bar_incognito_badge : R$drawable.btn_tabstrip_switch_normal);
    }

    @Override // org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButton
    public void setTabModelSelector(y13 y13Var) {
        super.setTabModelSelector(y13Var);
        if (y13Var != null) {
            d();
            this.k = new b();
            Iterator it = this.h.y().iterator();
            while (it.hasNext()) {
                ((TabModel) it.next()).j(this.k);
            }
        }
    }
}
