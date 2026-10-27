package com.amazon.podcast.bookmark;

import com.amazon.podcast.appsync.QueryParameters;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class BookmarkQueryParameters extends QueryParameters {
    private final Set<String> ids;

    public BookmarkQueryParameters(final Set<String> ids) {
        this.ids = ids;
    }

    public Set<String> getIds() {
        return this.ids;
    }
}
