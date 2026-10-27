package kotlin.jvm.internal;

import kotlin.SinceKotlin;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class t {
    public kotlin.reflect.f a(FunctionReference functionReference) {
        return functionReference;
    }

    public kotlin.reflect.c b(Class cls) {
        return new m(cls);
    }

    public kotlin.reflect.h c(MutablePropertyReference0 mutablePropertyReference0) {
        return mutablePropertyReference0;
    }

    public kotlin.reflect.i d(MutablePropertyReference1 mutablePropertyReference1) {
        return mutablePropertyReference1;
    }

    public kotlin.reflect.j e(MutablePropertyReference2 mutablePropertyReference2) {
        return mutablePropertyReference2;
    }

    public kotlin.reflect.l f(PropertyReference0 propertyReference0) {
        return propertyReference0;
    }

    public kotlin.reflect.m g(PropertyReference1 propertyReference1) {
        return propertyReference1;
    }

    public kotlin.reflect.n h(PropertyReference2 propertyReference2) {
        return propertyReference2;
    }

    @SinceKotlin(version = "1.3")
    public String i(p pVar) {
        String string = pVar.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    @SinceKotlin(version = "1.1")
    public String j(Lambda lambda) {
        return i(lambda);
    }
}
