package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;
import java.io.IOException;
import java.net.Socket;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HK {
    public final ConnectivityManager a;

    public HK(Context context) {
        this.a = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public int a(Network network) {
        NetworkInfo networkInfo;
        try {
            try {
                networkInfo = this.a.getNetworkInfo(network);
            } catch (NullPointerException unused) {
                networkInfo = this.a.getNetworkInfo(network);
            }
        } catch (NullPointerException unused2) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.getType() == 17) {
            networkInfo = this.a.getActiveNetworkInfo();
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            return 6;
        }
        return TK.a(networkInfo.getType(), networkInfo.getSubtype());
    }

    public Network b() {
        Network networkB;
        NetworkInfo networkInfo;
        if (Build.VERSION.SDK_INT >= 23) {
            networkB = C1185mt.b(this.a);
            if (networkB != null) {
                return networkB;
            }
        } else {
            networkB = null;
        }
        NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        for (Network network : TK.c(this, null)) {
            try {
                try {
                    networkInfo = this.a.getNetworkInfo(network);
                } catch (NullPointerException unused) {
                    networkInfo = null;
                }
            } catch (NullPointerException unused2) {
                networkInfo = this.a.getNetworkInfo(network);
            }
            if (networkInfo != null && (networkInfo.getType() == activeNetworkInfo.getType() || networkInfo.getType() == 17)) {
                networkB = network;
            }
        }
        return networkB;
    }

    public NetworkCapabilities c(Network network) {
        return this.a.getNetworkCapabilities(network);
    }

    public boolean d(Network network) {
        Socket socket = new Socket();
        try {
            StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
            StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
            try {
                network.bindSocket(socket);
                if (vmPolicy != null) {
                    StrictMode.setVmPolicy(vmPolicy);
                }
                try {
                    socket.close();
                    return true;
                } catch (IOException unused) {
                    return true;
                }
            } finally {
            }
        } catch (IOException unused2) {
            try {
                socket.close();
            } catch (IOException unused3) {
            }
            return false;
        } catch (Throwable th) {
            try {
                socket.close();
            } catch (IOException unused4) {
            }
            throw th;
        }
    }
}
