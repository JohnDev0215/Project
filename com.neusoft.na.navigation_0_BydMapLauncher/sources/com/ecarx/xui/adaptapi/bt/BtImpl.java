package com.ecarx.xui.adaptapi.bt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.bt.a2dp.IA2dp;
import com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp;
import com.ecarx.xui.adaptapi.bt.gattserver.IGattServer;
import com.ecarx.xui.adaptapi.bt.hfp.IHfp;
import com.ecarx.xui.adaptapi.bt.hid.IHid;
import com.ecarx.xui.adaptapi.bt.map.IMap;
import com.ecarx.xui.adaptapi.bt.pbap.IPbap;
import com.ecarx.xui.adaptapi.bt.settings.IBtSettings;
import com.ecarx.xui.adaptapi.bt.spp.ISpp;
import com.neusoft.geely.btphone.service.IBtPhoneManager;

/* JADX INFO: loaded from: classes2.dex */
public class BtImpl extends Bt {
    private static final String cTag = "BtImpl";
    private A2dpProxy _a2dpProxy;
    private AvrcpProxy _avrcpProxy;
    private Context _context;
    private IBtPhoneManager _iBtPhoneManager;
    private PbapProxy _pbapProxy;
    private PhoneProxy _phoneProxy;
    private ServiceConnection _serviceConnection = new ServiceConnection() { // from class: com.ecarx.xui.adaptapi.bt.BtImpl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(BtImpl.cTag, "onServiceConnected binder = " + iBinder);
            BtImpl.this._iBtPhoneManager = IBtPhoneManager.Stub.asInterface(iBinder);
            Log.d(BtImpl.cTag, "onServiceConnected binder = " + BtImpl.this._iBtPhoneManager.asBinder());
            try {
                BtImpl.this._phoneProxy.setIHfp(BtImpl.this._iBtPhoneManager.getHfp());
                BtImpl.this._pbapProxy.setPbap(BtImpl.this._iBtPhoneManager.getPbap());
                BtImpl.this._a2dpProxy.setA2dp(BtImpl.this._iBtPhoneManager.getA2dp());
                BtImpl.this._avrcpProxy.setAvrcp(BtImpl.this._iBtPhoneManager.getAvrcp());
                BtImpl.this._settingProxy.setBtSetting(BtImpl.this._iBtPhoneManager.getBtSettings());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(BtImpl.cTag, "onServiceDisconnected");
            BtImpl.this._iBtPhoneManager = null;
        }
    };
    private SettingProxy _settingProxy;

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IGattServer getGattServer() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IHid getHid() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IMap getMap() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public ISpp getSpp() {
        return null;
    }

    public BtImpl(Context context) {
        Log.d(cTag, cTag);
        this._context = context;
        init();
    }

    private void init() {
        this._phoneProxy = new PhoneProxy();
        this._settingProxy = new SettingProxy(this);
        this._pbapProxy = new PbapProxy();
        this._a2dpProxy = new A2dpProxy();
        this._avrcpProxy = new AvrcpProxy();
        bindService();
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IA2dp getA2dp() {
        return this._a2dpProxy;
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IAvrcp getAvrcp() {
        return this._avrcpProxy;
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IHfp getHfp() {
        return this._phoneProxy;
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IPbap getPbap() {
        return this._pbapProxy;
    }

    @Override // com.ecarx.xui.adaptapi.bt.Bt
    public IBtSettings getBtSettings() {
        return this._settingProxy;
    }

    public void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.neusoft.geely.btphone.nf", "com.neusoft.geely.btphone.nf.BtManagerService"));
        Log.d(cTag, "bindService result = " + this._context.bindService(intent, this._serviceConnection, 1));
    }
}
