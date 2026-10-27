package L7;

import A7.D;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public abstract class t implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Member f4062a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f4063b;
    public final Class c;
    public final List d;

    public t(Member member, Type type, Class cls, Type[] typeArr) {
        List listB0;
        this.f4062a = member;
        this.f4063b = type;
        this.c = cls;
        if (cls != null) {
            D d = new D(2);
            d.f(cls);
            d.j(typeArr);
            ArrayList arrayList = d.l;
            listB0 = AbstractC1999n.D(arrayList.toArray(new Type[arrayList.size()]));
        } else {
            listB0 = AbstractC1996k.b0(typeArr);
        }
        this.d = listB0;
    }

    public void a(Object[] objArr) {
        l9.d.H(this, objArr);
    }

    public final void b(Object obj) {
        if (obj == null || !this.f4062a.getDeclaringClass().isInstance(obj)) {
            throw new IllegalArgumentException("An object member requires the object instance passed as the first argument.");
        }
    }

    @Override // L7.e
    public final Type p() {
        return this.f4063b;
    }

    @Override // L7.e
    public final List r() {
        return this.d;
    }

    @Override // L7.e
    public final Member s() {
        return this.f4062a;
    }
}
