package org.chromium.chrome.browser.base;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import org.chromium.base.BundleUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatService extends Service {
    public final String e;
    public a f;

    public SplitCompatService(String str) {
        this.e = str;
    }

    public static int a(SplitCompatService splitCompatService, Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public static boolean b(SplitCompatService splitCompatService, Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        Context contextB = SplitCompatApplication.b(context);
        a aVar = (a) BundleUtils.e(contextB, this.e);
        this.f = aVar;
        aVar.a = this;
        super.attachBaseContext(contextB);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f.a();
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f.b();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.f.c();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public final void onLowMemory() {
        super.onLowMemory();
        this.f.d();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        return this.f.e(intent, i, i2);
    }

    @Override // android.app.Service
    public final void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        this.f.f();
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return this.f.g(intent);
    }

    public static abstract class a {
        public SplitCompatService a;

        public abstract IBinder a();

        public int e(Intent intent, int i, int i2) {
            return SplitCompatService.a(this.a, intent, i, i2);
        }

        public boolean g(Intent intent) {
            return SplitCompatService.b(this.a, intent);
        }

        public void b() {
        }

        public void c() {
        }

        public void d() {
        }

        public void f() {
        }
    }
}
