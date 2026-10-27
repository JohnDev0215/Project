package org.chromium.chrome.browser.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import defpackage.nw2;
import defpackage.ue1;
import defpackage.ve1;
import defpackage.xz;
import org.chromium.base.BundleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.components.crash.PureJavaExceptionHandler;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatApplication extends Application {
    public static final /* synthetic */ int g = 0;
    public nw2<b> e;
    public b f;

    public class a implements PureJavaExceptionHandler.b {
    }

    public static void a(SplitCompatApplication splitCompatApplication, Intent intent, Bundle bundle) {
        super.startActivity(intent, bundle);
    }

    public static Context b(Context context) {
        return !BundleUtils.d("chrome") ? context : BundleUtils.a(context, "chrome");
    }

    public static boolean d() {
        return !Application.getProcessName().contains(":");
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x023d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0198  */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void attachBaseContext(android.content.Context r17) {
        /*
            Method dump skipped, instruction units count: 929
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.base.SplitCompatApplication.attachBaseContext(android.content.Context):void");
    }

    public final b c() {
        if (this.f == null) {
            b bVar = this.e.get();
            this.f = bVar;
            bVar.a = this;
        }
        return this.f;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        return super.getSystemService(str);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c().a();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ve1 ve1Var = ve1.i;
        ve1Var.getClass();
        Object obj = ThreadUtils.a;
        xz.a.registerComponentCallbacks(new ue1(ve1Var));
        c().b();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        c().c(i);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void startActivity(Intent intent, Bundle bundle) {
        a(c().a, intent, bundle);
    }

    public static class b {
        public SplitCompatApplication a;

        public void a() {
        }

        public void b() {
        }

        public void c(int i) {
        }
    }

    public void e(Context context) {
    }
}
