package okhttp3;

import okhttp3.internal.ws.RealWebSocket;
import okio.ByteString;
import p.f80;

/* JADX INFO: loaded from: classes.dex */
public interface WebSocket {

    public interface Factory {
        RealWebSocket a(Request request, f80 f80Var);
    }

    boolean a(ByteString byteString);

    boolean b(int i, String str);

    boolean c(String str);
}
