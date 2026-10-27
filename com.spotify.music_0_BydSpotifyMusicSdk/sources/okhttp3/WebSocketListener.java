package okhttp3;

import okhttp3.internal.ws.RealWebSocket;
import okio.ByteString;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class WebSocketListener {
    public void a(WebSocket webSocket, String str) {
        v03.j(webSocket, "webSocket");
    }

    public void b(WebSocket webSocket, int i, String str) {
        v03.j(webSocket, "webSocket");
    }

    public void c(WebSocket webSocket, Throwable th) {
        v03.j(webSocket, "webSocket");
    }

    public void d(WebSocket webSocket, String str) {
        v03.j(webSocket, "webSocket");
    }

    public void e(WebSocket webSocket, ByteString byteString) {
        v03.j(webSocket, "webSocket");
        v03.j(byteString, "bytes");
    }

    public void f(RealWebSocket realWebSocket, Response response) {
        v03.j(realWebSocket, "webSocket");
    }
}
