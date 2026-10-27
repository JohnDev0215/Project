package org.chromium.chrome.browser.management;

import android.content.Context;
import android.content.res.Configuration;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import defpackage.on3;
import defpackage.qh3;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class ManagementView extends ScrollView {
    public boolean e;
    public String f;
    public LinearLayout g;
    public TextView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public TextView l;
    public TextView m;
    public TextView n;
    public qh3 o;

    public ManagementView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a() {
        if (!this.e) {
            this.h.setText(getResources().getString(R$string.management_not_managed_subtitle));
        } else if (TextUtils.isEmpty(this.f)) {
            this.h.setText(getResources().getString(R$string.management_subtitle));
        } else {
            this.h.setText(getResources().getString(R$string.management_subtitle_managed_by, this.f));
        }
        this.i.setVisibility(this.e ? 0 : 4);
        this.j.setVisibility(this.e ? 0 : 4);
        this.k.setVisibility(this.e ? 0 : 4);
        this.l.setVisibility(this.e ? 0 : 4);
        this.m.setVisibility(this.e ? 0 : 4);
        this.n.setVisibility(this.e ? 0 : 4);
    }

    public final void b() {
        qh3 qh3Var = this.o;
        if (qh3Var != null) {
            qh3Var.c();
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.cm_padding);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.cm_padding_wide);
        qh3 qh3Var2 = new qh3(this.g);
        this.o = qh3Var2;
        new on3(this.g, qh3Var2, dimensionPixelSize, dimensionPixelSize2).a();
    }

    public String getManagerName() {
        return this.f;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.g = (LinearLayout) findViewById(R$id.management_container);
        this.h = (TextView) findViewById(R$id.title_text);
        this.i = (TextView) findViewById(R$id.description_text);
        this.j = (TextView) findViewById(R$id.learn_more);
        this.k = (TextView) findViewById(R$id.browser_reporting);
        this.l = (TextView) findViewById(R$id.browser_reporting_explanation);
        this.m = (TextView) findViewById(R$id.extension_report_username);
        this.n = (TextView) findViewById(R$id.extension_report_version);
        this.e = false;
        this.f = null;
        a();
        setFocusable(true);
        setFocusableInTouchMode(true);
        b();
    }

    public void setLearnMoreText(SpannableString spannableString) {
        this.j.setText(spannableString);
        this.j.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setManaged(boolean z) {
        if (this.e != z) {
            this.e = z;
            a();
        }
    }

    public void setManagerName(String str) {
        if (TextUtils.equals(this.f, str)) {
            return;
        }
        this.f = str;
        a();
    }
}
