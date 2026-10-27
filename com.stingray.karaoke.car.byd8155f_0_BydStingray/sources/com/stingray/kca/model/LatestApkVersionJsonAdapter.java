package com.stingray.kca.model;

import A7.m;
import E5.H;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import h2.AbstractC1347d;
import io.sentry.C1489n1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/LatestApkVersionJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/LatestApkVersion;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LatestApkVersionJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13161a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13162b;
    public final q c;
    public volatile Constructor d;

    public LatestApkVersionJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13161a = C1489n1.z("version", "name", "forced", "url");
        w wVar = w.l;
        this.f13162b = h10.c(String.class, wVar, "version");
        this.c = h10.c(Boolean.class, wVar, "forced");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        String str2 = null;
        Boolean bool = null;
        String str3 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13161a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13162b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                str2 = (String) this.f13162b.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                bool = (Boolean) this.c.a(uVar);
                i7 &= -5;
            } else if (iF == 3) {
                str3 = (String) this.f13162b.a(uVar);
                i7 &= -9;
            }
        }
        uVar.j();
        if (i7 == -16) {
            return new LatestApkVersion(str, str2, bool, str3);
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = LatestApkVersion.class.getDeclaredConstructor(String.class, String.class, Boolean.class, String.class, Integer.TYPE, f.c);
            this.d = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, bool, str3, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (LatestApkVersion) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        LatestApkVersion latestApkVersion = (LatestApkVersion) obj;
        m.f(xVar, "writer");
        if (latestApkVersion == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("version");
        String str = latestApkVersion.f13159a;
        q qVar = this.f13162b;
        qVar.d(xVar, str);
        xVar.k("name");
        qVar.d(xVar, latestApkVersion.f13160b);
        xVar.k("forced");
        this.c.d(xVar, latestApkVersion.c);
        xVar.k("url");
        qVar.d(xVar, latestApkVersion.d);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(38, "GeneratedJsonAdapter(LatestApkVersion)");
    }
}
