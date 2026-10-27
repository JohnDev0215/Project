package org.chromium.components.content_settings;

import J.N;
import android.widget.TextView;
import defpackage.f00;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.components.page_info.PageInfoRowView;
import org.chromium.components.page_info.d;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class CookieControlsBridge {
    public long a;
    public final f00 b;

    public CookieControlsBridge(f00 f00Var, WebContents webContents, Profile profile) {
        this.b = f00Var;
        this.a = N.Ma648rK8(this, webContents, profile);
    }

    @CalledByNative
    public final void onCookieBlockingStatusChanged(int i, int i2) {
        d dVar = (d) this.b;
        dVar.o = i;
        boolean z = i2 != 0;
        dVar.p = z;
        PageInfoCookiesPreference pageInfoCookiesPreference = dVar.l;
        if (pageInfoCookiesPreference != null) {
            pageInfoCookiesPreference.M0(i, z);
        }
    }

    @CalledByNative
    public final void onCookiesCountChanged(int i, int i2) {
        d dVar = (d) this.b;
        dVar.m = i;
        dVar.n = i2;
        PageInfoRowView pageInfoRowView = dVar.h;
        String quantityString = i2 > 0 ? pageInfoRowView.getContext().getResources().getQuantityString(org.chromium.components.page_info.R$plurals.cookie_controls_blocked_cookies, i2, Integer.valueOf(i2)) : null;
        TextView textView = pageInfoRowView.g;
        textView.setText(quantityString);
        textView.setVisibility(quantityString == null ? 8 : 0);
        PageInfoCookiesPreference pageInfoCookiesPreference = dVar.l;
        if (pageInfoCookiesPreference != null) {
            pageInfoCookiesPreference.N0(i, i2);
        }
    }
}
