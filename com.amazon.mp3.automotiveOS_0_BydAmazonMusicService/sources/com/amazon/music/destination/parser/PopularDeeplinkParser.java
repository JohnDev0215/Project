package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.PopularDestination;

/* JADX INFO: loaded from: classes4.dex */
public class PopularDeeplinkParser extends GenreDeeplinkParser<PopularDestination> {
    @Override // com.amazon.music.destination.parser.GenreDeeplinkParser
    protected String getRoot() {
        return "popular";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public PopularDestination parse(Uri uri) {
        return new PopularDestination(ParserUtil.getGenre(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
