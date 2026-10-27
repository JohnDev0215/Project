package org.chromium.components.infobars;

import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class ConfirmInfoBar extends InfoBar {
    public final String o;
    public final String p;
    public final String q;

    public ConfirmInfoBar(int i, int i2, Bitmap bitmap, String str, String str2, String str3, String str4) {
        super(i, i2, str, bitmap);
        this.o = str3;
        this.p = str4;
        this.q = str2;
    }

    @CalledByNative
    public static ConfirmInfoBar create(int i, Bitmap bitmap, String str, String str2, String str3, String str4) {
        return new ConfirmInfoBar(i, 0, bitmap, str, str2, str3, str4);
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.wz0
    public void c(boolean z) {
        s(z ? 1 : 2);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public void p(InfoBarLayout infoBarLayout) {
        infoBarLayout.h(this.o, this.p);
        String str = this.q;
        if (str == null || str.isEmpty()) {
            return;
        }
        infoBarLayout.r = str;
        infoBarLayout.n.setText(infoBarLayout.g());
    }
}
