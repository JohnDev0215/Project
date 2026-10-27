package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.core.DeeplinkParser;
import com.amazon.music.destination.core.Destination;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public abstract class UnimplementedDeeplinkParser<T extends Destination> implements DeeplinkParser<T> {
    private final String randomString = UUID.randomUUID().toString();

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public final T parse(Uri uri) {
        throw new IllegalStateException("Deeplinking is currently not implemented");
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public final String getUrlRegex() {
        return "^" + this.randomString + "$";
    }
}
