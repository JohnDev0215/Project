package com.amazon.mp3.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectivityBroadcastReceiver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001f\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0006\u0010\b\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\u0006R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u000b\u0010\rR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/download/ConnectivityBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "Landroid/content/Context;", "context", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "", "onReceive", "register", "unregister", "", "isUserInitialized", "Z", "()Z", "Lkotlin/Function0;", "onConnectivityAvailable", "Lkotlin/jvm/functions/Function0;", "<init>", "(ZLkotlin/jvm/functions/Function0;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ConnectivityBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = ConnectivityBroadcastReceiver.class.getSimpleName();
    private final boolean isUserInitialized;
    private final Function0<Unit> onConnectivityAvailable;

    public ConnectivityBroadcastReceiver(boolean z, Function0<Unit> onConnectivityAvailable) {
        Intrinsics.checkNotNullParameter(onConnectivityAvailable, "onConnectivityAvailable");
        this.isUserInitialized = z;
        this.onConnectivityAvailable = onConnectivityAvailable;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String str = TAG;
        Log.debug(str, Intrinsics.stringPlus("Broadcast Received. Intent = ", intent));
        if (MusicDownloader.getInstance(context).hasSuitableConnection(this.isUserInitialized)) {
            Log.debug(str, "Connectivity Available");
            unregister();
            this.onConnectivityAvailable.invoke();
        }
    }

    public final void register() {
        AmazonApplication.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void unregister() {
        Log.debug(TAG, "Unregistering Connectivity Broadcast Receiver");
        try {
            AmazonApplication.getContext().unregisterReceiver(this);
        } catch (IllegalArgumentException unused) {
            Log.error(TAG, "Connectivity Broadcast-Receiver is not registered");
        }
    }
}
