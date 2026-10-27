package org.chromium.chrome.browser.infobar;

import defpackage.xr;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.infobars.InfoBarCompactLayout;

/* JADX INFO: loaded from: classes.dex */
public class NearOomInfoBar extends InfoBar {
    public NearOomInfoBar() {
        super(R$drawable.infobar_chrome, R$color.infobar_icon_drawable_color, null, null);
    }

    @CalledByNative
    public static NearOomInfoBar create() {
        return new NearOomInfoBar();
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void o(InfoBarCompactLayout infoBarCompactLayout) {
        InfoBarCompactLayout.a aVar = new InfoBarCompactLayout.a(infoBarCompactLayout);
        aVar.b = infoBarCompactLayout.getResources().getString(R$string.near_oom_intervention_message);
        int i = R$string.near_oom_intervention_decline;
        aVar.b(infoBarCompactLayout.getResources().getString(i), new xr(25, this));
        aVar.a();
    }
}
