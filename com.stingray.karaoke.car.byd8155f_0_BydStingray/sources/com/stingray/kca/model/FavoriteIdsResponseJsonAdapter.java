package com.stingray.kca.model;

import A7.m;
import E5.H;
import E5.M;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import h2.AbstractC1347d;
import io.sentry.C1489n1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/FavoriteIdsResponseJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/FavoriteIdsResponse;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FavoriteIdsResponseJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13089a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13090b;
    public volatile Constructor c;

    public FavoriteIdsResponseJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13089a = C1489n1.z("artistIds", "songIds");
        this.f13090b = h10.c(M.g(List.class, String.class), w.l, "artistIds");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        List list = null;
        List list2 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13089a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                list = (List) this.f13090b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                list2 = (List) this.f13090b.a(uVar);
                i7 &= -3;
            }
        }
        uVar.j();
        if (i7 == -4) {
            return new FavoriteIdsResponse(list, list2);
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            declaredConstructor = FavoriteIdsResponse.class.getDeclaredConstructor(List.class, List.class, Integer.TYPE, f.c);
            this.c = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(list, list2, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (FavoriteIdsResponse) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        FavoriteIdsResponse favoriteIdsResponse = (FavoriteIdsResponse) obj;
        m.f(xVar, "writer");
        if (favoriteIdsResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("artistIds");
        List list = favoriteIdsResponse.f13087a;
        q qVar = this.f13090b;
        qVar.d(xVar, list);
        xVar.k("songIds");
        qVar.d(xVar, favoriteIdsResponse.f13088b);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(41, "GeneratedJsonAdapter(FavoriteIdsResponse)");
    }
}
