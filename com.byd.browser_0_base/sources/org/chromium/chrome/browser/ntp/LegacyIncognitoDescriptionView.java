package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import defpackage.iq2;
import defpackage.zx0;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.ui.widget.ChromeBulletSpan;

/* JADX INFO: loaded from: classes2.dex */
public class LegacyIncognitoDescriptionView extends LinearLayout implements zx0 {
    public int e;
    public int f;
    public LinearLayout g;
    public TextView h;
    public TextView i;
    public LinearLayout j;
    public TextView k;
    public TextView[] l;
    public RelativeLayout m;
    public SwitchCompat n;
    public ImageView o;
    public TextView p;
    public TextView q;

    public LegacyIncognitoDescriptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            Method dump skipped, instruction units count: 529
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ntp.LegacyIncognitoDescriptionView.a():void");
    }

    public final void b(int i, int i2) {
        ((TextView) findViewById(i)).setText(iq2.a(getContext().getResources().getString(i2).replaceAll("<li>([^<]+)\n", "<li>$1</li>\n").replaceFirst(" *<li>([^<]*)</li>", "<li1>$1</li1>").replaceFirst(" *<li>([^<]*)</li>", "<li2>$1</li2>").replaceFirst(" *<li>([^<]*)</li>\n", "<li3>$1</li3>").replaceAll(" *</?ul>\\n?", ""), new iq2.a(new ForegroundColorSpan(getContext().getColor(R$color.incognito_emphasis)), "<em>", "</em>"), new iq2.a(new ChromeBulletSpan(getContext()), "<li1>", "</li1>"), new iq2.a(new ChromeBulletSpan(getContext()), "<li2>", "</li2>"), new iq2.a(new ChromeBulletSpan(getContext()), "<li3>", "</li3>")));
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = getContext().getResources().getConfiguration().screenWidthDp;
        this.f = getContext().getResources().getConfiguration().screenHeightDp;
        int i = R$id.new_tab_incognito_features;
        b(i, R$string.new_tab_otr_not_saved);
        int i2 = R$id.new_tab_incognito_warning;
        b(i2, R$string.new_tab_otr_visible);
        this.g = (LinearLayout) findViewById(R$id.new_tab_incognito_container);
        this.h = (TextView) findViewById(R$id.new_tab_incognito_title);
        this.i = (TextView) findViewById(R$id.new_tab_incognito_subtitle);
        this.k = (TextView) findViewById(R$id.learn_more);
        this.l = new TextView[]{this.i, (TextView) findViewById(i), (TextView) findViewById(i2)};
        this.j = (LinearLayout) findViewById(R$id.new_tab_incognito_bulletpoints_container);
        this.m = (RelativeLayout) findViewById(R$id.cookie_controls_card);
        this.n = (SwitchCompat) findViewById(R$id.cookie_controls_card_toggle);
        this.o = (ImageView) findViewById(R$id.cookie_controls_card_managed_icon);
        this.p = (TextView) findViewById(R$id.cookie_controls_card_title);
        this.q = (TextView) findViewById(R$id.cookie_controls_card_subtitle);
        a();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i3 = this.e;
        int i4 = configuration.screenWidthDp;
        if (i3 != i4 || this.f != configuration.screenHeightDp) {
            this.e = i4;
            this.f = configuration.screenHeightDp;
            a();
        }
        super.onMeasure(i, i2);
    }

    @Override // defpackage.zx0
    public void setCookieControlsEnforcement(int i) {
        int i2;
        String string;
        boolean z = i != 0;
        this.n.setEnabled(!z);
        this.o.setVisibility(z ? 0 : 8);
        this.p.setEnabled(!z);
        this.q.setEnabled(!z);
        Resources resources = getContext().getResources();
        StringBuilder sb = new StringBuilder();
        sb.append(resources.getString(R$string.new_tab_otr_third_party_cookie_sublabel));
        if (!z) {
            this.q.setText(sb.toString());
            return;
        }
        if (i == 1) {
            i2 = R$drawable.ic_business_small;
            string = resources.getString(R$string.managed_by_your_organization);
        } else {
            if (i != 3) {
                return;
            }
            i2 = R$drawable.settings_cog;
            string = resources.getString(R$string.new_tab_otr_cookie_controls_controlled_tooltip_text);
        }
        this.o.setImageResource(i2);
        sb.append("\n");
        sb.append(string);
        this.q.setText(sb.toString());
    }

    @Override // defpackage.zx0
    public void setCookieControlsIconOnclickListener(View.OnClickListener onClickListener) {
        this.o.setOnClickListener(onClickListener);
    }

    @Override // defpackage.zx0
    public void setCookieControlsToggle(boolean z) {
        this.n.setChecked(z);
    }

    @Override // defpackage.zx0
    public void setCookieControlsToggleOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.n.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override // defpackage.zx0
    public void setLearnMoreOnclickListener(View.OnClickListener onClickListener) {
        this.k.setOnClickListener(onClickListener);
    }
}
