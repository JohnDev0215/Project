package spi;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class cm extends mn {
    public final String a;
    public final long b;
    public final l2 c;

    public cm(@Nullable String str, long j, @NotNull l2 source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.a = str;
        this.b = j;
        this.c = source;
    }

    @Override // spi.mn
    public final long contentLength() {
        return this.b;
    }

    @Override // spi.mn
    @Nullable
    public final mi contentType() {
        String str = this.a;
        if (str != null) {
            return mi.f.b(str);
        }
        return null;
    }

    @Override // spi.mn
    @NotNull
    public final l2 source() {
        return this.c;
    }
}
