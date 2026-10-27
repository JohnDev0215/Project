package org.chromium.chrome.browser.tabmodel;

import J.N;
import defpackage.fz2;
import defpackage.g03;
import defpackage.g23;
import defpackage.p13;
import defpackage.r13;
import defpackage.y13;
import defpackage.zp1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TabModelJniBridge implements TabModel {
    public final boolean e;
    public final int f;
    public long g;

    public TabModelJniBridge(Profile profile, int i) {
        this.e = profile.h();
        this.f = i;
    }

    @CalledByNative
    public static boolean hasOtherRelatedTabs(Tab tab) {
        zp1<y13> zp1VarT;
        y13 y13Var;
        WindowAndroid windowAndroidD = tab.D();
        if (windowAndroidD == null || (zp1VarT = g23.t(windowAndroidD)) == null || (y13Var = zp1VarT.get()) == null) {
            return false;
        }
        p13 p13VarD = y13Var.u().d(tab.isIncognito());
        if (p13VarD instanceof g03) {
            return ((g03) p13VarD).F(tab);
        }
        return false;
    }

    @CalledByNative
    public abstract boolean closeTabAt(int i);

    @CalledByNative
    public Tab createNewTabForDevTools(GURL gurl) {
        return t(false).b(2, null, new LoadUrlParams(gurl.j(), 0));
    }

    @CalledByNative
    public abstract boolean createTabWithWebContents(Tab tab, Profile profile, WebContents webContents);

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public void destroy() {
        long j = this.g;
        if (j != 0) {
            N.Mg3Aho0E(j, this);
            this.g = 0L;
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final Profile f() {
        return (Profile) N.McKCR2Q3(this.g, this);
    }

    @Override // defpackage.w03
    @CalledByNative
    public abstract int getCount();

    @Override // defpackage.w03
    @CalledByNative
    public abstract Tab getTabAt(int i);

    @Override // defpackage.w03
    @CalledByNative
    public abstract int index();

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    @CalledByNative
    public abstract boolean isActiveModel();

    @Override // defpackage.w03
    public final boolean isIncognito() {
        return this.e;
    }

    @CalledByNative
    public abstract boolean isSessionRestoreInProgress();

    @CalledByNative
    public abstract void openNewTab(Tab tab, GURL gurl, Origin origin, String str, ResourceRequestBody resourceRequestBody, int i, boolean z, boolean z2);

    @CalledByNative
    public final void setIndex(int i) {
        ((r13) this).q(i, 3, false);
    }

    public abstract fz2 t(boolean z);
}
