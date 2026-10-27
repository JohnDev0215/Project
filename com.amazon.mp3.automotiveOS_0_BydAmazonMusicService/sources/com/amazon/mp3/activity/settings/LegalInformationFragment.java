package com.amazon.mp3.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.ConfigurableURLs;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.hawkfire.upsell.UnsupportedMarketplaceException;
import com.amazon.mp3.util.Log;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegalInformationFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/activity/settings/LegalInformationFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "isChildPrivacyNoticeEnabled", "", "isYourAdPrivacyChoiceEnabled", "modifyPreferencesBasedOnMarketPlace", "", "onCreatePreferences", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "onPreferenceTreeClick", "preference", "Landroidx/preference/Preference;", "sendMenuTapEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "showLegalWebView", "urlKey", "Lcom/amazon/mp3/environment/url/ConfigurableURLs;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LegalInformationFragment extends PreferenceFragmentCompat {
    private final String TAG = LegalInformationFragment.class.getSimpleName();

    /* JADX INFO: compiled from: LegalInformationFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Setting.values().length];
            iArr[Setting.TERMS_OF_USE.ordinal()] = 1;
            iArr[Setting.ALEXA_DEVICE_TERMS.ordinal()] = 2;
            iArr[Setting.PRIVACY_NOTES.ordinal()] = 3;
            iArr[Setting.CHILDREN_PRIVACY_NOTICE.ordinal()] = 4;
            iArr[Setting.ATTRIBUTION_NOTICES.ordinal()] = 5;
            iArr[Setting.AD_PRIVACY_CHOICES.ordinal()] = 6;
            iArr[Setting.ACCOUNT_DELETION.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.legal_information_preferences, rootKey);
        modifyPreferencesBasedOnMarketPlace();
    }

    private final void modifyPreferencesBasedOnMarketPlace() {
        if (!isYourAdPrivacyChoiceEnabled()) {
            Context context = getContext();
            String string = context == null ? null : context.getString(R.string.setting_key_legal_ad_privacy_choices);
            Preference preferenceFindPreference = string == null ? null : findPreference(string);
            if (preferenceFindPreference != null) {
                preferenceFindPreference.setVisible(false);
            }
        }
        if (isChildPrivacyNoticeEnabled()) {
            return;
        }
        Context context2 = getContext();
        String string2 = context2 == null ? null : context2.getString(R.string.setting_key_legal_children_privacy_notice);
        Preference preferenceFindPreference2 = string2 != null ? findPreference(string2) : null;
        if (preferenceFindPreference2 == null) {
            return;
        }
        preferenceFindPreference2.setVisible(false);
    }

    private final boolean isChildPrivacyNoticeEnabled() {
        return MusicTerritory.UK.equalsIgnoreCase(AccountDetailUtil.getMusicTerritoryOfResidence());
    }

    private final boolean isYourAdPrivacyChoiceEnabled() {
        return AccountDetailUtil.get(getContext()).getHomeMarketPlace().getId().equals(Marketplace.USA.getId()) && Feature.your_ad_privacy_choices_legal_setting_menu.isEnabled();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != null) {
            Setting clickedSetting = SettingsUtil.getClickedSetting(preference, getContext());
            switch (clickedSetting == null ? -1 : WhenMappings.$EnumSwitchMapping$0[clickedSetting.ordinal()]) {
                case 1:
                    return showLegalWebView(ConfigurableURLs.LEGAL_TERMS_OF_USE_URL, ActionType.LEGAL_SETTING_TERMS_OF_USE_CLICK);
                case 2:
                    return showLegalWebView(ConfigurableURLs.LEGAL_ALEXA_DEVICE_TERMS, ActionType.LEGAL_SETTING_ALEXA_DEVICE_TERMS_CLICK);
                case 3:
                    return showLegalWebView(ConfigurableURLs.LEGAL_PRIVACY_NOTES_URL, ActionType.LEGAL_SETTING_PRIVACY_NOTES_CLICK);
                case 4:
                    showLegalWebView(ConfigurableURLs.LEGAL_CHILDREN_PRIVACY_NOTICE, ActionType.LEGAL_SETTING_CHILDREN_PRIVACY_NOTICE_CLICK);
                    break;
                case 5:
                    showLegalWebView(ConfigurableURLs.LEGAL_ATTRIBUTION_NOTICES, ActionType.LEGAL_SETTING_ATTRIBUTION_NOTICES_CLICK);
                    break;
                case 6:
                    return showLegalWebView(ConfigurableURLs.LEGAL_AD_PRIVACY_CHOICES_URL, ActionType.LEGAL_SETTING_AD_PRIVACY_CHOICES_CLICK);
                case 7:
                    return showLegalWebView(ConfigurableURLs.LEGAL_ACCOUNT_DELETION, ActionType.LEGAL_SETTING_ACCOUNT_DELETION_CLICK);
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    private final boolean showLegalWebView(ConfigurableURLs urlKey, ActionType actionType) {
        try {
            String string = EndpointsProvider.get().getConfigurableURLEndPoint(urlKey).toString();
            if (string == null) {
                return true;
            }
            sendMenuTapEvent(actionType);
            Uri uri = Uri.parse(string);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(url)");
            startActivity(new Intent("android.intent.action.VIEW", uri));
            return true;
        } catch (UnsupportedMarketplaceException e) {
            Log.error(this.TAG, "No legal URI returned by Configuration! Check home market place and device language.", e);
            return true;
        }
    }

    private final void sendMenuTapEvent(ActionType actionType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(PageType.SETTINGS).withPageSubType(PageSubType.LEGAL_INFORMATION.name()).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
    }
}
