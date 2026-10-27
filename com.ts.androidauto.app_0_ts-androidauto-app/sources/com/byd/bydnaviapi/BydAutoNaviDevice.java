package com.byd.bydnaviapi;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.util.Log;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.util.ArrayList;

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
        String packageName = con.getPackageName();
        Log.e(TAG, "BydAutoNaviDevice packageName : " + packageName);
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
            try {
                this.bydAutoInstrumentDevice.sendTbtNaviInfo(mapType, nextTbtIcon, nextRoadName, nextRoadDist, nextDistUnit, nextNextTbtIcon, nextNextRoadDist, nextNextDistUnit, distToDest, distToDestUnit, toDestTime, toDestDays, toDestHours, toDestMinutes);
                try {
                    sendHudEasyNavGuideInfo(nextTbtIcon, nextRoadName, nextRoadDist, nextDistUnit);
                    return 0;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return -2147482648;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public int sendSimpleNaviInfo(int mapType, int nextTbtIcon, String nextRoadName, String nextRoadDistData, String nextRoadDistUnit, int nextNextTbtIcon, String nextNextDistData, String nextNextDistUnit, String distToDestDisData, String distToDestDisUnit, String distToDestTimeDayUnit, String distToDestTimeHourUnit, String distToDestTimeMinUnit, String distToDestTimeSecUnit, String distToDestTime, String targetDisUnit, String toDestDays, String toDestTime, String multipleText, String multipleText1, String multipleText2) {
        Log.d(TAG, "sendSimpleNaviInfo mapType : " + mapType + "\nnextTbtIcon : " + nextTbtIcon + "\nnextRoadName : " + nextRoadName + "\nnextRoadDistData : " + nextRoadDistData + "\nnextRoadDistUnit : " + nextRoadDistUnit + "\nnextNextTbtIcon : " + nextNextTbtIcon + "\nnextNextDistData : " + nextNextDistData + "\nnextNextDistUnit : " + nextNextDistUnit + "\ndistToDestDisData : " + distToDestDisData + "\ndistToDestDisUnit : " + distToDestDisUnit + "\ndistToDestTimeDayUnit : " + distToDestTimeDayUnit + "\ndistToDestTimeHourUnit : " + distToDestTimeHourUnit + "\ndistToDestTimeMinUnit : " + distToDestTimeMinUnit + "\ndistToDestTimeSecUnit : " + distToDestTimeSecUnit + "\ndistToDestTime : " + distToDestTime + "\ntargetDisUnit : " + targetDisUnit + "\ntoDestDays : " + toDestDays + "\ntoDestTime : " + toDestTime + "\nmultipleText : " + multipleText + "\nmultipleText1 : " + multipleText1 + "\nmultipleText2 : " + multipleText2);
        try {
            try {
                this.bydAutoInstrumentDevice.sendTbtSimpleNaviInfo(mapType, nextTbtIcon, nextRoadName, nextRoadDistData, nextRoadDistUnit, nextNextTbtIcon, nextNextDistData, nextNextDistUnit, distToDestDisData, distToDestDisUnit, distToDestTime, toDestDays, toDestTime, multipleText, multipleText1, multipleText2);
                BYDAutoWhudDevice bYDAutoWhudDevice = this.bydAutoWhudDevice;
                StringBuilder sb = new StringBuilder();
                try {
                    sb.append(nextRoadDistData);
                    sb.append(" ");
                    try {
                        sb.append(nextRoadDistUnit);
                        bYDAutoWhudDevice.setNextRoadDistanceUnitDataLen(sb.toString());
                        try {
                            this.bydAutoWhudDevice.setNameNextRoad(nextRoadName);
                            try {
                                this.bydAutoWhudDevice.setEasyNavGuideInfo(nextTbtIcon, 0L, 0);
                                return 0;
                            } catch (Exception e) {
                                e = e;
                                e.printStackTrace();
                                return -2147482648;
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    return -2147482648;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Exception e6) {
            e = e6;
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
        int sendNextRoadDist = (int) nextRoadDist;
        Log.d(TAG, "setEasyNavGuideInfo nextTbtIcon : " + nextTbtIcon + " , sendNextRoadDist : " + sendNextRoadDist + " , nextDistUnit : " + nextDistUnit);
        this.bydAutoWhudDevice.setEasyNavGuideInfo(nextTbtIcon, (long) sendNextRoadDist, nextDistUnit);
    }

    public int sendNaviPresentationState(int mapType, int state) {
        Log.d(TAG, "sendNaviPresentationState mapType : " + mapType + " , state : " + state);
        try {
            return this.bydAutoInstrumentDevice.sendNaviPresentationState(mapType, state);
        } catch (Exception e) {
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
            boolean found = false;
            int i = 0;
            while (true) {
                if (i >= this.absBYDAutoNaviListeners.size()) {
                    break;
                }
                if (this.absBYDAutoNaviListeners.get(i) != l) {
                    i++;
                } else {
                    found = true;
                    break;
                }
            }
            if (!found) {
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    private byte[] stringToUnicode(String str) {
        String str2 = str == null ? AndroidAutoConst.DEFAULT_SCREEN_NAME : str;
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[str2.length() * 2];
        sb.setLength(0);
        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            sb.append("\\u");
            int j = c >>> '\b';
            String tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            bytes[i * 2] = (byte) j;
            int j2 = c & 255;
            String tmp2 = Integer.toHexString(j2);
            if (tmp2.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp2);
            bytes[(i * 2) + 1] = (byte) (j2 & 255);
        }
        String unicode = new String(sb);
        Log.d("stringToUnicode", "str : " + str2 + ": unicode : " + unicode);
        return bytes;
    }
}
