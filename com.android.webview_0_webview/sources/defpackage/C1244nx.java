package defpackage;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.LayoutInflater;

/* JADX INFO: renamed from: nx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1244nx extends ContextWrapper {
    public Context a;

    public C1244nx(Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        if (this.a == null) {
            Context applicationContext = getBaseContext().getApplicationContext();
            if (applicationContext == getBaseContext()) {
                this.a = this;
            } else {
                this.a = AbstractC1299ox.a(applicationContext);
            }
        }
        return this.a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        Context context = AbstractC1299ox.c;
        return context != null ? context.getAssets() : getBaseContext().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return new C1189mx(this, C1244nx.class.getClassLoader(), getBaseContext().getClassLoader());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Context context = AbstractC1299ox.c;
        return context != null ? context.getResources() : getBaseContext().getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        Context context = AbstractC1299ox.c;
        return context != null ? LayoutInflater.from(context) : ((LayoutInflater) getBaseContext().getSystemService(str)).cloneInContext(this);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Context context = AbstractC1299ox.c;
        return context != null ? context.getTheme() : getBaseContext().getTheme();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        getBaseContext().registerComponentCallbacks(componentCallbacks);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        if (Is.a(this) == null) {
            intent.setFlags(268435456);
        }
        super.startActivity(intent);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        getBaseContext().unregisterComponentCallbacks(componentCallbacks);
    }
}
