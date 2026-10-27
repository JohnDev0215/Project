package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.AllAccessUserPlaylistDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class AllAccessUserPlaylistDeeplinkParser implements DeeplinkParser<AllAccessUserPlaylistDestination> {
    private static final String ALL_ACCESS_USER_PLAYLISTS_SEGMENT = "user-playlists";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/user-playlists/[^/]*";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public AllAccessUserPlaylistDestination parse(Uri uri) {
        return new AllAccessUserPlaylistDestination(ParserUtil.getLastSegment(uri), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
