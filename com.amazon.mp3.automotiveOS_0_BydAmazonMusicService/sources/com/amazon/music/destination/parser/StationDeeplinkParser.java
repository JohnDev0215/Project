package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.StationDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class StationDeeplinkParser implements DeeplinkParser<StationDestination> {
    private static final String STATION_KEY_REGEX = "[A-Za-z0-9]+";
    private static final String STATION_SEGMENT = "stations";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/stations/[A-Za-z0-9]+";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public StationDestination parse(Uri uri) {
        return new StationDestination(ParserUtil.findSegmentAfter(uri, STATION_SEGMENT), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    public static boolean isStationDeeplink(Uri uri) {
        return uri.toString().matches(new StationDeeplinkParser().getUrlRegex());
    }
}
