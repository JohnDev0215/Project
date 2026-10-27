package spi;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ts {

    @Nullable
    public vs a;
    public long b;

    @NotNull
    public final String c;
    public final boolean d;

    public ts(@NotNull String name, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.c = name;
        this.d = z;
        this.b = -1L;
    }

    public /* synthetic */ ts(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z);
    }

    public abstract long a();

    @NotNull
    public final String toString() {
        return this.c;
    }
}
