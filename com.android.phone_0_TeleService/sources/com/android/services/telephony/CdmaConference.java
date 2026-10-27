package com.android.services.telephony;

import android.net.Uri;
import android.os.PersistableBundle;
import android.telecom.Connection;
import android.telecom.PhoneAccountHandle;
import android.telephony.CarrierConfigManager;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.phone.PhoneGlobals;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CdmaConference extends TelephonyConferenceBase implements Holdable {
    private int mCapabilities;
    private int mProperties;

    @Override // com.android.services.telephony.Holdable
    public boolean isChildHoldable() {
        return false;
    }

    @Override // com.android.services.telephony.Holdable
    public void setHoldable(boolean z) {
    }

    public CdmaConference(PhoneAccountHandle phoneAccountHandle) {
        super(phoneAccountHandle);
        setActive();
        this.mProperties = 2;
        setConnectionProperties(2);
    }

    public void updateCapabilities(int i) {
        setConnectionCapabilities(i | 64);
    }

    @Override // android.telecom.Conference
    public void onDisconnect() {
        Call originalCall = getOriginalCall();
        if (originalCall != null) {
            Log.d(this, "Found multiparty call to hangup for conference.", new Object[0]);
            try {
                originalCall.hangup();
            } catch (CallStateException e) {
                Log.e(this, e, "Exception thrown trying to hangup conference", new Object[0]);
            }
        }
    }

    @Override // android.telecom.Conference
    public void onSeparate(Connection connection) {
        Log.e(this, new Exception(), "Separate not supported for CDMA conference call.", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onAddConferenceParticipants(List<Uri> list) {
        Log.e(this, new Exception(), "Adding Conference Participants not supported  for CDMA conference call.", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onAnswer(int i) {
        Log.e(this, new Exception(), "Answer not supported for CDMA conference call.", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onReject() {
        Log.e(this, new Exception(), "Reject not supported for CDMA conference call.", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onHold() {
        Log.e(this, new Exception(), "Hold not supported for CDMA conference call.", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onUnhold() {
        Log.e(this, new Exception(), "Unhold not supported for CDMA conference call.", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onMerge() {
        Log.i(this, "Merging CDMA conference call.", new Object[0]);
        this.mCapabilities &= -5;
        if (isSwapSupportedAfterMerge()) {
            this.mCapabilities |= 8;
        }
        updateCapabilities(this.mCapabilities);
        sendFlash();
    }

    @Override // android.telecom.Conference
    public void onPlayDtmfTone(char c) {
        CdmaConnection firstConnection = getFirstConnection();
        if (firstConnection != null) {
            firstConnection.onPlayDtmfTone(c);
        } else {
            Log.w(this, "No CDMA connection found while trying to play dtmf tone.", new Object[0]);
        }
    }

    @Override // android.telecom.Conference
    public void onStopDtmfTone() {
        CdmaConnection firstConnection = getFirstConnection();
        if (firstConnection != null) {
            firstConnection.onStopDtmfTone();
        } else {
            Log.w(this, "No CDMA connection found while trying to stop dtmf tone.", new Object[0]);
        }
    }

    @Override // android.telecom.Conference
    public void onSwap() {
        Log.i(this, "Swapping CDMA conference call.", new Object[0]);
        sendFlash();
    }

    private void sendFlash() {
        Call originalCall = getOriginalCall();
        if (originalCall != null) {
            try {
                originalCall.getPhone().switchHoldingAndActive();
            } catch (CallStateException e) {
                Log.e(this, e, "Error while trying to send flash command.", new Object[0]);
            }
        }
    }

    private Call getOriginalCall() {
        com.android.internal.telephony.Connection originalConnection;
        List<Connection> connections = getConnections();
        if (connections.isEmpty() || (originalConnection = getOriginalConnection(connections.get(0))) == null) {
            return null;
        }
        return originalConnection.getCall();
    }

    private final boolean isSwapSupportedAfterMerge() {
        PersistableBundle config;
        PhoneGlobals phoneGlobals = PhoneGlobals.getInstance();
        if (phoneGlobals == null || (config = ((CarrierConfigManager) phoneGlobals.getSystemService("carrier_config")).getConfig()) == null) {
            return true;
        }
        boolean z = config.getBoolean("support_swap_after_merge_bool");
        Log.d(this, "Current network support swap after call merged capability is " + z, new Object[0]);
        return z;
    }

    private com.android.internal.telephony.Connection getOriginalConnection(Connection connection) {
        if (connection instanceof CdmaConnection) {
            return ((CdmaConnection) connection).getOriginalConnection();
        }
        Log.e(this, null, "Non CDMA connection found in a CDMA conference", new Object[0]);
        return null;
    }

    private CdmaConnection getFirstConnection() {
        List<Connection> connections = getConnections();
        if (connections.isEmpty()) {
            return null;
        }
        return (CdmaConnection) connections.get(0);
    }
}
