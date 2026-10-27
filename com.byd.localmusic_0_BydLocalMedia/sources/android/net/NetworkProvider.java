package android.net;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class NetworkProvider {
    public static final int CMD_CANCEL_REQUEST = 2;
    public static final int CMD_REQUEST_NETWORK = 1;
    public static final int FIRST_PROVIDER_ID = 1;
    public static final int ID_NONE = -1;
    public static final int ID_VPN = -2;
    private final ConnectivityManager mCm;
    private final Messenger mMessenger;
    private final String mName;
    private int mProviderId = -1;

    @SystemApi
    public void onNetworkRequestWithdrawn(NetworkRequest networkRequest) {
    }

    @SystemApi
    public void onNetworkRequested(NetworkRequest networkRequest, int i, int i2) {
    }

    @SystemApi
    public NetworkProvider(Context context, Looper looper, String str) {
        this.mCm = ConnectivityManager.from(context);
        this.mMessenger = new Messenger(new Handler(looper) { // from class: android.net.NetworkProvider.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    NetworkProvider.this.onNetworkRequested((NetworkRequest) message.obj, message.arg1, message.arg2);
                    return;
                }
                if (i != 2) {
                    Log.e(NetworkProvider.this.mName, "Unhandled message: " + message.what);
                    return;
                }
                NetworkProvider.this.onNetworkRequestWithdrawn((NetworkRequest) message.obj);
            }
        });
        this.mName = str;
    }

    public Messenger getMessenger() {
        return this.mMessenger;
    }

    public String getName() {
        return this.mName;
    }

    public int getProviderId() {
        return this.mProviderId;
    }

    public void setProviderId(int i) {
        this.mProviderId = i;
    }

    @SystemApi
    public void declareNetworkRequestUnfulfillable(NetworkRequest networkRequest) {
        this.mCm.declareNetworkRequestUnfulfillable(networkRequest);
    }
}
