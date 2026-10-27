package com.engine.tbox.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.ReservationCharging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/* JADX INFO: loaded from: classes.dex */
public class BroadCastUtils {
    public static final String APP_VERSION_CODE = "app_version_code";
    public static final String AUTH_POLICY_STATUS = "status";
    public static final String AUTH_STATE = "auth_state";
    public static final String BIND_ACK = "bind_ack";
    public static final String BIND_CLOUD_INFO_ACK_ACTION = "android.intent.action.Server_Register_Bind_Info_Ack";
    public static final String BIND_CLOUD_INFO_ACTION = "android.intent.action.Server_Register_Bind_Info";
    public static final String BIND_CLOUD_SERVICE_REQ_ACTION = "android.intent.action.Server_Register_Bind_Req";
    public static final String BIND_CLOUD_SERVICE_RSP_ACTION = "android.intent.action.Server_Register_Bind_Res";
    public static final String BIND_CONTENT = "bind_content";
    public static final String BIND_STATUS = "bind_status";
    private static final byte BYTE1 = 1;
    private static final byte BYTE2 = 2;
    private static final byte BYTE3 = 4;
    private static final byte BYTE4 = 8;
    private static final byte BYTE5 = 16;
    private static final byte BYTE6 = 32;
    public static final String CHARGE_CMD = "charge_cmd";
    public static final String CHARGE_MODE = "charge_mode";
    public static final String CHARGE_UPLOAD_ACTION = "com.byd.smartcharge.charge_upload";
    public static final String CHEAP_TIME_END_HOUR = "time_eprice_end_hour";
    public static final String CHEAP_TIME_END_MIN = "time_eprice_end_min";
    public static final String CHEAP_TIME_START_HOUR = "time_eprice_start_hour";
    public static final String CHEAP_TIME_START_MIN = "time_eprice_start_min";
    public static final String CLOUD_MANAGER_14_ACTION = "com.byd.cloudmanager.broadcast.14";
    public static final String CLOUD_MANAGER_RECV_ACTION = "com.byd.cloudmanager.receiver";
    private static final String DATA_SCHEME = "file";
    public static final String GET_PLANE_ACTION = "com.byd.smartcharge.get.plan";
    public static final String GO_OUT_HOUR = "time_goout_hour_min";
    public static final String GO_OUT_MIN = "time_goout_min";
    public static final String GO_OUT_REPEAT_MODE = "goout_repeat_mode";
    public static final String GO_OUT_SWITCH = "goout_switch";
    public static final String IVI_POLICY_VERSION = "privacy_policy_version";
    public static final String OP_POLICY_DATE = "effective_date";
    public static final String PLANE_ACTION = "com.byd.smartcharge.plan";
    public static final String PLANE_CHARGE_ACTION = "com.byd.smartcharge.plan_change";
    public static final String PLANE_CHARGE_RES_ACTION = "com.byd.smartcharge.plan_change_res";
    public static final String PLANE_RES_ACTION = "com.byd.smartcharge.plan_res";
    public static final String PLANE_SWITCH = "plan_switch";
    public static final String PLANE_UPLOAD_ACTION = "com.byd.smartcharge.plan_upload";
    public static final String PLAN_USE_TYPE = "plan_use_type";
    public static final String PRIVATE_POLICY_ACTION = "android.intent.action.PRIVACY_PAGE_OPERATION_CHANGE";
    public static final String REPEAT_MODE = "repeat_mode";
    public static final String RES = "res";
    public static final String RESULT = "result";
    private static final byte SUNDAY = 64;
    private static final String TAG = "TBoxBroadcastUtils";
    public static final String TBOX_AUTH_STATE_ACTION = "com.byd.tbox.auth_state";
    public static final String TCP_CLOUD_SEVER_STATUS_ACTION = "com.byd.tcp.cloud.server.status";
    private static final String TEST = "test";
    public static final String TEST_ACTION = "com.ts.test";
    public static final String TIME_END_HOUR = "time_end_hour";
    public static final String TIME_END_MIN = "time_end_min";
    public static final String TIME_START_HOUR = "time_start_hour";
    public static final String TIME_START_MIN = "time_start_min";
    public static final String TIME_ZONE = "time_zone";
    public static final String USER_POLICY_OPERATION = "user_operation";
    private static final int V2G_539_03 = 5;
    private static final int V2G_539_04 = 7;
    private static final int V2G_540_E1 = 6;
    private static final String V2G_IN_CMD = "cmd";
    private static final String V2G_KEY = "json";
    public static final String VERSION_UPLOAD_ACTION = "com.byd.smartcharge.app.version_upload";
    private Context mContext;
    private BroadcastInterface mInterface;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.engine.tbox.utils.BroadCastUtils.1
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            byte b = 0;
            if (action == null) {
                LogUtils.d(BroadCastUtils.TAG, "action is null", new Object[0]);
            }
            LogUtils.i(BroadCastUtils.TAG, "on Receiver:" + intent + " action:" + action, new Object[0]);
            switch (action.hashCode()) {
                case -1665311200:
                    b = !action.equals("android.intent.action.MEDIA_REMOVED") ? (byte) -1 : BroadCastUtils.BYTE4;
                    break;
                case -968966600:
                    b = !action.equals(BroadCastUtils.PLANE_UPLOAD_ACTION) ? (byte) -1 : (byte) 2;
                    break;
                case -963871873:
                    b = !action.equals("android.intent.action.MEDIA_UNMOUNTED") ? (byte) -1 : (byte) 7;
                    break;
                case -962802743:
                    if (!action.equals(BroadCastUtils.PLANE_RES_ACTION)) {
                        b = -1;
                    }
                    break;
                case -900435968:
                    b = !action.equals(BroadCastUtils.GET_PLANE_ACTION) ? (byte) -1 : BroadCastUtils.BYTE3;
                    break;
                case -625887599:
                    b = !action.equals("android.intent.action.MEDIA_EJECT") ? (byte) -1 : (byte) 9;
                    break;
                case -464780004:
                    b = !action.equals(BroadCastUtils.PRIVATE_POLICY_ACTION) ? (byte) -1 : (byte) 6;
                    break;
                case 187770676:
                    b = !action.equals(BroadCastUtils.TEST_ACTION) ? (byte) -1 : (byte) 10;
                    break;
                case 308901761:
                    b = !action.equals(BroadCastUtils.BIND_CLOUD_SERVICE_REQ_ACTION) ? (byte) -1 : (byte) 11;
                    break;
                case 335811892:
                    b = !action.equals(BroadCastUtils.VERSION_UPLOAD_ACTION) ? (byte) -1 : (byte) 5;
                    break;
                case 481993813:
                    b = !action.equals(BroadCastUtils.BIND_CLOUD_INFO_ACK_ACTION) ? (byte) -1 : (byte) 12;
                    break;
                case 1678632280:
                    b = !action.equals(BroadCastUtils.CLOUD_MANAGER_14_ACTION) ? (byte) -1 : (byte) 13;
                    break;
                case 2004193293:
                    b = !action.equals(BroadCastUtils.CHARGE_UPLOAD_ACTION) ? (byte) -1 : (byte) 3;
                    break;
                case 2136760392:
                    b = !action.equals(BroadCastUtils.PLANE_CHARGE_RES_ACTION) ? (byte) -1 : (byte) 1;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    BroadCastUtils.this.handlePlaneRes(intent);
                    break;
                case 1:
                    BroadCastUtils.this.handlePlaneChargeRes(intent);
                    break;
                case 2:
                    BroadCastUtils.this.handlePlaneUpload(intent);
                    break;
                case 3:
                    BroadCastUtils.this.handleChargeUpload(intent);
                    break;
                case 4:
                    BroadCastUtils.this.handleGetPlane();
                    break;
                case 5:
                    BroadCastUtils.this.handleAppVersionCode(intent);
                    break;
                case 6:
                    BroadCastUtils.this.handlePrivatePolicy(intent);
                    break;
                case 7:
                case 8:
                case 9:
                    BroadCastUtils.this.handleUsbRemove();
                    break;
                case 10:
                    BroadCastUtils.this.handleTest(intent);
                    break;
                case 11:
                    BroadCastUtils.this.handleBindCloudServiceReq();
                    break;
                case 12:
                    BroadCastUtils.this.handleCloudInfoAck(intent);
                    break;
                case 13:
                    BroadCastUtils.this.handleCloudManager14(intent);
                    break;
            }
        }
    };

    private static byte periodTransform(byte period) {
        byte sunday = (byte) (period & SUNDAY);
        byte byte1 = (byte) ((period & 1) << 5);
        byte byte2 = (byte) ((period & 2) << 3);
        byte byte3 = (byte) ((period & BYTE3) << 1);
        byte byte4 = (byte) ((period & BYTE4) >> 1);
        byte byte5 = (byte) ((period & BYTE5) >> 3);
        byte byte6 = (byte) ((period & BYTE6) >> 5);
        return (byte) (sunday | byte1 | byte2 | byte3 | byte4 | byte5 | byte6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBindCloudServiceReq() {
        LogUtils.i(TAG, "handleBindCloudServiceReq mInterface:" + this.mInterface, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onBindCloudServiceReq();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCloudInfoAck(Intent intent) {
        int ack = intent.getIntExtra(BIND_ACK, 0);
        LogUtils.i(TAG, "handleCloudInfoAck ack:" + ack + " mInterface:" + this.mInterface, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onBindInfoAck(ack);
            }
        }
    }

    private void handle539(String json) {
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onV2G539(json);
            }
        }
    }

    private void handle540(String json) {
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onV2G540(json);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCloudManager14(Intent intent) {
        String json = intent.getStringExtra(V2G_KEY);
        if (TextUtils.isEmpty(json)) {
            LogUtils.e(TAG, "error for receive CloudManager14 v2g without key:json", new Object[0]);
            return;
        }
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = (JsonObject) gson.fromJson(json, JsonObject.class);
            if (jsonObject.has(V2G_IN_CMD)) {
                int cmd = jsonObject.get(V2G_IN_CMD).getAsInt();
                if (cmd == 7 || cmd == 5) {
                    handle539(json);
                } else if (cmd == 6) {
                    handle540(json);
                } else {
                    LogUtils.d(TAG, "not supported cmd:" + cmd, new Object[0]);
                }
            }
        } catch (Exception exp) {
            LogUtils.w(TAG, "error in handleCloudManager14 with " + exp.getMessage(), new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePrivatePolicy(Intent intent) {
        String version = intent.getStringExtra(IVI_POLICY_VERSION);
        String opDate = intent.getStringExtra(OP_POLICY_DATE);
        int policy = intent.getIntExtra(AUTH_POLICY_STATUS, 0);
        int userOp = intent.getIntExtra(USER_POLICY_OPERATION, 0);
        if (version == null || opDate == null) {
            LogUtils.e(TAG, "handlePrivatePolicy version:" + version + " opDate:" + opDate, new Object[0]);
            return;
        }
        LogUtils.i(TAG, "handlePrivatePolicy mInterface:" + this.mInterface + " version:" + version + " opDate:" + opDate + " policy:" + policy + " userOp:" + userOp, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onPrivatePolicyStatusChanged(policy, userOp, version, opDate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAppVersionCode(Intent intent) {
        int code = intent.getIntExtra(APP_VERSION_CODE, 0);
        LogUtils.d(TAG, "handleAppVersionCode mInterface:" + this.mInterface + " code:" + code, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onAppVersionCode(code);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleGetPlane() {
        LogUtils.d(TAG, "handleGetPlane mInterface:" + this.mInterface, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onGetPlane();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleChargeUpload(Intent intent) {
        int charge = intent.getIntExtra(CHARGE_MODE, 1);
        LogUtils.d(TAG, "handleChargeUpload:" + charge + " mInterface:" + this.mInterface, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onChargeUpload(charge);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlaneUpload(Intent intent) {
        ReservationCharging.RcTime startTime = new ReservationCharging.RcTime();
        int hour = intent.getIntExtra(TIME_START_HOUR, 0);
        int min = intent.getIntExtra(TIME_START_MIN, 0);
        if (!startTime.setHourAndMin(hour, min)) {
            LogUtils.e(TAG, "start time error hour:" + hour + " min:" + min, new Object[0]);
        }
        ReservationCharging.RcTime endTime = new ReservationCharging.RcTime();
        int endHour = intent.getIntExtra(TIME_END_HOUR, 0);
        int endMin = intent.getIntExtra(TIME_END_MIN, 0);
        if (!endTime.setHourAndMin(endHour, endMin)) {
            LogUtils.e(TAG, "end time error hour:" + endHour + " min:" + endMin, new Object[0]);
        }
        ReservationCharging rc = new ReservationCharging();
        rc.setRsvTime(startTime, endTime);
        int repeatMode = intent.getByteExtra(REPEAT_MODE, (byte) 0);
        rc.setRsvPlanePeriod(periodTransform((byte) repeatMode));
        int planeSwitch = intent.getIntExtra(PLANE_SWITCH, 0);
        rc.setRsvPlaneSwitch((byte) planeSwitch);
        int goOutHour = intent.getIntExtra(GO_OUT_HOUR, 0);
        int goOutMin = intent.getIntExtra(GO_OUT_MIN, 0);
        ReservationCharging.RcTime goOutTime = new ReservationCharging.RcTime();
        if (!goOutTime.setHourAndMin(goOutHour, goOutMin)) {
            LogUtils.e(TAG, "go out time error goOutHour:" + goOutHour + " goOutMin:" + goOutMin, new Object[0]);
        }
        rc.setRsvOutTime(goOutTime);
        int goOutPeriod = intent.getByteExtra(GO_OUT_REPEAT_MODE, (byte) 0);
        rc.setRsvOutPeriod(periodTransform((byte) goOutPeriod));
        int goOutSwitch = intent.getIntExtra(GO_OUT_SWITCH, 0);
        rc.setRsvOutSwitch((byte) goOutSwitch);
        ReservationCharging.RcTime cheapStartTime = new ReservationCharging.RcTime();
        int cheapStartHour = intent.getIntExtra(CHEAP_TIME_START_HOUR, 0);
        int cheapStartMin = intent.getIntExtra(CHEAP_TIME_START_MIN, 0);
        if (!cheapStartTime.setHourAndMin(cheapStartHour, cheapStartMin)) {
            LogUtils.e(TAG, "cheapStartTime cheapStartHour:" + cheapStartHour + " cheapStartMin:" + cheapStartMin, new Object[0]);
        }
        ReservationCharging.RcTime cheapEndTime = new ReservationCharging.RcTime();
        int cheapEndHour = intent.getIntExtra(CHEAP_TIME_END_HOUR, 0);
        int cheapEndMin = intent.getIntExtra(CHEAP_TIME_END_MIN, 0);
        if (!cheapEndTime.setHourAndMin(cheapEndHour, cheapEndMin)) {
            LogUtils.e(TAG, "cheapEndTime cheapEndHour:" + cheapEndHour + " cheapEndMin", new Object[0]);
        }
        rc.setPreferentialTariffTimeSlot(cheapStartTime, cheapEndTime);
        int plaseUseType = intent.getIntExtra(PLAN_USE_TYPE, 0);
        rc.setPlaneSelect((byte) plaseUseType);
        String timeZone = intent.getStringExtra(TIME_ZONE);
        rc.setTimeZone(timeZone == null ? "N/A" : timeZone);
        LogUtils.i(TAG, "handlePlaneUpload:" + rc + " mInterface:" + this.mInterface, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onPlaneUpload(rc);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlaneChargeRes(Intent intent) {
        int res = intent.getIntExtra(RES, 2);
        if (isValidPlaneChargeRes(res)) {
            LogUtils.d(TAG, "handlePlaneChargeRes:" + res + " mInterface:" + this.mInterface, new Object[0]);
        } else {
            LogUtils.e(TAG, "handlePlaneChargeRes:" + res + " mInterface:" + this.mInterface, new Object[0]);
        }
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onPlaneChargeRes(res);
            }
        }
    }

    private boolean isValidPlaneChargeRes(int res) {
        if (res == 1 || res == 2) {
            return true;
        }
        return false;
    }

    private boolean isValidPlaneRes(int res) {
        if (res == 0 || res == 1 || res == 2) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlaneRes(Intent intent) {
        int res = intent.getIntExtra(RESULT, 0);
        if (isValidPlaneRes(res)) {
            LogUtils.d(TAG, "handlePlaneRes:" + res + " mInterface:" + this.mInterface, new Object[0]);
        } else {
            LogUtils.e(TAG, "handlePlaneRes:" + res + " mInterface:" + this.mInterface, new Object[0]);
        }
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onPlaneRes(res);
            }
        }
    }

    public interface BroadcastInterface {
        void onAppVersionCode(int i);

        void onBindCloudServiceReq();

        void onBindInfoAck(int i);

        void onChargeUpload(int i);

        void onGetPlane();

        void onPlaneChargeRes(int i);

        void onPlaneRes(int i);

        void onPlaneUpload(ReservationCharging reservationCharging);

        void onPrivatePolicyStatusChanged(int i, int i2, String str, String str2);

        void onTest(int i);

        void onUsbRemove();

        default void onV2G539(String json) {
        }

        default void onV2G540(String json) {
        }
    }

    public void registerBroadCast(Context context, BroadcastInterface broadcastInterface) {
        this.mInterface = broadcastInterface;
        this.mContext = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PLANE_RES_ACTION);
        intentFilter.addAction(PLANE_CHARGE_RES_ACTION);
        intentFilter.addAction(GET_PLANE_ACTION);
        intentFilter.addAction(PLANE_UPLOAD_ACTION);
        intentFilter.addAction(CHARGE_UPLOAD_ACTION);
        intentFilter.addAction(VERSION_UPLOAD_ACTION);
        intentFilter.addAction(PRIVATE_POLICY_ACTION);
        intentFilter.addAction(TEST_ACTION);
        intentFilter.addAction(BIND_CLOUD_SERVICE_REQ_ACTION);
        intentFilter.addAction(BIND_CLOUD_INFO_ACK_ACTION);
        context.registerReceiver(this.mReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter2.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter2.addDataScheme(DATA_SCHEME);
        context.registerReceiver(this.mReceiver, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(CLOUD_MANAGER_RECV_ACTION);
        intentFilter3.addAction(CLOUD_MANAGER_14_ACTION);
        context.registerReceiver(this.mReceiver, intentFilter3);
    }

    public void destroyBroadCast() {
        synchronized (this) {
            this.mInterface = null;
        }
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public void sendPlaneBroadCast(ReservationCharging rc) {
        LogUtils.i(TAG, "sendPlaneBroadCast:" + rc, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(PLANE_ACTION);
        rc.setRsvPlanePeriod(periodTransform(rc.getRsvPlanePeriod()));
        rc.setRsvOutPeriod(periodTransform(rc.getRsvOutPeriod()));
        sendIntentRcBroadCast(intent, rc);
    }

    public void sendPlaneBroadCast(Intent intent) {
        LogUtils.i(TAG, "sendPlaneBroadCast", new Object[0]);
        intent.setAction(PLANE_ACTION);
        this.mContext.sendBroadcast(intent);
    }

    public void sendPlaneCharge(int chargeCmd) {
        LogUtils.i(TAG, "sendPlaneChargeRes:" + chargeCmd, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(PLANE_CHARGE_ACTION);
        intent.putExtra(CHARGE_CMD, chargeCmd);
        this.mContext.sendBroadcast(intent);
    }

    public boolean sendPlaneChargeUploadBroadCast(ReservationCharging rc) {
        LogUtils.i(TAG, "sendPlaneChargeUploadBroadCast:" + rc, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(PLANE_UPLOAD_ACTION);
        sendIntentRcBroadCast(intent, rc);
        return true;
    }

    public boolean sendPlaneChargeUpload(int res) {
        LogUtils.i(TAG, "sendPlaneChargeUpload:" + res, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(CHARGE_UPLOAD_ACTION);
        intent.putExtra(CHARGE_MODE, res);
        this.mContext.sendBroadcast(intent);
        return true;
    }

    private void sendIntentRcBroadCast(Intent intent, ReservationCharging rc) {
        LogUtils.i(TAG, "sendIntentRcBroadCast:" + rc, new Object[0]);
        intent.putExtra(TIME_START_HOUR, (int) rc.getRsvStartHour());
        intent.putExtra(TIME_START_MIN, (int) rc.getRsvStartMin());
        intent.putExtra(REPEAT_MODE, rc.getRsvPlanePeriod());
        intent.putExtra(PLANE_SWITCH, (int) rc.getRsvPlaneSwitch());
        intent.putExtra(TIME_END_HOUR, (int) rc.getRsvEndHour());
        intent.putExtra(TIME_END_MIN, (int) rc.getRsvEndMin());
        intent.putExtra(GO_OUT_HOUR, (int) rc.getRsvOutHour());
        intent.putExtra(GO_OUT_MIN, (int) rc.getRsvOutMin());
        intent.putExtra(GO_OUT_REPEAT_MODE, rc.getRsvOutPeriod());
        intent.putExtra(GO_OUT_SWITCH, (int) rc.getRsvOutSwitch());
        intent.putExtra(CHEAP_TIME_START_HOUR, (int) rc.getPreferentialTariffStartHour());
        intent.putExtra(CHEAP_TIME_START_MIN, (int) rc.getPreferentialTariffStartMin());
        intent.putExtra(CHEAP_TIME_END_HOUR, (int) rc.getPreferentialTariffEndHour());
        intent.putExtra(CHEAP_TIME_END_MIN, (int) rc.getPreferentialTariffEndMin());
        intent.putExtra(PLAN_USE_TYPE, (int) rc.getPlaneSelect());
        intent.putExtra(TIME_ZONE, rc.getTimeZone());
        this.mContext.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTest(Intent intent) {
        int testcase = intent.getIntExtra(TEST, 0);
        LogUtils.i(TAG, "handleStrTest mInterface:" + this.mInterface + " testcase:" + testcase, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onTest(testcase);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUsbRemove() {
        LogUtils.i(TAG, "handleUsbRemove mInterface:" + this.mInterface, new Object[0]);
        synchronized (this) {
            if (this.mInterface != null) {
                this.mInterface.onUsbRemove();
            }
        }
    }

    public boolean sendBindCloudRsp(int bindStatus, String bindContent) {
        LogUtils.i(TAG, "sendBindCloudRsp:" + bindStatus + " bind_content:" + bindContent, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(BIND_CLOUD_SERVICE_RSP_ACTION);
        intent.putExtra(BIND_STATUS, bindStatus);
        intent.putExtra(BIND_CONTENT, bindContent);
        this.mContext.sendBroadcast(intent);
        return true;
    }

    public boolean sendBindInfo(int bindStatus) {
        LogUtils.i(TAG, "sendBindInfo bind_status:" + bindStatus, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(BIND_CLOUD_INFO_ACTION);
        intent.putExtra(BIND_STATUS, bindStatus);
        this.mContext.sendBroadcast(intent);
        return true;
    }

    public boolean sendAuthState(boolean auth) {
        LogUtils.i(TAG, "sendAuthState:" + auth, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(TBOX_AUTH_STATE_ACTION);
        intent.putExtra(AUTH_STATE, auth);
        this.mContext.sendBroadcast(intent);
        return true;
    }

    public boolean sendCloudTcpState(boolean z) {
        LogUtils.i(TAG, "sendCloudTcpState:" + z, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(TCP_CLOUD_SEVER_STATUS_ACTION);
        intent.putExtra("tcp_status", z ? 1 : 0);
        this.mContext.sendBroadcast(intent);
        return true;
    }

    public boolean send539(String json) {
        LogUtils.i(TAG, "send539 broadcast:" + json, new Object[0]);
        Intent intent = new Intent();
        intent.setAction(CLOUD_MANAGER_RECV_ACTION);
        intent.putExtra("key", json);
        this.mContext.sendBroadcast(intent);
        return true;
    }
}
