package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import defpackage.bz;
import defpackage.yp;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.NewTabButton;

/* JADX INFO: loaded from: classes2.dex */
class StartSurfaceToolbarView extends RelativeLayout {
    public LinearLayout e;
    public NewTabButton f;
    public boolean g;
    public View h;
    public IncognitoToggleTabLayout i;
    public ImageButton j;
    public ColorStateList k;
    public boolean l;
    public boolean m;

    public StartSurfaceToolbarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a() {
        if (!this.m) {
            this.f.setVisibility(8);
            this.e.setVisibility(8);
            return;
        }
        this.f.setVisibility(this.g ? 8 : 0);
        this.e.setVisibility(this.g ? 0 : 8);
        if (this.g) {
            ViewParent parent = this.e.getParent();
            LinearLayout linearLayout = this.e;
            parent.requestChildFocus(linearLayout, linearLayout);
        } else {
            ViewParent parent2 = this.e.getParent();
            NewTabButton newTabButton = this.f;
            parent2.requestChildFocus(newTabButton, newTabButton);
        }
    }

    public final void b(boolean z) {
        setBackgroundColor(yp.b(getContext(), z));
        if (this.k == null) {
            this.k = bz.b(getContext(), R$color.default_icon_color_light_tint_list);
            bz.b(getContext(), R$color.default_icon_color_tint_list);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (LinearLayout) findViewById(R$id.new_tab_view);
        this.f = (NewTabButton) findViewById(R$id.new_tab_button);
        this.i = (IncognitoToggleTabLayout) ((ViewStub) findViewById(R$id.incognito_tabs_stub)).inflate();
        this.j = (ImageButton) findViewById(R$id.identity_disc_button);
        this.h = findViewById(R$id.start_tab_switcher_button);
        b(false);
        this.f.setStartSurfaceEnabled(true);
    }

    public void setIdentityDiscClickHandler(View.OnClickListener onClickListener) {
        this.j.setOnClickListener(onClickListener);
    }

    public void setOnNewTabClickHandler(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
        this.f.setOnClickListener(onClickListener);
    }
}
