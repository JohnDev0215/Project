package kotlin.jvm.internal;

/* JADX INFO: compiled from: ReflectionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class k {
    public kotlin.reflect.c a(Class cls) {
        return new d(cls);
    }

    public kotlin.reflect.d b(Class cls, String str) {
        return new i(cls, str);
    }

    public kotlin.reflect.g c(MutablePropertyReference1 mutablePropertyReference1) {
        return mutablePropertyReference1;
    }

    public kotlin.reflect.j d(PropertyReference1 propertyReference1) {
        return propertyReference1;
    }

    public String e(g gVar) {
        String string = gVar.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public String f(Lambda lambda) {
        return e(lambda);
    }
}
