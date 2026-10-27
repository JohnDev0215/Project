package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import defpackage.bz;
import defpackage.bz0;
import defpackage.g90;
import defpackage.re3;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class NewTabButton extends ChromeImageButton implements bz0.b, View.OnLongClickListener {
    public final ColorStateList h;
    public final ColorStateList i;
    public final boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    public NewTabButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = false;
        this.h = bz.b(getContext(), R$color.default_icon_color_light_tint_list);
        this.i = bz.b(getContext(), R$color.default_icon_color_tint_list);
        setImageDrawable(re3.a(getContext().getResources(), R$drawable.new_tab_icon, getContext().getTheme()));
        this.j = DeviceFormFactor.a(context);
        b();
        setOnLongClickListener(this);
    }

    public final void b() {
        setImageTintList(this.j || ((g90.a(getContext()) || this.l || this.m) && this.k) ? this.h : this.i);
    }

    @Override // bz0.b
    public final void i(boolean z) {
        if (this.k == z) {
            return;
        }
        this.k = z;
        setContentDescription(getResources().getText(z ? R$string.accessibility_toolbar_btn_new_incognito_tab : R$string.accessibility_toolbar_btn_new_tab));
        b();
        invalidate();
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        return Toast.f(getContext(), view, getResources().getString(this.k ? R$string.button_new_incognito_tab : R$string.button_new_tab));
    }

    public void setGridTabSwitcherEnabled(boolean z) {
        if (this.l == z) {
            return;
        }
        this.l = z;
        b();
        invalidate();
    }

    public void setIncognitoStateProvider(bz0 bz0Var) {
        bz0Var.a(this);
    }

    public void setStartSurfaceEnabled(boolean z) {
        if (this.m == z) {
            return;
        }
        this.m = z;
        b();
        invalidate();
    }
}
