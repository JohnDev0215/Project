package com.vivid.spotify.common.base;

import android.app.Application;
import android.content.res.Configuration;
import android.os.LocaleList;
import android.text.TextUtils;
import i3.m;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: BaseApplication.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e extends Application {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    m f5213h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5211e = "";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final CopyOnWriteArrayList<v3.a> f5212g = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f5214i = 0;

    private String i() {
        LocaleList locales = getResources().getConfiguration().getLocales();
        return (locales == null || locales.isEmpty()) ? "" : locales.get(0).getLanguage();
    }

    public void g(v3.a aVar) {
        this.f5212g.addIfAbsent(aVar);
    }

    public boolean h() {
        return false;
    }

    public int j() {
        return this.f5214i;
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return true;
    }

    public boolean m() {
        return false;
    }

    public void n(String str) {
        Iterator<v3.a> it = this.f5212g.iterator();
        while (it.hasNext()) {
            it.next().onLanguageChange(str);
        }
    }

    public void o(v3.a aVar) {
        this.f5212g.remove(aVar);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LocaleList locales = configuration.getLocales();
        if (locales == null || locales.isEmpty()) {
            return;
        }
        String language = locales.get(0).getLanguage();
        if (TextUtils.equals(this.f5211e, language)) {
            return;
        }
        this.f5211e = language;
        h3.i.b("BaseApplication").b("b" + this.f5211e, new Object[0]);
        n(language);
        this.f5213h.K();
        h3.i.b("BaseApplication").b("refreshMetadata", new Object[0]);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.f5211e = i();
        h3.i.b("BaseApplication").b("a" + this.f5211e, new Object[0]);
    }

    public void p(int i7) {
        this.f5214i = i7;
    }
}
