package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: JvmAbi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0002H\u0002J\f\u0010\u0005\u001a\u00020\u0002*\u00020\u0002H\u0002J\u000e\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002¨\u0006\u000b"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/JvmAbi;", "", "", "", "startsWithIsPrefix", "capitalizeAsciiOnly", "propertyName", "computeGetterName", "computeSetterName", "<init>", "()V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JvmAbi {
    public static final JvmAbi INSTANCE = new JvmAbi();

    private JvmAbi() {
    }

    public final String computeGetterName(String propertyName) {
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        if (startsWithIsPrefix(propertyName)) {
            return propertyName;
        }
        return "get" + capitalizeAsciiOnly(propertyName);
    }

    public final String computeSetterName(String propertyName) {
        String strCapitalizeAsciiOnly;
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        StringBuilder sb = new StringBuilder();
        sb.append("set");
        if (startsWithIsPrefix(propertyName)) {
            strCapitalizeAsciiOnly = propertyName.substring(2);
            Intrinsics.checkNotNullExpressionValue(strCapitalizeAsciiOnly, "this as java.lang.String).substring(startIndex)");
        } else {
            strCapitalizeAsciiOnly = capitalizeAsciiOnly(propertyName);
        }
        sb.append(strCapitalizeAsciiOnly);
        return sb.toString();
    }

    private final boolean startsWithIsPrefix(String str) {
        if (!StringsKt__StringsJVMKt.startsWith$default(str, "is", false, 2, null) || str.length() == 2) {
            return false;
        }
        char cCharAt = str.charAt(2);
        return Intrinsics.compare(97, (int) cCharAt) > 0 || Intrinsics.compare((int) cCharAt, 122) > 0;
    }

    private final String capitalizeAsciiOnly(String str) {
        boolean z = false;
        if (str.length() == 0) {
            return str;
        }
        char cCharAt = str.charAt(0);
        if ('a' <= cCharAt && cCharAt < '{') {
            z = true;
        }
        if (!z) {
            return str;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        String strSubstring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return upperCase + strSubstring;
    }
}
