package org.chromium.chrome.browser.tab;

import J.N;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import defpackage.Cif;
import defpackage.d92;
import defpackage.ik3;
import defpackage.k53;
import defpackage.n23;
import defpackage.n3;
import defpackage.xz;
import defpackage.yc1;
import java.util.Iterator;
import org.chromium.base.BuildInfo;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.components.find_in_page.FindMatchRectsDetails;
import org.chromium.components.find_in_page.FindNotificationDetails;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public final class TabWebContentsDelegateAndroidImpl extends k53 {
    public final TabImpl a;
    public final k53 b;
    public final Handler c = new Handler();
    public final d92 d = new d92(7, this);

    public TabWebContentsDelegateAndroidImpl(TabImpl tabImpl, k53 k53Var) {
        this.a = tabImpl;
        this.b = k53Var;
    }

    @CalledByNative
    public static FindMatchRectsDetails createFindMatchRectsDetails(int i, int i2, RectF rectF) {
        return new FindMatchRectsDetails(i, i2, rectF);
    }

    @CalledByNative
    public static FindNotificationDetails createFindNotificationDetails(int i, Rect rect, int i2, boolean z) {
        return new FindNotificationDetails(i, rect, i2, z);
    }

    @CalledByNative
    public static Rect createRect(int i, int i2, int i3, int i4) {
        return new Rect(i, i2, i3, i4);
    }

    @CalledByNative
    public static RectF createRectF(float f, float f2, float f3, float f4) {
        return new RectF(f, f2, f3, f4);
    }

    @CalledByNative
    public static void setMatchRectByIndex(FindMatchRectsDetails findMatchRectsDetails, int i, RectF rectF) {
        findMatchRectsDetails.b[i] = rectF;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int a() {
        return this.b.a();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void activateContents() {
        this.b.activateContents();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean addMessageToConsole(int i, String str, int i2, String str2) {
        return !BuildInfo.b();
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z) {
        return this.b.addNewContents(webContents, webContents2, i, rect, z);
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean canShowAppBanners() {
        return this.b.canShowAppBanners();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void closeContents() {
        Handler handler = this.c;
        d92 d92Var = this.d;
        handler.removeCallbacks(d92Var);
        handler.post(d92Var);
        this.b.closeContents();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean controlsResizeView() {
        return this.b.controlsResizeView();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void enterFullscreenModeForTab(boolean z, boolean z2) {
        this.b.enterFullscreenModeForTab(z, z2);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void exitFullscreenModeForTab() {
        this.b.exitFullscreenModeForTab();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void fullscreenStateChangedForTab(boolean z, boolean z2) {
        this.b.fullscreenStateChangedForTab(z, z2);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getBottomControlsHeight() {
        return this.b.getBottomControlsHeight();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getBottomControlsMinHeight() {
        return this.b.getBottomControlsMinHeight();
    }

    @Override // defpackage.k53
    @CalledByNative
    public String getManifestScope() {
        return this.b.getManifestScope();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getTopControlsHeight() {
        return this.b.getTopControlsHeight();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getTopControlsMinHeight() {
        return this.b.getTopControlsMinHeight();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getVirtualKeyboardHeight() {
        return this.b.getVirtualKeyboardHeight();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void handleKeyboardEvent(KeyEvent keyEvent) {
        this.b.handleKeyboardEvent(keyEvent);
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean isCustomTab() {
        return this.b.isCustomTab();
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean isForceDarkWebContentEnabled() {
        return this.b.isForceDarkWebContentEnabled();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean isFullscreenForTabOrPending() {
        return this.b.isFullscreenForTabOrPending();
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean isInstalledWebappDelegateGeolocation() {
        return this.b.isInstalledWebappDelegateGeolocation();
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean isModalContextMenu() {
        return this.b.isModalContextMenu();
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean isNightModeEnabled() {
        return this.b.isNightModeEnabled();
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean isPictureInPictureEnabled() {
        return this.b.isPictureInPictureEnabled();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void loadingStateChanged(boolean z) {
        TabImpl tabImpl = this.a;
        WebContents webContents = tabImpl.g;
        boolean z2 = webContents != null && webContents.k();
        b<n23> bVar = tabImpl.k;
        if (!z2) {
            boolean z3 = tabImpl.r;
            tabImpl.r = false;
            WebContents webContents2 = tabImpl.g;
            if (webContents2 != null && !tabImpl.m && !tabImpl.C && !TabImpl.S(tabImpl) && webContents2.g() != null && !TextUtils.isEmpty(webContents2.g().e())) {
                if (TabImpl.H.contains(webContents2.g().e())) {
                    n3.i1(webContents2, -0.7d);
                    Log.i("test", "setZoomLevel :  host  " + webContents2.g().e());
                }
            }
            Iterator<n23> it = bVar.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((n23) aVar.next()).L0(tabImpl, z3);
                }
            }
        } else {
            if (z) {
                tabImpl.r = true;
            }
            Iterator<n23> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    break;
                } else {
                    ((n23) aVar2.next()).K(tabImpl, z);
                }
            }
        }
        this.b.loadingStateChanged(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void navigationStateChanged(int i) {
        int i2 = i & 2;
        TabImpl tabImpl = this.a;
        if (i2 != 0) {
            yc1.i(xz.a, tabImpl.getId(), tabImpl.g, tabImpl.getUrl());
            Cif.e(xz.a, tabImpl.getId(), tabImpl.g, tabImpl.getUrl(), tabImpl.isIncognito());
            ik3.d(xz.a, tabImpl.getId(), tabImpl.g, tabImpl.getUrl(), tabImpl.isIncognito());
        }
        if ((i & 8) != 0) {
            tabImpl.a0();
        }
        if ((i & 1) != 0) {
            b.a aVarN = tabImpl.N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).x0(tabImpl);
            }
        }
        this.b.navigationStateChanged(i);
    }

    @CalledByNative
    public final void onFindMatchRectsAvailable(FindMatchRectsDetails findMatchRectsDetails) {
        b.a aVarN = this.a.N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).j0(findMatchRectsDetails);
        }
    }

    @CalledByNative
    public final void onFindResultAvailable(FindNotificationDetails findNotificationDetails) {
        b.a aVarN = this.a.N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).j1(findNotificationDetails);
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void onUpdateUrl(GURL gurl) {
        b.a aVarN = this.a.N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).k(gurl);
        }
        this.b.onUpdateUrl(gurl);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i, boolean z) {
        this.b.openNewTab(gurl, str, resourceRequestBody, i, z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void rendererResponsive() {
        this.a.O(true);
        this.b.rendererResponsive();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void rendererUnresponsive() {
        TabImpl tabImpl = this.a;
        WebContents webContents = tabImpl.g;
        if (webContents != null) {
            N.MsGvyS6g(webContents);
        }
        tabImpl.O(false);
        this.b.rendererUnresponsive();
    }

    @Override // defpackage.k53
    @CalledByNative
    public void setOverlayMode(boolean z) {
        this.b.setOverlayMode(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean shouldAnimateBrowserControlsHeightChanges() {
        return this.b.shouldAnimateBrowserControlsHeightChanges();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean shouldBlockMediaRequest(GURL gurl) {
        return this.b.shouldBlockMediaRequest(gurl);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean shouldCreateWebContents(GURL gurl) {
        return this.b.shouldCreateWebContents(gurl);
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean shouldEnableEmbeddedMediaExperience() {
        return this.b.shouldEnableEmbeddedMediaExperience();
    }

    @Override // defpackage.k53
    @CalledByNative
    public boolean shouldResumeRequestsForCreatedWindow() {
        return this.b.shouldResumeRequestsForCreatedWindow();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void showRepostFormWarningDialog() {
        this.b.showRepostFormWarningDialog();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean takeFocus(boolean z) {
        return this.b.takeFocus(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void visibleSSLStateChanged() {
        AppHooks.get().getClass();
        TabImpl tabImpl = this.a;
        b.a aVarN = tabImpl.N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).R0(tabImpl);
        }
        this.b.visibleSSLStateChanged();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void webContentsCreated(WebContents webContents, long j, long j2, String str, GURL gurl, WebContents webContents2) {
        this.b.webContentsCreated(webContents, j, j2, str, gurl, webContents2);
    }
}
