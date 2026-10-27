package com.amazon.mp3.dialog.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.dialogtarget.DialogTargetStorable;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellUIConfig;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilderFactory;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;

/* JADX INFO: loaded from: classes3.dex */
public class HawkfireUpsellDialogFragmentV2 extends DialogFragment implements DialogTargetStorable {
    public static final String TAG = HawkfireUpsellDialogFragmentV2.class.getSimpleName();
    protected View mRootView;

    public static HawkfireUpsellDialogFragmentV2 newInstance(final Bundle bundle) {
        HawkfireUpsellDialogFragmentV2 hawkfireUpsellDialogFragmentV2 = new HawkfireUpsellDialogFragmentV2();
        hawkfireUpsellDialogFragmentV2.setArguments(bundle);
        return hawkfireUpsellDialogFragmentV2;
    }

    @Override // com.amazon.mp3.dialog.dialogtarget.DialogTargetStorable
    public HawkfireUpsellDialogFragmentV2 getInstance(final Bundle bundle) {
        return newInstance(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final FragmentActivity activity = getActivity();
        this.mRootView = View.inflate(getActivity(), R.layout.dialog_hawkfire_bauhaus, null);
        UpsellUIConfig upsellUIConfig = (UpsellUIConfig) getArguments().getParcelable("UI_CONFIG_KEY");
        final boolean z = SubscriptionManagerSingleton.isInitialized() && SubscriptionManagerSingleton.getInstance().hasFreeTrial();
        final WebTarget webTargetBuild = new OfferPageWebTargetBuilderFactory(activity).newBuilder().applyTier(SubscriptionTier.UNLIMITED_INDIVIDUAL).build();
        AlertDialog.Builder alertDialog = UpsellUtil.getAlertDialog(activity, this.mRootView, upsellUIConfig, new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragmentV2.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                HawkfireUpsellDialogFragmentV2.this.dismiss();
                activity.startActivity(UnlimitedUpsellActivity.getStartIntent(activity, webTargetBuild));
                if (z) {
                    MetricsLogger.emitUiClickMetricForUpsell(ActionType.START_TRIAL_BAUHAUS_BLOCKING_HAWKFIRE_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL_TO_TRIAL.getMetricsValue());
                } else {
                    MetricsLogger.emitUiClickMetricForUpsell(ActionType.START_PAID_BAUHAUS_BLOCKING_HAWKFIRE_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL_TO_PAID.getMetricsValue());
                }
            }
        }, new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragmentV2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                HawkfireUpsellDialogFragmentV2.this.dismiss();
                MetricsLogger.emitUiClickMetricForUpsell(ActionType.NO_THANKS_BAUHAUS_BLOCKING_HAWKFIRE_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL.getMetricsValue());
            }
        });
        if (!(savedInstanceState != null && savedInstanceState.getBoolean("IS_ORIENTATION_CHANGED", false))) {
            sendContentViewEvent();
        }
        return alertDialog.create();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle savedState) {
        savedState.putBoolean("IS_ORIENTATION_CHANGED", true);
        super.onSaveInstanceState(savedState);
    }

    private void sendContentViewEvent() {
        MetricsLogger.sendUpsellContentViewEvent(ContainerType.BLOCKING_UPSELL_DIALOG, ContentName.BAUHAUS_HAWKFIRE_BLOCKING_UPSELL);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        UpsellManager.getInstance().setShowingBlockingDialog(false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpsellManager.getInstance().setShowingBlockingDialog(true);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        KeyEventDispatcher.Component activity = getActivity();
        if (activity instanceof DialogFragmentListener) {
            ((DialogFragmentListener) activity).onNegativeButtonClicked(dialog);
        }
    }
}
