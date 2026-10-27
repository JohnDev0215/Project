package com.amazon.mp3.download.generator;

/* JADX INFO: loaded from: classes3.dex */
public class CannotGenerateGroupException extends Exception {
    public final Reason reason;

    public enum Reason {
        NoTracks,
        AlreadyDownloaded,
        AccessDenied
    }

    public CannotGenerateGroupException(final Reason reason) {
        this.reason = reason;
    }
}
