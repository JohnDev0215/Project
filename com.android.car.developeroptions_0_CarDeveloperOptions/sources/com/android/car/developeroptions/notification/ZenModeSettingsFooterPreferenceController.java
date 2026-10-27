package com.android.car.developeroptions.notification;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.ListFormatter;
import android.net.Uri;
import android.os.Bundle;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.notification.ZenModeSettings;
import com.android.car.developeroptions.utils.AnnotationSpan;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeSettingsFooterPreferenceController extends AbstractZenModePreferenceController {
    private FragmentManager mFragment;

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "footer_preference";
    }

    public ZenModeSettingsFooterPreferenceController(Context context, Lifecycle lifecycle, FragmentManager fragmentManager) {
        super(context, "footer_preference", lifecycle);
        this.mFragment = fragmentManager;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        int zenMode = getZenMode();
        return zenMode == 1 || zenMode == 2 || zenMode == 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        boolean zIsAvailable = isAvailable();
        preference.setVisible(zIsAvailable);
        if (zIsAvailable) {
            preference.setTitle(getFooterText());
        }
    }

    protected CharSequence getFooterText() {
        ZenModeConfig zenModeConfig = getZenModeConfig();
        if (!Objects.equals(this.mBackend.getConsolidatedPolicy(), zenModeConfig.toNotificationPolicy())) {
            List<ZenModeConfig.ZenRule> activeRules = getActiveRules(zenModeConfig);
            ArrayList arrayList = new ArrayList();
            Iterator<ZenModeConfig.ZenRule> it = activeRules.iterator();
            while (it.hasNext()) {
                String str = it.next().name;
                if (str != null) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() > 0) {
                String str2 = ListFormatter.getInstance().format(arrayList);
                if (!str2.isEmpty()) {
                    return TextUtils.concat(this.mContext.getResources().getString(R.string.zen_mode_settings_dnd_custom_settings_footer, str2), AnnotationSpan.linkify(this.mContext.getResources().getText(R.string.zen_mode_settings_dnd_custom_settings_footer_link), new AnnotationSpan.LinkInfo("link", new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.ZenModeSettingsFooterPreferenceController.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            ZenModeSettingsFooterPreferenceController.this.showCustomSettingsDialog();
                        }
                    })));
                }
            }
        }
        return getDefaultPolicyFooter(zenModeConfig);
    }

    private String getDefaultPolicyFooter(ZenModeConfig zenModeConfig) {
        ZenModeConfig.ZenRule zenRule = zenModeConfig.manualRule;
        String string = "";
        long manualRuleTime = -1;
        if (zenRule != null) {
            Uri uri = zenRule.conditionId;
            String str = zenRule.enabler;
            if (str != null) {
                String ownerCaption = AbstractZenModePreferenceController.mZenModeConfigWrapper.getOwnerCaption(str);
                if (!ownerCaption.isEmpty()) {
                    string = this.mContext.getString(R.string.zen_mode_settings_dnd_automatic_rule_app, ownerCaption);
                }
            } else {
                if (uri == null) {
                    return this.mContext.getString(R.string.zen_mode_settings_dnd_manual_indefinite);
                }
                manualRuleTime = AbstractZenModePreferenceController.mZenModeConfigWrapper.parseManualRuleTime(uri);
                if (manualRuleTime > 0) {
                    string = this.mContext.getString(R.string.zen_mode_settings_dnd_manual_end_time, AbstractZenModePreferenceController.mZenModeConfigWrapper.getFormattedTime(manualRuleTime, this.mContext.getUserId()));
                }
            }
        }
        for (ZenModeConfig.ZenRule zenRule2 : zenModeConfig.automaticRules.values()) {
            if (zenRule2.isAutomaticActive()) {
                if (!AbstractZenModePreferenceController.mZenModeConfigWrapper.isTimeRule(zenRule2.conditionId)) {
                    return this.mContext.getString(R.string.zen_mode_settings_dnd_automatic_rule, zenRule2.name);
                }
                long automaticRuleEndTime = AbstractZenModePreferenceController.mZenModeConfigWrapper.parseAutomaticRuleEndTime(zenRule2.conditionId);
                if (automaticRuleEndTime > manualRuleTime) {
                    string = this.mContext.getString(R.string.zen_mode_settings_dnd_automatic_rule, zenRule2.name);
                    manualRuleTime = automaticRuleEndTime;
                }
            }
        }
        return string;
    }

    private List<ZenModeConfig.ZenRule> getActiveRules(ZenModeConfig zenModeConfig) {
        ArrayList arrayList = new ArrayList();
        ZenModeConfig.ZenRule zenRule = zenModeConfig.manualRule;
        if (zenRule != null) {
            arrayList.add(zenRule);
        }
        for (ZenModeConfig.ZenRule zenRule2 : zenModeConfig.automaticRules.values()) {
            if (zenRule2.isAutomaticActive()) {
                arrayList.add(zenRule2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCustomSettingsDialog() {
        ZenCustomSettingsDialog zenCustomSettingsDialog = new ZenCustomSettingsDialog();
        zenCustomSettingsDialog.setNotificationPolicy(this.mBackend.getConsolidatedPolicy());
        zenCustomSettingsDialog.show(this.mFragment, ZenCustomSettingsDialog.class.getName());
    }

    public static class ZenCustomSettingsDialog extends InstrumentedDialogFragment {
        private String KEY_POLICY = "policy";
        private NotificationManager.Policy mPolicy;
        private ZenModeSettings.SummaryBuilder mSummaryBuilder;

        /* JADX INFO: Access modifiers changed from: private */
        public int getAllowRes(boolean z) {
            return z ? R.string.zen_mode_sound_summary_on : R.string.zen_mode_sound_summary_off;
        }

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 1612;
        }

        public void setNotificationPolicy(NotificationManager.Policy policy) {
            this.mPolicy = policy;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            NotificationManager.Policy policy;
            final FragmentActivity activity = getActivity();
            if (bundle != null && (policy = (NotificationManager.Policy) bundle.getParcelable(this.KEY_POLICY)) != null) {
                this.mPolicy = policy;
            }
            this.mSummaryBuilder = new ZenModeSettings.SummaryBuilder(activity);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.zen_custom_settings_dialog_title);
            builder.setNeutralButton(R.string.zen_custom_settings_dialog_review_schedule, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.notification.ZenModeSettingsFooterPreferenceController.ZenCustomSettingsDialog.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    SubSettingLauncher subSettingLauncher = new SubSettingLauncher(activity);
                    subSettingLauncher.setDestination(ZenModeAutomationSettings.class.getName());
                    subSettingLauncher.setSourceMetricsCategory(142);
                    subSettingLauncher.launch();
                }
            });
            builder.setPositiveButton(R.string.zen_custom_settings_dialog_ok, (DialogInterface.OnClickListener) null);
            builder.setView(LayoutInflater.from(activity).inflate((XmlPullParser) activity.getResources().getLayout(R.layout.zen_custom_settings_dialog), (ViewGroup) null, false));
            final AlertDialog alertDialogCreate = builder.create();
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.developeroptions.notification.ZenModeSettingsFooterPreferenceController.ZenCustomSettingsDialog.2
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    TextView textView = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_calls_allow);
                    TextView textView2 = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_messages_allow);
                    TextView textView3 = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_alarms_allow);
                    TextView textView4 = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_media_allow);
                    TextView textView5 = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_system_allow);
                    TextView textView6 = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_reminders_allow);
                    TextView textView7 = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_events_allow);
                    TextView textView8 = (TextView) alertDialogCreate.findViewById(R.id.zen_custom_settings_dialog_show_notifications);
                    textView.setText(ZenCustomSettingsDialog.this.mSummaryBuilder.getCallsSettingSummary(ZenCustomSettingsDialog.this.mPolicy));
                    textView2.setText(ZenCustomSettingsDialog.this.mSummaryBuilder.getMessagesSettingSummary(ZenCustomSettingsDialog.this.mPolicy));
                    ZenCustomSettingsDialog zenCustomSettingsDialog = ZenCustomSettingsDialog.this;
                    textView3.setText(zenCustomSettingsDialog.getAllowRes(zenCustomSettingsDialog.mPolicy.allowAlarms()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog2 = ZenCustomSettingsDialog.this;
                    textView4.setText(zenCustomSettingsDialog2.getAllowRes(zenCustomSettingsDialog2.mPolicy.allowMedia()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog3 = ZenCustomSettingsDialog.this;
                    textView5.setText(zenCustomSettingsDialog3.getAllowRes(zenCustomSettingsDialog3.mPolicy.allowSystem()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog4 = ZenCustomSettingsDialog.this;
                    textView6.setText(zenCustomSettingsDialog4.getAllowRes(zenCustomSettingsDialog4.mPolicy.allowReminders()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog5 = ZenCustomSettingsDialog.this;
                    textView7.setText(zenCustomSettingsDialog5.getAllowRes(zenCustomSettingsDialog5.mPolicy.allowEvents()));
                    textView8.setText(ZenCustomSettingsDialog.this.mSummaryBuilder.getBlockedEffectsSummary(ZenCustomSettingsDialog.this.mPolicy));
                }
            });
            return alertDialogCreate;
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putParcelable(this.KEY_POLICY, this.mPolicy);
        }
    }
}
