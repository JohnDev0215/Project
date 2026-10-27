package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public abstract class GenreDestination extends Destination {
    private final String genre;
    private final String widgetId;

    public GenreDestination(String str, String str2, String str3) {
        super(str2, str3);
        this.genre = str;
        this.widgetId = null;
    }

    public GenreDestination(String str, String str2, String str3, String str4) {
        super(str3, str4);
        this.genre = str;
        this.widgetId = str2;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getWidgetId() {
        return this.widgetId;
    }
}
