package com.neusoft.optimus.utility.Storage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import com.neusoft.optimus.utility.Storage.ISourceListener;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class Constant {
    private static boolean _ifServiceHaveBound = false;
    private static boolean _isNotSend = true;
    private static int _kc2 = -1;
    private static Runnable _runnable = new Runnable() { // from class: com.neusoft.optimus.utility.Storage.Constant.1
        @Override // java.lang.Runnable
        public void run() {
            LogTool.d(Constant.cTag, "setSource time over Cancel isNotSend to true");
            boolean unused = Constant._isNotSend = true;
        }
    };
    private static String _source = "audio_usb1";
    private static ISourceListener _sourceListener = null;
    private static final String cAllSource = "AllSource";
    public static final String cAudio_bt = "audio_bt";
    public static final String cAudio_hdd = "audio_hdd";
    public static final String cAudio_usb1 = "audio_usb1";
    public static final String cAudio_usb2 = "audio_usb2";
    public static final String cBanTing = "BanTing";
    public static final String cCanDiagnosis = "com.neusoft.ACTION_CANDIAGNOSIS";
    public static final String cCanDiagnosisMode = "CanDiagnosisMode";
    public static final String cCanDiagnosisModeIsSuccess = "CanDiagnosisModeIsSuccess";
    public static final String cCarLife = "CarLife";
    public static final String cCarPlay = "CarPlay";
    public static final String cCarPlay_audio = "carplay_audio";
    public static final int cDown = 265;
    public static final String cGeelyLastsource = "geely_lastsource";
    public static final String cGeelyModeSource = "geely_modesource";
    private static final String cHK_Source = "HK_Source";
    public static final String cImage_usb1 = "Image_usb1";
    public static final String cImage_usb2 = "Image_usb2";
    public static final String cRadio_am = "radio_am";
    public static final String cRadio_fm = "radio_fm";
    public static final String cSourceMediumChanged = "com.neusoft.source_medium_changed";
    public static final String cSourceSelByApp = "source_select";
    public static final String cSourceType = "sourceType";
    public static final String cSourceTypeLastAudio = "LastAudio";
    public static final String cSourceTypeLastUSB = "LastUSB";
    private static final String cTag = "Constant";
    public static final int cUp = 264;
    public static final String cVideo_hdd = "video_hdd";
    public static final String cVideo_usb1 = "video_usb1";
    public static final String cVideo_usb2 = "video_usb2";
    private static boolean isTest = true;

    private Constant() {
    }

    public static synchronized String getSource(Context context) {
        if (isTest) {
            return _source;
        }
        String strUpdateUsbName = updateUsbName(Settings.System.getString(context.getContentResolver(), cAllSource));
        LogTool.d(cTag, "getSource @Source = " + strUpdateUsbName);
        return strUpdateUsbName;
    }

    public static synchronized String getSource(Context context, String str) {
        if (isTest) {
            return _source;
        }
        String strUpdateUsbName = updateUsbName(Settings.System.getString(context.getContentResolver(), str));
        LogTool.d(cTag, "getSource @sourceType = %s, Source = %s", str, strUpdateUsbName);
        return strUpdateUsbName;
    }

    public static void initSource(Context context) {
        context.bindService(new Intent("com.neusoft.shockwave.instrumentserver.specific.service.InstrumentService"), new AudioConnection(), 1);
    }

    public static synchronized void setSource(Context context, String str) {
        if (isTest) {
            _source = str;
        } else {
            setSource(context, str, true);
        }
    }

    public static synchronized void setSource(Context context, String str, boolean z) {
        if (isTest) {
            _source = str;
            return;
        }
        LogTool.d(cTag, "setSource --Source = " + str + ", isMust = " + z);
        if (str == null) {
            return;
        }
        boolean z2 = str.equals(cRadio_fm) || str.equals(cRadio_am) || str.equals(cAudio_hdd) || str.equals(cAudio_usb1) || str.equals(cAudio_usb2) || str.equals(cAudio_bt);
        boolean z3 = str.equals(cRadio_fm) || str.equals(cRadio_am) || str.equals(cAudio_hdd) || str.equals(cAudio_usb1) || str.equals(cAudio_usb2) || str.equals(cVideo_hdd) || str.equals(cVideo_usb1) || str.equals(cVideo_usb2) || str.equals(cAudio_bt);
        boolean z4 = str.equals(cAudio_hdd) || str.equals(cAudio_usb1) || str.equals(cAudio_usb2) || str.equals(cAudio_bt);
        boolean z5 = str.equals(cRadio_fm) || str.equals(cRadio_am) || str.equals(cAudio_hdd) || str.equals(cAudio_usb1) || str.equals(cAudio_usb2) || str.equals(cVideo_hdd) || str.equals(cVideo_usb1) || str.equals(cVideo_usb2) || str.equals(cAudio_bt) || str.equals(cCarPlay) || str.equals(cCarLife) || str.equals(cBanTing);
        _source = str;
        if (str.equals(updateUsbName(Settings.System.getString(context.getContentResolver(), cAllSource)))) {
            if (_isNotSend && z) {
                sendChangeSource(context, str);
                sendBroadcast(context, str);
            }
        } else {
            LogTool.d(cTag, "setSource @setAllSource = " + str);
            Settings.System.putString(context.getContentResolver(), cAllSource, updateUsbName(str));
            sendChangeSource(context, str);
            sendBroadcast(context, str);
        }
        if (z2 && !str.equals(updateUsbName(Settings.System.getString(context.getContentResolver(), cGeelyModeSource)))) {
            LogTool.d(cTag, "setSource @setModeKeySource = " + str);
            Settings.System.putString(context.getContentResolver(), cGeelyModeSource, updateUsbName(str));
        }
        if (z3 && !str.equals(updateUsbName(Settings.System.getString(context.getContentResolver(), cGeelyLastsource)))) {
            LogTool.d(cTag, "setSource @setLastSource = " + str);
            Settings.System.putString(context.getContentResolver(), cGeelyLastsource, updateUsbName(str));
        }
        if (z2 && !str.equals(updateUsbName(Settings.System.getString(context.getContentResolver(), cSourceTypeLastAudio)))) {
            LogTool.d(cTag, "setSource @setLastAudio = " + str);
            Settings.System.putString(context.getContentResolver(), cSourceTypeLastAudio, updateUsbName(str));
        }
        if (z4 && !str.equals(updateUsbName(Settings.System.getString(context.getContentResolver(), cSourceTypeLastUSB)))) {
            LogTool.d(cTag, "setSource @setLastUSB = " + str);
            Settings.System.putString(context.getContentResolver(), cSourceTypeLastUSB, updateUsbName(str));
        }
        if (z5 && !str.equals(updateUsbName(Settings.System.getString(context.getContentResolver(), cHK_Source)))) {
            LogTool.d(cTag, "setSource @setHardKeySource = " + str);
            Settings.System.putString(context.getContentResolver(), cHK_Source, updateUsbName(str));
        }
    }

    private static void sendChangeSource(Context context, String str) {
        try {
            LogTool.d(cTag, "sendChangeSource  _sourceListener--" + _sourceListener);
            if (_sourceListener != null) {
                LogTool.d(cTag, "_sourceListener  send Source _source:%s", str);
                _ifServiceHaveBound = false;
                _sourceListener.onSourceChanged(str);
            } else {
                _ifServiceHaveBound = true;
                context.bindService(new Intent("com.neusoft.shockwave.instrumentserver.specific.service.InstrumentService"), new AudioConnection(), 1);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void sendBroadcast(Context context, String str) {
        LogTool.d(cTag, "setSource sendBroadcast source = " + str);
        Intent intent = new Intent();
        intent.setAction(cSourceMediumChanged);
        intent.putExtra(cSourceType, str);
        context.sendBroadcast(intent);
        Intent intent2 = new Intent();
        intent2.setAction(cCanDiagnosis);
        intent2.putExtra(cCanDiagnosisModeIsSuccess, 0);
        intent2.putExtra(cCanDiagnosisMode, str);
        context.sendBroadcast(intent2);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.removeCallbacks(_runnable);
        handler.postDelayed(_runnable, 700L);
        _isNotSend = false;
    }

    static class AudioConnection implements ServiceConnection {
        AudioConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogTool.d(Constant.cTag, "onServiceConnected---start");
            if (Constant._sourceListener == null) {
                ISourceListener unused = Constant._sourceListener = ISourceListener.Stub.asInterface(iBinder);
                if (Constant._ifServiceHaveBound) {
                    try {
                        Constant._sourceListener.onSourceChanged(Constant._source);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
            LogTool.d(Constant.cTag, "onServiceConnected---end");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ISourceListener unused = Constant._sourceListener = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027 A[PHI: r4
  0x0027: PHI (r4v3 java.lang.String) = (r4v0 java.lang.String), (r4v1 java.lang.String), (r4v2 java.lang.String) binds: [B:9:0x0025, B:15:0x0039, B:21:0x004b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002f A[PHI: r0
  0x002f: PHI (r0v10 java.lang.String) = (r0v7 java.lang.String), (r0v8 java.lang.String), (r0v9 java.lang.String) binds: [B:12:0x002d, B:18:0x0040, B:24:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String updateUsbName(java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "updateUsbName source = "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "Constant"
            com.neusoft.optimus.utility.log.LogTool.d_begin(r2, r0)
            if (r5 != 0) goto L18
            r5 = 0
            return r5
        L18:
            int r0 = com.neusoft.optimus.utility.Storage.Constant._kc2
            r3 = 1
            if (r0 != r3) goto L55
            java.lang.String r0 = "audio_usb1"
            boolean r3 = r5.equals(r0)
            java.lang.String r4 = "audio_usb2"
            if (r3 == 0) goto L29
        L27:
            r5 = r4
            goto L55
        L29:
            boolean r3 = r5.equals(r4)
            if (r3 == 0) goto L31
        L2f:
            r5 = r0
            goto L55
        L31:
            java.lang.String r0 = "video_usb1"
            boolean r3 = r5.equals(r0)
            java.lang.String r4 = "video_usb2"
            if (r3 == 0) goto L3c
            goto L27
        L3c:
            boolean r3 = r5.equals(r4)
            if (r3 == 0) goto L43
            goto L2f
        L43:
            java.lang.String r0 = "Image_usb1"
            boolean r3 = r5.equals(r0)
            java.lang.String r4 = "Image_usb2"
            if (r3 == 0) goto L4e
            goto L27
        L4e:
            boolean r3 = r5.equals(r4)
            if (r3 == 0) goto L55
            goto L2f
        L55:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.neusoft.optimus.utility.log.LogTool.d_end(r2, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.optimus.utility.Storage.Constant.updateUsbName(java.lang.String):java.lang.String");
    }
}
