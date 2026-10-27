package com.byd.bydnaviapi;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.util.Log;
import com.neusoft.na.common.FileUtils;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class BydAutoNaviDevice extends AbsBYDAutoDevice {
    public static final int MAP_STATE_FULL_CLOSE = 3;
    public static final int MAP_STATE_FULL_OPEN = 6;
    public static final int MAP_STATE_NAVI_CLOSE = 1;
    public static final int MAP_STATE_NOT_ACTIVATION = 8;
    public static final int MAP_STATE_NOT_AGREE_AGREEMENT = 9;
    public static final int MAP_STATE_SIMPLE_CLOSE = 4;
    public static final int MAP_STATE_SIMPLE_OPEN = 7;
    public static final int MAP_STATE_SMALL_CLOSE = 2;
    public static final int MAP_STATE_SMALL_OPEN = 5;
    private static final int NAVIDEVICE_COMMAND_BUSY = -2147482647;
    private static final int NAVIDEVICE_COMMAND_FAIL = -2147482648;
    private static final int NAVIDEVICE_COMMAND_INVALID = -2147482645;
    private static final int NAVIDEVICE_COMMAND_SUCCESS = 0;
    private static final int NAVIDEVICE_COMMAND_TIMEOUT = -2147482646;
    private static final String TAG = "BydAutoNaviDevice_api_0.2.35";
    public static final int UISTATE_FULL_MAP_TRANS = 1;
    public static final int UISTATE_FULL_MAP_TRANS_NOT = 2;
    public static final int UISTATE_MAP_CLOSE = 7;
    public static final int UISTATE_SIMPLE_MAP_TRANS = 5;
    public static final int UISTATE_SIMPLE_MAP_TRANS_NOT = 6;
    public static final int UISTATE_SMALL_MAP_TRANS = 3;
    public static final int UISTATE_SMALL_MAP_TRANS_NOT = 4;
    private static BydAutoNaviDevice sDevice;
    private final AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener;
    private final ArrayList<AbsBYDAutoNaviListener> absBYDAutoNaviListeners;
    private final BYDAutoInstrumentDevice bydAutoInstrumentDevice;
    private final BYDAutoWhudDevice bydAutoWhudDevice;

    public enum Unit {
        UNIT_INVALID,
        UNIT_KM,
        UNIT_MILES,
        UNIT_M,
        UNIT_YARDS,
        UNIT_FT
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    BydAutoNaviDevice(Context con) {
        super(con.getApplicationContext());
        this.absBYDAutoNaviListeners = new ArrayList<>();
        this.absBYDAutoInstrumentListener = new AbsBYDAutoInstrumentListener() { // from class: com.byd.bydnaviapi.BydAutoNaviDevice.1
            @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
            public void onInsUiChanged(int uiState) {
                super.onInsUiChanged(uiState);
                synchronized (BydAutoNaviDevice.this.absBYDAutoNaviListeners) {
                    for (int i = 0; i < BydAutoNaviDevice.this.absBYDAutoNaviListeners.size(); i++) {
                        if (BydAutoNaviDevice.this.absBYDAutoNaviListeners.get(i) != null) {
                            ((AbsBYDAutoNaviListener) BydAutoNaviDevice.this.absBYDAutoNaviListeners.get(i)).onInsUiChanged(uiState);
                        }
                    }
                }
            }

            @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
            public void onMapFocusChanged(int mapType) {
                super.onMapFocusChanged(mapType);
                synchronized (BydAutoNaviDevice.this.absBYDAutoNaviListeners) {
                    for (int i = 0; i < BydAutoNaviDevice.this.absBYDAutoNaviListeners.size(); i++) {
                        if (BydAutoNaviDevice.this.absBYDAutoNaviListeners.get(i) != null) {
                            ((AbsBYDAutoNaviListener) BydAutoNaviDevice.this.absBYDAutoNaviListeners.get(i)).onMapFocusChanged(mapType);
                        }
                    }
                }
            }

            @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
            public void onINSNavDisplayMode(int value) {
                super.onINSNavDisplayMode(value);
                synchronized (BydAutoNaviDevice.this.absBYDAutoNaviListeners) {
                    for (int i = 0; i < BydAutoNaviDevice.this.absBYDAutoNaviListeners.size(); i++) {
                        if (BydAutoNaviDevice.this.absBYDAutoNaviListeners.get(i) != null) {
                            ((AbsBYDAutoNaviListener) BydAutoNaviDevice.this.absBYDAutoNaviListeners.get(i)).onINSNavDisplayMode(value);
                        }
                    }
                }
            }
        };
        Log.e(TAG, "BydAutoNaviDevice packageName : " + con.getPackageName());
        this.bydAutoWhudDevice = BYDAutoWhudDevice.getInstance(con);
        this.bydAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(con);
    }

    public static synchronized BydAutoNaviDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BydAutoNaviDevice(con);
        }
        return sDevice;
    }

    public int sendTbtNaviInfo(int mapType, int nextTbtIcon, String nextRoadName, float nextRoadDist, int nextDistUnit, int nextNextTbtIcon, float nextNextRoadDist, int nextNextDistUnit, float distToDest, int distToDestUnit, int toDestTime, int toDestDays, int toDestHours, int toDestMinutes, int toDestSeconds) {
        Log.d(TAG, "sendTbtNaviInfo mapType : " + mapType + " , nextTbtIcon : " + nextTbtIcon + " , nextRoadName : " + nextRoadName + " , nextRoadDist : " + nextRoadDist + " , nextDistUnit : " + nextDistUnit + " , nextNextTbtIcon : " + nextNextTbtIcon + " , nextNextRoadDist : " + nextNextRoadDist + " , nextNextDistUnit : " + nextNextDistUnit + " , distToDest : " + distToDest + " , distToDestUnit : " + distToDestUnit + " , toDestTime : " + toDestTime + " , toDestDays : " + toDestDays + " , toDestHours : " + toDestHours + " , toDestMinutes : " + toDestMinutes + " , toDestSeconds : " + toDestSeconds);
        try {
            this.bydAutoInstrumentDevice.sendTbtNaviInfo(mapType, nextTbtIcon, nextRoadName, nextRoadDist, nextDistUnit, nextNextTbtIcon, nextNextRoadDist, nextNextDistUnit, distToDest, distToDestUnit, toDestTime, toDestDays, toDestHours, toDestMinutes);
            sendHudEasyNavGuideInfo(nextTbtIcon, nextRoadName, nextRoadDist, nextDistUnit);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    public int sendSimpleNaviInfo(int mapType, int nextTbtIcon, String nextRoadName, String nextRoadDistData, String nextRoadDistUnit, int nextNextTbtIcon, String nextNextDistData, String nextNextDistUnit, String distToDestDisData, String distToDestDisUnit, String distToDestTimeDayUnit, String distToDestTimeHourUnit, String distToDestTimeMinUnit, String distToDestTimeSecUnit, String distToDestTime, String targetDisUnit, String toDestDays, String toDestTime, String multipleText, String multipleText1, String multipleText2) {
        Log.d(TAG, "sendSimpleNaviInfo mapType : " + mapType + "\nnextTbtIcon : " + nextTbtIcon + "\nnextRoadName : " + nextRoadName + "\nnextRoadDistData : " + nextRoadDistData + "\nnextRoadDistUnit : " + nextRoadDistUnit + "\nnextNextTbtIcon : " + nextNextTbtIcon + "\nnextNextDistData : " + nextNextDistData + "\nnextNextDistUnit : " + nextNextDistUnit + "\ndistToDestDisData : " + distToDestDisData + "\ndistToDestDisUnit : " + distToDestDisUnit + "\ndistToDestTimeDayUnit : " + distToDestTimeDayUnit + "\ndistToDestTimeHourUnit : " + distToDestTimeHourUnit + "\ndistToDestTimeMinUnit : " + distToDestTimeMinUnit + "\ndistToDestTimeSecUnit : " + distToDestTimeSecUnit + "\ndistToDestTime : " + distToDestTime + "\ntargetDisUnit : " + targetDisUnit + "\ntoDestDays : " + toDestDays + "\ntoDestTime : " + toDestTime + "\nmultipleText : " + multipleText + "\nmultipleText1 : " + multipleText1 + "\nmultipleText2 : " + multipleText2);
        try {
            this.bydAutoInstrumentDevice.sendTbtSimpleNaviInfo(mapType, nextTbtIcon, nextRoadName, nextRoadDistData, nextRoadDistUnit, nextNextTbtIcon, nextNextDistData, nextNextDistUnit, distToDestDisData, distToDestDisUnit, distToDestTime, toDestDays, toDestTime, multipleText, multipleText1, multipleText2);
            this.bydAutoWhudDevice.setNextRoadDistanceUnitDataLen(nextRoadDistData + StringUtils.SPACE + nextRoadDistUnit);
            this.bydAutoWhudDevice.setNameNextRoad(nextRoadName);
            this.bydAutoWhudDevice.setEasyNavGuideInfo(nextTbtIcon, 0L, 0);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -2147482648;
        }
    }

    private void sendHudEasyNavGuideInfo(int nextTbtIcon, String nextRoadName, float nextRoadDist, int nextDistUnit) {
        Log.d(TAG, "sendHudEasyNavGuideInfo nextTbtIcon : " + nextTbtIcon + " , nextRoadName : " + nextRoadName + " , nextRoadDist : " + nextRoadDist + " , nextDistUnit : " + nextDistUnit);
        this.bydAutoWhudDevice.setNameNextRoad(nextRoadName);
        if (nextDistUnit == Unit.UNIT_KM.ordinal() || nextDistUnit == Unit.UNIT_M.ordinal()) {
            if (nextDistUnit == Unit.UNIT_KM.ordinal()) {
                nextRoadDist *= 1000.0f;
            }
            nextDistUnit = 1;
        } else if (nextDistUnit == Unit.UNIT_MILES.ordinal() || nextDistUnit == Unit.UNIT_FT.ordinal()) {
            if (nextDistUnit == Unit.UNIT_MILES.ordinal()) {
                nextRoadDist *= 5280.0f;
            }
            nextDistUnit = 2;
        } else if (nextDistUnit == Unit.UNIT_YARDS.ordinal()) {
            nextDistUnit = 3;
        }
        int i = (int) nextRoadDist;
        Log.d(TAG, "setEasyNavGuideInfo nextTbtIcon : " + nextTbtIcon + " , sendNextRoadDist : " + i + " , nextDistUnit : " + nextDistUnit);
        this.bydAutoWhudDevice.setEasyNavGuideInfo(nextTbtIcon, i, nextDistUnit);
    }

    public int sendNaviPresentationState(int mapType, int state) {
        Log.d(TAG, "sendNaviPresentationState mapType : " + mapType + " , state : " + state);
        try {
            return this.bydAutoInstrumentDevice.sendNaviPresentationState(mapType, state);
        } catch (Exception unused) {
            return -2147482648;
        }
    }

    public int requestMapFocus(int mapType) {
        Log.d(TAG, "requestMapFocus mapType : " + mapType);
        return this.bydAutoInstrumentDevice.requestMapFocus(mapType);
    }

    public int abandonMapFocus(int mapType) {
        Log.d(TAG, "abandonMapFocus mapType : " + mapType);
        return this.bydAutoInstrumentDevice.abandonMapFocus(mapType);
    }

    public int getFocusMapType() {
        return this.bydAutoInstrumentDevice.getFocusMapType();
    }

    public int sendNaviState(int mapType, int naviState) {
        Log.d(TAG, "sendNaviState mapType : " + mapType + " , naviState : " + naviState);
        return this.bydAutoInstrumentDevice.sendNaviState(mapType, naviState);
    }

    public int sendNaviStateOnlyCPAA(int mapType, int naviState) {
        Log.d(TAG, "sendNaviStateOnlyCPAA mapType : " + mapType + " , naviState : " + naviState);
        return this.bydAutoInstrumentDevice.sendNaviStateOnlyCPAA(mapType, naviState);
    }

    public int setOverseasMapSupplier(int state) {
        Log.d(TAG, "setOverseasMapSupplier state : " + state);
        return this.bydAutoWhudDevice.setOverseasMapSupplier(state);
    }

    public int getInsNavDisplayMode() {
        return this.bydAutoInstrumentDevice.getInsNaviDisplayMode();
    }

    public int setInsNavDisplayMode(int value) {
        Log.d(TAG, "setInsNavDisplayMode value : " + value);
        return this.bydAutoInstrumentDevice.setInsNaviDisplayMode(value);
    }

    public void registerListener(AbsBYDAutoNaviListener l) {
        Log.d(TAG, "registerListener l : " + l);
        synchronized (this.absBYDAutoNaviListeners) {
            if (this.absBYDAutoNaviListeners.size() == 0) {
                this.bydAutoInstrumentDevice.registerListener(this.absBYDAutoInstrumentListener);
            }
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.absBYDAutoNaviListeners.size()) {
                    break;
                }
                if (this.absBYDAutoNaviListeners.get(i) == l) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                this.absBYDAutoNaviListeners.add(l);
            }
        }
    }

    public void unregisterListener(AbsBYDAutoNaviListener l) {
        Log.d(TAG, "unregisterListener l : " + l);
        synchronized (this.absBYDAutoNaviListeners) {
            for (int i = 0; i < this.absBYDAutoNaviListeners.size(); i++) {
                if (this.absBYDAutoNaviListeners.get(i) == l) {
                    this.absBYDAutoNaviListeners.remove(l);
                }
            }
            if (this.absBYDAutoNaviListeners.size() == 0) {
                this.bydAutoInstrumentDevice.unregisterListener(this.absBYDAutoInstrumentListener);
            }
        }
    }

    private byte[] stringToUnicode(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[str.length() * 2];
        sb.setLength(0);
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            sb.append("\\u");
            int i2 = cCharAt >>> '\b';
            String hexString = Integer.toHexString(i2);
            if (hexString.length() == 1) {
                sb.append(FileUtils.DEFAULT_VERSION);
            }
            sb.append(hexString);
            int i3 = i * 2;
            bArr[i3] = (byte) i2;
            int i4 = cCharAt & 255;
            String hexString2 = Integer.toHexString(i4);
            if (hexString2.length() == 1) {
                sb.append(FileUtils.DEFAULT_VERSION);
            }
            sb.append(hexString2);
            bArr[i3 + 1] = (byte) (i4 & 255);
        }
        Log.d("stringToUnicode", "str : " + str + ": unicode : " + new String(sb));
        return bArr;
    }
}
