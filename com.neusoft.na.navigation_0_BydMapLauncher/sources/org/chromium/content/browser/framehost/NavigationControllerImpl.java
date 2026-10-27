package org.chromium.content.browser.framehost;

import android.graphics.Bitmap;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.NavigationHistory;
import org.chromium.content_public.common.ResourceRequestBody;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class NavigationControllerImpl implements NavigationController {
    private long mNativeNavigationControllerAndroid;

    private native boolean nativeCanCopyStateOver(long j);

    private native boolean nativeCanGoBack(long j);

    private native boolean nativeCanGoForward(long j);

    private native boolean nativeCanGoToOffset(long j, int i);

    private native boolean nativeCanPruneAllButLastCommitted(long j);

    private native void nativeCancelPendingReload(long j);

    private native void nativeClearHistory(long j);

    private native void nativeClearSslPreferences(long j);

    private native void nativeContinuePendingReload(long j);

    private native void nativeCopyStateFrom(long j, long j2);

    private native void nativeCopyStateFromAndPrune(long j, long j2, boolean z);

    private native void nativeGetDirectedNavigationHistory(long j, NavigationHistory navigationHistory, boolean z, int i);

    private native NavigationEntry nativeGetEntryAtIndex(long j, int i);

    private native int nativeGetLastCommittedEntryIndex(long j);

    private native int nativeGetNavigationHistory(long j, Object obj);

    private native String nativeGetOriginalUrlForVisibleNavigationEntry(long j);

    private native NavigationEntry nativeGetPendingEntry(long j);

    private native boolean nativeGetUseDesktopUserAgent(long j);

    private native void nativeGoBack(long j);

    private native void nativeGoForward(long j);

    private native void nativeGoToNavigationIndex(long j, int i);

    private native void nativeGoToOffset(long j, int i);

    private native boolean nativeIsInitialNavigation(long j);

    private native void nativeLoadIfNecessary(long j);

    private native void nativeLoadUrl(long j, String str, int i, int i2, String str2, int i3, int i4, String str3, ResourceRequestBody resourceRequestBody, String str4, String str5, String str6, boolean z, boolean z2, boolean z3);

    private native void nativeReload(long j, boolean z);

    private native void nativeReloadBypassingCache(long j, boolean z);

    private native void nativeReloadDisableLoFi(long j, boolean z);

    private native void nativeReloadToRefreshContent(long j, boolean z);

    private native boolean nativeRemoveEntryAtIndex(long j, int i);

    private native void nativeRequestRestoreLoad(long j);

    private native void nativeSetUseDesktopUserAgent(long j, boolean z, boolean z2);

    private NavigationControllerImpl(long j) {
        this.mNativeNavigationControllerAndroid = j;
    }

    private static NavigationControllerImpl create(long j) {
        return new NavigationControllerImpl(j);
    }

    private void destroy() {
        this.mNativeNavigationControllerAndroid = 0L;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean canGoBack() {
        long j = this.mNativeNavigationControllerAndroid;
        return j != 0 && nativeCanGoBack(j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean canGoForward() {
        long j = this.mNativeNavigationControllerAndroid;
        return j != 0 && nativeCanGoForward(j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean canGoToOffset(int i) {
        long j = this.mNativeNavigationControllerAndroid;
        return j != 0 && nativeCanGoToOffset(j, i);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void goToOffset(int i) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeGoToOffset(j, i);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void goToNavigationIndex(int i) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeGoToNavigationIndex(j, i);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void goBack() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeGoBack(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void goForward() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeGoForward(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean isInitialNavigation() {
        long j = this.mNativeNavigationControllerAndroid;
        return j != 0 && nativeIsInitialNavigation(j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void loadIfNecessary() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeLoadIfNecessary(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void requestRestoreLoad() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeRequestRestoreLoad(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void reload(boolean z) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeReload(j, z);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void reloadToRefreshContent(boolean z) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeReloadToRefreshContent(j, z);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void reloadBypassingCache(boolean z) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeReloadBypassingCache(j, z);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void reloadDisableLoFi(boolean z) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeReloadDisableLoFi(j, z);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void cancelPendingReload() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeCancelPendingReload(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void continuePendingReload() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeContinuePendingReload(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void loadUrl(LoadUrlParams loadUrlParams) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeLoadUrl(j, loadUrlParams.getUrl(), loadUrlParams.getLoadUrlType(), loadUrlParams.getTransitionType(), loadUrlParams.getReferrer() != null ? loadUrlParams.getReferrer().getUrl() : null, loadUrlParams.getReferrer() != null ? loadUrlParams.getReferrer().getPolicy() : 0, loadUrlParams.getUserAgentOverrideOption(), loadUrlParams.getExtraHeadersString(), loadUrlParams.getPostData(), loadUrlParams.getBaseUrl(), loadUrlParams.getVirtualUrlForDataUrl(), loadUrlParams.getDataUrlAsString(), loadUrlParams.getCanLoadLocalResources(), loadUrlParams.getIsRendererInitiated(), loadUrlParams.getShouldReplaceCurrentEntry());
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void clearHistory() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeClearHistory(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public NavigationHistory getNavigationHistory() {
        if (this.mNativeNavigationControllerAndroid == 0) {
            return null;
        }
        NavigationHistory navigationHistory = new NavigationHistory();
        navigationHistory.setCurrentEntryIndex(nativeGetNavigationHistory(this.mNativeNavigationControllerAndroid, navigationHistory));
        return navigationHistory;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public NavigationHistory getDirectedNavigationHistory(boolean z, int i) {
        if (this.mNativeNavigationControllerAndroid == 0) {
            return null;
        }
        NavigationHistory navigationHistory = new NavigationHistory();
        nativeGetDirectedNavigationHistory(this.mNativeNavigationControllerAndroid, navigationHistory, z, i);
        return navigationHistory;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public String getOriginalUrlForVisibleNavigationEntry() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j == 0) {
            return null;
        }
        return nativeGetOriginalUrlForVisibleNavigationEntry(j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void clearSslPreferences() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeClearSslPreferences(j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean getUseDesktopUserAgent() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j == 0) {
            return false;
        }
        return nativeGetUseDesktopUserAgent(j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void setUseDesktopUserAgent(boolean z, boolean z2) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            nativeSetUseDesktopUserAgent(j, z, z2);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public NavigationEntry getEntryAtIndex(int i) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            return nativeGetEntryAtIndex(j, i);
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public NavigationEntry getPendingEntry() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            return nativeGetPendingEntry(j);
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public int getLastCommittedEntryIndex() {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            return nativeGetLastCommittedEntryIndex(j);
        }
        return -1;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean removeEntryAtIndex(int i) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j != 0) {
            return nativeRemoveEntryAtIndex(j, i);
        }
        return false;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean canCopyStateOver() {
        long j = this.mNativeNavigationControllerAndroid;
        return j != 0 && nativeCanCopyStateOver(j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean canPruneAllButLastCommitted() {
        long j = this.mNativeNavigationControllerAndroid;
        return j != 0 && nativeCanPruneAllButLastCommitted(j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void copyStateFrom(NavigationController navigationController) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j == 0) {
            return;
        }
        long j2 = ((NavigationControllerImpl) navigationController).mNativeNavigationControllerAndroid;
        if (j2 == 0) {
            return;
        }
        nativeCopyStateFrom(j, j2);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void copyStateFromAndPrune(NavigationController navigationController, boolean z) {
        long j = this.mNativeNavigationControllerAndroid;
        if (j == 0) {
            return;
        }
        long j2 = ((NavigationControllerImpl) navigationController).mNativeNavigationControllerAndroid;
        if (j2 == 0) {
            return;
        }
        nativeCopyStateFromAndPrune(j, j2, z);
    }

    private static void addToNavigationHistory(Object obj, Object obj2) {
        ((NavigationHistory) obj).addEntry((NavigationEntry) obj2);
    }

    private static NavigationEntry createNavigationEntry(int i, String str, String str2, String str3, String str4, Bitmap bitmap, int i2) {
        return new NavigationEntry(i, str, str2, str3, str4, bitmap, i2);
    }
}
