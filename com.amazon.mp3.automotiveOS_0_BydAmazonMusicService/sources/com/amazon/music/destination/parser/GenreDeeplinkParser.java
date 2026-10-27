package com.amazon.music.destination.parser;

import com.amazon.music.destination.GenreDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public abstract class GenreDeeplinkParser<T extends GenreDestination> implements DeeplinkParser<T> {
    protected abstract String getRoot();

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return "/" + getRoot() + "(/genre/.+|(/)|)";
    }
}
