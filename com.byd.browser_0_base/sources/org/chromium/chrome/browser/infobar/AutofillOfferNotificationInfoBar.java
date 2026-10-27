package org.chromium.chrome.browser.infobar;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import defpackage.C0175b;
import defpackage.uh3;
import defpackage.un1;
import defpackage.xr;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarControlLayout;
import org.chromium.components.infobars.InfoBarLayout;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class AutofillOfferNotificationInfoBar extends ConfirmInfoBar {
    public final long r;
    public String s;
    public final GURL t;
    public final String u;
    public final int v;

    public AutofillOfferNotificationInfoBar(long j, int i, String str, String str2, GURL gurl) {
        super(0, 0, null, str, null, str2, null);
        this.r = j;
        this.t = gurl;
        this.u = str;
        this.v = i;
    }

    @CalledByNative
    public static AutofillOfferNotificationInfoBar create(long j, int i, String str, String str2, GURL gurl) {
        return new AutofillOfferNotificationInfoBar(j, i, str, str2, gurl);
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        int i;
        super.p(infoBarLayout);
        if (TextUtils.isEmpty(this.s) || (i = this.v) == 0) {
            return;
        }
        uh3.l(infoBarLayout.getMessageTextView());
        infoBarLayout.getMessageLayout().c(i, this.u);
        InfoBarControlLayout infoBarControlLayoutA = infoBarLayout.a();
        String string = this.k.getString(R$string.autofill_offers_reminder_infobar_description_text, this.s);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        int iIndexOf = string.indexOf(this.s);
        spannableStringBuilder.setSpan(new StyleSpan(1), iIndexOf, this.s.length() + iIndexOf, 33);
        if (this.t.b) {
            String string2 = this.k.getString(R$string.autofill_offers_reminder_deep_link_text);
            un1 un1Var = new un1(this.k, new xr(24, this));
            SpannableString spannableString = new SpannableString(C0175b.j(" ", string2));
            spannableString.setSpan(un1Var, 1, string2.length() + 1, 18);
            spannableStringBuilder.append((CharSequence) spannableString);
        }
        infoBarControlLayoutA.a(0, spannableStringBuilder);
    }

    @CalledByNative
    public final void setCreditCardDetails(String str, int i) {
        this.s = str;
    }
}
