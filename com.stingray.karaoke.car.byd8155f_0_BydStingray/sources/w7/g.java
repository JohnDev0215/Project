package W7;

import f8.InterfaceC1265a;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class g extends e implements InterfaceC1265a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f9883b;

    public g(C2061f c2061f, Object[] objArr) {
        super(c2061f);
        this.f9883b = objArr;
    }

    public final ArrayList a() {
        Object[] objArr = this.f9883b;
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            A7.m.c(obj);
            Class<?> cls = obj.getClass();
            List list = AbstractC0839c.f9878a;
            arrayList.add(Enum.class.isAssignableFrom(cls) ? new s(null, (Enum) obj) : obj instanceof Annotation ? new f(null, (Annotation) obj) : obj instanceof Object[] ? new g(null, (Object[]) obj) : obj instanceof Class ? new o(null, (Class) obj) : new u(null, obj));
        }
        return arrayList;
    }
}
