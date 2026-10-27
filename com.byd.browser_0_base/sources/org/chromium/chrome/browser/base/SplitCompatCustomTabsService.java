package org.chromium.chrome.browser.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import androidx.browser.customtabs.CustomTabsService;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.List;
import org.chromium.base.BundleUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatCustomTabsService extends CustomTabsService {
    public final String g = "e50";
    public a h;

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean a(CustomTabsSessionToken customTabsSessionToken) {
        this.h.a(customTabsSessionToken);
        return super.a(customTabsSessionToken);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        Context contextB = SplitCompatApplication.b(context);
        a aVar = (a) BundleUtils.e(contextB, this.g);
        this.h = aVar;
        aVar.getClass();
        super.attachBaseContext(contextB);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final Bundle b(String str, Bundle bundle) {
        return this.h.b(str);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean c(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list) {
        return this.h.c(customTabsSessionToken, uri, bundle, list);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean d(CustomTabsSessionToken customTabsSessionToken) {
        return this.h.d(customTabsSessionToken);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final int e(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle) {
        return this.h.h(customTabsSessionToken, str);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean f(int i, Uri uri, Bundle bundle, CustomTabsSessionToken customTabsSessionToken) {
        return this.h.i(i, uri, customTabsSessionToken);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean g(CustomTabsSessionToken customTabsSessionToken, Uri uri) {
        return this.h.j(customTabsSessionToken, uri);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean h(CustomTabsSessionToken customTabsSessionToken, Bundle bundle) {
        return this.h.k(customTabsSessionToken, bundle);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean i(int i, Uri uri, Bundle bundle, CustomTabsSessionToken customTabsSessionToken) {
        return this.h.l(i, uri, customTabsSessionToken);
    }

    @Override // androidx.browser.customtabs.CustomTabsService
    public final boolean j(long j) {
        return this.h.m(j);
    }

    @Override // androidx.browser.customtabs.CustomTabsService, android.app.Service
    public final IBinder onBind(Intent intent) {
        this.h.e(intent);
        return this.f;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.h.f();
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        this.h.g();
        return false;
    }

    public static abstract class a {
        public abstract void a(CustomTabsSessionToken customTabsSessionToken);

        public abstract Bundle b(String str);

        public abstract boolean c(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list);

        public abstract boolean d(CustomTabsSessionToken customTabsSessionToken);

        public abstract int h(CustomTabsSessionToken customTabsSessionToken, String str);

        public abstract boolean i(int i, Uri uri, CustomTabsSessionToken customTabsSessionToken);

        public abstract boolean j(CustomTabsSessionToken customTabsSessionToken, Uri uri);

        public abstract boolean k(CustomTabsSessionToken customTabsSessionToken, Bundle bundle);

        public abstract boolean l(int i, Uri uri, CustomTabsSessionToken customTabsSessionToken);

        public abstract boolean m(long j);

        public void f() {
        }

        public void g() {
        }

        public void e(Intent intent) {
        }
    }
}
