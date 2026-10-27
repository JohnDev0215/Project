package okhttp3;

import kotlin.jvm.internal.i;
import okio.ByteString;

/* JADX INFO: compiled from: WebSocketListener.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class WebSocketListener {
    public void onClosed(WebSocket webSocket, int i, String str) {
        i.c(webSocket, "webSocket");
        i.c(str, "reason");
    }

    public void onClosing(WebSocket webSocket, int i, String str) {
        i.c(webSocket, "webSocket");
        i.c(str, "reason");
    }

    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
        i.c(webSocket, "webSocket");
        i.c(th, "t");
    }

    public void onMessage(WebSocket webSocket, String str) {
        i.c(webSocket, "webSocket");
        i.c(str, "text");
    }

    public void onMessage(WebSocket webSocket, ByteString byteString) {
        i.c(webSocket, "webSocket");
        i.c(byteString, "bytes");
    }

    public void onOpen(WebSocket webSocket, Response response) {
        i.c(webSocket, "webSocket");
        i.c(response, "response");
    }
}
