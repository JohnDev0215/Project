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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/PurchasePageResponseJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/PurchasePageResponse;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PurchasePageResponseJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13193b;
    public volatile Constructor c;

    public PurchasePageResponseJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13192a = C1489n1.z("purchasePageId");
        this.f13193b = h10.c(String.class, w.l, "purchasePageId");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13192a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13193b.a(uVar);
                i7 = -2;
            }
        }
        uVar.j();
        if (i7 == -2) {
            return new PurchasePageResponse(str);
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            declaredConstructor = PurchasePageResponse.class.getDeclaredConstructor(String.class, Integer.TYPE, f.c);
            this.c = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (PurchasePageResponse) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        PurchasePageResponse purchasePageResponse = (PurchasePageResponse) obj;
        m.f(xVar, "writer");
        if (purchasePageResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("purchasePageId");
        this.f13193b.d(xVar, purchasePageResponse.f13191a);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(42, "GeneratedJsonAdapter(PurchasePageResponse)");
    }
}
