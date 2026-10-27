package com.neusoft.na.ehp.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.yun.AbsBYDAutoYunListener;
import android.hardware.bydauto.yun.ActivateInfo;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.ehp.BuildConfig;
import com.neusoft.na.ehp.dbu.DialogActivity;
import com.neusoft.na.ehp.manager.EhpManager;
import com.neusoft.na.ehp.utils.ConfigUtil;
import com.neusoft.na.ehp.utils.FileUtil;
import com.neusoft.na.ehpdbuserver.EhpDbuOnMsg;
import com.neusoft.na.ehpdbuserver.EhpDbuServer;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.java_websocket.extensions.ExtensionRequestData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NativeManager extends Binder {
    private static final String PERMISSION = "android.permission.BYDAUTO_BODYWORK_COMMON";
    private static final long STATUS_DBU_START_FINISH = 6;
    private static final long STATUS_STOP_EHP = 12;
    private static final String TAG = "NeuEhpDbuManager";
    private static final int WHAT = 10086;
    private static NativeManager dbuManager;
    private BYDAutoBodyworkDevice bydAutoBodyworkDevice;
    private BYDAutoYunDevice bydAutoYunDevice;
    private EhpDbuServer ehpDbuServer;
    private EhpManager ehpManager;
    private IProgressListener listener;
    private IActiveChangedListener mActiveChangedListener;
    private boolean mChecked;
    private String mNewVersion;
    private boolean mScanUsbPathOnLaunch;
    private boolean mDbuStartStatus = false;
    private Handler mScheduledHandler = new Handler(Looper.getMainLooper()) { // from class: com.neusoft.na.ehp.manager.NativeManager.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Log.i(NativeManager.TAG, "activation: time out");
            NativeManager.this.sendResult(false);
        }
    };
    private int mActiveStatus = 2;

    public interface IActiveChangedListener {
        void onChanged(int i);
    }

    public interface IProgressListener {
        void action(int i, int i2, int i3);
    }

    public static synchronized NativeManager getManager(Context context) {
        if (dbuManager == null) {
            dbuManager = new NativeManager(context);
        }
        return dbuManager;
    }

    private NativeManager(Context context) {
        EhpManager ehpManager = new EhpManager(context);
        this.ehpManager = ehpManager;
        ehpManager.setCallback(new EhpManager.IURIDCallback() { // from class: com.neusoft.na.ehp.manager.NativeManager.1
            @Override // com.neusoft.na.ehp.manager.EhpManager.IURIDCallback
            public void onURID(int i) {
                NativeManager.this.ehpDbuServer.SetVehiclePosURID(i);
            }

            @Override // com.neusoft.na.ehp.manager.EhpManager.IURIDCallback
            public void onEhpStoped() {
                NativeManager.this.ehpDbuServer.EhpStopped();
            }
        });
    }

    public void init(final Context context) {
        final ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(new Runnable() { // from class: com.neusoft.na.ehp.manager.NativeManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m63lambda$init$0$comneusoftnaehpmanagerNativeManager(context);
            }
        }, 0L, 100L, TimeUnit.MILLISECONDS);
        this.bydAutoYunDevice = BYDAutoYunDevice.getInstance(context);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.na.ehp.manager.NativeManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m64lambda$init$1$comneusoftnaehpmanagerNativeManager(context);
            }
        });
        ConfigUtil configUtil = new ConfigUtil(context);
        boolean zInitConfig = configUtil.initConfig(context);
        Log.i(TAG, "init: config:" + zInitConfig);
        if (zInitConfig) {
            this.ehpDbuServer = new EhpDbuServer();
            String absolutePath = new FileUtil().getAssetsInstallDir(context).getAbsolutePath();
            this.ehpDbuServer.register(new EhpDbuOnMsg() { // from class: com.neusoft.na.ehp.manager.NativeManager.2
                @Override // com.neusoft.na.ehpdbuserver.EhpDbuOnMsg
                public void onEhpDBUOnMessage(long j, long j2, String str) {
                    Log.i(NativeManager.TAG, "onEhpDBUOnMessage:" + j + "," + j2 + "," + str);
                    if (j == NativeManager.STATUS_DBU_START_FINISH || j == 13) {
                        NativeManager.this.mDbuStartStatus = true;
                        Log.i(NativeManager.TAG, "onEhpDBUOnMessage mNeedScanUsbPathOnLaunch: " + NativeManager.this.mScanUsbPathOnLaunch);
                        if (NativeManager.this.mScanUsbPathOnLaunch) {
                            String dbPath = FileUtil.getDbPath(context);
                            if (!TextUtils.isEmpty(dbPath) && j == NativeManager.STATUS_DBU_START_FINISH) {
                                NativeManager.this.setUsbPath(dbPath);
                            }
                        }
                        NativeManager.this.ehpManager.start(context);
                        NativeManager.this.ehpDbuServer.SetOverwiteAttributeForUSBUpdate(true);
                    } else if (j == 9) {
                        Log.i(NativeManager.TAG, "onEhpDBUOnMessage:status == 9");
                        NativeManager.this.ehpManager.setVersion(context, str);
                    } else if (j == 10) {
                        if (NativeManager.this.listener != null) {
                            NativeManager.this.listener.action(0, (int) j2, -1);
                        }
                    } else if (j != 0) {
                        if (j == NativeManager.STATUS_STOP_EHP) {
                            NativeManager.this.ehpManager.stop();
                        } else if (j != 2) {
                            if (NativeManager.this.listener != null) {
                                NativeManager.this.listener.action(2, (int) j, (int) j2);
                            } else {
                                NativeManager.this.startDialog(context, 2L, j, (int) j2);
                            }
                        }
                    }
                    Log.i(NativeManager.TAG, "onEhpDBUOnMessage end. status:" + j + "," + j2);
                }

                @Override // com.neusoft.na.ehpdbuserver.EhpDbuOnMsg
                public void onEhpDBUTargetDBVersion(long j, String str) {
                    Log.i(NativeManager.TAG, "onEhpDBUTargetDBVersion: " + str);
                    NativeManager.this.mNewVersion = str;
                    NativeManager.this.startDialog(context, 1L, 0L, -1);
                    Log.i(NativeManager.TAG, "onEhpDBUTargetDBVersion end. version:" + str);
                }

                @Override // com.neusoft.na.ehpdbuserver.EhpDbuOnMsg
                public void onEhpLicenseStatus(int i, long j, long j2, int i2, boolean z) {
                    Log.i(NativeManager.TAG, "onEhpLicenseStatus: " + i + "," + z);
                    NativeManager.this.mActiveStatus = i;
                    try {
                        if (NativeManager.this.mActiveChangedListener != null) {
                            NativeManager.this.mActiveChangedListener.onChanged(NativeManager.this.mActiveStatus);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        NativeManager.this.mActiveChangedListener = null;
                    }
                    boolean z2 = i == 0;
                    if (z2) {
                        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
                    }
                    NativeManager.this.ehpManager.setActive(context, i);
                    if (!z) {
                        NativeManager.this.activation(context, z2, i2, j, j2);
                    }
                    Log.i(NativeManager.TAG, "onEhpLicenseStatus end. active:" + z2);
                }

                @Override // com.neusoft.na.ehpdbuserver.EhpDbuOnMsg
                public void onEhpTIServiceStatus(boolean z) {
                    Log.i(NativeManager.TAG, "onEhpTIServiceStatus: " + z);
                    NativeManager.this.ehpManager.setServiceStatus(z);
                }
            });
            Log.i(TAG, "init: hasPermission:" + (context.checkSelfPermission("android.permission.BYDAUTO_BODYWORK_COMMON") == 0));
            String deviceId = getDeviceId(context);
            String countryCode = getCountryCode(context);
            Log.i(TAG, "init: autoVIN:[" + deviceId + "], countryCode:" + countryCode);
            long jStart = this.ehpDbuServer.start(absolutePath, deviceId, countryCode);
            Log.i(TAG, "init: start:" + jStart);
            if (jStart == -3) {
                configUtil.deleteAll(context);
                Log.i(TAG, "init2: config:" + configUtil.initConfig(context));
                Log.i(TAG, "init2: start:" + this.ehpDbuServer.start(absolutePath, deviceId, countryCode));
            }
        }
    }

    /* JADX INFO: renamed from: lambda$init$0$com-neusoft-na-ehp-manager-NativeManager, reason: not valid java name */
    /* synthetic */ void m63lambda$init$0$comneusoftnaehpmanagerNativeManager(Context context) {
        this.ehpManager.sendErrorData(context, 4);
    }

    /* JADX INFO: renamed from: lambda$init$1$com-neusoft-na-ehp-manager-NativeManager, reason: not valid java name */
    /* synthetic */ void m64lambda$init$1$comneusoftnaehpmanagerNativeManager(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.neusoft.na.ehp.action.test.value");
        intentFilter.addAction("com.neusoft.na.ehp.action.test.start");
        intentFilter.addAction("com.neusoft.na.ehp.action.test");
        context.registerReceiver(new TestBroadcast(), intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDialog(Context context, long j, long j2, int i) {
        Log.i(TAG, "startDialog: " + j);
        Intent intent = new Intent(context, (Class<?>) DialogActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, j);
        intent.putExtra("value", j2);
        intent.putExtra("type", i);
        context.startActivity(intent);
    }

    public void setUsbPath(String str) {
        this.mScanUsbPathOnLaunch = true;
        if (!this.mDbuStartStatus) {
            Log.i(TAG, "setUsbPath: wait dbu init finish -> " + str);
            return;
        }
        Log.i(TAG, "startDbu: " + str + "," + this.ehpDbuServer);
        if (this.ehpDbuServer != null) {
            boolean checked = getChecked();
            Log.i(TAG, "setUsbPath: checked" + checked);
            this.ehpDbuServer.SetUSBMountedPath(str, checked);
        }
        this.mNewVersion = null;
    }

    public void startUSBUpdate() {
        EhpDbuServer ehpDbuServer;
        Log.i(TAG, "startUSBUpdat ");
        Log.i(TAG, "startUSBUpdat " + this.mNewVersion);
        if (this.mNewVersion == null || (ehpDbuServer = this.ehpDbuServer) == null) {
            return;
        }
        ehpDbuServer.StartUSBUpdate();
    }

    public void onUnmount() {
        Log.i(TAG, "onUnmount: " + this.listener);
        IProgressListener iProgressListener = this.listener;
        if (iProgressListener != null) {
            iProgressListener.action(3, 0, 0);
        }
    }

    private String getDeviceId(Context context) {
        boolean z = context.checkSelfPermission("android.permission.BYDAUTO_BODYWORK_COMMON") == 0;
        Log.i(TAG, "init: hasPermission:" + z);
        if (!z) {
            return ExtensionRequestData.EMPTY_VALUE;
        }
        this.bydAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String autoVIN = this.bydAutoBodyworkDevice.getAutoVIN();
        Log.i(TAG, "BYDAutoBodyworkDevice getAutoVIN == " + autoVIN);
        if (autoVIN != null) {
            return autoVIN;
        }
        Log.i(TAG, "init: autoVIN is null");
        return ExtensionRequestData.EMPTY_VALUE;
    }

    private String getCountryCode(Context context) {
        return parseJson(context);
    }

    private String parseJson(Context context) {
        String str;
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = context.getResources().getAssets().open("countrycode-20230801095817.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            str = new String(bArr);
        } catch (IOException e) {
            e = e;
            str = null;
        }
        try {
            inputStreamOpen.close();
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
        }
        if (str == null) {
            Log.d(TAG, "content is null ");
            return null;
        }
        String systemProperties = getSystemProperties(context, "persist.vendor.byd.cfg.huc.config.area");
        Log.i(TAG, "parseJson codeNum =" + systemProperties);
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("countrycode");
                String string2 = jSONObject.getString("number");
                if (!string2.isEmpty() && string2.equals(systemProperties)) {
                    Log.i(TAG, "parseJson number =" + string2 + ",CountryCode =" + string);
                    return string;
                }
            }
            return ExtensionRequestData.EMPTY_VALUE;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return ExtensionRequestData.EMPTY_VALUE;
        }
    }

    private String getSystemProperties(Context context, String str) {
        String str2;
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            str2 = (String) clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, str);
        } catch (Exception e) {
            Log.e(TAG, "get: " + e.getMessage());
            str2 = ExtensionRequestData.EMPTY_VALUE;
        }
        Log.i(TAG, "[getSystemProperties] --> key == " + str + ", value == " + str2);
        return str2;
    }

    public void setListener(IProgressListener iProgressListener) {
        this.listener = iProgressListener;
    }

    private class TestBroadcast extends BroadcastReceiver {
        private TestBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(NativeManager.TAG, "onReceive: " + intent.getAction());
            if ("com.neusoft.na.ehp.action.test.value".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("type", 1);
                int intExtra2 = intent.getIntExtra("value", 1);
                if (NativeManager.this.listener != null) {
                    NativeManager.this.listener.action(intExtra, intExtra2, -1);
                    return;
                }
                return;
            }
            if ("com.neusoft.na.ehp.action.test.start".equals(intent.getAction())) {
                Intent intent2 = new Intent(context, (Class<?>) DialogActivity.class);
                intent2.setClassName(BuildConfig.APPLICATION_ID, "com.neusoft.na.ehp.dbu.DialogActivity");
                intent2.addFlags(268435456);
                context.startActivity(intent2);
                return;
            }
            if ("com.neusoft.na.ehp.action.test".equals(intent.getAction())) {
                NativeManager.this.startDialog(context, intent.getIntExtra("type", 1), intent.getIntExtra("value", 1), -1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activation(Context context, boolean z, int i, long j, long j2) {
        ActivateInfo.ActiveState activeState;
        ActivateInfo.NationalRegion nationalRegion;
        ActivateInfo.Region region;
        Log.i(TAG, "activation: " + z);
        String deviceId = getDeviceId(context);
        Log.i(TAG, "activation: " + deviceId);
        String countryCode = getCountryCode(context);
        if (z && !TextUtils.isEmpty(countryCode)) {
            activeState = ActivateInfo.ActiveState.ATCIVE_SUCCESS;
        } else {
            activeState = ActivateInfo.ActiveState.ATCIVE_FAILED;
        }
        ActivateInfo.ActiveState activeState2 = activeState;
        byte b = (byte) (TextUtils.isEmpty(deviceId) ? 2 : 1);
        if (TextUtils.isEmpty(countryCode)) {
            countryCode = "47";
        }
        countryCode.hashCode();
        switch (countryCode) {
            case "1":
                nationalRegion = ActivateInfo.NationalRegion.US;
                break;
            case "7":
                nationalRegion = ActivateInfo.NationalRegion.RU;
                break;
            case "20":
                nationalRegion = ActivateInfo.NationalRegion.EG;
                break;
            case "30":
                nationalRegion = ActivateInfo.NationalRegion.GR;
                break;
            case "31":
                nationalRegion = ActivateInfo.NationalRegion.NL;
                break;
            case "32":
                nationalRegion = ActivateInfo.NationalRegion.BE;
                break;
            case "33":
                nationalRegion = ActivateInfo.NationalRegion.FR;
                break;
            case "34":
                nationalRegion = ActivateInfo.NationalRegion.ES;
                break;
            case "36":
                nationalRegion = ActivateInfo.NationalRegion.HU;
                break;
            case "39":
                nationalRegion = ActivateInfo.NationalRegion.IT;
                break;
            case "40":
                nationalRegion = ActivateInfo.NationalRegion.RO;
                break;
            case "41":
                nationalRegion = ActivateInfo.NationalRegion.CH;
                break;
            case "43":
                nationalRegion = ActivateInfo.NationalRegion.AT;
                break;
            case "44":
                nationalRegion = ActivateInfo.NationalRegion.GB;
                break;
            case "45":
                nationalRegion = ActivateInfo.NationalRegion.DK;
                break;
            case "46":
                nationalRegion = ActivateInfo.NationalRegion.SE;
                break;
            case "47":
                nationalRegion = ActivateInfo.NationalRegion.NO;
                break;
            case "48":
                nationalRegion = ActivateInfo.NationalRegion.PL;
                break;
            case "49":
                nationalRegion = ActivateInfo.NationalRegion.DE;
                break;
            case "51":
                nationalRegion = ActivateInfo.NationalRegion.PE;
                break;
            case "52":
                nationalRegion = ActivateInfo.NationalRegion.MX;
                break;
            case "55":
                nationalRegion = ActivateInfo.NationalRegion.BR;
                break;
            case "56":
                nationalRegion = ActivateInfo.NationalRegion.CL;
                break;
            case "57":
                nationalRegion = ActivateInfo.NationalRegion.CO;
                break;
            case "60":
                nationalRegion = ActivateInfo.NationalRegion.MY;
                break;
            case "61":
                nationalRegion = ActivateInfo.NationalRegion.AU;
                break;
            case "62":
                nationalRegion = ActivateInfo.NationalRegion.ID;
                break;
            case "63":
                nationalRegion = ActivateInfo.NationalRegion.PH;
                break;
            case "64":
                nationalRegion = ActivateInfo.NationalRegion.NZ;
                break;
            case "65":
                nationalRegion = ActivateInfo.NationalRegion.SG;
                break;
            case "66":
                nationalRegion = ActivateInfo.NationalRegion.TH;
                break;
            case "84":
                nationalRegion = ActivateInfo.NationalRegion.VNM;
                break;
            case "90":
                nationalRegion = ActivateInfo.NationalRegion.TR;
                break;
            case "92":
                nationalRegion = ActivateInfo.NationalRegion.PK;
                break;
            case "212":
                nationalRegion = ActivateInfo.NationalRegion.MA;
                break;
            case "351":
                nationalRegion = ActivateInfo.NationalRegion.PT;
                break;
            case "352":
                nationalRegion = ActivateInfo.NationalRegion.LU;
                break;
            case "353":
                nationalRegion = ActivateInfo.NationalRegion.IE;
                break;
            case "354":
                nationalRegion = ActivateInfo.NationalRegion.IS;
                break;
            case "355":
                nationalRegion = ActivateInfo.NationalRegion.AL;
                break;
            case "356":
                nationalRegion = ActivateInfo.NationalRegion.MT;
                break;
            case "357":
                nationalRegion = ActivateInfo.NationalRegion.CY;
                break;
            case "358":
                nationalRegion = ActivateInfo.NationalRegion.FI;
                break;
            case "359":
                nationalRegion = ActivateInfo.NationalRegion.BG;
                break;
            case "370":
                nationalRegion = ActivateInfo.NationalRegion.LT;
                break;
            case "371":
                nationalRegion = ActivateInfo.NationalRegion.LV;
                break;
            case "372":
                nationalRegion = ActivateInfo.NationalRegion.EE;
                break;
            case "375":
                nationalRegion = ActivateInfo.NationalRegion.BY;
                break;
            case "377":
                nationalRegion = ActivateInfo.NationalRegion.MC;
                break;
            case "379":
                nationalRegion = ActivateInfo.NationalRegion.VA;
                break;
            case "381":
                nationalRegion = ActivateInfo.NationalRegion.RS;
                break;
            case "382":
                nationalRegion = ActivateInfo.NationalRegion.ME;
                break;
            case "385":
                nationalRegion = ActivateInfo.NationalRegion.HR;
                break;
            case "386":
                nationalRegion = ActivateInfo.NationalRegion.SI;
                break;
            case "420":
                nationalRegion = ActivateInfo.NationalRegion.CZ;
                break;
            case "421":
                nationalRegion = ActivateInfo.NationalRegion.SK;
                break;
            case "423":
                nationalRegion = ActivateInfo.NationalRegion.LI;
                break;
            case "502":
                nationalRegion = ActivateInfo.NationalRegion.GT;
                break;
            case "506":
                nationalRegion = ActivateInfo.NationalRegion.CR;
                break;
            case "507":
                nationalRegion = ActivateInfo.NationalRegion.PA;
                break;
            case "591":
                nationalRegion = ActivateInfo.NationalRegion.BOL;
                break;
            case "593":
                nationalRegion = ActivateInfo.NationalRegion.EC;
                break;
            case "595":
                nationalRegion = ActivateInfo.NationalRegion.PY;
                break;
            case "598":
                nationalRegion = ActivateInfo.NationalRegion.UY;
                break;
            case "965":
                nationalRegion = ActivateInfo.NationalRegion.KW;
                break;
            case "966":
                nationalRegion = ActivateInfo.NationalRegion.SA;
                break;
            case "968":
                nationalRegion = ActivateInfo.NationalRegion.OM;
                break;
            case "971":
                nationalRegion = ActivateInfo.NationalRegion.AE;
                break;
            case "973":
                nationalRegion = ActivateInfo.NationalRegion.BH;
                break;
            case "974":
                nationalRegion = ActivateInfo.NationalRegion.QA;
                break;
            case "977":
                nationalRegion = ActivateInfo.NationalRegion.NP;
                break;
            case "997":
                nationalRegion = ActivateInfo.NationalRegion.KZ;
                break;
            case "1242":
                nationalRegion = ActivateInfo.NationalRegion.BS;
                break;
            case "1876":
                nationalRegion = ActivateInfo.NationalRegion.JM;
                break;
            default:
                nationalRegion = ActivateInfo.NationalRegion.INVALID;
                break;
        }
        if (i == 1) {
            region = ActivateInfo.Region.Europe;
        } else if (i == 2) {
            region = ActivateInfo.Region.America;
        } else if (i == 3) {
            region = ActivateInfo.Region.AsiaPacific;
        } else if (i == 4) {
            region = ActivateInfo.Region.MiddleEastAfrica;
        } else {
            region = ActivateInfo.Region.INVALID_REGION;
        }
        ActivateInfo activateInfo = new ActivateInfo(ActivateInfo.ProviderType.MAP, b, ActivateInfo.ProviderName.NeuSoft, activeState2, j, j2, nationalRegion, region, context.getPackageName());
        boolean z2 = context.checkSelfPermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON) == 0;
        if (z2) {
            this.bydAutoYunDevice.registerListener(new AbsBYDAutoYunListener() { // from class: com.neusoft.na.ehp.manager.NativeManager.4
                @Override // android.hardware.bydauto.yun.AbsBYDAutoYunListener
                public void onAppActivateResult(int i2, int i3) {
                    super.onAppActivateResult(i2, i3);
                    NativeManager.this.mScheduledHandler.removeMessages(NativeManager.WHAT);
                    Log.i("AbsBYDAutoYunListener", "activation: result " + i2);
                    NativeManager.this.sendResult(i2 == 1);
                }
            });
            this.bydAutoYunDevice.setAppActivateInfo(activateInfo);
        }
        Log.i(TAG, "activation: done " + z2);
        this.mScheduledHandler.sendEmptyMessageDelayed(WHAT, 120000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendResult(boolean z) {
        Log.i(TAG, "sendResult: " + z);
        EhpDbuServer ehpDbuServer = this.ehpDbuServer;
        if (ehpDbuServer != null) {
            ehpDbuServer.DownlinkBroadcast(z);
        }
    }

    public void startVp() {
        Log.i(TAG, "startVp: " + this.ehpManager);
        EhpManager ehpManager = this.ehpManager;
        if (ehpManager != null) {
            ehpManager.startVp();
        }
    }

    public void stopVP() {
        Log.i(TAG, "stopVP: " + this.ehpManager);
        EhpManager ehpManager = this.ehpManager;
        if (ehpManager != null) {
            ehpManager.stopVP();
        }
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
    }

    public boolean getChecked() {
        return this.mChecked;
    }

    public int getActiveStatus() {
        return this.mActiveStatus;
    }

    public void setActiveChangedListener(IActiveChangedListener iActiveChangedListener) {
        this.mActiveChangedListener = iActiveChangedListener;
    }
}
