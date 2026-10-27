package com.amazon.musicplayqueueservice.client.v2.track.types;

/* JADX INFO: loaded from: classes5.dex */
public class ActionEnumValues {
    private static final String[] values = {"NEXT", "PREVIOUS", "SCRUB_FORWARD", "SCRUB_BACKWARD", "RATE_TRACK", "SHUFFLE", "LOOP", "LOOP_ONE", "TRANSFER_QUEUE", "SHOW_QUEUE_VIEW"};

    private ActionEnumValues() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
