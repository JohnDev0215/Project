package spi;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.u8;

/* JADX INFO: loaded from: classes2.dex */
public final class ru implements u8.c {
    public final /* synthetic */ u8 a;

    public ru(u8 u8Var) {
        this.a = u8Var;
    }

    @Override // spi.u8.c
    @NotNull
    public final u8 a(@NotNull c3 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this.a;
    }
}
