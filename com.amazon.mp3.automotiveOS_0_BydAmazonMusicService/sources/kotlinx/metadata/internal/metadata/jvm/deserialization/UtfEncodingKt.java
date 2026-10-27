package kotlinx.metadata.internal.metadata.jvm.deserialization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: utfEncoding.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"", "", "strings", "", "stringsToBytes", "([Ljava/lang/String;)[B", "metadata.jvm"}, k = 2, mv = {1, 7, 1})
public final class UtfEncodingKt {
    public static final byte[] stringsToBytes(String[] strings) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        int length = 0;
        for (String str : strings) {
            length += str.length();
        }
        byte[] bArr = new byte[length];
        int i = 0;
        for (String str2 : strings) {
            int length2 = str2.length();
            int i2 = 0;
            while (i2 < length2) {
                bArr[i] = (byte) str2.charAt(i2);
                i2++;
                i++;
            }
        }
        return bArr;
    }
}
