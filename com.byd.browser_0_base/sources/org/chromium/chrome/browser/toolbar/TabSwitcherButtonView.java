package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import defpackage.w43;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: loaded from: classes2.dex */
public class TabSwitcherButtonView extends ListMenuButton {
    public w43 r;

    public TabSwitcherButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.components.browser_ui.widget.listmenu.ListMenuButton, org.chromium.ui.widget.ChromeImageButton, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        w43 w43VarD = w43.d(getContext(), 3);
        this.r = w43VarD;
        setImageDrawable(w43VarD);
    }

    public void setTint(ColorStateList colorStateList) {
        this.r.c(colorStateList);
    }
}
