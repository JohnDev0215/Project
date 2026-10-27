package dagger.spi.shaded.androidx.room.compiler.processing.util;

import javax.lang.model.SourceVersion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NamingUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0016\u0010\u0003\u001a\u00020\u0002*\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"isValidJavaSourceName", "", "", "sanitizeAsJavaParameterName", "argIndex", "", "room-compiler-processing"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class NamingUtilsKt {
    public static final String sanitizeAsJavaParameterName(String str, int i) {
        if (str != null && SourceVersion.isName(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('p');
        sb.append(i);
        return sb.toString();
    }

    public static final boolean isValidJavaSourceName(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return SourceVersion.isName(str);
    }
}
