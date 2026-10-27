package com.amazon.music.recommendation;

import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsRequestSerializer;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsResponseDeserializer;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsRequestSerializer;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsResponseDeserializer;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsRequestSerializer;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        GetBrowseRecommendationsRequestSerializer.register(objectMapper);
        GetBrowseRecommendationsResponseDeserializer.register(objectMapper);
        GetSimilarityRecommendationsRequestSerializer.register(objectMapper);
        GetSimilarityRecommendationsResponseDeserializer.register(objectMapper);
        GetBrowseRecommendationsBySourceAsinsRequestSerializer.register(objectMapper);
        GetBrowseRecommendationsBySourceAsinsResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
