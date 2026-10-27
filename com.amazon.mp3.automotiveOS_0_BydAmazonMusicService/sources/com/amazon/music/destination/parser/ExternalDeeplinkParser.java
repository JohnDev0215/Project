package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.ExternalDestination;
import com.amazon.music.destination.core.DeeplinkParser;

/* JADX INFO: loaded from: classes4.dex */
public class ExternalDeeplinkParser implements DeeplinkParser<ExternalDestination> {
    private static final String EXTERNAL_TAG = "external://url=";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return EXTERNAL_TAG;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ExternalDestination parse(Uri uri) {
        return new ExternalDestination(uri.toString().replace(EXTERNAL_TAG, ""), "", "");
    }
}
