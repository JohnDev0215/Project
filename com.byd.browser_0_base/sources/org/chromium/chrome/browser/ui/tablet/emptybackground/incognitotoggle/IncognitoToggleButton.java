package org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import defpackage.bz;
import defpackage.e23;
import defpackage.y13;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class IncognitoToggleButton extends ChromeImageButton {
    public static final /* synthetic */ int j = 0;
    public y13 h;
    public a i;

    public class a implements e23 {
        public a() {
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            int i = IncognitoToggleButton.j;
            IncognitoToggleButton.this.b();
        }
    }

    public IncognitoToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void b() {
        y13 y13Var = this.h;
        if (y13Var == null || y13Var.z() == null) {
            return;
        }
        setContentDescription(getContext().getString(this.h.r() ? R$string.accessibility_tabstrip_btn_incognito_toggle_incognito : R$string.accessibility_tabstrip_btn_incognito_toggle_standard));
        setImage(this.h.r());
    }

    @Override // org.chromium.ui.widget.ChromeImageButton, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setScaleType(ImageView.ScaleType.CENTER);
    }

    public void setImage(boolean z) {
        setImageResource(R$drawable.incognito_simple);
        setImageTintList(bz.b(getContext(), z ? R$color.default_icon_color_white_tint_list : R$color.default_icon_color_tint_list));
    }

    public void setTabModelSelector(y13 y13Var) {
        this.h = y13Var;
        if (y13Var != null) {
            b();
            a aVar = new a();
            this.i = aVar;
            this.h.m(aVar);
        }
    }
}
