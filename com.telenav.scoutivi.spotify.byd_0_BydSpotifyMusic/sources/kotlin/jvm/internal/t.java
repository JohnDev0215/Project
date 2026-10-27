package kotlin.jvm.internal;

/* JADX INFO: compiled from: ReflectionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class t {
    public w5.e a(i iVar) {
        return iVar;
    }

    public w5.c b(Class cls) {
        return new e(cls);
    }

    public w5.d c(Class cls, String str) {
        return new n(cls, str);
    }

    public String d(h hVar) {
        String string = hVar.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public String e(m mVar) {
        return d(mVar);
    }
}
