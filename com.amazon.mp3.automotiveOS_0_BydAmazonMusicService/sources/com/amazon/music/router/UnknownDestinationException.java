package com.amazon.music.router;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class UnknownDestinationException extends Exception {
    private final Destination destination;

    public UnknownDestinationException(Destination destination) {
        super("Destination doesn't have registered handlers. Destination:" + destination.getClass().getSimpleName());
        this.destination = destination;
    }

    public Destination getDestination() {
        return this.destination;
    }
}
