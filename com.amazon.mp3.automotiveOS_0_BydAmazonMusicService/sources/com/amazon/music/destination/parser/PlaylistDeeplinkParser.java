package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.PlaylistDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistDeeplinkParser implements DeeplinkParser<PlaylistDestination> {
    private static final String PLAYLISTS_SEGMENT = "playlists";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public PlaylistDestination parse(Uri uri) {
        return new PlaylistDestination(ParserUtil.findSegmentAfter(uri, PLAYLISTS_SEGMENT), ParserUtil.getTrackAsin(uri), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), ParserUtil.getStoryAsinQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + PLAYLISTS_SEGMENT + ParserUtil.optional("/[A-Za-z0-9]+") + ParserUtil.optional("/.*");
    }
}
