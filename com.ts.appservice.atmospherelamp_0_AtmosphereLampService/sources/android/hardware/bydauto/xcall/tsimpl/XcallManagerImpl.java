package android.hardware.bydauto.xcall.tsimpl;

import android.content.Context;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.xcall.AbsBYDXCallListener;
import android.hardware.bydauto.xcall.BYDAutoXCallDevice;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class XcallManagerImpl {
    private static final String TAG = "XcallManagerImpl";
    private static volatile TsManagerImpl mTsManagerImpl;
    private static XcallManagerImpl sInstance;
    private Context mContext;
    private final CopyOnWriteArrayList<AbsBYDXCallListener> mAbsBydXcallListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    private XcallManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_COMMON);
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.xcall.tsimpl.XcallManagerImpl.1
            public void onAssistChanged(int id, int areaId, CarAssistPacket packet) {
                if (!XcallManagerImpl.this.mAbsBydXcallListeners.isEmpty()) {
                    try {
                        XcallManagerImpl.this.callback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(XcallManagerImpl.TAG, "callback is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(XcallManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    public static XcallManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (XcallManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new XcallManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callback(int id, int areaId, CarAssistPacket packet) {
        Log.i(TAG, "XcallCallback propertyId:" + id);
        switch (id) {
            case 557872642:
                int callTypeR = packet.getCallTypeR();
                for (AbsBYDXCallListener listener : this.mAbsBydXcallListeners) {
                    listener.onCallEvent(callTypeR);
                }
                break;
            case 557872643:
                int callStatusR = packet.getCallStatusR();
                for (AbsBYDXCallListener listener2 : this.mAbsBydXcallListeners) {
                    listener2.onCallStatusChanged(callStatusR);
                }
                break;
            case 557872644:
                int callBackModeR = packet.getCallBackModeR();
                for (AbsBYDXCallListener listener3 : this.mAbsBydXcallListeners) {
                    listener3.onCallBackModeChanged(callBackModeR);
                }
                break;
        }
    }

    public int setCallType(int callType) {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_SET);
        Log.v(TAG, "setCallType is execute");
        if (mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarAssistAdapterManager().setCallType(callType);
    }

    public int setRequestType(int requestType) {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_SET);
        Log.v(TAG, "setRequestType is execute");
        if (mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarAssistAdapterManager().setRequestType(requestType);
    }

    public int getCallType() {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_GET);
        Log.v(TAG, "getCallType is execute");
        if (mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarAssistAdapterManager().getCallType();
    }

    public int getCallStatus() {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_GET);
        Log.v(TAG, "getCallStatus is execute");
        if (mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarAssistAdapterManager().getCallStatus();
    }

    public int getCallBackMode() {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_GET);
        Log.v(TAG, "getCallBackMode is execute");
        if (mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarAssistAdapterManager().getCallBackMode();
    }

    public void registerListener(AbsBYDXCallListener listener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe xcall listener " + listener);
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_COMMON);
        Log.d(TAG, "registerListener is execute" + listener);
        if (listener == null) {
            return;
        }
        if (!this.mAbsBydXcallListeners.contains(listener)) {
            this.mAbsBydXcallListeners.add(listener);
        }
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void unregisterListener(AbsBYDXCallListener listener) {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_COMMON);
        Log.d(TAG, "unregisterListener is execute" + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydXcallListeners.remove(listener);
    }
}
