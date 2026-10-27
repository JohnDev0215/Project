package org.chromium.chrome.browser.infobar;

import J.N;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.jm;
import defpackage.uh3;
import java.util.ArrayList;
import java.util.LinkedList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.autofill.g;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarControlLayout;
import org.chromium.components.infobars.InfoBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class AutofillSaveCardInfoBar extends ConfirmInfoBar {
    public final String r;
    public final Bitmap s;
    public final long t;
    public final ArrayList u;
    public final int v;
    public final String w;
    public String x;
    public final boolean y;
    public final LinkedList<g> z;

    public class a extends ClickableSpan {
        public final /* synthetic */ g.a e;

        public a(g.a aVar) {
            this.e = aVar;
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(View view) {
            AutofillSaveCardInfoBar autofillSaveCardInfoBar = AutofillSaveCardInfoBar.this;
            N.MLmVDUZa(autofillSaveCardInfoBar.t, autofillSaveCardInfoBar, this.e.c);
        }
    }

    public AutofillSaveCardInfoBar(long j, int i, Bitmap bitmap, String str, String str2, String str3, String str4, boolean z, String str5, Bitmap bitmap2) {
        super(z ? 0 : i, z ? 0 : R$color.infobar_icon_drawable_color, bitmap, str, str2, str3, str4);
        this.u = new ArrayList();
        this.v = -1;
        this.z = new LinkedList<>();
        this.v = i;
        this.w = str;
        this.y = z;
        this.t = j;
        this.r = str5;
        this.s = bitmap2;
    }

    @CalledByNative
    public static AutofillSaveCardInfoBar create(long j, int i, Bitmap bitmap, String str, String str2, String str3, String str4, boolean z, String str5, Bitmap bitmap2) {
        return new AutofillSaveCardInfoBar(j, i, bitmap, str, str2, str3, str4, z, str5, bitmap2);
    }

    @CalledByNative
    public final void addDetail(int i, String str, String str2) {
        this.u.add(new jm(i, str, str2));
    }

    @CalledByNative
    public final void addLegalMessageLine(String str) {
        this.z.add(new g(str));
    }

    @CalledByNative
    public final void addLinkToLastLegalMessageLine(int i, int i2, String str) {
        this.z.getLast().b.add(new g.a(i, i2, str));
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        Bitmap bitmap;
        super.p(infoBarLayout);
        if (this.y) {
            uh3.l(infoBarLayout.getMessageTextView());
            infoBarLayout.getMessageLayout().c(this.v, this.w);
        }
        InfoBarControlLayout infoBarControlLayoutA = infoBarLayout.a();
        if (!TextUtils.isEmpty(this.x)) {
            infoBarControlLayoutA.a(0, this.x);
        }
        int i = 0;
        while (true) {
            ArrayList arrayList = this.u;
            if (i >= arrayList.size()) {
                break;
            }
            jm jmVar = (jm) arrayList.get(i);
            infoBarControlLayoutA.b(jmVar.a, R$dimen.infobar_descriptive_text_size, jmVar.b, jmVar.c);
            i++;
        }
        for (g gVar : this.z) {
            SpannableString spannableString = new SpannableString(gVar.a);
            for (g.a aVar : gVar.b) {
                spannableString.setSpan(new a(aVar), aVar.a, aVar.b, 17);
            }
            infoBarControlLayoutA.a(0, spannableString);
        }
        String str = this.r;
        if (str == null || (bitmap = this.s) == null) {
            return;
        }
        Resources resources = infoBarLayout.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.infobar_small_icon_size);
        resources.getDimensionPixelOffset(R$dimen.infobar_padding);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(infoBarLayout.getContext()).inflate(R$layout.infobar_footer, (ViewGroup) null, false);
        ((TextView) linearLayout.findViewById(R$id.infobar_footer_email)).setText(str);
        RoundedCornerImageView roundedCornerImageView = (RoundedCornerImageView) linearLayout.findViewById(R$id.infobar_footer_profile_pic);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, dimensionPixelSize, dimensionPixelSize, false);
        int i2 = dimensionPixelSize / 2;
        roundedCornerImageView.d(i2, i2, i2, i2);
        roundedCornerImageView.setImageBitmap(bitmapCreateScaledBitmap);
        infoBarLayout.m = linearLayout;
    }

    @CalledByNative
    public final void setDescriptionText(String str) {
        this.x = str;
    }
}
