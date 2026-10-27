package ts.car.service.androidauto.data;

import ts.car.service.androidauto.common.VehicleConst;

/* JADX INFO: loaded from: classes2.dex */
public final class VehicleData {

    @Deprecated
    public int mAapDisplayAreaHeight;

    @Deprecated
    public int mAapDisplayAreaMarginLeft;

    @Deprecated
    public int mAapDisplayAreaMarginTop;

    @Deprecated
    public int mAapDisplayAreaWidth;

    @Deprecated
    public int mAapResolution1080pHeight;

    @Deprecated
    public float mAapResolution1080pPixelAspect;

    @Deprecated
    public int mAapResolution1080pRealDpi;

    @Deprecated
    public int mAapResolution1080pReportDpi;

    @Deprecated
    public int mAapResolution1080pWidth;

    @Deprecated
    public int mAapResolution480pHeight;

    @Deprecated
    public float mAapResolution480pPixelAspect;

    @Deprecated
    public int mAapResolution480pRealDpi;

    @Deprecated
    public int mAapResolution480pReportDpi;

    @Deprecated
    public int mAapResolution480pWidth;

    @Deprecated
    public int mAapResolution720pHeight;

    @Deprecated
    public float mAapResolution720pPixelAspect;

    @Deprecated
    public int mAapResolution720pRealDpi;

    @Deprecated
    public int mAapResolution720pReportDpi;

    @Deprecated
    public int mAapResolution720pWidth;

    @Deprecated
    public int mAapSurfaceView1080pHeight;

    @Deprecated
    public int mAapSurfaceView1080pScrollX;

    @Deprecated
    public int mAapSurfaceView1080pScrollY;

    @Deprecated
    public int mAapSurfaceView1080pWidth;

    @Deprecated
    public int mAapSurfaceView480pHeight;

    @Deprecated
    public int mAapSurfaceView480pScrollX;

    @Deprecated
    public int mAapSurfaceView480pScrollY;

    @Deprecated
    public int mAapSurfaceView480pWidth;

    @Deprecated
    public int mAapSurfaceView720pHeight;

    @Deprecated
    public int mAapSurfaceView720pScrollX;

    @Deprecated
    public int mAapSurfaceView720pScrollY;

    @Deprecated
    public int mAapSurfaceView720pWidth;
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
    public VehicleConst.AapHardkeyEvent[] mSupportHardKeyList;
    public int mTouchScreenHeight;
    public int mTouchScreenWidth;
    public VehicleConst.AapVehicleDrivePosition mVehicleDriverPosition;

    @Deprecated
    public VehicleConst.AapEvConnectorType mVehicleEvConnectorType;
    public VehicleConst.AapEvConnectorType[] mVehicleEvConnectorTypeList;

    @Deprecated
    public VehicleConst.AapVehicleFuelType mVehicleFuelType;
    public VehicleConst.AapVehicleFuelType[] mVehicleFuelTypeList;
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

    public static class AapVideoConfig {
        public int mAapDisplayAreaHeight;
        public int mAapDisplayAreaMarginLeft;
        public int mAapDisplayAreaMarginTop;
        public int mAapDisplayAreaWidth;
        public int mAapResolutionHeight;
        public float mAapResolutionPixelAspect;
        public int mAapResolutionRealDpi;
        public int mAapResolutionReportDpi;
        public int mAapResolutionWidth;
        public int mAapSurfaceViewHeight;
        public int mAapSurfaceViewScrollX;
        public int mAapSurfaceViewScrollY;
        public int mAapSurfaceViewWidth;
        public int mAapTouchPointOffsetX;
        public int mAapTouchPointOffsetY;
        public int mTouchScreenHeight;
        public int mTouchScreenWidth;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("VehicleData={").append(" VecMaker=").append(this.mVehicleMaker).append(" VecModel=").append(this.mVehicleModel).append(" HuMaker=").append(this.mHeadUnitMaker).append(" HuModel=").append(this.mHeadUnitModel).append(" }");
        return stringBuffer.toString();
    }
}
