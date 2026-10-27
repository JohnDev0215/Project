package p;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public final class be2 extends uc {
    public final Socket m;

    public be2(Socket socket) {
        this.m = socket;
    }

    @Override // p.uc
    public final IOException k(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // p.uc
    public final void l() {
        Socket socket = this.m;
        try {
            socket.close();
        } catch (AssertionError e) {
            if (!v03.w(e)) {
                throw e;
            }
            jp1.a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e);
        } catch (Exception e2) {
            jp1.a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e2);
        }
    }
}
