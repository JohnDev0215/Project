package L7;

import A7.D;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class f extends t implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f4051f;

    /* JADX WARN: Illegal instructions before constructor call */
    public f(Constructor constructor, Object obj, int i7) {
        this.f4050e = i7;
        switch (i7) {
            case 1:
                A7.m.f(constructor, "constructor");
                Class declaringClass = constructor.getDeclaringClass();
                A7.m.e(declaringClass, "constructor.declaringClass");
                Type[] genericParameterTypes = constructor.getGenericParameterTypes();
                A7.m.e(genericParameterTypes, "constructor.genericParameterTypes");
                super(constructor, declaringClass, null, genericParameterTypes);
                this.f4051f = obj;
                break;
            default:
                A7.m.f(constructor, "constructor");
                Class declaringClass2 = constructor.getDeclaringClass();
                A7.m.e(declaringClass2, "constructor.declaringClass");
                Type[] genericParameterTypes2 = constructor.getGenericParameterTypes();
                A7.m.e(genericParameterTypes2, "constructor.genericParameterTypes");
                super(constructor, declaringClass2, null, (Type[]) (genericParameterTypes2.length <= 2 ? new Type[0] : AbstractC1996k.I(genericParameterTypes2, 1, genericParameterTypes2.length - 1)));
                this.f4051f = obj;
                break;
        }
    }

    @Override // L7.e
    public final Object q(Object[] objArr) {
        switch (this.f4050e) {
            case 0:
                l9.d.H(this, objArr);
                Constructor constructor = (Constructor) this.f4062a;
                D d = new D(3);
                d.f(this.f4051f);
                d.j(objArr);
                d.f(null);
                ArrayList arrayList = d.l;
                return constructor.newInstance(arrayList.toArray(new Object[arrayList.size()]));
            default:
                l9.d.H(this, objArr);
                Constructor constructor2 = (Constructor) this.f4062a;
                D d10 = new D(2);
                d10.f(this.f4051f);
                d10.j(objArr);
                ArrayList arrayList2 = d10.l;
                return constructor2.newInstance(arrayList2.toArray(new Object[arrayList2.size()]));
        }
    }
}
