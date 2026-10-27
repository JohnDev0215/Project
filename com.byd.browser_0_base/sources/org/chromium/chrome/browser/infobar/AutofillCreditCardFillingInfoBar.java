package org.chromium.chrome.browser.infobar;

import android.graphics.Bitmap;
import defpackage.jm;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarControlLayout;
import org.chromium.components.infobars.InfoBarLayout;
import org.chromium.components.infobars.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class AutofillCreditCardFillingInfoBar extends ConfirmInfoBar {
    public final ArrayList r;

    public AutofillCreditCardFillingInfoBar(int i, Bitmap bitmap, String str, String str2, String str3) {
        super(i, R$color.infobar_icon_drawable_color, bitmap, str, null, str2, str3);
        this.r = new ArrayList();
    }

    @CalledByNative
    public static AutofillCreditCardFillingInfoBar create(long j, int i, Bitmap bitmap, String str, String str2, String str3) {
        return new AutofillCreditCardFillingInfoBar(i, bitmap, str, str2, str3);
    }

    @CalledByNative
    public final void addDetail(int i, String str, String str2) {
        this.r.add(new jm(i, str, str2));
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        InfoBarControlLayout infoBarControlLayoutA = infoBarLayout.a();
        int i = 0;
        while (true) {
            ArrayList arrayList = this.r;
            if (i >= arrayList.size()) {
                return;
            }
            jm jmVar = (jm) arrayList.get(i);
            infoBarControlLayoutA.b(jmVar.a, R$dimen.infobar_text_size, jmVar.b, jmVar.c);
            i++;
        }
    }
}
