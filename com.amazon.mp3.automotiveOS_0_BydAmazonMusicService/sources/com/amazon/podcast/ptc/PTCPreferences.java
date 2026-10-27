package com.amazon.podcast.ptc;

import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientState;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCSelectionsClientState;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCWriteCacheElement;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum PTCPreferences {
    INSTANCE;

    private static final String PREF_NAME = "PodcastTasteCollection";
    private static final String SHOW_PTC_INGRESS = "show_ptc_ingress";
    private static final List<String> categoryIds = new ArrayList();
    private static final Map<String, GridItemElement> podcastsMap = new LinkedHashMap();
    private boolean isPtcTrailerPlayed = false;

    PTCPreferences() {
    }

    public final boolean readShowPTCIngress(final Context context) {
        return getPTCSharedPreferences(context).getBoolean(SHOW_PTC_INGRESS, true);
    }

    public final List<String> getPTCCategorySelections() {
        return categoryIds;
    }

    public final void cachePTCCategorySelections(final List<String> ids) {
        List<String> list = categoryIds;
        list.clear();
        list.addAll(ids);
    }

    public final Map<String, GridItemElement> getPTCPodcastSelections() {
        return podcastsMap;
    }

    public final void cachePTCPodcastSelections(final Map<String, GridItemElement> podcasts) {
        Map<String, GridItemElement> map = podcastsMap;
        map.clear();
        map.putAll(podcasts);
    }

    public final void clearData(final Context context) {
        getPTCSharedPreferences(context).edit().clear().apply();
        clearSelections();
        clearPTCTrailerCache();
    }

    public final void clearSelections() {
        categoryIds.clear();
        podcastsMap.clear();
    }

    public final PTCPreferencesClientState readPTCPreferencesClientState(final Context context) {
        return PTCPreferencesClientState.builder().withShowPTCIngress(Boolean.valueOf(readShowPTCIngress(context))).build();
    }

    public final PTCSelectionsClientState readPTCSelectionsClientState() {
        return PTCSelectionsClientState.builder().withCategories(categoryIds).withPodcasts(new ArrayList(podcastsMap.keySet())).build();
    }

    final void writeShowPTCIngress(final Context context, final Boolean value) {
        getPTCSharedPreferences(context).edit().putBoolean(SHOW_PTC_INGRESS, value.booleanValue()).apply();
    }

    public boolean getPTCTrailerCacheValue() {
        return this.isPtcTrailerPlayed;
    }

    public void clearPTCTrailerCache() {
        this.isPtcTrailerPlayed = false;
    }

    public void setPTCTrailerCache(final PTCWriteCacheElement writeCacheElement) {
        this.isPtcTrailerPlayed = writeCacheElement == null ? false : writeCacheElement.isDidPlayTrailer().booleanValue();
    }

    private final SharedPreferences getPTCSharedPreferences(final Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }
}
