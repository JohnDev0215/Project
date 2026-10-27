package org.xwalk.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Map;
import org.xwalk.core.extension.XWalkExternalExtensionManagerImpl;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkView extends FrameLayout {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ANIMATABLE = "animatable";
    public static final int RELOAD_IGNORE_CACHE = 1;
    public static final int RELOAD_NORMAL = 0;
    public static final String SURFACE_VIEW = "SurfaceView";
    public static final String TEXTURE_VIEW = "TextureView";
    private static final String XWALK_ATTRS_NAMESPACE = "http://schemas.android.com/apk/res-auto";
    private ReflectMethod addJavascriptInterfaceObjectStringMethod;
    private Object bridge;
    private ReflectMethod canZoomInMethod;
    private ReflectMethod canZoomOutMethod;
    private ReflectMethod captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod;
    private ReflectMethod clearCacheForSingleFileStringMethod;
    private ReflectMethod clearCachebooleanMethod;
    private ReflectMethod clearClientCertPreferencesRunnableMethod;
    private ReflectMethod clearFormDataMethod;
    private ReflectMethod clearMatchesMethod;
    private ReflectMethod clearSslPreferencesMethod;
    private ReflectMethod computeHorizontalScrollOffsetMethod;
    private ReflectMethod computeHorizontalScrollRangeMethod;
    private ReflectMethod computeVerticalScrollExtentMethod;
    private ReflectMethod computeVerticalScrollOffsetMethod;
    private ReflectMethod computeVerticalScrollRangeMethod;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod evaluateJavascriptStringValueCallbackMethod;
    private ReflectMethod findAllAsyncStringMethod;
    private ReflectMethod findNextbooleanMethod;
    private ReflectMethod getAPIVersionMethod;
    private ReflectMethod getCertificateMethod;
    private ReflectMethod getCompositingSurfaceTypeMethod;
    private ReflectMethod getContentHeightMethod;
    private ReflectMethod getExtensionManagerMethod;
    private ReflectMethod getFaviconMethod;
    private ReflectMethod getHitTestResultMethod;
    private ReflectMethod getNavigationHistoryMethod;
    private ReflectMethod getOriginalUrlMethod;
    private ReflectMethod getRemoteDebuggingUrlMethod;
    private ReflectMethod getSettingsMethod;
    private ReflectMethod getTitleMethod;
    private ReflectMethod getUrlMethod;
    private ReflectMethod getUserAgentStringMethod;
    private ReflectMethod getXWalkVersionMethod;
    private ReflectMethod hasEnteredFullscreenMethod;
    private ReflectMethod leaveFullscreenMethod;
    private ReflectMethod loadAppFromManifestStringStringMethod;
    private ReflectMethod loadDataStringStringStringMethod;
    private ReflectMethod loadDataWithBaseURLStringStringStringStringStringMethod;
    private ReflectMethod loadStringStringMapMethod;
    private ReflectMethod loadStringStringMethod;
    private ReflectMethod loadUrlStringMapMethod;
    private ReflectMethod loadUrlStringMethod;
    private String mAnimatable;
    private ReflectMethod onActivityResultintintIntentMethod;
    private ReflectMethod onCreateInputConnectionEditorInfoMethod;
    private ReflectMethod onDestroyMethod;
    private ReflectMethod onHideMethod;
    private ReflectMethod onNewIntentIntentMethod;
    private ReflectMethod onShowMethod;
    private ReflectMethod onTouchEventMotionEventMethod;
    private ReflectMethod pauseTimersMethod;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod reloadintMethod;
    private ReflectMethod removeJavascriptInterfaceStringMethod;
    private ReflectMethod restoreStateBundleMethod;
    private ReflectMethod resumeTimersMethod;
    private ReflectMethod saveStateBundleMethod;
    private ReflectMethod scrollByintintMethod;
    private ReflectMethod scrollTointintMethod;
    private ReflectMethod setAcceptLanguagesStringMethod;
    private ReflectMethod setBackgroundColorintMethod;
    private ReflectMethod setDownloadListenerXWalkDownloadListenerInternalMethod;
    private ReflectMethod setFindListenerXWalkFindListenerInternalMethod;
    private ReflectMethod setInitialScaleintMethod;
    private ReflectMethod setNetworkAvailablebooleanMethod;
    private ReflectMethod setOnTouchListenerOnTouchListenerMethod;
    private ReflectMethod setOriginAccessWhitelistStringStringArrayMethod;
    private ReflectMethod setResourceClientXWalkResourceClientInternalMethod;
    private ReflectMethod setSurfaceViewVisibilityintMethod;
    private ReflectMethod setUIClientXWalkUIClientInternalMethod;
    private ReflectMethod setUserAgentStringStringMethod;
    private ReflectMethod setXWalkViewInternalVisibilityintMethod;
    private ReflectMethod setZOrderOnTopbooleanMethod;
    private ReflectMethod startActivityForResultIntentintBundleMethod;
    private ReflectMethod stopLoadingMethod;
    private ReflectMethod zoomByfloatMethod;
    private ReflectMethod zoomInMethod;
    private ReflectMethod zoomOutMethod;

    @Override // android.view.View
    public void setLayerType(int i, Paint paint) {
    }

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkView(Context context) {
        super(context, null);
        this.loadStringStringMethod = new ReflectMethod((Class<?>) null, "load", (Class<?>[]) new Class[0]);
        this.loadStringStringMapMethod = new ReflectMethod((Class<?>) null, "load", (Class<?>[]) new Class[0]);
        this.loadDataStringStringStringMethod = new ReflectMethod((Class<?>) null, "loadData", (Class<?>[]) new Class[0]);
        this.loadDataWithBaseURLStringStringStringStringStringMethod = new ReflectMethod((Class<?>) null, "loadDataWithBaseURL", (Class<?>[]) new Class[0]);
        this.loadUrlStringMethod = new ReflectMethod((Class<?>) null, "loadUrl", (Class<?>[]) new Class[0]);
        this.loadUrlStringMapMethod = new ReflectMethod((Class<?>) null, "loadUrl", (Class<?>[]) new Class[0]);
        this.loadAppFromManifestStringStringMethod = new ReflectMethod((Class<?>) null, "loadAppFromManifest", (Class<?>[]) new Class[0]);
        this.reloadintMethod = new ReflectMethod((Class<?>) null, "reload", (Class<?>[]) new Class[0]);
        this.stopLoadingMethod = new ReflectMethod((Class<?>) null, "stopLoading", (Class<?>[]) new Class[0]);
        this.getUrlMethod = new ReflectMethod((Class<?>) null, "getUrl", (Class<?>[]) new Class[0]);
        this.getHitTestResultMethod = new ReflectMethod((Class<?>) null, "getHitTestResult", (Class<?>[]) new Class[0]);
        this.getContentHeightMethod = new ReflectMethod((Class<?>) null, "getContentHeight", (Class<?>[]) new Class[0]);
        this.getTitleMethod = new ReflectMethod((Class<?>) null, "getTitle", (Class<?>[]) new Class[0]);
        this.getOriginalUrlMethod = new ReflectMethod((Class<?>) null, "getOriginalUrl", (Class<?>[]) new Class[0]);
        this.getNavigationHistoryMethod = new ReflectMethod((Class<?>) null, "getNavigationHistory", (Class<?>[]) new Class[0]);
        this.addJavascriptInterfaceObjectStringMethod = new ReflectMethod((Class<?>) null, "addJavascriptInterface", (Class<?>[]) new Class[0]);
        this.removeJavascriptInterfaceStringMethod = new ReflectMethod((Class<?>) null, "removeJavascriptInterface", (Class<?>[]) new Class[0]);
        this.evaluateJavascriptStringValueCallbackMethod = new ReflectMethod((Class<?>) null, "evaluateJavascript", (Class<?>[]) new Class[0]);
        this.clearCachebooleanMethod = new ReflectMethod((Class<?>) null, "clearCache", (Class<?>[]) new Class[0]);
        this.clearCacheForSingleFileStringMethod = new ReflectMethod((Class<?>) null, "clearCacheForSingleFile", (Class<?>[]) new Class[0]);
        this.hasEnteredFullscreenMethod = new ReflectMethod((Class<?>) null, "hasEnteredFullscreen", (Class<?>[]) new Class[0]);
        this.leaveFullscreenMethod = new ReflectMethod((Class<?>) null, "leaveFullscreen", (Class<?>[]) new Class[0]);
        this.pauseTimersMethod = new ReflectMethod((Class<?>) null, "pauseTimers", (Class<?>[]) new Class[0]);
        this.resumeTimersMethod = new ReflectMethod((Class<?>) null, "resumeTimers", (Class<?>[]) new Class[0]);
        this.onHideMethod = new ReflectMethod((Class<?>) null, "onHide", (Class<?>[]) new Class[0]);
        this.onShowMethod = new ReflectMethod((Class<?>) null, "onShow", (Class<?>[]) new Class[0]);
        this.onDestroyMethod = new ReflectMethod((Class<?>) null, "onDestroy", (Class<?>[]) new Class[0]);
        this.startActivityForResultIntentintBundleMethod = new ReflectMethod((Class<?>) null, "startActivityForResult", (Class<?>[]) new Class[0]);
        this.onActivityResultintintIntentMethod = new ReflectMethod((Class<?>) null, "onActivityResult", (Class<?>[]) new Class[0]);
        this.onNewIntentIntentMethod = new ReflectMethod((Class<?>) null, "onNewIntent", (Class<?>[]) new Class[0]);
        this.saveStateBundleMethod = new ReflectMethod((Class<?>) null, "saveState", (Class<?>[]) new Class[0]);
        this.restoreStateBundleMethod = new ReflectMethod((Class<?>) null, "restoreState", (Class<?>[]) new Class[0]);
        this.getAPIVersionMethod = new ReflectMethod((Class<?>) null, "getAPIVersion", (Class<?>[]) new Class[0]);
        this.getXWalkVersionMethod = new ReflectMethod((Class<?>) null, "getXWalkVersion", (Class<?>[]) new Class[0]);
        this.setUIClientXWalkUIClientInternalMethod = new ReflectMethod((Class<?>) null, "setUIClient", (Class<?>[]) new Class[0]);
        this.setResourceClientXWalkResourceClientInternalMethod = new ReflectMethod((Class<?>) null, "setResourceClient", (Class<?>[]) new Class[0]);
        this.setBackgroundColorintMethod = new ReflectMethod((Class<?>) null, "setBackgroundColor", (Class<?>[]) new Class[0]);
        this.setOriginAccessWhitelistStringStringArrayMethod = new ReflectMethod((Class<?>) null, "setOriginAccessWhitelist", (Class<?>[]) new Class[0]);
        this.setUserAgentStringStringMethod = new ReflectMethod((Class<?>) null, "setUserAgentString", (Class<?>[]) new Class[0]);
        this.getUserAgentStringMethod = new ReflectMethod((Class<?>) null, "getUserAgentString", (Class<?>[]) new Class[0]);
        this.setAcceptLanguagesStringMethod = new ReflectMethod((Class<?>) null, "setAcceptLanguages", (Class<?>[]) new Class[0]);
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod = new ReflectMethod((Class<?>) null, "captureBitmapAsync", (Class<?>[]) new Class[0]);
        this.getSettingsMethod = new ReflectMethod((Class<?>) null, "getSettings", (Class<?>[]) new Class[0]);
        this.setNetworkAvailablebooleanMethod = new ReflectMethod((Class<?>) null, "setNetworkAvailable", (Class<?>[]) new Class[0]);
        this.getRemoteDebuggingUrlMethod = new ReflectMethod((Class<?>) null, "getRemoteDebuggingUrl", (Class<?>[]) new Class[0]);
        this.zoomInMethod = new ReflectMethod((Class<?>) null, "zoomIn", (Class<?>[]) new Class[0]);
        this.zoomOutMethod = new ReflectMethod((Class<?>) null, "zoomOut", (Class<?>[]) new Class[0]);
        this.zoomByfloatMethod = new ReflectMethod((Class<?>) null, "zoomBy", (Class<?>[]) new Class[0]);
        this.canZoomInMethod = new ReflectMethod((Class<?>) null, "canZoomIn", (Class<?>[]) new Class[0]);
        this.canZoomOutMethod = new ReflectMethod((Class<?>) null, "canZoomOut", (Class<?>[]) new Class[0]);
        this.onCreateInputConnectionEditorInfoMethod = new ReflectMethod((Class<?>) null, "onCreateInputConnection", (Class<?>[]) new Class[0]);
        this.setInitialScaleintMethod = new ReflectMethod((Class<?>) null, "setInitialScale", (Class<?>[]) new Class[0]);
        this.getFaviconMethod = new ReflectMethod((Class<?>) null, "getFavicon", (Class<?>[]) new Class[0]);
        this.setZOrderOnTopbooleanMethod = new ReflectMethod((Class<?>) null, "setZOrderOnTop", (Class<?>[]) new Class[0]);
        this.clearFormDataMethod = new ReflectMethod((Class<?>) null, "clearFormData", (Class<?>[]) new Class[0]);
        this.setSurfaceViewVisibilityintMethod = new ReflectMethod((Class<?>) null, "setSurfaceViewVisibility", (Class<?>[]) new Class[0]);
        this.setXWalkViewInternalVisibilityintMethod = new ReflectMethod((Class<?>) null, "setXWalkViewInternalVisibility", (Class<?>[]) new Class[0]);
        this.setDownloadListenerXWalkDownloadListenerInternalMethod = new ReflectMethod((Class<?>) null, "setDownloadListener", (Class<?>[]) new Class[0]);
        this.onTouchEventMotionEventMethod = new ReflectMethod((Class<?>) null, "onTouchEvent", (Class<?>[]) new Class[0]);
        this.setOnTouchListenerOnTouchListenerMethod = new ReflectMethod((Class<?>) null, "setOnTouchListener", (Class<?>[]) new Class[0]);
        this.scrollTointintMethod = new ReflectMethod((Class<?>) null, "scrollTo", (Class<?>[]) new Class[0]);
        this.scrollByintintMethod = new ReflectMethod((Class<?>) null, "scrollBy", (Class<?>[]) new Class[0]);
        this.computeHorizontalScrollRangeMethod = new ReflectMethod((Class<?>) null, "computeHorizontalScrollRange", (Class<?>[]) new Class[0]);
        this.computeHorizontalScrollOffsetMethod = new ReflectMethod((Class<?>) null, "computeHorizontalScrollOffset", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollRangeMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollRange", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollOffsetMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollOffset", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollExtentMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollExtent", (Class<?>[]) new Class[0]);
        this.getExtensionManagerMethod = new ReflectMethod((Class<?>) null, "getExtensionManager", (Class<?>[]) new Class[0]);
        this.clearSslPreferencesMethod = new ReflectMethod((Class<?>) null, "clearSslPreferences", (Class<?>[]) new Class[0]);
        this.clearClientCertPreferencesRunnableMethod = new ReflectMethod((Class<?>) null, "clearClientCertPreferences", (Class<?>[]) new Class[0]);
        this.getCertificateMethod = new ReflectMethod((Class<?>) null, "getCertificate", (Class<?>[]) new Class[0]);
        this.setFindListenerXWalkFindListenerInternalMethod = new ReflectMethod((Class<?>) null, "setFindListener", (Class<?>[]) new Class[0]);
        this.findAllAsyncStringMethod = new ReflectMethod((Class<?>) null, "findAllAsync", (Class<?>[]) new Class[0]);
        this.findNextbooleanMethod = new ReflectMethod((Class<?>) null, "findNext", (Class<?>[]) new Class[0]);
        this.clearMatchesMethod = new ReflectMethod((Class<?>) null, "clearMatches", (Class<?>[]) new Class[0]);
        this.getCompositingSurfaceTypeMethod = new ReflectMethod((Class<?>) null, "getCompositingSurfaceType", (Class<?>[]) new Class[0]);
        SurfaceView surfaceView = new SurfaceView(context);
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        addView(surfaceView);
        ArrayList<Object> arrayList = new ArrayList<>();
        this.constructorTypes = arrayList;
        arrayList.add(Context.class);
        ArrayList<Object> arrayList2 = new ArrayList<>();
        this.constructorParams = arrayList2;
        arrayList2.add(context);
        this.postWrapperMethod = new ReflectMethod(this, "postXWalkViewInternalContextConstructor", (Class<?>[]) new Class[0]);
        reflectionInit();
    }

    public void postXWalkViewInternalContextConstructor() {
        addView((FrameLayout) this.bridge, new FrameLayout.LayoutParams(-1, -1));
        removeViewAt(0);
        new XWalkExternalExtensionManagerImpl(this);
    }

    public XWalkView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.loadStringStringMethod = new ReflectMethod((Class<?>) null, "load", (Class<?>[]) new Class[0]);
        this.loadStringStringMapMethod = new ReflectMethod((Class<?>) null, "load", (Class<?>[]) new Class[0]);
        this.loadDataStringStringStringMethod = new ReflectMethod((Class<?>) null, "loadData", (Class<?>[]) new Class[0]);
        this.loadDataWithBaseURLStringStringStringStringStringMethod = new ReflectMethod((Class<?>) null, "loadDataWithBaseURL", (Class<?>[]) new Class[0]);
        this.loadUrlStringMethod = new ReflectMethod((Class<?>) null, "loadUrl", (Class<?>[]) new Class[0]);
        this.loadUrlStringMapMethod = new ReflectMethod((Class<?>) null, "loadUrl", (Class<?>[]) new Class[0]);
        this.loadAppFromManifestStringStringMethod = new ReflectMethod((Class<?>) null, "loadAppFromManifest", (Class<?>[]) new Class[0]);
        this.reloadintMethod = new ReflectMethod((Class<?>) null, "reload", (Class<?>[]) new Class[0]);
        this.stopLoadingMethod = new ReflectMethod((Class<?>) null, "stopLoading", (Class<?>[]) new Class[0]);
        this.getUrlMethod = new ReflectMethod((Class<?>) null, "getUrl", (Class<?>[]) new Class[0]);
        this.getHitTestResultMethod = new ReflectMethod((Class<?>) null, "getHitTestResult", (Class<?>[]) new Class[0]);
        this.getContentHeightMethod = new ReflectMethod((Class<?>) null, "getContentHeight", (Class<?>[]) new Class[0]);
        this.getTitleMethod = new ReflectMethod((Class<?>) null, "getTitle", (Class<?>[]) new Class[0]);
        this.getOriginalUrlMethod = new ReflectMethod((Class<?>) null, "getOriginalUrl", (Class<?>[]) new Class[0]);
        this.getNavigationHistoryMethod = new ReflectMethod((Class<?>) null, "getNavigationHistory", (Class<?>[]) new Class[0]);
        this.addJavascriptInterfaceObjectStringMethod = new ReflectMethod((Class<?>) null, "addJavascriptInterface", (Class<?>[]) new Class[0]);
        this.removeJavascriptInterfaceStringMethod = new ReflectMethod((Class<?>) null, "removeJavascriptInterface", (Class<?>[]) new Class[0]);
        this.evaluateJavascriptStringValueCallbackMethod = new ReflectMethod((Class<?>) null, "evaluateJavascript", (Class<?>[]) new Class[0]);
        this.clearCachebooleanMethod = new ReflectMethod((Class<?>) null, "clearCache", (Class<?>[]) new Class[0]);
        this.clearCacheForSingleFileStringMethod = new ReflectMethod((Class<?>) null, "clearCacheForSingleFile", (Class<?>[]) new Class[0]);
        this.hasEnteredFullscreenMethod = new ReflectMethod((Class<?>) null, "hasEnteredFullscreen", (Class<?>[]) new Class[0]);
        this.leaveFullscreenMethod = new ReflectMethod((Class<?>) null, "leaveFullscreen", (Class<?>[]) new Class[0]);
        this.pauseTimersMethod = new ReflectMethod((Class<?>) null, "pauseTimers", (Class<?>[]) new Class[0]);
        this.resumeTimersMethod = new ReflectMethod((Class<?>) null, "resumeTimers", (Class<?>[]) new Class[0]);
        this.onHideMethod = new ReflectMethod((Class<?>) null, "onHide", (Class<?>[]) new Class[0]);
        this.onShowMethod = new ReflectMethod((Class<?>) null, "onShow", (Class<?>[]) new Class[0]);
        this.onDestroyMethod = new ReflectMethod((Class<?>) null, "onDestroy", (Class<?>[]) new Class[0]);
        this.startActivityForResultIntentintBundleMethod = new ReflectMethod((Class<?>) null, "startActivityForResult", (Class<?>[]) new Class[0]);
        this.onActivityResultintintIntentMethod = new ReflectMethod((Class<?>) null, "onActivityResult", (Class<?>[]) new Class[0]);
        this.onNewIntentIntentMethod = new ReflectMethod((Class<?>) null, "onNewIntent", (Class<?>[]) new Class[0]);
        this.saveStateBundleMethod = new ReflectMethod((Class<?>) null, "saveState", (Class<?>[]) new Class[0]);
        this.restoreStateBundleMethod = new ReflectMethod((Class<?>) null, "restoreState", (Class<?>[]) new Class[0]);
        this.getAPIVersionMethod = new ReflectMethod((Class<?>) null, "getAPIVersion", (Class<?>[]) new Class[0]);
        this.getXWalkVersionMethod = new ReflectMethod((Class<?>) null, "getXWalkVersion", (Class<?>[]) new Class[0]);
        this.setUIClientXWalkUIClientInternalMethod = new ReflectMethod((Class<?>) null, "setUIClient", (Class<?>[]) new Class[0]);
        this.setResourceClientXWalkResourceClientInternalMethod = new ReflectMethod((Class<?>) null, "setResourceClient", (Class<?>[]) new Class[0]);
        this.setBackgroundColorintMethod = new ReflectMethod((Class<?>) null, "setBackgroundColor", (Class<?>[]) new Class[0]);
        this.setOriginAccessWhitelistStringStringArrayMethod = new ReflectMethod((Class<?>) null, "setOriginAccessWhitelist", (Class<?>[]) new Class[0]);
        this.setUserAgentStringStringMethod = new ReflectMethod((Class<?>) null, "setUserAgentString", (Class<?>[]) new Class[0]);
        this.getUserAgentStringMethod = new ReflectMethod((Class<?>) null, "getUserAgentString", (Class<?>[]) new Class[0]);
        this.setAcceptLanguagesStringMethod = new ReflectMethod((Class<?>) null, "setAcceptLanguages", (Class<?>[]) new Class[0]);
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod = new ReflectMethod((Class<?>) null, "captureBitmapAsync", (Class<?>[]) new Class[0]);
        this.getSettingsMethod = new ReflectMethod((Class<?>) null, "getSettings", (Class<?>[]) new Class[0]);
        this.setNetworkAvailablebooleanMethod = new ReflectMethod((Class<?>) null, "setNetworkAvailable", (Class<?>[]) new Class[0]);
        this.getRemoteDebuggingUrlMethod = new ReflectMethod((Class<?>) null, "getRemoteDebuggingUrl", (Class<?>[]) new Class[0]);
        this.zoomInMethod = new ReflectMethod((Class<?>) null, "zoomIn", (Class<?>[]) new Class[0]);
        this.zoomOutMethod = new ReflectMethod((Class<?>) null, "zoomOut", (Class<?>[]) new Class[0]);
        this.zoomByfloatMethod = new ReflectMethod((Class<?>) null, "zoomBy", (Class<?>[]) new Class[0]);
        this.canZoomInMethod = new ReflectMethod((Class<?>) null, "canZoomIn", (Class<?>[]) new Class[0]);
        this.canZoomOutMethod = new ReflectMethod((Class<?>) null, "canZoomOut", (Class<?>[]) new Class[0]);
        this.onCreateInputConnectionEditorInfoMethod = new ReflectMethod((Class<?>) null, "onCreateInputConnection", (Class<?>[]) new Class[0]);
        this.setInitialScaleintMethod = new ReflectMethod((Class<?>) null, "setInitialScale", (Class<?>[]) new Class[0]);
        this.getFaviconMethod = new ReflectMethod((Class<?>) null, "getFavicon", (Class<?>[]) new Class[0]);
        this.setZOrderOnTopbooleanMethod = new ReflectMethod((Class<?>) null, "setZOrderOnTop", (Class<?>[]) new Class[0]);
        this.clearFormDataMethod = new ReflectMethod((Class<?>) null, "clearFormData", (Class<?>[]) new Class[0]);
        this.setSurfaceViewVisibilityintMethod = new ReflectMethod((Class<?>) null, "setSurfaceViewVisibility", (Class<?>[]) new Class[0]);
        this.setXWalkViewInternalVisibilityintMethod = new ReflectMethod((Class<?>) null, "setXWalkViewInternalVisibility", (Class<?>[]) new Class[0]);
        this.setDownloadListenerXWalkDownloadListenerInternalMethod = new ReflectMethod((Class<?>) null, "setDownloadListener", (Class<?>[]) new Class[0]);
        this.onTouchEventMotionEventMethod = new ReflectMethod((Class<?>) null, "onTouchEvent", (Class<?>[]) new Class[0]);
        this.setOnTouchListenerOnTouchListenerMethod = new ReflectMethod((Class<?>) null, "setOnTouchListener", (Class<?>[]) new Class[0]);
        this.scrollTointintMethod = new ReflectMethod((Class<?>) null, "scrollTo", (Class<?>[]) new Class[0]);
        this.scrollByintintMethod = new ReflectMethod((Class<?>) null, "scrollBy", (Class<?>[]) new Class[0]);
        this.computeHorizontalScrollRangeMethod = new ReflectMethod((Class<?>) null, "computeHorizontalScrollRange", (Class<?>[]) new Class[0]);
        this.computeHorizontalScrollOffsetMethod = new ReflectMethod((Class<?>) null, "computeHorizontalScrollOffset", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollRangeMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollRange", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollOffsetMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollOffset", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollExtentMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollExtent", (Class<?>[]) new Class[0]);
        this.getExtensionManagerMethod = new ReflectMethod((Class<?>) null, "getExtensionManager", (Class<?>[]) new Class[0]);
        this.clearSslPreferencesMethod = new ReflectMethod((Class<?>) null, "clearSslPreferences", (Class<?>[]) new Class[0]);
        this.clearClientCertPreferencesRunnableMethod = new ReflectMethod((Class<?>) null, "clearClientCertPreferences", (Class<?>[]) new Class[0]);
        this.getCertificateMethod = new ReflectMethod((Class<?>) null, "getCertificate", (Class<?>[]) new Class[0]);
        this.setFindListenerXWalkFindListenerInternalMethod = new ReflectMethod((Class<?>) null, "setFindListener", (Class<?>[]) new Class[0]);
        this.findAllAsyncStringMethod = new ReflectMethod((Class<?>) null, "findAllAsync", (Class<?>[]) new Class[0]);
        this.findNextbooleanMethod = new ReflectMethod((Class<?>) null, "findNext", (Class<?>[]) new Class[0]);
        this.clearMatchesMethod = new ReflectMethod((Class<?>) null, "clearMatches", (Class<?>[]) new Class[0]);
        this.getCompositingSurfaceTypeMethod = new ReflectMethod((Class<?>) null, "getCompositingSurfaceType", (Class<?>[]) new Class[0]);
        if (isInEditMode()) {
            return;
        }
        if (attributeSet != null) {
            this.mAnimatable = attributeSet.getAttributeValue(XWALK_ATTRS_NAMESPACE, ANIMATABLE);
        }
        SurfaceView surfaceView = new SurfaceView(context);
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        addView(surfaceView);
        ArrayList<Object> arrayList = new ArrayList<>();
        this.constructorTypes = arrayList;
        arrayList.add(Context.class);
        this.constructorTypes.add(AttributeSet.class);
        ArrayList<Object> arrayList2 = new ArrayList<>();
        this.constructorParams = arrayList2;
        arrayList2.add(context);
        this.constructorParams.add(attributeSet);
        this.postWrapperMethod = new ReflectMethod(this, "postXWalkViewInternalContextAttributeSetConstructor", (Class<?>[]) new Class[0]);
        reflectionInit();
    }

    public void postXWalkViewInternalContextAttributeSetConstructor() {
        addView((FrameLayout) this.bridge, new FrameLayout.LayoutParams(-1, -1));
        removeViewAt(0);
        new XWalkExternalExtensionManagerImpl(this);
    }

    @Deprecated
    public XWalkView(Context context, Activity activity) {
        super(context, null);
        this.loadStringStringMethod = new ReflectMethod((Class<?>) null, "load", (Class<?>[]) new Class[0]);
        this.loadStringStringMapMethod = new ReflectMethod((Class<?>) null, "load", (Class<?>[]) new Class[0]);
        this.loadDataStringStringStringMethod = new ReflectMethod((Class<?>) null, "loadData", (Class<?>[]) new Class[0]);
        this.loadDataWithBaseURLStringStringStringStringStringMethod = new ReflectMethod((Class<?>) null, "loadDataWithBaseURL", (Class<?>[]) new Class[0]);
        this.loadUrlStringMethod = new ReflectMethod((Class<?>) null, "loadUrl", (Class<?>[]) new Class[0]);
        this.loadUrlStringMapMethod = new ReflectMethod((Class<?>) null, "loadUrl", (Class<?>[]) new Class[0]);
        this.loadAppFromManifestStringStringMethod = new ReflectMethod((Class<?>) null, "loadAppFromManifest", (Class<?>[]) new Class[0]);
        this.reloadintMethod = new ReflectMethod((Class<?>) null, "reload", (Class<?>[]) new Class[0]);
        this.stopLoadingMethod = new ReflectMethod((Class<?>) null, "stopLoading", (Class<?>[]) new Class[0]);
        this.getUrlMethod = new ReflectMethod((Class<?>) null, "getUrl", (Class<?>[]) new Class[0]);
        this.getHitTestResultMethod = new ReflectMethod((Class<?>) null, "getHitTestResult", (Class<?>[]) new Class[0]);
        this.getContentHeightMethod = new ReflectMethod((Class<?>) null, "getContentHeight", (Class<?>[]) new Class[0]);
        this.getTitleMethod = new ReflectMethod((Class<?>) null, "getTitle", (Class<?>[]) new Class[0]);
        this.getOriginalUrlMethod = new ReflectMethod((Class<?>) null, "getOriginalUrl", (Class<?>[]) new Class[0]);
        this.getNavigationHistoryMethod = new ReflectMethod((Class<?>) null, "getNavigationHistory", (Class<?>[]) new Class[0]);
        this.addJavascriptInterfaceObjectStringMethod = new ReflectMethod((Class<?>) null, "addJavascriptInterface", (Class<?>[]) new Class[0]);
        this.removeJavascriptInterfaceStringMethod = new ReflectMethod((Class<?>) null, "removeJavascriptInterface", (Class<?>[]) new Class[0]);
        this.evaluateJavascriptStringValueCallbackMethod = new ReflectMethod((Class<?>) null, "evaluateJavascript", (Class<?>[]) new Class[0]);
        this.clearCachebooleanMethod = new ReflectMethod((Class<?>) null, "clearCache", (Class<?>[]) new Class[0]);
        this.clearCacheForSingleFileStringMethod = new ReflectMethod((Class<?>) null, "clearCacheForSingleFile", (Class<?>[]) new Class[0]);
        this.hasEnteredFullscreenMethod = new ReflectMethod((Class<?>) null, "hasEnteredFullscreen", (Class<?>[]) new Class[0]);
        this.leaveFullscreenMethod = new ReflectMethod((Class<?>) null, "leaveFullscreen", (Class<?>[]) new Class[0]);
        this.pauseTimersMethod = new ReflectMethod((Class<?>) null, "pauseTimers", (Class<?>[]) new Class[0]);
        this.resumeTimersMethod = new ReflectMethod((Class<?>) null, "resumeTimers", (Class<?>[]) new Class[0]);
        this.onHideMethod = new ReflectMethod((Class<?>) null, "onHide", (Class<?>[]) new Class[0]);
        this.onShowMethod = new ReflectMethod((Class<?>) null, "onShow", (Class<?>[]) new Class[0]);
        this.onDestroyMethod = new ReflectMethod((Class<?>) null, "onDestroy", (Class<?>[]) new Class[0]);
        this.startActivityForResultIntentintBundleMethod = new ReflectMethod((Class<?>) null, "startActivityForResult", (Class<?>[]) new Class[0]);
        this.onActivityResultintintIntentMethod = new ReflectMethod((Class<?>) null, "onActivityResult", (Class<?>[]) new Class[0]);
        this.onNewIntentIntentMethod = new ReflectMethod((Class<?>) null, "onNewIntent", (Class<?>[]) new Class[0]);
        this.saveStateBundleMethod = new ReflectMethod((Class<?>) null, "saveState", (Class<?>[]) new Class[0]);
        this.restoreStateBundleMethod = new ReflectMethod((Class<?>) null, "restoreState", (Class<?>[]) new Class[0]);
        this.getAPIVersionMethod = new ReflectMethod((Class<?>) null, "getAPIVersion", (Class<?>[]) new Class[0]);
        this.getXWalkVersionMethod = new ReflectMethod((Class<?>) null, "getXWalkVersion", (Class<?>[]) new Class[0]);
        this.setUIClientXWalkUIClientInternalMethod = new ReflectMethod((Class<?>) null, "setUIClient", (Class<?>[]) new Class[0]);
        this.setResourceClientXWalkResourceClientInternalMethod = new ReflectMethod((Class<?>) null, "setResourceClient", (Class<?>[]) new Class[0]);
        this.setBackgroundColorintMethod = new ReflectMethod((Class<?>) null, "setBackgroundColor", (Class<?>[]) new Class[0]);
        this.setOriginAccessWhitelistStringStringArrayMethod = new ReflectMethod((Class<?>) null, "setOriginAccessWhitelist", (Class<?>[]) new Class[0]);
        this.setUserAgentStringStringMethod = new ReflectMethod((Class<?>) null, "setUserAgentString", (Class<?>[]) new Class[0]);
        this.getUserAgentStringMethod = new ReflectMethod((Class<?>) null, "getUserAgentString", (Class<?>[]) new Class[0]);
        this.setAcceptLanguagesStringMethod = new ReflectMethod((Class<?>) null, "setAcceptLanguages", (Class<?>[]) new Class[0]);
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod = new ReflectMethod((Class<?>) null, "captureBitmapAsync", (Class<?>[]) new Class[0]);
        this.getSettingsMethod = new ReflectMethod((Class<?>) null, "getSettings", (Class<?>[]) new Class[0]);
        this.setNetworkAvailablebooleanMethod = new ReflectMethod((Class<?>) null, "setNetworkAvailable", (Class<?>[]) new Class[0]);
        this.getRemoteDebuggingUrlMethod = new ReflectMethod((Class<?>) null, "getRemoteDebuggingUrl", (Class<?>[]) new Class[0]);
        this.zoomInMethod = new ReflectMethod((Class<?>) null, "zoomIn", (Class<?>[]) new Class[0]);
        this.zoomOutMethod = new ReflectMethod((Class<?>) null, "zoomOut", (Class<?>[]) new Class[0]);
        this.zoomByfloatMethod = new ReflectMethod((Class<?>) null, "zoomBy", (Class<?>[]) new Class[0]);
        this.canZoomInMethod = new ReflectMethod((Class<?>) null, "canZoomIn", (Class<?>[]) new Class[0]);
        this.canZoomOutMethod = new ReflectMethod((Class<?>) null, "canZoomOut", (Class<?>[]) new Class[0]);
        this.onCreateInputConnectionEditorInfoMethod = new ReflectMethod((Class<?>) null, "onCreateInputConnection", (Class<?>[]) new Class[0]);
        this.setInitialScaleintMethod = new ReflectMethod((Class<?>) null, "setInitialScale", (Class<?>[]) new Class[0]);
        this.getFaviconMethod = new ReflectMethod((Class<?>) null, "getFavicon", (Class<?>[]) new Class[0]);
        this.setZOrderOnTopbooleanMethod = new ReflectMethod((Class<?>) null, "setZOrderOnTop", (Class<?>[]) new Class[0]);
        this.clearFormDataMethod = new ReflectMethod((Class<?>) null, "clearFormData", (Class<?>[]) new Class[0]);
        this.setSurfaceViewVisibilityintMethod = new ReflectMethod((Class<?>) null, "setSurfaceViewVisibility", (Class<?>[]) new Class[0]);
        this.setXWalkViewInternalVisibilityintMethod = new ReflectMethod((Class<?>) null, "setXWalkViewInternalVisibility", (Class<?>[]) new Class[0]);
        this.setDownloadListenerXWalkDownloadListenerInternalMethod = new ReflectMethod((Class<?>) null, "setDownloadListener", (Class<?>[]) new Class[0]);
        this.onTouchEventMotionEventMethod = new ReflectMethod((Class<?>) null, "onTouchEvent", (Class<?>[]) new Class[0]);
        this.setOnTouchListenerOnTouchListenerMethod = new ReflectMethod((Class<?>) null, "setOnTouchListener", (Class<?>[]) new Class[0]);
        this.scrollTointintMethod = new ReflectMethod((Class<?>) null, "scrollTo", (Class<?>[]) new Class[0]);
        this.scrollByintintMethod = new ReflectMethod((Class<?>) null, "scrollBy", (Class<?>[]) new Class[0]);
        this.computeHorizontalScrollRangeMethod = new ReflectMethod((Class<?>) null, "computeHorizontalScrollRange", (Class<?>[]) new Class[0]);
        this.computeHorizontalScrollOffsetMethod = new ReflectMethod((Class<?>) null, "computeHorizontalScrollOffset", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollRangeMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollRange", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollOffsetMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollOffset", (Class<?>[]) new Class[0]);
        this.computeVerticalScrollExtentMethod = new ReflectMethod((Class<?>) null, "computeVerticalScrollExtent", (Class<?>[]) new Class[0]);
        this.getExtensionManagerMethod = new ReflectMethod((Class<?>) null, "getExtensionManager", (Class<?>[]) new Class[0]);
        this.clearSslPreferencesMethod = new ReflectMethod((Class<?>) null, "clearSslPreferences", (Class<?>[]) new Class[0]);
        this.clearClientCertPreferencesRunnableMethod = new ReflectMethod((Class<?>) null, "clearClientCertPreferences", (Class<?>[]) new Class[0]);
        this.getCertificateMethod = new ReflectMethod((Class<?>) null, "getCertificate", (Class<?>[]) new Class[0]);
        this.setFindListenerXWalkFindListenerInternalMethod = new ReflectMethod((Class<?>) null, "setFindListener", (Class<?>[]) new Class[0]);
        this.findAllAsyncStringMethod = new ReflectMethod((Class<?>) null, "findAllAsync", (Class<?>[]) new Class[0]);
        this.findNextbooleanMethod = new ReflectMethod((Class<?>) null, "findNext", (Class<?>[]) new Class[0]);
        this.clearMatchesMethod = new ReflectMethod((Class<?>) null, "clearMatches", (Class<?>[]) new Class[0]);
        this.getCompositingSurfaceTypeMethod = new ReflectMethod((Class<?>) null, "getCompositingSurfaceType", (Class<?>[]) new Class[0]);
        SurfaceView surfaceView = new SurfaceView(context);
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        addView(surfaceView);
        ArrayList<Object> arrayList = new ArrayList<>();
        this.constructorTypes = arrayList;
        arrayList.add(Context.class);
        this.constructorTypes.add(Activity.class);
        ArrayList<Object> arrayList2 = new ArrayList<>();
        this.constructorParams = arrayList2;
        arrayList2.add(context);
        this.constructorParams.add(activity);
        this.postWrapperMethod = new ReflectMethod(this, "postXWalkViewInternalContextActivityConstructor", (Class<?>[]) new Class[0]);
        reflectionInit();
    }

    public void postXWalkViewInternalContextActivityConstructor() {
        addView((FrameLayout) this.bridge, new FrameLayout.LayoutParams(-1, -1));
        removeViewAt(0);
        new XWalkExternalExtensionManagerImpl(this);
    }

    @Deprecated
    public void load(String str, String str2) {
        try {
            this.loadStringStringMethod.invoke(str, str2);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Deprecated
    public void load(String str, String str2, Map<String, String> map) {
        try {
            this.loadStringStringMapMethod.invoke(str, str2, map);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void loadData(String str, String str2, String str3) {
        try {
            this.loadDataStringStringStringMethod.invoke(str, str2, str3);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        try {
            this.loadDataWithBaseURLStringStringStringStringStringMethod.invoke(str, str2, str3, str4, str5);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void loadUrl(String str) {
        try {
            this.loadUrlStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void loadUrl(String str, Map<String, String> map) {
        try {
            this.loadUrlStringMapMethod.invoke(str, map);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void loadAppFromManifest(String str, String str2) {
        try {
            this.loadAppFromManifestStringStringMethod.invoke(str, str2);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void reload(int i) {
        try {
            this.reloadintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void stopLoading() {
        try {
            this.stopLoadingMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public String getUrl() {
        try {
            return (String) this.getUrlMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public XWalkHitTestResult getHitTestResult() {
        try {
            return (XWalkHitTestResult) this.coreWrapper.getWrapperObject(this.getHitTestResultMethod.invoke(new Object[0]));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public int getContentHeight() {
        try {
            return ((Integer) this.getContentHeightMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public String getTitle() {
        try {
            return (String) this.getTitleMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public String getOriginalUrl() {
        try {
            return (String) this.getOriginalUrlMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public XWalkNavigationHistory getNavigationHistory() {
        try {
            return (XWalkNavigationHistory) this.coreWrapper.getWrapperObject(this.getNavigationHistoryMethod.invoke(new Object[0]));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void addJavascriptInterface(Object obj, String str) {
        try {
            this.addJavascriptInterfaceObjectStringMethod.invoke(obj, str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.addJavascriptInterfaceObjectStringMethod.setArguments(obj, str);
                XWalkCoreWrapper.reserveReflectMethod(this.addJavascriptInterfaceObjectStringMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    public void removeJavascriptInterface(String str) {
        try {
            this.removeJavascriptInterfaceStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.removeJavascriptInterfaceStringMethod.setArguments(str);
                XWalkCoreWrapper.reserveReflectMethod(this.removeJavascriptInterfaceStringMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        try {
            this.evaluateJavascriptStringValueCallbackMethod.invoke(str, valueCallback);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void clearCache(boolean z) {
        try {
            this.clearCachebooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void clearCacheForSingleFile(String str) {
        try {
            this.clearCacheForSingleFileStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean hasEnteredFullscreen() {
        try {
            return ((Boolean) this.hasEnteredFullscreenMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void leaveFullscreen() {
        try {
            this.leaveFullscreenMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void pauseTimers() {
        try {
            this.pauseTimersMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void resumeTimers() {
        try {
            this.resumeTimersMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onHide() {
        try {
            this.onHideMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onShow() {
        try {
            this.onShowMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onDestroy() {
        try {
            this.onDestroyMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        try {
            this.startActivityForResultIntentintBundleMethod.invoke(intent, Integer.valueOf(i), bundle);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Deprecated
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.onActivityResultintintIntentMethod.invoke(Integer.valueOf(i), Integer.valueOf(i2), intent);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean onNewIntent(Intent intent) {
        try {
            return ((Boolean) this.onNewIntentIntentMethod.invoke(intent)).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean saveState(Bundle bundle) {
        try {
            return ((Boolean) this.saveStateBundleMethod.invoke(bundle)).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean restoreState(Bundle bundle) {
        try {
            return ((Boolean) this.restoreStateBundleMethod.invoke(bundle)).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public String getAPIVersion() {
        try {
            return (String) this.getAPIVersionMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public String getXWalkVersion() {
        try {
            return (String) this.getXWalkVersionMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setUIClient(XWalkUIClient xWalkUIClient) {
        try {
            this.setUIClientXWalkUIClientInternalMethod.invoke(xWalkUIClient.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.setUIClientXWalkUIClientInternalMethod.setArguments(new ReflectMethod(xWalkUIClient, "getBridge", (Class<?>[]) new Class[0]));
                XWalkCoreWrapper.reserveReflectMethod(this.setUIClientXWalkUIClientInternalMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    public void setResourceClient(XWalkResourceClient xWalkResourceClient) {
        try {
            this.setResourceClientXWalkResourceClientInternalMethod.invoke(xWalkResourceClient.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.setResourceClientXWalkResourceClientInternalMethod.setArguments(new ReflectMethod(xWalkResourceClient, "getBridge", (Class<?>[]) new Class[0]));
                XWalkCoreWrapper.reserveReflectMethod(this.setResourceClientXWalkResourceClientInternalMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        try {
            this.setBackgroundColorintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void setOriginAccessWhitelist(String str, String[] strArr) {
        try {
            this.setOriginAccessWhitelistStringStringArrayMethod.invoke(str, strArr);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void setUserAgentString(String str) {
        try {
            this.setUserAgentStringStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public String getUserAgentString() {
        try {
            return (String) this.getUserAgentStringMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setAcceptLanguages(String str) {
        try {
            this.setAcceptLanguagesStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void captureBitmapAsync(XWalkGetBitmapCallback xWalkGetBitmapCallback) {
        try {
            this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod.invoke(xWalkGetBitmapCallback.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public XWalkSettings getSettings() {
        try {
            return (XWalkSettings) this.coreWrapper.getWrapperObject(this.getSettingsMethod.invoke(new Object[0]));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setNetworkAvailable(boolean z) {
        try {
            this.setNetworkAvailablebooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public Uri getRemoteDebuggingUrl() {
        try {
            return (Uri) this.getRemoteDebuggingUrlMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public boolean zoomIn() {
        try {
            return ((Boolean) this.zoomInMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean zoomOut() {
        try {
            return ((Boolean) this.zoomOutMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void zoomBy(float f) {
        try {
            this.zoomByfloatMethod.invoke(Float.valueOf(f));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean canZoomIn() {
        try {
            return ((Boolean) this.canZoomInMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean canZoomOut() {
        try {
            return ((Boolean) this.canZoomOutMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        try {
            return (InputConnection) this.onCreateInputConnectionEditorInfoMethod.invoke(editorInfo);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setInitialScale(int i) {
        try {
            this.setInitialScaleintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public Bitmap getFavicon() {
        try {
            return (Bitmap) this.getFaviconMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setZOrderOnTop(boolean z) {
        try {
            this.setZOrderOnTopbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void clearFormData() {
        try {
            this.clearFormDataMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i == 4) {
            i = 8;
        }
        super.setVisibility(i);
        setXWalkViewInternalVisibility(i);
        setSurfaceViewVisibility(i);
    }

    public void setSurfaceViewVisibility(int i) {
        try {
            this.setSurfaceViewVisibilityintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.setSurfaceViewVisibilityintMethod.setArguments(Integer.valueOf(i));
                XWalkCoreWrapper.reserveReflectMethod(this.setSurfaceViewVisibilityintMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    public void setXWalkViewInternalVisibility(int i) {
        try {
            this.setXWalkViewInternalVisibilityintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.setXWalkViewInternalVisibilityintMethod.setArguments(Integer.valueOf(i));
                XWalkCoreWrapper.reserveReflectMethod(this.setXWalkViewInternalVisibilityintMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    public void setDownloadListener(XWalkDownloadListener xWalkDownloadListener) {
        try {
            this.setDownloadListenerXWalkDownloadListenerInternalMethod.invoke(xWalkDownloadListener.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.setDownloadListenerXWalkDownloadListenerInternalMethod.setArguments(new ReflectMethod(xWalkDownloadListener, "getBridge", (Class<?>[]) new Class[0]));
                XWalkCoreWrapper.reserveReflectMethod(this.setDownloadListenerXWalkDownloadListenerInternalMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    private boolean performLongClickDelegate() {
        return performLongClick();
    }

    private boolean onTouchEventDelegate(MotionEvent motionEvent) {
        return onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return ((Boolean) this.onTouchEventMotionEventMethod.invoke(motionEvent)).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    private void onScrollChangedDelegate(int i, int i2, int i3, int i4) {
        onScrollChanged(i, i2, i3, i4);
    }

    private void onFocusChangedDelegate(boolean z, int i, Rect rect) {
        onFocusChanged(z, i, rect);
    }

    private void onOverScrolledDelegate(int i, int i2, boolean z, boolean z2) {
        onOverScrolled(i, i2, z, z2);
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        try {
            this.setOnTouchListenerOnTouchListenerMethod.invoke(onTouchListener);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        try {
            this.scrollTointintMethod.invoke(Integer.valueOf(i), Integer.valueOf(i2));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        try {
            this.scrollByintintMethod.invoke(Integer.valueOf(i), Integer.valueOf(i2));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        try {
            return ((Integer) this.computeHorizontalScrollRangeMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        try {
            return ((Integer) this.computeHorizontalScrollOffsetMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        try {
            return ((Integer) this.computeVerticalScrollRangeMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        try {
            return ((Integer) this.computeVerticalScrollOffsetMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        try {
            return ((Integer) this.computeVerticalScrollExtentMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public XWalkExternalExtensionManager getExtensionManager() {
        try {
            return (XWalkExternalExtensionManager) this.coreWrapper.getWrapperObject(this.getExtensionManagerMethod.invoke(new Object[0]));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void clearSslPreferences() {
        try {
            this.clearSslPreferencesMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void clearClientCertPreferences(Runnable runnable) {
        try {
            this.clearClientCertPreferencesRunnableMethod.invoke(runnable);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public SslCertificate getCertificate() {
        try {
            return (SslCertificate) this.getCertificateMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setFindListener(XWalkFindListener xWalkFindListener) {
        try {
            this.setFindListenerXWalkFindListenerInternalMethod.invoke(xWalkFindListener.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                this.setFindListenerXWalkFindListenerInternalMethod.setArguments(new ReflectMethod(xWalkFindListener, "getBridge", (Class<?>[]) new Class[0]));
                XWalkCoreWrapper.reserveReflectMethod(this.setFindListenerXWalkFindListenerInternalMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    public void findAllAsync(String str) {
        try {
            this.findAllAsyncStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void findNext(boolean z) {
        try {
            this.findNextbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void clearMatches() {
        try {
            this.clearMatchesMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public String getCompositingSurfaceType() {
        try {
            return (String) this.getCompositingSurfaceTypeMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    void reflectionInit() {
        XWalkCoreWrapper.initEmbeddedMode();
        XWalkCoreWrapper xWalkCoreWrapper = XWalkCoreWrapper.getInstance();
        this.coreWrapper = xWalkCoreWrapper;
        if (xWalkCoreWrapper == null) {
            XWalkCoreWrapper.reserveReflectObject(this);
            return;
        }
        int size = this.constructorTypes.size();
        Class[] clsArr = new Class[size + 1];
        for (int i = 0; i < size; i++) {
            Object obj = this.constructorTypes.get(i);
            if (obj instanceof String) {
                clsArr[i] = this.coreWrapper.getBridgeClass((String) obj);
                ArrayList<Object> arrayList = this.constructorParams;
                arrayList.set(i, this.coreWrapper.getBridgeObject(arrayList.get(i)));
            } else if (obj instanceof Class) {
                clsArr[i] = (Class) obj;
            }
        }
        clsArr[size] = Object.class;
        this.constructorParams.add(this);
        try {
            this.bridge = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkViewBridge"), clsArr).newInstance(this.constructorParams.toArray());
            ReflectMethod reflectMethod = this.postWrapperMethod;
            if (reflectMethod != null) {
                reflectMethod.invoke(new Object[0]);
            }
            this.loadStringStringMethod.init(this.bridge, null, "loadSuper", String.class, String.class);
            this.loadStringStringMapMethod.init(this.bridge, null, "loadSuper", String.class, String.class, Map.class);
            this.loadDataStringStringStringMethod.init(this.bridge, null, "loadDataSuper", String.class, String.class, String.class);
            this.loadDataWithBaseURLStringStringStringStringStringMethod.init(this.bridge, null, "loadDataWithBaseURLSuper", String.class, String.class, String.class, String.class, String.class);
            this.loadUrlStringMethod.init(this.bridge, null, "loadUrlSuper", String.class);
            this.loadUrlStringMapMethod.init(this.bridge, null, "loadUrlSuper", String.class, Map.class);
            this.loadAppFromManifestStringStringMethod.init(this.bridge, null, "loadAppFromManifestSuper", String.class, String.class);
            this.reloadintMethod.init(this.bridge, null, "reloadSuper", Integer.TYPE);
            this.stopLoadingMethod.init(this.bridge, null, "stopLoadingSuper", new Class[0]);
            this.getUrlMethod.init(this.bridge, null, "getUrlSuper", new Class[0]);
            this.getHitTestResultMethod.init(this.bridge, null, "getHitTestResultSuper", new Class[0]);
            this.getContentHeightMethod.init(this.bridge, null, "getContentHeightSuper", new Class[0]);
            this.getTitleMethod.init(this.bridge, null, "getTitleSuper", new Class[0]);
            this.getOriginalUrlMethod.init(this.bridge, null, "getOriginalUrlSuper", new Class[0]);
            this.getNavigationHistoryMethod.init(this.bridge, null, "getNavigationHistorySuper", new Class[0]);
            this.addJavascriptInterfaceObjectStringMethod.init(this.bridge, null, "addJavascriptInterfaceSuper", Object.class, String.class);
            this.removeJavascriptInterfaceStringMethod.init(this.bridge, null, "removeJavascriptInterfaceSuper", String.class);
            this.evaluateJavascriptStringValueCallbackMethod.init(this.bridge, null, "evaluateJavascriptSuper", String.class, ValueCallback.class);
            this.clearCachebooleanMethod.init(this.bridge, null, "clearCacheSuper", Boolean.TYPE);
            this.clearCacheForSingleFileStringMethod.init(this.bridge, null, "clearCacheForSingleFileSuper", String.class);
            this.hasEnteredFullscreenMethod.init(this.bridge, null, "hasEnteredFullscreenSuper", new Class[0]);
            this.leaveFullscreenMethod.init(this.bridge, null, "leaveFullscreenSuper", new Class[0]);
            this.pauseTimersMethod.init(this.bridge, null, "pauseTimersSuper", new Class[0]);
            this.resumeTimersMethod.init(this.bridge, null, "resumeTimersSuper", new Class[0]);
            this.onHideMethod.init(this.bridge, null, "onHideSuper", new Class[0]);
            this.onShowMethod.init(this.bridge, null, "onShowSuper", new Class[0]);
            this.onDestroyMethod.init(this.bridge, null, "onDestroySuper", new Class[0]);
            this.startActivityForResultIntentintBundleMethod.init(this.bridge, null, "startActivityForResultSuper", Intent.class, Integer.TYPE, Bundle.class);
            this.onActivityResultintintIntentMethod.init(this.bridge, null, "onActivityResultSuper", Integer.TYPE, Integer.TYPE, Intent.class);
            this.onNewIntentIntentMethod.init(this.bridge, null, "onNewIntentSuper", Intent.class);
            this.saveStateBundleMethod.init(this.bridge, null, "saveStateSuper", Bundle.class);
            this.restoreStateBundleMethod.init(this.bridge, null, "restoreStateSuper", Bundle.class);
            this.getAPIVersionMethod.init(this.bridge, null, "getAPIVersionSuper", new Class[0]);
            this.getXWalkVersionMethod.init(this.bridge, null, "getXWalkVersionSuper", new Class[0]);
            this.setUIClientXWalkUIClientInternalMethod.init(this.bridge, null, "setUIClientSuper", this.coreWrapper.getBridgeClass("XWalkUIClientBridge"));
            this.setResourceClientXWalkResourceClientInternalMethod.init(this.bridge, null, "setResourceClientSuper", this.coreWrapper.getBridgeClass("XWalkResourceClientBridge"));
            this.setBackgroundColorintMethod.init(this.bridge, null, "setBackgroundColorSuper", Integer.TYPE);
            this.setOriginAccessWhitelistStringStringArrayMethod.init(this.bridge, null, "setOriginAccessWhitelistSuper", String.class, String[].class);
            this.setUserAgentStringStringMethod.init(this.bridge, null, "setUserAgentStringSuper", String.class);
            this.getUserAgentStringMethod.init(this.bridge, null, "getUserAgentStringSuper", new Class[0]);
            this.setAcceptLanguagesStringMethod.init(this.bridge, null, "setAcceptLanguagesSuper", String.class);
            this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod.init(this.bridge, null, "captureBitmapAsyncSuper", this.coreWrapper.getBridgeClass("XWalkGetBitmapCallbackBridge"));
            this.getSettingsMethod.init(this.bridge, null, "getSettingsSuper", new Class[0]);
            this.setNetworkAvailablebooleanMethod.init(this.bridge, null, "setNetworkAvailableSuper", Boolean.TYPE);
            this.getRemoteDebuggingUrlMethod.init(this.bridge, null, "getRemoteDebuggingUrlSuper", new Class[0]);
            this.zoomInMethod.init(this.bridge, null, "zoomInSuper", new Class[0]);
            this.zoomOutMethod.init(this.bridge, null, "zoomOutSuper", new Class[0]);
            this.zoomByfloatMethod.init(this.bridge, null, "zoomBySuper", Float.TYPE);
            this.canZoomInMethod.init(this.bridge, null, "canZoomInSuper", new Class[0]);
            this.canZoomOutMethod.init(this.bridge, null, "canZoomOutSuper", new Class[0]);
            this.onCreateInputConnectionEditorInfoMethod.init(this.bridge, null, "onCreateInputConnectionSuper", EditorInfo.class);
            this.setInitialScaleintMethod.init(this.bridge, null, "setInitialScaleSuper", Integer.TYPE);
            this.getFaviconMethod.init(this.bridge, null, "getFaviconSuper", new Class[0]);
            this.setZOrderOnTopbooleanMethod.init(this.bridge, null, "setZOrderOnTopSuper", Boolean.TYPE);
            this.clearFormDataMethod.init(this.bridge, null, "clearFormDataSuper", new Class[0]);
            this.setSurfaceViewVisibilityintMethod.init(this.bridge, null, "setSurfaceViewVisibilitySuper", Integer.TYPE);
            this.setXWalkViewInternalVisibilityintMethod.init(this.bridge, null, "setXWalkViewInternalVisibilitySuper", Integer.TYPE);
            this.setDownloadListenerXWalkDownloadListenerInternalMethod.init(this.bridge, null, "setDownloadListenerSuper", this.coreWrapper.getBridgeClass("XWalkDownloadListenerBridge"));
            this.onTouchEventMotionEventMethod.init(this.bridge, null, "onTouchEventSuper", MotionEvent.class);
            this.setOnTouchListenerOnTouchListenerMethod.init(this.bridge, null, "setOnTouchListenerSuper", View.OnTouchListener.class);
            this.scrollTointintMethod.init(this.bridge, null, "scrollToSuper", Integer.TYPE, Integer.TYPE);
            this.scrollByintintMethod.init(this.bridge, null, "scrollBySuper", Integer.TYPE, Integer.TYPE);
            this.computeHorizontalScrollRangeMethod.init(this.bridge, null, "computeHorizontalScrollRangeSuper", new Class[0]);
            this.computeHorizontalScrollOffsetMethod.init(this.bridge, null, "computeHorizontalScrollOffsetSuper", new Class[0]);
            this.computeVerticalScrollRangeMethod.init(this.bridge, null, "computeVerticalScrollRangeSuper", new Class[0]);
            this.computeVerticalScrollOffsetMethod.init(this.bridge, null, "computeVerticalScrollOffsetSuper", new Class[0]);
            this.computeVerticalScrollExtentMethod.init(this.bridge, null, "computeVerticalScrollExtentSuper", new Class[0]);
            this.getExtensionManagerMethod.init(this.bridge, null, "getExtensionManagerSuper", new Class[0]);
            this.clearSslPreferencesMethod.init(this.bridge, null, "clearSslPreferencesSuper", new Class[0]);
            this.clearClientCertPreferencesRunnableMethod.init(this.bridge, null, "clearClientCertPreferencesSuper", Runnable.class);
            this.getCertificateMethod.init(this.bridge, null, "getCertificateSuper", new Class[0]);
            this.setFindListenerXWalkFindListenerInternalMethod.init(this.bridge, null, "setFindListenerSuper", this.coreWrapper.getBridgeClass("XWalkFindListenerBridge"));
            this.findAllAsyncStringMethod.init(this.bridge, null, "findAllAsyncSuper", String.class);
            this.findNextbooleanMethod.init(this.bridge, null, "findNextSuper", Boolean.TYPE);
            this.clearMatchesMethod.init(this.bridge, null, "clearMatchesSuper", new Class[0]);
            this.getCompositingSurfaceTypeMethod.init(this.bridge, null, "getCompositingSurfaceTypeSuper", new Class[0]);
        } catch (UnsupportedOperationException unused) {
        }
    }
}
