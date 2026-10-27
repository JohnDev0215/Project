package com.amazon.music.destination.parser;

import com.amazon.music.destination.core.DeeplinkParser;
import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractLibraryDeeplinkParser<T extends Destination> implements DeeplinkParser<T> {
    protected static final String LIBRARY_SEGMENT = "library";

    protected static String getLibraryRegexPrefix() {
        return "/library/";
    }
}
