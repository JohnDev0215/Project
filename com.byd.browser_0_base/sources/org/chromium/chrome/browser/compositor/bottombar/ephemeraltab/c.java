package org.chromium.chrome.browser.compositor.bottombar.ephemeraltab;

import defpackage.ji;
import defpackage.yh0;
import defpackage.zh0;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: EphemeralTabMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public final ji a;
    public final EphemeralTabCoordinator.FaviconLoader b;
    public final org.chromium.base.b<yh0> c = new org.chromium.base.b<>();
    public final int d;
    public WebContents e;
    public zh0 f;
    public b g;
    public Profile h;

    public c(ji jiVar, EphemeralTabCoordinator.FaviconLoader faviconLoader, int i) {
        this.a = jiVar;
        this.b = faviconLoader;
        this.d = i;
    }

    public final void a(GURL gurl) {
        this.e.m().b(new LoadUrlParams(gurl.j(), 0));
    }
}
