package com.ts.androidauto.aap.common;

import android.text.TextUtils;
import com.google.android.projection.proto.Protos;

/* JADX INFO: loaded from: classes.dex */
public class VehicleInfo {
    public int mAapTouchPointOffsetX;
    public int mAapTouchPointOffsetY;
    public String mAoaSerialNumber;
    public String mClientCert;
    public int mClusterViewingDistance;
    public String mDisplayActivityClassName;
    public String mDisplayActivityPackageName;
    public String mDisplayName;
    public String mHeadUnitMaker;
    public String mHeadUnitModel;
    public String mHeadUnitSwBuild;
    public String mHeadUnitSwVer;
    public boolean mIsNeedDecryptCert;
    public boolean mIsNeedShowProjectionOnPhoneCall;
    public boolean mIsSupportClusterProjection;
    public boolean mIsSupportDisplayNaviDataUpdate;
    public boolean mIsSupportNativeNavigation;
    public boolean mIsSupportWireless;
    public boolean mIsTruck;
    public boolean mIsUpdateClusterScreenSize;
    public String mPrivateKey;
    public String mRootCert;
    private Protos.KeyCode[] mSupportHardkeyList;
    public int mTouchScreenHeight;
    public int mTouchScreenWidth;
    private Protos.EvConnectorType[] mVehicleEvConnectorTypeList;
    private Protos.FuelType[] mVehicleFuelTypeList;
    public String mVehicleId;
    public String mVehicleMaker;
    public String mVehicleModel;
    public String mVehicleYear;
    public int mViewingDistance;
    public AapVideoConfig mAapVideoConfig480p = new AapVideoConfig();
    public AapVideoConfig mAapVideoConfig720p = new AapVideoConfig();
    public AapVideoConfig mAapVideoConfig1080p = new AapVideoConfig();
    public AapVideoConfig mAapClusterVideoConfig480p = new AapVideoConfig();
    public AapVideoConfig mAapClusterVideoConfig720p = new AapVideoConfig();
    public AapVideoConfig mAapClusterVideoConfig1080pSmall = new AapVideoConfig();
    public AapVideoConfig mAapClusterVideoConfig1080pFull = new AapVideoConfig();
    public Protos.DriverPosition mVehicleDriverPosition = Protos.DriverPosition.DRIVER_POSITION_UNKNOWN;

    public static class AapVideoConfig {
        public int mAapContentInsetsBottom;
        public int mAapContentInsetsLeft;
        public int mAapContentInsetsRight;
        public int mAapContentInsetsTop;
        public int mAapDisplayAreaHeight;
        public int mAapDisplayAreaMarginLeft;
        public int mAapDisplayAreaMarginTop;
        public int mAapDisplayAreaWidth;
        public int mAapMarginBottom;
        public int mAapMarginLeft;
        public int mAapMarginRight;
        public int mAapMarginTop;
        public int mAapResolutionHeight;
        public float mAapResolutionPixelAspect;
        public int mAapResolutionRealDpi;
        public int mAapResolutionReportDpi;
        public int mAapResolutionWidth;
        public int mAapStableContentInsetsBottom;
        public int mAapStableContentInsetsLeft;
        public int mAapStableContentInsetsRight;
        public int mAapStableContentInsetsTop;
        public int mAapSurfaceViewHeight;
        public int mAapSurfaceViewScrollX;
        public int mAapSurfaceViewScrollY;
        public int mAapSurfaceViewWidth;
        public int mAapTouchPointOffsetX;
        public int mAapTouchPointOffsetY;
        public int mTouchScreenHeight;
        public int mTouchScreenWidth;
    }

    public String getVehicleMaker() {
        if (TextUtils.isEmpty(this.mVehicleMaker)) {
            return "OEM";
        }
        return this.mVehicleMaker;
    }

    public String getVehicleModel() {
        if (TextUtils.isEmpty(this.mVehicleModel)) {
            return "CAR";
        }
        return this.mVehicleModel;
    }

    public String getVehicleYear() {
        if (TextUtils.isEmpty(this.mVehicleYear)) {
            return "multi";
        }
        return this.mVehicleYear;
    }

    public String getVehicleId() {
        if (TextUtils.isEmpty(this.mVehicleId)) {
            return "0000000000000000";
        }
        return this.mVehicleId;
    }

    public String getHeadUnitMaker() {
        if (TextUtils.isEmpty(this.mHeadUnitMaker)) {
            return "NULL";
        }
        return this.mHeadUnitMaker;
    }

    public String getHeadUnitModel() {
        if (TextUtils.isEmpty(this.mHeadUnitModel)) {
            return "NULL";
        }
        return this.mHeadUnitModel;
    }

    public String getHeadUnitSwBuild() {
        if (TextUtils.isEmpty(this.mHeadUnitSwBuild)) {
            return "multi";
        }
        return this.mHeadUnitSwBuild;
    }

    public String getHeadUnitSwVersion() {
        if (TextUtils.isEmpty(this.mHeadUnitSwVer)) {
            return "00.00";
        }
        return this.mHeadUnitSwVer;
    }

    public String getAoaSerialNumber() {
        if (TextUtils.isEmpty(this.mAoaSerialNumber)) {
            return "00000000";
        }
        return this.mAoaSerialNumber;
    }

    public synchronized void setFuelTypes(Protos.FuelType[] types) {
        this.mVehicleFuelTypeList = types;
    }

    public synchronized Protos.FuelType[] getFuelTypes() {
        if (this.mVehicleFuelTypeList == null) {
            this.mVehicleFuelTypeList = new Protos.FuelType[0];
        }
        return this.mVehicleFuelTypeList;
    }

    public synchronized void setEvConnectorTypes(Protos.EvConnectorType[] types) {
        this.mVehicleEvConnectorTypeList = types;
    }

    public synchronized Protos.EvConnectorType[] getEvConnectorTypes() {
        if (this.mVehicleEvConnectorTypeList == null) {
            this.mVehicleEvConnectorTypeList = new Protos.EvConnectorType[0];
        }
        return this.mVehicleEvConnectorTypeList;
    }

    public synchronized void setSupportKeyCodes(Protos.KeyCode[] keyCodes) {
        this.mSupportHardkeyList = keyCodes;
    }

    public synchronized int[] getSupportKeyCodes() {
        if (this.mSupportHardkeyList == null) {
            return new int[0];
        }
        int[] keyCodes = new int[this.mSupportHardkeyList.length];
        for (int i = 0; i < this.mSupportHardkeyList.length; i++) {
            if (this.mSupportHardkeyList[i] == null) {
                this.mSupportHardkeyList[i] = Protos.KeyCode.KEYCODE_UNKNOWN;
            }
            keyCodes[i] = this.mSupportHardkeyList[i].getNumber();
        }
        return keyCodes;
    }
}
