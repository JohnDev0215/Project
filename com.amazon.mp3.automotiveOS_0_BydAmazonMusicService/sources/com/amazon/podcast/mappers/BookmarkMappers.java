package com.amazon.podcast.mappers;

import Podcast.BookmarkInterface.v1_0.SetBookmarksLastSyncTimeMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.SetOnReportOperationsMethodDeserializer;
import Podcast.BookmarkInterface.v1_0.SetOnReportOperationsMethodSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
final class BookmarkMappers {
    BookmarkMappers() {
    }

    public static void register(final ObjectMapper mapper) {
        SetOnReportOperationsMethodSerializer.register(mapper);
        SetOnReportOperationsMethodDeserializer.register(mapper);
        SetBookmarksLastSyncTimeMethodDeserializer.register(mapper);
    }
}
