package com.android.car.settings.tts;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TtsEngines;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiTwoActionIconPreference;

/* JADX INFO: loaded from: classes5.dex */
public class PreferredEngineEntryPreferenceController extends PreferenceController<CarUiTwoActionIconPreference> {
    private static final Logger LOG = new Logger((Class<?>) PreferredEngineEntryPreferenceController.class);
    private TtsEngines mEnginesHelper;

    public PreferredEngineEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mEnginesHelper = new TtsEngines(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<CarUiTwoActionIconPreference> getPreferenceType() {
        return CarUiTwoActionIconPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        getPreference().setOnSecondaryActionClickListener(new Runnable() { // from class: com.android.car.settings.tts.-$$Lambda$PreferredEngineEntryPreferenceController$ZNBZJSH6W_FdZMCfowdYNCTHBuI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreateInternal$0$PreferredEngineEntryPreferenceController();
            }
        });
    }

    public /* synthetic */ void lambda$onCreateInternal$0$PreferredEngineEntryPreferenceController() {
        TtsEngines ttsEngines = this.mEnginesHelper;
        TextToSpeech.EngineInfo info = ttsEngines.getEngineInfo(ttsEngines.getDefaultEngine());
        Intent subSettingsIntent = this.mEnginesHelper.getSettingsIntent(info.name);
        if (subSettingsIntent != null) {
            getContext().startActivity(subSettingsIntent);
        } else {
            LOG.e("subSettingsIntent is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(CarUiTwoActionIconPreference preference) {
        TtsEngines ttsEngines = this.mEnginesHelper;
        TextToSpeech.EngineInfo info = ttsEngines.getEngineInfo(ttsEngines.getDefaultEngine());
        preference.setSummary(info.label);
    }
}
