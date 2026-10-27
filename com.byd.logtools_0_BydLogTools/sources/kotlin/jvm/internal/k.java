package kotlin.jvm.internal;

/* JADX INFO: compiled from: ReflectionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class k {
    public kotlin.reflect.e a(FunctionReference functionReference) {
        return functionReference;
    }

    public kotlin.reflect.c b(Class cls) {
        return new d(cls);
    }

    public kotlin.reflect.g c(MutablePropertyReference0 mutablePropertyReference0) {
        return mutablePropertyReference0;
    }

    public String d(g gVar) {
        String string = gVar.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public String e(Lambda lambda) {
        return d(lambda);
    }
}
