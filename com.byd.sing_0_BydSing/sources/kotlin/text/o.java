package kotlin.text;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: StringsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class o extends n {
    public static String c(byte[] bArr) {
        kotlin.jvm.internal.h.d(bArr, "$this$decodeToString");
        return new String(bArr, d.f4111a);
    }

    public static byte[] d(String str) {
        kotlin.jvm.internal.h.d(str, "$this$encodeToByteArray");
        byte[] bytes = str.getBytes(d.f4111a);
        kotlin.jvm.internal.h.c(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    public static final boolean e(String str, String str2, boolean z) {
        kotlin.jvm.internal.h.d(str, "$this$endsWith");
        kotlin.jvm.internal.h.d(str2, "suffix");
        return !z ? str.endsWith(str2) : h(str, str.length() - str2.length(), str2, 0, str2.length(), true);
    }

    public static /* synthetic */ boolean f(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return e(str, str2, z);
    }

    public static boolean g(String str, String str2, boolean z) {
        return str == null ? str2 == null : !z ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public static final boolean h(String str, int i, String str2, int i2, int i3, boolean z) {
        kotlin.jvm.internal.h.d(str, "$this$regionMatches");
        kotlin.jvm.internal.h.d(str2, "other");
        return !z ? str.regionMatches(i, str2, i2, i3) : str.regionMatches(z, i, str2, i2, i3);
    }

    public static String i(String str, String str2, String str3, boolean z) {
        kotlin.jvm.internal.h.d(str, "$this$replace");
        kotlin.jvm.internal.h.d(str2, "oldValue");
        kotlin.jvm.internal.h.d(str3, "newValue");
        int i = 0;
        int iT = StringsKt__StringsKt.t(str, str2, 0, z);
        if (iT < 0) {
            return str;
        }
        int length = str2.length();
        int iA = kotlin.q.n.a(length, 1);
        int length2 = (str.length() - length) + str3.length();
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        do {
            sb.append((CharSequence) str, i, iT);
            sb.append(str3);
            i = iT + length;
            if (iT >= str.length()) {
                break;
            }
            iT = StringsKt__StringsKt.t(str, str2, iT + iA, z);
        } while (iT > 0);
        sb.append((CharSequence) str, i, str.length());
        String string = sb.toString();
        kotlin.jvm.internal.h.c(string, "stringBuilder.append(this, i, length).toString()");
        return string;
    }

    public static /* synthetic */ String j(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return i(str, str2, str3, z);
    }

    public static boolean k(String str, String str2, boolean z) {
        kotlin.jvm.internal.h.d(str, "$this$startsWith");
        kotlin.jvm.internal.h.d(str2, "prefix");
        return !z ? str.startsWith(str2) : h(str, 0, str2, 0, str2.length(), z);
    }

    public static /* synthetic */ boolean l(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return k(str, str2, z);
    }
}
