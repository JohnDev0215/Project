package android.hardware.bydauto.yun.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.yun.AbsBYDAutoYunListener;
import android.hardware.bydauto.yun.ActivateInfo;
import android.hardware.bydauto.yun.ActivateInfoV2;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.hardware.bydauto.yun.CanInfo;
import android.provider.Settings;
import com.ts.lib.caradapter.cloud.CarCloudPacket;
import com.ts.lib.caradapter.cloud.CarCollectDataPacket;
import com.ts.lib.caradapter.cloud.CarRemotePacket;
import com.ts.lib.caradapter.cloud.OnCloudChangedListener;
import com.ts.ota.otasdkmgr.DigitalKeyBeanSdk;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class YunManagerImpl {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_YunManagerImpl";
    protected static final String TAG = "YunManagerImpl";
    private static volatile YunManagerImpl sInstance;
    private AbsBYDAutoYunListener mAbsBYDAutoYunListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private OtaSdkManager mOtaSdkManager;
    private TsManagerImpl mTsManagerImpl;
    private boolean mIgnoreBydPermission = false;
    private List<CanInfo> mAllSubscribeList = new ArrayList();
    IAbsBYDTBoxListener.Stub bydTboxListener = new IAbsBYDTBoxListener.Stub() { // from class: android.hardware.bydauto.yun.tsimpl.YunManagerImpl.2
        public void onDeleteAllNfcKeyResult(int i, int i2) {
        }

        public void onDeleteNfcKeyByKeyNoResult(String str, int i) {
        }

        public void onDeleteNfcKeyResult(int i, int i2) {
        }

        public void onDeleteOwnIphoneKeyResult(String str, int i) {
        }

        public void onKeyListPageRefresh() {
        }

        public void onKeyListResponse(int i, List<DigitalKeyBeanSdk> list) {
        }

        public void onMobileNetworkStateChanged(int i) {
        }

        public void onNfcOperationCommand(int i) {
        }

        public void onAppActivateResult(int i, int i2) {
            if (YunManagerImpl.this.mAbsBYDAutoYunListener != null) {
                YunManagerImpl.this.mAbsBYDAutoYunListener.onAppActivateResult(i, i2);
            }
        }
    };

    YunManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.v(TAG, "YunManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
        this.mTsManagerImpl.setOnCloudChangedListener(new OnCloudChangedListener() { // from class: android.hardware.bydauto.yun.tsimpl.YunManagerImpl.1
            public void onCollectDataChanged(int i2, int i3, CarCollectDataPacket carCollectDataPacket) {
            }

            public void onRemoteChanged(int i2, int i3, CarRemotePacket carRemotePacket) {
            }

            public void onCloudChanged(int i2, int i3, CarCloudPacket carCloudPacket) {
                CanInfo canInfoPacket2convert = YunManagerImpl.this.packet2convert(carCloudPacket, i3);
                if (YunManagerImpl.this.mAbsBYDAutoYunListener != null) {
                    YunManagerImpl.this.mAbsBYDAutoYunListener.onChange(canInfoPacket2convert);
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(YunManagerImpl.TAG, "cloud call back state :" + z);
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static YunManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (YunManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new YunManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public void subscribe(List<CanInfo> list) {
        Log.v(TAG, "subscribe is run");
    }

    public void unSubscribe() {
        Log.v(TAG, "unSubscribe is run");
    }

    public void getCanBytesSync(int i, int i2) {
        Log.v(TAG, "getCanBytesSync is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getCanBytesSync mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().getCanBytesSync(i, i2);
        }
    }

    public byte[] getCanBytes(int i, int i2) {
        Log.v(TAG, "getCanBytes is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getCanBytes mForTestValue is" + this.mForTest + " execute return");
            return new byte[0];
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return new byte[0];
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getCanBytes(i, i2);
    }

    public void remoteControl(int i) {
        Log.v(TAG, "remoteControl is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteControl mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteControl(i);
        }
    }

    public void remoteUnlockFourDoors() {
        Log.v(TAG, "remoteUnlockFourDoors is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteUnlockFourDoors mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteUnlockFourDoors();
        }
    }

    public void remoteAcControl(int i, int i2, int i3, int i4) {
        Log.v(TAG, "remoteAcControlOpen is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteAcControlOpen mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteAcControl(i, i2, i3, i4);
        }
    }

    public void remoteSeatControl(int i, int i2, int i3) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteSeatControl(i, i2, i3);
        }
    }

    public void remoteSeatControl(int i, int i2, int i3, int i4, int i5) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteSeatControl(i, i2, i3, i4, i5);
        }
    }

    public void remoteSteeringHeatingControl(int i, int i2) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteSteeringHeatingControl(i, i2);
        }
    }

    public byte getWaterThermometer() {
        Log.v(TAG, "getWaterThermometer is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWaterThermometer mForTestValue is" + this.mForTest + " execute return");
            return (byte) 0;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getWaterThermometer();
    }

    public int getRemainBattery() {
        Log.v(TAG, "getRemainBattery is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemainBattery mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getRemainBattery();
    }

    public byte getVehicleSpeed() {
        Log.v(TAG, "getVehicleSpeed is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getVehicleSpeed mForTestValue is" + this.mForTest + " execute return");
            return (byte) 0;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return (byte) 0;
        }
        return this.mTsManagerImpl.getCarCloudAdapterManager().getVehicleSpeed();
    }

    public void setAppActivateInfo(ActivateInfo activateInfo) {
        Log.v(TAG, "setAppActivateInfo is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setAppActivateInfo mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        ActivateInfo.ProviderType supplierType = activateInfo.getSupplierType();
        ActivateInfo.ProviderName supplierName = activateInfo.getSupplierName();
        ActivateInfo.ActiveState activateState = activateInfo.getActivateState();
        ActivateInfo.NationalRegion activateCountry = activateInfo.getActivateCountry();
        ActivateInfo.Region activateRegion = activateInfo.getActivateRegion();
        this.mOtaSdkManager.setAppActivateInfo(supplierType != null ? supplierType.toByte() : (byte) 0, activateInfo.getAutoVinResult(), supplierName != null ? supplierName.toByte() : (byte) 0, activateState != null ? activateState.toByte() : (byte) 0, activateInfo.getActivateTime(), activateInfo.getLicenseTime(), activateCountry != null ? activateCountry.toByte() : (byte) 0, activateRegion != null ? activateRegion.toByte() : (byte) 0, activateInfo.getAppPkgName());
    }

    public void setAppActivateInfo(ActivateInfoV2 activateInfoV2) {
        Log.v(TAG, "setAppActivateInfo V2 is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setAppActivateInfo mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        this.mOtaSdkManager.setAppActivateInfo(activateInfoV2.getSupplierType(), activateInfoV2.getAutoVinResult(), activateInfoV2.getSupplierName(), activateInfoV2.getActivateState(), activateInfoV2.getActivateTime(), activateInfoV2.getLicenseTime(), activateInfoV2.getActivateCountry(), activateInfoV2.getActivateRegion(), activateInfoV2.getAppPkgName());
    }

    public boolean isConnectTbox() {
        if (1 == this.mForTest) {
            Log.e(TAG, " isConnectTbox mForTestValue is " + this.mForTest + " execute return");
            return false;
        }
        return this.mOtaSdkManager.isConnectTbox();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CanInfo packet2convert(CarCloudPacket carCloudPacket, int i) {
        CanInfo canInfo = new CanInfo();
        canInfo.setCanId(carCloudPacket.getCanId());
        canInfo.setChildId((byte) i);
        canInfo.setMask(carCloudPacket.getMask());
        canInfo.setData(carCloudPacket.getData());
        return canInfo;
    }

    private List<CarCloudPacket> convert2Packet(List<CanInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (CanInfo canInfo : list) {
            CarCloudPacket carCloudPacket = new CarCloudPacket();
            carCloudPacket.setCanId(canInfo.getCanId());
            carCloudPacket.setChildId(canInfo.getChildId());
            carCloudPacket.setMask(canInfo.getMask());
            carCloudPacket.setData(canInfo.getData());
            arrayList.add(carCloudPacket);
        }
        return arrayList;
    }

    public void registerListener(AbsBYDAutoYunListener absBYDAutoYunListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe yun listener " + absBYDAutoYunListener);
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        this.mAbsBYDAutoYunListener = absBYDAutoYunListener;
        this.mOtaSdkManager.registerTboxListener(this.bydTboxListener);
    }

    public void registerListener(AbsBYDAutoYunListener absBYDAutoYunListener, int[] iArr) {
        Log.d(TAG, "registerListener featureIDs is run");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
    }

    public void unregisterListener(AbsBYDAutoYunListener absBYDAutoYunListener) {
        Log.d(TAG, "unregisterListener is run");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        this.mAbsBYDAutoYunListener = null;
        this.mOtaSdkManager.unregisterTboxListener(this.bydTboxListener);
    }
}
