package com.ecarx.xui.adaptapi.hvac.specific;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import com.ecarx.xui.adaptapi.hvac.IAQSState;
import com.ecarx.xui.adaptapi.hvac.IHvacCallback;
import com.ecarx.xui.adaptapi.hvac.IHvacState;
import com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard;
import com.ecarx.xui.adaptapi.hvac.impl.AqsState;
import com.ecarx.xui.adaptapi.hvac.impl.HvacState;
import com.ecarx.xui.adaptapi.hvac.impl.HvacTouchBoard;
import com.ecarx.xui.adaptapi.hvac.inf.IDataListener;
import com.ecarx.xui.adaptapi.policy.VoiceAssistantPolicy;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public class AcBean implements IDataListener {
    private static final int AC_AQS = 3;
    private static final int AC_AVAILABLE = 2;
    private static final int AC_CALLBACK = 1;
    private static final String TAG = "Hvac";
    private Context mContext;
    private IHvacCallback mHvacCallback;
    private HvacState mHvacState;
    private HvacTouchBoard mHvacTouchBoard;
    private boolean mIsHvacAvailable;
    private int mPepsMode;
    private int mPepsTimeOut;
    private int mPepsValidity;
    private DataBundle _dataBundle = new DataBundle();
    private int touchMode = -2147483647;
    private int windowType = -2147483647;
    private boolean isFragranceOn = false;
    private Handler handler = new Handler() { // from class: com.ecarx.xui.adaptapi.hvac.specific.AcBean.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                if (AcBean.this.mHvacCallback != null) {
                    Log.d(AcBean.TAG, "mHvacCallback is not null");
                    AcBean.this.mHvacCallback.onStateChanged(AcBean.this.mHvacState);
                    return;
                }
                return;
            }
            if (i != 2) {
                if (i == 3 && AcBean.this.mHvacCallback != null) {
                    AcBean.this.mHvacCallback.onAQSStateChanged(AcBean.this.mAqsState);
                    return;
                }
                return;
            }
            Log.d(AcBean.TAG, "Boolean.parseBoolean(msg.obj.toString()=" + Boolean.parseBoolean(message.obj.toString()) + " ,mIsHvacAvailable=" + AcBean.this.mIsHvacAvailable);
            if (Boolean.parseBoolean(message.obj.toString()) != AcBean.this.mIsHvacAvailable) {
                AcBean.this.mIsHvacAvailable = Boolean.parseBoolean(message.obj.toString());
                if (AcBean.this.mHvacCallback != null) {
                    AcBean.this.mHvacCallback.onHvacAvailable(Boolean.parseBoolean(message.obj.toString()));
                }
            }
        }
    };
    private DataBundle _acModel = new DataBundle();
    private AqsState mAqsState = new AqsState();

    private boolean getBoolean(byte b) {
        if (b != 1) {
            b = 0;
        }
        return b == 1;
    }

    private int getInt(byte b, byte b2) {
        return ((b << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (b2 & 255);
    }

    private int getOutsideTemperature(byte b) {
        return (b & 15) + (((b & 240) >> 4) * 16);
    }

    private int getTouchMode(int i) {
        if (i == 0) {
            return 2147483637;
        }
        switch (i) {
            case 5:
                return 2;
            case 6:
                return 1;
            case 7:
                return 3;
            case 8:
                return 4;
            case 9:
                return 5;
            default:
                return -2147483647;
        }
    }

    public AcBean(Context context) {
        this.mContext = context;
        this.mHvacState = new HvacState(context);
        this.mHvacTouchBoard = new HvacTouchBoard(context);
    }

    public boolean getIsFragranceOn() {
        Log.d(TAG, "isFragranceOn =" + this.isFragranceOn);
        return this.isFragranceOn;
    }

    public void setHvacAvailable(boolean z) {
        this.mIsHvacAvailable = z;
    }

    public void setHvacCallBack(IHvacCallback iHvacCallback) {
        this.mHvacCallback = iHvacCallback;
    }

    public IHvacState getHvacState() {
        return this.mHvacState;
    }

    public IHvacTouchBoard getHvacTouchBoard() {
        return this.mHvacTouchBoard;
    }

    public IAQSState getAqsState() {
        return this.mAqsState;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.inf.IDataListener
    public void insertData(int i, byte[] bArr) {
        Keys keys = new Keys();
        parseOnCallBackData(i, bArr, keys, new Keys());
        if (keys.size() > 0) {
            sendMultipleEvent(keys);
        }
    }

    @Override // com.ecarx.xui.adaptapi.hvac.inf.IDataListener
    public void initialData(byte[] bArr) {
        Keys keys = new Keys();
        parseInitialData(bArr, keys, new Keys());
        sendMultipleInitEvent(keys);
    }

    @Override // com.ecarx.xui.adaptapi.hvac.inf.IDataListener
    public void initAqsState(byte[] bArr, byte[] bArr2) {
        this.mAqsState.notifyAqsOn(bArr[12] == 1);
        byte b = bArr[8];
        if (b == 1 || b == 2) {
            this.mAqsState.notifyPmWaring(true);
        } else if (b == 3) {
            this.mAqsState.notifyPmWaring(false);
        }
        this.mAqsState.notifyPmSensorState(bArr[8]);
        int i = getInt(bArr[2], bArr[3]);
        int i2 = getInt(bArr[4], bArr[5]);
        this.mAqsState.notifyPM25AQILevel(bArr[6]);
        this.mAqsState.notifyAmbientPM25AQILevel(bArr[7]);
        this.mAqsState.notifyPmInsideValue(i);
        this.mAqsState.notifyPmOutsideValue(i2);
        this.mAqsState.notifyAcCO2Value(getInt(bArr2[9], bArr2[10]));
        this.mAqsState.notifyAcCO2Level(bArr2[11]);
    }

    private void parseInitialData(byte[] bArr, Keys keys, Keys keys2) {
        byte b = bArr[0];
        if (b == 6) {
            return;
        }
        if (b == 17) {
            Log.d(TAG, "parseInitialData AC data");
            parseBooleanData(bArr[12], keys, keys2, 7, 2, 0);
            parseBooleanData(bArr[14], keys, keys2, 10, 2, 0);
            parseIntData(bArr[19], keys, keys2, 3, 31, 0);
            parseIntData(bArr[20], keys, keys2, 4, 31, 0);
            parseBooleanData(bArr[17], keys, keys2, 16, 2, 0);
            parseIntData(bArr[15], keys, keys2, 5, 4, 0);
            parseBooleanData(bArr[11], keys, keys2, 6, 4, 0);
            parseBooleanData(bArr[12], keys, keys2, 7, 2, 0);
            parseBooleanData(bArr[10], keys, keys2, 8, 2, 0);
            parseBooleanData(bArr[9], keys, keys2, 9, 2, 0);
            parseIntData(bArr[16], keys, keys2, 11, 5, 0);
            parseIntData(bArr[13], keys, keys2, 12, 9, 0);
            parseBooleanData(bArr[1], keys, keys2, 14, 2, 0);
            parseBooleanData(bArr[18], keys, keys2, 15, 2, 0);
            parseIntData(getInt(bArr[2], bArr[3]), keys, keys2, 17, 999, 0);
            parseIntData(getInt(bArr[4], bArr[5]), keys, keys2, 19, 999, 0);
            parseIntData(bArr[6], keys, keys2, 21, 6, 0);
            parseIntData(bArr[7], keys, keys2, 22, 6, 0);
            parseIntData(bArr[8], keys, keys2, 23, 4, 0);
            return;
        }
        if (b == 18) {
            if (AcModelHelper.getInstance(this.mContext).hasAcThirdPanel()) {
                parseIntData(bArr[3], keys, keys2, 64, 2, 0);
            }
            parseBooleanData(bArr[4], keys, keys2, 69, 2, 0);
            parseIntData(getInt(bArr[9], bArr[10]), keys, keys2, 70, 5000, 0);
            parseIntData(bArr[11], keys, keys2, 71, 6, 0);
            parseBooleanData(bArr[6], keys, keys2, 72, 2, 0);
            parseIntData(bArr[13], keys, keys2, 75, 4, 0);
            parseIntData(bArr[14], keys, keys2, 76, 4, 0);
            parseBooleanData(bArr[15], keys, keys2, 73, 2, 0);
            return;
        }
        if (b == 20) {
            parseBooleanData(bArr[1], keys, keys2, 34, 3, 0);
            return;
        }
        if (b == 9) {
            return;
        }
        if (b == 3) {
            Log.d(TAG, "parseInitialData Seat data");
            parseIntData(bArr[1], keys, keys2, 24, 5, 0);
            parseIntData(bArr[2], keys, keys2, 25, 5, 0);
            parseIntData(bArr[4], keys, keys2, 26, 4, 0);
            parseIntData(bArr[3], keys, keys2, 27, 4, 0);
            return;
        }
        if (b == 4) {
            parseIntData(bArr[1], keys, keys2, 46, 4, 0);
            return;
        }
        if (b == 8) {
            parseBooleanData(bArr[1], keys, keys2, 66, 2, 0);
            parseBooleanData(bArr[2], keys, keys2, 67, 2, 0);
            return;
        }
        if (b == 22) {
            parseIntData(getOutsideTemperature(bArr[1]), keys, keys2, 47, 201, 0);
            parseIntData(bArr[6], keys, keys2, 36, 2, 0);
            parseIntData(bArr[7], keys, keys2, 37, 2, 0);
            parseIntData(bArr[8], keys, keys2, 38, 2, 0);
            parseIntData(bArr[9], keys, keys2, 39, 2, 0);
            parseIntData(bArr[10], keys, keys2, 40, 2, 0);
            parseBooleanData(bArr[2], keys, keys2, 43, 2, 0);
            parseBooleanData(bArr[3], keys, keys2, 42, 2, 0);
            if (AcModelHelper.getInstance(this.mContext).hasRCPSet()) {
                parseIntData(bArr[4], keys, keys2, 65, 2, 0);
            }
            parseIntData(bArr[15], keys, keys2, 44, 2, 0);
            parseIntData(bArr[21], keys, keys2, 45, 2, 0);
            if (AcModelHelper.getInstance(this.mContext).hasADS()) {
                parseIntData(bArr[12], keys, keys2, 48, 3, 0);
            }
            if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                parseIntData(bArr[17], keys, keys2, 50, 3, 0);
                parseIntData(bArr[19], keys, keys2, 60, 3, 0);
                parseIntData(bArr[18], keys, keys2, 61, 3, 0);
            }
            if (AcModelHelper.getInstance(this.mContext).isAcMultipleTemp()) {
                parseBooleanData(bArr[22], keys, keys2, 63, 2, 0);
            }
        }
    }

    private void parseOnCallBackData(int i, byte[] bArr, Keys keys, Keys keys2) {
        if (i == 1820) {
            byte b = bArr[0];
            if (b == 8) {
                this.mPepsValidity = bArr[7];
                this.mPepsMode = bArr[6];
                this.mPepsTimeOut = bArr[11];
                updatePepsState();
                return;
            }
            if (b == 9) {
                parseIntData(bArr[1], keys, keys2, 24, 5, 0);
                parseIntData(bArr[2], keys, keys2, 25, 5, 0);
                parseIntData(bArr[4], keys, keys2, 26, 4, 0);
                parseIntData(bArr[3], keys, keys2, 27, 4, 0);
                return;
            }
            if (b == 6) {
                parseIntData(bArr[4], keys, keys2, 46, 4, 0);
                parseBooleanData(bArr[6], keys, keys2, 66, 2, 0);
                parseBooleanData(bArr[7], keys, keys2, 67, 2, 0);
                return;
            } else {
                if (b == 12) {
                    parseIntData(bArr[8], keys, keys2, 75, 4, 0);
                    parseIntData(bArr[9], keys, keys2, 76, 4, 0);
                    return;
                }
                return;
            }
        }
        if (i == 1821) {
            byte b2 = bArr[0];
            return;
        }
        if (i == 1822) {
            byte b3 = bArr[0];
            if (b3 == 1) {
                parseBooleanData(bArr[1], keys, keys2, 14, 2, 0);
                parseIntData(getInt(bArr[2], bArr[3]), keys, keys2, 17, 999, 0);
                parseIntData(getInt(bArr[4], bArr[5]), keys, keys2, 19, 999, 0);
                parseIntData(bArr[6], keys, keys2, 21, 6, 0);
                parseIntData(bArr[7], keys, keys2, 22, 6, 0);
                parseIntData(bArr[8], keys, keys2, 23, 4, 0);
                parseBooleanData(bArr[17], keys, keys2, 10, 2, 0);
                parseIntData(bArr[18], keys, keys2, 5, 4, 0);
                parseBooleanData(bArr[14], keys, keys2, 6, 4, 0);
                parseBooleanData(bArr[15], keys, keys2, 7, 2, 0);
                parseBooleanData(bArr[13], keys, keys2, 8, 2, 0);
                parseBooleanData(bArr[12], keys, keys2, 9, 2, 0);
                parseIntData(bArr[19], keys, keys2, 11, 7, 0);
                parseIntData(bArr[16], keys, keys2, 12, 9, 0);
                parseIntData(getInt(bArr[20], bArr[21]), keys, keys2, 70, 999, 0);
                parseIntData(bArr[22], keys, keys2, 71, 6, 0);
                return;
            }
            if (b3 != 2) {
                if (b3 == 3) {
                    parseBooleanData(bArr[3], keys, keys2, 34, 3, 0);
                    return;
                }
                if (b3 == 5) {
                    parseIntData(bArr[7], keys, keys2, 36, 2, 0);
                    parseIntData(bArr[8], keys, keys2, 37, 2, 0);
                    parseIntData(bArr[9], keys, keys2, 38, 2, 0);
                    parseIntData(bArr[10], keys, keys2, 39, 2, 0);
                    parseIntData(bArr[11], keys, keys2, 40, 2, 0);
                    if (AcModelHelper.getInstance(this.mContext).hasADS()) {
                        parseIntData(bArr[1], keys, keys2, 48, 3, 0);
                    }
                    if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                        parseIntData(bArr[3], keys, keys2, 50, 3, 0);
                        parseIntData(bArr[5], keys, keys2, 60, 3, 0);
                        parseIntData(bArr[4], keys, keys2, 61, 3, 0);
                        return;
                    }
                    return;
                }
                return;
            }
            parseBooleanData(bArr[1], keys, keys2, 16, 2, 0);
            parseIntData(bArr[3], keys, keys2, 3, 31, 0);
            parseIntData(bArr[4], keys, keys2, 4, 31, 0);
            parseIntData(getOutsideTemperature(bArr[8]), keys, keys2, 47, 201, 0);
            parseBooleanData(bArr[2], keys, keys2, 15, 2, 0);
            parseBooleanData(bArr[9], keys, keys2, 43, 2, 0);
            parseBooleanData(bArr[10], keys, keys2, 42, 2, 0);
            parseIntData(bArr[16], keys, keys2, 45, 2, 0);
            if (AcModelHelper.getInstance(this.mContext).hasRCPSet()) {
                parseIntData(bArr[11], keys, keys2, 65, 2, 0);
            }
            parseIntData(bArr[15], keys, keys2, 44, 2, 0);
            if (AcModelHelper.getInstance(this.mContext).isAcMultipleTemp()) {
                parseBooleanData(bArr[17], keys, keys2, 63, 2, 0);
            }
            if (AcModelHelper.getInstance(this.mContext).hasAcThirdPanel()) {
                parseIntData(bArr[18], keys, keys2, 64, 2, 0);
            }
            parseBooleanData(bArr[19], keys, keys2, 69, 2, 0);
            parseBooleanData(bArr[21], keys, keys2, 72, 2, 0);
            parseBooleanData(bArr[24], keys, keys2, 73, 2, 0);
            return;
        }
        if (i == 1835 && bArr[0] == 1) {
            parseBooleanData(bArr[8], keys, keys2, 77, 2, 0);
        }
    }

    private boolean parseBooleanData(byte b, Keys keys, Keys keys2, int i, int i2, int i3) {
        boolean z;
        if ((b >= i2 && b < i3) || (z = getBoolean(b)) == this._acModel.getBoolean(i, false)) {
            return false;
        }
        Log.d(TAG, "View key :" + i + " changed : " + z);
        this._acModel.putBoolean(i, z);
        this._dataBundle.putBoolean(i, z);
        keys.addKey(i);
        for (int i4 = 0; i4 < Define.cStatusAirConditionKeys.length; i4++) {
            if (i == Define.cStatusAirConditionKeys[i4]) {
                keys2.addKey(i);
            }
        }
        return true;
    }

    private boolean parseIntData(int i, Keys keys, Keys keys2, int i2, int i3, int i4) {
        if ((i >= i3 && i < i4) || i == this._acModel.getInt(i2, -1)) {
            return false;
        }
        Log.d(TAG, "View key :" + i2 + " changed :" + i);
        this._acModel.putInt(i2, i);
        this._dataBundle.putInt(i2, i);
        keys.addKey(i2);
        for (int i5 = 0; i5 < Define.cStatusAirConditionKeys.length; i5++) {
            if (i2 == Define.cStatusAirConditionKeys[i5]) {
                keys2.addKey(i2);
            }
        }
        return true;
    }

    private void sendMessage(int i) {
        Message message = new Message();
        message.what = i;
        this.handler.sendMessage(message);
    }

    private void sendMessage(int i, boolean z) {
        Message message = new Message();
        message.what = i;
        message.obj = Boolean.valueOf(z);
        this.handler.sendMessage(message);
    }

    private void sendMultipleEvent(Keys keys) {
        updateAcData(keys);
        updateAqsData(keys);
    }

    private void sendMultipleInitEvent(Keys keys) {
        int size = keys.size();
        for (int i = 0; i < size; i++) {
            int key = keys.getKey(i);
            if (key == 19) {
                this.mHvacState.notifyAmbientPM25AQI(this._dataBundle.getInt(19, -1));
            } else if (key == 34) {
                this.mHvacState.notifyParkingSwitch(this._dataBundle.getBoolean(34, false));
            } else if (key != 50) {
                if (key == 69) {
                    this.mHvacState.notifyAcPTCHeat(this._dataBundle.getBoolean(69, false));
                } else if (key != 60) {
                    if (key != 61) {
                        if (key == 72) {
                            this.mHvacState.notifyAcEco(this._dataBundle.getBoolean(72, false));
                        } else if (key == 73) {
                            this.mHvacState.notifyAcSeatDisplay(this._dataBundle.getBoolean(73, false));
                        } else if (key != 75) {
                            if (key != 76) {
                                switch (key) {
                                    case 3:
                                        this.mHvacState.notifyLeftTemperature(this._dataBundle.getInt(key, -1));
                                        break;
                                    case 4:
                                        this.mHvacState.notifyRightTemperature(this._dataBundle.getInt(key, -1));
                                        break;
                                    case 5:
                                        this.mHvacState.notifyIonsMode(this._dataBundle.getInt(5, -1));
                                        break;
                                    case 6:
                                        this.mHvacState.notifyLoopMode(this._dataBundle.getBoolean(6, false));
                                        break;
                                    case 7:
                                        this.mHvacState.notifyAqsOn(this._dataBundle.getBoolean(7, false));
                                        break;
                                    case 8:
                                        this.mHvacState.notifyAcMaxOn(this._dataBundle.getBoolean(8, false));
                                        break;
                                    case 9:
                                        this.mHvacState.notifyAcAutoOn(this._dataBundle.getBoolean(9, false));
                                        break;
                                    case 10:
                                        this.mHvacState.notifyDualOn(this._dataBundle.getBoolean(10, false));
                                        break;
                                    case 11:
                                        this.mHvacState.notifyBlowingMode(this._dataBundle.getInt(11, -1));
                                        break;
                                    case 12:
                                        this.mHvacState.notifyBlowingLevel(this._dataBundle.getInt(12, -1));
                                        break;
                                    default:
                                        switch (key) {
                                            case 14:
                                                this.mHvacState.notifyAcOn(this._dataBundle.getBoolean(14, false));
                                                break;
                                            case 15:
                                                this.mHvacState.notifyRearLock(this._dataBundle.getBoolean(15, false));
                                                break;
                                            case 16:
                                                this.mHvacState.notifyHvacOn(this._dataBundle.getBoolean(key, false));
                                                break;
                                            case 17:
                                                this.mHvacState.notifyPM25AQI(this._dataBundle.getInt(17, -1));
                                                break;
                                            default:
                                                switch (key) {
                                                    case 21:
                                                        this.mHvacState.notifyPM25AQILevel(this._dataBundle.getInt(21, -1));
                                                        break;
                                                    case 22:
                                                        this.mHvacState.notifyAmbientPM25AQILevel(this._dataBundle.getInt(22, -1));
                                                        break;
                                                    case 23:
                                                        int i2 = this._dataBundle.getInt(23, -1);
                                                        if (i2 == 1 || i2 == 2) {
                                                            this.mHvacState.notifyPmSwitch(true);
                                                        } else if (i2 == 3) {
                                                            this.mHvacState.notifyPmSwitch(false);
                                                        }
                                                        break;
                                                    case 24:
                                                        this.mHvacState.notifyLeftHeatingLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    case 25:
                                                        this.mHvacState.notifyRightHeatingLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    case 26:
                                                        this.mHvacState.notifyLeftVentilationLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    case 27:
                                                        this.mHvacState.notifyLeftMassageLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    default:
                                                        switch (key) {
                                                            case 42:
                                                                this.mHvacState.notifyRearDefrostingOn(this._dataBundle.getBoolean(42, false));
                                                                break;
                                                            case 43:
                                                                this.mHvacState.notifyFrontDefrostingOn(this._dataBundle.getBoolean(43, false));
                                                                break;
                                                            case 44:
                                                                this.mHvacState.notifyBackDefrosterActive(this._dataBundle.getInt(44, -1));
                                                                break;
                                                            case 45:
                                                                this.mHvacState.notifyDisplayActive(this._dataBundle.getInt(45, -1));
                                                                break;
                                                            case 46:
                                                                this.mHvacState.notifyRightVentilationLevel(this._dataBundle.getInt(key, -1));
                                                                break;
                                                            case 47:
                                                                this.mHvacState.notifyAmbientTemperature(this._dataBundle.getInt(47, -1));
                                                                break;
                                                            case 48:
                                                                if (AcModelHelper.getInstance(this.mContext).hasADS()) {
                                                                    this.mHvacState.notifyADS(this._dataBundle.getInt(48, -1));
                                                                }
                                                                break;
                                                            default:
                                                                switch (key) {
                                                                    case 63:
                                                                        if (AcModelHelper.getInstance(this.mContext).isAcMultipleTemp()) {
                                                                            this.mHvacState.notifyAcMultipleTemp(this._dataBundle.getBoolean(63, false));
                                                                        }
                                                                        break;
                                                                    case 64:
                                                                        if (AcModelHelper.getInstance(this.mContext).hasAcThirdPanel()) {
                                                                            if (this._dataBundle.getInt(64, -1) == 0) {
                                                                                this.mHvacState.notifyAcThirdPanel(true);
                                                                            } else if (this._dataBundle.getInt(64, -1) == 1) {
                                                                                this.mHvacState.notifyAcThirdPanel(false);
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 65:
                                                                        if (AcModelHelper.getInstance(this.mContext).hasRCPSet()) {
                                                                            if (this._dataBundle.getInt(65, -1) == 0) {
                                                                                this.mHvacState.notifyAcRcp(true);
                                                                            } else if (this._dataBundle.getInt(65, -1) == 1) {
                                                                                this.mHvacState.notifyAcRcp(false);
                                                                            }
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
                            } else if (OfflineConfig.getE01OfflineConfig(227) == 0) {
                                this.mHvacState.notifyRightHeatingLevel(this._dataBundle.getInt(key, -1));
                            }
                        } else if (OfflineConfig.getE01OfflineConfig(227) == 0) {
                            this.mHvacState.notifyLeftHeatingLevel(this._dataBundle.getInt(key, -1));
                        }
                    } else if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                        this.mHvacState.notifyFraganceType(this._dataBundle.getInt(61, -1));
                    }
                } else if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                    this.mHvacState.notifyFraganceLevel(this._dataBundle.getInt(60, -1));
                }
            } else if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                this.mHvacState.notifyFragance(this._dataBundle.getInt(50, -1));
                if (1 == this._dataBundle.getInt(50, -1)) {
                    this.isFragranceOn = true;
                } else if (this._dataBundle.getInt(50, -1) == 0) {
                    this.isFragranceOn = false;
                }
            }
        }
    }

    public void playBeepSound() {
        ((AudioManager) this.mContext.getSystemService(Car.AUDIO_SERVICE)).playSoundEffect(0);
    }

    public void updatePepsState() {
        Log.d(TAG, "updatePepsState--mPepsTimeOut=" + this.mPepsTimeOut + ",mPepsValidity=" + this.mPepsValidity + ",mPepsMode=" + this.mPepsMode);
        if (this.mPepsTimeOut == 1) {
            sendMessage(2, false);
            return;
        }
        if (this.mPepsValidity == 2) {
            int i = this.mPepsMode;
            if (i == 2 || i == 4) {
                sendMessage(2, true);
                return;
            } else {
                sendMessage(2, false);
                return;
            }
        }
        sendMessage(2, false);
    }

    private void updateAcData(Keys keys) {
        int size = keys.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int key = keys.getKey(i);
            if (key == 3) {
                int i2 = this._dataBundle.getInt(key, -1);
                this.mHvacState.notifyLeftTemperature(i2);
                if (!AcModelHelper.getInstance(this.mContext).hasDual()) {
                    this.mHvacState.notifyRightTemperature(i2);
                }
            } else if (key == 4) {
                this.mHvacState.notifyRightTemperature(this._dataBundle.getInt(key, -1));
            } else if (key == 5) {
                this.mHvacState.notifyIonsMode(this._dataBundle.getInt(5, -1));
            } else if (key == 6) {
                this.mHvacState.notifyLoopMode(this._dataBundle.getBoolean(6, false));
            } else if (key != 34) {
                if (key != 50) {
                    if (key == 69) {
                        this.mHvacState.notifyAcPTCHeat(this._dataBundle.getBoolean(69, false));
                    } else if (key != 60) {
                        if (key != 61) {
                            if (key == 72) {
                                this.mHvacState.notifyAcEco(this._dataBundle.getBoolean(72, false));
                            } else if (key != 73) {
                                switch (key) {
                                    case 8:
                                        this.mHvacState.notifyAcMaxOn(this._dataBundle.getBoolean(8, false));
                                        break;
                                    case 9:
                                        this.mHvacState.notifyAcAutoOn(this._dataBundle.getBoolean(9, false));
                                        break;
                                    case 10:
                                        this.mHvacState.notifyDualOn(this._dataBundle.getBoolean(10, false));
                                        break;
                                    case 11:
                                        this.mHvacState.notifyBlowingMode(this._dataBundle.getInt(11, -1));
                                        break;
                                    case 12:
                                        this.mHvacState.notifyBlowingLevel(this._dataBundle.getInt(12, -1));
                                        break;
                                    default:
                                        switch (key) {
                                            case 14:
                                                this.mHvacState.notifyAcOn(this._dataBundle.getBoolean(14, false));
                                                break;
                                            case 15:
                                                this.mHvacState.notifyRearLock(this._dataBundle.getBoolean(15, false));
                                                break;
                                            case 16:
                                                this.mHvacState.notifyHvacOn(this._dataBundle.getBoolean(key, false));
                                                break;
                                            default:
                                                switch (key) {
                                                    case 24:
                                                        this.mHvacState.notifyLeftHeatingLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    case 25:
                                                        this.mHvacState.notifyRightHeatingLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    case 26:
                                                        this.mHvacState.notifyLeftVentilationLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    case 27:
                                                        this.mHvacState.notifyLeftMassageLevel(this._dataBundle.getInt(key, -1));
                                                        break;
                                                    default:
                                                        switch (key) {
                                                            case 42:
                                                                this.mHvacState.notifyRearDefrostingOn(this._dataBundle.getBoolean(42, false));
                                                                break;
                                                            case 43:
                                                                this.mHvacState.notifyFrontDefrostingOn(this._dataBundle.getBoolean(43, false));
                                                                break;
                                                            case 44:
                                                                this.mHvacState.notifyBackDefrosterActive(this._dataBundle.getInt(44, -1));
                                                                break;
                                                            case 45:
                                                                this.mHvacState.notifyDisplayActive(this._dataBundle.getInt(45, -1));
                                                                break;
                                                            case 46:
                                                                this.mHvacState.notifyRightVentilationLevel(this._dataBundle.getInt(key, -1));
                                                                break;
                                                            case 47:
                                                                this.mHvacState.notifyAmbientTemperature(this._dataBundle.getInt(47, -1));
                                                                break;
                                                            case 48:
                                                                if (AcModelHelper.getInstance(this.mContext).hasADS()) {
                                                                    this.mHvacState.notifyADS(this._dataBundle.getInt(48, -1));
                                                                }
                                                                break;
                                                            default:
                                                                switch (key) {
                                                                    case 63:
                                                                        if (AcModelHelper.getInstance(this.mContext).isAcMultipleTemp()) {
                                                                            this.mHvacState.notifyAcMultipleTemp(this._dataBundle.getBoolean(63, false));
                                                                        }
                                                                        break;
                                                                    case 64:
                                                                        if (AcModelHelper.getInstance(this.mContext).hasAcThirdPanel()) {
                                                                            if (this._dataBundle.getInt(64, -1) == 0) {
                                                                                this.mHvacState.notifyAcThirdPanel(true);
                                                                            } else if (this._dataBundle.getInt(64, -1) == 1) {
                                                                                this.mHvacState.notifyAcThirdPanel(false);
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 65:
                                                                        if (AcModelHelper.getInstance(this.mContext).hasRCPSet()) {
                                                                            if (this._dataBundle.getInt(65, -1) == 0) {
                                                                                this.mHvacState.notifyAcRcp(true);
                                                                            } else if (this._dataBundle.getInt(65, -1) == 1) {
                                                                                this.mHvacState.notifyAcRcp(false);
                                                                            }
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (key) {
                                                                            case 75:
                                                                                if (OfflineConfig.getE01OfflineConfig(227) == 0) {
                                                                                    this.mHvacState.notifyLeftHeatingLevel(this._dataBundle.getInt(key, -1));
                                                                                }
                                                                                break;
                                                                            case 76:
                                                                                if (OfflineConfig.getE01OfflineConfig(227) == 0) {
                                                                                    this.mHvacState.notifyRightHeatingLevel(this._dataBundle.getInt(key, -1));
                                                                                }
                                                                                break;
                                                                            case 77:
                                                                                this.mHvacState.notifyIsAcLostConnection(this._dataBundle.getBoolean(77, false));
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
                                this.mHvacState.notifyAcSeatDisplay(this._dataBundle.getBoolean(73, false));
                            }
                        } else if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                            this.mHvacState.notifyFraganceType(this._dataBundle.getInt(61, -1));
                        }
                    } else if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                        this.mHvacState.notifyFraganceLevel(this._dataBundle.getInt(60, -1));
                    }
                } else if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                    this.mHvacState.notifyFragance(this._dataBundle.getInt(50, -1));
                    if (1 == this._dataBundle.getInt(50, -1)) {
                        this.isFragranceOn = true;
                    } else if (this._dataBundle.getInt(50, -1) == 0) {
                        this.isFragranceOn = false;
                    }
                }
            } else {
                this.mHvacState.notifyParkingSwitch(this._dataBundle.getBoolean(34, false));
            }
            z = true;
        }
        if (z) {
            sendMessage(1);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int getDisplayWindowType(String str, int i) {
        Log.d(TAG, "getDisplayWindowType data=" + i);
        switch (i) {
            case 1:
            case 2:
            case 6:
            case 7:
            case 8:
                if (str.equals(VoiceAssistantPolicy.NL5HH) || str.equals("NL3B")) {
                }
                break;
            case 13:
                if (!str.equals(VoiceAssistantPolicy.NL5QJ) && !str.equals(VoiceAssistantPolicy.NL5HH)) {
                    if (str.equals("NL3B")) {
                    }
                }
                break;
            case 14:
                if (!str.equals(VoiceAssistantPolicy.NL5QJ) && !str.equals(VoiceAssistantPolicy.NL5HH)) {
                    if (str.equals("NL3B")) {
                    }
                }
                break;
            case 15:
                if (!str.equals(VoiceAssistantPolicy.NL5QJ) && !str.equals(VoiceAssistantPolicy.NL5HH)) {
                    if (str.equals("NL3B")) {
                    }
                    break;
                }
                break;
            case 16:
                if (!str.equals(VoiceAssistantPolicy.NL5QJ) && !str.equals(VoiceAssistantPolicy.NL5HH)) {
                    if (str.equals("NL3B")) {
                    }
                    break;
                }
                break;
        }
        return 2;
    }

    private void updateAqsData(Keys keys) {
        int size = keys.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int key = keys.getKey(i);
            if (key == 7) {
                this.mAqsState.notifyAqsOn(this._dataBundle.getBoolean(7, false));
            } else if (key == 17) {
                this.mAqsState.notifyPmInsideValue(this._dataBundle.getInt(17, -1));
            } else if (key == 19) {
                this.mAqsState.notifyPmOutsideValue(this._dataBundle.getInt(19, -1));
            } else if (key == 70) {
                this.mAqsState.notifyAcCO2Value(this._dataBundle.getInt(70, -1));
            } else if (key != 71) {
                switch (key) {
                    case 21:
                        this.mAqsState.notifyPM25AQILevel(this._dataBundle.getInt(21, -1));
                        break;
                    case 22:
                        this.mAqsState.notifyAmbientPM25AQILevel(this._dataBundle.getInt(22, -1));
                        break;
                    case 23:
                        int i2 = this._dataBundle.getInt(23, -1);
                        this.mAqsState.notifyPmSensorState(i2);
                        if (i2 == 1 || i2 == 2) {
                            this.mAqsState.notifyPmWaring(true);
                        } else if (i2 == 3) {
                            this.mAqsState.notifyPmWaring(false);
                        }
                        break;
                }
            } else {
                this.mAqsState.notifyAcCO2Level(this._dataBundle.getInt(71, -1));
            }
            z = true;
        }
        if (z) {
            sendMessage(3);
        }
    }
}
