package r9;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public final class I extends C2315e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Socket f21908m;

    public I(Socket socket) {
        this.f21908m = socket;
    }

    @Override // r9.C2315e
    public final void k() {
        Socket socket = this.f21908m;
        try {
            socket.close();
        } catch (AssertionError e10) {
            if (!AbstractC2312b.f(e10)) {
                throw e10;
            }
            x.f21949a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e10);
        } catch (Exception e11) {
            x.f21949a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e11);
        }
    }

    public final IOException l(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }
}
