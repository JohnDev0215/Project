package L7;

import A7.A;
import H7.InterfaceC0318c;
import W7.AbstractC0839c;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f4041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f4042b;
    public final int c;
    public final List d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f4043e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f4044f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f4045g;

    public a(Class cls, ArrayList arrayList, int i7, int i10, List list) {
        A7.m.f(cls, "jClass");
        A7.k.p(i7, "callMode");
        A7.k.p(i10, "origin");
        A7.m.f(list, "methods");
        this.f4041a = cls;
        this.f4042b = arrayList;
        this.c = i7;
        this.d = list;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList2.add(((Method) it.next()).getGenericReturnType());
        }
        this.f4043e = arrayList2;
        List list2 = this.d;
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(list2, 10));
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            Class<?> returnType = ((Method) it2.next()).getReturnType();
            A7.m.e(returnType, "it");
            List list3 = AbstractC0839c.f9878a;
            Class<?> cls2 = (Class) AbstractC0839c.c.get(returnType);
            if (cls2 != null) {
                returnType = cls2;
            }
            arrayList3.add(returnType);
        }
        this.f4044f = arrayList3;
        List list4 = this.d;
        ArrayList arrayList4 = new ArrayList(AbstractC2000o.J(list4, 10));
        Iterator it3 = list4.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((Method) it3.next()).getDefaultValue());
        }
        this.f4045g = arrayList4;
        if (this.c == 2 && i10 == 1 && !AbstractC1998m.k0(this.f4042b, "value").isEmpty()) {
            throw new UnsupportedOperationException("Positional call of a Java annotation constructor is allowed only if there are no parameters or one parameter named \"value\". This restriction exists because Java annotations (in contrast to Kotlin)do not impose any order on their arguments. Use KCallable#callBy instead.");
        }
    }

    @Override // L7.e
    public final Type p() {
        return this.f4041a;
    }

    @Override // L7.e
    public final Object q(Object[] objArr) {
        Object obj;
        String strA;
        l9.d.H(this, objArr);
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        int i7 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList2 = this.f4042b;
            if (i7 >= length) {
                return io.sentry.config.a.T(this.f4041a, z.x(AbstractC1998m.F0(arrayList2, arrayList)), this.d);
            }
            Object array = objArr[i7];
            int i11 = i10 + 1;
            ArrayList arrayList3 = this.f4044f;
            if (array == null && this.c == 1) {
                obj = this.f4045g.get(i10);
            } else {
                Class cls = (Class) arrayList3.get(i10);
                if (array instanceof Class) {
                    obj = null;
                } else {
                    if (array instanceof InterfaceC0318c) {
                        array = z.i((InterfaceC0318c) array);
                    } else if (array instanceof Object[]) {
                        Object[] objArr2 = (Object[]) array;
                        if (!(objArr2 instanceof Class[])) {
                            if (objArr2 instanceof InterfaceC0318c[]) {
                                A7.m.d(array, "null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                                InterfaceC0318c[] interfaceC0318cArr = (InterfaceC0318c[]) array;
                                ArrayList arrayList4 = new ArrayList(interfaceC0318cArr.length);
                                for (InterfaceC0318c interfaceC0318c : interfaceC0318cArr) {
                                    arrayList4.add(z.i(interfaceC0318c));
                                }
                                array = arrayList4.toArray(new Class[0]);
                            } else {
                                array = objArr2;
                            }
                        }
                        obj = null;
                    }
                    obj = cls.isInstance(array) ? array : null;
                }
            }
            if (obj == null) {
                String str = (String) arrayList2.get(i10);
                Class cls2 = (Class) arrayList3.get(i10);
                InterfaceC0318c interfaceC0318cB = A7.m.a(cls2, Class.class) ? A.f443a.b(InterfaceC0318c.class) : (cls2.isArray() && A7.m.a(cls2.getComponentType(), Class.class)) ? A.f443a.b(InterfaceC0318c[].class) : z.l(cls2);
                if (A7.m.a(interfaceC0318cB.a(), A.f443a.b(Object[].class).a())) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(interfaceC0318cB.a());
                    sb.append('<');
                    Class<?> componentType = z.i(interfaceC0318cB).getComponentType();
                    A7.m.e(componentType, "kotlinClass.java.componentType");
                    sb.append(z.l(componentType).a());
                    sb.append('>');
                    strA = sb.toString();
                } else {
                    strA = interfaceC0318cB.a();
                }
                throw new IllegalArgumentException("Argument #" + i10 + ' ' + str + " is not of the required type " + strA);
            }
            arrayList.add(obj);
            i7++;
            i10 = i11;
        }
    }

    @Override // L7.e
    public final List r() {
        return this.f4043e;
    }

    @Override // L7.e
    public final /* bridge */ /* synthetic */ Member s() {
        return null;
    }

    public /* synthetic */ a(Class cls, ArrayList arrayList, int i7) {
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(cls.getDeclaredMethod((String) it.next(), null));
        }
        this(cls, arrayList, i7, 2, arrayList2);
    }
}
