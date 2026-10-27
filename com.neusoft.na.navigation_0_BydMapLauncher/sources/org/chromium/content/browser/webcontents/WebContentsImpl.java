package org.chromium.content.browser.webcontents;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.ContentBitmapCallback;
import org.chromium.content_public.browser.ImageDownloadCallback;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsObserver;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class WebContentsImpl implements WebContents {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long PARCELABLE_VERSION_ID = 0;
    private static final String PARCEL_PROCESS_GUARD_KEY = "processguard";
    private static final String PARCEL_VERSION_KEY = "version";
    private static final String PARCEL_WEBCONTENTS_KEY = "webcontents";
    private boolean mContextMenuOpened;
    private long mNativeWebContentsAndroid;
    private NavigationController mNavigationController;
    private WebContentsObserverProxy mObserverProxy;
    private static UUID sParcelableUUID = UUID.randomUUID();
    public static final Parcelable.Creator<WebContents> CREATOR = new Parcelable.Creator<WebContents>() { // from class: org.chromium.content.browser.webcontents.WebContentsImpl.1
        @Override // android.os.Parcelable.Creator
        public WebContents createFromParcel(Parcel parcel) {
            Bundle bundle = parcel.readBundle();
            if (bundle.getLong("version", -1L) != 0) {
                return null;
            }
            if (WebContentsImpl.sParcelableUUID.compareTo(((ParcelUuid) bundle.getParcelable(WebContentsImpl.PARCEL_PROCESS_GUARD_KEY)).getUuid()) != 0) {
                return null;
            }
            return WebContentsImpl.nativeFromNativePtr(bundle.getLong(WebContentsImpl.PARCEL_WEBCONTENTS_KEY));
        }

        @Override // android.os.Parcelable.Creator
        public WebContents[] newArray(int i) {
            return new WebContents[i];
        }
    };

    private native void nativeAddMessageToDevToolsConsole(long j, int i, String str);

    private native void nativeAdjustSelectionByCharacterOffset(long j, int i, int i2);

    private native void nativeCopy(long j);

    private native void nativeCut(long j);

    private static native void nativeDestroyWebContents(long j);

    private native int nativeDownloadImage(long j, String str, boolean z, int i, boolean z2, ImageDownloadCallback imageDownloadCallback);

    private native void nativeEvaluateJavaScript(long j, String str, JavaScriptCallback javaScriptCallback);

    private native void nativeEvaluateJavaScriptForTests(long j, String str, JavaScriptCallback javaScriptCallback);

    private native void nativeExitFullscreen(long j);

    private native boolean nativeFocusLocationBarByDefault(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native WebContents nativeFromNativePtr(long j);

    private native int nativeGetBackgroundColor(long j);

    private native void nativeGetContentBitmap(long j, ContentBitmapCallback contentBitmapCallback, Bitmap.Config config, float f, float f2, float f3, float f4, float f5);

    private native String nativeGetEncoding(long j);

    private native String nativeGetLastCommittedURL(long j);

    private native int nativeGetThemeColor(long j);

    private native String nativeGetTitle(long j);

    private native String nativeGetURL(long j);

    private native String nativeGetVisibleURL(long j);

    private native boolean nativeHasAccessedInitialDocument(long j);

    private native void nativeInsertCSS(long j, String str);

    private native boolean nativeIsIncognito(long j);

    private native boolean nativeIsLoading(long j);

    private native boolean nativeIsLoadingToDifferentDocument(long j);

    private native boolean nativeIsRenderWidgetHostViewReady(long j);

    private native boolean nativeIsShowingInterstitialPage(long j);

    private native void nativeOnContextMenuClosed(long j);

    private native void nativeOnHide(long j);

    private native void nativeOnShow(long j);

    private native void nativePaste(long j);

    private native void nativeReloadLoFiImages(long j);

    private native void nativeReplace(long j, String str);

    private native void nativeRequestAccessibilitySnapshot(long j, AccessibilitySnapshotCallback accessibilitySnapshotCallback);

    private native void nativeResumeLoadingCreatedWebContents(long j);

    private native void nativeResumeMediaSession(long j);

    private native void nativeScrollFocusedEditableNodeIntoView(long j);

    private native void nativeSelectAll(long j);

    private native void nativeSelectWordAroundCaret(long j);

    private native void nativeSendMessageToFrame(long j, String str, String str2, String str3);

    private native void nativeSetAudioMuted(long j, boolean z);

    private native void nativeShowImeIfNeeded(long j);

    private native void nativeShowInterstitialPage(long j, String str, long j2);

    private native void nativeStop(long j);

    private native void nativeStopMediaSession(long j);

    private native void nativeSuspendAllMediaPlayers(long j);

    private native void nativeSuspendMediaSession(long j);

    private native void nativeUnselect(long j);

    private native void nativeUpdateTopControlsState(long j, boolean z, boolean z2, boolean z3);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static void invalidateSerializedWebContentsForTesting() {
        sParcelableUUID = UUID.randomUUID();
    }

    private WebContentsImpl(long j, NavigationController navigationController) {
        this.mNativeWebContentsAndroid = j;
        this.mNavigationController = navigationController;
    }

    private static WebContentsImpl create(long j, NavigationController navigationController) {
        return new WebContentsImpl(j, navigationController);
    }

    private void clearNativePtr() {
        this.mNativeWebContentsAndroid = 0L;
        this.mNavigationController = null;
        WebContentsObserverProxy webContentsObserverProxy = this.mObserverProxy;
        if (webContentsObserverProxy != null) {
            webContentsObserverProxy.destroy();
            this.mObserverProxy = null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putLong("version", 0L);
        bundle.putParcelable(PARCEL_PROCESS_GUARD_KEY, new ParcelUuid(sParcelableUUID));
        bundle.putLong(PARCEL_WEBCONTENTS_KEY, this.mNativeWebContentsAndroid);
        parcel.writeBundle(bundle);
    }

    private long getNativePointer() {
        return this.mNativeWebContentsAndroid;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void destroy() {
        if (!ThreadUtils.runningOnUiThread()) {
            throw new IllegalStateException("Attempting to destroy WebContents on non-UI thread");
        }
        long j = this.mNativeWebContentsAndroid;
        if (j != 0) {
            nativeDestroyWebContents(j);
        }
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean isDestroyed() {
        return this.mNativeWebContentsAndroid == 0;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public NavigationController getNavigationController() {
        return this.mNavigationController;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String getTitle() {
        return nativeGetTitle(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String getVisibleUrl() {
        return nativeGetVisibleURL(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean isLoading() {
        return nativeIsLoading(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean isLoadingToDifferentDocument() {
        return nativeIsLoadingToDifferentDocument(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void stop() {
        nativeStop(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void cut() {
        nativeCut(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void copy() {
        nativeCopy(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void paste() {
        nativePaste(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void replace(String str) {
        nativeReplace(this.mNativeWebContentsAndroid, str);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void selectAll() {
        nativeSelectAll(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void unselect() {
        if (isDestroyed()) {
            return;
        }
        nativeUnselect(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void insertCSS(String str) {
        if (isDestroyed()) {
            return;
        }
        nativeInsertCSS(this.mNativeWebContentsAndroid, str);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void onHide() {
        nativeOnHide(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void onShow() {
        nativeOnShow(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void suspendAllMediaPlayers() {
        nativeSuspendAllMediaPlayers(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void setAudioMuted(boolean z) {
        nativeSetAudioMuted(this.mNativeWebContentsAndroid, z);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int getBackgroundColor() {
        return nativeGetBackgroundColor(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void showInterstitialPage(String str, long j) {
        nativeShowInterstitialPage(this.mNativeWebContentsAndroid, str, j);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean isShowingInterstitialPage() {
        return nativeIsShowingInterstitialPage(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean focusLocationBarByDefault() {
        return nativeFocusLocationBarByDefault(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean isReady() {
        return nativeIsRenderWidgetHostViewReady(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void exitFullscreen() {
        nativeExitFullscreen(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void updateTopControlsState(boolean z, boolean z2, boolean z3) {
        nativeUpdateTopControlsState(this.mNativeWebContentsAndroid, z, z2, z3);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void showImeIfNeeded() {
        nativeShowImeIfNeeded(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void scrollFocusedEditableNodeIntoView() {
        nativeScrollFocusedEditableNodeIntoView(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void selectWordAroundCaret() {
        nativeSelectWordAroundCaret(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void adjustSelectionByCharacterOffset(int i, int i2) {
        nativeAdjustSelectionByCharacterOffset(this.mNativeWebContentsAndroid, i, i2);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String getUrl() {
        if (isDestroyed()) {
            return null;
        }
        return nativeGetURL(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String getLastCommittedUrl() {
        return nativeGetLastCommittedURL(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean isIncognito() {
        return nativeIsIncognito(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void resumeLoadingCreatedWebContents() {
        nativeResumeLoadingCreatedWebContents(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void evaluateJavaScript(String str, JavaScriptCallback javaScriptCallback) {
        if (isDestroyed() || str == null) {
            return;
        }
        nativeEvaluateJavaScript(this.mNativeWebContentsAndroid, str, javaScriptCallback);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void evaluateJavaScriptForTests(String str, JavaScriptCallback javaScriptCallback) {
        if (str == null) {
            return;
        }
        nativeEvaluateJavaScriptForTests(this.mNativeWebContentsAndroid, str, javaScriptCallback);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void addMessageToDevToolsConsole(int i, String str) {
        nativeAddMessageToDevToolsConsole(this.mNativeWebContentsAndroid, i, str);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void sendMessageToFrame(String str, String str2, String str3) {
        nativeSendMessageToFrame(this.mNativeWebContentsAndroid, str, str2, str3);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public boolean hasAccessedInitialDocument() {
        return nativeHasAccessedInitialDocument(this.mNativeWebContentsAndroid);
    }

    private static void onEvaluateJavaScriptResult(String str, JavaScriptCallback javaScriptCallback) {
        javaScriptCallback.handleJavaScriptResult(str);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int getThemeColor() {
        return nativeGetThemeColor(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void requestAccessibilitySnapshot(AccessibilitySnapshotCallback accessibilitySnapshotCallback) {
        nativeRequestAccessibilitySnapshot(this.mNativeWebContentsAndroid, accessibilitySnapshotCallback);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void resumeMediaSession() {
        nativeResumeMediaSession(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void suspendMediaSession() {
        nativeSuspendMediaSession(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void stopMediaSession() {
        nativeStopMediaSession(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public String getEncoding() {
        return nativeGetEncoding(this.mNativeWebContentsAndroid);
    }

    private static void onAccessibilitySnapshot(AccessibilitySnapshotNode accessibilitySnapshotNode, AccessibilitySnapshotCallback accessibilitySnapshotCallback) {
        accessibilitySnapshotCallback.onAccessibilitySnapshot(accessibilitySnapshotNode);
    }

    private static void addAccessibilityNodeAsChild(AccessibilitySnapshotNode accessibilitySnapshotNode, AccessibilitySnapshotNode accessibilitySnapshotNode2) {
        accessibilitySnapshotNode.addChild(accessibilitySnapshotNode2);
    }

    private static AccessibilitySnapshotNode createAccessibilitySnapshotNode(int i, int i2, int i3, int i4, boolean z, String str, int i5, int i6, float f, int i7, String str2) {
        AccessibilitySnapshotNode accessibilitySnapshotNode = new AccessibilitySnapshotNode(str, str2);
        if (f >= 0.0d) {
            accessibilitySnapshotNode.setStyle(i5, i6, f, (i7 & 1) > 0, (i7 & 2) > 0, (i7 & 4) > 0, (i7 & 8) > 0);
        }
        accessibilitySnapshotNode.setLocationInfo(i, i2, i3, i4, z);
        return accessibilitySnapshotNode;
    }

    private static void setAccessibilitySnapshotSelection(AccessibilitySnapshotNode accessibilitySnapshotNode, int i, int i2) {
        accessibilitySnapshotNode.setSelection(i, i2);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void addObserver(WebContentsObserver webContentsObserver) {
        if (this.mObserverProxy == null) {
            this.mObserverProxy = new WebContentsObserverProxy(this);
        }
        this.mObserverProxy.addObserver(webContentsObserver);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void removeObserver(WebContentsObserver webContentsObserver) {
        WebContentsObserverProxy webContentsObserverProxy = this.mObserverProxy;
        if (webContentsObserverProxy == null) {
            return;
        }
        webContentsObserverProxy.removeObserver(webContentsObserver);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public ObserverList.RewindableIterator<WebContentsObserver> getObserversForTesting() {
        return this.mObserverProxy.getObserversForTesting();
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void getContentBitmapAsync(Bitmap.Config config, float f, Rect rect, ContentBitmapCallback contentBitmapCallback) {
        nativeGetContentBitmap(this.mNativeWebContentsAndroid, contentBitmapCallback, config, f, rect.left, rect.top, rect.width(), rect.height());
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void onContextMenuOpened() {
        this.mContextMenuOpened = true;
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void onContextMenuClosed() {
        if (this.mContextMenuOpened) {
            this.mContextMenuOpened = false;
            long j = this.mNativeWebContentsAndroid;
            if (j != 0) {
                nativeOnContextMenuClosed(j);
            }
        }
    }

    private void onGetContentBitmapFinished(ContentBitmapCallback contentBitmapCallback, Bitmap bitmap, int i) {
        contentBitmapCallback.onFinishGetBitmap(bitmap, i);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public void reloadLoFiImages() {
        nativeReloadLoFiImages(this.mNativeWebContentsAndroid);
    }

    @Override // org.chromium.content_public.browser.WebContents
    public int downloadImage(String str, boolean z, int i, boolean z2, ImageDownloadCallback imageDownloadCallback) {
        return nativeDownloadImage(this.mNativeWebContentsAndroid, str, z, i, z2, imageDownloadCallback);
    }

    private void onDownloadImageFinished(ImageDownloadCallback imageDownloadCallback, int i, int i2, String str, List<Bitmap> list, List<Rect> list2) {
        imageDownloadCallback.onFinishDownloadImage(i, i2, str, list, list2);
    }

    private static List<Bitmap> createBitmapList() {
        return new ArrayList();
    }

    private static void addToBitmapList(List<Bitmap> list, Bitmap bitmap) {
        list.add(bitmap);
    }

    private static List<Rect> createSizeList() {
        return new ArrayList();
    }

    private static void createSizeAndAddToList(List<Rect> list, int i, int i2) {
        list.add(new Rect(0, 0, i, i2));
    }
}
