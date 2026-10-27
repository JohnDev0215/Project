package com.google.android.gms.wearable;

import androidx.annotation.NonNull;
import com.google.android.gms.wearable.ChannelClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzj extends ChannelClient.ChannelCallback {
    final /* synthetic */ WearableListenerService zza;

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onChannelClosed(@NonNull ChannelClient.Channel channel, int i, int i2) {
        this.zza.onChannelClosed(channel, i, i2);
    }

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onChannelOpened(@NonNull ChannelClient.Channel channel) {
        this.zza.onChannelOpened(channel);
    }

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onInputClosed(@NonNull ChannelClient.Channel channel, int i, int i2) {
        this.zza.onInputClosed(channel, i, i2);
    }

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onOutputClosed(@NonNull ChannelClient.Channel channel, int i, int i2) {
        this.zza.onOutputClosed(channel, i, i2);
    }
}
