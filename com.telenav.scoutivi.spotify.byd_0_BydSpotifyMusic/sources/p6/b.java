package p6;

/* JADX INFO: compiled from: -Platform.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final byte[] a(String asUtf8ToByteArray) {
        kotlin.jvm.internal.l.e(asUtf8ToByteArray, "$this$asUtf8ToByteArray");
        byte[] bytes = asUtf8ToByteArray.getBytes(y5.d.f9992b);
        kotlin.jvm.internal.l.d(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    public static final String b(byte[] toUtf8String) {
        kotlin.jvm.internal.l.e(toUtf8String, "$this$toUtf8String");
        return new String(toUtf8String, y5.d.f9992b);
    }
}
