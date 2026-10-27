package com.android.car.developeroptions.notification;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeButtonPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    private FragmentManager mFragment;
    private Button mZenButtonOff;
    private Button mZenButtonOn;

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_settings_button_container";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeButtonPreferenceController(Context context, Lifecycle lifecycle, FragmentManager fragmentManager) {
        super(context, "zen_mode_settings_button_container", lifecycle);
        this.mFragment = fragmentManager;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mZenButtonOn == null) {
            this.mZenButtonOn = (Button) ((LayoutPreference) preference).findViewById(R.id.zen_mode_settings_turn_on_button);
            updateZenButtonOnClickListener();
        }
        if (this.mZenButtonOff == null) {
            Button button = (Button) ((LayoutPreference) preference).findViewById(R.id.zen_mode_settings_turn_off_button);
            this.mZenButtonOff = button;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeButtonPreferenceController$GG5aOf0Hhq3SOp7807iHHyT2RXg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$updateState$0$ZenModeButtonPreferenceController(view);
                }
            });
        }
        updateButtons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateState$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateState$0$ZenModeButtonPreferenceController(View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        this.mBackend.setZenMode(0);
    }

    private void updateButtons() {
        int zenMode = getZenMode();
        if (zenMode == 1 || zenMode == 2 || zenMode == 3) {
            this.mZenButtonOff.setVisibility(0);
            this.mZenButtonOn.setVisibility(8);
        } else {
            this.mZenButtonOff.setVisibility(8);
            updateZenButtonOnClickListener();
            this.mZenButtonOn.setVisibility(0);
        }
    }

    private void updateZenButtonOnClickListener() {
        final int zenDuration = getZenDuration();
        if (zenDuration == -1) {
            this.mZenButtonOn.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeButtonPreferenceController$Uxi5W2uU5OZv0tp2Dw_idoaa9Kg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$updateZenButtonOnClickListener$1$ZenModeButtonPreferenceController(view);
                }
            });
        } else if (zenDuration == 0) {
            this.mZenButtonOn.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeButtonPreferenceController$wvROha89JenhpB-2372wkOU0080
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$updateZenButtonOnClickListener$2$ZenModeButtonPreferenceController(view);
                }
            });
        } else {
            this.mZenButtonOn.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeButtonPreferenceController$NCaPF7vcBZ3wEgzkKByKfe2vGi4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$updateZenButtonOnClickListener$3$ZenModeButtonPreferenceController(zenDuration, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateZenButtonOnClickListener$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateZenButtonOnClickListener$1$ZenModeButtonPreferenceController(View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        new SettingsEnableZenModeDialog().show(this.mFragment, "EnableZenModeButton");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateZenButtonOnClickListener$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateZenButtonOnClickListener$2$ZenModeButtonPreferenceController(View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        this.mBackend.setZenMode(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateZenButtonOnClickListener$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateZenButtonOnClickListener$3$ZenModeButtonPreferenceController(int i, View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        this.mBackend.setZenModeForDuration(i);
    }
}
