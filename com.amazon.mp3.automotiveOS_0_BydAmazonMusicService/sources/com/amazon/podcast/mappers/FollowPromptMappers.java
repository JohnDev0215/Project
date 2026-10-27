package com.amazon.podcast.mappers;

import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElementDeserializer;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElementSerializer;
import Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethodDeserializer;
import Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethodSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
final class FollowPromptMappers {
    FollowPromptMappers() {
    }

    public static void register(final ObjectMapper mapper) {
        HidePromptPreferenceElementSerializer.register(mapper);
        HidePromptPreferenceElementDeserializer.register(mapper);
        WriteHidePromptPreferencesMethodSerializer.register(mapper);
        WriteHidePromptPreferencesMethodDeserializer.register(mapper);
    }
}
