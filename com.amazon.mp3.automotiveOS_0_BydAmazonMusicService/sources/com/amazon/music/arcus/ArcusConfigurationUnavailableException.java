package com.amazon.music.arcus;

/* JADX INFO: loaded from: classes4.dex */
public class ArcusConfigurationUnavailableException extends Exception {
    public ArcusConfigurationUnavailableException() {
        super("Something went wrong. Can not return value from Arcus.");
    }

    public ArcusConfigurationUnavailableException(String str) {
        super(str);
    }

    public ArcusConfigurationUnavailableException(String str, Throwable th) {
        super(str, th);
    }
}
