package android.hardware.bydauto.engine;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.energy.tsimpl.EnergyManagerImpl;
import android.hardware.bydauto.engine.tsimpl.EngineManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoEngineDevice extends AbsBYDAutoDevice {
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int ENGINE_CAPACITY_MAX = 250;
    public static final int ENGINE_CAPACITY_MIN = 0;
    public static final int ENGINE_COMMAND_BUSY = -2147482647;
    public static final int ENGINE_COMMAND_FAILED = -2147482648;
    public static final int ENGINE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int ENGINE_COMMAND_SUCCESS = 0;
    public static final int ENGINE_COMMAND_TIMEOUT = -2147482646;
    public static final int ENGINE_COOLANT_LEVEL_LOW = 1;
    public static final int ENGINE_COOLANT_LEVEL_NORMAL = 2;
    public static final int ENGINE_COOLING_FAN_TEMPERATURE_MAX = 1;
    public static final int ENGINE_COOLING_FAN_TEMPERATURE_MIN = 0;
    public static final double ENGINE_DISPLACEMENT_MAX = 25.5d;
    public static final double ENGINE_DISPLACEMENT_MIN = 0.0d;
    public static final int ENGINE_IDLING_STATE_OFF = 0;
    public static final int ENGINE_IDLING_STATE_ON = 1;
    public static final double ENGINE_MILEAGE_MAX = 1677721.5d;
    public static final double ENGINE_MILEAGE_MIN = 0.0d;
    public static final int ENGINE_OIL_MAX = 254;
    public static final int ENGINE_OIL_MIN = 0;
    public static final int ENGINE_POWER_MAX = 300;
    public static final int ENGINE_POWER_MIN = -100;
    public static final int ENGINE_SIMULATOR_VOICE_SOURCE_1 = 1;
    public static final int ENGINE_SIMULATOR_VOICE_SOURCE_2 = 2;
    public static final int ENGINE_SIMULATOR_VOICE_SOURCE_3 = 3;
    public static final int ENGINE_SPEED_MAX = 8000;
    public static final int ENGINE_SPEED_MIN = 0;
    public static final int ENGINE_SPEED_WARNING_NO = 1;
    public static final int ENGINE_SPEED_WARNING_YES = 0;
    public static final int ENGINE_STATE_BREAKDOWN = 2;
    public static final int ENGINE_STATE_NORMAL = 1;
    public static final int ENGINE_TARGET_IDLING_VALUE_MAX = 2540;
    public static final int ENGINE_TARGET_IDLING_VALUE_MIN = 0;
    public static final String ENGINE_TYPE1 = "371QA";
    public static final String ENGINE_TYPE10 = "488QA";
    public static final String ENGINE_TYPE11 = "4G15";
    public static final String ENGINE_TYPE12 = "4G18";
    public static final String ENGINE_TYPE13 = "4G69";
    public static final String ENGINE_TYPE14 = "473QE";
    public static final String ENGINE_TYPE15 = "471ZQA";
    public static final String ENGINE_TYPE2 = "473QB";
    public static final String ENGINE_TYPE3 = "473QC";
    public static final String ENGINE_TYPE4 = "473QD";
    public static final String ENGINE_TYPE5 = "476ZQA";
    public static final String ENGINE_TYPE6 = "483QA";
    public static final String ENGINE_TYPE7 = "483QB";
    public static final String ENGINE_TYPE8 = "483QB CNG";
    public static final String ENGINE_TYPE9 = "487ZQA";
    public static final int ENGINE_VOICE_SIMULATOR_OFF = 0;
    public static final int ENGINE_VOICE_SIMULATOR_ON = 1;
    public static final int ENGINE_WATER_TEMPERATURE_ABNORMAL = 255;
    public static final int ENGINE_WATER_TEMPERATURE_MAX = 194;
    public static final int ENGINE_WATER_TEMPERATURE_MIN = -60;
    public static final String FEATURE_ENGINE_VOICE_SIMULATOR = "EngineVoiceSimulator";
    public static final String FEATURE_ENGINE_VOICE_SOURCE = "EngineVoiceSource";
    public static final String PERMISSION_BYDAUTO_ENGINE_COMMON = "android.permission.BYDAUTO_ENGINE_COMMON";
    public static final String PERMISSION_BYDAUTO_ENGINE_GET = "android.permission.BYDAUTO_ENGINE_GET";
    public static final String PERMISSION_BYDAUTO_ENGINE_SET = "android.permission.BYDAUTO_ENGINE_SET";
    public static final int STATE_ENGINE_CRANKING = 1;
    public static final int STATE_ENGINE_FAULT = 7;
    public static final int STATE_ENGINE_RUNNING = 3;
    public static final int STATE_ENGINE_STOP = 0;
    protected static final String TAG = "BYDAutoEngineDevice";
    private static volatile BYDAutoEngineDevice mBYDAutoEngineDevice;
    private EngineManagerImpl mEngineManagerImpl;

    BYDAutoEngineDevice(Context context) {
        super(context);
        this.mEngineManagerImpl = EngineManagerImpl.getInstance(context);
    }

    public static BYDAutoEngineDevice getInstance(Context context) {
        if (mBYDAutoEngineDevice == null) {
            synchronized (EnergyManagerImpl.class) {
                if (mBYDAutoEngineDevice == null) {
                    mBYDAutoEngineDevice = new BYDAutoEngineDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoEngineDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mEngineManagerImpl.get(iArr, cls);
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public String getEngineCode() {
        return this.mEngineManagerImpl.getEngineCode();
    }

    public int getEngineCondition() {
        return this.mEngineManagerImpl.getEngineCondition();
    }

    public int getEngineCoolantLevel() {
        return this.mEngineManagerImpl.getEngineCoolantLevel();
    }

    public float getEngineDisplacement() {
        return this.mEngineManagerImpl.getEngineDisplacement();
    }

    public int getEnginePower() {
        return this.mEngineManagerImpl.getEnginePower();
    }

    public int getEngineSimulatorVoiceSource() {
        return 0;
    }

    public int getEngineSpeed() {
        return this.mEngineManagerImpl.getEngineSpeed();
    }

    public int getEngineSpeedWarningValue() {
        return 0;
    }

    public byte[] getEngineState() {
        return new byte[0];
    }

    public int getEngineVoiceSimulatorState() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_ENGINE_GET;
    }

    public int getOilLevel() {
        return this.mEngineManagerImpl.getOilLevel();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_ENGINE_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int hasFeature(String str) {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        this.mEngineManagerImpl.registerListener(absBYDAutoEngineListener);
    }

    public void setAllStatus() {
    }

    public int setEngineCondition(int i) {
        return this.mEngineManagerImpl.setEngineCondition(i);
    }

    public int setEngineSimulatorVoiceSource(int i) {
        return 0;
    }

    public int setEngineVoiceSimulatorState(int i) {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        this.mEngineManagerImpl.unregisterListener(absBYDAutoEngineListener);
    }

    public void registerListener(AbsBYDAutoEngineListener absBYDAutoEngineListener, int[] iArr) {
        this.mEngineManagerImpl.registerListener(absBYDAutoEngineListener, iArr);
    }
}
