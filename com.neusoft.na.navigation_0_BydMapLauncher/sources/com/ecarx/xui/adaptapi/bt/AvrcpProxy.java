package com.ecarx.xui.adaptapi.bt;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp;
import com.ecarx.xui.adaptapi.bt.avrcp.IAvrcpCallback;
import com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback;

/* JADX INFO: loaded from: classes2.dex */
public class AvrcpProxy implements IAvrcp {
    private static final String cTag = "AvrcpProxy";
    private IAvrcpCallback _cb;
    private com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp _iAvrcp;
    private IAvrcpCallback.Stub _iAvrcpCallback = new IAvrcpCallback.Stub() { // from class: com.ecarx.xui.adaptapi.bt.AvrcpProxy.1
        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcpServiceReady() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcpServiceReady");
            if (AvrcpProxy.this._cb != null) {
                Log.d(AvrcpProxy.cTag, "onAvrcpServiceReady");
                AvrcpProxy.this._cb.onAvrcpServiceReady();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcpStateChanged(String str, int i, int i2) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcpStateChanged address=" + str + " prevState=" + i + " newState=" + i2);
            if (AvrcpProxy.this._cb != null) {
                Log.d(AvrcpProxy.cTag, "onAvrcpStateChanged");
                AvrcpProxy.this._cb.onAvrcpStateChanged(str, i, i2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp13CapabilitiesSupportEvent(byte[] bArr) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp13CapabilitiesSupportEvent");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp13CapabilitiesSupportEvent(bArr);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp13PlayerSettingAttributesList(byte[] bArr) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp13PlayerSettingAttributesList");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp13PlayerSettingAttributesList(bArr);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp13PlayerSettingValuesList(byte b, byte[] bArr) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp13PlayerSettingValuesList");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp13PlayerSettingValuesList(b, bArr);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp13PlayerSettingCurrentValues(byte[] bArr, byte[] bArr2) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp13PlayerSettingCurrentValues");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp13PlayerSettingCurrentValues(bArr, bArr2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp13SetPlayerSettingValueSuccess() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp13SetPlayerSettingValueSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp13SetPlayerSettingValueSuccess();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp13ElementAttributesPlaying(int[] iArr, String[] strArr) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp13ElementAttributesPlaying id=" + iArr[0] + " text=" + strArr[0]);
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp13ElementAttributesPlaying(iArr, strArr);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp13PlayStatus(long j, long j2, byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp13PlayStatus statusId = " + ((int) b));
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp13PlayStatus(j, j2, b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13RegisterEventWatcherSuccess(byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13RegisterEventWatcherSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13RegisterEventWatcherSuccess(b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13RegisterEventWatcherFail(byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13RegisterEventWatcherFail");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13RegisterEventWatcherFail(b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventPlaybackStatusChanged(byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventPlaybackStatusChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventPlaybackStatusChanged(b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventTrackChanged(long j) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventTrackChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventTrackChanged(j);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventTrackReachedEnd() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventTrackReachedEnd");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventTrackReachedEnd();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventTrackReachedStart() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventTrackReachedStart");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventTrackReachedStart();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventPlaybackPosChanged(long j) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventPlaybackPosChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventPlaybackPosChanged(j);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventBatteryStatusChanged(byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventBatteryStatusChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventBatteryStatusChanged(b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventSystemStatusChanged(byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventSystemStatusChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventSystemStatusChanged(b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp13EventPlayerSettingChanged(byte[] bArr, byte[] bArr2) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp13EventPlayerSettingChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp13EventPlayerSettingChanged(bArr, bArr2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp14EventNowPlayingContentChanged() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp14EventNowPlayingContentChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp14EventNowPlayingContentChanged();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp14EventAvailablePlayerChanged() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp14EventAvailablePlayerChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp14EventAvailablePlayerChanged();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp14EventAddressedPlayerChanged(int i, int i2) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp14EventAddressedPlayerChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp14EventAddressedPlayerChanged(i, i2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp14EventUidsChanged(int i) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp14EventUidsChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp14EventUidsChanged(i);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcp14EventVolumeChanged(byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcp14EventVolumeChanged");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcp14EventVolumeChanged(b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14SetAddressedPlayerSuccess() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14SetAddressedPlayerSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14SetAddressedPlayerSuccess();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14SetBrowsedPlayerSuccess(String[] strArr, int i, long j) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14SetBrowsedPlayerSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14SetBrowsedPlayerSuccess(strArr, i, j);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14FolderItems(int i, long j) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14FolderItems");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14FolderItems(i, j);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14MediaItems(int i, long j) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14MediaItems");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14MediaItems(i, j);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14ChangePathSuccess(long j) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14ChangePathSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14ChangePathSuccess(j);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14ItemAttributes(int[] iArr, String[] strArr) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14ItemAttributes id=" + iArr[0] + " texts=" + strArr[0]);
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14ItemAttributes(iArr, strArr);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14PlaySelectedItemSuccess() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14PlaySelectedItemSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14PlaySelectedItemSuccess();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14SearchResult(int i, long j) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14SearchResult");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14SearchResult(i, j);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14AddToNowPlayingSuccess() throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14AddToNowPlayingSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14AddToNowPlayingSuccess();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void retAvrcp14SetAbsoluteVolumeSuccess(byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "retAvrcp14SetAbsoluteVolumeSuccess");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.retAvrcp14SetAbsoluteVolumeSuccess(b);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
        public void onAvrcpErrorResponse(int i, int i2, byte b) throws RemoteException {
            Log.d(AvrcpProxy.cTag, "onAvrcpErrorResponse");
            if (AvrcpProxy.this._cb != null) {
                AvrcpProxy.this._cb.onAvrcpErrorResponse(i, i2, b);
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean isAvrcpServiceReady() {
        Log.d(cTag, "isAvrcpServiceReady");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.isAvrcpServiceReady();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean registerAvrcpCallback(com.ecarx.xui.adaptapi.bt.avrcp.IAvrcpCallback iAvrcpCallback) {
        Log.d(cTag, "registerAvrcpCallback cb = " + iAvrcpCallback);
        this._cb = iAvrcpCallback;
        com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
        if (iAvrcp == null) {
            return true;
        }
        try {
            boolean zIsAvrcpServiceReady = iAvrcp.isAvrcpServiceReady();
            Log.d(cTag, "isAvrcpServiceReady = " + zIsAvrcpServiceReady);
            if (!zIsAvrcpServiceReady) {
                return true;
            }
            this._cb.onAvrcpServiceReady();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean unregisterAvrcpCallback(com.ecarx.xui.adaptapi.bt.avrcp.IAvrcpCallback iAvrcpCallback) {
        Log.d(cTag, "unregisterAvrcpCallback cb = " + iAvrcpCallback);
        this._cb = null;
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public int getAvrcpConnectionState() {
        Log.d(cTag, "getAvrcpConnectionState");
        com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
        if (iAvrcp == null) {
            return 0;
        }
        try {
            return iAvrcp.getAvrcpConnectionState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean isAvrcpConnected() {
        Log.d(cTag, "isAvrcpConnected");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.isAvrcpConnected();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public String getAvrcpConnectedAddress() {
        Log.d(cTag, "getAvrcpConnectedAddress");
        com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
        if (iAvrcp == null) {
            return null;
        }
        try {
            return iAvrcp.getAvrcpConnectedAddress();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpConnect(String str) {
        Log.d(cTag, "reqAvrcpConnect address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpConnect(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpDisconnect(String str) {
        Log.d(cTag, "reqAvrcpDisconnect address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpDisconnect(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean isAvrcp13Supported(String str) {
        Log.d(cTag, "isAvrcp13Supported address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.isAvrcp13Supported(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean isAvrcp14Supported(String str) {
        Log.d(cTag, "isAvrcp14Supported address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.isAvrcp14Supported(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpPlay() {
        Log.d(cTag, "reqAvrcpPlay");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpPlay();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpStop() {
        Log.d(cTag, "reqAvrcpStop");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpStop();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpPause() {
        Log.d(cTag, "reqAvrcpPause");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpPause();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpForward() {
        Log.d(cTag, "reqAvrcpForward");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpForward();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpBackward() {
        Log.d(cTag, "reqAvrcpBackward");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpBackward();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpVolumeUp() {
        Log.d(cTag, "reqAvrcpVolumeUp");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpVolumeUp();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpVolumeDown() {
        Log.d(cTag, "reqAvrcpVolumeDown");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpVolumeDown();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpStartFastForward() {
        Log.d(cTag, "reqAvrcpStartFastForward");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpStartFastForward();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpStopFastForward() {
        Log.d(cTag, "reqAvrcpStopFastForward");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpStopFastForward();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpStartRewind() {
        Log.d(cTag, "reqAvrcpStartRewind");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpStartRewind();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpStopRewind() {
        Log.d(cTag, "reqAvrcpStopRewind");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpStopRewind();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13GetCapabilitiesSupportEvent() {
        Log.d(cTag, "reqAvrcp13GetCapabilitiesSupportEvent");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13GetCapabilitiesSupportEvent();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13GetPlayerSettingAttributesList() {
        Log.d(cTag, "reqAvrcp13GetPlayerSettingAttributesList");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13GetPlayerSettingAttributesList();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13GetPlayerSettingValuesList(byte b) {
        Log.d(cTag, "reqAvrcp13GetPlayerSettingValuesList");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13GetPlayerSettingValuesList(b);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13GetPlayerSettingCurrentValues() {
        Log.d(cTag, "reqAvrcp13GetPlayerSettingCurrentValues");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13GetPlayerSettingCurrentValues();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13SetPlayerSettingValue(byte b, byte b2) {
        Log.d(cTag, "reqAvrcp13SetPlayerSettingValue");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13SetPlayerSettingValue(b, b2);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13GetElementAttributesPlaying() {
        Log.d(cTag, "reqAvrcp13GetElementAttributesPlaying");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13GetElementAttributesPlaying();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13GetPlayStatus() {
        Log.d(cTag, "reqAvrcp13GetPlayStatus");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13GetPlayStatus();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpRegisterEventWatcher(byte b, long j) {
        Log.d(cTag, "reqAvrcpRegisterEventWatcher");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpRegisterEventWatcher(b, j);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpUnregisterEventWatcher(byte b) {
        Log.d(cTag, "reqAvrcpUnregisterEventWatcher");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpUnregisterEventWatcher(b);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13NextGroup() {
        Log.d(cTag, "reqAvrcp13NextGroup");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13NextGroup();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp13PreviousGroup() {
        Log.d(cTag, "reqAvrcp13PreviousGroup");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp13PreviousGroup();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean isAvrcp14BrowsingChannelEstablished() {
        Log.d(cTag, "isAvrcp14BrowsingChannelEstablished");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.isAvrcp14BrowsingChannelEstablished();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14SetAddressedPlayer(int i) {
        Log.d(cTag, "reqAvrcp14SetAddressedPlayer playId =" + i);
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14SetAddressedPlayer(i);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14SetBrowsedPlayer(int i) {
        Log.d(cTag, "getAvrcpConnectionState");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14SetBrowsedPlayer(i);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14GetFolderItems(byte b) {
        Log.d(cTag, "reqAvrcp14GetFolderItems scopeId =" + ((int) b));
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14GetFolderItems(b);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14ChangePath(int i, long j, byte b) {
        Log.d(cTag, "reqAvrcp14ChangePath");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14ChangePath(i, j, b);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14GetItemAttributes(byte b, int i, long j) {
        Log.d(cTag, "reqAvrcp14GetItemAttributes");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14GetItemAttributes(b, i, j);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14PlaySelectedItem(byte b, int i, long j) {
        Log.d(cTag, "reqAvrcp14PlaySelectedItem");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14PlaySelectedItem(b, i, j);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14Search(String str) {
        Log.d(cTag, "reqAvrcp14Search text =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14Search(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14AddToNowPlaying(byte b, int i, long j) {
        Log.d(cTag, "reqAvrcp14AddToNowPlaying");
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14AddToNowPlaying(b, i, j);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcp14SetAbsoluteVolume(byte b) {
        Log.d(cTag, "reqAvrcp14SetAbsoluteVolume volume =" + ((int) b));
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcp14SetAbsoluteVolume(b);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp
    public boolean reqAvrcpQueryVersion(String str) {
        Log.d(cTag, "reqAvrcpQueryVersion address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp = this._iAvrcp;
            if (iAvrcp != null) {
                return iAvrcp.reqAvrcpQueryVersion(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setAvrcp(com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp iAvrcp) {
        Log.d(cTag, "setAvrcp avrcp =" + iAvrcp);
        this._iAvrcp = iAvrcp;
        if (iAvrcp == null) {
            return;
        }
        try {
            iAvrcp.registerAvrcpCallback(this._iAvrcpCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
