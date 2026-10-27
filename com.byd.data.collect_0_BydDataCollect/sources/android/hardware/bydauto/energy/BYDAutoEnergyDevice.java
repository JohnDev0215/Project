package android.hardware.bydauto.energy;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.energy.tsimpl.EnergyManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoEnergyDevice extends AbsBYDAutoDevice {
    public static final int BCM_STATE_CLOSE = 1;
    public static final int BCM_STATE_OPEN = 0;
    public static final int CURVE_TYPE_CLEAR = 1;
    public static final int CURVE_TYPE_ELEC = 0;
    public static final int CURVE_TYPE_NORMAL = 0;
    public static final int CURVE_TYPE_OIL = 1;
    public static final int DC_WORK_MODE_STATE_BOOST = 1;
    public static final int DC_WORK_MODE_STATE_CLOSE = 0;
    public static final int DC_WORK_MODE_STATE_STEP_DOWN = 2;
    public static final int DRIVE_DISTANCE_MODE_DYNAMIC = 2;
    public static final int DRIVE_DISTANCE_MODE_STANDAR = 1;
    public static final int DRIVE_DISTANCE_STANDAR_CLTC = 2;
    public static final int DRIVE_DISTANCE_STANDAR_NEDC = 1;
    public static final int ENERGY_COMMAND_BUSY = -2147482647;
    public static final int ENERGY_COMMAND_FAILED = -2147482648;
    public static final int ENERGY_COMMAND_INVALID = -2147482645;
    public static final int ENERGY_COMMAND_SUCCESS = 0;
    public static final int ENERGY_COMMAND_TIMEOUT = -2147482646;
    public static final int ENERGY_ELECTRICITY = 2;
    public static final int ENERGY_FB_LARGE = 3;
    public static final int ENERGY_FB_SMALL = 1;
    public static final int ENERGY_FB_STANDARD = 2;
    public static final int ENERGY_INTELLIGENT = 1;
    public static final int ENERGY_MANDATORY = 2;
    public static final int ENERGY_MILEAGE = 1;
    public static final int ENERGY_MODE_EV = 1;
    public static final int ENERGY_MODE_FORCE_EV = 2;
    public static final int ENERGY_MODE_FUEL = 4;
    public static final int ENERGY_MODE_HEV = 3;
    public static final int ENERGY_MODE_KEEP = 5;
    public static final int ENERGY_MODE_STOP = 0;
    public static final int ENERGY_OPERATION_ECONOMY = 1;
    public static final int ENERGY_OPERATION_KEEP = 3;
    public static final int ENERGY_OPERATION_MODE_ECO = 2;
    public static final int ENERGY_OPERATION_MODE_HEV_NORMAL = 3;
    public static final int ENERGY_OPERATION_MODE_INVALID = 0;
    public static final int ENERGY_OPERATION_MODE_NORMAL = 1;
    public static final int ENERGY_OPERATION_MODE_SPORT = 3;
    public static final int ENERGY_OPERATION_MUDDY = 5;
    public static final int ENERGY_OPERATION_NORMAL = 3;
    public static final int ENERGY_OPERATION_SAND = 6;
    public static final int ENERGY_OPERATION_SNOW = 4;
    public static final int ENERGY_OPERATION_SPORT = 2;
    public static final int ENERGY_POWER_ENERATION_END = 2;
    public static final int ENERGY_POWER_GENERATING = 1;
    public static final int ENERGY_POWER_GENERATION_END = 2;
    public static final int ENERGY_POWER_GENERATION_ERROR = 3;
    public static final int ENERGY_POWER_GENERATION_INVALID = 0;
    public static final int ENERGY_POWER_GENERATION_VALUE_MAX = 31;
    public static final int ENERGY_POWER_GENERATION_VALUE_MIN = 1;
    public static final int ENERGY_ROAD_SURFACE_COMMON = 1;
    public static final int ENERGY_ROAD_SURFACE_KEEP = 0;
    public static final int ENERGY_ROAD_SURFACE_MUDDY = 3;
    public static final int ENERGY_ROAD_SURFACE_ONE = 0;
    public static final int ENERGY_ROAD_SURFACE_RESCUE = 5;
    public static final int ENERGY_ROAD_SURFACE_SAND = 4;
    public static final int ENERGY_ROAD_SURFACE_SNOW = 2;
    public static final int ENERGY_ROAD_SURFACE_TERRAIN = 6;
    public static final int ENERGY_ROAD_SURFACE_THREE = 1;
    public static final int ENERGY_SET_OPERATION_MODE_ECO = 2;
    public static final int ENERGY_SET_OPERATION_MODE_INVALID = 0;
    public static final int ENERGY_SET_OPERATION_MODE_MUDDY = 5;
    public static final int ENERGY_SET_OPERATION_MODE_NORMAL = 1;
    public static final int ENERGY_SET_OPERATION_MODE_SAND = 6;
    public static final int ENERGY_SET_OPERATION_MODE_SNOW = 4;
    public static final int ENERGY_SET_OPERATION_MODE_SPORT = 3;
    public static final int ENERGY_STATE_DRIVE_ASSIST_DRIVING_MODE_1 = 24;
    public static final int ENERGY_STATE_DRIVE_ASSIST_DRIVING_MODE_2 = 25;
    public static final int ENERGY_STATE_DRIVE_ASSIST_DRIVING_MODE_3 = 26;
    public static final int ENERGY_STATE_DRIVE_ASSIST_FEEDBACK_MODE_1 = 27;
    public static final int ENERGY_STATE_DRIVE_ASSIST_FEEDBACK_MODE_2 = 28;
    public static final int ENERGY_STATE_DRIVE_ASSIST_FEEDBACK_MODE_3 = 29;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FOUR_WHEEL_DRIVE = 1;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FOUR_WHEEL_DRIVE_FEEDBACK = 4;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FRONT_WHEEL_DRIVE = 2;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FRONT_WHEEL_DRIVE_FEEDBACK = 5;
    public static final int ENERGY_STATE_ELECTRIC_POWER_REAR_WHEEL_DRIVE = 3;
    public static final int ENERGY_STATE_ELECTRIC_POWER_REAR_WHEEL_DRIVE_FEEDBACK = 6;
    public static final int ENERGY_STATE_FUEL_POWER_DRIVE = 17;
    public static final int ENERGY_STATE_GENERATE_ELECTRICITY = 18;
    public static final int ENERGY_STATE_HEV_FRONT_WHEEL_DRIVE_PARALLELING = 8;
    public static final int ENERGY_STATE_HEV_THREE_POWER = 7;
    public static final int ENERGY_STATE_HEV_TWO_POWER_FOUR_WHEEL_DRIVE = 9;
    public static final int ENERGY_STATE_HIGH_SPEED_GENERATE_ELECTRICITY = 22;
    public static final int ENERGY_STATE_HYBRID_POWER_FEEDBACK_MODE_1 = 14;
    public static final int ENERGY_STATE_HYBRID_POWER_FEEDBACK_MODE_2 = 15;
    public static final int ENERGY_STATE_HYBRID_POWER_FEEDBACK_MODE_3 = 16;
    public static final int ENERGY_STATE_HYBRID_POWER_LOW_POWER_OUTPUT = 10;
    public static final int ENERGY_STATE_HYBRID_POWER_RUNNING_GENERATE_ELECTRICITY_1 = 11;
    public static final int ENERGY_STATE_HYBRID_POWER_RUNNING_GENERATE_ELECTRICITY_2 = 12;
    public static final int ENERGY_STATE_HYBRID_POWER_RUNNING_GENERATE_ELECTRICITY_3 = 13;
    public static final int ENERGY_STATE_IDLING = 23;
    public static final int ENERGY_STATE_LOW_SPEED_GENERATE_ELECTRICITY_1 = 20;
    public static final int ENERGY_STATE_LOW_SPEED_GENERATE_ELECTRICITY_2 = 21;
    public static final int ENERGY_STATE_MAX = 255;
    public static final int ENERGY_STATE_NONE = 0;
    public static final int ENERGY_STATE_SERIES_FOUR_WHEEL_DRIVE_2 = 33;
    public static final int ENERGY_STATE_SERIES_MODE_2 = 30;
    public static final int ENERGY_STATE_SERIES_MODE_3 = 31;
    public static final int ENERGY_STATE_SERIES_MODE_4 = 32;
    public static final int ENERGY_STATE_SERIES_REAR_WHEEL_DRIVE = 19;
    public static final int EPADAL_OFF = 2;
    public static final int EPADAL_ON = 1;
    public static final int ITAC_ALLOW_NO = 0;
    public static final int ITAC_ALLOW_YES = 1;
    public static final int ITAC_GET_MODE_CONTEST = 2;
    public static final int ITAC_GET_MODE_DRIFT = 1;
    public static final int ITAC_GET_MODE_OFF = 0;
    public static final int ITAC_GET_MODE_RESCUE = 3;
    public static final int ITAC_GET_MODE_TERRAIN = 4;
    public static final int ITAC_SET_MODE_CONTEST = 5;
    public static final int ITAC_SET_MODE_DRIFT = 4;
    public static final int ITAC_SET_MODE_OFF = 1;
    public static final int ITAC_SET_MODE_RESCUE = 2;
    public static final int ITAC_SET_MODE_TERRAIN = 3;
    public static final int ITAC_STATE_OFF = 1;
    public static final int ITAC_STATE_ON = 0;
    public static final double LAST_200M_ELEC_HEV_MAX = 134.6d;
    public static final double LAST_200M_ELEC_HEV_MIN = -70.0d;
    public static final int LAST_200M_ELEC_MAX = 1346;
    public static final int LAST_200M_ELEC_MIN = -700;
    public static final int LAST_200M_OIL_MAX = 51;
    public static final int LAST_200M_OIL_MIN = 0;
    public static final double LAST_50KM_ELEC_MAX = 99.9d;
    public static final double LAST_50KM_ELEC_MIN = -99.9d;
    public static final double LAST_50KM_OIL_MAX = 30.0d;
    public static final double LAST_50KM_OIL_MIN = 0.0d;
    public static final int MEMORY_STATE_OFF = 1;
    public static final int MEMORY_STATE_ON = 2;
    public static final int POINT_INVALID = 0;
    public static final int POINT_VALID = 1;
    public static final int PRESALE_STATE_OFF = 1;
    public static final int PRESALE_STATE_ON = 0;
    public static final int STATE_DATA_FAIL = 0;
    public static final int STATE_DATA_SUCCESS = 1;
    public static final int STATE_INVALID = 0;
    public static final int STATE_OFF = 2;
    public static final int STATE_ON = 1;
    protected static final String TAG = "BYDAutoEnergyDevice";
    private static volatile BYDAutoEnergyDevice mBYDAutoEnergyDevice;
    private EnergyManagerImpl mEnergyManagerImpl;

    BYDAutoEnergyDevice(Context context) {
        super(context);
        this.mEnergyManagerImpl = EnergyManagerImpl.getInstance(context);
    }

    public static BYDAutoEnergyDevice getInstance(Context context) {
        if (mBYDAutoEnergyDevice == null) {
            synchronized (EnergyManagerImpl.class) {
                if (mBYDAutoEnergyDevice == null) {
                    mBYDAutoEnergyDevice = new BYDAutoEnergyDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoEnergyDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mEnergyManagerImpl.get(iArr, cls);
    }

    public void getAllStatus() {
    }

    public int getBCMState() {
        return 0;
    }

    public int getChangePHint() {
        return this.mEnergyManagerImpl.getChangePHint();
    }

    public int getChargePortCoverTip() {
        return this.mEnergyManagerImpl.getChargePortCoverTip();
    }

    public int getDCWorkMode() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getDriveDistanceShowMode() {
        return this.mEnergyManagerImpl.getDriveDistanceShowMode();
    }

    public int getDriveDistanceShowStandard() {
        return this.mEnergyManagerImpl.getDriveDistanceShowStandard();
    }

    public int getEPedalState() {
        return this.mEnergyManagerImpl.getEPedalState();
    }

    public int getElectricPointFlag() {
        return this.mEnergyManagerImpl.getElectricPointFlag();
    }

    public int getElectricShowMode() {
        return this.mEnergyManagerImpl.getElectricShowMode();
    }

    public int getEnergyCurve() {
        return this.mEnergyManagerImpl.getEnergyCurve();
    }

    public float[] getEnergyElectricCurveX(int i2, int i3) {
        return this.mEnergyManagerImpl.getEnergyElectricCurveX(i2, i3);
    }

    public float[] getEnergyElectricCurveY(int i2, int i3) {
        return this.mEnergyManagerImpl.getEnergyElectricCurveY(i2, i3);
    }

    public int getEnergyFeedback() {
        return this.mEnergyManagerImpl.getEnergyFeedback();
    }

    public int getEnergyMode() {
        return this.mEnergyManagerImpl.getEnergyMode();
    }

    public float[] getEnergyOilCurveX(int i2, int i3) {
        return this.mEnergyManagerImpl.getEnergyOilCurveX(i2, i3);
    }

    public float[] getEnergyOilCurveY(int i2, int i3) {
        return this.mEnergyManagerImpl.getEnergyOilCurveY(i2, i3);
    }

    public int getEnergyState() {
        return this.mEnergyManagerImpl.getEnergyState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    public int getHEVElectricPointFlag() {
        return this.mEnergyManagerImpl.getHevElectricPointFlag();
    }

    public float getHEVLast200MElectricCon() {
        return this.mEnergyManagerImpl.getHevLast200MElectricCon();
    }

    public float getLast200MElectricCon() {
        return this.mEnergyManagerImpl.getLast200mElectricCon();
    }

    public float getLast200MOilCon() {
        return this.mEnergyManagerImpl.getLast200mOilCon();
    }

    public float getLast50KMElectricConAvg() {
        return this.mEnergyManagerImpl.getLast50KmElectricConAvg();
    }

    public float getLast50KMOilConAvg() {
        return this.mEnergyManagerImpl.getLast50KmOilConAvg();
    }

    public int getMandatoryElectricState() {
        return this.mEnergyManagerImpl.getMandatoryElectricState();
    }

    public int getMemoryElectricState() {
        return this.mEnergyManagerImpl.getMemoryElectricState();
    }

    public int getMemoryMaxEvState() {
        return this.mEnergyManagerImpl.getMemoryMaxEvState();
    }

    public int getMemorySportState() {
        return this.mEnergyManagerImpl.getMemorySportState();
    }

    public int getOilPointFlag() {
        return this.mEnergyManagerImpl.getOilPointFlag();
    }

    public int getOperationMode() {
        return this.mEnergyManagerImpl.getOperationMode();
    }

    public int getOperationModeNormal() {
        return this.mEnergyManagerImpl.getOperationModeNormal();
    }

    public int getPowerGenerationState() {
        return this.mEnergyManagerImpl.getPowerGenerationState();
    }

    public int getPowerGenerationValue() {
        return this.mEnergyManagerImpl.getPowerGenerationValue();
    }

    public int getPreSaleModelState() {
        return this.mEnergyManagerImpl.getPreSaleModelState();
    }

    public int getRoadSurfacKind() {
        return 0;
    }

    public int getRoadSurfaceMode() {
        return this.mEnergyManagerImpl.getRoadSurfaceMode();
    }

    public int getRoadSurfaceModeNormal() {
        return this.mEnergyManagerImpl.getRoadSurfaceModeNormal();
    }

    public int getRollBenchModeStatus() {
        return this.mEnergyManagerImpl.getRollBenchModeStatus();
    }

    public int getSOCTarget() {
        return this.mEnergyManagerImpl.getSocTarget();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    public int getTripACPercent50KM() {
        return this.mEnergyManagerImpl.getTripAcPercent50km();
    }

    public int getTripAcPercent() {
        return this.mEnergyManagerImpl.getTripAcPercent();
    }

    public int getTripDrivePercent() {
        return this.mEnergyManagerImpl.getTripDrivePercent();
    }

    public int getTripDrivePercent50KM() {
        return this.mEnergyManagerImpl.getTripDrivePercent50km();
    }

    public int getTripElecAppliancePercent() {
        return this.mEnergyManagerImpl.getTripElecAppliancePercent();
    }

    public int getTripElecAppliancePercent50KM() {
        return this.mEnergyManagerImpl.getTripElecAppliancePercent50km();
    }

    public float getTripElecConAvg() {
        return this.mEnergyManagerImpl.getTripElecConAvg();
    }

    public float getTripEnergyConAvg() {
        return this.mEnergyManagerImpl.getTripEnergyConAvg();
    }

    public float getTripLast50KMElectricConAvg() {
        return this.mEnergyManagerImpl.getTripLast50kmElectricConAvg();
    }

    public float getTripLast50KMFuelConAvg() {
        return this.mEnergyManagerImpl.getTripLast50kmFuelConAvg();
    }

    public float getTripLast50KMOilConAvg() {
        return this.mEnergyManagerImpl.getTripLast50kmOilConAvg();
    }

    public float getTripOilConAvg() {
        return this.mEnergyManagerImpl.getTripOilConAvg();
    }

    public int getTripOtherPercent() {
        return this.mEnergyManagerImpl.getTripOtherPercent();
    }

    public int getTripOtherPercent50KM() {
        return this.mEnergyManagerImpl.getTripOtherPercent50km();
    }

    public int getTripPointFlag() {
        return this.mEnergyManagerImpl.getTripPointFlag();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public float getYEnergyCon() {
        return this.mEnergyManagerImpl.getYEnergyCon();
    }

    public int getYEnergyConLimit() {
        return this.mEnergyManagerImpl.getYEnergyConLimit();
    }

    public int getiTacExitTag() {
        return this.mEnergyManagerImpl.getiTacExitTag();
    }

    public int getiTacMode() {
        return this.mEnergyManagerImpl.getiTacMode();
    }

    public int getiTacpAllowTag() {
        return this.mEnergyManagerImpl.getiTacpAllowTag();
    }

    public int getiTacpState() {
        return this.mEnergyManagerImpl.getiTacpState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        this.mEnergyManagerImpl.registerListener(absBYDAutoEnergyListener);
    }

    public void registerListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener, int[] iArr) {
    }

    public int resetCurveData(int i2) {
        return this.mEnergyManagerImpl.resetCurveData(i2);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mEnergyManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public void setAllStatus() {
    }

    public int setChargePortCoverHint(int i2) {
        return this.mEnergyManagerImpl.setChargePortCoverHint(i2);
    }

    public int setConfirmChargePortCover(int i2) {
        return this.mEnergyManagerImpl.setConfirmChargePortCover(i2);
    }

    public int setDriveDistanceShowMode(int i2) {
        return this.mEnergyManagerImpl.setDriveDistanceShowMode(i2);
    }

    public int setDriveDistanceShowStandard(int i2) {
        return this.mEnergyManagerImpl.setDriveDistanceShowStandard(i2);
    }

    public int setEPedalState(int i2) {
        return this.mEnergyManagerImpl.setEPedalState(i2);
    }

    public int setElectricShowMode(int i2) {
        return this.mEnergyManagerImpl.setElectricShowMode(i2);
    }

    public int setEnergyFeedback(int i2) {
        return this.mEnergyManagerImpl.setEnergyFeedback(i2);
    }

    public int setEnergyMode(int i2) {
        return 0;
    }

    public int setMandatoryElectricState(int i2) {
        return this.mEnergyManagerImpl.setMandatoryElectricState(i2);
    }

    public int setMemoryElectricState(int i2) {
        return this.mEnergyManagerImpl.setMemoryElectricState(i2);
    }

    public int setMemoryMaxEvState(int i2) {
        return this.mEnergyManagerImpl.setMemoryMaxEvState(i2);
    }

    public int setMemorySportState(int i2) {
        return this.mEnergyManagerImpl.setMemorySportState(i2);
    }

    public int setOperationMode(int i2) {
        return this.mEnergyManagerImpl.setOperationMode(i2);
    }

    public int setPreSaleModelState(int i2) {
        return this.mEnergyManagerImpl.setPreSaleModelState(i2);
    }

    public int setRoadSurfaceMode(int i2) {
        return 0;
    }

    public int setSOCTarget(int i2) {
        return this.mEnergyManagerImpl.setSocTarget(i2);
    }

    public int setiTacMode(int i2) {
        return this.mEnergyManagerImpl.setiTacMode(i2);
    }

    public int setiTacpState(int i2) {
        return this.mEnergyManagerImpl.setiTacpState(i2);
    }

    public void unregisterListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        this.mEnergyManagerImpl.unregisterListener(absBYDAutoEnergyListener);
    }
}
