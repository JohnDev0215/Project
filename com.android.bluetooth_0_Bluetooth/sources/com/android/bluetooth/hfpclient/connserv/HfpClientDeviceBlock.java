package com.android.bluetooth.hfpclient.connserv;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccount;
import android.telecom.TelecomManager;
import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class HfpClientDeviceBlock {
    private static final boolean DBG = false;
    private static final String IS_BLUETOOTH_CALL = "android.telecom.extra.IS_BLUETOOTH_CALL";
    private HfpClientConference mConference;
    private final HfpClientConnectionService mConnServ;
    private final Map<UUID, HfpClientConnection> mConnections = new HashMap();
    private final Context mContext;
    private final BluetoothDevice mDevice;
    private BluetoothHeadsetClient mHeadsetProfile;
    private final PhoneAccount mPhoneAccount;
    private final String mTAG;
    private final TelecomManager mTelecomManager;

    HfpClientDeviceBlock(HfpClientConnectionService connServ, BluetoothDevice device, BluetoothHeadsetClient headsetProfile) {
        this.mConnServ = connServ;
        this.mContext = connServ;
        this.mDevice = device;
        this.mTAG = "HfpClientDeviceBlock." + this.mDevice.getAddress();
        this.mPhoneAccount = HfpClientConnectionService.createAccount(this.mContext, device);
        TelecomManager telecomManager = (TelecomManager) this.mContext.getSystemService("telecom");
        this.mTelecomManager = telecomManager;
        telecomManager.registerPhoneAccount(this.mPhoneAccount);
        this.mTelecomManager.enablePhoneAccount(this.mPhoneAccount.getAccountHandle(), true);
        setUserSelectedOutgoingPhoneAccount();
        this.mHeadsetProfile = headsetProfile;
        if (headsetProfile != null) {
            List<BluetoothHeadsetClientCall> calls = headsetProfile.getCurrentCalls(this.mDevice);
            if (calls == null) {
                Log.w(this.mTAG, "Got connected but calls were null, ignoring the broadcast");
                return;
            }
            for (BluetoothHeadsetClientCall call : calls) {
                handleCall(call);
            }
            return;
        }
        Log.e(this.mTAG, "headset profile is null, ignoring broadcast.");
    }

    synchronized HfpClientConnection onCreateIncomingConnection(BluetoothHeadsetClientCall call) {
        HfpClientConnection connection = this.mConnections.get(call.getUUID());
        if (connection != null) {
            connection.onAdded();
            return connection;
        }
        Log.e(this.mTAG, "Call " + call + " ignored: connection does not exist");
        return null;
    }

    HfpClientConnection onCreateOutgoingConnection(Uri address) {
        HfpClientConnection connection = buildConnection(null, address);
        if (connection != null) {
            connection.onAdded();
        }
        return connection;
    }

    synchronized HfpClientConnection onCreateUnknownConnection(BluetoothHeadsetClientCall call) {
        Uri.fromParts("tel", call.getNumber(), null);
        HfpClientConnection connection = this.mConnections.get(call.getUUID());
        if (connection != null) {
            connection.onAdded();
            return connection;
        }
        Log.e(this.mTAG, "Call " + call + " ignored: connection does not exist");
        return null;
    }

    synchronized void onConference(Connection connection1, Connection connection2) {
        if (this.mConference == null) {
            this.mConference = new HfpClientConference(this.mPhoneAccount.getAccountHandle(), this.mDevice, this.mHeadsetProfile);
        }
        if (connection1.getConference() == null) {
            this.mConference.addConnection(connection1);
        }
        if (connection2.getConference() == null) {
            this.mConference.addConnection(connection2);
        }
    }

    synchronized void cleanup() {
        Log.d(this.mTAG, "Resetting state for device " + this.mDevice);
        disconnectAll();
        this.mTelecomManager.unregisterPhoneAccount(this.mPhoneAccount.getAccountHandle());
    }

    synchronized void handleCall(BluetoothHeadsetClientCall call) {
        HfpClientConnection connection = findConnectionKey(call);
        if (connection != null && isDisconnectingToActive(connection, call)) {
            connection.close(1);
            this.mConnections.remove(call.getUUID());
            connection = null;
        }
        if (connection != null) {
            connection.updateCall(call);
            connection.handleCallChanged();
        }
        if (connection == null) {
            buildConnection(call, null);
            Bundle b = new Bundle();
            if (call.getState() == 2 || call.getState() == 3 || call.getState() == 0 || call.getState() == 1) {
                b.putParcelable("android.telecom.extra.OUTGOING_CALL_EXTRAS", call);
                b.putBoolean(IS_BLUETOOTH_CALL, true);
                this.mTelecomManager.addNewUnknownCall(this.mPhoneAccount.getAccountHandle(), b);
            } else if (call.getState() == 4 || call.getState() == 5) {
                b.putParcelable("android.telecom.extra.INCOMING_CALL_EXTRAS", call);
                b.putBoolean(IS_BLUETOOTH_CALL, true);
                b.putBoolean("android.telecom.extra.CALL_EXTERNAL_RINGER", call.isInBandRing());
                this.mTelecomManager.addNewIncomingCall(this.mPhoneAccount.getAccountHandle(), b);
            }
        } else if (call.getState() == 7) {
            this.mConnections.remove(call.getUUID());
        }
        updateConferenceableConnections();
    }

    private synchronized HfpClientConnection findConnectionKey(BluetoothHeadsetClientCall call) {
        return this.mConnections.get(call.getUUID());
    }

    private void disconnectAll() {
        for (HfpClientConnection connection : this.mConnections.values()) {
            connection.onHfpDisconnected();
        }
        this.mConnections.clear();
        HfpClientConference hfpClientConference = this.mConference;
        if (hfpClientConference != null) {
            hfpClientConference.destroy();
            this.mConference = null;
        }
    }

    private boolean isDisconnectingToActive(HfpClientConnection prevConn, BluetoothHeadsetClientCall newCall) {
        if (prevConn.isClosing() && prevConn.getCall().getState() != newCall.getState() && newCall.getState() != 7) {
            return true;
        }
        return false;
    }

    private synchronized HfpClientConnection buildConnection(BluetoothHeadsetClientCall call, Uri number) {
        HfpClientConnection connection;
        if (this.mHeadsetProfile == null) {
            Log.e(this.mTAG, "Cannot create connection for call " + call + " when Profile not available");
            return null;
        }
        if (call == null && number == null) {
            Log.e(this.mTAG, "Both call and number cannot be null.");
            return null;
        }
        if (call != null) {
            connection = new HfpClientConnection(this.mConnServ, this.mDevice, this.mHeadsetProfile, call);
        } else {
            connection = new HfpClientConnection(this.mConnServ, this.mDevice, this.mHeadsetProfile, number);
        }
        if (connection.getState() != 6) {
            this.mConnections.put(connection.getUUID(), connection);
        }
        return connection;
    }

    private void updateConferenceableConnections() {
        boolean addConf = false;
        HfpClientConference hfpClientConference = this.mConference;
        if (hfpClientConference != null) {
            for (Connection confConn : hfpClientConference.getConnections()) {
                if (!((HfpClientConnection) confConn).inConference()) {
                    this.mConference.removeConnection(confConn);
                }
            }
        }
        for (Connection otherConn : this.mConnections.values()) {
            if (((HfpClientConnection) otherConn).inConference()) {
                if (this.mConference == null) {
                    this.mConference = new HfpClientConference(this.mPhoneAccount.getAccountHandle(), this.mDevice, this.mHeadsetProfile);
                }
                if (this.mConference.addConnection(otherConn)) {
                    addConf = true;
                }
            }
        }
        HfpClientConference hfpClientConference2 = this.mConference;
        if (hfpClientConference2 != null && hfpClientConference2.getConnections().size() == 0) {
            this.mConference.setDisconnected(new DisconnectCause(2));
            this.mConference.destroy();
            this.mConference = null;
        }
        HfpClientConference hfpClientConference3 = this.mConference;
        if (hfpClientConference3 != null && addConf) {
            this.mConnServ.addConference(hfpClientConference3);
        }
    }

    public void setUserSelectedOutgoingPhoneAccount() {
        this.mTelecomManager.setUserSelectedOutgoingPhoneAccount(this.mPhoneAccount.getAccountHandle());
    }
}
