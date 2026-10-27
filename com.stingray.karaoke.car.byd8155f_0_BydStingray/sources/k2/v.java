package k2;

import H7.H;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class v extends j {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f17707n;

    public v() {
        super(2008);
        this.f17707n = 1;
    }

    public static int a(int i7, int i10) {
        if (i7 == 2000 && i10 == 1) {
            return 2001;
        }
        return i7;
    }

    public static v b(int i7, IOException iOException) {
        String message = iOException.getMessage();
        int i10 = iOException instanceof SocketTimeoutException ? 2002 : iOException instanceof InterruptedIOException ? 1004 : (message == null || !H.R(message).matches("cleartext.*not permitted.*")) ? 2001 : 2007;
        return i10 == 2007 ? new u(2007, iOException, "Cleartext HTTP traffic not permitted. See https://developer.android.com/guide/topics/media/issues/cleartext-not-permitted") : new v(i10, i7, iOException);
    }

    public v(int i7, int i10, IOException iOException) {
        super(iOException, a(i7, i10));
        this.f17707n = i10;
    }

    public v(int i7, IOException iOException, String str) {
        super(str, iOException, a(i7, 1));
        this.f17707n = 1;
    }
}
