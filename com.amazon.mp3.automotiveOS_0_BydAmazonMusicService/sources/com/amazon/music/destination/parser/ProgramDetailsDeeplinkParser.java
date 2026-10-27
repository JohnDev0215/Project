package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.ProgramDetailsDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class ProgramDetailsDeeplinkParser implements DeeplinkParser<ProgramDetailsDestination> {
    private static final String PROGRAM_DETAILS_SEGMENT = "(?:matches|conferences|shows)";
    private static final String SHOWS_SEGMENT = "shows";
    private static final String SPORTS_SEGMENT = "sports";

    private String getShowRegex() {
        return "/shows.*";
    }

    private String getSportsRegex() {
        return "/sports/(?:matches|conferences|shows).*";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return ".*(?:matches|conferences|shows).*";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ProgramDetailsDestination parse(Uri uri) {
        return new ProgramDetailsDestination(ParserUtil.findSegmentAfterRegex(uri, PROGRAM_DETAILS_SEGMENT), getProgramType(uri), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    private ProgramDetailsDestination.ProgramType getProgramType(Uri uri) {
        if (uri.getPath().matches(getSportsRegex())) {
            return ProgramDetailsDestination.ProgramType.SPORTS;
        }
        if (uri.getPath().matches(getShowRegex())) {
            return ProgramDetailsDestination.ProgramType.SHOWS;
        }
        return ProgramDetailsDestination.ProgramType.NONE;
    }
}
