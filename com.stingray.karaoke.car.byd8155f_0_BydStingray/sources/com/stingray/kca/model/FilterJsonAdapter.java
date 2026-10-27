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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/FilterJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/Filter;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FilterJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13094b;
    public volatile Constructor c;

    public FilterJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13093a = C1489n1.z("id", "label");
        this.f13094b = h10.c(String.class, w.l, "id");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        String str2 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13093a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13094b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                str2 = (String) this.f13094b.a(uVar);
                i7 &= -3;
            }
        }
        uVar.j();
        if (i7 == -4) {
            return new Filter(str, str2);
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            declaredConstructor = Filter.class.getDeclaredConstructor(String.class, String.class, Integer.TYPE, f.c);
            this.c = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (Filter) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        Filter filter = (Filter) obj;
        m.f(xVar, "writer");
        if (filter == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("id");
        String str = filter.f13091a;
        q qVar = this.f13094b;
        qVar.d(xVar, str);
        xVar.k("label");
        qVar.d(xVar, filter.f13092b);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(28, "GeneratedJsonAdapter(Filter)");
    }
}
