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
        public void onAppActivateResult(int i2, int i3) {
            if (YunManagerImpl.this.mAbsBYDAutoYunListener != null) {
                YunManagerImpl.this.mAbsBYDAutoYunListener.onAppActivateResult(i2, i3);
            }
        }

        public void onDeleteAllNfcKeyResult(int i2, int i3) {
        }

        public void onDeleteNfcKeyByKeyNoResult(String str, int i2) {
        }

        public void onDeleteNfcKeyResult(int i2, int i3) {
        }

        public void onDeleteOwnIphoneKeyResult(String str, int i2) {
        }

        public void onKeyListPageRefresh() {
        }

        public void onKeyListResponse(int i2, List<DigitalKeyBeanSdk> list) {
        }

        public void onMobileNetworkStateChanged(int i2) {
        }

        public void onNfcOperationCommand(int i2) {
        }
    };

    YunManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i2 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i2;
            if (1 == i2) {
                Log.v(TAG, "YunManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
        this.mTsManagerImpl.setOnCloudChangedListener(new OnCloudChangedListener() { // from class: android.hardware.bydauto.yun.tsimpl.YunManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                Log.v(YunManagerImpl.TAG, "cloud call back state :" + z);
            }

            public void onCloudChanged(int i3, int i4, CarCloudPacket carCloudPacket) {
                CanInfo canInfoPacket2convert = YunManagerImpl.this.packet2convert(carCloudPacket, i4);
                if (YunManagerImpl.this.mAbsBYDAutoYunListener != null) {
                    YunManagerImpl.this.mAbsBYDAutoYunListener.onChange(canInfoPacket2convert);
                }
            }

            public void onCollectDataChanged(int i3, int i4, CarCollectDataPacket carCollectDataPacket) {
            }

            public void onRemoteChanged(int i3, int i4, CarRemotePacket carRemotePacket) {
            }
        });
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

    /* JADX INFO: Access modifiers changed from: private */
    public CanInfo packet2convert(CarCloudPacket carCloudPacket, int i2) {
        CanInfo canInfo = new CanInfo();
        canInfo.setCanId(carCloudPacket.getCanId());
        canInfo.setChildId((byte) i2);
        canInfo.setMask(carCloudPacket.getMask());
        canInfo.setData(carCloudPacket.getData());
        return canInfo;
    }

    public byte[] getCanBytes(int i2, int i3) {
        Log.v(TAG, "getCanBytes is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCloudAdapterManager().getCanBytes(i2, i3);
            }
            Log.e(TAG, "adapter is not connect");
            return new byte[0];
        }
        Log.v(TAG, "getCanBytes mForTestValue is" + this.mForTest + " execute return");
        return new byte[0];
    }

    public void getCanBytesSync(int i2, int i3) {
        Log.v(TAG, "getCanBytesSync is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getCarCloudAdapterManager().getCanBytesSync(i2, i3);
                return;
            }
        }
        Log.v(TAG, "getCanBytesSync mForTestValue is" + this.mForTest + " execute return");
    }

    public int getRemainBattery() {
        Log.v(TAG, "getRemainBattery is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCloudAdapterManager().getRemainBattery();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getRemainBattery mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public byte getVehicleSpeed() {
        Log.v(TAG, "getVehicleSpeed is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCloudAdapterManager().getVehicleSpeed();
            }
            Log.e(TAG, "adapter is not connect");
            return (byte) 0;
        }
        Log.v(TAG, "getVehicleSpeed mForTestValue is" + this.mForTest + " execute return");
        return (byte) 0;
    }

    public byte getWaterThermometer() {
        Log.v(TAG, "getWaterThermometer is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() != null) {
                return this.mTsManagerImpl.getCarCloudAdapterManager().getWaterThermometer();
            }
            Log.e(TAG, "adapter is not connect");
            return (byte) 0;
        }
        Log.v(TAG, "getWaterThermometer mForTestValue is" + this.mForTest + " execute return");
        return (byte) 0;
    }

    public boolean isConnectTbox() {
        if (1 != this.mForTest) {
            return this.mOtaSdkManager.isConnectTbox();
        }
        Log.e(TAG, " isConnectTbox mForTestValue is " + this.mForTest + " execute return");
        return false;
    }

    public void registerListener(AbsBYDAutoYunListener absBYDAutoYunListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe yun listener " + absBYDAutoYunListener);
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        this.mAbsBYDAutoYunListener = absBYDAutoYunListener;
        this.mOtaSdkManager.registerTboxListener(this.bydTboxListener);
    }

    public void remoteAcControl(int i2, int i3, int i4, int i5) {
        Log.v(TAG, "remoteAcControlOpen is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getCarCloudAdapterManager().remoteAcControl(i2, i3, i4, i5);
                return;
            }
        }
        Log.v(TAG, "remoteAcControlOpen mForTestValue is" + this.mForTest + " execute return");
    }

    public void remoteControl(int i2) {
        Log.v(TAG, "remoteControl is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getCarCloudAdapterManager().remoteControl(i2);
                return;
            }
        }
        Log.v(TAG, "remoteControl mForTestValue is" + this.mForTest + " execute return");
    }

    public void remoteSeatControl(int i2, int i3, int i4) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getCarCloudAdapterManager().remoteSeatControl(i2, i3, i4);
                return;
            }
        }
        Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
    }

    public void remoteSteeringHeatingControl(int i2, int i3) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getCarCloudAdapterManager().remoteSteeringHeatingControl(i2, i3);
                return;
            }
        }
        Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
    }

    public void remoteUnlockFourDoors() {
        Log.v(TAG, "remoteUnlockFourDoors is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
                Log.e(TAG, "adapter is not connect");
                return;
            } else {
                this.mTsManagerImpl.getCarCloudAdapterManager().remoteUnlockFourDoors();
                return;
            }
        }
        Log.v(TAG, "remoteUnlockFourDoors mForTestValue is" + this.mForTest + " execute return");
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

    public void subscribe(List<CanInfo> list) {
        Log.v(TAG, "subscribe is run");
    }

    public void unSubscribe() {
        Log.v(TAG, "unSubscribe is run");
    }

    public void unregisterListener(AbsBYDAutoYunListener absBYDAutoYunListener) {
        Log.d(TAG, "unregisterListener is run");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        this.mAbsBYDAutoYunListener = null;
        this.mOtaSdkManager.unregisterTboxListener(this.bydTboxListener);
    }

    public void registerListener(AbsBYDAutoYunListener absBYDAutoYunListener, int[] iArr) {
        Log.d(TAG, "registerListener featureIDs is run");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
    }

    public void remoteSeatControl(int i2, int i3, int i4, int i5, int i6) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteSeatControl(i2, i3, i4, i5, i6);
        }
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
}
