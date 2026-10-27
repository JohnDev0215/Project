package android.hardware.bydauto.xcall.tsimpl;

import android.content.Context;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.xcall.AbsBYDXCallListener;
import android.hardware.bydauto.xcall.BYDAutoXCallDevice;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import java.util.Iterator;
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
            public void onAssistChanged(int i, int i2, CarAssistPacket carAssistPacket) {
                if (XcallManagerImpl.this.mAbsBydXcallListeners.isEmpty()) {
                    return;
                }
                try {
                    XcallManagerImpl.this.callback(i, i2, carAssistPacket);
                } catch (Exception unused) {
                    Log.e(XcallManagerImpl.TAG, "callback is error");
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(XcallManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
    }

    public static XcallManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (XcallManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new XcallManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callback(int i, int i2, CarAssistPacket carAssistPacket) {
        Log.i(TAG, "XcallCallback propertyId:" + i);
        switch (i) {
            case XcallHalId.ID_CALL_TYPER /* 557872642 */:
                int callTypeR = carAssistPacket.getCallTypeR();
                Iterator<AbsBYDXCallListener> it = this.mAbsBydXcallListeners.iterator();
                while (it.hasNext()) {
                    it.next().onCallEvent(callTypeR);
                }
                break;
            case XcallHalId.ID_CALL_STATUSR /* 557872643 */:
                int callStatusR = carAssistPacket.getCallStatusR();
                Iterator<AbsBYDXCallListener> it2 = this.mAbsBydXcallListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onCallStatusChanged(callStatusR);
                }
                break;
            case XcallHalId.ID_CALL_BACKMODER /* 557872644 */:
                int callBackModeR = carAssistPacket.getCallBackModeR();
                Iterator<AbsBYDXCallListener> it3 = this.mAbsBydXcallListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onCallBackModeChanged(callBackModeR);
                }
                break;
        }
    }

    public int setCallType(int i) {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_SET);
        Log.v(TAG, "setCallType is execute");
        if (mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarAssistAdapterManager().setCallType(i);
    }

    public int setRequestType(int i) {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_SET);
        Log.v(TAG, "setRequestType is execute");
        if (mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarAssistAdapterManager().setRequestType(i);
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

    public void registerListener(AbsBYDXCallListener absBYDXCallListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe xcall listener " + absBYDXCallListener);
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_COMMON);
        Log.d(TAG, "registerListener is execute" + absBYDXCallListener);
        if (absBYDXCallListener == null) {
            return;
        }
        if (!this.mAbsBydXcallListeners.contains(absBYDXCallListener)) {
            this.mAbsBydXcallListeners.add(absBYDXCallListener);
        }
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void unregisterListener(AbsBYDXCallListener absBYDXCallListener) {
        enforcePermission(BYDAutoXCallDevice.PERMISSION_BYDAUTO_XCALL_COMMON);
        Log.d(TAG, "unregisterListener is execute" + absBYDXCallListener);
        if (absBYDXCallListener == null) {
            return;
        }
        this.mAbsBydXcallListeners.remove(absBYDXCallListener);
    }
}
