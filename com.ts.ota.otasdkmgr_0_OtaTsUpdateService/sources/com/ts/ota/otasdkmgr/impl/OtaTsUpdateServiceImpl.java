package com.ts.ota.otasdkmgr.impl;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TetheringManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.util.ConcurrentUtils;
import com.byd.ota.master.listener.UpdateBaseListener;
import com.engine.tbox.TboxManager;
import com.engine.tbox.data.ActiveCode;
import com.engine.tbox.data.ActiveCodeV2;
import com.engine.tbox.data.Apn1Info;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.DigitalKeyAccountInfo;
import com.engine.tbox.data.NetworkQueryResult;
import com.ts.avm.sdk.AvmManager;
import com.ts.diag.manager.DiagManager;
import com.ts.diag.manager.DiagOtaCallBack;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.ota.CarVendorOtaMgr;
import com.ts.lib.power.manager.CarPowerManager;
import com.ts.ota.otasdkmgr.DiagnosticAidlInfo;
import com.ts.ota.otasdkmgr.EcuInfo;
import com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.IAbsMessageCallback;
import com.ts.ota.otasdkmgr.IOtaSdkManager;
import com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy;
import com.ts.ota.otasdkmgr.ITestCallback;
import com.ts.ota.otasdkmgr.IUpdateListener;
import com.ts.ota.otasdkmgr.Info;
import com.ts.ota.otasdkmgr.adapter.TSOtaMasterAdapter;
import com.ts.ota.otasdkmgr.callback.IUpdateListenerCallback;
import com.ts.ota.otasdkmgr.manager.BaseUpdateManager;
import com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger;
import com.ts.ota.otasdkmgr.manager.ResetManager;
import com.ts.ota.otasdkmgr.utils.Constant;
import com.ts.qnxmanager.QnxManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import vendor.bosch.tee.V1_0.BoschResult;
import vendor.bosch.tee.V1_0.IBoschTeeService;

