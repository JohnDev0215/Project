package f.k0.h;

import java.io.IOException;

/* JADX INFO: compiled from: RouteException.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends RuntimeException {
    private IOException firstException;
    private IOException lastException;

    i(IOException iOException) {
        super(iOException);
        this.firstException = iOException;
        this.lastException = iOException;
    }

    void a(IOException iOException) {
        f.k0.e.a(this.firstException, iOException);
        this.lastException = iOException;
    }

    public IOException b() {
        return this.firstException;
    }

    public IOException d() {
        return this.lastException;
    }
}
