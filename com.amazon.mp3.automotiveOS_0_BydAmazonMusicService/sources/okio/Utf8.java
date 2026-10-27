package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Utf8.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a'\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"", "", "beginIndex", "endIndex", "", "size", "(Ljava/lang/String;II)J", "utf8Size", "okio"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "Utf8")
public final class Utf8 {
    public static /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(String str, int i, int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("beginIndex < 0: ", Integer.valueOf(i)).toString());
        }
        if (!(i2 >= i)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        }
        if (!(i2 <= str.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        }
        long j = 0;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                j++;
            } else {
                if (cCharAt < 2048) {
                    i3 = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i3 = 3;
                } else {
                    int i4 = i + 1;
                    char cCharAt2 = i4 < i2 ? str.charAt(i4) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j++;
                        i = i4;
                    } else {
                        j += (long) 4;
                        i += 2;
                    }
                }
                j += (long) i3;
            }
            i++;
        }
        return j;
    }
}
