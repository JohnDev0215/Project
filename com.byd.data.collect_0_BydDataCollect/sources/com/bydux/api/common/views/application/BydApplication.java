package com.bydux.api.common.views.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import d.a.a.f.c.a.b.a;
import d.a.a.f.c.a.c.c;

/* JADX INFO: loaded from: classes.dex */
public class BydApplication extends Application implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f3546b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3547c = getClass().getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3548d = -1;

    public static Context d() {
        return f3546b;
    }

    @Override // d.a.a.f.c.a.b.a
    public void a() {
        d.a.a.f.b.b.a.i(this.f3547c, "onChangeSkinFail", null);
    }

    @Override // d.a.a.f.c.a.b.a
    public void b() {
        d.a.a.f.b.b.a.i(this.f3547c, "onChangeSkinSuccess", null);
    }

    @Override // d.a.a.f.c.a.b.a
    public void c() {
        d.a.a.f.b.b.a.i(this.f3547c, "onChangeSkinStart", null);
    }

    protected boolean e() {
        return false;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f3548d != configuration.uiMode) {
            c.n().v(c.n().B(configuration), this);
            this.f3548d = configuration.uiMode;
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f3546b = this;
        d.a.a.f.a.a.b().a(this);
        d.a.a.f.b.b.a.i(this.f3547c, "onCreate", null);
        d.a.a.f.b.b.a.b(this);
        this.f3548d = new Configuration(getResources().getConfiguration()).uiMode;
        c.n().s(this, this, e());
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        d.a.a.f.b.b.a.i(this.f3547c, "onTerminate", null);
        c.n().y();
    }
}
