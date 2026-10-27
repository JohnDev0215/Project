package com.amazon.podcast.followPrompt;

import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientState;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElement;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.podcast.mappers.Mappers;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public enum FollowPromptPreferences {
    INSTANCE;

    private static final String HIDE_FOLLOW_PROMPT_PREFERENCES = "hide_follow_prompt_preferences";
    private static final String PREF_NAME = "PodcastFollowPrompt";
    private static final String TAG = "FollowPromptPreferences";
    private static final Logger logger = LoggerFactory.getLogger(TAG);

    public final HidePromptPreferenceClientState readHidePromptPreferenceClientState(final Context context) {
        return HidePromptPreferenceClientState.builder().withPreferenceMap(readHidePromptPreferences(context)).build();
    }

    public final void writeHidePromptPreference(final Context context, final String podcastId, final HidePromptPreferenceElement element) {
        if (element == null) {
            element = buildHidePromptElement();
        }
        Map<String, HidePromptPreferenceElement> hidePromptPreferences = readHidePromptPreferences(context);
        hidePromptPreferences.put(podcastId, element);
        writeHidePromptPreferences(context, serializeHidePromptPreferencesMap(hidePromptPreferences));
    }

    public final void clearData(final Context context) {
        getFollowPromptSharedPreferences(context).edit().clear().apply();
    }

    private Map<String, HidePromptPreferenceElement> readHidePromptPreferences(final Context context) {
        return deserializeHidePromptPreferencesMap(getFollowPromptSharedPreferences(context).getString(HIDE_FOLLOW_PROMPT_PREFERENCES, null));
    }

    private void writeHidePromptPreferences(final Context context, final String serializedPreferences) {
        if (serializedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = getFollowPromptSharedPreferences(context).edit();
        editorEdit.putString(HIDE_FOLLOW_PROMPT_PREFERENCES, serializedPreferences);
        editorEdit.apply();
    }

    private String serializeHidePromptPreferencesMap(final Map<String, HidePromptPreferenceElement> hidePromptPreferences) {
        try {
            return Mappers.mapper().writeValueAsString(hidePromptPreferences);
        } catch (Exception e) {
            logger.error("Unable to serialize {}.", HIDE_FOLLOW_PROMPT_PREFERENCES, e);
            return null;
        }
    }

    private Map<String, HidePromptPreferenceElement> deserializeHidePromptPreferencesMap(final String serializedPreferences) {
        if (serializedPreferences == null) {
            return new HashMap();
        }
        try {
            return (Map) Mappers.mapper().readValue(serializedPreferences, new TypeReference<HashMap<String, HidePromptPreferenceElement>>() { // from class: com.amazon.podcast.followPrompt.FollowPromptPreferences.1
            });
        } catch (Exception e) {
            logger.error("Unable to deserialize {}.", HIDE_FOLLOW_PROMPT_PREFERENCES, e);
            return new HashMap();
        }
    }

    private HidePromptPreferenceElement buildHidePromptElement() {
        return HidePromptPreferenceElement.builder().withUpdatedTime(System.currentTimeMillis()).build();
    }

    private SharedPreferences getFollowPromptSharedPreferences(final Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }
}
