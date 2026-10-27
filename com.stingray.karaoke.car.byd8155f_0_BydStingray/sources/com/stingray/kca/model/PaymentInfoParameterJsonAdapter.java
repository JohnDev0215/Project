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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/PaymentInfoParameterJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/PaymentInfoParameter;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PaymentInfoParameterJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13186b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Constructor f13187e;

    public PaymentInfoParameterJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13185a = C1489n1.z("currency", "introPriceInfo", "price");
        w wVar = w.l;
        this.f13186b = h10.c(String.class, wVar, "currency");
        this.c = h10.c(IntroPriceInfoParameter.class, wVar, "introPriceInfo");
        this.d = h10.c(Float.class, wVar, "price");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        IntroPriceInfoParameter introPriceInfoParameter = null;
        Float f10 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13185a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13186b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                introPriceInfoParameter = (IntroPriceInfoParameter) this.c.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                f10 = (Float) this.d.a(uVar);
                i7 &= -5;
            }
        }
        uVar.j();
        if (i7 == -8) {
            return new PaymentInfoParameter(str, introPriceInfoParameter, f10);
        }
        Constructor declaredConstructor = this.f13187e;
        if (declaredConstructor == null) {
            declaredConstructor = PaymentInfoParameter.class.getDeclaredConstructor(String.class, IntroPriceInfoParameter.class, Float.class, Integer.TYPE, f.c);
            this.f13187e = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, introPriceInfoParameter, f10, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (PaymentInfoParameter) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        PaymentInfoParameter paymentInfoParameter = (PaymentInfoParameter) obj;
        m.f(xVar, "writer");
        if (paymentInfoParameter == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("currency");
        this.f13186b.d(xVar, paymentInfoParameter.f13183a);
        xVar.k("introPriceInfo");
        this.c.d(xVar, paymentInfoParameter.f13184b);
        xVar.k("price");
        this.d.d(xVar, paymentInfoParameter.c);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(42, "GeneratedJsonAdapter(PaymentInfoParameter)");
    }
}
