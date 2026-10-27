package com.byd.bluetoothcall.service;

import android.app.ActivityManager;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.key.AbsBYDAutoKeyListener;
import android.hardware.bydauto.key.AbsBYDAutoSceneKeyListener;
import android.hardware.bydauto.key.BYDAutoKeyDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import c.a.a.b;
import c.a.a.c;
import c.a.a.d;
import c.a.a.e;
import com.byd.bluetoothcall.f.f;
import com.byd.bluetoothcall.view.a;
import com.byd.bluetoothcall.view.activity.MainActivity;
import com.byd.bluetoothcall.widget.WidgetProvide;
import com.byd.constant.StateConstant$SCO_STATE;
import com.byd.info.BtCallInfo;
import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.ToastUtils;
import com.ts.car.core.ServiceProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.carplay.ICallStateListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayServiceListener;
import ts.car.service.carplay.ICarPlayServiceManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.CallStateInfo;

/* JADX INFO: loaded from: classes.dex */
public class BYD_BlueToothService extends Service implements c, b, e, d {
    private AbsBYDAutoSceneKeyListener autoSceneKeyListener;
    private boolean isExsitThidCall;
    private String mAndroidAutoBtiddr;
    private Context mContext;
    private a yb_BT_call;
    private a yb_CP_call;
    private String TAG = "BYD_BlueToothService";
    private List<a> mCallList = new ArrayList();
    private boolean mWidgetCardShow = false;
    private final int CHANGE_AUDIO_2_BT = 1001;
    private final int CHANGE_AUDIO_2_PHONE = 1002;
    private final int CHANGE_DIALOG_BIG = 1003;
    private final int CHANGE_DIALOG_SMALL = 1004;
    private final int SHOW_BIG_DIALOG = 1005;
    private final int UPDATE_THIRD_CALL = 1006;
    private final int SEND_CALL_INFO_2_YB = 10001;
    private final int BT_CALL_AND_CP_CALL = 10004;
    private final int CLOSE_OTHER_CALL_WINDOW = 10005;
    private final int SEND_INVALID_VALUE_2_YB = 10007;
    private final int SEND_INVALID_VALUE_2_WHUD = 10008;
    private final int BT_CONNECT_STATE_SHOW_TOAST = 10009;
    private final int UPDATE_WIDGET = 10010;
    private final int HIDE_STAUTSBAR_WM = 10011;
    private final int SHOW_STAUTSBAR_WM = 10012;
    private final int HIDE_ALL_CALL_WM = 10013;
    private final int CP_CALL_TIMER = 20001;
    private boolean isBackCar = false;
    private boolean isACC_OFF = false;
    private boolean isFirstAcc_On = false;
    private int mAccState = -1;
    private boolean isPower_OFF = false;
    private boolean isSendBTCall2YB = false;
    private boolean isXCallMode = false;
    private String endCallNumber = "";
    private String endCallName = "";
    private int endCallType = 0;
    private int endCallTime = 0;
    private long mCPCallingTime = 0;
    private ICarPlayAppManager mCarPlayAppManager = null;
    private ICarPlayServiceManager mCarPlayServiceManager = null;
    private IAndroidAutoAppManager mAndroidAutoAppManager = null;
    private boolean mCPServiceConenct = false;
    private boolean mAAServiceConenct = false;
    private final IServiceConnectionListener mAAServiceConnectionListener = new AnonymousClass6();
    private ICarPlayServiceListener mCarPlayServiceListener = new ICarPlayServiceListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.7
        @Override // ts.car.service.carplay.ICarPlayServiceListener
        public void onRequestVideoFocusChanged(int i) {
            BydLogHelper.logD(BYD_BlueToothService.this.TAG, "onRequestVideoFocusChanged = " + i);
            com.byd.bluetoothcall.c.a.F().F0(i == 0);
            if (i == 0) {
                ToastUtils.cancel();
            }
            if (com.byd.bluetoothcall.c.a.F().Q().size() > 0) {
                if (i == 0) {
                    BYD_BlueToothService.this.mHandler.removeMessages(10012);
                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 100L);
                    return;
                }
                return;
            }
            if (BYD_BlueToothService.this.mCallList.size() <= 0) {
                BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 0L);
                BYD_BlueToothService.this.HideAllCallView();
            } else if (i == 0) {
                BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 0L);
                BYD_BlueToothService.this.HideAllCallView();
            }
        }
    };
    private IServiceConnectionListener mCarPlayServiceManagerConnectionListener = new IServiceConnectionListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.8
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            try {
                if (BYD_BlueToothService.this.mCarPlayServiceManager != null) {
                    BYD_BlueToothService.this.mCarPlayServiceManager.registerCarPlayServiceListener(BYD_BlueToothService.this.mCarPlayServiceListener);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
        }
    };
    private final IServiceConnectionListener mCPServiceConnectionListener = new IServiceConnectionListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.9
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            BYD_BlueToothService.this.mCPServiceConenct = true;
            BydLogHelper.logD(BYD_BlueToothService.this.TAG, "mCPServiceConnectionListener onServiceConnected = " + iPlatformService.serviceName() + " == " + BYD_BlueToothService.this.mCarPlayServiceManager.serviceName() + " == " + BYD_BlueToothService.this.mCarPlayAppManager.serviceName());
            try {
                BYD_BlueToothService bYD_BlueToothService = BYD_BlueToothService.this;
                bYD_BlueToothService.CPSessionStateChanged(bYD_BlueToothService.mCarPlayAppManager.getCarPlaySessionStatus());
                BYD_BlueToothService.this.mCarPlayAppManager.registerCarPlayStateListener(new ICarPlayStateListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.9.1
                    @Override // ts.car.service.carplay.ICarPlayStateListener
                    public void onAppStateChanged(int i, boolean z, int i2) {
                    }

                    @Override // ts.car.service.carplay.ICarPlayStateListener
                    public void onSessionStateChanged(int i) {
                        BydLogHelper.logD(BYD_BlueToothService.this.TAG, "onSessionStateChanged = " + i);
                        BYD_BlueToothService.this.CPSessionStateChanged(i);
                    }
                });
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            BydLogHelper.logD(BYD_BlueToothService.this.TAG, "onServiceDisconnected = ");
            if (BYD_BlueToothService.this.yb_CP_call != null) {
                com.byd.bluetoothcall.c.a.F().Q().clear();
                BYD_BlueToothService.this.yb_CP_call = null;
                BYD_BlueToothService.this.sendCallInfo2Instrument();
                BYD_BlueToothService.this.mHandler.sendEmptyMessage(10005);
            }
            BYD_BlueToothService.this.mCPServiceConenct = false;
        }
    };
    private ICarPlayAppListener carPlayAppListener = new ICarPlayAppListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.10
        @Override // ts.car.service.carplay.ICarPlayAppListener
        public void onCarPlayDisplayStatusChanged(boolean z) {
            Log.d(BYD_BlueToothService.this.TAG, "onCarPlayDisplayStatusChanged == " + z + com.byd.bluetoothcall.c.a.F().Q().size());
            com.byd.bluetoothcall.c.a.F().F0(z);
            if (z) {
                ToastUtils.cancel();
            }
            if (com.byd.bluetoothcall.c.a.F().Q().size() > 0) {
                if (!z) {
                    BYD_BlueToothService.this.mHandler.removeMessages(10011);
                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10012, 200L);
                    return;
                } else {
                    BYD_BlueToothService.this.mHandler.removeMessages(10012);
                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 100L);
                    BYD_BlueToothService.this.HideAllCallView();
                    return;
                }
            }
            if (BYD_BlueToothService.this.mCallList.size() <= 0) {
                BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 0L);
                return;
            }
            if (z) {
                BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 0L);
                BYD_BlueToothService.this.HideAllCallView();
                return;
            }
            if (com.byd.bluetoothcall.c.a.F().J() || com.byd.bluetoothcall.c.a.F().b0() || BYD_BlueToothService.this.isXCallMode) {
                BYD_BlueToothService.this.mHandler.sendEmptyMessage(1004);
            } else {
                BYD_BlueToothService.this.mHandler.sendEmptyMessage(1005);
            }
            BYD_BlueToothService.this.mHandler.removeMessages(10011);
            BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10012, 200L);
        }
    };
    private ICallStateListener callStateListener = new ICallStateListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.11
        @Override // ts.car.service.carplay.ICallStateListener
        public void onCallStateInfoChanged(CallStateInfo callStateInfo) {
            BydLogHelper.logD(BYD_BlueToothService.this.TAG, "onCPCallStateInfoChanged === " + callStateInfo.toString());
            BYD_BlueToothService.this.cpCallStateInfoChange(callStateInfo);
        }
    };
    private Handler mCPTimerHandler = new Handler(Looper.myLooper()) { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.12
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            if (message.what == 20001) {
                BYD_BlueToothService.access$2108(BYD_BlueToothService.this);
                Log.d("QWERTY", "mCPCallingTime == " + BYD_BlueToothService.this.mCPCallingTime);
                if (BYD_BlueToothService.this.yb_CP_call != null) {
                    BYD_BlueToothService.this.yb_CP_call.l((int) BYD_BlueToothService.this.mCPCallingTime);
                    BYD_BlueToothService.this.sendCallInfo2Instrument();
                }
                BYD_BlueToothService.this.mCPTimerHandler.sendEmptyMessageDelayed(20001, 1000L);
            }
            super.dispatchMessage(message);
        }
    };
    private int sendTimes = 0;
    private int sendInvalidTimes = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.15
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            int iSendCallInfo;
            int i = message.what;
            if (i != 10001) {
                if (i == 10004) {
                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).t();
                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                    com.byd.bluetoothcall.view.d.b.g(BYD_BlueToothService.this.mContext).f();
                    com.byd.bluetoothcall.view.d.b.g(BYD_BlueToothService.this.mContext).h(2, BYD_BlueToothService.this.getString(R.string.cp_iphone_call_in), message.obj);
                } else if (i != 10005) {
                    switch (i) {
                        case 1001:
                            if (BYD_BlueToothService.this.mCallList != null && BYD_BlueToothService.this.mCallList.size() > 0) {
                                c.a.b.b.f0().l(StateConstant$SCO_STATE.CHANNEL_BT);
                            }
                            break;
                        case 1002:
                            if (BYD_BlueToothService.this.mCallList != null && BYD_BlueToothService.this.mCallList.size() > 0) {
                                c.a.b.b.f0().l(StateConstant$SCO_STATE.CHANNEL_PHONE);
                            }
                            break;
                        case 1003:
                            try {
                                if (BYD_BlueToothService.this.mCallList != null && BYD_BlueToothService.this.mCallList.size() > 0 && com.byd.bluetoothcall.c.a.F().Q().size() == 0) {
                                    if ((BYD_BlueToothService.this.mAAServiceConenct && BYD_BlueToothService.this.mAndroidAutoAppManager != null && com.byd.bluetoothcall.c.a.F().y() == 1) || (BYD_BlueToothService.this.mCPServiceConenct && BYD_BlueToothService.this.mCarPlayAppManager != null && BYD_BlueToothService.this.mCarPlayAppManager.getCarPlayDisplayStatus())) {
                                        com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                        com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                        com.byd.bluetoothcall.view.ui.a.d(BYD_BlueToothService.this.mContext).e();
                                    } else {
                                        if (!com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).o()) {
                                            com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).u();
                                            com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                        }
                                        com.byd.bluetoothcall.c.a.F().p0(true);
                                    }
                                } else if (BYD_BlueToothService.this.mCallList == null || BYD_BlueToothService.this.mCallList.size() <= 0 || com.byd.bluetoothcall.c.a.F().Q().size() <= 0) {
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.a.d(BYD_BlueToothService.this.mContext).e();
                                } else if (BYD_BlueToothService.this.isSendBTCall2YB) {
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).u();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.c.a.F().p0(true);
                                }
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                            break;
                        case 1004:
                            try {
                                if (BYD_BlueToothService.this.mCallList == null || BYD_BlueToothService.this.mCallList.size() <= 0 || com.byd.bluetoothcall.c.a.F().Q().size() != 0) {
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                    if (com.byd.bluetoothcall.view.d.c.g(BYD_BlueToothService.this.mContext).h()) {
                                        com.byd.bluetoothcall.view.d.c.g(BYD_BlueToothService.this.mContext).a();
                                    }
                                } else if ((BYD_BlueToothService.this.mAAServiceConenct && BYD_BlueToothService.this.mAndroidAutoAppManager != null && com.byd.bluetoothcall.c.a.F().y() == 1) || (BYD_BlueToothService.this.mCPServiceConenct && BYD_BlueToothService.this.mCarPlayAppManager != null && BYD_BlueToothService.this.mCarPlayAppManager.getCarPlayDisplayStatus())) {
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.a.d(BYD_BlueToothService.this.mContext).e();
                                    if (com.byd.bluetoothcall.view.d.c.g(BYD_BlueToothService.this.mContext).h()) {
                                        com.byd.bluetoothcall.view.d.c.g(BYD_BlueToothService.this.mContext).a();
                                    }
                                } else {
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).t();
                                    com.byd.bluetoothcall.c.a.F().p0(true);
                                    if (com.byd.bluetoothcall.c.a.F().A()) {
                                        com.byd.bluetoothcall.view.d.c.g(BYD_BlueToothService.this.mContext).e();
                                    }
                                }
                            } catch (RemoteException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        case 1005:
                            try {
                                if (BYD_BlueToothService.this.mCallList != null && BYD_BlueToothService.this.mCallList.size() > 0 && com.byd.bluetoothcall.c.a.F().Q().size() == 0) {
                                    if ((BYD_BlueToothService.this.mAAServiceConenct && BYD_BlueToothService.this.mAndroidAutoAppManager != null && com.byd.bluetoothcall.c.a.F().y() == 1) || (BYD_BlueToothService.this.mCPServiceConenct && BYD_BlueToothService.this.mCarPlayAppManager != null && BYD_BlueToothService.this.mCarPlayAppManager.getCarPlayDisplayStatus())) {
                                        com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                        com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                    } else {
                                        com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).u();
                                        com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                        com.byd.bluetoothcall.c.a.F().p0(true);
                                    }
                                } else if (BYD_BlueToothService.this.mCallList == null || BYD_BlueToothService.this.mCallList.size() <= 0 || com.byd.bluetoothcall.c.a.F().Q().size() <= 0) {
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                } else if (BYD_BlueToothService.this.isSendBTCall2YB) {
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).u();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.c.a.F().p0(true);
                                }
                            } catch (RemoteException e4) {
                                e4.printStackTrace();
                            }
                            break;
                        case 1006:
                            com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).w((List) message.obj);
                            com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).z((List) message.obj);
                            break;
                        default:
                            switch (i) {
                                case 10007:
                                    if (BYD_BlueToothService.this.mHandler.hasMessages(10001)) {
                                        BYD_BlueToothService.this.mHandler.removeMessages(10001);
                                    }
                                    if (BYD_BlueToothService.this.sendInvalidTimes >= 3) {
                                        BYD_BlueToothService.this.sendInvalidTimes = 0;
                                        BYD_BlueToothService.this.mHandler.removeMessages(10007);
                                    } else {
                                        int iSendCallInfo2 = BYDAutoInstrumentDevice.getInstance(BYD_BlueToothService.this.mContext).sendCallInfo(BYD_BlueToothService.this.endCallNumber, BYD_BlueToothService.this.endCallName, 0, BYD_BlueToothService.this.endCallTime, BYD_BlueToothService.this.endCallType);
                                        Log.d("LKJHG", "send_invalid_result = " + iSendCallInfo2);
                                        if (iSendCallInfo2 != 0) {
                                            BYD_BlueToothService.access$3608(BYD_BlueToothService.this);
                                            BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10007, 500L);
                                        } else {
                                            BYD_BlueToothService.this.endCallNumber = "";
                                            BYD_BlueToothService.this.endCallName = "";
                                            BYD_BlueToothService.this.endCallTime = 0;
                                            BYD_BlueToothService.this.sendInvalidTimes = 0;
                                            BYD_BlueToothService.this.endCallType = 0;
                                            BYD_BlueToothService.this.mHandler.removeMessages(10007);
                                        }
                                    }
                                    break;
                                case 10008:
                                    BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext).setCallState(0);
                                    break;
                                case 10009:
                                    try {
                                        if ((!BYD_BlueToothService.this.mCPServiceConenct || BYD_BlueToothService.this.mCarPlayAppManager == null || !BYD_BlueToothService.this.mCarPlayAppManager.getCarPlayDisplayStatus()) && (!BYD_BlueToothService.this.mAAServiceConenct || BYD_BlueToothService.this.mAndroidAutoAppManager == null || com.byd.bluetoothcall.c.a.F().y() != 1)) {
                                            String str = (String) message.obj;
                                            if (!TextUtils.isEmpty(str) && BYD_BlueToothService.this.mContext != null) {
                                                ToastUtils.show(BYD_BlueToothService.this.mContext, str);
                                            }
                                        }
                                    } catch (RemoteException e5) {
                                        e5.printStackTrace();
                                    }
                                    break;
                                case 10010:
                                    WidgetProvide.j();
                                    break;
                                case 10011:
                                    BYD_BlueToothService.this.mHandler.removeMessages(10012);
                                    if (com.byd.bluetoothcall.view.ui.a.d(BYD_BlueToothService.this.mContext).g()) {
                                        com.byd.bluetoothcall.view.ui.a.d(BYD_BlueToothService.this.mContext).e();
                                    }
                                    break;
                                case 10012:
                                    if (BYD_BlueToothService.this.mAccState == 2) {
                                        com.byd.bluetoothcall.view.ui.a.d(BYD_BlueToothService.this.mContext).h();
                                    }
                                    break;
                                case 10013:
                                    com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                    com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                    break;
                                case 10014:
                                    if (TextUtils.isEmpty(c.a.e.b.a("ts.vendor.audioon", ""))) {
                                        com.byd.bluetoothcall.c.a.F().n0(true);
                                    }
                                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10015, 3000L);
                                    break;
                                case 10015:
                                    com.byd.bluetoothcall.c.a.F().n0(false);
                                    break;
                                case 10016:
                                    try {
                                        if ((!BYD_BlueToothService.this.mCPServiceConenct || BYD_BlueToothService.this.mCarPlayAppManager == null || !BYD_BlueToothService.this.mCarPlayAppManager.getCarPlayDisplayStatus()) && (!BYD_BlueToothService.this.mAAServiceConenct || BYD_BlueToothService.this.mAndroidAutoAppManager == null || com.byd.bluetoothcall.c.a.F().y() != 1)) {
                                            ToastUtils.show(BYD_BlueToothService.this.mContext, R.string.contact_already_exists);
                                        }
                                    } catch (RemoteException e6) {
                                        e6.printStackTrace();
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    com.byd.bluetoothcall.view.d.b.g(BYD_BlueToothService.this.mContext).a();
                }
            } else if (BYD_BlueToothService.this.sendTimes < 3) {
                if (BYD_BlueToothService.this.yb_BT_call != null && BYD_BlueToothService.this.yb_CP_call == null) {
                    BYD_BlueToothService.this.mHandler.removeMessages(10007);
                    BYDAutoInstrumentDevice bYDAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(BYD_BlueToothService.this.mContext);
                    String strB = BYD_BlueToothService.this.yb_BT_call.b();
                    String strA = BYD_BlueToothService.this.yb_BT_call.a();
                    BYD_BlueToothService bYD_BlueToothService = BYD_BlueToothService.this;
                    iSendCallInfo = bYDAutoInstrumentDevice.sendCallInfo(strB, strA, bYD_BlueToothService.ChangeBTState(bYD_BlueToothService.yb_BT_call.c()), BYD_BlueToothService.this.yb_BT_call.d() / 1000, BYD_BlueToothService.this.yb_BT_call.e() ? 1 : 0);
                    BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext).setCallNumber(BYD_BlueToothService.this.yb_BT_call.b());
                    BYDAutoWhudDevice bYDAutoWhudDevice = BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext);
                    BYD_BlueToothService bYD_BlueToothService2 = BYD_BlueToothService.this;
                    bYDAutoWhudDevice.setCallState(bYD_BlueToothService2.ChangeWHUDState(bYD_BlueToothService2.yb_BT_call.c()));
                    BYD_BlueToothService bYD_BlueToothService3 = BYD_BlueToothService.this;
                    bYD_BlueToothService3.sendTime2WHUD(bYD_BlueToothService3.yb_BT_call.d());
                    BYD_BlueToothService bYD_BlueToothService4 = BYD_BlueToothService.this;
                    String strA2 = bYD_BlueToothService4.yb_BT_call.a();
                    BYD_BlueToothService bYD_BlueToothService5 = BYD_BlueToothService.this;
                    bYD_BlueToothService4.ShowStautsBarCallView(1, strA2, bYD_BlueToothService5.ChangeBTState(bYD_BlueToothService5.yb_BT_call.c()));
                    BYD_BlueToothService bYD_BlueToothService6 = BYD_BlueToothService.this;
                    bYD_BlueToothService6.endCallNumber = bYD_BlueToothService6.yb_BT_call.b();
                    BYD_BlueToothService bYD_BlueToothService7 = BYD_BlueToothService.this;
                    bYD_BlueToothService7.endCallName = bYD_BlueToothService7.yb_BT_call.a();
                    BYD_BlueToothService bYD_BlueToothService8 = BYD_BlueToothService.this;
                    bYD_BlueToothService8.endCallTime = bYD_BlueToothService8.yb_BT_call.d() / 1000;
                    BYD_BlueToothService bYD_BlueToothService9 = BYD_BlueToothService.this;
                    bYD_BlueToothService9.endCallType = bYD_BlueToothService9.yb_BT_call.e() ? 1 : 0;
                    if (BYD_BlueToothService.this.yb_BT_call != null) {
                        Log.d("LKJHG", "result = " + iSendCallInfo + " = yb_BT_call = " + BYD_BlueToothService.this.yb_BT_call.toString());
                    }
                } else if (BYD_BlueToothService.this.yb_BT_call == null && BYD_BlueToothService.this.yb_CP_call != null) {
                    BYD_BlueToothService.this.mHandler.removeMessages(10007);
                    BYDAutoInstrumentDevice bYDAutoInstrumentDevice2 = BYDAutoInstrumentDevice.getInstance(BYD_BlueToothService.this.mContext);
                    String strB2 = BYD_BlueToothService.this.yb_CP_call.b();
                    String strA3 = BYD_BlueToothService.this.yb_CP_call.a();
                    BYD_BlueToothService bYD_BlueToothService10 = BYD_BlueToothService.this;
                    iSendCallInfo = bYDAutoInstrumentDevice2.sendCallInfo(strB2, strA3, bYD_BlueToothService10.ChangeCPState(bYD_BlueToothService10.yb_CP_call.c()), BYD_BlueToothService.this.yb_CP_call.d(), 0);
                    BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext).setCallNumber(BYD_BlueToothService.this.yb_CP_call.b());
                    BYDAutoWhudDevice bYDAutoWhudDevice2 = BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext);
                    BYD_BlueToothService bYD_BlueToothService11 = BYD_BlueToothService.this;
                    bYDAutoWhudDevice2.setCallState(bYD_BlueToothService11.ChangeCP2WHUDState(bYD_BlueToothService11.yb_CP_call.c()));
                    BYD_BlueToothService bYD_BlueToothService12 = BYD_BlueToothService.this;
                    bYD_BlueToothService12.sendTime2WHUD(bYD_BlueToothService12.yb_CP_call.d() * 1000);
                    BYD_BlueToothService bYD_BlueToothService13 = BYD_BlueToothService.this;
                    String strA4 = bYD_BlueToothService13.yb_CP_call.a();
                    BYD_BlueToothService bYD_BlueToothService14 = BYD_BlueToothService.this;
                    bYD_BlueToothService13.ShowStautsBarCallView(2, strA4, bYD_BlueToothService14.ChangeCPState(bYD_BlueToothService14.yb_CP_call.c()));
                    BYD_BlueToothService bYD_BlueToothService15 = BYD_BlueToothService.this;
                    bYD_BlueToothService15.endCallNumber = bYD_BlueToothService15.yb_CP_call.b();
                    BYD_BlueToothService bYD_BlueToothService16 = BYD_BlueToothService.this;
                    bYD_BlueToothService16.endCallName = bYD_BlueToothService16.yb_CP_call.a();
                    if (BYD_BlueToothService.this.yb_CP_call.c() == 4) {
                        BYD_BlueToothService bYD_BlueToothService17 = BYD_BlueToothService.this;
                        bYD_BlueToothService17.endCallTime = bYD_BlueToothService17.yb_CP_call.d();
                    }
                    BYD_BlueToothService.this.endCallType = 0;
                    if (BYD_BlueToothService.this.yb_CP_call != null) {
                        Log.d("LKJHG", "result = " + iSendCallInfo + " = yb_CP_call = " + BYD_BlueToothService.this.yb_CP_call.toString());
                    }
                } else if (BYD_BlueToothService.this.yb_BT_call == null || BYD_BlueToothService.this.yb_CP_call == null) {
                    Log.d("LKJHG", "endCallTime = " + BYD_BlueToothService.this.endCallTime);
                    iSendCallInfo = BYDAutoInstrumentDevice.getInstance(BYD_BlueToothService.this.mContext).sendCallInfo(BYD_BlueToothService.this.endCallNumber, BYD_BlueToothService.this.endCallName, 4, BYD_BlueToothService.this.endCallTime, BYD_BlueToothService.this.endCallType);
                    BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext).setCallState(5);
                    BYD_BlueToothService.this.mHandler.removeMessages(10008);
                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10008, 4000L);
                    BYD_BlueToothService.this.mHandler.removeMessages(10012);
                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 100L);
                    Log.d("LKJHG", "result = " + iSendCallInfo + " = endCallNumber = " + f.d(BYD_BlueToothService.this.endCallNumber) + "state = 4");
                    BYD_BlueToothService.this.mHandler.removeMessages(10007);
                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10007, 4000L);
                } else {
                    BYD_BlueToothService.this.mHandler.removeMessages(10007);
                    if (BYD_BlueToothService.this.isSendBTCall2YB) {
                        BYDAutoInstrumentDevice bYDAutoInstrumentDevice3 = BYDAutoInstrumentDevice.getInstance(BYD_BlueToothService.this.mContext);
                        String strB3 = BYD_BlueToothService.this.yb_BT_call.b();
                        String strA5 = BYD_BlueToothService.this.yb_BT_call.a();
                        BYD_BlueToothService bYD_BlueToothService18 = BYD_BlueToothService.this;
                        iSendCallInfo = bYDAutoInstrumentDevice3.sendCallInfo(strB3, strA5, bYD_BlueToothService18.ChangeBTState(bYD_BlueToothService18.yb_BT_call.c()), BYD_BlueToothService.this.yb_BT_call.d() / 1000, BYD_BlueToothService.this.yb_BT_call.e() ? 1 : 0);
                        BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext).setCallNumber(BYD_BlueToothService.this.yb_BT_call.b());
                        BYDAutoWhudDevice bYDAutoWhudDevice3 = BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext);
                        BYD_BlueToothService bYD_BlueToothService19 = BYD_BlueToothService.this;
                        bYDAutoWhudDevice3.setCallState(bYD_BlueToothService19.ChangeWHUDState(bYD_BlueToothService19.yb_BT_call.c()));
                        BYD_BlueToothService bYD_BlueToothService20 = BYD_BlueToothService.this;
                        bYD_BlueToothService20.sendTime2WHUD(bYD_BlueToothService20.yb_BT_call.d());
                        BYD_BlueToothService bYD_BlueToothService21 = BYD_BlueToothService.this;
                        String strA6 = bYD_BlueToothService21.yb_BT_call.a();
                        BYD_BlueToothService bYD_BlueToothService22 = BYD_BlueToothService.this;
                        bYD_BlueToothService21.ShowStautsBarCallView(1, strA6, bYD_BlueToothService22.ChangeBTState(bYD_BlueToothService22.yb_BT_call.c()));
                        BYD_BlueToothService bYD_BlueToothService23 = BYD_BlueToothService.this;
                        bYD_BlueToothService23.endCallNumber = bYD_BlueToothService23.yb_BT_call.b();
                        BYD_BlueToothService bYD_BlueToothService24 = BYD_BlueToothService.this;
                        bYD_BlueToothService24.endCallName = bYD_BlueToothService24.yb_BT_call.a();
                        BYD_BlueToothService bYD_BlueToothService25 = BYD_BlueToothService.this;
                        bYD_BlueToothService25.endCallTime = bYD_BlueToothService25.yb_BT_call.d() / 1000;
                        BYD_BlueToothService bYD_BlueToothService26 = BYD_BlueToothService.this;
                        bYD_BlueToothService26.endCallType = bYD_BlueToothService26.yb_BT_call.e() ? 1 : 0;
                        if (BYD_BlueToothService.this.yb_BT_call != null) {
                            Log.d("LKJHG", "result = " + iSendCallInfo + " = yb_BT_call = " + BYD_BlueToothService.this.yb_BT_call.toString());
                        }
                    } else {
                        BYDAutoInstrumentDevice bYDAutoInstrumentDevice4 = BYDAutoInstrumentDevice.getInstance(BYD_BlueToothService.this.mContext);
                        String strB4 = BYD_BlueToothService.this.yb_CP_call.b();
                        String strA7 = BYD_BlueToothService.this.yb_CP_call.a();
                        BYD_BlueToothService bYD_BlueToothService27 = BYD_BlueToothService.this;
                        iSendCallInfo = bYDAutoInstrumentDevice4.sendCallInfo(strB4, strA7, bYD_BlueToothService27.ChangeCPState(bYD_BlueToothService27.yb_CP_call.c()), BYD_BlueToothService.this.yb_CP_call.d(), 0);
                        BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext).setCallNumber(BYD_BlueToothService.this.yb_CP_call.b());
                        BYDAutoWhudDevice bYDAutoWhudDevice4 = BYDAutoWhudDevice.getInstance(BYD_BlueToothService.this.mContext);
                        BYD_BlueToothService bYD_BlueToothService28 = BYD_BlueToothService.this;
                        bYDAutoWhudDevice4.setCallState(bYD_BlueToothService28.ChangeCP2WHUDState(bYD_BlueToothService28.yb_CP_call.c()));
                        BYD_BlueToothService bYD_BlueToothService29 = BYD_BlueToothService.this;
                        bYD_BlueToothService29.sendTime2WHUD(bYD_BlueToothService29.yb_CP_call.d() * 1000);
                        BYD_BlueToothService bYD_BlueToothService30 = BYD_BlueToothService.this;
                        String strA8 = bYD_BlueToothService30.yb_CP_call.a();
                        BYD_BlueToothService bYD_BlueToothService31 = BYD_BlueToothService.this;
                        bYD_BlueToothService30.ShowStautsBarCallView(2, strA8, bYD_BlueToothService31.ChangeCPState(bYD_BlueToothService31.yb_CP_call.c()));
                        BYD_BlueToothService bYD_BlueToothService32 = BYD_BlueToothService.this;
                        bYD_BlueToothService32.endCallNumber = bYD_BlueToothService32.yb_CP_call.b();
                        BYD_BlueToothService bYD_BlueToothService33 = BYD_BlueToothService.this;
                        bYD_BlueToothService33.endCallName = bYD_BlueToothService33.yb_CP_call.a();
                        if (BYD_BlueToothService.this.yb_CP_call.c() == 4) {
                            BYD_BlueToothService bYD_BlueToothService34 = BYD_BlueToothService.this;
                            bYD_BlueToothService34.endCallTime = bYD_BlueToothService34.yb_CP_call.d();
                        }
                        BYD_BlueToothService.this.endCallType = 0;
                        if (BYD_BlueToothService.this.yb_CP_call != null) {
                            Log.d("LKJHG", "result = " + iSendCallInfo + " = yb_CP_call = " + BYD_BlueToothService.this.yb_CP_call.toString());
                        }
                    }
                }
                if (iSendCallInfo == 0) {
                    BYD_BlueToothService.this.sendTimes = 0;
                    BYD_BlueToothService.this.mHandler.removeMessages(10001);
                } else {
                    BYD_BlueToothService.access$2308(BYD_BlueToothService.this);
                    BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10001, 500L);
                }
            } else {
                BYD_BlueToothService.this.sendTimes = 0;
                BYD_BlueToothService.this.mHandler.removeMessages(10001);
            }
            super.dispatchMessage(message);
        }
    };
    private AudioManager.OnAudioFocusChangeListener focusListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.22
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
        }
    };
    private BroadcastReceiver mSplitModeReceiver = new BroadcastReceiver() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.23
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (action.equals("SplitScreenStatus.BroadcastReceiver")) {
                if (BYD_BlueToothService.this.mCallList == null || BYD_BlueToothService.this.mCallList.size() <= 0) {
                    return;
                }
                BYD_BlueToothService.this.ChangeWindowMode(50);
                return;
            }
            try {
                if (action.equals("SplitScreenFocus.BroadcastReceiver")) {
                    String stringExtra = intent.getStringExtra("SplitScreenFocusPackage");
                    BydLogHelper.logD(BYD_BlueToothService.this.TAG, "SplitScreenFocus.BroadcastReceiver = " + stringExtra);
                    if (TextUtils.isEmpty(stringExtra)) {
                        return;
                    }
                    if (!stringExtra.equals("com.byd.bluetoothcall")) {
                        com.byd.bluetoothcall.c.a.F().B0(false);
                        if (BYD_BlueToothService.this.autoSceneKeyListener != null && BYD_BlueToothService.this.mContext != null) {
                            BYDAutoKeyDevice.getInstance(BYD_BlueToothService.this.mContext).unregisterSceneListener(BYD_BlueToothService.this.autoSceneKeyListener, 101);
                        }
                        com.byd.bluetoothcall.c.a.F().t();
                        return;
                    }
                    com.byd.bluetoothcall.c.a.F().B0(true);
                    if (BYD_BlueToothService.this.autoSceneKeyListener == null || BYD_BlueToothService.this.mContext == null || c.a.b.b.f0().n0() != 2) {
                        return;
                    }
                    BYDAutoKeyDevice.getInstance(BYD_BlueToothService.this.mContext).registerSceneListener(BYD_BlueToothService.this.autoSceneKeyListener, 101);
                    return;
                }
                if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                    String stringExtra2 = intent.getStringExtra("reason");
                    if (stringExtra2 == null || !stringExtra2.equals("homekey")) {
                        return;
                    }
                    BydLogHelper.logD("DANDAN", "HOME===");
                    if (com.byd.bluetoothcall.c.a.F().y() == 1) {
                        com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                        com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                        return;
                    } else {
                        if (!BYD_BlueToothService.this.isSendBTCall2YB || BYD_BlueToothService.this.mCallList.size() <= 0) {
                            return;
                        }
                        com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).t();
                        com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                        return;
                    }
                }
                if (action.equals("com.byd.system.ecall.ACTION_SHOW")) {
                    Log.d(BYD_BlueToothService.this.TAG, "com.byd.system.ecall.ACTION_SHOW");
                    BYD_BlueToothService.this.isXCallMode = true;
                    if (BYD_BlueToothService.this.mCallList == null || BYD_BlueToothService.this.mCallList.size() <= 0) {
                        return;
                    }
                    BYD_BlueToothService.this.ChangeWindowMode(50);
                    BYD_BlueToothService.this.ChangeAudio(1000);
                    return;
                }
                if (!action.equals("com.byd.system.ecall.ACTION_DISMISS")) {
                    if (action.equals("com.byd.action.card.more.app")) {
                        com.byd.bluetoothcall.c.a.F().c();
                        return;
                    }
                    return;
                }
                Log.d(BYD_BlueToothService.this.TAG, "com.byd.system.ecall.ACTION_DISMISS");
                BYD_BlueToothService.this.isXCallMode = false;
                if (BYD_BlueToothService.this.mCallList == null || BYD_BlueToothService.this.mCallList.size() <= 0) {
                    return;
                }
                BYD_BlueToothService.this.ChangeWindowMode(50);
                BYD_BlueToothService.this.ChangeAudio(1000);
            } catch (Exception unused) {
            }
        }
    };

    /* JADX INFO: renamed from: com.byd.bluetoothcall.service.BYD_BlueToothService$6, reason: invalid class name */
    class AnonymousClass6 implements IServiceConnectionListener {
        AnonymousClass6() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            BYD_BlueToothService.this.mAAServiceConenct = true;
            BydLogHelper.logD(BYD_BlueToothService.this.TAG, "mAAServiceConnectionListener onServiceConnected = ");
            try {
                BYD_BlueToothService bYD_BlueToothService = BYD_BlueToothService.this;
                bYD_BlueToothService.mAndroidAutoBtiddr = bYD_BlueToothService.mAndroidAutoAppManager.getProjectionBluetoothAddress();
                Log.d(BYD_BlueToothService.this.TAG, "initeAndroidAuto == mAndroidAutoBtiddr = " + BYD_BlueToothService.this.mAndroidAutoBtiddr);
                BYD_BlueToothService.this.mAndroidAutoAppManager.registerAndroidAutoAppListener(new IAndroidAutoAppListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.6.1
                    @Override // ts.car.service.androidauto.IAndroidAutoAppListener
                    public void onLastAvailableDeviceChanged(String str, int i) {
                    }

                    @Override // ts.car.service.androidauto.IAndroidAutoAppListener
                    public void onProjectionBluetoothAddressChanged(String str) {
                        Log.d(BYD_BlueToothService.this.TAG, "onProjectionBluetoothAddressChanged  address = " + str);
                        BYD_BlueToothService.this.mAndroidAutoBtiddr = str;
                    }

                    @Override // ts.car.service.androidauto.IAndroidAutoAppListener
                    public void onVideoFocusStatusChanged(int i) {
                        BydLogHelper.logD(BYD_BlueToothService.this.TAG, "onVideoFocusStatusChanged  = " + i);
                        com.byd.bluetoothcall.c.a.F().l0(i);
                        if (i == 1) {
                            ToastUtils.cancel();
                        }
                        if (BYD_BlueToothService.this.mCallList.size() > 0) {
                            if (i == 1) {
                                BYD_BlueToothService.this.mHandler.post(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.6.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Log.d(BYD_BlueToothService.this.TAG, "mAndroidAutoBtiddr = " + BYD_BlueToothService.this.mAndroidAutoBtiddr);
                                        if (c.a.b.b.f0().e0() != null) {
                                            if (BYD_BlueToothService.this.mAndroidAutoBtiddr == null || !BYD_BlueToothService.this.mAndroidAutoBtiddr.equals(c.a.b.b.f0().e0().getAddress())) {
                                                if (BYD_BlueToothService.this.mHandler.hasMessages(1004)) {
                                                    BYD_BlueToothService.this.mHandler.removeMessages(1004);
                                                }
                                                BYD_BlueToothService.this.mHandler.sendEmptyMessage(1004);
                                            } else {
                                                com.byd.bluetoothcall.view.ui.a.d(BYD_BlueToothService.this.mContext).e();
                                                com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).m();
                                                com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).m();
                                            }
                                        }
                                        if (TextUtils.isEmpty(c.a.e.b.a("ts.vendor.audioon", ""))) {
                                            c.a.e.b.c("ts.vendor.audioon", "audio_on");
                                        }
                                        com.byd.bluetoothcall.view.d.c.g(BYD_BlueToothService.this.mContext).a();
                                    }
                                });
                                return;
                            }
                            if (com.byd.bluetoothcall.c.a.F().J() || com.byd.bluetoothcall.c.a.F().b0() || BYD_BlueToothService.this.isXCallMode) {
                                BYD_BlueToothService.this.mHandler.sendEmptyMessage(1004);
                            } else {
                                BYD_BlueToothService.this.mHandler.sendEmptyMessage(1005);
                            }
                            BYD_BlueToothService.this.mHandler.removeMessages(10011);
                            BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10012, 200L);
                        }
                    }
                });
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            BydLogHelper.logD(BYD_BlueToothService.this.TAG, "onServiceDisconnected = ");
            BYD_BlueToothService.this.mAAServiceConenct = false;
        }
    }

    class MyBind extends Binder {
        MyBind() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void CPSessionStateChanged(int i) {
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            if (i == 2) {
                try {
                    iCarPlayAppManager.registerCallStateListener(this.callStateListener);
                    this.mCarPlayAppManager.registerCarPlayAppListener(this.carPlayAppListener);
                    return;
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (i == 4) {
                try {
                    if (this.yb_CP_call != null) {
                        com.byd.bluetoothcall.c.a.F().Q().clear();
                        this.yb_CP_call = null;
                        sendCallInfo2Instrument();
                        this.mHandler.sendEmptyMessage(10005);
                    }
                    this.mCarPlayAppManager.unregisterCallStateListener(this.callStateListener);
                    this.mCarPlayAppManager.unregisterCarPlayAppListener(this.carPlayAppListener);
                } catch (RemoteException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ChangeAudio(int i) {
        if (this.isXCallMode) {
            if (this.mHandler.hasMessages(1002)) {
                this.mHandler.removeMessages(1002);
            }
            this.mHandler.sendEmptyMessageDelayed(1002, i);
        } else {
            if (this.mHandler.hasMessages(1001)) {
                this.mHandler.removeMessages(1001);
            }
            this.mHandler.sendEmptyMessageDelayed(1001, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ChangeBTState(int i) {
        if (i == 0 || i == 1) {
            return 3;
        }
        if (i == 2 || i == 3) {
            return 2;
        }
        return (i == 4 || i == 5) ? 1 : 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ChangeCP2WHUDState(int i) {
        if (i == 0) {
            return 5;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return (i == 4 || i == 5) ? 3 : 0;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ChangeCPState(int i) {
        if (i == 0) {
            return 4;
        }
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return (i == 4 || i == 5) ? 3 : 0;
        }
        return 1;
    }

    private int ChangeState2AVM(int i) {
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 4;
        }
        if (i != 4) {
            return i != 5 ? 7 : 1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ChangeWHUDState(int i) {
        if (i == 0 || i == 1) {
            return 3;
        }
        if (i == 2 || i == 3) {
            return 1;
        }
        return (i == 4 || i == 5) ? 2 : 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ChangeWindowMode(int i) {
        Log.d("QNMD", "isNaviMode = " + com.byd.bluetoothcall.c.a.F().J() + " = isSplit_screenMode = " + com.byd.bluetoothcall.c.a.F().b0() + " = isXCallMode = " + this.isXCallMode + "== " + com.byd.bluetoothcall.c.a.F().A());
        if (com.byd.bluetoothcall.c.a.F().J() || com.byd.bluetoothcall.c.a.F().b0() || this.isXCallMode || com.byd.bluetoothcall.c.a.F().A()) {
            if (this.mHandler.hasMessages(1004)) {
                this.mHandler.removeMessages(1004);
            }
            this.mHandler.sendEmptyMessageDelayed(1004, i);
        } else {
            if (this.mHandler.hasMessages(1003)) {
                this.mHandler.removeMessages(1003);
            }
            this.mHandler.sendEmptyMessageDelayed(1003, i);
        }
    }

    private void Exit_Close_Screen() {
        Context context = this.mContext;
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver"));
            intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
            if (packageManager.queryIntentServices(intent, 0).isEmpty()) {
                return;
            }
            this.mContext.startService(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void HideAllCallView() {
        this.mHandler.sendEmptyMessage(10013);
    }

    private int SCO_CHANGE_INT(StateConstant$SCO_STATE stateConstant$SCO_STATE) {
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_BT) {
            return 2;
        }
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_SWITCHING) {
            return 1;
        }
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_PHONE) {
        }
        return 0;
    }

    private void ShowCallView() {
        ChangeWindowMode(50);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ShowStautsBarCallView(int i, String str, int i2) {
        ICarPlayAppManager iCarPlayAppManager;
        com.byd.bluetoothcall.view.ui.a.d(this.mContext).f(i, str, i2);
        try {
            if ((this.mCPServiceConenct && (iCarPlayAppManager = this.mCarPlayAppManager) != null && iCarPlayAppManager.getCarPlayDisplayStatus()) || (this.mAAServiceConenct && this.mAndroidAutoAppManager != null && com.byd.bluetoothcall.c.a.F().y() == 1)) {
                if (com.byd.bluetoothcall.view.ui.a.d(this.mContext).g()) {
                    com.byd.bluetoothcall.view.ui.a.d(this.mContext).e();
                }
            } else {
                if (com.byd.bluetoothcall.view.ui.a.d(this.mContext).g()) {
                    return;
                }
                com.byd.bluetoothcall.view.ui.a.d(this.mContext).h();
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    private void UpdateUnReadMissedCalls() {
        com.byd.bluetoothcall.c.a.F().l();
        updateWidget(300L);
    }

    static /* synthetic */ long access$2108(BYD_BlueToothService bYD_BlueToothService) {
        long j = bYD_BlueToothService.mCPCallingTime;
        bYD_BlueToothService.mCPCallingTime = 1 + j;
        return j;
    }

    static /* synthetic */ int access$2308(BYD_BlueToothService bYD_BlueToothService) {
        int i = bYD_BlueToothService.sendTimes;
        bYD_BlueToothService.sendTimes = i + 1;
        return i;
    }

    static /* synthetic */ int access$3608(BYD_BlueToothService bYD_BlueToothService) {
        int i = bYD_BlueToothService.sendInvalidTimes;
        bYD_BlueToothService.sendInvalidTimes = i + 1;
        return i;
    }

    private boolean compareList(List<a> list, List<BtCallInfo> list2) {
        if (list.size() == 0 && list2.size() == 0) {
            return true;
        }
        if (list.size() != 0 && list.size() == list2.size()) {
            if (list2.size() == 1) {
                if (list.get(0).b().equals(list2.get(0).m()) && list.get(0).c() == list2.get(0).n()) {
                    return true;
                }
            } else if (list2.size() == 2) {
                if (list.get(0).b().equals(list2.get(0).m())) {
                    if (list.get(0).c() == list2.get(0).n() && list.get(1).b().equals(list2.get(1).m()) && list.get(1).c() == list2.get(1).n()) {
                        return true;
                    }
                } else if (list.get(0).b().equals(list2.get(1).m()) && list.get(0).c() == list2.get(1).n() && list.get(1).b().equals(list2.get(0).m()) && list.get(1).c() == list2.get(0).n()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cpCallStateInfoChange(CallStateInfo callStateInfo) {
        boolean z;
        if (callStateInfo.getCallUuid() == null) {
            return;
        }
        if (com.byd.bluetoothcall.c.a.F().Q().size() > 0) {
            int i = 0;
            while (true) {
                if (i >= com.byd.bluetoothcall.c.a.F().Q().size()) {
                    z = false;
                    break;
                } else if (callStateInfo.getCallUuid().equals(com.byd.bluetoothcall.c.a.F().Q().get(i).getCallUuid())) {
                    com.byd.bluetoothcall.c.a.F().Q().remove(i);
                    if (callStateInfo.getStatus() != 0) {
                        com.byd.bluetoothcall.c.a.F().Q().add(0, callStateInfo);
                    }
                    z = true;
                } else {
                    i++;
                }
            }
            if (!z) {
                com.byd.bluetoothcall.c.a.F().Q().add(0, callStateInfo);
            }
        } else if (callStateInfo.getStatus() != 0) {
            com.byd.bluetoothcall.c.a.F().Q().add(0, callStateInfo);
        }
        BydLogHelper.logD(this.TAG, "callStateInfo.getStatus() === " + callStateInfo.getStatus());
        int status = callStateInfo.getStatus();
        if (status == 0) {
            BydLogHelper.logD(this.TAG, "CPlIST == " + com.byd.bluetoothcall.c.a.F().Q().size());
            if (com.byd.bluetoothcall.c.a.F().Q().size() == 0 && this.mCallList.size() > 0) {
                if (com.byd.bluetoothcall.c.a.F().Y()) {
                    Log.d(this.TAG, "CP 挂断 接听蓝牙 电话");
                    com.byd.bluetoothcall.c.a.F().v0(false);
                    c.a.b.b.f0().O();
                } else {
                    if (!com.byd.bluetoothcall.c.a.F().J() && !com.byd.bluetoothcall.c.a.F().b0() && !this.isXCallMode) {
                        this.mHandler.sendEmptyMessage(1005);
                    }
                    this.mHandler.sendEmptyMessage(10005);
                }
            }
            if (com.byd.bluetoothcall.c.a.F().Q().size() < 1 && this.mCPTimerHandler.hasMessages(20001)) {
                this.mCPTimerHandler.removeMessages(20001);
            }
        } else if (status != 1) {
            if (status != 2) {
                if (status == 4) {
                    if (this.mCallList.size() > 0) {
                        this.isSendBTCall2YB = true;
                    }
                    Log.d(this.TAG, "callStateInfo.getDuration() == " + callStateInfo.getDuration());
                    this.mCPCallingTime = callStateInfo.getDuration();
                    if (this.mCPTimerHandler.hasMessages(20001)) {
                        this.mCPTimerHandler.removeMessages(20001);
                    }
                    this.mCPTimerHandler.sendEmptyMessageDelayed(20001, 1000L);
                } else if (status != 5) {
                    this.mHandler.sendEmptyMessage(10005);
                }
            } else if (this.mCallList.size() > 0) {
                Message messageObtainMessage = this.mHandler.obtainMessage();
                messageObtainMessage.what = 10004;
                messageObtainMessage.obj = callStateInfo;
                this.mHandler.sendMessage(messageObtainMessage);
                this.isSendBTCall2YB = false;
            }
        } else if (this.mCallList.size() > 0) {
            this.isSendBTCall2YB = true;
        }
        if (com.byd.bluetoothcall.c.a.F().Q().size() <= 0) {
            this.yb_CP_call = null;
            sendCallInfo2Instrument();
            return;
        }
        CallStateInfo callStateInfo2 = com.byd.bluetoothcall.c.a.F().Q().get(0);
        if (this.yb_CP_call == null) {
            this.yb_CP_call = new a();
        }
        this.yb_CP_call.j(callStateInfo2.getDisplayName());
        this.yb_CP_call.i(callStateInfo2.getDisplayName());
        this.yb_CP_call.k(callStateInfo2.getStatus());
        this.yb_CP_call.l((int) callStateInfo2.getDuration());
        if (this.yb_CP_call.c() != 3) {
            sendCallInfo2Instrument();
        }
    }

    private void initService() {
        Intent intent = new Intent();
        intent.setAction("com.byd.bluetoothservice.BTService");
        intent.setPackage(getPackageName());
        startService(intent);
        com.byd.bluetoothcall.c.a.F().W(this.mContext);
        c.a.b.b.f0().F0(this);
        c.a.b.b.f0().E0(this);
        c.a.b.c.t().A(this);
        c.a.b.a.K().V(this);
        BYDAutoKeyDevice.getInstance(this.mContext).registerListener(new AbsBYDAutoKeyListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.1
            @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener, a.b.d
            public /* bridge */ /* synthetic */ void isConnected(boolean z) {
                super.isConnected(z);
            }

            @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener, a.b.d
            public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
                super.isOptimizationApi(z);
            }

            @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener
            public void onKeyClick(int i, int i2) {
                super.onKeyClick(i, i2);
                BydLogHelper.logD("DANDAN", "keyValue == " + i);
                try {
                    BYD_BlueToothService.this.onKeyReceiver(i, i2);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        });
        BYDAutoStudy.getInstance(this.mContext).registerConnectListener(new BYDAutoStudyListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.2
            @Override // android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener, a.b.d
            public void isConnected(boolean z) {
                Log.d(BYD_BlueToothService.this.TAG, "====mAccState==== isConnected = " + z);
                if (z) {
                    BYD_BlueToothService bYD_BlueToothService = BYD_BlueToothService.this;
                    bYD_BlueToothService.mAccState = BYDAutoBodyworkDevice.getInstance(bYD_BlueToothService.mContext).getPowerLevel();
                    Log.d(BYD_BlueToothService.this.TAG, "====mAccState==== inite = " + BYD_BlueToothService.this.mAccState);
                }
            }

            @Override // android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener, a.b.d
            public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
                super.isOptimizationApi(z);
            }
        });
        BYDAutoBodyworkDevice.getInstance(this.mContext).registerListener(new AbsBYDAutoBodyworkListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.3
            @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener, a.b.d
            public /* bridge */ /* synthetic */ void isConnected(boolean z) {
                super.isConnected(z);
            }

            @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener, a.b.d
            public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
                super.isOptimizationApi(z);
            }

            @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
            public void onPowerLevelChanged(int i) {
                super.onPowerLevelChanged(i);
                Log.d("BYD_BlueToothService", "====onPowerLevelChanged==== level = " + i);
                if (BYD_BlueToothService.this.mAccState != i) {
                    BYD_BlueToothService.this.mAccState = i;
                    BYD_BlueToothService bYD_BlueToothService = BYD_BlueToothService.this;
                    bYD_BlueToothService.isACC_OFF = bYD_BlueToothService.mAccState != 2;
                    BYD_BlueToothService bYD_BlueToothService2 = BYD_BlueToothService.this;
                    bYD_BlueToothService2.isFirstAcc_On = bYD_BlueToothService2.mAccState == 2;
                    if (BYD_BlueToothService.this.mAccState != 2) {
                        BYD_BlueToothService.this.mHandler.removeMessages(10007);
                        BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10007, 10L);
                        BYD_BlueToothService.this.mHandler.removeMessages(10008);
                        BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10008, 10L);
                        BYD_BlueToothService.this.mHandler.removeMessages(10012);
                        BYD_BlueToothService.this.mHandler.sendEmptyMessageDelayed(10011, 0L);
                        BYD_BlueToothService.this.isFirstAcc_On = false;
                    }
                    if (BYD_BlueToothService.this.mAccState == 0) {
                        c.a.e.b.c("ts.vendor.audioon", "");
                    }
                }
                Log.d(BYD_BlueToothService.this.TAG, "====onPowerLevelChanged==== isFirstAcc_On = " + BYD_BlueToothService.this.isFirstAcc_On);
            }
        });
        BYDAutoAudioDevice.getInstance(this.mContext).registerListener(new AbsBYDAutoAudioListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.4
            @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener, a.b.d
            public /* bridge */ /* synthetic */ void isConnected(boolean z) {
                super.isConnected(z);
            }

            @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener, a.b.d
            public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
                super.isOptimizationApi(z);
            }

            @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
            public void onBtCall_MicMuteChanged(final int i) {
                super.onBtCall_MicMuteChanged(i);
                Log.d("QNMD", "onBtCall_MicMuteChanged == " + i);
                BYD_BlueToothService.this.mHandler.post(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.byd.bluetoothcall.view.ui.b.l(BYD_BlueToothService.this.mContext).q(i);
                        com.byd.bluetoothcall.view.ui.c.l(BYD_BlueToothService.this.mContext).p(i);
                    }
                });
            }
        });
        this.autoSceneKeyListener = new AbsBYDAutoSceneKeyListener() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.5
            @Override // android.hardware.bydauto.key.AbsBYDAutoSceneKeyListener, a.b.d
            public /* bridge */ /* synthetic */ void isConnected(boolean z) {
                super.isConnected(z);
            }

            @Override // android.hardware.bydauto.key.AbsBYDAutoSceneKeyListener, a.b.d
            public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
                super.isOptimizationApi(z);
            }

            @Override // android.hardware.bydauto.key.AbsBYDAutoSceneKeyListener
            public void onKeyClick(int i, int i2) {
                if (i2 == 1 || i2 == 2) {
                    if (i == 8) {
                        com.byd.bluetoothcall.c.a.F().u(1);
                    } else if (i == 10) {
                        com.byd.bluetoothcall.c.a.F().v(1);
                    }
                }
                super.onKeyClick(i, i2);
            }
        };
        new com.byd.bluetoothcall.view.activity.a(this.mContext);
    }

    private void initeAndroidAuto() {
        if (f.b(this.mContext, "com.ts.androidauto.app")) {
            if (this.mAAServiceConenct) {
                Log.d(this.TAG, "AAService is Conect");
                return;
            }
            this.mAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this);
            BydLogHelper.logD(this.TAG, "mAndroidAutoAppManager = " + this.mAndroidAutoAppManager);
            IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
            if (iAndroidAutoAppManager != null) {
                iAndroidAutoAppManager.registerConnectionListener(this.mAAServiceConnectionListener);
            }
        }
    }

    private void initeCarPlay() {
        if (f.b(this.mContext, "com.ts.carplay.app")) {
            if (this.mCPServiceConenct) {
                Log.d(this.TAG, "CPService is Conect");
                return;
            }
            this.mCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this);
            BydLogHelper.logD(this.TAG, "mCarPlayAppManager = " + this.mCarPlayAppManager);
            if (this.mCarPlayAppManager != null) {
                com.byd.bluetoothcall.c.a.F().o0(this.mCarPlayAppManager);
                this.mCarPlayAppManager.registerConnectionListener(this.mCPServiceConnectionListener);
            }
            this.mCarPlayServiceManager = (ICarPlayServiceManager) ServiceProvider.get(ICarPlayServiceManager.class, this.mContext);
            BydLogHelper.logD(this.TAG, "mCarPlayServiceManager = " + this.mCarPlayServiceManager);
            ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
            if (iCarPlayServiceManager != null) {
                iCarPlayServiceManager.registerConnectionListener(this.mCarPlayServiceManagerConnectionListener);
            }
        }
    }

    private boolean isBT_Top() {
        ActivityManager.RunningTaskInfo runningTaskInfoM = com.byd.bluetoothcall.c.a.F().M();
        if (runningTaskInfoM == null) {
            return false;
        }
        String packageName = runningTaskInfoM.baseActivity.getPackageName();
        return !TextUtils.isEmpty(packageName) && packageName.equals("com.byd.bluetoothcall");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onKeyReceiver(int i, int i2) throws RemoteException {
        ICarPlayAppManager iCarPlayAppManager;
        ICarPlayAppManager iCarPlayAppManager2;
        if (i2 == 1) {
            if (i != 12) {
                if (i != 13) {
                    return;
                }
                if (com.byd.bluetoothcall.view.d.b.g(this.mContext).i()) {
                    com.byd.bluetoothcall.view.d.b.g(this.mContext).e();
                    return;
                }
                if (com.byd.bluetoothcall.c.a.F().Q().size() <= 0 || !this.mCPServiceConenct || (iCarPlayAppManager2 = this.mCarPlayAppManager) == null || !iCarPlayAppManager2.getCarPlayDisplayStatus()) {
                    if (this.mCallList.size() == 1) {
                        if (this.mCallList.get(0).c() == 4 || this.mCallList.get(0).c() == 5) {
                            c.a.b.b.f0().G0();
                            return;
                        } else {
                            c.a.b.b.f0().v0();
                            return;
                        }
                    }
                    if (this.mCallList.size() == 2) {
                        if (this.mCallList.get(1).c() == 4 || this.mCallList.get(1).c() == 5) {
                            c.a.b.b.f0().G0();
                            return;
                        } else {
                            c.a.b.b.f0().y0(com.byd.bluetoothcall.c.a.F().E());
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            if (f.g(this.mContext) == f.f3283d) {
                return;
            }
            if (f.g(this.mContext) == f.f3284e) {
                if (this.mAAServiceConenct && this.mAndroidAutoAppManager != null && com.byd.bluetoothcall.c.a.F().y() == 1) {
                    return;
                }
                Context context = this.mContext;
                ToastUtils.show(context, context.getString(R.string.warring_mode));
                return;
            }
            if (com.byd.bluetoothcall.view.d.c.g(this.mContext).h()) {
                return;
            }
            if (com.byd.bluetoothcall.view.d.b.g(this.mContext).i()) {
                com.byd.bluetoothcall.view.d.b.g(this.mContext).b();
                return;
            }
            if (com.byd.bluetoothcall.c.a.F().Q().size() <= 0 || this.mCallList.size() != 0) {
                if (this.mCallList.size() == 1) {
                    if (this.mCallList.get(0).c() == 4 || this.mCallList.get(0).c() == 5) {
                        c.a.b.b.f0().O();
                        return;
                    }
                } else if (this.mCallList.size() == 2 && (this.mCallList.get(0).c() == 4 || this.mCallList.get(0).c() == 5 || this.mCallList.get(1).c() == 4 || this.mCallList.get(1).c() == 5)) {
                    c.a.b.b.f0().P();
                    return;
                }
                if (BYDAutoGearboxDevice.getInstance(this.mContext).getGear() == 2) {
                    return;
                }
                com.byd.bluetoothcall.c.a.F().d();
                if (c.a.b.b.f0().n0() != 2) {
                    ICarPlayAppManager iCarPlayAppManager3 = this.mCarPlayAppManager;
                    if (iCarPlayAppManager3 != null && iCarPlayAppManager3.getCarPlaySessionStatus() == 2) {
                        try {
                            if (this.mCPServiceConenct && (iCarPlayAppManager = this.mCarPlayAppManager) != null) {
                                iCarPlayAppManager.requestUi(3);
                                return;
                            }
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                } else if (com.byd.bluetoothcall.c.a.F().X()) {
                    if (TextUtils.isEmpty(com.byd.bluetoothcall.c.a.F().U())) {
                        if (com.byd.bluetoothcall.c.a.F().R() != 1) {
                            this.mHandler.post(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.13
                                @Override // java.lang.Runnable
                                public void run() {
                                    com.byd.bluetoothcall.c.a.F().c();
                                }
                            });
                            Intent intent = new Intent(this.mContext, (Class<?>) MainActivity.class);
                            intent.setFlags(268435456);
                            intent.putExtra("page", 1);
                            startActivity(intent);
                            return;
                        }
                        String strO = c.a.b.c.t().x().o();
                        if (TextUtils.isEmpty(strO) || strO.equals("unknown")) {
                            return;
                        }
                        for (int i3 = 0; i3 < this.mCallList.size(); i3++) {
                            if (this.mCallList.get(i3).b().equals(strO)) {
                                return;
                            }
                        }
                        com.byd.bluetoothcall.c.a.F().H0(strO);
                    }
                    c.a.b.b.f0().c(com.byd.bluetoothcall.c.a.F().U());
                    com.byd.bluetoothcall.c.a.F().t();
                    com.byd.bluetoothcall.c.a.F().s();
                    return;
                }
                Log.d("JaSON", "isSplit_screenMode = " + com.byd.bluetoothcall.c.a.F().b0() + "==  isNaviMode == " + com.byd.bluetoothcall.c.a.F().J());
                if (com.byd.bluetoothcall.c.a.F().b0() || !com.byd.bluetoothcall.c.a.F().J()) {
                    Log.d("JaSON", "==============================startActivity===============================");
                    ComponentName componentName = new ComponentName("com.byd.bluetoothcall", "com.byd.bluetoothcall.view.activity.MainActivity");
                    Intent intent2 = new Intent();
                    intent2.setFlags(268435456);
                    intent2.setComponent(componentName);
                    intent2.putExtra("page", 0);
                    startActivity(intent2);
                }
            }
        }
    }

    private void registerSplitBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SplitScreenStatus.BroadcastReceiver");
        intentFilter.addAction("SplitScreenFocus.BroadcastReceiver");
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("com.byd.system.ecall.ACTION_SHOW");
        intentFilter.addAction("com.byd.system.ecall.ACTION_DISMISS");
        intentFilter.addAction("com.byd.action.card.more.app");
        registerReceiver(this.mSplitModeReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallInfo2Instrument() {
        this.mHandler.removeMessages(10001);
        if (this.mAccState == 2) {
            this.mHandler.sendEmptyMessageDelayed(10001, 30L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTime2WHUD(int i) {
        if (i < 0) {
            return;
        }
        String[] strArrSplit = f.v(i).split(":");
        if (strArrSplit.length == 2) {
            BYDAutoWhudDevice.getInstance(this.mContext).setCallTimeHour(0);
            BYDAutoWhudDevice.getInstance(this.mContext).setCallTimeMinute(Integer.valueOf(strArrSplit[0]).intValue());
            BYDAutoWhudDevice.getInstance(this.mContext).setCallTimeSecond(Integer.valueOf(strArrSplit[1]).intValue());
        } else if (strArrSplit.length == 3) {
            BYDAutoWhudDevice.getInstance(this.mContext).setCallTimeHour(Integer.valueOf(strArrSplit[0]).intValue());
            BYDAutoWhudDevice.getInstance(this.mContext).setCallTimeMinute(Integer.valueOf(strArrSplit[1]).intValue());
            BYDAutoWhudDevice.getInstance(this.mContext).setCallTimeSecond(Integer.valueOf(strArrSplit[2]).intValue());
        }
    }

    private void updateCallName() {
        if (this.mCallList.size() != 2) {
            if (this.mCallList.size() == 1) {
                updateCallState(this.mCallList.get(0), this.mCallList.get(0).c());
                return;
            }
            return;
        }
        String strA = this.mCallList.get(0).a();
        if (this.mCallList.get(0).e()) {
            strA = getResources().getString(R.string.networkcall);
        } else if (TextUtils.isEmpty(this.mCallList.get(0).b())) {
            strA = getResources().getString(R.string.unknown_number);
        }
        com.byd.bluetoothcall.view.ui.c.l(this.mContext).c(com.byd.bluetoothcall.f.e.a(strA));
        com.byd.bluetoothcall.view.ui.b.l(this.mContext).a(com.byd.bluetoothcall.f.e.a(strA));
        updateCallState(this.mCallList.get(1), this.mCallList.get(1).c());
    }

    private void updateCallState(a aVar, int i) {
        String strA = aVar.a();
        boolean z = false;
        if (aVar.e()) {
            z = true;
            strA = getResources().getString(R.string.networkcall);
        } else if (TextUtils.isEmpty(aVar.b())) {
            strA = getResources().getString(R.string.unknown_number);
        }
        String str = strA;
        boolean z2 = z;
        com.byd.bluetoothcall.view.ui.c.l(this.mContext).u(str, aVar.b(), i, com.byd.bluetoothcall.f.d.b(this, aVar.b()), com.byd.bluetoothcall.c.a.F().G(), z2);
        com.byd.bluetoothcall.view.ui.b.l(this.mContext).v(str, aVar.b(), i, com.byd.bluetoothcall.f.d.b(this, aVar.b()), com.byd.bluetoothcall.c.a.F().G(), z2);
        if (this.yb_BT_call == null) {
            this.yb_BT_call = new a();
        }
        this.yb_BT_call.j(aVar.b());
        this.yb_BT_call.i(strA);
        this.yb_BT_call.k(i);
        this.yb_BT_call.h(aVar.e());
        sendCallInfo2Instrument();
    }

    private void updateThirdState(List<a> list) {
        if (this.mHandler.hasMessages(1006)) {
            this.mHandler.removeMessages(1006);
        }
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 1006;
        messageObtainMessage.obj = list;
        this.mHandler.sendMessageDelayed(messageObtainMessage, 0L);
    }

    private void updateWidget(long j) {
        if (this.mHandler.hasMessages(10010)) {
            this.mHandler.removeMessages(10010);
        }
        this.mHandler.sendEmptyMessageDelayed(10010, j);
    }

    @Override // c.a.a.b
    public void BT_Card_Slides_up() {
        if (com.byd.bluetoothcall.c.a.F().J() || com.byd.bluetoothcall.c.a.F().b0() || this.isXCallMode) {
            return;
        }
        this.mHandler.sendEmptyMessage(1005);
    }

    @Override // c.a.a.b
    public void BlueSwitch(int i) {
    }

    @Override // c.a.a.b
    public void CallState_Change(List<BtCallInfo> list) {
        boolean zCompareList = compareList(this.mCallList, list);
        Log.d(this.TAG, "CallState_Change === FLAG == " + zCompareList);
        if (zCompareList) {
            return;
        }
        this.mCallList.clear();
        for (int i = 0; i < list.size(); i++) {
            a aVar = new a();
            aVar.g(list.get(i).k());
            aVar.i(list.get(i).l());
            aVar.j(list.get(i).m());
            aVar.k(list.get(i).n());
            aVar.f(list.get(i).j());
            aVar.l(list.get(i).o());
            aVar.h(list.get(i).p());
            this.mCallList.add(aVar);
        }
        a aVar2 = null;
        if (this.mCallList.size() <= 0) {
            this.isSendBTCall2YB = false;
            com.byd.bluetoothcall.view.ui.c.l(this.mContext).m();
            com.byd.bluetoothcall.view.ui.b.l(this.mContext).m();
            BYDAutoSettingDevice.getInstance(this.mContext).setCallState(2);
            this.yb_BT_call = null;
            sendCallInfo2Instrument();
            com.byd.bluetoothcall.c.a.F().t0(0);
            if (com.byd.bluetoothcall.view.d.b.g(this.mContext).i()) {
                com.byd.bluetoothcall.view.d.b.g(this.mContext).a();
            }
            if (this.mAccState == 2 && com.byd.bluetoothcall.c.a.F().A()) {
                com.byd.bluetoothcall.c.a.F().n0(false);
            }
            if (com.byd.bluetoothcall.view.d.c.g(this.mContext).h()) {
                com.byd.bluetoothcall.view.d.c.g(this.mContext).a();
            }
            if (!com.byd.bluetoothcall.c.a.F().Y() || com.byd.bluetoothcall.c.a.F().Q().size() <= 0) {
                return;
            }
            Log.d(this.TAG, "蓝牙挂断接听CP电话");
            com.byd.bluetoothcall.c.a.F().v0(false);
            for (int i2 = 0; i2 < com.byd.bluetoothcall.c.a.F().Q().size(); i2++) {
                try {
                    if (com.byd.bluetoothcall.c.a.F().Q().get(i2).getStatus() == 2) {
                        com.byd.bluetoothcall.c.a.F().C().acceptCall(0, com.byd.bluetoothcall.c.a.F().Q().get(i2).getCallUuid());
                        return;
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            return;
        }
        this.mHandler.removeMessages(10015);
        BYDAutoSettingDevice.getInstance(this.mContext).setCallState(1);
        BydLogHelper.logD(this.TAG, "mCPCallList.size() === " + com.byd.bluetoothcall.c.a.F().Q().size() + this.isSendBTCall2YB);
        if (com.byd.bluetoothcall.c.a.F().Q().size() > 0) {
            if (!this.isSendBTCall2YB) {
                com.byd.bluetoothcall.view.ui.b.l(this.mContext).m();
            }
            if (this.mCallList.size() <= 1) {
                Log.d("DANDAN", "mCallList.get(0).getState() = " + this.mCallList.get(0).c());
                if (this.mCallList.get(0).c() == 4 || this.mCallList.get(0).c() == 5) {
                    this.isSendBTCall2YB = true;
                    aVar2 = this.mCallList.get(0);
                } else if (this.mCallList.get(0).c() == 2 || this.mCallList.get(0).c() == 3 || this.mCallList.get(0).c() == 0) {
                    Log.d("KJHGD", "=====================================================================");
                    this.isSendBTCall2YB = false;
                    c.a.b.b.f0().l(StateConstant$SCO_STATE.CHANNEL_PHONE);
                }
            } else if (this.mCallList.get(0).c() == 4 || this.mCallList.get(0).c() == 5) {
                this.isSendBTCall2YB = true;
                aVar2 = this.mCallList.get(0);
            } else if (this.mCallList.get(1).c() == 4 || this.mCallList.get(1).c() == 5) {
                this.isSendBTCall2YB = true;
                aVar2 = this.mCallList.get(1);
            }
            if (aVar2 != null) {
                com.byd.bluetoothcall.view.d.b.g(this.mContext).f();
                com.byd.bluetoothcall.view.d.b.g(this.mContext).h(1, getString(R.string.cp_bt_call_in), aVar2);
                this.mHandler.sendEmptyMessage(10013);
            } else {
                this.mHandler.sendEmptyMessage(10005);
            }
        } else {
            this.isSendBTCall2YB = true;
            com.byd.bluetoothcall.c.a.F().e();
            if (!com.byd.bluetoothcall.view.ui.b.l(this.mContext).o() && !com.byd.bluetoothcall.view.ui.c.l(this.mContext).o()) {
                com.byd.bluetoothcall.c.a.F().c();
                ChangeWindowMode(50);
            }
            if (this.isXCallMode) {
                ChangeAudio(200);
            }
        }
        if (this.mCallList.size() <= 1) {
            this.isExsitThidCall = false;
            com.byd.bluetoothcall.view.ui.c.l(this.mContext).e(this.isExsitThidCall);
            com.byd.bluetoothcall.view.ui.b.l(this.mContext).c(this.isExsitThidCall);
            updateCallState(this.mCallList.get(0), this.mCallList.get(0).c());
            if (this.mCallList.get(0).c() == 0 && com.byd.bluetoothcall.c.a.F().G()) {
                Log.d("QNMD", "calling --------Manage.getInstance().getMicMute() = " + com.byd.bluetoothcall.c.a.F().G());
                this.mHandler.postDelayed(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.14
                    @Override // java.lang.Runnable
                    public void run() {
                        BYDAutoAudioDevice.getInstance(BYD_BlueToothService.this.mContext).setBtCall_MicMute(1);
                    }
                }, 150L);
                return;
            }
            return;
        }
        this.isExsitThidCall = true;
        if (this.mCallList.get(0).c() == 0 && this.mCallList.get(1).c() == 1) {
            Collections.swap(this.mCallList, 0, 1);
        }
        String strA = this.mCallList.get(0).a();
        if (this.mCallList.get(0).e()) {
            strA = getResources().getString(R.string.networkcall);
        } else if (TextUtils.isEmpty(this.mCallList.get(0).b())) {
            strA = getResources().getString(R.string.unknown_number);
        }
        com.byd.bluetoothcall.view.ui.c.l(this.mContext).e(this.isExsitThidCall);
        com.byd.bluetoothcall.view.ui.b.l(this.mContext).c(this.isExsitThidCall);
        com.byd.bluetoothcall.view.ui.c.l(this.mContext).c(com.byd.bluetoothcall.f.e.a(strA));
        com.byd.bluetoothcall.view.ui.b.l(this.mContext).a(com.byd.bluetoothcall.f.e.a(strA));
        updateThirdState(this.mCallList);
        updateCallState(this.mCallList.get(1), this.mCallList.get(1).c());
    }

    @Override // c.a.a.b
    public void Call_Activity_is_Top(boolean z) {
        Context context;
        updateWidget(50L);
        if (!z) {
            if (this.autoSceneKeyListener == null || (context = this.mContext) == null) {
                return;
            }
            BYDAutoKeyDevice.getInstance(context).unregisterSceneListener(this.autoSceneKeyListener, 101);
            return;
        }
        if ((!com.byd.bluetoothcall.c.a.F().b0() || com.byd.bluetoothcall.c.a.F().c0()) && this.autoSceneKeyListener != null && this.mContext != null && c.a.b.b.f0().n0() == 2) {
            BYDAutoKeyDevice.getInstance(this.mContext).registerSceneListener(this.autoSceneKeyListener, 101);
        }
        Intent intent = new Intent("com.byd.card.open.FloatCard");
        intent.putExtra("package", "com.byd.bluetoothcall");
        sendBroadcast(intent);
    }

    @Override // c.a.a.b
    public void Database_contact_already_exists(boolean z) {
        this.mHandler.sendEmptyMessageDelayed(10016, 800L);
    }

    @Override // c.a.a.b
    public void InvalidCallNumber(String str) {
        ToastUtils.show(this.mContext, R.string.btcall_invalid_num);
    }

    @Override // c.a.a.b
    public void NewTopTaskInfo(String str) {
        List<a> list;
        int i = 0;
        while (true) {
            String[] strArr = com.byd.bluetoothcall.f.c.f3274a;
            if (i >= strArr.length) {
                return;
            }
            if (!TextUtils.isEmpty(str) && str.equals(strArr[i]) && (list = this.mCallList) != null && list.size() > 0) {
                ChangeWindowMode(50);
            }
            i++;
        }
    }

    @Override // c.a.a.e
    public void SyncBookTotal(int i) {
        com.byd.bluetoothcall.c.a.F().D0(i);
    }

    @Override // c.a.a.e
    public void SyncCallLogOne_data(CallLogInfo callLogInfo) {
        if (callLogInfo != null) {
            com.byd.bluetoothcall.e.a.g.execute(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.21
                @Override // java.lang.Runnable
                public void run() {
                    com.byd.bluetoothcall.c.a.F().g(false);
                }
            });
            if (callLogInfo.r().equals("MISSED")) {
                if (isBT_Top() && com.byd.bluetoothcall.c.a.F().R() == 2) {
                    com.byd.bluetoothcall.c.a.F().u0(0);
                    UpdateUnReadMissedCalls();
                } else {
                    com.byd.bluetoothcall.c.a.F().u0(com.byd.bluetoothcall.c.a.F().I() + 1);
                    UpdateUnReadMissedCalls();
                }
            }
        }
    }

    @Override // c.a.a.e
    public void SyncCallLog_data(List<CallLogInfo> list) {
        com.byd.bluetoothcall.c.a.F().u0(0);
        UpdateUnReadMissedCalls();
        com.byd.bluetoothcall.e.a.g.execute(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.20
            @Override // java.lang.Runnable
            public void run() {
                com.byd.bluetoothcall.c.a.F().g(true);
            }
        });
    }

    @Override // c.a.a.e
    public void SyncPhoeBook_data(List<ContactsInfo> list) {
        com.byd.bluetoothcall.e.a.g.execute(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.19
            @Override // java.lang.Runnable
            public void run() {
                com.byd.bluetoothcall.c.a.F().h();
            }
        });
    }

    @Override // c.a.a.e
    public void SyncProgress(ContactsInfo contactsInfo, int i, int i2) {
        com.byd.bluetoothcall.c.a.F().C0(i, i2);
    }

    @Override // c.a.a.e
    public void SyncState(Sync$SyncType sync$SyncType, Sync$SyncState sync$SyncState) {
        BydLogHelper.logD(this.TAG, "syncType = " + sync$SyncType + " = syncState = " + sync$SyncState);
        if (sync$SyncType != Sync$SyncType.PHONE_BOOK) {
            if (sync$SyncType == Sync$SyncType.ALL_OF_CALL_LOG) {
                com.byd.bluetoothcall.c.a.F().I0(sync$SyncState);
                return;
            }
            return;
        }
        com.byd.bluetoothcall.c.a.F().y0(sync$SyncState);
        if (sync$SyncState == Sync$SyncState.SYNC_START) {
            com.byd.bluetoothcall.c.a.F().D0(-1);
            com.byd.bluetoothcall.c.a.F().C0(com.byd.bluetoothcall.c.a.F().P(), 0);
            return;
        }
        if (sync$SyncState == Sync$SyncState.SYNC_END) {
            if (com.byd.bluetoothcall.c.a.F().y() == 1 || com.byd.bluetoothcall.c.a.F().e0()) {
                return;
            }
            this.mHandler.post(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.17
                @Override // java.lang.Runnable
                public void run() {
                    if (com.byd.bluetoothcall.c.a.F().P() < 1) {
                        ToastUtils.show(BYD_BlueToothService.this.mContext, BYD_BlueToothService.this.getString(R.string.bt_sync_failed), R.drawable.download_faild_icon);
                    } else {
                        ToastUtils.show(BYD_BlueToothService.this.mContext, BYD_BlueToothService.this.getString(R.string.bt_sync_succeed), R.drawable.download_succeed_icon);
                    }
                }
            });
            return;
        }
        if (sync$SyncState == Sync$SyncState.SYNC_FAILD) {
            com.byd.bluetoothcall.c.a.F().D0(-1);
            if (com.byd.bluetoothcall.c.a.F().y() == 1 || com.byd.bluetoothcall.c.a.F().e0()) {
                return;
            }
            this.mHandler.post(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.18
                @Override // java.lang.Runnable
                public void run() {
                    ToastUtils.show(BYD_BlueToothService.this.mContext, BYD_BlueToothService.this.getString(R.string.bt_sync_failed), R.drawable.download_faild_icon);
                }
            });
        }
    }

    @Override // c.a.a.b
    public void Update_Call_Channel(StateConstant$SCO_STATE stateConstant$SCO_STATE) {
        Log.d(this.TAG, "Update_Call_Channel == " + stateConstant$SCO_STATE);
        if (stateConstant$SCO_STATE == StateConstant$SCO_STATE.CHANNEL_BT) {
            Log.d("DANDAN", "Update_Call_Channel =CHANNEL_BT = getCallMuteState() == " + BYDAutoAudioDevice.getInstance(this.mContext).getCallMuteState());
            if (BYDAutoAudioDevice.getInstance(this.mContext).getCallMuteState() != 0) {
                this.mHandler.postDelayed(new Runnable() { // from class: com.byd.bluetoothcall.service.BYD_BlueToothService.16
                    @Override // java.lang.Runnable
                    public void run() {
                        BYDAutoAudioDevice.getInstance(BYD_BlueToothService.this.mContext).setCallMuteState(0);
                    }
                }, 50L);
            }
        }
        if (this.mCallList.size() > 0) {
            com.byd.bluetoothcall.view.ui.c.l(this.mContext).d(stateConstant$SCO_STATE);
            com.byd.bluetoothcall.view.ui.b.l(this.mContext).b(stateConstant$SCO_STATE);
        }
    }

    @Override // c.a.a.b
    public void Update_Call_Time(BtCallInfo btCallInfo) {
        if (this.mCallList.size() <= 0 || btCallInfo == null) {
            return;
        }
        if (this.isXCallMode) {
            ChangeAudio(20);
        }
        if (this.mCallList.size() <= 1) {
            if (this.mCallList.size() == 1) {
                if (this.mCallList.get(0).c() == 1) {
                    com.byd.bluetoothcall.view.ui.c.l(this.mContext).v(getResources().getString(R.string.call_hold));
                    com.byd.bluetoothcall.view.ui.b.l(this.mContext).w(getResources().getString(R.string.call_hold));
                } else if (this.mCallList.get(0).c() == 0) {
                    com.byd.bluetoothcall.view.ui.c.l(this.mContext).v(f.v(btCallInfo.o()));
                    com.byd.bluetoothcall.view.ui.b.l(this.mContext).w(f.v(btCallInfo.o()));
                }
                a aVar = this.yb_BT_call;
                if (aVar != null) {
                    aVar.l(btCallInfo.o());
                    sendCallInfo2Instrument();
                    return;
                }
                return;
            }
            return;
        }
        for (int i = 0; i < this.mCallList.size(); i++) {
            if (this.mCallList.get(i).c() == 0) {
                if (i == 0) {
                    if (this.mCallList.get(1).c() == 1) {
                        com.byd.bluetoothcall.view.ui.c.l(this.mContext).v(f.v(btCallInfo.o()));
                        com.byd.bluetoothcall.view.ui.b.l(this.mContext).w(f.v(btCallInfo.o()));
                        a aVar2 = this.yb_BT_call;
                        if (aVar2 != null) {
                            aVar2.l(btCallInfo.o());
                            sendCallInfo2Instrument();
                        }
                        com.byd.bluetoothcall.view.ui.c.l(this.mContext).x(getResources().getString(R.string.call_hold));
                        com.byd.bluetoothcall.view.ui.b.l(this.mContext).A(getResources().getString(R.string.call_hold));
                    } else if (this.mCallList.get(1).c() == 5) {
                        com.byd.bluetoothcall.view.ui.c.l(this.mContext).x(f.v(btCallInfo.o()));
                        com.byd.bluetoothcall.view.ui.b.l(this.mContext).A(f.v(btCallInfo.o()));
                    }
                } else if (i == 1) {
                    if (this.mCallList.get(0).c() == 1) {
                        com.byd.bluetoothcall.view.ui.c.l(this.mContext).x(getResources().getString(R.string.call_hold));
                        com.byd.bluetoothcall.view.ui.b.l(this.mContext).A(getResources().getString(R.string.call_hold));
                        com.byd.bluetoothcall.view.ui.c.l(this.mContext).v(f.v(btCallInfo.o()));
                        com.byd.bluetoothcall.view.ui.b.l(this.mContext).w(f.v(btCallInfo.o()));
                        a aVar3 = this.yb_BT_call;
                        if (aVar3 != null) {
                            aVar3.l(btCallInfo.o());
                            sendCallInfo2Instrument();
                        }
                    } else if (this.mCallList.get(0).c() == 5) {
                        com.byd.bluetoothcall.view.ui.c.l(this.mContext).x(f.v(btCallInfo.o()));
                        com.byd.bluetoothcall.view.ui.b.l(this.mContext).A(f.v(btCallInfo.o()));
                    }
                }
            } else if (this.mCallList.get(i).c() == 1) {
                if (i == 0) {
                    if (this.mCallList.get(1).c() == 3) {
                        com.byd.bluetoothcall.view.ui.c.l(this.mContext).x(getResources().getString(R.string.call_hold));
                        com.byd.bluetoothcall.view.ui.b.l(this.mContext).A(getResources().getString(R.string.call_hold));
                    }
                } else if (i == 1 && this.mCallList.get(0).c() == 3) {
                    com.byd.bluetoothcall.view.ui.c.l(this.mContext).x(getResources().getString(R.string.call_hold));
                    com.byd.bluetoothcall.view.ui.b.l(this.mContext).A(getResources().getString(R.string.call_hold));
                }
            }
        }
    }

    public void Update_Third_Time(String str, long j) {
    }

    @Override // c.a.a.b
    public void WidgetCardShow(boolean z) {
        if (this.mWidgetCardShow != z) {
            this.mWidgetCardShow = z;
        }
    }

    @Override // c.a.a.b
    public void WidgetKeyBoardShow(boolean z) {
    }

    @Override // c.a.a.c
    public void connectStateChange(BluetoothDevice bluetoothDevice, int i) {
        Context context;
        Context context2;
        Log.d("KJHGFS", "connectStateChange =======");
        com.byd.bluetoothcall.c.a.F().k(bluetoothDevice != null && i == 2);
        updateWidget(500L);
        if (bluetoothDevice == null || i != 2) {
            if (i == 0) {
                com.byd.bluetoothcall.c.a.F().D0(-1);
                com.byd.bluetoothcall.c.a.F().k(false);
                if (this.autoSceneKeyListener != null && (context = this.mContext) != null) {
                    BYDAutoKeyDevice.getInstance(context).unregisterSceneListener(this.autoSceneKeyListener, 101);
                }
                BluetoothDevice bluetoothDeviceP0 = c.a.b.b.f0().p0();
                if (bluetoothDeviceP0 != null) {
                    Log.d(this.TAG, "====HFP 断开了====" + c.a.b.a.K().a(bluetoothDevice));
                    this.mHandler.removeMessages(10016);
                    if (c.a.b.a.K().a(bluetoothDeviceP0)) {
                        return;
                    }
                    String name = bluetoothDeviceP0.getName();
                    if (TextUtils.isEmpty(name)) {
                        return;
                    }
                    if (name.length() > 31) {
                        name = f.f(name, 30) + "···";
                    }
                    this.mHandler.removeMessages(10009);
                    Message messageObtainMessage = this.mHandler.obtainMessage();
                    messageObtainMessage.what = 10009;
                    messageObtainMessage.obj = String.format(getResources().getString(R.string.bluetooth_disconnected), name);
                    this.mHandler.sendMessageDelayed(messageObtainMessage, 300L);
                    c.a.b.b.f0().S();
                    return;
                }
                return;
            }
            return;
        }
        if (com.byd.bluetoothcall.c.a.F().X() && ((!com.byd.bluetoothcall.c.a.F().b0() || com.byd.bluetoothcall.c.a.F().c0()) && this.autoSceneKeyListener != null && (context2 = this.mContext) != null)) {
            BYDAutoKeyDevice.getInstance(context2).registerSceneListener(this.autoSceneKeyListener, 101);
        }
        Log.d(this.TAG, "isFirstAcc_On = " + this.isFirstAcc_On);
        if (this.isFirstAcc_On) {
            this.mHandler.removeMessages(10014);
            this.mHandler.sendEmptyMessageDelayed(10014, 0L);
            this.isFirstAcc_On = false;
        } else {
            com.byd.bluetoothcall.c.a.F().n0(false);
        }
        Log.d(this.TAG, "====HFP 连接了====" + c.a.b.a.K().a(bluetoothDevice));
        if (c.a.b.a.K().a(bluetoothDevice)) {
            return;
        }
        String name2 = bluetoothDevice.getName();
        if (TextUtils.isEmpty(name2)) {
            return;
        }
        if (name2.length() > 31) {
            name2 = f.f(name2, 30) + "···";
        }
        this.mHandler.removeMessages(10009);
        Message messageObtainMessage2 = this.mHandler.obtainMessage();
        messageObtainMessage2.what = 10009;
        messageObtainMessage2.obj = String.format(getResources().getString(R.string.bluetooth_connect_succeed), name2);
        this.mHandler.sendMessageDelayed(messageObtainMessage2, 500L);
    }

    @Override // c.a.a.b
    public void endCall(String str) {
        if (com.byd.bluetoothcall.c.a.F().Q().size() > 0) {
            return;
        }
        if (this.mCallList.size() == 1) {
            com.byd.bluetoothcall.view.ui.b.l(this.mContext).j();
            com.byd.bluetoothcall.view.ui.c.l(this.mContext).j();
        } else if (this.mCallList.size() == 2 && str.equals(this.mCallList.get(0).b())) {
            com.byd.bluetoothcall.view.ui.b.l(this.mContext).j();
            com.byd.bluetoothcall.view.ui.c.l(this.mContext).j();
        }
    }

    @Override // c.a.a.c
    public void initFinished() {
        Log.d(this.TAG, "initFinished ==============");
        initeCarPlay();
        initeAndroidAuto();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateWidget(0L);
        if ((configuration.uiMode & 48) == 32) {
            com.byd.bluetoothcall.c.a.F().w0(true);
        } else {
            com.byd.bluetoothcall.c.a.F().w0(false);
        }
        com.byd.bluetoothcall.c.a.F().x0(configuration.orientation);
        updateCallName();
        if (com.byd.bluetoothcall.view.d.a.f().i()) {
            com.byd.bluetoothcall.view.d.a.f().j(configuration);
        }
        if (com.byd.bluetoothcall.view.d.c.g(this.mContext).h()) {
            com.byd.bluetoothcall.view.d.c.g(this.mContext).i(configuration);
        }
        if (com.byd.bluetoothcall.view.d.b.g(this.mContext).i()) {
            com.byd.bluetoothcall.view.d.b.g(this.mContext).j(configuration);
        }
        if (this.mCallList.size() > 0) {
            com.byd.bluetoothcall.view.ui.b.l(this.mContext).r(configuration);
            com.byd.bluetoothcall.view.ui.c.l(this.mContext).q(configuration);
        } else {
            com.byd.bluetoothcall.view.ui.b.l(this.mContext).t();
            com.byd.bluetoothcall.view.ui.c.l(this.mContext).s();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logD("DANDAN", "BYD_BlueToothService ==onCreate ==");
        this.mContext = this;
        initService();
        registerSplitBroadcast();
    }

    @Override // android.app.Service
    public void onDestroy() {
        IAndroidAutoAppManager iAndroidAutoAppManager;
        ICarPlayAppManager iCarPlayAppManager;
        super.onDestroy();
        c.a.b.b.f0().O0(this);
        c.a.b.b.f0().N0(this);
        c.a.b.c.t().B(this);
        c.a.b.a.K().W(this);
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mCPServiceConenct && (iCarPlayAppManager = this.mCarPlayAppManager) != null) {
            iCarPlayAppManager.unregisterConnectionListener(this.mCPServiceConnectionListener);
        }
        if (this.mAAServiceConenct && (iAndroidAutoAppManager = this.mAndroidAutoAppManager) != null) {
            iAndroidAutoAppManager.unregisterConnectionListener(this.mAAServiceConnectionListener);
        }
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(this.mSplitModeReceiver);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 0;
        }
        String stringExtra = intent.getStringExtra("command");
        BydLogHelper.logD("DANDAN", "onStartCommand == " + stringExtra);
        if (stringExtra == null || stringExtra.equals("BOOT_COMPLETED") || !stringExtra.equals("BootCompleteReceiver")) {
            return 0;
        }
        Log.d(this.TAG, "BootCompleteReceiver,  ==  HfpBluetoothManage.getInstance().isCalling()");
        initeCarPlay();
        initeAndroidAuto();
        return 0;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // c.a.a.d
    public void updateA2dpConnectState(BluetoothDevice bluetoothDevice, int i) {
    }

    @Override // c.a.a.d
    public void updateA2dpConnectStateShowToast(BluetoothDevice bluetoothDevice, int i) {
        updateWidget(500L);
        if (bluetoothDevice != null && i == 2) {
            Log.d(this.TAG, "====A2DP 连接了====" + c.a.b.b.f0().C0(bluetoothDevice));
            if (c.a.b.b.f0().C0(bluetoothDevice)) {
                return;
            }
            String name = bluetoothDevice.getName();
            if (TextUtils.isEmpty(name)) {
                return;
            }
            if (name.length() > 31) {
                name = f.f(name, 30) + "···";
            }
            this.mHandler.removeMessages(10009);
            Message messageObtainMessage = this.mHandler.obtainMessage();
            messageObtainMessage.what = 10009;
            messageObtainMessage.obj = String.format(getResources().getString(R.string.bluetooth_connect_succeed), name);
            this.mHandler.sendMessageDelayed(messageObtainMessage, 300L);
            return;
        }
        if (bluetoothDevice == null || i != 0) {
            return;
        }
        Log.d(this.TAG, "====A2DP 断开了====" + c.a.b.b.f0().C0(bluetoothDevice));
        if (c.a.b.b.f0().C0(bluetoothDevice)) {
            return;
        }
        String name2 = bluetoothDevice.getName();
        if (TextUtils.isEmpty(name2)) {
            return;
        }
        if (name2.length() > 31) {
            name2 = f.f(name2, 30) + "···";
        }
        this.mHandler.removeMessages(10009);
        Message messageObtainMessage2 = this.mHandler.obtainMessage();
        messageObtainMessage2.what = 10009;
        messageObtainMessage2.obj = String.format(getResources().getString(R.string.bluetooth_disconnected), name2);
        this.mHandler.sendMessageDelayed(messageObtainMessage2, 300L);
    }

    @Override // c.a.a.d
    public void updateBattery(int i) {
    }

    @Override // c.a.a.d
    public void updateCurrentTime(long j) {
    }

    @Override // c.a.a.d
    public void updatePlayState(int i) {
    }

    @Override // c.a.a.d
    public void updateTitle(String str, String str2, String str3, String str4) {
    }

    @Override // c.a.a.d
    public void updateTotalTime(long j) {
    }

    @Override // c.a.a.c
    public void update_BatLevel(int i, BluetoothDevice bluetoothDevice) {
    }
}
