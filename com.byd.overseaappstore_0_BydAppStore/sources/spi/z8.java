package spi;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ln;

/* JADX INFO: loaded from: classes2.dex */
public interface z8 {
    void a() throws IOException;

    @NotNull
    vr b(@NotNull ln lnVar) throws IOException;

    @Nullable
    ln.a c(boolean z) throws IOException;

    void cancel();

    @NotNull
    wl d();

    void e() throws IOException;

    @NotNull
    rp f(@NotNull im imVar, long j) throws IOException;

    long g(@NotNull ln lnVar) throws IOException;

    void h(@NotNull im imVar) throws IOException;
}
