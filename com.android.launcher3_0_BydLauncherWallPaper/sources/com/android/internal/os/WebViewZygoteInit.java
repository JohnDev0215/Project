package com.android.internal.os;

import android.app.ApplicationLoaders;
import android.app.LoadedApk;
import android.content.pm.ApplicationInfo;
import android.net.LocalSocket;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebViewFactory;
import android.webkit.WebViewFactoryProvider;
import android.webkit.WebViewLibraryLoader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
class WebViewZygoteInit {
    public static final String TAG = "WebViewZygoteInit";

    WebViewZygoteInit() {
    }

    private static class WebViewZygoteServer extends ZygoteServer {
        private WebViewZygoteServer() {
        }

        @Override // com.android.internal.os.ZygoteServer
        protected ZygoteConnection createNewConnection(LocalSocket localSocket, String str) throws IOException {
            return new WebViewZygoteConnection(localSocket, str);
        }
    }

    private static class WebViewZygoteConnection extends ZygoteConnection {
        @Override // com.android.internal.os.ZygoteConnection
        protected boolean canPreloadApp() {
            return true;
        }

        @Override // com.android.internal.os.ZygoteConnection
        protected boolean isPreloadComplete() {
            return true;
        }

        @Override // com.android.internal.os.ZygoteConnection
        protected void preload() {
        }

        WebViewZygoteConnection(LocalSocket localSocket, String str) throws IOException {
            super(localSocket, str);
        }

        @Override // com.android.internal.os.ZygoteConnection
        protected void handlePreloadApp(ApplicationInfo applicationInfo) {
            Log.i(WebViewZygoteInit.TAG, "Beginning application preload for " + applicationInfo.packageName);
            doPreload(new LoadedApk(null, applicationInfo, null, null, false, true, false).getClassLoader(), WebViewFactory.getWebViewLibrary(applicationInfo));
            Zygote.allowAppFilesAcrossFork(applicationInfo);
            Log.i(WebViewZygoteInit.TAG, "Application preload done");
        }

        @Override // com.android.internal.os.ZygoteConnection
        protected void handlePreloadPackage(String str, String str2, String str3, String str4) {
            Log.i(WebViewZygoteInit.TAG, "Beginning package preload");
            ClassLoader classLoaderCreateAndCacheWebViewClassLoader = ApplicationLoaders.getDefault().createAndCacheWebViewClassLoader(str, str2, str4);
            for (String str5 : TextUtils.split(str, File.pathSeparator)) {
                Zygote.nativeAllowFileAcrossFork(str5);
            }
            doPreload(classLoaderCreateAndCacheWebViewClassLoader, str3);
            Log.i(WebViewZygoteInit.TAG, "Package preload done");
        }

        private void doPreload(ClassLoader classLoader, String str) {
            boolean z;
            boolean zBooleanValue;
            WebViewLibraryLoader.loadNativeLibrary(classLoader, str);
            int i = 1;
            try {
                Class<WebViewFactoryProvider> webViewProviderClass = WebViewFactory.getWebViewProviderClass(classLoader);
                Method method = webViewProviderClass.getMethod("preloadInZygote", new Class[0]);
                method.setAccessible(true);
                if (method.getReturnType() != Boolean.TYPE) {
                    Log.e(WebViewZygoteInit.TAG, "Unexpected return type: preloadInZygote must return boolean");
                    zBooleanValue = false;
                } else {
                    zBooleanValue = ((Boolean) webViewProviderClass.getMethod("preloadInZygote", new Class[0]).invoke(null, new Object[0])).booleanValue();
                    if (!zBooleanValue) {
                        try {
                            Log.e(WebViewZygoteInit.TAG, "preloadInZygote returned false");
                        } catch (ReflectiveOperationException e) {
                            z = zBooleanValue;
                            e = e;
                            Log.e(WebViewZygoteInit.TAG, "Exception while preloading package", e);
                            zBooleanValue = z;
                        }
                    }
                }
            } catch (ReflectiveOperationException e2) {
                e = e2;
                z = false;
            }
            try {
                DataOutputStream socketOutputStream = getSocketOutputStream();
                if (!zBooleanValue) {
                    i = 0;
                }
                socketOutputStream.writeInt(i);
            } catch (IOException e3) {
                throw new IllegalStateException("Error writing to command socket", e3);
            }
        }
    }

    public static void main(String[] strArr) {
        Log.i(TAG, "Starting WebViewZygoteInit");
        ChildZygoteInit.runZygoteServer(new WebViewZygoteServer(), strArr);
    }
}
