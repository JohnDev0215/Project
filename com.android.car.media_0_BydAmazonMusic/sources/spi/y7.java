package spi;

import android.car.utils.Logs;
import com.android.car.media.netObserver.NetStateChangeReceiver;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.TimerTask;

/* JADX INFO: compiled from: NetworkUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y7 extends TimerTask {
    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("google.com", 80), 5000);
            z7.c = socket.isConnected();
            Logs.i("NetworkUtils", " isReachable:" + socket.isConnected() + ",address:google.com");
            socket.close();
        } catch (Throwable unused) {
            z7.c = false;
            Logs.e("NetworkUtils", " isReachable: error");
        }
        if (z7.c) {
            z7.b(true);
        }
        NetStateChangeReceiver.c.set(z7.c);
        NetStateChangeReceiver.d.set(z7.c);
    }
}
