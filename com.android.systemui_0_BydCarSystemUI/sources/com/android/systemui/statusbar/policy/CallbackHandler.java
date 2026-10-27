package com.android.systemui.statusbar.policy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.statusbar.policy.TBoxSignalController;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class CallbackHandler extends Handler implements NetworkController.SignalCallback {
    private static final int MSG_ADD_REMOVE_SIGNAL = 7;
    private static final int MSG_MOBILE_DATA_ENABLED_CHANGED = 5;
    private final ArrayList<NetworkController.SignalCallback> mSignalCallbacks;

    public CallbackHandler() {
        super(Looper.getMainLooper());
        this.mSignalCallbacks = new ArrayList<>();
    }

    CallbackHandler(Looper looper) {
        super(looper);
        this.mSignalCallbacks = new ArrayList<>();
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        int i = msg.what;
        if (i == 5) {
            Iterator<NetworkController.SignalCallback> it = this.mSignalCallbacks.iterator();
            while (it.hasNext()) {
                it.next().setMobileDataEnabled(msg.arg1 != 0);
            }
        } else {
            if (i != 7) {
                return;
            }
            if (msg.arg1 != 0) {
                this.mSignalCallbacks.add((NetworkController.SignalCallback) msg.obj);
            } else {
                this.mSignalCallbacks.remove((NetworkController.SignalCallback) msg.obj);
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
    public void setWifiIndicators(final SignalController.State state, final NetworkController.IconState statusIcon, final String description, final boolean isTransient, final String statusLabel) {
        post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$CallbackHandler$MW7MHe5KaBPhXGP_PNzAhh6TvRk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setWifiIndicators$0$CallbackHandler(state, statusIcon, description, isTransient, statusLabel);
            }
        });
    }

    public /* synthetic */ void lambda$setWifiIndicators$0$CallbackHandler(SignalController.State state, NetworkController.IconState iconState, String str, boolean z, String str2) {
        Iterator<NetworkController.SignalCallback> it = this.mSignalCallbacks.iterator();
        while (it.hasNext()) {
            it.next().setWifiIndicators(state, iconState, str, z, str2);
        }
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
    public void setTBoxIndicators(final TBoxSignalController.TBoxSignalState state) {
        post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$CallbackHandler$xEsrGM3rY85abU9h-s2D00rLGdg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setTBoxIndicators$1$CallbackHandler(state);
            }
        });
    }

    public /* synthetic */ void lambda$setTBoxIndicators$1$CallbackHandler(TBoxSignalController.TBoxSignalState tBoxSignalState) {
        Iterator<NetworkController.SignalCallback> it = this.mSignalCallbacks.iterator();
        while (it.hasNext()) {
            it.next().setTBoxIndicators(tBoxSignalState);
        }
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
    public void setTBoxNoneSignal(final TBoxSignalController.TBoxSignalState state) {
        post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$CallbackHandler$NADXLbyqlVm6Cau1t22CeI1RPQM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setTBoxNoneSignal$2$CallbackHandler(state);
            }
        });
    }

    public /* synthetic */ void lambda$setTBoxNoneSignal$2$CallbackHandler(TBoxSignalController.TBoxSignalState tBoxSignalState) {
        Iterator<NetworkController.SignalCallback> it = this.mSignalCallbacks.iterator();
        while (it.hasNext()) {
            it.next().setTBoxNoneSignal(tBoxSignalState);
        }
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
    public void showTelephoneData(final boolean show) {
        BydLogHelper.logD("showTelephoneData show : " + show);
        post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$CallbackHandler$eDPr9WDrSLy4kR3CfWN1hdiT4xI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showTelephoneData$3$CallbackHandler(show);
            }
        });
    }

    public /* synthetic */ void lambda$showTelephoneData$3$CallbackHandler(boolean z) {
        Iterator<NetworkController.SignalCallback> it = this.mSignalCallbacks.iterator();
        while (it.hasNext()) {
            it.next().showTelephoneData(z);
        }
    }

    public void setListening(NetworkController.SignalCallback signalCallback, boolean z) {
        obtainMessage(7, z ? 1 : 0, 0, signalCallback).sendToTarget();
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
    public void setMobileDataEnabled(boolean z) {
        obtainMessage(5, z ? 1 : 0, 0).sendToTarget();
    }
}
