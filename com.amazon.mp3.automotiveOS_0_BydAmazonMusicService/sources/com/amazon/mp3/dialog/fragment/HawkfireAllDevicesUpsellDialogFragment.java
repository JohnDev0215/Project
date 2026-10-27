package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder;
import com.amazon.mp3.dialog.dialogtarget.DialogTargetStorable;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;

/* JADX INFO: loaded from: classes3.dex */
public class HawkfireAllDevicesUpsellDialogFragment extends DialogFragment implements DialogTargetStorable {
    public static final String TAG = HawkfireAllDevicesUpsellDialogFragment.class.getSimpleName();

    public static HawkfireAllDevicesUpsellDialogFragment newInstance(final Bundle bundle) {
        HawkfireAllDevicesUpsellDialogFragment hawkfireAllDevicesUpsellDialogFragment = new HawkfireAllDevicesUpsellDialogFragment();
        hawkfireAllDevicesUpsellDialogFragment.setArguments(bundle);
        return hawkfireAllDevicesUpsellDialogFragment;
    }

    @Override // com.amazon.mp3.dialog.dialogtarget.DialogTargetStorable
    public HawkfireAllDevicesUpsellDialogFragment getInstance(final Bundle bundle) {
        return newInstance(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        AccessibilityFocusDialogBuilder accessibilityFocusDialogBuilder = new AccessibilityFocusDialogBuilder(activity);
        accessibilityFocusDialogBuilder.setTitle(activity.getString(R.string.dmusic_hawkfire_home_account_upsell_dialog_title));
        accessibilityFocusDialogBuilder.setMessage(activity.getString(((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.hawkfire_all_devices_upsell_dialog_discount_message) ? R.string.dmusic_hawkfire_home_account_upsell_dialog_message_prime_discount : R.string.dmusic_hawkfire_home_account_upsell_dialog_message));
        accessibilityFocusDialogBuilder.setNegativeButton(R.string.close_button_content_description, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.HawkfireAllDevicesUpsellDialogFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0(dialogInterface, i);
            }
        });
        return accessibilityFocusDialogBuilder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateDialog$0(DialogInterface dialogInterface, int i) {
        sendDismissUiClickEvent();
        onNegativeButtonClicked(dialogInterface);
    }

    private void onNegativeButtonClicked(final DialogInterface dialog) {
        super.onDismiss(dialog);
        KeyEventDispatcher.Component activity = getActivity();
        if (activity instanceof DialogFragmentListener) {
            ((DialogFragmentListener) activity).onNegativeButtonClicked(dialog);
        }
    }

    private void sendDismissUiClickEvent() {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.CTA_DISMISS).withInteractionType(InteractionType.TAP).build());
    }
}
