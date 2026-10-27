package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.android.car.developeroptions.network.AirplaneModePreferenceController;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class MobileDataSlice implements CustomSliceable {
    private final Context mContext;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;

    public MobileDataSlice(Context context) {
        this.mContext = context;
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
    }

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", isMobileDataEnabled());
        int defaultSubscriptionId = getDefaultSubscriptionId(this.mSubscriptionManager);
        if (defaultSubscriptionId == -1) {
            return;
        }
        MobileNetworkUtils.setMobileDataEnabled(this.mContext, defaultSubscriptionId, booleanExtra, false);
    }

    protected static int getDefaultSubscriptionId(SubscriptionManager subscriptionManager) {
        SubscriptionInfo defaultDataSubscriptionInfo = subscriptionManager.getDefaultDataSubscriptionInfo();
        if (defaultDataSubscriptionInfo == null) {
            return -1;
        }
        return defaultDataSubscriptionInfo.getSubscriptionId();
    }

    boolean isAirplaneModeEnabled() {
        return new AirplaneModePreferenceController(this.mContext, "key").isChecked();
    }

    boolean isMobileDataEnabled() {
        TelephonyManager telephonyManager = this.mTelephonyManager;
        if (telephonyManager == null) {
            return false;
        }
        return telephonyManager.isDataEnabled();
    }

    public static class MobileDataWorker extends SliceBackgroundWorker<Void> {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public MobileDataWorker(Context context, Uri uri) {
            super(context, uri);
            new DataContentObserver(this, new Handler(Looper.getMainLooper()), this);
        }

        public void updateSlice() {
            notifySliceChange();
        }

        public class DataContentObserver extends ContentObserver {
            private final MobileDataWorker mSliceBackgroundWorker;

            public DataContentObserver(MobileDataWorker mobileDataWorker, Handler handler, MobileDataWorker mobileDataWorker2) {
                super(handler);
                this.mSliceBackgroundWorker = mobileDataWorker2;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                this.mSliceBackgroundWorker.updateSlice();
            }
        }
    }
}
