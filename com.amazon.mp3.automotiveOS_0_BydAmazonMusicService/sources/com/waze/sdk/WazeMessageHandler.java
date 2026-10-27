package com.waze.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: classes7.dex */
class WazeMessageHandler extends Handler {
    private final WazeSdk mWazeSdk;

    void onHandleMessage(Message message) {
    }

    WazeMessageHandler(WazeSdk wazeSdk) {
        this.mWazeSdk = wazeSdk;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i;
        Bundle data = message.getData();
        boolean zIsListeningNavigationData = this.mWazeSdk.isListeningNavigationData();
        Log.d("WazeSdk", String.format("Get message from Waze, msg=%s, data=%s, listen to navigation data? %s", Integer.valueOf(message.what), data, Boolean.valueOf(zIsListeningNavigationData)));
        int i2 = message.what;
        if (i2 == 502) {
            this.mWazeSdk.disconnect(data != null ? data.getInt("reason") : 0);
        }
        if (i2 == 702) {
            if (!zIsListeningNavigationData || (i = data.getInt("instruction")) < 0 || i >= WazeSdkConstants$WazeInstructions.values().length) {
                return;
            }
            this.mWazeSdk.onInstructionUpdated(WazeSdkConstants$WazeInstructions.values()[i]);
            return;
        }
        if (i2 == 709) {
            if (zIsListeningNavigationData) {
                this.mWazeSdk.onStreetNameChanged(data.getString("streetName"));
                return;
            }
            return;
        }
        if (i2 == 710) {
            if (zIsListeningNavigationData) {
                this.mWazeSdk.onNavigationStatusChanged(data.getBoolean("isNavigating"));
                return;
            }
            return;
        }
        switch (i2) {
            case 704:
                if (zIsListeningNavigationData) {
                    this.mWazeSdk.onRoundaboutExitUpdated(data.getInt("exitNumber"));
                }
                break;
            case 705:
                if (zIsListeningNavigationData) {
                    this.mWazeSdk.onInstructionDistanceUpdated(data.getString("distanceString"), data.getInt("distanceMeters"));
                }
                break;
            case 706:
                if (zIsListeningNavigationData) {
                    this.mWazeSdk.onTrafficSideUpdated(data.getBoolean("isLeftHandTraffic"));
                }
                break;
            default:
                onHandleMessage(message);
                break;
        }
    }
}
