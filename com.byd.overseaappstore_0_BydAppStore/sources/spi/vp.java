package spi;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class vp extends q0 {
    public final Socket l;

    public vp(@NotNull Socket socket) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        this.l = socket;
    }

    @Override // spi.q0
    @NotNull
    public final IOException j(@Nullable IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // spi.q0
    public final void k() {
        Level level;
        StringBuilder sb;
        Logger logger;
        Throwable th;
        try {
            this.l.close();
        } catch (AssertionError e) {
            if (!b.f(e)) {
                throw e;
            }
            Logger logger2 = fk.a;
            level = Level.WARNING;
            sb = new StringBuilder();
            th = e;
            logger = logger2;
            sb.append("Failed to close timed out socket ");
            sb.append(this.l);
            logger.log(level, sb.toString(), th);
        } catch (Exception e2) {
            Logger logger3 = fk.a;
            level = Level.WARNING;
            sb = new StringBuilder();
            th = e2;
            logger = logger3;
            sb.append("Failed to close timed out socket ");
            sb.append(this.l);
            logger.log(level, sb.toString(), th);
        }
    }
}
