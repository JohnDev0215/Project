package com.micphone;

import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.BuildConfig;
import com.audio.MicphoneService.R;
import com.audio.usbmic.UsbAudioNativeLoopConfig;
import com.audio.usbmic.UsbMicDevice;
import com.loostone.libtuning.component.provider.TuningProvider;
import com.micphone.bean.TsBydDeviceInfo;
import com.micphone.bean.TsBydDongleInfo;
import com.micphone.bean.TsBydMicInfo;
import com.micphone.service.MicphoneService;
import com.micphone.utils.Config;
import com.micphone.utils.HttpUtils;
import com.micphone.utils.ReflectUtil;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public class TsBydMicManager {
    private static final String BYD_CAR_TYPE = "byd";
    private static final String BYD_OTA_DONGLE_FAIL_SOLUTION = "插拔接收重试";
    private static final String BYD_OTA_DONGLE_FAIL_TIP = "接收器升级失败";
    private static final String BYD_OTA_MIC_FAIL_SOLUTION = "开关话筒重试";
    private static final String BYD_OTA_MIC_FAIL_TIP = "话筒升级失败";
    public static final String BYD_PROP_INSIDE_MIC = "sys.has.iusb.mic";
    private static final String BYD_PROP_VEHICLE_TYPE = "sys.ext.vehicle.type";
    private static final String BYD_V1_PRODUCT_NAME_TS_SA3 = "TS-D001";
    private static final String BYD_V1_PRODUCT_NAME_TS_UF = "BYD-micTS02";
    private static final String BYD_V4_DONGLE_MIC_NO_PAIR_TIP = "暂未配对麦克风";
    private static final String BYD_V4_DONGLE_MIC_NO_SUPPORT_TIP = "当前设备不支持";
    private static final String BYD_V4_DONGLE_NEW_VERSION_TIP = "1.修改版本信息显示问题";
    private static final String BYD_V4_DONGLE_NO_VERSION_TIP = "无版本信息";
    private static final String BYD_V4_PRODUCT_NAME_TS_HT = "BYD-micTS03";
    private static final String DENZA_CAR_TYPE = "denza";
    private static final int ENDPOINT_REPORT_COMMAND_CONNECT = 197;
    private static final int ENDPOINT_REPORT_COMMAND_DISCONNECT = 198;
    private static final int ENDPOINT_REPORT_COMMAND_END_PAIR = 196;
    private static final int ENDPOINT_REPORT_COMMAND_START_PAIR = 195;
    private static final int HANDLE_MIC_STATE_REPORT_CONNECT_MSG = 2;
    private static final long HANDLE_MIC_STATE_REPORT_DELAY = 1000;
    private static final int HANDLE_MIC_STATE_REPORT_DIS_CONNECT_MSG = 3;
    private static final long HANDLE_SET_EFFECT_DELAY = 250;
    private static final int HANDLE_SET_EFFECT_DELAY_MSG = 4;
    private static final String MIC_CHANNEL_CLOSE = "Karaoke_enable=false";
    private static final String MIC_CHANNEL_OPEN = "Karaoke_enable=true";
    private static final String MIC_CHE_LIAN_OFF = "thunderstone_mic=off";
    private static final String MIC_CHE_LIAN_ON = "thunderstone_mic=on";
    private static final String RAW_HT_DONGLE_VERSION_NAME = "ht_dongle";
    private static final String RAW_HT_MIC_VERSION_NAME = "ht_mic";
    private static final String RAW_SA3_DONGLE_VERSION_NAME = "sa3_dongle";
    private static final String RAW_UF_DONGLE_VERSION_NAME = "uf_dongle";
    public static final String SDK_VERSION_NAME = "SDK:1.3.19";
    private static final String TAG = "TsBydMicManager";
    public static final String TS_CHANNEL = "600103";
    private static TsBydMicManager instance;
    private int curUpgradeSubId;
    private boolean interceptDetach;
    private boolean isForceOta;
    private int lastPid;
    private int lastVid;
    private int mCarType;
    private CheckNewVersionCallback mCheckNewVersionCallback;
    private Context mContext;
    private DeviceInfoCallback mDeviceInfoCallback;
    private TsDongleListener mListener;
    private ExecutorService mMethodExecutorService;
    private MicStateThreadHandler mMicStateThreadHandler;
    private ResetFactoryCallback mResetFactoryCallback;
    private TsBydDeviceInfo mTsBydDeviceInfo;
    private TsBydSoundPool mTsBydSoundPool;
    private UpgradeCallback mUpgradeCallback;
    private UpgradePreconditionCallback mUpgradePreconditionCallback;
    private long micLogEventConnectTime;
    private long micLogEventDisConnectTime;
    private boolean isThunderMic = false;
    private long lastSetEffectTime = 0;
    public ConcurrentHashMap<Integer, Integer> mMicSupplierS = new ConcurrentHashMap<>();
    private TsBydListener mTsBydListener = new TsBydListener() { // from class: com.micphone.TsBydMicManager.2
        @Override // com.micphone.TsBydListener
        public void onEndpointEvent(int i, int i2, int i3) {
            Log.d(TsBydMicManager.TAG, "onEndpointEvent=" + i + ", arg1=" + i2 + ",arg2=" + i3);
            int i4 = i2 & 15;
            if (i == 7) {
                int i5 = i3 & 15;
                Log.d(TsBydMicManager.TAG, "micType=" + i5);
                if (i5 < 0) {
                }
                TsBydMicManager.this.mMicSupplierS.put(Integer.valueOf(i4), Integer.valueOf(i5));
                TsBydMicManager.this.loadMicEffect(i4, i5);
                return;
            }
            if (i == 8) {
                TsBydMicManager.this.micFactoryTestCallback();
                return;
            }
            switch (i) {
                case 195:
                    Log.d(TsBydMicManager.TAG, "申请配对" + i4);
                    TsBydMicManager.this.micStatePairCallback(i4, 6);
                    break;
                case 196:
                    Log.d(TsBydMicManager.TAG, "完成配对" + i4);
                    TsBydMicManager.this.micStatePairCallback(i4, 4);
                    break;
                case 197:
                    Log.d(TsBydMicManager.TAG, "链接" + i4);
                    if ((i3 & 15) == 1 && TsBydMicManager.this.mTsBydSoundPool != null) {
                        TsBydMicManager.this.mTsBydSoundPool.playTipPair();
                    }
                    TsBydMicManager.this.micStateConnectCallback(i4, true);
                    Bundle bundle = new Bundle();
                    bundle.putInt("subId", i4);
                    bundle.putInt("state", 1);
                    Message message = new Message();
                    message.what = 2;
                    message.setData(bundle);
                    TsBydMicManager.this.mMicStateThreadHandler.sendMessageDelayed(message, TsBydMicManager.HANDLE_MIC_STATE_REPORT_DELAY);
                    break;
                case 198:
                    Log.d(TsBydMicManager.TAG, "断开" + i4);
                    TsBydMicManager.this.micStateConnectCallback(i4, false);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("subId", i4);
                    bundle2.putInt("state", 0);
                    Message message2 = new Message();
                    message2.what = 3;
                    message2.setData(bundle2);
                    TsBydMicManager.this.mMicStateThreadHandler.sendMessageDelayed(message2, TsBydMicManager.HANDLE_MIC_STATE_REPORT_DELAY);
                    break;
            }
        }

        @Override // com.micphone.TsBydListener
        public void onHotPlugInPidVid(int i, int i2) {
            Log.d(TsBydMicManager.TAG, "last p=" + TsBydMicManager.this.lastPid + ",v" + TsBydMicManager.this.lastVid);
            if (Config.KT_CDC_PID == i2 && Config.KT_CDC_VID == i && ((Config.KT_CDC_PID == TsBydMicManager.this.lastPid && Config.KT_CDC_VID == TsBydMicManager.this.lastVid) || ((Config.KT_LOADER_PID == TsBydMicManager.this.lastPid && Config.KT_LOADER_VID == TsBydMicManager.this.lastVid) || (TsBydMicManager.this.lastPid == 0 && TsBydMicManager.this.lastVid == 0)))) {
                TsBydMicManager.this.isForceOta = true;
            } else {
                TsBydMicManager.this.isForceOta = false;
            }
            Log.d(TsBydMicManager.TAG, "interceptDetach " + TsBydMicManager.this.interceptDetach);
            if (TsBydMicManager.this.interceptDetach && Config.KT_CDC_PID == i2 && Config.KT_CDC_VID == i) {
                TsBydMicManager.this.interceptDetach = false;
            }
            TsBydMicManager.this.lastPid = i2;
            TsBydMicManager.this.lastVid = i;
        }

        @Override // com.micphone.TsBydListener
        public void onHotPlugState(int i, boolean z, boolean z2) {
            String str;
            String str2 = TsBydMicManager.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onHotPlugState, state=");
            sb.append(i);
            sb.append(", broad=");
            sb.append(z);
            sb.append(", check=");
            sb.append(z2);
            sb.append(", mTsBydUIInterface is null? ");
            sb.append(TsBydMicManager.this.mTsBydUIInterface == null);
            Log.d(str2, sb.toString());
            if (i != 1) {
                if (i == 0) {
                    Log.d(TsBydMicManager.TAG, "interceptDetach " + TsBydMicManager.this.interceptDetach);
                    if (TsBydMicManager.this.interceptDetach) {
                        return;
                    }
                    if (z2) {
                        TsBydMicManager.this.closeMicChannel();
                        TsBydMicManager.this.isThunderMic = false;
                        TsBydMicManager.this.micDetachUpdateUIInfo();
                    }
                    if (TsBydMicManager.this.mTsBydUIInterface == null || !z) {
                        return;
                    }
                    TsBydMicManager.this.mTsBydUIInterface.detach();
                    return;
                }
                return;
            }
            if (z2) {
                TsBydMicManager.this.isThunderMic = true;
                TsBydMicManager.this.handleCheckDongleInfo();
                TsBydDongleInfo tsBydDongleInfo = TsBydMicManager.this.mTsBydDeviceInfo.mTsBydDongleInfo;
                if (tsBydDongleInfo != null && (str = tsBydDongleInfo.version) != null && "B".equals(str.substring(str.length() - 1)) && TsBydMicManager.BYD_V1_PRODUCT_NAME_TS_UF.equals(tsBydDongleInfo.productName)) {
                    for (int i2 = 0; i2 < 4; i2++) {
                        TsBydMicManager.this.initUFEffect(i2);
                        int i3 = i2 | 224;
                        Log.d(TsBydMicManager.TAG, "id = " + i3);
                        UsbMicDevice.usbMicSwitchEffect(i3);
                    }
                }
                TsBydMicManager.this.openMicChannel();
                TsBydMicManager.this.handleCheckNewVersion();
            }
            if (TsBydMicManager.this.mTsBydUIInterface == null || !z) {
                return;
            }
            TsBydMicManager.this.mTsBydUIInterface.attach();
        }

        @Override // com.micphone.TsBydListener
        public void onInsideMic() {
            TsBydMicManager.this.mTsBydUIInterface.onOutsideDongleRejected();
        }

        @Override // com.micphone.TsBydListener
        public void onOtaEvent(int i, int i2, int i3) {
            Log.d(TsBydMicManager.TAG, "onOtaEvent=" + i + ", arg1=" + i2 + ",arg2=" + i3);
            if (i == 240 && i2 == 255) {
                if (TsBydMicManager.this.mMethodExecutorService == null || TsBydMicManager.this.mMethodExecutorService.isShutdown()) {
                    return;
                }
                TsBydMicManager.this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        TsBydMicManager.this.doDongleUpgrade();
                    }
                });
                return;
            }
            if (i == 240 && i2 == 242) {
                TsBydDongleInfo tsBydDongleInfo = TsBydMicManager.this.mTsBydDeviceInfo.mTsBydDongleInfo;
                if (tsBydDongleInfo != null) {
                    tsBydDongleInfo.upgradeProcess = i3;
                }
            } else if (i == 224) {
                if (i2 == 226) {
                    Iterator<Map.Entry<Integer, TsBydMicInfo>> it = TsBydMicManager.this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TsBydMicInfo value = it.next().getValue();
                        if (value.upgradeResult == TsBydMicInfo.MicUpResult.Start) {
                            value.upgradeProcess = i3;
                            break;
                        }
                    }
                } else if (i2 == 224) {
                    TsBydMicManager.this.curUpgradeSubId = i3;
                }
            }
            TsBydMicManager.this.doUpgradeProcess();
        }

        @Override // com.micphone.TsBydListener
        public void onOtaKTEventMode() {
            Log.d(TsBydMicManager.TAG, "onOtaKTEventMode");
            if (TsBydMicManager.this.mMethodExecutorService == null || TsBydMicManager.this.mMethodExecutorService.isShutdown()) {
                return;
            }
            TsBydMicManager.this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.2.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.d(TsBydMicManager.TAG, "isForceOta=" + TsBydMicManager.this.isForceOta);
                    if (TsBydMicManager.this.isForceOta) {
                        TsBydMicManager.this.forceOtaKt();
                    } else {
                        TsBydMicManager.this.doDongleUpgrade();
                    }
                }
            });
        }
    };
    private Handler manThreadHandler = new Handler(Looper.getMainLooper());
    private TsBydUIInterface mTsBydUIInterface = new TsBydUIInterface() { // from class: com.micphone.TsBydMicManager.7
        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void attach() {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.1
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("attach ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.attach();
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void detach() {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.2
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("detach ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.detach();
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onDongleInfoChange(final String str) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.16
                @Override // java.lang.Runnable
                public void run() {
                    String str2 = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onDongleInfoChange ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mDeviceInfoCallback is null? ");
                    sb.append(TsBydMicManager.this.mDeviceInfoCallback == null);
                    sb.append(", dongleInfo=");
                    sb.append(str);
                    Log.d(str2, sb.toString());
                    if (TsBydMicManager.this.mDeviceInfoCallback != null) {
                        TsBydMicManager.this.mDeviceInfoCallback.onDongleInfoChange(str);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onFactoryMicPowerOff() {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.21
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onFactoryMicPowerOff ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onFactoryMicPowerOff();
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onInteractEffect(final String str) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.7
                @Override // java.lang.Runnable
                public void run() {
                    String str2 = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onInteractEffect ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    sb.append(", effect=");
                    sb.append(str);
                    Log.d(str2, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onInteractEffect(str);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onMicConnectToVehicleStateChange(final boolean z) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.10
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onMicConnectToVehicleStateChange ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mUpgradePreconditionCallback is null? ");
                    sb.append(TsBydMicManager.this.mUpgradePreconditionCallback == null);
                    sb.append(", state=");
                    sb.append(z);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mUpgradePreconditionCallback != null) {
                        TsBydMicManager.this.mUpgradePreconditionCallback.onMicConnectToVehicleStateChange(z);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onMicConnectionChanged(final boolean z) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.20
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onMicConnectionChanged ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onMicConnectionChanged(z);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onMicInfoChange(final String str) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.15
                @Override // java.lang.Runnable
                public void run() {
                    String str2 = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onMicInfoChange ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mDeviceInfoCallback is null? ");
                    sb.append(TsBydMicManager.this.mDeviceInfoCallback == null);
                    sb.append(", micInfo=");
                    sb.append(str);
                    Log.d(str2, sb.toString());
                    if (TsBydMicManager.this.mDeviceInfoCallback != null) {
                        TsBydMicManager.this.mDeviceInfoCallback.onMicInfoChange(str);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onMicMeetConditionChange(final boolean z) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.11
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onMicMeetConditionChange ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mUpgradePreconditionCallback is null? ");
                    sb.append(TsBydMicManager.this.mUpgradePreconditionCallback == null);
                    sb.append(", state=");
                    sb.append(z);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mUpgradePreconditionCallback != null) {
                        TsBydMicManager.this.mUpgradePreconditionCallback.onMicMeetConditionChange(z);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onMicPowerChanged(final int i, final boolean z) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.3
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onMicPowerChanged ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    sb.append(", micNum=");
                    sb.append(i);
                    sb.append(", micState=");
                    sb.append(z);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onMicPowerChanged(i, z);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onNewVersionAvailable(final String str) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.8
                @Override // java.lang.Runnable
                public void run() {
                    String str2 = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onNewVersionAvailable ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mCheckNewVersionCallback is null? ");
                    sb.append(TsBydMicManager.this.mCheckNewVersionCallback == null);
                    sb.append(", info=");
                    sb.append(str);
                    Log.d(str2, sb.toString());
                    if (TsBydMicManager.this.mCheckNewVersionCallback != null) {
                        TsBydMicManager.this.mCheckNewVersionCallback.onNewVersionAvailable(str);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onOutsideDongleRejected() {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.5
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onOutsideDongleRejected ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onOutsideDongleRejected();
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onPairResultChanged(final int i, final int i2, final int i3) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.19
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onPairResultChanged ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onPairResultChanged(i, i2, i3);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onPairStateChanged(final int i, final int i2) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.4
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onPairStateChanged ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    sb.append(", micNum=");
                    sb.append(i);
                    sb.append(", pairState=");
                    sb.append(i2);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onPairStateChanged(i, i2);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onPremiumSpeakerState(final boolean z) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.6
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onPremiumSpeakerState ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mListener is null? ");
                    sb.append(TsBydMicManager.this.mListener == null);
                    sb.append(", state=");
                    sb.append(z);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mListener != null) {
                        TsBydMicManager.this.mListener.onPremiumSpeakerState(z);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onResetFactoryFailed(final String str) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.18
                @Override // java.lang.Runnable
                public void run() {
                    String str2 = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onResetFactoryFailed ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mResetFactoryCallback is null? ");
                    sb.append(TsBydMicManager.this.mResetFactoryCallback == null);
                    sb.append(", msg=");
                    sb.append(str);
                    Log.d(str2, sb.toString());
                    if (TsBydMicManager.this.mResetFactoryCallback != null) {
                        TsBydMicManager.this.mResetFactoryCallback.onResetFactoryFailed(str);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onResetFactorySuccess() {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.17
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onResetFactorySuccess ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mResetFactoryCallback is null? ");
                    sb.append(TsBydMicManager.this.mResetFactoryCallback == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mResetFactoryCallback != null) {
                        TsBydMicManager.this.mResetFactoryCallback.onResetFactorySuccess();
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onUpToDate() {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.9
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onUpToDate ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mCheckNewVersionCallback is null? ");
                    sb.append(TsBydMicManager.this.mCheckNewVersionCallback == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mCheckNewVersionCallback != null) {
                        TsBydMicManager.this.mCheckNewVersionCallback.onUpToDate();
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onUpgradeFailed(final String str, final String str2) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.14
                @Override // java.lang.Runnable
                public void run() {
                    String str3 = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onUpgradeFailed ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mUpgradeCallback is null? ");
                    sb.append(TsBydMicManager.this.mUpgradeCallback == null);
                    sb.append(", reason=");
                    sb.append(str);
                    sb.append(", solution=");
                    sb.append(str2);
                    Log.d(str3, sb.toString());
                    if (TsBydMicManager.this.mUpgradeCallback != null) {
                        TsBydMicManager.this.mUpgradeCallback.onUpgradeFailed(str, str2);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onUpgradeProgress(final int i) {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.12
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onUpgradeProgress ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mUpgradeCallback is null? ");
                    sb.append(TsBydMicManager.this.mUpgradeCallback == null);
                    sb.append(", progress=");
                    sb.append(i);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mUpgradeCallback != null) {
                        TsBydMicManager.this.mUpgradeCallback.onUpgradeProgress(i);
                    }
                }
            });
        }

        @Override // com.micphone.TsBydMicManager.TsBydUIInterface
        public void onUpgradeSuccess() {
            TsBydMicManager.this.manThreadHandler.post(new Runnable() { // from class: com.micphone.TsBydMicManager.7.13
                @Override // java.lang.Runnable
                public void run() {
                    String str = TsBydMicManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onUpgradeSuccess ");
                    sb.append(Thread.currentThread().getName());
                    sb.append(", mUpgradeCallback is null? ");
                    sb.append(TsBydMicManager.this.mUpgradeCallback == null);
                    Log.d(str, sb.toString());
                    if (TsBydMicManager.this.mUpgradeCallback != null) {
                        TsBydMicManager.this.mUpgradeCallback.onUpgradeSuccess();
                    }
                }
            });
        }
    };

    public interface CheckNewVersionCallback {
        void onNewVersionAvailable(String str);

        void onUpToDate();
    }

    public interface DeviceInfoCallback {
        void onDongleInfoChange(String str);

        void onMicInfoChange(String str);
    }

    private final class MicStateThreadHandler extends Handler {
        public MicStateThreadHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2 || i == 3) {
                Bundle data = message.getData();
                TsBydMicManager.this.updateMicSStateInfo(data.getInt("subId"), data.getInt("state"));
                return;
            }
            if (i != 4) {
                return;
            }
            int i2 = message.getData().getInt(TuningProvider.VALUE);
            Log.d(TsBydMicManager.TAG, "receive " + i2);
            TsBydMicManager.this.handleSetEffectDelayMsg(i2);
        }
    }

    public interface ResetFactoryCallback {
        void onResetFactoryFailed(String str);

        void onResetFactorySuccess();
    }

    private interface TsBydUIInterface {
        void attach();

        void detach();

        void onDongleInfoChange(String str);

        void onFactoryMicPowerOff();

        void onInteractEffect(String str);

        void onMicConnectToVehicleStateChange(boolean z);

        void onMicConnectionChanged(boolean z);

        void onMicInfoChange(String str);

        void onMicMeetConditionChange(boolean z);

        void onMicPowerChanged(int i, boolean z);

        void onNewVersionAvailable(String str);

        void onOutsideDongleRejected();

        void onPairResultChanged(int i, int i2, int i3);

        void onPairStateChanged(int i, int i2);

        void onPremiumSpeakerState(boolean z);

        void onResetFactoryFailed(String str);

        void onResetFactorySuccess();

        void onUpToDate();

        void onUpgradeFailed(String str, String str2);

        void onUpgradeProgress(int i);

        void onUpgradeSuccess();
    }

    public interface TsDongleListener {
        default void attach() {
        }

        default void detach() {
        }

        default void onFactoryMicPowerOff() {
        }

        default void onInteractEffect(String str) {
        }

        default void onMicConnectionChanged(boolean z) {
        }

        default void onMicPowerChanged(int i, boolean z) {
        }

        default void onOutsideDongleRejected() {
        }

        default void onPairResultChanged(int i, int i2, int i3) {
        }

        default void onPairStateChanged(int i, int i2) {
        }

        default void onPremiumSpeakerState(boolean z) {
        }
    }

    public interface UpgradeCallback {
        void onUpgradeFailed(String str, String str2);

        void onUpgradeProgress(int i);

        void onUpgradeSuccess();
    }

    public interface UpgradePreconditionCallback {
        void onMicConnectToVehicleStateChange(boolean z);

        void onMicMeetConditionChange(boolean z);
    }

    private TsBydMicManager(Context context, int i) {
        this.mContext = context;
        this.mCarType = i;
        String str = TAG;
        Log.d(str, "mCarType " + this.mCarType);
        Log.d(str, "vehicle_type " + ReflectUtil.getProperty(BYD_PROP_VEHICLE_TYPE, null));
        UsbAudioNativeLoopConfig.addDeviceNameToListByDenZa();
        UsbAudioNativeLoopConfig.addDeviceNameToListByByd();
        HandlerThread handlerThread = new HandlerThread("mic_state");
        handlerThread.start();
        this.mMicStateThreadHandler = new MicStateThreadHandler(handlerThread.getLooper());
        this.mTsBydDeviceInfo = new TsBydDeviceInfo();
        this.mMethodExecutorService = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.micphone.TsBydMicManager.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "MethodExecutor");
            }
        });
        this.mTsBydSoundPool = TsBydSoundPool.getInstance(this.mContext);
        Log.d(str, "version = v1.1.5 + SDK:1.3.19");
    }

    private void callbackCheckNewVersion() {
        TsBydDeviceInfo tsBydDeviceInfo = this.mTsBydDeviceInfo;
        TsBydDongleInfo tsBydDongleInfo = tsBydDeviceInfo.mTsBydDongleInfo;
        boolean z = false;
        boolean z2 = tsBydDongleInfo != null ? tsBydDongleInfo.hasNewVersion : false;
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = tsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().getValue().isHasNewVersion) {
                z = true;
                break;
            }
        }
        Log.d(TAG, "callbackCheckNewVersion=" + this.mTsBydDeviceInfo.toString());
        if (z2 || z) {
            this.mTsBydUIInterface.onNewVersionAvailable(BYD_V4_DONGLE_NEW_VERSION_TIP);
        } else {
            this.mTsBydUIInterface.onUpToDate();
        }
    }

    private void callbackDongleInfo() {
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        if (tsBydDongleInfo == null) {
            this.mTsBydUIInterface.onDongleInfoChange(null);
        } else {
            this.mTsBydUIInterface.onDongleInfoChange(tsBydDongleInfo.infoCollection());
        }
    }

    private void callbackMicAndDongle(TsBydDongleInfo.DongleUpResult dongleUpResult, HashMap<Integer, Integer> map) {
        boolean z;
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            } else if (it.next().getValue().intValue() == 0) {
                z = false;
                break;
            }
        }
        if (z && dongleUpResult == TsBydDongleInfo.DongleUpResult.Success) {
            this.mTsBydUIInterface.onUpgradeSuccess();
            return;
        }
        if (z) {
            this.mTsBydUIInterface.onUpgradeFailed("dongle失败", BuildConfig.FLAVOR);
        } else if (dongleUpResult == TsBydDongleInfo.DongleUpResult.Success) {
            this.mTsBydUIInterface.onUpgradeFailed("mic失败", BuildConfig.FLAVOR);
        } else {
            this.mTsBydUIInterface.onUpgradeFailed("mic和dongle都失败", BuildConfig.FLAVOR);
        }
    }

    private void callbackMicSCanUpgrade() {
        boolean z;
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().getValue().isCanUpgrade) {
                z = true;
                break;
            }
        }
        Log.d(TAG, "callbackMicSCanUpgrade " + this.mTsBydDeviceInfo.toString());
        if (z) {
            this.mTsBydUIInterface.onMicMeetConditionChange(true);
        } else {
            this.mTsBydUIInterface.onMicConnectToVehicleStateChange(false);
            this.mTsBydUIInterface.onMicMeetConditionChange(false);
        }
    }

    private void callbackMicSInfo() {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap = this.mTsBydDeviceInfo.mTsBydMicInfoS;
        String str = new String();
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            str = str + it.next().getValue().infoCollection() + "\n";
        }
        if (TextUtils.isEmpty(str)) {
            str = BYD_V4_DONGLE_MIC_NO_PAIR_TIP;
        }
        this.mTsBydUIInterface.onMicInfoChange(str);
    }

    private void callbackOnlyDongle(TsBydDongleInfo.DongleUpResult dongleUpResult, int i) {
        if (dongleUpResult == TsBydDongleInfo.DongleUpResult.Success) {
            this.mTsBydUIInterface.onUpgradeSuccess();
            return;
        }
        this.mTsBydUIInterface.onUpgradeFailed("接收器升级失败 " + i, BYD_OTA_DONGLE_FAIL_SOLUTION);
    }

    private void callbackOnlyMic(HashMap<Integer, Integer> map) {
        boolean z;
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            } else if (it.next().getValue().intValue() == 0) {
                z = false;
                break;
            }
        }
        if (z) {
            this.mTsBydUIInterface.onUpgradeSuccess();
        } else {
            this.mTsBydUIInterface.onUpgradeFailed("mic失败", BuildConfig.FLAVOR);
        }
    }

    private void callbackUpgradeResult() {
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        TsBydDongleInfo.DongleUpResult dongleUpResult = tsBydDongleInfo != null ? tsBydDongleInfo.upgradeResult : TsBydDongleInfo.DongleUpResult.NoNeed;
        HashMap<Integer, Integer> map = new HashMap<>();
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        while (it.hasNext()) {
            TsBydMicInfo value = it.next().getValue();
            TsBydMicInfo.MicUpResult micUpResult = value.upgradeResult;
            if (micUpResult == TsBydMicInfo.MicUpResult.Success) {
                map.put(Integer.valueOf(value.newSubId), 1);
            } else if (micUpResult == TsBydMicInfo.MicUpResult.Fail) {
                map.put(Integer.valueOf(value.newSubId), 0);
            }
        }
        Log.d(TAG, "callbackUpgradeResult=" + this.mTsBydDeviceInfo.toString());
        TsBydDongleInfo.DongleUpResult dongleUpResult2 = TsBydDongleInfo.DongleUpResult.NoNeed;
        if (dongleUpResult != dongleUpResult2 && map.size() != 0) {
            callbackMicAndDongle(dongleUpResult, map);
            return;
        }
        if (dongleUpResult == dongleUpResult2 && map.size() != 0) {
            callbackOnlyMic(map);
            UsbMicDevice.rebootDongle();
        } else {
            if (dongleUpResult == dongleUpResult2 || map.size() != 0) {
                return;
            }
            callbackOnlyDongle(dongleUpResult, tsBydDongleInfo.upgradeCode);
        }
    }

    private void cheLianMicOff() {
        AudioManager audioManager;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("cheLianMicOff mContext is null=");
        sb.append(this.mContext == null);
        Log.d(str, sb.toString());
        Context context = this.mContext;
        if (context == null || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
            return;
        }
        audioManager.setParameters(MIC_CHE_LIAN_OFF);
        Log.d(str, "cheLianMicOff setParameters thunderstone_mic=off");
    }

    private void cheLianMicOn() {
        AudioManager audioManager;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("cheLianMicOn mContext is null=");
        sb.append(this.mContext == null);
        Log.d(str, sb.toString());
        Context context = this.mContext;
        if (context == null || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
            return;
        }
        audioManager.setParameters(MIC_CHE_LIAN_ON);
        Log.d(str, "cheLianMicOn setParameters thunderstone_mic=on");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeMicChannel() {
        AudioManager audioManager;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("closeMicChannel mContext is null=");
        sb.append(this.mContext == null);
        Log.d(str, sb.toString());
        Context context = this.mContext;
        if (context == null || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
            return;
        }
        audioManager.setParameters(MIC_CHANNEL_CLOSE);
        Log.d(str, "closeMicChannel setParameters Karaoke_enable=false");
    }

    private boolean deviceIsUpgrade() {
        boolean z;
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (it.next().getValue().upgradeResult == TsBydMicInfo.MicUpResult.Start) {
                z = true;
                break;
            }
        }
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        return z || (tsBydDongleInfo != null && tsBydDongleInfo.upgradeResult == TsBydDongleInfo.DongleUpResult.Start);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDongleUpgrade() {
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        if (tsBydDongleInfo != null) {
            if (!tsBydDongleInfo.hasNewVersion) {
                tsBydDongleInfo.upgradeResult = TsBydDongleInfo.DongleUpResult.NoNeed;
                return;
            }
            try {
                tsBydDongleInfo.upgradeResult = TsBydDongleInfo.DongleUpResult.Start;
                String dongleName = getDongleName();
                InputStream inputStreamOpenRawResource = BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName) ? this.mContext.getResources().openRawResource(getRawId(RAW_HT_DONGLE_VERSION_NAME)) : null;
                if (BYD_V1_PRODUCT_NAME_TS_UF.equals(dongleName) || Config.KT_CDC_0712_NAME.equals(dongleName)) {
                    inputStreamOpenRawResource = this.mContext.getResources().openRawResource(getRawId(RAW_UF_DONGLE_VERSION_NAME));
                }
                if (BYD_V1_PRODUCT_NAME_TS_SA3.equals(dongleName) || Config.KT_CDC_0203_NAME.equals(dongleName)) {
                    inputStreamOpenRawResource = this.mContext.getResources().openRawResource(getRawId(RAW_SA3_DONGLE_VERSION_NAME));
                }
                byte[] bArr = new byte[inputStreamOpenRawResource.available()];
                inputStreamOpenRawResource.read(bArr);
                int iUpgrade = UsbMicDevice.upgrade(bArr);
                Log.d(TAG, "dongle_upgrade " + iUpgrade);
                inputStreamOpenRawResource.close();
                tsBydDongleInfo.upgradeCode = iUpgrade;
                if (iUpgrade < 0) {
                    tsBydDongleInfo.upgradeResult = TsBydDongleInfo.DongleUpResult.Fail;
                } else {
                    tsBydDongleInfo.upgradeResult = TsBydDongleInfo.DongleUpResult.Success;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "dongle_upgrade error " + e.toString());
                tsBydDongleInfo.upgradeResult = TsBydDongleInfo.DongleUpResult.Fail;
                tsBydDongleInfo.upgradeCode = -20;
            }
        }
        callbackUpgradeResult();
    }

    private void doDongleUpgradeBefore() {
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        if (tsBydDongleInfo != null) {
            if (!tsBydDongleInfo.hasNewVersion) {
                tsBydDongleInfo.upgradeResult = TsBydDongleInfo.DongleUpResult.NoNeed;
                return;
            }
            if (BYD_V1_PRODUCT_NAME_TS_UF.equals(tsBydDongleInfo.productName) || Config.KT_CDC_0712_NAME.equals(tsBydDongleInfo.productName)) {
                this.interceptDetach = true;
            }
            if (BYD_V1_PRODUCT_NAME_TS_SA3.equals(tsBydDongleInfo.productName) || Config.KT_CDC_0203_NAME.equals(tsBydDongleInfo.productName)) {
                this.interceptDetach = true;
            }
            UsbMicDevice.change2OtaMode();
        }
    }

    private void doMicUpgrade() {
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        while (it.hasNext()) {
            TsBydMicInfo value = it.next().getValue();
            if (!value.isCanUpgrade) {
                value.upgradeResult = TsBydMicInfo.MicUpResult.NoNeed;
                return;
            }
            try {
                value.upgradeResult = TsBydMicInfo.MicUpResult.Start;
                InputStream inputStreamOpenRawResource = this.mContext.getResources().openRawResource(getRawId(RAW_HT_MIC_VERSION_NAME));
                byte[] bArr = new byte[inputStreamOpenRawResource.available()];
                inputStreamOpenRawResource.read(bArr);
                int iBle_upgrade = UsbMicDevice.ble_upgrade(value.newSubId, bArr);
                Log.d(TAG, "ble_upgrade " + iBle_upgrade);
                inputStreamOpenRawResource.close();
                if (iBle_upgrade < 0) {
                    value.upgradeResult = TsBydMicInfo.MicUpResult.Fail;
                } else {
                    value.upgradeResult = TsBydMicInfo.MicUpResult.Success;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "ble_upgrade error " + e.toString());
                value.upgradeResult = TsBydMicInfo.MicUpResult.Fail;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUpgradeProcess() {
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        boolean z = tsBydDongleInfo != null ? tsBydDongleInfo.hasNewVersion : false;
        HashMap map = new HashMap();
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap = this.mTsBydDeviceInfo.mTsBydMicInfoS;
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            TsBydMicInfo value = it.next().getValue();
            if (value.isCanUpgrade) {
                map.put(Integer.valueOf(value.newSubId), 1);
            }
        }
        Log.d(TAG, "doUpgradeProcess=" + this.mTsBydDeviceInfo.toString());
        if (z) {
            if (map.size() == 0) {
                this.mTsBydUIInterface.onUpgradeProgress(tsBydDongleInfo.upgradeProcess);
                return;
            } else if (map.size() == 1) {
                this.mTsBydUIInterface.onUpgradeProgress((getMicProcessCurId() / 3) + ((tsBydDongleInfo.upgradeProcess * 2) / 3));
                return;
            } else {
                if (map.size() == 2) {
                    this.mTsBydUIInterface.onUpgradeProgress((getMicProcessOtherId() / 4) + (getMicProcessCurId() / 4) + ((tsBydDongleInfo.upgradeProcess * 2) / 4));
                    return;
                }
                return;
            }
        }
        if (map.size() == 0) {
            return;
        }
        if (map.size() == 1) {
            Iterator<Map.Entry<Integer, TsBydMicInfo>> it2 = concurrentHashMap.entrySet().iterator();
            while (it2.hasNext()) {
                this.mTsBydUIInterface.onUpgradeProgress(it2.next().getValue().upgradeProcess);
            }
        } else if (map.size() == 2) {
            this.mTsBydUIInterface.onUpgradeProgress((getMicProcessOtherId() / 2) + (getMicProcessCurId() / 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceOtaKt() {
        try {
            InputStream inputStreamOpenRawResource = this.mContext.getResources().openRawResource(getRawId(RAW_UF_DONGLE_VERSION_NAME));
            byte[] bArr = new byte[inputStreamOpenRawResource.available()];
            inputStreamOpenRawResource.read(bArr);
            int iUpgrade = UsbMicDevice.upgrade(bArr);
            Log.d(TAG, "forceOtaKt " + iUpgrade);
            inputStreamOpenRawResource.close();
        } catch (Exception e) {
            Log.d(TAG, "forceOtaKt e " + e);
        }
    }

    private String getDongleName() {
        UsbManager usbManager;
        Context context = this.mContext;
        if (context == null || (usbManager = (UsbManager) context.getSystemService("usb")) == null) {
            return null;
        }
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            int productId = usbDevice.getProductId();
            int vendorId = usbDevice.getVendorId();
            String productName = Build.VERSION.SDK_INT >= 21 ? usbDevice.getProductName() : null;
            Log.d(TAG, "pid = " + productId + ", vid = " + vendorId + " dongleName=" + productName);
            if (Config.isSupportDeviceId(vendorId, productId)) {
                return productName;
            }
        }
        return null;
    }

    public static TsBydMicManager getInstance(Context context, int i) {
        if (instance == null) {
            instance = new TsBydMicManager(context, i);
        }
        return instance;
    }

    private int getMicProcessCurId() {
        if (this.mTsBydDeviceInfo.mTsBydMicInfoS.size() != 0 && this.mTsBydDeviceInfo.mTsBydMicInfoS.containsKey(Integer.valueOf(this.curUpgradeSubId))) {
            return this.mTsBydDeviceInfo.mTsBydMicInfoS.get(Integer.valueOf(this.curUpgradeSubId)).upgradeProcess;
        }
        return 0;
    }

    private int getMicProcessOtherId() {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap = this.mTsBydDeviceInfo.mTsBydMicInfoS;
        if (concurrentHashMap.size() == 0) {
            return 0;
        }
        int i = -1;
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = concurrentHashMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TsBydMicInfo value = it.next().getValue();
            int i2 = this.curUpgradeSubId;
            int i3 = value.newSubId;
            if (i2 != i3) {
                i = i3;
                break;
            }
        }
        if (this.mTsBydDeviceInfo.mTsBydMicInfoS.containsKey(Integer.valueOf(i))) {
            return this.mTsBydDeviceInfo.mTsBydMicInfoS.get(Integer.valueOf(i)).upgradeProcess;
        }
        return 0;
    }

    private int getRawId(String str) {
        Field[] declaredFields = R.raw.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            try {
                if (declaredFields[i].getName().contains(str)) {
                    return declaredFields[i].getInt(R.raw.class);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return -1;
    }

    private String getRawName(String str) {
        for (Field field : R.raw.class.getDeclaredFields()) {
            String name = field.getName();
            if (name.contains(str)) {
                return name;
            }
        }
        return null;
    }

    private byte[] getUDiskEffectFile(int i) {
        if (i == 0) {
            return TsBydUDiskConfig.getInstance(this.mContext).getEffectRecord();
        }
        if (i == 1) {
            return TsBydUDiskConfig.getInstance(this.mContext).getEffectKtv();
        }
        if (i == 2) {
            return TsBydUDiskConfig.getInstance(this.mContext).getEffectMusic();
        }
        if (i != 3) {
            return null;
        }
        return TsBydUDiskConfig.getInstance(this.mContext).getEffectDefault();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCheckDongleInfo() {
        Log.e(TAG, "handleCheckDongleInfo isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic) {
            if (!isSupportShowDeviceInfo()) {
                this.mTsBydUIInterface.onDongleInfoChange(BYD_V4_DONGLE_NO_VERSION_TIP);
            } else {
                updateDongleInfo();
                callbackDongleInfo();
            }
        }
    }

    private void handleCheckMicNewVersion() {
        Log.e(TAG, "handleCheckMicNewVersion isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic && isSupportMicOta()) {
            updateMicSNewVersion();
            callbackCheckNewVersion();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCheckMicSInfo() {
        Log.e(TAG, "handleCheckMicSInfo isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic) {
            if (!isSupportShowDeviceInfo()) {
                this.mTsBydUIInterface.onMicInfoChange(BYD_V4_DONGLE_NO_VERSION_TIP);
            } else {
                updateMicSInfo();
                callbackMicSInfo();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCheckNewVersion() {
        Log.e(TAG, "handleCheckNewVersion isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic) {
            boolean zIsSupportMicOta = isSupportMicOta();
            if (zIsSupportMicOta) {
                updateMicSNewVersion();
            }
            boolean zIsSupportDongleOta = isSupportDongleOta();
            if (zIsSupportDongleOta) {
                updateDongleNewVersion();
            }
            if (zIsSupportMicOta || zIsSupportDongleOta) {
                callbackCheckNewVersion();
            } else {
                this.mTsBydUIInterface.onUpToDate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCheckUpgradePrecondition() {
        Log.e(TAG, "handleCheckUpgradePrecondition isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic) {
            boolean zIsSupportMicOta = isSupportMicOta();
            boolean zIsSupportDongleOta = isSupportDongleOta();
            if (zIsSupportMicOta && zIsSupportDongleOta) {
                updateMicSCanUpgrade();
                callbackMicSCanUpgrade();
            } else if (zIsSupportDongleOta) {
                this.mTsBydUIInterface.onMicConnectToVehicleStateChange(true);
                this.mTsBydUIInterface.onMicMeetConditionChange(true);
            } else {
                this.mTsBydUIInterface.onMicConnectToVehicleStateChange(false);
                this.mTsBydUIInterface.onMicMeetConditionChange(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResetFactory() {
        String str = TAG;
        Log.e(str, "handleResetFactory isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic) {
            if (!isSupportDongleOta()) {
                this.mTsBydUIInterface.onResetFactoryFailed(BYD_V4_DONGLE_MIC_NO_SUPPORT_TIP);
                return;
            }
            int iResetFactory = UsbMicDevice.resetFactory();
            Log.d(str, "handleResetFactory " + iResetFactory);
            if (iResetFactory == 1) {
                this.mTsBydUIInterface.onResetFactorySuccess();
            } else {
                this.mTsBydUIInterface.onResetFactoryFailed("恢复出厂失败");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleSetEffect(int i) {
        InputStream ufEffectFile;
        String dongleName = getDongleName();
        String str = TAG;
        Log.e(str, "setEffect " + dongleName);
        if (BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName)) {
            ufEffectFile = TsBydEffectFile.getInstance(this.mContext).getHtEffectFile(i);
        } else if (BYD_V1_PRODUCT_NAME_TS_SA3.equals(dongleName)) {
            ufEffectFile = TsBydEffectFile.getInstance(this.mContext).getSa3EffectFile(i);
        } else if (BYD_V1_PRODUCT_NAME_TS_UF.equals(dongleName)) {
            ufEffectFile = TsBydEffectFile.getInstance(this.mContext).getUfEffectFile(this.mCarType, i);
        } else {
            Log.d(str, "音效没匹配");
            ufEffectFile = null;
        }
        try {
            byte[] bArr = new byte[ufEffectFile.available()];
            ufEffectFile.read(bArr);
            int iUsbMicLoadAudioEffect = UsbMicDevice.usbMicLoadAudioEffect(bArr);
            Log.d(str, "b load audio myEffect " + iUsbMicLoadAudioEffect);
            ufEffectFile.close();
            return iUsbMicLoadAudioEffect == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetEffectDelayMsg(final int i) {
        ExecutorService executorService = this.mMethodExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.5
            @Override // java.lang.Runnable
            public void run() {
                String str;
                TsBydDongleInfo tsBydDongleInfo = TsBydMicManager.this.mTsBydDeviceInfo.mTsBydDongleInfo;
                if (tsBydDongleInfo != null && (str = tsBydDongleInfo.version) != null && "B".equals(str.substring(str.length() - 1))) {
                    UsbMicDevice.usbMicLoadAudioEffectTipSound(i);
                }
                boolean zIsJarEffectLoad = TsBydUDiskConfig.getInstance(TsBydMicManager.this.mContext).isJarEffectLoad();
                Log.d(TsBydMicManager.TAG, "isUseJar=" + zIsJarEffectLoad);
                if (zIsJarEffectLoad) {
                    TsBydMicManager.this.handleSetEffect(i);
                } else {
                    TsBydMicManager.this.handleSetEffectUDisk(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleSetEffectUDisk(int i) {
        try {
            byte[] uDiskEffectFile = getUDiskEffectFile(i);
            int iUsbMicLoadAudioEffect = -1;
            if (uDiskEffectFile != null) {
                iUsbMicLoadAudioEffect = UsbMicDevice.usbMicLoadAudioEffect(uDiskEffectFile);
                Log.d(TAG, "b load audio myEffect " + iUsbMicLoadAudioEffect);
            } else {
                Log.d(TAG, "effect is null ");
            }
            return iUsbMicLoadAudioEffect == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTsUpgrade() {
        String str = TAG;
        Log.e(str, "handleTsUpgrade isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic) {
            boolean zIsSupportMicOta = isSupportMicOta();
            if (zIsSupportMicOta) {
                doMicUpgrade();
            }
            boolean zIsSupportDongleOta = isSupportDongleOta();
            if (zIsSupportDongleOta) {
                doDongleUpgradeBefore();
            }
            if (!zIsSupportMicOta && !zIsSupportDongleOta) {
                this.mTsBydUIInterface.onUpgradeFailed(BYD_V4_DONGLE_MIC_NO_SUPPORT_TIP, BuildConfig.FLAVOR);
            }
            boolean zIsOnlyMicUpgrade = isOnlyMicUpgrade();
            Log.e(str, "onlyMicUpgrade " + zIsOnlyMicUpgrade);
            if (zIsOnlyMicUpgrade) {
                callbackUpgradeResult();
            }
        }
    }

    private void handlerClearMicState() {
        this.mTsBydDeviceInfo.mTsBydMicInfoS.clear();
    }

    private void handlerMicState(int i, int i2) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap = this.mTsBydDeviceInfo.mTsBydMicInfoS;
        int size = concurrentHashMap.size();
        String str = TAG;
        Log.d(str, "len = " + size);
        if (i2 <= 0) {
            if (concurrentHashMap.containsKey(Integer.valueOf(i))) {
                TsBydMicInfo tsBydMicInfo = concurrentHashMap.get(Integer.valueOf(i));
                tsBydMicInfo.disConnectTime = System.currentTimeMillis();
                tsBydMicInfo.connectState = false;
                if (size == 1) {
                    this.micLogEventDisConnectTime = System.currentTimeMillis();
                    Log.d(str, "logEvent mic connect times, start = " + this.micLogEventConnectTime + ", end = " + this.micLogEventDisConnectTime);
                    TsBydUIInterface tsBydUIInterface = this.mTsBydUIInterface;
                    if (tsBydUIInterface != null) {
                        tsBydUIInterface.onMicConnectionChanged(false);
                        cheLianMicOff();
                    }
                }
                concurrentHashMap.remove(Integer.valueOf(i));
                reportLogEvent(1000, i);
                return;
            }
            return;
        }
        if (!concurrentHashMap.containsKey(Integer.valueOf(i))) {
            TsBydMicInfo tsBydMicInfo2 = new TsBydMicInfo();
            tsBydMicInfo2.newSubId = i;
            tsBydMicInfo2.oldSubId = i - 1;
            concurrentHashMap.put(Integer.valueOf(i), tsBydMicInfo2);
        }
        updateMicSInfo();
        if (this.mTsBydUIInterface != null) {
            TsBydMicInfo tsBydMicInfo3 = concurrentHashMap.get(Integer.valueOf(i));
            this.mTsBydUIInterface.onPairResultChanged(i, tsBydMicInfo3.pairType, 1);
            tsBydMicInfo3.connectState = true;
            reportLogEvent(1001, i);
        }
        if (size == 0) {
            this.micLogEventConnectTime = concurrentHashMap.get(Integer.valueOf(i)).connectTime;
            TsBydUIInterface tsBydUIInterface2 = this.mTsBydUIInterface;
            if (tsBydUIInterface2 != null) {
                tsBydUIInterface2.onMicConnectionChanged(true);
                cheLianMicOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initUFEffect(int i) {
        InputStream ufEffectFile = TsBydEffectFile.getInstance(this.mContext).getUfEffectFile(this.mCarType, i);
        try {
            byte[] bArr = new byte[ufEffectFile.available()];
            ufEffectFile.read(bArr);
            int iUsbMicLoadAudioEffect = UsbMicDevice.usbMicLoadAudioEffect(bArr);
            Log.d(TAG, "b load audio myEffect " + iUsbMicLoadAudioEffect);
            ufEffectFile.close();
            return iUsbMicLoadAudioEffect == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isOnlyMicUpgrade() {
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().getValue().isCanUpgrade) {
                z = true;
            }
        }
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        return z && !(tsBydDongleInfo != null && tsBydDongleInfo.hasNewVersion);
    }

    private boolean isSupportDongleOta() {
        String dongleName = getDongleName();
        Log.d(TAG, "isSupportDongleOta " + dongleName);
        if (BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName) || BYD_V1_PRODUCT_NAME_TS_UF.equals(dongleName)) {
            return true;
        }
        if (BYD_V1_PRODUCT_NAME_TS_SA3.equals(dongleName)) {
        }
        return false;
    }

    private boolean isSupportMicBle() {
        String dongleName = getDongleName();
        Log.d(TAG, "isSupportMicBle " + dongleName);
        return BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName);
    }

    private boolean isSupportMicOta() {
        String dongleName = getDongleName();
        Log.d(TAG, "isSupportMicOta " + dongleName);
        return BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName);
    }

    private boolean isSupportShowDeviceInfo() {
        String dongleName = getDongleName();
        Log.d(TAG, "isSupportShowDeviceInfo " + dongleName);
        return BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName) || BYD_V1_PRODUCT_NAME_TS_SA3.equals(dongleName) || BYD_V1_PRODUCT_NAME_TS_UF.equals(dongleName);
    }

    private boolean is_use_S_dongle() {
        String dongleName = getDongleName();
        Log.d(TAG, "is_use_S_dongle " + dongleName);
        return BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int loadMicEffect(int i, int i2) {
        HashMap<Integer, Integer> map;
        int iUsbMicLoadMicEffect = 0;
        if (i2 < 1 || i2 > 3) {
            return 0;
        }
        String dongleName = getDongleName();
        String str = TAG;
        Log.e(str, "loadMicEffect " + dongleName);
        if (BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName)) {
            map = new HashMap<Integer, Integer>() { // from class: com.micphone.TsBydMicManager.3
                {
                    put(2, Integer.valueOf(R.raw.thunder_mic_effect_ht_loostone));
                    put(3, Integer.valueOf(R.raw.thunder_mic_effect_ht_thunderstone));
                }
            };
        } else if (BYD_V1_PRODUCT_NAME_TS_SA3.equals(dongleName)) {
            map = null;
        } else if (BYD_V1_PRODUCT_NAME_TS_UF.equals(dongleName)) {
            map = new HashMap<Integer, Integer>() { // from class: com.micphone.TsBydMicManager.4
                {
                    put(1, Integer.valueOf(R.raw.thunder_mic_effect_uf_5department));
                    put(2, Integer.valueOf(R.raw.thunder_mic_effect_uf_loostone));
                    put(3, Integer.valueOf(R.raw.thunder_mic_effect_uf_thunderstone));
                }
            };
        } else {
            Log.d(str, "mic音效没匹配");
            map = null;
        }
        if (map == null) {
            return 0;
        }
        InputStream inputStreamOpenRawResource = this.mContext.getResources().openRawResource(map.get(Integer.valueOf(i2)).intValue());
        try {
            byte[] bArr = new byte[inputStreamOpenRawResource.available()];
            inputStreamOpenRawResource.read(bArr);
            iUsbMicLoadMicEffect = UsbMicDevice.usbMicLoadMicEffect(i, bArr);
            Log.e(str, "load audio mic effect " + iUsbMicLoadMicEffect);
            inputStreamOpenRawResource.close();
            return iUsbMicLoadMicEffect;
        } catch (Exception e) {
            e.printStackTrace();
            return iUsbMicLoadMicEffect;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void micDetachUpdateUIInfo() {
        updateDongleInfo();
        callbackDongleInfo();
        handlerClearMicState();
        updateMicSInfo();
        callbackMicSInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void micFactoryTestCallback() {
        if (isSupportMicBle()) {
            this.mTsBydUIInterface.onFactoryMicPowerOff();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void micStateConnectCallback(int i, boolean z) {
        if (isSupportMicBle()) {
            this.mTsBydUIInterface.onMicPowerChanged(i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void micStatePairCallback(int i, int i2) {
        if (isSupportMicBle()) {
            this.mTsBydUIInterface.onPairStateChanged(i, i2);
        }
    }

    private void onCreate(Context context) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCreate context is null = ");
        sb.append(context == null);
        Log.d(str, sb.toString());
        if (context == null) {
            return;
        }
        this.isThunderMic = false;
        MicphoneService.setBydListener(this.mTsBydListener);
        Intent intent = new Intent(context, (Class<?>) MicphoneService.class);
        if (!Config.isFrontService() || Build.VERSION.SDK_INT < 26) {
            context.startService(intent);
        } else {
            context.startForegroundService(intent);
        }
        Log.d(str, "start success");
    }

    private void onDestroy(Context context) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onDestroy context is null = ");
        sb.append(context == null);
        Log.d(str, sb.toString());
        if (context == null) {
            return;
        }
        this.isThunderMic = false;
        MicphoneService.setBydListener(null);
        context.stopService(new Intent(context, (Class<?>) MicphoneService.class));
        Log.d(str, "stop success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openMicChannel() {
        AudioManager audioManager;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("openMicChannel mContext is null=");
        sb.append(this.mContext == null);
        Log.d(str, sb.toString());
        Context context = this.mContext;
        if (context == null || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
            return;
        }
        audioManager.setParameters(MIC_CHANNEL_OPEN);
        Log.d(str, "openMicChannel setParameters Karaoke_enable=true");
    }

    private void reportLogEvent(int i, int i2) {
        HttpUtils.httpGetAsyncRequest(TsBydEventBase.getBaseEvent(this, i, i2), new HttpUtils.HttpListener() { // from class: com.micphone.TsBydMicManager.6
            @Override // com.micphone.utils.HttpUtils.HttpListener
            public void onResult(String str) {
                Log.d(TsBydMicManager.TAG, "s=" + str);
            }
        });
    }

    private void updateDongleInfo() {
        if (this.isThunderMic) {
            TsBydDeviceInfo tsBydDeviceInfo = this.mTsBydDeviceInfo;
            if (tsBydDeviceInfo.mTsBydDongleInfo == null) {
                tsBydDeviceInfo.mTsBydDongleInfo = new TsBydDongleInfo();
            }
        } else {
            this.mTsBydDeviceInfo.mTsBydDongleInfo = null;
        }
        TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
        if (tsBydDongleInfo != null) {
            String str = TAG;
            Log.d(str, "updateDongleInfo start -> " + Thread.currentThread().getName() + ", " + tsBydDongleInfo.toString());
            String strUsbMicGetDongleProductName = UsbMicDevice.usbMicGetDongleProductName();
            String strUsbMicGetDongleVersion = UsbMicDevice.usbMicGetDongleVersion();
            tsBydDongleInfo.productName = strUsbMicGetDongleProductName;
            tsBydDongleInfo.vendorName = MicphoneService.getUsbDeviceManufacturer();
            tsBydDongleInfo.serialNumber = MicphoneService.getUsbDeviceSn();
            tsBydDongleInfo.version = strUsbMicGetDongleVersion;
            Log.d(str, "updateDongleInfo end -> " + Thread.currentThread().getName() + ", " + tsBydDongleInfo.toString());
        }
    }

    private void updateDongleNewVersion() {
        Date date;
        try {
            String strUsbMicGetDongleVersion = UsbMicDevice.usbMicGetDongleVersion();
            String str = TAG;
            Log.d(str, "usbDongleVersion " + strUsbMicGetDongleVersion);
            if (TextUtils.isEmpty(strUsbMicGetDongleVersion)) {
                return;
            }
            String dongleName = getDongleName();
            Date date2 = null;
            if (BYD_V4_PRODUCT_NAME_TS_HT.equals(dongleName)) {
                String rawName = getRawName(RAW_HT_DONGLE_VERSION_NAME);
                Log.d(str, "jarDongleVersion " + rawName);
                if (TextUtils.isEmpty(rawName)) {
                    return;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                String[] strArrSplit = strUsbMicGetDongleVersion.split("_");
                Date date3 = simpleDateFormat.parse(strArrSplit[strArrSplit.length - 1]);
                String[] strArrSplit2 = rawName.split("_");
                date = simpleDateFormat.parse(strArrSplit2[strArrSplit2.length - 1]);
                date2 = date3;
            } else {
                date = null;
            }
            if (BYD_V1_PRODUCT_NAME_TS_UF.equals(dongleName)) {
                String rawName2 = getRawName(RAW_UF_DONGLE_VERSION_NAME);
                Log.d(str, "jarDongleVersion " + rawName2);
                if (TextUtils.isEmpty(rawName2)) {
                    return;
                }
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyMMdd");
                date2 = simpleDateFormat2.parse(strUsbMicGetDongleVersion.substring(strUsbMicGetDongleVersion.length() - 6));
                date = simpleDateFormat2.parse(rawName2.substring(rawName2.length() - 6));
            }
            if (BYD_V1_PRODUCT_NAME_TS_SA3.equals(dongleName)) {
                String rawName3 = getRawName(RAW_SA3_DONGLE_VERSION_NAME);
                Log.d(str, "jarDongleVersion " + rawName3);
                if (TextUtils.isEmpty(rawName3)) {
                    return;
                }
                SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyMMdd");
                date2 = simpleDateFormat3.parse(strUsbMicGetDongleVersion.substring(strUsbMicGetDongleVersion.length() - 6));
                date = simpleDateFormat3.parse(rawName3.substring(rawName3.length() - 6));
            }
            TsBydDongleInfo tsBydDongleInfo = this.mTsBydDeviceInfo.mTsBydDongleInfo;
            if (date2.before(date)) {
                if (tsBydDongleInfo != null) {
                    tsBydDongleInfo.hasNewVersion = true;
                }
            } else if (tsBydDongleInfo != null) {
                tsBydDongleInfo.hasNewVersion = false;
            }
        } catch (Exception e) {
            Log.d(TAG, "updateDongleNewVersion " + e);
        }
    }

    private void updateMicSCanUpgrade() {
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        while (it.hasNext()) {
            TsBydMicInfo value = it.next().getValue();
            int i = value.battery;
            boolean z = value.isHasNewVersion;
            Log.d(TAG, "micBattery " + i + ", hasNew " + z);
            if (z && i > 0) {
                value.isCanUpgrade = true;
            }
        }
    }

    private void updateMicSInfo() {
        String formatName;
        Log.d(TAG, "updateMicSInfo start -> " + Thread.currentThread().getName() + ", " + this.mTsBydDeviceInfo.mTsBydMicInfoS.toString());
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
        while (it.hasNext()) {
            TsBydMicInfo value = it.next().getValue();
            int i = value.oldSubId;
            String strUsbMicGetMicName = UsbMicDevice.usbMicGetMicName(i);
            String strValueOf = String.valueOf(UsbMicDevice.usbMicGetMicID(i));
            String strUsbMicGetMicVersion = UsbMicDevice.usbMicGetMicVersion(i);
            int iUsbMicGetMicPairType = UsbMicDevice.usbMicGetMicPairType(i);
            int iUsbMicGetMicPower = UsbMicDevice.usbMicGetMicPower(i);
            value.productName = strUsbMicGetMicName;
            if (strValueOf != null && (formatName = value.getFormatName(strValueOf)) != null && formatName.length() > 1) {
                value.vendorName = formatName.substring(0, 1);
                value.serialNumber = formatName.substring(1);
            }
            value.version = strUsbMicGetMicVersion;
            value.battery = iUsbMicGetMicPower;
            value.pairType = iUsbMicGetMicPairType;
            value.connectTime = System.currentTimeMillis();
            value.supplier = this.mMicSupplierS.get(Integer.valueOf(value.newSubId)).intValue();
        }
        Log.d(TAG, "updateMicSInfo end -> " + Thread.currentThread().getName() + ", " + this.mTsBydDeviceInfo.mTsBydMicInfoS.toString());
    }

    private void updateMicSNewVersion() {
        String rawName = getRawName(RAW_HT_MIC_VERSION_NAME);
        String str = TAG;
        Log.d(str, "jarMicVersion " + rawName);
        if (TextUtils.isEmpty(rawName)) {
            return;
        }
        try {
            String[] strArrSplit = rawName.split("_");
            int i = Integer.parseInt(strArrSplit[strArrSplit.length - 1]);
            Log.d(str, "intJarMicVersion " + i);
            Iterator<Map.Entry<Integer, TsBydMicInfo>> it = this.mTsBydDeviceInfo.mTsBydMicInfoS.entrySet().iterator();
            while (it.hasNext()) {
                TsBydMicInfo value = it.next().getValue();
                String str2 = value.version;
                if (str2 != null) {
                    String[] strArrSplit2 = str2.split("-");
                    if (strArrSplit2.length > 0) {
                        int i2 = Integer.parseInt(strArrSplit2[0]);
                        Log.d(TAG, "micUsbVersion " + i2);
                        if (i2 < i) {
                            value.isHasNewVersion = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "updateMicSNewVersion " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMicSStateInfo(int i, int i2) {
        if (deviceIsUpgrade()) {
            Log.d(TAG, "upgrade ...");
            return;
        }
        handlerMicState(i, i2);
        handleCheckMicSInfo();
        handleCheckMicNewVersion();
        handleCheckUpgradePrecondition();
    }

    public int cancelPair(int i) {
        String str = TAG;
        Log.d(str, "cancelPair=" + i);
        Log.e(str, "cancelPair isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic || !isSupportMicBle()) {
            return -1;
        }
        int iCancelPair = UsbMicDevice.cancelPair(i);
        Log.d(str, "cancelPair ret=" + iCancelPair);
        return iCancelPair;
    }

    public void checkDeviceInfo(DeviceInfoCallback deviceInfoCallback) {
        Log.d(TAG, "checkDeviceInfo " + deviceInfoCallback);
        this.mDeviceInfoCallback = deviceInfoCallback;
        ExecutorService executorService = this.mMethodExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.11
            @Override // java.lang.Runnable
            public void run() {
                TsBydMicManager.this.handleCheckDongleInfo();
                TsBydMicManager.this.handleCheckMicSInfo();
            }
        });
    }

    public void checkNewVersion(CheckNewVersionCallback checkNewVersionCallback) {
        Log.d(TAG, "checkNewVersion " + checkNewVersionCallback);
        this.mCheckNewVersionCallback = checkNewVersionCallback;
        ExecutorService executorService = this.mMethodExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.8
            @Override // java.lang.Runnable
            public void run() {
                TsBydMicManager.this.handleCheckNewVersion();
            }
        });
    }

    public void checkUpgradePrecondition(UpgradePreconditionCallback upgradePreconditionCallback) {
        Log.d(TAG, "checkUpgradePrecondition " + upgradePreconditionCallback);
        this.mUpgradePreconditionCallback = upgradePreconditionCallback;
        ExecutorService executorService = this.mMethodExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.9
            @Override // java.lang.Runnable
            public void run() {
                TsBydMicManager.this.handleCheckUpgradePrecondition();
            }
        });
    }

    public int confirmPair(int i) {
        String str = TAG;
        Log.d(str, "confirmPair=" + i);
        Log.e(str, "confirmPair isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic || !isSupportMicBle()) {
            return -1;
        }
        int iConfirmPair = UsbMicDevice.confirmPair(i);
        Log.d(str, "confirmPair ret=" + iConfirmPair);
        return iConfirmPair;
    }

    public void erasePair() {
        String str = TAG;
        Log.e(str, "erasePair isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic && isSupportMicBle()) {
            Log.d(str, "erasePair " + UsbMicDevice.erasePair());
        }
    }

    public int getBLePairLength() {
        String str = TAG;
        Log.e(str, "getBLePairLength isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic || !isSupportMicBle()) {
            return -1;
        }
        int bLePairLength = UsbMicDevice.getBLePairLength();
        Log.d(str, "getBLePairLength " + bLePairLength);
        return bLePairLength;
    }

    public int getCarType() {
        return this.mCarType;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getMicBattery(int i) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        String str = TAG;
        Log.d(str, "getMicBattery=" + i);
        Log.e(str, "getMicBattery isThunderMic = " + this.isThunderMic);
        if (this.isThunderMic && (concurrentHashMap = this.mTsBydDeviceInfo.mTsBydMicInfoS) != null && concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return concurrentHashMap.get(Integer.valueOf(i)).battery;
        }
        return -1;
    }

    public boolean getMicConnectionState() {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        String str = TAG;
        Log.d(str, "getMicConnectionState");
        Log.e(str, "getMicConnectionState isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic || (concurrentHashMap = this.mTsBydDeviceInfo.mTsBydMicInfoS) == null) {
            return false;
        }
        Iterator<Map.Entry<Integer, TsBydMicInfo>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().connectState) {
                return true;
            }
        }
        return false;
    }

    public String getMicSupplier(int i) {
        ConcurrentHashMap<Integer, TsBydMicInfo> concurrentHashMap;
        String str = TAG;
        Log.d(str, "getMicSupplier=" + i);
        Log.e(str, "getMicSupplier isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic || (concurrentHashMap = this.mTsBydDeviceInfo.mTsBydMicInfoS) == null || !concurrentHashMap.containsKey(Integer.valueOf(i))) {
            return BuildConfig.FLAVOR;
        }
        int i2 = concurrentHashMap.get(Integer.valueOf(i)).supplier;
        return i2 != 2 ? i2 != 3 ? BuildConfig.FLAVOR : "TH" : "LS";
    }

    public String getSdkVersion() {
        return SDK_VERSION_NAME;
    }

    public TsBydDeviceInfo getmTsBydDeviceInfo() {
        return this.mTsBydDeviceInfo;
    }

    public boolean isBindMic() {
        return this.isThunderMic;
    }

    public boolean isPremiumSpeakerConnected() {
        String str = TAG;
        Log.d(str, "isPremiumSpeakerConnected");
        Log.e(str, "isPremiumSpeakerConnected isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic) {
        }
        return false;
    }

    public synchronized boolean isThunderMic() {
        String property = ReflectUtil.getProperty(BYD_PROP_INSIDE_MIC, "false");
        String str = TAG;
        Log.d(str, "insideMic=" + property);
        if ("true".equals(property)) {
            return false;
        }
        Log.d(str, "isThunderMic=" + this.isThunderMic);
        if (this.isThunderMic) {
            return true;
        }
        Log.d(str, "check thunder mic");
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        UsbManager usbManager = (UsbManager) context.getSystemService("usb");
        if (usbManager == null) {
            return false;
        }
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            int productId = usbDevice.getProductId();
            int vendorId = usbDevice.getVendorId();
            String productName = Build.VERSION.SDK_INT >= 21 ? usbDevice.getProductName() : null;
            String str2 = TAG;
            Log.d(str2, "pid = " + productId + ", vid = " + vendorId + " dongleName=" + productName);
            boolean zIsSupportDeviceId = Config.isSupportDeviceId(vendorId, productId);
            boolean zIsSupportDevice = UsbAudioNativeLoopConfig.isSupportDevice(this.mContext, productName);
            boolean zIsKtCdc = Config.isKtCdc(productName);
            Log.d(str2, "isSupport = " + zIsSupportDeviceId + ", isSupportByConfig = " + zIsSupportDevice + " isOta=" + zIsKtCdc);
            if (zIsSupportDeviceId && (zIsSupportDevice || zIsKtCdc)) {
                Log.d(str2, "match mic");
                return true;
            }
        }
        Log.d(TAG, "end read");
        return false;
    }

    public boolean micDeInit() {
        closeMicChannel();
        onDestroy(this.mContext);
        MicStateThreadHandler micStateThreadHandler = this.mMicStateThreadHandler;
        if (micStateThreadHandler == null) {
            return true;
        }
        micStateThreadHandler.removeCallbacksAndMessages(null);
        return true;
    }

    public boolean micInit() {
        onCreate(this.mContext);
        return true;
    }

    public void resetFactory(ResetFactoryCallback resetFactoryCallback) {
        Log.d(TAG, "resetFactory " + resetFactoryCallback);
        this.mResetFactoryCallback = resetFactoryCallback;
        ExecutorService executorService = this.mMethodExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.12
            @Override // java.lang.Runnable
            public void run() {
                TsBydMicManager.this.handleResetFactory();
            }
        });
    }

    public void setDongleListener(TsDongleListener tsDongleListener) {
        Log.d(TAG, "setDongleListener " + tsDongleListener);
        this.mListener = tsDongleListener;
    }

    public boolean setEffect(int i, boolean z) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setEffect context is null = ");
        sb.append(this.mContext == null);
        sb.append(", value=");
        sb.append(i);
        sb.append(",isTip=");
        sb.append(z);
        Log.d(str, sb.toString());
        Log.d(str, "mCarType " + this.mCarType);
        if (this.mContext == null) {
            return false;
        }
        Log.e(str, "setEffect isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic) {
            return false;
        }
        if (z) {
            TsBydSoundPool tsBydSoundPool = this.mTsBydSoundPool;
            if (tsBydSoundPool != null) {
                tsBydSoundPool.playTip(i);
            }
            Log.d(str, "tip_sound ");
        }
        if (i < 0) {
            i = 0;
        }
        if (i > 3) {
            i = 3;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(TuningProvider.VALUE, i);
        Message message = new Message();
        message.what = 4;
        message.setData(bundle);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.lastSetEffectTime;
        if (0 >= j || j >= HANDLE_SET_EFFECT_DELAY) {
            this.lastSetEffectTime = jCurrentTimeMillis;
            this.mMicStateThreadHandler.sendMessageDelayed(message, HANDLE_SET_EFFECT_DELAY);
            return true;
        }
        this.mMicStateThreadHandler.removeMessages(4);
        MicStateThreadHandler micStateThreadHandler = this.mMicStateThreadHandler;
        long j2 = HANDLE_SET_EFFECT_DELAY - j;
        micStateThreadHandler.sendMessageDelayed(message, j2);
        Log.d(str, "drop other,reserve = " + i + ", delay=" + j2);
        return true;
    }

    public boolean setReverb(int i) {
        String str = TAG;
        Log.d(str, "setReverb=" + i);
        Log.e(str, "setReverb isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic) {
            return false;
        }
        if (i < 0) {
            i = 0;
        }
        if (i > 10) {
            i = 10;
        }
        int iCeil = (int) (is_use_S_dongle() ? Math.ceil((((double) i) / 10.0d) * 20.0d) : Math.ceil((((double) i) / 10.0d) * 15.0d));
        Log.d(str, "setTransferVolume=" + iCeil);
        int iUsbMicSetReverb = UsbMicDevice.usbMicSetReverb(iCeil);
        Log.d(str, "setReverb=" + iUsbMicSetReverb);
        return iUsbMicSetReverb == 0;
    }

    public boolean setVolume(int i) {
        String str = TAG;
        Log.d(str, "setVolume=" + i);
        Log.e(str, "setVolume isThunderMic = " + this.isThunderMic);
        if (!this.isThunderMic) {
            return false;
        }
        if (i < 0) {
            i = 0;
        }
        if (i > 10) {
            i = 10;
        }
        int iCeil = (int) (is_use_S_dongle() ? Math.ceil((((double) i) / 10.0d) * 20.0d) : Math.ceil((((double) i) / 10.0d) * 15.0d));
        Log.d(str, "setTransferVolume=" + iCeil);
        int iUsbMicSetMicVolume = UsbMicDevice.usbMicSetMicVolume(0, iCeil);
        Log.d(str, "setVolume=" + iUsbMicSetMicVolume);
        return iUsbMicSetMicVolume == 0;
    }

    public void ts_upgrade(UpgradeCallback upgradeCallback) {
        Log.d(TAG, "ts_upgrade " + upgradeCallback);
        this.mUpgradeCallback = upgradeCallback;
        ExecutorService executorService = this.mMethodExecutorService;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.mMethodExecutorService.execute(new Runnable() { // from class: com.micphone.TsBydMicManager.10
            @Override // java.lang.Runnable
            public void run() {
                TsBydMicManager.this.handleTsUpgrade();
            }
        });
    }
}
