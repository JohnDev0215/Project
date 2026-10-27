package com.android.car.settings.tts;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TtsEngines;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;

/* JADX INFO: loaded from: classes5.dex */
public class PreferredEngineOptionsPreferenceController extends PreferenceController<PreferenceGroup> {
    private static final Logger LOG = new Logger((Class<?>) PreferredEngineOptionsPreferenceController.class);
    private final TtsEngines mEnginesHelper;
    private boolean mIsStarted;
    private String mPreviousEngine;
    private TextToSpeech mTts;

    public PreferredEngineOptionsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mEnginesHelper = new TtsEngines(getContext());
        this.mIsStarted = false;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mTts = new TextToSpeech(getContext(), null);
        for (final TextToSpeech.EngineInfo engine : this.mEnginesHelper.getEngines()) {
            CarUiPreference preference = new CarUiPreference(getContext());
            preference.setKey(engine.name);
            preference.setTitle(engine.label);
            preference.setShowChevron(false);
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.tts.-$$Lambda$PreferredEngineOptionsPreferenceController$UIehK_cT9d1fCRW92TVLr-6W-cU
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return this.f$0.lambda$onCreateInternal$0$PreferredEngineOptionsPreferenceController(engine, preference2);
                }
            });
            getPreference().addPreference(preference);
        }
    }

    public /* synthetic */ boolean lambda$onCreateInternal$0$PreferredEngineOptionsPreferenceController(TextToSpeech.EngineInfo engine, Preference pref) {
        TextToSpeech.EngineInfo current = this.mEnginesHelper.getEngineInfo(this.mTts.getCurrentEngine());
        if (TextUtils.equals(engine.label, current.label)) {
            return false;
        }
        updateDefaultEngine(engine.name);
        return true;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mIsStarted = true;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mIsStarted = false;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            this.mTts = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        TextToSpeech.EngineInfo current = this.mEnginesHelper.getEngineInfo(this.mTts.getCurrentEngine());
        for (int i = 0; i < preference.getPreferenceCount(); i++) {
            CarUiPreference pref = (CarUiPreference) preference.getPreference(i);
            if (pref.getTitle().equals(current.label)) {
                pref.setSummary(R.string.text_to_speech_current_engine);
            } else {
                pref.setSummary("");
            }
        }
    }

    private void updateDefaultEngine(String engineName) {
        LOG.d("Updating default synth to : " + engineName);
        this.mPreviousEngine = this.mTts.getCurrentEngine();
        LOG.i("Shutting down current tts engine");
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
        LOG.i("Updating engine : Attempting to connect to engine: " + engineName);
        this.mTts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() { // from class: com.android.car.settings.tts.-$$Lambda$PreferredEngineOptionsPreferenceController$OkxtlyKA8-6uz09BxWEqO_UIN-I
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public final void onInit(int i) {
                this.f$0.lambda$updateDefaultEngine$1$PreferredEngineOptionsPreferenceController(i);
            }
        }, engineName);
        LOG.i("Success");
    }

    public /* synthetic */ void lambda$updateDefaultEngine$1$PreferredEngineOptionsPreferenceController(int status) {
        if (this.mIsStarted) {
            onUpdateEngine(status);
            refreshUi();
        }
    }

    private void onUpdateEngine(int status) {
        if (status == 0) {
            LOG.d("Updating engine: Successfully bound to the engine: " + this.mTts.getCurrentEngine());
            Settings.Secure.putString(getContext().getContentResolver(), "tts_default_synth", this.mTts.getCurrentEngine());
            return;
        }
        LOG.d("Updating engine: Failed to bind to engine, reverting.");
        if (this.mPreviousEngine != null) {
            this.mTts = new TextToSpeech(getContext(), null, this.mPreviousEngine);
        }
        this.mPreviousEngine = null;
    }
}
