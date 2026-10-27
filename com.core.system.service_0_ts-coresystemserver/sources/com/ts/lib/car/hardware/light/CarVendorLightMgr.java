package com.ts.lib.car.hardware.light;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.light.CarVendorLightManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorLightMgr extends CarVendorManagerBase implements CarVendorLightManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static LightEventChangeValue sLightEventChangeValue = new LightEventChangeValue();
    private final LinkedList<OnCarLightChangeEventListenerProxy> mListeners = new LinkedList<>();
    OnCarLightChangeEventListenerProxy proxy = null;
    private Integer[] mExtLightIds = {557860610, 557860617, 557860615, 557860613, 557860611, 557860619, 557857721, 557860608, 557860609, 557860624, 557860631, Integer.valueOf(CarVendorLightManager.ID_REVERSING_LIGHT_STATE), 557870867, Integer.valueOf(CarVendorLightManager.ID_TURN_LIGHT_STATE), 557860635, 557860638, 557860637, 557860642, 557879809, 557860633, 557860634, 557860643, 557857713, 557857718, 557857719, 557857717, 557857716, 557857712, 557857710, 557857711, 557856188, 557856041, Integer.valueOf(CarVendorLightManager.ID_BACKLIGHT_LEVEL_R), Integer.valueOf(CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_CONFIG_R), Integer.valueOf(CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_STATE_R), Integer.valueOf(CarVendorLightManager.ID_FRONT_CENTER_LIGHT_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_FRONT_FOG_LAMP_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_HIGHTLIGHT_POSITION_CONFIG_R), Integer.valueOf(CarVendorLightManager.ID_HIGHTLIGHT_POSITION_R), Integer.valueOf(CarVendorLightManager.ID_INTELLIGENT_POSTION_FEEDBACK_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_CORNER_LIGHT_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_DAYTIME_LIGHT_STATE_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_FRONT_READING_LAMP_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_REAR_R_LIGHT_SWITCH_R), Integer.valueOf(CarVendorLightManager.ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_CORNER_LIGHT_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_DAYTIME_LIGHT_STATE_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_FRONT_READING_LAMP_STATUS_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_REAR_R_LIGHT_SWITCH_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_REMOTE_LIGHT_STATE_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_REMOTE_LIGHT_STATE_R), Integer.valueOf(CarVendorLightManager.ID_LEFT_DAYTIME_RUN_LAMP_STATE_R), Integer.valueOf(CarVendorLightManager.ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R), 557857169, Integer.valueOf(CarVendorLightManager.ID_FRONT_FOG_LIGHT_CONFIG_R), Integer.valueOf(CarVendorLightManager.ID_FRONT_FOG_LIGHT_SWITCH_STATE_R), 557857171, 557857172, Integer.valueOf(CarVendorLightManager.ID_MIDDLE_STERN_LAMP_STATE_R), 557860697, 557860698, 557860706, 557860709, 557860703, 557860704, 557860702, 557860700, 557860701, 557860699, 557860712, 557860710, 557860707};

    public CarVendorLightMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorLightManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorLightManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorLightManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorLightManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860697, 0);
            Log.debug(CarVendorLightManager.TAG, "getAmbientStateR value = " + value + " property: 0x" + tenToHex(557860697));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAmbientStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAmbientStateS value = " + value + " property: 0x" + tenToHex(557860696));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860696, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientSwitchConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860698, 0);
            Log.debug(CarVendorLightManager.TAG, "getAmbientSwitchConfigR value = " + value + " property: 0x" + tenToHex(557860698));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAtmospereLampPopupPromptR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860706, 0);
            Log.debug(CarVendorLightManager.TAG, "getAtmospereLampPopupPromptR value = " + value + " property: 0x" + tenToHex(557860706));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAtmospereLampUiDisplayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860709, 0);
            Log.debug(CarVendorLightManager.TAG, "getAtmospereLampUiDisplayR value = " + value + " property: 0x" + tenToHex(557860709));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAtmospereLightNightWeakenModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860703, 0);
            Log.debug(CarVendorLightManager.TAG, "getAtmospereLightNightWeakenModeConfigR value = " + value + " property: 0x" + tenToHex(557860703));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAtmospereLightNightWeakenModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860704, 0);
            Log.debug(CarVendorLightManager.TAG, "getAtmospereLightNightWeakenModeR value = " + value + " property: 0x" + tenToHex(557860704));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAtmospereLightNightWeakenModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAtmospereLightNightWeakenModeS value = " + value + " property: 0x" + tenToHex(557860705));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860705, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAtmosphereLampBodyDisplayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860702, 0);
            Log.debug(CarVendorLightManager.TAG, "getAtmosphereLampBodyDisplayR value = " + value + " property: 0x" + tenToHex(557860702));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightAdasIndicatorStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860700, 0);
            Log.debug(CarVendorLightManager.TAG, "getLightAdasIndicatorStatusR value = " + value + " property: 0x" + tenToHex(557860700));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightFrontGlowLogoWorkStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860701, 0);
            Log.debug(CarVendorLightManager.TAG, "getLightFrontGlowLogoWorkStateR value = " + value + " property: 0x" + tenToHex(557860701));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightRearGlowLogoWorkStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860699, 0);
            Log.debug(CarVendorLightManager.TAG, "getLightRearGlowLogoWorkStateR value = " + value + " property: 0x" + tenToHex(557860699));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getThemeColorFeatureFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860712, 0);
            Log.debug(CarVendorLightManager.TAG, "getThemeColorFeatureFlagR value = " + value + " property: 0x" + tenToHex(557860712));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getThemeColorFeedBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860710, 0);
            Log.debug(CarVendorLightManager.TAG, "getThemeColorFeedBackR value = " + value + " property: 0x" + tenToHex(557860710));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getThemeColorSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860707, 0);
            Log.debug(CarVendorLightManager.TAG, "getThemeColorSwitchStateR value = " + value + " property: 0x" + tenToHex(557860707));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setThemeLinkedAmbienceLightColorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setThemeLinkedAmbienceLightColorS value = " + value + " property: 0x" + tenToHex(557860708));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860708, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setThemeLinkedAtmosphereLampSwitchS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setThemeLinkedAtmosphereLampSwitchS value = " + value + " property: 0x" + tenToHex(557860711));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860711, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getMiddleSternLampStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_MIDDLE_STERN_LAMP_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getMiddleSternLampStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_MIDDLE_STERN_LAMP_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAllWeatherLightSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857169, 0);
            Log.debug(CarVendorLightManager.TAG, "getAllWeatherLightSwitchStateR value = " + value + " property: 0x" + tenToHex(557857169));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAllWeatherLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAllWeatherLightSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_ALL_WEATHER_LIGHT_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_ALL_WEATHER_LIGHT_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getFrontFogLightConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_FRONT_FOG_LIGHT_CONFIG_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getFrontFogLightConfigR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_FRONT_FOG_LIGHT_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getFrontFogLightSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_FRONT_FOG_LIGHT_SWITCH_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getFrontFogLightSwitchStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_FRONT_FOG_LIGHT_SWITCH_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setFrontFogLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setFrontFogLightSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_FRONT_FOG_LIGHT_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_FRONT_FOG_LIGHT_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getHeadlightControlModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857171, 0);
            Log.debug(CarVendorLightManager.TAG, "getHeadlightControlModeR value = " + value + " property: 0x" + tenToHex(557857171));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setHeadlightControlModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setHeadlightControlModeS value = " + value + " property: 0x" + tenToHex(557857170));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857170, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRearFogLightSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857172, 0);
            Log.debug(CarVendorLightManager.TAG, "getRearFogLightSwitchStateR value = " + value + " property: 0x" + tenToHex(557857172));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setRearFogLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setRearFogLightSwitchStateS value = " + value + " property: 0x" + tenToHex(557857173));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857173, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftDaytimeRunLampStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_DAYTIME_RUN_LAMP_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftDaytimeRunLampStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_DAYTIME_RUN_LAMP_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightDaytimeRunLampStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightDaytimeRunLampStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftRemoteLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_REMOTE_LIGHT_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftRemoteLightStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_REMOTE_LIGHT_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightRemoteLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_REMOTE_LIGHT_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightRemoteLightStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_REMOTE_LIGHT_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getCargoLightSwitchConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_CONFIG_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getCargoLightSwitchConfigR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getCargoLightSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getCargoLightSwitchStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setCargoLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setCargoLightSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getFrontCenterLightStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_FRONT_CENTER_LIGHT_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getFrontCenterLightStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_FRONT_CENTER_LIGHT_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getFrontFogLampStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_FRONT_FOG_LAMP_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getFrontFogLampStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_FRONT_FOG_LAMP_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setHighlightPositionS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setHighlightPositionS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_HIGHLIGHT_POSITION_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_HIGHLIGHT_POSITION_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getHightlightPositionConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_HIGHTLIGHT_POSITION_CONFIG_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getHightlightPositionConfigR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_HIGHTLIGHT_POSITION_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getHightlightPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_HIGHTLIGHT_POSITION_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getHightlightPositionR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_HIGHTLIGHT_POSITION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIntelligentPostionFeedbackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_INTELLIGENT_POSTION_FEEDBACK_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getIntelligentPostionFeedbackR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_INTELLIGENT_POSTION_FEEDBACK_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftCornerLightStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_CORNER_LIGHT_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftCornerLightStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_CORNER_LIGHT_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftDaytimeLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_DAYTIME_LIGHT_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftDaytimeLightStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_DAYTIME_LIGHT_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftFrontPositionSwlStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftFrontPositionSwlStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftFrontReadingLampStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_FRONT_READING_LAMP_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftFrontReadingLampStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_FRONT_READING_LAMP_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setLeftFrontReadingLampSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setLeftFrontReadingLampSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_FRONT_READING_LAMP_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_LEFT_FRONT_READING_LAMP_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftMiddleRowRLightRotaryStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftMiddleRowRLightRotaryStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setLeftMiddleRowRLightRotaryStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setLeftMiddleRowRLightRotaryStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftMiddleRowSideRLightSwitchR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftMiddleRowSideRLightSwitchR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setLeftMiddleRowSideRLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setLeftMiddleRowSideRLightSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLeftRearRLightSwitchR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_LEFT_REAR_R_LIGHT_SWITCH_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getLeftRearRLightSwitchR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_REAR_R_LIGHT_SWITCH_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setLeftRearRLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setLeftRearRLightSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_LEFT_REAR_R_LIGHT_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_LEFT_REAR_R_LIGHT_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getPositionLightDisplayFeedCallbackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getPositionLightDisplayFeedCallbackR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setPositionLightDisplayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setPositionLightDisplayS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_POSITION_LIGHT_DISPLAY_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_POSITION_LIGHT_DISPLAY_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightCornerLightStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_CORNER_LIGHT_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightCornerLightStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_CORNER_LIGHT_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightDaytimeLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_DAYTIME_LIGHT_STATE_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightDaytimeLightStateR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_DAYTIME_LIGHT_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightFrontPositionSWLStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightFrontPositionSWLStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightFrontReadingLampStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_FRONT_READING_LAMP_STATUS_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightFrontReadingLampStatusR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_FRONT_READING_LAMP_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setRightFrontReadingLampSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setRightFrontReadingLampSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_FRONT_READING_LAMP_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_RIGHT_FRONT_READING_LAMP_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightMiddleRowRLightRotaryR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightMiddleRowRLightRotaryR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setRightMiddleRowRLightRotaryStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setRightMiddleRowRLightRotaryStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightMiddleRowRLightSwitchR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightMiddleRowRLightSwitchR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setRightMiddleRowRLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setRightMiddleRowRLightSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getRightRearRLightSwitchR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_RIGHT_REAR_R_LIGHT_SWITCH_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getRightRearRLightSwitchR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_REAR_R_LIGHT_SWITCH_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setRightRearRLightSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setRightRearRLightSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_RIGHT_REAR_R_LIGHT_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_RIGHT_REAR_R_LIGHT_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getDayTimeRunningLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860643, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDayTimeRunningLightStateR property: 0x");
            sb.append(tenToHex(557860643));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setKtvModeBrightnessS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setKtvModeBrightnessS property: 0x" + tenToHex(CarVendorLightManager.ID_KTV_MODE_BRIGHTNESS_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_KTV_MODE_BRIGHTNESS_S, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setKtvModeColorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setKtvModeColorS property: 0x" + tenToHex(CarVendorLightManager.ID_KTV_MODE_COLOR_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_KTV_MODE_COLOR_S, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAmbientMulticolorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAmbientMulticolorStateS property: 0x" + tenToHex(557860616) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860616, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientFeedBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860635, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientFeedBackR property: 0x");
            sb.append(tenToHex(557860635));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightHighBeamR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860638, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightHighBeamR property: 0x");
            sb.append(tenToHex(557860638));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightLowBeamStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860637, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightLowBeamStatusR property: 0x");
            sb.append(tenToHex(557860637));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightRearFog() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860642, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightRearFog property: 0x");
            sb.append(tenToHex(557860642));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightRearPosition() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860644, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightRearPosition property: 0x");
            sb.append(tenToHex(557860644));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightSideStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557879809, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightSideStatusR property: 0x");
            sb.append(tenToHex(557879809));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setResetDelayLightParamS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setResetDelayLightParamS property: 0x" + tenToHex(557860627) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860627, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getReversingLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860633, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReversingLightStateR property: 0x");
            sb.append(tenToHex(557860633));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getTurnLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860634, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnLightStateR property: 0x");
            sb.append(tenToHex(557860634));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAFSSwitch() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860610, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAFSSwitch property: 0x");
            sb.append(tenToHex(557860610));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAFSSwitchSate(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAFSSwitchSate  property: 0x" + tenToHex(557860626) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860626, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientMulticolorModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860617, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMulticolorModeR property: 0x");
            sb.append(tenToHex(557860617));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAmbientMulticolorModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAmbientMulticolorModeS property: 0x" + tenToHex(557860618) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860618, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientMulticolorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860615, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMulticolorStateR property: 0x");
            sb.append(tenToHex(557860615));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientMusicModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860613, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMusicModeR property: 0x");
            sb.append(tenToHex(557860613));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAmbientMusicModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAmbientMusicModeS property: 0x" + tenToHex(557860614) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860614, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientMusicModeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860611, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMusicModeStateR property: 0x");
            sb.append(tenToHex(557860611));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setAmbientMusicModeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setAmbientMusicModeStateS property: 0x" + tenToHex(557860612) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860612, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getDayTimeLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860619, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDayTimeLightStateR property: 0x");
            sb.append(tenToHex(557860619));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setDayTimeLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setDayTimeLightStateS property: 0x" + tenToHex(557860620) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860620, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setDoorLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setDoorLightStateS property: 0x" + tenToHex(557860622) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860622, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLastSetedArea() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857721, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLastSetedArea property: 0x");
            sb.append(tenToHex(557857721));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightAutoStatusOne() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860608, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightAutoStatusOne property: 0x");
            sb.append(tenToHex(557860608));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getLightAutoStatusTwo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860609, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightAutoStatusTwo property: 0x");
            sb.append(tenToHex(557860609));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getTurnLightFlashStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860624, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnLightFlashStateR property: 0x");
            sb.append(tenToHex(557860624));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setTurnLightFlashStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setTurnLightFlashStateS property: 0x" + tenToHex(557860625) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860625, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientFeedBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860631, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientFeedBack property: 0x");
            sb.append(tenToHex(557860631));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getReversingLightState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_REVERSING_LIGHT_STATE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReversingLightState property: 0x");
            sb.append(tenToHex(CarVendorLightManager.ID_REVERSING_LIGHT_STATE));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getStopLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870867, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getStopLightStateR property: 0x");
            sb.append(tenToHex(557870867));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getTurnLightState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_TURN_LIGHT_STATE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnLightState property: 0x");
            sb.append(tenToHex(CarVendorLightManager.ID_TURN_LIGHT_STATE));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getAmbientColorsSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856188, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientColorsSupport property: 0x");
            sb.append(tenToHex(557856188));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalColorBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857713, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorBackR property: 0x");
            sb.append(tenToHex(557857713));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalColorFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857712, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorFrontR property: 0x");
            sb.append(tenToHex(557857712));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalColorStarRingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857710, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorStarRingR property: 0x");
            sb.append(tenToHex(557857710));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalColorStarrySkyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857711, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorStarrySkyR property: 0x");
            sb.append(tenToHex(557857711));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalBrightnessBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857718, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessBackR property: 0x");
            sb.append(tenToHex(557857718));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalBrightnessFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857719, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessFrontR property: 0x");
            sb.append(tenToHex(557857719));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalBrightnessStarrySkyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857717, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessStarrySkyR property: 0x");
            sb.append(tenToHex(557857717));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getIalBrightnessStarRingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857716, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessStarRingR property: 0x");
            sb.append(tenToHex(557857716));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setIalBrightnessS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setIALBrightnessS property: 0x" + tenToHex(557857720) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857720, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setIalColorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setIALColorS property: 0x" + tenToHex(557857714) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857714, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setIalAreaDifferentWays(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setIALAreaDifferentWays property: 0x" + tenToHex(557857722) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857722, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getDayLightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856041, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDayLightSupport property: 0x");
            sb.append(tenToHex(557856041));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorLightManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void setOnSceneModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorLightManager.TAG, "setOnSceneModeS value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_ON_SCENE_MODE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorLightManager.ID_ON_SCENE_MODE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public int getBacklightLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorLightManager.ID_BACKLIGHT_LEVEL_R, 0);
            Log.debug(CarVendorLightManager.TAG, "getBacklightLevelR value = " + value + " property: 0x" + tenToHex(CarVendorLightManager.ID_BACKLIGHT_LEVEL_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void addCarLightEventListener(CarVendorLightManager.OnCarLightEventListener listener) throws CarNotConnectedException {
        this.proxy = null;
        this.proxy = findListenerLocked(listener);
        synchronized (this) {
            if (this.proxy == null) {
                OnCarLightChangeEventListenerProxy onCarLightChangeEventListenerProxy = new OnCarLightChangeEventListenerProxy(listener);
                this.proxy = onCarLightChangeEventListenerProxy;
                this.mListeners.add(onCarLightChangeEventListenerProxy);
            }
        }
        Log.info(CarVendorLightManager.TAG, "addCarLightEventListener propId size: " + this.mExtLightIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtLightIds) {
                int propId = num.intValue();
                Log.debug(CarVendorLightManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                if (this.mCarPropertyManager != null) {
                    this.mCarPropertyManager.registerCallback(this.proxy, propId, 1.0f);
                }
            }
        } catch (Exception ex) {
            this.mCarPropertyManager = null;
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.light.CarVendorLightManager
    public void removeCarLightEventListener(CarVendorLightManager.OnCarLightEventListener listener) throws CarNotConnectedException {
        this.proxy = null;
        synchronized (this) {
            OnCarLightChangeEventListenerProxy onCarLightChangeEventListenerProxyFindListenerLocked = findListenerLocked(listener);
            this.proxy = onCarLightChangeEventListenerProxyFindListenerLocked;
            if (onCarLightChangeEventListenerProxyFindListenerLocked == null) {
                return;
            }
            this.mListeners.remove(onCarLightChangeEventListenerProxyFindListenerLocked);
            Log.info(CarVendorLightManager.TAG, "removeCarLightEventListener listener size: " + this.mListeners.size());
            try {
                new Thread(new Runnable() { // from class: com.ts.lib.car.hardware.light.CarVendorLightMgr.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.info(CarVendorLightManager.TAG, "mCarPropertyManager is start unregisterCallback");
                        CarVendorLightMgr.this.mCarPropertyManager.unregisterCallback(CarVendorLightMgr.this.proxy);
                        Log.info(CarVendorLightManager.TAG, "mCarPropertyManager is end unregisterCallback");
                    }
                }).start();
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LightEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sLightEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorLightManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case CarVendorLightManager.ID_KTV_MODE_BRIGHTNESS_S /* 557844261 */:
                sLightEventChangeValue.setKtvModeBrightnessS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856041:
                sLightEventChangeValue.setDayLightSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856188:
                sLightEventChangeValue.setAmbientColorsSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857721:
                sLightEventChangeValue.setLastSetedArea(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860622:
                sLightEventChangeValue.setDoorLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_KTV_MODE_COLOR_S /* 557860646 */:
                sLightEventChangeValue.setKtvModeColorS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_BACKLIGHT_LEVEL_R /* 557860648 */:
                sLightEventChangeValue.setBacklightLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_FRONT_READING_LAMP_SWITCH_STATE_S /* 557860649 */:
                sLightEventChangeValue.setLeftFrontReadingLampSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_FRONT_READING_LAMP_SWITCH_STATE_S /* 557860650 */:
                sLightEventChangeValue.setRightFrontReadingLampSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_STATE_S /* 557860651 */:
                sLightEventChangeValue.setLeftMiddleRowSideRLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_STATE_S /* 557860652 */:
                sLightEventChangeValue.setRightMiddleRowRLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S /* 557860653 */:
                sLightEventChangeValue.setLeftMiddleRowRLightRotaryStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S /* 557860654 */:
                sLightEventChangeValue.setRightMiddleRowRLightRotaryStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_REAR_R_LIGHT_SWITCH_STATE_S /* 557860655 */:
                sLightEventChangeValue.setLeftRearRLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_REAR_R_LIGHT_SWITCH_STATE_S /* 557860656 */:
                sLightEventChangeValue.setRightRearRLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_FRONT_READING_LAMP_STATUS_R /* 557860657 */:
                sLightEventChangeValue.setLeftFrontReadingLampStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_FRONT_READING_LAMP_STATUS_R /* 557860658 */:
                sLightEventChangeValue.setRightFrontReadingLampStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R /* 557860659 */:
                sLightEventChangeValue.setLeftMiddleRowSideRLightSwitchR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R /* 557860660 */:
                sLightEventChangeValue.setRightMiddleRowRLightSwitchR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R /* 557860661 */:
                sLightEventChangeValue.setLeftMiddleRowRLightRotaryStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R /* 557860662 */:
                sLightEventChangeValue.setRightMiddleRowRLightRotaryR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_REAR_R_LIGHT_SWITCH_R /* 557860663 */:
                sLightEventChangeValue.setLeftRearRLightSwitchR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_REAR_R_LIGHT_SWITCH_R /* 557860664 */:
                sLightEventChangeValue.setRightRearRLightSwitchR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_STATE_R /* 557860665 */:
                sLightEventChangeValue.setCargoLightSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_CONFIG_R /* 557860666 */:
                sLightEventChangeValue.setCargoLightSwitchConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_CARGO_LIGHT_SWITCH_STATE_S /* 557860667 */:
                sLightEventChangeValue.setCargoLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_CORNER_LIGHT_STATUS_R /* 557860668 */:
                sLightEventChangeValue.setLeftCornerLightStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_CORNER_LIGHT_STATUS_R /* 557860669 */:
                sLightEventChangeValue.setRightCornerLightStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_FRONT_FOG_LAMP_STATUS_R /* 557860670 */:
                sLightEventChangeValue.setFrontFogLampStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_FRONT_CENTER_LIGHT_STATUS_R /* 557860671 */:
                sLightEventChangeValue.setFrontCenterLightStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R /* 557860672 */:
                sLightEventChangeValue.setPositionLightDisplayFeedCallbackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_POSITION_LIGHT_DISPLAY_S /* 557860673 */:
                sLightEventChangeValue.setPositionLightDisplayS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_DAYTIME_LIGHT_STATE_R /* 557860674 */:
                sLightEventChangeValue.setRightDaytimeLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_DAYTIME_LIGHT_STATE_R /* 557860675 */:
                sLightEventChangeValue.setLeftDaytimeLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_HIGHLIGHT_POSITION_S /* 557860676 */:
                sLightEventChangeValue.setHighlightPositionS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_HIGHTLIGHT_POSITION_R /* 557860677 */:
                sLightEventChangeValue.setHightlightPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_HIGHTLIGHT_POSITION_CONFIG_R /* 557860678 */:
                sLightEventChangeValue.setHightlightPositionConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_INTELLIGENT_POSTION_FEEDBACK_R /* 557860679 */:
                sLightEventChangeValue.setIntelligentPostionFeedbackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R /* 557860680 */:
                sLightEventChangeValue.setLeftFrontPositionSwlStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R /* 557860681 */:
                sLightEventChangeValue.setRightFrontPositionSwlStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_DAYTIME_RUN_LAMP_STATE_R /* 557860682 */:
                sLightEventChangeValue.setLeftDaytimeRunLampStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R /* 557860683 */:
                sLightEventChangeValue.setRightDaytimeRunLampStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_FRONT_FOG_LIGHT_SWITCH_STATE_R /* 557860688 */:
                sLightEventChangeValue.setFrontFogLightSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_FRONT_FOG_LIGHT_CONFIG_R /* 557860689 */:
                sLightEventChangeValue.setFrontFogLightConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_ALL_WEATHER_LIGHT_SWITCH_STATE_S /* 557860691 */:
                sLightEventChangeValue.setAllWeatherLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_FRONT_FOG_LIGHT_SWITCH_STATE_S /* 557860692 */:
                sLightEventChangeValue.setFrontFogLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_LEFT_REMOTE_LIGHT_STATE_R /* 557860693 */:
                sLightEventChangeValue.setLeftRemoteLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_RIGHT_REMOTE_LIGHT_STATE_R /* 557860694 */:
                sLightEventChangeValue.setRightRemoteLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorLightManager.ID_MIDDLE_STERN_LAMP_STATE_R /* 557860695 */:
                sLightEventChangeValue.setMiddleSternLampStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860696:
                sLightEventChangeValue.setAmbientStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860697:
                sLightEventChangeValue.setAmbientStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860698:
                sLightEventChangeValue.setAmbientSwitchConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860699:
                sLightEventChangeValue.setLightRearGlowLogoWorkStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860700:
                sLightEventChangeValue.setLightAdasIndicatorStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860701:
                sLightEventChangeValue.setLightFrontGlowLogoWorkStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860702:
                sLightEventChangeValue.setAtmosphereLampBodyDisplayR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860703:
                sLightEventChangeValue.setAtmospereLightNightWeakenModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860704:
                sLightEventChangeValue.setAtmospereLightNightWeakenModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860705:
                sLightEventChangeValue.setAtmospereLightNightWeakenModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860706:
                sLightEventChangeValue.setAtmospereLampPopupPromptR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860707:
                sLightEventChangeValue.setThemeColorSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860708:
                sLightEventChangeValue.setThemeLinkedAmbienceLightColorS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860709:
                sLightEventChangeValue.setAtmospereLampUiDisplayR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860710:
                sLightEventChangeValue.setThemeColorFeedBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860711:
                sLightEventChangeValue.setThemeLinkedAtmosphereLampSwitchS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860712:
                sLightEventChangeValue.setThemeColorFeatureFlagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557870867:
                sLightEventChangeValue.setStopLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557879809:
                sLightEventChangeValue.setLightSideStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557857169:
                        sLightEventChangeValue.setAllWeatherLightSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857170:
                        sLightEventChangeValue.setHeadlightControlModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857171:
                        sLightEventChangeValue.setHeadlightControlModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857172:
                        sLightEventChangeValue.setRearFogLightSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857173:
                        sLightEventChangeValue.setRearFogLightSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557857710:
                                sLightEventChangeValue.setIalColorStarRingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557857711:
                                sLightEventChangeValue.setIalColorStarrySkyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557857712:
                                sLightEventChangeValue.setIalColorFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557857713:
                                sLightEventChangeValue.setIalColorBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557857716:
                                        sLightEventChangeValue.setIalBrightnessStarRingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857717:
                                        sLightEventChangeValue.setIalBrightnessStarrySkyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857718:
                                        sLightEventChangeValue.setIalBrightnessBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857719:
                                        sLightEventChangeValue.setIalBrightnessFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557860608:
                                                sLightEventChangeValue.setLightAutoStatusOne(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860609:
                                                sLightEventChangeValue.setLightAutoStatusTwo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860610:
                                                sLightEventChangeValue.setAFSSwitch(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860611:
                                                sLightEventChangeValue.setAmbientMusicModeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860612:
                                                sLightEventChangeValue.setAmbientMusicModeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860613:
                                                sLightEventChangeValue.setAmbientMusicModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860614:
                                                sLightEventChangeValue.setAmbientMusicModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860615:
                                                sLightEventChangeValue.setAmbientMulticolorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860616:
                                                sLightEventChangeValue.setAmbientMulticolorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860617:
                                                sLightEventChangeValue.setAmbientMulticolorModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860618:
                                                sLightEventChangeValue.setAmbientMulticolorModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860619:
                                                sLightEventChangeValue.setDayTimeLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557860620:
                                                sLightEventChangeValue.setDayTimeLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 557860624:
                                                        sLightEventChangeValue.setTurnLightFlashStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557860625:
                                                        sLightEventChangeValue.setTurnLightFlashStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557860626:
                                                        sLightEventChangeValue.setAFSSwitchSate(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557860627:
                                                        sLightEventChangeValue.setResetDelayLightParamS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case CarVendorLightManager.ID_REVERSING_LIGHT_STATE /* 557860629 */:
                                                                sLightEventChangeValue.setReversingLightState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case CarVendorLightManager.ID_TURN_LIGHT_STATE /* 557860630 */:
                                                                sLightEventChangeValue.setTurnLightState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557860631:
                                                                sLightEventChangeValue.setAmbientFeedBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557860633:
                                                                        sLightEventChangeValue.setReversingLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557860634:
                                                                        sLightEventChangeValue.setTurnLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557860635:
                                                                        sLightEventChangeValue.setAmbientFeedBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557860637:
                                                                                sLightEventChangeValue.setLightLowBeamStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557860638:
                                                                                sLightEventChangeValue.setLightHighBeamR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case 557860642:
                                                                                        sLightEventChangeValue.setLightRearFog(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557860643:
                                                                                        sLightEventChangeValue.setDayTimeRunningLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557860644:
                                                                                        sLightEventChangeValue.setLightRearPosition(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorLightManager.TAG, "break other properties ,except light");
                                                                                        break;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
        Log.debug(CarVendorLightManager.TAG, "convertCarVendorPropertyValue " + sLightEventChangeValue.toString(), DEBUG_LOGGER);
        return sLightEventChangeValue;
    }

    private OnCarLightChangeEventListenerProxy findListenerLocked(CarVendorLightManager.OnCarLightEventListener listener) {
        for (OnCarLightChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarLightChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorLightManager.OnCarLightEventListener listener;

        public OnCarLightChangeEventListenerProxy(CarVendorLightManager.OnCarLightEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorLightMgr.this.traceCallbackPrepare();
                LightEventChangeValue newValue = CarVendorLightMgr.convertCarVendorProperty(value);
                CarVendorLightMgr.this.traceCallbackBefore();
                this.listener.onLightEventChange(newValue);
                CarVendorLightMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorLightManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorLightManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onLightErrorEvent(propertyId, zone);
        }
    }
}
