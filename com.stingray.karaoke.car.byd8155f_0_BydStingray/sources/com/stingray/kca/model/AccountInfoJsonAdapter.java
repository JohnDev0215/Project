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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/AccountInfoJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/AccountInfo;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AccountInfoJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13047b;
    public final q c;
    public volatile Constructor d;

    public AccountInfoJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13046a = C1489n1.z("suid", "loginId", "emailAddress", "userToken", "subscriptionInfo");
        w wVar = w.l;
        this.f13047b = h10.c(String.class, wVar, "suid");
        this.c = h10.c(SubscriptionInfo.class, wVar, "subscriptionInfo");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        SubscriptionInfo subscriptionInfo = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13046a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13047b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                str2 = (String) this.f13047b.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                str3 = (String) this.f13047b.a(uVar);
                i7 &= -5;
            } else if (iF == 3) {
                str4 = (String) this.f13047b.a(uVar);
                i7 &= -9;
            } else if (iF == 4) {
                subscriptionInfo = (SubscriptionInfo) this.c.a(uVar);
                i7 &= -17;
            }
        }
        uVar.j();
        if (i7 == -32) {
            return new AccountInfo(str, str2, str3, str4, subscriptionInfo);
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = AccountInfo.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, SubscriptionInfo.class, Integer.TYPE, f.c);
            this.d = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, str3, str4, subscriptionInfo, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (AccountInfo) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        AccountInfo accountInfo = (AccountInfo) obj;
        m.f(xVar, "writer");
        if (accountInfo == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("suid");
        String str = accountInfo.f13043a;
        q qVar = this.f13047b;
        qVar.d(xVar, str);
        xVar.k("loginId");
        qVar.d(xVar, accountInfo.f13044b);
        xVar.k("emailAddress");
        qVar.d(xVar, accountInfo.c);
        xVar.k("userToken");
        qVar.d(xVar, accountInfo.d);
        xVar.k("subscriptionInfo");
        this.c.d(xVar, accountInfo.f13045e);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(33, "GeneratedJsonAdapter(AccountInfo)");
    }
}