/* JADX INFO: loaded from: classes.dex */
public class OtaTsUpdateServiceImpl extends IOtaSdkManager.Stub {
    private static final String ACTION_ENTER_OTA_MODEL = "com.byd.ota.broadcast.ENTER_OTA_MODEL";
    private static final int APPOINTMENTTIME_RESULT = 108;
    private static final int CLR_ECU_DTC_INFO = 19;
    private static final int COMMON_NET_CONTROL_CMD = 23;
    private static final int ECU_DID_INFO = 107;
    private static final int ECU_DTC_CLEAN = 112;
    private static final int ECU_DTC_INFO = 111;
    private static final int ECU_UDS_COMM_DIAGNOSTIC_CMD = 21;
    private static final int FAILED = 1;
    private static final int FIVE_SECOND = 5000;
    private static final int GET_AQ_SLOT = 11;
    private static final int GET_ECU_DID_INFO = 10;
    private static final int GET_ECU_DTC_INFO = 18;
    private static final int GET_OTA_STATE_MSG = 1;
    private static final int HEART_BEAT_OTA_STATE = 16;
    private static final int HSM_UPDATE_FAILED = -200;
    private static final String KEY_DIAG_REQ = "diagReq";
    private static final String KEY_ECU_DIAL_ID = "ecuDialId";
    private static final String KEY_ECU_ID = "ecuId";
    private static final int NETWORK_QUERY_RESULT_RESERVE = 2;
    private static final int ONE = 1;
    private static final int ONE_SECOND = 1000;
    private static final int RESULT_FAIL = -1;
    private static final String RESULT_NO = "resultNumber";
    private static final int RESULT_OK = 0;
    private static final int SET_QNX_OTA_STATE = 15;
    private static final int SET_QNX_OTA_STATE_RESULT = 15;
    private static final int SET_UPDATE_FLAG = 14;
    private static final int SET_UPDATE_FLAG_RESULT = 14;
    private static final String SUB_TYPE = "subtype";
    private static final int SUCCESS = 0;
    private static final int SWITCH_AQ_SLOT = 12;
    private static final String TAG = "dota.OtaTsUpdateServiceImpl";
    private static final int TBOX_COMMAND_FAILED = 1;
    private static final int TBOX_COMMAND_SUCCESS = 0;
    private static final boolean TBOX_DEFAULT_RESULT = false;
    private static final String TBOX_DEFAULT_VALUE = "";
    private static final int TBOX_ERROR_CODE = -1;
    private static final int TWO = 2;
    private static final int TYPE_OTA = 8;
    private static final String VALUE_DIAG_REQ = "14FFFFFF";
    private static final String VALUE_ECU_DIAL_ID = "7DF/7E0";
    private static final String VALUE_ECU_ID = "CAR_ALL_ECU";
    private static final int ZERO = 0;
    private static QnxManager qnxMgr;
    private BaseUpdateManager baseUpdateManager;
    private DiagManager diagManager;
    private DiagOtaCallBack diagOtaCallBack;
    private ActivityManager mActivityMgr;
    private AppointTime mAppointTime;
    private AvmManager mAvmManager;
    private BluetoothAdapter mBtAdapter;
    private Handler mCallbackHandler;
    private CarPowerManager mCarPowerManager;
    private CarVendorOtaMgr mCarVendorOtaMgr;
    private Context mContext;
    private HandlerThread mHandlerThread;
    private TSOtaMasterAdapter mOtaMasterAdaptor;
    private PackageManager mPackageMgr;
    private ResetManager mResetManager;
    private TboxManager mTboxManager;
    private IBoschTeeService mTeeService;
    private TetheringManager mTetheringManager;
    private WifiManager mWifiManager;
    private IOtaTsUpdateServiceProxy otaServiceProxy;
    private OtaTboxUpdateMananger tboxUpdateMgr;
    private UpdateManager updateManager;
    private static int wifiState = -1;
    private static int btState = -1;
    private static int apState = -1;
    private IUpdateListenerCallback updateListener = IUpdateListenerCallback.getInstance();
    private ArrayList<String> appsBlackList = new ArrayList<>();
    Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                OtaTsUpdateServiceImpl.this.androidOtaStateCallback(msg.arg1);
            } else {
                Log.w(OtaTsUpdateServiceImpl.TAG, "handleMessage: has no such msg");
            }
        }
    };
    private RemoteCallbackList<IAbsBYDAutoOtaListener> mYxfCanAbsBydAutoOtaListener = new RemoteCallbackList<>();
    private RemoteCallbackList<IAbsMessageCallback> mYxfAbsMessageCallbackList = new RemoteCallbackList<>();

    public OtaTsUpdateServiceImpl(Context context, IOtaTsUpdateServiceProxy proxy) {
        this.mContext = context;
        this.otaServiceProxy = proxy;
        this.mAppointTime = AppointTime.getInstance(context);
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mCallbackHandler = new Handler(this.mHandlerThread.getLooper());
        this.baseUpdateManager = new BaseUpdateManager();
        UpdateManager updateManager = new UpdateManager();
        this.updateManager = updateManager;
        this.baseUpdateManager.registerCallback(updateManager);
        this.diagManager = DiagManager.getInstance(context);
        DiagCallback diagCallback = new DiagCallback();
        this.diagOtaCallBack = diagCallback;
        this.diagManager.registerOtaDiagDataCallBack(diagCallback);
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        AvmManager avmManager = AvmManager.getInstance();
        this.mAvmManager = avmManager;
        avmManager.init(this.mContext, null);
        OtaTboxUpdateMananger otaTboxUpdateMananger = OtaTboxUpdateMananger.getInstance(this.mContext, proxy);
        this.tboxUpdateMgr = otaTboxUpdateMananger;
        otaTboxUpdateMananger.registerExportLogListener(this.updateManager);
        qnxMgr = QnxManager.getInstance();
        this.mResetManager = ResetManager.getInstance();
        this.mPackageMgr = this.mContext.getPackageManager();
        this.mActivityMgr = (ActivityManager) this.mContext.getSystemService("activity");
        this.mOtaMasterAdaptor = this.otaServiceProxy.getTSOtaMasterAdapter();
        this.mTetheringManager = (TetheringManager) this.mContext.getSystemService(TetheringManager.class);
        try {
            this.mTeeService = IBoschTeeService.getService();
            Log.d(TAG, "mTeeService=" + this.mTeeService);
        } catch (Exception e) {
            Log.e(TAG, "init tee service failed");
        }
        initCarManager();
        initAppBlackList();
    }

    public void setTboxManager(TboxManager manager) {
        if (manager != null) {
            this.mTboxManager = manager;
        } else {
            this.mTboxManager = TboxManager.getInstance(this.mContext);
        }
    }

    public void initCarManager() {
        this.mCarVendorOtaMgr = this.otaServiceProxy.getCarVendorOtaMgr();
        this.mCarPowerManager = this.otaServiceProxy.getCarPowerManager();
        Log.e(TAG, "initCarManager: mCarVendorOtaMgr " + this.mCarVendorOtaMgr + " mCarPowerManager " + this.mCarPowerManager);
    }

    private class UpdateManager extends UpdateBaseListener {
        private UpdateManager() {
        }

        public void onMessageChanged(Bundle bundle) {
            int subType = bundle.getInt(OtaTsUpdateServiceImpl.SUB_TYPE);
            final int resultNo = bundle.getInt(OtaTsUpdateServiceImpl.RESULT_NO);
            Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: received!!! subtype:" + subType + ", result:" + resultNo);
            if (subType == 11) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: GET_AQ_SLOT in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    final int slotStatus = bundle.getInt("slotStatus");
                    OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                            for (int i = 0; i < size; i++) {
                                try {
                                    ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onGetAQSlot(slotStatus);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                        }
                    });
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: GET_AQ_SLOT is null");
                }
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: GET_AQ_SLOT out");
            } else if (subType == 12) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SWITCH_AQ_SLOT in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                            for (int i = 0; i < size; i++) {
                                try {
                                    ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onSetAQSlot(resultNo);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                        }
                    });
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SWITCH_AQ_SLOT is null");
                }
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SWITCH_AQ_SLOT out");
            } else if (subType == 14) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SET_UPDATE_FLAG_RESULT in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.5
                        @Override // java.lang.Runnable
                        public void run() {
                            int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                            for (int i = 0; i < size; i++) {
                                try {
                                    ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onUpdateFlag(resultNo);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                        }
                    });
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SET_UPDATE_FLAG_RESULT is null");
                }
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SET_UPDATE_FLAG_RESULT out");
            } else if (subType == 15) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SET_QNX_OTA_STATE_RESULT in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.6
                        @Override // java.lang.Runnable
                        public void run() {
                            int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                            for (int i = 0; i < size; i++) {
                                try {
                                    ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onQnxOtaState(resultNo);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                        }
                    });
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SET_QNX_OTA_STATE_RESULT is null");
                }
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: SET_QNX_OTA_STATE_RESULT out");
            } else if (subType == 23) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: COMMON_NET_CONTROL_CMD in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.9
                        @Override // java.lang.Runnable
                        public void run() {
                            int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                            for (int i = 0; i < size; i++) {
                                try {
                                    ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onSendCanNM(resultNo);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                        }
                    });
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: COMMON_NET_CONTROL_CMD is null");
                }
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: COMMON_NET_CONTROL_CMD out");
            } else if (subType == OtaTsUpdateServiceImpl.ECU_DID_INFO) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: ECU_DID_INFO in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    final List<EcuInfo> infoList = bundle.getParcelableArrayList("ecuListRsp");
                    if (infoList != null && infoList.size() != 0) {
                        OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.1
                            @Override // java.lang.Runnable
                            public void run() {
                                int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                                for (int i = 0; i < size; i++) {
                                    try {
                                        ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onEcuInfo(infoList);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                            }
                        });
                        Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: received infolist: " + infoList + ", size is " + infoList.size());
                    } else {
                        Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: received infolist is null");
                    }
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: ECU_DID_INFO is null");
                }
            } else if (subType == OtaTsUpdateServiceImpl.APPOINTMENTTIME_RESULT) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: APPOINTMENTTIME_RESULT in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.4
                        @Override // java.lang.Runnable
                        public void run() {
                            int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                            for (int i = 0; i < size; i++) {
                                try {
                                    ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onAppointmentTime(resultNo);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                        }
                    });
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: APPOINTMENTTIME_RESULT is null");
                }
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: APPOINTMENTTIME_RESULT out");
            } else if (subType == OtaTsUpdateServiceImpl.ECU_DTC_INFO) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: ECU_DTC_INFO in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    final List<DiagnosticAidlInfo> diagInfoList = bundle.getParcelableArrayList("ecuDtcListRsp");
                    if (diagInfoList != null && diagInfoList.size() != 0) {
                        OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.7
                            @Override // java.lang.Runnable
                            public void run() {
                                int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                                for (int i = 0; i < size; i++) {
                                    try {
                                        ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onGetDiagnosticTroubleCode(diagInfoList);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                            }
                        });
                        Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: received diagInfoList: " + diagInfoList + ", size is " + diagInfoList.size());
                    } else {
                        Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: received diagInfoList is null");
                    }
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: ECU_DTC_INFO is null");
                }
            } else if (subType == OtaTsUpdateServiceImpl.ECU_DTC_CLEAN) {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: ECU_DTC_CLEAN in");
                if (OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList != null) {
                    final List<DiagnosticAidlInfo> diagInfoList2 = bundle.getParcelableArrayList("clearEcuDtcListRsp");
                    if (diagInfoList2 != null && diagInfoList2.size() != 0) {
                        OtaTsUpdateServiceImpl.this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.UpdateManager.8
                            @Override // java.lang.Runnable
                            public void run() {
                                int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                                for (int i = 0; i < size; i++) {
                                    try {
                                        ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onClearDiagnosticTroubleCode(diagInfoList2);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                            }
                        });
                        Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: received clrDiagnosticlist: " + diagInfoList2 + ", size is " + diagInfoList2.size());
                    } else {
                        Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: received clrDiagnosticlist is null");
                    }
                } else {
                    Log.e(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: ECU_DTC_CLEAN is null");
                }
            } else {
                Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged: msg has no such SUB_TYPE");
            }
            Log.w(OtaTsUpdateServiceImpl.TAG, "onMessageChanged.out");
        }

        public void onTboxUpdateProgress(int process) {
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTboxUpdateProgress.in");
            OtaTsUpdateServiceImpl.this.updateListener.onTboxUpdateProgress(process);
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTboxUpdateProgress.out");
        }

        public void onTBOXUpgradeResult(int result) {
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTBOXUpgradeResult.in");
            OtaTsUpdateServiceImpl.this.updateListener.onTboxUpdateResult(result);
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTBOXUpgradeResult.out");
        }

        public void onTBOXConnectState(int state) {
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTBOXConnectState.in");
            OtaTsUpdateServiceImpl.this.updateListener.onTboxConnectState(state);
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTBOXConnectState.out");
        }

        public void onTboxExportLogProgress(int progress) {
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTboxExportLogProgress.in");
            OtaTsUpdateServiceImpl.this.updateListener.onTboxExportLogProgress(progress);
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTboxExportLogProgress.out");
        }

        public void onTboxExportLogResult(int result) {
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTboxExportLogResult.in");
            OtaTsUpdateServiceImpl.this.updateListener.onTboxExportLogResult(result);
            Log.w(OtaTsUpdateServiceImpl.TAG, "onTboxExportLogResult.out");
        }
    }

    private class DiagCallback extends DiagOtaCallBack {
        private DiagCallback() {
        }

        public void on7dfConnectStatusChange(byte state) {
            super.on7dfConnectStatusChange(state);
            Log.w(OtaTsUpdateServiceImpl.TAG, "on7dfConnectStatusChange: " + ((int) state));
            OtaTsUpdateServiceImpl.this.on7dfExistState(state == 1);
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getTestString() {
        Log.e(TAG, "getTestString=ftf 2022-04-28 11:24:26");
        return "ftf 2022-04-28 11:24:26";
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getDevicetype() {
        Log.w(TAG, " getDevicetype.in");
        Log.w(TAG, " getDevicetype.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getSetPermission() {
        Log.w(TAG, " getSetPermission.in");
        Log.w(TAG, " getSetPermission.out=not impl");
        return "not impl";
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getGetPermission() {
        Log.w(TAG, " getGetPermission.in");
        Log.w(TAG, " getGetPermission.out=not impl");
        return "not impl";
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public byte[] getOTAInfoACK() {
        Log.w(TAG, " getOTAInfoACK.in");
        Log.w(TAG, " getOTAInfoACK.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int notifyTargetID(byte[] id) {
        Log.w(TAG, " notifyTargetID.in");
        Log.w(TAG, " notifyTargetID.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int notifyTargetID2(byte[] channelAndId) {
        Log.w(TAG, " notifyTargetID2.in");
        Log.w(TAG, " notifyTargetID2.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendOTAData(byte[] data) {
        Log.w(TAG, " sendOTAData.in");
        Log.w(TAG, " sendOTAData.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendOTAData2(byte[] channelAndData) {
        Log.w(TAG, " sendOTAData2.in");
        Log.w(TAG, " sendOTAData2.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int StartOTA() {
        Log.w(TAG, " StartOTA.in");
        Log.w(TAG, " StartOTA.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int FinishOTA() {
        Log.w(TAG, " FinishOTA.in");
        Log.w(TAG, " FinishOTA.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendOTAServiceData(byte[] data) {
        Log.w(TAG, " sendOTAServiceData.in");
        Log.w(TAG, " sendOTAServiceData.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendECUIds(int purpose, byte[] ids) {
        Log.w(TAG, " sendECUIds.in");
        Log.w(TAG, " sendECUIds.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendOTACtlCMD(byte[] controlCmd) {
        Log.w(TAG, " sendOTACtlCMD.in");
        Log.w(TAG, " sendOTACtlCMD.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public byte[] getECUVersion() {
        Log.w(TAG, " getECUVersion.in");
        Log.w(TAG, " getECUVersion.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public byte[] getECUSoftcode() {
        Log.w(TAG, " getECUSoftcode.in");
        Log.w(TAG, " getECUSoftcode.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public byte[] getECUVersionAndSoftcode() {
        Log.w(TAG, " getECUVersionAndSoftcode.in");
        Log.w(TAG, " getECUVersionAndSoftcode.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int queryECUVersionAndSoftcode(byte[] ids) {
        Log.w(TAG, " queryECUVersionAndSoftcode.in");
        Log.w(TAG, " queryECUVersionAndSoftcode.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public byte[] getMCUVersionAndSoftcode() {
        Log.w(TAG, " getMCUVersionAndSoftcode.in");
        Log.w(TAG, " getMCUVersionAndSoftcode.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int queryMCUVersionAndSoftcode(byte[] ids) {
        Log.w(TAG, " queryMCUVersionAndSoftcode.in");
        Log.w(TAG, " queryMCUVersionAndSoftcode.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getPoweroffInform() {
        Log.w(TAG, " getPoweroffInform.in");
        Log.w(TAG, " getPoweroffInform.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getOTAState() {
        Log.w(TAG, " getOTAState.in");
        Log.w(TAG, " getOTAState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getOTATimecountState() {
        Log.w(TAG, " getOTATimecountState.in");
        Log.w(TAG, " getOTATimecountState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendOTATimecountCtlCMD(int install, int user, int hour, int minute) {
        Log.w(TAG, " sendOTATimecountCtlCMD.in");
        Log.w(TAG, " sendOTATimecountCtlCMD.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setScreenState(int state) {
        Log.w(TAG, " setScreenState.in");
        Log.w(TAG, " setScreenState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getDischargeMainContactorState() {
        int rstate = -1;
        Log.w(TAG, " getDischargeMainContactorState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, "CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getDischargeMainContactorState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getDischargeMainContactorState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendECUIds2(int purpose, byte[] ids) {
        Log.w(TAG, " sendECUIds2.in");
        Log.w(TAG, " sendECUIds2.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getPowerOnForbidState() {
        Log.w(TAG, " getPowerOnForbidState.in");
        Log.w(TAG, " getPowerOnForbidState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getLFDoorLockState() {
        Log.w(TAG, " getLFDoorLockState.in");
        Log.w(TAG, " getLFDoorLockState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setPowerOnForbidState(int state) {
        Log.w(TAG, " setPowerOnForbidState.in");
        Log.w(TAG, " setPowerOnForbidState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public byte[] getCanInfo() {
        Log.w(TAG, " getCanInfo.in");
        Log.w(TAG, " getCanInfo.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setCanInfoSwitchState(int state) {
        Log.w(TAG, " setCanInfoSwitchState.in");
        Log.w(TAG, " setCanInfoSwitchState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public double getBatteryPowerVoltage() {
        Log.w(TAG, " getBatteryPowerVoltage.in");
        Log.w(TAG, " getBatteryPowerVoltage.out=-1.0");
        return -1.0d;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public byte[] getFaultCodeInfo() {
        Log.w(TAG, " getFaultCodeInfo.in");
        Log.w(TAG, " getFaultCodeInfo.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getLocalDiagStatus() {
        Log.w(TAG, " getLocalDiagStatus.in");
        Log.w(TAG, " getLocalDiagStatus.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setMcuMessage(int message) {
        Log.w(TAG, " setMcuMessage.in");
        Log.w(TAG, " setMcuMessage.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int syncMcuState() {
        Log.w(TAG, " syncMcuState.in");
        Log.w(TAG, " syncMcuState.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int sendMonitorTable(byte[] id) {
        Log.w(TAG, " sendMonitorTable.in");
        Log.w(TAG, " sendMonitorTable.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setAllStatus() {
        Log.w(TAG, " setAllStatus.in");
        Log.w(TAG, " setAllStatus.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getType() {
        Log.w(TAG, " getType.in");
        Log.w(TAG, " getType.out=-1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int[] getFeatureList() {
        Log.w(TAG, " getFeatureList.in");
        Log.w(TAG, " getFeatureList.out=" + ((Object) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getVehicleVersion() {
        Log.w(TAG, " getVehicleVersion.in");
        Log.w(TAG, " getVehicleVersion.out=not impl");
        return "not impl";
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean getEcuInfo(List<Info> infos, IAbsMessageCallback callback) {
        Log.w(TAG, " getEcuInfo.in list:" + infos);
        if (this.baseUpdateManager == null) {
            Log.e(TAG, " getEcuInfo is null");
            return false;
        }
        ArrayList<Info> cachedEcuinfo = new ArrayList<>(infos);
        Log.w(TAG, " getEcuInfo: ecu number = " + cachedEcuinfo.size());
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ecuListReq", cachedEcuinfo);
        this.baseUpdateManager.sendMessage(10, bundle);
        Log.w(TAG, " getEcuInfo.out");
        return true;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean getAQSlot(IAbsMessageCallback callback) {
        Log.w(TAG, " getAQSlot.in");
        if (this.baseUpdateManager == null) {
            Log.e(TAG, " getAQSlot is null");
            return false;
        }
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        this.baseUpdateManager.sendMessage(11, null);
        Log.w(TAG, " getAQSlot.out");
        return true;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setAQSlot(int slot, IAbsMessageCallback callback) {
        Log.w(TAG, " setAQSlot.in slot->" + slot);
        if (this.baseUpdateManager == null) {
            Log.e(TAG, " setAQSlot is null");
            return false;
        }
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("switchSlot", slot);
        this.baseUpdateManager.sendMessage(12, bundle);
        Log.w(TAG, " setAQSlot.out");
        return true;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void QNXreboot() {
        Log.w(TAG, " QNXreboot.in");
        Log.w(TAG, " QNXreboot.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    @Deprecated
    public int getVehicleType() {
        int rstate = -1;
        Log.w(TAG, " getVehicleType.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " mCarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getVehicleType();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getVehicleType.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getTurnOffState() {
        int rstate = -1;
        Log.w(TAG, " getTurnOffState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getIccidCode CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getTurnOffState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getTurnOffState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getIgnitionState() {
        int rstate = -1;
        Log.w(TAG, " getIgnitionState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getIgnitionState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getIgnitionState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getIgnitionState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getBurglarState() {
        int rstate = -1;
        Log.w(TAG, " getBurglarState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getBurglarState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getBurglarState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getBurglarState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getBatteryTemp(int batteryType) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getBatteryTemp.in batteryType->" + batteryType);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getBatteryTemp CarVendorOtaMgr is null");
            return -1;
        }
        try {
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (batteryType == 0) {
            rstate = carVendorOtaMgr.getEnvironmentTempSecond();
        } else {
            if (batteryType == 1) {
                rstate = carVendorOtaMgr.getEnvironmentTemp();
            }
            Log.w(TAG, " getBatteryTemp.out=" + rstate);
            return rstate;
        }
        Log.w(TAG, " getBatteryTemp.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getEpbState() {
        int rstate = -1;
        Log.w(TAG, " getEpbState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getEpbState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getEpbState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getEpbState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean getSmartPowerUsable() {
        Log.w(TAG, " getSmartPowerUsable.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getSmartPowerUsable CarVendorOtaMgr is null");
            return false;
        }
        try {
            int rstate = carVendorOtaMgr.getSmartPowerUsable();
            return rstate == 1;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
            Log.w(TAG, " getSmartPowerUsable.out=0");
            return false;
        }
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getSmartPowerState() {
        int rstate = -1;
        Log.w(TAG, " getSmartPowerState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getSmartPowerState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getSmartPowerState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getSmartPowerState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getSmartPowerException() {
        int rstate = -1;
        Log.w(TAG, " getSmartPowerException.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getSmartPowerException CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getSmartPowerException();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getSmartPowerException.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getDcState() {
        int rstate = -1;
        Log.w(TAG, " getDcState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getDcState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getDcState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getDcState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getBatteryVoltage(int batteryType) {
        int rstate = -1;
        Log.w(TAG, " getBatteryVoltage.in batteryType->" + batteryType);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getBatteryVoltage CarVendorOtaMgr is null");
            return -1;
        }
        try {
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (batteryType == 0) {
            rstate = carVendorOtaMgr.getBatteryVoltageSecond();
        } else {
            if (batteryType == 1) {
                rstate = carVendorOtaMgr.getBatteryVoltage();
            }
            Log.w(TAG, " getBatteryVoltage.out=" + rstate);
            return rstate;
        }
        Log.w(TAG, " getBatteryVoltage.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getChargeState() {
        int rstate = -1;
        Log.w(TAG, " getChargeState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getChargeState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getChargeState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getChargeState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getBatteryManagementDeviceState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getBatteryManagementDeviceState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getChargeState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getDischargeMainContactorState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getBatteryManagementDeviceState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getDischargeState(int type) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getDischargeState.in type->" + type);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getDischargeState CarVendorOtaMgr is null");
            return -1;
        }
        try {
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (type == 2) {
            rstate = carVendorOtaMgr.getDischargeState();
        } else {
            if (type == 1) {
                rstate = carVendorOtaMgr.getDischargeStateVtol();
            }
            Log.w(TAG, " getDischargeState.out=" + rstate);
            return rstate;
        }
        Log.w(TAG, " getDischargeState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getChargeAndDischargeSystemState() throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getChargeAndDischargeSystemState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getChargeState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getChargeAndDischargeSystemState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getChargeAndDischargeSystemState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getSmartChargeState(int batteryType) throws RemoteException {
        int rstate = -1;
        Log.w(TAG, " getSmartChargeState.in batteryType->" + batteryType);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getSmartChargeState CarVendorOtaMgr is null");
            return -1;
        }
        try {
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (batteryType == 0) {
            rstate = carVendorOtaMgr.getSmartChargeState();
        } else {
            if (batteryType == 1) {
                rstate = carVendorOtaMgr.getSmartChargeStateSecond();
            }
            Log.w(TAG, " getSmartChargeState.out=" + rstate);
            return rstate;
        }
        Log.w(TAG, " getSmartChargeState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getIg3RelayState() {
        int rstate = -1;
        Log.w(TAG, " getIg3RelayState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getIg3RelayState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getIg3RelayState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getIg3RelayState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getBcmOtaState() {
        int rstate = -1;
        Log.w(TAG, " getBcmOtaState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getBcmOtaState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getBcmOtaState();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getBcmOtaState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getLFDoorLockStatus() {
        int rstate = 0;
        Log.w(TAG, " getLFDoorLockStatus.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getLFDoorLockStatus mCarVendorOtaMgr is null");
            return 0;
        }
        try {
            rstate = carVendorOtaMgr.getDoorLockStatusAreaLeftFront();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getLFDoorLockStatus.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean get7dfExistState() {
        Log.w(TAG, " get7dfExistState.in");
        DiagManager diagManager = this.diagManager;
        if (diagManager == null) {
            Log.e(TAG, " get7dfExistState CarVendorOtaMgr is null");
            return false;
        }
        diagManager.requestOta7dfState();
        Log.w(TAG, " get7dfExistState.out");
        return true;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setSmartPower(int state) {
        boolean rstate = false;
        Log.w(TAG, " setSmartPower.in state->" + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setSmartPower CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setSmartPower(state);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setSmartPower.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setEclPower(int state) {
        boolean rstate = false;
        Log.w(TAG, " setEclPower.in state->" + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setEclPower CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setEclPower(state);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setEclPower.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setQuitSmartCharge(int state) {
        boolean rstate = false;
        Log.w(TAG, " setQuitSmartCharge.in state->" + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setQuitSmartCharge CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setQuitSmartCharge(state);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setQuitSmartCharge.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setQuitChargeAndDischarge(int state) {
        boolean rstate = false;
        Log.w(TAG, " setQuitChargeAndDischarge.in state->" + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setQuitChargeAndDischarge CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setQuitChargeAndDischarge(state);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setQuitChargeAndDischarge.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setOtaModel(int state) {
        if (this.baseUpdateManager == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("heartOtaState", state);
        this.baseUpdateManager.sendMessage(16, bundle);
        return true;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setOtaPower(int state) {
        boolean rstate = false;
        Log.w(TAG, " setOtaPower.in state->" + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setOtaPower CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setOtaPower(state);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setOtaPower.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setHighVoltageCmd(int command) {
        boolean rstate = false;
        Log.w(TAG, " setHighVoltageCmd.in command->" + command);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setHighVoltageCmd CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setHighVoltageCmd(command);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setHighVoltageCmd.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setBackupAreaDownload(int state) throws RemoteException {
        boolean rstate = false;
        Log.w(TAG, " setBackupAreaDownload.in state->" + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setBackupAreaDownload CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setBackupAreaDownload(state);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setBackupAreaDownload.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setBackLightState(int state) throws RemoteException {
        Log.w(TAG, " setBackLightState.in state->" + state);
        CarPowerManager carPowerManager = this.mCarPowerManager;
        if (carPowerManager == null) {
            Log.e(TAG, " setBackLightState mCarPowerManager is null");
            return false;
        }
        boolean rstate = carPowerManager.setDisplayPowerState(1, state);
        Log.w(TAG, " setBackLightState.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void registerCallback(IUpdateListener listener) throws RemoteException {
        Log.w(TAG, " registerCallback.in");
        IUpdateListenerCallback.getInstance().registerListener(listener);
        Log.w(TAG, " registerCallback.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void unregisterCallback(IUpdateListener listener) throws RemoteException {
        Log.w(TAG, " unregisterCallback.in");
        IUpdateListenerCallback.getInstance().unregisterListener(listener);
        Log.w(TAG, " unregisterCallback.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void startUpdate(String path) throws RemoteException {
        Log.w(TAG, " startUpdate.in path->" + path);
        TSOtaMasterAdapter tSOtaMasterAdapter = this.mOtaMasterAdaptor;
        if (tSOtaMasterAdapter == null) {
            Log.e(TAG, " startUpdate TSOtaMasterAdapter() is null");
        } else {
            tSOtaMasterAdapter.startUpdate(path);
            Log.w(TAG, " startUpdate.out");
        }
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void startDownloadBackup(String path) throws RemoteException {
        Log.w(TAG, " startDownloadBackup.in path->" + path);
        TSOtaMasterAdapter tSOtaMasterAdapter = this.mOtaMasterAdaptor;
        if (tSOtaMasterAdapter == null) {
            Log.e(TAG, " startDownloadBackup TSOtaMasterAdapter() is null");
        } else {
            tSOtaMasterAdapter.startDownloadBackup(path);
            Log.w(TAG, " startDownloadBackup.out");
        }
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void startUpdateTbox(String path) throws RemoteException {
        Log.w(TAG, " startUpdateTbox.in path->" + path);
        TSOtaMasterAdapter tSOtaMasterAdapter = this.mOtaMasterAdaptor;
        if (tSOtaMasterAdapter == null) {
            Log.e(TAG, " startUpdateTbox TSOtaMasterAdapter() is null");
        } else {
            tSOtaMasterAdapter.startUpdateTbox(path);
            Log.w(TAG, " startUpdateTbox.out");
        }
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void registerListener(IAbsBYDAutoOtaListener listener) throws RemoteException {
        Log.w(TAG, " registerListener.in listener = " + listener);
        this.mYxfCanAbsBydAutoOtaListener.register(listener);
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void registerTboxListener(IAbsBYDTBoxListener listener) throws RemoteException {
        Log.w(TAG, " register IAbsBYDTBoxListener.in listener = " + listener);
        this.tboxUpdateMgr.registerTboxListener(listener);
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void registerAppActivateListener(IAbsBYDTBoxListener listener) throws RemoteException {
        Log.w(TAG, " register IAbsBYDTBoxListener.in listener = " + listener);
        this.tboxUpdateMgr.appActivateCallback(listener);
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void unregisterListener(IAbsBYDAutoOtaListener listener) throws RemoteException {
        Log.w(TAG, " unregisterListener.in listener = " + listener);
        this.mYxfCanAbsBydAutoOtaListener.unregister(listener);
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void unregisterTboxListener(IAbsBYDTBoxListener listener) throws RemoteException {
        this.tboxUpdateMgr.unregisterTboxListener(listener);
    }

    public void onTurnOffState(final int state) {
        Log.w(TAG, " onTurnOffState.in state = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.2
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onTurnOffState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onSmartPowerState(final int state) {
        Log.w(TAG, " onSmartPowerState.in state = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.3
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onSmartPowerState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onDcState(final int state) {
        Log.w(TAG, " onDcState.in state = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.4
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onDcState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onIg3RelayState(final int state) {
        Log.w(TAG, " onIg3RelayState.in state = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.5
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onIg3RelayState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onBackupAreaDownloadState(final int state) {
        Log.w(TAG, " onBackupAreaDownloadState.in state = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.6
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onBackupAreaDownloadState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void on7dfExistState(final boolean exist) {
        Log.w(TAG, " on7dfExistState.in exist = " + exist);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.7
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).on7dfExistState(exist);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onLFDoorLockStatus(final int state) {
        Log.w(TAG, " onLFDoorLockStatus.in exist = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.8
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onLFDoorLockStatus(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onSocValueChanged(final int value) {
        Log.w(TAG, " onSocValueChanged.in exist = " + value);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.9
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onSocValueChanged(value);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onProhibitPowerOnState(final int state) {
        Log.w(TAG, " onProhibitPowerOnState.in exist = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.10
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onProhibitPowerOnState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onStartKeyState(final int state) {
        Log.w(TAG, " onStartKeyState.in exist = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.11
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onStartKeyState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onNfcOperationCommand(final int cmd) {
        Log.w(TAG, " onNfcOperationCommand.in cmd = " + cmd);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.-$$Lambda$OtaTsUpdateServiceImpl$79efpnzQsFhEis7Gl2EtSW8OlQM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onNfcOperationCommand$0$OtaTsUpdateServiceImpl(cmd);
            }
        });
    }

    public /* synthetic */ void lambda$onNfcOperationCommand$0$OtaTsUpdateServiceImpl(int cmd) {
        int size = this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                ((IAbsBYDAutoOtaListener) this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onNfcOperationCommand(cmd);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getTBoxSerialNumber() {
        Log.w(TAG, " getTBoxSerialNumber.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getTBoxSerialNumber: mTboxManager is null");
            return " ";
        }
        if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getTBoxSerialNumber TboxManager.isTboxConnected failed");
            return " ";
        }
        if (this.mTboxManager.getTboxStaticInfo() == null) {
            Log.e(TAG, "getTboxStaticInfo is null");
            return " ";
        }
        String sn = this.mTboxManager.getTboxStaticInfo().getTboxSerial();
        return sn;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getTBoxSoftVersion() {
        Log.w(TAG, " getTBoxSoftVersion.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getTBoxSoftVersion: mTboxManager is null");
            return " ";
        }
        if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getTBoxSoftVersion TboxManager.isTboxConnected failed");
            return " ";
        }
        if (this.mTboxManager.getTboxStaticInfo() == null) {
            Log.e(TAG, "getTboxStaticInfo is null");
            return " ";
        }
        String tboxVer = this.mTboxManager.getTboxStaticInfo().getTboxSoftVersion();
        return tboxVer;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getTBoxHardwareVersion() {
        Log.w(TAG, " getTBoxHardwareVersion.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getTBoxHardwareVersion: mTboxManager is null");
            return " ";
        }
        if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getTBoxHardwareVersion TboxManager.isTboxConnected failed");
            return " ";
        }
        if (this.mTboxManager.getTboxStaticInfo() == null) {
            Log.e(TAG, "getTboxStaticInfo is null");
            return " ";
        }
        String tboxHwVer = this.mTboxManager.getTboxStaticInfo().getTboxHardVersion();
        return tboxHwVer;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean getTBoxUpgradeLog(String path) {
        Log.w(TAG, " getTBoxUpgradeLog.in path->" + path);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getTBoxUpgradeLog: mTboxManager is null");
            return false;
        }
        if (tboxManager.isTboxConnected()) {
            return this.mTboxManager.exportLog(path);
        }
        Log.e(TAG, "getTBoxUpgradeLog TboxManager.isTboxConnected failed");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean decodeAndCheckSign(Bundle bundle) {
        Log.w(TAG, " decodeAndCheckSign.in bundle->" + bundle);
        TSOtaMasterAdapter tSOtaMasterAdapter = this.mOtaMasterAdaptor;
        if (tSOtaMasterAdapter == null) {
            Log.e(TAG, " decodeAndCheckSign TSOtaMasterAdapter() is null");
            return false;
        }
        boolean result = tSOtaMasterAdapter.decodeAndCheckSign(bundle);
        Log.w(TAG, " decodeAndCheckSign.out");
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public float getVehicleSpeed() {
        float rstate = -1.0f;
        Log.w(TAG, " getVehicleSpeed.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getVehicleSpeed: CarVendorOtaMgr is null");
            return -1.0f;
        }
        try {
            rstate = carVendorOtaMgr.getVehicleSpeed();
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
        }
        Log.w(TAG, " getVehicleSpeed.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getSocValue() {
        int rstate = -1;
        Log.w(TAG, " getSocValue.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getSocValue: CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getSocValueR();
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
        }
        Log.w(TAG, " getSocValue.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setAppointmentTime(int day, int hours, int minutes, int type, int wakeup, IAbsMessageCallback callback) {
        Log.w(TAG, " setAppointmentTime.in-> day:" + day + " hours:" + hours + " minutes:" + minutes);
        if (type != 1 && type != 2) {
            Log.e(TAG, " setAppointmentTime: type is incorrect");
            return;
        }
        synchronized (this) {
            if (callback != null) {
                this.mYxfAbsMessageCallbackList.register(callback);
                this.mAppointTime.setAppointmentTime(day, hours, minutes, type, wakeup);
            } else {
                this.mAppointTime.setAppointmentTime(day, hours, minutes, type, wakeup);
            }
        }
        Log.w(TAG, " setAppointmentTime.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setUpdateFlag(int flag, IAbsMessageCallback callback) {
        Log.w(TAG, " setUpdateFlag.in-> flag:" + flag);
        CarPowerManager carPowerManager = this.mCarPowerManager;
        if (carPowerManager != null) {
            carPowerManager.setUpdateFlag(flag);
            Log.w(TAG, " mCarPowerManager.setUpdateFlag:" + flag);
        } else {
            Log.w(TAG, " setUpdateFlag: mCarPowerManager null");
        }
        if (this.baseUpdateManager == null) {
            Log.e(TAG, " setUpdateFlag is null");
            return;
        }
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("updateFlag", flag);
        this.baseUpdateManager.sendMessage(14, bundle);
        Log.w(TAG, " setUpdateFlag.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setQnxOtaState(int state, IAbsMessageCallback callback) {
        Log.w(TAG, " setQnxOtaState.in-> state:" + state);
        if (this.baseUpdateManager == null) {
            Log.e(TAG, " setQnxOtaState is null");
            return;
        }
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("otaState", state);
        this.baseUpdateManager.sendMessage(15, bundle);
        Log.w(TAG, " setQnxOtaState.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public synchronized void setAndroidOtaState(int state, IAbsMessageCallback callback) {
        Log.w(TAG, " setAndroidOtaState.in-> state:" + state);
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        if (state == 1) {
            Intent intent = new Intent();
            intent.setAction(ACTION_ENTER_OTA_MODEL);
            this.mContext.sendBroadcast(intent);
            Message msg = new Message();
            msg.what = 1;
            msg.arg1 = state;
            this.mHandler.sendMessageDelayed(msg, 5000L);
        } else {
            androidOtaStateCallback(state);
        }
        Log.w(TAG, " setAndroidOtaState.out");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void androidOtaStateCallback(final int state) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> result = executor.submit(new Callable() { // from class: com.ts.ota.otasdkmgr.impl.-$$Lambda$OtaTsUpdateServiceImpl$G2HUogbvWkYUqMc1pLDeJ5fBEik
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$androidOtaStateCallback$1$OtaTsUpdateServiceImpl(state);
            }
        });
        executor.shutdown();
        if (!executor.isTerminated()) {
            sleep(1000L);
        }
        try {
            if (result == null) {
                Log.e(TAG, "androidOtaStateCallback: result is null");
                onAndroidOtaState(-1);
            } else {
                Log.w(TAG, "androidOtaStateCallback: handleOtaState= " + result.get());
                onAndroidOtaState(result.get().intValue());
            }
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, "androidOtaStateCallback: Get result failed");
        }
    }

    public /* synthetic */ Integer lambda$androidOtaStateCallback$1$OtaTsUpdateServiceImpl(int state) throws Exception {
        return Integer.valueOf(handleOtaState(state));
    }

    private int handleOtaState(int state) {
        int resultCode;
        boolean result = false;
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager == null || this.mBtAdapter == null) {
            Log.e(TAG, "handleOtaState is fail: mWifiManager : " + this.mWifiManager + " mBtAdapter : " + this.mBtAdapter);
            return -1;
        }
        boolean z = false;
        if (state == 0) {
            result = this.mCarPowerManager.exitOta();
            if (wifiState == 3) {
                result = result && setWifiState(true);
            }
            if (btState == 12) {
                result = result && setBtState(true);
            }
            if (apState == 13) {
                if (result && setApState(true)) {
                    z = true;
                }
                result = z;
            }
        } else if (state == 1) {
            wifiState = wifiManager.getWifiState();
            btState = this.mBtAdapter.getState();
            apState = this.mWifiManager.getWifiApState();
            if (forceStopAppsInBlackList() && setWifiState(false) && setBtState(false) && setApState(false) && this.mCarPowerManager.enterOta() && killAvm()) {
                z = true;
            }
            result = z;
        } else {
            Log.w(TAG, "handleOtaState: handler nothing!");
        }
        if (result) {
            resultCode = 0;
        } else {
            resultCode = -1;
        }
        Log.w(TAG, "handleOtaState: result= " + resultCode);
        return resultCode;
    }

    public void onBcmOtaState(final int state) {
        Log.w(TAG, " onBcmOtaState.in exist = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.12
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onBcmOtaState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onAndroidOtaState(final int resultCode) {
        Log.w(TAG, " onAndroidOtaState.in exist = " + resultCode);
        if (this.mYxfAbsMessageCallbackList != null) {
            Log.w(TAG, " onAndroidOtaState.in callback");
            this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.13
                @Override // java.lang.Runnable
                public void run() {
                    int size = OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.beginBroadcast();
                    for (int i = 0; i < size; i++) {
                        try {
                            ((IAbsMessageCallback) OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.getBroadcastItem(i)).onAndroidOtaState(resultCode);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    OtaTsUpdateServiceImpl.this.mYxfAbsMessageCallbackList.finishBroadcast();
                }
            });
        } else {
            Log.e(TAG, " onAndroidOtaState.in callback is null");
        }
    }

    private boolean setWifiState(boolean state) {
        boolean isWifiEnable = this.mWifiManager.isWifiEnabled();
        boolean result = true;
        Log.w(TAG, "setWifiState: " + state);
        if (state) {
            if (!isWifiEnable) {
                result = this.mWifiManager.setWifiEnabled(true);
            }
        } else if (isWifiEnable) {
            result = this.mWifiManager.setWifiEnabled(false);
        }
        Log.w(TAG, "setWifiState: result= " + result);
        return result;
    }

    private boolean setBtState(boolean state) {
        boolean isBtEnable = this.mBtAdapter.isEnabled();
        boolean result = true;
        Log.w(TAG, "setBtState: " + state);
        if (state) {
            if (!isBtEnable) {
                result = this.mBtAdapter.enable();
            }
        } else if (isBtEnable) {
            result = this.mBtAdapter.disable();
        }
        Log.w(TAG, "setBtState: result= " + result);
        return result;
    }

    private boolean setApState(boolean state) {
        if (this.mTetheringManager == null) {
            Log.e(TAG, "setApState mTetheringManager is null");
            return false;
        }
        Log.w(TAG, "setApState: " + state + TBOX_DEFAULT_VALUE);
        if (state) {
            this.mTetheringManager.startTethering(0, ConcurrentUtils.DIRECT_EXECUTOR, new TetheringManager.StartTetheringCallback() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.14
                public void onTetheringFailed(int result) {
                }
            });
            return true;
        }
        if (apState == 13) {
            this.mTetheringManager.stopTethering(0);
            return true;
        }
        Log.w(TAG, "setApState: now is close");
        return true;
    }

    private boolean killAvm() {
        int result = this.mAvmManager.killAvm();
        Log.w(TAG, "killAvm: result= " + result);
        if (result == 0) {
            return true;
        }
        return false;
    }

    private void initAppBlackList() {
        try {
            InputStream fileStream = this.mContext.getAssets().open("AppBlackList.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fileStream);
            fileStream.close();
            Element element = document.getDocumentElement();
            parseBlackListElement(element);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Log.e(TAG, "getAppBlackList: Fail to load AppBlackList.xml! Reason: " + ex);
        }
    }

    private void parseBlackListElement(Element node) {
        NodeList childList = node.getElementsByTagName("package");
        if (childList == null) {
            Log.e(TAG, "parseBlackListElement: Element is empty");
            return;
        }
        for (int i = 0; i < childList.getLength(); i++) {
            Node childNode = childList.item(i);
            if (childNode.getNodeType() == 1) {
                String packageName = ((Element) childNode).getAttribute("name");
                Log.w(TAG, "parseBlackListElement: " + packageName);
                this.appsBlackList.add(packageName);
            }
        }
    }

    private boolean forceStopAppsInBlackList() {
        if (this.mPackageMgr != null && this.mActivityMgr != null) {
            for (String packageName : this.appsBlackList) {
                try {
                    Log.w(TAG, "AppsInBlackList: " + packageName);
                    PackageInfo pkgInfo = this.mPackageMgr.getPackageInfo(packageName, 1);
                    if ((2097152 & pkgInfo.applicationInfo.flags) == 0) {
                        try {
                            this.mActivityMgr.forceStopPackage(pkgInfo.packageName);
                            Log.w(TAG, "ForceStopPackage " + pkgInfo.packageName);
                        } catch (Exception exception) {
                            Log.e(TAG, "ForceStopPackage failed: " + pkgInfo.packageName + " cause: " + exception);
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.w(TAG, "forceStopAppsInBlackList not found package: " + packageName);
                }
            }
            return true;
        }
        Log.e(TAG, "forceStopAppsInBlackList: Force stop apps failed");
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setCloudServiceListener(IAbsMessageCallback callback) {
        Log.w(TAG, " setCloudServiceListener.in");
        this.tboxUpdateMgr.otaNotificationCallback(callback);
        Log.w(TAG, " setCloudServiceListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void stopCloudServiceListener(IAbsMessageCallback callback) {
        Log.w(TAG, " unregisterCloudServiceListener.in");
        this.tboxUpdateMgr.unregisterOtaNtyCallback(callback);
        Log.w(TAG, " unregisterCloudServiceListener.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void notifyTboxOtaState(int state) {
        Log.w(TAG, " notifyTboxOtaState.in state->" + state);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "notifyTboxOtaState: mTboxManager is null");
            return;
        }
        if (tboxManager.isTboxConnected()) {
            this.mTboxManager.setOtaState(state);
        } else {
            Log.e(TAG, "notifyTboxOtaState: TboxManager isTboxConnected failed");
        }
        Log.w(TAG, " notifyTboxOtaState.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getCloudServiceVersion() {
        Log.w(TAG, " getCloudServiceVersion.in");
        String cloudVer = TBOX_DEFAULT_VALUE;
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getCloudServiceVersion: mTboxManager is null");
            return TBOX_DEFAULT_VALUE;
        }
        if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getCloudServiceVersion: TboxManager isTboxConnected failed");
        } else {
            cloudVer = this.mTboxManager.getCloudServiceVersion();
        }
        Log.w(TAG, " getCloudServiceVersion.out cloudVer : " + cloudVer);
        return cloudVer;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean switchCloudServiceEnv(int env) {
        Log.w(TAG, " switchCloudServiceEnv.in env->" + env);
        boolean result = false;
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "switchCloudServiceEnv: mTboxManager is null");
            return false;
        }
        if (tboxManager.isTboxConnected()) {
            result = this.mTboxManager.switchCloudServiceEnv(env);
        } else {
            Log.e(TAG, "switchCloudServiceEnv: TboxManager isTboxConnected failed");
        }
        Log.w(TAG, " switchCloudServiceEnv.out result " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getCloudServiceConnectState() {
        Log.w(TAG, " getCloudServiceConnectState.in");
        int connState = -1;
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getCloudServiceConnectState: mTboxManager is null");
            return -1;
        }
        if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getCloudServiceConnectState: TboxManager isTboxConnected failed");
        } else {
            connState = this.mTboxManager.getCloudServiceConnectState();
        }
        Log.w(TAG, " getCloudServiceConnectState.out connState : " + connState);
        return connState;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getCloudServieEv() {
        Log.w(TAG, " getCloudServieEv.in");
        int cloudEnv = -1;
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getCloudServieEv: mTboxManager is null");
            return -1;
        }
        if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getCloudServieEv: TboxManager isTboxConnected failed");
        } else {
            cloudEnv = this.mTboxManager.getCloudServieEv();
        }
        Log.w(TAG, " getCloudServieEv.out cloudEnv : " + cloudEnv);
        return cloudEnv;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean executeCMD(Bundle cmd) {
        Log.w(TAG, " executeCMD.in cmd->" + cmd);
        TSOtaMasterAdapter tSOtaMasterAdapter = this.mOtaMasterAdaptor;
        if (tSOtaMasterAdapter == null) {
            Log.e(TAG, " executeCMD TSOtaMasterAdapter() is null");
            return false;
        }
        boolean result = tSOtaMasterAdapter.executeCMD(cmd);
        Log.w(TAG, " executeCMD.out");
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setMobileNetworkState(boolean state) {
        Log.w(TAG, " setMobileNetworkState.in state->" + state);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setMobileNetworkState: mTboxManager is null");
            return;
        }
        if (tboxManager.isTboxConnected()) {
            this.mTboxManager.enableApn2(state);
        } else {
            Log.e(TAG, "setMobileNetworkState TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " setMobileNetworkState.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setAppActivateInfo(byte supplierType, byte autoVinResult, byte supplierName, byte activateState, long activateTime, long licenseTime, byte activateCountry, byte activateRegion, String appPkgName) {
        Log.w(TAG, " setAppActivateInfo.in->supplierType:" + ((int) supplierType) + " autoVinResult:" + ((int) autoVinResult) + " supplierName:" + ((int) supplierName) + " activateState:" + ((int) activateState) + " licenseTime:" + licenseTime + " activateCountry:" + ((int) activateCountry) + " activateRegion:" + ((int) activateRegion) + " appPkgName:" + appPkgName);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setAppActivateInfo: mTboxManager is null");
            return;
        }
        if (tboxManager.isTboxConnected()) {
            ActiveCode activeCode = new ActiveCode();
            activeCode.setProviderType(ActiveCode.byteToProviderType(supplierType));
            activeCode.setGetVirtualVinResult(isAutoVinSuccess(autoVinResult));
            activeCode.setProviderName(ActiveCode.byteToProviderName(supplierName));
            activeCode.setActiveState(ActiveCode.byteToActiveState(activateState));
            activeCode.setActiveTime(activateTime);
            activeCode.setExpireTime(licenseTime);
            activeCode.setNational(ActiveCode.byteToNation(activateCountry));
            activeCode.setRegion(ActiveCode.byteToRegion(activateRegion));
            activeCode.setPackageName(appPkgName);
            this.mTboxManager.sendActiveCode(activeCode);
        } else {
            Log.e(TAG, "setAppActivateInfo TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " setAppActivateInfo.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setAppActivateInfoV2(int supplierType, int autoVinResult, int supplierName, int activateState, long activateTime, long licenseTime, int activateCountry, int activateRegion, String appPkgName) {
        Log.w(TAG, " setAppActivateInfoV2.in->supplierType:" + supplierType + " autoVinResult:" + autoVinResult + " supplierName:" + supplierName + " activateState:" + activateState + " activateTime" + activateTime + " licenseTime:" + licenseTime + " activateCountry:" + activateCountry + " activateRegion:" + activateRegion + " appPkgName:" + appPkgName);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setAppActivateInfo: mTboxManager is null");
            return;
        }
        if (tboxManager.isTboxConnected()) {
            ActiveCodeV2 activeCode = new ActiveCodeV2();
            activeCode.setProviderType(supplierType);
            activeCode.setVirtualVin(autoVinResult);
            activeCode.setProviderName(supplierName);
            activeCode.setActiveResult(activateState);
            activeCode.setActiveTime(activateTime);
            activeCode.setExpireTime(licenseTime);
            activeCode.setCountry(activateCountry);
            activeCode.setRegion(activateRegion);
            activeCode.setPackageName(appPkgName);
            this.mTboxManager.sendActiveCode(activeCode);
        } else {
            Log.e(TAG, "setAppActivateInfoV2 TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " setAppActivateInfoV2.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getProhibitPowerOnState() {
        int rstate = -1;
        Log.w(TAG, " getProhibitPowerOnState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getProhibitPowerOnState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getProhibitPowerOnStateR();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getProhibitPowerOnState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setMultimediaSelfUpdate(int state) {
        boolean rstate = false;
        Log.w(TAG, " setMultimediaSelfUpdate.in state->" + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " setMultimediaSelfUpdate CarVendorOtaMgr is null");
            return false;
        }
        try {
            carVendorOtaMgr.setMultimediaSelfUpdateS(state);
            rstate = true;
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " setMultimediaSelfUpdate.out = " + rstate);
        return rstate;
    }

    private boolean isAutoVinSuccess(byte autoVinResult) {
        if (autoVinResult == 1) {
            return true;
        }
        return false;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getNetworkOperatorList() {
        int rstate = Constant.CHARGE_COMMAND_FAILED;
        Log.w(TAG, " getNetworkOperatorList.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getNetworkOperatorList: mTboxManager is null");
            return Constant.CHARGE_COMMAND_FAILED;
        }
        if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getNetworkOperatorList: TboxManager isTboxConnected failed");
        } else {
            int state = this.mTboxManager.getNetworkOperatorList();
            rstate = state == 0 ? 0 : Constant.CHARGE_COMMAND_FAILED;
        }
        Log.w(TAG, " getNetworkOperatorList.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getCurNetworkOperator() {
        Log.w(TAG, " getCurNetworkOperator.in");
        Log.w(TAG, " getCurNetworkOperator.out = " + ((String) null));
        return null;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setNetworkOperator(String code) {
        int rstate = Constant.CHARGE_COMMAND_FAILED;
        Log.w(TAG, " setNetworkOperator.in code->" + code);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setNetworkOperator: mTboxManager is null");
            return Constant.CHARGE_COMMAND_FAILED;
        }
        if (tboxManager.isTboxConnected()) {
            int state = this.mTboxManager.setNetworkOperator(code);
            rstate = state == 0 ? 0 : Constant.CHARGE_COMMAND_FAILED;
        } else {
            Log.e(TAG, "setNetworkOperator: TboxManager isTboxConnected failed");
        }
        Log.w(TAG, " setNetworkOperator.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setRestartTbox(int state) {
        int rstate = Constant.CHARGE_COMMAND_FAILED;
        Log.w(TAG, " setRestartTbox.in state->" + state);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setRestartTbox: mTboxManager is null");
            return Constant.CHARGE_COMMAND_FAILED;
        }
        if (tboxManager.isTboxConnected()) {
            boolean restartTbox = this.mTboxManager.setRestartTbox(state);
            rstate = restartTbox ? 0 : Constant.CHARGE_COMMAND_FAILED;
        } else {
            Log.e(TAG, "setRestartTbox: TboxManager isTboxConnected failed");
        }
        Log.w(TAG, " setRestartTbox.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getTboxState() {
        Log.w(TAG, " getTboxState.in");
        Log.w(TAG, " getTboxState.out = -1");
        return -1;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void registerTestListener(ITestCallback testCallback) {
        this.tboxUpdateMgr.registerTextTboxListener(testCallback);
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void unregisterTestListener(ITestCallback testCallback) {
        this.tboxUpdateMgr.unregisterTextTboxListener(testCallback);
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean getMobileNetworkState() {
        boolean rstate = false;
        Log.w(TAG, " getMobileNetworkState.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getMobileNetworkState: mTboxManager is null");
        } else if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "getMobileNetworkState TboxManager.isTboxConnected failed");
        } else {
            rstate = this.mTboxManager.getApn2State();
        }
        Log.w(TAG, " getMobileNetworkState.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void getDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, IAbsMessageCallback callback) {
        Log.w(TAG, " getDiagnosticTroubleCode.in list:" + info);
        if (this.baseUpdateManager == null) {
            Log.e(TAG, " getDiagnosticTroubleCode is null");
            return;
        }
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        ArrayList<DiagnosticAidlInfo> mCachedDiagAidlInfoList = new ArrayList<>(info);
        Log.w(TAG, "getDiagnosticTroubleCode number: " + mCachedDiagAidlInfoList.size());
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ecuDtcListReq", mCachedDiagAidlInfoList);
        this.baseUpdateManager.sendMessage(18, bundle);
        Log.w(TAG, " getDiagnosticTroubleCode.out = true");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void clearDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, final IAbsMessageCallback callback) {
        Log.w(TAG, " clearDiagnosticTroubleCode.in list:" + info);
        if (this.baseUpdateManager == null) {
            Log.e(TAG, " clearDiagnosticTroubleCode is null");
            return;
        }
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        if (info.size() == 0) {
            Log.w(TAG, "clearDiagnosticTroubleCode: need to clear whole trouble code");
            Bundle bundle = new Bundle();
            bundle.putString(KEY_ECU_ID, VALUE_ECU_ID);
            bundle.putString(KEY_ECU_DIAL_ID, VALUE_ECU_DIAL_ID);
            bundle.putString(KEY_DIAG_REQ, VALUE_DIAG_REQ);
            if (this.baseUpdateManager.sendMessage(21, bundle)) {
                Log.w(TAG, "clearDiagnosticTroubleCode: sendMessage success");
                this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.-$$Lambda$OtaTsUpdateServiceImpl$uBTD30z-RgaJSJiVLnS-623SWUw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$clearDiagnosticTroubleCode$2$OtaTsUpdateServiceImpl(callback);
                    }
                });
            }
        } else {
            ArrayList<DiagnosticAidlInfo> mCachedClrDiagInfoList = new ArrayList<>(info);
            Log.w(TAG, "clearDiagnosticTroubleCode number: " + mCachedClrDiagInfoList.size());
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList("clearEcuDtcListReq", mCachedClrDiagInfoList);
            this.baseUpdateManager.sendMessage(19, bundle2);
        }
        Log.w(TAG, " clearDiagnosticTroubleCode.out = true");
    }

    public /* synthetic */ void lambda$clearDiagnosticTroubleCode$2$OtaTsUpdateServiceImpl(IAbsMessageCallback callback) {
        int size = this.mYxfAbsMessageCallbackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            try {
                IAbsMessageCallback absMsgCallback = (IAbsMessageCallback) this.mYxfAbsMessageCallbackList.getBroadcastItem(i);
                if (absMsgCallback == callback) {
                    Log.w(TAG, "clearDiagnosticTroubleCode: found callback");
                    DiagnosticAidlInfo diagInfo = new DiagnosticAidlInfo();
                    List<DiagnosticAidlInfo> infos = new ArrayList<>();
                    diagInfo.setEcuId(VALUE_ECU_ID);
                    diagInfo.setEcuDiaId(VALUE_ECU_DIAL_ID);
                    infos.add(diagInfo);
                    absMsgCallback.onClearDiagnosticTroubleCode(infos);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.mYxfAbsMessageCallbackList.finishBroadcast();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int notifyTboxTrafficResult(int i) {
        int i2;
        Log.w(TAG, " notifyTboxTrafficResult.in result " + i);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "notifyTboxTrafficResult: mTboxManager is null");
            return -1;
        }
        if (tboxManager.isTboxConnected()) {
            boolean zSendNetworkTrafficQueryResult = 0;
            if (i == 0) {
                zSendNetworkTrafficQueryResult = this.mTboxManager.sendNetworkTrafficQueryResult(NetworkQueryResult.SUCCESS);
            } else if (i == 1) {
                zSendNetworkTrafficQueryResult = this.mTboxManager.sendNetworkTrafficQueryResult(NetworkQueryResult.FAILED);
            } else if (i == 2) {
                zSendNetworkTrafficQueryResult = this.mTboxManager.sendNetworkTrafficQueryResult(NetworkQueryResult.RESERVE);
            }
            i2 = !zSendNetworkTrafficQueryResult;
        } else {
            Log.e(TAG, "notifyTboxTrafficResult: TboxManager isTboxConnected failed");
            i2 = 1;
        }
        Log.w(TAG, " notifyTboxTrafficResult.out = " + i2);
        return i2;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setCloudServieEv(int ev) {
        int rstate = -1;
        Log.w(TAG, " setCloudServieEv.in ev->" + ev);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setCloudServieEv: mTboxManager is null");
        } else if (tboxManager.isTboxConnected()) {
            boolean isSuccess = this.mTboxManager.switchCloudServiceEnv(ev);
            Log.d(TAG, "setCloudServieEv: switchCloudServiceEnv isSuccess " + isSuccess);
            rstate = this.mTboxManager.getCloudServieEv();
        } else {
            Log.e(TAG, "setCloudServieEv TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " setCloudServieEv.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean isConnectTbox() {
        boolean rstate = false;
        Log.w(TAG, " isConnectTbox.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "isConnectTbox: mTboxManager is null");
        } else if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "isConnectTbox TboxManager.isTboxConnected failed");
        } else {
            rstate = this.mTboxManager.isTboxConnected();
        }
        Log.w(TAG, " isConnectTbox.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, String keyId) {
        Log.w(TAG, " queryKeyListReq: carAccount->" + carAccount + " entityKeyTag->" + entityKeyTag + " keyType->" + keyType + " userId->" + userId + " keyId->" + keyId);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "queryKeyListReq: mTboxManager is null");
        } else if (tboxManager.isTboxConnected()) {
            DigitalKeyAccountInfo mDitKeyAccountInfo = new DigitalKeyAccountInfo();
            mDitKeyAccountInfo.setCarAccount(carAccount);
            mDitKeyAccountInfo.setEntityKeyTag(entityKeyTag);
            mDitKeyAccountInfo.setKeyType(keyType);
            mDitKeyAccountInfo.setUserId(userId);
            mDitKeyAccountInfo.setKeyId(keyId);
            this.mTboxManager.queryKeyListReq(mDitKeyAccountInfo);
        } else {
            Log.e(TAG, "queryKeyListReq TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " queryKeyListReq.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void queryKeyListByKeyArray(int carAccount, int entityKeyTag, int keyType, String userId, byte[] keyId) {
        Log.w(TAG, " queryKeyListByKeyArray: carAccount->" + carAccount + " entityKeyTag->" + entityKeyTag + " keyType->" + keyType + " userId->" + userId + " keyId->" + Arrays.toString(keyId));
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "queryKeyListByKeyArray: mTboxManager is null");
        } else if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "queryKeyListByKeyArray TboxManager.isTboxConnected failed");
        } else {
            DigitalKeyAccountInfo mDitKeyAccountInfo = new DigitalKeyAccountInfo();
            mDitKeyAccountInfo.setCarAccount(carAccount);
            mDitKeyAccountInfo.setEntityKeyTag(entityKeyTag);
            mDitKeyAccountInfo.setKeyType(keyType);
            mDitKeyAccountInfo.setUserId(userId);
            mDitKeyAccountInfo.setKeyIdArray(keyId);
            this.mTboxManager.queryKeyListReq(mDitKeyAccountInfo);
        }
        Log.w(TAG, " queryKeyListByKeyArray.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void uploadNfcOpenInfo() {
        Log.w(TAG, " uploadNfcOpenInfo.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "uploadNfcOpenInfo: mTboxManager is null");
        } else if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "uploadNfcOpenInfo TboxManager.isTboxConnected failed");
        } else {
            this.mTboxManager.uploadNfcOpenInfo();
        }
        Log.w(TAG, " uploadNfcOpenInfo.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void deleteNfcKey(int op) {
        Log.w(TAG, " deleteNfcKey: op->" + op);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "deleteNfcKey: mTboxManager is null");
        } else if (tboxManager.isTboxConnected()) {
            this.mTboxManager.deleteNfcKeyByType(op);
        } else {
            Log.e(TAG, "deleteNfcKey TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " deleteNfcKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void deleteSingleNfcKey(int op, String no) {
        Log.w(TAG, " delete single nfc key: op->" + op + " no->" + no);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "deleteNfcKey: mTboxManager is null");
        } else if (tboxManager.isTboxConnected()) {
            this.mTboxManager.deleteNfcKeyByKeyNo(op, no);
        } else {
            Log.e(TAG, "deleteNfcKey TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " deleteNfcKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void deleteOwnIphoneKey(int op, String no) {
        Log.w(TAG, " delete iphone nfc key: op->" + op + " no->" + no);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "deleteOwnIphoneKey: mTboxManager is null");
        } else if (tboxManager.isTboxConnected()) {
            this.mTboxManager.deleteOwnIphoneKey(op, no);
        } else {
            Log.e(TAG, "deleteOwnIphoneKey: TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " deleteOwnIphoneKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void startRegisterNFCKey() {
        Log.w(TAG, " startRegisterNFCKey.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "startRegisterNFCKey: mTboxManager is null");
        } else if (!tboxManager.isTboxConnected()) {
            Log.e(TAG, "startRegisterNFCKey TboxManager.isTboxConnected failed");
        } else {
            this.mTboxManager.startRegisterNfcKey();
        }
        Log.w(TAG, " startRegisterNFCKey.out");
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getIg1RelayState() {
        int rstate = 0;
        Log.w(TAG, " getIg1RelayState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getIg1RelayState CarVendorOtaMgr is null");
            return 0;
        }
        try {
            rstate = carVendorOtaMgr.getIg1RelayStateR();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getIg1RelayState.out=" + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getIg4RelayState() {
        int rstate = 0;
        Log.w(TAG, " getIg4RelayState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getIg4RelayState CarVendorOtaMgr is null");
            return 0;
        }
        try {
            rstate = carVendorOtaMgr.getIg4RelayStateR();
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        Log.w(TAG, " getIg4RelayState.out=" + rstate);
        return rstate;
    }

    public void onIg1RelayState(final int state) {
        Log.w(TAG, " onIg1RelayState.in state = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.15
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onIg1RelayState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    public void onIg4RelayState(final int state) {
        Log.w(TAG, " onIg4RelayState.in state = " + state);
        this.mCallbackHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl.16
            @Override // java.lang.Runnable
            public void run() {
                int size = OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.beginBroadcast();
                for (int i = 0; i < size; i++) {
                    try {
                        ((IAbsBYDAutoOtaListener) OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.getBroadcastItem(i)).onIg4RelayState(state);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                OtaTsUpdateServiceImpl.this.mYxfCanAbsBydAutoOtaListener.finishBroadcast();
            }
        });
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void resetOta() {
        Log.w(TAG, " resetOta.in");
        boolean result = this.mResetManager.reset();
        Log.w(TAG, " resetOta.out = " + result);
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int notifyTboxCmdResult(int cmd, int result) {
        int rstate = -1;
        Log.w(TAG, " notifyTboxCmdResult.in : cmd " + cmd + " result " + result);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "notifyTboxCmdResult mTboxManager is null");
            return -1;
        }
        if (tboxManager.isTboxConnected()) {
            rstate = this.mTboxManager.notifyTBoxCmdResult(cmd, result);
        } else {
            Log.e(TAG, "notifyTboxCmdResult TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " notifyTboxCmdResult.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int uploadCdr(int length, String cdrStr) {
        int rstate = -1;
        Log.w(TAG, " uploadCdr.in : length " + length + " cdrStr " + cdrStr);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "uploadCdr mTboxManager is null");
            return -1;
        }
        if (tboxManager.isTboxConnected()) {
            rstate = this.mTboxManager.uploadCdr(length, cdrStr);
        } else {
            Log.e(TAG, "uploadCdr TboxManager.isTboxConnected failed");
        }
        Log.w(TAG, " uploadCdr.out = " + rstate);
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean sendCanNM(int source, int state, IAbsMessageCallback callback) {
        Log.w(TAG, " sendCanNM.in : source: " + source + " state: " + state);
        if (qnxMgr == null) {
            return false;
        }
        if (callback != null) {
            this.mYxfAbsMessageCallbackList.register(callback);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("networkControlState", state);
        qnxMgr.sendMessage(8, 23, null, bundle);
        return true;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getAutoVin() {
        String rstate = null;
        Log.w(TAG, " getAutoVin.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getAutoVin: mTboxManager is null");
        } else {
            rstate = tboxManager.getVirtualVin();
        }
        Log.w(TAG, " getAutoVin.out");
        return rstate;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int updateHsm(String path) {
        int result = HSM_UPDATE_FAILED;
        Log.w(TAG, " updateHsm.in, path->" + path);
        IBoschTeeService iBoschTeeService = this.mTeeService;
        if (iBoschTeeService == null) {
            Log.e(TAG, "updateHsm: mTeeService is null");
            return HSM_UPDATE_FAILED;
        }
        try {
            BoschResult boschResult = iBoschTeeService.hsmOta(path);
            if (boschResult != null) {
                result = boschResult.ret;
            }
        } catch (Exception e) {
            Log.e(TAG, "updateHsm: failed to update hsm");
        }
        Log.w(TAG, " updateHsm.out = " + result);
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setTcpDumpState(boolean z) {
        boolean zTcpdumpReq = 0;
        Log.w(TAG, " setTcpDumpState.in, state->" + z);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setTcpDumpState: mTboxManager is null");
            return 1;
        }
        if (tboxManager.isTboxConnected()) {
            zTcpdumpReq = this.mTboxManager.tcpdumpReq(!z ? 1 : 0);
        }
        Log.w(TAG, " updateHsm.out = " + zTcpdumpReq);
        return !zTcpdumpReq;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int clearTboxLog() {
        int result = -1;
        Log.w(TAG, " clearTboxLog.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "clearTboxLog: mTboxManager is null");
            return -1;
        }
        if (tboxManager.isTboxConnected()) {
            result = this.mTboxManager.deleteTboxLog(1);
        }
        Log.w(TAG, " clearTboxLog.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setApn3State(boolean z) {
        int i = 1;
        Log.w(TAG, " setApn3State.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setApn3State: mTboxManager is null");
            return 1;
        }
        if (tboxManager.isTboxConnected()) {
            i = !this.mTboxManager.enableApn3(z) ? 1 : 0;
        }
        Log.w(TAG, " setApn3State.out = " + i);
        return i;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getApn1FailReson() {
        Log.w(TAG, " getApn1FailReson.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getApn1FailReson: mTboxManager is null");
            return -1;
        }
        Apn1Info apn1Info = tboxManager.getApn1Info();
        int result = apn1Info == null ? -1 : apn1Info.getApConnectState();
        Log.w(TAG, " getApn1FailReson.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getApn1Address() {
        Log.w(TAG, " getApn1Address.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getApn1Address: mTboxManager is null");
            return TBOX_DEFAULT_VALUE;
        }
        Apn1Info apn1Info = tboxManager.getApn1Info();
        String result = apn1Info == null ? TBOX_DEFAULT_VALUE : apn1Info.getIpString();
        Log.w(TAG, " getApn1Address.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public String getYunUuidStr() {
        Log.w(TAG, " getYunUuidStr.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getYunUuidStr: mTboxManager is null");
            return TBOX_DEFAULT_VALUE;
        }
        Apn1Info apn1Info = tboxManager.getApn1Info();
        String result = apn1Info == null ? TBOX_DEFAULT_VALUE : apn1Info.getUuid();
        StringBuilder sb = new StringBuilder();
        sb.append(" getYunUuidStr.out = ");
        sb.append(result == null ? 0 : result.length());
        Log.w(TAG, sb.toString());
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setQueryOwnersKey(int config, int op) {
        Log.w(TAG, " setQueryOwnersKey.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setQueryOwnersKey: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setQueryOwnersKey(config, op);
        Log.w(TAG, " setQueryOwnersKey.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setQueryOwnersKeyV2(byte[] data) {
        Log.w(TAG, " setQueryOwnersKeyV2.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setQueryOwnersKeyV2: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setQueryOwnersKeyV2(data);
        Log.w(TAG, " setQueryOwnersKeyV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setRegisterKeyStartPairingV2(byte[] data) {
        Log.w(TAG, " setRegisterKeyStartPairingV2.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setRegisterKeyStartPairingV2: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setRegisterKeyStartPairingV2(data);
        Log.w(TAG, " setRegisterKeyStartPairingV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setAllowStartTimeAfterUnlockV2(byte[] data) {
        Log.w(TAG, " setAllowStartTimeAfterUnlockV2.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setAllowStartTimeAfterUnlockV2: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setAllowStartTimeAfterUnlockV2(data);
        Log.w(TAG, " setAllowStartTimeAfterUnlockV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean deleteNfcFindKeyV2(byte[] data) {
        Log.w(TAG, " deleteNfcFindKeyV2.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "deleteNfcFindKeyV2: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.deleteNfcFindKeyV2(data);
        Log.w(TAG, " deleteNfcFindKeyV2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean offOnlineRemoveKey97V2(byte[] data) {
        Log.w(TAG, " offOnlineRemoveKey97V2.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "offOnlineRemoveKey97V2: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.offOnlineRemoveKey97V2(data);
        Log.w(TAG, " offOnlineRemoveKey97V2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean offOnlineRemoveKey98V2(byte[] data) {
        Log.w(TAG, " offOnlineRemoveKey98V2.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "offOnlineRemoveKey98V2: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.offOnlineRemoveKey98V2(data);
        Log.w(TAG, " offOnlineRemoveKey98V2.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setRegisterKeyStartPairing(int config, int op) {
        Log.w(TAG, " setRegisterKeyStartPairing.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setRegisterKeyStartPairing: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setRegisterKeyStartPairing(config, op);
        Log.w(TAG, " setRegisterKeyStartPairing.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setAllowStartTimeAfterUnlock(int config, int value) {
        Log.w(TAG, " setAllowStartTimeAfterUnlock.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setAllowStartTimeAfterUnlock: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setAllowStartTimeAfterUnlock(config, value);
        Log.w(TAG, " setAllowStartTimeAfterUnlock.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setOperationCmdAndKeyType(int cmd, int type) {
        Log.w(TAG, "setOperationCmdAndKeyType.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setOperationCmdAndKeyType: mTboxManager is null");
            return -1;
        }
        int result = tboxManager.setOperationCmdAndKeyType(cmd, type);
        Log.w(TAG, " setOperationCmdAndKeyType.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setKeyIdOne(byte[] arr) {
        Log.w(TAG, " setKeyIdOne.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setKeyIdOne: mTboxManager is null");
            return -1;
        }
        int result = tboxManager.setKeyIdOne(arr);
        Log.w(TAG, " setKeyIdOne.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setKeyIdTwo(byte[] arr) {
        Log.w(TAG, " setKeyIdTwo.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setKeyIdTwo: mTboxManager is null");
            return -1;
        }
        int result = tboxManager.setKeyIdTwo(arr);
        Log.w(TAG, " setKeyIdTwo.out = " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean reqNetworkOperatorMode() {
        Log.w(TAG, " reqNetworkOperatorMode.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "reqNetworkOperatorMode: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.reqNetworkOperatorMode();
        Log.w(TAG, " reqNetworkOperatorMode.in result : " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean reqOperatorInfo() {
        Log.w(TAG, " reqOperatorInfo.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "reqOperatorInfo: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.reqOperatorInfo();
        Log.w(TAG, " reqOperatorInfo.in result : " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setManuallyStationedNetwork(String network, int sigLevel) {
        Log.w(TAG, " setManuallyStationedNetwork.in network : " + network + ", sigLevel : " + sigLevel);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setManuallyStationedNetwork: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setManuallyStationedNetwork(network, sigLevel);
        Log.w(TAG, " setManuallyStationedNetwork.in result : " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean setAutomaticNetwork() {
        Log.w(TAG, " setAutomaticNetwork.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setAutomaticNetwork: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.setAutomaticNetwork();
        Log.w(TAG, " setAutomaticNetwork.in result : " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public boolean reqSimCardState() {
        Log.w(TAG, " reqSimCardState.in");
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "reqSimCardState: mTboxManager is null");
            return false;
        }
        boolean result = tboxManager.reqSimCardState();
        Log.w(TAG, " reqSimCardState.in result : " + result);
        return result;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setQxdmLogState(int i, int i2) {
        Log.w(TAG, " setQxdmLogState.in logState : " + i + ", logType : " + i2);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setQxdmLogState: mTboxManager is null");
            return 1;
        }
        int i3 = !tboxManager.operaQxdmLog(i, i2) ? 1 : 0;
        Log.w(TAG, " setQxdmLogState.in result : " + i3);
        return i3;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public void setUsbUpdateState(int state) {
        Log.w(TAG, " setUsbUpdateState.in state : " + state);
        CarVendorOtaMgr carVendorOtaMgr = this.mCarVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, "setUsbUpdateState CarVendorOtaMgr is null");
            return;
        }
        try {
            carVendorOtaMgr.setUsbUpdateStateS(state);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int enableSimcardNetwork(int i) {
        Log.w(TAG, " enableSimcardNetwork.in simcardType : " + i);
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "enableSimcardNetwork: mTboxManager is null");
            return -1;
        }
        int i2 = !tboxManager.sendSimCardType(i) ? 1 : 0;
        Log.w(TAG, " enableSimcardNetwork.out = " + i2);
        return i2;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int syncApnParam(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (this.mTboxManager == null) {
            Log.e(TAG, "syncApnParam: mTboxManager is null");
            return -1;
        }
        ApnArgs.Builder builder = new ApnArgs.Builder();
        try {
            builder.apnName(str);
            builder.user(str2);
            builder.pass(str3);
            builder.apnType(Integer.parseInt(str4));
            builder.apnProtocol(Integer.parseInt(str5));
            builder.proxy(str6);
            builder.port(str7);
            int i = !this.mTboxManager.sendApnArgs(builder.build()) ? 1 : 0;
            Log.w(TAG, " syncApnParam.out = " + i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getTrafficFromTbox(int i, String str) {
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getTrafficFromTbox: mTboxManager is null");
            return -1;
        }
        int i2 = !tboxManager.queryNetworkFlow(i, str) ? 1 : 0;
        Log.w(TAG, " getTrafficFromTbox.out = " + i2);
        return i2;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int setNonWhiteListNetworkEnable(boolean z) {
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "setNonWhiteListNetworkEnable: mTboxManager is null");
            return -1;
        }
        int i = !tboxManager.setNetworkLimit(z) ? 1 : 0;
        Log.w(TAG, " setNonWhiteListNetworkEnable.out = " + i);
        return i;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getApnParamFromTbox() {
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getApnParamFromTbox: mTboxManager is null");
            return -1;
        }
        int i = !tboxManager.queryApnArgs() ? 1 : 0;
        Log.w(TAG, " getApnParamFromTbox.out = " + i);
        return i;
    }

    @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
    public int getTboxNetworkState(int i) {
        TboxManager tboxManager = this.mTboxManager;
        if (tboxManager == null) {
            Log.e(TAG, "getTboxNetworkState: mTboxManager is null");
            return -1;
        }
        int i2 = !tboxManager.getTboxNetworkState(i) ? 1 : 0;
        Log.w(TAG, " getTboxNetworkState.out = " + i2);
        return i2;
    }
}
