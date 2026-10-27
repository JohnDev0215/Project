package com.amazon.music.station;

import com.amazon.music.storeservice.model.GetStationRecommendationsRequestSerializer;
import com.amazon.music.storeservice.model.GetStationRecommendationsResponseDeserializer;
import com.amazon.musicensembleservice.stations.GetStationSectionsRequestSerializer;
import com.amazon.musicensembleservice.stations.GetStationSectionsResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.RateEntityRequestSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
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
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
