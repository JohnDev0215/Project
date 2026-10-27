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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/ModelResponseJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/ModelResponse;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ModelResponseJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13177a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13178b;
    public final q c;
    public volatile Constructor d;

    public ModelResponseJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13177a = C1489n1.z("name", "modelPattern", "description", "isAlreadyRegistered", "acceptNewRegistration", "retailerId", "image");
        w wVar = w.l;
        this.f13178b = h10.c(String.class, wVar, "name");
        this.c = h10.c(Boolean.class, wVar, "isAlreadyRegistered");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        int i7 = -1;
        String str2 = null;
        String str3 = null;
        Boolean bool = null;
        Boolean bool2 = null;
        String str4 = null;
        String str5 = null;
        while (uVar.m()) {
            switch (uVar.F(this.f13177a)) {
                case -1:
                    uVar.I();
                    uVar.K();
                    break;
                case 0:
                    str = (String) this.f13178b.a(uVar);
                    i7 &= -2;
                    break;
                case 1:
                    str2 = (String) this.f13178b.a(uVar);
                    i7 &= -3;
                    break;
                case 2:
                    str3 = (String) this.f13178b.a(uVar);
                    i7 &= -5;
                    break;
                case 3:
                    bool = (Boolean) this.c.a(uVar);
                    i7 &= -9;
                    break;
                case 4:
                    bool2 = (Boolean) this.c.a(uVar);
                    i7 &= -17;
                    break;
                case 5:
                    str4 = (String) this.f13178b.a(uVar);
                    i7 &= -33;
                    break;
                case 6:
                    str5 = (String) this.f13178b.a(uVar);
                    i7 &= -65;
                    break;
            }
        }
        uVar.j();
        if (i7 == -128) {
            return new ModelResponse(str, str2, str3, bool, bool2, str4, str5);
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = ModelResponse.class.getDeclaredConstructor(String.class, String.class, String.class, Boolean.class, Boolean.class, String.class, String.class, Integer.TYPE, f.c);
            this.d = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, str3, bool, bool2, str4, str5, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (ModelResponse) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        ModelResponse modelResponse = (ModelResponse) obj;
        m.f(xVar, "writer");
        if (modelResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("name");
        String str = modelResponse.f13172a;
        q qVar = this.f13178b;
        qVar.d(xVar, str);
        xVar.k("modelPattern");
        qVar.d(xVar, modelResponse.f13173b);
        xVar.k("description");
        qVar.d(xVar, modelResponse.c);
        xVar.k("isAlreadyRegistered");
        Boolean bool = modelResponse.d;
        q qVar2 = this.c;
        qVar2.d(xVar, bool);
        xVar.k("acceptNewRegistration");
        qVar2.d(xVar, modelResponse.f13174e);
        xVar.k("retailerId");
        qVar.d(xVar, modelResponse.f13175f);
        xVar.k("image");
        qVar.d(xVar, modelResponse.f13176g);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(35, "GeneratedJsonAdapter(ModelResponse)");
    }
}
