package com.android.car.settings.applications.assist;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TextContextPreferenceController extends AssistConfigBasePreferenceController {
    public TextContextPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setChecked(isAssistContextEnabled());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference twoStatePreference, Object obj) {
        Settings.Secure.putInt(getContext().getContentResolver(), "assist_structure_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    @Override // com.android.car.settings.applications.assist.AssistConfigBasePreferenceController
    protected List<Uri> getSettingUris() {
        return Collections.singletonList(Settings.Secure.getUriFor("assist_structure_enabled"));
    }

    private boolean isAssistContextEnabled() {
        return Settings.Secure.getInt(getContext().getContentResolver(), "assist_structure_enabled", 1) != 0;
    }
}
