package com.amazon.mp3.cloudqueue;

import com.amazon.music.storeservice.model.GetStationRecommendationsRequestSerializer;
import com.amazon.music.storeservice.model.GetStationRecommendationsResponseDeserializer;
import com.amazon.musicensembleservice.stations.GetStationSectionsRequestSerializer;
import com.amazon.musicensembleservice.stations.GetStationSectionsResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.RateEntityRequestSerializer;
import com.amazon.musicplayqueueservice.client.v2.queue.AbstractCreateQueueRequestSerializer;
import com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksRequestV1Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes3.dex */
class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        CreateQueueRequestSerializer.register(objectMapper);
        CreateQueueResponseDeserializer.register(objectMapper);
        GetNextTracksRequestSerializer.register(objectMapper);
        GetNextTracksResponseDeserializer.register(objectMapper);
        GetStationSectionsRequestSerializer.register(objectMapper);
        GetStationSectionsResponseDeserializer.register(objectMapper);
        RateEntityRequestSerializer.register(objectMapper);
        GetStationRecommendationsRequestSerializer.register(objectMapper);
        GetStationRecommendationsResponseDeserializer.register(objectMapper);
        AbstractCreateQueueRequestSerializer.register(objectMapper);
        com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueResponseDeserializer.register(objectMapper);
        GetNextTracksRequestV1Serializer.register(objectMapper);
        com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksResponseDeserializer.register(objectMapper);
        com.amazon.musicplayqueueservice.client.v2.RateEntityRequestSerializer.register(objectMapper);
        GetConnectionInfoRequestSerializer.register(objectMapper);
        GetConnectionInfoResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
