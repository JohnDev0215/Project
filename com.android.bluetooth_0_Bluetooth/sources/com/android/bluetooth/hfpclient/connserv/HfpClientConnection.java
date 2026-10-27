package com.android.bluetooth.hfpclient.connserv;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.Context;
import android.net.Uri;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.util.Log;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class HfpClientConnection extends Connection {
    private static final boolean DBG = false;
    private static final String TAG = "HfpClientConnection";
    private boolean mAdded;
    private boolean mClientHasEcc;
    private boolean mClosed;
    private final Context mContext;
    private BluetoothHeadsetClientCall mCurrentCall;
    private final BluetoothDevice mDevice;
    private BluetoothHeadsetClient mHeadsetProfile;
    private HfpClientConnectionService mHfpClientConnectionService;
    private boolean mLocalDisconnect;
    private int mPreviousCallState = -1;
    private boolean mClosing = false;

    public HfpClientConnection(Context context, BluetoothDevice device, BluetoothHeadsetClient client, BluetoothHeadsetClientCall call) {
        this.mDevice = device;
        this.mContext = context;
        this.mHeadsetProfile = client;
        if (call == null) {
            throw new IllegalStateException("Call is null");
        }
        this.mCurrentCall = call;
        handleCallChanged();
        finishInitializing();
    }

    public HfpClientConnection(Context context, BluetoothDevice device, BluetoothHeadsetClient client, Uri number) {
        this.mDevice = device;
        this.mContext = context;
        this.mHeadsetProfile = client;
        if (client == null) {
            throw new IllegalStateException("HeadsetProfile is null, returning");
        }
        BluetoothHeadsetClientCall bluetoothHeadsetClientCallDial = client.dial(device, number.getSchemeSpecificPart());
        this.mCurrentCall = bluetoothHeadsetClientCallDial;
        if (bluetoothHeadsetClientCallDial == null) {
            close(1);
            Log.e(TAG, "Failed to create the call, dial failed.");
        } else {
            setInitializing();
            setDialing();
            finishInitializing();
        }
    }

    void finishInitializing() {
        this.mClientHasEcc = HfpClientConnectionService.hasHfpClientEcc(this.mHeadsetProfile, this.mDevice);
        int i = 0;
        setAudioModeIsVoip(false);
        BluetoothHeadsetClientCall bluetoothHeadsetClientCall = this.mCurrentCall;
        if (bluetoothHeadsetClientCall != null) {
            Uri number = Uri.fromParts("tel", bluetoothHeadsetClientCall.getNumber(), null);
            setAddress(number, 1);
        }
        if (getState() == 4 || getState() == 5) {
            i = 1;
        }
        setConnectionCapabilities(i | 12354);
    }

    public UUID getUUID() {
        return this.mCurrentCall.getUUID();
    }

    public void onHfpDisconnected() {
        this.mHeadsetProfile = null;
        close(1);
    }

    public void onAdded() {
        this.mAdded = true;
    }

    public BluetoothHeadsetClientCall getCall() {
        return this.mCurrentCall;
    }

    public boolean inConference() {
        BluetoothHeadsetClientCall bluetoothHeadsetClientCall;
        return this.mAdded && (bluetoothHeadsetClientCall = this.mCurrentCall) != null && bluetoothHeadsetClientCall.isMultiParty() && getState() != 6;
    }

    public void enterPrivateMode() {
        this.mHeadsetProfile.enterPrivateMode(this.mDevice, this.mCurrentCall.getId());
        setActive();
    }

    public void updateCall(BluetoothHeadsetClientCall call) {
        if (call == null) {
            Log.e(TAG, "Updating call to a null value.");
        } else {
            this.mCurrentCall = call;
        }
    }

    public void handleCallChanged() {
        HfpClientConference conference = (HfpClientConference) getConference();
        int state = this.mCurrentCall.getState();
        switch (state) {
            case 0:
                setActive();
                if (conference != null) {
                    conference.setActive();
                }
                break;
            case 1:
            case 6:
                setOnHold();
                if (conference != null) {
                    conference.setOnHold();
                }
                break;
            case 2:
            case 3:
                setDialing();
                break;
            case 4:
            case 5:
                setRinging();
                break;
            case 7:
                int i = this.mPreviousCallState;
                if (i == 4 || i == 5) {
                    close(5);
                } else if (this.mLocalDisconnect) {
                    close(2);
                } else {
                    close(3);
                }
                break;
            default:
                Log.wtf(TAG, "Unexpected phone state " + state);
                break;
        }
        this.mPreviousCallState = state;
    }

    public synchronized void close(int cause) {
        if (this.mClosed) {
            return;
        }
        Log.d(TAG, "Setting " + this.mCurrentCall + " to disconnected " + getTelecomCallId());
        setDisconnected(new DisconnectCause(cause));
        this.mClosed = true;
        this.mCurrentCall = null;
        destroy();
    }

    public synchronized boolean isClosing() {
        return this.mClosing;
    }

    public synchronized BluetoothDevice getDevice() {
        return this.mDevice;
    }

    @Override // android.telecom.Connection
    public synchronized void onPlayDtmfTone(char c) {
        if (!this.mClosed) {
            this.mHeadsetProfile.sendDTMF(this.mDevice, (byte) c);
        }
    }

    @Override // android.telecom.Connection
    public synchronized void onDisconnect() {
        if (!this.mClosed) {
            this.mHeadsetProfile.terminateCall(this.mDevice, this.mCurrentCall);
            this.mLocalDisconnect = true;
            this.mClosing = true;
        }
    }

    @Override // android.telecom.Connection
    public void onAbort() {
        onDisconnect();
    }

    @Override // android.telecom.Connection
    public synchronized void onHold() {
        if (!this.mClosed) {
            this.mHeadsetProfile.holdCall(this.mDevice);
        }
    }

    @Override // android.telecom.Connection
    public synchronized void onUnhold() {
        if (getHfpClientConnectionService().getAllConnections().size() > 1) {
            Log.w(TAG, "Ignoring unhold; call hold on the foreground call");
        } else {
            if (!this.mClosed) {
                this.mHeadsetProfile.acceptCall(this.mDevice, 1);
            }
        }
    }

    @Override // android.telecom.Connection
    public synchronized void onAnswer() {
        if (!this.mClosed) {
            this.mHeadsetProfile.acceptCall(this.mDevice, 0);
        }
    }

    @Override // android.telecom.Connection
    public synchronized void onReject() {
        if (!this.mClosed) {
            this.mHeadsetProfile.rejectCall(this.mDevice);
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof HfpClientConnection)) {
            return false;
        }
        Uri otherAddr = ((HfpClientConnection) o).getAddress();
        return getAddress() == otherAddr || (otherAddr != null && otherAddr.equals(getAddress()));
    }

    public String toString() {
        return "HfpClientConnection{" + getAddress() + "," + stateToString(getState()) + "," + this.mCurrentCall + "}";
    }

    public void setHfpClientConnectionService(HfpClientConnectionService hfpClientConnectionService) {
        this.mHfpClientConnectionService = hfpClientConnectionService;
    }

    public HfpClientConnectionService getHfpClientConnectionService() {
        return this.mHfpClientConnectionService;
    }
}
