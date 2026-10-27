package com.audio.usbmic;

import android.content.Context;
import android.util.Log;
import com.micphone.TsBydUDiskConfig;
import com.micphone.utils.Config;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UsbAudioNativeLoopConfig {
    public static final int LOOPBACK_AUTO_START = 1;
    private static final int LOOPBACK_EFFECT_HARDWARE = 0;
    private static final int LOOPBACK_EFFECT_SOFTWARE = 1;
    public static final int LOOPBACK_MANUAL_START = 0;
    private static final int MAX_MIXER_NUM = 10;
    public static int mCaptureCard = 0;
    public static int mCaptureChannels = 0;
    public static int mCaptureCount = 0;
    public static int mCaptureDevice = 0;
    public static int mCaptureFormat = 0;
    public static int mCaptureFreq = 0;
    public static int mCapturePeriods = 0;
    public static int mCaptureQueues = 0;
    public static int mEffectType = 0;
    public static int mLoopMode = 0;
    public static String mPlatformName = "";
    public static int mPlaybackCard = 0;
    public static String mPlaybackCardName = "";
    public static int mPlaybackChannels;
    public static int mPlaybackCount;
    public static int mPlaybackDevice;
    public static int mPlaybackFormat;
    public static int mPlaybackFreq;
    public static int mPlaybackPeriods;
    public static int mPlaybackQueues;
    private static List<String> mDeviceNameList = new ArrayList();
    public static String[] mMixerCtl = new String[20];
    public static String[] mMixerVal = new String[20];

    public static void addDeviceNameToListByByd() {
        Log.d("TsMicphoneService", "addDeviceNameToListByByd");
        addDeviceNameToListByConfig(Config.DONGLE_BYD);
    }

    private static void addDeviceNameToListByConfig(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            mDeviceNameList.add((String) it.next());
        }
    }

    public static void addDeviceNameToListByDenZa() {
        Log.d("TsMicphoneService", "addDeviceNameToListByDenZa");
        addDeviceNameToListByConfig(Config.DONGLE_DENZA);
    }

    public static int getMaxMixerNum() {
        return 10;
    }

    public static boolean isSoftEffect() {
        return mEffectType == 1;
    }

    public static boolean isSupportDevice(Context context, String str) {
        Log.e("TsMicphoneService", " mDeviceNameList.size() " + mDeviceNameList.size());
        if (!Config.needCheckMicphoneName()) {
            return true;
        }
        if (!TsBydUDiskConfig.getInstance(context).isNameCheck()) {
            Log.e("TsMicphoneService", "U Disk close name check!!!");
            return true;
        }
        if (mDeviceNameList.size() == 0) {
            return false;
        }
        Iterator<String> it = mDeviceNameList.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static int loopMode() {
        return mLoopMode;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00e4 A[Catch: Exception -> 0x0442, TryCatch #0 {Exception -> 0x0442, blocks: (B:24:0x0076, B:25:0x0093, B:27:0x0099, B:30:0x00a8, B:32:0x00c6, B:34:0x00ce, B:139:0x0433, B:37:0x00e4, B:39:0x00fe, B:41:0x0103, B:43:0x011d, B:45:0x0122, B:48:0x0148, B:50:0x0155, B:51:0x015b, B:52:0x0160, B:54:0x0166, B:56:0x0178, B:78:0x0259, B:57:0x018c, B:59:0x0196, B:60:0x01aa, B:62:0x01b4, B:63:0x01c8, B:65:0x01d2, B:66:0x01e6, B:68:0x01f0, B:69:0x0203, B:71:0x020d, B:72:0x0220, B:74:0x022a, B:75:0x023d, B:77:0x0247, B:79:0x025f, B:81:0x0273, B:82:0x027a, B:84:0x0280, B:86:0x0291, B:87:0x029a, B:88:0x02a1, B:89:0x02a8, B:91:0x02ae, B:93:0x02be, B:115:0x039f, B:94:0x02d2, B:96:0x02dc, B:97:0x02f0, B:99:0x02fa, B:100:0x030e, B:102:0x0318, B:103:0x032c, B:105:0x0336, B:106:0x0349, B:108:0x0353, B:109:0x0366, B:111:0x0370, B:112:0x0383, B:114:0x038d, B:117:0x03a7, B:119:0x03b5, B:121:0x03c3, B:122:0x03c8, B:124:0x03ce, B:126:0x03d6, B:127:0x03ea, B:128:0x03ed, B:130:0x03f9, B:132:0x0407, B:133:0x040c, B:135:0x0412, B:137:0x041a, B:138:0x0430, B:44:0x0120, B:40:0x0101, B:140:0x043c), top: B:146:0x0076 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int parserXmlConfig(android.content.Context r19) {
        /*
            Method dump skipped, instruction units count: 1096
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.audio.usbmic.UsbAudioNativeLoopConfig.parserXmlConfig(android.content.Context):int");
    }
}
