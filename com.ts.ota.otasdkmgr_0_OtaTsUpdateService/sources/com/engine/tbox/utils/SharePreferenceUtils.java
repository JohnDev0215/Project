package com.engine.tbox.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.ReservationCharging;

/* JADX INFO: loaded from: classes.dex */
public class SharePreferenceUtils {
    private static final String NAME = "T-BOX";
    public static final String PRIVATE_POLICY_SEND_RESULT = "private_policy_send_result";
    public static final String PRIVATE_POLICY_STATUA = "private_policy_statua";
    private static final String TAG = "TboxSharePreferenceUtils";
    private static final String VIRTUAL_VIN = "virtual_vin";
    private Context mContext;
    SharedPreferences mSharedReference;
    private static int PRIVATE_POLICY_STATUS_AGREE = 1;
    private static int DEFAULT_USER_OPERATION = -1;

    public interface OnPrivatePolicyReady {
        void onPirvatePolicyReady(int i, int i2, String str, String str2);
    }

    public SharePreferenceUtils(Context context) {
        this.mSharedReference = context.getSharedPreferences("T-BOX", 0);
    }

    public boolean storeReservationCharging(ReservationCharging rc) {
        boolean zCommit;
        synchronized (this) {
            SharedPreferences.Editor edit = this.mSharedReference.edit();
            edit.putInt(BroadCastUtils.TIME_START_HOUR, rc.getRsvStartHour());
            edit.putInt(BroadCastUtils.TIME_START_MIN, rc.getRsvStartMin());
            edit.putInt(BroadCastUtils.REPEAT_MODE, rc.getRsvPlanePeriod());
            edit.putInt(BroadCastUtils.PLANE_SWITCH, rc.getRsvPlaneSwitch());
            edit.putInt(BroadCastUtils.TIME_END_HOUR, rc.getRsvEndHour());
            edit.putInt(BroadCastUtils.TIME_END_MIN, rc.getRsvEndMin());
            edit.putInt(BroadCastUtils.GO_OUT_HOUR, rc.getRsvOutHour());
            edit.putInt(BroadCastUtils.GO_OUT_MIN, rc.getRsvOutMin());
            edit.putInt(BroadCastUtils.GO_OUT_REPEAT_MODE, rc.getRsvOutPeriod());
            edit.putInt(BroadCastUtils.GO_OUT_SWITCH, rc.getRsvOutSwitch());
            edit.putInt(BroadCastUtils.CHEAP_TIME_START_HOUR, rc.getPreferentialTariffStartHour());
            edit.putInt(BroadCastUtils.CHEAP_TIME_START_MIN, rc.getPreferentialTariffStartMin());
            edit.putInt(BroadCastUtils.CHEAP_TIME_END_HOUR, rc.getPreferentialTariffEndHour());
            edit.putInt(BroadCastUtils.CHEAP_TIME_END_MIN, rc.getPreferentialTariffEndMin());
            edit.putString(BroadCastUtils.TIME_ZONE, rc.getTimeZone());
            zCommit = edit.commit();
        }
        return zCommit;
    }

    public Intent getStoreReservationCharging() {
        Intent intent = new Intent();
        synchronized (this) {
            if (!this.mSharedReference.contains(BroadCastUtils.TIME_START_HOUR)) {
                return null;
            }
            intent.putExtra(BroadCastUtils.TIME_START_HOUR, this.mSharedReference.getInt(BroadCastUtils.TIME_START_HOUR, 0));
            intent.putExtra(BroadCastUtils.TIME_START_MIN, this.mSharedReference.getInt(BroadCastUtils.TIME_START_MIN, 0));
            intent.putExtra(BroadCastUtils.REPEAT_MODE, this.mSharedReference.getInt(BroadCastUtils.REPEAT_MODE, 0));
            intent.putExtra(BroadCastUtils.PLANE_SWITCH, this.mSharedReference.getInt(BroadCastUtils.PLANE_SWITCH, 0));
            intent.putExtra(BroadCastUtils.TIME_END_HOUR, this.mSharedReference.getInt(BroadCastUtils.TIME_END_HOUR, 0));
            intent.putExtra(BroadCastUtils.TIME_END_MIN, this.mSharedReference.getInt(BroadCastUtils.TIME_END_MIN, 0));
            intent.putExtra(BroadCastUtils.GO_OUT_HOUR, this.mSharedReference.getInt(BroadCastUtils.GO_OUT_HOUR, 0));
            intent.putExtra(BroadCastUtils.GO_OUT_MIN, this.mSharedReference.getInt(BroadCastUtils.GO_OUT_MIN, 0));
            intent.putExtra(BroadCastUtils.GO_OUT_REPEAT_MODE, this.mSharedReference.getInt(BroadCastUtils.GO_OUT_REPEAT_MODE, 0));
            intent.putExtra(BroadCastUtils.GO_OUT_SWITCH, this.mSharedReference.getInt(BroadCastUtils.GO_OUT_SWITCH, 0));
            intent.putExtra(BroadCastUtils.CHEAP_TIME_START_HOUR, this.mSharedReference.getInt(BroadCastUtils.CHEAP_TIME_START_HOUR, 0));
            intent.putExtra(BroadCastUtils.CHEAP_TIME_END_HOUR, this.mSharedReference.getInt(BroadCastUtils.CHEAP_TIME_END_HOUR, 0));
            intent.putExtra(BroadCastUtils.CHEAP_TIME_START_MIN, this.mSharedReference.getInt(BroadCastUtils.CHEAP_TIME_START_MIN, 0));
            intent.putExtra(BroadCastUtils.CHEAP_TIME_END_MIN, this.mSharedReference.getInt(BroadCastUtils.CHEAP_TIME_END_MIN, 0));
            intent.putExtra(BroadCastUtils.TIME_ZONE, this.mSharedReference.getString(BroadCastUtils.TIME_ZONE, "N/A"));
            return intent;
        }
    }

