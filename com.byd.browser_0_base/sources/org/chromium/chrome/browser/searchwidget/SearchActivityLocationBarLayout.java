package org.chromium.chrome.browser.searchwidget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import defpackage.et2;
import defpackage.g3;
import defpackage.jg2;
import defpackage.pr1;
import defpackage.qm0;
import defpackage.s61;
import defpackage.so3;
import defpackage.yp;
import defpackage.zf2;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.omnibox.LocationBarLayout;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.omnibox.h;
import org.chromium.chrome.browser.omnibox.suggestions.c;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class SearchActivityLocationBarLayout extends LocationBarLayout {
    public static final /* synthetic */ int t = 0;
    public boolean r;
    public boolean s;

    public SearchActivityLocationBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R$layout.location_bar_base);
    }

    @Override // org.chromium.chrome.browser.omnibox.LocationBarLayout
    public final void a(c cVar, h hVar, et2 et2Var, f fVar, jg2 jg2Var) {
        super.a(cVar, hVar, et2Var, fVar, jg2Var);
        boolean zA = LocaleManager.getInstance().a();
        this.r = zA;
        this.l.h.f73J = zA;
        findViewById(R$id.url_action_container).setVisibility(0);
        Context context = getContext();
        GradientDrawable gradientDrawable = (GradientDrawable) context.getDrawable(R$drawable.modern_toolbar_text_box_background_with_primary_color);
        gradientDrawable.mutate();
        gradientDrawable.setTint(yp.c(context, R$dimen.toolbar_text_box_elevation));
        if (pr1.e(getContext())) {
            gradientDrawable.setTint(pr1.d() ? yp.c(getContext(), R$dimen.omnibox_suggestion_bg_elevation) : yp.c(getContext(), R$dimen.omnibox_suggestion_dropdown_bg_elevation));
            if (pr1.d()) {
                gradientDrawable.setCornerRadius(getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_bg_round_corner_radius));
            }
            setPaddingRelative(getResources().getDimensionPixelSize(R$dimen.location_bar_start_padding_modern), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
        }
        setBackground(gradientDrawable);
        setUrlFocusChangePercent(1.0f);
    }

    @Override // org.chromium.chrome.browser.omnibox.LocationBarLayout
    public final void b() {
        f();
    }

    @Override // org.chromium.chrome.browser.omnibox.LocationBarLayout
    public final void c() {
        this.o = true;
        boolean zA = LocaleManager.getInstance().a();
        this.r = zA;
        this.l.h.f73J = zA;
    }

    public final void d(int i, so3 so3Var, g3 g3Var) {
        if (this.o) {
            zf2.e(getContext(), g3Var);
        }
        if (i == 1) {
            if (so3Var.a()) {
                so3Var.f(2);
                return;
            } else {
                Toast.c(getContext(), R$string.quick_action_search_widget_message_no_voice_search, 1).e();
                f();
                return;
            }
        }
        if (i != 2) {
            f();
            return;
        }
        f();
        s61 s61Var = s61.b;
        this.m.isIncognito();
        DeviceFormFactor.a(getContext());
        s61Var.a.getClass();
        Toast.c(getContext(), R$string.quick_action_search_widget_message_no_google_lens, 1).e();
    }

    public final void f() {
        this.h.post(new qm0(29, this));
    }
}
