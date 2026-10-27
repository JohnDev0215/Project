package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.ProgramsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class ProgramsDeeplinkParser implements DeeplinkParser<ProgramsDestination> {
    private static final String PROGRAMS_SEGMENT = "competitions";
    private static final String SPORTS_SEGMENT = "sports";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/sports/competitions.*";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ProgramsDestination parse(Uri uri) {
        return new ProgramsDestination(ParserUtil.findSegmentAfter(uri, PROGRAMS_SEGMENT), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
