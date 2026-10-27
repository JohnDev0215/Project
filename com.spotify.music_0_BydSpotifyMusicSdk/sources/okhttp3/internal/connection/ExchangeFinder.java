package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.Address;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ExchangeFinder {
    public final RealConnectionPool a;
    public final Address b;
    public final RealCall c;
    public final EventListener d;
    public RouteSelector.Selection e;
    public RouteSelector f;
    public int g;
    public int h;
    public int i;
    public Route j;

    public ExchangeFinder(RealConnectionPool realConnectionPool, Address address, RealCall realCall, EventListener eventListener) {
        v03.j(realConnectionPool, "connectionPool");
        v03.j(eventListener, "eventListener");
        this.a = realConnectionPool;
        this.b = address;
        this.c = realCall;
        this.d = eventListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x02da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okhttp3.internal.connection.RealConnection a(int r16, int r17, int r18, int r19, boolean r20, boolean r21) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 910
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.a(int, int, int, int, boolean, boolean):okhttp3.internal.connection.RealConnection");
    }

    public final boolean b(HttpUrl httpUrl) {
        v03.j(httpUrl, "url");
        HttpUrl httpUrl2 = this.b.i;
        return httpUrl.e == httpUrl2.e && v03.b(httpUrl.d, httpUrl2.d);
    }

    public final void c(IOException iOException) {
        v03.j(iOException, "e");
        this.j = null;
        if ((iOException instanceof StreamResetException) && ((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
            this.g++;
        } else if (iOException instanceof ConnectionShutdownException) {
            this.h++;
        } else {
            this.i++;
        }
    }
}
