package spi;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface c3 extends Cloneable {

    public interface a {
        @NotNull
        c3 a(@NotNull im imVar);
    }

    void cancel();

    void e(@NotNull e3 e3Var);

    @NotNull
    ln execute() throws IOException;

    boolean isCanceled();

    @NotNull
    im request();

    @NotNull
    dt timeout();
}
