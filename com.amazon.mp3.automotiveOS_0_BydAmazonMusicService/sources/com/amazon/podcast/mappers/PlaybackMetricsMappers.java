package com.amazon.podcast.mappers;

import Podcast.PlaybackMetricsInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.PlaybackMetricsInterface.v1_0.SetOnReportOperationsMethodSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
final class PlaybackMetricsMappers {
    PlaybackMetricsMappers() {
    }

    public static void register(final ObjectMapper mapper) {
        SetOnReportOperationsMethodSerializer.register(mapper);
        SetOnReportOperationsMethodDeserializer.register(mapper);
    }
}
