package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.MyLibraryDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class MyLibraryDeeplinkParser implements DeeplinkParser<MyLibraryDestination> {
    private final String MY_SEGMENT = "my";
    private final String LIBRARY_SEGMENT = "library";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/my/library";
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public MyLibraryDestination parse(Uri uri) {
        return new MyLibraryDestination(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }
}
