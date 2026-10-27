package org.chromium.chrome.browser.infobar;

import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class KnownInterceptionDisclosureInfoBar extends ConfirmInfoBar {
    public final String r;

    public KnownInterceptionDisclosureInfoBar(int i, Bitmap bitmap, String str, String str2, String str3, String str4) {
        super(i, 0, bitmap, str, str2, str3, "");
        this.r = str4;
    }

    @CalledByNative
    public static ConfirmInfoBar create(int i, Bitmap bitmap, String str, String str2, String str3, String str4) {
        return new KnownInterceptionDisclosureInfoBar(i, bitmap, str, str2, str3, str4);
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        infoBarLayout.getMessageLayout().a(0, this.r);
    }
}
