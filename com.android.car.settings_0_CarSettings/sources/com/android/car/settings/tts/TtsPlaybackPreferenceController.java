package com.android.car.settings.tts;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TtsEngines;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.ActivityResultCallback;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.common.SeekBarPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class TtsPlaybackPreferenceController extends PreferenceController<PreferenceGroup> implements ActivityResultCallback {
    static final int GET_SAMPLE_TEXT = 2;
    private static final Logger LOG = new Logger((Class<?>) TtsPlaybackPreferenceController.class);
    static final int VOICE_DATA_CHECK = 1;
    Handler mBackgroundHandler;
    private HandlerThread mBackgroundHandlerThread;
    private ListPreference mDefaultLanguagePreference;
    private TtsEngines mEnginesHelper;
    private final TextToSpeech.OnInitListener mOnInitListener;
    private Preference mResetPreference;
    private String mSampleText;
    private Locale mSampleTextLocale;
    private int mSelectedLocaleIndex;
    private SeekBarPreference mSpeechRatePreference;
    private TextToSpeech mTts;
    private boolean mTtsInitialized;
    private TtsPlaybackSettingsManager mTtsPlaybackManager;
    private Handler mUiHandler;
    private SeekBarPreference mVoicePitchPreference;

    public /* synthetic */ void lambda$new$1$TtsPlaybackPreferenceController(int status) {
        if (status == 0) {
            this.mTtsInitialized = true;
            this.mTtsPlaybackManager = new TtsPlaybackSettingsManager(getContext(), this.mTts, this.mEnginesHelper);
            this.mTts.setSpeechRate(r0.getCurrentSpeechRate() / 100.0f);
            this.mTts.setPitch(this.mTtsPlaybackManager.getCurrentVoicePitch() / 100.0f);
            startEngineVoiceDataCheck(this.mTts.getCurrentEngine());
            this.mBackgroundHandler.post(new Runnable() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$feUsver_Yk6NDZqq71epJ2bwwTQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0$TtsPlaybackPreferenceController();
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$0$TtsPlaybackPreferenceController() {
        checkOrUpdateSampleText();
        this.mUiHandler.post(new $$Lambda$uuNPjUySrQywzP_6wvNi_JdqwI(this));
    }

    public TtsPlaybackPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mTtsInitialized = false;
        this.mOnInitListener = new TextToSpeech.OnInitListener() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$4FTeTZqtZTercctah_1LF0vMAuE
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public final void onInit(int i) {
                this.f$0.lambda$new$1$TtsPlaybackPreferenceController(i);
            }
        };
        this.mEnginesHelper = new TtsEngines(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mDefaultLanguagePreference = initDefaultLanguagePreference();
        this.mSpeechRatePreference = initSpeechRatePreference();
        this.mVoicePitchPreference = initVoicePitchPreference();
        this.mResetPreference = initResetTtsPlaybackPreference();
        this.mUiHandler = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread("BackgroundHandlerThread");
        this.mBackgroundHandlerThread = handlerThread;
        handlerThread.start();
        this.mBackgroundHandler = new Handler(this.mBackgroundHandlerThread.getLooper());
        this.mTts = new TextToSpeech(getContext(), this.mOnInitListener);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        HandlerThread handlerThread = this.mBackgroundHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mBackgroundHandler = null;
            this.mBackgroundHandlerThread = null;
        }
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            this.mTts = null;
            this.mTtsPlaybackManager = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        boolean isValid = isDefaultLocaleValid();
        this.mDefaultLanguagePreference.setEnabled(isValid);
        this.mDefaultLanguagePreference.setVisible(false);
        this.mSpeechRatePreference.setEnabled(isValid);
        this.mVoicePitchPreference.setEnabled(isValid);
        this.mResetPreference.setEnabled(isValid);
        if (!isValid && this.mDefaultLanguagePreference.getEntries() != null) {
            this.mDefaultLanguagePreference.setEnabled(true);
        }
        if (this.mDefaultLanguagePreference.getEntries() != null) {
            this.mDefaultLanguagePreference.setValueIndex(this.mSelectedLocaleIndex);
            ListPreference listPreference = this.mDefaultLanguagePreference;
            listPreference.setSummary(listPreference.getEntries()[this.mSelectedLocaleIndex]);
        }
        if (this.mTtsInitialized) {
            this.mSpeechRatePreference.setValue(this.mTtsPlaybackManager.getCurrentSpeechRate());
            this.mVoicePitchPreference.setValue(this.mTtsPlaybackManager.getCurrentVoicePitch());
        }
    }

    @Override // com.android.car.settings.common.ActivityResultCallback
    public void processActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            onVoiceDataIntegrityCheckDone(resultCode, data);
        } else if (requestCode == 2) {
            onSampleTextReceived(resultCode, data);
        } else {
            LOG.e("Got unknown activity result");
        }
    }

    private void startEngineVoiceDataCheck(String engine) {
        Intent intent = new Intent("android.speech.tts.engine.CHECK_TTS_DATA");
        intent.setPackage(engine);
        try {
            LOG.d("Updating engine: Checking voice data: " + intent.toUri(0));
            getFragmentController().startActivityForResult(intent, 1, this);
        } catch (ActivityNotFoundException e) {
            LOG.e("Failed to check TTS data, no activity found for " + intent);
        }
    }

    private void startGetSampleText() {
        String currentEngine = this.mTts.getCurrentEngine();
        if (TextUtils.isEmpty(currentEngine)) {
            currentEngine = this.mTts.getDefaultEngine();
        }
        Intent intent = new Intent("android.speech.tts.engine.GET_SAMPLE_TEXT");
        Locale effectiveTtsLocale = this.mTtsPlaybackManager.getEffectiveTtsLocale();
        this.mSampleTextLocale = effectiveTtsLocale;
        if (effectiveTtsLocale == null) {
            return;
        }
        intent.putExtra("language", effectiveTtsLocale.getLanguage());
        intent.putExtra("country", this.mSampleTextLocale.getCountry());
        intent.putExtra("variant", this.mSampleTextLocale.getVariant());
        intent.setPackage(currentEngine);
        try {
            LOG.d("Getting sample text: " + intent.toUri(0));
            getFragmentController().startActivityForResult(intent, 2, this);
        } catch (ActivityNotFoundException e) {
            LOG.e("Failed to get sample text, no activity found for " + intent + ")");
        }
    }

    private void onVoiceDataIntegrityCheckDone(int resultCode, Intent data) {
        String engine = this.mTts.getCurrentEngine();
        if (engine == null) {
            LOG.e("Voice data check complete, but no engine bound");
            return;
        }
        if (data == null || resultCode == 0) {
            LOG.e("Engine failed voice data integrity check (null return or invalid result code)" + this.mTts.getCurrentEngine());
            return;
        }
        Settings.Secure.putString(getContext().getContentResolver(), "tts_default_synth", engine);
        ArrayList<String> availableLangs = data.getStringArrayListExtra("availableVoices");
        if (availableLangs == null || availableLangs.size() == 0) {
            refreshUi();
            return;
        }
        updateDefaultLanguagePreference(availableLangs);
        int iFindLocaleIndex = findLocaleIndex(this.mTtsPlaybackManager.getStoredTtsLocale());
        this.mSelectedLocaleIndex = iFindLocaleIndex;
        if (iFindLocaleIndex < 0) {
            this.mSelectedLocaleIndex = 0;
        }
        this.mBackgroundHandler.post(new Runnable() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$ql2YWDqO52XOERXt1b9KFiasAto
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onVoiceDataIntegrityCheckDone$2$TtsPlaybackPreferenceController();
            }
        });
    }

    public /* synthetic */ void lambda$onVoiceDataIntegrityCheckDone$2$TtsPlaybackPreferenceController() {
        startGetSampleText();
        this.mUiHandler.post(new $$Lambda$uuNPjUySrQywzP_6wvNi_JdqwI(this));
    }

    private void onSampleTextReceived(int resultCode, Intent data) {
        String sample = getContext().getString(R.string.tts_default_sample_string);
        if (resultCode == 0 && data != null) {
            String tmp = data.getStringExtra("sampleText");
            if (!TextUtils.isEmpty(tmp)) {
                sample = tmp;
            }
            LOG.d("Got sample text: " + sample);
        } else {
            LOG.d("Using default sample text :" + sample);
        }
        this.mSampleText = sample;
    }

    private void updateLanguageTo(Locale locale) {
        int selectedLocaleIndex = findLocaleIndex(locale);
        if (selectedLocaleIndex == -1) {
            LOG.w("updateLanguageTo called with unknown locale argument");
        } else {
            if (this.mTtsPlaybackManager.updateTtsLocale(locale)) {
                this.mSelectedLocaleIndex = selectedLocaleIndex;
                checkOrUpdateSampleText();
                refreshUi();
                return;
            }
            LOG.e("updateLanguageTo failed to update tts language");
        }
    }

    private int findLocaleIndex(Locale locale) {
        String localeString = locale != null ? locale.toString() : "";
        return this.mDefaultLanguagePreference.findIndexOfValue(localeString);
    }

    private boolean isDefaultLocaleValid() {
        if (!this.mTtsInitialized) {
            return false;
        }
        if (this.mSampleTextLocale == null) {
            LOG.e("Default language was not retrieved from engine " + this.mTts.getCurrentEngine());
            return false;
        }
        if (this.mDefaultLanguagePreference.getEntries() == null) {
            return false;
        }
        int index = this.mDefaultLanguagePreference.findIndexOfValue(this.mSampleTextLocale.toString());
        return index >= 0;
    }

    private void checkOrUpdateSampleText() {
        if (!this.mTtsInitialized) {
            return;
        }
        Locale defaultLocale = this.mTtsPlaybackManager.getEffectiveTtsLocale();
        if (defaultLocale == null) {
            LOG.e("Failed to get default language from engine " + this.mTts.getCurrentEngine());
            return;
        }
        if (!Objects.equals(defaultLocale, this.mSampleTextLocale)) {
            this.mSampleText = null;
            this.mSampleTextLocale = null;
        }
        if (this.mSampleText == null) {
            startGetSampleText();
        }
    }

    String getSampleText() {
        return this.mSampleText;
    }

    private ListPreference initDefaultLanguagePreference() {
        ListPreference defaultLanguagePreference = (ListPreference) getPreference().findPreference(getContext().getString(R.string.pk_tts_default_language));
        defaultLanguagePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$2dVUI6--IxQ366hlXWFN7VB_8Vg
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$initDefaultLanguagePreference$3$TtsPlaybackPreferenceController(preference, obj);
            }
        });
        return defaultLanguagePreference;
    }

    public /* synthetic */ boolean lambda$initDefaultLanguagePreference$3$TtsPlaybackPreferenceController(Preference preference, Object newValue) {
        String localeString = (String) newValue;
        updateLanguageTo(!TextUtils.isEmpty(localeString) ? this.mEnginesHelper.parseLocaleString(localeString) : null);
        checkOrUpdateSampleText();
        return true;
    }

    private void updateDefaultLanguagePreference(ArrayList<String> availableLangs) {
        ArrayList<Locale> locales = new ArrayList<>();
        for (int i = 0; i < availableLangs.size(); i++) {
            Locale locale = this.mEnginesHelper.parseLocaleString(availableLangs.get(i));
            if (locale != null) {
                locales.add(locale);
            }
        }
        Collections.sort(locales, new Comparator() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$kToM5IBz8cCsy7qsQVWCbS9cV3o
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Locale) obj).getDisplayName().compareToIgnoreCase(((Locale) obj2).getDisplayName());
            }
        });
        CharSequence[] entries = new CharSequence[availableLangs.size() + 1];
        CharSequence[] entryValues = new CharSequence[availableLangs.size() + 1];
        entries[0] = getContext().getString(R.string.tts_lang_use_system);
        entryValues[0] = "";
        int i2 = 1;
        for (Locale locale2 : locales) {
            entries[i2] = locale2.getDisplayName();
            entryValues[i2] = locale2.toString();
            i2++;
        }
        this.mDefaultLanguagePreference.setEntries(entries);
        this.mDefaultLanguagePreference.setEntryValues(entryValues);
    }

    private SeekBarPreference initSpeechRatePreference() {
        SeekBarPreference speechRatePreference = (SeekBarPreference) getPreference().findPreference(getContext().getString(R.string.pk_tts_speech_rate));
        speechRatePreference.setMin(10);
        speechRatePreference.setMax(TtsPlaybackSettingsManager.MAX_SPEECH_RATE);
        speechRatePreference.setShowSeekBarValue(false);
        speechRatePreference.setContinuousUpdate(false);
        speechRatePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$ADNCOYhpSCPY3veGKROEkqjDTSE
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$initSpeechRatePreference$5$TtsPlaybackPreferenceController(preference, obj);
            }
        });
        speechRatePreference.setEnabled(false);
        return speechRatePreference;
    }

    public /* synthetic */ boolean lambda$initSpeechRatePreference$5$TtsPlaybackPreferenceController(Preference preference, Object newValue) {
        TtsPlaybackSettingsManager ttsPlaybackSettingsManager = this.mTtsPlaybackManager;
        if (ttsPlaybackSettingsManager != null) {
            ttsPlaybackSettingsManager.updateSpeechRate(((Integer) newValue).intValue());
            this.mTtsPlaybackManager.speakSampleText(this.mSampleText);
            return true;
        }
        LOG.e("speech rate preference enabled before it is allowed");
        return false;
    }

    private SeekBarPreference initVoicePitchPreference() {
        SeekBarPreference pitchPreference = (SeekBarPreference) getPreference().findPreference(getContext().getString(R.string.pk_tts_pitch));
        pitchPreference.setMin(25);
        pitchPreference.setMax(TtsPlaybackSettingsManager.MAX_VOICE_PITCH);
        pitchPreference.setShowSeekBarValue(false);
        pitchPreference.setContinuousUpdate(false);
        pitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$4_mWdMIxJZAVGDLmERIc2qvn07s
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$initVoicePitchPreference$6$TtsPlaybackPreferenceController(preference, obj);
            }
        });
        pitchPreference.setEnabled(false);
        return pitchPreference;
    }

    public /* synthetic */ boolean lambda$initVoicePitchPreference$6$TtsPlaybackPreferenceController(Preference preference, Object newValue) {
        TtsPlaybackSettingsManager ttsPlaybackSettingsManager = this.mTtsPlaybackManager;
        if (ttsPlaybackSettingsManager != null) {
            ttsPlaybackSettingsManager.updateVoicePitch(((Integer) newValue).intValue());
            this.mTtsPlaybackManager.speakSampleText(this.mSampleText);
            return true;
        }
        LOG.e("speech pitch preference enabled before it is allowed");
        return false;
    }

    private Preference initResetTtsPlaybackPreference() {
        Preference resetPreference = getPreference().findPreference(getContext().getString(R.string.pk_tts_reset));
        resetPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.tts.-$$Lambda$TtsPlaybackPreferenceController$W0zQNzEDJch1sL3-kWT1cELB6_Y
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$initResetTtsPlaybackPreference$7$TtsPlaybackPreferenceController(preference);
            }
        });
        resetPreference.setEnabled(false);
        return resetPreference;
    }

    public /* synthetic */ boolean lambda$initResetTtsPlaybackPreference$7$TtsPlaybackPreferenceController(Preference preference) {
        TtsPlaybackSettingsManager ttsPlaybackSettingsManager = this.mTtsPlaybackManager;
        if (ttsPlaybackSettingsManager != null) {
            ttsPlaybackSettingsManager.resetVoicePitch();
            this.mTtsPlaybackManager.resetSpeechRate();
            refreshUi();
            return true;
        }
        LOG.e("reset preference enabled before it is allowed");
        return false;
    }
}
