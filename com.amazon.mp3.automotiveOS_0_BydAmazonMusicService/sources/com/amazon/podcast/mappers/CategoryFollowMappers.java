package com.amazon.podcast.mappers;

import Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethodDeserializer;
import Podcast.CategoryFollowInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.CategoryFollowInterface.v1_0.SetOnReportOperationsMethodSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
final class CategoryFollowMappers {
    CategoryFollowMappers() {
    }

    public static void register(final ObjectMapper mapper) {
        SetOnReportOperationsMethodSerializer.register(mapper);
        SetOnReportOperationsMethodDeserializer.register(mapper);
        SetCategoryFollowsLastSyncTimeMethodDeserializer.register(mapper);
    }
}
