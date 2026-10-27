package com.android.car.developeroptions.dream;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.dream.DreamBackend;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class StartNowPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final DreamBackend mBackend;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "dream_start_now_button_container";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public StartNowPreferenceController(Context context) {
        super(context);
        this.mBackend = DreamBackend.getInstance(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ((Button) ((LayoutPreference) preferenceScreen.findPreference(getPreferenceKey())).findViewById(R.id.dream_start_now_button)).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.dream.-$$Lambda$StartNowPreferenceController$GzBolJeR_x--eRDG3LiesG80uTs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$0$StartNowPreferenceController(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$StartNowPreferenceController(View view) {
        this.mBackend.startDreaming();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        ((Button) ((LayoutPreference) preference).findViewById(R.id.dream_start_now_button)).setEnabled(this.mBackend.getWhenToDreamSetting() != 3);
    }
}
