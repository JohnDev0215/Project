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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/LoginInfoJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/LoginInfo;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LoginInfoJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13169a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13170b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Constructor f13171e;

    public LoginInfoJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13169a = C1489n1.z("token", "durationMs", "ipAddress", "ipCountry", "ipRegion", "ipCity", "refreshToken", "accountInfo");
        w wVar = w.l;
        this.f13170b = h10.c(String.class, wVar, "token");
        this.c = h10.c(Integer.class, wVar, "durationMs");
        this.d = h10.c(AccountInfo.class, wVar, "accountInfo");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        int i7 = -1;
        Integer num = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        AccountInfo accountInfo = null;
        while (uVar.m()) {
            switch (uVar.F(this.f13169a)) {
                case -1:
                    uVar.I();
                    uVar.K();
                    break;
                case 0:
                    str = (String) this.f13170b.a(uVar);
                    i7 &= -2;
                    break;
                case 1:
                    num = (Integer) this.c.a(uVar);
                    i7 &= -3;
                    break;
                case 2:
                    str2 = (String) this.f13170b.a(uVar);
                    i7 &= -5;
                    break;
                case 3:
                    str3 = (String) this.f13170b.a(uVar);
                    i7 &= -9;
                    break;
                case 4:
                    str4 = (String) this.f13170b.a(uVar);
                    i7 &= -17;
                    break;
                case 5:
                    str5 = (String) this.f13170b.a(uVar);
                    i7 &= -33;
                    break;
                case 6:
                    str6 = (String) this.f13170b.a(uVar);
                    i7 &= -65;
                    break;
                case 7:
                    accountInfo = (AccountInfo) this.d.a(uVar);
                    i7 &= -129;
                    break;
            }
        }
        uVar.j();
        if (i7 == -256) {
            return new LoginInfo(str, num, str2, str3, str4, str5, str6, accountInfo);
        }
        Constructor declaredConstructor = this.f13171e;
        if (declaredConstructor == null) {
            declaredConstructor = LoginInfo.class.getDeclaredConstructor(String.class, Integer.class, String.class, String.class, String.class, String.class, String.class, AccountInfo.class, Integer.TYPE, f.c);
            this.f13171e = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, num, str2, str3, str4, str5, str6, accountInfo, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (LoginInfo) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        LoginInfo loginInfo = (LoginInfo) obj;
        m.f(xVar, "writer");
        if (loginInfo == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("token");
        String str = loginInfo.f13163a;
        q qVar = this.f13170b;
        qVar.d(xVar, str);
        xVar.k("durationMs");
        this.c.d(xVar, loginInfo.f13164b);
        xVar.k("ipAddress");
        qVar.d(xVar, loginInfo.c);
        xVar.k("ipCountry");
        qVar.d(xVar, loginInfo.d);
        xVar.k("ipRegion");
        qVar.d(xVar, loginInfo.f13165e);
        xVar.k("ipCity");
        qVar.d(xVar, loginInfo.f13166f);
        xVar.k("refreshToken");
        qVar.d(xVar, loginInfo.f13167g);
        xVar.k("accountInfo");
        this.d.d(xVar, loginInfo.f13168h);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(31, "GeneratedJsonAdapter(LoginInfo)");
    }
}
