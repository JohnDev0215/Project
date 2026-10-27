package org.chromium.android_webview.nonembedded;

import J.N;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Process;
import defpackage.AbstractC1077kw;
import defpackage.AbstractC1237nq;
import defpackage.C0088Eq;
import defpackage.C1354px;
import defpackage.C1569tt;
import defpackage.Is;
import defpackage.Ts;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.chromium.base.PathUtils;
import org.chromium.ui.base.ResourceBundle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebViewApkApplication extends Application {
    public static synchronized boolean a() {
        try {
            if (C1569tt.l.f()) {
                return true;
            }
            C1569tt.l.m(3);
            C1569tt.l.i(Is.a);
            C1569tt c1569tt = C1569tt.l;
            synchronized (c1569tt.c) {
                c1569tt.a();
            }
            N.MMMvkQsN();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Is.a = this;
        if (Is.b().contains(":webview_")) {
            PathUtils.b("webview", "WebView");
            C0088Eq.a();
            Context context2 = Is.a;
            context2.getPackageManager().setComponentEnabledSetting(new ComponentName(context2, "org.chromium.android_webview.services.DeveloperModeContentProvider"), 0, 1);
        }
        String[] strArr = AbstractC1237nq.a;
        ResourceBundle.a = new String[0];
        ResourceBundle.b = strArr;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createConfigurationContext(Configuration configuration) {
        AbstractC1077kw.c();
        return super.createConfigurationContext(configuration);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        AbstractC1077kw.c();
        return super.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        AbstractC1077kw.c();
        return super.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        AbstractC1077kw.c();
        return super.getTheme();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        if (i < 26 || i >= 28 || !Process.isIsolated()) {
            return;
        }
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            if (applicationInfo.metaData != null && applicationInfo.metaData.containsKey("preloaded_fonts")) {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                cls.getMethod("currentActivityThread", new Class[0]);
                Method method = cls.getMethod("getPackageManager", new Class[0]);
                Field declaredField = cls.getDeclaredField("sPackageManager");
                declaredField.setAccessible(true);
                Class<?> cls2 = Class.forName("android.content.pm.IPackageManager");
                declaredField.set(null, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new C1354px(method.invoke(null, new Object[0]))));
            }
        } catch (Exception e) {
            Ts.f("FontWorkaround", "Installing workaround failed, continuing without", e);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
