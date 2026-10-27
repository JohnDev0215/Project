package com.android.bluetooth.avrcpcontroller;

import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class PlayerApplicationSettings {
    static final byte EQUALIZER_STATUS = 1;
    private static final byte JNI_EQUALIZER_STATUS_OFF = 1;
    private static final byte JNI_EQUALIZER_STATUS_ON = 2;
    private static final byte JNI_REPEAT_STATUS_ALL_TRACK_REPEAT = 3;
    private static final byte JNI_REPEAT_STATUS_GROUP_REPEAT = 4;
    private static final byte JNI_REPEAT_STATUS_OFF = 1;
    private static final byte JNI_REPEAT_STATUS_SINGLE_TRACK_REPEAT = 2;
    private static final byte JNI_SCAN_STATUS_ALL_TRACK_SCAN = 2;
    private static final byte JNI_SCAN_STATUS_GROUP_SCAN = 3;
    private static final byte JNI_SCAN_STATUS_OFF = 1;
    private static final byte JNI_SHUFFLE_STATUS_ALL_TRACK_SHUFFLE = 2;
    private static final byte JNI_SHUFFLE_STATUS_GROUP_SHUFFLE = 3;
    private static final byte JNI_SHUFFLE_STATUS_OFF = 1;
    private static final byte JNI_STATUS_INVALID = -1;
    static final byte REPEAT_STATUS = 2;
    static final byte SCAN_STATUS = 4;
    static final byte SHUFFLE_STATUS = 3;
    private static final String TAG = "PlayerApplicationSettings";
    private SparseArray<Integer> mSettings = new SparseArray<>();
    private SparseArray<ArrayList<Integer>> mSupportedValues = new SparseArray<>();

    PlayerApplicationSettings() {
    }

    static PlayerApplicationSettings makeSupportedSettings(byte[] btAvrcpAttributeList) {
        PlayerApplicationSettings newObj = new PlayerApplicationSettings();
        int i = 0;
        while (i < btAvrcpAttributeList.length) {
            try {
                int i2 = i + 1;
                byte attrId = btAvrcpAttributeList[i];
                int i3 = i2 + 1;
                byte numSupportedVals = btAvrcpAttributeList[i2];
                ArrayList<Integer> supportedValues = new ArrayList<>();
                int j = 0;
                while (j < numSupportedVals) {
                    supportedValues.add(Integer.valueOf(mapAttribIdValtoAvrcpPlayerSetting(attrId, btAvrcpAttributeList[i3])));
                    j++;
                    i3++;
                }
                newObj.mSupportedValues.put(attrId, supportedValues);
                i = i3;
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e(TAG, "makeSupportedSettings attributeList index error.");
            }
        }
        return newObj;
    }

    static PlayerApplicationSettings makeSettings(byte[] btAvrcpAttributeList) {
        PlayerApplicationSettings newObj = new PlayerApplicationSettings();
        int i = 0;
        while (i < btAvrcpAttributeList.length) {
            try {
                int i2 = i + 1;
                byte attrId = btAvrcpAttributeList[i];
                int i3 = i2 + 1;
                newObj.mSettings.put(attrId, Integer.valueOf(mapAttribIdValtoAvrcpPlayerSetting(attrId, btAvrcpAttributeList[i2])));
                i = i3;
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e(TAG, "makeSettings JNI_ATTRIButeList index error.");
            }
        }
        return newObj;
    }

    public void setSupport(PlayerApplicationSettings updates) {
        this.mSettings = updates.mSettings;
        this.mSupportedValues = updates.mSupportedValues;
    }

    public boolean supportsSetting(int settingType, int settingValue) {
        if (this.mSupportedValues.get(settingType) == null) {
            return false;
        }
        return this.mSupportedValues.valueAt(settingType).contains(Integer.valueOf(settingValue));
    }

    public boolean supportsSetting(int settingType) {
        return this.mSupportedValues.get(settingType) != null;
    }

    public int getSetting(int settingType) {
        if (this.mSettings.get(settingType) == null) {
            return -1;
        }
        return this.mSettings.get(settingType).intValue();
    }

    private static int mapAttribIdValtoAvrcpPlayerSetting(byte attribId, byte attribVal) {
        if (attribId == 2) {
            if (attribVal == 1) {
                return 0;
            }
            if (attribVal == 2) {
                return 1;
            }
            if (attribVal != 3) {
                return attribVal != 4 ? -1 : 3;
            }
            return 2;
        }
        if (attribId == 3) {
            if (attribVal == 1) {
                return 0;
            }
            if (attribVal != 2) {
                return attribVal != 3 ? -1 : 2;
            }
            return 1;
        }
        return -1;
    }

    static byte mapAvrcpPlayerSettingstoBTattribVal(int mSetting, int mSettingVal) {
        if (mSetting == 2) {
            if (mSettingVal != 0) {
                if (mSettingVal == 1) {
                    return (byte) 2;
                }
                if (mSettingVal != 2) {
                    return mSettingVal != 3 ? (byte) -1 : (byte) 4;
                }
                return (byte) 3;
            }
            return (byte) 1;
        }
        if (mSetting == 3) {
            if (mSettingVal != 0) {
                if (mSettingVal != 1) {
                    return mSettingVal != 2 ? (byte) -1 : (byte) 3;
                }
                return (byte) 2;
            }
            return (byte) 1;
        }
        return (byte) -1;
    }
}
