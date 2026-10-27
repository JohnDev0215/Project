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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/IntroPriceInfoParameterJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/IntroPriceInfoParameter;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IntroPriceInfoParameterJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13105a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13106b;
    public final q c;
    public volatile Constructor d;

    public IntroPriceInfoParameterJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13105a = C1489n1.z("introPrice", "introPriceCycles");
        w wVar = w.l;
        this.f13106b = h10.c(Float.class, wVar, "introPrice");
        this.c = h10.c(Integer.class, wVar, "introPriceCycles");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        Float f10 = null;
        Integer num = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13105a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                f10 = (Float) this.f13106b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                num = (Integer) this.c.a(uVar);
                i7 &= -3;
            }
        }
        uVar.j();
        if (i7 == -4) {
            return new IntroPriceInfoParameter(f10, num);
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = IntroPriceInfoParameter.class.getDeclaredConstructor(Float.class, Integer.class, Integer.TYPE, f.c);
            this.d = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(f10, num, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (IntroPriceInfoParameter) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        IntroPriceInfoParameter introPriceInfoParameter = (IntroPriceInfoParameter) obj;
        m.f(xVar, "writer");
        if (introPriceInfoParameter == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("introPrice");
        this.f13106b.d(xVar, introPriceInfoParameter.f13103a);
        xVar.k("introPriceCycles");
        this.c.d(xVar, introPriceInfoParameter.f13104b);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(45, "GeneratedJsonAdapter(IntroPriceInfoParameter)");
    }
}
