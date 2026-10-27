package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: JvmOkio.kt */
/* JADX INFO: loaded from: classes.dex */
final class SocketAsyncTimeout extends AsyncTimeout {
    private final Logger logger;
    private final Socket socket;

    public SocketAsyncTimeout(Socket socket) {
        i.c(socket, "socket");
        this.socket = socket;
        this.logger = Logger.getLogger("okio.Okio");
    }

    @Override // okio.AsyncTimeout
    protected IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // okio.AsyncTimeout
    protected void timedOut() {
        try {
            this.socket.close();
        } catch (AssertionError e2) {
            if (!Okio.isAndroidGetsocknameError(e2)) {
                throw e2;
            }
            this.logger.log(Level.WARNING, "Failed to close timed out socket " + this.socket, (Throwable) e2);
        } catch (Exception e3) {
            this.logger.log(Level.WARNING, "Failed to close timed out socket " + this.socket, (Throwable) e3);
        }
    }
}
