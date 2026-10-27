package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import defpackage.bq;
import defpackage.fu0;
import defpackage.hu0;
import defpackage.sk;
import defpackage.zx0;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class IncognitoNewTabPageView extends FrameLayout {
    public b e;
    public boolean f;
    public NewTabPageScrollView g;
    public zx0 h;
    public int i;
    public int j;
    public int k;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            d dVar = ((c) IncognitoNewTabPageView.this.e).a;
            dVar.getClass();
            fu0 fu0VarA = hu0.a();
            int i = R$string.help_context_incognito_learn_more;
            Activity activity = dVar.k;
            ((hu0) fu0VarA).c(activity, activity.getString(i), null, Profile.d().f(true));
        }
    }

    public interface b {
    }

    public IncognitoNewTabPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = true;
    }

    public b getManager() {
        return this.e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f) {
            ((c) this.e).a.getClass();
            this.f = false;
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        NewTabPageScrollView newTabPageScrollView = (NewTabPageScrollView) findViewById(R$id.ntp_scrollview);
        this.g = newTabPageScrollView;
        newTabPageScrollView.setBackgroundColor(getContext().getColor(R$color.ntp_bg_incognito));
        setContentDescription(getResources().getText(R$string.accessibility_new_incognito_tab_page));
        this.g.setDescendantFocusability(131072);
        ViewStub viewStub = (ViewStub) findViewById(R$id.incognito_description_layout_stub);
        sk skVar = bq.a;
        if (N.M09VlOh_("IncognitoNtpRevamp")) {
            viewStub.setLayoutResource(R$layout.revamped_incognito_description_layout);
        } else {
            viewStub.setLayoutResource(R$layout.incognito_description_layout);
        }
        zx0 zx0Var = (zx0) viewStub.inflate();
        this.h = zx0Var;
        zx0Var.setLearnMoreOnclickListener(new a());
    }

    public void setIncognitoCookieControlsIconOnclickListener(View.OnClickListener onClickListener) {
        this.h.setCookieControlsIconOnclickListener(onClickListener);
    }

    public void setIncognitoCookieControlsToggleChecked(boolean z) {
        this.h.setCookieControlsToggle(z);
    }

    public void setIncognitoCookieControlsToggleCheckedListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.h.setCookieControlsToggleOnCheckedChangeListener(onCheckedChangeListener);
    }

    public void setIncognitoCookieControlsToggleEnforcement(int i) {
        this.h.setCookieControlsEnforcement(i);
    }
}
