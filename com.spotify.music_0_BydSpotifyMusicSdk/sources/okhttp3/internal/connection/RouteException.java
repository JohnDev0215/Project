package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.a;

/* JADX INFO: loaded from: classes.dex */
public final class RouteException extends RuntimeException {
    private final IOException firstConnectException;
    private IOException lastConnectException;

    public RouteException(IOException iOException) {
        super(iOException);
        this.firstConnectException = iOException;
        this.lastConnectException = iOException;
    }

    public final void a(IOException iOException) {
        a.a(this.firstConnectException, iOException);
        this.lastConnectException = iOException;
    }

    public final IOException b() {
        return this.firstConnectException;
    }

    public final IOException c() {
        return this.lastConnectException;
    }
}
