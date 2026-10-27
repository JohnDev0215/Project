package com.amazon.podcast.mappers;

import Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethodSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
final class UiMetricsMappers {
    UiMetricsMappers() {
    }

    public static void register(final ObjectMapper mapper) {
        SetOnReportOperationsMethodSerializer.register(mapper);
        SetOnReportOperationsMethodDeserializer.register(mapper);
    }
}
