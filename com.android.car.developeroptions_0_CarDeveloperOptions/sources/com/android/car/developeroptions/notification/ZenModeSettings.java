package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.app.NotificationManager;
import android.content.Context;
import android.provider.SearchIndexableResource;
import android.service.notification.ZenModeConfig;
import androidx.fragment.app.FragmentManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.notification.ZenModeSettings;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeSettings extends ZenModeSettingsBase {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.notification.ZenModeSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.zen_mode_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_interruptions;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 76;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_settings;
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle(), getFragmentManager());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle, FragmentManager fragmentManager) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZenModeCallsPreferenceController(context, lifecycle, "zen_mode_behavior_calls"));
        arrayList.add(new ZenModeMessagesPreferenceController(context, lifecycle, "zen_mode_behavior_messages"));
        arrayList.add(new ZenModeBlockedEffectsPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeDurationPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeAutomationPreferenceController(context));
        arrayList.add(new ZenModeButtonPreferenceController(context, lifecycle, fragmentManager));
        arrayList.add(new ZenModeSettingsFooterPreferenceController(context, lifecycle, fragmentManager));
        return arrayList;
    }

    public static class SummaryBuilder {
        private static final int[] ALL_PRIORITY_CATEGORIES = {32, 64, 128, 4, 2, 1, 8, 16};
        private Context mContext;

        public SummaryBuilder(Context context) {
            this.mContext = context;
        }

        String getCallsSettingSummary(NotificationManager.Policy policy) {
            List<String> enabledCategories = getEnabledCategories(policy, new Predicate() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeSettings$SummaryBuilder$YUsXLwjuPEqKDzwWBQvpEcw9IhQ
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ZenModeSettings.SummaryBuilder.lambda$getCallsSettingSummary$1((Integer) obj);
                }
            }, false);
            int size = enabledCategories.size();
            if (size == 0) {
                return this.mContext.getString(R.string.zen_mode_from_none_calls);
            }
            if (size == 1) {
                return this.mContext.getString(R.string.zen_mode_calls_summary_one, enabledCategories.get(0));
            }
            return this.mContext.getString(R.string.zen_mode_calls_summary_two, enabledCategories.get(0), enabledCategories.get(1));
        }

        static /* synthetic */ boolean lambda$getCallsSettingSummary$1(Integer num) {
            return 8 == num.intValue() || 16 == num.intValue();
        }

        String getMessagesSettingSummary(NotificationManager.Policy policy) {
            List<String> enabledCategories = getEnabledCategories(policy, new Predicate() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeSettings$SummaryBuilder$kET1r8bbNYogoCFb7DDW0h4CwQg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ZenModeSettings.SummaryBuilder.lambda$getMessagesSettingSummary$2((Integer) obj);
                }
            }, false);
            if (enabledCategories.size() == 0) {
                return this.mContext.getString(R.string.zen_mode_from_none_messages);
            }
            return enabledCategories.get(0);
        }

        static /* synthetic */ boolean lambda$getMessagesSettingSummary$2(Integer num) {
            return 4 == num.intValue();
        }

        String getSoundSummary() {
            if (NotificationManager.from(this.mContext).getZenMode() != 0) {
                String description = ZenModeConfig.getDescription(this.mContext, true, NotificationManager.from(this.mContext).getZenModeConfig(), false);
                return description == null ? this.mContext.getString(R.string.zen_mode_sound_summary_on) : this.mContext.getString(R.string.zen_mode_sound_summary_on_with_info, description);
            }
            int enabledAutomaticRulesCount = getEnabledAutomaticRulesCount();
            if (enabledAutomaticRulesCount > 0) {
                Context context = this.mContext;
                return context.getString(R.string.zen_mode_sound_summary_off_with_info, context.getResources().getQuantityString(R.plurals.zen_mode_sound_summary_summary_off_info, enabledAutomaticRulesCount, Integer.valueOf(enabledAutomaticRulesCount)));
            }
            return this.mContext.getString(R.string.zen_mode_sound_summary_off);
        }

        String getBlockedEffectsSummary(NotificationManager.Policy policy) {
            int i = policy.suppressedVisualEffects;
            if (i == 0) {
                return this.mContext.getResources().getString(R.string.zen_mode_restrict_notifications_summary_muted);
            }
            if (NotificationManager.Policy.areAllVisualEffectsSuppressed(i)) {
                return this.mContext.getResources().getString(R.string.zen_mode_restrict_notifications_summary_hidden);
            }
            return this.mContext.getResources().getString(R.string.zen_mode_restrict_notifications_summary_custom);
        }

        String getAutomaticRulesSummary() {
            int enabledAutomaticRulesCount = getEnabledAutomaticRulesCount();
            if (enabledAutomaticRulesCount == 0) {
                return this.mContext.getString(R.string.zen_mode_settings_summary_off);
            }
            return this.mContext.getResources().getQuantityString(R.plurals.zen_mode_settings_summary_on, enabledAutomaticRulesCount, Integer.valueOf(enabledAutomaticRulesCount));
        }

        int getEnabledAutomaticRulesCount() {
            Map<String, AutomaticZenRule> automaticZenRules = NotificationManager.from(this.mContext).getAutomaticZenRules();
            int i = 0;
            if (automaticZenRules != null) {
                Iterator<Map.Entry<String, AutomaticZenRule>> it = automaticZenRules.entrySet().iterator();
                while (it.hasNext()) {
                    AutomaticZenRule value = it.next().getValue();
                    if (value != null && value.isEnabled()) {
                        i++;
                    }
                }
            }
            return i;
        }

        private List<String> getEnabledCategories(NotificationManager.Policy policy, Predicate<Integer> predicate, boolean z) {
            ArrayList arrayList = new ArrayList();
            for (int i : ALL_PRIORITY_CATEGORIES) {
                boolean z2 = z && arrayList.isEmpty();
                if (predicate.test(Integer.valueOf(i)) && isCategoryEnabled(policy, i) && (i != 16 || !isCategoryEnabled(policy, 8) || policy.priorityCallSenders != 0)) {
                    arrayList.add(getCategory(i, policy, z2));
                }
            }
            return arrayList;
        }

        private boolean isCategoryEnabled(NotificationManager.Policy policy, int i) {
            return (policy.priorityCategories & i) != 0;
        }

        private String getCategory(int i, NotificationManager.Policy policy, boolean z) {
            if (i == 32) {
                if (z) {
                    return this.mContext.getString(R.string.zen_mode_alarms);
                }
                return this.mContext.getString(R.string.zen_mode_alarms_list);
            }
            if (i == 64) {
                if (z) {
                    return this.mContext.getString(R.string.zen_mode_media);
                }
                return this.mContext.getString(R.string.zen_mode_media_list);
            }
            if (i == 128) {
                if (z) {
                    return this.mContext.getString(R.string.zen_mode_system);
                }
                return this.mContext.getString(R.string.zen_mode_system_list);
            }
            if (i == 4) {
                int i2 = policy.priorityMessageSenders;
                if (i2 == 0) {
                    return this.mContext.getString(R.string.zen_mode_from_anyone);
                }
                if (i2 == 1) {
                    return this.mContext.getString(R.string.zen_mode_from_contacts);
                }
                return this.mContext.getString(R.string.zen_mode_from_starred);
            }
            if (i == 2) {
                if (z) {
                    return this.mContext.getString(R.string.zen_mode_events);
                }
                return this.mContext.getString(R.string.zen_mode_events_list);
            }
            if (i == 1) {
                if (z) {
                    return this.mContext.getString(R.string.zen_mode_reminders);
                }
                return this.mContext.getString(R.string.zen_mode_reminders_list);
            }
            if (i != 8) {
                if (i != 16) {
                    return "";
                }
                if (z) {
                    return this.mContext.getString(R.string.zen_mode_repeat_callers);
                }
                return this.mContext.getString(R.string.zen_mode_repeat_callers_list);
            }
            int i3 = policy.priorityCallSenders;
            if (i3 == 0) {
                return this.mContext.getString(R.string.zen_mode_all_callers);
            }
            if (i3 == 1) {
                return this.mContext.getString(R.string.zen_mode_contacts_callers);
            }
            return this.mContext.getString(R.string.zen_mode_starred_callers);
        }
    }
}
