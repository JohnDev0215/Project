package com.android.phone;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.telecom.PhoneAccountHandle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;

/* JADX INFO: loaded from: classes.dex */
public class SubscriptionInfoHelper {
    private Context mContext;
    private int mSubId;
    private String mSubLabel;

    public SubscriptionInfoHelper(Context context, Intent intent) {
        this.mSubId = -1;
        this.mContext = context;
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE");
        if (phoneAccountHandle != null) {
            this.mSubId = PhoneUtils.getSubIdForPhoneAccountHandle(phoneAccountHandle);
        }
        if (this.mSubId == -1) {
            this.mSubId = intent.getIntExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionId", -1);
        }
        this.mSubLabel = intent.getStringExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionLabel");
    }

    public Intent getIntent(Class cls) {
        Intent intent = new Intent(this.mContext, (Class<?>) cls);
        if (hasSubId()) {
            intent.putExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionId", this.mSubId);
        }
        if (!TextUtils.isEmpty(this.mSubLabel)) {
            intent.putExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionLabel", this.mSubLabel);
        }
        return intent;
    }

    public static void addExtrasToIntent(Intent intent, SubscriptionInfo subscriptionInfo) {
        if (subscriptionInfo == null) {
            return;
        }
        intent.putExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionId", subscriptionInfo.getSubscriptionId());
        intent.putExtra("com.android.phone.settings.SubscriptionInfoHelper.SubscriptionLabel", subscriptionInfo.getDisplayName().toString());
    }

    public Phone getPhone() {
        if (hasSubId()) {
            return PhoneFactory.getPhone(SubscriptionManager.getPhoneId(this.mSubId));
        }
        return PhoneGlobals.getPhone();
    }

    public void setActionBarTitle(ActionBar actionBar, Resources resources, int i) {
        if (actionBar == null || TextUtils.isEmpty(this.mSubLabel) || !TelephonyManager.from(this.mContext).isMultiSimEnabled()) {
            return;
        }
        actionBar.setTitle(String.format(resources.getString(i), this.mSubLabel));
    }

    public boolean hasSubId() {
        return this.mSubId != -1;
    }

    public int getSubId() {
        return this.mSubId;
    }
}
