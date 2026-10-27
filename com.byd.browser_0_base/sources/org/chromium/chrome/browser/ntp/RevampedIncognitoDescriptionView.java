package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SwitchCompat;
import defpackage.ao3;
import defpackage.iq2;
import defpackage.lj1;
import defpackage.un1;
import defpackage.zx0;
import java.util.Objects;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$string;
import org.chromium.ui.widget.ChromeBulletSpan;

/* JADX INFO: loaded from: classes2.dex */
public class RevampedIncognitoDescriptionView extends LinearLayout implements zx0 {
    public Resources e;
    public int f;
    public int g;
    public LinearLayout h;
    public LinearLayout i;
    public LinearLayout j;
    public LinearLayout k;
    public LinearLayout l;
    public TextView m;
    public SwitchCompat n;
    public ImageView o;
    public TextView p;
    public TextView q;

    public RevampedIncognitoDescriptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void adjustLearnMore(View.OnClickListener onClickListener) {
        String string = getContext().getResources().getString(R$string.revamped_incognito_ntp_learn_more);
        Context context = getContext();
        int i = R$color.default_text_color_link_light;
        Objects.requireNonNull(onClickListener);
        this.m.setText(iq2.a(string, new iq2.a(new un1(context, i, new lj1(3, onClickListener)), "<a>", "</a>")));
        this.m.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public final void a() {
        int dimensionPixelSize;
        int iMin;
        int dimensionPixelSize2;
        int dimensionPixelSize3 = this.e.getDimensionPixelSize(R$dimen.incognito_ntp_content_max_width);
        int dimensionPixelSize4 = this.e.getDimensionPixelSize(R$dimen.incognito_ntp_does_and_doesnt_top_spacing);
        int integer = this.e.getInteger(R$integer.descriptions_weight);
        if (this.f <= this.e.getDimensionPixelSize(R$dimen.incognito_ntp_wide_layout_threshold)) {
            dimensionPixelSize2 = this.e.getDimensionPixelSize(this.f <= this.e.getDimensionPixelSize(R$dimen.incognito_ntp_portrait_small_or_big_threshold) ? R$dimen.incognito_ntp_portrait_horizontal_small_padding : R$dimen.incognito_ntp_portrait_horizontal_big_padding);
            dimensionPixelSize = this.e.getDimensionPixelSize(R$dimen.incognito_ntp_portrait_vertical_padding);
            this.h.setGravity(8388611);
            this.j.setOrientation(1);
            iMin = Math.min(dimensionPixelSize3, this.f - (dimensionPixelSize2 * 2));
            LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
            layoutParams.setMargins(0, dimensionPixelSize4, 0, 0);
            this.k.setLayoutParams(layoutParams);
            this.l.setLayoutParams(layoutParams);
        } else {
            int dimensionPixelSize5 = this.e.getDimensionPixelSize(R$dimen.incognito_ntp_landscape_small_or_big_threshold);
            int dimensionPixelSize6 = this.e.getDimensionPixelSize(R$dimen.incognito_ntp_landscape_horizontal_padding);
            dimensionPixelSize = this.e.getDimensionPixelSize(this.g <= dimensionPixelSize5 ? R$dimen.incognito_ntp_landscape_vertical_small_padding : R$dimen.incognito_ntp_landscape_vertical_big_padding);
            this.h.setGravity(1);
            this.j.setOrientation(0);
            iMin = Math.min(dimensionPixelSize3, this.f - (dimensionPixelSize6 * 2));
            int dimensionPixelSize7 = this.e.getDimensionPixelSize(R$dimen.incognito_ntp_descriptions_horizontal_spacing);
            float f = integer;
            LinearLayoutCompat.LayoutParams layoutParams2 = new LinearLayoutCompat.LayoutParams(f);
            layoutParams2.setMargins(0, dimensionPixelSize4, dimensionPixelSize7, 0);
            this.k.setLayoutParams(layoutParams2);
            LinearLayoutCompat.LayoutParams layoutParams3 = new LinearLayoutCompat.LayoutParams(f);
            layoutParams3.setMargins(0, dimensionPixelSize4, 0, 0);
            this.l.setLayoutParams(layoutParams3);
            dimensionPixelSize2 = dimensionPixelSize6;
        }
        this.i.setLayoutParams(new LinearLayout.LayoutParams(iMin, -2));
        int dimensionPixelSize8 = this.e.getDimensionPixelSize(R$dimen.incognito_ntp_learn_more_vertical_spacing) - ((int) ((getContext().getResources().getDimensionPixelSize(R$dimen.min_touch_target_size) - this.m.getTextSize()) / 2.0f));
        ((LinearLayout.LayoutParams) this.m.getLayoutParams()).setMargins(0, dimensionPixelSize8, 0, dimensionPixelSize8);
        this.h.setPadding(dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize);
    }

    public final void b(int i, int i2) {
        ((TextView) findViewById(i)).setText(iq2.a(getContext().getResources().getString(i2).replaceFirst(" *<li>([^<]*)</li>", "<li1>$1</li1>").replaceFirst(" *<li>([^<]*)</li>", "<li2>$1</li2>").replaceFirst(" *<li>([^<]*)</li>\n", "<li3>$1</li3>").replaceAll(" *</?ul>\\n?", ""), new iq2.a(new ChromeBulletSpan(getContext()), "<li1>", "</li1>"), new iq2.a(new ChromeBulletSpan(getContext()), "<li2>", "</li2>"), new iq2.a(new ChromeBulletSpan(getContext()), "<li3>", "</li3>")));
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = getContext().getResources();
        this.f = ao3.a(getContext(), this.e.getConfiguration().screenWidthDp);
        this.g = ao3.a(getContext(), this.e.getConfiguration().screenHeightDp);
        this.h = (LinearLayout) findViewById(R$id.revamped_incognito_ntp_container);
        b(R$id.revamped_incognito_ntp_does_description_view, R$string.revamped_incognito_ntp_does_description);
        b(R$id.revamped_incognito_ntp_does_not_description_view, R$string.revamped_incognito_ntp_does_not_description);
        this.i = (LinearLayout) findViewById(R$id.revamped_incognito_ntp_content);
        this.j = (LinearLayout) findViewById(R$id.revamped_incognito_ntp_description_text_container);
        this.k = (LinearLayout) findViewById(R$id.revamped_incognito_ntp_does_layout);
        this.l = (LinearLayout) findViewById(R$id.revamped_incognito_ntp_does_not_layout);
        this.m = (TextView) findViewById(R$id.revamped_incognito_ntp_learn_more);
        this.n = (SwitchCompat) findViewById(R$id.revamped_cookie_controls_card_toggle);
        this.o = (ImageView) findViewById(R$id.revamped_cookie_controls_card_managed_icon);
        this.p = (TextView) findViewById(R$id.revamped_cookie_controls_card_title);
        this.q = (TextView) findViewById(R$id.revamped_cookie_controls_card_subtitle);
        a();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Configuration configuration = this.e.getConfiguration();
        int iA = ao3.a(getContext(), configuration.screenWidthDp);
        int iA2 = ao3.a(getContext(), configuration.screenHeightDp);
        if (this.f != iA || this.g != iA2) {
            this.f = iA;
            this.g = iA2;
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
        adjustLearnMore(onClickListener);
    }
}
