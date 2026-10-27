package com.visualon.OSMPUtils;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes7.dex */
public class VOHdmiCapableChangeListener {
    private static final VOSupportEncoding SUPPORT_ENCODING = new VOSupportEncoding(new int[]{2}, 0);
    private final Context context;
    private final Listener listener;
    private final BroadcastReceiver receiver = new HdmiAudioPlugBroadcastReceiver();

    public interface Listener {
        void onHdmiConnectChanged(VOSupportEncoding vOSupportEncoding);
    }

    public VOHdmiCapableChangeListener(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    @TargetApi(21)
    public void register() {
        BroadcastReceiver broadcastReceiver = this.receiver;
        if (broadcastReceiver != null) {
            this.context.registerReceiver(broadcastReceiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
        }
        this.listener.onHdmiConnectChanged(SUPPORT_ENCODING);
    }

    public void unregister() {
        BroadcastReceiver broadcastReceiver = this.receiver;
        if (broadcastReceiver != null) {
            this.context.unregisterReceiver(broadcastReceiver);
        }
    }

    @TargetApi(21)
    private final class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
        private HdmiAudioPlugBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.media.action.HDMI_AUDIO_PLUG")) {
                VOHdmiCapableChangeListener.this.listener.onHdmiConnectChanged(new VOSupportEncoding(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0)));
            }
        }
    }
}
