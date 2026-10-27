package defpackage;

/* JADX INFO: compiled from: ReflectionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class ga2 {
    public static String a(vr0 vr0Var) {
        String string = vr0Var.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
