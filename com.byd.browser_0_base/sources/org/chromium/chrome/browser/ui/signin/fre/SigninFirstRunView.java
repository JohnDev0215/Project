package org.chromium.chrome.browser.ui.signin.fre;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.go2;
import defpackage.ro2;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: loaded from: classes2.dex */
public class SigninFirstRunView extends RelativeLayout implements ro2 {
    public View e;
    public TextView f;
    public ProgressBar g;
    public View h;
    public TextViewWithClickableSpans i;
    public ProgressBar j;
    public TextView k;
    public TextView l;
    public final go2 m;

    public SigninFirstRunView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        go2 go2Var = new go2(this);
        this.m = go2Var;
        go2Var.K(attributeSet, 0);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.m;
        if (go2Var != null) {
            go2Var.J();
        }
    }

    public View getBrowserManagedHeaderView() {
        return this.e;
    }

    public View getContinueButtonView() {
        return this.h;
    }

    public TextViewWithClickableSpans getFooterView() {
        return this.i;
    }

    public ProgressBar getInitialLoadProgressSpinnerView() {
        return this.g;
    }

    public TextView getPrivacyDisclaimer() {
        return this.f;
    }

    public ProgressBar getSigninProgressSpinner() {
        return this.j;
    }

    public TextView getSigninProgressText() {
        return this.k;
    }

    public TextView getmMessage() {
        return this.l;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = findViewById(R$id.fre_browser_managed_by);
        this.g = (ProgressBar) findViewById(R$id.fre_native_and_policy_load_progress_spinner);
        this.h = findViewById(R$id.signin_fre_continue_button);
        this.i = (TextViewWithClickableSpans) findViewById(R$id.signin_fre_footer);
        this.j = (ProgressBar) findViewById(R$id.fre_signin_progress_spinner);
        this.k = (TextView) findViewById(R$id.fre_signin_progress_text);
        this.f = (TextView) findViewById(R$id.privacy_disclaimer);
        this.l = (TextView) findViewById(R$id.message);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.m;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }
}
