package com.android.car.settings.datausage;

import android.R;
import android.content.Context;
import android.net.NetworkTemplate;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionPlan;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.format.Formatter;
import com.android.internal.util.CollectionUtils;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class DataUsageUtils {
    static final long PETA = 1000000000000000L;

    private DataUsageUtils() {
    }

    public static NetworkTemplate getMobileNetworkTemplate(TelephonyManager telephonyManager, int subscriptionId) {
        NetworkTemplate mobileAll = NetworkTemplate.buildTemplateMobileAll(telephonyManager.getSubscriberId(subscriptionId));
        return NetworkTemplate.normalize(mobileAll, telephonyManager.getMergedSubscriberIds());
    }

    public static int getDefaultSubscriptionId(SubscriptionManager subscriptionManager) {
        if (subscriptionManager == null) {
            return -1;
        }
        SubscriptionInfo subscriptionInfo = subscriptionManager.getDefaultDataSubscriptionInfo();
        if (subscriptionInfo == null) {
            List<SubscriptionInfo> list = subscriptionManager.getAllSubscriptionInfoList();
            if (list.size() == 0) {
                return -1;
            }
            subscriptionInfo = list.get(0);
        }
        return subscriptionInfo.getSubscriptionId();
    }

    public static CharSequence bytesToIecUnits(Context context, long byteValue) {
        Formatter.BytesResult res = Formatter.formatBytes(context.getResources(), byteValue, 8);
        return BidiFormatter.getInstance().unicodeWrap(context.getString(R.string.default_audio_route_name_external_device, res.value, res.units));
    }

    public static SubscriptionPlan getPrimaryPlan(SubscriptionManager subManager, int subscriptionId) {
        List<SubscriptionPlan> plans = subManager.getSubscriptionPlans(subscriptionId);
        if (CollectionUtils.isEmpty(plans)) {
            return null;
        }
        SubscriptionPlan plan = plans.get(0);
        if (plan.getDataLimitBytes() <= 0 || !saneSize(plan.getDataUsageBytes()) || plan.getCycleRule() == null) {
            return null;
        }
        return plan;
    }

    private static boolean saneSize(long value) {
        return value >= 0 && value < PETA;
    }
}
