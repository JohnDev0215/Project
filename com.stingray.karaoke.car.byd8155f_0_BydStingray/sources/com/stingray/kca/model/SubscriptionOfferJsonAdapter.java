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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/SubscriptionOfferJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/SubscriptionOffer;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SubscriptionOfferJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13221b;
    public volatile Constructor c;

    public SubscriptionOfferJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13220a = C1489n1.z("productId", "name", "description", "promo");
        this.f13221b = h10.c(String.class, w.l, "productId");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13220a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13221b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                str2 = (String) this.f13221b.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                str3 = (String) this.f13221b.a(uVar);
                i7 &= -5;
            } else if (iF == 3) {
                str4 = (String) this.f13221b.a(uVar);
                i7 &= -9;
            }
        }
        uVar.j();
        if (i7 == -16) {
            return new SubscriptionOffer(str, str2, str3, str4);
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            declaredConstructor = SubscriptionOffer.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, Integer.TYPE, f.c);
            this.c = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, str3, str4, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (SubscriptionOffer) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        SubscriptionOffer subscriptionOffer = (SubscriptionOffer) obj;
        m.f(xVar, "writer");
        if (subscriptionOffer == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("productId");
        String str = subscriptionOffer.f13218a;
        q qVar = this.f13221b;
        qVar.d(xVar, str);
        xVar.k("name");
        qVar.d(xVar, subscriptionOffer.f13219b);
        xVar.k("description");
        qVar.d(xVar, subscriptionOffer.c);
        xVar.k("promo");
        qVar.d(xVar, subscriptionOffer.d);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(39, "GeneratedJsonAdapter(SubscriptionOffer)");
    }
}
