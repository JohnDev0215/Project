package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: SearchBoxDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bg2 implements f {
    public final int a;
    public boolean b = false;
    public Tab c;

    public bg2(Context context) {
        this.a = yp.b(context, false);
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int a() {
        return this.a;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final rj3 b() {
        return rj3.h;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int c(boolean z) {
        return 0;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final Tab d() {
        return this.c;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final boolean e() {
        return false;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final km1 g() {
        return km1.a;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final String getTitle() {
        return "";
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final boolean isIncognito() {
        return false;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int j(boolean z, boolean z2) {
        return this.b ? 19 : 16;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int m() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final String n() {
        return zf2.a().f.b;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int p() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final boolean q() {
        return this.c != null;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int r() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final boolean s() {
        return false;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final void l(f.a aVar) {
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final void o(f.a aVar) {
    }
}
