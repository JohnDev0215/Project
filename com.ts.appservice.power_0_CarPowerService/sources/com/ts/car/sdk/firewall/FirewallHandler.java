package com.ts.car.sdk.firewall;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.List;
import ts.car.service.firewall.IFirewallListener;
import ts.car.service.firewall.IFirewallResponse;

/* JADX INFO: loaded from: classes.dex */
public class FirewallHandler extends Handler {
    static final String TAG = "FirewallManager";
    private final List<IFirewallListener> mFirewallListeners;
    private final List<IFirewallResponse> mFirewallResponses;

    public FirewallHandler(Looper looper, List<IFirewallListener> listeners, List<IFirewallResponse> responses) {
        super(looper);
        this.mFirewallListeners = listeners;
        this.mFirewallResponses = responses;
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        int i = msg.what;
    }
}
