package com.ts.carplay.lib.iap2manager;

import android.content.Context;
import android.os.SystemClock;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayLocationDataManager {
    public static final int IAP_INFO_TYPE_START_LOCATION_INFO = 1;
    public static final int IAP_INFO_TYPE_STOP_LOCATION_INFO = 2;
    public static final int LOCATIONDM_DATA0 = 0;
    public static final int NINE = 9;
    public static final int NMEA_SENTENCE_TYPE_GPGGA = 1;
    public static final int NMEA_SENTENCE_TYPE_GPRMC = 2;
    public static final int NMEA_SENTENCE_TYPE_NULL = 0;
    public static final int NMEA_SENTENCE_TYPE_PASCD = 8;
    public static final int ONE_THOUSAND = 1000;
    private static final char PASCD_END = '*';
    private static final int PASCD_INDEX_SIX = 6;
    private static final int PASCD_INDEX_ZERO = 0;
    private static final char PASCD_START = '$';
    private static final String SAMPLE_COUNT = ",10";
    private static final String SENSOR_TYPE = ",C,";
    private static final String SENTENCE_ID = "PASCD,";
    public static final int SIXTEEN = 16;
    private static final int SLEEP_TIME = 99;
    private static final String SLIP_DETECT = ",0";
    private static final String TAG = "CarPlayLocationDataManager";
    public static final int TEN = 10;
    private CarPlayManager mManager;
    private String[] timeOffSet = {",0.00,", ",0.10,", ",0.20,", ",0.30,", ",0.40,", ",0.50,", ",0.60,", ",0.70,", ",0.80,", ",0.90,"};
    private SendLocationDataThread mSendLocationDataThread = null;
    private int mDataTypeMask = 0;
    private long mTimeMillsTemp = 0;
    private float mSpeed = 0.0f;
    private int mGearMode = 2;
    private String mGpgga = "";
    private String mGprmc = "";

    static /* synthetic */ int access$076(CarPlayLocationDataManager x0, int x1) {
        int i = x0.mDataTypeMask | x1;
        x0.mDataTypeMask = i;
        return i;
    }

    public void init(Context context) {
    }

    public CarPlayLocationDataManager(CarPlayManager manager) {
        this.mManager = null;
        this.mManager = manager;
    }

    public void onSessionEstablished() {
        LogUtil.info(TAG, "CarPlayLocationDataManager onSessionEstablished");
    }

    public void onSessionDestoryed() {
        LogUtil.info(TAG, "CarPlayLocationDataManager onSessionDestoryed");
        SendLocationDataThread sendLocationDataThread = this.mSendLocationDataThread;
        if (sendLocationDataThread != null) {
            sendLocationDataThread.stopThread();
            this.mSendLocationDataThread = null;
        }
        this.mDataTypeMask = 0;
    }

    public void locationEventHandle(int event, int data) {
        LogUtil.info(TAG, "location event handler, event: " + event + ", data:" + data);
        if (event == 1) {
            SendLocationDataThread sendLocationDataThread = this.mSendLocationDataThread;
            if (sendLocationDataThread != null) {
                sendLocationDataThread.stopThread();
            }
            long currentTime = System.currentTimeMillis();
            long upTime = SystemClock.uptimeMillis();
            this.mTimeMillsTemp = currentTime - upTime;
            SendLocationDataThread sendLocationDataThread2 = new SendLocationDataThread();
            this.mSendLocationDataThread = sendLocationDataThread2;
            sendLocationDataThread2.start();
            this.mDataTypeMask = data;
            SendLocationDataThread sendLocationDataThread3 = this.mSendLocationDataThread;
            if (sendLocationDataThread3 != null) {
                sendLocationDataThread3.setDataType(data);
                return;
            }
            return;
        }
        if (event == 2) {
            SendLocationDataThread sendLocationDataThread4 = this.mSendLocationDataThread;
            if (sendLocationDataThread4 != null) {
                sendLocationDataThread4.clearDataType(data);
            }
            SendLocationDataThread sendLocationDataThread5 = this.mSendLocationDataThread;
            if (sendLocationDataThread5 != null) {
                sendLocationDataThread5.stopThread();
                this.mSendLocationDataThread = null;
            }
        }
    }

    public class SendLocationDataThread extends Thread {
        private long mSendTime = 0;
        private boolean mRunningFlag = true;
        private volatile StringBuilder mPascd = new StringBuilder();

        public SendLocationDataThread() {
        }

        public void stopThread() {
            this.mRunningFlag = false;
        }

        public boolean isRunning() {
            return this.mRunningFlag;
        }

        public void setDataType(int type) {
            LogUtil.info(CarPlayLocationDataManager.TAG, "setDataType, type:" + type);
            CarPlayLocationDataManager.access$076(CarPlayLocationDataManager.this, type);
        }

        public void clearDataType(int type) {
            LogUtil.info(CarPlayLocationDataManager.TAG, "clearDataType, type:" + type);
            CarPlayLocationDataManager.this.mDataTypeMask = type;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.mRunningFlag && !Thread.currentThread().isInterrupted()) {
                if (this.mSendTime == 0) {
                    this.mSendTime = CarPlayLocationDataManager.this.getCurrentTimemillis();
                }
                this.mPascd.delete(0, this.mPascd.length());
                StringBuilder sb = this.mPascd;
                sb.append(CarPlayLocationDataManager.SENTENCE_ID);
                sb.append(CarPlayLocationDataManager.SENSOR_TYPE);
                sb.append(CarPlayLocationDataManager.this.getTransmissionState());
                sb.append(CarPlayLocationDataManager.SLIP_DETECT);
                sb.append(CarPlayLocationDataManager.SAMPLE_COUNT);
                for (int i = 0; i < 10; i++) {
                    StringBuilder sb2 = this.mPascd;
                    sb2.append(CarPlayLocationDataManager.this.timeOffSet[i]);
                    sb2.append(CarPlayLocationDataManager.this.getSpeed());
                    this.mSendTime += 99;
                    if (!this.mRunningFlag) {
                        break;
                    }
                    try {
                        long current = CarPlayLocationDataManager.this.getCurrentTimemillis();
                        if (current < this.mSendTime) {
                            Thread.sleep(this.mSendTime - current);
                        }
                    } catch (InterruptedException exception) {
                        LogUtil.error(CarPlayLocationDataManager.TAG, "get speed failed: " + exception.toString());
                        exception.printStackTrace();
                    }
                }
                if (this.mRunningFlag && CarPlayLocationDataManager.this.mDataTypeMask != 0 && (CarPlayLocationDataManager.this.mDataTypeMask & 8) == 8) {
                    this.mPascd.insert(6, CarPlayLocationDataManager.this.getCurrentSeconds(this.mSendTime));
                    String exclusiveorNumber = CarPlayLocationDataManager.this.getExclusiveOrNumber(this.mPascd.toString());
                    StringBuilder sbInsert = this.mPascd.insert(0, CarPlayLocationDataManager.PASCD_START);
                    sbInsert.append(CarPlayLocationDataManager.PASCD_END);
                    sbInsert.append(exclusiveorNumber);
                    CarPlayLocationDataManager.this.mManager.onSendLocationInformation(this.mPascd.toString());
                }
            }
        }
    }

    public void setGpgga(String gpgga) {
        this.mGpgga = gpgga;
        if ((this.mDataTypeMask & 1) == 1) {
            CarPlayManager carPlayManager = this.mManager;
            if (carPlayManager != null) {
                carPlayManager.onSendLocationInformation(gpgga);
            } else {
                LogUtil.warning(TAG, "setGpgga mManager null");
            }
        }
    }

    public void setGprmc(String gprmc) {
        this.mGprmc = gprmc;
        if ((this.mDataTypeMask & 2) == 2) {
            CarPlayManager carPlayManager = this.mManager;
            if (carPlayManager != null) {
                carPlayManager.onSendLocationInformation(gprmc);
            } else {
                LogUtil.warning(TAG, "setGprmc mManager null");
            }
        }
    }

    public String getGpppa() {
        return this.mGpgga;
    }

    public String getGprmc() {
        return this.mGprmc;
    }

    public void setSpeed(float vehicleSpeed) {
        this.mSpeed = vehicleSpeed;
    }

    public synchronized String getSpeed() {
        return String.format(Locale.ENGLISH, "%.3f", Float.valueOf(this.mSpeed));
    }

    public String getCurrentSeconds(long now) {
        String seconds = new String();
        try {
            SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
            float currentSeconds = (now - sdfOne.parse(sdfOne.format(Long.valueOf(now))).getTime()) / 1000.0f;
            seconds = String.valueOf(currentSeconds);
            for (int i = 0; i < seconds.length(); i++) {
                if ('.' == seconds.charAt(i)) {
                    int decimalNumber = (seconds.length() - 1) - i;
                    if (decimalNumber == 0) {
                        seconds = seconds + ".000";
                    } else if (decimalNumber == 1) {
                        seconds = seconds + "00";
                    } else if (decimalNumber == 2) {
                        seconds = seconds + "0";
                    }
                }
            }
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return seconds;
    }

    public void setTransmissionState(int gearMode) {
        this.mGearMode = gearMode;
    }

    public char getTransmissionState() {
        char state;
        int i = this.mGearMode;
        if (i == 0) {
            state = 'N';
        } else if (i == 1) {
            state = 'D';
        } else if (i == 2) {
            state = 'P';
        } else if (i == 3) {
            state = 'R';
        } else {
            state = 'U';
        }
        LogUtil.verbose(TAG, "TransmissionState: " + state);
        return state;
    }

    public String getExclusiveOrNumber(String pascd) {
        int number = 0;
        byte[] bys = pascd.getBytes();
        for (byte b : bys) {
            number ^= b;
        }
        String str = decimalToHex(number);
        if (str.length() == 1) {
            return "0" + str;
        }
        if (str.length() == 0) {
            return "00";
        }
        return str;
    }

    public long getCurrentTimemillis() {
        long timeMills = SystemClock.uptimeMillis();
        return this.mTimeMillsTemp + timeMills;
    }

    public String decimalToHex(int decimal) {
        String hex = "";
        while (decimal != 0) {
            int hexValue = decimal % 16;
            hex = toHexChar(hexValue) + hex;
            decimal /= 16;
        }
        return hex;
    }

    public char toHexChar(int hexValue) {
        if (hexValue <= 9 && hexValue >= 0) {
            return (char) (hexValue + 48);
        }
        return (char) ((hexValue - 10) + 65);
    }
}
