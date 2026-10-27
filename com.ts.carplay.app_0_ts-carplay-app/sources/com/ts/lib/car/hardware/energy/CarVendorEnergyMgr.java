package com.ts.lib.car.hardware.energy;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.energy.CarVendorEnergyManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorEnergyMgr extends CarVendorManagerBase implements CarVendorEnergyManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static EnergyEventChangeValue sEnergyEventChangeValue = new EnergyEventChangeValue();
    private final LinkedList<OnCarEnergyChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtEnergyIds = {557847581, 557847586, 559944735, 559944734, 559944737, 559944736, 557847580, 557847558, 557847569, 557847565, 557847556, 557847563, 557847567, 557847576, 557847574, 557847572, 557847552, 557847554, 557847555, 557847578, 557847553, 557847571, 557847587, 559944740, 557847590, 557847592, 557847597, 557847598, 557847599, 557847596, 557847594, 557847601, 557847610, 557847605, 557847609, 557847604, 557847611, 557847606, 559944768, 559944766, 559944770, 559944772, 559944771, 559944765, 557847612, 557847607, 557847608, 557847617, 559944767, 557860387};

    public CarVendorEnergyMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorEnergyManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorEnergyManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorEnergyManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorEnergyManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getRollBenchModeStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860387, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getRollBenchModeStatusR value = " + value + " property: 0x" + tenToHex(557860387));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripAcPercent50kmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847610, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripAcPercent50kmR value = " + value + " property: 0x" + tenToHex(557847610));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripAcPercentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847605, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripAcPercentR value = " + value + " property: 0x" + tenToHex(557847605));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripDrivePercent50kmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847609, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripDrivePercent50kmR value = " + value + " property: 0x" + tenToHex(557847609));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripDrivePercentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847604, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripDrivePercentR value = " + value + " property: 0x" + tenToHex(557847604));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripElecAppliancePercent50kmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847611, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripElecAppliancePercent50kmR value = " + value + " property: 0x" + tenToHex(557847611));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripElecAppliancePercentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847606, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripElecAppliancePercentR value = " + value + " property: 0x" + tenToHex(557847606));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getTripElecConAvgR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944768, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripElecConAvgR value = " + value + " property: 0x" + tenToHex(559944768));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getTripEnergyConAvgR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944766, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripEnergyConAvgR value = " + value + " property: 0x" + tenToHex(559944766));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getTripLast50kmElectricConAvgR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944770, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripLast50kmElectricConAvgR value = " + value + " property: 0x" + tenToHex(559944770));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getTripLast50kmFuelConAvgR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944772, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripLast50kmFuelConAvgR value = " + value + " property: 0x" + tenToHex(559944772));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getTripLast50kmOilConAvgR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944771, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripLast50kmOilConAvgR value = " + value + " property: 0x" + tenToHex(559944771));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getTripOilConAvgR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944765, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripOilConAvgR value = " + value + " property: 0x" + tenToHex(559944765));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripOtherPercent50kmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847612, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripOtherPercent50kmR value = " + value + " property: 0x" + tenToHex(557847612));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripOtherPercentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847607, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripOtherPercentR value = " + value + " property: 0x" + tenToHex(557847607));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getTripPointFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847608, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getTripPointFlagR value = " + value + " property: 0x" + tenToHex(557847608));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getYEnergyConLimitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847617, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getYEnergyConLimitR value = " + value + " property: 0x" + tenToHex(557847617));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getYEnergyConR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944767, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getYEnergyConR value = " + value + " property: 0x" + tenToHex(559944767));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setChargePortCoverHintS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setChargePortCoverHintS value = " + value + " property: 0x" + tenToHex(CarVendorEnergyManager.ID_CHARGE_PORT_COVER_HINT_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorEnergyManager.ID_CHARGE_PORT_COVER_HINT_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getChargePortCoverTipR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847601, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getChargePortCoverTipR value = " + value + " property: 0x" + tenToHex(557847601));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setConfirmChargePortCoverS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setConfirmChargePortCoverS value = " + value + " property: 0x" + tenToHex(CarVendorEnergyManager.ID_CONFIRM_CHARGE_PORT_COVER_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorEnergyManager.ID_CONFIRM_CHARGE_PORT_COVER_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getChangePHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847597, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getChangePHintR value = " + value + " property: 0x" + tenToHex(557847597));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getiTacExitTagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847598, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getiTacExitTagR value = " + value + " property: 0x" + tenToHex(557847598));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getiTacModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847599, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getiTacModeR value = " + value + " property: 0x" + tenToHex(557847599));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setiTacModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setiTacModeS value = " + value + " property: 0x" + tenToHex(CarVendorEnergyManager.ID_I_T_A_C_MODE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorEnergyManager.ID_I_T_A_C_MODE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getiTacpAllowTagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847596, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getiTacpAllowTagR value = " + value + " property: 0x" + tenToHex(557847596));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getiTacpStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847594, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getiTacpStateR value = " + value + " property: 0x" + tenToHex(557847594));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setiTacpStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setiTacpStateS value = " + value + " property: 0x" + tenToHex(CarVendorEnergyManager.ID_I_T_A_C_P_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorEnergyManager.ID_I_T_A_C_P_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getHevElectricPointFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847587, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHevElectricPointFlagR property: 0x");
            sb.append(tenToHex(557847587));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getHevLast200MElectricConR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944740, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHevLast200MElectricConR property: 0x");
            sb.append(tenToHex(559944740));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setResetCurveDataS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setResetCurveDataS property: 0x" + tenToHex(557847589) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847589, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getElectricPointFlag() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847581, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElectricPointFlag property: 0x");
            sb.append(tenToHex(557847581));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getEnergyStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847586, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnergyStateR property: 0x");
            sb.append(tenToHex(557847586));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getDriveDistanceShowModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847558, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriveDistanceShowModeR property: 0x");
            sb.append(tenToHex(557847558));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getLast200MElectricCon() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944735, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLast200MElectricCon property: 0x");
            sb.append(tenToHex(559944735));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getLast200MOilCon() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944734, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLast200MOilCon property: 0x");
            sb.append(tenToHex(559944734));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getLast50KMElectricConAvg() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944737, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLast50KMElectricConAvg property: 0x");
            sb.append(tenToHex(559944737));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public float getLast50KMOilConAvg() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559944736, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLast50KMOilConAvg property: 0x");
            sb.append(tenToHex(559944736));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getOilPointFlag() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847580, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilPointFlag property: 0x");
            sb.append(tenToHex(557847580));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setDriveDistanceShowModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setDriveDistanceShowModeS property: 0x" + tenToHex(557847557) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847557, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getDriveDistanceShowStandardR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847569, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriveDistanceShowStandardR property: 0x");
            sb.append(tenToHex(557847569));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setDriveDistanceShowStandardS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setDriveDistanceShowStandardS property: 0x" + tenToHex(557847570) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847570, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getEPedalStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847563, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEPedalStateR property: 0x");
            sb.append(tenToHex(557847563));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setEPedalStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setEPedalStateS property: 0x" + tenToHex(557847564) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847564, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getElectricShowModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847565, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElectricShowModeR property: 0x");
            sb.append(tenToHex(557847565));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setElectricShowModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setElectricShowModeS property: 0x" + tenToHex(557847566) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847566, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getEnergyMode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847556, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnergyMode property: 0x");
            sb.append(tenToHex(557847556));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getMandatoryElectricStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847567, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMandatoryElectricStateR property: 0x");
            sb.append(tenToHex(557847567));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setMandatoryElectricStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setMandatoryElectricStateS property: 0x" + tenToHex(557847568) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847568, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getMemoryElectricStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847576, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMemoryElectricStateR property: 0x");
            sb.append(tenToHex(557847576));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setMemoryElectricStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setMemoryElectricStateS property: 0x" + tenToHex(557847577) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847577, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getMemoryMaxEvStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847574, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMemoryMaxEvStateR property: 0x");
            sb.append(tenToHex(557847574));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setMemoryMaxEvStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setMemoryMaxEvStateS property: 0x" + tenToHex(557847575) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847575, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getMemorySportStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847572, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMemorySportStateR property: 0x");
            sb.append(tenToHex(557847572));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setMemorySportStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setMemorySportStateS property: 0x" + tenToHex(557847573) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847573, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getOperationMode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847552, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOperationMode property: 0x");
            sb.append(tenToHex(557847552));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getPowerGenerationState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847554, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPowerGenerationState property: 0x");
            sb.append(tenToHex(557847554));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getPowerGenerationValue() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847555, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPowerGenerationValue property: 0x");
            sb.append(tenToHex(557847555));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getPreSaleModelStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847578, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPreSaleModelStateR property: 0x");
            sb.append(tenToHex(557847578));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setPreSaleModelStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setPreSaleModelStateS property: 0x" + tenToHex(557847579) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847579, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setResetCurveData(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setResetCurveData property: 0x" + tenToHex(557847571) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847571, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getRoadSurfaceMode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847553, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRoadSurfaceMode property: 0x");
            sb.append(tenToHex(557847553));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEnergyManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getOperationModeNormalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847590, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getOperationModeNormalR value = " + value + " property: 0x" + tenToHex(557847590));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void setOperationModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEnergyManager.TAG, "setOperationModeS value = " + value + " property: 0x" + tenToHex(557847591));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847591, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public int getRoadSurfaceModeNormalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847592, 0);
            Log.debug(CarVendorEnergyManager.TAG, "getRoadSurfaceModeNormalR value = " + value + " property: 0x" + tenToHex(557847592));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void addCarEnergyEventListener(CarVendorEnergyManager.OnCarEnergyEventListener listener) throws CarNotConnectedException {
        OnCarEnergyChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarEnergyChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorEnergyManager.TAG, "addCarEnergyEventListener propId size: " + this.mExtEnergyIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtEnergyIds) {
                int propId = num.intValue();
                Log.debug(CarVendorEnergyManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.energy.CarVendorEnergyManager
    public void removeCarEnergyEventListener(CarVendorEnergyManager.OnCarEnergyEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarEnergyChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorEnergyManager.TAG, "removeCarEnergyEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EnergyEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sEnergyEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorEnergyManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        if (propertyId == 557847617) {
            sEnergyEventChangeValue.setYEnergyConLimitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
        } else if (propertyId == 557860387) {
            sEnergyEventChangeValue.setRollBenchModeStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
        } else if (propertyId != 559944740) {
            switch (propertyId) {
                case 557847552:
                    sEnergyEventChangeValue.setOperationMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557847553:
                    sEnergyEventChangeValue.setRoadSurfaceMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557847554:
                    sEnergyEventChangeValue.setPowerGenerationState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557847555:
                    sEnergyEventChangeValue.setPowerGenerationValue(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557847556:
                    sEnergyEventChangeValue.setEnergyMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557847557:
                    sEnergyEventChangeValue.setDriveDistanceShowModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557847558:
                    sEnergyEventChangeValue.setDriveDistanceShowModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                default:
                    switch (propertyId) {
                        case 557847563:
                            sEnergyEventChangeValue.setEPedalStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847564:
                            sEnergyEventChangeValue.setEPedalStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847565:
                            sEnergyEventChangeValue.setElectricShowModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847566:
                            sEnergyEventChangeValue.setElectricShowModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847567:
                            sEnergyEventChangeValue.setMandatoryElectricStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847568:
                            sEnergyEventChangeValue.setMandatoryElectricStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847569:
                            sEnergyEventChangeValue.setDriveDistanceShowStandardR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847570:
                            sEnergyEventChangeValue.setDriveDistanceShowStandardS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847571:
                            sEnergyEventChangeValue.setResetCurveData(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847572:
                            sEnergyEventChangeValue.setMemorySportStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847573:
                            sEnergyEventChangeValue.setMemorySportStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847574:
                            sEnergyEventChangeValue.setMemoryMaxEvStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847575:
                            sEnergyEventChangeValue.setMemoryMaxEvStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847576:
                            sEnergyEventChangeValue.setMemoryElectricStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847577:
                            sEnergyEventChangeValue.setMemoryElectricStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847578:
                            sEnergyEventChangeValue.setPreSaleModelStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847579:
                            sEnergyEventChangeValue.setPreSaleModelStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847580:
                            sEnergyEventChangeValue.setOilPointFlag(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557847581:
                            sEnergyEventChangeValue.setElectricPointFlag(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        default:
                            switch (propertyId) {
                                case 557847586:
                                    sEnergyEventChangeValue.setEnergyStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                    break;
                                case 557847587:
                                    sEnergyEventChangeValue.setHevElectricPointFlagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                    break;
                                default:
                                    switch (propertyId) {
                                        case 557847589:
                                            sEnergyEventChangeValue.setResetCurveDataS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case 557847590:
                                            sEnergyEventChangeValue.setOperationModeNormalR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case 557847591:
                                            sEnergyEventChangeValue.setOperationModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case 557847592:
                                            sEnergyEventChangeValue.setRoadSurfaceModeNormalR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        default:
                                            switch (propertyId) {
                                                case 557847594:
                                                    sEnergyEventChangeValue.setiTacpStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                case CarVendorEnergyManager.ID_I_T_A_C_P_STATE_S /* 557847595 */:
                                                    sEnergyEventChangeValue.setiTacpStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                case 557847596:
                                                    sEnergyEventChangeValue.setiTacpAllowTagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                case 557847597:
                                                    sEnergyEventChangeValue.setChangePHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                case 557847598:
                                                    sEnergyEventChangeValue.setiTacExitTagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                case 557847599:
                                                    sEnergyEventChangeValue.setiTacModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                case CarVendorEnergyManager.ID_I_T_A_C_MODE_S /* 557847600 */:
                                                    sEnergyEventChangeValue.setiTacModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                case 557847601:
                                                    sEnergyEventChangeValue.setChargePortCoverTipR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                    break;
                                                default:
                                                    switch (propertyId) {
                                                        case CarVendorEnergyManager.ID_CONFIRM_CHARGE_PORT_COVER_S /* 557847603 */:
                                                            sEnergyEventChangeValue.setConfirmChargePortCoverS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847604:
                                                            sEnergyEventChangeValue.setTripDrivePercentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847605:
                                                            sEnergyEventChangeValue.setTripACPercentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847606:
                                                            sEnergyEventChangeValue.setTripElecAppliancePercentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847607:
                                                            sEnergyEventChangeValue.setTripOtherPercentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847608:
                                                            sEnergyEventChangeValue.setTripPointFlagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847609:
                                                            sEnergyEventChangeValue.setTripDrivePercent50KMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847610:
                                                            sEnergyEventChangeValue.setTripACPercent50KMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847611:
                                                            sEnergyEventChangeValue.setTripElecAppliancePercent50KMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        case 557847612:
                                                            sEnergyEventChangeValue.setTripOtherPercent50KMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                            break;
                                                        default:
                                                            switch (propertyId) {
                                                                case 559944734:
                                                                    sEnergyEventChangeValue.setLast200MOilCon(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                    break;
                                                                case 559944735:
                                                                    sEnergyEventChangeValue.setLast200MElectricCon(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                    break;
                                                                case 559944736:
                                                                    sEnergyEventChangeValue.setLast50KMOilConAvg(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                    break;
                                                                case 559944737:
                                                                    sEnergyEventChangeValue.setLast50KMElectricConAvg(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                    break;
                                                                default:
                                                                    switch (propertyId) {
                                                                        case 559944765:
                                                                            sEnergyEventChangeValue.setTripOilConAvgR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                            break;
                                                                        case 559944766:
                                                                            sEnergyEventChangeValue.setTripEnergyConAvgR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                            break;
                                                                        case 559944767:
                                                                            sEnergyEventChangeValue.setYEnergyConR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                            break;
                                                                        case 559944768:
                                                                            sEnergyEventChangeValue.setTripElecConAvgR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                            break;
                                                                        default:
                                                                            switch (propertyId) {
                                                                                case 559944770:
                                                                                    sEnergyEventChangeValue.setTripLast50KMElectricConAvgR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                                    break;
                                                                                case 559944771:
                                                                                    sEnergyEventChangeValue.setTripLast50KMOilConAvgR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                                    break;
                                                                                case 559944772:
                                                                                    sEnergyEventChangeValue.setTripLast50KMFuelConAvgR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                                                    break;
                                                                                default:
                                                                                    Log.debug(CarVendorEnergyManager.TAG, "break other properties ,except energy");
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
        } else {
            sEnergyEventChangeValue.setHevLast200MElectricConR(CastArrayUtil.toPrimitive((Float) value.getValue()));
        }
        Log.debug(CarVendorEnergyManager.TAG, "convertCarVendorPropertyValue " + sEnergyEventChangeValue.toString(), DEBUG_LOGGER);
        return sEnergyEventChangeValue;
    }

    private OnCarEnergyChangeEventListenerProxy findListenerLocked(CarVendorEnergyManager.OnCarEnergyEventListener listener) {
        for (OnCarEnergyChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarEnergyChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorEnergyManager.OnCarEnergyEventListener listener;

        public OnCarEnergyChangeEventListenerProxy(CarVendorEnergyManager.OnCarEnergyEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorEnergyMgr.this.traceCallbackPrepare();
                EnergyEventChangeValue newValue = CarVendorEnergyMgr.convertCarVendorProperty(value);
                CarVendorEnergyMgr.this.traceCallbackBefore();
                this.listener.onEnergyEventChange(newValue);
                CarVendorEnergyMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorEnergyManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorEnergyManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onEnergyErrorEvent(propertyId, zone);
        }
    }
}
