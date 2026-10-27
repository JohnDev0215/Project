package com.amazon.mp3.hawkfire.upsell;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.account.details.TOUUtil;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.playback.activity.ConcurrencyErrorDialogActivity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.upsellweb.LandingPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.OneClickWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilderFactory;
import com.amazon.mp3.util.LocaleUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.musicsubscriptionofferservice.BillingPeriod;
import com.amazon.musicsubscriptionofferservice.MonetaryAmount;
import com.amazon.musicsubscriptionofferservice.PlanOffer;
import com.amazon.musicsubscriptionofferservice.PlanSchedule;
import com.amazon.musicsubscriptionofferservice.Result;
import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponse;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity extends DialogActivity {
    private static final String TAG = "ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity";
    private String mOldDeviceName;

    public static Intent getIntent(final Context context, final String oldDeviceName) {
        Intent intent = new Intent(context, (Class<?>) ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity.class);
        intent.putExtra("com.amazon.mp3.playback.activity.EXTRA_CONCURRENCY_DEVICE_NAME", oldDeviceName);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_one_click_upsell_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.message);
        Button button = (Button) viewInflate.findViewById(R.id.action);
        Button button2 = (Button) viewInflate.findViewById(R.id.cancel);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.legal);
        final boolean z = SubscriptionManagerSingleton.isInitialized() && SubscriptionManagerSingleton.getInstance().hasFreeTrial();
        textView.setText(getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_family_one_click_upsell_title));
        int i = z ? R.string.dmusic_prime_streaming_concurrency_dlg_one_click_continue_btn_free_trial_eligible : R.string.dmusic_prime_streaming_concurrency_dlg_one_click_continue_btn;
        button.setText(i);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.hawkfire.upsell.ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createDialog$0(z, view);
            }
        });
        button2.setText(R.string.dmusic_prime_streaming_concurrency_dlg_one_click_cancel_btn);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.hawkfire.upsell.ConcurrentStreamingUnlimitedFamilyUpsellOneClickActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createDialog$1(view);
            }
        });
        textView2.setText(R.string.dmusic_prime_streaming_concurrency_dlg_message_family_one_click_upsell);
        textView3.setText(Html.fromHtml(getString(z ? R.string.dmusic_prime_streaming_concurrency_dlg_one_click_tou_free_trial_eligible : R.string.dmusic_prime_streaming_concurrency_dlg_one_click_tou, getString(i), TOUUtil.get(getApplicationContext()).getTOUUrl(), getFamilyPlanPrice(), new SettingsPageWebTargetBuilderFactory(getApplicationContext()).newBuilder().build().getUrl(), new LandingPageWebTargetBuilderFactory(getApplicationContext()).newBuilder().applyTier(SubscriptionTier.UNLIMITED_FAMILY).build().getUrl())));
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewInflate);
        builder.setCancelable(false);
        MetricsLogger.ConcurrencyUpsellOneClickViewEvent();
        return builder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$0(boolean z, View view) {
        if (z) {
            MetricsLogger.emitUiClickMetricForUpsell(com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.START_TRIAL_CONCURRENT_FAMILY_UPSELL_ONE_CLICK_SIGNUP, ContentName.CONCURRENT_FAMILY_UPSELL_ONE_CLICK_SIGNUP_TO_TRIAL.getMetricsValue());
        } else {
            MetricsLogger.emitUiClickMetricForUpsell(com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.START_PAID_CONCURRENT_FAMILY_UPSELL_ONE_CLICK_SIGNUP, ContentName.CONCURRENT_FAMILY_UPSELL_ONE_CLICK_SIGNUP_TO_PAID.getMetricsValue());
        }
        onClickUpsell();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createDialog$1(View view) {
        MetricsLogger.emitUiClickMetricForUpsell(com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.NO_THANKS_CONCURRENT_FAMILY_UPSELL_ONE_CLICK_SIGNUP, ContentName.CONCURRENT_FAMILY_ONE_CLICK_SIGNUP_UPSELL.getMetricsValue());
        onClickCancel();
    }

    private void onClickUpsell() {
        Intent startIntent;
        Context applicationContext = getApplicationContext();
        UnlimitedUpsellActivity.setHawkfireUpsellAutoplayFlag(applicationContext, true);
        if (((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.unlimited_family_upsell)) {
            startIntent = UnlimitedUpsellActivity.getStartIntent(applicationContext, getFamilyOneClickSignupPageWebTarget());
        } else {
            startIntent = UnlimitedUpsellActivity.getStartIntent(applicationContext, getFamilyOneClickUpgradePageWebTarget());
        }
        startActivity(startIntent);
        finish();
    }

    private WebTarget getFamilyOneClickSignupPageWebTarget() {
        HashMap map = new HashMap();
        map.put("ref", "dm_wcp_concurrency_fm_ups_oneclick");
        return new OneClickWebTargetBuilderFactory(getApplicationContext()).newBuilder().applyActionType(ActionType.SIGNUP).applyPlanName("HAWKFIRE_FAMILY_MONTHLY").withQueryParamsToForward(map).build();
    }

    private WebTarget getFamilyOneClickUpgradePageWebTarget() {
        HashMap map = new HashMap();
        map.put("ref", "dm_wcp_concurrency_fm_ups_oneclick");
        return new OneClickWebTargetBuilderFactory(getApplicationContext()).newBuilder().applyActionType(ActionType.UPGRADE).applyPlanName("HAWKFIRE_FAMILY_MONTHLY").withQueryParamsToForward(map).build();
    }

    private void onClickCancel() {
        this.mOldDeviceName = getIntent().getStringExtra("com.amazon.mp3.playback.activity.EXTRA_CONCURRENCY_DEVICE_NAME");
        startActivity(ConcurrencyErrorDialogActivity.getIntent(getApplicationContext(), this.mOldDeviceName));
        finish();
    }

    public static String getFamilyPlanPrice() {
        MusicTerritory musicTerritoryFromValue = MusicTerritory.fromValue(AccountDetailUtil.getMusicTerritoryOfResidence());
        if (!SubscriptionManagerSingleton.isInitialized()) {
            return null;
        }
        try {
            BillingPeriod billingPeriodRetrieveHawkfireFamilyPlanPriceFromEligibleOffers = retrieveHawkfireFamilyPlanPriceFromEligibleOffers(SubscriptionManagerSingleton.getMSOSInstance().getMsosResponseCache());
            Validate.notNull(billingPeriodRetrieveHawkfireFamilyPlanPriceFromEligibleOffers, "Billing Period is missing from response", new Object[0]);
            MonetaryAmount monetaryAmount = (MonetaryAmount) Validate.notNull(billingPeriodRetrieveHawkfireFamilyPlanPriceFromEligibleOffers.getPrice(), "Monetary Amount is missing from response", new Object[0]);
            String str = (String) Validate.notEmpty(monetaryAmount.getCurrencyCode(), "Currency Code is missing from response", new Object[0]);
            String str2 = NumberFormat.getInstance(LocaleUtil.getLocaleFromCurrencyCode(str)).format((BigDecimal) Validate.notNull(monetaryAmount.getValue(), "Price is missing from response", new Object[0]));
            return Currency.getInstance(str).getSymbol() + str2;
        } catch (UnsupportedMarketplaceException unused) {
            Log.error(TAG, "Unable to retrieve Hawkfire family monthly price for music territory %s", musicTerritoryFromValue);
            return null;
        } catch (IllegalStateException e) {
            e = e;
            Log.error(TAG, "Could not retrieve family plan price." + e.toString());
            return null;
        } catch (NullPointerException e2) {
            e = e2;
            Log.error(TAG, "Could not retrieve family plan price." + e.toString());
            return null;
        }
    }

    private static BillingPeriod retrieveHawkfireFamilyPlanPriceFromEligibleOffers(RetrieveEligibleSubscriptionOffersResponse response) throws IllegalStateException, NullPointerException {
        Iterator<Result> it = ((RetrieveEligibleSubscriptionOffersResponse) Validate.notNull(response, "Response was null", new Object[0])).getResults().iterator();
        while (it.hasNext()) {
            for (PlanOffer planOffer : (List) Validate.notEmpty(((PlanSchedule) Validate.notNull(it.next().getPlanSchedule(), "Plan Schedule is missing from response", new Object[0])).getPlanOffers(), "Plan offers are missing from response", new Object[0])) {
                if ("HAWKFIRE_FAMILY_MONTHLY".equals((String) Validate.notEmpty(planOffer.getPlanId(), "Plan ID is missing from response", new Object[0]))) {
                    return (BillingPeriod) ((List) Validate.notEmpty(planOffer.getBillingSchedule(), "Billing Schedule is missing from response", new Object[0])).get(0);
                }
            }
        }
        return null;
    }
}
