package com.android.bluetooth.opp;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppHandoverReceiver extends BroadcastReceiver {
    private static final boolean D = false;
    public static final String TAG = "BluetoothOppHandoverReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        ArrayList<Uri> uris;
        int id;
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals("android.nfc.handover.intent.action.HANDOVER_SEND") || action.equals("android.nfc.handover.intent.action.HANDOVER_SEND_MULTIPLE")) {
            final BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (device == null) {
                return;
            }
            final String mimeType = intent.getType();
            ArrayList<Uri> uris2 = new ArrayList<>();
            if (action.equals("android.nfc.handover.intent.action.HANDOVER_SEND")) {
                Uri stream = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                if (stream != null) {
                    uris2.add(stream);
                }
            } else {
                if (action.equals("android.nfc.handover.intent.action.HANDOVER_SEND_MULTIPLE")) {
                    uris = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
                }
                if (mimeType == null && uris != null && !uris.isEmpty()) {
                    final ArrayList<Uri> finalUris = uris;
                    Thread t = new Thread(new Runnable() { // from class: com.android.bluetooth.opp.BluetoothOppHandoverReceiver.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BluetoothOppManager.getInstance(context).saveSendingFileInfo(mimeType, finalUris, true, true);
                            BluetoothOppManager.getInstance(context).startTransfer(device);
                        }
                    });
                    t.start();
                    return;
                }
                return;
            }
            uris = uris2;
            if (mimeType == null) {
                return;
            } else {
                return;
            }
        }
        if (action.equals("android.btopp.intent.action.WHITELIST_DEVICE")) {
            BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (device2 == null) {
                return;
            }
            BluetoothOppManager.getInstance(context).addToWhitelist(device2.getAddress());
            return;
        }
        if (action.equals("android.btopp.intent.action.STOP_HANDOVER_TRANSFER") && (id = intent.getIntExtra("android.nfc.handover.intent.extra.TRANSFER_ID", -1)) != -1) {
            Uri contentUri = Uri.parse(BluetoothShare.CONTENT_URI + "/" + id);
            context.getContentResolver().delete(contentUri, null, null);
        }
    }
}
