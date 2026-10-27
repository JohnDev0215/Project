package com.ts.lib.car.hardware.fragrance;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorFragranceMgr extends CarVendorManagerBase implements CarVendorFragranceManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static FragranceEventChangeValue sFragranceEventChangeValue = new FragranceEventChangeValue();
    private final LinkedList<OnCarFragranceChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtFragranceIds = {557858816, 557858819, 557858818, 557858822, 557858821, Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_AAR), 557858825, Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_BAR), 557858827, Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CAR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CBR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DAR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DBR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_AAR), 557858841, Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_BAR), 557858843, Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CAR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CBR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DAR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DBR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_AAR), 557858833, Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_BAR), 557858835, Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CAR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CBR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DAR), Integer.valueOf(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DBR), 557858823, 557858849, 557858848, 557856028, 557856029, 557856030};

    public CarVendorFragranceMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorFragranceManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorFragranceManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorFragranceManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorFragranceManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneAaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_AAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneAaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_AAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneAbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858825, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneAbR property: 0x");
            sb.append(tenToHex(557858825));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneBaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_BAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneBaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_BAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneBbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858827, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneBbR property: 0x");
            sb.append(tenToHex(557858827));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneCaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneCaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneCbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CBR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneCbR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CBR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneDaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneDaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameOneDbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DBR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameOneDbR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DBR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeAaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_AAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeAaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_AAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeAbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858841, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeAbR property: 0x");
            sb.append(tenToHex(557858841));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeBaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_BAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeBaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_BAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeBbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858843, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeBbR property: 0x");
            sb.append(tenToHex(557858843));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeCaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeCaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeCbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CBR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeCbR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CBR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeDaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeDaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameThreeDbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DBR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameThreeDbR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DBR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoAaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_AAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoAaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_AAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoAbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858833, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoAbR property: 0x");
            sb.append(tenToHex(557858833));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoBaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_BAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoBaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_BAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoBbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858835, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoBbR property: 0x");
            sb.append(tenToHex(557858835));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoCaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoCaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoCbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CBR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoCbR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CBR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoDaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DAR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoDaR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DAR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceNameTwoDbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DBR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceNameTwoDbR property: 0x");
            sb.append(tenToHex(CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DBR));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getCurFragranceType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858816, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCurFragranceType property: 0x");
            sb.append(tenToHex(557858816));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceConcentrationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858819, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceConcentrationR property: 0x");
            sb.append(tenToHex(557858819));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public void setFragranceConcentrationS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorFragranceManager.TAG, "setFragranceConcentrationS property: 0x" + tenToHex(557858820) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858820, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceResidueOne() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858818, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceResidueOne property: 0x");
            sb.append(tenToHex(557858818));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceResidueThree() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858822, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceResidueThree property: 0x");
            sb.append(tenToHex(557858822));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceResidueTwo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858821, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceResidueTwo property: 0x");
            sb.append(tenToHex(557858821));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public void setFragranceTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorFragranceManager.TAG, "setFragranceTypeS property: 0x" + tenToHex(557858817) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858817, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceLessAllowance() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858823, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceLessAllowance(int value) property: 0x");
            sb.append(tenToHex(557858823));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public void addCarFragranceEventListener(CarVendorFragranceManager.OnCarFragranceEventListener listener) throws CarNotConnectedException {
        OnCarFragranceChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarFragranceChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorFragranceManager.TAG, "addCarFragranceEventListener propId size: " + this.mExtFragranceIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtFragranceIds) {
                int propId = num.intValue();
                Log.debug(CarVendorFragranceManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public void removeCarFragranceEventListener(CarVendorFragranceManager.OnCarFragranceEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarFragranceChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorFragranceManager.TAG, "removeCarFragranceEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getSupportFragranceTypeOne() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856028, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFragranceTypeOne property: 0x");
            sb.append(tenToHex(557856028));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getSupportFragranceTypeThree() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856030, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFragranceTypeThree property: 0x");
            sb.append(tenToHex(557856030));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getSupportFragranceTypeTwo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856029, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFragranceTypeTwo property: 0x");
            sb.append(tenToHex(557856029));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceLessAllowanceThreeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858849, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceLessAllowanceThreeR property: 0x");
            sb.append(tenToHex(557858849));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager
    public int getFragranceLessAllowanceTwoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858848, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFragranceLessAllowanceTwoR property: 0x");
            sb.append(tenToHex(557858848));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorFragranceManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FragranceEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sFragranceEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorFragranceManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557856028:
                sFragranceEventChangeValue.setSupportFragranceTypeOne(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856029:
                sFragranceEventChangeValue.setSupportFragranceTypeTwo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856030:
                sFragranceEventChangeValue.setSupportFragranceTypeThree(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557858816:
                        sFragranceEventChangeValue.setCurFragranceType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858817:
                        sFragranceEventChangeValue.setFragranceTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858818:
                        sFragranceEventChangeValue.setFragranceResidueOne(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858819:
                        sFragranceEventChangeValue.setFragranceConcentrationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858820:
                        sFragranceEventChangeValue.setFragranceConcentrationS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858821:
                        sFragranceEventChangeValue.setFragranceResidueTwo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858822:
                        sFragranceEventChangeValue.setFragranceResidueThree(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858823:
                        sFragranceEventChangeValue.setFragranceLessAllowance(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_AAR /* 557858824 */:
                        sFragranceEventChangeValue.setFragranceNameOneAaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858825:
                        sFragranceEventChangeValue.setFragranceNameOneAbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_BAR /* 557858826 */:
                        sFragranceEventChangeValue.setFragranceNameOneBaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858827:
                        sFragranceEventChangeValue.setFragranceNameOneBbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CAR /* 557858828 */:
                        sFragranceEventChangeValue.setFragranceNameOneCaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_CBR /* 557858829 */:
                        sFragranceEventChangeValue.setFragranceNameOneCbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DAR /* 557858830 */:
                        sFragranceEventChangeValue.setFragranceNameOneDaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_ONE_DBR /* 557858831 */:
                        sFragranceEventChangeValue.setFragranceNameOneDbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_AAR /* 557858832 */:
                        sFragranceEventChangeValue.setFragranceNameTwoAaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858833:
                        sFragranceEventChangeValue.setFragranceNameTwoAbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_BAR /* 557858834 */:
                        sFragranceEventChangeValue.setFragranceNameTwoBaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858835:
                        sFragranceEventChangeValue.setFragranceNameTwoBbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CAR /* 557858836 */:
                        sFragranceEventChangeValue.setFragranceNameTwoCaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_CBR /* 557858837 */:
                        sFragranceEventChangeValue.setFragranceNameTwoCbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DAR /* 557858838 */:
                        sFragranceEventChangeValue.setFragranceNameTwoDaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_TWO_DBR /* 557858839 */:
                        sFragranceEventChangeValue.setFragranceNameTwoDbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_AAR /* 557858840 */:
                        sFragranceEventChangeValue.setFragranceNameThreeAaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858841:
                        sFragranceEventChangeValue.setFragranceNameThreeAbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_BAR /* 557858842 */:
                        sFragranceEventChangeValue.setFragranceNameThreeBaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858843:
                        sFragranceEventChangeValue.setFragranceNameThreeBbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CAR /* 557858844 */:
                        sFragranceEventChangeValue.setFragranceNameThreeCaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_CBR /* 557858845 */:
                        sFragranceEventChangeValue.setFragranceNameThreeCbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DAR /* 557858846 */:
                        sFragranceEventChangeValue.setFragranceNameThreeDaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorFragranceManager.ID_FRAGRANCE_NAME_THREE_DBR /* 557858847 */:
                        sFragranceEventChangeValue.setFragranceNameThreeDbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858848:
                        sFragranceEventChangeValue.setFragranceLessAllowanceTwoR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557858849:
                        sFragranceEventChangeValue.setFragranceLessAllowanceThreeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        Log.debug(CarVendorFragranceManager.TAG, "break other properties ,except fragrance");
                        break;
                }
                break;
        }
        Log.debug(CarVendorFragranceManager.TAG, "convertCarVendorPropertyValue " + sFragranceEventChangeValue.toString(), DEBUG_LOGGER);
        return sFragranceEventChangeValue;
    }

    private OnCarFragranceChangeEventListenerProxy findListenerLocked(CarVendorFragranceManager.OnCarFragranceEventListener listener) {
        for (OnCarFragranceChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarFragranceChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorFragranceManager.OnCarFragranceEventListener listener;

        public OnCarFragranceChangeEventListenerProxy(CarVendorFragranceManager.OnCarFragranceEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorFragranceMgr.this.traceCallbackPrepare();
                FragranceEventChangeValue newValue = CarVendorFragranceMgr.convertCarVendorProperty(value);
                CarVendorFragranceMgr.this.traceCallbackBefore();
                this.listener.onFragranceEventChange(newValue);
                CarVendorFragranceMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorFragranceManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorFragranceManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onFragranceErrorEvent(propertyId, zone);
        }
    }
}
