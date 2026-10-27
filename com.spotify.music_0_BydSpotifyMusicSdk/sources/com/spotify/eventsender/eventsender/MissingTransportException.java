package com.spotify.eventsender.eventsender;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class MissingTransportException extends IOException {
    public MissingTransportException() {
        super("No HTTP transport available.");
    }
}
