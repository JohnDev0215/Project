package defpackage;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* JADX INFO: renamed from: yy, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractServiceC1849yy extends Service {
    public Bu s;

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

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.s.a(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Bu bu = new Bu(new ContentChildProcessServiceDelegate(), this, getApplicationContext());
        this.s = bu;
        bu.b();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.s.c();
        this.s = null;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
