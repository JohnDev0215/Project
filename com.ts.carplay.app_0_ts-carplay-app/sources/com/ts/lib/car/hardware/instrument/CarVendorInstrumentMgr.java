package com.ts.lib.car.hardware.instrument;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorInstrumentMgr extends CarVendorManagerBase implements CarVendorInstrumentManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static InstrumentEventChangeValue sInstrumentEventChangeValue = new InstrumentEventChangeValue();
    private final LinkedList<OnCarInstrumentChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtInstrumentIds = {557857052, 559954186, 557857029, 557857026, 557857027, 557857024, 557857138, 557857036, 557857035, 557857053, 557857087, 557857102, 557857129, 557857057, 557857123, 557857099, 557857110, 557857109, 557857101, 557857095, 557857084, 557857130, 557857111, 557857104, 557857103, 557857093, 557857090, 557857091, 557857100, 557857086, 557857059, 557857060, 557857085, 557857077, 557857127, 557857069, 557857070, 557857121, 557857120, 557857080, 557857116, 557857079, 557857061, 557857071, 557857073, 557857106, 557857108, 557857105, 557857107, 557857068, 557857112, 557857063, 557857089, 557857088, 557857126, 557857128, 557857125, 557857094, 557857082, 557857118, 557857076, 557857072, 557857097, 557857096, 557857098, 557857074, 557857075, 557857131, 557857083, 557857081, 557857132, 557857056, 557857062, 557857058, 557857067, 557857066, 557857065, 557857114, 557857054, 557857122, 557857055, 557857117, 557857119, 557857064, 557857124, 557857092, 557857078, 557857113, 557857033, 557857032, 557857140, 557857048, 557857049, 557857050, 557857051, 557857046, 557857047, 557857044, 557846528, 557857144, 557868037, 557857145, 557857147, 557857146, 557857115, 557857148, 557868033, 557867010, 557867009, 557857152, Integer.valueOf(CarVendorInstrumentManager.ID_INSTRUMENT_CONFIGURE), Integer.valueOf(CarVendorInstrumentManager.ID_INSTRUMENT_SCREENTYPE), Integer.valueOf(CarVendorInstrumentManager.ID_MALFUNCTION_INFO_SECOND), Integer.valueOf(CarVendorInstrumentManager.ID_CALL_INFO_UP_R), 557857158, Integer.valueOf(CarVendorInstrumentManager.ID_ACC_COUNTER_UI), 557869067, 557869062, 557857169, 557857171, 557857172, 557857186, 557857187, 557857184, 557857185, 557857182, 557857183, 557857188, 557857189, 557857207, 557857208, 557857209, 557857210, 557868043};

    public CarVendorInstrumentMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorInstrumentManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorInstrumentManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorInstrumentManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorInstrumentManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setclearDistanceMilesTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setclearDistanceMilesTypeS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_CLEAR_DISTANCE_MILES_TYPE));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorInstrumentManager.ID_CLEAR_DISTANCE_MILES_TYPE, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getDistanceMileageUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557868043, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getDistanceMileageUnitR value = " + value + " property: 0x" + tenToHex(557868043));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicInfoMusicSourceS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicInfoMusicSourceS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_INFO_NEW_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorInstrumentManager.ID_MUSIC_INFO_NEW_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicInfoPlayProgressS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicInfoPlayProgressS value = " + value + " property: 0x" + tenToHex(557872397));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872397, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicInfoPlayStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicInfoPlayStateS value = " + value + " property: 0x" + tenToHex(557872395));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872395, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicInfoRadioStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicInfoRadioStateS value = " + value + " property: 0x" + tenToHex(557872396));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872396, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb2(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb2 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_2));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_2, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb3(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb3 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_3));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_3, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb4(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb4 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_4));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_4, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb5(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb5 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_5));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_5, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb6(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb6 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_6));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_6, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb7(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb7 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_7));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_7, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb8(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb8 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_8));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_8, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSb9(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSb9 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_9));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_9, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSbA(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSbA value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_A));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_A, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSbB(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSbB value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_B));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_B, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSbC(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSbC value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_C));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_C, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSbD(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSbD value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_D));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_D, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameSbE(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameSbE value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_E));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_NAME_S_B_E, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd2(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd2 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_2));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_2, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd3(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd3 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_3));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_3, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd4(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd4 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_4));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_4, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd5(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd5 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_5));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_5, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd6(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd6 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_6));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_6, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd7(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd7 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_7));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_7, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd8(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd8 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_8));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_8, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSd9(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSd9 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_9));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_9, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSdA(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSdA value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_A));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_A, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSdB(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSdB value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_B));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_B, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSdC(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSdC value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_C));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_C, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSdD(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSdD value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_D));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_D, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelSdE(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelSdE value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_E));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S_D_E, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMasterVolumeFirstR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857207, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMasterVolumeFirstR value = " + value + " property: 0x" + tenToHex(557857207));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMasterVolumeSecondR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857208, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMasterVolumeSecondR value = " + value + " property: 0x" + tenToHex(557857208));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS(long[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S));
        try {
            this.mCarPropertyManager.setProperty(Long[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS92(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS92 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_92));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_92, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS93(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS93 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_93));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_93, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS94(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS94 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_94));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_94, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS95(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS95 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_95));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_95, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS96(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS96 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_96));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_96, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS97(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS97 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_97));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_97, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS98(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS98 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_98));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_98, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS99(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS99 value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_99));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_99, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS9A(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS9A value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9A));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9A, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS9B(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS9B value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9B));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9B, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS9C(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS9C value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9C));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9C, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS9D(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS9D value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9D));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9D, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicSingerNameS9E(byte[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicSingerNameS9E value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9E));
        try {
            this.mCarPropertyManager.setProperty(byte[].class, CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9E, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMuteStateFirstR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857209, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMuteStateFirstR value = " + value + " property: 0x" + tenToHex(557857209));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMuteStateSecondR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857210, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMuteStateSecondR value = " + value + " property: 0x" + tenToHex(557857210));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMasterVolumeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMasterVolumeS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MASTER_VOLUME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorInstrumentManager.ID_MASTER_VOLUME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicChannelStatusFirstR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857186, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicChannelStatusFirstR value = " + value + " property: 0x" + tenToHex(557857186));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicChannelStatusSecondR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857187, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicChannelStatusSecondR value = " + value + " property: 0x" + tenToHex(557857187));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicControlStatusFirstR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857184, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicControlStatusFirstR value = " + value + " property: 0x" + tenToHex(557857184));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicControlStatusSecondR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857185, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicControlStatusSecondR value = " + value + " property: 0x" + tenToHex(557857185));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicInfoS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicInfoS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_INFO_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorInstrumentManager.ID_MUSIC_INFO_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMusicNameS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMusicNameS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUSIC_NAME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorInstrumentManager.ID_MUSIC_NAME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicProgressFirstR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857182, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicProgressFirstR value = " + value + " property: 0x" + tenToHex(557857182));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicProgressSecondR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857183, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicProgressSecondR value = " + value + " property: 0x" + tenToHex(557857183));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicResourceConfigFirstR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857188, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicResourceConfigFirstR value = " + value + " property: 0x" + tenToHex(557857188));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMusicResourceConfigSecondR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857189, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getMusicResourceConfigSecondR value = " + value + " property: 0x" + tenToHex(557857189));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMuteStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMuteStateS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_MUTE_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorInstrumentManager.ID_MUTE_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setPadPrsntTimeS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setPadPrsntTimeS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_PAD_PRSNT_TIME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorInstrumentManager.ID_PAD_PRSNT_TIME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setPadTotalTimeS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setPadTotalTimeS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_PAD_TOTAL_TIME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorInstrumentManager.ID_PAD_TOTAL_TIME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRadioChannelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRadioChannelS value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_RADIO_CHANNEL_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorInstrumentManager.ID_RADIO_CHANNEL_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getAllWeatherLightsStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857169, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getAllWeatherLightsStateR value = " + value + " property: 0x" + tenToHex(557857169));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getHeadlightControlModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857171, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getHeadlightControlModeR value = " + value + " property: 0x" + tenToHex(557857171));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setHeadlightControlModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setHeadlightControlModeS value = " + value + " property: 0x" + tenToHex(557857170));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857170, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getRearFogLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857172, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getRearFogLightStateR value = " + value + " property: 0x" + tenToHex(557857172));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setRearFogLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setRearFogLightStateS value = " + value + " property: 0x" + tenToHex(557857173));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857173, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getLowFuelWarnLightColorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557869067, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getLowFuelWarnLightColorR value = " + value + " property: 0x" + tenToHex(557869067));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getLowPowerBatteryWarnLightColorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557869062, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getLowPowerBatteryWarnLightColorR value = " + value + " property: 0x" + tenToHex(557869062));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getAreaInfoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557868037, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getAreaInfoR value = " + value + " property: 0x" + tenToHex(557868037), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setNotifyMapStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setNotifyMapStateS value = " + value + " property: 0x" + tenToHex(557857161), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857161, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setNotifyScreenStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setNotifyScreenStatusS value = " + value + " property: 0x" + tenToHex(557857159), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857159, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getDayNightModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857144, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDayNightModeR property: 0x");
            sb.append(tenToHex(557857144));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setCallInfoDownS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setCallInfoDownS value = " + value + " property: 0x" + tenToHex(557872402));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872402, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getCallInfoUpR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorInstrumentManager.ID_CALL_INFO_UP_R, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getCallInfoUpR value = " + value + " property: 0x" + tenToHex(CarVendorInstrumentManager.ID_CALL_INFO_UP_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getSpeedUpFormInsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857158, 0);
            Log.debug(CarVendorInstrumentManager.TAG, "getSpeedUpFormInsR value = " + value + " property: 0x" + tenToHex(557857158));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setDayNightModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setDayNightModeS property: 0x" + tenToHex(557857142) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857142, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getINSCenterDisplayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875262, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getINSCenterDisplayR property: 0x");
            sb.append(tenToHex(557875262));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setINSCenterDisplayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setINSCenterDisplayS property: 0x" + tenToHex(557857139) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857139, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMaintenanceInfoMileStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857145, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMaintenanceInfoMileStateR property: 0x");
            sb.append(tenToHex(557857145));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMaintenanceInfoMileStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMaintenanceInfoMileStateS property: 0x" + tenToHex(557857156) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857156, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMaintenanceInfoThreeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857147, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMaintenanceInfoThreeR property: 0x");
            sb.append(tenToHex(557857147));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMaintenanceInfoTwoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857146, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMaintenanceInfoTwoR property: 0x");
            sb.append(tenToHex(557857146));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosReservedSOne() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857115, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosReservedSOne property: 0x");
            sb.append(tenToHex(557857115));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosReservedSTwo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857148, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosReservedSTwo property: 0x");
            sb.append(tenToHex(557857148));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMileageUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557868033, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMileageUnitR property: 0x");
            sb.append(tenToHex(557868033));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getSpeedUnitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557867010, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSpeedUnitR property: 0x");
            sb.append(tenToHex(557867010));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getSpeedUpCountStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557867009, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSpeedUpCountStateR property: 0x");
            sb.append(tenToHex(557867009));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setSpeedUpCountStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setSpeedUpCountStateS property: 0x" + tenToHex(557857143) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857143, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getTotalMileageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857152, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTotalMileageR property: 0x");
            sb.append(tenToHex(557857152));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getAlarmBuzzleState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857052, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAlarmBuzzleState property: 0x");
            sb.append(tenToHex(557857052));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public float getExternalChargingPower() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954186, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getExternalChargingPower property: 0x");
            sb.append(tenToHex(559954186));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getINSAdasCenterDisplay() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857138, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getINSAdasCenterDisplay property: 0x");
            sb.append(tenToHex(557857138));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getInstrumentConfigure() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorInstrumentManager.ID_INSTRUMENT_CONFIGURE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentConfigure property: 0x");
            sb.append(tenToHex(CarVendorInstrumentManager.ID_INSTRUMENT_CONFIGURE));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getInsAdasCenterDisplayRespond40C() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857136, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInsAdasCenterDisplayRespond40C property: 0x");
            sb.append(tenToHex(557857136));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setINSAdasCenterDisplaySend26F(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setINSAdasCenterDisplaySend26F property: 0x" + tenToHex(557857137) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857137, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setINSAdasCenterDisplaySend4C1(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setINSAdasCenterDisplaySend4C1 property: 0x" + tenToHex(557857135) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857135, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getINSNavDisplayModeR() throws CarNotConnectedException {
        return 0;
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setINSNavDisplayModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setINSNavDisplayModeS property: 0x" + tenToHex(557857134) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857134, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getInstrumentAutoDimmStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857029, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentAutoDimmStateR property: 0x");
            sb.append(tenToHex(557857029));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setInstrumentAutoDimmStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setInstrumentAutoDimmStateS property: 0x" + tenToHex(557857030) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857030, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getInstrumentBrightnessLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857026, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentBrightnessLeftR property: 0x");
            sb.append(tenToHex(557857026));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getInstrumentBrightnessR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857027, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentBrightnessR property: 0x");
            sb.append(tenToHex(557857027));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setInstrumentBrightnessS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setInstrumentBrightnessS property: 0x" + tenToHex(557857028) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857028, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getInstrumentThemeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857024, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentThemeR property: 0x");
            sb.append(tenToHex(557857024));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setInstrumentThemeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setInstrumentThemeS property: 0x" + tenToHex(557857025) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857025, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMaintenanceInfoTimeState(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMaintenanceInfoTimeState property: 0x" + tenToHex(557857039) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857039, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMaintenanceInfoTimeStateInstrument() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857036, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMaintenanceInfoTimeStateInstrument property: 0x");
            sb.append(tenToHex(557857036));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public float getMaintenanceInfoTimeStateMaintain() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 557857035, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMaintenanceInfoTimeStateMaintain property: 0x");
            sb.append(tenToHex(557857035));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosACHotManaFailYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857087, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosACHotManaFailYellow property: 0x");
            sb.append(tenToHex(557857087));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosAEBFunctLimitYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857102, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosAEBFunctLimitYellow property: 0x");
            sb.append(tenToHex(557857102));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosAPAFunctionLimitWhite() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857129, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosAPAFunctionLimitWhite property: 0x");
            sb.append(tenToHex(557857129));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosBrakeFluidLevelIsLowRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857057, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosBrakeFluidLevelIsLowRed property: 0x");
            sb.append(tenToHex(557857057));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosChargPortCoverNotClsWhite() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857123, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosChargPortCoverNotClsWhite property: 0x");
            sb.append(tenToHex(557857123));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckBrakePlateYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857099, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckBrakePlateYellow property: 0x");
            sb.append(tenToHex(557857099));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckChargOutEquipYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857110, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckChargOutEquipYellow property: 0x");
            sb.append(tenToHex(557857110));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckDischargeEquipYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857109, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckDischargeEquipYellow property: 0x");
            sb.append(tenToHex(557857109));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckFMRRYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857101, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckFMRRYellow property: 0x");
            sb.append(tenToHex(557857101));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckGenerationSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857095, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckGenerationSysYellow property: 0x");
            sb.append(tenToHex(557857095));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckHeadlightSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857084, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckHeadlightSysYellow property: 0x");
            sb.append(tenToHex(557857084));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckMotPretBeltSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857130, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckMotPretBeltSysYellow property: 0x");
            sb.append(tenToHex(557857130));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckOnBoardcharSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857111, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckOnBoardcharSysYellow property: 0x");
            sb.append(tenToHex(557857111));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckPedestriDeteSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857104, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckPedestriDeteSysYellow property: 0x");
            sb.append(tenToHex(557857104));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckPredCollAlaSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857103, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckPredCollAlaSysYellow property: 0x");
            sb.append(tenToHex(557857103));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckTheBrakSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857093, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckTheBrakSysYellow property: 0x");
            sb.append(tenToHex(557857093));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckTheOilMonitSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857090, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckTheOilMonitSysYellow property: 0x");
            sb.append(tenToHex(557857090));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckTirePresMonSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857091, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckTirePresMonSysYellow property: 0x");
            sb.append(tenToHex(557857091));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCheckVideoControllerYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857100, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCheckVideoControllerYellow property: 0x");
            sb.append(tenToHex(557857100));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosCoolantLiquiLevelLowYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857086, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosCoolantLiquiLevelLowYellow property: 0x");
            sb.append(tenToHex(557857086));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosEPBIsNotReleasedYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857127, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosEPBIsNotReleasedYellow property: 0x");
            sb.append(tenToHex(557857127));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosEVFunctionIsLimitedRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857069, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosEVFunctionIsLimitedRed property: 0x");
            sb.append(tenToHex(557857069));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosEngAccessoriesLimitedRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857077, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosEngAccessoriesLimitedRed property: 0x");
            sb.append(tenToHex(557857077));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosEngineCoolTempTooHigh1Red() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857059, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosEngineCoolTempTooHigh1Red property: 0x");
            sb.append(tenToHex(557857059));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosEngineCoolTempTooHigh2Red() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857060, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosEngineCoolTempTooHigh2Red property: 0x");
            sb.append(tenToHex(557857060));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosEngineStartFailedRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857085, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosEngineStartFailedRed property: 0x");
            sb.append(tenToHex(557857085));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosExitSmartDrivingModeRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857070, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosExitSmartDrivingModeRed property: 0x");
            sb.append(tenToHex(557857070));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosExterDischrgFunctOffYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857121, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosExterDischrgFunctOffYellow property: 0x");
            sb.append(tenToHex(557857121));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosGPFNeedsRegeneration() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857116, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosGPFNeedsRegeneration property: 0x");
            sb.append(tenToHex(557857116));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getInstrumentScreenType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorInstrumentManager.ID_INSTRUMENT_SCREENTYPE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentScreenType property: 0x");
            sb.append(tenToHex(CarVendorInstrumentManager.ID_INSTRUMENT_SCREENTYPE));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosGearboxAdaptingWhiite() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857120, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosGearboxAdaptingWhiite property: 0x");
            sb.append(tenToHex(557857120));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosGearboxOilTempTooHighRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857080, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosGearboxOilTempTooHighRed property: 0x");
            sb.append(tenToHex(557857080));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosHighClutchTemperatureRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857079, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosHighClutchTemperatureRed property: 0x");
            sb.append(tenToHex(557857079));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosInsufficientOilPressureRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857061, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosInsufficientOilPressureRed property: 0x");
            sb.append(tenToHex(557857061));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosIntelligentDriveFailureRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857071, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosIntelligentDriveFailureRed property: 0x");
            sb.append(tenToHex(557857071));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLVPowerSupplySysFaultyRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857063, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLVPowerSupplySysFaultyRed property: 0x");
            sb.append(tenToHex(557857063));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLackOfParkCapacityYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857073, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLackOfParkCapacityYellow property: 0x");
            sb.append(tenToHex(557857073));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLimitLaneDepartuFuncYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857107, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLimitLaneDepartuFuncYellow property: 0x");
            sb.append(tenToHex(557857107));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLimitedAdaptiveCruiseYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857106, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLimitedAdaptiveCruiseYellow property: 0x");
            sb.append(tenToHex(557857106));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLimitedBlindSpotMoniYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857108, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLimitedBlindSpotMoniYellow property: 0x");
            sb.append(tenToHex(557857108));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLimitedLaneKeepingYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857105, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLimitedLaneKeepingYellow property: 0x");
            sb.append(tenToHex(557857105));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLowBatteryLimitPowerYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857112, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLowBatteryLimitPowerYellow property: 0x");
            sb.append(tenToHex(557857112));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosLowBatteryLimitedPowerRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857068, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosLowBatteryLimitedPowerRed property: 0x");
            sb.append(tenToHex(557857068));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosOilLevelIsTooHighYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857089, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosOilLevelIsTooHighYellow property: 0x");
            sb.append(tenToHex(557857089));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosOilLevelIsTooLowYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857088, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosOilLevelIsTooLowYellow property: 0x");
            sb.append(tenToHex(557857088));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosParkingBrakeNotReleaYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857126, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosParkingBrakeNotReleaYellow property: 0x");
            sb.append(tenToHex(557857126));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseAddFuelYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857128, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseAddFuelYellow property: 0x");
            sb.append(tenToHex(557857128));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseChargeInTimeYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857125, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseChargeInTimeYellow property: 0x");
            sb.append(tenToHex(557857125));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckABSSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857094, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckABSSysYellow property: 0x");
            sb.append(tenToHex(557857094));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckADBSystemRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857082, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckADBSystemRed property: 0x");
            sb.append(tenToHex(557857082));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckConnectorYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857118, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckConnectorYellow property: 0x");
            sb.append(tenToHex(557857118));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckEPBRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857072, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckEPBRed property: 0x");
            sb.append(tenToHex(557857072));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckESPSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857097, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckESPSysYellow property: 0x");
            sb.append(tenToHex(557857097));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckEngineSystemRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857076, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckEngineSystemRed property: 0x");
            sb.append(tenToHex(557857076));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckGearSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857096, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckGearSysYellow property: 0x");
            sb.append(tenToHex(557857096));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckHDCSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857098, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckHDCSysYellow property: 0x");
            sb.append(tenToHex(557857098));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckLBMSRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857074, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckLBMSRed property: 0x");
            sb.append(tenToHex(557857074));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckLVSupSysRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857075, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckLVSupSysRed property: 0x");
            sb.append(tenToHex(557857075));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckMemorySysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857131, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckMemorySysYellow property: 0x");
            sb.append(tenToHex(557857131));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckPowerSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857083, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckPowerSysYellow property: 0x");
            sb.append(tenToHex(557857083));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckSRSSystemRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857081, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckSRSSystemRed property: 0x");
            sb.append(tenToHex(557857081));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckSunrooSysYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857132, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckSunrooSysYellow property: 0x");
            sb.append(tenToHex(557857132));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckTheBrakeSysRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857056, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckTheBrakeSysRed property: 0x");
            sb.append(tenToHex(557857056));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckThePowerSysRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857062, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckThePowerSysRed property: 0x");
            sb.append(tenToHex(557857062));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckTheSteerSysRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857058, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckTheSteerSysRed property: 0x");
            sb.append(tenToHex(557857058));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckTransmiSys1Red() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857066, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckTransmiSys1Red property: 0x");
            sb.append(tenToHex(557857066));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckTransmisSys2Red() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857067, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckTransmisSys2Red property: 0x");
            sb.append(tenToHex(557857067));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseCheckVehicleNetRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857065, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseCheckVehicleNetRed property: 0x");
            sb.append(tenToHex(557857065));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseGoBydServShopYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857114, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseGoBydServShopYellow property: 0x");
            sb.append(tenToHex(557857114));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseLeaveVehicleImmdRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857054, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseLeaveVehicleImmdRed property: 0x");
            sb.append(tenToHex(557857054));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosPleaseStopDischargingYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857122, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosPleaseStopDischargingYellow property: 0x");
            sb.append(tenToHex(557857122));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosRemoteCtrlDriveSuspeYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857055, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosRemoteCtrlDriveSuspeYellow property: 0x");
            sb.append(tenToHex(557857055));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosRestrictedDriveFunctionRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857117, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosRestrictedDriveFunctionRed property: 0x");
            sb.append(tenToHex(557857117));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosSolenoidValveCleanWhiite() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857119, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosSolenoidValveCleanWhiite property: 0x");
            sb.append(tenToHex(557857119));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosStartTypeIronBatFaultyRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857064, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosStartTypeIronBatFaultyRed property: 0x");
            sb.append(tenToHex(557857064));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosSwitchToPRangeYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857124, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosSwitchToPRangeYellow property: 0x");
            sb.append(tenToHex(557857124));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosTirePressureIsLowYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857092, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosTirePressureIsLowYellow property: 0x");
            sb.append(tenToHex(557857092));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosTransmisLimitedinFuncRed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857078, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosTransmisLimitedinFuncRed property: 0x");
            sb.append(tenToHex(557857078));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalFunctionInfosVehicleEntForEvModeYellow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857113, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalFunctionInfosVehicleEntForEvModeYellow property: 0x");
            sb.append(tenToHex(557857113));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalfunctionInfo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857053, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalfunctionInfo property: 0x");
            sb.append(tenToHex(557857053));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMilesByType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857033, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMilesByType property: 0x");
            sb.append(tenToHex(557857033));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMalfunctionInfoSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorInstrumentManager.ID_MALFUNCTION_INFO_SECOND, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMalfunctionInfoSecond property: 0x");
            sb.append(tenToHex(CarVendorInstrumentManager.ID_MALFUNCTION_INFO_SECOND));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getMilesTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857032, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMilesTypeR property: 0x");
            sb.append(tenToHex(557857032));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setMilesTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setMilesTypeS property: 0x" + tenToHex(557857031) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857031, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getSportModeState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857140, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSportModeState property: 0x");
            sb.append(tenToHex(557857140));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitFuelConsumptionAndDistanceInstrumentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857048, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitFuelConsumptionAndDistanceInstrumentR property: 0x");
            sb.append(tenToHex(557857048));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitFuelConsumptionAndDistanceLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857049, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitFuelConsumptionAndDistanceLeftR property: 0x");
            sb.append(tenToHex(557857049));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setUnitFuelConsumptionAndDistanceS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setUnitFuelConsumptionAndDistanceS property: 0x" + tenToHex(557857042) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857042, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitPowerInstrumentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857050, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitPowerInstrumentR property: 0x");
            sb.append(tenToHex(557857050));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitPowerLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857051, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitPowerLeftR property: 0x");
            sb.append(tenToHex(557857051));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getAccCounterUiR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorInstrumentManager.ID_ACC_COUNTER_UI, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAccCounterUiR property: 0x");
            sb.append(tenToHex(CarVendorInstrumentManager.ID_ACC_COUNTER_UI));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setUnitPowerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setUnitPowerS property: 0x" + tenToHex(557857043) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857043, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitPressureInstrumentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857046, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitPressureInstrumentR property: 0x");
            sb.append(tenToHex(557857046));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitPressureLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857047, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitPressureLeftR property: 0x");
            sb.append(tenToHex(557857047));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setUnitPressureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setUnitPressureS property: 0x" + tenToHex(557857041) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857041, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitTemperatureInstrumentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857044, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitTemperatureInstrumentR property: 0x");
            sb.append(tenToHex(557857044));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public int getUnitTemperatureLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846528, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUnitTemperatureLeftR property: 0x");
            sb.append(tenToHex(557846528));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorInstrumentManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void setUnitTemperatureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorInstrumentManager.TAG, "setUnitTemperatureS property: 0x" + tenToHex(557857040) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857040, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void addCarInstrumentEventListener(CarVendorInstrumentManager.OnCarInstrumentEventListener listener) throws CarNotConnectedException {
        OnCarInstrumentChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarInstrumentChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorInstrumentManager.TAG, "addCarInstrumentEventListener propId size: " + this.mExtInstrumentIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtInstrumentIds) {
                int propId = num.intValue();
                Log.debug(CarVendorInstrumentManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager
    public void removeCarInstrumentEventListener(CarVendorInstrumentManager.OnCarInstrumentEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarInstrumentChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorInstrumentManager.TAG, "removeCarInstrumentEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InstrumentEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sInstrumentEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorInstrumentManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557846528:
                sInstrumentEventChangeValue.setUnitTemperatureLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857046:
                sInstrumentEventChangeValue.setUnitPressureInstrumentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857047:
                sInstrumentEventChangeValue.setUnitPressureLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857048:
                sInstrumentEventChangeValue.setUnitFuelConsumptionAndDistanceInstrumentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857049:
                sInstrumentEventChangeValue.setUnitFuelConsumptionAndDistanceLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857050:
                sInstrumentEventChangeValue.setUnitPowerInstrumentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857051:
                sInstrumentEventChangeValue.setUnitPowerLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857052:
                sInstrumentEventChangeValue.setAlarmBuzzleState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857053:
                sInstrumentEventChangeValue.setMalfunctionInfo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857054:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseLeaveVehicleImmdRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857055:
                sInstrumentEventChangeValue.setMalFunctionInfosRemoteCtrlDriveSuspeYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857056:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckTheBrakeSysRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857057:
                sInstrumentEventChangeValue.setMalFunctionInfosBrakeFluidLevelIsLowRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857058:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckTheSteerSysRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857059:
                sInstrumentEventChangeValue.setMalFunctionInfosEngineCoolTempTooHigh1Red(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857060:
                sInstrumentEventChangeValue.setMalFunctionInfosEngineCoolTempTooHigh2Red(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857061:
                sInstrumentEventChangeValue.setMalFunctionInfosInsufficientOilPressureRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857062:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckThePowerSysRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857063:
                sInstrumentEventChangeValue.setMalFunctionInfosLVPowerSupplySysFaultyRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857064:
                sInstrumentEventChangeValue.setMalFunctionInfosStartTypeIronBatFaultyRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857065:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckVehicleNetRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857066:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckTransmiSys1Red(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857067:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckTransmisSys2Red(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857068:
                sInstrumentEventChangeValue.setMalFunctionInfosLowBatteryLimitedPowerRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857069:
                sInstrumentEventChangeValue.setMalFunctionInfosEVFunctionIsLimitedRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857070:
                sInstrumentEventChangeValue.setMalFunctionInfosExitSmartDrivingModeRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857071:
                sInstrumentEventChangeValue.setMalFunctionInfosIntelligentDriveFailureRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857072:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckEPBRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857073:
                sInstrumentEventChangeValue.setMalFunctionInfosLackOfParkCapacityYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857074:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckLBMSRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857075:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckLVSupSysRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857076:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckEngineSystemRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857077:
                sInstrumentEventChangeValue.setMalFunctionInfosEngAccessoriesLimitedRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857078:
                sInstrumentEventChangeValue.setMalFunctionInfosTransmisLimitedinFuncRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857079:
                sInstrumentEventChangeValue.setMalFunctionInfosHighClutchTemperatureRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857080:
                sInstrumentEventChangeValue.setMalFunctionInfosGearboxOilTempTooHighRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857081:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckSRSSystemRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857082:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckADBSystemRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857083:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckPowerSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857084:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckHeadlightSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857085:
                sInstrumentEventChangeValue.setMalFunctionInfosEngineStartFailedRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857086:
                sInstrumentEventChangeValue.setMalFunctionInfosCoolantLiquiLevelLowYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857087:
                sInstrumentEventChangeValue.setMalFunctionInfosACHotManaFailYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857088:
                sInstrumentEventChangeValue.setMalFunctionInfosOilLevelIsTooLowYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857089:
                sInstrumentEventChangeValue.setMalFunctionInfosOilLevelIsTooHighYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857090:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckTheOilMonitSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857091:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckTirePresMonSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857092:
                sInstrumentEventChangeValue.setMalFunctionInfosTirePressureIsLowYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857093:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckTheBrakSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857094:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckABSSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857095:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckGenerationSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857096:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckGearSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857097:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckESPSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857098:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckHDCSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857099:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckBrakePlateYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857100:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckVideoControllerYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857101:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckFMRRYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857102:
                sInstrumentEventChangeValue.setMalFunctionInfosAEBFunctLimitYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857103:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckPredCollAlaSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857104:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckPedestriDeteSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857105:
                sInstrumentEventChangeValue.setMalFunctionInfosLimitedLaneKeepingYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857106:
                sInstrumentEventChangeValue.setMalFunctionInfosLimitedAdaptiveCruiseYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857107:
                sInstrumentEventChangeValue.setMalFunctionInfosLimitLaneDepartuFuncYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857108:
                sInstrumentEventChangeValue.setMalFunctionInfosLimitedBlindSpotMoniYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857109:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckDischargeEquipYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857110:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckChargOutEquipYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857111:
                sInstrumentEventChangeValue.setMalFunctionInfosCheckOnBoardcharSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857112:
                sInstrumentEventChangeValue.setMalFunctionInfosLowBatteryLimitPowerYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857113:
                sInstrumentEventChangeValue.setMalFunctionInfosVehicleEntForEvModeYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857114:
                sInstrumentEventChangeValue.setMalFunctionInfosPleaseGoBydServShopYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857140:
                sInstrumentEventChangeValue.setSportModeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MALFUNCTION_INFO_SECOND /* 557857150 */:
                sInstrumentEventChangeValue.setMalfunctionInfoSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857158:
                sInstrumentEventChangeValue.setSpeedUpFormInsR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557868033:
                sInstrumentEventChangeValue.setMileageUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557868037:
                sInstrumentEventChangeValue.setAreaInfoR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557868043:
                sInstrumentEventChangeValue.setDistanceMileageUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557869062:
                sInstrumentEventChangeValue.setLowPowerBatteryWarnLightColorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557869067:
                sInstrumentEventChangeValue.setLowFuelWarnLightColorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557872402:
                sInstrumentEventChangeValue.setCallInfoDownS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_CALL_INFO_UP_R /* 557875467 */:
                sInstrumentEventChangeValue.setCallInfoUpR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_PAD_PRSNT_TIME_S /* 557922710 */:
                sInstrumentEventChangeValue.setPadPrsntTimeS(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_PAD_TOTAL_TIME_S /* 557922713 */:
                sInstrumentEventChangeValue.setPadTotalTimeS(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_INFO_S /* 557922726 */:
                sInstrumentEventChangeValue.setMusicInfoS(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_NAME_S /* 558971303 */:
                sInstrumentEventChangeValue.setMusicNameS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S /* 558971305 */:
                sInstrumentEventChangeValue.setMusicSingerNameS(CastArrayUtil.toPrimitive((Long[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_RADIO_CHANNEL_S /* 558971337 */:
                sInstrumentEventChangeValue.setRadioChannelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 559954186:
                sInstrumentEventChangeValue.setExternalChargingPower(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_92 /* 561002922 */:
                sInstrumentEventChangeValue.setMusicSingerNameS92(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_93 /* 561002923 */:
                sInstrumentEventChangeValue.setMusicSingerNameS93(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_94 /* 561002924 */:
                sInstrumentEventChangeValue.setMusicSingerNameS94(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_95 /* 561002925 */:
                sInstrumentEventChangeValue.setMusicSingerNameS95(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_96 /* 561002926 */:
                sInstrumentEventChangeValue.setMusicSingerNameS96(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_97 /* 561002927 */:
                sInstrumentEventChangeValue.setMusicSingerNameS97(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_98 /* 561002928 */:
                sInstrumentEventChangeValue.setMusicSingerNameS98(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_99 /* 561002929 */:
                sInstrumentEventChangeValue.setMusicSingerNameS99(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9A /* 561002930 */:
                sInstrumentEventChangeValue.setMusicSingerNameS9A(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9B /* 561002931 */:
                sInstrumentEventChangeValue.setMusicSingerNameS9B(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9C /* 561002932 */:
                sInstrumentEventChangeValue.setMusicSingerNameS9C(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9D /* 561002933 */:
                sInstrumentEventChangeValue.setMusicSingerNameS9D(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            case CarVendorInstrumentManager.ID_MUSIC_SINGER_NAME_S_9E /* 561002934 */:
                sInstrumentEventChangeValue.setMusicSingerNameS9E(CastArrayUtil.toPrimitive((byte[]) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557857024:
                        sInstrumentEventChangeValue.setInstrumentThemeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857025:
                        sInstrumentEventChangeValue.setInstrumentThemeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857026:
                        sInstrumentEventChangeValue.setInstrumentBrightnessLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857027:
                        sInstrumentEventChangeValue.setInstrumentBrightnessR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857028:
                        sInstrumentEventChangeValue.setInstrumentBrightnessS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857029:
                        sInstrumentEventChangeValue.setInstrumentAutoDimmStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857030:
                        sInstrumentEventChangeValue.setInstrumentAutoDimmStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857031:
                        sInstrumentEventChangeValue.setMilesTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857032:
                        sInstrumentEventChangeValue.setMilesTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557857033:
                        sInstrumentEventChangeValue.setMilesByType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557857035:
                                sInstrumentEventChangeValue.setMaintenanceInfoTimeStateMaintain(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case 557857036:
                                sInstrumentEventChangeValue.setMaintenanceInfoTimeStateInstrument(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557857037:
                                sInstrumentEventChangeValue.setMaintenanceInfoTimeState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557857040:
                                        sInstrumentEventChangeValue.setUnitTemperatureS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857041:
                                        sInstrumentEventChangeValue.setUnitPressureS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857042:
                                        sInstrumentEventChangeValue.setUnitFuelConsumptionAndDistanceS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857043:
                                        sInstrumentEventChangeValue.setUnitPowerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857044:
                                        sInstrumentEventChangeValue.setUnitTemperatureInstrumentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557857116:
                                                sInstrumentEventChangeValue.setMalFunctionInfosGPFNeedsRegeneration(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857117:
                                                sInstrumentEventChangeValue.setMalFunctionInfosRestrictedDriveFunctionRed(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857118:
                                                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckConnectorYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857119:
                                                sInstrumentEventChangeValue.setMalFunctionInfosSolenoidValveCleanWhiite(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857120:
                                                sInstrumentEventChangeValue.setMalFunctionInfosGearboxAdaptingWhiite(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857121:
                                                sInstrumentEventChangeValue.setMalFunctionInfosExterDischrgFunctOffYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857122:
                                                sInstrumentEventChangeValue.setMalFunctionInfosPleaseStopDischargingYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857123:
                                                sInstrumentEventChangeValue.setMalFunctionInfosChargPortCoverNotClsWhite(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857124:
                                                sInstrumentEventChangeValue.setMalFunctionInfosSwitchToPRangeYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857125:
                                                sInstrumentEventChangeValue.setMalFunctionInfosPleaseChargeInTimeYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857126:
                                                sInstrumentEventChangeValue.setMalFunctionInfosParkingBrakeNotReleaYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857127:
                                                sInstrumentEventChangeValue.setMalFunctionInfosEPBIsNotReleasedYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857128:
                                                sInstrumentEventChangeValue.setMalFunctionInfosPleaseAddFuelYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857129:
                                                sInstrumentEventChangeValue.setMalFunctionInfosAPAFunctionLimitWhite(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857130:
                                                sInstrumentEventChangeValue.setMalFunctionInfosCheckMotPretBeltSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857131:
                                                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckMemorySysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857132:
                                                sInstrumentEventChangeValue.setMalFunctionInfosPleaseCheckSunrooSysYellow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857133:
                                                sInstrumentEventChangeValue.setINSNavDisplayModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857134:
                                                sInstrumentEventChangeValue.setINSNavDisplayModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857135:
                                                sInstrumentEventChangeValue.setINSAdasCenterDisplaySend4C1(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857136:
                                                sInstrumentEventChangeValue.setINSAdasCenterDisplayRespond40C(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857137:
                                                sInstrumentEventChangeValue.setINSAdasCenterDisplaySend26F(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857138:
                                                sInstrumentEventChangeValue.setINSAdasCenterDisplay(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case CarVendorInstrumentManager.ID_INSTRUMENT_SCREENTYPE /* 557857154 */:
                                                        sInstrumentEventChangeValue.setInstrumentScreenType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case CarVendorInstrumentManager.ID_INSTRUMENT_CONFIGURE /* 557857155 */:
                                                        sInstrumentEventChangeValue.setInstrumentConfigure(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 557857169:
                                                                sInstrumentEventChangeValue.setAllWeatherLightsStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857170:
                                                                sInstrumentEventChangeValue.setHeadlightControlModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857171:
                                                                sInstrumentEventChangeValue.setHeadlightControlModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857172:
                                                                sInstrumentEventChangeValue.setRearFogLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857173:
                                                                sInstrumentEventChangeValue.setRearFogLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case CarVendorInstrumentManager.ID_MASTER_VOLUME_S /* 557857180 */:
                                                                        sInstrumentEventChangeValue.setMasterVolumeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case CarVendorInstrumentManager.ID_MUTE_STATE_S /* 557857181 */:
                                                                        sInstrumentEventChangeValue.setMuteStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857182:
                                                                        sInstrumentEventChangeValue.setMusicProgressFirstR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857183:
                                                                        sInstrumentEventChangeValue.setMusicProgressSecondR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857184:
                                                                        sInstrumentEventChangeValue.setMusicControlStatusFirstR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857185:
                                                                        sInstrumentEventChangeValue.setMusicControlStatusSecondR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857186:
                                                                        sInstrumentEventChangeValue.setMusicChannelStatusFirstR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857187:
                                                                        sInstrumentEventChangeValue.setMusicChannelStatusSecondR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857188:
                                                                        sInstrumentEventChangeValue.setMusicResourceConfigFirstR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857189:
                                                                        sInstrumentEventChangeValue.setMusicResourceConfigSecondR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557857207:
                                                                                sInstrumentEventChangeValue.setMasterVolumeFirstR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557857208:
                                                                                sInstrumentEventChangeValue.setMasterVolumeSecondR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557857209:
                                                                                sInstrumentEventChangeValue.setMuteStateFirstR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557857210:
                                                                                sInstrumentEventChangeValue.setMuteStateSecondR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case 557867009:
                                                                                        sInstrumentEventChangeValue.setSpeedUpCountStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557867010:
                                                                                        sInstrumentEventChangeValue.setSpeedUnitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorInstrumentManager.ID_ACC_COUNTER_UI /* 557867011 */:
                                                                                        sInstrumentEventChangeValue.setAccCounterUiR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorInstrumentManager.TAG, "break other properties ,except instrument");
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
        Log.debug(CarVendorInstrumentManager.TAG, "convertCarVendorPropertyValue " + sInstrumentEventChangeValue.toString(), DEBUG_LOGGER);
        return sInstrumentEventChangeValue;
    }

    private OnCarInstrumentChangeEventListenerProxy findListenerLocked(CarVendorInstrumentManager.OnCarInstrumentEventListener listener) {
        for (OnCarInstrumentChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarInstrumentChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorInstrumentManager.OnCarInstrumentEventListener listener;

        public OnCarInstrumentChangeEventListenerProxy(CarVendorInstrumentManager.OnCarInstrumentEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorInstrumentMgr.this.traceCallbackPrepare();
                InstrumentEventChangeValue newValue = CarVendorInstrumentMgr.convertCarVendorProperty(value);
                CarVendorInstrumentMgr.this.traceCallbackBefore();
                this.listener.onInstrumentEventChange(newValue);
                CarVendorInstrumentMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorInstrumentManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorInstrumentManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onInstrumentErrorEvent(propertyId, zone);
        }
    }
}
