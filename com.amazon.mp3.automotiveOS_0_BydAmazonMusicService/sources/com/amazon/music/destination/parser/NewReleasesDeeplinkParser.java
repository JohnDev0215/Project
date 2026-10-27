package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.NewReleasesDestination;

/* JADX INFO: loaded from: classes4.dex */
public class NewReleasesDeeplinkParser extends GenreDeeplinkParser<NewReleasesDestination> {
    @Override // com.amazon.music.destination.parser.GenreDeeplinkParser
    protected String getRoot() {
        return "new";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public NewReleasesDestination parse(Uri uri) {
        return new NewReleasesDestination(ParserUtil.getGenre(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
