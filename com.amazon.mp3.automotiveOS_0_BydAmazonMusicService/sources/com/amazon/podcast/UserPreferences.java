package com.amazon.podcast;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* JADX INFO: loaded from: classes5.dex */
public final class UserPreferences {
    private static UserPreferences INSTANCE;
    private SharedPreferences.Editor editor;
    private final Object lock = new Object();
    private final SharedPreferences sharedPreferences;

    private UserPreferences(final Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        this.sharedPreferences = defaultSharedPreferences;
        this.editor = defaultSharedPreferences.edit();
    }

    public static UserPreferences get(final Context context) {
        UserPreferences userPreferences = INSTANCE;
        return userPreferences != null ? userPreferences : new UserPreferences(context);
    }

    public int readLastVisitedLibraryTab() {
        return this.sharedPreferences.getInt("podcast_library_last_visited_tab", 0);
    }

    public void writeLastVisitedLibraryTab(final int position) {
        this.editor.putInt("podcast_library_last_visited_tab", position);
        this.editor.apply();
    }

    public boolean readPodcastTranscriptDisabledFromSettings() {
        return this.sharedPreferences.getBoolean("podcast_transcripts_disabled_from_settings", false);
    }

    public void writePodcastTranscriptDisabledFromSettings(final boolean isPodcastTranscriptDisabled) {
        this.editor.putBoolean("podcast_transcripts_disabled_from_settings", isPodcastTranscriptDisabled);
        this.editor.apply();
    }

    public void clear() {
        synchronized (this.lock) {
            this.editor.remove("podcast_library_last_visited_tab");
            this.editor.apply();
        }
    }
}
