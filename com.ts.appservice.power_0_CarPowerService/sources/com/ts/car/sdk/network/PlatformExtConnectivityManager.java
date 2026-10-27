package com.ts.car.sdk.network;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.ArrayList;
import java.util.List;
import ts.car.ncm.network.IExtConnectivityManager;
import ts.car.ncm.network.IExtConnectivityServiceListener;
import ts.car.ncm.network.IfaceInfo;
import ts.car.service.ncm.IExtConnectivityManager;
import ts.car.service.ncm.IExtNetworkListener;
import ts.car.service.ncm.data.IfaceInfo;
import ts.car.service.ncm.data.IpConfiguration;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformExtConnectivityManager extends SimpleServiceBase implements IExtConnectivityManager {
    private static final int MSG_AVAILABILITY_CHANGED = 1000;
    private static final int MSG_TBOX_MOBILE_NETWORK_CHANGED = 1001;
    private static final String REMOTE_SERVICE_NAME = "ExtConnectivityService";
    private static final String SERVICE_COMPONENT = "com.ts.car.ncm/.network.ExtConnectivityService";
    private static final String TAG = PlatformExtConnectivityManager.class.getSimpleName();
    private Handler mHandler;
    private IExtConnectivityServiceListener mIExtConnectivityServiceListener;
    private final List<IExtNetworkListener> mIExtNetworkListenerList;
    private ts.car.ncm.network.IExtConnectivityManager mService;

    @PlatformConstruction
    public PlatformExtConnectivityManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        this.mIExtNetworkListenerList = new ArrayList();
        this.mIExtConnectivityServiceListener = new IExtConnectivityServiceListener.Stub() { // from class: com.ts.car.sdk.network.PlatformExtConnectivityManager.2
            @Override // ts.car.ncm.network.IExtConnectivityServiceListener
            public void onAvailabilityChanged(IfaceInfo info) {
                PlatformExtConnectivityManager.this.mHandler.obtainMessage(1000, Convert.convertAidlToIfaceInfoData(info)).sendToTarget();
            }

            @Override // ts.car.ncm.network.IExtConnectivityServiceListener
            public void onMoibleNetworkStateChanged(boolean z) {
                PlatformExtConnectivityManager.this.mHandler.obtainMessage(PlatformExtConnectivityManager.MSG_TBOX_MOBILE_NETWORK_CHANGED, Integer.valueOf(z ? 1 : 0)).sendToTarget();
            }
        };
        Log.d("MainActivity", "PlatformConstruction");
        this.mHandler = new Handler(this.mThread.getLooper()) { // from class: com.ts.car.sdk.network.PlatformExtConnectivityManager.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Log.v(PlatformExtConnectivityManager.TAG, "handleMessage msg.what:" + msg.what);
                int i = msg.what;
                if (i == 1000) {
                    ts.car.service.ncm.data.IfaceInfo info = (ts.car.service.ncm.data.IfaceInfo) msg.obj;
                    Log.d(PlatformExtConnectivityManager.TAG, "MSG_AVAILABILITY_CHANGED info:" + info);
                    synchronized (PlatformExtConnectivityManager.this.mIExtNetworkListenerList) {
                        for (IExtNetworkListener listener : PlatformExtConnectivityManager.this.mIExtNetworkListenerList) {
                            if (listener != null) {
                                listener.onAvailabilityChanged(info);
                            }
                        }
                    }
                    return;
                }
                if (i != PlatformExtConnectivityManager.MSG_TBOX_MOBILE_NETWORK_CHANGED) {
                    Log.d(PlatformExtConnectivityManager.TAG, "Unknown msg");
                    return;
                }
                boolean enabled = ((Integer) msg.obj).intValue() == 1;
                Log.d(PlatformExtConnectivityManager.TAG, "MSG_TBOX_MOBILE_NETWORK_CHANGED enabled:" + enabled);
                synchronized (PlatformExtConnectivityManager.this.mIExtNetworkListenerList) {
                    for (IExtNetworkListener listener2 : PlatformExtConnectivityManager.this.mIExtNetworkListenerList) {
                        if (listener2 != null) {
                            listener2.onMoibleNetworkStateChanged(enabled);
                        }
                    }
                }
            }
        };
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-Network_4.5.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        Log.d(TAG, "onServiceConnected.");
        ts.car.ncm.network.IExtConnectivityManager iExtConnectivityManagerAsInterface = IExtConnectivityManager.Stub.asInterface(iBinder);
        this.mService = iExtConnectivityManagerAsInterface;
        if (iExtConnectivityManagerAsInterface != null) {
            try {
                iExtConnectivityManagerAsInterface.registerListener(this.mIExtConnectivityServiceListener);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied.");
        this.mService = null;
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public List<ts.car.service.ncm.data.IfaceInfo> getIfaceInfos() throws RemoteException {
        Log.d("MainActivity", "enter getIfaceInfos");
        ts.car.ncm.network.IExtConnectivityManager iExtConnectivityManager = this.mService;
        if (iExtConnectivityManager != null) {
            try {
                List<ts.car.service.ncm.data.IfaceInfo> infos = Convert.convertAidlToIfaceInfosData(iExtConnectivityManager.getIfaceInfos());
                if (infos == null) {
                    Log.d("MainActivity", "getIfaceInfos failed!");
                } else {
                    Log.d("MainActivity", "getIfaceInfos success!");
                }
                return infos;
            } catch (RemoteException exception) {
                Log.d("MainActivity", "getIfaceInfos failed!");
                exception.printStackTrace();
                return null;
            }
        }
        Log.d("MainActivity", "mService is null!");
        return null;
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public IfaceInfo.IfaceState getIfaceState(String iface) throws RemoteException {
        ts.car.ncm.network.IExtConnectivityManager iExtConnectivityManager;
        if (iface != null && iface.trim().length() != 0 && (iExtConnectivityManager = this.mService) != null) {
            try {
                ts.car.service.ncm.data.IfaceInfo info = Convert.convertAidlToIfaceInfoData(iExtConnectivityManager.getIfaceInfo(iface));
                if (info != null) {
                    return info.ifaceState;
                }
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
        return IfaceInfo.IfaceState.UNKNOWN;
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public IpConfiguration getIpConfiguration(String iface) throws RemoteException {
        ts.car.ncm.network.IExtConnectivityManager iExtConnectivityManager;
        if (iface != null && iface.trim().length() != 0 && (iExtConnectivityManager = this.mService) != null) {
            try {
                ts.car.service.ncm.data.IfaceInfo info = Convert.convertAidlToIfaceInfoData(iExtConnectivityManager.getIfaceInfo(iface));
                if (info != null) {
                    return info.ipConfig;
                }
                return null;
            } catch (RemoteException exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public void registerListener(IExtNetworkListener listener) throws RemoteException {
        if (listener == null) {
            throw new IllegalArgumentException("ExtNetworkListener must not be null");
        }
        synchronized (this.mIExtNetworkListenerList) {
            if (!this.mIExtNetworkListenerList.contains(listener)) {
                this.mIExtNetworkListenerList.add(listener);
            }
        }
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public void unregisterListener(IExtNetworkListener listener) throws RemoteException {
        if (listener == null) {
            throw new IllegalArgumentException("ExtNetworkListener must not be null");
        }
        synchronized (this.mIExtNetworkListenerList) {
            this.mIExtNetworkListenerList.remove(listener);
        }
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public boolean enableInterface(String ifaceName, boolean enable) {
        ts.car.ncm.network.IExtConnectivityManager iExtConnectivityManager;
        if (ifaceName != null && ifaceName.trim().length() != 0 && (iExtConnectivityManager = this.mService) != null) {
            try {
                return iExtConnectivityManager.enableInterface(ifaceName, enable);
            } catch (RemoteException exception) {
                exception.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public boolean enableMobileNetwork(boolean enable) {
        ts.car.ncm.network.IExtConnectivityManager iExtConnectivityManager = this.mService;
        if (iExtConnectivityManager != null) {
            try {
                return iExtConnectivityManager.enableMobileNetwork(enable);
            } catch (RemoteException execption) {
                execption.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override // ts.car.service.ncm.IExtConnectivityManager
    public boolean getMobileNetworkEnable() {
        ts.car.ncm.network.IExtConnectivityManager iExtConnectivityManager = this.mService;
        if (iExtConnectivityManager != null) {
            try {
                return iExtConnectivityManager.getMobileNetworkEnable();
            } catch (RemoteException excption) {
                excption.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
