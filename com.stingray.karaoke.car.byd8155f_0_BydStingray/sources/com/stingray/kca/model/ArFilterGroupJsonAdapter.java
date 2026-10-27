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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/ArFilterGroupJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/ArFilterGroup;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ArFilterGroupJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13055a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13056b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Constructor f13057e;

    public ArFilterGroupJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13055a = C1489n1.z("id", "items", "defaultItemId", "displayTitle", "image");
        w wVar = w.l;
        this.f13056b = h10.c(String.class, wVar, "id");
        this.c = h10.c(M.g(List.class, ArFilterItem.class), wVar, "items");
        this.d = h10.c(String.class, wVar, "defaultItemId");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        List list = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13055a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13056b.a(uVar);
                if (str == null) {
                    throw f.l("id", "id", uVar);
                }
            } else if (iF == 1) {
                list = (List) this.c.a(uVar);
                if (list == null) {
                    throw f.l("items", "items", uVar);
                }
            } else if (iF == 2) {
                str2 = (String) this.d.a(uVar);
                i7 &= -5;
            } else if (iF == 3) {
                str3 = (String) this.d.a(uVar);
                i7 &= -9;
            } else if (iF == 4) {
                str4 = (String) this.d.a(uVar);
                i7 &= -17;
            }
        }
        uVar.j();
        if (i7 == -29) {
            if (str == null) {
                throw f.f("id", "id", uVar);
            }
            if (list != null) {
                return new ArFilterGroup(str, list, str2, str3, str4);
            }
            throw f.f("items", "items", uVar);
        }
        Constructor declaredConstructor = this.f13057e;
        if (declaredConstructor == null) {
            declaredConstructor = ArFilterGroup.class.getDeclaredConstructor(String.class, List.class, String.class, String.class, String.class, Integer.TYPE, f.c);
            this.f13057e = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        if (str == null) {
            throw f.f("id", "id", uVar);
        }
        if (list == null) {
            throw f.f("items", "items", uVar);
        }
        Object objNewInstance = declaredConstructor.newInstance(str, list, str2, str3, str4, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (ArFilterGroup) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        ArFilterGroup arFilterGroup = (ArFilterGroup) obj;
        m.f(xVar, "writer");
        if (arFilterGroup == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("id");
        this.f13056b.d(xVar, arFilterGroup.f13052a);
        xVar.k("items");
        this.c.d(xVar, arFilterGroup.f13053b);
        xVar.k("defaultItemId");
        String str = arFilterGroup.c;
        q qVar = this.d;
        qVar.d(xVar, str);
        xVar.k("displayTitle");
        qVar.d(xVar, arFilterGroup.d);
        xVar.k("image");
        qVar.d(xVar, arFilterGroup.f13054e);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(35, "GeneratedJsonAdapter(ArFilterGroup)");
    }
}
