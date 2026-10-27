package com.android.car.settings.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class VolumeSettingsRingtoneManager {
    private static final int AUDIO_FEEDBACK_DURATION_MS = 1000;
    private static final Logger LOG = new Logger((Class<?>) VolumeSettingsRingtoneManager.class);
    private final Context mContext;
    private Ringtone mCurrentRingtone;
    private final Map<Integer, Ringtone> mGroupToRingtoneMap = new HashMap();
    private final Map<Integer, Uri> mAttributeUsageToRingtoneUriMap = new HashMap();
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public VolumeSettingsRingtoneManager(Context context) {
        this.mContext = context;
        populateAttributeUsageToRingtoneUriMap();
    }

    private void populateAttributeUsageToRingtoneUriMap() {
        int[] audioAttributeUsages = this.mContext.getResources().getIntArray(R.array.config_ringtone_audio_attribute_usages_map_key);
        String[] ringtoneStrings = this.mContext.getResources().getStringArray(R.array.config_ringtone_uri_map_value);
        if (audioAttributeUsages.length != ringtoneStrings.length) {
            throw new IllegalArgumentException("config_ringtone_audio_attribute_usages_map_key and config_ringtone_uri_map_value must have the same number of items");
        }
        for (int i = 0; i < audioAttributeUsages.length; i++) {
            int usage = audioAttributeUsages[i];
            if (AudioAttributes.usageToString(usage).contains("unknown usage")) {
                throw new IllegalArgumentException("Invalid usage in config_ringtone_audio_attribute_usages_map_key");
            }
            Uri ringtoneUri = Uri.parse(ringtoneStrings[i]);
            LOG.d("Usage " + usage + " mapped to ringtone " + ringtoneUri);
            this.mAttributeUsageToRingtoneUriMap.put(Integer.valueOf(usage), ringtoneUri);
        }
    }

    public void playAudioFeedback(int group, int usage) {
        Ringtone nextRingtone = lazyLoadRingtone(group, usage);
        Ringtone ringtone = this.mCurrentRingtone;
        if (ringtone != null && ringtone != nextRingtone && ringtone.isPlaying()) {
            this.mCurrentRingtone.stop();
        }
        this.mUiHandler.removeCallbacksAndMessages(null);
        this.mCurrentRingtone = nextRingtone;
        nextRingtone.play();
        this.mUiHandler.postDelayed(new Runnable() { // from class: com.android.car.settings.sound.-$$Lambda$VolumeSettingsRingtoneManager$LW-8vKqwrFIlQ_jaSj1gw9ktbLU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$playAudioFeedback$0$VolumeSettingsRingtoneManager();
            }
        }, 1000L);
    }

    public /* synthetic */ void lambda$playAudioFeedback$0$VolumeSettingsRingtoneManager() {
        if (this.mCurrentRingtone.isPlaying()) {
            this.mCurrentRingtone.stop();
            this.mCurrentRingtone = null;
        }
    }

    public void stopCurrentRingtone() {
        Ringtone ringtone = this.mCurrentRingtone;
        if (ringtone != null) {
            ringtone.stop();
        }
    }

    private Ringtone lazyLoadRingtone(int group, int usage) {
        if (!this.mGroupToRingtoneMap.containsKey(Integer.valueOf(group))) {
            Ringtone ringtone = RingtoneManager.getRingtone(this.mContext, getRingtoneUri(usage));
            ringtone.setAudioAttributes(new AudioAttributes.Builder().setUsage(usage).build());
            this.mGroupToRingtoneMap.put(Integer.valueOf(group), ringtone);
        }
        return this.mGroupToRingtoneMap.get(Integer.valueOf(group));
    }

    Uri getRingtoneUri(int usage) {
        return this.mAttributeUsageToRingtoneUriMap.getOrDefault(Integer.valueOf(usage), Settings.System.DEFAULT_RINGTONE_URI);
    }
}
