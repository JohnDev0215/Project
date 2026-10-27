package com.android.car.developeroptions.tts;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TtsEngines;
import android.speech.tts.UtteranceProgressListener;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.GearPreference;
import com.android.car.developeroptions.widget.SeekBarPreference;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.ActionButtonsPreference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class TextToSpeechSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, GearPreference.OnGearClickListener {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.tts.TextToSpeechSettings.2
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.tts_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private ActionButtonsPreference mActionButtons;
    private List<String> mAvailableStrLocals;
    private Locale mCurrentDefaultLocale;
    private String mCurrentEngine;
    private SeekBarPreference mDefaultPitchPref;
    private SeekBarPreference mDefaultRatePref;
    private ListPreference mLocalePreference;
    private int mDefaultPitch = 100;
    private int mDefaultRate = 100;
    private int mSelectedLocaleIndex = -1;
    private TextToSpeech mTts = null;
    private TtsEngines mEnginesHelper = null;
    private String mSampleText = null;
    private final TextToSpeech.OnInitListener mInitListener = new TextToSpeech.OnInitListener() { // from class: com.android.car.developeroptions.tts.-$$Lambda$xPzWOTYFBqQcC1UpB9M1dqlOswY
        @Override // android.speech.tts.TextToSpeech.OnInitListener
        public final void onInit(int i) {
            this.f$0.onInitEngine(i);
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 94;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.tts_settings);
        getActivity().setVolumeControlStream(3);
        this.mEnginesHelper = new TtsEngines(getActivity().getApplicationContext());
        ListPreference listPreference = (ListPreference) findPreference("tts_default_lang");
        this.mLocalePreference = listPreference;
        listPreference.setOnPreferenceChangeListener(this);
        this.mDefaultPitchPref = (SeekBarPreference) findPreference("tts_default_pitch");
        this.mDefaultRatePref = (SeekBarPreference) findPreference("tts_default_rate");
        ActionButtonsPreference actionButtonsPreference = (ActionButtonsPreference) findPreference("action_buttons");
        actionButtonsPreference.setButton1Text(R.string.tts_play);
        actionButtonsPreference.setButton1OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.tts.-$$Lambda$TextToSpeechSettings$2xpQnye_ekgCpyDT8ZIebNnM5YY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$TextToSpeechSettings(view);
            }
        });
        actionButtonsPreference.setButton1Enabled(false);
        actionButtonsPreference.setButton2Text(R.string.tts_reset);
        actionButtonsPreference.setButton2OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.tts.-$$Lambda$TextToSpeechSettings$l1aX4-EZ_wNlJC-f8cXZ3vlA7aI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$1$TextToSpeechSettings(view);
            }
        });
        actionButtonsPreference.setButton1Enabled(true);
        this.mActionButtons = actionButtonsPreference;
        if (bundle == null) {
            this.mLocalePreference.setEnabled(false);
            this.mLocalePreference.setEntries(new CharSequence[0]);
            this.mLocalePreference.setEntryValues(new CharSequence[0]);
        } else {
            CharSequence[] charSequenceArray = bundle.getCharSequenceArray("locale_entries");
            CharSequence[] charSequenceArray2 = bundle.getCharSequenceArray("locale_entry_values");
            CharSequence charSequence = bundle.getCharSequence("locale_value");
            this.mLocalePreference.setEntries(charSequenceArray);
            this.mLocalePreference.setEntryValues(charSequenceArray2);
            this.mLocalePreference.setValue(charSequence != null ? charSequence.toString() : null);
            this.mLocalePreference.setEnabled(charSequenceArray.length > 0);
        }
        this.mTts = new TextToSpeech(getActivity().getApplicationContext(), this.mInitListener);
        setTtsUtteranceProgressListener();
        initSettings();
        setRetainInstance(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$TextToSpeechSettings(View view) {
        speakSampleText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$1$TextToSpeechSettings(View view) {
        resetTts();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getListView().getItemAnimator().setMoveDuration(0L);
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech == null || this.mCurrentDefaultLocale == null) {
            return;
        }
        if (!textToSpeech.getDefaultEngine().equals(this.mTts.getCurrentEngine())) {
            try {
                this.mTts.shutdown();
                this.mTts = null;
            } catch (Exception e) {
                Log.e("TextToSpeechSettings", "Error shutting down TTS engine" + e);
            }
            this.mTts = new TextToSpeech(getActivity().getApplicationContext(), this.mInitListener);
            setTtsUtteranceProgressListener();
            initSettings();
        } else {
            this.mTts.setPitch(Settings.Secure.getInt(getContentResolver(), "tts_default_pitch", 100) / 100.0f);
        }
        Locale defaultLanguage = this.mTts.getDefaultLanguage();
        Locale locale = this.mCurrentDefaultLocale;
        if (locale == null || locale.equals(defaultLanguage)) {
            return;
        }
        updateWidgetState(false);
        checkDefaultLocale();
    }

    private void setTtsUtteranceProgressListener() {
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech == null) {
            return;
        }
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: com.android.car.developeroptions.tts.TextToSpeechSettings.1
            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String str) {
                TextToSpeechSettings.this.updateWidgetState(false);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String str) {
                TextToSpeechSettings.this.updateWidgetState(true);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String str) {
                Log.e("TextToSpeechSettings", "Error while trying to synthesize sample text");
                TextToSpeechSettings.this.updateWidgetState(true);
            }
        });
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            this.mTts = null;
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequenceArray("locale_entries", this.mLocalePreference.getEntries());
        bundle.putCharSequenceArray("locale_entry_values", this.mLocalePreference.getEntryValues());
        bundle.putCharSequence("locale_value", this.mLocalePreference.getValue());
    }

    private void initSettings() {
        ContentResolver contentResolver = getContentResolver();
        this.mDefaultRate = Settings.Secure.getInt(contentResolver, "tts_default_rate", 100);
        this.mDefaultPitch = Settings.Secure.getInt(contentResolver, "tts_default_pitch", 100);
        this.mDefaultRatePref.setProgress(getSeekBarProgressFromValue("tts_default_rate", this.mDefaultRate));
        this.mDefaultRatePref.setOnPreferenceChangeListener(this);
        this.mDefaultRatePref.setMax(getSeekBarProgressFromValue("tts_default_rate", 600));
        this.mDefaultPitchPref.setProgress(getSeekBarProgressFromValue("tts_default_pitch", this.mDefaultPitch));
        this.mDefaultPitchPref.setOnPreferenceChangeListener(this);
        this.mDefaultPitchPref.setMax(getSeekBarProgressFromValue("tts_default_pitch", 400));
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            this.mCurrentEngine = textToSpeech.getCurrentEngine();
            this.mTts.setSpeechRate(this.mDefaultRate / 100.0f);
            this.mTts.setPitch(this.mDefaultPitch / 100.0f);
        }
        if (getActivity() instanceof SettingsActivity) {
            String str = this.mCurrentEngine;
            if (str != null) {
                TextToSpeech.EngineInfo engineInfo = this.mEnginesHelper.getEngineInfo(str);
                Preference preferenceFindPreference = findPreference("tts_engine_preference");
                ((GearPreference) preferenceFindPreference).setOnGearClickListener(this);
                preferenceFindPreference.setSummary(engineInfo.label);
            }
            checkVoiceData(this.mCurrentEngine);
            return;
        }
        throw new IllegalStateException("TextToSpeechSettings used outside a Settings");
    }

    private int getValueFromSeekBarProgress(String str, int i) {
        return str.equals("tts_default_rate") ? i + 10 : str.equals("tts_default_pitch") ? i + 25 : i;
    }

    private int getSeekBarProgressFromValue(String str, int i) {
        return str.equals("tts_default_rate") ? i - 10 : str.equals("tts_default_pitch") ? i - 25 : i;
    }

    public void onInitEngine(int i) {
        if (i == 0) {
            checkDefaultLocale();
            getActivity().runOnUiThread(new Runnable() { // from class: com.android.car.developeroptions.tts.-$$Lambda$TextToSpeechSettings$pfgENTmKX_30RsS_wS3j0B-P7t4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onInitEngine$2$TextToSpeechSettings();
                }
            });
        } else {
            updateWidgetState(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onInitEngine$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onInitEngine$2$TextToSpeechSettings() {
        this.mLocalePreference.setEnabled(true);
    }

    private void checkDefaultLocale() {
        Locale defaultLanguage = this.mTts.getDefaultLanguage();
        if (defaultLanguage == null) {
            Log.e("TextToSpeechSettings", "Failed to get default language from engine " + this.mCurrentEngine);
            updateWidgetState(false);
            return;
        }
        Locale locale = this.mCurrentDefaultLocale;
        Locale localeString = this.mEnginesHelper.parseLocaleString(defaultLanguage.toString());
        this.mCurrentDefaultLocale = localeString;
        if (!Objects.equals(locale, localeString)) {
            this.mSampleText = null;
        }
        this.mTts.setLanguage(defaultLanguage);
        if (evaluateDefaultLocale() && this.mSampleText == null) {
            getSampleText();
        }
    }

    private boolean evaluateDefaultLocale() {
        boolean z;
        Locale locale = this.mCurrentDefaultLocale;
        if (locale != null && this.mAvailableStrLocals != null) {
            try {
                String iSO3Language = locale.getISO3Language();
                if (!TextUtils.isEmpty(this.mCurrentDefaultLocale.getISO3Country())) {
                    iSO3Language = iSO3Language + "-" + this.mCurrentDefaultLocale.getISO3Country();
                }
                if (!TextUtils.isEmpty(this.mCurrentDefaultLocale.getVariant())) {
                    iSO3Language = iSO3Language + "-" + this.mCurrentDefaultLocale.getVariant();
                }
                Iterator<String> it = this.mAvailableStrLocals.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    if (it.next().equalsIgnoreCase(iSO3Language)) {
                        z = false;
                        break;
                    }
                }
                int language = this.mTts.setLanguage(this.mCurrentDefaultLocale);
                if (language == -2 || language == -1 || z) {
                    updateWidgetState(false);
                    return false;
                }
                updateWidgetState(true);
                return true;
            } catch (MissingResourceException unused) {
                updateWidgetState(false);
            }
        }
        return false;
    }

    private void getSampleText() {
        String currentEngine = this.mTts.getCurrentEngine();
        if (TextUtils.isEmpty(currentEngine)) {
            currentEngine = this.mTts.getDefaultEngine();
        }
        Intent intent = new Intent("android.speech.tts.engine.GET_SAMPLE_TEXT");
        intent.putExtra("language", this.mCurrentDefaultLocale.getLanguage());
        intent.putExtra("country", this.mCurrentDefaultLocale.getCountry());
        intent.putExtra("variant", this.mCurrentDefaultLocale.getVariant());
        intent.setPackage(currentEngine);
        try {
            startActivityForResult(intent, 1983);
        } catch (ActivityNotFoundException unused) {
            Log.e("TextToSpeechSettings", "Failed to get sample text, no activity found for " + intent + ")");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1983) {
            onSampleTextReceived(i2, intent);
        } else if (i == 1977) {
            onVoiceDataIntegrityCheckDone(intent);
            if (i2 != 0) {
                updateDefaultLocalePref(intent);
            }
        }
    }

    private void updateDefaultLocalePref(Intent intent) {
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("availableVoices");
        intent.getStringArrayListExtra("unavailableVoices");
        if (stringArrayListExtra == null || stringArrayListExtra.size() == 0) {
            this.mLocalePreference.setEnabled(false);
            return;
        }
        Locale localePrefForEngine = this.mEnginesHelper.isLocaleSetToDefaultForEngine(this.mTts.getCurrentEngine()) ? null : this.mEnginesHelper.getLocalePrefForEngine(this.mTts.getCurrentEngine());
        ArrayList<Pair> arrayList = new ArrayList(stringArrayListExtra.size());
        for (int i = 0; i < stringArrayListExtra.size(); i++) {
            Locale localeString = this.mEnginesHelper.parseLocaleString(stringArrayListExtra.get(i));
            if (localeString != null) {
                arrayList.add(new Pair(localeString.getDisplayName(), localeString));
            }
        }
        Collections.sort(arrayList, new Comparator() { // from class: com.android.car.developeroptions.tts.-$$Lambda$TextToSpeechSettings$4V7DcY2ixO5-LGy3-bwie3kwGjE
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((String) ((Pair) obj).first).compareToIgnoreCase((String) ((Pair) obj2).first);
            }
        });
        this.mSelectedLocaleIndex = 0;
        CharSequence[] charSequenceArr = new CharSequence[stringArrayListExtra.size() + 1];
        CharSequence[] charSequenceArr2 = new CharSequence[stringArrayListExtra.size() + 1];
        charSequenceArr[0] = getActivity().getString(R.string.tts_lang_use_system);
        charSequenceArr2[0] = "";
        int i2 = 1;
        for (Pair pair : arrayList) {
            if (((Locale) pair.second).equals(localePrefForEngine)) {
                this.mSelectedLocaleIndex = i2;
            }
            charSequenceArr[i2] = (CharSequence) pair.first;
            charSequenceArr2[i2] = ((Locale) pair.second).toString();
            i2++;
        }
        this.mLocalePreference.setEntries(charSequenceArr);
        this.mLocalePreference.setEntryValues(charSequenceArr2);
        this.mLocalePreference.setEnabled(true);
        setLocalePreference(this.mSelectedLocaleIndex);
    }

    private void setLocalePreference(int i) {
        if (i < 0) {
            this.mLocalePreference.setValue("");
            this.mLocalePreference.setSummary(R.string.tts_lang_not_selected);
        } else {
            this.mLocalePreference.setValueIndex(i);
            ListPreference listPreference = this.mLocalePreference;
            listPreference.setSummary(listPreference.getEntries()[i]);
        }
    }

    private String getDefaultSampleString() {
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null && textToSpeech.getLanguage() != null) {
            try {
                String iSO3Language = this.mTts.getLanguage().getISO3Language();
                String[] stringArray = getActivity().getResources().getStringArray(R.array.tts_demo_strings);
                String[] stringArray2 = getActivity().getResources().getStringArray(R.array.tts_demo_string_langs);
                for (int i = 0; i < stringArray.length; i++) {
                    if (stringArray2[i].equals(iSO3Language)) {
                        return stringArray[i];
                    }
                }
            } catch (MissingResourceException unused) {
            }
        }
        return this.getString(R.string.tts_default_sample_string);
    }

    private boolean isNetworkRequiredForSynthesis() {
        Set<String> features = this.mTts.getFeatures(this.mCurrentDefaultLocale);
        return (features == null || !features.contains("networkTts") || features.contains("embeddedTts")) ? false : true;
    }

    private void onSampleTextReceived(int i, Intent intent) {
        String defaultSampleString = getDefaultSampleString();
        if (i == 0 && intent != null && intent != null && intent.getStringExtra("sampleText") != null) {
            defaultSampleString = intent.getStringExtra("sampleText");
        }
        this.mSampleText = defaultSampleString;
        if (defaultSampleString != null) {
            updateWidgetState(true);
        } else {
            Log.e("TextToSpeechSettings", "Did not have a sample string for the requested language. Using default");
        }
    }

    private void speakSampleText() {
        boolean zIsNetworkRequiredForSynthesis = isNetworkRequiredForSynthesis();
        if (!zIsNetworkRequiredForSynthesis || (zIsNetworkRequiredForSynthesis && this.mTts.isLanguageAvailable(this.mCurrentDefaultLocale) >= 0)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("utteranceId", "Sample");
            this.mTts.speak(this.mSampleText, 0, map);
        } else {
            Log.w("TextToSpeechSettings", "Network required for sample synthesis for requested language");
            displayNetworkAlert();
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if ("tts_default_rate".equals(preference.getKey())) {
            updateSpeechRate(((Integer) obj).intValue());
        } else if ("tts_default_pitch".equals(preference.getKey())) {
            updateSpeechPitchValue(((Integer) obj).intValue());
        } else if (preference == this.mLocalePreference) {
            String str = (String) obj;
            updateLanguageTo(!TextUtils.isEmpty(str) ? this.mEnginesHelper.parseLocaleString(str) : null);
            checkDefaultLocale();
        }
        return true;
    }

    private void updateLanguageTo(Locale locale) {
        String string = locale != null ? locale.toString() : "";
        int i = 0;
        while (true) {
            if (i >= this.mLocalePreference.getEntryValues().length) {
                i = -1;
                break;
            } else if (string.equalsIgnoreCase(this.mLocalePreference.getEntryValues()[i].toString())) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            Log.w("TextToSpeechSettings", "updateLanguageTo called with unknown locale argument");
            return;
        }
        ListPreference listPreference = this.mLocalePreference;
        listPreference.setSummary(listPreference.getEntries()[i]);
        this.mSelectedLocaleIndex = i;
        this.mEnginesHelper.updateLocalePrefForEngine(this.mTts.getCurrentEngine(), locale);
        TextToSpeech textToSpeech = this.mTts;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        textToSpeech.setLanguage(locale);
    }

    private void resetTts() {
        int seekBarProgressFromValue = getSeekBarProgressFromValue("tts_default_rate", 100);
        this.mDefaultRatePref.setProgress(seekBarProgressFromValue);
        updateSpeechRate(seekBarProgressFromValue);
        int seekBarProgressFromValue2 = getSeekBarProgressFromValue("tts_default_pitch", 100);
        this.mDefaultPitchPref.setProgress(seekBarProgressFromValue2);
        updateSpeechPitchValue(seekBarProgressFromValue2);
    }

    private void updateSpeechRate(int i) {
        this.mDefaultRate = getValueFromSeekBarProgress("tts_default_rate", i);
        try {
            Settings.Secure.putInt(getContentResolver(), "tts_default_rate", this.mDefaultRate);
            if (this.mTts != null) {
                this.mTts.setSpeechRate(this.mDefaultRate / 100.0f);
            }
        } catch (NumberFormatException e) {
            Log.e("TextToSpeechSettings", "could not persist default TTS rate setting", e);
        }
    }

    private void updateSpeechPitchValue(int i) {
        this.mDefaultPitch = getValueFromSeekBarProgress("tts_default_pitch", i);
        try {
            Settings.Secure.putInt(getContentResolver(), "tts_default_pitch", this.mDefaultPitch);
            if (this.mTts != null) {
                this.mTts.setPitch(this.mDefaultPitch / 100.0f);
            }
        } catch (NumberFormatException e) {
            Log.e("TextToSpeechSettings", "could not persist default TTS pitch setting", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWidgetState(final boolean z) {
        getActivity().runOnUiThread(new Runnable() { // from class: com.android.car.developeroptions.tts.-$$Lambda$TextToSpeechSettings$1izF7xrSx_nmPLvUBivFkPB6Ons
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateWidgetState$4$TextToSpeechSettings(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateWidgetState$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateWidgetState$4$TextToSpeechSettings(boolean z) {
        this.mActionButtons.setButton1Enabled(z);
        this.mDefaultRatePref.setEnabled(z);
        this.mDefaultPitchPref.setEnabled(z);
    }

    private void displayNetworkAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(android.R.string.dialog_alert_title);
        builder.setMessage(getActivity().getString(R.string.tts_engine_network_required));
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    private void checkVoiceData(String str) {
        Intent intent = new Intent("android.speech.tts.engine.CHECK_TTS_DATA");
        intent.setPackage(str);
        try {
            startActivityForResult(intent, 1977);
        } catch (ActivityNotFoundException unused) {
            Log.e("TextToSpeechSettings", "Failed to check TTS data, no activity found for " + intent + ")");
        }
    }

    private void onVoiceDataIntegrityCheckDone(Intent intent) {
        String currentEngine = this.mTts.getCurrentEngine();
        if (currentEngine == null) {
            Log.e("TextToSpeechSettings", "Voice data check complete, but no engine bound");
            return;
        }
        if (intent == null) {
            Log.e("TextToSpeechSettings", "Engine failed voice data integrity check (null return)" + this.mTts.getCurrentEngine());
            return;
        }
        Settings.Secure.putString(getContentResolver(), "tts_default_synth", currentEngine);
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("availableVoices");
        this.mAvailableStrLocals = stringArrayListExtra;
        if (stringArrayListExtra == null) {
            Log.e("TextToSpeechSettings", "Voice data check complete, but no available voices found");
            this.mAvailableStrLocals = new ArrayList();
        }
        if (evaluateDefaultLocale()) {
            getSampleText();
        }
    }

    @Override // com.android.car.developeroptions.widget.GearPreference.OnGearClickListener
    public void onGearClick(GearPreference gearPreference) {
        if ("tts_engine_preference".equals(gearPreference.getKey())) {
            Intent settingsIntent = this.mEnginesHelper.getSettingsIntent(this.mEnginesHelper.getEngineInfo(this.mCurrentEngine).name);
            if (settingsIntent != null) {
                startActivity(settingsIntent);
            } else {
                Log.e("TextToSpeechSettings", "settingsIntent is null");
            }
        }
    }
}
