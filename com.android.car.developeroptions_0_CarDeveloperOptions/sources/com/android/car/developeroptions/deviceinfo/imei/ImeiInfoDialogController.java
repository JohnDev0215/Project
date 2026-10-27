package com.android.car.developeroptions.deviceinfo.imei;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import com.android.car.developeroptions.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ImeiInfoDialogController {
    static final int ID_CDMA_SETTINGS = 2131362021;
    static final int ID_GSM_SETTINGS = 2131362280;
    static final int ID_IMEI_SV_VALUE = 2131362328;
    static final int ID_IMEI_VALUE = 2131362329;
    static final int ID_MEID_NUMBER_VALUE = 2131362424;
    static final int ID_MIN_NUMBER_VALUE = 2131362437;
    static final int ID_PRL_VERSION_VALUE = 2131362620;
    private final ImeiInfoDialogFragment mDialog;
    private final int mSlotId;
    private final SubscriptionInfo mSubscriptionInfo;
    private final TelephonyManager mTelephonyManager;

    private static CharSequence getTextAsDigits(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return "";
        }
        if (!TextUtils.isDigitsOnly(charSequence)) {
            return charSequence;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new TtsSpan.DigitsBuilder(charSequence.toString()).build(), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public ImeiInfoDialogController(ImeiInfoDialogFragment imeiInfoDialogFragment, int i) {
        this.mDialog = imeiInfoDialogFragment;
        this.mSlotId = i;
        Context context = imeiInfoDialogFragment.getContext();
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mSubscriptionInfo = getSubscriptionInfo(context, i);
    }

    public void populateImeiInfo() {
        if (this.mTelephonyManager.getPhoneType() == 2) {
            updateDialogForCdmaPhone();
        } else {
            updateDialogForGsmPhone();
        }
    }

    private void updateDialogForCdmaPhone() {
        Resources resources = this.mDialog.getContext().getResources();
        this.mDialog.setText(R.id.meid_number_value, getMeid());
        ImeiInfoDialogFragment imeiInfoDialogFragment = this.mDialog;
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        imeiInfoDialogFragment.setText(R.id.min_number_value, subscriptionInfo != null ? this.mTelephonyManager.getCdmaMin(subscriptionInfo.getSubscriptionId()) : "");
        if (resources.getBoolean(R.bool.config_msid_enable)) {
            this.mDialog.setText(R.id.min_number_label, resources.getString(R.string.status_msid_number));
        }
        this.mDialog.setText(R.id.prl_version_value, getCdmaPrlVersion());
        if (this.mSubscriptionInfo != null && isCdmaLteEnabled()) {
            this.mDialog.setText(R.id.imei_value, getTextAsDigits(this.mTelephonyManager.getImei(this.mSlotId)));
            this.mDialog.setText(R.id.imei_sv_value, getTextAsDigits(this.mTelephonyManager.getDeviceSoftwareVersion(this.mSlotId)));
        } else {
            this.mDialog.removeViewFromScreen(R.id.gsm_settings);
        }
    }

    private void updateDialogForGsmPhone() {
        this.mDialog.setText(R.id.imei_value, getTextAsDigits(this.mTelephonyManager.getImei(this.mSlotId)));
        this.mDialog.setText(R.id.imei_sv_value, getTextAsDigits(this.mTelephonyManager.getDeviceSoftwareVersion(this.mSlotId)));
        this.mDialog.removeViewFromScreen(R.id.cdma_settings);
    }

    private SubscriptionInfo getSubscriptionInfo(Context context, int i) {
        List activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.isEmpty()) {
            return null;
        }
        return (SubscriptionInfo) activeSubscriptionInfoList.get(i);
    }

    String getCdmaPrlVersion() {
        return this.mTelephonyManager.getCdmaPrlVersion();
    }

    boolean isCdmaLteEnabled() {
        return this.mTelephonyManager.isLteCdmaEvdoGsmWcdmaEnabled();
    }

    String getMeid() {
        return this.mTelephonyManager.getMeid(this.mSlotId);
    }
}
