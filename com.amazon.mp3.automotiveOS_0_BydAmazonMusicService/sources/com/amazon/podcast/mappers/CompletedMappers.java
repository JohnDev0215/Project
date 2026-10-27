package com.amazon.podcast.mappers;

import Podcast.CompletedInterface.v1_0.SetCompletedLastSyncTimeMethodDeserializer;
import Podcast.CompletedInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.CompletedInterface.v1_0.SetOnReportOperationsMethodSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
final class CompletedMappers {
    CompletedMappers() {
    }

    public static void register(final ObjectMapper mapper) {
        SetOnReportOperationsMethodSerializer.register(mapper);
        SetOnReportOperationsMethodDeserializer.register(mapper);
        SetCompletedLastSyncTimeMethodDeserializer.register(mapper);
    }
}
