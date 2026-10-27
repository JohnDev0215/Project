package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public interface SocketAdapter {

    public static final class DefaultImpls {
    }

    boolean a(SSLSocket sSLSocket);

    String b(SSLSocket sSLSocket);

    boolean c();

    void d(SSLSocket sSLSocket, String str, List list);
}
