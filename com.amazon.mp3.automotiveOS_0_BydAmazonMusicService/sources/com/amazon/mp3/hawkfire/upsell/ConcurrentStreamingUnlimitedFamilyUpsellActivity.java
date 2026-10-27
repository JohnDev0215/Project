package com.amazon.mp3.hawkfire.upsell;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.ConcurrencyErrorDialogActivity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilderFactory;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.events.UserInteractionAppEvent;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class ConcurrentStreamingUnlimitedFamilyUpsellActivity extends DialogActivity {
    private String mOldDeviceName;
    private final DialogInterface.OnClickListener mOnPositiveButtonClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.hawkfire.upsell.ConcurrentStreamingUnlimitedFamilyUpsellActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            UserInteractionAppEvent.builder("concurrentFamilyUpsellTrial").publish();
            ConcurrentStreamingUnlimitedFamilyUpsellActivity.this.onClickUpsell();
        }
    };
    private final DialogInterface.OnClickListener mOnCancelClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.hawkfire.upsell.ConcurrentStreamingUnlimitedFamilyUpsellActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            UserInteractionAppEvent.builder("concurrentFamilyUpsellNoThanks").publish();
            ConcurrentStreamingUnlimitedFamilyUpsellActivity.this.onClickCancel();
        }
    };

    public static Intent getIntent(final Context context, final String oldDeviceName) {
        Intent intent = new Intent(context, (Class<?>) ConcurrentStreamingUnlimitedFamilyUpsellActivity.class);
        intent.putExtra("com.amazon.mp3.playback.activity.EXTRA_CONCURRENCY_DEVICE_NAME", oldDeviceName);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        boolean z = SubscriptionManagerSingleton.isInitialized() && SubscriptionManagerSingleton.getInstance().hasFreeTrial();
        String string = getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_family_upsell_title);
        int i = z ? R.string.dmusic_hawkfire_account_upsell_dialog_positive_btn_free_trial_eligible : R.string.dmusic_hawkfire_account_upsell_dialog_positive_btn;
        AlertDialog.Builder builderNewAlertDialogBuilder = newAlertDialogBuilder();
        builderNewAlertDialogBuilder.setTitle(string);
        builderNewAlertDialogBuilder.setMessage(getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_family_upsell));
        builderNewAlertDialogBuilder.setPositiveButton(i, this.mOnPositiveButtonClickListener);
        builderNewAlertDialogBuilder.setCancelable(false);
        builderNewAlertDialogBuilder.setNegativeButton(R.string.dmusic_hawkfire_account_upsell_dialog_negative_btn, this.mOnCancelClickListener);
        MetricsLogger.ConcurrencyUpsellViewEvent();
        return builderNewAlertDialogBuilder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickUpsell() {
        WebTarget familyOfferPageWebTarget;
        if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
            familyOfferPageWebTarget = getSettingsPageWebTarget();
        } else {
            familyOfferPageWebTarget = getFamilyOfferPageWebTarget();
        }
        startActivity(UnlimitedUpsellActivity.getStartIntent(getApplicationContext(), familyOfferPageWebTarget));
        finish();
    }

    private WebTarget getFamilyOfferPageWebTarget() {
        HashMap map = new HashMap();
        map.put("ref", "dm_wcp_concurrency_fm_ups_fmsu_T2");
        return new OfferPageWebTargetBuilderFactory(getApplicationContext()).newBuilder().applyTier(SubscriptionTier.UNLIMITED_FAMILY).withQueryParamsToForward(map).build();
    }

    private WebTarget getSettingsPageWebTarget() {
        return new SettingsPageWebTargetBuilderFactory(getApplicationContext()).newBuilder().applyTier(SubscriptionTier.UNLIMITED_FAMILY).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickCancel() {
        this.mOldDeviceName = getIntent().getStringExtra("com.amazon.mp3.playback.activity.EXTRA_CONCURRENCY_DEVICE_NAME");
        startActivity(ConcurrencyErrorDialogActivity.getIntent(getApplicationContext(), this.mOldDeviceName));
        finish();
    }
}
