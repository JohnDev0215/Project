package com.ts.qnxapp.util;

import android.os.Bundle;
import bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr;
import ipc.fdbus.FdbusMessage;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TemperatureToBundle {
    private static final int MAX_MODULE_NUMBER = 100;
    private static final String MSG_TYPE = "msgType";
    private static final int NTF_AMP_PERI = 17;
    private static final int NTF_CPU = 2;
    private static final int NTF_DIODE = 20;
    private static final int NTF_PWR_MAIN = 25;
    private static final String SENSOR_ID = "sensorId";
    private static final String SENSOR_NAME = "sensorName";
    public static final int SUBTYPE_EVT_COM_MOD_INFO = 102;
    public static final int SUBTYPE_TEMPERATURE = 101;
    private static final String SUB_TYPE = "subtype";
    private static final String TAG = "TemperatureToBundle";
    private static final String TEMPERATURE_VALUE = "temperatureValue";
    private static final String TFT_LCD_TEMP = "tftLcdTemp";
    private static final String TFT_MODULE_NAME = "DISP_IP";
    private static final String TFT_PCB_TEMP = "tftPcmTemp";
    private static final String THERMAL_LEVEL = "thermalLevel";
    private static volatile TemperatureToBundle sInstance;

    public static TemperatureToBundle getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (TemperatureToBundle.class) {
            if (sInstance == null) {
                sInstance = new TemperatureToBundle();
            }
        }
        return sInstance;
    }

    public Bundle messageTemperatureToBundle(FdbusMessage msg, int flag) {
        Bundle bundle = new Bundle();
        int i = 2;
        if (flag == 2) {
            int iCode = msg.code();
            if (iCode == 1) {
                bundle.putInt(MSG_TYPE, 10);
                bundle.putInt(SUB_TYPE, SUBTYPE_EVT_COM_MOD_INFO);
                try {
                    ComBoschCmPlatformNotifCtr.ModuleInfo moduleInfo = ComBoschCmPlatformNotifCtr.ModuleInfo.parseFrom(msg.byteArray());
                    String name = moduleInfo.getName();
                    if (name.equals(TFT_MODULE_NAME)) {
                        if (moduleInfo.hasLcdTemp()) {
                            String lcdTemp = moduleInfo.getLcdTemp();
                            bundle.putString(TFT_LCD_TEMP, lcdTemp);
                        }
                    } else {
                        return null;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (iCode == 458753) {
                bundle.putInt(MSG_TYPE, 10);
                bundle.putInt(SUB_TYPE, SUBTYPE_TEMPERATURE);
                try {
                    ComBoschCmPlatformNotifCtr.NtfThermalInfoMsg ntfThermalInfoMsg = ComBoschCmPlatformNotifCtr.NtfThermalInfoMsg.parseFrom(msg.byteArray());
                    int itemsCount = ntfThermalInfoMsg.getItemsCount();
                    ArrayList<Integer> sensorId = new ArrayList<>();
                    ArrayList<String> sensorName = new ArrayList<>();
                    float[] temperatureValues = new float[4];
                    ArrayList<Integer> thermalLevel = new ArrayList<>();
                    int needIndex = 0;
                    int i2 = 0;
                    while (i2 < itemsCount) {
                        ComBoschCmPlatformNotifCtr.NtfThermalInfoItem ntfThermalInfoMsgItems = ntfThermalInfoMsg.getItems(i2);
                        ComBoschCmPlatformNotifCtr.NtfTempSensor_t ntftempId = ntfThermalInfoMsgItems.getNtftempId();
                        int ntftempIdNumber = ntftempId.getNumber();
                        if (ntftempIdNumber == 17 || ntftempIdNumber == i || ntftempIdNumber == 25 || ntftempIdNumber == 20) {
                            sensorId.add(Integer.valueOf(ntftempId.getNumber()));
                            String ntftempName = ntfThermalInfoMsgItems.getNtftempName();
                            sensorName.add(ntftempName);
                            float ntftempValue = ntfThermalInfoMsgItems.getNtftempValue();
                            temperatureValues[needIndex] = ntftempValue;
                            needIndex++;
                            ComBoschCmPlatformNotifCtr.NtfTempLevel_t ntftempLevel = ntfThermalInfoMsgItems.getNtftempLevel();
                            thermalLevel.add(Integer.valueOf(ntftempLevel.getNumber()));
                        }
                        i2++;
                        i = 2;
                    }
                    bundle.putIntegerArrayList(SENSOR_ID, sensorId);
                    bundle.putStringArrayList(SENSOR_NAME, sensorName);
                    bundle.putFloatArray(TEMPERATURE_VALUE, temperatureValues);
                    bundle.putIntegerArrayList(THERMAL_LEVEL, thermalLevel);
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        return bundle;
    }
}
