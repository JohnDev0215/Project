package com.amazon.podcast.views.podcastDetailTemplate;

import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public enum PodcastDetailClientState {
    INSTANCE;

    private static final String CLIENT_STATE_NAMES_KEY = "clientStateNames";
    private static final String DOWNLOAD_KEY = "downloaded";
    private static final String REFINEMENT_SHARED_PREFS = "Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState";
    private static final String SEASONS_KEY = "seasons";
    private static final String SORT_KEY = "sort";
    private static final String TAG;
    private static final String UN_PLAYED_KEY = "unplayed";
    private static final Logger logger;

    static {
        String simpleName = PodcastDetailClientState.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public static String getRefinementClientStateName(final String podcastId) {
        if (podcastId.isEmpty()) {
            return null;
        }
        return RefinementOptionsClientState.class.getCanonicalName() + "#" + podcastId;
    }

    public void clear(final Context context) {
        SharedPreferences refinementsSharedPref = getRefinementsSharedPref(context);
        Iterator<String> it = refinementsSharedPref.getStringSet(CLIENT_STATE_NAMES_KEY, new HashSet()).iterator();
        while (it.hasNext()) {
            clearRefinementsForClientStateName(context, it.next());
        }
        refinementsSharedPref.edit().clear().apply();
    }

    public RefinementOptionsClientState readClientState(final Context context, final String clientStateName) {
        SharedPreferences sharedPrefsForClientStateName = getSharedPrefsForClientStateName(context, clientStateName);
        return RefinementOptionsClientState.builder().withSort(sharedPrefsForClientStateName.getString(SORT_KEY, null)).withDownload(Boolean.valueOf(sharedPrefsForClientStateName.getBoolean(DOWNLOAD_KEY, false))).withUnplayed(Boolean.valueOf(sharedPrefsForClientStateName.getBoolean(UN_PLAYED_KEY, false))).withSeasonNumber(sharedPrefsForClientStateName.getString(SEASONS_KEY, null)).build();
    }

    void writeSortValue(final Context context, final String selectedSortId, final String clientStateName) {
        saveClientStateName(context, clientStateName);
        SharedPreferences.Editor editorEdit = getSharedPrefsForClientStateName(context, clientStateName).edit();
        editorEdit.putString(SORT_KEY, selectedSortId);
        editorEdit.apply();
    }

    void writeDownloadFilterValue(final Context context, final boolean isDownloadSelected, final String clientStateName) {
        saveClientStateName(context, clientStateName);
        SharedPreferences.Editor editorEdit = getSharedPrefsForClientStateName(context, clientStateName).edit();
        editorEdit.putBoolean(DOWNLOAD_KEY, isDownloadSelected);
        editorEdit.apply();
    }

    void writeUnPlayedFilterValue(final Context context, final boolean isUnPlayedSelected, final String clientStateName) {
        saveClientStateName(context, clientStateName);
        SharedPreferences.Editor editorEdit = getSharedPrefsForClientStateName(context, clientStateName).edit();
        editorEdit.putBoolean(UN_PLAYED_KEY, isUnPlayedSelected);
        editorEdit.apply();
    }

    void writeSeasonNumber(final Context context, final String selectedSeasonsNumber, final String clientStateName) {
        saveClientStateName(context, clientStateName);
        SharedPreferences.Editor editorEdit = getSharedPrefsForClientStateName(context, clientStateName).edit();
        editorEdit.putString(SEASONS_KEY, selectedSeasonsNumber);
        editorEdit.apply();
    }

    private void clearRefinementsForClientStateName(final Context context, final String clientStateName) {
        getSharedPrefsForClientStateName(context, clientStateName).edit().clear().apply();
    }

    private void saveClientStateName(final Context context, final String clientStateName) {
        String strCompatibleClientStateName = compatibleClientStateName(clientStateName);
        SharedPreferences refinementsSharedPref = getRefinementsSharedPref(context);
        Set<String> stringSet = refinementsSharedPref.getStringSet(CLIENT_STATE_NAMES_KEY, new HashSet());
        if (stringSet.contains(strCompatibleClientStateName)) {
            return;
        }
        HashSet hashSet = new HashSet(stringSet);
        hashSet.add(strCompatibleClientStateName);
        refinementsSharedPref.edit().putStringSet(CLIENT_STATE_NAMES_KEY, hashSet).apply();
    }

    private SharedPreferences getRefinementsSharedPref(final Context context) {
        return context.getSharedPreferences(REFINEMENT_SHARED_PREFS, 0);
    }

    private SharedPreferences getSharedPrefsForClientStateName(final Context context, final String clientStateName) {
        return context.getSharedPreferences(compatibleClientStateName(clientStateName), 0);
    }

    private String compatibleClientStateName(final String clientStateName) {
        return clientStateName.contains("v2_0") ? clientStateName.replace("v2_0", "v1_0") : clientStateName;
    }
}
