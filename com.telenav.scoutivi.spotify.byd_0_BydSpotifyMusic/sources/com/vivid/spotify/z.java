package com.vivid.spotify;

import android.app.Application;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: SafeModeManager.java */
/* JADX INFO: loaded from: classes.dex */
public class z {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f5602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final SharedPreferences f5603f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5598a = "SafeModeManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f5599b = "safe_mode";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f5600c = "enableSafeMode";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f5601d = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final CopyOnWriteArraySet<a> f5604g = new CopyOnWriteArraySet<>();

    /* JADX INFO: compiled from: SafeModeManager.java */
    public interface a {
        void a(boolean z6);
    }

    public z(Application application) {
        this.f5602e = true;
        SharedPreferences sharedPreferences = application.getSharedPreferences("safe_mode", 0);
        this.f5603f = sharedPreferences;
        this.f5602e = sharedPreferences.getBoolean("enableSafeMode", true);
    }

    private void c() {
        Iterator<a> it = this.f5604g.iterator();
        while (it.hasNext()) {
            it.next().a(this.f5601d);
        }
    }

    public boolean a() {
        return this.f5602e;
    }

    public boolean b() {
        return this.f5601d;
    }

    public void d(a aVar) {
        this.f5604g.add(aVar);
        aVar.a(this.f5601d);
    }

    public void e(a aVar) {
        this.f5604g.remove(aVar);
    }

    public void f(boolean z6) {
        SharedPreferences.Editor editorEdit = this.f5603f.edit();
        editorEdit.putBoolean("enableSafeMode", z6);
        editorEdit.apply();
        h3.i.b("SafeModeManager").b("a :%s", Boolean.valueOf(z6));
        this.f5602e = z6;
    }

    public void g(boolean z6) {
        boolean z7 = z6 && this.f5602e;
        if (this.f5601d != z7) {
            this.f5601d = z7;
            c();
        }
    }
}
