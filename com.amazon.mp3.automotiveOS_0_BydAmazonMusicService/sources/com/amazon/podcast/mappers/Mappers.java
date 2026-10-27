package com.amazon.podcast.mappers;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElementDeserializer;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElementSerializer;
import SOACoreInterface.v1_0.MethodDeserializer;
import SOACoreInterface.v1_0.MethodSerializer;
import SOACoreInterface.v1_0.RequestDeserializer;
import SOACoreInterface.v1_0.RequestSerializer;
import SOACoreInterface.v1_0.ResponseDeserializer;
import SOACoreInterface.v1_0.ResponseSerializer;
import SOACoreInterface.v1_0.SOAObjectDeserializer;
import SOACoreInterface.v1_0.SOAObjectSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
public final class Mappers {
    private static final ObjectMapper mapper;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        mapper = objectMapper;
        RequestSerializer.register(objectMapper);
        RequestDeserializer.register(objectMapper);
        ResponseSerializer.register(objectMapper);
        ResponseDeserializer.register(objectMapper);
        FollowMappers.register(objectMapper);
        BookmarkMappers.register(objectMapper);
        BiteBookmarkMappers.register(objectMapper);
        PlaybackMetricsMappers.register(objectMapper);
        SaveMappers.register(objectMapper);
        SOAObjectDeserializer.register(objectMapper);
        SOAObjectSerializer.register(objectMapper);
        MediaMetadataElementSerializer.register(objectMapper);
        MediaMetadataElementDeserializer.register(objectMapper);
        MethodSerializer.register(objectMapper);
        MethodDeserializer.register(objectMapper);
        UiMetricsMappers.register(objectMapper);
        CompletedMappers.register(objectMapper);
        CategoryFollowMappers.register(objectMapper);
        FollowPromptMappers.register(objectMapper);
    }

    public static final ObjectMapper mapper() {
        return mapper;
    }
}
