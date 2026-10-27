package com.ts.lib.car.hardware.carbody;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class CarVendorCarbodyMgr extends CarVendorManagerBase implements CarVendorCarbodyManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static CarbodyEventChangeValue sCarbodyEventChangeValue = new CarbodyEventChangeValue();
    private final LinkedList<OnCarCarbodyChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtCarbodyIds = {557862965, 557862931, 557862930, 557862926, 557862948, 557862943, 557862945, 557862947, 557862944, 557862946, 557862942, 557862927, 557862936, 557862937, 557862940, 557862938, 557862939, 557862941, 557862912, 557862913, 557862914, 557862915, 557862951, 557862952, 557862949, 557862950, 557862981, 557862978, 557862975, 557862980, 557862982, 557862985, 557862987, 557862983, 557862953, 557862973, 557862974, 557862972, 557862986, 557862988, 557862984, 557862979, 557862968, 557862967, 557862970, 557862969, 557862966, Integer.valueOf(CarVendorCarbodyManager.ID_WINDOWBLIND_STATER), 557862954, 557862956, 557862955, 557862957, 557862959, 557862960, 557862963, 557862964, 557862958, 557862961, 557862962, 557875227, 557862991, Integer.valueOf(CarVendorCarbodyManager.ID_ANTI_THEFT_STATUS_R), 557862916, 557862918, 557862917, 557862919, 557863013, 557863002, 557863005, 557863009, 557862999, 557862998, 557863008, 557863003, 557862995, 557863001, 557863011, 557863000, 557863010, 557863004, 557863007, 557862997, 557863006, 557862996, 557845265, 557860644, 557858049, 557858048, 559954814, 559954815, 559954812, 559954813, 559954810, 559954811, 557863023, 557863022, 557863024, 557863017, 557863016, 557860642, 557857806, 557857805, 557863019, 557857850, 557945600, 557945603, 557945605, 557945606, 557945607, 557945608, 557945609, 557945610, 557945611, 557945612, 557945613, 557945614, 557945615, 557945616, 557945617, 557945618, 557945619, 557945620, 557945621, 557945622, 557945623, 557945624, 557945625, 557945626, 557945627, 557945628, 557945629, 557945630, 557945631, 557945632, 557945633, 557945634, 557945635, 557945636, 557945637, 557945638, 557945639, 557945640, 557945641, 557945642, 557945643, 557945644, 557945645, 557945646, 557945647, 557945648, 557945649, 557945650, 557945651, 557945652, 557945653, 557945654, 557945655, 557945656, 557945657, 557945658, 557945659, 557945660, 557945661, 557945662, 557945663, 557945664, 557945763, 557945665, 557945666, 557945667, 557945668, 557945669, 557945670, 557945671, 557945672, 557945673, 557945674, 557945675, 557945676, 557945677, 557945678, 557945679, 557945680, 557945681, 557945682, 557945683, 557945684, 557945685, 557945686, 557945687, 557945688, 557945689, 557945690, 557945691, 557945692, 557945693, 557945694, 557945695, 557945696, 557945697, 557945698, 557945699, 557945700, 557945701, 557945702, 557945703, 557945704, 557945705, 557945706, 557945707, 557945708, 557945709, 557945710, 557945711, 557945712, 557945713, 557945714, 557945715, 557945716, 557945717, 557945718, 557945719, 557945720, 557945721, 557945722, 557945723, 557945724, 557945725, 557945726, 557945727, 557945728, 557945729, 557945730, 557945731, 557945732, 557945733, 557945734, 557945735, 557945736, 557945737, 557945738, 557945739, 557945740, 557945741, 557945742, 557945743, 557945744, 557945745, 557945746, 557945747, 557945748, 557945749, 557945750, 557945751, 557945752, 557945753, 557945754, 557945755, 557945756, 557945757, 557945758, 557945759, 557945760, 557945761, 557945762, 557945764, 557945765, 557945766, 557945767, 557945768, 557945769, 557945770, 557945771, 557945772, 557945773, 557945774, 557945775, 557945777, 557945778, Integer.valueOf(CarVendorCarbodyManager.ID_MOON_ROOF_PERCENT_CONTROL_FLAG_R), Integer.valueOf(CarVendorCarbodyManager.ID_AUTO_TYPE_NEW), Integer.valueOf(CarVendorCarbodyManager.ID_ELECTRIC_PEDAL_CONFIG_R), Integer.valueOf(CarVendorCarbodyManager.ID_FOUR_PEOPLE_ENJOY_CONFIG_R), 557863044, 557863042, 557863045, 557863043, Integer.valueOf(CarVendorCarbodyManager.ID_MOON_ROOF_CONFIG_R), Integer.valueOf(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_LEFT_R), Integer.valueOf(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_RIGHT_R), Integer.valueOf(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_LEFT_R), Integer.valueOf(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_RIGHT_R), 557863041, 557868039, 557859175, Integer.valueOf(CarVendorCarbodyManager.ID_WINDOW_PERCENTAGE_CONTROL_CONFIG_R), 557863049, 557863051, 557863050, 557863052, 557863053, 557863055, 557863054, 557863056, Integer.valueOf(CarVendorCarbodyManager.ID_AWNING_CONFIGUTATION_R), 557863057, 557863061, 557863062, 557863063, 557863064, Integer.valueOf(CarVendorCarbodyManager.ID_SYSTEM_CONFIG_R), 557863072, 557863075, 557863071, 557863068, 557863070, 557863073, 557863069, 557863074, 557863076, 557863079, 557863078, 557863081, 557863077, 557863080, 557863067, 557863086, 557863087};

    public CarVendorCarbodyMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorCarbodyManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorCarbodyManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorCarbodyManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorCarbodyManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLeftMidDoorVoiceWakeUpChangedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863086, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getLeftMidDoorVoiceWakeUpChangedR value = " + value + " property: 0x" + tenToHex(557863086));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRightMidDoorVoiceWakeUpChangedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863087, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getRightMidDoorVoiceWakeUpChangedR value = " + value + " property: 0x" + tenToHex(557863087));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getFourPeopleEnjoyConfig2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863072, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getFourPeopleEnjoyConfig2R value = " + value + " property: 0x" + tenToHex(557863072));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getFseLagerBedModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863075, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getFseLagerBedModeConfigR value = " + value + " property: 0x" + tenToHex(557863075));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setFseLargeBedModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setFseLargeBedModeS value = " + value + " property: 0x" + tenToHex(557863083));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557863083, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getFseSeatSystemWorkModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863071, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getFseSeatSystemWorkModeR value = " + value + " property: 0x" + tenToHex(557863071));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setLargeBedModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setLargeBedModeS value = " + value + " property: 0x" + tenToHex(557863082));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557863082, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLeftSeatRailsRow3FeedbackPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863068, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getLeftSeatRailsRow3FeedbackPositionR value = " + value + " property: 0x" + tenToHex(557863068));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLeftSeatRow3NonElectricallyAdjustStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863070, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getLeftSeatRow3NonElectricallyAdjustStateR value = " + value + " property: 0x" + tenToHex(557863070));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRestoreSeatConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863073, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getRestoreSeatConfigR value = " + value + " property: 0x" + tenToHex(557863073));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRightSeatRailsRow3FeedbackPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863069, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getRightSeatRailsRow3FeedbackPositionR value = " + value + " property: 0x" + tenToHex(557863069));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatLargeBedModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863074, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatLargeBedModeConfigR value = " + value + " property: 0x" + tenToHex(557863074));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatModeCurFeedbackStatus2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863076, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatModeCurFeedbackStatus2R value = " + value + " property: 0x" + tenToHex(557863076));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatModeCurFeedbackStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863079, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatModeCurFeedbackStatusR value = " + value + " property: 0x" + tenToHex(557863079));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatModeFailFeedbackStatus2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863078, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatModeFailFeedbackStatus2R value = " + value + " property: 0x" + tenToHex(557863078));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatModeFailFeedbackStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863081, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatModeFailFeedbackStatusR value = " + value + " property: 0x" + tenToHex(557863081));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatModeSuccessFeedbackStatus2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863077, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatModeSuccessFeedbackStatus2R value = " + value + " property: 0x" + tenToHex(557863077));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatModeSuccessFeedbackStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863080, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatModeSuccessFeedbackStatusR value = " + value + " property: 0x" + tenToHex(557863080));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setSeatRow3OnekeyEnterPitS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setSeatRow3OnekeyEnterPitS value = " + value + " property: 0x" + tenToHex(557863084));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557863084, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getThirdRowSeatsExecutionResultsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863067, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getThirdRowSeatsExecutionResultsR value = " + value + " property: 0x" + tenToHex(557863067));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setModeCommandS(int[] arr) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setModeCommandS arr = " + arr + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_MODE_COMMAND_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorCarbodyManager.ID_MODE_COMMAND_S, 0, CastArrayUtil.toWrap(arr));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setModeCommandBs(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setModeCommandBs value = " + value + " property: 0x" + tenToHex(557928602));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, 557928602, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setModeCommandCs(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setModeCommandCs value = " + value + " property: 0x" + tenToHex(557928621));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, 557928621, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan464R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945773, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan464R property: 0x");
            sb.append(tenToHex(557945773));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan338R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945774, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan338R property: 0x");
            sb.append(tenToHex(557945774));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan145R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945775, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan145R property: 0x");
            sb.append(tenToHex(557945775));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateByRearBCMLFR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863061, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateByRearBCMLFR value = " + value + " property: 0x" + tenToHex(557863061));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateByRearBCMLRR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863062, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateByRearBCMLRR value = " + value + " property: 0x" + tenToHex(557863062));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateByRearBCMRFR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863063, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateByRearBCMRFR value = " + value + " property: 0x" + tenToHex(557863063));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateByRearBCMRRR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863064, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateByRearBCMRRR value = " + value + " property: 0x" + tenToHex(557863064));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSystemConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_SYSTEM_CONFIG_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSystemConfigR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_SYSTEM_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getAwningConfigutationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_AWNING_CONFIGUTATION_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getAwningConfigutationR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_AWNING_CONFIGUTATION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getEvReadyIndicatorLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863057, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getEvReadyIndicatorLightStateR value = " + value + " property: 0x" + tenToHex(557863057));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setSideWindowShadeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setSideWindowShadeStateS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_SIDE_WINDOW_SHADE_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_SIDE_WINDOW_SHADE_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorLLeftRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863051, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorLLeftRightR value = " + value + " property: 0x" + tenToHex(557863051));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorLeftLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863049, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorLeftLeftR value = " + value + " property: 0x" + tenToHex(557863049));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorRightLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863050, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorRightLeftR value = " + value + " property: 0x" + tenToHex(557863050));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorRightRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863052, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorRightRightR value = " + value + " property: 0x" + tenToHex(557863052));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorSlidingLeftLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863053, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorSlidingLeftLeftR value = " + value + " property: 0x" + tenToHex(557863053));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorSlidingLeftRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863055, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorSlidingLeftRightR value = " + value + " property: 0x" + tenToHex(557863055));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorSlidingRightLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863054, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorSlidingRightLeftR value = " + value + " property: 0x" + tenToHex(557863054));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getScreenControlSlideDoorSlidingRightRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863056, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getScreenControlSlideDoorSlidingRightRightR value = " + value + " property: 0x" + tenToHex(557863056));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getAutoTypeNew() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_AUTO_TYPE_NEW, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getAutoTypeNew value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_AUTO_TYPE_NEW));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getElectricPedalConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_ELECTRIC_PEDAL_CONFIG_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getElectricPedalConfigR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_ELECTRIC_PEDAL_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getFourPeopleEnjoyConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_FOUR_PEOPLE_ENJOY_CONFIG_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getFourPeopleEnjoyConfigR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_FOUR_PEOPLE_ENJOY_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateLeftBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863044, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateLeftBackR value = " + value + " property: 0x" + tenToHex(557863044));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateLeftFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863042, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateLeftFrontR value = " + value + " property: 0x" + tenToHex(557863042));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateRightBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863045, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateRightBackR value = " + value + " property: 0x" + tenToHex(557863045));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getInternalDoorHandleStateRightFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863043, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getInternalDoorHandleStateRightFrontR value = " + value + " property: 0x" + tenToHex(557863043));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getMoonRoofConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_MOON_ROOF_CONFIG_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getMoonRoofConfigR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_MOON_ROOF_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatLumbarSupportConfigCoPilotLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_LEFT_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatLumbarSupportConfigCoPilotLeftR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_LEFT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatLumbarSupportConfigCoPilotRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_RIGHT_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatLumbarSupportConfigCoPilotRightR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_RIGHT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatLumbarSupportConfigMainLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_LEFT_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatLumbarSupportConfigMainLeftR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_LEFT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatLumbarSupportConfigMainRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_RIGHT_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatLumbarSupportConfigMainRightR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_RIGHT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setSeatModeControlS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setSeatModeControlS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_SEAT_MODE_CONTROL_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_SEAT_MODE_CONTROL_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSeatModeWakeupFeedbackStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863041, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSeatModeWakeupFeedbackStateR value = " + value + " property: 0x" + tenToHex(557863041));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getShiftModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557868039, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getShiftModeR value = " + value + " property: 0x" + tenToHex(557868039));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getTailDoorTypeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859175, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getTailDoorTypeConfigR value = " + value + " property: 0x" + tenToHex(557859175));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWaistSupportAdjustmentCoPilotS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWaistSupportAdjustmentCoPilotS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_WAIST_SUPPORT_ADJUSTMENT_CO_PILOT_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_WAIST_SUPPORT_ADJUSTMENT_CO_PILOT_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWaistSupportAdjustmentMainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWaistSupportAdjustmentMainS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_WAIST_SUPPORT_ADJUSTMENT_MAIN_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_WAIST_SUPPORT_ADJUSTMENT_MAIN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowPercentageControlConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_WINDOW_PERCENTAGE_CONTROL_CONFIG_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getWindowPercentageControlConfigR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_WINDOW_PERCENTAGE_CONTROL_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRecoverOrSaveParamsChanged5() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857850, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRecoverOrSaveParamsChanged5 property: 0x");
            sb.append(tenToHex(557857850));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRearWindowblindInitStateNewR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863019, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getRearWindowblindInitStateNewR value = " + value + " property: 0x" + tenToHex(557863019), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setVoiceCtlMoonRoofS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setVoiceCtlMoonRoofS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_VOICE_CTL_MOON_ROOF_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_VOICE_CTL_MOON_ROOF_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setVoiceCtlSunshadePanelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setVoiceCtlSunshadePanelS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_VOICE_CTL_SUNSHADE_PANEL_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_VOICE_CTL_SUNSHADE_PANEL_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getFuelElecLowPowerR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557845265, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFuelElecLowPowerR property: 0x");
            sb.append(tenToHex(557845265));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLightSideStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557879809, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightSideStatusR property: 0x");
            sb.append(tenToHex(557879809));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLightLowBeamStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860637, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightLowBeamStatusR property: 0x");
            sb.append(tenToHex(557860637));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLightHighBeamR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860638, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightHighBeamR property: 0x");
            sb.append(tenToHex(557860638));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLightRearFog() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860642, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightRearFog property: 0x");
            sb.append(tenToHex(557860642));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getBcmVechileStatus() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863017, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getBCMVechileStatus value = " + value + " property: 0x" + tenToHex(557863017));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSmartVoiceLimit() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863016, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getSmartVoiceLimit value = " + value + " property: 0x" + tenToHex(557863016));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getReadyIndicatorLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863013, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReadyIndicatorLightStateR property: 0x");
            sb.append(tenToHex(557863013));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRearviewMirrorSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856147, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorSupport property: 0x");
            sb.append(tenToHex(557856147));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLightRearPosition() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860644, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightRearPosition property: 0x");
            sb.append(tenToHex(557860644));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowAntiPinchConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856209, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowAntiPinchConfigFirst property: 0x");
            sb.append(tenToHex(557856209));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setAllDoorsLock(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setAllDoorsLock property: 0x" + tenToHex(557856519) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856519, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorLockStatusAreaRightRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863024, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusAreaRightRear property: 0x");
            sb.append(tenToHex(557863024));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorLockStatusLeftRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863023, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusLeftRear property: 0x");
            sb.append(tenToHex(557863023));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorLockStatusRightFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863022, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusRightFront property: 0x");
            sb.append(tenToHex(557863022));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setturnSteeringWheel(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setturnSteeringWheel property: 0x" + tenToHex(557857653) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857653, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setturnRearviewMirror(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setturnRearviewMirror property: 0x" + tenToHex(557857652) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857652, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public float getRearviewMirrorXLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954814, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorXLeft property: 0x");
            sb.append(tenToHex(559954814));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public float getRearviewMirrorXRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954815, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorXRight property: 0x");
            sb.append(tenToHex(559954815));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public float getRearviewMirrorYLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954812, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorYLeft property: 0x");
            sb.append(tenToHex(559954812));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public float getRearviewMirrorYRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954813, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorYRight property: 0x");
            sb.append(tenToHex(559954813));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public float getSteeringWheelY() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954810, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelY property: 0x");
            sb.append(tenToHex(559954810));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public float getSteeringWheelZ() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954811, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelZ property: 0x");
            sb.append(tenToHex(559954811));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getAutoExternalRearMirrorFoldActionStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858048, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoExternalRearMirrorFoldActionStateR property: 0x");
            sb.append(tenToHex(557858048));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setAutoExternalRearMirrorFoldStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setAutoExternalRearMirrorFoldStateS property: 0x" + tenToHex(557858050) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858050, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getAutoExternalRearMirrorFoldedStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858049, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoExternalRearMirrorFoldedStateR property: 0x");
            sb.append(tenToHex(557858049));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinEightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863002, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinEightR property: 0x");
            sb.append(tenToHex(557863002));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinElevenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863005, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinElevenR property: 0x");
            sb.append(tenToHex(557863005));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinFifteenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863009, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinFifteenR property: 0x");
            sb.append(tenToHex(557863009));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinFiveR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862999, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinFiveR property: 0x");
            sb.append(tenToHex(557862999));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getonSaveSeatParamsAllChangedPlace7() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857806, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSaveSeatParamsAllChangedPlace7 property: 0x");
            sb.append(tenToHex(557857806));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getonSaveSeatParamsAllChangedPlace5() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857805, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSaveSeatParamsAllChangedPlace5 property: 0x");
            sb.append(tenToHex(557857805));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinFourR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862998, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinFourR property: 0x");
            sb.append(tenToHex(557862998));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinFourteenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863008, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinFourteenR property: 0x");
            sb.append(tenToHex(557863008));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinNineR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863003, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinNineR property: 0x");
            sb.append(tenToHex(557863003));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinOneR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862995, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinOneR property: 0x");
            sb.append(tenToHex(557862995));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinSevenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863001, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinSevenR property: 0x");
            sb.append(tenToHex(557863001));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinSeventeenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863011, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinSeventeenR property: 0x");
            sb.append(tenToHex(557863011));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinSixR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863000, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinSixR property: 0x");
            sb.append(tenToHex(557863000));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinSixteenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863010, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinSixteenR property: 0x");
            sb.append(tenToHex(557863010));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinTenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863004, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinTenR property: 0x");
            sb.append(tenToHex(557863004));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinThirteenR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863007, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinThirteenR property: 0x");
            sb.append(tenToHex(557863007));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinThreeR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862997, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinThreeR property: 0x");
            sb.append(tenToHex(557862997));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinTwelveR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557863006, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinTwelveR property: 0x");
            sb.append(tenToHex(557863006));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRealAutoVinTwoR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862996, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRealAutoVinTwoR property: 0x");
            sb.append(tenToHex(557862996));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setSettingLuggageS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setSettingLuggageS property: 0x" + tenToHex(557863012) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557863012, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getBatteryVoltageLevelLowTips() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875227, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryVoltageLevelLowTips property: 0x");
            sb.append(tenToHex(557875227));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getAntiTheftStatusR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_ANTI_THEFT_STATUS_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAntiTheftStatusR property: 0x");
            sb.append(tenToHex(CarVendorCarbodyManager.ID_ANTI_THEFT_STATUS_R));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowOpenPercentRearPanelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862991, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowOpenPercentRearPanelR property: 0x");
            sb.append(tenToHex(557862991));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getAutoSystemState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862931, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoSystemState property: 0x");
            sb.append(tenToHex(557862931));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getAutoType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862930, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoType property: 0x");
            sb.append(tenToHex(557862930));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getBatteryVoltageLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862926, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryVoltageLevel property: 0x");
            sb.append(tenToHex(557862926));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorStateHood() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862948, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStateHood property: 0x");
            sb.append(tenToHex(557862948));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorStateLeftFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862943, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStateLeftFront property: 0x");
            sb.append(tenToHex(557862943));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorStateLeftRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862945, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStateLeftRear property: 0x");
            sb.append(tenToHex(557862945));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorStateLuggage() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862947, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStateLuggage property: 0x");
            sb.append(tenToHex(557862947));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorStateRightFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862944, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStateRightFront property: 0x");
            sb.append(tenToHex(557862944));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getDoorStateRightRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862946, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStateRightRear property: 0x");
            sb.append(tenToHex(557862946));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getMoonRoofConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862942, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMoonRoofConfig property: 0x");
            sb.append(tenToHex(557862942));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getPowerLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862927, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPowerLevel property: 0x");
            sb.append(tenToHex(557862927));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSteeringWheelValueAngel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862928, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelValueAngel property: 0x");
            sb.append(tenToHex(557862928));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSteeringWheelValueSpeed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862929, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelValueSpeed property: 0x");
            sb.append(tenToHex(557862929));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowControlStateLeftFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862916, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowControlStateLeftFrontR property: 0x");
            sb.append(tenToHex(557862916));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowControlStateLeftRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862918, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowControlStateLeftRearR property: 0x");
            sb.append(tenToHex(557862918));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowControlState(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowControlStateLeftFrontS property: 0x" + tenToHex(CarVendorCarbodyManager.ID_WINDOW_CONTROL_STATE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorCarbodyManager.ID_WINDOW_CONTROL_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowControlStateLeftFrontS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowControlStateLeftFrontS property: 0x" + tenToHex(557862920) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862920, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowControlStateLeftRearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowControlStateLeftRearS property: 0x" + tenToHex(557862922) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862922, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowControlStateRightFrontS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowControlStateRightFrontS property: 0x" + tenToHex(557862921) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862921, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowControlStateRightRearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowControlStateRightRearS property: 0x" + tenToHex(557862923) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862923, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowControlStateRightFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862917, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowControlStateRightFrontR property: 0x");
            sb.append(tenToHex(557862917));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowControlStateRightRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862919, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowControlStateRightRearR property: 0x");
            sb.append(tenToHex(557862919));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowOpenPercentLeftFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862936, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowOpenPercentLeftFrontR property: 0x");
            sb.append(tenToHex(557862936));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowOpenPercentLeftRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862937, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowOpenPercentLeftRearR property: 0x");
            sb.append(tenToHex(557862937));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowOpenPercentMoonR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862940, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowOpenPercentMoonR property: 0x");
            sb.append(tenToHex(557862940));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowOpenPercentMoonS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowOpenPercentMoonS property: 0x" + tenToHex(557862924) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862924, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowOpenPercentRightFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862938, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowOpenPercentRightFrontR property: 0x");
            sb.append(tenToHex(557862938));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowOpenPercentRightRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862939, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowOpenPercentRightRearR property: 0x");
            sb.append(tenToHex(557862939));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowOpenPercentSunR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862941, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowOpenPercentSunR property: 0x");
            sb.append(tenToHex(557862941));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowOpenPercentSunS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowOpenPercentSunS property: 0x" + tenToHex(557862925) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862925, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowStateLeftFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862912, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowStateLeftFront property: 0x");
            sb.append(tenToHex(557862912));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowStateLeftRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862913, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowStateLeftRear property: 0x");
            sb.append(tenToHex(557862913));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowStateRightFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862914, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowStateRightFront property: 0x");
            sb.append(tenToHex(557862914));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowStateRightRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862915, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowStateRightRear property: 0x");
            sb.append(tenToHex(557862915));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getEnergyTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862951, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnergyTypeR property: 0x");
            sb.append(tenToHex(557862951));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSunRoofStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862952, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSunRoofStateR property: 0x");
            sb.append(tenToHex(557862952));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowStateMoonRoofR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862949, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowStateMoonRoofR property: 0x");
            sb.append(tenToHex(557862949));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowStateSunshadeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862950, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowStateSunshadeR property: 0x");
            sb.append(tenToHex(557862950));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getBackDoorAntiPlayModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862981, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackDoorAntiPlayModeR property: 0x");
            sb.append(tenToHex(557862981));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getBackDoorCurStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862978, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackDoorCurStateR property: 0x");
            sb.append(tenToHex(557862978));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setBackDoorHeightPositionR(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setBackDoorHeightPositionR property: 0x" + tenToHex(557862977) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862977, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getBackDoorInitStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862975, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackDoorInitStateR property: 0x");
            sb.append(tenToHex(557862975));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getBackDoorPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862980, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackDoorPositionR property: 0x");
            sb.append(tenToHex(557862980));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setElecBackDoorControlStateR(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setElecBackDoorControlStateR property: 0x" + tenToHex(CarVendorCarbodyManager.ID_ELEC_BACK_DOOR_CONTROL_STATER) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_ELEC_BACK_DOOR_CONTROL_STATER, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setBackDoorOpenedHeightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setBackDoorOpenedHeightS property: 0x" + tenToHex(557857562) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857562, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getElecSlideDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862982, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElecSlideDoorStateR property: 0x");
            sb.append(tenToHex(557862982));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLeftSlideDoorActionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862985, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftSlideDoorActionR property: 0x");
            sb.append(tenToHex(557862985));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLeftSlideDoorPercentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862987, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftSlideDoorPercentR property: 0x");
            sb.append(tenToHex(557862987));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLeftSlideDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862983, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftSlideDoorStateR property: 0x");
            sb.append(tenToHex(557862983));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setLeftSlideDoorVoiceControlStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setLeftSlideDoorVoiceControlStateS property: 0x" + tenToHex(CarVendorCarbodyManager.ID_LEFT_SLIDE_DOOR_VOICE_CONTROL_STATES) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_LEFT_SLIDE_DOOR_VOICE_CONTROL_STATES, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getLeftStrutMorotRunningState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862953, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftStrutMorotRunningState property: 0x");
            sb.append(tenToHex(557862953));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRearWindowblindInitStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862973, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearWindowblindInitStateR property: 0x");
            sb.append(tenToHex(557862973));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRearWindowblindPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862974, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearWindowblindPositionR property: 0x");
            sb.append(tenToHex(557862974));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRearWindowblindStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862972, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearWindowblindStateR property: 0x");
            sb.append(tenToHex(557862972));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRightSlideDoorActionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862986, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightSlideDoorActionR property: 0x");
            sb.append(tenToHex(557862986));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRightSlideDoorPercentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862988, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightSlideDoorPercentR property: 0x");
            sb.append(tenToHex(557862988));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRightSlideDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862984, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightSlideDoorStateR property: 0x");
            sb.append(tenToHex(557862984));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setRightSlideDoorVoiceControlStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setRightSlideDoorVoiceControlStateS property: 0x" + tenToHex(557862990) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862990, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getRightStrutMorotRunningStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862979, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightStrutMorotRunningStateR property: 0x");
            sb.append(tenToHex(557862979));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSunroofCloseNoticeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862968, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSunroofCloseNoticeR property: 0x");
            sb.append(tenToHex(557862968));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSunroofInitStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862967, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSunroofInitStateR property: 0x");
            sb.append(tenToHex(557862967));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSunroofPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862970, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSunroofPositionR property: 0x");
            sb.append(tenToHex(557862970));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getSunroofWindowblindPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862969, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSunroofWindowblindPositionR property: 0x");
            sb.append(tenToHex(557862969));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowInitStateFL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862954, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowInitStateFL property: 0x");
            sb.append(tenToHex(557862954));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowInitStateFR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862956, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowInitStateFR property: 0x");
            sb.append(tenToHex(557862956));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowInitStateRL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862955, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowInitStateRL property: 0x");
            sb.append(tenToHex(557862955));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowInitStateRR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862957, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowInitStateRR property: 0x");
            sb.append(tenToHex(557862957));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowThermalProtectionStateFL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862958, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowThermalProtectionStateFL property: 0x");
            sb.append(tenToHex(557862958));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowThermalProtectionStateFR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862960, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowThermalProtectionStateFR property: 0x");
            sb.append(tenToHex(557862960));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowThermalProtectionStateFrontSunshade() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862963, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowThermalProtectionStateFrontSunshade property: 0x");
            sb.append(tenToHex(557862963));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowThermalProtectionStateRL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862959, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowThermalProtectionStateRL property: 0x");
            sb.append(tenToHex(557862959));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowThermalProtectionStateRR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862961, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowThermalProtectionStateRR property: 0x");
            sb.append(tenToHex(557862961));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowThermalProtectionStateRearSunshade() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862964, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowThermalProtectionStateRearSunshade property: 0x");
            sb.append(tenToHex(557862964));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowThermalProtectionStateSkylight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862962, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowThermalProtectionStateSkylight property: 0x");
            sb.append(tenToHex(557862962));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowblindInitStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862966, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowblindInitStateR property: 0x");
            sb.append(tenToHex(557862966));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowblindStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_WINDOWBLIND_STATER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowblindStateR property: 0x");
            sb.append(tenToHex(CarVendorCarbodyManager.ID_WINDOWBLIND_STATER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowOpenPercentRearPanelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowOpenPercentRearPanelS property: 0x" + tenToHex(557862992) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862992, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getWindowPermitStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862965, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowPermitStateR property: 0x");
            sb.append(tenToHex(557862965));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowOpenPercentLeftFrontS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowOpenPercentLeftFrontS property: 0x" + tenToHex(557862932) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862932, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowOpenPercentLeftRearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowOpenPercentLeftRearS property: 0x" + tenToHex(557862933) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862933, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowOpenPercentRightFrontS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowOpenPercentRightFrontS property: 0x" + tenToHex(557862934) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862934, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowOpenPercentRightRearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowOpenPercentRightRearS property: 0x" + tenToHex(557862935) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557862935, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setBackDoorHeightPositionMyCarS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setBackDoorHeightPositionMyCarS property: 0x" + tenToHex(CarVendorCarbodyManager.ID_BACK_DOOR_HEIGHT_POSITION_MYCAR_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_BACK_DOOR_HEIGHT_POSITION_MYCAR_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan055R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945600, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan055R value = " + value + " property: 0x" + tenToHex(557945600));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan0d5R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945603, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan0d5R value = " + value + " property: 0x" + tenToHex(557945603));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan10dR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945605, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan10dR value = " + value + " property: 0x" + tenToHex(557945605));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan121R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945606, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan121R value = " + value + " property: 0x" + tenToHex(557945606));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan123R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945607, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan123R value = " + value + " property: 0x" + tenToHex(557945607));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan12dR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945608, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan12dR value = " + value + " property: 0x" + tenToHex(557945608));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan12fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945609, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan12fR value = " + value + " property: 0x" + tenToHex(557945609));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan133R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945610, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan133R value = " + value + " property: 0x" + tenToHex(557945610));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan138R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945611, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan138R value = " + value + " property: 0x" + tenToHex(557945611));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan13fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945612, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan13fR value = " + value + " property: 0x" + tenToHex(557945612));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan144R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945613, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan144R value = " + value + " property: 0x" + tenToHex(557945613));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan151R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945614, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan151R value = " + value + " property: 0x" + tenToHex(557945614));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan167R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945615, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan167R value = " + value + " property: 0x" + tenToHex(557945615));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan18aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945616, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan18aR value = " + value + " property: 0x" + tenToHex(557945616));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan19fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945617, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan19fR value = " + value + " property: 0x" + tenToHex(557945617));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan1c0R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945618, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan1c0R value = " + value + " property: 0x" + tenToHex(557945618));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan1d1R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945619, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan1d1R value = " + value + " property: 0x" + tenToHex(557945619));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan218R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945620, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan218R value = " + value + " property: 0x" + tenToHex(557945620));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan223R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945621, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan223R value = " + value + " property: 0x" + tenToHex(557945621));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan233R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945622, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan233R value = " + value + " property: 0x" + tenToHex(557945622));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan235R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945623, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan235R value = " + value + " property: 0x" + tenToHex(557945623));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan23aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945624, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan23aR value = " + value + " property: 0x" + tenToHex(557945624));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan242R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945625, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan242R value = " + value + " property: 0x" + tenToHex(557945625));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan245R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945626, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan245R value = " + value + " property: 0x" + tenToHex(557945626));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan248R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945627, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan248R value = " + value + " property: 0x" + tenToHex(557945627));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan24aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945628, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan24aR value = " + value + " property: 0x" + tenToHex(557945628));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan24cR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945629, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan24cR value = " + value + " property: 0x" + tenToHex(557945629));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan25dR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945630, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan25dR value = " + value + " property: 0x" + tenToHex(557945630));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan261R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945631, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan261R value = " + value + " property: 0x" + tenToHex(557945631));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan26fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945632, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan26fR value = " + value + " property: 0x" + tenToHex(557945632));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan271R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945633, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan271R value = " + value + " property: 0x" + tenToHex(557945633));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan272R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945634, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan272R value = " + value + " property: 0x" + tenToHex(557945634));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan275R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945635, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan275R value = " + value + " property: 0x" + tenToHex(557945635));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan27aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945636, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan27aR value = " + value + " property: 0x" + tenToHex(557945636));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan287R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945637, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan287R value = " + value + " property: 0x" + tenToHex(557945637));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan288R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945638, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan288R value = " + value + " property: 0x" + tenToHex(557945638));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan28fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945639, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan28fR value = " + value + " property: 0x" + tenToHex(557945639));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan294R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945640, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan294R value = " + value + " property: 0x" + tenToHex(557945640));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan296R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945641, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan296R value = " + value + " property: 0x" + tenToHex(557945641));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan29cR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945642, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan29cR value = " + value + " property: 0x" + tenToHex(557945642));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan29eR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945643, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan29eR value = " + value + " property: 0x" + tenToHex(557945643));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2a4R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945644, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2a4R value = " + value + " property: 0x" + tenToHex(557945644));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2a6R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945645, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2a6R value = " + value + " property: 0x" + tenToHex(557945645));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2a7R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945646, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2a7R value = " + value + " property: 0x" + tenToHex(557945646));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2b0R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945647, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2b0R value = " + value + " property: 0x" + tenToHex(557945647));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2b4R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945648, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2b4R value = " + value + " property: 0x" + tenToHex(557945648));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2b6R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945649, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2b6R value = " + value + " property: 0x" + tenToHex(557945649));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2baR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945650, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2baR value = " + value + " property: 0x" + tenToHex(557945650));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2bcR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945651, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2bcR value = " + value + " property: 0x" + tenToHex(557945651));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2c0R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945652, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2c0R value = " + value + " property: 0x" + tenToHex(557945652));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2d5R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945653, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2d5R value = " + value + " property: 0x" + tenToHex(557945653));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2ebR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945654, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2ebR value = " + value + " property: 0x" + tenToHex(557945654));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan2f0R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945655, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan2f0R value = " + value + " property: 0x" + tenToHex(557945655));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan30dR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945656, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan30dR value = " + value + " property: 0x" + tenToHex(557945656));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan31eR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945657, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan31eR value = " + value + " property: 0x" + tenToHex(557945657));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan321R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945658, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan321R value = " + value + " property: 0x" + tenToHex(557945658));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan323R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945659, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan323R value = " + value + " property: 0x" + tenToHex(557945659));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan32dR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945660, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan32dR value = " + value + " property: 0x" + tenToHex(557945660));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan334R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945661, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan334R value = " + value + " property: 0x" + tenToHex(557945661));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan337R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945662, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan337R value = " + value + " property: 0x" + tenToHex(557945662));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan341R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945663, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan341R value = " + value + " property: 0x" + tenToHex(557945663));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan342R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945664, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan342R value = " + value + " property: 0x" + tenToHex(557945664));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan344R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945763, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan344R property: 0x");
            sb.append(tenToHex(557945763));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan319R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945764, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan319R property: 0x");
            sb.append(tenToHex(557945764));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan346R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945665, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan346R value = " + value + " property: 0x" + tenToHex(557945665));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan347R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945666, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan347R value = " + value + " property: 0x" + tenToHex(557945666));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan34aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945667, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan34aR value = " + value + " property: 0x" + tenToHex(557945667));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan34fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945668, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan34fR value = " + value + " property: 0x" + tenToHex(557945668));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan36bR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945669, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan36bR value = " + value + " property: 0x" + tenToHex(557945669));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan36dR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945670, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan36dR value = " + value + " property: 0x" + tenToHex(557945670));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan36eR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945671, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan36eR value = " + value + " property: 0x" + tenToHex(557945671));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan37fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945672, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan37fR value = " + value + " property: 0x" + tenToHex(557945672));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan38aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945673, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan38aR value = " + value + " property: 0x" + tenToHex(557945673));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan38bR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945674, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan38bR value = " + value + " property: 0x" + tenToHex(557945674));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan394R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945675, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan394R value = " + value + " property: 0x" + tenToHex(557945675));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan396R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945676, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan396R value = " + value + " property: 0x" + tenToHex(557945676));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3a0R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945677, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3a0R value = " + value + " property: 0x" + tenToHex(557945677));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3a7R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945678, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3a7R value = " + value + " property: 0x" + tenToHex(557945678));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3acR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945679, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3acR value = " + value + " property: 0x" + tenToHex(557945679));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3adR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945680, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3adR value = " + value + " property: 0x" + tenToHex(557945680));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3b0R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945681, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3b0R value = " + value + " property: 0x" + tenToHex(557945681));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3b3R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945682, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3b3R value = " + value + " property: 0x" + tenToHex(557945682));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3b4R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945683, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3b4R value = " + value + " property: 0x" + tenToHex(557945683));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3bbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945684, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3bbR value = " + value + " property: 0x" + tenToHex(557945684));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3bdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945685, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3bdR value = " + value + " property: 0x" + tenToHex(557945685));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3c1R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945686, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3c1R value = " + value + " property: 0x" + tenToHex(557945686));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3c8R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945687, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3c8R value = " + value + " property: 0x" + tenToHex(557945687));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3caR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945688, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3caR value = " + value + " property: 0x" + tenToHex(557945688));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3cdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945689, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3cdR value = " + value + " property: 0x" + tenToHex(557945689));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3d9R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945690, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3d9R value = " + value + " property: 0x" + tenToHex(557945690));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3daR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945691, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3daR value = " + value + " property: 0x" + tenToHex(557945691));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3f1R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945692, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3f1R value = " + value + " property: 0x" + tenToHex(557945692));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3f3R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945693, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3f3R value = " + value + " property: 0x" + tenToHex(557945693));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3feR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945694, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3feR value = " + value + " property: 0x" + tenToHex(557945694));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3ffR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945695, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan3ffR value = " + value + " property: 0x" + tenToHex(557945695));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan401R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945696, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan401R value = " + value + " property: 0x" + tenToHex(557945696));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan402R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945697, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan402R value = " + value + " property: 0x" + tenToHex(557945697));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan404R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945698, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan404R value = " + value + " property: 0x" + tenToHex(557945698));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan407R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945699, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan407R value = " + value + " property: 0x" + tenToHex(557945699));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan40dR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945700, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan40dR value = " + value + " property: 0x" + tenToHex(557945700));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan417R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945701, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan417R value = " + value + " property: 0x" + tenToHex(557945701));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan418R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945702, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan418R value = " + value + " property: 0x" + tenToHex(557945702));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan41aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945703, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan41aR value = " + value + " property: 0x" + tenToHex(557945703));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan41cR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945704, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan41cR value = " + value + " property: 0x" + tenToHex(557945704));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan41eR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945705, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan41eR value = " + value + " property: 0x" + tenToHex(557945705));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan41fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945706, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan41fR value = " + value + " property: 0x" + tenToHex(557945706));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan429R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945707, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan429R value = " + value + " property: 0x" + tenToHex(557945707));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan42eR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945708, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan42eR value = " + value + " property: 0x" + tenToHex(557945708));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan42fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945709, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan42fR value = " + value + " property: 0x" + tenToHex(557945709));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan432R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945710, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan432R value = " + value + " property: 0x" + tenToHex(557945710));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan433R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945711, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan433R value = " + value + " property: 0x" + tenToHex(557945711));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan434R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945712, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan434R value = " + value + " property: 0x" + tenToHex(557945712));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan436R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945713, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan436R value = " + value + " property: 0x" + tenToHex(557945713));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan437R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945714, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan437R value = " + value + " property: 0x" + tenToHex(557945714));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan438R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945715, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan438R value = " + value + " property: 0x" + tenToHex(557945715));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan43eR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945716, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan43eR value = " + value + " property: 0x" + tenToHex(557945716));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan43fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945717, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan43fR value = " + value + " property: 0x" + tenToHex(557945717));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan443R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945718, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan443R value = " + value + " property: 0x" + tenToHex(557945718));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan444R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945719, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan444R value = " + value + " property: 0x" + tenToHex(557945719));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan447R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945720, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan447R value = " + value + " property: 0x" + tenToHex(557945720));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan449R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945721, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan449R value = " + value + " property: 0x" + tenToHex(557945721));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan453R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945722, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan453R value = " + value + " property: 0x" + tenToHex(557945722));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan454R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945723, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan454R value = " + value + " property: 0x" + tenToHex(557945723));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan461R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945724, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan461R value = " + value + " property: 0x" + tenToHex(557945724));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan46cR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945725, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan46cR value = " + value + " property: 0x" + tenToHex(557945725));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan470R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945726, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan470R value = " + value + " property: 0x" + tenToHex(557945726));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan475R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945727, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan475R value = " + value + " property: 0x" + tenToHex(557945727));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan480R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945728, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan480R value = " + value + " property: 0x" + tenToHex(557945728));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan481R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945729, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan481R value = " + value + " property: 0x" + tenToHex(557945729));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan486R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945730, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan486R value = " + value + " property: 0x" + tenToHex(557945730));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan487R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945731, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan487R value = " + value + " property: 0x" + tenToHex(557945731));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan488R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945732, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan488R value = " + value + " property: 0x" + tenToHex(557945732));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan489R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945733, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan489R value = " + value + " property: 0x" + tenToHex(557945733));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan48aR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945734, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan48aR value = " + value + " property: 0x" + tenToHex(557945734));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan48eR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945735, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan48eR value = " + value + " property: 0x" + tenToHex(557945735));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan48fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945736, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan48fR value = " + value + " property: 0x" + tenToHex(557945736));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan494R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945737, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan494R value = " + value + " property: 0x" + tenToHex(557945737));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan49cR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945738, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan49cR value = " + value + " property: 0x" + tenToHex(557945738));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4a1R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945739, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4a1R value = " + value + " property: 0x" + tenToHex(557945739));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4a4R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945740, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4a4R value = " + value + " property: 0x" + tenToHex(557945740));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4a5R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945741, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4a5R value = " + value + " property: 0x" + tenToHex(557945741));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4a7R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945742, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4a7R value = " + value + " property: 0x" + tenToHex(557945742));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4b1R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945743, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4b1R value = " + value + " property: 0x" + tenToHex(557945743));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4b2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945744, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4b2R value = " + value + " property: 0x" + tenToHex(557945744));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4b3R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945745, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4b3R value = " + value + " property: 0x" + tenToHex(557945745));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4b6R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945746, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4b6R value = " + value + " property: 0x" + tenToHex(557945746));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4bbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945747, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4bbR value = " + value + " property: 0x" + tenToHex(557945747));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4bdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945748, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4bdR value = " + value + " property: 0x" + tenToHex(557945748));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4bfR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945749, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4bfR value = " + value + " property: 0x" + tenToHex(557945749));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4c2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945750, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4c2R value = " + value + " property: 0x" + tenToHex(557945750));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4c8R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945751, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4c8R value = " + value + " property: 0x" + tenToHex(557945751));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4d9R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945752, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4d9R value = " + value + " property: 0x" + tenToHex(557945752));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4e5R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945753, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4e5R value = " + value + " property: 0x" + tenToHex(557945753));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4e6R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945754, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4e6R value = " + value + " property: 0x" + tenToHex(557945754));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4f2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945755, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4f2R value = " + value + " property: 0x" + tenToHex(557945755));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4f4R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945756, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4f4R value = " + value + " property: 0x" + tenToHex(557945756));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4f6R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945757, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4f6R value = " + value + " property: 0x" + tenToHex(557945757));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4f7R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945758, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4f7R value = " + value + " property: 0x" + tenToHex(557945758));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4faR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945759, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4faR value = " + value + " property: 0x" + tenToHex(557945759));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4fbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945760, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4fbR value = " + value + " property: 0x" + tenToHex(557945760));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4fdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945761, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4fdR value = " + value + " property: 0x" + tenToHex(557945761));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4feR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945762, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getCan4feR value = " + value + " property: 0x" + tenToHex(557945762));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan258R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945765, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan258R property: 0x");
            sb.append(tenToHex(557945765));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan351R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945766, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan351R property: 0x");
            sb.append(tenToHex(557945766));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4e8R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945767, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan4e8R property: 0x");
            sb.append(tenToHex(557945767));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan4e9R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945768, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan4e9R property: 0x");
            sb.append(tenToHex(557945768));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan257R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945769, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan257R property: 0x");
            sb.append(tenToHex(557945769));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan48cR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945770, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan48cR property: 0x");
            sb.append(tenToHex(557945770));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan3d6R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945771, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan3d6R property: 0x");
            sb.append(tenToHex(557945771));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan38fR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945772, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan38fR property: 0x");
            sb.append(tenToHex(557945772));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan1eaR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945777, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan1EAR property: 0x");
            sb.append(tenToHex(557945777));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int[] getCan359R() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557945778, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCan359R property: 0x");
            sb.append(tenToHex(557945778));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorCarbodyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setAllDoorsLockSceneS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setAllDoorsLockSceneS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_ALL_DOORS_LOCK_SCENE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_ALL_DOORS_LOCK_SCENE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setSunroofAndSunshadeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setSunroofAndSunshadeS value = " + value + " property: 0x" + tenToHex(557863027));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557863027, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setWindowsModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setWindowsModeS value = " + value + " property: 0x" + tenToHex(557863026));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557863026, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void setElecSlideDoorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarbodyManager.TAG, "setElecSlideDoorStateS value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_ELEC_SLIDE_DOOR_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorCarbodyManager.ID_ELEC_SLIDE_DOOR_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public int getMoonRoofPercentControlFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarbodyManager.ID_MOON_ROOF_PERCENT_CONTROL_FLAG_R, 0);
            Log.debug(CarVendorCarbodyManager.TAG, "getMoonRoofPercentControlFlagR value = " + value + " property: 0x" + tenToHex(CarVendorCarbodyManager.ID_MOON_ROOF_PERCENT_CONTROL_FLAG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void addCarCarbodyEventListener(CarVendorCarbodyManager.OnCarCarbodyEventListener listener) throws CarNotConnectedException {
        OnCarCarbodyChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarCarbodyChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorCarbodyManager.TAG, "addCarCarbodyEventListener propId size: " + this.mExtCarbodyIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtCarbodyIds) {
                int propId = num.intValue();
                Log.debug(CarVendorCarbodyManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager
    public void removeCarCarbodyEventListener(CarVendorCarbodyManager.OnCarCarbodyEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarCarbodyChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorCarbodyManager.TAG, "removeCarCarbodyEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CarbodyEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sCarbodyEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorCarbodyManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557845265:
                sCarbodyEventChangeValue.setFuelElecLowPowerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857850:
                sCarbodyEventChangeValue.setRecoverOrSaveParamsChanged5(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859175:
                sCarbodyEventChangeValue.setTailDoorTypeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860644:
                sCarbodyEventChangeValue.setLightRearPosition(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862936:
                sCarbodyEventChangeValue.setWindowOpenPercentLeftFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862937:
                sCarbodyEventChangeValue.setWindowOpenPercentLeftRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862938:
                sCarbodyEventChangeValue.setWindowOpenPercentRightFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862939:
                sCarbodyEventChangeValue.setWindowOpenPercentRightRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862940:
                sCarbodyEventChangeValue.setWindowOpenPercentMoonR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862941:
                sCarbodyEventChangeValue.setWindowOpenPercentSunR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862942:
                sCarbodyEventChangeValue.setMoonRoofConfig(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862943:
                sCarbodyEventChangeValue.setDoorStateLeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862944:
                sCarbodyEventChangeValue.setDoorStateRightFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862945:
                sCarbodyEventChangeValue.setDoorStateLeftRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862946:
                sCarbodyEventChangeValue.setDoorStateRightRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862947:
                sCarbodyEventChangeValue.setDoorStateLuggage(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862948:
                sCarbodyEventChangeValue.setDoorStateHood(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862949:
                sCarbodyEventChangeValue.setWindowStateMoonRoofR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862950:
                sCarbodyEventChangeValue.setWindowStateSunshadeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862951:
                sCarbodyEventChangeValue.setEnergyTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862952:
                sCarbodyEventChangeValue.setSunRoofStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862953:
                sCarbodyEventChangeValue.setLeftStrutMorotRunningState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862954:
                sCarbodyEventChangeValue.setWindowInitStateFL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862955:
                sCarbodyEventChangeValue.setWindowInitStateRL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862956:
                sCarbodyEventChangeValue.setWindowInitStateFR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862957:
                sCarbodyEventChangeValue.setWindowInitStateRR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862958:
                sCarbodyEventChangeValue.setWindowThermalProtectionStateFL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862959:
                sCarbodyEventChangeValue.setWindowThermalProtectionStateRL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862960:
                sCarbodyEventChangeValue.setWindowThermalProtectionStateFR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862961:
                sCarbodyEventChangeValue.setWindowThermalProtectionStateRR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862962:
                sCarbodyEventChangeValue.setWindowThermalProtectionStateSkylight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862963:
                sCarbodyEventChangeValue.setWindowThermalProtectionStateFrontSunshade(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862964:
                sCarbodyEventChangeValue.setWindowThermalProtectionStateRearSunshade(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862965:
                sCarbodyEventChangeValue.setWindowPermitStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862966:
                sCarbodyEventChangeValue.setWindowblindInitStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862967:
                sCarbodyEventChangeValue.setSunroofInitStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862968:
                sCarbodyEventChangeValue.setSunroofCloseNoticeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862969:
                sCarbodyEventChangeValue.setSunroofWindowblindPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862970:
                sCarbodyEventChangeValue.setSunroofPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarbodyManager.ID_WINDOWBLIND_STATER /* 557862971 */:
                sCarbodyEventChangeValue.setWindowblindStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862972:
                sCarbodyEventChangeValue.setRearWindowblindStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862973:
                sCarbodyEventChangeValue.setRearWindowblindInitStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862974:
                sCarbodyEventChangeValue.setRearWindowblindPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862975:
                sCarbodyEventChangeValue.setBackDoorInitStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarbodyManager.ID_ELEC_BACK_DOOR_CONTROL_STATER /* 557862976 */:
                sCarbodyEventChangeValue.setElecBackDoorControlStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862977:
                sCarbodyEventChangeValue.setBackDoorHeightPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862978:
                sCarbodyEventChangeValue.setBackDoorCurStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862979:
                sCarbodyEventChangeValue.setRightStrutMorotRunningStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862980:
                sCarbodyEventChangeValue.setBackDoorPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862981:
                sCarbodyEventChangeValue.setBackDoorAntiPlayModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862982:
                sCarbodyEventChangeValue.setElecSlideDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862983:
                sCarbodyEventChangeValue.setLeftSlideDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862984:
                sCarbodyEventChangeValue.setRightSlideDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862985:
                sCarbodyEventChangeValue.setLeftSlideDoorActionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862986:
                sCarbodyEventChangeValue.setRightSlideDoorActionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862987:
                sCarbodyEventChangeValue.setLeftSlideDoorPercentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862988:
                sCarbodyEventChangeValue.setRightSlideDoorPercentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarbodyManager.ID_LEFT_SLIDE_DOOR_VOICE_CONTROL_STATES /* 557862989 */:
                sCarbodyEventChangeValue.setLeftSlideDoorVoiceControlStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862990:
                sCarbodyEventChangeValue.setRightSlideDoorVoiceControlStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862991:
                sCarbodyEventChangeValue.setWindowOpenPercentRearPanelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862992:
                sCarbodyEventChangeValue.setWindowOpenPercentRearPanelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863019:
                sCarbodyEventChangeValue.setRearWindowblindInitStateNewR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarbodyManager.ID_WINDOW_PERCENTAGE_CONTROL_CONFIG_R /* 557863048 */:
                sCarbodyEventChangeValue.setWindowPercentageControlConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863049:
                sCarbodyEventChangeValue.setScreenControlSlideDoorLeftLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863050:
                sCarbodyEventChangeValue.setScreenControlSlideDoorRightLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863051:
                sCarbodyEventChangeValue.setScreenControlSlideDoorLLeftRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863052:
                sCarbodyEventChangeValue.setScreenControlSlideDoorRightRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863053:
                sCarbodyEventChangeValue.setScreenControlSlideDoorSlidingLeftLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863054:
                sCarbodyEventChangeValue.setScreenControlSlideDoorSlidingRightLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863055:
                sCarbodyEventChangeValue.setScreenControlSlideDoorSlidingLeftRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863056:
                sCarbodyEventChangeValue.setScreenControlSlideDoorSlidingRightRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863057:
                sCarbodyEventChangeValue.setEvReadyIndicatorLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarbodyManager.ID_AWNING_CONFIGUTATION_R /* 557863058 */:
                sCarbodyEventChangeValue.setAwningConfigutationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarbodyManager.ID_SIDE_WINDOW_SHADE_STATE_S /* 557863059 */:
                sCarbodyEventChangeValue.setSideWindowShadeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarbodyManager.ID_SYSTEM_CONFIG_R /* 557863060 */:
                sCarbodyEventChangeValue.setSystemConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863061:
                sCarbodyEventChangeValue.setInternalDoorHandleStateByRearBCMLFR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863062:
                sCarbodyEventChangeValue.setInternalDoorHandleStateByRearBCMLRR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863063:
                sCarbodyEventChangeValue.setInternalDoorHandleStateByRearBCMRFR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863064:
                sCarbodyEventChangeValue.setInternalDoorHandleStateByRearBCMRRR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863067:
                sCarbodyEventChangeValue.setThirdRowSeatsExecutionResultsR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863068:
                sCarbodyEventChangeValue.setLeftSeatRailsRow3FeedbackPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863069:
                sCarbodyEventChangeValue.setRightSeatRailsRow3FeedbackPositionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863070:
                sCarbodyEventChangeValue.setLeftSeatRow3NonElectricallyAdjustStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863071:
                sCarbodyEventChangeValue.setFseSeatSystemWorkModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863072:
                sCarbodyEventChangeValue.setFourPeopleEnjoyConfig2R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863073:
                sCarbodyEventChangeValue.setRestoreSeatConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863074:
                sCarbodyEventChangeValue.setSeatLargeBedModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863075:
                sCarbodyEventChangeValue.setFseLagerBedModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863076:
                sCarbodyEventChangeValue.setSeatModeCurFeedbackStatus2R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863077:
                sCarbodyEventChangeValue.setSeatModeSuccessFeedbackStatus2R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863078:
                sCarbodyEventChangeValue.setSeatModeFailFeedbackStatus2R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863079:
                sCarbodyEventChangeValue.setSeatModeCurFeedbackStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863080:
                sCarbodyEventChangeValue.setSeatModeSuccessFeedbackStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863081:
                sCarbodyEventChangeValue.setSeatModeFailFeedbackStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863082:
                sCarbodyEventChangeValue.setLargeBedModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863083:
                sCarbodyEventChangeValue.setFseLargeBedModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863084:
                sCarbodyEventChangeValue.setSeatRow3OnekeyEnterPitS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863086:
                sCarbodyEventChangeValue.setLeftMidDoorVoiceWakeUpChangedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557863087:
                sCarbodyEventChangeValue.setRightMidDoorVoiceWakeUpChangedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557868039:
                sCarbodyEventChangeValue.setShiftModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875227:
                sCarbodyEventChangeValue.setBatteryVoltageLevelLowTips(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557928602:
                sCarbodyEventChangeValue.setModeCommandBs(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557928621:
                sCarbodyEventChangeValue.setModeCommandCs(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945600:
                sCarbodyEventChangeValue.setCan055R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945603:
                sCarbodyEventChangeValue.setCan0d5R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945605:
                sCarbodyEventChangeValue.setCan10dR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945606:
                sCarbodyEventChangeValue.setCan121R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945607:
                sCarbodyEventChangeValue.setCan123R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945608:
                sCarbodyEventChangeValue.setCan12dR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945609:
                sCarbodyEventChangeValue.setCan12fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945610:
                sCarbodyEventChangeValue.setCan133R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945611:
                sCarbodyEventChangeValue.setCan138R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945612:
                sCarbodyEventChangeValue.setCan13fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945613:
                sCarbodyEventChangeValue.setCan144R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945614:
                sCarbodyEventChangeValue.setCan151R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945615:
                sCarbodyEventChangeValue.setCan167R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945616:
                sCarbodyEventChangeValue.setCan18aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945617:
                sCarbodyEventChangeValue.setCan19fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945618:
                sCarbodyEventChangeValue.setCan1c0R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945619:
                sCarbodyEventChangeValue.setCan1d1R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945620:
                sCarbodyEventChangeValue.setCan218R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945621:
                sCarbodyEventChangeValue.setCan223R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945622:
                sCarbodyEventChangeValue.setCan233R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945623:
                sCarbodyEventChangeValue.setCan235R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945624:
                sCarbodyEventChangeValue.setCan23aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945625:
                sCarbodyEventChangeValue.setCan242R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945626:
                sCarbodyEventChangeValue.setCan245R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945627:
                sCarbodyEventChangeValue.setCan248R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945628:
                sCarbodyEventChangeValue.setCan24aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945629:
                sCarbodyEventChangeValue.setCan24cR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945630:
                sCarbodyEventChangeValue.setCan25dR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945631:
                sCarbodyEventChangeValue.setCan261R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945632:
                sCarbodyEventChangeValue.setCan26fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945633:
                sCarbodyEventChangeValue.setCan271R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945634:
                sCarbodyEventChangeValue.setCan272R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945635:
                sCarbodyEventChangeValue.setCan275R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945636:
                sCarbodyEventChangeValue.setCan27aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945637:
                sCarbodyEventChangeValue.setCan287R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945638:
                sCarbodyEventChangeValue.setCan288R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945639:
                sCarbodyEventChangeValue.setCan28fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945640:
                sCarbodyEventChangeValue.setCan294R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945641:
                sCarbodyEventChangeValue.setCan296R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945642:
                sCarbodyEventChangeValue.setCan29cR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945643:
                sCarbodyEventChangeValue.setCan29eR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945644:
                sCarbodyEventChangeValue.setCan2a4R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945645:
                sCarbodyEventChangeValue.setCan2a6R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945646:
                sCarbodyEventChangeValue.setCan2a7R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945647:
                sCarbodyEventChangeValue.setCan2b0R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945648:
                sCarbodyEventChangeValue.setCan2b4R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945649:
                sCarbodyEventChangeValue.setCan2b6R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945650:
                sCarbodyEventChangeValue.setCan2baR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945651:
                sCarbodyEventChangeValue.setCan2bcR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945652:
                sCarbodyEventChangeValue.setCan2c0R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945653:
                sCarbodyEventChangeValue.setCan2d5R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945654:
                sCarbodyEventChangeValue.setCan2ebR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945655:
                sCarbodyEventChangeValue.setCan2f0R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945656:
                sCarbodyEventChangeValue.setCan30dR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945657:
                sCarbodyEventChangeValue.setCan31eR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945658:
                sCarbodyEventChangeValue.setCan321R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945659:
                sCarbodyEventChangeValue.setCan323R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945660:
                sCarbodyEventChangeValue.setCan32dR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945661:
                sCarbodyEventChangeValue.setCan334R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945662:
                sCarbodyEventChangeValue.setCan337R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945663:
                sCarbodyEventChangeValue.setCan341R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945664:
                sCarbodyEventChangeValue.setCan342R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945665:
                sCarbodyEventChangeValue.setCan346R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945666:
                sCarbodyEventChangeValue.setCan347R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945667:
                sCarbodyEventChangeValue.setCan34aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945668:
                sCarbodyEventChangeValue.setCan34fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945669:
                sCarbodyEventChangeValue.setCan36bR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945670:
                sCarbodyEventChangeValue.setCan36dR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945671:
                sCarbodyEventChangeValue.setCan36eR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945672:
                sCarbodyEventChangeValue.setCan37fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945673:
                sCarbodyEventChangeValue.setCan38aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945674:
                sCarbodyEventChangeValue.setCan38bR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945675:
                sCarbodyEventChangeValue.setCan394R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945676:
                sCarbodyEventChangeValue.setCan396R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945677:
                sCarbodyEventChangeValue.setCan3a0R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945678:
                sCarbodyEventChangeValue.setCan3a7R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945679:
                sCarbodyEventChangeValue.setCan3acR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945680:
                sCarbodyEventChangeValue.setCan3adR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945681:
                sCarbodyEventChangeValue.setCan3b0R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945682:
                sCarbodyEventChangeValue.setCan3b3R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945683:
                sCarbodyEventChangeValue.setCan3b4R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945684:
                sCarbodyEventChangeValue.setCan3bbR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945685:
                sCarbodyEventChangeValue.setCan3bdR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945686:
                sCarbodyEventChangeValue.setCan3c1R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945687:
                sCarbodyEventChangeValue.setCan3c8R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945688:
                sCarbodyEventChangeValue.setCan3caR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945689:
                sCarbodyEventChangeValue.setCan3cdR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945690:
                sCarbodyEventChangeValue.setCan3d9R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945691:
                sCarbodyEventChangeValue.setCan3daR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945692:
                sCarbodyEventChangeValue.setCan3f1R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945693:
                sCarbodyEventChangeValue.setCan3f3R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945694:
                sCarbodyEventChangeValue.setCan3feR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945695:
                sCarbodyEventChangeValue.setCan3ffR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945696:
                sCarbodyEventChangeValue.setCan401R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945697:
                sCarbodyEventChangeValue.setCan402R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945698:
                sCarbodyEventChangeValue.setCan404R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945699:
                sCarbodyEventChangeValue.setCan407R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945700:
                sCarbodyEventChangeValue.setCan40dR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945701:
                sCarbodyEventChangeValue.setCan417R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945702:
                sCarbodyEventChangeValue.setCan418R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945703:
                sCarbodyEventChangeValue.setCan41aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945704:
                sCarbodyEventChangeValue.setCan41cR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945705:
                sCarbodyEventChangeValue.setCan41eR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945706:
                sCarbodyEventChangeValue.setCan41fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945707:
                sCarbodyEventChangeValue.setCan429R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945708:
                sCarbodyEventChangeValue.setCan42eR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945709:
                sCarbodyEventChangeValue.setCan42fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945710:
                sCarbodyEventChangeValue.setCan432R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945711:
                sCarbodyEventChangeValue.setCan433R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945712:
                sCarbodyEventChangeValue.setCan434R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945713:
                sCarbodyEventChangeValue.setCan436R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945714:
                sCarbodyEventChangeValue.setCan437R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945715:
                sCarbodyEventChangeValue.setCan438R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945716:
                sCarbodyEventChangeValue.setCan43eR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945717:
                sCarbodyEventChangeValue.setCan43fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945718:
                sCarbodyEventChangeValue.setCan443R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945719:
                sCarbodyEventChangeValue.setCan444R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945720:
                sCarbodyEventChangeValue.setCan447R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945721:
                sCarbodyEventChangeValue.setCan449R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945722:
                sCarbodyEventChangeValue.setCan453R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945723:
                sCarbodyEventChangeValue.setCan454R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945724:
                sCarbodyEventChangeValue.setCan461R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945725:
                sCarbodyEventChangeValue.setCan46cR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945726:
                sCarbodyEventChangeValue.setCan470R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945727:
                sCarbodyEventChangeValue.setCan475R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945728:
                sCarbodyEventChangeValue.setCan480R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945729:
                sCarbodyEventChangeValue.setCan481R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945730:
                sCarbodyEventChangeValue.setCan486R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945731:
                sCarbodyEventChangeValue.setCan487R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945732:
                sCarbodyEventChangeValue.setCan488R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945733:
                sCarbodyEventChangeValue.setCan489R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945734:
                sCarbodyEventChangeValue.setCan48aR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945735:
                sCarbodyEventChangeValue.setCan48eR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945736:
                sCarbodyEventChangeValue.setCan48fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945737:
                sCarbodyEventChangeValue.setCan494R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945738:
                sCarbodyEventChangeValue.setCan49cR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945739:
                sCarbodyEventChangeValue.setCan4a1R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945740:
                sCarbodyEventChangeValue.setCan4a4R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945741:
                sCarbodyEventChangeValue.setCan4a5R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945742:
                sCarbodyEventChangeValue.setCan4a7R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945743:
                sCarbodyEventChangeValue.setCan4b1R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945744:
                sCarbodyEventChangeValue.setCan4b2R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945745:
                sCarbodyEventChangeValue.setCan4b3R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945746:
                sCarbodyEventChangeValue.setCan4b6R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945747:
                sCarbodyEventChangeValue.setCan4bbR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945748:
                sCarbodyEventChangeValue.setCan4bdR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945749:
                sCarbodyEventChangeValue.setCan4bfR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945750:
                sCarbodyEventChangeValue.setCan4c2R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945751:
                sCarbodyEventChangeValue.setCan4c8R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945752:
                sCarbodyEventChangeValue.setCan4d9R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945753:
                sCarbodyEventChangeValue.setCan4e5R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945754:
                sCarbodyEventChangeValue.setCan4e6R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945755:
                sCarbodyEventChangeValue.setCan4f2R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945756:
                sCarbodyEventChangeValue.setCan4f4R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945757:
                sCarbodyEventChangeValue.setCan4f6R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945758:
                sCarbodyEventChangeValue.setCan4f7R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945759:
                sCarbodyEventChangeValue.setCan4faR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945760:
                sCarbodyEventChangeValue.setCan4fbR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945761:
                sCarbodyEventChangeValue.setCan4fdR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945762:
                sCarbodyEventChangeValue.setCan4feR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945763:
                sCarbodyEventChangeValue.setCan344R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945764:
                sCarbodyEventChangeValue.setCan319R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945765:
                sCarbodyEventChangeValue.setCan258R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945766:
                sCarbodyEventChangeValue.setCan351R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945767:
                sCarbodyEventChangeValue.setCan4e8R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945768:
                sCarbodyEventChangeValue.setCan4e9R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945769:
                sCarbodyEventChangeValue.setCan257R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945770:
                sCarbodyEventChangeValue.setCan48cR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945771:
                sCarbodyEventChangeValue.setCan3d6R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945772:
                sCarbodyEventChangeValue.setCan38fR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945773:
                sCarbodyEventChangeValue.setCan464R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945774:
                sCarbodyEventChangeValue.setCan338R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945775:
                sCarbodyEventChangeValue.setCan145R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945777:
                sCarbodyEventChangeValue.setCan1eaR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557945778:
                sCarbodyEventChangeValue.setCan359R(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557969457:
                sCarbodyEventChangeValue.setHasMessage(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 559954810:
                sCarbodyEventChangeValue.setSteeringWheelY(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954811:
                sCarbodyEventChangeValue.setSteeringWheelZ(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954812:
                sCarbodyEventChangeValue.setRearviewMirrorYLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954813:
                sCarbodyEventChangeValue.setRearviewMirrorYRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954814:
                sCarbodyEventChangeValue.setRearviewMirrorXLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954815:
                sCarbodyEventChangeValue.setRearviewMirrorXRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557857652:
                        sCarbodyEventChangeValue.setturnRearviewMirror(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857653:
                        sCarbodyEventChangeValue.setturnSteeringWheel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557857805:
                                sCarbodyEventChangeValue.setonSaveSeatParamsAllChangedPlace5(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557857806:
                                sCarbodyEventChangeValue.setonSaveSeatParamsAllChangedPlace7(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557858048:
                                        sCarbodyEventChangeValue.setAutoExternalRearMirrorFoldActionStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557858049:
                                        sCarbodyEventChangeValue.setAutoExternalRearMirrorFoldedStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557862912:
                                                sCarbodyEventChangeValue.setWindowStateLeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557862913:
                                                sCarbodyEventChangeValue.setWindowStateLeftRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557862914:
                                                sCarbodyEventChangeValue.setWindowStateRightFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557862915:
                                                sCarbodyEventChangeValue.setWindowStateRightRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557862916:
                                                sCarbodyEventChangeValue.setWindowControlStateLeftFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557862917:
                                                sCarbodyEventChangeValue.setWindowControlStateRightFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557862918:
                                                sCarbodyEventChangeValue.setWindowControlStateLeftRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557862919:
                                                sCarbodyEventChangeValue.setWindowControlStateRightRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 557862924:
                                                        sCarbodyEventChangeValue.setWindowOpenPercentMoonS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557862925:
                                                        sCarbodyEventChangeValue.setWindowOpenPercentSunS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557862926:
                                                        sCarbodyEventChangeValue.setBatteryVoltageLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557862927:
                                                        sCarbodyEventChangeValue.setPowerLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557862928:
                                                        sCarbodyEventChangeValue.setSteeringWheelValueAngel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557862929:
                                                        sCarbodyEventChangeValue.setSteeringWheelValueSpeed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557862930:
                                                        sCarbodyEventChangeValue.setAutoType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557862931:
                                                        sCarbodyEventChangeValue.setAutoSystemState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case CarVendorCarbodyManager.ID_ANTI_THEFT_STATUS_R /* 557862994 */:
                                                                sCarbodyEventChangeValue.setAntiTheftStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557862995:
                                                                sCarbodyEventChangeValue.setRealAutoVinOneR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557862996:
                                                                sCarbodyEventChangeValue.setRealAutoVinTwoR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557862997:
                                                                sCarbodyEventChangeValue.setRealAutoVinThreeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557862998:
                                                                sCarbodyEventChangeValue.setRealAutoVinFourR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557862999:
                                                                sCarbodyEventChangeValue.setRealAutoVinFiveR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863000:
                                                                sCarbodyEventChangeValue.setRealAutoVinSixR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863001:
                                                                sCarbodyEventChangeValue.setRealAutoVinSevenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863002:
                                                                sCarbodyEventChangeValue.setRealAutoVinEightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863003:
                                                                sCarbodyEventChangeValue.setRealAutoVinNineR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863004:
                                                                sCarbodyEventChangeValue.setRealAutoVinTenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863005:
                                                                sCarbodyEventChangeValue.setRealAutoVinElevenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863006:
                                                                sCarbodyEventChangeValue.setRealAutoVinTwelveR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863007:
                                                                sCarbodyEventChangeValue.setRealAutoVinThirteenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863008:
                                                                sCarbodyEventChangeValue.setRealAutoVinFourteenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863009:
                                                                sCarbodyEventChangeValue.setRealAutoVinFifteenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863010:
                                                                sCarbodyEventChangeValue.setRealAutoVinSixteenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863011:
                                                                sCarbodyEventChangeValue.setRealAutoVinSeventeenR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863012:
                                                                sCarbodyEventChangeValue.setSettingLuggageS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557863013:
                                                                sCarbodyEventChangeValue.setReadyIndicatorLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557863016:
                                                                        sCarbodyEventChangeValue.setSmartVoiceLimit(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557863017:
                                                                        sCarbodyEventChangeValue.setBcmVechileStatus(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557863022:
                                                                                sCarbodyEventChangeValue.setDoorLockStatusRightFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557863023:
                                                                                sCarbodyEventChangeValue.setDoorLockStatusLeftRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557863024:
                                                                                sCarbodyEventChangeValue.setDoorLockStatusAreaRightRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case CarVendorCarbodyManager.ID_MOON_ROOF_PERCENT_CONTROL_FLAG_R /* 557863029 */:
                                                                                        sCarbodyEventChangeValue.setMoonRoofPercentControlFlagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_ELEC_SLIDE_DOOR_STATE_S /* 557863030 */:
                                                                                        sCarbodyEventChangeValue.setElecSlideDoorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_AUTO_TYPE_NEW /* 557863031 */:
                                                                                        sCarbodyEventChangeValue.setAutoTypeNew(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_MOON_ROOF_CONFIG_R /* 557863032 */:
                                                                                        sCarbodyEventChangeValue.setMoonRoofConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_RIGHT_R /* 557863033 */:
                                                                                        sCarbodyEventChangeValue.setSeatLumbarSupportConfigMainRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_LEFT_R /* 557863034 */:
                                                                                        sCarbodyEventChangeValue.setSeatLumbarSupportConfigMainLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_LEFT_R /* 557863035 */:
                                                                                        sCarbodyEventChangeValue.setSeatLumbarSupportConfigCoPilotLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_RIGHT_R /* 557863036 */:
                                                                                        sCarbodyEventChangeValue.setSeatLumbarSupportConfigCoPilotRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_WAIST_SUPPORT_ADJUSTMENT_MAIN_S /* 557863037 */:
                                                                                        sCarbodyEventChangeValue.setWaistSupportAdjustmentMainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_WAIST_SUPPORT_ADJUSTMENT_CO_PILOT_S /* 557863038 */:
                                                                                        sCarbodyEventChangeValue.setWaistSupportAdjustmentCoPilotS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_FOUR_PEOPLE_ENJOY_CONFIG_R /* 557863039 */:
                                                                                        sCarbodyEventChangeValue.setFourPeopleEnjoyConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_SEAT_MODE_CONTROL_S /* 557863040 */:
                                                                                        sCarbodyEventChangeValue.setSeatModeControlS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557863041:
                                                                                        sCarbodyEventChangeValue.setSeatModeWakeupFeedbackStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557863042:
                                                                                        sCarbodyEventChangeValue.setInternalDoorHandleStateLeftFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557863043:
                                                                                        sCarbodyEventChangeValue.setInternalDoorHandleStateRightFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557863044:
                                                                                        sCarbodyEventChangeValue.setInternalDoorHandleStateLeftBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557863045:
                                                                                        sCarbodyEventChangeValue.setInternalDoorHandleStateRightBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorCarbodyManager.ID_ELECTRIC_PEDAL_CONFIG_R /* 557863046 */:
                                                                                        sCarbodyEventChangeValue.setElectricPedalConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorCarbodyManager.TAG, "break other properties ,except carbody");
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
        Log.debug(CarVendorCarbodyManager.TAG, "convertCarVendorPropertyValue " + sCarbodyEventChangeValue.toString(), DEBUG_LOGGER);
        return sCarbodyEventChangeValue;
    }

    private OnCarCarbodyChangeEventListenerProxy findListenerLocked(CarVendorCarbodyManager.OnCarCarbodyEventListener listener) {
        for (OnCarCarbodyChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarCarbodyChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorCarbodyManager.OnCarCarbodyEventListener listener;

        public OnCarCarbodyChangeEventListenerProxy(CarVendorCarbodyManager.OnCarCarbodyEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorCarbodyMgr.this.traceCallbackPrepare();
                CarbodyEventChangeValue newValue = CarVendorCarbodyMgr.convertCarVendorProperty(value);
                CarVendorCarbodyMgr.this.traceCallbackBefore();
                this.listener.onCarbodyEventChange(newValue);
                CarVendorCarbodyMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorCarbodyManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorCarbodyManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onCarbodyErrorEvent(propertyId, zone);
        }
    }
}
