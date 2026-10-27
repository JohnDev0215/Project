package p;

import com.apollographql.apollo3.exception.ApolloWebSocketClosedException;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.ws.RealWebSocket;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class f80 extends WebSocketListener {
    public final /* synthetic */ av a;
    public final /* synthetic */ fr b;

    public f80(bv bvVar, fr frVar) {
        this.a = bvVar;
        this.b = frVar;
    }

    @Override // okhttp3.WebSocketListener
    public final void a(WebSocket webSocket, String str) {
        v03.j(webSocket, "webSocket");
        this.b.h(null);
    }

    @Override // okhttp3.WebSocketListener
    public final void b(WebSocket webSocket, int i, String str) {
        Object objX;
        v03.j(webSocket, "webSocket");
        bv bvVar = (bv) this.a;
        lq2 lq2Var = lq2.a;
        do {
            objX = bvVar.X(bvVar.I(), lq2Var);
            if (objX == y61.a || objX == y61.b) {
                break;
            }
        } while (objX == y61.c);
        this.b.h(new ApolloWebSocketClosedException(i, str));
    }

    @Override // okhttp3.WebSocketListener
    public final void c(WebSocket webSocket, Throwable th) {
        Object objX;
        v03.j(webSocket, "webSocket");
        bv bvVar = (bv) this.a;
        lq2 lq2Var = lq2.a;
        do {
            objX = bvVar.X(bvVar.I(), lq2Var);
            if (objX == y61.a || objX == y61.b) {
                break;
            }
        } while (objX == y61.c);
        this.b.h(th);
    }

    @Override // okhttp3.WebSocketListener
    public final void d(WebSocket webSocket, String str) {
        v03.j(webSocket, "webSocket");
        this.b.n(str);
    }

    @Override // okhttp3.WebSocketListener
    public final void e(WebSocket webSocket, ByteString byteString) {
        v03.j(webSocket, "webSocket");
        v03.j(byteString, "bytes");
        this.b.n(byteString.s());
    }

    @Override // okhttp3.WebSocketListener
    public final void f(RealWebSocket realWebSocket, Response response) {
        Object objX;
        v03.j(realWebSocket, "webSocket");
        bv bvVar = (bv) this.a;
        lq2 lq2Var = lq2.a;
        do {
            objX = bvVar.X(bvVar.I(), lq2Var);
            if (objX == y61.a || objX == y61.b) {
                return;
            }
        } while (objX == y61.c);
    }
}
