package com.amazon.music.recents;

import com.amazon.nimblymusicservice.GetRecentActivityRequestSerializer;
import com.amazon.nimblymusicservice.GetRecentActivityResponseDeserializer;
import com.amazon.nimblymusicservice.GetRecentTrackActivityRequestSerializer;
import com.amazon.nimblymusicservice.GetRecentTrackActivityResponseDeserializer;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackRequestSerializer;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackResponseDeserializer;
import com.amazon.nimblymusicservice.HideFromMostRecentRequestSerializer;
import com.amazon.nimblymusicservice.HideFromRecentTrackActivityRequestSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
final class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        GetRecentActivityRequestSerializer.register(objectMapper);
        GetRecentActivityResponseDeserializer.register(objectMapper);
        GetRecentTrackActivityRequestSerializer.register(objectMapper);
        GetRecentTrackActivityResponseDeserializer.register(objectMapper);
        HideFromMostRecentRequestSerializer.register(objectMapper);
        HideFromRecentTrackActivityRequestSerializer.register(objectMapper);
        GetRecentUnfilteredTrackRequestSerializer.register(objectMapper);
        GetRecentUnfilteredTrackResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
