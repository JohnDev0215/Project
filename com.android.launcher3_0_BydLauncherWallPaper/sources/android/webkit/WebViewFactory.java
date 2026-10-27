package android.webkit;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.app.AppGlobals;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.Trace;
import android.util.AndroidRuntimeException;
import android.util.ArraySet;
import android.util.Log;
import android.webkit.IWebViewUpdateService;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class WebViewFactory {
    private static final String CHROMIUM_WEBVIEW_FACTORY = "com.android.webview.chromium.WebViewChromiumFactoryProviderForR";
    private static final String CHROMIUM_WEBVIEW_FACTORY_METHOD = "create";
    private static final boolean DEBUG = false;
    public static final int LIBLOAD_ADDRESS_SPACE_NOT_RESERVED = 2;
    public static final int LIBLOAD_FAILED_JNI_CALL = 7;
    public static final int LIBLOAD_FAILED_LISTING_WEBVIEW_PACKAGES = 4;
    public static final int LIBLOAD_FAILED_TO_FIND_NAMESPACE = 10;
    public static final int LIBLOAD_FAILED_TO_LOAD_LIBRARY = 6;
    public static final int LIBLOAD_FAILED_TO_OPEN_RELRO_FILE = 5;
    public static final int LIBLOAD_FAILED_WAITING_FOR_RELRO = 3;
    public static final int LIBLOAD_FAILED_WAITING_FOR_WEBVIEW_REASON_UNKNOWN = 8;
    public static final int LIBLOAD_SUCCESS = 0;
    public static final int LIBLOAD_WRONG_PACKAGE_NAME = 1;
    private static final String LOGTAG = "WebViewFactory";
    private static String WEBVIEW_UPDATE_SERVICE_NAME = "webviewupdate";
    private static String sDataDirectorySuffix;
    private static PackageInfo sPackageInfo;
    private static WebViewFactoryProvider sProviderInstance;
    private static final Object sProviderLock = new Object();
    private static boolean sWebViewDisabled;
    private static Boolean sWebViewSupported;

    private static String getWebViewPreparationErrorReason(int i) {
        return i != 3 ? i != 4 ? i != 8 ? "Unknown" : "Crashed for unknown reason" : "No WebView installed" : "Time out waiting for Relro files being created";
    }

    static class MissingWebViewPackageException extends Exception {
        public MissingWebViewPackageException(String str) {
            super(str);
        }

        public MissingWebViewPackageException(Exception exc) {
            super(exc);
        }
    }

    private static boolean isWebViewSupported() {
        if (sWebViewSupported == null) {
            sWebViewSupported = Boolean.valueOf(AppGlobals.getInitialApplication().getPackageManager().hasSystemFeature(PackageManager.FEATURE_WEBVIEW));
        }
        return sWebViewSupported.booleanValue();
    }

    static void disableWebView() {
        synchronized (sProviderLock) {
            if (sProviderInstance != null) {
                throw new IllegalStateException("Can't disable WebView: WebView already initialized");
            }
            sWebViewDisabled = true;
        }
    }

    static void setDataDirectorySuffix(String str) {
        synchronized (sProviderLock) {
            if (sProviderInstance != null) {
                throw new IllegalStateException("Can't set data directory suffix: WebView already initialized");
            }
            if (str.indexOf(File.separatorChar) >= 0) {
                throw new IllegalArgumentException("Suffix " + str + " contains a path separator");
            }
            sDataDirectorySuffix = str;
        }
    }

    static String getDataDirectorySuffix() {
        String str;
        synchronized (sProviderLock) {
            str = sDataDirectorySuffix;
        }
        return str;
    }

    public static String getWebViewLibrary(ApplicationInfo applicationInfo) {
        if (applicationInfo.metaData != null) {
            return applicationInfo.metaData.getString("com.android.webview.WebViewLibrary");
        }
        return null;
    }

    public static PackageInfo getLoadedPackageInfo() {
        PackageInfo packageInfo;
        synchronized (sProviderLock) {
            packageInfo = sPackageInfo;
        }
        return packageInfo;
    }

    public static Class<WebViewFactoryProvider> getWebViewProviderClass(ClassLoader classLoader) throws ClassNotFoundException {
        return Class.forName(CHROMIUM_WEBVIEW_FACTORY, true, classLoader);
    }

    public static int loadWebViewNativeLibraryFromPackage(String str, ClassLoader classLoader) {
        if (!isWebViewSupported()) {
            return 1;
        }
        try {
            WebViewProviderResponse webViewProviderResponseWaitForAndGetProvider = getUpdateService().waitForAndGetProvider();
            if (webViewProviderResponseWaitForAndGetProvider.status != 0 && webViewProviderResponseWaitForAndGetProvider.status != 3) {
                return webViewProviderResponseWaitForAndGetProvider.status;
            }
            if (!webViewProviderResponseWaitForAndGetProvider.packageInfo.packageName.equals(str)) {
                return 1;
            }
            try {
                int iLoadNativeLibrary = WebViewLibraryLoader.loadNativeLibrary(classLoader, getWebViewLibrary(AppGlobals.getInitialApplication().getPackageManager().getPackageInfo(str, 268435584).applicationInfo));
                return iLoadNativeLibrary == 0 ? webViewProviderResponseWaitForAndGetProvider.status : iLoadNativeLibrary;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e(LOGTAG, "Couldn't find package " + str);
                return 1;
            }
        } catch (RemoteException e) {
            Log.e(LOGTAG, "error waiting for relro creation", e);
            return 8;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0077, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007b, code lost:
    
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static android.webkit.WebViewFactoryProvider getProvider() {
        /*
            java.lang.Object r0 = android.webkit.WebViewFactory.sProviderLock
            monitor-enter(r0)
            android.webkit.WebViewFactoryProvider r1 = android.webkit.WebViewFactory.sProviderInstance     // Catch: java.lang.Throwable -> L92
            if (r1 == 0) goto L9
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L92
            return r1
        L9:
            int r1 = android.os.Process.myUid()     // Catch: java.lang.Throwable -> L92
            if (r1 == 0) goto L8a
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 == r2) goto L8a
            r2 = 1001(0x3e9, float:1.403E-42)
            if (r1 == r2) goto L8a
            r2 = 1027(0x403, float:1.439E-42)
            if (r1 == r2) goto L8a
            r2 = 1002(0x3ea, float:1.404E-42)
            if (r1 == r2) goto L8a
            boolean r1 = isWebViewSupported()     // Catch: java.lang.Throwable -> L92
            if (r1 == 0) goto L84
            boolean r1 = android.webkit.WebViewFactory.sWebViewDisabled     // Catch: java.lang.Throwable -> L92
            if (r1 != 0) goto L7c
            java.lang.String r1 = "WebViewFactory.getProvider()"
            r2 = 16
            android.os.Trace.traceBegin(r2, r1)     // Catch: java.lang.Throwable -> L92
            java.lang.Class r1 = getProviderClass()     // Catch: java.lang.Throwable -> L77
            r4 = 0
            r5 = 1
            r6 = 0
            java.lang.String r7 = "create"
            java.lang.Class[] r8 = new java.lang.Class[r5]     // Catch: java.lang.Exception -> L44 java.lang.Throwable -> L77
            java.lang.Class<android.webkit.WebViewDelegate> r9 = android.webkit.WebViewDelegate.class
            r8[r4] = r9     // Catch: java.lang.Exception -> L44 java.lang.Throwable -> L77
            java.lang.reflect.Method r1 = r1.getMethod(r7, r8)     // Catch: java.lang.Exception -> L44 java.lang.Throwable -> L77
            goto L45
        L44:
            r1 = r6
        L45:
            java.lang.String r7 = "WebViewFactoryProvider invocation"
            android.os.Trace.traceBegin(r2, r7)     // Catch: java.lang.Throwable -> L77
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            android.webkit.WebViewDelegate r7 = new android.webkit.WebViewDelegate     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            r7.<init>()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            r5[r4] = r7     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            java.lang.Object r1 = r1.invoke(r6, r5)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            android.webkit.WebViewFactoryProvider r1 = (android.webkit.WebViewFactoryProvider) r1     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            android.webkit.WebViewFactory.sProviderInstance = r1     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            android.os.Trace.traceEnd(r2)     // Catch: java.lang.Throwable -> L77
            android.os.Trace.traceEnd(r2)     // Catch: java.lang.Throwable -> L92
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L92
            return r1
        L63:
            r1 = move-exception
            goto L73
        L65:
            r1 = move-exception
            java.lang.String r4 = "WebViewFactory"
            java.lang.String r5 = "error instantiating provider"
            android.util.Log.e(r4, r5, r1)     // Catch: java.lang.Throwable -> L63
            android.util.AndroidRuntimeException r4 = new android.util.AndroidRuntimeException     // Catch: java.lang.Throwable -> L63
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L63
            throw r4     // Catch: java.lang.Throwable -> L63
        L73:
            android.os.Trace.traceEnd(r2)     // Catch: java.lang.Throwable -> L77
            throw r1     // Catch: java.lang.Throwable -> L77
        L77:
            r1 = move-exception
            android.os.Trace.traceEnd(r2)     // Catch: java.lang.Throwable -> L92
            throw r1     // Catch: java.lang.Throwable -> L92
        L7c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L92
            java.lang.String r2 = "WebView.disableWebView() was called: WebView is disabled"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L92
            throw r1     // Catch: java.lang.Throwable -> L92
        L84:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException     // Catch: java.lang.Throwable -> L92
            r1.<init>()     // Catch: java.lang.Throwable -> L92
            throw r1     // Catch: java.lang.Throwable -> L92
        L8a:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException     // Catch: java.lang.Throwable -> L92
            java.lang.String r2 = "For security reasons, WebView is not allowed in privileged processes"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L92
            throw r1     // Catch: java.lang.Throwable -> L92
        L92:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L92
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.webkit.WebViewFactory.getProvider():android.webkit.WebViewFactoryProvider");
    }

    private static boolean signaturesEquals(Signature[] signatureArr, Signature[] signatureArr2) {
        if (signatureArr == null) {
            return signatureArr2 == null;
        }
        if (signatureArr2 == null) {
            return false;
        }
        ArraySet arraySet = new ArraySet();
        for (Signature signature : signatureArr) {
            arraySet.add(signature);
        }
        ArraySet arraySet2 = new ArraySet();
        for (Signature signature2 : signatureArr2) {
            arraySet2.add(signature2);
        }
        return arraySet.equals(arraySet2);
    }

    private static void verifyPackageInfo(PackageInfo packageInfo, PackageInfo packageInfo2) throws MissingWebViewPackageException {
        if (!packageInfo.packageName.equals(packageInfo2.packageName)) {
            throw new MissingWebViewPackageException("Failed to verify WebView provider, packageName mismatch, expected: " + packageInfo.packageName + " actual: " + packageInfo2.packageName);
        }
        if (packageInfo.getLongVersionCode() > packageInfo2.getLongVersionCode()) {
            throw new MissingWebViewPackageException("Failed to verify WebView provider, version code is lower than expected: " + packageInfo.getLongVersionCode() + " actual: " + packageInfo2.getLongVersionCode());
        }
        if (getWebViewLibrary(packageInfo2.applicationInfo) == null) {
            throw new MissingWebViewPackageException("Tried to load an invalid WebView provider: " + packageInfo2.packageName);
        }
        if (!signaturesEquals(packageInfo.signatures, packageInfo2.signatures)) {
            throw new MissingWebViewPackageException("Failed to verify WebView provider, signature mismatch");
        }
    }

    private static Context getWebViewContextAndSetProvider() throws MissingWebViewPackageException {
        Application initialApplication = AppGlobals.getInitialApplication();
        try {
            Trace.traceBegin(16L, "WebViewUpdateService.waitForAndGetProvider()");
            try {
                WebViewProviderResponse webViewProviderResponseWaitForAndGetProvider = getUpdateService().waitForAndGetProvider();
                Trace.traceEnd(16L);
                if (webViewProviderResponseWaitForAndGetProvider.status != 0 && webViewProviderResponseWaitForAndGetProvider.status != 3) {
                    throw new MissingWebViewPackageException("Failed to load WebView provider: " + getWebViewPreparationErrorReason(webViewProviderResponseWaitForAndGetProvider.status));
                }
                Trace.traceBegin(16L, "ActivityManager.addPackageDependency()");
                try {
                    ActivityManager.getService().addPackageDependency(webViewProviderResponseWaitForAndGetProvider.packageInfo.packageName);
                    Trace.traceEnd(16L);
                    PackageManager packageManager = initialApplication.getPackageManager();
                    Trace.traceBegin(16L, "PackageManager.getPackageInfo()");
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(webViewProviderResponseWaitForAndGetProvider.packageInfo.packageName, 268444864);
                        Trace.traceEnd(16L);
                        verifyPackageInfo(webViewProviderResponseWaitForAndGetProvider.packageInfo, packageInfo);
                        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                        Trace.traceBegin(16L, "initialApplication.createApplicationContext");
                        try {
                            Context contextCreateApplicationContext = initialApplication.createApplicationContext(applicationInfo, 3);
                            sPackageInfo = packageInfo;
                            return contextCreateApplicationContext;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (PackageManager.NameNotFoundException | RemoteException e) {
            throw new MissingWebViewPackageException("Failed to load WebView provider: " + e);
        }
    }

    private static Class<WebViewFactoryProvider> getProviderClass() {
        Application initialApplication = AppGlobals.getInitialApplication();
        try {
            Trace.traceBegin(16L, "WebViewFactory.getWebViewContextAndSetProvider()");
            try {
                Context webViewContextAndSetProvider = getWebViewContextAndSetProvider();
                Trace.traceEnd(16L);
                Log.i(LOGTAG, "Loading " + sPackageInfo.packageName + " version " + sPackageInfo.versionName + " (code " + sPackageInfo.getLongVersionCode() + ")");
                Trace.traceBegin(16L, "WebViewFactory.getChromiumProviderClass()");
                try {
                    try {
                        for (String str : webViewContextAndSetProvider.getApplicationInfo().getAllApkPaths()) {
                            initialApplication.getAssets().addAssetPathAsSharedLibrary(str);
                        }
                        ClassLoader classLoader = webViewContextAndSetProvider.getClassLoader();
                        Trace.traceBegin(16L, "WebViewFactory.loadNativeLibrary()");
                        WebViewLibraryLoader.loadNativeLibrary(classLoader, getWebViewLibrary(sPackageInfo.applicationInfo));
                        Trace.traceEnd(16L);
                        Trace.traceBegin(16L, "Class.forName()");
                        try {
                            Class<WebViewFactoryProvider> webViewProviderClass = getWebViewProviderClass(classLoader);
                            Trace.traceEnd(16L);
                            return webViewProviderClass;
                        } finally {
                        }
                    } catch (ClassNotFoundException e) {
                        Log.e(LOGTAG, "error loading provider", e);
                        throw new AndroidRuntimeException(e);
                    }
                } finally {
                }
            } finally {
            }
        } catch (MissingWebViewPackageException e2) {
            Log.e(LOGTAG, "Chromium WebView package does not exist", e2);
            throw new AndroidRuntimeException(e2);
        }
    }

    public static void prepareWebViewInZygote() {
        try {
            WebViewLibraryLoader.reserveAddressSpaceInZygote();
        } catch (Throwable th) {
            Log.e(LOGTAG, "error preparing native loader", th);
        }
    }

    public static int onWebViewProviderChanged(PackageInfo packageInfo) {
        int iPrepareNativeLibraries;
        try {
            iPrepareNativeLibraries = WebViewLibraryLoader.prepareNativeLibraries(packageInfo);
        } catch (Throwable th) {
            Log.e(LOGTAG, "error preparing webview native library", th);
            iPrepareNativeLibraries = 0;
        }
        WebViewZygote.onWebViewProviderChanged(packageInfo);
        return iPrepareNativeLibraries;
    }

    public static IWebViewUpdateService getUpdateService() {
        if (isWebViewSupported()) {
            return getUpdateServiceUnchecked();
        }
        return null;
    }

    static IWebViewUpdateService getUpdateServiceUnchecked() {
        return IWebViewUpdateService.Stub.asInterface(ServiceManager.getService(WEBVIEW_UPDATE_SERVICE_NAME));
    }
}
