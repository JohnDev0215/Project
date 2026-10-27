package com.amazon.podcast.views.downloadsTemplate;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes5.dex */
public enum DownloadRefinementPreferences {
    INSTANCE;

    private static final String PODCAST_DOWNLOADS_TEMPLATE_REFINEMENT_SELECTION = "podcastDownloadsTemplateRefinementSelection";
    private static final String SORT_KEY = "SORT";
    private static final String UNPLAYED_KEY = "UNPLAYED";

    public void clear(final Context context) {
        getPreference(context).edit().clear().apply();
    }

    void writeSortValue(final Context context, final String selection) {
        SharedPreferences.Editor editorEdit = getPreference(context).edit();
        editorEdit.putString(SORT_KEY, selection);
        editorEdit.apply();
    }

    void writeUnPlayedFilterValue(final Context context, final boolean isSelected) {
        SharedPreferences.Editor editorEdit = getPreference(context).edit();
        editorEdit.putBoolean(UNPLAYED_KEY, isSelected);
        editorEdit.apply();
    }

    String readSortValue(final Context context) {
        return getPreference(context).getString(SORT_KEY, null);
    }

    boolean readUnplayedFilterValue(final Context context) {
        return getPreference(context).getBoolean(UNPLAYED_KEY, false);
    }

    private SharedPreferences getPreference(final Context context) {
        return context.getSharedPreferences(PODCAST_DOWNLOADS_TEMPLATE_REFINEMENT_SELECTION, 0);
    }
}
