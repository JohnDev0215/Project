package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.StationsGenreDestination;

/* JADX INFO: loaded from: classes4.dex */
public class StationsGenreDeeplinkParser extends GenreDeeplinkParser<StationsGenreDestination> {
    @Override // com.amazon.music.destination.parser.GenreDeeplinkParser
    protected String getRoot() {
        return "stations";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public StationsGenreDestination parse(Uri uri) {
        return new StationsGenreDestination(ParserUtil.getGenre(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
