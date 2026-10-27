package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.PodcastDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastDeeplinkParser implements DeeplinkParser<PodcastDestination> {
    private static final String OPTIONAL_PATHS = "(/.*)?";
    private static final String PODCAST_SEGMENT = "/podcasts";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public PodcastDestination parse(Uri uri) {
        return new PodcastDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), uri.toString());
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return PODCAST_SEGMENT + OPTIONAL_PATHS;
    }
}
