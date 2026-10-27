package com.android.bluetooth.hfpclient.connserv;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.telecom.Conference;
import android.telecom.Connection;
import android.telecom.PhoneAccountHandle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class HfpClientConference extends Conference {
    private static final String TAG = "HfpClientConference";
    private BluetoothDevice mDevice;
    private BluetoothHeadsetClient mHeadsetProfile;

    public HfpClientConference(PhoneAccountHandle handle, BluetoothDevice device, BluetoothHeadsetClient client) {
        super(handle);
        this.mDevice = device;
        this.mHeadsetProfile = client;
        boolean manage = HfpClientConnectionService.hasHfpClientEcc(client, device);
        setConnectionCapabilities((manage ? 128 : 0) | 3);
        setActive();
    }

    @Override // android.telecom.Conference
    public void onDisconnect() {
        Log.d(TAG, "onDisconnect");
        this.mHeadsetProfile.terminateCall(this.mDevice, (BluetoothHeadsetClientCall) null);
    }

    @Override // android.telecom.Conference
    public void onMerge(Connection connection) {
        Log.d(TAG, "onMerge " + connection);
        addConnection(connection);
    }

    @Override // android.telecom.Conference
    public void onSeparate(Connection connection) {
        Log.d(TAG, "onSeparate " + connection);
        ((HfpClientConnection) connection).enterPrivateMode();
        removeConnection(connection);
    }

    @Override // android.telecom.Conference
    public void onHold() {
        Log.d(TAG, "onHold");
        this.mHeadsetProfile.holdCall(this.mDevice);
    }

    @Override // android.telecom.Conference
    public void onUnhold() {
        Log.d(TAG, "onUnhold");
        this.mHeadsetProfile.acceptCall(this.mDevice, 1);
    }

    @Override // android.telecom.Conference
    public void onPlayDtmfTone(char c) {
        Log.d(TAG, "onPlayDtmfTone " + c);
        BluetoothHeadsetClient bluetoothHeadsetClient = this.mHeadsetProfile;
        if (bluetoothHeadsetClient != null) {
            bluetoothHeadsetClient.sendDTMF(this.mDevice, (byte) c);
        }
    }

    @Override // android.telecom.Conference
    public void onConnectionAdded(Connection connection) {
        Log.d(TAG, "onConnectionAdded " + connection);
        if (connection.getState() == 5 && getState() == 4) {
            connection.onAnswer();
        } else if (connection.getState() == 4 && getState() == 5) {
            this.mHeadsetProfile.acceptCall(this.mDevice, 0);
        }
    }
}
