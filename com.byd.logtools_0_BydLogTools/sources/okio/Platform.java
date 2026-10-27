package okio;

import kotlin.jvm.b.a;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.i;
import kotlin.text.d;

/* JADX INFO: renamed from: okio.-Platform, reason: invalid class name */
/* JADX INFO: compiled from: -Platform.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Platform {
    public static final byte[] asUtf8ToByteArray(String str) {
        i.c(str, "$this$asUtf8ToByteArray");
        byte[] bytes = str.getBytes(d.f5162a);
        i.b(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <R> R m146synchronized(Object obj, a<? extends R> aVar) {
        R rInvoke;
        i.c(obj, "lock");
        i.c(aVar, "block");
        synchronized (obj) {
            try {
                rInvoke = aVar.invoke();
                h.b(1);
            } catch (Throwable th) {
                h.b(1);
                h.a(1);
                throw th;
            }
        }
        h.a(1);
        return rInvoke;
    }

    public static final String toUtf8String(byte[] bArr) {
        i.c(bArr, "$this$toUtf8String");
        return new String(bArr, d.f5162a);
    }
}
