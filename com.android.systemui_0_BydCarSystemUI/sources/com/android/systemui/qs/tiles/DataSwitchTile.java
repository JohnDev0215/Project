package com.android.systemui.qs.tiles;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ArrayUtils;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;

/* JADX INFO: loaded from: classes.dex */
public class DataSwitchTile extends QSTileImpl<QSTile.BooleanState> {
    private static String TAG = "DataSwitchTile";
    private TelephonyManager mTelephonyManager;

    public int getFiveGState(int i) {
        switch (i) {
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
                return 1;
            default:
                return 0;
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_5g;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public DataSwitchTile(Context context) {
        super(context);
        this.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        Log.d(TAG, "getNetworkType types=" + SystemProperties.get("ro.telephony.default_network"));
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_data_switch);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        if (isSelected) {
            this.mTelephonyManager.setPreferredNetworkType(getSubId(), enableNr());
            showToastCustom(this.mContext.getString(R.string.qs_title_toast_open_5_g));
        } else {
            this.mTelephonyManager.setPreferredNetworkType(getSubId(), disableNr());
            showToastCustom(this.mContext.getString(R.string.qs_title_toast_close_5_g));
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = getFiveGState() == 1;
        BydLogHelper.logD("" + ((QSTile.BooleanState) this.mState).value);
        refreshState();
    }

    public static int getSubId() {
        int defaultDataSubscriptionId = Build.VERSION.SDK_INT >= 24 ? SubscriptionManager.getDefaultDataSubscriptionId() : -1;
        Log.i(TAG, "defaultDataSubId=" + defaultDataSubscriptionId);
        return defaultDataSubscriptionId;
    }

    private int getNetworkType() {
        int subId = getSubId();
        if (!SubscriptionManager.isValidSubscriptionId(subId)) {
            int[] activeSubscriptionIdList = SubscriptionManager.from(this.mContext).getActiveSubscriptionIdList();
            if (!ArrayUtils.isEmpty(activeSubscriptionIdList)) {
                subId = activeSubscriptionIdList[0];
            }
        }
        Log.i(TAG, "getNetworkType subscriptionId=" + subId);
        int i = -1;
        if (subId != -1) {
            String str = SystemProperties.get("ro.telephony.default_network");
            Log.d(TAG, "getNetworkType types=" + str);
            try {
                if (!str.isEmpty() && str.contains(ListUtils.DEFAULT_JOIN_SEPARATOR)) {
                    i = Integer.parseInt(str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "mNetworkType=" + i);
        return i;
    }

    public int getFiveGState() {
        return getFiveGState(getNetworkType());
    }

    public int enableNr() {
        int networkType = getNetworkType();
        if (networkType == 15) {
            return 29;
        }
        if (networkType == 17) {
            return 30;
        }
        if (networkType == 23) {
            return 23;
        }
        if (networkType == 19) {
            return 31;
        }
        if (networkType == 20) {
            return 32;
        }
        switch (networkType) {
            case 8:
                return 25;
            case 9:
                return 26;
            case 10:
                return 27;
            case 11:
                return 24;
            case 12:
                return 28;
            default:
                return 33;
        }
    }

    public int disableNr() {
        switch (getNetworkType()) {
            case 23:
                return 23;
            case 24:
                return 11;
            case 25:
                return 8;
            case 26:
                return 9;
            case 27:
                return 10;
            case 28:
                return 12;
            case 29:
                return 15;
            case 30:
                return 17;
            case 31:
                return 19;
            case 32:
                return 20;
            default:
                return 22;
        }
    }
}
