package p6;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* JADX INFO: compiled from: JvmOkio.kt */
/* JADX INFO: loaded from: classes.dex */
final class z extends d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final Socket f8916m;

    public z(Socket socket) {
        kotlin.jvm.internal.l.e(socket, "socket");
        this.f8916m = socket;
    }

    @Override // p6.d
    protected IOException t(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // p6.d
    protected void x() {
        try {
            this.f8916m.close();
        } catch (AssertionError e7) {
            if (!o.c(e7)) {
                throw e7;
            }
            p.f8888a.log(Level.WARNING, "Failed to close timed out socket " + this.f8916m, (Throwable) e7);
        } catch (Exception e8) {
            p.f8888a.log(Level.WARNING, "Failed to close timed out socket " + this.f8916m, (Throwable) e8);
        }
    }
}
