package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.SharedUserPlaylistDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class SharedUserPlaylistDeeplinkParser implements DeeplinkParser<SharedUserPlaylistDestination> {
    private static final String USER_PLAYLISTS_SEGMENT = "user-playlists";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/user-playlists/[^/]*";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public SharedUserPlaylistDestination parse(Uri uri) {
        return new SharedUserPlaylistDestination(ParserUtil.getLastSegment(uri), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
