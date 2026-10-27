package com.amazon.podcast.mappers;

import Podcast.BiteBookmarkInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.BiteBookmarkInterface.v1_0.SetOnReportOperationsMethodSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
public class BiteBookmarkMappers {
    public static void register(final ObjectMapper mapper) {
        SetOnReportOperationsMethodSerializer.register(mapper);
        SetOnReportOperationsMethodDeserializer.register(mapper);
    }
}
