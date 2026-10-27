package com.neusoft.na.ehp.manager;

import android.content.Context;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.neusoft.na.ehp.AndroidManager;
import com.neusoft.na.ehp.Ehp;
import com.neusoft.na.ehp.IEhpInfoFetchCallBack;
import com.neusoft.na.ehp.utils.ConfigUtil;
import com.neusoft.na.ehp.utils.DataCovert;
import com.neusoft.na.ehp.utils.LocationHandler;
import com.neusoft.na.ehp.utils.MessageBean;
import com.neusoft.na.ehp.utils.MessageType;
import java.lang.reflect.Method;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class EhpManager {
    private static final String KEY = "isa_active_data_version";
    private static final String PERMISSION = "android.permission.BYDAUTO_STATISTIC_COMMON";
    private static final String TAG = "NeuEhpManager";
    public static boolean isPostEventBus = false;
    private AndroidManager androidManager;
    private IURIDCallback mCallback;
    private Ehp mEhp;
    private int mGpsStatus;
    private Integer mActiveStatus = null;
    private Boolean mServiceStatus = null;
    private String mVersion = null;
    private MessageBean bean = new MessageBean();

    public interface IURIDCallback {
        void onEhpStoped();

        void onURID(int i);
    }

    public EhpManager(final Context context) {
        final boolean z = context.getSharedPreferences("dltLogStatus", 0).getBoolean("openDlt", false);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.na.ehp.manager.EhpManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m62lambda$new$0$comneusoftnaehpmanagerEhpManager(context, z);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-neusoft-na-ehp-manager-EhpManager, reason: not valid java name */
    /* synthetic */ void m62lambda$new$0$comneusoftnaehpmanagerEhpManager(Context context, final boolean z) {
        new LocationHandler(context).setDataUpdateListener(new LocationHandler.IDataUpdateListener() { // from class: com.neusoft.na.ehp.manager.EhpManager.1
            @Override // com.neusoft.na.ehp.utils.LocationHandler.IDataUpdateListener
            public void onLocationUpdate(Location location) {
                if (EhpManager.this.androidManager == null || location == null || EhpManager.this.mEhp == null || !EhpManager.this.mEhp.isRunning()) {
                    return;
                }
                if (z) {
                    Log.i(EhpManager.TAG, "onLocationUpdate:" + location.toString());
                }
                EhpManager.this.postEventBus(MessageType.LOCATION, location);
                EhpManager.this.androidManager.setPosition(System.currentTimeMillis(), location.getLongitude(), location.getLatitude(), Double.valueOf(location.getAltitude()), Float.valueOf(location.getBearing()), Float.valueOf(location.getSpeed()), EhpManager.this.mGpsStatus);
            }

            @Override // com.neusoft.na.ehp.utils.LocationHandler.IDataUpdateListener
            public void onGasStatusUpdate(int i) {
                EhpManager.this.mGpsStatus = i;
            }
        }).start();
    }

    public void start(final Context context) {
        Log.d(TAG, "start");
        if (TextUtils.isEmpty(this.mVersion) || "reserved".equals(this.mVersion)) {
            Log.w(TAG, "start: no db");
            return;
        }
        final boolean z = context.checkSelfPermission("android.permission.BYDAUTO_STATISTIC_COMMON") == 0;
        Log.i(TAG, "start: hasPermission:" + z);
        Ehp ehp = this.mEhp;
        if (ehp != null && ehp.isRunning()) {
            Log.i(TAG, "ehp core is running");
            return;
        }
        this.mEhp = new Ehp();
        Log.i(TAG, "ehp start: " + this.mEhp.start(new ConfigUtil(context).getConfig()));
        final boolean z2 = context.getSharedPreferences("dltLogStatus", 0).getBoolean("openDlt", false);
        this.androidManager = (AndroidManager) this.mEhp.getFeature(AndroidManager.class);
        setEhpControl();
        Log.i(TAG, "register");
        this.androidManager.register(new IEhpInfoFetchCallBack() { // from class: com.neusoft.na.ehp.manager.EhpManager.2
            @Override // com.neusoft.na.ehp.IEhpInfoFetchCallBack
            public void onBydIsaMessage(int[] iArr, double d, double d2, int[] iArr2) {
                int[] iArrMergeArray = iArr2 != null ? DataCovert.mergeArray(iArr, iArr2) : iArr;
                EhpManager.this.postEventBus(MessageType.DATA, iArrMergeArray);
                if (EhpManager.this.mActiveStatus.intValue() == 2) {
                    iArr[12] = 0;
                    iArr[13] = 0;
                    iArr[14] = 0;
                    iArr[15] = 0;
                }
                if ((EhpManager.this.mActiveStatus.intValue() == 0 || EhpManager.this.mActiveStatus.intValue() == 2) && z) {
                    int iSendIsaDataToAdas = BYDAutoStatisticDevice.getInstance(context).sendIsaDataToAdas(iArr);
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i : iArrMergeArray) {
                        stringBuffer.append(i);
                        stringBuffer.append(",");
                    }
                    if (z2) {
                        Log.i(EhpManager.TAG, "ehp data: to system status: " + iSendIsaDataToAdas + "->" + stringBuffer.toString() + d + "," + d2);
                    } else {
                        Log.i(EhpManager.TAG, "ehp data: to system status: " + iSendIsaDataToAdas + "->" + stringBuffer.toString());
                    }
                }
            }

            @Override // com.neusoft.na.ehp.IEhpInfoFetchCallBack
            public void onCurrentPosition(int i) {
                Log.i(EhpManager.TAG, "onCurrentPosition: " + i);
                if (EhpManager.this.mCallback != null) {
                    EhpManager.this.mCallback.onURID(i);
                }
            }
        });
        Boolean bool = this.mServiceStatus;
        if (bool != null) {
            setServiceStatus(bool.booleanValue());
        }
        Log.i(TAG, "register done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postEventBus(MessageType messageType, Object obj) {
        try {
            if (isPostEventBus) {
                if (messageType == MessageType.DATA) {
                    this.bean.setData((int[]) obj);
                    this.bean.setMessageType(messageType);
                } else if (messageType == MessageType.LOCATION) {
                    this.bean.setLocation((Location) obj);
                    this.bean.setMessageType(messageType);
                } else if (messageType == MessageType.DB_VERSION) {
                    Log.i(TAG, "postVersion==");
                    String str = (String) obj;
                    Log.i(TAG, "postVersion:" + str);
                    this.bean.setDb_version(str);
                    this.bean.setMessageType(messageType);
                }
                EventBus.getDefault().post(this.bean);
            }
        } catch (Exception e) {
            Log.i(TAG, "postEventBus:" + e.toString());
        }
    }

    public void stop() {
        Log.i(TAG, "stop: " + this.mEhp);
        Ehp ehp = this.mEhp;
        if (ehp != null) {
            boolean zIsRunning = ehp.isRunning();
            Log.i(TAG, "stop isRunning: " + zIsRunning);
            if (zIsRunning) {
                Log.i(TAG, "stop: " + this.mEhp.stop());
            }
            this.mEhp = null;
            this.androidManager = null;
        }
        IURIDCallback iURIDCallback = this.mCallback;
        if (iURIDCallback != null) {
            iURIDCallback.onEhpStoped();
        }
    }

    public void setActive(Context context, int i) {
        this.mActiveStatus = Integer.valueOf(i);
        setEhpControl();
        setSystemProperties(context);
        sendErrorData(context, i == 0 ? 1 : 4);
    }

    public void setServiceStatus(boolean z) {
        Log.i(TAG, "setServiceStatus: " + z);
        this.mServiceStatus = Boolean.valueOf(z);
        AndroidManager androidManager = this.androidManager;
        if (androidManager != null) {
            androidManager.trafficServiceStatus(z);
        }
    }

    private void setEhpControl() {
        Log.i(TAG, "setEhpControl");
        if (this.androidManager == null || this.mActiveStatus == null) {
            return;
        }
        Log.i(TAG, "setEhpControl: " + this.mActiveStatus);
        this.androidManager.ehpControl(this.mActiveStatus.intValue() == 0 || this.mActiveStatus.intValue() == 2);
    }

    public void setVersion(Context context, String str) {
        if (TextUtils.isEmpty(str) || "reserved".equals(str)) {
            sendErrorData(context, 3);
        } else {
            this.mVersion = str;
        }
        DataCovert.dbVersion = str;
        Log.i(TAG, "dbVersion: " + DataCovert.dbVersion);
        postEventBus(MessageType.DB_VERSION, str);
        setSystemProperties(context);
    }

    public void sendErrorData(Context context, int i) {
        if (context.checkSelfPermission("android.permission.BYDAUTO_STATISTIC_COMMON") == 0) {
            Log.i(TAG, "sendErrorData: " + BYDAutoStatisticDevice.getInstance(context).sendIsaDataToAdas(new int[]{31, 0, 0, 0, 8191, 0, 31, 31, 0, 255, 1, i, 0, 0, 0, 0, 0}));
        }
    }

    public void startVp() {
        Log.i(TAG, "startVp: " + this.androidManager);
        AndroidManager androidManager = this.androidManager;
        if (androidManager != null) {
            androidManager.positionReplayControl(true);
        }
    }

    public void stopVP() {
        Log.i(TAG, "stopVP: " + this.androidManager);
        AndroidManager androidManager = this.androidManager;
        if (androidManager != null) {
            androidManager.positionReplayControl(false);
        }
    }

    public void setCallback(IURIDCallback iURIDCallback) {
        this.mCallback = iURIDCallback;
    }

    private void setSystemProperties(Context context) {
        Log.i(TAG, "setSystemProperties");
        if (this.mVersion == null || this.mActiveStatus == null) {
            return;
        }
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Method method = clsLoadClass.getMethod("set", String.class, String.class);
            String str = this.mVersion + "." + (this.mActiveStatus.intValue() == 0 ? 2 : 1);
            method.invoke(clsLoadClass, KEY, str);
            Log.i(TAG, "[setSystemProperties] --> value == " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
