package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SneakyThrow {
    public static void reThrow(@NonNull Exception exc) throws Throwable {
        sneakyThrow(exc);
    }

    private static <E extends Throwable> void sneakyThrow(@NonNull Throwable th) throws Throwable {
        throw th;
    }

    private SneakyThrow() {
    }
}
