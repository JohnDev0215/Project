package org.chromium.components.subresource_filter;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import defpackage.l51;
import defpackage.un1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.infobars.InfoBarControlLayout;
import org.chromium.components.infobars.InfoBarLayout;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes3.dex */
public class AdsBlockedInfoBar extends ConfirmInfoBar implements CompoundButton.OnCheckedChangeListener {
    public final String r;
    public final String s;
    public final String t;
    public final String u;
    public final String v;
    public boolean w;
    public boolean x;
    public ButtonCompat y;

    public AdsBlockedInfoBar(int i, String str, String str2, String str3, String str4, String str5) {
        super(i, R$color.infobar_icon_drawable_color, null, str, null, null, null);
        this.s = str5;
        this.r = str;
        this.t = str2;
        this.u = str3;
        this.v = str4;
    }

    @CalledByNative
    public static InfoBar show(int i, String str, String str2, String str3, String str4, String str5) {
        return new AdsBlockedInfoBar(i, str, str2, str3, str4, str5);
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void c(boolean z) {
        s(this.x ? 2 : 1);
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void d() {
        if (!this.w) {
            this.w = true;
            this.j = q();
            InfoBarContainerView infoBarContainerView = ((InfoBarContainer) this.i).q;
            if (infoBarContainerView != null) {
                infoBarContainerView.q.l();
            }
        }
        super.d();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.y.setText(z ? this.u : this.t);
        this.x = z;
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        if (!this.w) {
            String string = infoBarLayout.getContext().getString(R$string.details_link);
            infoBarLayout.setMessage(this.r);
            infoBarLayout.r = string;
            infoBarLayout.n.setText(infoBarLayout.g());
            return;
        }
        infoBarLayout.setMessage(infoBarLayout.getContext().getString(R$string.blocked_ads_prompt_title));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) new SpannableString(this.s));
        spannableStringBuilder.append((CharSequence) " ");
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) infoBarLayout.getContext().getString(R$string.learn_more));
        spannableStringBuilder.setSpan(new un1(infoBarLayout.getContext(), new l51(23, this)), length, spannableStringBuilder.length(), 33);
        infoBarLayout.getMessageLayout().a(0, spannableStringBuilder);
        String str = this.t;
        infoBarLayout.h(str, null);
        InfoBarControlLayout infoBarControlLayoutA = infoBarLayout.a();
        int i = R$id.subresource_filter_infobar_toggle;
        LinearLayout linearLayout = (LinearLayout) InfoBarControlLayout.d(org.chromium.components.infobars.R$layout.infobar_control_toggle, infoBarControlLayoutA.getContext(), infoBarControlLayoutA);
        infoBarControlLayoutA.addView(linearLayout, new InfoBarControlLayout.a());
        linearLayout.removeView((ImageView) linearLayout.findViewById(org.chromium.components.infobars.R$id.control_icon));
        ((TextView) linearLayout.findViewById(org.chromium.components.infobars.R$id.control_message)).setText(this.v);
        SwitchCompat switchCompat = (SwitchCompat) linearLayout.findViewById(org.chromium.components.infobars.R$id.control_toggle_switch);
        switchCompat.setId(i);
        switchCompat.setChecked(false);
        ((SwitchCompat) linearLayout.findViewById(i)).setOnCheckedChangeListener(this);
        ButtonCompat primaryButton = infoBarLayout.getPrimaryButton();
        this.y = primaryButton;
        primaryButton.setMinEms(Math.max(str.length(), this.u.length()));
    }
}
