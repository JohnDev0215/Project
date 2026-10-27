package com.ecarx.xui.adaptapi.bt;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.bt.a2dp.IA2dp;
import com.ecarx.xui.adaptapi.bt.a2dp.IA2dpCallback;
import com.ecarx.xui.adaptapi.bt1.a2dp.IA2dpCallback;

/* JADX INFO: loaded from: classes2.dex */
public class A2dpProxy implements IA2dp {
    private static final String cTag = "A2dpProxy";
    private IA2dpCallback _cb;
    private com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp _iA2dp;
    private com.ecarx.xui.adaptapi.bt1.a2dp.IA2dpCallback _iA2dpCalback = new IA2dpCallback.Stub() { // from class: com.ecarx.xui.adaptapi.bt.A2dpProxy.1
        @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dpCallback
        public void onA2dpServiceReady() throws RemoteException {
            Log.d(A2dpProxy.cTag, "onA2dpServiceReady");
            if (A2dpProxy.this._cb != null) {
                Log.d(A2dpProxy.cTag, "onA2dpServiceReady");
                A2dpProxy.this._cb.onA2dpServiceReady();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.a2dp.IA2dpCallback
        public void onA2dpStateChanged(String str, int i, int i2) throws RemoteException {
            Log.d(A2dpProxy.cTag, "onA2dpStateChanged address =" + str + " prevState =" + i + " newState =" + i2);
            if (A2dpProxy.this._cb != null) {
                Log.d(A2dpProxy.cTag, "onA2dpStateChanged");
                A2dpProxy.this._cb.onA2dpStateChanged(str, i, i2);
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public boolean isA2dpServiceReady() {
        Log.d(cTag, "isA2dpServiceReady");
        try {
            com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
            if (iA2dp != null) {
                return iA2dp.isA2dpServiceReady();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public boolean registerA2dpCallback(com.ecarx.xui.adaptapi.bt.a2dp.IA2dpCallback iA2dpCallback) {
        Log.d(cTag, "registerA2dpCallback cb = " + iA2dpCallback);
        this._cb = iA2dpCallback;
        com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
        if (iA2dp == null) {
            return true;
        }
        try {
            boolean zIsA2dpServiceReady = iA2dp.isA2dpServiceReady();
            Log.d(cTag, "isA2dpServiceReady = " + zIsA2dpServiceReady);
            if (zIsA2dpServiceReady) {
                this._cb.onA2dpServiceReady();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public boolean unregisterA2dpCallback(com.ecarx.xui.adaptapi.bt.a2dp.IA2dpCallback iA2dpCallback) {
        Log.d(cTag, "unregisterA2dpCallback cb = " + iA2dpCallback);
        this._cb = null;
        try {
            com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
            if (iA2dp != null) {
                return iA2dp.unregisterA2dpCallback(this._iA2dpCalback);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public int getA2dpConnectionState() {
        Log.d(cTag, "getA2dpConnectionState");
        com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
        if (iA2dp == null) {
            return 0;
        }
        try {
            return iA2dp.getA2dpConnectionState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public boolean isA2dpConnected() {
        Log.d(cTag, "isA2dpConnected");
        try {
            com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
            if (iA2dp != null) {
                return iA2dp.isA2dpConnected();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public String getA2dpConnectedAddress() {
        Log.d(cTag, "getA2dpConnectedAddress");
        com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
        if (iA2dp == null) {
            return null;
        }
        try {
            return iA2dp.getA2dpConnectedAddress();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public boolean reqA2dpConnect(String str) {
        Log.d(cTag, "reqA2dpConnect address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
            if (iA2dp != null) {
                return iA2dp.reqA2dpConnect(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public boolean reqA2dpDisconnect(String str) {
        Log.d(cTag, "reqA2dpDisconnect address =" + str);
        try {
            com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
            if (iA2dp != null) {
                return iA2dp.reqA2dpDisconnect(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public void pauseA2dpRender() {
        Log.d(cTag, "pauseA2dpRender");
        com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
        if (iA2dp != null) {
            try {
                iA2dp.pauseA2dpRender();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public void startA2dpRender() {
        Log.d(cTag, "startA2dpRender");
        com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
        if (iA2dp != null) {
            try {
                iA2dp.startA2dpRender();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.a2dp.IA2dp
    public boolean setA2dpLocalVolume(float f) {
        Log.d(cTag, "setA2dpLocalVolume vol =" + f);
        try {
            com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp = this._iA2dp;
            if (iA2dp != null) {
                return iA2dp.setA2dpLocalVolume(f);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setA2dp(com.ecarx.xui.adaptapi.bt1.a2dp.IA2dp iA2dp) {
        Log.d(cTag, "setA2dp a2dp = " + iA2dp);
        this._iA2dp = iA2dp;
        if (iA2dp == null) {
            return;
        }
        try {
            iA2dp.registerA2dpCallback(this._iA2dpCalback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
