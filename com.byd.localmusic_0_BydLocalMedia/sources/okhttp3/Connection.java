package okhttp3;

import java.net.Socket;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface Connection {
    @Nullable
    Handshake handshake();

    Protocol protocol();

    Route route();

    Socket socket();
}
