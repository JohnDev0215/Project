package com.ts.androidauto.sdk.aidl.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.androidauto.sdk.common.VehicleConst;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleInfo implements Parcelable {
    public static final Parcelable.Creator<VehicleInfo> CREATOR = new Parcelable.Creator<VehicleInfo>() { // from class: com.ts.androidauto.sdk.aidl.data.VehicleInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleInfo createFromParcel(Parcel in) {
            return new VehicleInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VehicleInfo[] newArray(int size) {
            return new VehicleInfo[size];
        }
    };
    public AapVideoConfig mAapClusterVideoConfig1080pFull;
    public AapVideoConfig mAapClusterVideoConfig1080pSmall;
    public AapVideoConfig mAapClusterVideoConfig480p;
    public AapVideoConfig mAapClusterVideoConfig720p;
    public int mAapTouchPointOffsetX;
    public int mAapTouchPointOffsetY;
    public AapVideoConfig mAapVideoConfig1080p;
    public AapVideoConfig mAapVideoConfig480p;
    public AapVideoConfig mAapVideoConfig720p;
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
    public VehicleConst.AapHardkeyEvent[] mSupportHardKeyList;
    public int mTouchScreenHeight;
    public int mTouchScreenWidth;
    public VehicleConst.AapVehicleDrivePosition mVehicleDriverPosition;
    public VehicleConst.AapEvConnectorType[] mVehicleEvConnectorTypeList;
    public VehicleConst.AapVehicleFuelType[] mVehicleFuelTypeList;
    public String mVehicleId;
    public String mVehicleMaker;
    public String mVehicleModel;
    public String mVehicleYear;
    public int mViewingDistance;

    public static class AapVideoConfig implements Parcelable {
        public static final Parcelable.Creator<AapVideoConfig> CREATOR = new Parcelable.Creator<AapVideoConfig>() { // from class: com.ts.androidauto.sdk.aidl.data.VehicleInfo.AapVideoConfig.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AapVideoConfig createFromParcel(Parcel in) {
                return new AapVideoConfig(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AapVideoConfig[] newArray(int size) {
                return new AapVideoConfig[size];
            }
        };
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

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public AapVideoConfig() {
        }

        public AapVideoConfig(Parcel in) {
            this.mAapDisplayAreaWidth = in.readInt();
            this.mAapDisplayAreaHeight = in.readInt();
            this.mAapDisplayAreaMarginLeft = in.readInt();
            this.mAapDisplayAreaMarginTop = in.readInt();
            this.mAapResolutionWidth = in.readInt();
            this.mAapResolutionHeight = in.readInt();
            this.mAapResolutionReportDpi = in.readInt();
            this.mAapResolutionRealDpi = in.readInt();
            this.mAapResolutionPixelAspect = in.readFloat();
            this.mAapSurfaceViewWidth = in.readInt();
            this.mAapSurfaceViewHeight = in.readInt();
            this.mAapSurfaceViewScrollX = in.readInt();
            this.mAapSurfaceViewScrollY = in.readInt();
            this.mTouchScreenWidth = in.readInt();
            this.mTouchScreenHeight = in.readInt();
            this.mAapTouchPointOffsetX = in.readInt();
            this.mAapTouchPointOffsetY = in.readInt();
            this.mAapContentInsetsLeft = in.readInt();
            this.mAapContentInsetsRight = in.readInt();
            this.mAapContentInsetsTop = in.readInt();
            this.mAapContentInsetsBottom = in.readInt();
            this.mAapStableContentInsetsLeft = in.readInt();
            this.mAapStableContentInsetsRight = in.readInt();
            this.mAapStableContentInsetsTop = in.readInt();
            this.mAapStableContentInsetsBottom = in.readInt();
            this.mAapMarginLeft = in.readInt();
            this.mAapMarginTop = in.readInt();
            this.mAapMarginRight = in.readInt();
            this.mAapMarginBottom = in.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mAapDisplayAreaWidth);
            dest.writeInt(this.mAapDisplayAreaHeight);
            dest.writeInt(this.mAapDisplayAreaMarginLeft);
            dest.writeInt(this.mAapDisplayAreaMarginTop);
            dest.writeInt(this.mAapResolutionWidth);
            dest.writeInt(this.mAapResolutionHeight);
            dest.writeInt(this.mAapResolutionReportDpi);
            dest.writeInt(this.mAapResolutionRealDpi);
            dest.writeFloat(this.mAapResolutionPixelAspect);
            dest.writeInt(this.mAapSurfaceViewWidth);
            dest.writeInt(this.mAapSurfaceViewHeight);
            dest.writeInt(this.mAapSurfaceViewScrollX);
            dest.writeInt(this.mAapSurfaceViewScrollY);
            dest.writeInt(this.mTouchScreenWidth);
            dest.writeInt(this.mTouchScreenHeight);
            dest.writeInt(this.mAapTouchPointOffsetX);
            dest.writeInt(this.mAapTouchPointOffsetY);
            dest.writeInt(this.mAapContentInsetsLeft);
            dest.writeInt(this.mAapContentInsetsRight);
            dest.writeInt(this.mAapContentInsetsTop);
            dest.writeInt(this.mAapContentInsetsBottom);
            dest.writeInt(this.mAapStableContentInsetsLeft);
            dest.writeInt(this.mAapStableContentInsetsRight);
            dest.writeInt(this.mAapStableContentInsetsTop);
            dest.writeInt(this.mAapStableContentInsetsBottom);
            dest.writeInt(this.mAapMarginLeft);
            dest.writeInt(this.mAapMarginTop);
            dest.writeInt(this.mAapMarginRight);
            dest.writeInt(this.mAapMarginBottom);
        }
    }

    public VehicleInfo() {
        this.mAapVideoConfig480p = new AapVideoConfig();
        this.mAapVideoConfig720p = new AapVideoConfig();
        this.mAapVideoConfig1080p = new AapVideoConfig();
        this.mAapClusterVideoConfig480p = new AapVideoConfig();
        this.mAapClusterVideoConfig720p = new AapVideoConfig();
        this.mAapClusterVideoConfig1080pSmall = new AapVideoConfig();
        this.mAapClusterVideoConfig1080pFull = new AapVideoConfig();
    }

    public VehicleInfo(Parcel in) {
        this.mAapVideoConfig480p = new AapVideoConfig();
        this.mAapVideoConfig720p = new AapVideoConfig();
        this.mAapVideoConfig1080p = new AapVideoConfig();
        this.mAapClusterVideoConfig480p = new AapVideoConfig();
        this.mAapClusterVideoConfig720p = new AapVideoConfig();
        this.mAapClusterVideoConfig1080pSmall = new AapVideoConfig();
        this.mAapClusterVideoConfig1080pFull = new AapVideoConfig();
        this.mTouchScreenWidth = in.readInt();
        this.mTouchScreenHeight = in.readInt();
        this.mAapTouchPointOffsetX = in.readInt();
        this.mAapTouchPointOffsetY = in.readInt();
        this.mAapVideoConfig480p = (AapVideoConfig) in.readParcelable(AapVideoConfig.class.getClassLoader());
        this.mAapVideoConfig720p = (AapVideoConfig) in.readParcelable(AapVideoConfig.class.getClassLoader());
        this.mAapVideoConfig1080p = (AapVideoConfig) in.readParcelable(AapVideoConfig.class.getClassLoader());
        this.mAapClusterVideoConfig480p = (AapVideoConfig) in.readParcelable(AapVideoConfig.class.getClassLoader());
        this.mAapClusterVideoConfig720p = (AapVideoConfig) in.readParcelable(AapVideoConfig.class.getClassLoader());
        this.mAapClusterVideoConfig1080pSmall = (AapVideoConfig) in.readParcelable(AapVideoConfig.class.getClassLoader());
        this.mAapClusterVideoConfig1080pFull = (AapVideoConfig) in.readParcelable(AapVideoConfig.class.getClassLoader());
        this.mVehicleMaker = in.readString();
        this.mVehicleModel = in.readString();
        this.mVehicleYear = in.readString();
        this.mVehicleId = in.readString();
        this.mVehicleDriverPosition = VehicleConst.AapVehicleDrivePosition.values()[in.readInt()];
        this.mHeadUnitMaker = in.readString();
        this.mHeadUnitModel = in.readString();
        this.mHeadUnitSwBuild = in.readString();
        this.mHeadUnitSwVer = in.readString();
        this.mDisplayName = in.readString();
        this.mViewingDistance = in.readInt();
        this.mClusterViewingDistance = in.readInt();
        this.mAoaSerialNumber = in.readString();
        int fuelTypeCount = in.readInt();
        this.mVehicleFuelTypeList = new VehicleConst.AapVehicleFuelType[fuelTypeCount];
        for (int i = 0; i < fuelTypeCount; i++) {
            this.mVehicleFuelTypeList[i] = VehicleConst.AapVehicleFuelType.values()[in.readInt()];
        }
        int evConnectorTypeCount = in.readInt();
        this.mVehicleEvConnectorTypeList = new VehicleConst.AapEvConnectorType[evConnectorTypeCount];
        for (int i2 = 0; i2 < evConnectorTypeCount; i2++) {
            this.mVehicleEvConnectorTypeList[i2] = VehicleConst.AapEvConnectorType.values()[in.readInt()];
        }
        int supportHardKeyCount = in.readInt();
        this.mSupportHardKeyList = new VehicleConst.AapHardkeyEvent[supportHardKeyCount];
        for (int i3 = 0; i3 < supportHardKeyCount; i3++) {
            this.mSupportHardKeyList[i3] = VehicleConst.AapHardkeyEvent.values()[in.readInt()];
        }
        int i4 = in.readInt();
        this.mIsTruck = i4 > 0;
        this.mIsSupportWireless = in.readInt() > 0;
        this.mIsSupportDisplayNaviDataUpdate = in.readInt() > 0;
        this.mIsNeedShowProjectionOnPhoneCall = in.readInt() > 0;
        this.mIsSupportNativeNavigation = in.readInt() > 0;
        this.mRootCert = in.readString();
        this.mClientCert = in.readString();
        this.mPrivateKey = in.readString();
        this.mIsNeedDecryptCert = in.readInt() > 0;
        this.mDisplayActivityPackageName = in.readString();
        this.mDisplayActivityClassName = in.readString();
        this.mIsSupportClusterProjection = in.readInt() > 0;
        this.mIsUpdateClusterScreenSize = in.readInt() > 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mTouchScreenWidth);
        parcel.writeInt(this.mTouchScreenHeight);
        parcel.writeInt(this.mAapTouchPointOffsetX);
        parcel.writeInt(this.mAapTouchPointOffsetY);
        parcel.writeParcelable(this.mAapVideoConfig480p, i);
        parcel.writeParcelable(this.mAapVideoConfig720p, i);
        parcel.writeParcelable(this.mAapVideoConfig1080p, i);
        parcel.writeParcelable(this.mAapClusterVideoConfig480p, i);
        parcel.writeParcelable(this.mAapClusterVideoConfig720p, i);
        parcel.writeParcelable(this.mAapClusterVideoConfig1080pSmall, i);
        parcel.writeParcelable(this.mAapClusterVideoConfig1080pFull, i);
        parcel.writeString(this.mVehicleMaker);
        parcel.writeString(this.mVehicleModel);
        parcel.writeString(this.mVehicleYear);
        parcel.writeString(this.mVehicleId);
        if (this.mVehicleDriverPosition == null) {
            this.mVehicleDriverPosition = VehicleConst.AapVehicleDrivePosition.AAP_DRIVE_UNKNOWN;
        }
        parcel.writeInt(this.mVehicleDriverPosition.ordinal());
        parcel.writeString(this.mHeadUnitMaker);
        parcel.writeString(this.mHeadUnitModel);
        parcel.writeString(this.mHeadUnitSwBuild);
        parcel.writeString(this.mHeadUnitSwVer);
        parcel.writeString(this.mDisplayName);
        parcel.writeInt(this.mViewingDistance);
        parcel.writeInt(this.mClusterViewingDistance);
        parcel.writeString(this.mAoaSerialNumber);
        if (this.mVehicleFuelTypeList == null) {
            this.mVehicleFuelTypeList = new VehicleConst.AapVehicleFuelType[0];
        }
        parcel.writeInt(this.mVehicleFuelTypeList.length);
        int i2 = 0;
        while (true) {
            VehicleConst.AapVehicleFuelType[] aapVehicleFuelTypeArr = this.mVehicleFuelTypeList;
            if (i2 >= aapVehicleFuelTypeArr.length) {
                break;
            }
            if (aapVehicleFuelTypeArr[i2] == null) {
                aapVehicleFuelTypeArr[i2] = VehicleConst.AapVehicleFuelType.AAP_FUEL_UNKNOWN;
            }
            parcel.writeInt(this.mVehicleFuelTypeList[i2].ordinal());
            i2++;
        }
        if (this.mVehicleEvConnectorTypeList == null) {
            this.mVehicleEvConnectorTypeList = new VehicleConst.AapEvConnectorType[0];
        }
        parcel.writeInt(this.mVehicleEvConnectorTypeList.length);
        int i3 = 0;
        while (true) {
            VehicleConst.AapEvConnectorType[] aapEvConnectorTypeArr = this.mVehicleEvConnectorTypeList;
            if (i3 >= aapEvConnectorTypeArr.length) {
                break;
            }
            if (aapEvConnectorTypeArr[i3] == null) {
                aapEvConnectorTypeArr[i3] = VehicleConst.AapEvConnectorType.AAP_EV_CONNECTOR_UNKNOWN;
            }
            parcel.writeInt(this.mVehicleEvConnectorTypeList[i3].ordinal());
            i3++;
        }
        if (this.mSupportHardKeyList == null) {
            this.mSupportHardKeyList = new VehicleConst.AapHardkeyEvent[0];
        }
        parcel.writeInt(this.mSupportHardKeyList.length);
        int i4 = 0;
        while (true) {
            VehicleConst.AapHardkeyEvent[] aapHardkeyEventArr = this.mSupportHardKeyList;
            if (i4 < aapHardkeyEventArr.length) {
                if (aapHardkeyEventArr[i4] == null) {
                    aapHardkeyEventArr[i4] = VehicleConst.AapHardkeyEvent.AAP_KEYCODE_SEARCH;
                }
                parcel.writeInt(this.mSupportHardKeyList[i4].ordinal());
                i4++;
            } else {
                parcel.writeInt(this.mIsTruck ? 1 : 0);
                parcel.writeInt(this.mIsSupportWireless ? 1 : 0);
                parcel.writeInt(this.mIsSupportDisplayNaviDataUpdate ? 1 : 0);
                parcel.writeInt(this.mIsNeedShowProjectionOnPhoneCall ? 1 : 0);
                parcel.writeInt(this.mIsSupportNativeNavigation ? 1 : 0);
                parcel.writeString(this.mRootCert);
                parcel.writeString(this.mClientCert);
                parcel.writeString(this.mPrivateKey);
                parcel.writeInt(this.mIsNeedDecryptCert ? 1 : 0);
                parcel.writeString(this.mDisplayActivityPackageName);
                parcel.writeString(this.mDisplayActivityClassName);
                parcel.writeInt(this.mIsSupportClusterProjection ? 1 : 0);
                parcel.writeInt(this.mIsUpdateClusterScreenSize ? 1 : 0);
                return;
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("VehicleInfo={");
        sb.append(" VecMaker=");
        sb.append(this.mVehicleMaker);
        sb.append(" VecModel=");
        sb.append(this.mVehicleModel);
        sb.append(" HuMaker=");
        sb.append(this.mHeadUnitMaker);
        sb.append(" HuModel=");
        sb.append(this.mHeadUnitModel);
        sb.append(" TouchSceenW=");
        sb.append(this.mTouchScreenWidth);
        sb.append(" TouchSceenH=");
        sb.append(this.mTouchScreenHeight);
        sb.append(" }");
        return sb.toString();
    }
}
