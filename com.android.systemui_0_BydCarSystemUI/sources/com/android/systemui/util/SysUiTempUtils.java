package com.android.systemui.util;

import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import androidx.exifinterface.media.ExifInterface;
import com.android.systemui.Constants;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.statusbar.wm.TempData;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SysUiTempUtils {
    private static volatile SysUiTempUtils sysUiTempUtils;
    private final String[] windLevelStr = {"1", "2", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7"};
    private final String[] tempCentigrade = {"Lo", "18", "19", "20", "21", "22", "23", TimeManagerImpl.TIME_FORMAT_TWENTY_FOUR, "25", "26", "27", "28", "29", "30", "31", "32", "Hi"};
    private final String[] tempFahrenheit = {"Lo", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "Hi"};

    private SysUiTempUtils() {
    }

    public static synchronized SysUiTempUtils getInstance() {
        if (sysUiTempUtils == null) {
            synchronized (SysUiTempUtils.class) {
                if (sysUiTempUtils == null) {
                    sysUiTempUtils = new SysUiTempUtils();
                }
            }
        }
        return sysUiTempUtils;
    }

    public String getWindLevelStr(int index) {
        String[] strArr = this.windLevelStr;
        if (index >= strArr.length) {
            return null;
        }
        return strArr[index];
    }

    public int getWindLevelSize() {
        return this.windLevelStr.length;
    }

    private int getAcHeatingButtonConfig() {
        return BYDAutoAcDevice.getInstance(SystemUIApplication.getInstance().getApplicationContext()).getAcHeatingButtonConfig();
    }

    private int getTempRegulatingAccuracy() {
        return BYDAutoAcDevice.getInstance(SystemUIApplication.getInstance().getApplicationContext()).getTempRegulatingAccuracy();
    }

    public String[] getTemps(int unit) {
        int acHeatingButtonConfig = getAcHeatingButtonConfig();
        int tempRegulatingAccuracy = getTempRegulatingAccuracy();
        BydLogHelper.logD("acHeatingButtonConfig = " + acHeatingButtonConfig + " ,tempRegulatingAccuracy = " + tempRegulatingAccuracy);
        if (unit == 0) {
            if (tempRegulatingAccuracy == 2) {
                if (acHeatingButtonConfig == 0) {
                    return Constants.tempF05SupportAcHeatValue;
                }
                return Constants.tempF05NoSupportAcHeatValue;
            }
            if (acHeatingButtonConfig == 0) {
                return Constants.tempF1SupportAcHeatValue;
            }
            return Constants.tempF1NoSupportAcHeatValue;
        }
        if (tempRegulatingAccuracy == 2) {
            if (acHeatingButtonConfig == 0) {
                return Constants.tempC05SupportAcHeatValue;
            }
            return Constants.tempC05NoSupportAcHeatValue;
        }
        if (acHeatingButtonConfig == 0) {
            return Constants.tempC1SupportAcHeatValue;
        }
        return Constants.tempC1NoSupportAcHeatValue;
    }

    public int[] getTempsKey(int unit) {
        int acHeatingButtonConfig = getAcHeatingButtonConfig();
        int tempRegulatingAccuracy = getTempRegulatingAccuracy();
        BydLogHelper.logD("acHeatingButtonConfig = " + acHeatingButtonConfig + " ,tempRegulatingAccuracy = " + tempRegulatingAccuracy);
        if (unit == 0) {
            if (tempRegulatingAccuracy == 2) {
                if (acHeatingButtonConfig == 0) {
                    return Constants.tempF05SupportAcHeatKey;
                }
                return Constants.tempF05NoSupportAcHeatKey;
            }
            if (acHeatingButtonConfig == 0) {
                return Constants.tempF1SupportAcHeatKey;
            }
            return Constants.tempF1NoSupportAcHeatKey;
        }
        if (tempRegulatingAccuracy == 2) {
            if (acHeatingButtonConfig == 0) {
                return Constants.tempC05SupportAcHeatKey;
            }
            return Constants.tempC05NoSupportAcHeatKey;
        }
        if (acHeatingButtonConfig == 0) {
            return Constants.tempC1SupportAcHeatKey;
        }
        return Constants.tempC1NoSupportAcHeatKey;
    }

    public int[] getTempsKeyReverse(int unit) {
        int[] tempsKey = getTempsKey(unit);
        int length = tempsKey.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = tempsKey[(tempsKey.length - 1) - i];
        }
        return iArr;
    }

    public List<TempData> getTempDatas(int unit) {
        ArrayList arrayList = new ArrayList();
        String[] temps = getTemps(unit);
        int[] tempsKey = getTempsKey(unit);
        for (int length = temps.length - 1; length >= 0; length--) {
            arrayList.add(new TempData(temps[length], tempsKey[length]));
        }
        return arrayList;
    }
}
