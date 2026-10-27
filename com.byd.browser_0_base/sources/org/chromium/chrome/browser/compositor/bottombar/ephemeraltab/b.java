package org.chromium.chrome.browser.compositor.bottombar.ephemeraltab;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ProgressBar;
import android.widget.TextView;
import defpackage.bk3;
import defpackage.ji;
import defpackage.oj2;
import defpackage.rq3;
import defpackage.uh3;
import defpackage.wb;
import defpackage.xz;
import defpackage.yh0;
import defpackage.zh0;
import org.chromium.base.b;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: EphemeralTabMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends rq3 {
    public boolean f;
    public GURL g;
    public final /* synthetic */ c h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, WebContents webContents) {
        super(webContents);
        this.h = cVar;
    }

    @Override // defpackage.rq3
    public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        boolean z = navigationHandle.g;
        c cVar = this.h;
        if (z) {
            this.f = navigationHandle.i;
            ((TextView) cVar.f.l.findViewById(R$id.origin)).setText(oj2.j(1, this.e.get().s()));
            return;
        }
        if (navigationHandle.h) {
            Toast.c(xz.a, R$string.ephemeral_tab_sheet_not_viewable, 0).e();
            cVar.a.c(cVar.f, true);
        }
    }

    @Override // defpackage.rq3
    public final void didStartNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (navigationHandle.c) {
            return;
        }
        GURL gurl = navigationHandle.e;
        if (gurl.equals(this.g)) {
            return;
        }
        boolean z = this.f;
        c cVar = this.h;
        if (z && bk3.l(gurl)) {
            cVar.a.c(cVar.f, true);
            this.g = null;
            return;
        }
        ji jiVar = cVar.a;
        zh0 zh0Var = cVar.f;
        b.a aVarD = cVar.c.d();
        while (aVarD.hasNext()) {
            ((yh0) aVarD.next()).b(gurl, jiVar, zh0Var);
        }
        this.g = gurl;
        final wb wbVar = new wb(10, this);
        Profile profile = cVar.h;
        final EphemeralTabCoordinator.FaviconLoader faviconLoader = cVar.b;
        faviconLoader.getClass();
        FaviconHelper.FaviconImageCallback faviconImageCallback = new FaviconHelper.FaviconImageCallback() { // from class: org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.a
            @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
            public final void onFaviconAvailable(Bitmap bitmap, GURL gurl2) {
                Context context = faviconLoader.a;
                wbVar.onResult(bitmap != null ? org.chromium.chrome.browser.ui.favicon.a.c(context.getResources(), bitmap) : uh3.e(context, R$drawable.ic_globe_24dp, R$color.default_icon_color_tint_list));
            }
        };
        FaviconHelper faviconHelper = faviconLoader.b;
        faviconHelper.getClass();
        faviconHelper.a(profile, gurl.j(), faviconLoader.c, faviconImageCallback);
    }

    @Override // defpackage.rq3
    public final void loadProgressChanged(float f) {
        zh0 zh0Var = this.h.f;
        if (zh0Var != null) {
            ((ProgressBar) zh0Var.l.findViewById(R$id.progress_bar)).setProgress(Math.round(f * 100.0f));
        }
    }

    @Override // defpackage.rq3
    public final void titleWasSet(String str) {
        c cVar = this.h;
        ((TextView) cVar.f.l.findViewById(R$id.title)).setText(str);
        zh0 zh0Var = cVar.f;
        b.a aVarD = cVar.c.d();
        while (aVarD.hasNext()) {
            ((yh0) aVarD.next()).c(zh0Var);
        }
    }
}
