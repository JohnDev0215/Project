package com.amazon.mp3.net.dmls;

/* JADX INFO: loaded from: classes3.dex */
public enum StreamingStatus {
    UNDEFINED("UNDEFINED"),
    PLAY("PLAY"),
    PAUSE("PAUSE"),
    STOP("STOP");

    private String mName;

    StreamingStatus(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public static StreamingStatus fromString(String s) {
        for (StreamingStatus streamingStatus : values()) {
            if (streamingStatus.mName.equals(s)) {
                return streamingStatus;
            }
        }
        return UNDEFINED;
    }
}
