package com.amazon.music.destination.core;

import android.net.Uri;
import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public interface DeeplinkParser<T extends Destination> {
    String getUrlRegex();

    T parse(Uri uri);
}
