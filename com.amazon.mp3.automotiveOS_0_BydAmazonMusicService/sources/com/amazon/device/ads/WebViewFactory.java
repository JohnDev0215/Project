package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class WebViewFactory {
    private static WebViewFactory instance = new WebViewFactory();
    private final MobileAdsCookieManager cookieManager;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLoggerFactory loggerFactory;
    private final WebViewConstructor webViewConstructor;
    private boolean webViewDebugging;

    protected WebViewFactory() {
        this(MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance(), new MobileAdsCookieManager(), new WebViewConstructor());
    }

    WebViewFactory(MobileAdsInfoStore mobileAdsInfoStore, MobileAdsLoggerFactory mobileAdsLoggerFactory, DebugProperties debugProperties, MobileAdsCookieManager mobileAdsCookieManager, WebViewConstructor webViewConstructor) {
        this.webViewDebugging = false;
        this.infoStore = mobileAdsInfoStore;
        this.loggerFactory = mobileAdsLoggerFactory;
        this.debugProperties = debugProperties;
        this.cookieManager = mobileAdsCookieManager;
        this.webViewConstructor = webViewConstructor;
    }

    public static final WebViewFactory getInstance() {
        return instance;
    }

    static class MobileAdsCookieManager {
        private boolean cookieSyncManagerCreated = false;

        MobileAdsCookieManager() {
        }
    }

    static class WebViewConstructor {
        WebViewConstructor() {
        }
    }
}
