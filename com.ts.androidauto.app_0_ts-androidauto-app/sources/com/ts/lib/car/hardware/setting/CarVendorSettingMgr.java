package com.ts.lib.car.hardware.setting;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.setting.CarVendorSettingManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorSettingMgr extends CarVendorManagerBase implements CarVendorSettingManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static SettingEventChangeValue sSettingEventChangeValue = new SettingEventChangeValue();
    private final LinkedList<OnCarSettingChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtSettingIds = {557857536, 557857543, 557857540, 557857555, 557857557, 557857796, 557857597, 557857594, 557857595, 557846608, 557857609, 557857561, 557857626, 557857603, 557857792, 557857793, 557857605, 557857705, 557857565, 557857599, 557857611, 557857788, 557857587, 557871104, 557857759, 557857690, 557857691, 557857689, 557857688, 559955504, 559955505, 559954845, 559954844, 557857751, 557857747, 557857576, 557857582, 557857583, 557857574, 557857586, 557857580, 557857578, 557857571, 557857721, 557857718, 557857719, 557857717, 557857716, 557857713, 557857712, 557857711, 557857710, 557857762, 557857560, 557857569, 557857567, 557857744, 557857803, 557857749, 557857624, 557857622, 557857621, 557857768, 557857769, 557857764, 557857765, 557857771, 557857770, 557857766, 557857767, 557857776, 557857779, 557857781, 557857782, 557857778, 557857780, 557857783, 557857617, 557857619, 557856058, 557857755, 557857799, 557857549, 557857551, 557857553, 557857563, 559954814, 559954815, 559954812, 559954813, 557856073, 557857591, 557857592, 557857703, 557857699, 557857708, 557857701, 557857615, 557857613, 557856063, 557857750, 559954826, 559954827, 559954825, 559954824, 557857726, 557857727, 557857729, 557857725, 557857728, 557857724, 557857666, 557857667, 557857665, 557857664, 559954822, 559954823, 559954821, 559954820, 557857736, 557857737, 557857739, 557857735, 557857738, 557857734, 557857752, 557857790, 557857589, 559954810, 559954811, 557857800, 557857802, 557856146, 557857709, 557857706, 559954838, 559954839, 559954837, 559954836, 559954834, 559954835, 559954833, 559954832, 557857833, 557857838, 557857835, 557857814, 557857807, 557857808, 557857809, 557857805, 557857806, 557857811, 557857723, 557857730, 557857731, 557857740, 557857741, 557857733, 557857813, 557857844, 557857846, 557857845, 557857841, 557857843, 557857842, 557857840, 557857810, 557857847, 557857848, 557857849, 557857850, 557857851, 557857852, 557857912, 557857913, 557857910, 557857911, 557857896, 557857897, 557857894, 557857895, 557857880, 557857881, 557857878, 557857879, 557857904, 557857905, 557857902, 557857903, 557857888, 557857889, 557857886, 557857887, 557857872, 557857873, 557857870, 557857871, 557857922, 557857923, 557857928, 557857929, 557857944, 557859104, 557859105, 557857938, 557859103, 557857857, 557857853, 557857855, 557857859, 557857945, 557859101, 557858329, 557857862, 557858305, 557857866, 557857864, 557857865, 557857869, 557857867, 557857868, 557857921, 557857919, 557857804, 557858331, 557858330, 557858345, 557858334, 557858337, 557858336, 557858340, 557862926, 557858354, 557860631, 557860611, 557858415, 557858413, 557858366, 557858365, 557858375, 557858411, 557858374, 557858378, 557858379, 557858361, 557858377, 557858391, 557858390, 557858399, 557858383, 557858384, 557858385, 557858382, 557858393, 557858388, 557858392, 557858395, 557858394, 557858398, 557858387, 557858386, 557858396, 557858368, 557858412, 557858367, 557858370, 557858414, 557858355, 557858376, 557858360, 557858416, 557858369, 557858364, 557858358, 557858373, 557858357, 557858418, 557858363, 557858362, 557858371, 557858429, 557858389, 557858397, 557856185, 557856184, 557856186, 557856076, 557856889, 557856891, 557858462, 557858461, 557858445, 557858459, 557858449, 557858441, 557856892, 557856894, 557858446, 557858460, 557858450, 557858442, 557858372, 557858527, 557858528, 557858529, 557858530, 557867008, 557858025, 557858010, 557857990, 557858026, 557858021, 557858017, 557858016, 557857982, 557857972, 557857968, 557857992, 557857970, 557858018, 557857984, Integer.valueOf(CarVendorSettingManager.ID_ELECTRIC_TAIL_CONFIGURATION_R), 557858027, 557857951, 557858063, 557857979, 557857985, 557857965, 557857966, 557857967, 557858058, 557857997, 557857958, 557857959, 557857957, 557857994, 557857995, Integer.valueOf(CarVendorSettingManager.ID_REAR_LEFT_MASSAGE_SW_CONFIGURATION_R), Integer.valueOf(CarVendorSettingManager.ID_REAR_RIGHT_MASSAGE_SW_CONFIGURATION_R), 557857996, 557857981, 557857962, 557857980, 557857964, 557857974, 557858060, 557857993, 557857998, 557858011, 557857976, 557857983, 557858020, 557858023, 557858024, 557858022, 557857977, 557857991, 557859165, 557858089, 557858090, 557858084, 557858083, 557858093, 557875283, 557858091, 557858096, Integer.valueOf(CarVendorSettingManager.ID_PHYSICALKEY_CONFIG_R), Integer.valueOf(CarVendorSettingManager.ID_PHYSICALKEY_SECUP_CONFIG_R), Integer.valueOf(CarVendorSettingManager.ID_PHYSICALKEY_SECURL_CONFIG_R), Integer.valueOf(CarVendorSettingManager.ID_PHYSICALKEY_SECURR_CONFIG_R), Integer.valueOf(CarVendorSettingManager.ID_PHYSICALKEY_SECU_CONFIG_R), 557858102, 557847553, Integer.valueOf(CarVendorSettingManager.ID_STEERING_WHEEL_HEATED_PLAN_R), 557858107, 557858108, 557858110, 557858109, 557858111, 557858116, 557858112, 557859210, 557859211, 557858119, 557859205, 557858122, 557858123, 557858114, 557858115, 557858117, 557858126, 557858125, 557858128, 557858127, 557858142, 557858141, 557858144, 557858147, 557858146, 557858154, 557858149, 557858148, 557858143, 557858145, 557858151, 557858150, 557858153, 557858152, 557858140, 557858157, 557858158, 557858162, 557858163, 557858161, 557858160, 557859134, 557859144, 557858159, 557858196, 557858197, 557858194, 557858195, 557858168, 557858167, 557858204, 557858219, 557858220, 557858218, 557858207, 557858206, 557858217, 557858216, 557858212, 557858210, 557858215, 557858205, 557858214, 557858213, 557858203, 557858202, 557858225, 557858226, 557858227, 557858224, 557858209, 557858208, 557858222, 557858223, 557858221, 557858228, 557858229, 557858230, 557858231, 557858166, 557858165, 557858200, 557858201, 557858198, 557858199, 557858177, 557858173, 557858171, 557858176, 557858170, 557858175, 557858169, 557858174, 557858193, 557858192, 557858191, 557858190};

    public CarVendorSettingMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorSettingManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorSettingManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorSettingManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorSettingManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBdMassageLevelLeftRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858196, 0);
            Log.debug(CarVendorSettingManager.TAG, "getBdMassageLevelLeftRearR value = " + value + " property: 0x" + tenToHex(557858196));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBdMassageLevelRightRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858197, 0);
            Log.debug(CarVendorSettingManager.TAG, "getBdMassageLevelRightRearR value = " + value + " property: 0x" + tenToHex(557858197));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBdMassageModeLeftRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858194, 0);
            Log.debug(CarVendorSettingManager.TAG, "getBdMassageModeLeftRearR value = " + value + " property: 0x" + tenToHex(557858194));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBdMassageModeRightRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858195, 0);
            Log.debug(CarVendorSettingManager.TAG, "getBdMassageModeRightRearR value = " + value + " property: 0x" + tenToHex(557858195));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDragConnectionTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858168, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDragConnectionTypeR value = " + value + " property: 0x" + tenToHex(557858168));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDragTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858167, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDragTypeR value = " + value + " property: 0x" + tenToHex(557858167));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDriverMasToggleModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858204, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDriverMasToggleModeConfigR value = " + value + " property: 0x" + tenToHex(557858204));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrHotStone2MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858219, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrHotStone2MassageConfigR value = " + value + " property: 0x" + tenToHex(557858219));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrHotStone3MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858220, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrHotStone3MassageConfigR value = " + value + " property: 0x" + tenToHex(557858220));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrHotStoneMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858218, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrHotStoneMassageConfigR value = " + value + " property: 0x" + tenToHex(557858218));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrMas2ToggleModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858207, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrMas2ToggleModeConfigR value = " + value + " property: 0x" + tenToHex(557858207));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrMasToggleModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858206, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrMasToggleModeConfigR value = " + value + " property: 0x" + tenToHex(557858206));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrNormal2MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858217, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrNormal2MassageConfigR value = " + value + " property: 0x" + tenToHex(557858217));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrNormalMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858216, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrNormalMassageConfigR value = " + value + " property: 0x" + tenToHex(557858216));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMain2HotStoneMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858212, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMain2HotStoneMassageConfigR value = " + value + " property: 0x" + tenToHex(557858212));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMainNormalMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858210, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMainNormalMassageConfigR value = " + value + " property: 0x" + tenToHex(557858210));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPas2HotStoneMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858215, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPas2HotStoneMassageConfigR value = " + value + " property: 0x" + tenToHex(557858215));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPasHotStoneMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858214, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPasHotStoneMassageConfigR value = " + value + " property: 0x" + tenToHex(557858214));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPasNormalMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858213, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPasNormalMassageConfigR value = " + value + " property: 0x" + tenToHex(557858213));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerMasToggleModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858205, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPassengerMasToggleModeConfigR value = " + value + " property: 0x" + tenToHex(557858205));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrEcuMassageLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858203, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrEcuMassageLevelR value = " + value + " property: 0x" + tenToHex(557858203));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrEcuMassageModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858202, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrEcuMassageModeR value = " + value + " property: 0x" + tenToHex(557858202));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrHotStone2MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858225, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrHotStone2MassageConfigR value = " + value + " property: 0x" + tenToHex(557858225));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrHotStone3MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858226, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrHotStone3MassageConfigR value = " + value + " property: 0x" + tenToHex(557858226));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrHotStone4MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858227, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrHotStone4MassageConfigR value = " + value + " property: 0x" + tenToHex(557858227));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrHotStoneMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858224, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrHotStoneMassageConfigR value = " + value + " property: 0x" + tenToHex(557858224));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrMas2ToggleModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858209, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrMas2ToggleModeConfigR value = " + value + " property: 0x" + tenToHex(557858209));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrMasToggleModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858208, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrMasToggleModeConfigR value = " + value + " property: 0x" + tenToHex(557858208));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrNormal2MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858222, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrNormal2MassageConfigR value = " + value + " property: 0x" + tenToHex(557858222));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrNormal3MassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858223, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrNormal3MassageConfigR value = " + value + " property: 0x" + tenToHex(557858223));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrNormalMassageConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858221, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrNormalMassageConfigR value = " + value + " property: 0x" + tenToHex(557858221));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatSecullDistributionFormConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858228, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSeatSecullDistributionFormConfigR value = " + value + " property: 0x" + tenToHex(557858228));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatSeculrDistributionFormConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858229, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSeatSeculrDistributionFormConfigR value = " + value + " property: 0x" + tenToHex(557858229));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatSecurlDistributionFormConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858230, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSeatSecurlDistributionFormConfigR value = " + value + " property: 0x" + tenToHex(557858230));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatSecurrDistributionFormConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858231, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSeatSecurrDistributionFormConfigR value = " + value + " property: 0x" + tenToHex(557858231));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecondRightSeatBack2InitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858166, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecondRightSeatBack2InitR value = " + value + " property: 0x" + tenToHex(557858166));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecondRightSeatHorizontal2InitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858165, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecondRightSeatHorizontal2InitR value = " + value + " property: 0x" + tenToHex(557858165));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecuMassageLevelLeftRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858200, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecuMassageLevelLeftRearR value = " + value + " property: 0x" + tenToHex(557858200));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecuMassageLevelRightRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858201, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecuMassageLevelRightRearR value = " + value + " property: 0x" + tenToHex(557858201));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecuMassageModeLeftRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858198, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecuMassageModeLeftRearR value = " + value + " property: 0x" + tenToHex(557858198));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecuMassageModeRightRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858199, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecuMassageModeRightRearR value = " + value + " property: 0x" + tenToHex(557858199));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTowingProhibitedStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858177, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTowingProhibitedStateR value = " + value + " property: 0x" + tenToHex(557858177));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerDragTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858173, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerDragTypeR value = " + value + " property: 0x" + tenToHex(557858173));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setTrailerDragTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setTrailerDragTypeS value = " + value + " property: 0x" + tenToHex(557858172));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858172, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerLargeItemConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858171, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerLargeItemConfigurationR value = " + value + " property: 0x" + tenToHex(557858171));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerLargeLimitWeightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858176, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerLargeLimitWeightR value = " + value + " property: 0x" + tenToHex(557858176));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerMiddleItemConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858170, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerMiddleItemConfigurationR value = " + value + " property: 0x" + tenToHex(557858170));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerMiddleLimitWeightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858175, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerMiddleLimitWeightR value = " + value + " property: 0x" + tenToHex(557858175));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerSmallItemConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858169, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerSmallItemConfigurationR value = " + value + " property: 0x" + tenToHex(557858169));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerSmallLimitWeightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858174, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerSmallLimitWeightR value = " + value + " property: 0x" + tenToHex(557858174));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getUcuMassageLevelDeputyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858193, 0);
            Log.debug(CarVendorSettingManager.TAG, "getUcuMassageLevelDeputyR value = " + value + " property: 0x" + tenToHex(557858193));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getUcuMassageLevelMainR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858192, 0);
            Log.debug(CarVendorSettingManager.TAG, "getUcuMassageLevelMainR value = " + value + " property: 0x" + tenToHex(557858192));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getUcuMassageModeDeputyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858191, 0);
            Log.debug(CarVendorSettingManager.TAG, "getUcuMassageModeDeputyR value = " + value + " property: 0x" + tenToHex(557858191));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getUcuMassageModeMainR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858190, 0);
            Log.debug(CarVendorSettingManager.TAG, "getUcuMassageModeMainR value = " + value + " property: 0x" + tenToHex(557858190));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMainSeatBackInitedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858162, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMainSeatBackInitedR value = " + value + " property: 0x" + tenToHex(557858162));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMainSeatHightInitedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858163, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMainSeatHightInitedR value = " + value + " property: 0x" + tenToHex(557858163));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMainSeatHorizontalInitedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858161, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMainSeatHorizontalInitedR value = " + value + " property: 0x" + tenToHex(557858161));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTapDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858160, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTapDoorStateR value = " + value + " property: 0x" + tenToHex(557858160));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getThirdRowLeftBackrestDisplayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859134, 0);
            Log.debug(CarVendorSettingManager.TAG, "getThirdRowLeftBackrestDisplayR value = " + value + " property: 0x" + tenToHex(557859134));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getThirdRowRightBackrestDisplayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859144, 0);
            Log.debug(CarVendorSettingManager.TAG, "getThirdRowRightBackrestDisplayR value = " + value + " property: 0x" + tenToHex(557859144));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWashElectricPedalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858159, 0);
            Log.debug(CarVendorSettingManager.TAG, "getWashElectricPedalR value = " + value + " property: 0x" + tenToHex(557858159));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMeterExportAreaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858158, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMeterExportAreaR value = " + value + " property: 0x" + tenToHex(557858158));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatDistributionFormConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858157, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSeatDistributionFormConfigR value = " + value + " property: 0x" + tenToHex(557858157));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCenterRearSafebeltStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858142, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCenterRearSafebeltStatusR value = " + value + " property: 0x" + tenToHex(557858142));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLeftRearSafebeltStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858141, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLeftRearSafebeltStatusR value = " + value + " property: 0x" + tenToHex(557858141));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLeftThirdSafebeltStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858144, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLeftThirdSafebeltStatusR value = " + value + " property: 0x" + tenToHex(557858144));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMainSeatBackInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858147, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMainSeatBackInitR value = " + value + " property: 0x" + tenToHex(557858147));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMainSeatHorizontalInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858146, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMainSeatHorizontalInitR value = " + value + " property: 0x" + tenToHex(557858146));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getOneKeyEnterPitConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858154, 0);
            Log.debug(CarVendorSettingManager.TAG, "getOneKeyEnterPitConfigR value = " + value + " property: 0x" + tenToHex(557858154));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassangerSeatBackInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858149, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPassangerSeatBackInitR value = " + value + " property: 0x" + tenToHex(557858149));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassangerSeatHorizontalInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858148, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPassangerSeatHorizontalInitR value = " + value + " property: 0x" + tenToHex(557858148));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRightRearSafebeltStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858143, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRightRearSafebeltStatusR value = " + value + " property: 0x" + tenToHex(557858143));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRightThirdSafebeltStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858145, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRightThirdSafebeltStatusR value = " + value + " property: 0x" + tenToHex(557858145));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecondLeftSeatBackInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858151, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecondLeftSeatBackInitR value = " + value + " property: 0x" + tenToHex(557858151));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecondLeftSeatHorizontalInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858150, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecondLeftSeatHorizontalInitR value = " + value + " property: 0x" + tenToHex(557858150));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecondRightSeatBackInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858153, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecondRightSeatBackInitR value = " + value + " property: 0x" + tenToHex(557858153));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSecondRightSeatHorizontalInitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858152, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSecondRightSeatHorizontalInitR value = " + value + " property: 0x" + tenToHex(557858152));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setThirdRowSeatLeftBackrestS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setThirdRowSeatLeftBackrestS value = " + value + " property: 0x" + tenToHex(557858155));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858155, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setThirdRowSeatRightBackrestS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setThirdRowSeatRightBackrestS value = " + value + " property: 0x" + tenToHex(557858156));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858156, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getThirdRowSeatWorkModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858140, 0);
            Log.debug(CarVendorSettingManager.TAG, "getThirdRowSeatWorkModeR value = " + value + " property: 0x" + tenToHex(557858140));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCloudServiceStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCloudServiceStatusS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_CLOUD_SERVICE_STATUS_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_CLOUD_SERVICE_STATUS_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setNetworkSecurityStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setNetworkSecurityStatusS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_NETWORK_SECURITY_STATUS_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_NETWORK_SECURITY_STATUS_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setOptimizationStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setOptimizationStatusS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_OPTIMIZATION_STATUS_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_OPTIMIZATION_STATUS_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDriverSeatHeating437StateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858126, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDriverSeatHeating437StateR value = " + value + " property: 0x" + tenToHex(557858126));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDriverSeatVentilating437StateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858125, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDriverSeatVentilating437StateR value = " + value + " property: 0x" + tenToHex(557858125));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerSeatHeating3caStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858128, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPassengerSeatHeating3caStateR value = " + value + " property: 0x" + tenToHex(557858128));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerSeatVentilating3caStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858127, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPassengerSeatVentilating3caStateR value = " + value + " property: 0x" + tenToHex(557858127));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIviProfileSettingsDmS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIviProfileSettingsDmS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_I_V_I_PROFILE_SETTINGS_D_M_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_I_V_I_PROFILE_SETTINGS_D_M_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCarSeatLayoutR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858116, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCarSeatLayoutR value = " + value + " property: 0x" + tenToHex(557858116));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDriveModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858112, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDriveModeR value = " + value + " property: 0x" + tenToHex(557858112));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrig1PositionMarkBitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859210, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrig1PositionMarkBitR value = " + value + " property: 0x" + tenToHex(557859210));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrig2PositionMarkBitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859211, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrig2PositionMarkBitR value = " + value + " property: 0x" + tenToHex(557859211));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigNumFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859205, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigNumFlagR value = " + value + " property: 0x" + tenToHex(557859205));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigWorkFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858122, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigWorkFlagR value = " + value + " property: 0x" + tenToHex(557858122));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigWorkModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858123, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigWorkModeR value = " + value + " property: 0x" + tenToHex(557858123));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRefrigWorkModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRefrigWorkModeS value = " + value + " property: 0x" + tenToHex(557858124));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858124, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorMobileTerminalPushReR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858119, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigeratorMobileTerminalPushReR value = " + value + " property: 0x" + tenToHex(557858119));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSandAbsModeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858114, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSandAbsModeStateR value = " + value + " property: 0x" + tenToHex(557858114));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSandAbsModeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSandAbsModeStateS value = " + value + " property: 0x" + tenToHex(557858113));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858113, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSandAbsTipMessageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858115, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSandAbsTipMessageR value = " + value + " property: 0x" + tenToHex(557858115));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSaveOwnerStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858117, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSaveOwnerStateR value = " + value + " property: 0x" + tenToHex(557858117));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getGoalRoadModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557847553, 0);
            Log.debug(CarVendorSettingManager.TAG, "getGoalRoadModeR value = " + value + " property: 0x" + tenToHex(557847553));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setGoalRoadModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setGoalRoadModeS value = " + value + " property: 0x" + tenToHex(557847591));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557847591, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSteeringWheelHeatedPlanR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_STEERING_WHEEL_HEATED_PLAN_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSteeringWheelHeatedPlanR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_STEERING_WHEEL_HEATED_PLAN_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWadingPatternSocTipsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858107, 0);
            Log.debug(CarVendorSettingManager.TAG, "getWadingPatternSocTipsR value = " + value + " property: 0x" + tenToHex(557858107));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWadingPatternSpeedTipsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858108, 0);
            Log.debug(CarVendorSettingManager.TAG, "getWadingPatternSpeedTipsR value = " + value + " property: 0x" + tenToHex(557858108));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWadingPatternStateTipsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858110, 0);
            Log.debug(CarVendorSettingManager.TAG, "getWadingPatternStateTipsR value = " + value + " property: 0x" + tenToHex(557858110));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeaModeChargingSocTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858111, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSeaModeChargingSocTypeR value = " + value + " property: 0x" + tenToHex(557858111));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWadingPatternTestTipsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858109, 0);
            Log.debug(CarVendorSettingManager.TAG, "getWadingPatternTestTipsR value = " + value + " property: 0x" + tenToHex(557858109));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCuraSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858096, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCuraSwitchStateR value = " + value + " property: 0x" + tenToHex(557858096));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCuraSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCuraSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_CURA_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_CURA_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPhysicalkeyConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_PHYSICALKEY_CONFIG_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPhysicalkeyConfigR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_PHYSICALKEY_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPhysicalkeySecuConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_PHYSICALKEY_SECU_CONFIG_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPhysicalkeySecuConfigR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_PHYSICALKEY_SECU_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPhysicalkeySecupConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_PHYSICALKEY_SECUP_CONFIG_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPhysicalkeySecupConfigR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_PHYSICALKEY_SECUP_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPhysicalkeySecurlConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_PHYSICALKEY_SECURL_CONFIG_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPhysicalkeySecurlConfigR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_PHYSICALKEY_SECURL_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPhysicalkeySecurrConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_PHYSICALKEY_SECURR_CONFIG_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPhysicalkeySecurrConfigR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_PHYSICALKEY_SECURR_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearRightSeatGreetStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858102, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearRightSeatGreetStateR value = " + value + " property: 0x" + tenToHex(557858102));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearRightSeatGreetStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearRightSeatGreetStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_RIGHT_SEAT_GREET_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_RIGHT_SEAT_GREET_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCarDoorTipsSoundTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCarDoorTipsSoundTypeS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_CAR_DOOR_TIPS_SOUND_TYPE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_CAR_DOOR_TIPS_SOUND_TYPE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPedalSuppressionSystemStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858093, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPedalSuppressionSystemStateR value = " + value + " property: 0x" + tenToHex(557858093));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setPedalSuppressionSystemStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setPedalSuppressionSystemStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_PEDAL_SUPPRESSION_SYSTEM_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_PEDAL_SUPPRESSION_SYSTEM_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSwcontrolStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858091, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSwcontrolStateR value = " + value + " property: 0x" + tenToHex(557858091));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonScreenRotationStatus() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557875283, 0);
            Log.debug(CarVendorSettingManager.TAG, "getonScreenRotationStatus value = " + value + " property: 0x" + tenToHex(557875283));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCpdImsSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858089, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCpdImsSwitchStateR value = " + value + " property: 0x" + tenToHex(557858089));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCpdImsSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCpdImsSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_CPD_IMS_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_CPD_IMS_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRncStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858090, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRncStateR value = " + value + " property: 0x" + tenToHex(557858090));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRncStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRncStateS value = " + value + " property: 0x" + tenToHex(557859185));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859185, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getsocTargetLowLimitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858084, 0);
            Log.debug(CarVendorSettingManager.TAG, "getsocTargetLowLimitR value = " + value + " property: 0x" + tenToHex(557858084));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getsocTargetRangeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858083, 0);
            Log.debug(CarVendorSettingManager.TAG, "getsocTargetRangeR value = " + value + " property: 0x" + tenToHex(557858083));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAlarmPromptStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858025, 0);
            Log.debug(CarVendorSettingManager.TAG, "getAlarmPromptStateR value = " + value + " property: 0x" + tenToHex(557858025));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCanopyAutomaticDimmingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858010, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCanopyAutomaticDimmingR value = " + value + " property: 0x" + tenToHex(557858010));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCanopyAutomaticDimmingS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCanopyAutomaticDimmingS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_CANOPY_AUTOMATIC_DIMMING_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_CANOPY_AUTOMATIC_DIMMING_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getControlCallbackStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857990, 0);
            Log.debug(CarVendorSettingManager.TAG, "getControlCallbackStateR value = " + value + " property: 0x" + tenToHex(557857990));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCurrentModeOfTailStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858026, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCurrentModeOfTailStateR value = " + value + " property: 0x" + tenToHex(557858026));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCurrentStateOfTheTailStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858021, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCurrentStateOfTheTailStateR value = " + value + " property: 0x" + tenToHex(557858021));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCurrentStatusOfDimmingSunroofR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858017, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCurrentStatusOfDimmingSunroofR value = " + value + " property: 0x" + tenToHex(557858017));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCurrentTransmittanceOfSkylightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858016, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCurrentTransmittanceOfSkylightR value = " + value + " property: 0x" + tenToHex(557858016));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDelayDownPowerTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857982, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDelayDownPowerTimeR value = " + value + " property: 0x" + tenToHex(557857982));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDelayPowerDownStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDelayPowerDownStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_DELAY_POWER_DOWN_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_DELAY_POWER_DOWN_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDelayPowerDownStateTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDelayPowerDownStateTimeS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_DELAY_POWER_DOWN_STATE_TIME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_DELAY_POWER_DOWN_STATE_TIME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDelayPowerDownTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857972, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDelayPowerDownTimeR value = " + value + " property: 0x" + tenToHex(557857972));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDelayPowerDownTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDelayPowerDownTimeS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_DELAY_POWER_DOWN_TIME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_DELAY_POWER_DOWN_TIME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDelayPowerOffStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857968, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDelayPowerOffStateR value = " + value + " property: 0x" + tenToHex(557857968));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDelayPowerOffStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857992, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDelayPowerOffStatusR value = " + value + " property: 0x" + tenToHex(557857992));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDelayPowerOffTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857970, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDelayPowerOffTimeR value = " + value + " property: 0x" + tenToHex(557857970));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDelayPowerOffTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDelayPowerOffTimeS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_DELAY_POWER_OFF_TIME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_DELAY_POWER_OFF_TIME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDimmingCanopyConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859165, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDimmingCanopyConfigurationR value = " + value + " property: 0x" + tenToHex(557859165));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDimmingCanopyLowCodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858018, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDimmingCanopyLowCodeR value = " + value + " property: 0x" + tenToHex(557858018));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDlgMessageCodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857984, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDlgMessageCodeR value = " + value + " property: 0x" + tenToHex(557857984));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setElectricTailAutoModeVoiceSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setElectricTailAutoModeVoiceSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_ELECTRIC_TAIL_AUTO_MODE_VOICE_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_ELECTRIC_TAIL_AUTO_MODE_VOICE_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setElectricTailAutomaticModeSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setElectricTailAutomaticModeSwitchStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_ELECTRIC_TAIL_AUTOMATIC_MODE_SWITCH_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_ELECTRIC_TAIL_AUTOMATIC_MODE_SWITCH_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getElectricTailConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_ELECTRIC_TAIL_CONFIGURATION_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getElectricTailConfigurationR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_ELECTRIC_TAIL_CONFIGURATION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getElectricTailMaintenanceFuntatusStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858027, 0);
            Log.debug(CarVendorSettingManager.TAG, "getElectricTailMaintenanceFuntatusStateR value = " + value + " property: 0x" + tenToHex(557858027));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdComSpeedUpStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857951, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFdComSpeedUpStateR value = " + value + " property: 0x" + tenToHex(557857951));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeChildLockR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858063, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeChildLockR value = " + value + " property: 0x" + tenToHex(557858063));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setFridgeChildLockS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setFridgeChildLockS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_FRIDGE_CHILD_LOCK_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_FRIDGE_CHILD_LOCK_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857979, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeDoorStateR value = " + value + " property: 0x" + tenToHex(557857979));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setFridgeDoorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setFridgeDoorStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_FRIDGE_DOOR_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_FRIDGE_DOOR_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeLocationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857985, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeLocationR value = " + value + " property: 0x" + tenToHex(557857985));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeWorkColdTemperatureR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857965, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeWorkColdTemperatureR value = " + value + " property: 0x" + tenToHex(557857965));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeWorkHeatTemperatureR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857966, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeWorkHeatTemperatureR value = " + value + " property: 0x" + tenToHex(557857966));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeWorkSaveTemperatureR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857967, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeWorkSaveTemperatureR value = " + value + " property: 0x" + tenToHex(557857967));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setFrontPassengerSeatSeatPotSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setFrontPassengerSeatSeatPotSwitchStateS value = " + value + " property: 0x" + tenToHex(557857637));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857637, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIceBoxTriggerSourceS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIceBoxTriggerSourceS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_ICE_BOX_TRIGGER_SOURCE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_ICE_BOX_TRIGGER_SOURCE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLeftElectricPedalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858058, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLeftElectricPedalR value = " + value + " property: 0x" + tenToHex(557858058));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setOpeningAngleInformationSetOnTheRS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setOpeningAngleInformationSetOnTheRS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_OPENING_ANGLE_INFORMATION_SET_ON_THE_R_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_OPENING_ANGLE_INFORMATION_SET_ON_THE_R_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPhoneRemindCodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857997, 0);
            Log.debug(CarVendorSettingManager.TAG, "getPhoneRemindCodeR value = " + value + " property: 0x" + tenToHex(557857997));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857994, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearDoorStateR value = " + value + " property: 0x" + tenToHex(557857994));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearFridgeDelayStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearFridgeDelayStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_FRIDGE_DELAY_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_FRIDGE_DELAY_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearFridgeDelayTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearFridgeDelayTimeS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_FRIDGE_DELAY_TIME_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_FRIDGE_DELAY_TIME_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearFridgeDoorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearFridgeDoorStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_FRIDGE_DOOR_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_FRIDGE_DOOR_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearFridgeSterilizerStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearFridgeSterilizerStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_FRIDGE_STERILIZER_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_FRIDGE_STERILIZER_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearFridgeTemperatureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearFridgeTemperatureS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_FRIDGE_TEMPERATURE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_FRIDGE_TEMPERATURE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearFridgeWorkStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearFridgeWorkStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_FRIDGE_WORK_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_FRIDGE_WORK_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearHeatTempR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857995, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearHeatTempR value = " + value + " property: 0x" + tenToHex(557857995));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearLeftMassageSwConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_REAR_LEFT_MASSAGE_SW_CONFIGURATION_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearLeftMassageSwConfigurationR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_LEFT_MASSAGE_SW_CONFIGURATION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearRightMassageSwConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSettingManager.ID_REAR_RIGHT_MASSAGE_SW_CONFIGURATION_R, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearRightMassageSwConfigurationR value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_RIGHT_MASSAGE_SW_CONFIGURATION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearSaveTempR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857996, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearSaveTempR value = " + value + " property: 0x" + tenToHex(557857996));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearWorkTemperatureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearWorkTemperatureS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_WORK_TEMPERATURE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_WORK_TEMPERATURE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearviewMirrorIsAutoLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857959, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearviewMirrorIsAutoLightStateR value = " + value + " property: 0x" + tenToHex(557857959));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearviewMirrorIsAutoLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearviewMirrorIsAutoLightStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_AUTO_LIGHT_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_AUTO_LIGHT_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearviewMirrorIsScreenStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearviewMirrorIsScreenStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_SCREEN_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_SCREEN_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearviewMirrorIsStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857958, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearviewMirrorIsStateR value = " + value + " property: 0x" + tenToHex(557857958));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearviewMirrorIsTurnedStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857957, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearviewMirrorIsTurnedStateR value = " + value + " property: 0x" + tenToHex(557857957));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearviewMirrorIsTurnedStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearviewMirrorIsTurnedStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_TURNED_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_TURNED_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorTemperatureR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857981, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigeratorTemperatureR value = " + value + " property: 0x" + tenToHex(557857981));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorWorkModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857962, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigeratorWorkModeR value = " + value + " property: 0x" + tenToHex(557857962));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRefrigeratorWorkModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRefrigeratorWorkModeS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REFRIGERATOR_WORK_MODE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REFRIGERATOR_WORK_MODE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorWorkStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857980, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigeratorWorkStateR value = " + value + " property: 0x" + tenToHex(557857980));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorWorkTemperatureR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857964, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRefrigeratorWorkTemperatureR value = " + value + " property: 0x" + tenToHex(557857964));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRefrigeratorWorkTemperatureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRefrigeratorWorkTemperatureS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REFRIGERATOR_WORK_TEMPERATURE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REFRIGERATOR_WORK_TEMPERATURE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRemberPowerOffStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857974, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRemberPowerOffStateR value = " + value + " property: 0x" + tenToHex(557857974));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRemberPowerOffStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRemberPowerOffStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_REMBER_POWER_OFF_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REMBER_POWER_OFF_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRightElectricPedalR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858060, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRightElectricPedalR value = " + value + " property: 0x" + tenToHex(557858060));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getScreenCallbackMessageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857993, 0);
            Log.debug(CarVendorSettingManager.TAG, "getScreenCallbackMessageR value = " + value + " property: 0x" + tenToHex(557857993));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getServiceControlCallbackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857998, 0);
            Log.debug(CarVendorSettingManager.TAG, "getServiceControlCallbackR value = " + value + " property: 0x" + tenToHex(557857998));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSkyCurtainPowerOffAndDimR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858011, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSkyCurtainPowerOffAndDimR value = " + value + " property: 0x" + tenToHex(557858011));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSkyCurtainPowerOffAndDimS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSkyCurtainPowerOffAndDimS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_SKY_CURTAIN_POWER_OFF_AND_DIM_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_SKY_CURTAIN_POWER_OFF_AND_DIM_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSterilizationStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857976, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSterilizationStateR value = " + value + " property: 0x" + tenToHex(557857976));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSterilizationStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSterilizationStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_STERILIZATION_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_STERILIZATION_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSterilizationWorkStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857983, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSterilizationWorkStateR value = " + value + " property: 0x" + tenToHex(557857983));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTailInitializationStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858020, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTailInitializationStateR value = " + value + " property: 0x" + tenToHex(557858020));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTailMotorThermalProtectionStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858023, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTailMotorThermalProtectionStateR value = " + value + " property: 0x" + tenToHex(557858023));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTailPositionFeedbackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858024, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTailPositionFeedbackR value = " + value + " property: 0x" + tenToHex(557858024));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTailPositionPercentageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858022, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTailPositionPercentageR value = " + value + " property: 0x" + tenToHex(557858022));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getVoiceTipMessageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857977, 0);
            Log.debug(CarVendorSettingManager.TAG, "getVoiceTipMessageR value = " + value + " property: 0x" + tenToHex(557857977));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getVoiceTipsMessageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857977, 0);
            Log.debug(CarVendorSettingManager.TAG, "getVoiceTipsMessageR value = " + value + " property: 0x" + tenToHex(557857977));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setWorkCommondS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setWorkCommondS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_WORK_COMMOND_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_WORK_COMMOND_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWorkStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857991, 0);
            Log.debug(CarVendorSettingManager.TAG, "getWorkStateR value = " + value + " property: 0x" + tenToHex(557857991));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setWorkStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setWorkStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_WORK_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_WORK_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setWorkTemperatureS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setWorkTemperatureS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_WORK_TEMPERATURE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_WORK_TEMPERATURE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void seteOwnerStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "seteOwnerStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_E_OWNER_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_E_OWNER_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setflSeatBasinSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setflSeatBasinSwitchStateS value = " + value + " property: 0x" + tenToHex(557857636));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857636, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setresetRearviewMirrorIsSetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setresetRearviewMirrorIsSetS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_RESET_REARVIEW_MIRROR_IS_SETS));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_RESET_REARVIEW_MIRROR_IS_SETS, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void turnRearviewMirrorIS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "turnRearviewMirrorIS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_REARVIEW_MIRROR_IS));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_REARVIEW_MIRROR_IS, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatBackRearLeftLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatBackRearLeftLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_LEFT_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_LEFT_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatBackRearLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatBackRearLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatBackRearLeftRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatBackRearLeftRightRcS value = " + value + " property: 0x" + tenToHex(557858048));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858048, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatBackRearRightLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatBackRearRightLeftRcS value = " + value + " property: 0x" + tenToHex(557858050));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858050, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatBackRearRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatBackRearRightRcS value = " + value + " property: 0x" + tenToHex(557858049));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858049, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatBackRearRightRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatBackRearRightRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_RIGHT_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_RIGHT_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintRearLeftLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintRearLeftLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintRearLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintRearLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintRearLeftRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintRearLeftRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintRearRightLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintRearRightLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeThreePositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859212, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeThreePositionR value = " + value + " property: 0x" + tenToHex(557859212));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeTwoPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859211, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeTwoPositionR value = " + value + " property: 0x" + tenToHex(557859211));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeTemperatureRegulationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859196, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeTemperatureRegulationR value = " + value + " property: 0x" + tenToHex(557859196));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeMinWagePowerGenerationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859198, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeMinWagePowerGenerationR value = " + value + " property: 0x" + tenToHex(557859198));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeExpansionAndContractionFunctionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859197, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeExpansionAndContractionFunctionR value = " + value + " property: 0x" + tenToHex(557859197));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeWorkingModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859195, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeWorkingModeR value = " + value + " property: 0x" + tenToHex(557859195));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCarFridgeTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859199, 0);
            Log.debug(CarVendorSettingManager.TAG, "getCarFridgeTypeR value = " + value + " property: 0x" + tenToHex(557859199));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getExclusiveSaveFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859200, 0);
            Log.debug(CarVendorSettingManager.TAG, "getExclusiveSaveFlagR value = " + value + " property: 0x" + tenToHex(557859200));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeChildLockFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859201, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeChildLockFlagR value = " + value + " property: 0x" + tenToHex(557859201));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeOnePositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859210, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeOnePositionR value = " + value + " property: 0x" + tenToHex(557859210));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearRightFridgeHeatingSchemeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859209, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearRightFridgeHeatingSchemeR value = " + value + " property: 0x" + tenToHex(557859209));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearFridgeWorkTypeFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859208, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearFridgeWorkTypeFlagR value = " + value + " property: 0x" + tenToHex(557859208));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearFridgeKeyTypeFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859207, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearFridgeKeyTypeFlagR value = " + value + " property: 0x" + tenToHex(557859207));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearFridgeDisinfectFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859206, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearFridgeDisinfectFlagR value = " + value + " property: 0x" + tenToHex(557859206));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeNumR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859205, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeNumR value = " + value + " property: 0x" + tenToHex(557859205));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFrontFridgeDisinfectFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859204, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFrontFridgeDisinfectFlagR value = " + value + " property: 0x" + tenToHex(557859204));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeDelayedPowerOutageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859203, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeDelayedPowerOutageR value = " + value + " property: 0x" + tenToHex(557859203));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFridgeScreenFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859202, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFridgeScreenFlagR value = " + value + " property: 0x" + tenToHex(557859202));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintRearRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintRearRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintRearRightRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintRearRightRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionRearLeftLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionRearLeftLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionRearLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionRearLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionRearLeftRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionRearLeftRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionRearRightLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionRearRightLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionRearRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionRearRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionRearRightRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionRearRightRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationRearLeftLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationRearLeftLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationRearLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationRearLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationRearLeftRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationRearLeftRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationRearRightLeftRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationRearRightLeftRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_LEFT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_LEFT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationRearRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationRearRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationRearRightRightRcS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationRearRightRightRcS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_RIGHT_R_C_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_RIGHT_R_C_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setvoiceOpenMirrorlsTurnedStatS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setvoiceOpenMirrorlsTurnedStatS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_VOICE_OPEN_MIRRORLS_TURNED_STATE));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_VOICE_OPEN_MIRRORLS_TURNED_STATE, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDragAndDropModeTipsStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858462, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDragAndDropModeTipsStateR value = " + value + " property: 0x" + tenToHex(557858462), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDragModeForwardAndBackwardStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858461, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDragModeForwardAndBackwardStateR value = " + value + " property: 0x" + tenToHex(557858461), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdDynamicSuspensionModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858383, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFdDynamicSuspensionModeR value = " + value + " property: 0x" + tenToHex(557858383), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBsisCollisionStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856889, 0);
            Log.debug(CarVendorSettingManager.TAG, "getBsisCollisionStateR value = " + value + " property: 0x" + tenToHex(557856889), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBsisCollisionStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBsisCollisionStateS value = " + value + " property: 0x" + tenToHex(557856890), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856890, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBsisFuncStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856891, 0);
            Log.debug(CarVendorSettingManager.TAG, "getBsisFuncStatusR value = " + value + " property: 0x" + tenToHex(557856891), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIviProfileSettingsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857947, 0);
            Log.debug(CarVendorSettingManager.TAG, "getIviProfileSettingsR value = " + value + " property: 0x" + tenToHex(557857947), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLeftRseChildModeSwitchStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858445, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLeftRseChildModeSwitchStatusR value = " + value + " property: 0x" + tenToHex(557858445), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLeftRseStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLeftRseStateS value = " + value + " property: 0x" + tenToHex(557858439), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858439, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLeftRseSwitchCommandS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLeftRseSwitchCommandS value = " + value + " property: 0x" + tenToHex(557858437), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858437, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLrseChildAreaStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLrseChildAreaStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_LRSE_CHILD_AREA_STATE_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_LRSE_CHILD_AREA_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLrseChildModeCode1stByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLrseChildModeCode1stByteS value = " + value + " property: 0x" + tenToHex(557858451), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858451, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLrseChildModeCode2ndByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLrseChildModeCode2ndByteS value = " + value + " property: 0x" + tenToHex(557858452), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858452, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLrseChildModeCode3rdByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLrseChildModeCode3rdByteS value = " + value + " property: 0x" + tenToHex(557858453), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858453, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLrseChildModeCode4thByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLrseChildModeCode4thByteS value = " + value + " property: 0x" + tenToHex(557858454), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858454, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrseChildModeCodeWriteFbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858459, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrseChildModeCodeWriteFbR value = " + value + " property: 0x" + tenToHex(557858459), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLrseChildModeOpenTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLrseChildModeOpenTimeS value = " + value + " property: 0x" + tenToHex(557858447), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858447, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrseChildModeTimeWriteFbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858449, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrseChildModeTimeWriteFbR value = " + value + " property: 0x" + tenToHex(557858449), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLrseMachineSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858441, 0);
            Log.debug(CarVendorSettingManager.TAG, "getLrseMachineSwitchStateR value = " + value + " property: 0x" + tenToHex(557858441), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMoisAssembleStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856892, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMoisAssembleStateR value = " + value + " property: 0x" + tenToHex(557856892), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMoisAssembleStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMoisAssembleStateS value = " + value + " property: 0x" + tenToHex(557856893), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856893, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setScreenAngleValueS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setScreenAngleValueS value = " + value + " property: 0x" + tenToHex(557858338), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858338, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMoisFuncStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856894, 0);
            Log.debug(CarVendorSettingManager.TAG, "getMoisFuncStatusR value = " + value + " property: 0x" + tenToHex(557856894), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRightRseChildModeSwitchStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858446, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRightRseChildModeSwitchStatusR value = " + value + " property: 0x" + tenToHex(557858446), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRightRseStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRightRseStateS value = " + value + " property: 0x" + tenToHex(557858440), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858440, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRightRseSwitchCommandS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRightRseSwitchCommandS value = " + value + " property: 0x" + tenToHex(557858438), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858438, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRrseChildAreaStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRrseChildAreaStateS value = " + value + " property: 0x" + tenToHex(557858444), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858444, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRrseChildModeCode1stByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRrseChildModeCode1stByteS value = " + value + " property: 0x" + tenToHex(557858455), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858455, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRrseChildModeCode2ndByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRrseChildModeCode2ndByteS value = " + value + " property: 0x" + tenToHex(557858456), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858456, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRrseChildModeCode3rdByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRrseChildModeCode3rdByteS value = " + value + " property: 0x" + tenToHex(557858457), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858457, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRrseChildModeCode4thByteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRrseChildModeCode4thByteS value = " + value + " property: 0x" + tenToHex(557858458), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858458, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrseChildModeCodeWriteFbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858460, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrseChildModeCodeWriteFbR value = " + value + " property: 0x" + tenToHex(557858460), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRrseChildModeOpenTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRrseChildModeOpenTimeS value = " + value + " property: 0x" + tenToHex(557858448), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858448, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrseChildModeTimeWriteFbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858450, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrseChildModeTimeWriteFbR value = " + value + " property: 0x" + tenToHex(557858450), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRrseMachineSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858442, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRrseMachineSwitchStateR value = " + value + " property: 0x" + tenToHex(557858442), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSwsMediumRelatedKeysR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557867008, 0);
            Log.debug(CarVendorSettingManager.TAG, "getSwsMediumRelatedKeysR value = " + value + " property: 0x" + tenToHex(557867008), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDoorHeightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856076, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorHeightSupport property: 0x");
            sb.append(tenToHex(557856076));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getElectroniChildLockConfigSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856207, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElectroniChildLockConfigSecond property: 0x");
            sb.append(tenToHex(557856207));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientAreaSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856186, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientAreaSupport property: 0x");
            sb.append(tenToHex(557856186));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientLightColorSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856184, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientLightColorSupport property: 0x");
            sb.append(tenToHex(557856184));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientLightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856185, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientLightSupport property: 0x");
            sb.append(tenToHex(557856185));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAutoExternalRearMirrorFollowUpSwitchSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856053, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoExternalRearMirrorFollowUpSwitchSupport property: 0x");
            sb.append(tenToHex(557856053));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSupportSeatMassageMain() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856019, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageMain property: 0x");
            sb.append(tenToHex(557856019));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSupportSeatMassageDeputy() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856020, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageDeputy property: 0x");
            sb.append(tenToHex(557856020));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSupportSeatMassageLeftRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856021, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageLeftRear property: 0x");
            sb.append(tenToHex(557856021));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSupportSeatMassageRightRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856022, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageRightRear property: 0x");
            sb.append(tenToHex(557856022));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAmbientMusicModeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAmbientMusicModeStateS property: 0x" + tenToHex(557860612) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860612, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientMusicModeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860611, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMusicModeStateR property: 0x");
            sb.append(tenToHex(557860611));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientMulticolorModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860617, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMulticolorModeR property: 0x");
            sb.append(tenToHex(557860617));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAmbientMulticolorModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAmbientMulticolorModeS property: 0x" + tenToHex(557860618) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860618, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAmbientMulticolorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAmbientMulticolorStateS property: 0x" + tenToHex(557860616) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860616, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientMulticolorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860615, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMulticolorStateR property: 0x");
            sb.append(tenToHex(557860615));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientMusicModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860613, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMusicModeR property: 0x");
            sb.append(tenToHex(557860613));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAmbientMusicModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAmbientMusicModeS property: 0x" + tenToHex(557860614) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860614, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAmbientFeedBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860631, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientFeedBack property: 0x");
            sb.append(tenToHex(557860631));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBleOpenDriverDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858415, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBleOpenDriverDoorStateR property: 0x");
            sb.append(tenToHex(557858415));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBleOpenDriverDoorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBleOpenDriverDoorStateS property: 0x" + tenToHex(557858423) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858423, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBrakeCloseDoorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858413, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBrakeCloseDoorR property: 0x");
            sb.append(tenToHex(557858413));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBrakeCloseDoorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBrakeCloseDoorS property: 0x" + tenToHex(557858426) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858426, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCampingBlanceActionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858366, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCampingBlanceActionR property: 0x");
            sb.append(tenToHex(557858366));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCampingBlanceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858365, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCampingBlanceStateR property: 0x");
            sb.append(tenToHex(557858365));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCampingBlanceStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCampingBlanceStateS property: 0x" + tenToHex(557858405) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858405, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCampingBlanceSupportedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858375, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCampingBlanceSupportedR property: 0x");
            sb.append(tenToHex(557858375));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCheckSumS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCheckSumS property: 0x" + tenToHex(557858410) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858410, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCloseDoorLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858411, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCloseDoorLevelR property: 0x");
            sb.append(tenToHex(557858411));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCloseDoorLevelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCloseDoorLevelS property: 0x" + tenToHex(CarVendorSettingManager.ID_CLOSE_DOOR_LEVEL_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_CLOSE_DOOR_LEVEL_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getCvcModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858374, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCvcModeR property: 0x");
            sb.append(tenToHex(557858374));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCvcModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCvcModeS property: 0x" + tenToHex(557858408) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858408, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDampStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858378, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDampStateR property: 0x");
            sb.append(tenToHex(557858378));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDampStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDampStateS property: 0x" + tenToHex(557858401) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858401, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDyHeightByVoiceS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDyHeightByVoiceS property: 0x" + tenToHex(557858407) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858407, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDyHeightLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858379, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDyHeightLevelR property: 0x");
            sb.append(tenToHex(557858379));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDyHeightToggleStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858361, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDyHeightToggleStateR property: 0x");
            sb.append(tenToHex(557858361));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDyHeightToggleStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDyHeightToggleStateS property: 0x" + tenToHex(557858402) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858402, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDyHeightTurnActionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858377, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDyHeightTurnActionR property: 0x");
            sb.append(tenToHex(557858377));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDyHeightValueS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDyHeightValueS property: 0x" + tenToHex(557858400) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858400, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdCampingBlanceActionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858391, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdCampingBlanceActionR property: 0x");
            sb.append(tenToHex(557858391));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdCampingBlanceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858390, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdCampingBlanceStateR property: 0x");
            sb.append(tenToHex(557858390));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdCampingBlanceSupportedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858399, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdCampingBlanceSupportedR property: 0x");
            sb.append(tenToHex(557858399));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdCrcCheckNumR() throws CarNotConnectedException {
        return 0;
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdDampStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858383, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdDampStateR property: 0x");
            sb.append(tenToHex(557858383));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdDyHeightLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858384, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdDyHeightLevelR property: 0x");
            sb.append(tenToHex(557858384));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdDyHeightToggleStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858385, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdDyHeightToggleStateR property: 0x");
            sb.append(tenToHex(557858385));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdDyHeightTurnActionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858382, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdDyHeightTurnActionR property: 0x");
            sb.append(tenToHex(557858382));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdFixedStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858393, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdFixedStateR property: 0x");
            sb.append(tenToHex(557858393));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdFrontBoxTakeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858388, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdFrontBoxTakeStateR property: 0x");
            sb.append(tenToHex(557858388));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdLiftModeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858392, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdLiftModeStateR property: 0x");
            sb.append(tenToHex(557858392));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdModeSupportedHturnR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858395, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdModeSupportedHturnR property: 0x");
            sb.append(tenToHex(557858395));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdSpeedSupportedTurnR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858394, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdSpeedSupportedTurnR property: 0x");
            sb.append(tenToHex(557858394));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdTrailerModeCodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858398, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdTrailerModeCodeR property: 0x");
            sb.append(tenToHex(557858398));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdWelcomeActiveR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858387, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdWelcomeActiveR property: 0x");
            sb.append(tenToHex(557858387));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdWelcomeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858386, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdWelcomeStateR property: 0x");
            sb.append(tenToHex(557858386));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdWelcomeSupportedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858396, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFdWelcomeSupportedR property: 0x");
            sb.append(tenToHex(557858396));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFixedStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858368, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFixedStateR property: 0x");
            sb.append(tenToHex(557858368));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIntelLockOpenDriverDoorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858412, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelLockOpenDriverDoorR property: 0x");
            sb.append(tenToHex(557858412));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIntelLockOpenDriverDoorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIntelLockOpenDriverDoorS property: 0x" + tenToHex(557858425) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858425, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLiftModeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858367, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLiftModeStateR property: 0x");
            sb.append(tenToHex(557858367));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLiftModeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLiftModeStateS property: 0x" + tenToHex(557858403) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858403, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMessageCounterS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMessageCounterS property: 0x" + tenToHex(557858409) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858409, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getModeSupportedHturnR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858370, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getModeSupportedHturnR property: 0x");
            sb.append(tenToHex(557858370));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getNfcOpenDriverDoorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858414, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getNfcOpenDriverDoorR property: 0x");
            sb.append(tenToHex(557858414));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setNfcOpenDriverDoorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setNfcOpenDriverDoorS property: 0x" + tenToHex(557858422) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858422, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setNotifyTunnelLengthS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setNotifyTunnelLengthS property: 0x" + tenToHex(557858417) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858417, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setOtaModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setOTAModeS property: 0x" + tenToHex(557860375) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860375, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getOnFdCrcCheckNumR() throws CarNotConnectedException {
        return 0;
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getOneLevelSeatHeatingStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858355, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOneLevelSeatHeatingStateR property: 0x");
            sb.append(tenToHex(557858355));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearSeatGreetStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858376, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearSeatGreetStateR property: 0x");
            sb.append(tenToHex(557858376));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearSeatGreetStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearSeatGreetStateS property: 0x" + tenToHex(557858427) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858427, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearViewEcuMirrorFlipStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858360, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearViewEcuMirrorFlipStateR property: 0x");
            sb.append(tenToHex(557858360));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSafeBeltCloseDriveDoorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858416, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafeBeltCloseDriveDoorR property: 0x");
            sb.append(tenToHex(557858416));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSafeBeltCloseDriveDoorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSafeBeltCloseDriveDoorStateS property: 0x" + tenToHex(557858424) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858424, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSpeedSupportedTurnR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858369, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSpeedSupportedTurnR property: 0x");
            sb.append(tenToHex(557858369));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTailBoxTakeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858364, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTailBoxTakeStateR property: 0x");
            sb.append(tenToHex(557858364));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setTailBoxTakeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setTailBoxTakeStateS property: 0x" + tenToHex(557858406) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858406, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTailBoxTakeSupportedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858372, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTailBoxTakeSupportedR property: 0x");
            sb.append(tenToHex(557858372));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerLightCheckStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858358, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrailerLightCheckStateR property: 0x");
            sb.append(tenToHex(557858358));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setTrailerLightCheckStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setTrailerLightCheckStateS property: 0x" + tenToHex(557858420) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858420, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerModeCodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858373, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrailerModeCodeR property: 0x");
            sb.append(tenToHex(557858373));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerModeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858357, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrailerModeStateR property: 0x");
            sb.append(tenToHex(557858357));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setTrailerModeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setTrailerModeStateS property: 0x" + tenToHex(557858428) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858428, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getVspStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858418, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVspStateR property: 0x");
            sb.append(tenToHex(557858418));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setVspStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setVspStateS property: 0x" + tenToHex(557858419) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858419, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWelcomeActiveR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858363, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWelcomeActiveR property: 0x");
            sb.append(tenToHex(557858363));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWelcomeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858362, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWelcomeStateR property: 0x");
            sb.append(tenToHex(557858362));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setWelcomeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setWelcomeStateS property: 0x" + tenToHex(557858404) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858404, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getWelcomeSupportedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858371, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWelcomeSupportedR property: 0x");
            sb.append(tenToHex(557858371));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setnotifyEnterTunnelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setnotifyEnterTunnelS property: 0x" + tenToHex(557858356) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858356, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setInternationalLanguageS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setInternationalLanguageS property: 0x" + tenToHex(557858343) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858343, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSomethingLeftInRefrigeratorR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858354, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSomethingLeftInRefrigeratorR property: 0x");
            sb.append(tenToHex(557858354));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLanguageS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLanguageS property: 0x" + tenToHex(557858342) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858342, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMissPackageCountR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858331, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMissPackageCountR property: 0x");
            sb.append(tenToHex(557858331));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPabCodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858330, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPabCodeR property: 0x");
            sb.append(tenToHex(557858330));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPabStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858345, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPabStateR property: 0x");
            sb.append(tenToHex(557858345));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setPabStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setPabStateS property: 0x" + tenToHex(557858344) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858344, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getScreenAngleR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858334, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getScreenAngleR property: 0x");
            sb.append(tenToHex(557858334));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getScreenAngleValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858338, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getScreenAngleValueR property: 0x");
            sb.append(tenToHex(557858338));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getScreenAntiPinchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858337, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getScreenAntiPinchStateR property: 0x");
            sb.append(tenToHex(557858337));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getScreenRotationActionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858336, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getScreenRotationActionR property: 0x");
            sb.append(tenToHex(557858336));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setScreenRotationLockS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setScreenRotationLockS property: 0x" + tenToHex(557858335) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858335, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getScreenRotationOverHotStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858340, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getScreenRotationOverHotStateR property: 0x");
            sb.append(tenToHex(557858340));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setScreenRotationS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setScreenRotationS property: 0x" + tenToHex(557858333) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858333, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getScreenVoltageStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862926, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getScreenVoltageStateR property: 0x");
            sb.append(tenToHex(557862926));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRecoverOrSaveParamsChanged2() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857847, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRecoverOrSaveParamsChanged2 property: 0x");
            sb.append(tenToHex(557857847));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRecoverOrSaveParamsChanged3() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857848, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRecoverOrSaveParamsChanged3 property: 0x");
            sb.append(tenToHex(557857848));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRecoverOrSaveParamsChanged4() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857849, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRecoverOrSaveParamsChanged4 property: 0x");
            sb.append(tenToHex(557857849));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRecoverOrSaveParamsChanged5() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857850, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRecoverOrSaveParamsChanged5 property: 0x");
            sb.append(tenToHex(557857850));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRecoverOrSaveParamsChanged6() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857851, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRecoverOrSaveParamsChanged6 property: 0x");
            sb.append(tenToHex(557857851));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRecoverOrSaveParamsChanged7() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857852, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRecoverOrSaveParamsChanged7 property: 0x");
            sb.append(tenToHex(557857852));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonSeatHeatingStateChanged437() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857810, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSeatHeatingStateChanged437 property: 0x");
            sb.append(tenToHex(557857810));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerLastLeftStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857844, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerLastLeftStateR property: 0x");
            sb.append(tenToHex(557857844));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerLastMiddleStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857846, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerLastMiddleStateR property: 0x");
            sb.append(tenToHex(557857846));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerLastRightStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857845, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerLastRightStateR property: 0x");
            sb.append(tenToHex(557857845));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerRearLeftStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857841, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerRearLeftStateR property: 0x");
            sb.append(tenToHex(557857841));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerRearMiddleStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857843, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerRearMiddleStateR property: 0x");
            sb.append(tenToHex(557857843));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerRearRightStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857842, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerRearRightStateR property: 0x");
            sb.append(tenToHex(557857842));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPassengerStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857840, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerStateR property: 0x");
            sb.append(tenToHex(557857840));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearSeatsFoldOrExpand(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearSeatsFoldOrExpand property: 0x" + tenToHex(557857839) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857839, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getComSpeedDownStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857833, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getComSpeedDownStateR property: 0x");
            sb.append(tenToHex(557857833));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setComSpeedDownStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setComSpeedDownStateS property: 0x" + tenToHex(CarVendorSettingManager.ID_COM_SPEED_DOWN_STATES) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_COM_SPEED_DOWN_STATES, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getComSpeedUpStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857838, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getComSpeedUpStateR property: 0x");
            sb.append(tenToHex(557857838));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setComSpeedUpStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setComSpeedUpStateS property: 0x" + tenToHex(557857832) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857832, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHSpeedAutoCloseStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857835, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHSpeedAutoCloseStateR property: 0x");
            sb.append(tenToHex(557857835));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHSpeedAutoCloseStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHSpeedAutoCloseStateS property: 0x" + tenToHex(CarVendorSettingManager.ID_H_SPEED_AUTO_CLOSE_STATES) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_H_SPEED_AUTO_CLOSE_STATES, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLockOffR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857814, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLockOffR property: 0x");
            sb.append(tenToHex(557857814));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearViewMirrorFlipSetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearViewMirrorFlipSetS property: 0x" + tenToHex(CarVendorSettingManager.ID_REAR_VIEW_MIRROR_FLIP_SETS) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_REAR_VIEW_MIRROR_FLIP_SETS, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateBackLeftDomainControlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857730, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateBackLeftDomainControlR property: 0x");
            sb.append(tenToHex(557857730));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatHeatingStateBackLeftDomainControlS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatHeatingStateBackLeftDomainControlS property: 0x" + tenToHex(557857827) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857827, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateBackRightDomainControlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857731, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateBackRightDomainControlR property: 0x");
            sb.append(tenToHex(557857731));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatHeatingStateBackRightDomainControlS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatHeatingStateBackRightDomainControlS property: 0x" + tenToHex(557857828) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857828, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatHeatingStateDriverDomainControlS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatHeatingStateDriverDomainControlS property: 0x" + tenToHex(557857826) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857826, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateBackLeftDomainControlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857740, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateBackLeftDomainControlR property: 0x");
            sb.append(tenToHex(557857740));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatVentilatingStateBackLeftDomainControlS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatVentilatingStateBackLeftDomainControlS property: 0x" + tenToHex(557857830) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857830, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateBackRightDomainControlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857741, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateBackRightDomainControlR property: 0x");
            sb.append(tenToHex(557857741));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatVentilatingStateBackRightDomainControlS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatVentilatingStateBackRightDomainControlS property: 0x" + tenToHex(557857831) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857831, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateDriverDomainControlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857733, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateDriverDomainControlR property: 0x");
            sb.append(tenToHex(557857733));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSteeringWheelHeatingStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857813, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelHeatingStateR property: 0x");
            sb.append(tenToHex(557857813));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setTurnSeatbackrestPercentDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setTurnSeatbackrestPercentDriver property: 0x" + tenToHex(557857816) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857816, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setTurnSeatbackrestPercentPassenger(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setTurnSeatbackrestPercentPassenger property: 0x" + tenToHex(557857817) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857817, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setVoiceCtlBackDoor(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setVoiceCtlBackDoor property: 0x" + tenToHex(557857815) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857815, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonSaveSeatParamsAllChanged3CA() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857807, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSaveSeatParamsAllChanged3CA property: 0x");
            sb.append(tenToHex(557857807));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonSaveSeatParamsAllChanged402() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857808, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSaveSeatParamsAllChanged402 property: 0x");
            sb.append(tenToHex(557857808));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonSaveSeatParamsAllChanged42F() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857809, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSaveSeatParamsAllChanged42F property: 0x");
            sb.append(tenToHex(557857809));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonSaveSeatParamsAllChangedPlace5() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857805, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSaveSeatParamsAllChangedPlace5 property: 0x");
            sb.append(tenToHex(557857805));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonSaveSeatParamsAllChangedPlace7() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857806, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSaveSeatParamsAllChangedPlace7 property: 0x");
            sb.append(tenToHex(557857806));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonSmartWelcomeLightStateChanged() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857811, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonSmartWelcomeLightStateChanged property: 0x");
            sb.append(tenToHex(557857811));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getonTrayStateChanged() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857804, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getonTrayStateChanged property: 0x");
            sb.append(tenToHex(557857804));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnCaliperSplintPercentDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnCaliperSplintPercentDriver property: 0x" + tenToHex(557857823) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857823, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnCaliperSplintPercentPassenger(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnCaliperSplintPercentPassenger property: 0x" + tenToHex(557857824) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857824, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnCushionPercentDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnCushionPercentDriver property: 0x" + tenToHex(557857825) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857825, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnHeadrestHeightPercentDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnHeadrestHeightPercentDriver property: 0x" + tenToHex(557857822) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857822, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnHeadrestHorizationPercentDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnHeadrestHorizationPercentDriver property: 0x" + tenToHex(557857821) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857821, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHeightPercentCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHeightPercentCopilot property: 0x" + tenToHex(557857812) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857812, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHeightPercentDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHeightPercentDriver property: 0x" + tenToHex(557857820) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857820, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationPercentDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationPercentDriver property: 0x" + tenToHex(557857818) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857818, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationPercentPassenger(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationPercentPassenger property: 0x" + tenToHex(557857819) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857819, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getACAutoAirR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857543, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACAutoAirR property: 0x");
            sb.append(tenToHex(557857543));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setACAutoAirS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setACAutoAirS property: 0x" + tenToHex(557857544) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857544, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getACBTWindR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857536, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACBTWindR property: 0x");
            sb.append(tenToHex(557857536));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setACBTWindS4E3(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setACBTWindS4E3 property: 0x" + tenToHex(557857539) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857539, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setACBTWindSX28(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setACBTWindSX28 property: 0x" + tenToHex(557857538) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857538, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setACBTWindSX31(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setACBTWindSX31 property: 0x" + tenToHex(557857537) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857537, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getACPauseCycleR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857540, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACPauseCycleR property: 0x");
            sb.append(tenToHex(557857540));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setACPauseCycleS4E3(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setACPauseCycleS4E3 property: 0x" + tenToHex(557857541) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857541, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setACPauseCycleS4EF(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setACPauseCycleS4EF property: 0x" + tenToHex(557857542) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857542, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getACTunnelCycleR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857555, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACTunnelCycleR property: 0x");
            sb.append(tenToHex(557857555));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setACTunnelCycleS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setACTunnelCycleS property: 0x" + tenToHex(557857556) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857556, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAcWarmModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857557, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWarmModeR property: 0x");
            sb.append(tenToHex(557857557));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAcWarmModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAcWarmModeS property: 0x" + tenToHex(557857558) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857558, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAutoExternalRearMirrorFollowUpSwitchR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857796, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoExternalRearMirrorFollowUpSwitchR property: 0x");
            sb.append(tenToHex(557857796));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAutoExternalRearMirrorFollowUpSwitchS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAutoExternalRearMirrorFollowUpSwitchS property: 0x" + tenToHex(557857795) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857795, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAutoPowerOffStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857597, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoPowerOffStateR property: 0x");
            sb.append(tenToHex(557857597));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAutoPowerOffStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAutoPowerOffStateS property: 0x" + tenToHex(557857598) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857598, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAutoPowerOnStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857595, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoPowerOnStateR property: 0x");
            sb.append(tenToHex(557857595));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAutoPowerOnStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAutoPowerOnStateS property: 0x" + tenToHex(557857596) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857596, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAutoPurification(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAutoPurification property: 0x" + tenToHex(557857546) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857546, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getAutoPurificationState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846608, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoPurificationState property: 0x");
            sb.append(tenToHex(557846608));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAutoRainWiperStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAutoRainWiperStateS property: 0x" + tenToHex(557857610) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857610, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBackDoorOpenedHeightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857561, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackDoorOpenedHeightR property: 0x");
            sb.append(tenToHex(557857561));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBackDoorOpenedHeightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBackDoorOpenedHeightS property: 0x" + tenToHex(557857562) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857562, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBackHomeLightDelayValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857626, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackHomeLightDelayValueR property: 0x");
            sb.append(tenToHex(557857626));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBackHomeLightDelayValueS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBackHomeLightDelayValueS property: 0x" + tenToHex(557857627) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857627, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getBrakePedalModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857603, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBrakePedalModeR property: 0x");
            sb.append(tenToHex(557857603));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBrakePedalModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBrakePedalModeS property: 0x" + tenToHex(557857604) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857604, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getChargingPortR27C() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857792, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingPortR27C property: 0x");
            sb.append(tenToHex(557857792));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getChargingPortR475() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857793, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingPortR475 property: 0x");
            sb.append(tenToHex(557857793));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setChargingPortS4E3(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setChargingPortS4E3 property: 0x" + tenToHex(557857794) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857794, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getComfortableParkingStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857605, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getComfortableParkingStateR property: 0x");
            sb.append(tenToHex(557857605));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setComfortableParkingStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setComfortableParkingStateS property: 0x" + tenToHex(557857606) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857606, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDisinfStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857705, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDisinfStateR property: 0x");
            sb.append(tenToHex(557857705));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDisinfStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDisinfStateS property: 0x" + tenToHex(557857704) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857704, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDoorknobAutoShrinkStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857565, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorknobAutoShrinkStateR property: 0x");
            sb.append(tenToHex(557857565));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDoorknobAutoShrinkStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDoorknobAutoShrinkStateS property: 0x" + tenToHex(557857566) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857566, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDriverSeatAutoReturnR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857599, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverSeatAutoReturnR property: 0x");
            sb.append(tenToHex(557857599));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDriverSeatAutoReturnS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDriverSeatAutoReturnS property: 0x" + tenToHex(557857600) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857600, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDynamicSuspensionModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857611, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDynamicSuspensionModeR property: 0x");
            sb.append(tenToHex(557857611));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDynamicSuspensionModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDynamicSuspensionModeS property: 0x" + tenToHex(557857612) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857612, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getEnergyFeedbackR341() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857788, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnergyFeedbackR341 property: 0x");
            sb.append(tenToHex(557857788));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setEnergyFeedbackS4E3(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setEnergyFeedbackS4E3 property: 0x" + tenToHex(557857789) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857789, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getEngineSoundTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857587, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineSoundTypeR property: 0x");
            sb.append(tenToHex(557857587));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setEngineSoundTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setEngineSoundTypeS property: 0x" + tenToHex(557857588) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857588, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getExhibitionMode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557871104, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getExhibitionMode property: 0x");
            sb.append(tenToHex(557871104));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getExhibitionStartEnginePermitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857759, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getExhibitionStartEnginePermitR property: 0x");
            sb.append(tenToHex(557857759));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setExhibitionStartEnginePermitS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setExhibitionStartEnginePermitS property: 0x" + tenToHex(557857760) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857760, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHeadLampConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857751, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadLampConfig property: 0x");
            sb.append(tenToHex(557857751));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHeadlampLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857747, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadlampLevelR property: 0x");
            sb.append(tenToHex(557857747));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHeadlampLevelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHeadlampLevelS property: 0x" + tenToHex(557857748) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857748, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHeadrestHorizationBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857690, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestHorizationBackLeft property: 0x");
            sb.append(tenToHex(557857690));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHeadrestHorizationBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857691, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestHorizationBackRight property: 0x");
            sb.append(tenToHex(557857691));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHeadrestHorizationCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857689, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestHorizationCopilot property: 0x");
            sb.append(tenToHex(557857689));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHeadrestHorizationDriver() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857688, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestHorizationDriver property: 0x");
            sb.append(tenToHex(557857688));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getHeadrestTurnHeightBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559955504, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightBackLeft property: 0x");
            sb.append(tenToHex(559955504));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getHeadrestTurnHeightBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559955505, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightBackRight property: 0x");
            sb.append(tenToHex(559955505));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getHeadrestTurnHeightCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954845, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightCopilot property: 0x");
            sb.append(tenToHex(559954845));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getHeadrestTurnHeightDriver() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954844, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightDriver property: 0x");
            sb.append(tenToHex(559954844));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudBrightnessR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857576, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudBrightnessR property: 0x");
            sb.append(tenToHex(557857576));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudBrightnessS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudBrightnessS property: 0x" + tenToHex(557857577) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857577, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudDisplayContentRDrive() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857582, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudDisplayContentRDrive property: 0x");
            sb.append(tenToHex(557857582));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudDisplayContentRNavigation() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857583, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudDisplayContentRNavigation property: 0x");
            sb.append(tenToHex(557857583));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudDisplayContentSDrive(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudDisplayContentSDrive property: 0x" + tenToHex(557857584) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857584, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudDisplayContentSNavigation(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudDisplayContentSNavigation property: 0x" + tenToHex(557857585) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857585, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudHeightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857574, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudHeightR property: 0x");
            sb.append(tenToHex(557857574));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudHeightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudHeightS property: 0x" + tenToHex(557857575) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857575, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudModeParams() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857586, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudModeParams property: 0x");
            sb.append(tenToHex(557857586));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857580, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudModeR property: 0x");
            sb.append(tenToHex(557857580));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudModeS property: 0x" + tenToHex(557857581) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857581, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudRotationAngleR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857578, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudRotationAngleR property: 0x");
            sb.append(tenToHex(557857578));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudRotationAngleS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudRotationAngleS property: 0x" + tenToHex(557857579) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857579, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857571, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudStateR property: 0x");
            sb.append(tenToHex(557857571));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudStateS property: 0x" + tenToHex(557857572) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857572, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIALAreaDifferentWays(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIALAreaDifferentWays property: 0x" + tenToHex(557857722) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857722, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALAreaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857721, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALAreaR property: 0x");
            sb.append(tenToHex(557857721));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALBrightnessBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857718, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessBackR property: 0x");
            sb.append(tenToHex(557857718));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALBrightnessFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857719, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessFrontR property: 0x");
            sb.append(tenToHex(557857719));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIALBrightnessS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIALBrightnessS property: 0x" + tenToHex(557857720) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857720, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALBrightnessStarRingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857716, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessStarRingR property: 0x");
            sb.append(tenToHex(557857716));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALBrightnessStarrySkyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857717, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALBrightnessStarrySkyR property: 0x");
            sb.append(tenToHex(557857717));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIALColorAdjustS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIALColorAdjustS property: 0x" + tenToHex(557857715) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857715, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALColorBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857713, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorBackR property: 0x");
            sb.append(tenToHex(557857713));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALColorFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857712, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorFrontR property: 0x");
            sb.append(tenToHex(557857712));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIALColorS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIALColorS property: 0x" + tenToHex(557857714) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857714, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALColorStarRingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857710, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorStarRingR property: 0x");
            sb.append(tenToHex(557857710));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIALColorStarrySkyR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857711, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIALColorStarrySkyR property: 0x");
            sb.append(tenToHex(557857711));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getINSThemeR() throws CarNotConnectedException {
        return 0;
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setINSThemeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setINSThemeS property: 0x" + tenToHex(557857025) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857025, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getInsideLightDoorStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857762, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInsideLightDoorStateR property: 0x");
            sb.append(tenToHex(557857762));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setInsideLightDoorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setInsideLightDoorStateS property: 0x" + tenToHex(557857763) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857763, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIntelligentAcAreaStateR(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIntelligentAcAreaStateR property: 0x" + tenToHex(557857559) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857559, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIntelligentAcAreaStateS() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857560, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentAcAreaStateS property: 0x");
            sb.append(tenToHex(557857560));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIntelligentLockStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857569, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentLockStateR property: 0x");
            sb.append(tenToHex(557857569));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIntelligentLockStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIntelligentLockStateS property: 0x" + tenToHex(557857570) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857570, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getIntelligentUnlockStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857567, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentUnlockStateR property: 0x");
            sb.append(tenToHex(557857567));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIntelligentUnlockStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIntelligentUnlockStateS property: 0x" + tenToHex(557857568) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857568, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLanternLanguageModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857744, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLanternLanguageModeR property: 0x");
            sb.append(tenToHex(557857744));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLanternLanguageModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLanternLanguageModeS property: 0x" + tenToHex(557857743) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857743, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLeaveCarPInd() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857803, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeaveCarPInd property: 0x");
            sb.append(tenToHex(557857803));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLeftHeadlampLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857749, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftHeadlampLevel property: 0x");
            sb.append(tenToHex(557857749));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLeftHomeLightDelayValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857624, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftHomeLightDelayValueR property: 0x");
            sb.append(tenToHex(557857624));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLeftHomeLightDelayValueS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLeftHomeLightDelayValueS property: 0x" + tenToHex(557857625) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857625, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLockCarRiseWindowR12F() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857622, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLockCarRiseWindowR12F property: 0x");
            sb.append(tenToHex(557857622));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getLockCarRiseWindowR48E() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857621, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLockCarRiseWindowR48E property: 0x");
            sb.append(tenToHex(557857621));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLockCarRiseWindowS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLockCarRiseWindowS property: 0x" + tenToHex(557857623) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857623, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLockOffS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLockOffS property: 0x" + tenToHex(557857797) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857797, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageLevel4C1(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageLevel4C1 property: 0x" + tenToHex(557857772) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857772, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageLevel4C2(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageLevel4C2 property: 0x" + tenToHex(557857773) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857773, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageLevel4C3(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageLevel4C3 property: 0x" + tenToHex(557857774) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857774, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageLevel4C4(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageLevel4C4 property: 0x" + tenToHex(557857775) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857775, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelLeftBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857768, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelLeftBack property: 0x");
            sb.append(tenToHex(557857768));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelLeftBackECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857769, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelLeftBackECU property: 0x");
            sb.append(tenToHex(557857769));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelLeftFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857764, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelLeftFront property: 0x");
            sb.append(tenToHex(557857764));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelLeftFrontECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857765, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelLeftFrontECU property: 0x");
            sb.append(tenToHex(557857765));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelRightBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857771, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelRightBack property: 0x");
            sb.append(tenToHex(557857771));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelRightBackECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857770, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelRightBackECU property: 0x");
            sb.append(tenToHex(557857770));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelRightFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857766, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelRightFront property: 0x");
            sb.append(tenToHex(557857766));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageLevelRightFrontECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857767, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageLevelRightFrontECU property: 0x");
            sb.append(tenToHex(557857767));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857776, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageModeR property: 0x");
            sb.append(tenToHex(557857776));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageModeR3CA() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857779, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageModeR3CA property: 0x");
            sb.append(tenToHex(557857779));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageModeR402() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857781, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageModeR402 property: 0x");
            sb.append(tenToHex(557857781));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageModeR42F() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857782, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageModeR42F property: 0x");
            sb.append(tenToHex(557857782));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageModeR4FA() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857778, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageModeR4FA property: 0x");
            sb.append(tenToHex(557857778));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageModeR4FALeftBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857780, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageModeR4FALeftBack property: 0x");
            sb.append(tenToHex(557857780));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMassageModeR4FARightBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857783, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMassageModeR4FARightBack property: 0x");
            sb.append(tenToHex(557857783));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageModeS4C1(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageModeS4C1 property: 0x" + tenToHex(557857784) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857784, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageModeS4C2(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageModeS4C2 property: 0x" + tenToHex(557857785) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857785, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageModeS4C3(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageModeS4C3 property: 0x" + tenToHex(557857786) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857786, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMassageModeS4C4(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMassageModeS4C4 property: 0x" + tenToHex(557857787) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857787, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMicroSwitchLockWindowStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857617, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMicroSwitchLockWindowStateR property: 0x");
            sb.append(tenToHex(557857617));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMicroSwitchLockWindowStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMicroSwitchLockWindowStateS property: 0x" + tenToHex(557857618) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857618, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMicroSwitchUnlockWindowStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857619, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMicroSwitchUnlockWindowStateR property: 0x");
            sb.append(tenToHex(557857619));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMicroSwitchUnlockWindowStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMicroSwitchUnlockWindowStateS property: 0x" + tenToHex(557857620) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857620, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMotorPretensionerSeatbeltStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856058, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMotorPretensionerSeatbeltStateR property: 0x");
            sb.append(tenToHex(557856058));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAfsSwitchSate(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAfsSwitchSate  property: 0x" + tenToHex(557860626) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860626, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMotorPretensionerSeatbeltStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMotorPretensionerSeatbeltStateS property: 0x" + tenToHex(557857608) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857608, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getOffReminder() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857755, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOffReminder property: 0x");
            sb.append(tenToHex(557857755));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getOverspeedLockR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857799, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOverspeedLockR property: 0x");
            sb.append(tenToHex(557857799));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setOverspeedLockS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setOverspeedLockS property: 0x" + tenToHex(557857798) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857798, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPM25PowerR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857549, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPM25PowerR property: 0x");
            sb.append(tenToHex(557857549));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setPM25PowerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setPM25PowerS property: 0x" + tenToHex(557857550) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857550, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPM25SwitchCheckR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857551, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPM25SwitchCheckR property: 0x");
            sb.append(tenToHex(557857551));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setPM25SwitchCheckS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setPM25SwitchCheckS property: 0x" + tenToHex(557857552) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857552, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getPM25TimeCheckR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857553, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPM25TimeCheckR property: 0x");
            sb.append(tenToHex(557857553));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setPM25TimeCheckS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setPM25TimeCheckS property: 0x" + tenToHex(557857554) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857554, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRainAutoLockWindowStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857563, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRainAutoLockWindowStateR property: 0x");
            sb.append(tenToHex(557857563));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRainAutoLockWindowStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRainAutoLockWindowStateS property: 0x" + tenToHex(557857564) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857564, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearAcPanelAutoLockTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856073, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearAcPanelAutoLockTimeR property: 0x");
            sb.append(tenToHex(557856073));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearAcPanelAutoLockTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearAcPanelAutoLockTimeS property: 0x" + tenToHex(557857757) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857757, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearViewMirrorFlipR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857591, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearViewMirrorFlipR property: 0x");
            sb.append(tenToHex(557857591));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearViewMirrorFlipS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearViewMirrorFlipS property: 0x" + tenToHex(557857593) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857593, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearViewMirrorFlipSetOneR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857592, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearViewMirrorFlipSetOneR property: 0x");
            sb.append(tenToHex(557857592));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearViewMirrorFlipSetTwoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857594, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearViewMirrorFlipSetTwoR property: 0x");
            sb.append(tenToHex(557857594));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getRearviewMirrorXLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954814, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorXLeft property: 0x");
            sb.append(tenToHex(559954814));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getRearviewMirrorXRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954815, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorXRight property: 0x");
            sb.append(tenToHex(559954815));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getRearviewMirrorYLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954812, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorYLeft property: 0x");
            sb.append(tenToHex(559954812));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getRearviewMirrorYRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954813, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorYRight property: 0x");
            sb.append(tenToHex(559954813));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorDoorState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857703, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRefrigeratorDoorState property: 0x");
            sb.append(tenToHex(557857703));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857699, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRefrigeratorModeR property: 0x");
            sb.append(tenToHex(557857699));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRefrigeratorModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRefrigeratorModeS property: 0x" + tenToHex(557857700) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857700, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRefrigeratorState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857708, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRefrigeratorState property: 0x");
            sb.append(tenToHex(557857708));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRemenberPowerOffStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857701, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemenberPowerOffStateR property: 0x");
            sb.append(tenToHex(557857701));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRemenberPowerOffStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRemenberPowerOffStateS property: 0x" + tenToHex(557857702) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857702, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRemoteControlDownwindowStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857615, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemoteControlDownwindowStateR property: 0x");
            sb.append(tenToHex(557857615));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRemoteControlDownwindowStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRemoteControlDownwindowStateS property: 0x" + tenToHex(557857616) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857616, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRemoteControlUpwindowStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857613, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemoteControlUpwindowStateR property: 0x");
            sb.append(tenToHex(557857613));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRemoteControlUpwindowStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRemoteControlUpwindowStateS property: 0x" + tenToHex(557857614) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857614, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRemoteKeyStartAcTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856063, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemoteKeyStartAcTimeR property: 0x");
            sb.append(tenToHex(557856063));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRemoteKeyStartAcTimeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRemoteKeyStartAcTimeS property: 0x" + tenToHex(557857548) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857548, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRightHeadlampLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857750, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightHeadlampLevel property: 0x");
            sb.append(tenToHex(557857750));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSOCTargetR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857790, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSOCTargetR property: 0x");
            sb.append(tenToHex(557857790));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSOCTargetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSOCTargetS property: 0x" + tenToHex(557857791) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857791, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateBackLeftECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857726, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateBackLeftECU property: 0x");
            sb.append(tenToHex(557857726));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateBackRightECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857727, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateBackRightECU property: 0x");
            sb.append(tenToHex(557857727));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatHeatingStateCopilotDomainControl(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatHeatingStateCopilotDomainControl property: 0x" + tenToHex(557857732) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857732, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateCopilotECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857725, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateCopilotECU property: 0x");
            sb.append(tenToHex(557857725));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateCopilotL1P() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857728, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateCopilotL1P property: 0x");
            sb.append(tenToHex(557857728));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateCopilotNonL1P() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857729, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateCopilotNonL1P property: 0x");
            sb.append(tenToHex(557857729));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateDriverDomainControl() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857723, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateDriverDomainControl property: 0x");
            sb.append(tenToHex(557857723));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingStateDriverECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857724, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingStateDriverECU property: 0x");
            sb.append(tenToHex(557857724));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHorizationBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857666, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHorizationBackLeft property: 0x");
            sb.append(tenToHex(557857666));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHorizationBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857667, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHorizationBackRight property: 0x");
            sb.append(tenToHex(557857667));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHorizationCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857665, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHorizationCopilot property: 0x");
            sb.append(tenToHex(557857665));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHorizationDriver() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857664, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHorizationDriver property: 0x");
            sb.append(tenToHex(557857664));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatTurnHeightBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954822, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightBackLeft property: 0x");
            sb.append(tenToHex(559954822));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatTurnHeightBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954823, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightBackRight property: 0x");
            sb.append(tenToHex(559954823));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatTurnHeightCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954821, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightCopilot property: 0x");
            sb.append(tenToHex(559954821));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatTurnHeightDriver() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954820, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightDriver property: 0x");
            sb.append(tenToHex(559954820));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateBackLeftECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857736, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateBackLeftECU property: 0x");
            sb.append(tenToHex(557857736));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateBackRightECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857737, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateBackRightECU property: 0x");
            sb.append(tenToHex(557857737));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatVentilatingStateCopilotDomainControl(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatVentilatingStateCopilotDomainControl property: 0x" + tenToHex(557857742) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857742, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateCopilotECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857735, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateCopilotECU property: 0x");
            sb.append(tenToHex(557857735));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateCopilotL1P() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857738, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateCopilotL1P property: 0x");
            sb.append(tenToHex(557857738));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateCopilotNonL1P() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857739, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateCopilotNonL1P property: 0x");
            sb.append(tenToHex(557857739));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSeatVentilatingStateDriverDomainControl(int state) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSeatVentilatingStateDriverDomainControl property: 0x" + tenToHex(557857829) + " class: int value: " + CastArrayUtil.parseString(state), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857829, 0, CastArrayUtil.toWrap(state));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingStateDriverECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857734, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingStateDriverECU property: 0x");
            sb.append(tenToHex(557857734));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatbackrestPostionBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954826, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestPostionBackLeft property: 0x");
            sb.append(tenToHex(559954826));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatbackrestPostionBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954827, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestPostionBackRight property: 0x");
            sb.append(tenToHex(559954827));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatbackrestPostionCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954825, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestPostionCopilot property: 0x");
            sb.append(tenToHex(559954825));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSeatbackrestPostionDriver() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954824, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestPostionDriver property: 0x");
            sb.append(tenToHex(559954824));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSmartWelcomeLightStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857752, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSmartWelcomeLightStateR property: 0x");
            sb.append(tenToHex(557857752));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSmartWelcomeLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSmartWelcomeLightStateS property: 0x" + tenToHex(557857753) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857753, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSpeedReminderValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857589, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSpeedReminderValueR property: 0x");
            sb.append(tenToHex(557857589));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSpeedReminderValueS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSpeedReminderValueS property: 0x" + tenToHex(557857590) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857590, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSteerAssisR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857800, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteerAssisR property: 0x");
            sb.append(tenToHex(557857800));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSteerAssisS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSteerAssisS property: 0x" + tenToHex(557857801) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857801, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSteerAssisSupported() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857802, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteerAssisSupported property: 0x");
            sb.append(tenToHex(557857802));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSteerPositionAutoReturnR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856146, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteerPositionAutoReturnR property: 0x");
            sb.append(tenToHex(557856146));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSteerPositionAutoReturnS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSteerPositionAutoReturnS property: 0x" + tenToHex(557857602) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857602, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSteeringWheelHeatingStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSteeringWheelHeatingStateS property: 0x" + tenToHex(557857761) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857761, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSteeringWheelY() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954810, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelY property: 0x");
            sb.append(tenToHex(559954810));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getSteeringWheelZ() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954811, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelZ property: 0x");
            sb.append(tenToHex(559954811));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrayInitState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857709, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrayInitState property: 0x");
            sb.append(tenToHex(557857709));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrayStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857706, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrayStateR property: 0x");
            sb.append(tenToHex(557857706));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setTrayStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setTrayStateS property: 0x" + tenToHex(557857707) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857707, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCaliperSplintHeightBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954838, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCaliperSplintHeightBackLeft property: 0x");
            sb.append(tenToHex(559954838));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCaliperSplintHeightBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954839, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCaliperSplintHeightBackRight property: 0x");
            sb.append(tenToHex(559954839));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCaliperSplintHeightCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954837, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCaliperSplintHeightCopilot property: 0x");
            sb.append(tenToHex(559954837));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCaliperSplintHeightDriver() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954836, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCaliperSplintHeightDriver property: 0x");
            sb.append(tenToHex(559954836));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCushionHeightBackLeft() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954834, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCushionHeightBackLeft property: 0x");
            sb.append(tenToHex(559954834));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCushionHeightBackRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954835, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCushionHeightBackRight property: 0x");
            sb.append(tenToHex(559954835));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCushionHeightCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954833, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCushionHeightCopilot property: 0x");
            sb.append(tenToHex(559954833));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public float getTurnCushionHeightDriver() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559954832, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnCushionHeightDriver property: 0x");
            sb.append(tenToHex(559954832));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setresetHudState(int value) throws CarNotConnectedException {
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getresetMotorPretensionerSeatbelt() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857609, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getresetMotorPretensionerSeatbelt property: 0x");
            sb.append(tenToHex(557857609));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setresetSeatParamsBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setresetSeatParamsBackLeft property: 0x" + tenToHex(557857678) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857678, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setresetSeatParamsBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setresetSeatParamsBackRight property: 0x" + tenToHex(557857679) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857679, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setresetSeatParamsCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setresetSeatParamsCopilot property: 0x" + tenToHex(557857677) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857677, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setresetSeatParamsDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setresetSeatParamsDriver property: 0x" + tenToHex(557857676) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857676, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setsaveSeatParamsAllBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setsaveSeatParamsAllBackLeft property: 0x" + tenToHex(557857656) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857656, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setsaveSeatParamsAllBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setsaveSeatParamsAllBackRight property: 0x" + tenToHex(557857657) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857657, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setsaveSeatParamsAllCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setsaveSeatParamsAllCopilot property: 0x" + tenToHex(557857655) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857655, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setsaveSeatParamsAllDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setsaveSeatParamsAllDriver property: 0x" + tenToHex(557857654) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857654, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setsendOffReminder(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setsendOffReminder property: 0x" + tenToHex(557857754) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857754, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnHeadrestTurnHeightBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnHeadrestTurnHeightBackLeft property: 0x" + tenToHex(557857630) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857630, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnHeadrestTurnHeightBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnHeadrestTurnHeightBackRight property: 0x" + tenToHex(557857631) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857631, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnHeadrestTurnHeightCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnHeadrestTurnHeightCopilot property: 0x" + tenToHex(557857629) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857629, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnHeadrestTurnHeightDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnHeadrestTurnHeightDriver property: 0x" + tenToHex(557857628) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857628, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnRearviewMirror(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnRearviewMirror property: 0x" + tenToHex(557857652) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857652, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintBackLeft property: 0x" + tenToHex(557857634) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857634, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintBackRight property: 0x" + tenToHex(557857635) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857635, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintCopilot property: 0x" + tenToHex(557857633) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857633, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCaliperSplintDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCaliperSplintDriver property: 0x" + tenToHex(557857632) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857632, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionBackLeft property: 0x" + tenToHex(557857638) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857638, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionBackRight property: 0x" + tenToHex(557857639) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857639, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionCopilot property: 0x" + tenToHex(557857637) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857637, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatCushionDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatCushionDriver property: 0x" + tenToHex(557857636) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857636, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHeightBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHeightBackLeft property: 0x" + tenToHex(557857646) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857646, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHeightBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHeightBackRight property: 0x" + tenToHex(557857647) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857647, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHeightCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHeightCopilot property: 0x" + tenToHex(557857645) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857645, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHeightDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHeightDriver property: 0x" + tenToHex(557857644) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857644, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationBackLeft property: 0x" + tenToHex(557857650) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857650, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationBackRight property: 0x" + tenToHex(557857651) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857651, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int testBlankPlace() {
        return 0;
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationCopilot(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationCopilot property: 0x" + tenToHex(557857649) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857649, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatHorizationDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatHorizationDriver property: 0x" + tenToHex(557857648) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857648, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatbackrestBackLeft(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatbackrestBackLeft property: 0x" + tenToHex(557857642) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857642, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatbackrestBackRight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatbackrestBackRight property: 0x" + tenToHex(557857643) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857643, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSeatbackrestDriver(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSeatbackrestDriver property: 0x" + tenToHex(557857640) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857640, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setturnSteeringWheel(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setturnSteeringWheel property: 0x" + tenToHex(557857653) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857653, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingLevelBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857912, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingLevelBackLeftR property: 0x");
            sb.append(tenToHex(557857912));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingLevelBackLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingLevelBackLeftS property: 0x" + tenToHex(557857916) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857916, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingLevelBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857913, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingLevelBackRightR property: 0x");
            sb.append(tenToHex(557857913));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingLevelBackRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingLevelBackRightS property: 0x" + tenToHex(557857917) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857917, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingLevelDomainR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857910, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingLevelDomainR property: 0x");
            sb.append(tenToHex(557857910));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingLevelDomainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingLevelDomainS property: 0x" + tenToHex(557857914) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857914, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingLevelPassengerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857911, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingLevelPassengerR property: 0x");
            sb.append(tenToHex(557857911));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingLevelPassengerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingLevelPassengerS property: 0x" + tenToHex(557857915) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857915, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingModeBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857896, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingModeBackLeftR property: 0x");
            sb.append(tenToHex(557857896));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingModeBackLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingModeBackLeftS property: 0x" + tenToHex(557857900) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857900, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingModeBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857897, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingModeBackRightR property: 0x");
            sb.append(tenToHex(557857897));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingModeBackRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingModeBackRightS property: 0x" + tenToHex(557857901) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857901, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingModeDomainR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857894, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingModeDomainR property: 0x");
            sb.append(tenToHex(557857894));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingModeDomainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingModeDomainS property: 0x" + tenToHex(557857898) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857898, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingModePassengerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857895, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingModePassengerR property: 0x");
            sb.append(tenToHex(557857895));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingModePassengerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingModePassengerS property: 0x" + tenToHex(557857899) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857899, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingStateBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857880, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingStateBackLeftR property: 0x");
            sb.append(tenToHex(557857880));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingStateBackLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingStateBackLeftS property: 0x" + tenToHex(557857884) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857884, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingStateBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857881, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingStateBackRightR property: 0x");
            sb.append(tenToHex(557857881));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingStateBackRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingStateBackRightS property: 0x" + tenToHex(557857885) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857885, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingStateDomainR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857878, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingStateDomainR property: 0x");
            sb.append(tenToHex(557857878));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingStateDomainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingStateDomainS property: 0x" + tenToHex(557857882) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857882, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatHeatingStatePassengerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857879, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatHeatingStatePassengerR property: 0x");
            sb.append(tenToHex(557857879));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatHeatingStatePassengerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatHeatingStatePassengerS property: 0x" + tenToHex(557857883) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857883, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingLevelBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857904, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingLevelBackLeftR property: 0x");
            sb.append(tenToHex(557857904));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingLevelBackLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingLevelBackLeftS property: 0x" + tenToHex(557857908) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857908, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingLevelBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857905, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingLevelBackRightR property: 0x");
            sb.append(tenToHex(557857905));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingLevelBackRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingLevelBackRightS property: 0x" + tenToHex(557857909) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857909, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingLevelDomainR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857902, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingLevelDomainR property: 0x");
            sb.append(tenToHex(557857902));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingLevelDomainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingLevelDomainS property: 0x" + tenToHex(557857906) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857906, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingLevelPassengerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857903, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingLevelPassengerR property: 0x");
            sb.append(tenToHex(557857903));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingLevelPassengerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingLevelPassengerS property: 0x" + tenToHex(557857907) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857907, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingModeBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857888, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingModeBackLeftR property: 0x");
            sb.append(tenToHex(557857888));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingModeBackLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingModeBackLeftS property: 0x" + tenToHex(557857892) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857892, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingModeBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857889, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingModeBackRightR property: 0x");
            sb.append(tenToHex(557857889));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingModeBackRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingModeBackRightS property: 0x" + tenToHex(557857893) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857893, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingModeDomainR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857886, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingModeDomainR property: 0x");
            sb.append(tenToHex(557857886));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingModeDomainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingModeDomainS property: 0x" + tenToHex(557857890) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857890, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingModePassengerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857887, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingModePassengerR property: 0x");
            sb.append(tenToHex(557857887));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingModePassengerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingModePassengerS property: 0x" + tenToHex(557857891) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857891, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingStateBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857872, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingStateBackLeftR property: 0x");
            sb.append(tenToHex(557857872));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingStateBackLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingStateBackLeftS property: 0x" + tenToHex(557857876) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857876, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingStateBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857873, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingStateBackRightR property: 0x");
            sb.append(tenToHex(557857873));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingStateBackRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingStateBackRightS property: 0x" + tenToHex(557857877) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857877, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingStateDomainR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857870, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingStateDomainR property: 0x");
            sb.append(tenToHex(557857870));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingStateDomainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingStateDomainS property: 0x" + tenToHex(557857874) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857874, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCallStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCallStateS value = " + value + " property: 0x" + tenToHex(557858430));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858430, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getDriverSeatAutoReturnB3R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858429, 0);
            Log.debug(CarVendorSettingManager.TAG, "getDriverSeatAutoReturnB3R value = " + value + " property: 0x" + tenToHex(557858429));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdTailBoxTakeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858389, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFdTailBoxTakeStateR value = " + value + " property: 0x" + tenToHex(557858389));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFdTailBoxTakeSupportedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858397, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFdTailBoxTakeSupportedR value = " + value + " property: 0x" + tenToHex(557858397));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int get5LevelSeatVentilatingStatePassengerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857871, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("get5LevelSeatVentilatingStatePassengerR property: 0x");
            sb.append(tenToHex(557857871));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void set5LevelSeatVentilatingStatePassengerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "set5LevelSeatVentilatingStatePassengerS property: 0x" + tenToHex(557857875) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857875, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAutoBackDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAutoBackDefaultReset property: 0x" + tenToHex(557858314) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858314, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBackDoorElectricModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBackDoorElectricModeS property: 0x" + tenToHex(557857861) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857861, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setBackLightDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setBackLightDefaultReset property: 0x" + tenToHex(557858318) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858318, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCampingS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCampingS property: 0x" + tenToHex(557858304) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858304, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCarHealthDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCarHealthDefaultReset property: 0x" + tenToHex(557858315) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858315, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCarSettingsDefaultResetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCarSettingsDefaultResetS property: 0x" + tenToHex(557858309) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858309, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCarSettingsResetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCarSettingsResetS property: 0x" + tenToHex(557858307) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858307, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setClockDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setClockDefaultReset property: 0x" + tenToHex(557858324) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858324, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDoorsAndWindowDefaultRessetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDoorsAndWindowDefaultRessetS property: 0x" + tenToHex(557858311) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858311, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDriveSettingsResetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDriveSettingsResetS property: 0x" + tenToHex(557858308) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858308, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getEcuMassageLevelBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857922, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEcuMassageLevelBackLeftR property: 0x");
            sb.append(tenToHex(557857922));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getEcuMassageLevelBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857923, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEcuMassageLevelBackRightR property: 0x");
            sb.append(tenToHex(557857923));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getEcuMassageModeBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857928, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEcuMassageModeBackLeftR property: 0x");
            sb.append(tenToHex(557857928));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getEcuMassageModeBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857929, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEcuMassageModeBackRightR property: 0x");
            sb.append(tenToHex(557857929));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setEqualizerDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setEqualizerDefaultReset property: 0x" + tenToHex(557858322) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858322, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHevChargingPortR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857944, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHevChargingPortR property: 0x");
            sb.append(tenToHex(557857944));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHsMassageStateBackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859104, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHsMassageStateBackLeftR property: 0x");
            sb.append(tenToHex(557859104));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHsMassageStateBackLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHsMassageStateBackLeftS property: 0x" + tenToHex(557857936) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857936, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHsMassageStateBackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859105, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHsMassageStateBackRightR property: 0x");
            sb.append(tenToHex(557859105));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHsMassageStateBackRightS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHsMassageStateBackRightS property: 0x" + tenToHex(557857937) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857937, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHsMassageStateDomainR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857938, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHsMassageStateDomainR property: 0x");
            sb.append(tenToHex(557857938));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHsMassageStateDomainS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHsMassageStateDomainS property: 0x" + tenToHex(557857934) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857934, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHsMassageStatePassengerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859103, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHsMassageStatePassengerR property: 0x");
            sb.append(tenToHex(557859103));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHsMassageStatePassengerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHsMassageStatePassengerS property: 0x" + tenToHex(557857935) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857935, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudNavigationStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857857, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudNavigationStateR property: 0x");
            sb.append(tenToHex(557857857));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudNavigationStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudNavigationStateS property: 0x" + tenToHex(557857858) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857858, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudPatternR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857853, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudPatternR property: 0x");
            sb.append(tenToHex(557857853));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudPatternS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudPatternS property: 0x" + tenToHex(557857854) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857854, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudSafeDriveStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857855, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudSafeDriveStateR property: 0x");
            sb.append(tenToHex(557857855));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudSafeDriveStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudSafeDriveStateS property: 0x" + tenToHex(557857856) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857856, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getHudScreenProjectionStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857859, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudScreenProjectionStateR property: 0x");
            sb.append(tenToHex(557857859));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setHudScreenProjectionStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setHudScreenProjectionStateS property: 0x" + tenToHex(557857860) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857860, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIalDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIalDefaultReset property: 0x" + tenToHex(557858316) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858316, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIntelligentDriveDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIntelligentDriveDefaultReset property: 0x" + tenToHex(557858320) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858320, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getKeyVibratorLevelR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857945, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getKeyVibratorLevelR property: 0x");
            sb.append(tenToHex(557857945));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setKeyVibratorLevelS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setKeyVibratorLevelS property: 0x" + tenToHex(557857946) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857946, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLaneAssistDriveDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLaneAssistDriveDefaultReset property: 0x" + tenToHex(557858321) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858321, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLanguageDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLanguageDefaultReset property: 0x" + tenToHex(557858325) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858325, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLightDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLightDefaultReset property: 0x" + tenToHex(557856042) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856042, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setLocksDefaultResetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setLocksDefaultResetS property: 0x" + tenToHex(557858312) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858312, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMainHsMassageStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859101, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainHsMassageStateR property: 0x");
            sb.append(tenToHex(557859101));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setMeditationModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setMeditationModeS property: 0x" + tenToHex(CarVendorSettingManager.ID_MEDITATION_MODE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_MEDITATION_MODE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setModeS property: 0x" + tenToHex(557857947) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857947, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setNewEnergyDefaultResetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setNewEnergyDefaultResetS property: 0x" + tenToHex(557858310) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858310, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getOnSafeBeltWarnStateChanged() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858329, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOnSafeBeltWarnStateChanged property: 0x");
            sb.append(tenToHex(557858329));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setOutChargeDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setOutChargeDefaultReset property: 0x" + tenToHex(557858327) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858327, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setPetModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setPetModeS property: 0x" + tenToHex(557857949) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857949, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRearMirrorDefaultResetS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRearMirrorDefaultResetS property: 0x" + tenToHex(557858313) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858313, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRestModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRestModeS property: 0x" + tenToHex(557857948) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857948, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRuningCloseDoorStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857862, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRuningCloseDoorStateR property: 0x");
            sb.append(tenToHex(557857862));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setRuningCloseDoorStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setRuningCloseDoorStateS property: 0x" + tenToHex(557857863) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857863, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSafeAssistDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSafeAssistDefaultReset property: 0x" + tenToHex(557858319) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858319, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSafeBeltWarnStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858305, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafeBeltWarnStateR property: 0x");
            sb.append(tenToHex(557858305));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSafeBeltWarnStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSafeBeltWarnStateS property: 0x" + tenToHex(557858306) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858306, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingNotL1PStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857866, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingNotL1PStateR property: 0x");
            sb.append(tenToHex(557857866));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingState2BackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857864, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingState2BackLeftR property: 0x");
            sb.append(tenToHex(557857864));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatHeatingState2BackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857865, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatHeatingState2BackRightR property: 0x");
            sb.append(tenToHex(557857865));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingNotL1PStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857869, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingNotL1PStateR property: 0x");
            sb.append(tenToHex(557857869));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingState2BackLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857867, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingState2BackLeftR property: 0x");
            sb.append(tenToHex(557857867));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSeatVentilatingState2BackRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857868, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatVentilatingState2BackRightR property: 0x");
            sb.append(tenToHex(557857868));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSetUiVersion(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSetUiVersion property: 0x" + tenToHex(557858328) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858328, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSoundFiledDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSoundFiledDefaultReset property: 0x" + tenToHex(557858323) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858323, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSteeringWheelHeatingAutoStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857921, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelHeatingAutoStateR property: 0x");
            sb.append(tenToHex(557857921));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSteeringWheelHeatingAutoStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSteeringWheelHeatingAutoStateS property: 0x" + tenToHex(557857920) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857920, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getSteeringWheelHeatingGearR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857919, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelHeatingGearR property: 0x");
            sb.append(tenToHex(557857919));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getMemorySetOperationFlagR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858527, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMemorySetOperationFlagR property: 0x");
            sb.append(tenToHex(557858527));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSettingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSteeringWheelHeatingGearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSteeringWheelHeatingGearS property: 0x" + tenToHex(557857918) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857918, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setUnitDefaultReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setUnitDefaultReset property: 0x" + tenToHex(557858326) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858326, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setWipeModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setWipeModeS property: 0x" + tenToHex(557857950) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857950, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setFactoryResetAll(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setFactoryResetAll property: 0x" + tenToHex(557857573) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857573, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setSysFactoryReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setSysFactoryReset property: 0x" + tenToHex(557903888) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557903888, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setIviProfileSettingsR(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setIviProfileSettingsR value = " + value + " property: 0x" + tenToHex(557857947), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857947, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setAdasRestS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setAdasRestS property: 0x" + tenToHex(CarVendorSettingManager.ID_ADAS_REST_S) + " class: int[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorSettingManager.ID_ADAS_REST_S, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setCarsettingRestS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setCarsettingRestS property: 0x" + tenToHex(CarVendorSettingManager.ID_CARSETTING_REST_S) + " class: int[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorSettingManager.ID_CARSETTING_REST_S, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDlinkRestS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDlinkRestS property: 0x" + tenToHex(CarVendorSettingManager.ID_DLINK_REST_S) + " class: int[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorSettingManager.ID_DLINK_REST_S, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setDriverSeatStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setDriverSeatStateS property: 0x" + tenToHex(CarVendorSettingManager.ID_DRIVER_SEAR_STATE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_DRIVER_SEAR_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getFrontWiperSpeedValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858528, 0);
            Log.debug(CarVendorSettingManager.TAG, "getFrontWiperSpeedValueR value = " + value + " property: 0x" + tenToHex(557858528));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getTrailerModeMileageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858529, 0);
            Log.debug(CarVendorSettingManager.TAG, "getTrailerModeMileageR value = " + value + " property: 0x" + tenToHex(557858529));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public int getRearWiperSpeedValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858530, 0);
            Log.debug(CarVendorSettingManager.TAG, "getRearWiperSpeedValueR value = " + value + " property: 0x" + tenToHex(557858530));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setInsideLightStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setInsideLightStateS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_INSIDE_LIGHT_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_INSIDE_LIGHT_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void setOutsideLightStateSceneS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorSettingManager.TAG, "setOutsideLightStateSceneS value = " + value + " property: 0x" + tenToHex(CarVendorSettingManager.ID_OUTSIDE_LIGHT_STATE_SCENE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorSettingManager.ID_OUTSIDE_LIGHT_STATE_SCENE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void addCarSettingEventListener(CarVendorSettingManager.OnCarSettingEventListener listener) throws CarNotConnectedException {
        OnCarSettingChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarSettingChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorSettingManager.TAG, "addCarSettingEventListener propId size: " + this.mExtSettingIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtSettingIds) {
                int propId = num.intValue();
                Log.debug(CarVendorSettingManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.setting.CarVendorSettingManager
    public void removeCarSettingEventListener(CarVendorSettingManager.OnCarSettingEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarSettingChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorSettingManager.TAG, "removeCarSettingEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SettingEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        value.getAreaId();
        sSettingEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorSettingManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557846608:
                sSettingEventChangeValue.setAutoPurificationState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557847553:
                sSettingEventChangeValue.setGoalRoadModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557847591:
                sSettingEventChangeValue.setGoalRoadModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856042:
                sSettingEventChangeValue.setLightDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856058:
                sSettingEventChangeValue.setMotorPretensionerSeatbeltStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856063:
                sSettingEventChangeValue.setRemoteKeyStartAcTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856073:
                sSettingEventChangeValue.setRearAcPanelAutoLockTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856076:
                sSettingEventChangeValue.setAmbientAreaSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856146:
                sSettingEventChangeValue.setSteerPositionAutoReturnR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857025:
                sSettingEventChangeValue.setINSThemeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857546:
                sSettingEventChangeValue.setAutoPurification(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857548:
                sSettingEventChangeValue.setRemoteKeyStartAcTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857549:
                sSettingEventChangeValue.setPM25PowerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857550:
                sSettingEventChangeValue.setPM25PowerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857551:
                sSettingEventChangeValue.setPM25SwitchCheckR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857552:
                sSettingEventChangeValue.setPM25SwitchCheckS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857553:
                sSettingEventChangeValue.setPM25TimeCheckR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857554:
                sSettingEventChangeValue.setPM25TimeCheckS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857555:
                sSettingEventChangeValue.setACTunnelCycleR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857556:
                sSettingEventChangeValue.setACTunnelCycleS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857557:
                sSettingEventChangeValue.setAcWarmModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857558:
                sSettingEventChangeValue.setAcWarmModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857559:
                sSettingEventChangeValue.setIntelligentAcAreaStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857560:
                sSettingEventChangeValue.setIntelligentAcAreaStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857561:
                sSettingEventChangeValue.setBackDoorOpenedHeightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857562:
                sSettingEventChangeValue.setBackDoorOpenedHeightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857563:
                sSettingEventChangeValue.setRainAutoLockWindowStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857564:
                sSettingEventChangeValue.setRainAutoLockWindowStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857565:
                sSettingEventChangeValue.setDoorknobAutoShrinkStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857566:
                sSettingEventChangeValue.setDoorknobAutoShrinkStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857567:
                sSettingEventChangeValue.setIntelligentUnlockStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857568:
                sSettingEventChangeValue.setIntelligentUnlockStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857569:
                sSettingEventChangeValue.setIntelligentLockStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857570:
                sSettingEventChangeValue.setIntelligentLockStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857571:
                sSettingEventChangeValue.setHudStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857572:
                sSettingEventChangeValue.setHudStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857573:
                sSettingEventChangeValue.setFactoryResetAll(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857574:
                sSettingEventChangeValue.setHudHeightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857575:
                sSettingEventChangeValue.setHudHeightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857576:
                sSettingEventChangeValue.setHudBrightnessR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857577:
                sSettingEventChangeValue.setHudBrightnessS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857578:
                sSettingEventChangeValue.setHudRotationAngleR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857579:
                sSettingEventChangeValue.setHudRotationAngleS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857580:
                sSettingEventChangeValue.setHudModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857581:
                sSettingEventChangeValue.setHudModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857582:
                sSettingEventChangeValue.setHudDisplayContentRDrive(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857583:
                sSettingEventChangeValue.setHudDisplayContentRNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857584:
                sSettingEventChangeValue.setHudDisplayContentSDrive(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857585:
                sSettingEventChangeValue.setHudDisplayContentSNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857586:
                sSettingEventChangeValue.setHudModeParams(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857587:
                sSettingEventChangeValue.setEngineSoundTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857588:
                sSettingEventChangeValue.setEngineSoundTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857589:
                sSettingEventChangeValue.setSpeedReminderValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857590:
                sSettingEventChangeValue.setSpeedReminderValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857591:
                sSettingEventChangeValue.setRearViewMirrorFlipR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857592:
                sSettingEventChangeValue.setRearViewMirrorFlipSetOneR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857593:
                sSettingEventChangeValue.setRearViewMirrorFlipS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857594:
                sSettingEventChangeValue.setRearViewMirrorFlipSetTwoR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857595:
                sSettingEventChangeValue.setAutoPowerOnStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857596:
                sSettingEventChangeValue.setAutoPowerOnStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857597:
                sSettingEventChangeValue.setAutoPowerOffStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857598:
                sSettingEventChangeValue.setAutoPowerOffStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857599:
                sSettingEventChangeValue.setDriverSeatAutoReturnR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857600:
                sSettingEventChangeValue.setDriverSeatAutoReturnS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857699:
                sSettingEventChangeValue.setRefrigeratorModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857700:
                sSettingEventChangeValue.setRefrigeratorModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857701:
                sSettingEventChangeValue.setRemenberPowerOffStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857702:
                sSettingEventChangeValue.setRemenberPowerOffStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857703:
                sSettingEventChangeValue.setRefrigeratorDoorState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857704:
                sSettingEventChangeValue.setDisinfStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857705:
                sSettingEventChangeValue.setDisinfStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857706:
                sSettingEventChangeValue.setTrayStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857707:
                sSettingEventChangeValue.setTrayStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857708:
                sSettingEventChangeValue.setRefrigeratorState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857709:
                sSettingEventChangeValue.setTrayInitState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857710:
                sSettingEventChangeValue.setIALColorStarRingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857711:
                sSettingEventChangeValue.setIALColorStarrySkyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857712:
                sSettingEventChangeValue.setIALColorFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857713:
                sSettingEventChangeValue.setIALColorBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857714:
                sSettingEventChangeValue.setIALColorS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857715:
                sSettingEventChangeValue.setIALColorAdjustS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857716:
                sSettingEventChangeValue.setIALBrightnessStarRingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857717:
                sSettingEventChangeValue.setIALBrightnessStarrySkyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857718:
                sSettingEventChangeValue.setIALBrightnessBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857719:
                sSettingEventChangeValue.setIALBrightnessFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857720:
                sSettingEventChangeValue.setIALBrightnessS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857721:
                sSettingEventChangeValue.setIALAreaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857722:
                sSettingEventChangeValue.setIALAreaDifferentWays(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857723:
                sSettingEventChangeValue.setSeatHeatingStateDriverDomainControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857724:
                sSettingEventChangeValue.setSeatHeatingStateDriverECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857725:
                sSettingEventChangeValue.setSeatHeatingStateCopilotECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857726:
                sSettingEventChangeValue.setSeatHeatingStateBackLeftECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857727:
                sSettingEventChangeValue.setSeatHeatingStateBackRightECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857728:
                sSettingEventChangeValue.setSeatHeatingStateCopilotL1P(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857729:
                sSettingEventChangeValue.setSeatHeatingStateCopilotNonL1P(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857730:
                sSettingEventChangeValue.setSeatHeatingStateBackLeftDomainControlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857731:
                sSettingEventChangeValue.setSeatHeatingStateBackRightDomainControlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857732:
                sSettingEventChangeValue.setSeatHeatingStateCopilotDomainControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857733:
                sSettingEventChangeValue.setSeatVentilatingStateDriverDomainControlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857734:
                sSettingEventChangeValue.setSeatVentilatingStateDriverECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857735:
                sSettingEventChangeValue.setSeatVentilatingStateCopilotECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857736:
                sSettingEventChangeValue.setSeatVentilatingStateBackLeftECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857737:
                sSettingEventChangeValue.setSeatVentilatingStateBackRightECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857738:
                sSettingEventChangeValue.setSeatVentilatingStateCopilotL1P(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857739:
                sSettingEventChangeValue.setSeatVentilatingStateCopilotNonL1P(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857740:
                sSettingEventChangeValue.setSeatVentilatingStateBackLeftDomainControlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857741:
                sSettingEventChangeValue.setSeatVentilatingStateBackRightDomainControlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857742:
                sSettingEventChangeValue.setSeatVentilatingStateCopilotDomainControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857743:
                sSettingEventChangeValue.setLanternLanguageModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857744:
                sSettingEventChangeValue.setLanternLanguageModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857745:
                sSettingEventChangeValue.setINSThemeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857747:
                sSettingEventChangeValue.setHeadlampLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857748:
                sSettingEventChangeValue.setHeadlampLevelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857749:
                sSettingEventChangeValue.setLeftHeadlampLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857750:
                sSettingEventChangeValue.setRightHeadlampLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857751:
                sSettingEventChangeValue.setHeadLampConfig(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857752:
                sSettingEventChangeValue.setSmartWelcomeLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857753:
                sSettingEventChangeValue.setSmartWelcomeLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857754:
                sSettingEventChangeValue.setsendOffReminder(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857755:
                sSettingEventChangeValue.setOffReminder(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857757:
                sSettingEventChangeValue.setRearAcPanelAutoLockTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857759:
                sSettingEventChangeValue.setExhibitionStartEnginePermitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857760:
                sSettingEventChangeValue.setExhibitionStartEnginePermitS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857761:
                sSettingEventChangeValue.setSteeringWheelHeatingStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857762:
                sSettingEventChangeValue.setInsideLightDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857763:
                sSettingEventChangeValue.setInsideLightDoorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857764:
                sSettingEventChangeValue.setMassageLevelLeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857765:
                sSettingEventChangeValue.setMassageLevelLeftFrontECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857766:
                sSettingEventChangeValue.setMassageLevelRightFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857767:
                sSettingEventChangeValue.setMassageLevelRightFrontECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857768:
                sSettingEventChangeValue.setMassageLevelLeftBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857769:
                sSettingEventChangeValue.setMassageLevelLeftBackECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857770:
                sSettingEventChangeValue.setMassageLevelRightBackECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857771:
                sSettingEventChangeValue.setMassageLevelRightBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857772:
                sSettingEventChangeValue.setMassageLevel4C1(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857773:
                sSettingEventChangeValue.setMassageLevel4C2(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857774:
                sSettingEventChangeValue.setMassageLevel4C3(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857775:
                sSettingEventChangeValue.setMassageLevel4C4(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857776:
                sSettingEventChangeValue.setMassageModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857777:
                sSettingEventChangeValue.setMassageModeR437(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857778:
                sSettingEventChangeValue.setMassageModeR4FA(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857779:
                sSettingEventChangeValue.setMassageModeR3CA(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857780:
                sSettingEventChangeValue.setMassageModeR4FALeftBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857781:
                sSettingEventChangeValue.setMassageModeR402(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857782:
                sSettingEventChangeValue.setMassageModeR42F(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857783:
                sSettingEventChangeValue.setMassageModeR4FALeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857784:
                sSettingEventChangeValue.setMassageModeS4C1(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857785:
                sSettingEventChangeValue.setMassageModeS4C2(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857786:
                sSettingEventChangeValue.setMassageModeS4C3(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857787:
                sSettingEventChangeValue.setMassageModeS4C4(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857788:
                sSettingEventChangeValue.setEnergyFeedbackR341(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857789:
                sSettingEventChangeValue.setEnergyFeedbackS4E3(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857790:
                sSettingEventChangeValue.setSOCTargetR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857791:
                sSettingEventChangeValue.setSOCTargetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857792:
                sSettingEventChangeValue.setChargingPortR27C(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857793:
                sSettingEventChangeValue.setChargingPortR475(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857794:
                sSettingEventChangeValue.setChargingPortS4E3(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857795:
                sSettingEventChangeValue.setAutoExternalRearMirrorFollowUpSwitchS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857796:
                sSettingEventChangeValue.setAutoExternalRearMirrorFollowUpSwitchR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857797:
                sSettingEventChangeValue.setLockOffS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857798:
                sSettingEventChangeValue.setOverspeedLockS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857799:
                sSettingEventChangeValue.setOverspeedLockR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857800:
                sSettingEventChangeValue.setSteerAssisR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857801:
                sSettingEventChangeValue.setSteerAssisS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857802:
                sSettingEventChangeValue.setSteerAssisSupported(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857803:
                sSettingEventChangeValue.setLeaveCarPInd(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857804:
                sSettingEventChangeValue.setonTrayStateChanged(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857805:
                sSettingEventChangeValue.setonSaveSeatParamsAllChangedPlace5(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857806:
                sSettingEventChangeValue.setonSaveSeatParamsAllChangedPlace7(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857807:
                sSettingEventChangeValue.setonSaveSeatParamsAllChanged3CA(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857808:
                sSettingEventChangeValue.setonSaveSeatParamsAllChanged402(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857809:
                sSettingEventChangeValue.setonSaveSeatParamsAllChanged42F(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857810:
                sSettingEventChangeValue.setonSeatHeatingStateChanged437(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857811:
                sSettingEventChangeValue.setonSmartWelcomeLightStateChanged(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857812:
                sSettingEventChangeValue.setturnSeatHeightPercentCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857813:
                sSettingEventChangeValue.setSteeringWheelHeatingStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857814:
                sSettingEventChangeValue.setLockOffR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857815:
                sSettingEventChangeValue.setVoiceCtlBackDoor(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857816:
                sSettingEventChangeValue.setTurnSeatbackrestPercentDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857817:
                sSettingEventChangeValue.setTurnSeatbackrestPercentPassenger(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857818:
                sSettingEventChangeValue.setturnSeatHorizationPercentDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857819:
                sSettingEventChangeValue.setturnSeatHorizationPercentPassenger(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857820:
                sSettingEventChangeValue.setturnSeatHeightPercentDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857821:
                sSettingEventChangeValue.setturnHeadrestHorizationPercentDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857822:
                sSettingEventChangeValue.setturnHeadrestHeightPercentDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857823:
                sSettingEventChangeValue.setturnCaliperSplintPercentDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857824:
                sSettingEventChangeValue.setturnCaliperSplintPercentPassenger(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857825:
                sSettingEventChangeValue.setturnCushionPercentDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857826:
                sSettingEventChangeValue.setSeatHeatingStateDriverDomainControlS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857827:
                sSettingEventChangeValue.setSeatHeatingStateBackLeftDomainControlS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857828:
                sSettingEventChangeValue.setSeatHeatingStateBackRightDomainControlS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857829:
                sSettingEventChangeValue.setSeatVentilatingStateDriverDomainControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857830:
                sSettingEventChangeValue.setSeatVentilatingStateBackLeftDomainControlS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857831:
                sSettingEventChangeValue.setSeatVentilatingStateBackRightDomainControlS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857832:
                sSettingEventChangeValue.setComSpeedUpStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857833:
                sSettingEventChangeValue.setComSpeedDownStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_COM_SPEED_DOWN_STATES /* 557857834 */:
                sSettingEventChangeValue.setComSpeedDownStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857835:
                sSettingEventChangeValue.setHSpeedAutoCloseStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_H_SPEED_AUTO_CLOSE_STATES /* 557857836 */:
                sSettingEventChangeValue.setHSpeedAutoCloseStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_VIEW_MIRROR_FLIP_SETS /* 557857837 */:
                sSettingEventChangeValue.setRearViewMirrorFlipSetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857838:
                sSettingEventChangeValue.setComSpeedUpStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857839:
                sSettingEventChangeValue.setRearSeatsFoldOrExpand(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857840:
                sSettingEventChangeValue.setPassengerStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857841:
                sSettingEventChangeValue.setPassengerRearLeftStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857842:
                sSettingEventChangeValue.setPassengerRearRightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857843:
                sSettingEventChangeValue.setPassengerRearMiddleStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857844:
                sSettingEventChangeValue.setPassengerLastLeftStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857845:
                sSettingEventChangeValue.setPassengerLastRightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857846:
                sSettingEventChangeValue.setPassengerLastMiddleStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857847:
                sSettingEventChangeValue.setRecoverOrSaveParamsChanged2(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857848:
                sSettingEventChangeValue.setRecoverOrSaveParamsChanged3(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857849:
                sSettingEventChangeValue.setRecoverOrSaveParamsChanged4(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857850:
                sSettingEventChangeValue.setRecoverOrSaveParamsChanged5(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857851:
                sSettingEventChangeValue.setRecoverOrSaveParamsChanged6(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857852:
                sSettingEventChangeValue.setRecoverOrSaveParamsChanged7(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857853:
                sSettingEventChangeValue.setHudPatternR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857854:
                sSettingEventChangeValue.setHudPatternS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857855:
                sSettingEventChangeValue.setHudSafeDriveStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857856:
                sSettingEventChangeValue.setHudSafeDriveStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857857:
                sSettingEventChangeValue.setHudNavigationStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857858:
                sSettingEventChangeValue.setHudNavigationStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857859:
                sSettingEventChangeValue.setHudScreenProjectionStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857860:
                sSettingEventChangeValue.setHudScreenProjectionStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857861:
                sSettingEventChangeValue.setBackDoorElectricModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857862:
                sSettingEventChangeValue.setRuningCloseDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857863:
                sSettingEventChangeValue.setRuningCloseDoorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857864:
                sSettingEventChangeValue.setSeatHeatingState2BackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857865:
                sSettingEventChangeValue.setSeatHeatingState2BackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857866:
                sSettingEventChangeValue.setSeatHeatingNotL1PStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857867:
                sSettingEventChangeValue.setSeatVentilatingState2BackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857868:
                sSettingEventChangeValue.setSeatVentilatingState2BackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857869:
                sSettingEventChangeValue.setSeatVentilatingNotL1PStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857870:
                sSettingEventChangeValue.set5LevelSeatVentilatingStateDomainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857871:
                sSettingEventChangeValue.set5LevelSeatVentilatingStatePassengerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857872:
                sSettingEventChangeValue.set5LevelSeatVentilatingStateBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857873:
                sSettingEventChangeValue.set5LevelSeatVentilatingStateBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857874:
                sSettingEventChangeValue.set5LevelSeatVentilatingStateDomainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857875:
                sSettingEventChangeValue.set5LevelSeatVentilatingStatePassengerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857876:
                sSettingEventChangeValue.set5LevelSeatVentilatingStateBackLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857877:
                sSettingEventChangeValue.set5LevelSeatVentilatingStateBackRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857878:
                sSettingEventChangeValue.set5LevelSeatHeatingStateDomainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857879:
                sSettingEventChangeValue.set5LevelSeatHeatingStatePassengerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857880:
                sSettingEventChangeValue.set5LevelSeatHeatingStateBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857881:
                sSettingEventChangeValue.set5LevelSeatHeatingStateBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857882:
                sSettingEventChangeValue.set5LevelSeatHeatingStateDomainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857883:
                sSettingEventChangeValue.set5LevelSeatHeatingStatePassengerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857884:
                sSettingEventChangeValue.set5LevelSeatHeatingStateBackLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857885:
                sSettingEventChangeValue.set5LevelSeatHeatingStateBackRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857886:
                sSettingEventChangeValue.set5LevelSeatVentilatingModeDomainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857887:
                sSettingEventChangeValue.set5LevelSeatVentilatingModePassengerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857888:
                sSettingEventChangeValue.set5LevelSeatVentilatingModeBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857889:
                sSettingEventChangeValue.set5LevelSeatVentilatingModeBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857890:
                sSettingEventChangeValue.set5LevelSeatVentilatingModeDomainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857891:
                sSettingEventChangeValue.set5LevelSeatVentilatingModePassengerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857892:
                sSettingEventChangeValue.set5LevelSeatVentilatingModeBackLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857893:
                sSettingEventChangeValue.set5LevelSeatVentilatingModeBackRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857894:
                sSettingEventChangeValue.set5LevelSeatHeatingModeDomainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857895:
                sSettingEventChangeValue.set5LevelSeatHeatingModePassengerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857896:
                sSettingEventChangeValue.set5LevelSeatHeatingModeBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857897:
                sSettingEventChangeValue.set5LevelSeatHeatingModeBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857898:
                sSettingEventChangeValue.set5LevelSeatHeatingModeDomainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857899:
                sSettingEventChangeValue.set5LevelSeatHeatingModePassengerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857900:
                sSettingEventChangeValue.set5LevelSeatHeatingModeBackLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857901:
                sSettingEventChangeValue.set5LevelSeatHeatingModeBackRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857902:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelDomainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857903:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelPassengerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857904:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857905:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857906:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelDomainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857907:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelPassengerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857908:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelBackLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857909:
                sSettingEventChangeValue.set5LevelSeatVentilatingLevelBackRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857910:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelDomainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857911:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelPassengerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857912:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857913:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857914:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelDomainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857915:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelPassengerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857916:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelBackLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857917:
                sSettingEventChangeValue.set5LevelSeatHeatingLevelBackRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857918:
                sSettingEventChangeValue.setSteeringWheelHeatingGearS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857919:
                sSettingEventChangeValue.setSteeringWheelHeatingGearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857920:
                sSettingEventChangeValue.setSteeringWheelHeatingAutoStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857921:
                sSettingEventChangeValue.setSteeringWheelHeatingAutoStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857922:
                sSettingEventChangeValue.setEcuMassageLevelBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857923:
                sSettingEventChangeValue.setEcuMassageLevelBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857928:
                sSettingEventChangeValue.setEcuMassageModeBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857929:
                sSettingEventChangeValue.setEcuMassageModeBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857934:
                sSettingEventChangeValue.setHsMassageStateDomainS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857935:
                sSettingEventChangeValue.setHsMassageStatePassengerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857936:
                sSettingEventChangeValue.setHsMassageStateBackLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857937:
                sSettingEventChangeValue.setHsMassageStateBackRightS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857938:
                sSettingEventChangeValue.setHsMassageStateDomainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857944:
                sSettingEventChangeValue.setHevChargingPortR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857945:
                sSettingEventChangeValue.setKeyVibratorLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857946:
                sSettingEventChangeValue.setKeyVibratorLevelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857947:
                sSettingEventChangeValue.setModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857948:
                sSettingEventChangeValue.setRestModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857949:
                sSettingEventChangeValue.setPetModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857950:
                sSettingEventChangeValue.setWipeModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857951:
                sSettingEventChangeValue.setFdComSpeedUpStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_TURNED_STATE_S /* 557857952 */:
                sSettingEventChangeValue.setRearviewMirrorIsTurnedStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_VOICE_OPEN_MIRRORLS_TURNED_STATE /* 557857953 */:
                sSettingEventChangeValue.setvoiceOpenMirrorlsTurnedStatS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_AUTO_LIGHT_STATE_S /* 557857954 */:
                sSettingEventChangeValue.setRearviewMirrorIsAutoLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REARVIEW_MIRROR_IS_SCREEN_STATE_S /* 557857955 */:
                sSettingEventChangeValue.setRearviewMirrorIsScreenStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_REARVIEW_MIRROR_IS /* 557857956 */:
                sSettingEventChangeValue.setturnRearviewMirrorIS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857957:
                sSettingEventChangeValue.setRearviewMirrorIsTurnedStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857958:
                sSettingEventChangeValue.setRearviewMirrorIsStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857959:
                sSettingEventChangeValue.setRearviewMirrorIsAutoLightStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_RESET_REARVIEW_MIRROR_IS_SETS /* 557857960 */:
                sSettingEventChangeValue.setresetRearviewMirrorIsSetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REFRIGERATOR_WORK_MODE_S /* 557857961 */:
                sSettingEventChangeValue.setRefrigeratorWorkModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857962:
                sSettingEventChangeValue.setRefrigeratorWorkModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REFRIGERATOR_WORK_TEMPERATURE_S /* 557857963 */:
                sSettingEventChangeValue.setRefrigeratorWorkTemperatureS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857964:
                sSettingEventChangeValue.setRefrigeratorWorkTemperatureR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857965:
                sSettingEventChangeValue.setFridgeWorkColdTemperatureR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857966:
                sSettingEventChangeValue.setFridgeWorkHeatTemperatureR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857967:
                sSettingEventChangeValue.setFridgeWorkSaveTemperatureR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857968:
                sSettingEventChangeValue.setDelayPowerOffStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_DELAY_POWER_OFF_TIME_S /* 557857969 */:
                sSettingEventChangeValue.setDelayPowerOffTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857970:
                sSettingEventChangeValue.setDelayPowerOffTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_DELAY_POWER_DOWN_TIME_S /* 557857971 */:
                sSettingEventChangeValue.setDelayPowerDownTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857972:
                sSettingEventChangeValue.setDelayPowerDownTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REMBER_POWER_OFF_STATE_S /* 557857973 */:
                sSettingEventChangeValue.setRemberPowerOffStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857974:
                sSettingEventChangeValue.setRemberPowerOffStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_STERILIZATION_STATE_S /* 557857975 */:
                sSettingEventChangeValue.setSterilizationStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857976:
                sSettingEventChangeValue.setSterilizationStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857977:
                sSettingEventChangeValue.setVoiceTipsMessageR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_FRIDGE_DOOR_STATE_S /* 557857978 */:
                sSettingEventChangeValue.setFridgeDoorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857979:
                sSettingEventChangeValue.setFridgeDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857980:
                sSettingEventChangeValue.setRefrigeratorWorkStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857981:
                sSettingEventChangeValue.setRefrigeratorTemperatureR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857982:
                sSettingEventChangeValue.setDelayDownPowerTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857983:
                sSettingEventChangeValue.setSterilizationWorkStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857984:
                sSettingEventChangeValue.setDlgMessageCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857985:
                sSettingEventChangeValue.setFridgeLocationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_WORK_STATE_S /* 557857986 */:
                sSettingEventChangeValue.setWorkStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_WORK_TEMPERATURE_S /* 557857987 */:
                sSettingEventChangeValue.setWorkTemperatureS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_WORK_COMMOND_S /* 557857988 */:
                sSettingEventChangeValue.setWorkCommondS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_WORK_TEMPERATURE_S /* 557857989 */:
                sSettingEventChangeValue.setRearWorkTemperatureS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857990:
                sSettingEventChangeValue.setControlCallbackStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857991:
                sSettingEventChangeValue.setWorkStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857992:
                sSettingEventChangeValue.setDelayPowerOffStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857993:
                sSettingEventChangeValue.setScreenCallbackMessageR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857994:
                sSettingEventChangeValue.setRearDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857995:
                sSettingEventChangeValue.setRearHeatTempR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857996:
                sSettingEventChangeValue.setRearSaveTempR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857997:
                sSettingEventChangeValue.setPhoneRemindCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857998:
                sSettingEventChangeValue.setServiceControlCallbackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_DELAY_POWER_DOWN_STATE_S /* 557857999 */:
                sSettingEventChangeValue.setDelayPowerDownStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_DELAY_POWER_DOWN_STATE_TIME_S /* 557858000 */:
                sSettingEventChangeValue.setDelayPowerDownStateTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_FRIDGE_CHILD_LOCK_S /* 557858001 */:
                sSettingEventChangeValue.setFridgeChildLockS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_E_OWNER_STATE_S /* 557858002 */:
                sSettingEventChangeValue.seteOwnerStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_FRIDGE_WORK_STATE_S /* 557858003 */:
                sSettingEventChangeValue.setRearFridgeWorkStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_FRIDGE_STERILIZER_STATE_S /* 557858004 */:
                sSettingEventChangeValue.setRearFridgeSterilizerStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_FRIDGE_TEMPERATURE_S /* 557858005 */:
                sSettingEventChangeValue.setRearFridgeTemperatureS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_FRIDGE_DELAY_STATE_S /* 557858006 */:
                sSettingEventChangeValue.setRearFridgeDelayStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_FRIDGE_DELAY_TIME_S /* 557858007 */:
                sSettingEventChangeValue.setRearFridgeDelayTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_FRIDGE_DOOR_STATE_S /* 557858008 */:
                sSettingEventChangeValue.setRearFridgeDoorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858010:
                sSettingEventChangeValue.setCanopyAutomaticDimmingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858011:
                sSettingEventChangeValue.setSkyCurtainPowerOffAndDimR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_CANOPY_AUTOMATIC_DIMMING_S /* 557858012 */:
                sSettingEventChangeValue.setCanopyAutomaticDimmingS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_SKY_CURTAIN_POWER_OFF_AND_DIM_S /* 557858013 */:
                sSettingEventChangeValue.setSkyCurtainPowerOffAndDimS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_OPENING_ANGLE_INFORMATION_SET_ON_THE_R_S /* 557858015 */:
                sSettingEventChangeValue.setOpeningAngleInformationSetOnTheRS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858016:
                sSettingEventChangeValue.setCurrentTransmittanceOfSkylightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858017:
                sSettingEventChangeValue.setCurrentStatusOfDimmingSunroofR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858018:
                sSettingEventChangeValue.setDimmingCanopyLowCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_ELECTRIC_TAIL_CONFIGURATION_R /* 557858019 */:
                sSettingEventChangeValue.setElectricTailConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858020:
                sSettingEventChangeValue.setTailInitializationStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858021:
                sSettingEventChangeValue.setCurrentStateOfTheTailStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858022:
                sSettingEventChangeValue.setTailPositionPercentageR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858023:
                sSettingEventChangeValue.setTailMotorThermalProtectionStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858024:
                sSettingEventChangeValue.setTailPositionFeedbackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858025:
                sSettingEventChangeValue.setAlarmPromptStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858026:
                sSettingEventChangeValue.setCurrentModeOfTailStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858027:
                sSettingEventChangeValue.setElectricTailMaintenanceFuntatusStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_ELECTRIC_TAIL_AUTOMATIC_MODE_SWITCH_STATE_S /* 557858028 */:
                sSettingEventChangeValue.setElectricTailAutomaticModeSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_ELECTRIC_TAIL_AUTO_MODE_VOICE_SWITCH_STATE_S /* 557858029 */:
                sSettingEventChangeValue.setElectricTailAutoModeVoiceSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_RIGHT_MASSAGE_SW_CONFIGURATION_R /* 557858030 */:
                sSettingEventChangeValue.setRearRightMassageSwConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_LEFT_MASSAGE_SW_CONFIGURATION_R /* 557858031 */:
                sSettingEventChangeValue.setRearLeftMassageSwConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_R_C_S /* 557858032 */:
                sSettingEventChangeValue.setturnSeatHorizationRearLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_LEFT_R_C_S /* 557858033 */:
                sSettingEventChangeValue.setturnSeatHorizationRearLeftLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_LEFT_RIGHT_R_C_S /* 557858034 */:
                sSettingEventChangeValue.setturnSeatHorizationRearLeftRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_R_C_S /* 557858035 */:
                sSettingEventChangeValue.setturnSeatHorizationRearRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_LEFT_R_C_S /* 557858036 */:
                sSettingEventChangeValue.setturnSeatHorizationRearRightLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_HORIZATION_REAR_RIGHT_RIGHT_R_C_S /* 557858037 */:
                sSettingEventChangeValue.setturnSeatHorizationRearRightRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_R_C_S /* 557858040 */:
                sSettingEventChangeValue.setturnSeatCushionRearLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_LEFT_R_C_S /* 557858041 */:
                sSettingEventChangeValue.setturnSeatCushionRearLeftLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_LEFT_RIGHT_R_C_S /* 557858042 */:
                sSettingEventChangeValue.setturnSeatCushionRearLeftRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_R_C_S /* 557858043 */:
                sSettingEventChangeValue.setturnSeatCushionRearRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_LEFT_R_C_S /* 557858044 */:
                sSettingEventChangeValue.setturnSeatCushionRearRightLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CUSHION_REAR_RIGHT_RIGHT_R_C_S /* 557858045 */:
                sSettingEventChangeValue.setturnSeatCushionRearRightRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_LEFT_R_C_S /* 557858046 */:
                sSettingEventChangeValue.setturnSeatBackRearLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_LEFT_LEFT_R_C_S /* 557858047 */:
                sSettingEventChangeValue.setturnSeatBackRearLeftLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858048:
                sSettingEventChangeValue.setturnSeatBackRearLeftRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858049:
                sSettingEventChangeValue.setturnSeatBackRearRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858050:
                sSettingEventChangeValue.setturnSeatBackRearRightLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_BACK_REAR_RIGHT_RIGHT_R_C_S /* 557858051 */:
                sSettingEventChangeValue.setturnSeatBackRearRightRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_R_C_S /* 557858052 */:
                sSettingEventChangeValue.setturnSeatCaliperSplintRearLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_LEFT_R_C_S /* 557858053 */:
                sSettingEventChangeValue.setturnSeatCaliperSplintRearLeftLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_RIGHT_R_C_S /* 557858054 */:
                sSettingEventChangeValue.setturnSeatCaliperSplintRearLeftRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_R_C_S /* 557858055 */:
                sSettingEventChangeValue.setturnSeatCaliperSplintRearRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_LEFT_R_C_S /* 557858056 */:
                sSettingEventChangeValue.setturnSeatCaliperSplintRearRightLeftRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_RIGHT_R_C_S /* 557858057 */:
                sSettingEventChangeValue.setturnSeatCaliperSplintRearRightRightRcS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858058:
                sSettingEventChangeValue.setLeftElectricPedalR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858060:
                sSettingEventChangeValue.setRightElectricPedalR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858063:
                sSettingEventChangeValue.setFridgeChildLockR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_ICE_BOX_TRIGGER_SOURCE_S /* 557858082 */:
                sSettingEventChangeValue.setIceBoxTriggerSourceS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858083:
                sSettingEventChangeValue.setsocTargetRangeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858084:
                sSettingEventChangeValue.setsocTargetLowLimitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_CPD_IMS_SWITCH_STATE_S /* 557858088 */:
                sSettingEventChangeValue.setCpdImsSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858089:
                sSettingEventChangeValue.setCpdImsSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858090:
                sSettingEventChangeValue.setRncStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858091:
                sSettingEventChangeValue.setSwcontrolStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_PEDAL_SUPPRESSION_SYSTEM_STATE_S /* 557858092 */:
                sSettingEventChangeValue.setPedalSuppressionSystemStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858093:
                sSettingEventChangeValue.setPedalSuppressionSystemStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858096:
                sSettingEventChangeValue.setCuraSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_PHYSICALKEY_CONFIG_R /* 557858097 */:
                sSettingEventChangeValue.setPhysicalkeyConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_PHYSICALKEY_SECU_CONFIG_R /* 557858098 */:
                sSettingEventChangeValue.setPhysicalkeySecuConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_PHYSICALKEY_SECUP_CONFIG_R /* 557858099 */:
                sSettingEventChangeValue.setPhysicalkeySecupConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_PHYSICALKEY_SECURL_CONFIG_R /* 557858100 */:
                sSettingEventChangeValue.setPhysicalkeySecurlConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_PHYSICALKEY_SECURR_CONFIG_R /* 557858101 */:
                sSettingEventChangeValue.setPhysicalkeySecurrConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858102:
                sSettingEventChangeValue.setRearRightSeatGreetStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_REAR_RIGHT_SEAT_GREET_STATE_S /* 557858103 */:
                sSettingEventChangeValue.setRearRightSeatGreetStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_CURA_SWITCH_STATE_S /* 557858104 */:
                sSettingEventChangeValue.setCuraSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_CAR_DOOR_TIPS_SOUND_TYPE_S /* 557858105 */:
                sSettingEventChangeValue.setCarDoorTipsSoundTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_STEERING_WHEEL_HEATED_PLAN_R /* 557858106 */:
                sSettingEventChangeValue.setSteeringWheelHeatedPlanR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858107:
                sSettingEventChangeValue.setWadingPatternSocTipsR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858108:
                sSettingEventChangeValue.setWadingPatternSpeedTipsR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858109:
                sSettingEventChangeValue.setWadingPatternTestTipsR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858110:
                sSettingEventChangeValue.setWadingPatternStateTipsR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858111:
                sSettingEventChangeValue.setSeaModeChargingSocTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858112:
                sSettingEventChangeValue.setDriveModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858113:
                sSettingEventChangeValue.setSandAbsModeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858114:
                sSettingEventChangeValue.setSandAbsModeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858115:
                sSettingEventChangeValue.setSandAbsTipMessageR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858116:
                sSettingEventChangeValue.setCarSeatLayoutR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858117:
                sSettingEventChangeValue.setSaveOwnerStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858119:
                sSettingEventChangeValue.setRefrigeratorMobileTerminalPushReR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858122:
                sSettingEventChangeValue.setRefrigWorkFlagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858123:
                sSettingEventChangeValue.setRefrigWorkModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858124:
                sSettingEventChangeValue.setRefrigWorkModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858125:
                sSettingEventChangeValue.setDriverSeatVentilating437StateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858126:
                sSettingEventChangeValue.setDriverSeatHeating437StateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858127:
                sSettingEventChangeValue.setPassengerSeatVentilating3CAStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858128:
                sSettingEventChangeValue.setPassengerSeatHeating3CAStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858140:
                sSettingEventChangeValue.setThirdRowSeatWorkModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858141:
                sSettingEventChangeValue.setLeftRearSafebeltStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858142:
                sSettingEventChangeValue.setCenterRearSafebeltStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858143:
                sSettingEventChangeValue.setRightRearSafebeltStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858144:
                sSettingEventChangeValue.setLeftThirdSafebeltStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858145:
                sSettingEventChangeValue.setRightThirdSafebeltStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858146:
                sSettingEventChangeValue.setMainSeatHorizontalInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858147:
                sSettingEventChangeValue.setMainSeatBackInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858148:
                sSettingEventChangeValue.setPassangerSeatHorizontalInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858149:
                sSettingEventChangeValue.setPassangerSeatBackInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858150:
                sSettingEventChangeValue.setSecondLeftSeatHorizontalInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858151:
                sSettingEventChangeValue.setSecondLeftSeatBackInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858152:
                sSettingEventChangeValue.setSecondRightSeatHorizontalInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858153:
                sSettingEventChangeValue.setSecondRightSeatBackInitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858154:
                sSettingEventChangeValue.setOneKeyEnterPitConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858155:
                sSettingEventChangeValue.setThirdRowSeatLeftBackrestS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858156:
                sSettingEventChangeValue.setThirdRowSeatRightBackrestS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858157:
                sSettingEventChangeValue.setSeatDistributionFormConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858158:
                sSettingEventChangeValue.setMeterExportAreaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858159:
                sSettingEventChangeValue.setWashElectricPedalR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858160:
                sSettingEventChangeValue.setTapDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858161:
                sSettingEventChangeValue.setMainSeatHorizontalInitedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858162:
                sSettingEventChangeValue.setMainSeatBackInitedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858163:
                sSettingEventChangeValue.setMainSeatHightInitedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858165:
                sSettingEventChangeValue.setSecondRightSeatHorizontal2InitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858166:
                sSettingEventChangeValue.setSecondRightSeatBack2InitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858167:
                sSettingEventChangeValue.setDragTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858168:
                sSettingEventChangeValue.setDragConnectionTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858169:
                sSettingEventChangeValue.setTrailerSmallItemConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858170:
                sSettingEventChangeValue.setTrailerMiddleItemConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858171:
                sSettingEventChangeValue.setTrailerLargeItemConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858172:
                sSettingEventChangeValue.setTrailerDragTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858173:
                sSettingEventChangeValue.setTrailerDragTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858174:
                sSettingEventChangeValue.setTrailerSmallLimitWeightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858175:
                sSettingEventChangeValue.setTrailerMiddleLimitWeightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858176:
                sSettingEventChangeValue.setTrailerLargeLimitWeightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858177:
                sSettingEventChangeValue.setTowingProhibitedStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858190:
                sSettingEventChangeValue.setUcuMassageModeMainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858191:
                sSettingEventChangeValue.setUcuMassageModeDeputyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858192:
                sSettingEventChangeValue.setUcuMassageLevelMainR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858193:
                sSettingEventChangeValue.setUcuMassageLevelDeputyR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858194:
                sSettingEventChangeValue.setBdMassageModeLeftRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858195:
                sSettingEventChangeValue.setBdMassageModeRightRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858196:
                sSettingEventChangeValue.setBdMassageLevelLeftRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858197:
                sSettingEventChangeValue.setBdMassageLevelRightRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858198:
                sSettingEventChangeValue.setSecuMassageModeLeftRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858199:
                sSettingEventChangeValue.setSecuMassageModeRightRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858200:
                sSettingEventChangeValue.setSecuMassageLevelLeftRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858201:
                sSettingEventChangeValue.setSecuMassageLevelRightRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858202:
                sSettingEventChangeValue.setRrEcuMassageModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858203:
                sSettingEventChangeValue.setRrEcuMassageLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858204:
                sSettingEventChangeValue.setDriverMasToggleModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858205:
                sSettingEventChangeValue.setPassengerMasToggleModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858206:
                sSettingEventChangeValue.setLrMasToggleModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858207:
                sSettingEventChangeValue.setLrMas2ToggleModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858208:
                sSettingEventChangeValue.setRrMasToggleModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858209:
                sSettingEventChangeValue.setRrMas2ToggleModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858210:
                sSettingEventChangeValue.setMainNormalMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858212:
                sSettingEventChangeValue.setMain2HotStoneMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858213:
                sSettingEventChangeValue.setPasNormalMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858214:
                sSettingEventChangeValue.setPasHotStoneMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858215:
                sSettingEventChangeValue.setPas2HotStoneMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858216:
                sSettingEventChangeValue.setLrNormalMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858217:
                sSettingEventChangeValue.setLrNormal2MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858218:
                sSettingEventChangeValue.setLrHotStoneMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858219:
                sSettingEventChangeValue.setLrHotStone2MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858220:
                sSettingEventChangeValue.setLrHotStone3MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858221:
                sSettingEventChangeValue.setRrNormalMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858222:
                sSettingEventChangeValue.setRrNormal2MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858223:
                sSettingEventChangeValue.setRrNormal3MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858224:
                sSettingEventChangeValue.setRrHotStoneMassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858225:
                sSettingEventChangeValue.setRrHotStone2MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858226:
                sSettingEventChangeValue.setRrHotStone3MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858227:
                sSettingEventChangeValue.setRrHotStone4MassageConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858228:
                sSettingEventChangeValue.setSeatSecullDistributionFormConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858229:
                sSettingEventChangeValue.setSeatSeculrDistributionFormConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858230:
                sSettingEventChangeValue.setSeatSecurlDistributionFormConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858231:
                sSettingEventChangeValue.setSeatSecurrDistributionFormConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858304:
                sSettingEventChangeValue.setCampingS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858305:
                sSettingEventChangeValue.setSafeBeltWarnStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858306:
                sSettingEventChangeValue.setSafeBeltWarnStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858307:
                sSettingEventChangeValue.setCarSettingsResetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858308:
                sSettingEventChangeValue.setDriveSettingsResetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858309:
                sSettingEventChangeValue.setCarSettingsDefaultResetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858310:
                sSettingEventChangeValue.setNewEnergyDefaultResetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858311:
                sSettingEventChangeValue.setDoorsAndWindowDefaultRessetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858312:
                sSettingEventChangeValue.setLocksDefaultResetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858313:
                sSettingEventChangeValue.setRearMirrorDefaultResetS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858314:
                sSettingEventChangeValue.setAutoBackDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858315:
                sSettingEventChangeValue.setCarHealthDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858316:
                sSettingEventChangeValue.setIalDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858318:
                sSettingEventChangeValue.setBackLightDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858319:
                sSettingEventChangeValue.setSafeAssistDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858320:
                sSettingEventChangeValue.setIntelligentDriveDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858321:
                sSettingEventChangeValue.setLaneAssistDriveDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858322:
                sSettingEventChangeValue.setEqualizerDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858323:
                sSettingEventChangeValue.setSoundFiledDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858324:
                sSettingEventChangeValue.setClockDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858325:
                sSettingEventChangeValue.setLanguageDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858326:
                sSettingEventChangeValue.setUnitDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858327:
                sSettingEventChangeValue.setOutChargeDefaultReset(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858328:
                sSettingEventChangeValue.setSetUiVersion(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858329:
                sSettingEventChangeValue.setOnSafeBeltWarnStateChanged(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858330:
                sSettingEventChangeValue.setPabCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858331:
                sSettingEventChangeValue.setMissPackageCountR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858333:
                sSettingEventChangeValue.setScreenRotationS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858334:
                sSettingEventChangeValue.setScreenAngleR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858335:
                sSettingEventChangeValue.setScreenRotationLockS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858336:
                sSettingEventChangeValue.setScreenRotationActionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858337:
                sSettingEventChangeValue.setScreenAntiPinchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858338:
                sSettingEventChangeValue.setScreenAngleValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858340:
                sSettingEventChangeValue.setScreenRotationOverHotStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858342:
                sSettingEventChangeValue.setLanguageS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858343:
                sSettingEventChangeValue.setInternationalLanguageS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858344:
                sSettingEventChangeValue.setPabStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858345:
                sSettingEventChangeValue.setPabStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858354:
                sSettingEventChangeValue.setSomethingLeftInRefrigeratorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858355:
                sSettingEventChangeValue.setOneLevelSeatHeatingStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858356:
                sSettingEventChangeValue.setnotifyEnterTunnelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858357:
                sSettingEventChangeValue.setTrailerModeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858358:
                sSettingEventChangeValue.setTrailerLightCheckStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858360:
                sSettingEventChangeValue.setRearViewEcuMirrorFlipStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858361:
                sSettingEventChangeValue.setDyHeightToggleStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858362:
                sSettingEventChangeValue.setWelcomeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858363:
                sSettingEventChangeValue.setWelcomeActiveR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858364:
                sSettingEventChangeValue.setTailBoxTakeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858365:
                sSettingEventChangeValue.setCampingBlanceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858366:
                sSettingEventChangeValue.setCampingBlanceActionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858367:
                sSettingEventChangeValue.setLiftModeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858368:
                sSettingEventChangeValue.setFixedStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858369:
                sSettingEventChangeValue.setSpeedSupportedTurnR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858370:
                sSettingEventChangeValue.setModeSupportedHturnR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858371:
                sSettingEventChangeValue.setWelcomeSupportedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858372:
                sSettingEventChangeValue.setTailBoxTakeSupportedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858373:
                sSettingEventChangeValue.setTrailerModeCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858374:
                sSettingEventChangeValue.setCvcModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858375:
                sSettingEventChangeValue.setCampingBlanceSupportedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858376:
                sSettingEventChangeValue.setRearSeatGreetStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858377:
                sSettingEventChangeValue.setDyHeightTurnActionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858378:
                sSettingEventChangeValue.setDampStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858379:
                sSettingEventChangeValue.setDyHeightLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858380:
                sSettingEventChangeValue.setFdCrcCheckNumR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858381:
                sSettingEventChangeValue.setOnFdCrcCheckNumR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858382:
                sSettingEventChangeValue.setFdDyHeightTurnActionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858383:
                sSettingEventChangeValue.setFdDampStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858384:
                sSettingEventChangeValue.setFdDyHeightLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858385:
                sSettingEventChangeValue.setFdDyHeightToggleStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858386:
                sSettingEventChangeValue.setFdWelcomeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858387:
                sSettingEventChangeValue.setFdWelcomeActiveR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858388:
                sSettingEventChangeValue.setFdFrontBoxTakeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858389:
                sSettingEventChangeValue.setFdTailBoxTakeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858390:
                sSettingEventChangeValue.setFdCampingBlanceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858391:
                sSettingEventChangeValue.setFdCampingBlanceActionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858392:
                sSettingEventChangeValue.setFdLiftModeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858393:
                sSettingEventChangeValue.setFdFixedStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858394:
                sSettingEventChangeValue.setFdSpeedSupportedTurnR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858395:
                sSettingEventChangeValue.setFdModeSupportedHturnR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858396:
                sSettingEventChangeValue.setFdWelcomeSupportedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858397:
                sSettingEventChangeValue.setFdTailBoxTakeSupportedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858398:
                sSettingEventChangeValue.setFdTrailerModeCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858399:
                sSettingEventChangeValue.setFdCampingBlanceSupportedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858400:
                sSettingEventChangeValue.setDyHeightValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858401:
                sSettingEventChangeValue.setDampStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858402:
                sSettingEventChangeValue.setDyHeightToggleStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858403:
                sSettingEventChangeValue.setLiftModeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858404:
                sSettingEventChangeValue.setWelcomeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858405:
                sSettingEventChangeValue.setCampingBlanceStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858406:
                sSettingEventChangeValue.setTailBoxTakeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858407:
                sSettingEventChangeValue.setDyHeightByVoiceS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858408:
                sSettingEventChangeValue.setCvcModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858409:
                sSettingEventChangeValue.setMessageCounterS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858410:
                sSettingEventChangeValue.setCheckSumS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858411:
                sSettingEventChangeValue.setCloseDoorLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858412:
                sSettingEventChangeValue.setIntelLockOpenDriverDoorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858413:
                sSettingEventChangeValue.setBrakeCloseDoorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858414:
                sSettingEventChangeValue.setNfcOpenDriverDoorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858415:
                sSettingEventChangeValue.setBleOpenDriverDoorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858416:
                sSettingEventChangeValue.setSafeBeltCloseDriveDoorR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858417:
                sSettingEventChangeValue.setNotifyTunnelLengthS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858418:
                sSettingEventChangeValue.setVspStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858419:
                sSettingEventChangeValue.setVspStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858420:
                sSettingEventChangeValue.setTrailerLightCheckStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858422:
                sSettingEventChangeValue.setNfcOpenDriverDoorS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858423:
                sSettingEventChangeValue.setBleOpenDriverDoorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858424:
                sSettingEventChangeValue.setSafeBeltCloseDriveDoorStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858425:
                sSettingEventChangeValue.setIntelLockOpenDriverDoorS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858426:
                sSettingEventChangeValue.setBrakeCloseDoorS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858427:
                sSettingEventChangeValue.setRearSeatGreetStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858428:
                sSettingEventChangeValue.setTrailerModeStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858429:
                sSettingEventChangeValue.setDriverSeatAutoReturnB3R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858430:
                sSettingEventChangeValue.setCallStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858437:
                sSettingEventChangeValue.setLeftRseSwitchCommandS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858438:
                sSettingEventChangeValue.setRightRseSwitchCommandS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858439:
                sSettingEventChangeValue.setLeftRseStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858440:
                sSettingEventChangeValue.setRightRseStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858441:
                sSettingEventChangeValue.setLrseMachineSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858442:
                sSettingEventChangeValue.setRrseMachineSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858444:
                sSettingEventChangeValue.setRrseChildAreaStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858445:
                sSettingEventChangeValue.setLeftRseChildModeSwitchStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858446:
                sSettingEventChangeValue.setRightRseChildModeSwitchStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858447:
                sSettingEventChangeValue.setLrseChildModeOpenTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858448:
                sSettingEventChangeValue.setRrseChildModeOpenTimeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858449:
                sSettingEventChangeValue.setLrseChildModeTimeWriteFbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858450:
                sSettingEventChangeValue.setRrseChildModeTimeWriteFbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858451:
                sSettingEventChangeValue.setLrseChildModeCode1stByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858452:
                sSettingEventChangeValue.setLrseChildModeCode2ndByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858453:
                sSettingEventChangeValue.setLrseChildModeCode3rdByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858454:
                sSettingEventChangeValue.setLrseChildModeCode4thByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858455:
                sSettingEventChangeValue.setRrseChildModeCode1stByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858456:
                sSettingEventChangeValue.setRrseChildModeCode2ndByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858457:
                sSettingEventChangeValue.setRrseChildModeCode3rdByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858458:
                sSettingEventChangeValue.setRrseChildModeCode4thByteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858459:
                sSettingEventChangeValue.setLrseChildModeCodeWriteFbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858460:
                sSettingEventChangeValue.setRrseChildModeCodeWriteFbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858461:
                sSettingEventChangeValue.setDragModeForwardAndBackwardStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858462:
                sSettingEventChangeValue.setDragAndDropModeTipsStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_DRIVER_SEAR_STATE_S /* 557858526 */:
                sSettingEventChangeValue.setDriverSeatStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858527:
                sSettingEventChangeValue.setMemorySetOperationFlagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858528:
                sSettingEventChangeValue.setFrontWiperSpeedValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858529:
                sSettingEventChangeValue.setTrailerModeMileageR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858530:
                sSettingEventChangeValue.setRearWiperSpeedValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_INSIDE_LIGHT_STATE_S /* 557858532 */:
                sSettingEventChangeValue.setInsideLightStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_OUTSIDE_LIGHT_STATE_SCENE_S /* 557858533 */:
                sSettingEventChangeValue.setOutsideLightStateSceneS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_LRSE_CHILD_AREA_STATE_S /* 557858534 */:
                sSettingEventChangeValue.setLrseChildAreaStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_CLOSE_DOOR_LEVEL_S /* 557858535 */:
                sSettingEventChangeValue.setCloseDoorLevelS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859101:
                sSettingEventChangeValue.setMainHsMassageStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859103:
                sSettingEventChangeValue.setHsMassageStatePassengerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859104:
                sSettingEventChangeValue.setHsMassageStateBackLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859105:
                sSettingEventChangeValue.setHsMassageStateBackRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859134:
                sSettingEventChangeValue.setThirdRowLeftBackrestDisplayR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859144:
                sSettingEventChangeValue.setThirdRowRightBackrestDisplayR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859165:
                sSettingEventChangeValue.setDimmingCanopyConfiguration(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859185:
                sSettingEventChangeValue.setRncStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859205:
                sSettingEventChangeValue.setRefrigNumFlagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859210:
                sSettingEventChangeValue.setRefrig1PositionMarkBitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557859211:
                sSettingEventChangeValue.setRefrig2PositionMarkBitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860611:
                sSettingEventChangeValue.setAmbientMusicModeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860613:
                sSettingEventChangeValue.setAmbientMusicModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860615:
                sSettingEventChangeValue.setAmbientMulticolorStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860617:
                sSettingEventChangeValue.setAmbientMulticolorModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860626:
                sSettingEventChangeValue.setAfsSwitchSate(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860631:
                sSettingEventChangeValue.setAmbientFeedBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862926:
                sSettingEventChangeValue.setScreenVoltageStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557867008:
                sSettingEventChangeValue.setSwsMediumRelatedKeysS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557871104:
                sSettingEventChangeValue.setExhibitionMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557875283:
                sSettingEventChangeValue.setScreenRotationStatus(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSettingManager.ID_ADAS_REST_S /* 557924014 */:
                sSettingEventChangeValue.setAdasRestS(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case CarVendorSettingManager.ID_DLINK_REST_S /* 557924030 */:
                sSettingEventChangeValue.setDlinkRestS(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case CarVendorSettingManager.ID_CARSETTING_REST_S /* 557924046 */:
                sSettingEventChangeValue.setCarsettingRestS(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 559954810:
                sSettingEventChangeValue.setSteeringWheelY(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954811:
                sSettingEventChangeValue.setSteeringWheelZ(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954812:
                sSettingEventChangeValue.setRearviewMirrorYLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954813:
                sSettingEventChangeValue.setRearviewMirrorYRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954814:
                sSettingEventChangeValue.setRearviewMirrorXLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954815:
                sSettingEventChangeValue.setRearviewMirrorXRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954820:
                sSettingEventChangeValue.setSeatTurnHeightDriver(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954821:
                sSettingEventChangeValue.setSeatTurnHeightCopilot(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954822:
                sSettingEventChangeValue.setSeatTurnHeightBackLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954823:
                sSettingEventChangeValue.setSeatTurnHeightBackRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954824:
                sSettingEventChangeValue.setSeatbackrestPostionDriver(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954825:
                sSettingEventChangeValue.setSeatbackrestPostionCopilot(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954826:
                sSettingEventChangeValue.setSeatbackrestPostionBackLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954827:
                sSettingEventChangeValue.setSeatbackrestPostionBackRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954832:
                sSettingEventChangeValue.setTurnCushionHeightDriver(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954833:
                sSettingEventChangeValue.setTurnCushionHeightCopilot(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954834:
                sSettingEventChangeValue.setTurnCushionHeightBackLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954835:
                sSettingEventChangeValue.setTurnCushionHeightBackRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954836:
                sSettingEventChangeValue.setTurnCaliperSplintHeightDriver(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954837:
                sSettingEventChangeValue.setTurnCaliperSplintHeightCopilot(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954838:
                sSettingEventChangeValue.setTurnCaliperSplintHeightBackLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954839:
                sSettingEventChangeValue.setTurnCaliperSplintHeightBackRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954844:
                sSettingEventChangeValue.setHeadrestTurnHeightDriver(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559954845:
                sSettingEventChangeValue.setHeadrestTurnHeightCopilot(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559955504:
                sSettingEventChangeValue.setHeadrestTurnHeightBackLeft(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559955505:
                sSettingEventChangeValue.setHeadrestTurnHeightBackRight(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case CarVendorSettingManager.ID_MEDITATION_MODE_S /* 567857951 */:
                sSettingEventChangeValue.setMeditationModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557856184:
                        sSettingEventChangeValue.setAmbientLightColorSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856185:
                        sSettingEventChangeValue.setAmbientLightSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557856186:
                        sSettingEventChangeValue.setAmbientAreaSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557856889:
                                sSettingEventChangeValue.setBsisCollisionStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856890:
                                sSettingEventChangeValue.setBsisCollisionStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856891:
                                sSettingEventChangeValue.setBsisFuncStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856892:
                                sSettingEventChangeValue.setMoisAssembleStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856893:
                                sSettingEventChangeValue.setMoisAssembleStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856894:
                                sSettingEventChangeValue.setMoisFuncStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557857536:
                                        sSettingEventChangeValue.setACBTWindR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857537:
                                        sSettingEventChangeValue.setACBTWindSX31(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857538:
                                        sSettingEventChangeValue.setACBTWindSX28(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857539:
                                        sSettingEventChangeValue.setACBTWindS4E3(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857540:
                                        sSettingEventChangeValue.setACPauseCycleR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857541:
                                        sSettingEventChangeValue.setACPauseCycleS4E3(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857542:
                                        sSettingEventChangeValue.setACPauseCycleS4EF(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857543:
                                        sSettingEventChangeValue.setACAutoAirR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557857544:
                                        sSettingEventChangeValue.setACAutoAirS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557857602:
                                                sSettingEventChangeValue.setSteerPositionAutoReturnS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857603:
                                                sSettingEventChangeValue.setBrakePedalModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857604:
                                                sSettingEventChangeValue.setBrakePedalModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857605:
                                                sSettingEventChangeValue.setComfortableParkingStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557857606:
                                                sSettingEventChangeValue.setComfortableParkingStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 557857608:
                                                        sSettingEventChangeValue.setMotorPretensionerSeatbeltStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857609:
                                                        sSettingEventChangeValue.setresetMotorPretensionerSeatbelt(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857610:
                                                        sSettingEventChangeValue.setAutoRainWiperStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857611:
                                                        sSettingEventChangeValue.setDynamicSuspensionModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857612:
                                                        sSettingEventChangeValue.setDynamicSuspensionModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857613:
                                                        sSettingEventChangeValue.setRemoteControlUpwindowStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857614:
                                                        sSettingEventChangeValue.setRemoteControlUpwindowStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857615:
                                                        sSettingEventChangeValue.setRemoteControlDownwindowStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857616:
                                                        sSettingEventChangeValue.setRemoteControlDownwindowStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857617:
                                                        sSettingEventChangeValue.setMicroSwitchLockWindowStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857618:
                                                        sSettingEventChangeValue.setMicroSwitchLockWindowStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857619:
                                                        sSettingEventChangeValue.setMicroSwitchUnlockWindowStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857620:
                                                        sSettingEventChangeValue.setMicroSwitchUnlockWindowStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857621:
                                                        sSettingEventChangeValue.setLockCarRiseWindowR48E(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857622:
                                                        sSettingEventChangeValue.setLockCarRiseWindowR12F(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857623:
                                                        sSettingEventChangeValue.setLockCarRiseWindowS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857624:
                                                        sSettingEventChangeValue.setLeftHomeLightDelayValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857625:
                                                        sSettingEventChangeValue.setLeftHomeLightDelayValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857626:
                                                        sSettingEventChangeValue.setBackHomeLightDelayValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857627:
                                                        sSettingEventChangeValue.setBackHomeLightDelayValueS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857628:
                                                        sSettingEventChangeValue.setturnHeadrestTurnHeightDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857629:
                                                        sSettingEventChangeValue.setturnHeadrestTurnHeightCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857630:
                                                        sSettingEventChangeValue.setturnHeadrestTurnHeightBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857631:
                                                        sSettingEventChangeValue.setturnHeadrestTurnHeightBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857632:
                                                        sSettingEventChangeValue.setturnSeatCaliperSplintDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857633:
                                                        sSettingEventChangeValue.setturnSeatCaliperSplintCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857634:
                                                        sSettingEventChangeValue.setturnSeatCaliperSplintBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857635:
                                                        sSettingEventChangeValue.setturnSeatCaliperSplintBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857636:
                                                        sSettingEventChangeValue.setturnSeatCushionDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857637:
                                                        sSettingEventChangeValue.setturnSeatCushionCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857638:
                                                        sSettingEventChangeValue.setturnSeatCushionBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857639:
                                                        sSettingEventChangeValue.setturnSeatCushionBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557857640:
                                                        sSettingEventChangeValue.setturnSeatbackrestDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 557857642:
                                                                sSettingEventChangeValue.setturnSeatbackrestBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857643:
                                                                sSettingEventChangeValue.setturnSeatbackrestBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857644:
                                                                sSettingEventChangeValue.setturnSeatHeightDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857645:
                                                                sSettingEventChangeValue.setturnSeatHeightCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857646:
                                                                sSettingEventChangeValue.setturnSeatHeightBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857647:
                                                                sSettingEventChangeValue.setturnSeatHeightBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857648:
                                                                sSettingEventChangeValue.setturnSeatHorizationDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857649:
                                                                sSettingEventChangeValue.setturnSeatHorizationCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857650:
                                                                sSettingEventChangeValue.setturnSeatHorizationBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857651:
                                                                sSettingEventChangeValue.setturnSeatHorizationBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857652:
                                                                sSettingEventChangeValue.setturnRearviewMirror(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857653:
                                                                sSettingEventChangeValue.setturnSteeringWheel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857654:
                                                                sSettingEventChangeValue.setsaveSeatParamsAllDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857655:
                                                                sSettingEventChangeValue.setsaveSeatParamsAllCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857656:
                                                                sSettingEventChangeValue.setsaveSeatParamsAllBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557857657:
                                                                sSettingEventChangeValue.setsaveSeatParamsAllBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557857664:
                                                                        sSettingEventChangeValue.setSeatHorizationDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857665:
                                                                        sSettingEventChangeValue.setSeatHorizationCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857666:
                                                                        sSettingEventChangeValue.setSeatHorizationBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557857667:
                                                                        sSettingEventChangeValue.setSeatHorizationBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557857676:
                                                                                sSettingEventChangeValue.setresetSeatParamsDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557857677:
                                                                                sSettingEventChangeValue.setresetSeatParamsCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557857678:
                                                                                sSettingEventChangeValue.setresetSeatParamsBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557857679:
                                                                                sSettingEventChangeValue.setresetSeatParamsBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case 557857688:
                                                                                        sSettingEventChangeValue.setHeadrestHorizationDriver(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557857689:
                                                                                        sSettingEventChangeValue.setHeadrestHorizationCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557857690:
                                                                                        sSettingEventChangeValue.setHeadrestHorizationBackLeft(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case 557857691:
                                                                                        sSettingEventChangeValue.setHeadrestHorizationBackRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorSettingManager.TAG, "break other properties ,except setting");
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
        Log.debug(CarVendorSettingManager.TAG, "convertCarVendorPropertyValue " + sSettingEventChangeValue.toString(), DEBUG_LOGGER);
        return sSettingEventChangeValue;
    }

    private OnCarSettingChangeEventListenerProxy findListenerLocked(CarVendorSettingManager.OnCarSettingEventListener listener) {
        for (OnCarSettingChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarSettingChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorSettingManager.OnCarSettingEventListener listener;

        public OnCarSettingChangeEventListenerProxy(CarVendorSettingManager.OnCarSettingEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorSettingMgr.this.traceCallbackPrepare();
                SettingEventChangeValue newValue = CarVendorSettingMgr.convertCarVendorProperty(value);
                CarVendorSettingMgr.this.traceCallbackBefore();
                this.listener.onSettingEventChange(newValue);
                CarVendorSettingMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorSettingManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorSettingManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onSettingErrorEvent(propertyId, zone);
        }
    }
}
