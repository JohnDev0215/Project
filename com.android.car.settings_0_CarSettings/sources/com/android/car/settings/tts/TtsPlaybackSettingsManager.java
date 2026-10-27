package com.android.car.settings.tts;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TtsEngines;
import com.android.car.settings.common.Logger;
import com.android.car.ui.AlertDialogBuilder;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
class TtsPlaybackSettingsManager {
    private static final Logger LOG = new Logger((Class<?>) TtsPlaybackSettingsManager.class);
    public static final int MAX_SPEECH_RATE = 600;
    public static final int MAX_VOICE_PITCH = 400;
    public static final int MIN_SPEECH_RATE = 10;
    public static final int MIN_VOICE_PITCH = 25;
    public static final float SCALING_FACTOR = 100.0f;
    private static final String UTTERANCE_ID = "Sample";
    private final Context mContext;
    private final TtsEngines mEnginesHelper;
    private final TextToSpeech mTts;

    TtsPlaybackSettingsManager(Context context, TextToSpeech tts, TtsEngines enginesHelper) {
        this.mContext = context;
        this.mTts = tts;
        this.mEnginesHelper = enginesHelper;
    }

    void updateSpeechRate(int speechRate) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "tts_default_rate", speechRate);
        this.mTts.setSpeechRate(speechRate / 100.0f);
        LOG.d("TTS default rate changed, now " + speechRate);
    }

    int getCurrentSpeechRate() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "tts_default_rate", 100);
    }

    void resetSpeechRate() {
        updateSpeechRate(100);
    }

    void updateVoicePitch(int pitch) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "tts_default_pitch", pitch);
        this.mTts.setPitch(pitch / 100.0f);
        LOG.d("TTS default pitch changed, now " + pitch);
    }

    int getCurrentVoicePitch() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "tts_default_pitch", 100);
    }

    void resetVoicePitch() {
        updateVoicePitch(100);
    }

    Locale getStoredTtsLocale() {
        if (this.mEnginesHelper.isLocaleSetToDefaultForEngine(this.mTts.getCurrentEngine())) {
            return null;
        }
        Locale currentLocale = this.mEnginesHelper.getLocalePrefForEngine(this.mTts.getCurrentEngine());
        return currentLocale;
    }

    Locale getEffectiveTtsLocale() {
        if (this.mTts.getVoice() == null) {
            return null;
        }
        return this.mEnginesHelper.parseLocaleString(this.mTts.getVoice().getLocale().toString());
    }

    boolean updateTtsLocale(Locale newLocale) {
        int resultCode = this.mTts.setLanguage(newLocale != null ? newLocale : Locale.getDefault());
        boolean success = (resultCode == -2 || resultCode == -1) ? false : true;
        if (success) {
            this.mEnginesHelper.updateLocalePrefForEngine(this.mTts.getCurrentEngine(), newLocale);
        }
        return success;
    }

    void speakSampleText(String text) {
        boolean networkRequired = this.mTts.getVoice().isNetworkConnectionRequired();
        Locale defaultLocale = getEffectiveTtsLocale();
        if (!networkRequired || (networkRequired && this.mTts.isLanguageAvailable(defaultLocale) >= 0)) {
            this.mTts.speak(text, 0, null, UTTERANCE_ID);
        } else {
            displayNetworkAlert();
        }
    }

    private void displayNetworkAlert() {
        AlertDialog dialog = new AlertDialogBuilder(this.mContext).setTitle(R.string.dialog_alert_title).setMessage(com.android.car.settings.R.string.tts_engine_network_required).setCancelable(false).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
        dialog.show();
    }
}
