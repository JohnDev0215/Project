package com.google.android.gms.wearable;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.internal.zzau;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class WearableListenerService extends Service implements DataApi.DataListener, MessageApi.MessageListener, CapabilityApi.CapabilityListener, ChannelApi.ChannelListener {

    @RecentlyNonNull
    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private ComponentName zza;
    private zzl zzb;
    private IBinder zzc;
    private Intent zzd;
    private Looper zze;
    private boolean zzg;
    private final Object zzf = new Object();
    private zzau zzh = new zzau(new zzj(this, null));

    @RecentlyNonNull
    public Looper getLooper() {
        if (this.zze == null) {
            HandlerThread handlerThread = new HandlerThread("WearableListenerService");
            handlerThread.start();
            this.zze = handlerThread.getLooper();
        }
        return this.zze;
    }

    @Override // android.app.Service
    @RecentlyNullable
    public final IBinder onBind(@RecentlyNonNull Intent intent) {
        if (BIND_LISTENER_INTENT_ACTION.equals(intent.getAction())) {
            return this.zzc;
        }
        return null;
    }

    @Override // com.google.android.gms.wearable.CapabilityApi.CapabilityListener
    public void onCapabilityChanged(@RecentlyNonNull CapabilityInfo capabilityInfo) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onChannelClosed(@RecentlyNonNull Channel channel, int i, int i2) {
    }

    public void onChannelClosed(@RecentlyNonNull ChannelClient.Channel channel, int i, int i2) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onChannelOpened(@RecentlyNonNull Channel channel) {
    }

    public void onChannelOpened(@RecentlyNonNull ChannelClient.Channel channel) {
    }

    public void onConnectedNodes(@RecentlyNonNull List<Node> list) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.zza = new ComponentName(this, getClass().getName());
        if (Log.isLoggable("WearableLS", 3)) {
            String strValueOf = String.valueOf(this.zza);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 10);
            sb.append("onCreate: ");
            sb.append(strValueOf);
            Log.d("WearableLS", sb.toString());
        }
        this.zzb = new zzl(this, getLooper());
        Intent intent = new Intent(BIND_LISTENER_INTENT_ACTION);
        this.zzd = intent;
        intent.setComponent(this.zza);
        this.zzc = new zzx(this, null);
    }

    @Override // com.google.android.gms.wearable.DataApi.DataListener
    public void onDataChanged(@RecentlyNonNull DataEventBuffer dataEventBuffer) {
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (Log.isLoggable("WearableLS", 3)) {
            String strValueOf = String.valueOf(this.zza);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 11);
            sb.append("onDestroy: ");
            sb.append(strValueOf);
            Log.d("WearableLS", sb.toString());
        }
        synchronized (this.zzf) {
            this.zzg = true;
            zzl zzlVar = this.zzb;
            if (zzlVar == null) {
                String strValueOf2 = String.valueOf(this.zza);
                StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 111);
                sb2.append("onDestroy: mServiceHandler not set, did you override onCreate() but forget to call super.onCreate()? component=");
                sb2.append(strValueOf2);
                throw new IllegalStateException(sb2.toString());
            }
            zzlVar.zzb();
        }
        super.onDestroy();
    }

    @ShowFirstParty
    public void onEntityUpdate(zza zzaVar) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onInputClosed(@RecentlyNonNull Channel channel, int i, int i2) {
    }

    public void onInputClosed(@RecentlyNonNull ChannelClient.Channel channel, int i, int i2) {
    }

    public void onMessageReceived(@RecentlyNonNull MessageEvent messageEvent) {
    }

    @ShowFirstParty
    public void onNotificationReceived(zzb zzbVar) {
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public void onOutputClosed(@RecentlyNonNull Channel channel, int i, int i2) {
    }

    public void onOutputClosed(@RecentlyNonNull ChannelClient.Channel channel, int i, int i2) {
    }

    public void onPeerConnected(@RecentlyNonNull Node node) {
    }

    public void onPeerDisconnected(@RecentlyNonNull Node node) {
    }

    @RecentlyNullable
    @ShowFirstParty
    public Task<byte[]> onRequest(@RecentlyNonNull String str, @RecentlyNonNull String str2, @RecentlyNonNull byte[] bArr) {
        return null;
    }
}
