package com.ecarx.xui.adaptapi.bt;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.bt.hfp.IHfp;
import com.ecarx.xui.adaptapi.bt.hfp.IHfpCallback;
import com.ecarx.xui.adaptapi.bt1.hfp.HfpClientCall;
import com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneProxy implements IHfp {
    private static final String cTag = "PhoneProxy";
    private IHfpCallback _cb;
    private com.ecarx.xui.adaptapi.bt1.hfp.IHfp _iHfp;
    private IHfpCallback.Stub _iHfpCallback = new IHfpCallback.Stub() { // from class: com.ecarx.xui.adaptapi.bt.PhoneProxy.1
        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpServiceReady() throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpServiceReady _cb = " + PhoneProxy.this._cb);
            if (PhoneProxy.this._cb != null) {
                Log.d(PhoneProxy.cTag, "onHfpServiceReady");
                PhoneProxy.this._cb.onHfpServiceReady();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpStateChanged(String str, int i, int i2) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpStateChanged address =" + str + " prevState=" + i + " newState=" + i2);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpStateChanged(str, i, i2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpAudioStateChanged(String str, int i, int i2) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpAudioStateChanged address =" + str + " prevState=" + i + " newState=" + i2);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpAudioStateChanged(str, i, i2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpVoiceDial(String str, boolean z) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpVoiceDial address =" + str + " isVoiceDialOn=" + z);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpVoiceDial(str, z);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpErrorResponse(String str, int i) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpErrorResponse address =" + str + " code=" + i);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpErrorResponse(str, i);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpRemoteTelecomService(String str, boolean z) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpRemoteTelecomService address =" + str + " isTelecomServiceOn=" + z);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpRemoteTelecomService(str, z);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpRemoteRoamingStatus(String str, boolean z) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpRemoteRoamingStatus address =" + str + " isRoamingOn=" + z);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpRemoteRoamingStatus(str, z);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpRemoteBatteryIndicator(String str, int i, int i2, int i3) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpRemoteBatteryIndicator address =" + str + " currentValue=" + i + " maxValue=" + i2 + " minValue=" + i3);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpRemoteBatteryIndicator(str, i, i2, i3);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpRemoteSignalStrength(String str, int i, int i2, int i3) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpRemoteSignalStrength address =" + str + " currentStrength=" + i + " maxStrength=" + i2 + " minStrength=" + i3);
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpRemoteSignalStrength(str, i, i2, i3);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback
        public void onHfpCallChanged(String str, HfpClientCall hfpClientCall) throws RemoteException {
            Log.d(PhoneProxy.cTag, "onHfpCallChanged address =" + str);
            com.ecarx.xui.adaptapi.bt.hfp.HfpClientCall hfpClientCall2 = new com.ecarx.xui.adaptapi.bt.hfp.HfpClientCall(hfpClientCall.getId(), hfpClientCall.getState(), hfpClientCall.getNumber(), hfpClientCall.isMultiParty(), hfpClientCall.isOutgoing());
            if (PhoneProxy.this._cb != null) {
                PhoneProxy.this._cb.onHfpCallChanged(str, hfpClientCall2);
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpConnect(String str) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpDisconnect(String str) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean isHfpServiceReady() {
        Log.d(cTag, "isHfpServiceReady");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.isHfpServiceReady();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean registerHfpCallback(com.ecarx.xui.adaptapi.bt.hfp.IHfpCallback iHfpCallback) {
        Log.d(cTag, "registerHfpCallback cb " + iHfpCallback);
        this._cb = iHfpCallback;
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return true;
        }
        try {
            boolean zIsHfpServiceReady = iHfp.isHfpServiceReady();
            Log.d(cTag, "isHfpServiceReady = " + zIsHfpServiceReady);
            if (zIsHfpServiceReady) {
                this._cb.onHfpServiceReady();
            }
            boolean zIsHfpConnected = this._iHfp.isHfpConnected();
            Log.d(cTag, "isHfpConnected = " + zIsHfpConnected);
            if (!zIsHfpConnected) {
                return true;
            }
            this._cb.onHfpStateChanged(this._iHfp.getHfpConnectedAddress(), 110, 140);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean unregisterHfpCallback(com.ecarx.xui.adaptapi.bt.hfp.IHfpCallback iHfpCallback) {
        Log.d(cTag, "unregisterHfpCallback cb " + iHfpCallback);
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.unregisterHfpCallback(this._iHfpCallback);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            this._cb = null;
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public int getHfpConnectionState() {
        Log.d(cTag, "getHfpConnectionState");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return 100;
        }
        try {
            return iHfp.getHfpConnectionState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 100;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean isHfpConnected() {
        Log.d(cTag, "isHfpConnected");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.isHfpConnected();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public String getHfpConnectedAddress() {
        Log.d(cTag, "getHfpConnectedAddress");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return null;
        }
        try {
            return iHfp.getHfpConnectedAddress();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public int getHfpAudioConnectionState() {
        Log.d(cTag, "getHfpAudioConnectionState");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return 100;
        }
        try {
            return iHfp.getHfpAudioConnectionState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 100;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public int getHfpRemoteSignalStrength() {
        Log.d(cTag, "getHfpRemoteSignalStrength");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return 0;
        }
        try {
            return iHfp.getHfpRemoteSignalStrength();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public List<com.ecarx.xui.adaptapi.bt.hfp.HfpClientCall> getHfpCallList() {
        Log.d(cTag, "getHfpCallList");
        try {
            List<HfpClientCall> hfpCallList = this._iHfp.getHfpCallList();
            ArrayList arrayList = new ArrayList();
            for (HfpClientCall hfpClientCall : hfpCallList) {
                arrayList.add(new com.ecarx.xui.adaptapi.bt.hfp.HfpClientCall(hfpClientCall.getId(), hfpClientCall.getState(), hfpClientCall.getNumber(), hfpClientCall.isMultiParty(), hfpClientCall.isOutgoing()));
            }
            return arrayList;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean isHfpRemoteOnRoaming() {
        Log.d(cTag, "isHfpRemoteOnRoaming");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.isHfpRemoteOnRoaming();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public int getHfpRemoteBatteryIndicator() {
        Log.d(cTag, "getHfpRemoteBatteryIndicator");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return 0;
        }
        try {
            return iHfp.getHfpRemoteBatteryIndicator();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean isHfpRemoteTelecomServiceOn() {
        Log.d(cTag, "isHfpRemoteTelecomServiceOn");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.isHfpRemoteTelecomServiceOn();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean isHfpRemoteVoiceDialOn() {
        Log.d(cTag, "isHfpRemoteVoiceDialOn");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.isHfpRemoteVoiceDialOn();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpDialCall(String str) {
        Log.d(cTag, "reqHfpDialCall number" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpDialCall(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpReDial() {
        Log.d(cTag, "reqHfpReDial");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpReDial();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpMemoryDial(String str) {
        Log.d(cTag, "reqHfpMemoryDial index " + str);
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpMemoryDial(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpAnswerCall(int i) {
        Log.d(cTag, "reqHfpAnswerCall flag " + i);
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpAnswerCall(i);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpRejectIncomingCall() {
        Log.d(cTag, "reqHfpRejectIncomingCall");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpRejectIncomingCall();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpTerminateCurrentCall() {
        Log.d(cTag, "reqHfpTerminateCurrentCall");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpTerminateCurrentCall();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpSendDtmf(String str) {
        Log.d(cTag, "reqHfpSendDtmf number " + str);
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpSendDtmf(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpAudioTransferToCarkit() {
        Log.d(cTag, "reqHfpAudioTransferToCarkit");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpAudioTransferToCarkit();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpAudioTransferToPhone() {
        Log.d(cTag, "reqHfpAudioTransferToPhone");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpAudioTransferToPhone();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public String getHfpRemoteNetworkOperator() {
        Log.d(cTag, "getHfpRemoteNetworkOperator");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return null;
        }
        try {
            return iHfp.getHfpRemoteNetworkOperator();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public String getHfpRemoteSubscriberNumber() {
        Log.d(cTag, "getHfpRemoteSubscriberNumber");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp == null) {
            return null;
        }
        try {
            return iHfp.getHfpRemoteSubscriberNumber();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean reqHfpVoiceDial(boolean z) {
        Log.d(cTag, "reqHfpVoiceDial " + z);
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.reqHfpVoiceDial(z);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public void pauseHfpRender() {
        Log.d(cTag, "pauseHfpRender");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp != null) {
            try {
                iHfp.pauseHfpRender();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public void startHfpRender() {
        Log.d(cTag, "startHfpRender");
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp != null) {
            try {
                iHfp.startHfpRender();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean isHfpMicMute() {
        Log.d(cTag, "isHfpMicMute");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.isHfpMicMute();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public void muteHfpMic(boolean z) {
        Log.d(cTag, "muteHfpMic mute " + z);
        com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
        if (iHfp != null) {
            try {
                iHfp.muteHfpMic(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.hfp.IHfp
    public boolean isHfpInBandRingtoneSupport() {
        Log.d(cTag, "isHfpInBandRingtoneSupport");
        try {
            com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp = this._iHfp;
            if (iHfp != null) {
                return iHfp.isHfpInBandRingtoneSupport();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public com.ecarx.xui.adaptapi.bt1.hfp.IHfp getIHfp() {
        return this._iHfp;
    }

    public void setIHfp(com.ecarx.xui.adaptapi.bt1.hfp.IHfp iHfp) {
        Log.d(cTag, "setIHfp hfp = " + iHfp);
        this._iHfp = iHfp;
        if (iHfp == null) {
            return;
        }
        try {
            iHfp.registerHfpCallback(this._iHfpCallback);
            if (this._cb != null) {
                boolean zIsHfpServiceReady = this._iHfp.isHfpServiceReady();
                Log.d(cTag, "isHfpServiceReady_setIHfp = " + zIsHfpServiceReady);
                if (zIsHfpServiceReady) {
                    this._cb.onHfpServiceReady();
                }
                boolean zIsHfpConnected = this._iHfp.isHfpConnected();
                Log.d(cTag, "isHfpConnected_setIHfp = " + zIsHfpConnected);
                if (zIsHfpConnected) {
                    this._cb.onHfpStateChanged(this._iHfp.getHfpConnectedAddress(), 110, 140);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
