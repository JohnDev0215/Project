package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.PersonalizedPlaylistsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class PersonalizedPlaylistsDeeplinkParser implements DeeplinkParser<PersonalizedPlaylistsDestination> {
    private static final String PERSONALIZED_PLAYLISTS_SEGMENT = "pp";
    private static final String PERSONALIZED_PLAYLISTS_TYPE_REGEX = "/playlists(/pp/.+|(/)|)";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return PERSONALIZED_PLAYLISTS_TYPE_REGEX;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public PersonalizedPlaylistsDestination parse(Uri uri) {
        return new PersonalizedPlaylistsDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri), ParserUtil.findSegmentAfter(uri, PERSONALIZED_PLAYLISTS_SEGMENT), ParserUtil.getAction(uri));
    }
}
