package com.amazon.music.play.queue;

import com.amazon.musicplayqueueservice.client.common.GetAlexaHintsRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetAlexaHintsResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.RateEntityRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.RateEntityResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
final class Mappers {
    private static final ObjectMapper MAPPER;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        GetTrackAndQueueMetadataRequestSerializer.register(objectMapper);
        GetTrackAndQueueMetadataResponseDeserializer.register(objectMapper);
        GetAlexaHintsRequestSerializer.register(objectMapper);
        GetAlexaHintsResponseDeserializer.register(objectMapper);
        RateEntityRequestSerializer.register(objectMapper);
        RateEntityResponseDeserializer.register(objectMapper);
        GetNextTracksRequestSerializer.register(objectMapper);
        GetNextTracksResponseDeserializer.register(objectMapper);
    }

    private Mappers() {
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
