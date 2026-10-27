package spi;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface e3 {
    void onFailure(@NotNull c3 c3Var, @NotNull IOException iOException);

    void onResponse(@NotNull c3 c3Var, @NotNull ln lnVar) throws IOException;
}