    public boolean deleteReservationCharging() {
        boolean zCommit;
        synchronized (this) {
            SharedPreferences.Editor edit = this.mSharedReference.edit();
            edit.remove(BroadCastUtils.TIME_START_HOUR);
            edit.remove(BroadCastUtils.TIME_START_MIN);
            edit.remove(BroadCastUtils.REPEAT_MODE);
            edit.remove(BroadCastUtils.PLANE_SWITCH);
            edit.remove(BroadCastUtils.TIME_END_HOUR);
            edit.remove(BroadCastUtils.TIME_END_MIN);
            edit.remove(BroadCastUtils.GO_OUT_HOUR);
            edit.remove(BroadCastUtils.GO_OUT_MIN);
            edit.remove(BroadCastUtils.GO_OUT_REPEAT_MODE);
            edit.remove(BroadCastUtils.GO_OUT_SWITCH);
            edit.remove(BroadCastUtils.CHEAP_TIME_START_MIN);
            edit.remove(BroadCastUtils.CHEAP_TIME_START_HOUR);
            edit.remove(BroadCastUtils.CHEAP_TIME_END_HOUR);
            edit.remove(BroadCastUtils.CHEAP_TIME_END_MIN);
            edit.remove(BroadCastUtils.TIME_ZONE);
            zCommit = edit.commit();
        }
        return zCommit;
    }

    public boolean storePrivatePolicy(int auth, int action, String time, String version) {
        boolean zCommit;
        synchronized (this) {
            SharedPreferences.Editor edit = this.mSharedReference.edit();
            edit.putInt(BroadCastUtils.AUTH_POLICY_STATUS, auth);
            edit.putInt(BroadCastUtils.USER_POLICY_OPERATION, action);
            edit.putString(BroadCastUtils.OP_POLICY_DATE, time);
            edit.putString(BroadCastUtils.IVI_POLICY_VERSION, version);
            zCommit = edit.commit();
        }
        return zCommit;
    }

    public void readPrivatePolicy(OnPrivatePolicyReady callback) {
        synchronized (this) {
            int auth = this.mSharedReference.getInt(BroadCastUtils.AUTH_POLICY_STATUS, PRIVATE_POLICY_STATUS_AGREE);
            int action = this.mSharedReference.getInt(BroadCastUtils.USER_POLICY_OPERATION, DEFAULT_USER_OPERATION);
            if (action == DEFAULT_USER_OPERATION) {
                LogUtils.e(TAG, "user_operation value error", new Object[0]);
                return;
            }
            String time = this.mSharedReference.getString(BroadCastUtils.OP_POLICY_DATE, "");
            if ("".equals(time)) {
                LogUtils.e(TAG, "effective_date value error", new Object[0]);
                return;
            }
            String version = this.mSharedReference.getString(BroadCastUtils.IVI_POLICY_VERSION, "");
            if ("".equals(version)) {
                LogUtils.e(TAG, "privacy_policy_version value error", new Object[0]);
            } else if (callback != null) {
                callback.onPirvatePolicyReady(auth, action, time, version);
            }
        }
    }

    public Boolean getPrivatePolicyStatus() {
        Boolean boolValueOf;
        synchronized (this) {
            boolValueOf = Boolean.valueOf(this.mSharedReference.getBoolean(PRIVATE_POLICY_STATUA, true));
        }
        return boolValueOf;
    }

    public void setPrivatePolicyStatus(Boolean privatePolicyStatus) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.mSharedReference.edit();
            edit.putBoolean(PRIVATE_POLICY_STATUA, privatePolicyStatus.booleanValue());
            edit.commit();
        }
    }

    public void setVirtualVin(String virtualVin) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.mSharedReference.edit();
            edit.putString(VIRTUAL_VIN, virtualVin);
            edit.commit();
        }
    }

    public String getVirtualVin() {
        String string;
        synchronized (this) {
            string = this.mSharedReference.getString(VIRTUAL_VIN, "");
        }
        return string;
    }
}
