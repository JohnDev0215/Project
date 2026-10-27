package com.ecarx.xui.adaptapi.bt;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.bt.pbap.IPbap;
import com.ecarx.xui.adaptapi.bt.pbap.IPbapCallback;
import com.ecarx.xui.adaptapi.bt1.pbap.IPbapCallback;
import com.ecarx.xui.adaptapi.bt1.pbap.PbapContact;

/* JADX INFO: loaded from: classes2.dex */
public class PbapProxy implements IPbap {
    private static final String cTag = "PbapProxy";
    private IPbapCallback _cb;
    private com.ecarx.xui.adaptapi.bt1.pbap.IPbap _iPbap;
    private IPbapCallback.Stub _iPbapCallback = new IPbapCallback.Stub() { // from class: com.ecarx.xui.adaptapi.bt.PbapProxy.1
        @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbapCallback
        public void onPbapServiceReady() throws RemoteException {
            Log.d(PbapProxy.cTag, "onPbapServiceReady");
            if (PbapProxy.this._cb != null) {
                Log.d(PbapProxy.cTag, "onPbapServiceReady");
                PbapProxy.this._cb.onPbapServiceReady();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbapCallback
        public void onPbapStateChanged(String str, int i, int i2, int i3, int i4) throws RemoteException {
            Log.d(PbapProxy.cTag, "onPbapStateChanged address =" + str + " prevState =" + i + " newState =" + i2 + " reason =" + i3 + " counts =" + i4);
            if (PbapProxy.this._cb != null) {
                Log.d(PbapProxy.cTag, "onPbapStateChanged");
                PbapProxy.this._cb.onPbapStateChanged(str, i, i2, i3, i4);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbapCallback
        public void retPbapDownloadedContact(PbapContact pbapContact) throws RemoteException {
            Log.d(PbapProxy.cTag, "retPbapDownloadedContact");
            if (PbapProxy.this._cb != null) {
                Log.d(PbapProxy.cTag, "retPbapDownloadedContact");
                PbapProxy.this._cb.retPbapDownloadedContact(new com.ecarx.xui.adaptapi.bt.pbap.PbapContact(pbapContact.getRemoteAddress(), pbapContact.getStorageType(), pbapContact.getFirstName(), pbapContact.getMiddleName(), pbapContact.getLastName(), pbapContact.getPhoneTypeArray(), pbapContact.getNumberArray(), pbapContact.getPhotoType(), pbapContact.getPhotoByteArray(), pbapContact.getEmailTypeArray(), pbapContact.getEmailArray(), pbapContact.getAddressType(), pbapContact.getAddressArray()));
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbapCallback
        public void retPbapDownloadedCallLog(String str, String str2, String str3, String str4, String str5, int i, String str6) throws RemoteException {
            Log.d(PbapProxy.cTag, "retPbapDownloadedCallLog address =" + str + " firstName =" + str2 + " middleName =" + str3 + " lastName =" + str4 + " number =" + str5 + " type =" + i + " timestamp =" + str6);
            if (PbapProxy.this._cb != null) {
                Log.d(PbapProxy.cTag, "retPbapDownloadedCallLog");
                PbapProxy.this._cb.retPbapDownloadedCallLog(str, str2, str3, str4, str5, i, str6);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.pbap.IPbapCallback
        public void onPbapDownloadNotify(String str, int i, int i2, int i3) throws RemoteException {
            Log.d(PbapProxy.cTag, "onPbapDownloadNotify address =" + str + " storage =" + i + " totalContacts =" + i2 + " downloadedContacts =" + i3);
            if (PbapProxy.this._cb != null) {
                Log.d(PbapProxy.cTag, "onPbapDownloadNotify");
                PbapProxy.this._cb.onPbapDownloadNotify(str, i, i2, i3);
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean isPbapServiceReady() {
        Log.d(cTag, "isPbapServiceReady");
        try {
            com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
            if (iPbap != null) {
                return iPbap.isPbapServiceReady();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean registerPbapCallback(com.ecarx.xui.adaptapi.bt.pbap.IPbapCallback iPbapCallback) {
        Log.d(cTag, "registerPbapCallback cb = " + iPbapCallback);
        this._cb = iPbapCallback;
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean unregisterPbapCallback(com.ecarx.xui.adaptapi.bt.pbap.IPbapCallback iPbapCallback) {
        Log.d(cTag, "unregisterPbapCallback cb = " + iPbapCallback);
        this._cb = null;
        try {
            com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
            if (iPbap != null) {
                return iPbap.unregisterPbapCallback(this._iPbapCallback);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public int getPbapConnectionState() {
        Log.d(cTag, "getPbapConnectionState");
        com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
        if (iPbap == null) {
            return 100;
        }
        try {
            return iPbap.getPbapConnectionState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 100;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean isPbapDownloading() {
        Log.d(cTag, "isPbapDownloading _iPbap = " + this._iPbap);
        try {
            com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
            if (iPbap != null) {
                return iPbap.isPbapDownloading();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public String getPbapDownloadingAddress() {
        Log.d(cTag, "getPbapDownloadingAddress");
        com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
        if (iPbap == null) {
            return null;
        }
        try {
            return iPbap.getPbapDownloadingAddress();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean reqPbapDownload(String str, int i, int i2) {
        Log.d(cTag, "reqPbapDownload address =" + str + " storage =" + i + " property =" + i2);
        try {
            com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
            if (iPbap != null) {
                return iPbap.reqPbapDownload(str, i, i2);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean reqPbapDownloadRange(String str, int i, int i2, int i3, int i4) {
        Log.d(cTag, "reqPbapDownloadRange address =" + str + " storage=" + i + " property =" + i2 + " startPos =" + i3 + " offset =" + i4);
        try {
            com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
            if (iPbap != null) {
                return iPbap.reqPbapDownloadRange(str, i, i2, i3, i4);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean reqPbapDownloadInterrupt(String str) {
        Log.d(cTag, "reqPbapDownloadInterrupt address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
            if (iPbap != null) {
                return iPbap.reqPbapDownloadInterrupt(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.pbap.IPbap
    public boolean setPbapDownloadNotify(int i) {
        Log.d(cTag, "setPbapDownloadNotify frequency =" + i);
        try {
            com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap = this._iPbap;
            if (iPbap != null) {
                return iPbap.setPbapDownloadNotify(i);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setPbap(com.ecarx.xui.adaptapi.bt1.pbap.IPbap iPbap) {
        Log.d(cTag, "setPbap pbap =" + iPbap);
        this._iPbap = iPbap;
        if (iPbap == null) {
            return;
        }
        try {
            iPbap.registerPbapCallback(this._iPbapCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
