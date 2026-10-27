package p;

/* JADX INFO: loaded from: classes.dex */
public final class t22 {
    public static String a(qz0 qz0Var) {
        String string = qz0Var.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
