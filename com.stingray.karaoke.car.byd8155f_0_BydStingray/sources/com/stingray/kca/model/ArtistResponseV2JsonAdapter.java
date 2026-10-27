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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/ArtistResponseV2JsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/ArtistResponseV2;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ArtistResponseV2JsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13067b;
    public final q c;
    public volatile Constructor d;

    public ArtistResponseV2JsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13066a = C1489n1.z("id", "name", "songCount");
        w wVar = w.l;
        this.f13067b = h10.c(String.class, wVar, "id");
        this.c = h10.c(Integer.class, wVar, "songCount");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        String str2 = null;
        Integer num = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13066a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13067b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                str2 = (String) this.f13067b.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                num = (Integer) this.c.a(uVar);
                i7 &= -5;
            }
        }
        uVar.j();
        if (i7 == -8) {
            return new ArtistResponseV2(str, str2, num);
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = ArtistResponseV2.class.getDeclaredConstructor(String.class, String.class, Integer.class, Integer.TYPE, f.c);
            this.d = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, num, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (ArtistResponseV2) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        ArtistResponseV2 artistResponseV2 = (ArtistResponseV2) obj;
        m.f(xVar, "writer");
        if (artistResponseV2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("id");
        String str = artistResponseV2.f13064a;
        q qVar = this.f13067b;
        qVar.d(xVar, str);
        xVar.k("name");
        qVar.d(xVar, artistResponseV2.f13065b);
        xVar.k("songCount");
        this.c.d(xVar, artistResponseV2.c);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(38, "GeneratedJsonAdapter(ArtistResponseV2)");
    }
}
