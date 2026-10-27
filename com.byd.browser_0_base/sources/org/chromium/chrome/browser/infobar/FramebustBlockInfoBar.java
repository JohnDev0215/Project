package org.chromium.chrome.browser.infobar;

import J.N;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.C0175b;
import defpackage.ie0;
import defpackage.ko0;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.infobars.InfoBarCompactLayout;
import org.chromium.components.infobars.InfoBarControlLayout;
import org.chromium.components.infobars.InfoBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class FramebustBlockInfoBar extends InfoBar {
    public final String o;
    public boolean p;

    public FramebustBlockInfoBar(String str) {
        super(R$drawable.infobar_chrome, R$color.infobar_icon_drawable_color, null, null);
        this.o = str;
    }

    @CalledByNative
    public static FramebustBlockInfoBar create(String str) {
        return new FramebustBlockInfoBar(str);
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void c(boolean z) {
        s(1);
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void d() {
        if (this.p) {
            super.d();
            return;
        }
        this.p = true;
        this.j = q();
        InfoBarContainerView infoBarContainerView = ((InfoBarContainer) this.i).q;
        if (infoBarContainerView != null) {
            infoBarContainerView.q.l();
        }
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void o(InfoBarCompactLayout infoBarCompactLayout) {
        InfoBarCompactLayout.a aVar = new InfoBarCompactLayout.a(infoBarCompactLayout);
        aVar.b = this.k.getString(R$string.redirect_blocked_short_message);
        int i = R$string.details_link;
        aVar.b(infoBarCompactLayout.getResources().getString(i), new ko0(9, this));
        aVar.a();
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        infoBarLayout.setMessage(this.k.getString(R$string.redirect_blocked_message));
        InfoBarControlLayout infoBarControlLayoutA = infoBarLayout.a();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.k).inflate(R$layout.infobar_control_url_ellipsizer, (ViewGroup) infoBarControlLayoutA, false);
        String strJ = this.o;
        String scheme = Uri.parse(strJ).getScheme();
        if (scheme == null) {
            strJ = C0175b.j("://", strJ);
            scheme = "";
        }
        String strSubstring = N.MR6Af3ZS(strJ, 0).substring(scheme.length() + 3);
        ((TextView) viewGroup.findViewById(R$id.url_scheme)).setText(scheme);
        ((TextView) viewGroup.findViewById(R$id.url_minus_scheme)).setText(strSubstring);
        viewGroup.setOnClickListener(new ie0(11, this));
        infoBarControlLayoutA.addView(viewGroup);
        infoBarLayout.h(this.k.getResources().getString(R$string.always_allow_redirects), null);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final boolean u() {
        return !this.p;
    }
}
