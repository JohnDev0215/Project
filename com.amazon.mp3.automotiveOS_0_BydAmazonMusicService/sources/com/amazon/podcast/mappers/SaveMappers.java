package com.amazon.podcast.mappers;

import Podcast.SaveInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.SaveInterface.v1_0.SetOnReportOperationsMethodSerializer;
import Podcast.SaveInterface.v1_0.SetSavesLastSyncTimeMethodDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
final class SaveMappers {
    SaveMappers() {
    }

    public static void register(final ObjectMapper mapper) {
        SetOnReportOperationsMethodSerializer.register(mapper);
        SetOnReportOperationsMethodDeserializer.register(mapper);
        SetSavesLastSyncTimeMethodDeserializer.register(mapper);
    }
}
