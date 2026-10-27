package org.chromium.android_webview.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import defpackage.AbstractBinderC0411Wq;
import defpackage.AbstractC1077kw;
import defpackage.BinderC1294os;
import defpackage.C0570br;
import defpackage.InterfaceC0514ar;
import defpackage.Is;
import defpackage.Ts;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VariationsSeedServer extends Service {
    public final AbstractBinderC0411Wq s = new BinderC1294os(this);

    public static void a(VariationsSeedServer variationsSeedServer, InterfaceC0514ar interfaceC0514ar) {
        Objects.requireNonNull(variationsSeedServer);
        Context context = Is.a;
        C0570br c0570brA = C0570br.a(context);
        Bundle bundle = new Bundle(c0570brA.a);
        if (bundle.isEmpty()) {
            return;
        }
        try {
            interfaceC0514ar.j(bundle);
            c0570brA.a.remove("job_interval");
            c0570brA.a.remove("job_queue_time");
            c0570brA.a.remove("seed_fetch_result");
            c0570brA.a.remove("seed_fetch_time");
            if (c0570brA.b(context)) {
                return;
            }
            Ts.a("VariationsSeedServer", "Failed to write variations SharedPreferences to disk", new Object[0]);
        } catch (RemoteException e) {
            Ts.a("VariationsSeedServer", "Error calling reportVariationsServiceMetrics", e);
        }
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

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.s;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
