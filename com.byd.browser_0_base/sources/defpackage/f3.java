package defpackage;

import J.N;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ActivityTabWebContentsDelegateAndroid.java */
/* JADX INFO: loaded from: classes.dex */
public class f3 extends k53 {
    public final m8<WebContents, GURL> a = new m8<>();
    public final Tab b;
    public Activity c;
    public final jp d;
    public final boolean e;
    public final kj f;
    public final rq0 g;
    public final gz2 h;
    public final nw2<y13> i;
    public final nw2<CompositorViewHolder> j;
    public final nw2<bi1> k;

    /* JADX INFO: compiled from: ActivityTabWebContentsDelegateAndroid.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            tab.t(this);
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
            if (windowAndroid == null) {
                f3.this.c = null;
            }
        }
    }

    public f3(Tab tab, Activity activity, jp jpVar, boolean z, kj kjVar, rq0 rq0Var, gz2 gz2Var, nw2<y13> nw2Var, nw2<CompositorViewHolder> nw2Var2, nw2<bi1> nw2Var3) {
        this.b = tab;
        this.c = activity;
        this.d = jpVar;
        this.e = z;
        this.f = kjVar;
        this.g = rq0Var;
        this.h = gz2Var;
        this.i = nw2Var;
        this.j = nw2Var2;
        this.k = nw2Var3;
        tab.x(new a());
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int a() {
        return 1;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void activateContents() {
        TabModel tabModelG;
        int iR;
        if (this.c == null) {
            Log.e("cr_ActivityTabWCDA", "Activity not set activateContents().  Bailing out.");
            return;
        }
        if (this.d.d()) {
            Log.e("cr_ActivityTabWCDA", "Activity destroyed before calling activateContents().  Bailing out.");
            return;
        }
        Tab tab = this.b;
        if (!tab.isInitialized()) {
            Log.e("cr_ActivityTabWCDA", "Tab not initialized before calling activateContents().  Bailing out.");
            return;
        }
        if (tab.isUserInteractable() || (iR = (tabModelG = this.i.get().g(tab.isIncognito())).r(tab)) == -1) {
            return;
        }
        tabModelG.q(iR, 3, false);
        if (ApplicationStatus.c(this.c) == 5) {
            b();
        }
    }

    @Override // defpackage.k53
    public final boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z) {
        Tab tab = this.b;
        fz2 fz2VarP0 = this.h.p0(tab.isIncognito());
        GURL gurlRemove = this.a.remove(webContents2);
        if (tab.n()) {
            return false;
        }
        boolean z2 = fz2VarP0.e() || fz2VarP0.d(tab, webContents2, 4, gurlRemove);
        if (z2) {
            if (i == 3) {
                if (CachedFeatureFlags.b(ar.d.b("TabGridLayoutAndroid:enable_tab_group_auto_creation"), true)) {
                    nw2<y13> nw2Var = this.i;
                    if (nw2Var.f() && nw2Var.get().u().c().B(tab.getId()).size() == 2) {
                        n3.X0("TabGroup.Created.DeveloperRequestedNewTab");
                    }
                }
                n3.X0("LinkNavigationOpenedInForegroundTab");
            } else if (i == 5) {
                AppHooks.get().getClass();
            }
        }
        return z2;
    }

    public void b() {
        Intent intentA = b.a(this.b.getId(), 0);
        intentA.addFlags(268435456);
        xz.a.startActivity(intentA);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean controlsResizeView() {
        nw2<CompositorViewHolder> nw2Var = this.j;
        return nw2Var.f() && nw2Var.get().f64J;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void enterFullscreenModeForTab(boolean z, boolean z2) {
        rq0 rq0Var = this.g;
        if (rq0Var != null) {
            rq0Var.a(new sq0(z, z2), this.b);
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void exitFullscreenModeForTab() {
        rq0 rq0Var = this.g;
        if (rq0Var != null) {
            rq0Var.b(this.b);
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void fullscreenStateChangedForTab(boolean z, boolean z2) {
        rq0 rq0Var = this.g;
        if (rq0Var == null || !rq0Var.g()) {
            return;
        }
        rq0Var.a(new sq0(z, z2), this.b);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getBottomControlsHeight() {
        kj kjVar = this.f;
        if (kjVar != null) {
            return kjVar.g();
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getBottomControlsMinHeight() {
        kj kjVar = this.f;
        if (kjVar != null) {
            return kjVar.b();
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getTopControlsHeight() {
        kj kjVar = this.f;
        if (kjVar != null) {
            return kjVar.l();
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getTopControlsMinHeight() {
        kj kjVar = this.f;
        if (kjVar != null) {
            return kjVar.a();
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getVirtualKeyboardHeight() {
        Activity activity = this.c;
        if (activity == null) {
            return 0;
        }
        return this.b.D().m().c(activity.getWindow().getDecorView().getRootView());
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void handleKeyboardEvent(android.view.KeyEvent r3) {
        /*
            r2 = this;
            int r0 = r3.getAction()
            if (r0 != 0) goto L2f
            android.app.Activity r0 = r2.c
            if (r0 == 0) goto L2f
            int r1 = r3.getKeyCode()
            boolean r0 = r0.onKeyDown(r1, r3)
            if (r0 == 0) goto L15
            return
        L15:
            int r0 = r3.getKeyCode()
            r1 = 111(0x6f, float:1.56E-43)
            if (r0 != r1) goto L2f
            boolean r0 = r3.hasNoModifiers()
            if (r0 == 0) goto L2f
            org.chromium.chrome.browser.tab.Tab r2 = r2.b
            org.chromium.content_public.browser.WebContents r2 = r2.c()
            if (r2 == 0) goto L2e
            r2.stop()
        L2e:
            return
        L2f:
            int r2 = r3.getKeyCode()
            r0 = 79
            if (r2 == r0) goto L42
            r0 = 222(0xde, float:3.11E-43)
            if (r2 == r0) goto L42
            switch(r2) {
                case 85: goto L42;
                case 86: goto L42;
                case 87: goto L42;
                case 88: goto L42;
                case 89: goto L42;
                case 90: goto L42;
                case 91: goto L42;
                default: goto L3e;
            }
        L3e:
            switch(r2) {
                case 126: goto L42;
                case 127: goto L42;
                case 128: goto L42;
                case 129: goto L42;
                case 130: goto L42;
                default: goto L41;
            }
        L41:
            goto L4f
        L42:
            android.content.Context r2 = defpackage.xz.a
            java.lang.String r0 = "audio"
            java.lang.Object r2 = r2.getSystemService(r0)
            android.media.AudioManager r2 = (android.media.AudioManager) r2
            r2.dispatchMediaKeyEvent(r3)
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f3.handleKeyboardEvent(android.view.KeyEvent):void");
    }

    @Override // defpackage.k53
    public final boolean isCustomTab() {
        return this.e;
    }

    @Override // defpackage.k53
    public final boolean isForceDarkWebContentEnabled() {
        Tab tab;
        WebContents webContentsC;
        Profile profileB;
        sk skVar = bq.a;
        if (N.M09VlOh_("WebContentsForceDark")) {
            return true;
        }
        if (!N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting") || (webContentsC = (tab = this.b).c()) == null || (profileB = Profile.b(tab.c())) == null) {
            return false;
        }
        return isNightModeEnabled() && mq3.a(profileB, webContentsC.s());
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean isFullscreenForTabOrPending() {
        rq0 rq0Var = this.g;
        if (rq0Var != null) {
            return rq0Var.g();
        }
        return false;
    }

    @Override // defpackage.k53
    public final boolean isModalContextMenu() {
        return !a8.k0(this.c);
    }

    @Override // defpackage.k53
    public final boolean isNightModeEnabled() {
        Activity activity = this.c;
        if (activity != null) {
            return z7.I(activity);
        }
        return false;
    }

    @Override // defpackage.k53
    public final boolean isPictureInPictureEnabled() {
        Activity activity = this.c;
        if (activity != null) {
            Context applicationContext = activity.getApplicationContext();
            if (Build.VERSION.SDK_INT >= 30) {
                TraceEvent traceEventH0 = TraceEvent.H0("PictureInPicture::isEnabled", null);
                try {
                    z = ((AppOpsManager) applicationContext.getSystemService("appops")).checkOpNoThrow("android:picture_in_picture", applicationContext.getApplicationInfo().uid, applicationContext.getPackageName()) == 0;
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            }
        }
        return z;
    }

    @Override // defpackage.k53
    public final void setOverlayMode(boolean z) {
        nw2<CompositorViewHolder> nw2Var = this.j;
        if (nw2Var.f()) {
            nw2Var.get().setOverlayMode(z);
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean shouldAnimateBrowserControlsHeightChanges() {
        kj kjVar = this.f;
        return kjVar != null && kjVar.j();
    }

    @Override // defpackage.k53
    public boolean shouldResumeRequestsForCreatedWindow() {
        return !this.h.p0(this.b.isIncognito()).e();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void showRepostFormWarningDialog() {
        Tab tab = this.b;
        fx2 fx2Var = (fx2) tab.B().b(fx2.class);
        if (fx2Var != null) {
            fx2Var.reset();
        }
        if (this.c == null || !tab.isUserInteractable()) {
            tab.c().m().v();
            return;
        }
        bi1 bi1Var = this.k.get();
        ym2 ym2Var = new ym2(bi1Var, new wb(4, this));
        Resources resources = this.c.getResources();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, ym2Var);
        bVar.d(di1.c, resources, R$string.http_post_warning_title);
        bVar.e(di1.f, resources.getString(R$string.http_post_warning));
        bVar.d(di1.j, resources, R$string.http_post_warning_resend);
        bVar.d(di1.m, resources, R$string.cancel);
        bVar.b(di1.q, true);
        bi1Var.j(bVar.a(), 0, true);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean takeFocus(boolean z) {
        Activity activity = this.c;
        if (activity == null) {
            return false;
        }
        if (z) {
            View viewFindViewById = activity.findViewById(R$id.menu_button);
            if (viewFindViewById != null && viewFindViewById.isShown()) {
                return viewFindViewById.requestFocus();
            }
            View viewFindViewById2 = this.c.findViewById(R$id.tab_switcher_button);
            if (viewFindViewById2 != null && viewFindViewById2.isShown()) {
                return viewFindViewById2.requestFocus();
            }
        } else {
            View viewFindViewById3 = activity.findViewById(R$id.url_bar);
            if (viewFindViewById3 != null) {
                return viewFindViewById3.requestFocus();
            }
        }
        return false;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void webContentsCreated(WebContents webContents, long j, long j2, String str, GURL gurl, WebContents webContents2) {
        this.a.put(webContents2, gurl);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i, boolean z) {
    }
}
