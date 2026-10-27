package org.chromium.chrome.browser.findinpage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import defpackage.bz;
import defpackage.ii2;
import defpackage.yp;
import org.chromium.chrome.R$color;

/* JADX INFO: loaded from: classes.dex */
public class FindToolbarPhone extends FindToolbar {
    public FindToolbarPhone(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final int k(boolean z, boolean z2) {
        if (z2) {
            return getContext().getColor(z ? R$color.default_red_light : R$color.white_alpha_50);
        }
        return super.k(z, z2);
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final void l() {
        setVisibility(0);
        super.l();
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final void q(boolean z) {
        setVisibility(8);
        super.q(z);
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final void v(boolean z) {
        int i;
        int i2;
        setBackgroundColor(yp.a(getContext(), z));
        ColorStateList colorStateListB = bz.b(getContext(), z ? org.chromium.components.browser_ui.styles.R$color.default_icon_color_light_tint_list : org.chromium.components.browser_ui.styles.R$color.default_icon_color_tint_list);
        this.i.setImageTintList(colorStateListB);
        this.h.setImageTintList(colorStateListB);
        this.g.setImageTintList(colorStateListB);
        if (z) {
            i = R$color.find_in_page_query_white_color;
            i2 = R$color.find_in_page_query_incognito_hint_color;
            this.j.setBackgroundResource(R$color.white_alpha_12);
        } else {
            i = R$color.default_text_color_list;
            i2 = R$color.find_in_page_query_default_hint_color;
            this.j.setBackgroundColor(ii2.e(getContext()));
        }
        this.f.setTextColor(bz.b(getContext(), i));
        this.f.setHintTextColor(getContext().getColor(i2));
    }
}
