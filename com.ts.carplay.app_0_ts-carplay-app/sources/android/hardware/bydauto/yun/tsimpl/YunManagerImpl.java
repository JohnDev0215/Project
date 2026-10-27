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
        public void onAppActivateResult(int result, int appTag) {
            if (YunManagerImpl.this.mAbsBYDAutoYunListener != null) {
                YunManagerImpl.this.mAbsBYDAutoYunListener.onAppActivateResult(result, appTag);
            }
        }

        public void onKeyListResponse(int totalNo, List<DigitalKeyBeanSdk> resp) {
        }

        public void onDeleteNfcKeyResult(int op, int result) {
        }

        public void onMobileNetworkStateChanged(int state) {
        }

        public void onDeleteAllNfcKeyResult(int type, int result) {
        }

        public void onDeleteNfcKeyByKeyNoResult(String keyNo, int result) {
        }

        public void onDeleteOwnIphoneKeyResult(String keyNo, int result) {
        }

        public void onKeyListPageRefresh() {
        }

        public void onNfcOperationCommand(int cmd) {
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
            public void onCloudChanged(int id, int childId, CarCloudPacket packet) {
                CanInfo canInfo = YunManagerImpl.this.packet2convert(packet, childId);
                if (YunManagerImpl.this.mAbsBYDAutoYunListener != null) {
                    YunManagerImpl.this.mAbsBYDAutoYunListener.onChange(canInfo);
                }
            }

            public void onRemoteChanged(int id, int areaId, CarRemotePacket packet) {
            }

            public void onCollectDataChanged(int id, int areaId, CarCollectDataPacket packet) {
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(YunManagerImpl.TAG, "cloud call back state :" + binderState);
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
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

    public void subscribe(List<CanInfo> canList) {
        Log.v(TAG, "subscribe is run");
    }

    public void unSubscribe() {
        Log.v(TAG, "unSubscribe is run");
    }

    public void getCanBytesSync(int id, int cid) {
        Log.v(TAG, "getCanBytesSync is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getCanBytesSync mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().getCanBytesSync(id, cid);
        }
    }

    public byte[] getCanBytes(int id, int cid) {
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
        return this.mTsManagerImpl.getCarCloudAdapterManager().getCanBytes(id, cid);
    }

    public void remoteControl(int control) {
        Log.v(TAG, "remoteControl is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteControl mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteControl(control);
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

    public void remoteAcControl(int p1, int p2, int p3, int p4) {
        Log.v(TAG, "remoteAcControlOpen is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteAcControlOpen mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteAcControl(p1, p2, p3, p4);
        }
    }

    public void remoteSeatControl(int p1, int p2, int p3) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteSeatControl(p1, p2, p3);
        }
    }

    public void remoteSeatControl(int p1, int p2, int p3, int p4, int p5) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteSeatControl(p1, p2, p3, p4, p5);
        }
    }

    public void remoteSteeringHeatingControl(int p1, int p2) {
        Log.v(TAG, "remoteSeatControlPassengerHeating is execute");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "remoteSeatControlPassengerHeating mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (this.mTsManagerImpl.getCarCloudAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarCloudAdapterManager().remoteSteeringHeatingControl(p1, p2);
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

    public void setAppActivateInfo(ActivateInfo info) {
        Log.v(TAG, "setAppActivateInfo is execute");
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
            byte supplierType = 0;
            byte supplierName = 0;
            byte activateState = 0;
            byte activateCountry = 0;
            byte activateRegion = 0;
            ActivateInfo.ProviderType providerType = info.getSupplierType();
            ActivateInfo.ProviderName providerName = info.getSupplierName();
            ActivateInfo.ActiveState activeState = info.getActivateState();
            ActivateInfo.NationalRegion country = info.getActivateCountry();
            ActivateInfo.Region region = info.getActivateRegion();
            byte autoVinResult = info.getAutoVinResult();
            long activateTIme = info.getActivateTime();
            long licenseTime = info.getLicenseTime();
            String appPkgName = info.getAppPkgName();
            if (providerType != null) {
                supplierType = providerType.toByte();
            }
            if (providerName != null) {
                supplierName = providerName.toByte();
            }
            if (activeState != null) {
                activateState = activeState.toByte();
            }
            if (country != null) {
                activateCountry = country.toByte();
            }
            if (region != null) {
                activateRegion = region.toByte();
            }
            this.mOtaSdkManager.setAppActivateInfo(supplierType, autoVinResult, supplierName, activateState, activateTIme, licenseTime, activateCountry, activateRegion, appPkgName);
            return;
        }
        Log.v(TAG, "setAppActivateInfo mForTestValue is" + this.mForTest + " execute return");
    }

    public void setAppActivateInfo(ActivateInfoV2 info) {
        Log.v(TAG, "setAppActivateInfo V2 is execute");
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_GET);
            int supplierType = info.getSupplierType();
            int autoVinResult = info.getAutoVinResult();
            int supplierName = info.getSupplierName();
            int activateState = info.getActivateState();
            long activateTime = info.getActivateTime();
            long licenseTime = info.getLicenseTime();
            int activateCountry = info.getActivateCountry();
            int activateRegion = info.getActivateRegion();
            String appPkgName = info.getAppPkgName();
            this.mOtaSdkManager.setAppActivateInfo(supplierType, autoVinResult, supplierName, activateState, activateTime, licenseTime, activateCountry, activateRegion, appPkgName);
            return;
        }
        Log.v(TAG, "setAppActivateInfo mForTestValue is" + this.mForTest + " execute return");
    }

    public boolean isConnectTbox() {
        if (1 == this.mForTest) {
            Log.e(TAG, " isConnectTbox mForTestValue is " + this.mForTest + " execute return");
            return false;
        }
        return this.mOtaSdkManager.isConnectTbox();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CanInfo packet2convert(CarCloudPacket cloudPacket, int childId) {
        CanInfo canInfo = new CanInfo();
        canInfo.setCanId(cloudPacket.getCanId());
        canInfo.setChildId((byte) childId);
        canInfo.setMask(cloudPacket.getMask());
        canInfo.setData(cloudPacket.getData());
        return canInfo;
    }

    private List<CarCloudPacket> convert2Packet(List<CanInfo> infoList) {
        List<CarCloudPacket> carCloudPackets = new ArrayList<>();
        for (CanInfo canInfo : infoList) {
            CarCloudPacket carCloudPacket = new CarCloudPacket();
            carCloudPacket.setCanId(canInfo.getCanId());
            carCloudPacket.setChildId(canInfo.getChildId());
            carCloudPacket.setMask(canInfo.getMask());
            carCloudPacket.setData(canInfo.getData());
            carCloudPackets.add(carCloudPacket);
        }
        return carCloudPackets;
    }

    public void registerListener(AbsBYDAutoYunListener listener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe yun listener " + listener);
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        this.mAbsBYDAutoYunListener = listener;
        this.mOtaSdkManager.registerTboxListener(this.bydTboxListener);
    }

    public void registerListener(AbsBYDAutoYunListener listener, int[] featureIDs) {
        Log.d(TAG, "registerListener featureIDs is run");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
    }

    public void unregisterListener(AbsBYDAutoYunListener listener) {
        Log.d(TAG, "unregisterListener is run");
        enforcePermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
        this.mAbsBYDAutoYunListener = null;
        this.mOtaSdkManager.unregisterTboxListener(this.bydTboxListener);
    }
}
