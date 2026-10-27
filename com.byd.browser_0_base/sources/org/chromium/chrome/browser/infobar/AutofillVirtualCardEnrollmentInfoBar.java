package org.chromium.chrome.browser.infobar;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.ko0;
import defpackage.uh3;
import defpackage.un1;
import defpackage.yb;
import java.util.LinkedList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.autofill.d;
import org.chromium.chrome.browser.autofill.g;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarControlLayout;
import org.chromium.components.infobars.InfoBarLayout;
import org.chromium.components.infobars.R$id;
import org.chromium.components.infobars.R$layout;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes.dex */
public class AutofillVirtualCardEnrollmentInfoBar extends ConfirmInfoBar {
    public final long r;
    public Bitmap s;
    public String t;
    public final int u;
    public final String v;
    public String w;
    public String x;
    public final LinkedList<g> y;
    public final LinkedList<g> z;

    public AutofillVirtualCardEnrollmentInfoBar(long j, int i, Bitmap bitmap, String str, String str2, String str3, String str4) {
        super(0, 0, bitmap, str, str2, str3, str4);
        this.u = -1;
        this.y = new LinkedList<>();
        this.z = new LinkedList<>();
        this.u = i;
        this.v = str;
        this.r = j;
    }

    @CalledByNative
    public static AutofillVirtualCardEnrollmentInfoBar create(long j, int i, Bitmap bitmap, String str, String str2, String str3, String str4) {
        return new AutofillVirtualCardEnrollmentInfoBar(j, i, bitmap, str, str2, str3, str4);
    }

    @CalledByNative
    public final void addCardDetail(Bitmap bitmap, String str) {
        this.s = bitmap;
        this.t = str;
    }

    @CalledByNative
    public final void addGoogleLegalMessageLine(String str) {
        this.y.add(new g(str));
    }

    @CalledByNative
    public final void addIssuerLegalMessageLine(String str) {
        this.z.add(new g(str));
    }

    @CalledByNative
    public final void addLinkToLastGoogleLegalMessageLine(int i, int i2, String str) {
        this.y.getLast().b.add(new g.a(i, i2, str));
    }

    @CalledByNative
    public final void addLinkToLastIssuerLegalMessageLine(int i, int i2, String str) {
        this.z.getLast().b.add(new g.a(i, i2, str));
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        uh3.l(infoBarLayout.getMessageTextView());
        InfoBarControlLayout infoBarControlLayoutA = infoBarLayout.a();
        int i = R$drawable.virtual_card_enrollment_illustration;
        ChromeImageView chromeImageView = new ChromeImageView(infoBarControlLayoutA.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        chromeImageView.setLayoutParams(layoutParams);
        infoBarControlLayoutA.addView(chromeImageView, new InfoBarControlLayout.a());
        chromeImageView.setImageResource(i);
        infoBarControlLayoutA.c(this.u, this.v);
        if (!TextUtils.isEmpty(this.w) && !TextUtils.isEmpty(this.x)) {
            SpannableString spannableString = new SpannableString(this.w);
            int length = this.w.length() - this.x.length();
            spannableString.setSpan(new un1(infoBarLayout.getContext(), new ko0(8, this)), length, this.x.length() + length, 17);
            infoBarControlLayoutA.a(0, spannableString);
        }
        String string = infoBarLayout.getContext().getString(R$string.autofill_virtual_card_enrollment_dialog_card_container_title);
        SpannableString spannableString2 = new SpannableString(String.format("%s\n%s %s", string, infoBarLayout.getContext().getString(R$string.autofill_virtual_card_enrollment_infobar_card_prefix), this.t));
        int length2 = string.length() + 1;
        Context context = infoBarLayout.getContext();
        int i2 = R$style.TextAppearance_TextSmall_Secondary_Baseline;
        spannableString2.setSpan(new TextAppearanceSpan(context, i2), length2, spannableString2.length(), 17);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(this.s, infoBarLayout.getResources().getDimensionPixelSize(R$dimen.virtual_card_enrollment_dialog_card_art_width), infoBarLayout.getResources().getDimensionPixelSize(R$dimen.virtual_card_enrollment_dialog_card_art_height), true);
        int i3 = R$dimen.infobar_descriptive_text_size;
        LinearLayout linearLayout = (LinearLayout) InfoBarControlLayout.d(R$layout.infobar_control_icon_with_description, infoBarControlLayoutA.getContext(), infoBarControlLayoutA);
        infoBarControlLayoutA.addView(linearLayout, new InfoBarControlLayout.a());
        ((ImageView) linearLayout.findViewById(R$id.control_icon)).setImageBitmap(bitmapCreateScaledBitmap);
        TextView textView = (TextView) linearLayout.findViewById(R$id.control_message);
        textView.setText(spannableString2);
        textView.setTextSize(0, infoBarControlLayoutA.getContext().getResources().getDimension(i3));
        linearLayout.removeView((TextView) linearLayout.findViewById(R$id.control_secondary_message));
        infoBarControlLayoutA.a(i2, d.e(infoBarLayout.getContext(), this.y, true, new yb(0, 2, this)));
        infoBarControlLayoutA.a(i2, d.e(infoBarLayout.getContext(), this.z, true, new yb(1, 2, this)));
    }

    @CalledByNative
    public final void setDescription(String str, String str2) {
        this.w = str;
        this.x = str2;
    }
}
