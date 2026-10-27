package spi;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public abstract class h1 {
    public final int a(@NotNull Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        if (throwable instanceof SocketTimeoutException) {
            return 1;
        }
        return throwable instanceof UnknownHostException ? true : throwable instanceof ConnectException ? 2 : 3;
    }
}
