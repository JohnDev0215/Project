package org.chromium.chrome.browser.infobar;

import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import defpackage.td2;
import defpackage.un1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class SafetyTipInfoBar extends ConfirmInfoBar {
    public final String r;
    public final String s;

    public SafetyTipInfoBar(int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5) {
        super(i, 0, bitmap, str, null, str3, str4);
        this.r = str5;
        this.s = str2;
    }

    @CalledByNative
    public static ConfirmInfoBar create(int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5) {
        return new SafetyTipInfoBar(i, bitmap, str, str2, str3, str4, str5);
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.r);
        String str = this.s;
        if (str != null && !str.isEmpty()) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new un1(infoBarLayout.getContext(), new td2(0, this)), 0, spannableString.length(), 17);
            spannableStringBuilder.append((CharSequence) " ").append((CharSequence) spannableString);
        }
        infoBarLayout.getMessageLayout().a(0, spannableStringBuilder);
    }
}
