package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.notification.ZenDeleteRuleDialog;
import com.android.car.developeroptions.notification.ZenRuleNameDialog;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.ActionButtonsPreference;

/* JADX INFO: loaded from: classes.dex */
public class ZenRuleButtonsPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    private PreferenceFragmentCompat mFragment;
    private String mId;
    private AutomaticZenRule mRule;

    public ZenRuleButtonsPreferenceController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, Lifecycle lifecycle) {
        super(context, "zen_action_buttons", lifecycle);
        this.mFragment = preferenceFragmentCompat;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mRule != null;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        if (isAvailable()) {
            ActionButtonsPreference actionButtonsPreference = (ActionButtonsPreference) preferenceScreen.findPreference("zen_action_buttons");
            actionButtonsPreference.setButton1Text(R.string.zen_mode_rule_name_edit);
            actionButtonsPreference.setButton1Icon(android.R.drawable.ic_media_route_connecting_dark_01_mtrl);
            actionButtonsPreference.setButton1OnClickListener(new EditRuleNameClickListener());
            actionButtonsPreference.setButton2Text(R.string.zen_mode_delete_rule_button);
            actionButtonsPreference.setButton2Icon(R.drawable.ic_settings_delete);
            actionButtonsPreference.setButton2OnClickListener(new DeleteRuleClickListener());
        }
    }

    public class EditRuleNameClickListener implements View.OnClickListener {
        public EditRuleNameClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ZenRuleNameDialog.show(ZenRuleButtonsPreferenceController.this.mFragment, ZenRuleButtonsPreferenceController.this.mRule.getName(), null, new ZenRuleNameDialog.PositiveClickListener() { // from class: com.android.car.developeroptions.notification.ZenRuleButtonsPreferenceController.EditRuleNameClickListener.1
                @Override // com.android.car.developeroptions.notification.ZenRuleNameDialog.PositiveClickListener
                public void onOk(String str, Fragment fragment) {
                    if (TextUtils.equals(str, ZenRuleButtonsPreferenceController.this.mRule.getName())) {
                        return;
                    }
                    ZenRuleButtonsPreferenceController zenRuleButtonsPreferenceController = ZenRuleButtonsPreferenceController.this;
                    zenRuleButtonsPreferenceController.mMetricsFeatureProvider.action(((AbstractPreferenceController) zenRuleButtonsPreferenceController).mContext, 1267, new Pair[0]);
                    ZenRuleButtonsPreferenceController.this.mRule.setName(str);
                    ZenRuleButtonsPreferenceController.this.mRule.setModified(true);
                    ZenRuleButtonsPreferenceController zenRuleButtonsPreferenceController2 = ZenRuleButtonsPreferenceController.this;
                    zenRuleButtonsPreferenceController2.mBackend.updateZenRule(zenRuleButtonsPreferenceController2.mId, ZenRuleButtonsPreferenceController.this.mRule);
                }
            });
        }
    }

    public class DeleteRuleClickListener implements View.OnClickListener {
        public DeleteRuleClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ZenDeleteRuleDialog.show(ZenRuleButtonsPreferenceController.this.mFragment, ZenRuleButtonsPreferenceController.this.mRule.getName(), ZenRuleButtonsPreferenceController.this.mId, new ZenDeleteRuleDialog.PositiveClickListener() { // from class: com.android.car.developeroptions.notification.ZenRuleButtonsPreferenceController.DeleteRuleClickListener.1
                @Override // com.android.car.developeroptions.notification.ZenDeleteRuleDialog.PositiveClickListener
                public void onOk(String str) {
                    Bundle bundle = new Bundle();
                    bundle.putString("DELETE_RULE", str);
                    ZenRuleButtonsPreferenceController zenRuleButtonsPreferenceController = ZenRuleButtonsPreferenceController.this;
                    zenRuleButtonsPreferenceController.mMetricsFeatureProvider.action(((AbstractPreferenceController) zenRuleButtonsPreferenceController).mContext, 175, new Pair[0]);
                    SubSettingLauncher subSettingLauncher = new SubSettingLauncher(((AbstractPreferenceController) ZenRuleButtonsPreferenceController.this).mContext);
                    subSettingLauncher.addFlags(67108864);
                    subSettingLauncher.setDestination(ZenModeAutomationSettings.class.getName());
                    subSettingLauncher.setSourceMetricsCategory(142);
                    subSettingLauncher.setArguments(bundle);
                    subSettingLauncher.launch();
                }
            });
        }
    }

    protected void onResume(AutomaticZenRule automaticZenRule, String str) {
        this.mRule = automaticZenRule;
        this.mId = str;
    }
}
