package com.amazon.mp3.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder;
import com.amazon.mp3.dialog.dialogtarget.DialogTargetStorable;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilderFactory;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;

/* JADX INFO: loaded from: classes3.dex */
public class HawkfireUpsellDialogFragment extends DialogFragment implements DialogTargetStorable {
    public static final String TAG = HawkfireUpsellDialogFragment.class.getSimpleName();

    public static HawkfireUpsellDialogFragment newInstance(final Bundle bundle) {
        HawkfireUpsellDialogFragment hawkfireUpsellDialogFragment = new HawkfireUpsellDialogFragment();
        hawkfireUpsellDialogFragment.setArguments(bundle);
        return hawkfireUpsellDialogFragment;
    }

    @Override // com.amazon.mp3.dialog.dialogtarget.DialogTargetStorable
    public HawkfireUpsellDialogFragment getInstance(final Bundle bundle) {
        return newInstance(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int i;
        FragmentActivity activity = getActivity();
        AccessibilityFocusDialogBuilder accessibilityFocusDialogBuilder = new AccessibilityFocusDialogBuilder(activity);
        boolean zIsFeatureAvailable = ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.hawkfire_account_upsell_dialog_message);
        final boolean z = SubscriptionManagerSingleton.isInitialized() && SubscriptionManagerSingleton.getInstance().hasFreeTrial();
        final WebTarget webTarget = getWebTarget(getActivity().getApplicationContext());
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        if (zIsFeatureAvailable) {
            i = MusicTerritory.CANADA.equalsIgnoreCase(musicTerritoryOfResidence) ? R.string.dmusic_hawkfire_account_upsell_dialog_message_with_prime_ca : R.string.dmusic_hawkfire_account_upsell_dialog_message_with_prime;
        } else if (z) {
            i = MusicTerritory.CANADA.equalsIgnoreCase(musicTerritoryOfResidence) ? R.string.dmusic_hawkfire_account_upsell_dialog_message_free_trial_eligible_ca : R.string.dmusic_hawkfire_account_upsell_dialog_message_free_trial_eligible;
        } else {
            i = MusicTerritory.CANADA.equalsIgnoreCase(musicTerritoryOfResidence) ? R.string.dmusic_hawkfire_account_upsell_dialog_message_ca : R.string.dmusic_hawkfire_account_upsell_dialog_message;
        }
        int i2 = z ? zIsFeatureAvailable ? R.string.dmusic_amp_unlimited_start_trial : R.string.dmusic_hawkfire_account_upsell_dialog_positive_btn_free_trial_eligible : R.string.dmusic_hawkfire_account_upsell_dialog_positive_btn;
        accessibilityFocusDialogBuilder.setTitle(activity.getString(R.string.dmusic_hawkfire_account_upsell_dialog_title));
        accessibilityFocusDialogBuilder.setMessage(activity.getString(i));
        accessibilityFocusDialogBuilder.setPositiveButton(i2, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                HawkfireUpsellDialogFragment.this.getActivity().startActivity(UnlimitedUpsellActivity.getStartIntent(HawkfireUpsellDialogFragment.this.getActivity(), webTarget));
                if (z) {
                    UserInteractionAppEvent.builder("startTrialUpsellDialog").publish();
                } else {
                    UserInteractionAppEvent.builder("startPaidUpsellDialog").publish();
                }
            }
        });
        accessibilityFocusDialogBuilder.setNegativeButton(R.string.dmusic_hawkfire_account_upsell_dialog_negative_btn, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.HawkfireUpsellDialogFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                UserInteractionAppEvent.builder("noThanksUpsellDialog").publish();
                HawkfireUpsellDialogFragment.this.onNegativeButtonClicked(dialog);
            }
        });
        if (!(savedInstanceState != null && savedInstanceState.getBoolean("IS_ORIENTATION_CHANGED", false))) {
            sendContentViewEvent(ContentName.HAWKFIRE_UPSELL_DIALOG_FRAGMENT);
        }
        return accessibilityFocusDialogBuilder.create();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle savedState) {
        savedState.putBoolean("IS_ORIENTATION_CHANGED", true);
        super.onSaveInstanceState(savedState);
    }

    private void sendContentViewEvent(final ContentName contentName) {
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.DIALOG).withContentName(contentName).withContainerIndex(0L).withNumItemsAccessible(0L).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    private WebTarget getWebTarget(final Context context) {
        WebTarget webTarget = getArguments() != null ? (WebTarget) getArguments().getSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET") : null;
        return webTarget != null ? webTarget : new OfferPageWebTargetBuilderFactory(context).newBuilder().withListenRedirectDestination().build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNegativeButtonClicked(final DialogInterface dialog) {
        KeyEventDispatcher.Component activity = getActivity();
        if (activity instanceof DialogFragmentListener) {
            ((DialogFragmentListener) activity).onNegativeButtonClicked(dialog);
        }
    }
}
