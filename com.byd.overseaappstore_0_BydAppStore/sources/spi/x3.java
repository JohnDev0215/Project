package spi;

import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class x3 {

    @NotNull
    public static final x3 a = new x3();

    private x3() {
    }

    public static String a(x3 x3Var, String str) {
        Objects.requireNonNull(x3Var);
        Intrinsics.checkNotNullParameter("downloadUrl", "filterTip");
        return Intrinsics.areEqual(str != null ? Boolean.valueOf(StringsKt__StringsKt.contains$default(str, "downloadUrl", false, 2, (Object) null)) : null, Boolean.TRUE) ? d.f("downloadUrl", " around hiding") : str;
    }
}
