package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.VideoDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class VideoDeeplinkParser implements DeeplinkParser<VideoDestination> {
    private static final String STORY_SEGMENT = "story";
    private static final String VIDEOS_SEGMENT = "videos";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public VideoDestination parse(Uri uri) {
        String strFindSegmentAfter = ParserUtil.findSegmentAfter(uri, VIDEOS_SEGMENT);
        if (strFindSegmentAfter == null) {
            return new VideoDestination(ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
        }
        if (strFindSegmentAfter.equals("story")) {
            return new VideoDestination(Boolean.TRUE, ParserUtil.findSegmentAfter(uri, "story"), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
        }
        return new VideoDestination(strFindSegmentAfter, ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + VIDEOS_SEGMENT + ParserUtil.optional("/[A-Za-z0-9]+") + ParserUtil.optional("/.*");
    }
}
